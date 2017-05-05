package com.zzc.web.scenicmanage.util;

import java.util.Calendar;
import java.util.Date;

import com.sun.star.util.DateTime;

public class TimeUtils {
	
		
	public int myQuarter(DateTime dt){
		
		
	  int myMonth=dt.Month; 
	return myMonth % 3 +1;
	
	
	
	
	}
	
		
	public static int getSeason(Date date) {  
		  
        int season = 0;  
  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int month = c.get(Calendar.MONTH);  
        switch (month) {  
        case Calendar.JANUARY:  
        case Calendar.FEBRUARY:  
        case Calendar.MARCH:  
            season = 1;  
            break;  
        case Calendar.APRIL:  
        case Calendar.MAY:  
        case Calendar.JUNE:  
            season = 2;  
            break;  
        case Calendar.JULY:  
        case Calendar.AUGUST:  
        case Calendar.SEPTEMBER:  
            season = 3;  
            break;  
        case Calendar.OCTOBER:  
        case Calendar.NOVEMBER:  
        case Calendar.DECEMBER:  
            season = 4;  
            break;  
        default:  
            break;  
        }  
        return season;  
    }  

}
