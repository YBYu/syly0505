package com.zzc.app.scenic.entity;

import java.io.Serializable;

/**
 * Created by CC on 2017/1/20. 景区季报实体类
 */
public class ScenicSeasonalReportEntity implements Serializable {

	private String period; // 填报周期 填报的年份
	private String cycle; // 每年的第多少周
	private String income;// 景区收入
	private String incomewithTicket;// 门票收入
	private String peopleCounts;// 接待人数
	private String peopleWithoutTicket;// 免票人数
	private String remarks;// 备注
	private String status;// 审核状态
	private String reportDate; // 报出日期

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ScenicSeasonalReportEntity() {
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getIncomewithTicket() {
		return incomewithTicket;
	}

	public void setIncomewithTicket(String incomewithTicket) {
		this.incomewithTicket = incomewithTicket;
	}

	public String getPeopleCounts() {
		return peopleCounts;
	}

	public void setPeopleCounts(String peopleCounts) {
		this.peopleCounts = peopleCounts;
	}

	public String getPeopleWithoutTicket() {
		return peopleWithoutTicket;
	}

	public void setPeopleWithoutTicket(String peopleWithoutTicket) {
		this.peopleWithoutTicket = peopleWithoutTicket;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ScenicSeasonalReportEntity(String period, String cycle,
			String income, String incomewithTicket, String peopleCounts,
			String peopleWithoutTicket, String remarks, String status) {
		super();
		this.period = period;
		this.cycle = cycle;
		this.income = income;
		this.incomewithTicket = incomewithTicket;
		this.peopleCounts = peopleCounts;
		this.peopleWithoutTicket = peopleWithoutTicket;
		this.remarks = remarks;
		this.status = status;
	}

}
