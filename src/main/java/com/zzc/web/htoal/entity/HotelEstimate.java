package com.zzc.web.htoal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


//import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelTarget;

/**
 * 
 *                  
 * @date: 2016年11月29日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelMonthly.java
 * @Version: 1.0
 * @About: 
 *酒店预计接待
 */
@Entity
@Table(name = "t_hotelEstimate")
@SuppressWarnings("serial")
@ExcelTarget("hotelEstimate")
public class HotelEstimate  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;	
	private String  id;
	private String countryCode;//地区编码
	@Excel(name="年份",orderNum="1")
	private String year;//年份
	//private String month;//月份
	@Excel(name="月份",orderNum="2")
	private String month;//月份
	private String smonth;//字符串类型的日期
	  //private String hid;//
	@Excel(name="大陆人次",orderNum="4")
	private Integer domestic;//国内
	@Excel(name="外国人次",orderNum="5")
	private Integer foreignTimes;
	@Excel(name="其中(澳门人次)",orderNum="6")
	private Integer macao;
	@Excel(name="其中(香港人次)",orderNum="7")
	private Integer hongkong;
	//@Excel(name="大陆人次",orderNum="3")
	@Excel(name="其中(台湾人次)",orderNum="8")
	private Integer taiwan;
	@Excel(name="总数",orderNum="9")
	private Integer sum;//总和
	@Excel(name="入境人次",orderNum="3")
	private Integer entry;//入境
	private Date writeDate;
	private String type;//0代表人次 1 代表人天
	
	// 0 未同步,1 已同步
	private String sourceStatus;

	//预计报表填报时间
	private String createTime;
	@Excel(name="大陆人天",orderNum="10")//D 代表人天
	private Integer domesticD;//国内
	@Excel(name="外国人天",orderNum="11")
	private Integer foreignDays;
	@Excel(name="其中(澳门人天)",orderNum="12")
	private Integer macaoD;
	@Excel(name="其中(香港人天)",orderNum="13")
	private Integer hongkongD;
	//@Excel(name="大陆人次",orderNum="3")
	@Excel(name="其中(台湾人天)",orderNum="14")
	private Integer taiwanD;
	@Excel(name="总数人天",orderNum="15")
	private Integer sumD;//总和
	@Excel(name="入境人天",orderNum="9")
	private Integer entryD;//入境

	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="t_entry",nullable=true)
	public Integer getEntry() {
		//return entry=taiwan+hongkong+macao+foreign;
		return entry;
	}
	public void setEntry(Integer entry) {
		this.entry = entry;
	}
	private String bymanager;//接待表填报人
	/*@Column(name ="t_organization_num",nullable=true)
	public String getOrganizationNum() {
		return organizationNum;
	}
	public void setOrganizationNum(String organizationNum) {
		this.organizationNum = organizationNum;
	}*/
/*	@Column(name ="t_name",nullable=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name =name;
	}*/
	@Column(name ="t_month",nullable=true)
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name ="t_macao",nullable=true)
	public Integer getMacao() {
		return macao;
	}
	public void setMacao(Integer macao) {
		this.macao = macao;
	}
	@Column(name ="t_hongkong",nullable=true)
	public Integer getHongkong() {
		return hongkong;
	}
	public void setHongkong(Integer hongkong) {
		this.hongkong = hongkong;
	}
	@Column(name ="t_taiwan",nullable=true)
	public Integer getTaiwan() {
		return taiwan;
	}
	public void setTaiwan(Integer taiwan) {
		this.taiwan = taiwan;
	}
	//总和
	@Column(name ="t_sums",nullable=true)
	public Integer getSum() {
		//return sum = taiwan+hongkong+macao+foreign+domestic;
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	@Column(name ="t_write_date",nullable=true)
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	@Column(name ="t_type",nullable=true)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name ="countryCode",nullable=true)
    public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@Column(name ="year",nullable=true)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name ="bymanager",nullable=true)
	public String getBymanager() {
		return bymanager;
	}
	public void setBymanager(String bymanager) {
		this.bymanager = bymanager;
	}
	@Column(name ="create_time",nullable=true)
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name ="s_month",nullable=true)
	public String getSmonth() {
		return smonth;
	}
	public void setSmonth(String smonth) {
		this.smonth = smonth;
	}
	@Column(name ="s_domestic",nullable=true)
	public Integer getDomestic() {
		return domestic;
	}
	public void setDomestic(Integer domestic) {
		this.domestic = domestic;
	}
	@Column(name ="s_domestic_d",nullable=true)
	public Integer getDomesticD() {
		return domesticD;
	}
	public void setDomesticD(Integer domesticD) {
		this.domesticD = domesticD;
	}
	@Column(name ="t_macao_d",nullable=true)
	public Integer getMacaoD() {
		return macaoD;
	}
	public void setMacaoD(Integer macaoD) {
		this.macaoD = macaoD;
	}
	@Column(name ="t_hongkong_d",nullable=true)
	public Integer getHongkongD() {
		return hongkongD;
	}
	public void setHongkongD(Integer hongkongD) {
		this.hongkongD = hongkongD;
	}
	@Column(name ="t_taiwan_d",nullable=true)
	public Integer getTaiwanD() {
		return taiwanD;
	}
	public void setTaiwanD(Integer taiwanD) {
		this.taiwanD = taiwanD;
	}
	//总和
	@Column(name ="t_sums_d",nullable=true)
	public Integer getSumD() {
		//return sum = taiwan+hongkong+macao+foreign+domestic;
		return sumD;
	}
	public void setSumD(Integer sumD) {
		this.sumD = sumD;
	}
	@Column(name ="t_entry_d",nullable=true)
	public Integer getEntryD() {
		return entryD;
	}
	public void setEntryD(Integer entryD) {
		this.entryD = entryD;
	}
	@Column(name="t_foreigns")
	public Integer getForeignTimes() {
		return foreignTimes;
	}
	public void setForeignTimes(Integer foreignTimes) {
		this.foreignTimes = foreignTimes;
	}
	@Column(name="t_foreigns_d")
	public Integer getForeignDays() {
		return foreignDays;
	}
	public void setForeignDays(Integer foreignDays) {
		this.foreignDays = foreignDays;
	}
	@Column(name="source_status", length=2)
	public String getSourceStatus() {
		return sourceStatus;
	}
	public void setSourceStatus(String sourceStatus) {
		this.sourceStatus = sourceStatus;
	}
	
	
}
