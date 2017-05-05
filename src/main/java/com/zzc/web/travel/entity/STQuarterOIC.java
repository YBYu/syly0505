package com.zzc.web.travel.entity;
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
import com.zzc.poi.excel.annotation.ExcelTarget;
/**
 * 
 *                  
 * @date: 2017年1月18日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: STQuarterOIC.java
 * @Version: 1.0
 * @About: 外联人次和接待人次之和统计表
 *
 */
@Entity      
@Table(name = "t_travelAgency_quarterly1")
@SuppressWarnings("serial")
@ExcelTarget("travelQuarterIn")
public class STQuarterOIC implements java.io.Serializable {
	
	private String id;
	@ExcelEntity()
	private Traveldata traveldata;              //关系属性
	@Excel(name="年份",needMerge=true)
    private String year;
    @Excel(name="季度",needMerge=true)
    private String quarter;
    //入境游客外联人次数
    @Excel(name="入境外联人次",needMerge=true)
    private Integer inGuestOne;
    //入境游客接待人次数
    @Excel(name="入境接待人次数",needMerge=true)
    private Integer inGuestTwo;
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
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name = "quarter")
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
    @Column(name ="in_guest_one",nullable=true,length=50)
	public Integer getInGuestOne() {
		return inGuestOne;
	}
	public void setInGuestOne(Integer inGuestOne) {
		this.inGuestOne = inGuestOne;
	}
	@Column(name ="in_guest_two",nullable=true,length=50)
	public Integer getInGuestTwo() {
		return inGuestTwo;
	}
	public void setInGuestTwo(Integer inGuestTwo) {
		this.inGuestTwo = inGuestTwo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="tid",insertable=false,updatable=false)
	public Traveldata getTraveldata() {
		return traveldata;
	}
	public void setTraveldata(Traveldata traveldata) {
		this.traveldata = traveldata;
	}
    
}
