package com.zzc.webservice;

import com.zzc.webservice.provinceHotel.WebService_SanYaSoapProxy;
import com.zzc.webservice.travelagency.PublicInterfaceSoapProxy;

public class ServiceInstance {
	
	// 旅行社接口
	private static PublicInterfaceSoapProxy travelAgencyService;
	
	// 酒店省级系统接口
	private static WebService_SanYaSoapProxy hotelProvinceService;
	
	public static synchronized PublicInterfaceSoapProxy getTravelAgencyService(){
		if(travelAgencyService == null){
			travelAgencyService = new PublicInterfaceSoapProxy();
		}
		return travelAgencyService;
	}
	
	public static synchronized WebService_SanYaSoapProxy getHotelProvinceService(){
		if(hotelProvinceService == null){
			hotelProvinceService = new WebService_SanYaSoapProxy();
		}
		return hotelProvinceService;
	}
	
}
