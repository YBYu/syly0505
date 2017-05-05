package com.zzc.app.scenic.entity;

import java.util.Date;

public class ScenicMonth {
	// 营业税金及附加(万元)
	private Double businesstax;
	// 利润总额(万元)
	private Double totalprofit;
	// 年末从业人员数(人次)
	private Integer endemployee;
	// 其中吸纳下岗职工(人次)
	private Integer laidworker;
	// 其中转移农村劳动力(人次)
	private Integer countrylabor;
	// 固定资产原价"
	private Double price;
	// 年末从业人员数"
	private Double asset;
	// 总接待人数
	private Integer receptionnum;
	// 其中境外人数
	private Integer overnum;
	// 其中免票人数
	private Integer freeticket;
	// 团散比
	private String compare;
	// 单位负责人
	private String principal;
	// 填表人
	private String preparer;
	// 联系电话
	private String telephone;
	// 报出日期
	private Date reportdate;
	// 年份
	private String year;
	// 月份
	private String month;
	// 用户id
	private String userID;

	public Double getBusinesstax() {
		return businesstax;
	}

	public void setBusinesstax(Double businesstax) {
		this.businesstax = businesstax;
	}

	public Double getTotalprofit() {
		return totalprofit;
	}

	public void setTotalprofit(Double totalprofit) {
		this.totalprofit = totalprofit;
	}

	public Integer getEndemployee() {
		return endemployee;
	}

	public void setEndemployee(Integer endemployee) {
		this.endemployee = endemployee;
	}

	public Integer getLaidworker() {
		return laidworker;
	}

	public void setLaidworker(Integer laidworker) {
		this.laidworker = laidworker;
	}

	public Integer getCountrylabor() {
		return countrylabor;
	}

	public void setCountrylabor(Integer countrylabor) {
		this.countrylabor = countrylabor;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAsset() {
		return asset;
	}

	public void setAsset(Double asset) {
		this.asset = asset;
	}

	public Integer getReceptionnum() {
		return receptionnum;
	}

	public void setReceptionnum(Integer receptionnum) {
		this.receptionnum = receptionnum;
	}

	public Integer getOvernum() {
		return overnum;
	}

	public void setOvernum(Integer overnum) {
		this.overnum = overnum;
	}

	public Integer getFreeticket() {
		return freeticket;
	}

	public void setFreeticket(Integer freeticket) {
		this.freeticket = freeticket;
	}

	public String getCompare() {
		return compare;
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

	public Date getReportdate() {
		return reportdate;
	}

	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
