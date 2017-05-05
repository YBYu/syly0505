package com.zzc.web.otherTravel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zzc.core.common.entity.IdEntity;
import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelEntity;
import com.zzc.poi.excel.annotation.ExcelTarget;
/**
 * 
 *                  
 * @date: 2016年12月14日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: AirportMonthly.java
 * @Version: 1.0
 * @About: 机场月报
 *
 */
@Entity
@Table(name = "t_airport_monthly")
@Inheritance(strategy = InheritanceType.JOINED)
@ExcelTarget("airportMonthly")
@SuppressWarnings("serial")
public class AirportMonthly extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	//private String id;
	//机场企业id
	/*private String year;
	private String month;*/
	private String otid;
	//月报时间，格式2016-12
	@Excel(name="月报时间",orderNum="1",needMerge=true)
	private Date dates;
	//本月计划完成的运输起降架次
	@Excel(name="本月计划完成的运输起降架次",orderNum="2",needMerge=true)
	private Integer plansortie;
	//本月实际完成的运输起降架次
	@Excel(name="本月实际完成的运输起降架次",orderNum="3",needMerge=true)
	private Integer actualsortie;
	//全年计划完成的运输起降架次
	private Integer plansortieyear;
	//本月计划出发运量-旅客（万人）
	private Double planouttraveller;
	//本月实际完成的出发货运-旅客（万人）
	@Excel(name="本月实际完成的出发货运-旅客（万人）",orderNum="4",needMerge=true)
	private Double actualouttraveller;
	//全年计划完成的出发货运
	private Double planouttravelleryear;
	//本月计划完成的出发运量-货邮行
	private Double planoutwill;
	//本月实际完成的出发运量-货邮行
	private Double actualoutwill;
	//全年计划完成的出发货运-货邮行（吨）
	private Double planoutwillyear;
	//本月计划完成的到达运量-旅客（万人）
	private Double planIntegerraveller;
	//本月实际完成的到达运量-旅客（万人）
	private Double actualIntegerraveller;
	//全年计划完成的到达运量-旅客（万人）
	private Double planIntegerravelleryear;
	//本月计划完成的到达运量-货邮行（吨）
	private Double planinwill;
	//本月实际完成的到达运量-货邮行（吨）
	private Double actualinwill;
	//全年计划完成的到达运量-货邮行（吨）
	private Double planinwillyear;
	//本月计划完成的吞吐量-旅客（万人）
	private Double planthroughputtraveller;
	//本月实际完成的吞吐量-旅客（万人）
	private Double actualthroughputtraveller;
	//全年计划完成的吞吐量-旅客（万人）
	private Double planthroughputtravelleryear;
	//本月计划完成的吞吐量-货邮行（吨）
	private Double planthroughputwill;
	//本月实际完成的吞吐量-货邮行（吨）
	private Double actualthroughputwill;
	//全年计划完成的吞吐量-货邮行（吨）
	private Double planthroughputwillyear;
	//是否代填报，1-是，2-否
	private Integer hisreport;
	//填报人
	private String reporter;
	//填报时间
	private Date reportdate;
	//审核状态，1-未填报，2-未审核，3-管理员已审核（审核不通过），4-管理员已审核（审核通过）
	private String status;
	@ExcelEntity(name="otherTravelInfo.name")
	private OtherTravelInfo otherTravelInfo;
	private String smonth;//月报时间,
