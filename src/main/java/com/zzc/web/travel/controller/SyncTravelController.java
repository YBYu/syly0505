package com.zzc.web.travel.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.TravelAnnualFinance;
import com.zzc.web.travel.entity.TravelQuarterIn;
import com.zzc.web.travel.entity.TravelQuarterInland;
import com.zzc.web.travel.entity.TravelQuarterOut;
import com.zzc.web.travel.entity.Traveldata;
import com.zzc.web.travel.entity.TraveldataAnnual;
import com.zzc.web.travel.service.TravelService;

@Component
public class SyncTravelController {
	
	private static SystemService systemService;
	
	private static TravelService travelService;
	
	@Autowired
	public SyncTravelController(SystemService systemService, TravelService travelService){
		SyncTravelController.systemService = systemService;
		SyncTravelController.travelService = travelService;
	}
	
	/**
	 * 提交旅行社至省系统
	 */
	public static void sendTravelInfo(){
		// 获取所有旅行社信息
		List<TraveldataAnnual> list = systemService.getList(TraveldataAnnual.class);
		
		// 获取去年的时间，
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		
		// 同步数据
		for (int i = 0; i < list.size(); i++) {
			TraveldataAnnual traveldataAnnual = list.get(i);
			// 只同步去年的数据
			if(!year.equals(traveldataAnnual.getYear())) continue;
			// 过滤出市级审核通过的数据
			if(!"1".equals(traveldataAnnual.getStatus())) continue;
			
			String rs = travelService.addOrUpdateTravelInfo(traveldataAnnual);
			// 提交成功
			if("200".equals(rs)){
				traveldataAnnual.setStatus("4");
				systemService.saveOrUpdate(traveldataAnnual);
			}
		}
	}
	
	/**
	 * 同步旅行社审核数据
	 */
	public static void syncTravelInfo(){
		// 获取所有旅行社信息
		List<TraveldataAnnual> list = systemService.getList(TraveldataAnnual.class);
		
		// 同步数据
		for (int i = 0; i < list.size(); i++) {
			TraveldataAnnual traveldataAnnual = list.get(i);
			// 过滤出市级审核通过的数据
			if(!"4".equals(traveldataAnnual.getStatus())) continue;
			String[] rs = travelService.syncTavelInfo(traveldataAnnual);
			// 是否同步成功
			if(rs == null) continue;
			String provinceState = rs[0];
			String provinceContent = rs[1];
			// 通过
			if("1".equals(provinceState)){
				traveldataAnnual.setStatus("6");
				traveldataAnnual.setGuo(provinceContent);
			}
			// 未通过
			if("2".equals(provinceState)){
				traveldataAnnual.setStatus("5");
				traveldataAnnual.setGuo(provinceContent);
			}
			systemService.saveOrUpdate(traveldataAnnual);
		}				
	}
	
	/**
	 * 提交财务年报至省系统
	 */
	public static void sendFinance(){
		// 获取所有财务年报
		List<TravelAnnualFinance> list = systemService.getList(TravelAnnualFinance.class);
		
		for (int i = 0; i < list.size(); i++) {
			TravelAnnualFinance travelAnnualFinance = list.get(i);
			// 过滤出市级审核通过的年报
			if(!"4".equals(travelAnnualFinance.getStatus())) continue;
			String rs = travelService.addOrUpdateTravelFinance(travelAnnualFinance);
			if("200".equals(rs)){
				travelAnnualFinance.setStatus("5");
				systemService.saveOrUpdate(travelAnnualFinance);
			}
		}
	}
	
