package com.zzc.app.otherTravel.entity;


public class MotorcarMonthlyReportEntity implements java.io.Serializable {

	// 对应企业的id
	private String userId;
	// 月报时间，格式：2016-12
	private String dates;
	// 接待人次（单位：人次）
	private String innum;
	// 出站人数
	private String outnum;
	// 吞吐量
	private String throughput;
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

	public String getInnum() {
		return innum;
	}

	public void setInnum(String innum) {
		this.innum = innum;
	}

	public String getOutnum() {
		return outnum;
	}

	public void setOutnum(String outnum) {
		this.outnum = outnum;
	}

	public String getThroughput() {
		return throughput;
	}

	public void setThroughput(String throughput) {
		this.throughput = throughput;
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

}
