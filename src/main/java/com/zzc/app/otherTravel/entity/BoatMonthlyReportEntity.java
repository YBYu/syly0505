package com.zzc.app.otherTravel.entity;

/**
 * 
 * 
 * @date: 2016年12月14日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: BoatMonthly.java
 * @Version: 1.0
 * @About: 游艇月报
 */

public class BoatMonthlyReportEntity implements java.io.Serializable {
	// 对应企业的id
	private String userId;
	private String dates;
	private String recepNum;// 接待人数
	private String income;// 营业收入
	private String reporter;// 填报人
	private String writeDate;// 填表日期
	private String status;

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

	public String getRecepNum() {
		return recepNum;
	}

	public void setRecepNum(String recepNum) {
		this.recepNum = recepNum;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