	/**
	 * 同步年报审核数据
	 */
	public static void syncFinance(){
		// 获取所有财务年报
		List<TravelAnnualFinance> list = systemService.getList(TravelAnnualFinance.class);
		
		for (int i = 0; i < list.size(); i++) {
			TravelAnnualFinance travelAnnualFinance = list.get(i);
			// 过滤出市级审核通过的年报
			if(!"5".equals(travelAnnualFinance.getStatus())) continue;
			String[] rs = travelService.getFinance(travelAnnualFinance);
			// 判断是否成功同步
			if(rs == null) continue;
			String provinceState = rs[0];
			String provinceContent = rs[1]; 
			// 通过
			if("1".equals(provinceState)){
				travelAnnualFinance.setStatus("7");
			}
			// 退回
			if("2".equals(provinceState)){
				travelAnnualFinance.setStatus("6");
			}
			travelAnnualFinance.setGuo(provinceContent);
			systemService.saveOrUpdate(travelAnnualFinance);
		}				
	}
	
	/**
	 * 发送季报数据
	 */
	public static void sendQuarter(){
		// 年和季度
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String quarter = getQuarter();
		quarter = String.valueOf(Integer.parseInt(quarter) - 1);
		// 对跨年的情况做处理
		if("0".equals(quarter)){
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			quarter = "4";
		}
		
		// 获取所有旅行社
		List<Traveldata> list = systemService.getList(Traveldata.class);
		
		for (int i = 0; i < list.size(); i++) {
			Traveldata traveldata = list.get(i);
			// 获取旅行社关联的季报
			List<TravelQuarterIn> entryList = systemService.findByProperty(TravelQuarterIn.class, "tid", traveldata.getId());
			List<TravelQuarterOut> leaveList = systemService.findByProperty(TravelQuarterOut.class, "tid", traveldata.getId());
			List<TravelQuarterInland> inlandList =  systemService.findByProperty(TravelQuarterInland.class, "tid", traveldata.getId());
			
			// 查找上季度的季报
			TravelQuarterIn travelQuarterIn = null;
			TravelQuarterOut travelQuarterOut = null;
			TravelQuarterInland travelQuarterInland = null;
			for (int j = 0; j < entryList.size(); j++) {
				TravelQuarterIn temp = entryList.get(j);
				if(year.equals(temp.getYear()) && quarter.equals(temp.getQuarter())){
					travelQuarterIn = temp;
					break;
				}
			}
			// 未填报则直接跳过
			if(travelQuarterIn == null) continue;
			
			for (int j = 0; j < entryList.size(); j++) {
				TravelQuarterOut temp = leaveList.get(j);
				if(year.equals(temp.getYear()) && quarter.equals(temp.getQuarter())){
					travelQuarterOut = temp;
					break;
				}
			}
			// 未填报则直接跳过
			if(travelQuarterOut == null) continue;
			
			for (int j = 0; j < inlandList.size(); j++) {
				TravelQuarterInland temp = inlandList.get(j);
				if(year.equals(temp.getYear()) && quarter.equals(temp.getQuarter())){
					travelQuarterInland = temp;
					break;
				}
			}
			// 未填报则直接跳过
			if(travelQuarterInland == null) continue;
			
			// 再次检查
			if(travelQuarterIn == null || travelQuarterOut == null || travelQuarterInland == null) continue;
			// 全部通过审核后才进行同步
			if("4".equals(travelQuarterIn.getStatus()) && "4".equals(travelQuarterOut.getStatus()) && "4".equals(travelQuarterInland.getStatus())){
				try {
					travelService.addOrUpdateEntryTravelQuarter(travelQuarterIn);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					travelService.addOrUpdateLeaveTravelQuarter(travelQuarterOut);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					travelService.addOrUpdateInlandTravelQuarter(travelQuarterInland);
				} catch (Exception e) {
					e.printStackTrace();
				}
				travelQuarterIn.setStatus("5");
				travelQuarterOut.setStatus("5");
				travelQuarterInland.setStatus("5");
				systemService.saveOrUpdate(travelQuarterIn);
				systemService.saveOrUpdate(travelQuarterOut);
				systemService.saveOrUpdate(travelQuarterInland);
			}
		}
	}
	