/*	@Id
	@Column(name ="ID",nullable=false,length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) { 
		this.id = id;
	}*/
	@Column(name="smonth")
	public String getSmonth() {
		return smonth;
	}

	public void setSmonth(String smonth) {
		this.smonth = smonth;
	}

	
	@Column(name="oid")
	public String getOtid() {
		return otid;
	}
	
	public void setOtid(String otid) {
		this.otid = otid;
	}
	@Column(name="t_date")
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	@Column(name="plan_sortie")
	public Integer getPlansortie() {
		return plansortie;
	}
	public void setPlansortie(Integer plansortie) {
		this.plansortie = plansortie;
	}
	@Column(name="actual_sortie")
	public Integer getActualsortie() {
		return actualsortie;
	}

	public void setActualsortie(Integer actualsortie) {
		this.actualsortie = actualsortie;
	}
	@Column(name="plan_sortie_year")
	public Integer getPlansortieyear() {
		return plansortieyear;
	}
	
	public void setPlansortieyear(Integer plansortieyear) {
		this.plansortieyear = plansortieyear;
	}
	@Column(name="plan_out_traveller")
	public Double getPlanouttraveller() {
		return planouttraveller;
	}
	public void setPlanouttraveller(Double planouttraveller) {
		this.planouttraveller = planouttraveller;
	}
	@Column(name="actual_out_traveller")
	public Double getActualouttraveller() {
		return actualouttraveller;
	}
	public void setActualouttraveller(Double actualouttraveller) {
		this.actualouttraveller = actualouttraveller;
	}
	@Column(name="plan_out_traveller_year")
	public Double getPlanouttravelleryear() {
		return planouttravelleryear;
	}
	public void setPlanouttravelleryear(Double planouttravelleryear) {
		this.planouttravelleryear = planouttravelleryear;
	}
	@Column(name="plan_out_will")
	public Double getPlanoutwill() {
		return planoutwill;
	}
	public void setPlanoutwill(Double planoutwill) {
		this.planoutwill = planoutwill;
	}
	@Column(name="actual_out_will")
	public Double getActualoutwill() {
		return actualoutwill;
	}
	public void setActualoutwill(Double actualoutwill) {
		this.actualoutwill = actualoutwill;
	}
	@Column(name="plan_out_will_year")
	public Double getPlanoutwillyear() {
		return planoutwillyear;
	}
	public void setPlanoutwillyear(Double planoutwillyear) {
		this.planoutwillyear = planoutwillyear;
	}
	@Column(name="plan_in_traveller")
	public Double getPlanIntegerraveller() {
		return planIntegerraveller;
	}
	public void setPlanIntegerraveller(Double planIntegerraveller) {
		this.planIntegerraveller = planIntegerraveller;
	}
	@Column(name="actual_in_traveller")
	public Double getActualIntegerraveller() {
		return actualIntegerraveller;
	}
	public void setActualIntegerraveller(Double actualIntegerraveller) {
		this.actualIntegerraveller = actualIntegerraveller;
	}
	@Column(name="plan_in_traveller_year")
	public Double getPlanIntegerravelleryear() {
		return planIntegerravelleryear;
	}
	public void setPlanIntegerravelleryear(Double planIntegerravelleryear) {
		this.planIntegerravelleryear = planIntegerravelleryear;
	}
	@Column(name="plan_in_will")
	public Double getPlaninwill() {
		return planinwill;
	}
	public void setPlaninwill(Double planinwill) {
		this.planinwill = planinwill;
	}
	@Column(name="actual_in_will")
	public Double getActualinwill() {
		return actualinwill;
	}
	public void setActualinwill(Double actualinwill) {
		this.actualinwill = actualinwill;
	}
	@Column(name="plan_in_will_year")
	public Double getPlaninwillyear() {
		return planinwillyear;
	}
	public void setPlaninwillyear(Double planinwillyear) {
		this.planinwillyear = planinwillyear;
	}
	@Column(name="plan_throughput_traveller")
	public Double getPlanthroughputtraveller() {
		return planthroughputtraveller;
	}
	public void setPlanthroughputtraveller(Double planthroughputtraveller) {
		this.planthroughputtraveller = planthroughputtraveller;
	}
	@Column(name="actual_throughput_traveller")
	public Double getActualthroughputtraveller() {
		return actualthroughputtraveller;
	}
	public void setActualthroughputtraveller(Double actualthroughputtraveller) {
		this.actualthroughputtraveller = actualthroughputtraveller;
	}
	@Column(name="plan_throughput_traveller_year")
	public Double getPlanthroughputtravelleryear() {
		return planthroughputtravelleryear;
	}
	public void setPlanthroughputtravelleryear(
			Double planthroughputtravelleryear) {
		this.planthroughputtravelleryear = planthroughputtravelleryear;
	}
	@Column(name="plan_throughput_will")
	public Double getPlanthroughputwill() {
		return planthroughputwill;
	}
	public void setPlanthroughputwill(Double planthroughputwill) {
		this.planthroughputwill = planthroughputwill;
	}
	@Column(name="actual_throughput_will")
	public Double getActualthroughputwill() {
		return actualthroughputwill;
	}
	public void setActualthroughputwill(Double actualthroughputwill) {
		this.actualthroughputwill = actualthroughputwill;
	}
	@Column(name="plan_throughput_will_year")
	public Double getPlanthroughputwillyear() {
		return planthroughputwillyear;
	}
	public void setPlanthroughputwillyear(Double planthroughputwillyear) {
		this.planthroughputwillyear = planthroughputwillyear;
	}
	@Column(name="his_report")
	public Integer getHisreport() {
		return hisreport;
	}
	public void setHisreport(Integer hisreport) {
		this.hisreport = hisreport;
	}
	@Column(name="reporter")
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	@Column(name="report_date")
	public Date getReportdate() {
		return reportdate;
	}
	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "otravel_id")
	public OtherTravelInfo getOtherTravelInfo() {
		return otherTravelInfo;
	}
	public void setOtherTravelInfo(OtherTravelInfo otherTravelInfo) {
		this.otherTravelInfo = otherTravelInfo;
	}
/*	@Column(name="year",nullable=true,length=10)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="month",nullable=true,length=10)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}*/
	
}
