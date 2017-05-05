package com.zzc.web.otherTravel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @FileName: BoatMonthly.java
 * @Version: 1.0
 * @About: 
 *   游艇月报
 */
@Entity
@Table(name = "t_boat_monthly")
@Inheritance(strategy = InheritanceType.JOINED)
@ExcelTarget("boatMonthly")
public class BoatMonthly extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String otid;//对应游艇企业的id
	@Excel(name="月报时间",orderNum="1",needMerge=true)
	private Date dates;
	@Excel(name="接待人数",orderNum="2",needMerge=true)
	private Integer recepNum;//接待人数
	@Excel(name="营业收入",orderNum="3",needMerge=true)
	private Double income;//营业收入
	private String hisreport;
	private String reporter;//填报人
	private Date writeDate;//填表日期
	private String status;
    private String smonth;//月报时间,
	
	
	@Column(name="smonth")
	public String getSmonth() {
		return smonth;
	}

	public void setSmonth(String smonth) {
		this.smonth = smonth;
	}
	@ExcelEntity(name="name")
	private OtherTravelInfo otherTravelInfo;
	@Column(name="reporter")
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	@Column(name="write_date")
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	@Column(name="oid")
	public String getOtid() {
		return otid;
	}
	public void setOtid(String otid) {
		this.otid = otid;
	}
	@Column(name="recep_num")
	public Integer getRecepNum() {
		return recepNum;
	}
	public void setRecepNum(Integer recepNum) {
		this.recepNum = recepNum;
	}
	@Column(name="income")
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
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
	@Column(name="his_report")
	public String getHisreport() {
		return hisreport;
	}
	public void setHisreport(String hisreport) {
		this.hisreport = hisreport;
	}
	@Column(name="t_date")
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}

	
	

}
