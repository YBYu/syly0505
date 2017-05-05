package com.zzc.web.travel.service;

import com.zzc.web.travel.entity.TravelAnnualFinance;
import com.zzc.web.travel.entity.TravelQuarterIn;
import com.zzc.web.travel.entity.TravelQuarterInland;
import com.zzc.web.travel.entity.TravelQuarterOut;
import com.zzc.web.travel.entity.TraveldataAnnual;


public interface TravelService {
	public String addOrUpdateTravelInfo(TraveldataAnnual traveldataAnnual);
	
	public String[] syncTavelInfo(TraveldataAnnual traveldataAnnual);
	
	public String addOrUpdateTravelFinance(TravelAnnualFinance travelannualfinance);
	
	public String[] getFinance(TravelAnnualFinance travelannualfinance);
	
	public String addOrUpdateEntryTravelQuarter(TravelQuarterIn travelQuarterIn);
	
	public String addOrUpdateLeaveTravelQuarter(TravelQuarterOut travelQuarterOut);
	
	public String addOrUpdateInlandTravelQuarter(TravelQuarterInland travelQuarterInland);
	
	public String[] getQuarterStatus(String year, String quarter, String license);
}
