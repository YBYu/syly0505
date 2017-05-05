package com.zzc.web.sylyUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 *                  
 * @date: 2016年12月6日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: TimeUtils.java
 * @Version: 1.0
 * @About: 
 *获取需要的时间
 */
public class TimeUtils {
	/**
	 * 
	 * @date: 2016年12月6日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: date
	 * @return:获取yyyy-MM的日期类型
	 * 
	 */
	public static String getCurrentMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String yearMonth = sdf.format(date);
		return yearMonth;
	}
	/**
	 * 
	 * @date: 2016年12月6日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: date
	 * @return:获取yyyy-MM的日期类型
	 * 
	 */
	public static Date getFornartTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(date);
		GregorianCalendar gc= new GregorianCalendar();
		 gc.setTime(date);
		 return gc.getTime();
	}
	/**
	 * 
	 * @date: 2016年12月7日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return: 获取当前月份的上一个月 yyyy-MM格式
	 */
	
	public static String getLastMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		GregorianCalendar gc= new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.MONTH, -1);
		String lastMonth = sdf.format(gc.getTime());
		return lastMonth;
	}
	public static String getLastmonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc= new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.MONTH, -1);
		String lastMonth = sdf.format(gc.getTime());
		return lastMonth;
	}
	/**
	 * 
	 * @date: 2016年12月7日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return: 获取当前月份的上一个月 yyyy-MM格式
	 */
	
	public static Date getLastMonthD(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		GregorianCalendar gc= new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.MONTH, -1);
		//String lastMonth = sdf.format(gc.getTime());
		return gc.getTime();
	}
	/**
	 * 
	 * @date: 2016年12月7日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return: 获取 yyyyMMdd格式
	 */
	
	public static String getMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		//GregorianCalendar gc= new GregorianCalendar();
		//gc.setTime(new Date());
		String dates = sdf.format(date);
		//gc.add(GregorianCalendar.MONTH, -1);
		//String lastMonth = sdf.format(gc.getTime());
		return dates;
	}
	/**
	 * 
	 * @date: 2016年12月7日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:yyyy-MM-dd
	 */
	public static String getTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//sdf.format(date);
		return sdf.format(date);
	}
	/** @date: 2016年12月7日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:将日期类型的字符串 转换为指定格式yyyy-MM
	 * 
	 */
	public static String getTimes(String time){
		//Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		//sdf.format(date);
		return sdf.format(time);
	}
	/**
	 * 
	 * @date: 2016年12月9日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:获取当年年份
	 */
	public static String getYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		GregorianCalendar gc= new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.YEAR, 0);
		Date i = gc.getTime();
		String year = sdf.format(i);
		return year;
	}
	/**
	 * 
	 * @date: 2016年12月13日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:获取去年的时间
	 */
	public static String getLastYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		GregorianCalendar gc= new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.YEAR, -1);
		Date i = gc.getTime();
		String year = sdf.format(i);
		return year;
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
            season = 4;  
            break;  
        case Calendar.APRIL:  
        case Calendar.MAY:  
        case Calendar.JUNE:  
            season = 1;  
            break;  
        case Calendar.JULY:  
        case Calendar.AUGUST:  
        case Calendar.SEPTEMBER:  
            season = 2;  
            break;  
        case Calendar.OCTOBER:  
        case Calendar.NOVEMBER:  
        case Calendar.DECEMBER:  
            season = 3;  
            break;  
        default:  
            break;  
        }  
        return season;  
    }  
	
	public static String excelExportTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

}
