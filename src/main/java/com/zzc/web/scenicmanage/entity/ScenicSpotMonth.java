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
 * @Description: 景区月报实体类
 * @author 冯勇齐
 * @date 2016-11-27 09:31:20
 * 
 */
@Entity
@Table(name = "t_scenicSpot_monthly")
@SuppressWarnings("serial")
public class ScenicSpotMonth implements java.io.Serializable{
	@ExcelEntity(name="scenicData.name")
	private ScenicData scenicData;
	private String  id;
	private String  monthid;
	public String getMonthid() {
		return monthid;
	}
	private String  userMonthId;
	private String year;
	private String month;
	@Excel(name="总收入(万元)",orderNum="2")
	private Double   taking;
	@Excel(name="门票收入(万元)",orderNum="3")
	private Double   ticket;
	@Excel(name="营业税金及附加(万元)",orderNum="5")
	private double   businesstax;
	@Excel(name="利润总额(万元)",orderNum="4")
	private double   totalprofit;
	@Excel(name="年末从业人员数(人次)",orderNum="6")
	private int   endemployee;   
	@Excel(name="其中吸纳下岗职工(人次)",orderNum="7")//年末从业人员数
	private int   laidworker;     
	@Excel(name="其中转移农村劳动力(人次)",orderNum="8")//其中吸纳下岗职工
	private int   countrylabor;    
	@Excel(name="固定资产原价",orderNum="17")//其中转移农村劳动力
	private double    price;
	@Excel(name="固定资产净值",orderNum="9")
	private double    asset;
	@Excel(name="总接待人数",orderNum="10")
	private Integer   receptionnum;  
	@Excel(name="其中境外人数",orderNum="11")//接待人数
	private Integer   overnum;               
	@Excel(name="其中免票人数",orderNum="12")//其中境外人数
	private Integer   freeticket;   
	@Excel(name="团散比",orderNum="13")//其中免票人数
	private String   compare;
	@Excel(name="单位负责人",orderNum="14")
	private String   principal;
	@Excel(name="填表人",orderNum="15")
	private String   preparer;
	@Excel(name="联系电话",orderNum="16")
	private String   telephone;
	@Excel(name="报出日期",orderNum="2")
	private Date     reportdate;  
	private byte     hisreport;
	private String     status;
	private Integer score;//诚信积分
	
	
	
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
//	@Column(name ="month_id",nullable=false,length=50)
//	public String getMonthid() {
//		return monthid;
//	}
	public void setMonthid(String monthid) {
		this.monthid = monthid;
	}	
	@Column(name ="user_month_id",nullable=true,length=50) 
	public String getUserMonthId() {
		return userMonthId;
	}
	public void setUserMonthId(String userMonthId) {
		this.userMonthId = userMonthId;
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
	@Column(name ="businesstax",nullable=true) 
	public double getBusinesstax() {
		return businesstax;
	}
	public void setBusinesstax(double businesstax) {
		this.businesstax = businesstax;
	}
	@Column(name ="totalprofit",nullable=true) 
	public double getTotalprofit() {
		return totalprofit;
	}
	public void setTotalprofit(double totalprofit) {
		this.totalprofit = totalprofit;
	}
	@Column(name ="endemployee",nullable=true,length=20) 
	public int getEndemployee() {
		return endemployee;
	}
	public void setEndemployee(int endemployee) {
		this.endemployee = endemployee;
	}
	@Column(name ="laidworker",nullable=true,length=20) 
	public int	 getLaidworker() {
		return laidworker;
	}
	public void setLaidworker(int laidworker) {
		this.laidworker = laidworker;
	}
	@Column(name ="countrylabor",nullable=true,length=20) 
	public int getCountrylabor() {
		return countrylabor;
	}
	public void setCountrylabor(int countrylabor) {
		this.countrylabor = countrylabor;
	}
	@Column(name ="price",nullable=true) 
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Column(name ="asset",nullable=true) 
	public double getAsset() {
		return asset;
	}
	public void setAsset(double asset) {
		this.asset = asset;
	}
	@Column(name ="year",nullable=true,length=10) 
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name ="month",nullable=true,length=10) 
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Column(name ="receptionnum",nullable=true,length=50) 
	public Integer getReceptionnum() {
		return receptionnum;
	}
	public void setReceptionnum(Integer receptionnum) {
		this.receptionnum = receptionnum;
	}
	@Column(name ="overnum",nullable=true,length=20) 
	public Integer getOvernum() {
		return overnum;
	}
	public void setOvernum(Integer overnum) {
		this.overnum = overnum;
	}
	@Column(name ="freeticket",nullable=true,length=20) 
	public Integer getFreeticket() {
		return freeticket;
	}
	public void setFreeticket(Integer freeticket) {
		this.freeticket = freeticket;
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
	@Column(name ="his_report",nullable=true) 
	public byte getHisreport() {
		return hisreport;
	}
	public void setHisreport(byte hisreport) {  
		this.hisreport = hisreport;
	}
	@Column(name ="status",nullable=true) 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="score")
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="month_id")
	public ScenicData getScenicData() {
		return scenicData;
	}
	public void setScenicData(ScenicData scenicData) {
		this.scenicData = scenicData;
	}
	
	
	
	
	
	   
	
	
}
