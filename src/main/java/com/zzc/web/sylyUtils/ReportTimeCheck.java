package com.zzc.web.sylyUtils;

import java.util.Calendar;

public class ReportTimeCheck {
	
	//周
	public static boolean WeekTimeCheck(int year,int week) {
		boolean tip = false;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		
		// 当前月
		int nowYear = calendar.get(Calendar.YEAR);
		int nowWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		int nowDay = calendar.get(Calendar.DAY_OF_WEEK)-1;
		int hour=calendar.get(Calendar.HOUR_OF_DAY);
		if(week!=52){
			if(nowYear==year){
				if(week==nowWeek-1){
					if(nowDay>1){
						tip=false;
					}else{
						if(hour<=15){
							tip=true;
						}else{
							tip=false;
						}
					}
				}else{
					tip=false;
				}
			}
		
		}else{
			if(year==nowYear-1){
				if(week==1){
					if(hour<=15){
						tip=true;
					}
				}
			}
		}
	
		
		
		return tip;
	}
	public static boolean monthTimeCheck(int year,int month,int day){
		boolean tip = false;
		Calendar calendar = Calendar.getInstance();
		// 当前月
		int nowYear = calendar.get(Calendar.YEAR);
		int nowMonth = calendar.get(Calendar.MONTH) + 1;
		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		if(month!=12){
			if(year==nowYear){
				if (nowMonth > month + 1) {
					tip =false;
				} else {
					if (nowDay <= day) {
						tip = true;
					}else {
						tip = false;
					}
				}
			}
			
		}else{
			if(year!=nowYear-1){
				tip=false;
			}else{
				if(nowMonth>1){
					tip=false;
				}else{
					if(nowDay<=day){
						tip=true;
					}else{
						tip=false;
					}
				}
			}
		}
		

		return tip;
	}
	public static boolean quarterTimeCheck(int year, int quarter) {
		boolean tip = false;
		Calendar calendar = Calendar.getInstance();
		
		// 当前月
		int nowYear = calendar.get(Calendar.YEAR);
		int nowMonth = calendar.get(Calendar.MONTH) + 1;
		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		switch (quarter) {
		case 1:
			// 4月之前成功
			if(year != nowYear) {
				tip = false;
			}else{
				if(nowMonth > 4){
					tip = false;
				}else{
					if(nowDay <= 10) tip = true;
					else tip = false;
				}
			}
			break;
		case 2:
			if(year != nowYear) {
				tip = false;
			}else{
				if(nowMonth > 7){
					tip = false;
				}else{
					if(nowDay <= 10) tip = true;
					else tip = false;
				}
			}
			break;
		case 3:
			if(year != nowYear) {
				tip = false;
			}else{
				if(nowMonth > 10){
					tip = false;
				}else{
					if(nowDay <= 10) tip = true;
					else tip = false;
				}
			}
			break;
		case 4:
			if(year < nowYear - 1) {
				tip = false;
			}else{
				if(nowMonth >1){
					tip = false;
				}else{
					if(nowDay <= 10) tip = true;
					else tip = false;
				}
			}
			break;
		}
		return tip;
	}
	
	public static boolean annualTimeCheck(int year) {
		boolean tip = false;
		Calendar calendar = Calendar.getInstance();
		
		// 当前月
		int nowYear = calendar.get(Calendar.YEAR);
		int nowMonth = calendar.get(Calendar.MONTH) + 1;
		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		if(year < nowYear - 1) {
			tip = false;
		}
		else{
			if(nowMonth < 2){
				tip = true;
			}else{
				if(nowDay <= 15) tip = true;
				else tip = false;
			}
		}
		
		return tip;
	}
}
