package com.zzc.app.scenic.entity;

import java.io.Serializable;

/**
 * Created by CC on 2017/1/20. 景区月报实体类
 */
public class ScenicMonthlyReportEntity implements Serializable {

	/*
	 * private String legalPersonCode; // 法人单位编码
	 */private String period; // 填报周期 填报的年份
	private String cycle; // 每年的第多少周
	private String taxes; // 营业税及附加
	private String totalIncome; // 利润总额
	private String employee; // 年底从业人员
	private String employeeLaidOff; // 吸纳下岗职工
	private String employeeCountry; // 转移农村劳动力
	private String fixedAssetsOriginal; // 固定资产原价
	private String fixedAssetsPure; // 固定资产净值
	private String peopleCounts; // 接待人数
	private String peopleOutBounds; // 境外人数
	private String peopleWithoutTicket; // 免票人数
	private String rate; // 团散比率
	private String responsiblePeople; // 负责人
	private String writer; // 填报人
	private String phone; // 电话
	private String reportDate; // 报出日期
	private String userId;
	private String status;
	private String businessIncome;// 营业收入
	private String ticketIncome;// 门票收入

	public String getBusinessIncome() {
		return businessIncome;
	}

	public void setBusinessIncome(String businessIncome) {
		this.businessIncome = businessIncome;
	}

	public String getTicketIncome() {
		return ticketIncome;
	}

	public void setTicketIncome(String ticketIncome) {
		this.ticketIncome = ticketIncome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ScenicMonthlyReportEntity() {
	}

	/*
	 * public String getLegalPersonCode() { return legalPersonCode; }
	 * 
	 * public void setLegalPersonCode(String legalPersonCode) {
	 * this.legalPersonCode = legalPersonCode; }
	 */

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

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEmployeeLaidOff() {
		return employeeLaidOff;
	}

	public void setEmployeeLaidOff(String employeeLaidOff) {
		this.employeeLaidOff = employeeLaidOff;
	}

	public String getEmployeeCountry() {
		return employeeCountry;
	}

	public void setEmployeeCountry(String employeeCountry) {
		this.employeeCountry = employeeCountry;
	}

	public String getFixedAssetsOriginal() {
		return fixedAssetsOriginal;
	}

	public void setFixedAssetsOriginal(String fixedAssetsOriginal) {
		this.fixedAssetsOriginal = fixedAssetsOriginal;
	}

	public String getFixedAssetsPure() {
		return fixedAssetsPure;
	}

	public void setFixedAssetsPure(String fixedAssetsPure) {
		this.fixedAssetsPure = fixedAssetsPure;
	}

	public String getPeopleCounts() {
		return peopleCounts;
	}

	public void setPeopleCounts(String peopleCounts) {
		this.peopleCounts = peopleCounts;
	}

	public String getPeopleOutBounds() {
		return peopleOutBounds;
	}

	public void setPeopleOutBounds(String peopleOutBounds) {
		this.peopleOutBounds = peopleOutBounds;
	}

	public String getPeopleWithoutTicket() {
		return peopleWithoutTicket;
	}

	public void setPeopleWithoutTicket(String peopleWithoutTicket) {
		this.peopleWithoutTicket = peopleWithoutTicket;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getResponsiblePeople() {
		return responsiblePeople;
	}

	public void setResponsiblePeople(String responsiblePeople) {
		this.responsiblePeople = responsiblePeople;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ScenicMonthlyReportEntity(String period, String cycle, String taxes,
			String totalIncome, String employee, String employeeLaidOff,
			String employeeCountry, String fixedAssetsOriginal,
			String fixedAssetsPure, String peopleCounts,
			String peopleOutBounds, String peopleWithoutTicket, String rate,
			String responsiblePeople, String writer, String phone,
			String reportDate, String userId, String status) {
		super();
		this.period = period;
		this.cycle = cycle;
		this.taxes = taxes;
		this.totalIncome = totalIncome;
		this.employee = employee;
		this.employeeLaidOff = employeeLaidOff;
		this.employeeCountry = employeeCountry;
		this.fixedAssetsOriginal = fixedAssetsOriginal;
		this.fixedAssetsPure = fixedAssetsPure;
		this.peopleCounts = peopleCounts;
		this.peopleOutBounds = peopleOutBounds;
		this.peopleWithoutTicket = peopleWithoutTicket;
		this.rate = rate;
		this.responsiblePeople = responsiblePeople;
		this.writer = writer;
		this.phone = phone;
		this.reportDate = reportDate;
		this.userId = userId;
		this.status = status;
	}

}
