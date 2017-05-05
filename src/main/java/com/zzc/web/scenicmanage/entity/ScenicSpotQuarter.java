package com.zzc.web.scenicmanage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelEntity;


/**
 * @Title: 
 * @Description: 景区季报实体类
 * @author 冯勇齐
 * @date 2016-12-9 15:31:20
 * 
 */
@Entity
@Table(name = "t_scenicSpot_quarterly")
@SuppressWarnings("serial")
public class ScenicSpotQuarter implements java.io.Serializable{

	
	@ExcelEntity(name="scenicData.name")
	private ScenicData scenicData;
	private  String    id;
	private  String   sid;
	@Excel(name="年份",orderNum="2")
	private  Integer   year;                      //年份
	@Excel(name="季度",orderNum="3")
	private  Integer   quarter;                   //季度
	@Excel(name="总收入(万元)",orderNum="4")
	private  double    totalincome;               //总收入
	@Excel(name="门票收入(万元)",orderNum="5")
	private  double    ticketincome;              //门票收入
	@Excel(name="接待人次(万人)",orderNum="6")       
	private  double    receptionnum;              //接待人次
	@Excel(name="免票人次(万人)",orderNum="7")
	private  double    exemptticketnum;           //免票人次
	@Excel(name="备注",orderNum="8")
	private  String    remarks;                   //备注
	private  String      status;                  //季报审核状态
	private  String      hisreport;
	private  Date  reportdate;                    //填报日期
	private  Integer 		score;	//诚信积分
	private String provinceSubmit;//是否提交给上级系统
	private String comments;//审核意见 
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="sid",nullable=true,length=50)
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	@Column(name ="year",nullable=true,length=10) 
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Column(name ="quarter",nullable=true,length=10) 
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	@Column(name ="total_income",nullable=true) 
	public double getTotalincome() {
		return totalincome;
	}
	public void setTotalincome(double totalincome) {
		this.totalincome = totalincome;
	}
	@Column(name ="ticket_income",nullable=true) 
	public double getTicketincome() {
		return ticketincome;
	}
	public void setTicketincome(double ticketincome) {
		this.ticketincome = ticketincome;
	}
	@Column(name ="reception_num",nullable=true) 
	public double getReceptionnum() {
		return receptionnum;
	}
	public void setReceptionnum(double receptionnum) {
		this.receptionnum = receptionnum;
	}
	@Column(name ="exempt_ticket_num",nullable=true) 
	public double getExemptticketnum() {
		return exemptticketnum;
	}
	public void setExemptticketnum(double exemptticketnum) {
		this.exemptticketnum = exemptticketnum;
	}
	@Column(name ="remarks",nullable=true,length=200) 
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name ="status",nullable=true,length=10) 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="his_report",nullable=true,length=10) 
	public String getHisreport() {
		return hisreport;
	}
	public void setHisreport(String hisreport) {
		this.hisreport = hisreport;
	}
	@Column(name ="score")
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name ="province_submit")
	public String getProvinceSubmit() {
		return provinceSubmit;
	}
	public void setProvinceSubmit(String provinceSubmit) {
		this.provinceSubmit = provinceSubmit;
	}
	@Column(name ="comments",length=2000)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	//关联景区基本信息，多对一
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="quarter_id")
	public ScenicData getScenicData() {
		return scenicData;
	}
	public void setScenicData(ScenicData scenicData) {
		this.scenicData = scenicData;
	}
	@Column(name ="reportdate") 
	public Date getReportdate() {
		return reportdate;
	}
	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	
	
	
}
