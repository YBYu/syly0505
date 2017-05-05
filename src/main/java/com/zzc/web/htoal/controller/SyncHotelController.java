package com.zzc.web.htoal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzc.web.htoal.entity.Holiday;
import com.zzc.web.htoal.entity.HotelAnnual;
import com.zzc.web.htoal.entity.HotelEstimate;
import com.zzc.web.htoal.entity.HotelMonthly;
import com.zzc.web.htoal.entity.HotelQuarter;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.HotelmanageSta;
import com.zzc.web.htoal.service.Hotelservice;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.touristoffices.entity.TouristOffices;

@Component
public class SyncHotelController {
	
	private static SystemService systemService;
	
	private static Hotelservice hotelService;
	
	@Autowired
	public SyncHotelController(SystemService systemService, Hotelservice hotelService){
		SyncHotelController.systemService = systemService;
		SyncHotelController.hotelService = hotelService;
	}
	
	/**
	 * 提交酒店数据至省和国家系统
	 * 
	 */
	public static void sendHotelInfo(){
		// 所有酒店的集合
		List<HotelmanageSta> list = systemService.getList(HotelmanageSta.class);
		
		// 需要同步到省系统的集合
		List<HotelmanageSta> provinceList = new ArrayList<>();
		// 需要同步到国家系统的集合
		List<HotelmanageSta> countryList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			HotelmanageSta hotelmanageSta = list.get(i);
			// 过滤审核通过的数据
			if(!"4".equals(hotelmanageSta.getStatus())) continue;
			if("1".equals(hotelmanageSta.getSourceType()) || "3".equals(hotelmanageSta.getSourceType())){
				if(!"1".equals(hotelmanageSta.getProvinceStatus())) continue;
				provinceList.add(hotelmanageSta);
			}
			if("2".equals(hotelmanageSta.getSourceType()) || "3".equals(hotelmanageSta.getSourceType())){
				if(!"1".equals(hotelmanageSta.getCountryStatus())) continue;
				countryList.add(hotelmanageSta);
			}
		}
		
		// 国家系统同步
		for (int i = 0; i < countryList.size(); i++) {
			HotelmanageSta hotelmanageSta = countryList.get(i);
			Hotelmanage hotelmanage = HotelmanageSta.copy(hotelmanageSta);
			
			// 过滤非星级酒店
			String housegrade = hotelmanage.getHousegrade();
			if(housegrade == null || housegrade.length() == 0) continue;
			if(Integer.parseInt(housegrade) < 4) continue;
			
			String type = "";
			String rs = "";
			// 审核时间为空则代表新建
			if(hotelmanage.getRevocateCountry() == null) {
				type = "add";
			}else{
				type = "edit";
			}
			rs = hotelService.addHotel(hotelmanage, type);
			
			// 判断接口操作是否成功
			if(!"200".equals(rs)) continue;
			// 审核通过后更新数据
			hotelmanageSta.setCountryStatus(Status.noAudit);
			hotelmanageSta.setVerifyTime(new Date());
			systemService.saveOrUpdate(hotelmanageSta);
			
			hotelmanage.setCountryStatus(Status.noAudit);
			hotelmanage.setVerifyTime(new Date());
			systemService.saveOrUpdate(hotelmanage);						
		}
		
