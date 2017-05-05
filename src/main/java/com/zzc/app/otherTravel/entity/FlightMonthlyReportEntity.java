package com.zzc.app.otherTravel.entity;

/**
 * 空中飞行-月报实体类
 */
public class FlightMonthlyReportEntity implements java.io.Serializable {
	private String userId;
	// 月报时间，格式：2016-12
	private String dates;
	// 接待人次
	private String receptionnum;
	// 营业收入
	private String businessincome;
	// 飞行架次
	private String flighttimes;

	// 审核状态，1-未填报，2-未审核，3-管理员已审核（审核通过），4-管理员已审核（审核不通过）
	private String status;
	private String writeDate;// 填表日期
	private String reporter;// 填报人

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getReceptionnum() {
		return receptionnum;
	}

	public void setReceptionnum(String receptionnum) {
		this.receptionnum = receptionnum;
	}

	public String getBusinessincome() {
		return businessincome;
	}

	public void setBusinessincome(String businessincome) {
		this.businessincome = businessincome;
	}

	public String getFlighttimes() {
		return flighttimes;
	}

	public void setFlighttimes(String flighttimes) {
		this.flighttimes = flighttimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
