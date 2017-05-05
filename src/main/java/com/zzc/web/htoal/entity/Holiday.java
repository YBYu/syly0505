package com.zzc.web.htoal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.zzc.core.common.entity.IdEntity;
import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelTarget;
/**
 * 
 *                  
 * @date: 2016年11月30日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: Holiday.java
 * @Version: 1.0
 * @About: 节假日模块,
 *
 */
@Entity
@Table(name = "t_holidaymodel")
@PrimaryKeyJoinColumn(name = "id")
@ExcelTarget("holiday")
public class Holiday  extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Excel(name="美兰机场万人次",orderNum="5")
	private Double meilanAir;//美兰机场
	@Excel(name="博鳌机场万人次",orderNum="6")
	private Double boaoAir;// 博鳌机场
	@Excel(name="凤凰机场万人次",orderNum="7")
	private Double fenghuangAir;//凤凰机场
	@Excel(name="海峡办万人次",orderNum="8")
	private Double straitOffice;	//海峡办
	@Excel(name="酒店万人次",orderNum="9")
	private Double hotelNum;//酒店万人次
	@Excel(name="酒店收入万元",orderNum="10")
	private Double hotelIncome;//酒店收入
	@Excel(name="景区数量",orderNum="11")
	private Double scenicNum;//景区数量
	@Excel(name="景区收入",orderNum="12")
	private Double scenicIncome;//景区收入
	@Excel(name="乡村旅游万人次",orderNum="13")
	private Double townTravelNum;// 乡村旅游万人次
	@Excel(name="乡村旅游收入万元",orderNum="14")
	private Double townTravelIncome;//乡村旅游收入
	@Excel(name="免税店数量",orderNum="15")
	private Double taxFreeNum;//免税店数量
	@Excel(name="免税店收入万元",orderNum="16")
	private Double taxFreeIncome;//免税店收入
	@Excel(name="节日类型",orderNum="1",replace={"元旦_0","清明_1","五一_2","端午_3","中秋_4","春节黄金周_5","十一黄金周_6"})
	private String type;//0元旦、1清明、2五一、3端午、4中秋，5春节黄金周、6十一黄金周
	@Excel(name="年份",orderNum="2")
	private String year;//年份
//	@Excel(name="开始时间",orderNum="3")
	private Date startTime;
//	@Excel(name="结束时间",orderNum="4")
	private Date endTime;
//	@Excel(name="酒店名称",orderNum="1")
	private String mname;//填报表的管理员姓名
	
	// 0 未同步,1 已同步
	private String sourceStatus;
	
	@Column(name = "meilanAir")
	public Double getMeilanAir() {
		return meilanAir;
	}
	public void setMeilanAir(Double meilanAir) {
		this.meilanAir = meilanAir;
	}
	@Column(name = "boaoAir")
	public Double getBoaoAir() {
		return boaoAir;
	}
	public void setBoaoAir(Double boaoAir) {
		this.boaoAir = boaoAir;
	}
	@Column(name = "fenghuangAir")
	public Double getFenghuangAir() {
		return fenghuangAir;
	}
	public void setFenghuangAir(Double fenghuangAir) {
		this.fenghuangAir = fenghuangAir;
	}
	@Column(name = "signatureFile")
	public Double getHotelNum() {
		return hotelNum;
	}
	public void setHotelNum(Double hotelNum) {
		this.hotelNum = hotelNum;
	}
	@Column(name = "hotelIncome")
	public Double getHotelIncome() {
		return hotelIncome;
	}
	public void setHotelIncome(Double hotelIncome) {
		this.hotelIncome = hotelIncome;
	}
	@Column(name = "townTravelNum")
	public Double getTownTravelNum() {
		return townTravelNum;
	}
	public void setTownTravelNum(Double townTravelNum) {
		this.townTravelNum = townTravelNum;
	}
	@Column(name = "townTravelIncome")
	public Double getTownTravelIncome() {
		return townTravelIncome;
	}
	public void setTownTravelIncome(Double townTravelIncome) {
		this.townTravelIncome = townTravelIncome;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name = "startTime")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name = "endTime")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "taxFreeNum")
	public Double getTaxFreeNum() {
		return taxFreeNum;
	}
	public void setTaxFreeNum(Double taxFreeNum) {
		this.taxFreeNum = taxFreeNum;
	}
	@Column(name = "taxFreeIncome")
	public Double getTaxFreeIncome() {
		return taxFreeIncome;
	}
	public void setTaxFreeIncome(Double taxFreeIncome) {
		this.taxFreeIncome = taxFreeIncome;
	}
	@Column(name = "straitOffice")
	public Double getStraitOffice() {
		return straitOffice;
	}
	public void setStraitOffice(Double straitOffice) {
		this.straitOffice = straitOffice;
	}
	@Column(name = "scenicNum")
	public Double getScenicNum() {
		return scenicNum;
	}
	public void setScenicNum(Double scenicNum) {
		this.scenicNum = scenicNum;
	}
	@Column(name = "scenicIncome")
	public Double getScenicIncome() {
		return scenicIncome;
	}
	public void setScenicIncome(Double scenicIncome) {
		this.scenicIncome = scenicIncome;
	}
	@Column(name = "m_name")
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	@Column(name="source_status", length=2)
	public String getSourceStatus() {
		return sourceStatus;
	}
	public void setSourceStatus(String sourceStatus) {
		this.sourceStatus = sourceStatus;
	}

}