		// 省系统同步
		for (int i = 0; i < provinceList.size(); i++) {
			HotelmanageSta hotelmanageSta = provinceList.get(i);
			Hotelmanage hotelmanage = HotelmanageSta.copy(hotelmanageSta);
			String rs = "";
			// 审核时间为空则代表新建
			if(hotelmanage.getRevocateProvince() == null) {
				// 如果国家系统通过审核后进行省系统同步
//				if(!"4".equals(hotelmanage.getCountryStatus())) continue;
				rs = hotelService.addProvinceHotel(hotelmanage);
			}else{
				// 如果国家系统通过审核后进行省系统同步
//				if(!"4".equals(hotelmanage.getCountryStatus())) continue;
				rs = hotelService.editProvinceHotel(hotelmanage);
			}						
			// 判断接口操作是否成功
			if(!"200".equals(rs)) continue;
			// 审核通过后更新数据
			hotelmanageSta.setProvinceStatus(Status.noAudit);
			hotelmanageSta.setVerifyTime1(new Date());
			systemService.saveOrUpdate(hotelmanageSta);

			hotelmanage.setProvinceStatus(Status.noAudit);
			hotelmanage.setVerifyTime1(new Date());
			systemService.saveOrUpdate(hotelmanage);
		}				
	}
	
	/**
	 * 提交酒店月报至省系统
	 * 
	 */
	public static void sendHotelMonthly(){
		// 获取所有月报
		List<HotelMonthly> list = systemService.getList(HotelMonthly.class);
		
		// 待提交的月报
		List<HotelMonthly> sendList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			HotelMonthly hotelMonthly = list.get(i);
			// 过滤未通过审核的月报
			if(!"4".equals(hotelMonthly.getStatus())) continue;
			// 过滤出已通过审核的月报
			if(!"1".equals(hotelMonthly.getProvinceState())) continue;
			// 过滤国家系统酒店的月报
			Hotelmanage hotelManage = systemService.get(Hotelmanage.class, hotelMonthly.getHotelId());
			if("1".equals(hotelManage.getSourceType()) || "3".equals(hotelManage.getSourceType())){
				sendList.add(hotelMonthly);
			}
		}
		
		for (int i = 0; i < sendList.size(); i++) {
			HotelMonthly hotelMonthly = sendList.get(i);
			String rs = "";
			if("1".equals(hotelMonthly.getRevocated())){
				rs = hotelService.modiflyAuditAndMonthly(hotelMonthly);
			}else{
				rs = hotelService.addAuditAndMonthly(hotelMonthly);
			}
			// 提交成功后改变状态
			if("200".equals(rs)){
				hotelMonthly.setProvinceState(Status.noAudit);
				systemService.saveOrUpdate(hotelMonthly);
			}
		}
	}
	
	/**
	 * 提交酒店季报至国家系统
	 * 
	 */
	public static void sendHotelQuarter(){
		// 所有季报集合
		List<HotelQuarter> list = systemService.getList(HotelQuarter.class);
		for (int i = 0; i < list.size(); i++) {
			HotelQuarter hotelQuarter = list.get(i);
			Hotelmanage hotelmanage = systemService.get(Hotelmanage.class, hotelQuarter.getHotelQid());
			// 过滤省系统酒店的季报
			if(!"2".equals(hotelmanage.getSourceType()) && !"3".equals(hotelmanage.getSourceType())) continue;
			if(Status.passAudit.equals(hotelQuarter.getStatus()) && Status.noSumbit.equals(hotelQuarter.getCountryState())){
				String rs = hotelService.addQuarterReport(hotelQuarter);
				if("200".equals(rs)){
					hotelQuarter.setCountryState(Status.noAudit);
					systemService.saveOrUpdate(hotelQuarter);
				}
			}
		}
	}

	/**
	 * 提交酒店年报至国家系统
	 * 
	 */
	public static void sendHotelAnnual(){
		// 所有季报集合
		List<HotelAnnual> list = systemService.getList(HotelAnnual.class);
		for (int i = 0; i < list.size(); i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			HotelAnnual hotelAnnual = list.get(i);
			Hotelmanage hotelmanage = systemService.get(Hotelmanage.class, hotelAnnual.getHotelAid());
			// 过滤省系统酒店的季报
			if(!"2".equals(hotelmanage.getSourceType()) && !"3".equals(hotelmanage.getSourceType())) continue;
			if(Status.passAudit.equals(hotelAnnual.getStatus()) && Status.noSumbit.equals(hotelAnnual.getCountryState())){
				String rs = hotelService.addAnnualReport(hotelAnnual);
				if("200".equals(rs)){
					hotelAnnual.setCountryState(Status.noAudit);
					systemService.saveOrUpdate(hotelAnnual);
				}
			}
		}
	}
	
	/**
	 * 同步省和国家系统酒店数据至本系统
	 * 
	 */
	public static void syncHotelInfo(){
		// 所有酒店的集合
		List<HotelmanageSta> list = systemService.getList(HotelmanageSta.class);
		
		// 省系统的集合
		List<HotelmanageSta> provinceList = new ArrayList<>();
		// 国家系统的集合
		List<HotelmanageSta> countryList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			HotelmanageSta hotelmanageSta = list.get(i);
			
			if("2".equals(hotelmanageSta.getProvinceStatus())){
				provinceList.add(hotelmanageSta);
			}
			if("2".equals(hotelmanageSta.getCountryStatus())){
				countryList.add(hotelmanageSta);
			}
		}
		
		// 更新省系统数据
		for (int i = 0; i < provinceList.size(); i++) {
			HotelmanageSta hotelmanageSta = provinceList.get(i);
			Hotelmanage hotelmanage = HotelmanageSta.copy(hotelmanageSta);
			String rs = hotelService.getProvinceHotelInfo(hotelmanageSta.getUsername());
			// 通过
			if("4".equals(rs)){
				hotelmanageSta.setProvinceStatus("4");
				hotelmanage.setProvinceStatus("4");
			}
			// 退回
			if("3".equals(rs)){
				hotelmanageSta.setProvinceStatus("3");
				hotelmanage.setProvinceStatus("3");
			}
			systemService.saveOrUpdate(hotelmanageSta);
			systemService.saveOrUpdate(hotelmanage);
		}
		
		// 更新国家系统数据
		for (int i = 0; i < countryList.size(); i++) {
			HotelmanageSta hotelmanageSta = countryList.get(i);
			Hotelmanage hotelmanage = HotelmanageSta.copy(hotelmanageSta);
			List<Hotelmanage> hotelList = hotelService.getCountryHotelInfo();
			for (int j = 0; j < hotelList.size(); j++) {
				Hotelmanage temp = hotelList.get(j);
				if(hotelmanageSta.getCode().equals(temp.getCode())){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					// 通过
					if("1".equals(temp.getCountryStatus())){
						hotelmanageSta.setCountryStatus("4");
						hotelmanage.setCountryStatus("4");
					}
					// 未通过
					if("2".equals(temp.getCountryStatus())){
						hotelmanageSta.setCountryStatus("3");
						hotelmanage.setCountryStatus("3");
					}
					// 获取国家系统审核意见
					String countryContent = hotelService.getBasicInfoContent(temp.getCode());
					hotelmanageSta.setGuo(countryContent);
					hotelmanage.setGuo(countryContent);
					systemService.saveOrUpdate(hotelmanageSta);
					systemService.saveOrUpdate(hotelmanage);
				}
			}
		}		
	}
	
	/**
	 * 同步省月报数据至本系统
	 * 
	 */
	public static void syncHotelMonthly(){
		// 获取所有月报
		List<HotelMonthly> list = systemService.getList(HotelMonthly.class);
		
		for (int i = 0; i < list.size(); i++) {
			HotelMonthly hotelMonthly = list.get(i);
			// 过滤未通过审核的月报
			if(!"4".equals(hotelMonthly.getStatus())) continue;
			// 过滤出已通过审核的月报
			if(!"2".equals(hotelMonthly.getProvinceState())) continue;
			Hotelmanage hotelManage = systemService.get(Hotelmanage.class, hotelMonthly.getHotelId());
			// 过滤国家酒店的月报数据
			if(!"1".equals(hotelManage.getSourceType()) && !"3".equals(hotelManage.getSourceType())) continue;
			// 对本系统已通过且省系统待审核的月报进行过滤
			if("4".equals(hotelMonthly.getStatus()) && "2".equals(hotelMonthly.getProvinceState())){
				String rs = hotelService.getProvinceMonthlyInfo(hotelMonthly);
				// 不通过 
				if("3".equals(rs)){
					hotelMonthly.setProvinceState(Status.notAudit);
				}
				// 通过
				if("4".equals(rs)){
					hotelMonthly.setProvinceState(Status.passAudit);
				}
				systemService.saveOrUpdate(hotelMonthly);
			}
		}
	}
	
	/**
	 * 同步国家季报数据至本系统
	 * 
	 */
	public static void syncHotelQuarter(){
		// 获取所有季报
		List<HotelQuarter> list = systemService.getList(HotelQuarter.class);
		
		for (int i = 0; i < list.size(); i++) {
			HotelQuarter hotelQuarter = list.get(i);
			Hotelmanage hotelManage = systemService.get(Hotelmanage.class, hotelQuarter.getHotelQid());
			// 过滤省系统的季报数据
			if(!"2".equals(hotelManage.getSourceType()) && !"3".equals(hotelManage.getSourceType())) continue;
			// 对已通过本系统审核的数据进行过滤
			if("4".equals(hotelQuarter.getStatus()) && "2".equals(hotelQuarter.getCountryState())){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				String rs = hotelService.getQuarterReport(hotelQuarter);
				// 通过
				if("1".equals(rs)){
					hotelQuarter.setCountryState(Status.passAudit);
				}
				// 不通过 
				if("2".equals(rs)){
					hotelQuarter.setCountryState(Status.notAudit);
				}
				String countryContent = hotelService.getQuarterReportContent(hotelManage.getCode(), String.valueOf(hotelQuarter.getQuarter()), hotelQuarter.getYear());
				hotelQuarter.setGuo(countryContent);
				systemService.saveOrUpdate(hotelQuarter);
			}
		}
	}
	
	/**
	 * 同步国家年报数据至本系统
	 * 
	 */
	public static void syncHotelAnnual(){
		// 获取所有年报
		List<HotelAnnual> list = systemService.getList(HotelAnnual.class);
		
		for (int i = 0; i < list.size(); i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			HotelAnnual hotelAnnual = list.get(i);
			Hotelmanage hotelManage = systemService.get(Hotelmanage.class, hotelAnnual.getHotelAid());
			// 过滤省系统的年报数据
			if(!"2".equals(hotelManage.getSourceType()) && !"3".equals(hotelManage.getSourceType())) continue;
			// 对已通过本系统审核的数据进行过滤
			if("4".equals(hotelAnnual.getStatus()) && "2".equals(hotelAnnual.getCountryState())){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				String rs = hotelService.getAnnualReport(hotelAnnual);
				// 通过
				if("1".equals(rs)){
					hotelAnnual.setCountryState(Status.passAudit);
				}
				// 不通过 
				if("2".equals(rs)){
					hotelAnnual.setCountryState(Status.notAudit);
				}
				String countryContent = hotelService.getAnnaulReportContent(hotelManage.getCode(), hotelAnnual.getYear());
				hotelAnnual.setGuo(countryContent);
				systemService.saveOrUpdate(hotelAnnual);
			}
		}
	}
	
	/**
	 * 提交预计接待数据至国家系统
	 * 
	 */
	public static void sendHotelEstimate(){
		List<HotelEstimate> list = systemService.getList(HotelEstimate.class);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			HotelEstimate hotelEstimate = (HotelEstimate) iterator.next();
			if("0".equals(hotelEstimate.getSourceStatus())){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				String rs = hotelService.addHotelEstimate(hotelEstimate);
				if("200".equals(rs)){
					hotelEstimate.setSourceStatus("1");
					systemService.saveOrUpdate(hotelEstimate);
				}
			}
		}
	}
	
	/**
	 * 提交停业数据
	 */
	public static void sendOnBuiness(){
		List<Hotelmanage> seasonList = systemService.findByProperty(Hotelmanage.class, "onBuinessSeasonSync", "1");
		for (Iterator iterator = seasonList.iterator(); iterator.hasNext();) {
			Hotelmanage hotelmanage = (Hotelmanage) iterator.next();
			if("1".equals(hotelmanage.getSourceType())) continue;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			String rs = hotelService.seasonStop(hotelmanage);
			if("200".equals(rs)){
				hotelmanage.setOnBuinessSeasonSync("0");
			}
			systemService.saveOrUpdate(hotelmanage);
		}
		List<Hotelmanage> yearList = systemService.findByProperty(Hotelmanage.class, "onBuinessYearSync", "1");
		for (Iterator iterator = yearList.iterator(); iterator.hasNext();) {
			Hotelmanage hotelmanage = (Hotelmanage) iterator.next();
			if("1".equals(hotelmanage.getSourceType())) continue;
			String rs = hotelService.yearStop(hotelmanage);
			if("200".equals(rs)){
				hotelmanage.setOnBuinessYearSync("0");
			}
			systemService.saveOrUpdate(hotelmanage);
		}
	}
	
	/**
	 * 新增旅游局
	 */
	public static void addTourism(){
		List<TouristOffices> list = systemService.findByProperty(TouristOffices.class, "needAdd", "1");
		hotelService.addTourism(list);
	}
	
	/**
	 * 编辑旅游局
	 */
	public static void editTourism(){
		List<TouristOffices> list = systemService.findByProperty(TouristOffices.class, "needEdit", "1");
		hotelService.editTourism(list);
	}
	
	/**
	 * 新增端午节数据
	 */
	public static void addDragonBoatFestival(){
		List<Holiday> list = systemService.getList(Holiday.class);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Holiday holiday = (Holiday) iterator.next();
			if("0".equals(holiday.getSourceStatus()) && "3".equals(holiday.getType())){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				String rs = hotelService.addDragonBoatFestival(holiday);
				if("200".equals(rs)){
					holiday.setSourceStatus("1");
					systemService.saveOrUpdate(holiday);
				}
			}
		}
	}
	
	/**
	 * 新增端午节数据
	 */
	public static void addMoonFestival(){
		List<Holiday> list = systemService.getList(Holiday.class);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Holiday holiday = (Holiday) iterator.next();
			if("0".equals(holiday.getSourceStatus()) && "4".equals(holiday.getType())){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				String rs = hotelService.addMoonFestival(holiday);
				if("200".equals(rs)){
					holiday.setSourceStatus("1");
					systemService.saveOrUpdate(holiday);
				}
			}
		}
	
	}
}
