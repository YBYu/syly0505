package com.zzc.web.htoal.service;


import java.util.List;

import com.zzc.core.common.service.CommonService;
import com.zzc.web.htoal.entity.Holiday;
import com.zzc.web.htoal.entity.HotelAnnual;
import com.zzc.web.htoal.entity.HotelEstimate;
import com.zzc.web.htoal.entity.HotelMonthly;
import com.zzc.web.htoal.entity.HotelQuarter;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.touristoffices.entity.TouristOffices;
/**
 * 
 *                  
 * @date: 2016年11月22日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: Hotelservice.java
 * @Version: 1.0
 * @About: 酒店信息展示的接口
 *
 */
public interface Hotelservice extends CommonService{

	public TSUser checkUserExits(TSUser user);
	public String getUserRole(TSUser user);
	public void pwdInit(TSUser user, String newPwd);
	
	// 基本信息
	public String addHotel(Hotelmanage hotelmanage, String type);
	
	public String addProvinceHotel(Hotelmanage hotelmanages);
	
	public String editProvinceHotel(Hotelmanage hotelmanages);
	
	public String getProvinceHotelInfo(String username);
	
	public List<Hotelmanage> getCountryHotelInfo();
	
	public String getBasicInfoContent(String code);
	
	// 月报
	public String addAuditAndMonthly(HotelMonthly hotelMonthly);
	
	public String modiflyAuditAndMonthly(HotelMonthly hotelMonthly);
	
	public String getProvinceMonthlyInfo(HotelMonthly hotelMonthly);
	
	// 季报
	public String addQuarterReport(HotelQuarter hotelQuarter);
	
	public String getQuarterReport(HotelQuarter hotelQuarter);
	
	public String getQuarterReportContent(String code, String quarter, String year);
	
	// 年报
	public String addAnnualReport(HotelAnnual hotelAnnual);
	
	public String getAnnualReport(HotelAnnual hotelAnnual);
	
	public String getAnnaulReportContent(String code, String year);
	
	// 旅游局
	public String addTourism(List<TouristOffices> list);
	
	public String editTourism(List<TouristOffices> list);
	
	// 预计接待数据
	public String addHotelEstimate(HotelEstimate hotelEstimate);
	
	// 季度停业
	public String seasonStop(Hotelmanage hotelmanage);
	
	// 年度停业
	public String yearStop(Hotelmanage hotelmanage);
	
	// 节假日-端午节
	public String addDragonBoatFestival(Holiday holiday);
	// 节假日-中秋节
	public String addMoonFestival(Holiday holiday);
}
