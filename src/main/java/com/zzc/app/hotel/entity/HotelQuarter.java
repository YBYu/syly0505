package com.zzc.app.hotel.entity;

public class HotelQuarter {
	private String userId; // 用户id
	private String year; // 年份
	private String quarter;// 1 2 3 4 第一二三四季度
	private String writer; // 填报人
	private String mobile; // 手机号码
	private String writerDate; // 填报日期
	private String totalIncome; // 总收入(万元)
	private String canteenIncome; // 餐厅收入(万元)
	private String roomIncome; // 客房收入(万元)
	private String otherIncome; // 其他收入(万元)
	private String roomnum; // 客房数量
	private String bednum; // 床位数量
	private String realNights;// 实际出租夜间数
	private String canNights;// 可供出租夜间数

	private String avgHotelPrice; // 平均房价

	private String status;// 审核状态

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWriterDate() {
		return writerDate;
	}

	public void setWriterDate(String writerDate) {
		this.writerDate = writerDate;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getCanteenIncome() {
		return canteenIncome;
	}

	public void setCanteenIncome(String canteenIncome) {
		this.canteenIncome = canteenIncome;
	}

	public String getRoomIncome() {
		return roomIncome;
	}

	public void setRoomIncome(String roomIncome) {
		this.roomIncome = roomIncome;
	}

	public String getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

	public String getBednum() {
		return bednum;
	}

	public void setBednum(String bednum) {
		this.bednum = bednum;
	}

	public String getRealNights() {
		return realNights;
	}

	public void setRealNights(String realNights) {
		this.realNights = realNights;
	}

	public String getCanNights() {
		return canNights;
	}

	public void setCanNights(String canNights) {
		this.canNights = canNights;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAvgHotelPrice() {
		return avgHotelPrice;
	}

	public void setAvgHotelPrice(String avgHotelPrice) {
		this.avgHotelPrice = avgHotelPrice;
	}

}
