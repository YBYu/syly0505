package com.zzc.web.sylyUtils;

public class HotelGrade {
	/**
	 * 代表非星级
	 */
	public static String grade="0";
	
	public static boolean isXINGJI(String grade){
		switch (grade) {
		case "0":
		case "1":
		case "2":
		case "3":
			return false;
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
			return true;
			
		default:
			throw new RuntimeException("酒店星级不对应:"+grade +"  ,在{其他_0,舒适_1,高档_2,豪华_3,一星_4,二星_5,三星_6,四星_7,五星_8}");
		}
	}
}
