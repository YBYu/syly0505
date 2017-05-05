package com.zzc.app.scenic.entity;

import java.io.Serializable;

public class ScenicWeek implements Serializable {
	// 营业收入(万元)
	private String taking;
	// "其中门票收入(万元)
	private String ticket;
	// "接待人数(人次)"
	private String receptionnum;
	// "其中境外人数(万元)"
	private String overnum;
	// "团散比"
	private String compare;
	// "单位负责人"
	private String principal;
	// "填表人"
	private String preparer;
	// "联系电话"
	private String telephone;
	// "填报日期")
	private String reportdate;
	// "年份"
	private String year;
	// "周期")
	private String cycle;
	// 用户id
	private String userId;
	// 审核状态
	private String status;

	public String getTaking() {
		return taking;
	}

	public void setTaking(String taking) {
		this.taking = taking;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getReceptionnum() {
		return receptionnum;
	}

	public void setReceptionnum(String receptionnum) {
		this.receptionnum = receptionnum;
	}

	public String getOvernum() {
		return overnum;
	}

	public void setOvernum(String overnum) {
		this.overnum = overnum;
	}

	public String getCompare() {
		return compare;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCompare(String compare) {
		this.compare = compare;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPreparer() {
		return preparer;
	}

	public void setPreparer(String preparer) {
		this.preparer = preparer;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

}