	/**
	 * 同步季报数据
	 */
	public static void syncQuarter(){
		// 年和季度
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String quarter = getQuarter();
		quarter = String.valueOf(Integer.parseInt(quarter) - 1);
		// 对跨年的情况做处理
		if("0".equals(quarter)){
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			quarter = "4";
		}
		
		// 获取所有旅行社
		List<Traveldata> list = systemService.getList(Traveldata.class);
		
		for (int i = 0; i < list.size(); i++) {
			Traveldata traveldata = list.get(i);
			// 获取旅行社关联的季报
			List<TravelQuarterIn> entryList = systemService.findByProperty(TravelQuarterIn.class, "tid", traveldata.getId());
			List<TravelQuarterOut> leaveList = systemService.findByProperty(TravelQuarterOut.class, "tid", traveldata.getId());
			List<TravelQuarterInland> inlandList =  systemService.findByProperty(TravelQuarterInland.class, "tid", traveldata.getId());
			
			// 查找上季度的季报
			TravelQuarterIn travelQuarterIn = null;
			TravelQuarterOut travelQuarterOut = null;
			TravelQuarterInland travelQuarterInland = null;
			for (int j = 0; j < entryList.size(); j++) {
				TravelQuarterIn temp = entryList.get(j);
				if(year.equals(temp.getYear()) && quarter.equals(temp.getQuarter())){
					travelQuarterIn = temp;
					break;
				}
			}
			// 未填报则直接跳过
			if(travelQuarterIn == null) continue;
			
			for (int j = 0; j < entryList.size(); j++) {
				TravelQuarterOut temp = leaveList.get(j);
				if(year.equals(temp.getYear()) && quarter.equals(temp.getQuarter())){
					travelQuarterOut = temp;
					break;
				}
			}
			// 未填报则直接跳过
			if(travelQuarterOut == null) continue;
			
			for (int j = 0; j < inlandList.size(); j++) {
				TravelQuarterInland temp = inlandList.get(j);
				if(year.equals(temp.getYear()) && quarter.equals(temp.getQuarter())){
					travelQuarterInland = temp;
					break;
				}
			}
			// 未填报则直接跳过
			if(travelQuarterInland == null) continue;
			
			// 再次检查
			if(travelQuarterIn == null || travelQuarterOut == null || travelQuarterInland == null) continue;
			// 全部为待审核才进行状态同步
			if("5".equals(travelQuarterIn.getStatus()) && "5".equals(travelQuarterOut.getStatus()) && "5".equals(travelQuarterInland.getStatus())){
				String[] rs = travelService.getQuarterStatus(year, quarter, traveldata.getLicensenum());
				if(rs == null) continue;
				String provinceState = rs[0];
				String provinceContent = rs[1];
				// 通过
				if("1".equals(provinceState)){
					traveldata.setQuarterStatus("7");
					travelQuarterIn.setStatus("7");
					travelQuarterOut.setStatus("7");
					travelQuarterInland.setStatus("7");
				}
				// 未通过
				if("2".equals(provinceState)){
					traveldata.setQuarterStatus("6");
					travelQuarterIn.setStatus("6");
					travelQuarterOut.setStatus("6");
					travelQuarterInland.setStatus("6");
				}
				travelQuarterIn.setGuo(provinceContent);
				travelQuarterOut.setGuo(provinceContent);
				travelQuarterInland.setGuo(provinceContent);
				systemService.saveOrUpdate(travelQuarterIn);
				systemService.saveOrUpdate(travelQuarterOut);
				systemService.saveOrUpdate(travelQuarterInland);
			}
		}
	}
	
	private static String getQuarter(){
		Calendar calendar = Calendar.getInstance();
		int nowMonth = calendar.get(Calendar.MONTH) +1;
		String quarter = "";
		if(nowMonth <= 3) quarter = "1";
		if(3 < nowMonth && nowMonth <= 6) quarter = "2";
		if(6 < nowMonth && nowMonth <= 9) quarter = "3";
		if(9 < nowMonth && nowMonth <= 12) quarter = "4";
		return quarter;
	}
}
