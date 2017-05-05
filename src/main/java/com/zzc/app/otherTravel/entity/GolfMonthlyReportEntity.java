package com.zzc.app.otherTravel.entity;

public class GolfMonthlyReportEntity implements java.io.Serializable {

	// 对应企业的id
	private String userId;
	// 月报时间，格式：2016-12
	private String dates;
	// 接待人次（单位：人次）
	private String receptionnum;
	// 营业收入（万元）
	private String businessincome;
	// private String hisreport;
	private String status;
	private String writeDate;// 填表日期

	private String reporter;// 填报人

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	/*
	 * public String getHisreport() { return hisreport; }
	 * 
	 * public void setHisreport(String hisreport) { this.hisreport = hisreport;
	 * }
	 */

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

}
