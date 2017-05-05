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
@Entity
@Table(name = "t_golf_monthly")
@Inheritance(strategy = InheritanceType.JOINED)
@ExcelTarget("golfMonthly")
public class GolfMonthly extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	//private String id;
	//高尔夫企业id,如果没有高尔夫企业，默认是1，表示高尔夫项目月报
	private String otid;
	//月报时间，格式：2016-12
	@Excel(name="月报时间",orderNum="1",needMerge=true)
	private Date dates;
	//接待人次（单位：人次）
	@Excel(name="接待人次",orderNum="2",needMerge=true)
	private Integer receptionnum;
	//营业收入（万元）
	@Excel(name="营业收入(万元)",orderNum="2",needMerge=true)
	private Double businessincome;
	private String hisreport;	
	private String status;
	private Date writeDate;//填表日期
	/*@Excel(name="企业名称",orderNum="1",needMerge=true)
	private String name;//企业名称 来源于涉旅机构类
*/	@ExcelEntity(name="name")
	private OtherTravelInfo otherTravelInfo;
    private String reporter;//填报人
    private String smonth;//月报时间,
	
	
	@Column(name="smonth")
	public String getSmonth() {
		return smonth;
	}

	public void setSmonth(String smonth) {
		this.smonth = smonth;
	}
    @Column(name="reporter")
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
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
	@Column(name="reception_num")
	public Integer getReceptionnum() {
		return receptionnum;
	}
	public void setReceptionnum(Integer receptionnum) {
		this.receptionnum = receptionnum;
	}
	@Column(name="business_income")
	public Double getBusinessincome() {
		return businessincome;
	}
	public void setBusinessincome(Double businessincome) {
		this.businessincome = businessincome;
	}
	@Column(name="his_report")
	public String getHisreport() {
		return hisreport;
	}
	public void setHisreport(String hisreport) {
		this.hisreport = hisreport;
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
	@Column(name="write_date")
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	
	
	
}
