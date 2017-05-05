package com.zzc.app.otherTravel.entity;

/**
 * 服务端获取实体类
 * 
 * @date: 2016年12月14日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: AirportMonthly.java
 * @Version: 1.0
 * @About: 机场月报
 * 
 */
@SuppressWarnings("serial")
public class AirportMonthlyReportEntity implements java.io.Serializable {
	// 对应涉旅的id
	private String userId;
	// 月报时间，格式2016-12
	private String dates;
	// 本月计划完成的运输起降架次
	private String plansortie;
	// 本月实际完成的运输起降架次
	private String actualsortie;
	// 全年计划完成的运输起降架次
	private String plansortieyear;
	// 本月计划出发运量-旅客（万人）
	private String planouttraveller;
	// 本月实际完成的出发货运-旅客（万人）
	private String actualouttraveller;
	// 全年计划完成的出发货运
	private String planouttravelleryear;
	// 本月计划完成的出发运量-货邮行
	private String planoutwill;
	// 本月实际完成的出发运量-货邮行
	private String actualoutwill;
	// 全年计划完成的出发货运-货邮行（吨）
	private String planoutwillyear;
	// 本月计划完成的到达运量-旅客（万人）
	private String planIntegerraveller;
	// 本月实际完成的到达运量-旅客（万人）
	private String actualIntegerraveller;
	// 全年计划完成的到达运量-旅客（万人）
	private String planIntegerravelleryear;
	// 本月计划完成的到达运量-货邮行（吨）
	private String planinwill;
	// 本月实际完成的到达运量-货邮行（吨）
	private String actualinwill;
	// 全年计划完成的到达运量-货邮行（吨）
	private String planinwillyear;
	// 本月计划完成的吞吐量-旅客（万人）
	private String planthroughputtraveller;
	// 本月实际完成的吞吐量-旅客（万人）
	private String actualthroughputtraveller;
	// 全年计划完成的吞吐量-旅客（万人）
	private String planthroughputtravelleryear;
	// 本月计划完成的吞吐量-货邮行（吨）
	private String planthroughputwill;
	// 本月实际完成的吞吐量-货邮行（吨）
	private String actualthroughputwill;
	// 全年计划完成的吞吐量-货邮行（吨）
	private String planthroughputwillyear;
	// 填报人
	private String reporter;
	// 填报时间
	private String reportdate;
	// 审核状态，1-未填报，2-未审核，3-管理员已审核（审核不通过），4-管理员已审核（审核通过）
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

	public String getPlansortie() {
		return plansortie;
	}

	public void setPlansortie(String plansortie) {
		this.plansortie = plansortie;
	}

	public String getActualsortie() {
		return actualsortie;
	}

	public void setActualsortie(String actualsortie) {
		this.actualsortie = actualsortie;
	}

	public String getPlansortieyear() {
		return plansortieyear;
	}

	public void setPlansortieyear(String plansortieyear) {
		this.plansortieyear = plansortieyear;
	}

	public String getPlanouttraveller() {
		return planouttraveller;
	}

	public void setPlanouttraveller(String planouttraveller) {
		this.planouttraveller = planouttraveller;
	}

	public String getActualouttraveller() {
		return actualouttraveller;
	}

	public void setActualouttraveller(String actualouttraveller) {
		this.actualouttraveller = actualouttraveller;
	}

	public String getPlanouttravelleryear() {
		return planouttravelleryear;
	}

	public void setPlanouttravelleryear(String planouttravelleryear) {
		this.planouttravelleryear = planouttravelleryear;
	}

	public String getPlanoutwill() {
		return planoutwill;
	}

	public void setPlanoutwill(String planoutwill) {
		this.planoutwill = planoutwill;
	}

	public String getActualoutwill() {
		return actualoutwill;
	}

	public void setActualoutwill(String actualoutwill) {
		this.actualoutwill = actualoutwill;
	}

	public String getPlanoutwillyear() {
		return planoutwillyear;
	}

	public void setPlanoutwillyear(String planoutwillyear) {
		this.planoutwillyear = planoutwillyear;
	}

	public String getPlanIntegerraveller() {
		return planIntegerraveller;
	}

	public void setPlanIntegerraveller(String planIntegerraveller) {
		this.planIntegerraveller = planIntegerraveller;
	}

	public String getActualIntegerraveller() {
		return actualIntegerraveller;
	}

	public void setActualIntegerraveller(String actualIntegerraveller) {
		this.actualIntegerraveller = actualIntegerraveller;
	}

	public String getPlanIntegerravelleryear() {
		return planIntegerravelleryear;
	}

	public void setPlanIntegerravelleryear(String planIntegerravelleryear) {
		this.planIntegerravelleryear = planIntegerravelleryear;
	}

	public String getPlaninwill() {
		return planinwill;
	}

	public void setPlaninwill(String planinwill) {
		this.planinwill = planinwill;
	}

	public String getActualinwill() {
		return actualinwill;
	}

	public void setActualinwill(String actualinwill) {
		this.actualinwill = actualinwill;
	}

	public String getPlaninwillyear() {
		return planinwillyear;
	}

	public void setPlaninwillyear(String planinwillyear) {
		this.planinwillyear = planinwillyear;
	}

	public String getPlanthroughputtraveller() {
		return planthroughputtraveller;
	}

	public void setPlanthroughputtraveller(String planthroughputtraveller) {
		this.planthroughputtraveller = planthroughputtraveller;
	}

	public String getActualthroughputtraveller() {
		return actualthroughputtraveller;
	}

	public void setActualthroughputtraveller(String actualthroughputtraveller) {
		this.actualthroughputtraveller = actualthroughputtraveller;
	}

	public String getPlanthroughputtravelleryear() {
		return planthroughputtravelleryear;
	}

	public void setPlanthroughputtravelleryear(
			String planthroughputtravelleryear) {
		this.planthroughputtravelleryear = planthroughputtravelleryear;
	}

	public String getPlanthroughputwill() {
		return planthroughputwill;
	}

	public void setPlanthroughputwill(String planthroughputwill) {
		this.planthroughputwill = planthroughputwill;
	}

	public String getActualthroughputwill() {
		return actualthroughputwill;
	}

	public void setActualthroughputwill(String actualthroughputwill) {
		this.actualthroughputwill = actualthroughputwill;
	}

	public String getPlanthroughputwillyear() {
		return planthroughputwillyear;
	}

	public void setPlanthroughputwillyear(String planthroughputwillyear) {
		this.planthroughputwillyear = planthroughputwillyear;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
