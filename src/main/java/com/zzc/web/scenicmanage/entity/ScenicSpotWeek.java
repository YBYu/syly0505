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
 * @Description: 景区周报实体类
 * @author 冯勇齐
 * @date 2016-11-9 08:23:11
 * 
 */

@Entity
@Table(name = "t_scenicSpot_weekly")
@SuppressWarnings("serial")
public class ScenicSpotWeek implements java.io.Serializable {

	
	
	
	private String   id;
	private String   sid;
	private String   scenicUserId;         //关联景区用户id
	private Date     startdate;             //创建日期
	private Date     enddate;               //结束日期
	@Excel(name="营业收入(万元)")
	private Double   taking;                           
	@Excel(name="其中门票收入(万元)")
	private Double   ticket;
	@Excel(name="接待人数(人次)")
	private Integer   receptionnum;
	@Excel(name="其中境外人数(万元)")
	private Integer   overnum;
	@Excel(name="团散比")
	private String   compare;
	@Excel(name="单位负责人")
	private String   principal;
	@Excel(name="填表人")
	private String   preparer;
	@Excel(name="联系电话")
	private String   telephone;
	@Excel(name="填报日期")
	private Date     reportdate;
	private String     status;
	@Excel(name="年份")
	private String   year;                  //年份
	@Excel(name="周期")
	private Integer  cycle;
	private String weekRange;
	private Integer   week;               
	private byte     hisreport;
	private Integer score;//诚信积分
	@ExcelEntity(name="scenicData.name")
	private ScenicData scenicData;
	//private List<ScenicData>scenicDataList;
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
	
		
	@Column(name ="scenic_user_id",nullable=true,length=50)
	public String getScenicUserId() {
		return scenicUserId;
	}
	public void setScenicUserId(String scenicUserId) {
		this.scenicUserId = scenicUserId;
	}
	@Column(name ="start_date",nullable=true)
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	@Column(name ="end_date",nullable=true)
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	@Column(name ="taking",nullable=true)
	public Double getTaking() {
		return taking;
	}
	public void setTaking(Double taking) {
		this.taking = taking;
	}
	@Column(name ="ticket",nullable=true)
	public Double getTicket() {
		return ticket;
	}
	public void setTicket(Double ticket) {
		this.ticket = ticket;
	}
	@Column(name ="receptionnum",nullable=true)
	public Integer getReceptionnum() {
		return receptionnum;
	}
	public void setReceptionnum(Integer receptionnum) {
		this.receptionnum = receptionnum;
	}
	@Column(name ="overnum",nullable=true)
	public Integer getOvernum() {
		return overnum;
	}
	public void setOvernum(Integer overnum) {
		this.overnum = overnum;
	}
	@Column(name ="compare",nullable=true,length=10)
	public String getCompare() {
		return compare;
	}
	public void setCompare(String compare) {
		this.compare = compare;
	}
	@Column(name ="principal",nullable=true,length=20)
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	@Column(name ="preparer",nullable=true,length=20)
	public String getPreparer() {
		return preparer;
	}
	public void setPreparer(String preparer) {
		this.preparer = preparer;
	}
	@Column(name ="telephone",nullable=true,length=20)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name ="reportdate",nullable=true)
	public Date getReportdate() {
		return reportdate;
	}
	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	@Column(name ="status",nullable=true,length=10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="year",nullable=true,length=10)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name ="cycle",nullable=true,length=10)
	public Integer getCycle() {
		return cycle;
	}
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}
	@Column(name ="his_report",nullable=true,length=4)
	public byte getHisreport() {
		return hisreport;
	}
	public void setHisreport(byte hisreport) {
		this.hisreport = hisreport;
	}
	@Column(name ="week",nullable=true)
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	@Column(name ="score")
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name ="week_range")
	public String getWeekRange() {
		return weekRange;
	}
	public void setWeekRange(String weekRange) {
		this.weekRange = weekRange;
	}
	//关联景区基本信息，多对一
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scenic_id")
	public ScenicData getScenicData() {
		return scenicData;
	}
	public void setScenicData(ScenicData scenicData) {
		this.scenicData = scenicData;
	}  
	
	
	
}
