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



@Entity      //3 旅行社组织接待国内旅游情况表
@Table(name = "t_travelAgency_quarterly3")
@SuppressWarnings("serial")
public class STQuarterInlandRecDay implements java.io.Serializable{
	

	private String  id;
    private String tid;
 
    private String  indexname;
    private String  code;
    private Integer personTime;
    private Integer personDay;
    private String  reportPerson;
    private String  reportTelephone;
    private String  reportDate;
    private String status;
    @Excel(name="年份",needMerge=true) 
    private String year;
    @Excel(name="季度",needMerge=true) 
    private String quarter;
    
    @ExcelEntity()
    private Traveldata traveldata;        //关系属性
    
    @Excel(name="北京",needMerge=true) 
    private Integer bjFour;
    @Excel(name="上海",needMerge=true) 
    private Integer shFour;
    



    @Excel(name="天津",needMerge=true) 
    private Integer tjFour;

    @Excel(name="河北",needMerge=true) 
    private Integer hbFour;
    @Excel(name="山西",needMerge=true)
    private Integer sxFour;
    @Excel(name="内蒙古",needMerge=true) 
    private Integer nmgFour;
    @Excel(name="辽宁",needMerge=true) 
    private Integer lnFour;
    @Excel(name="吉林",needMerge=true) 
    private Integer jlFour;
    @Excel(name="黑龙江",needMerge=true) 
    private Integer hljFour;
    @Excel(name="江苏",needMerge=true) 
    private Integer jsFour;
    @Excel(name="浙江",needMerge=true)
    private Integer zjFour;
    @Excel(name="安徽",needMerge=true) 
    private Integer ahFour;
    @Excel(name="福建",needMerge=true) 
    private Integer fjFour; 
    @Excel(name="江西",needMerge=true)
    private Integer jxFour;
    @Excel(name="山东",needMerge=true) 
    private Integer sdFour;
    @Excel(name="河南",needMerge=true) 
    private Integer henanFour;
    @Excel(name="湖北",needMerge=true)
    private Integer hubeiFour;
    @Excel(name="湖南",needMerge=true)
    private Integer hlFour;
    @Excel(name="广东",needMerge=true)
    private Integer gdFour;
    @Excel(name="广西",needMerge=true) 
    private Integer gxFour;
    @Excel(name="海南",needMerge=true)
    private Integer hainanFour;
    @Excel(name="重庆",needMerge=true) 
    private Integer cqFour;
   
    @Excel(name="四川",needMerge=true)
    private Integer scFour;
    @Excel(name="贵州",needMerge=true)
    private Integer gzFour;
    @Excel(name="云南",needMerge=true)
    private Integer ynFour;
    @Excel(name="西藏",needMerge=true)
    private Integer xzFour;
    @Excel(name="陕西",needMerge=true)
    private Integer shanxiFour;
    @Excel(name="甘肃",needMerge=true)
    private Integer gansuFour;
    @Excel(name="青海",needMerge=true)
    private Integer qinghaiFour;
    @Excel(name="宁夏",needMerge=true) 
    private Integer nxFour;
    @Excel(name="新疆",needMerge=true) 
    private Integer xjFour;


    
    
    


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
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	@Column(name ="indexname",nullable=true,length=60)
	public String getIndexname() {
		return indexname;
	}
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
	@Column(name ="code",nullable=true,length=60)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name ="person_time",nullable=true,length=60)
	public Integer getPersonTime() {
		return personTime;
	}
	public void setPersonTime(Integer personTime) {
		this.personTime = personTime;
	}
	@Column(name ="person_day",nullable=true,length=60)
	public Integer getPersonDay() {
		return personDay;
	}
	public void setPersonDay(Integer personDay) {
		this.personDay = personDay;
	}
	@Column(name ="report_person",nullable=true,length=60)
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	@Column(name ="report_telephone",nullable=true)
	public String getReportTelephone() {
		return reportTelephone;
	}
	public void setReportTelephone(String reportTelephone) {
		this.reportTelephone = reportTelephone;
	}
	@Column(name="report_date")
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	@Column(name ="status",nullable=true,length=10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="tid",insertable=false,updatable=false)
	public Traveldata getTraveldata() {
		return traveldata;
	}
	public void setTraveldata(Traveldata traveldata) {
		this.traveldata = traveldata;
	}	
	@Column(name ="bj_four",nullable=true,length=50)
	public Integer getBjFour() {
		return bjFour;
	}
	public void setBjFour(Integer bjFour) {
		this.bjFour = bjFour;
	}
	@Column(name ="tj_four",nullable=true,length=50)
	public Integer getTjFour() {
		return tjFour;
	}
	public void setTjFour(Integer tjFour) {
		this.tjFour = tjFour;
	}
	@Column(name ="hb_four",nullable=true,length=50)
	public Integer getHbFour() {
		return hbFour;
	}
	public void setHbFour(Integer hbFour) {
		this.hbFour = hbFour;
	}
	@Column(name ="sx_four",nullable=true,length=50)
	public Integer getSxFour() {
		return sxFour;
	}
	public void setSxFour(Integer sxFour) {
		this.sxFour = sxFour;
	}
	@Column(name ="nmg_four",nullable=true,length=50)
	public Integer getNmgFour() {
		return nmgFour;
	}
	public void setNmgFour(Integer nmgFour) {
		this.nmgFour = nmgFour;
	}
	@Column(name ="ln_four",nullable=true,length=50)
	public Integer getLnFour() {
		return lnFour;
	}
	public void setLnFour(Integer lnFour) {
		this.lnFour = lnFour;
	}
	@Column(name ="jl_four",nullable=true,length=50)
	public Integer getJlFour() {
		return jlFour;
	}
	public void setJlFour(Integer jlFour) {
		this.jlFour = jlFour;
	}
	@Column(name ="hlj_four",nullable=true,length=50)
	public Integer getHljFour() {
		return hljFour;
	}
	public void setHljFour(Integer hljFour) {
		this.hljFour = hljFour;
	}
	@Column(name ="sh_four",nullable=true,length=50)
	public Integer getShFour() {
		return shFour;
	}
	public void setShFour(Integer shFour) {
		this.shFour = shFour;
	}
	@Column(name ="js_four",nullable=true,length=50)
	public Integer getJsFour() {
		return jsFour;
	}
	public void setJsFour(Integer jsFour) {
		this.jsFour = jsFour;
	}
	@Column(name ="zj_four",nullable=true,length=50)
	public Integer getZjFour() {
		return zjFour;
	}
	public void setZjFour(Integer zjFour) {
		this.zjFour = zjFour;
	}
	@Column(name ="ah_four",nullable=true,length=50)
	public Integer getAhFour() {
		return ahFour;
	}
	public void setAhFour(Integer ahFour) {
		this.ahFour = ahFour;
	}
	@Column(name ="fj_four",nullable=true,length=50)
	public Integer getFjFour() {
			return fjFour;
		}
    public void setFjFour(Integer fjFour) {
			this.fjFour = fjFour;
		}
		
	@Column(name ="jx_four",nullable=true,length=50)
	public Integer getJxFour() {
		return jxFour;
	}
	public void setJxFour(Integer jxFour) {
		this.jxFour = jxFour;
	}
	@Column(name ="sd_four",nullable=true,length=50)
	public Integer getSdFour() {
		return sdFour;
	}
	public void setSdFour(Integer sdFour) {
		this.sdFour = sdFour;
	}
	@Column(name ="henan_four",nullable=true,length=50)
	public Integer getHenanFour() {
		return henanFour;
	}
	public void setHenanFour(Integer henanFour) {
		this.henanFour = henanFour;
	}
	@Column(name ="hubei_four",nullable=true,length=50)
	public Integer getHubeiFour() {
		return hubeiFour;
	}
	public void setHubeiFour(Integer hubeiFour) {
		this.hubeiFour = hubeiFour;
	}
	@Column(name ="hl_four",nullable=true,length=50)
	public Integer getHlFour() {
		return hlFour;
	}
	public void setHlFour(Integer hlFour) {
		this.hlFour = hlFour;
	}
	public Integer getGdFour() {
		return gdFour;
	}
	public void setGdFour(Integer gdFour) {
		this.gdFour = gdFour;
	}
	@Column(name ="gx_four",nullable=true,length=50)
	public Integer getGxFour() {
		return gxFour;
	}
	public void setGxFour(Integer gxFour) {
		this.gxFour = gxFour;
	}
	@Column(name ="hainan_four",nullable=true,length=50)
	public Integer getHainanFour() {
		return hainanFour;
	}
	public void setHainanFour(Integer hainanFour) {
		this.hainanFour = hainanFour;
	}
	@Column(name ="cq_four",nullable=true,length=50)
	public Integer getCqFour() {
		return cqFour;
	}
	public void setCqFour(Integer cqFour) {
		this.cqFour = cqFour;
	}
	@Column(name ="sc_four",nullable=true,length=50)
	public Integer getScFour() {
		return scFour;
	}
	public void setScFour(Integer scFour) {
		this.scFour = scFour;
	}
	@Column(name ="gz_four",nullable=true,length=50)
	public Integer getGzFour() {
		return gzFour;
	}
	public void setGzFour(Integer gzFour) {
		this.gzFour = gzFour;
	}
	@Column(name ="yn_four",nullable=true,length=50)
	public Integer getYnFour() {
		return ynFour;
	}
	public void setYnFour(Integer ynFour) {
		this.ynFour = ynFour;
	}
	@Column(name ="xz_four",nullable=true,length=50)
	public Integer getXzFour() {
		return xzFour;
	}
	public void setXzFour(Integer xzFour) {
		this.xzFour = xzFour;
	}
	@Column(name ="shanxi_four",nullable=true,length=50)
	public Integer getShanxiFour() {
		return shanxiFour;
	}
	public void setShanxiFour(Integer shanxiFour) {
		this.shanxiFour = shanxiFour;
	}
	@Column(name ="gansu_four",nullable=true,length=50)
	public Integer getGansuFour() {
		return gansuFour;
	}
	public void setGansuFour(Integer gansuFour) {
		this.gansuFour = gansuFour;
	}
	@Column(name ="qinghai_four",nullable=true,length=50)
	public Integer getQinghaiFour() {
		return qinghaiFour;
	}
	public void setQinghaiFour(Integer qinghaiFour) {
		this.qinghaiFour = qinghaiFour;
	}
	@Column(name ="nx_four",nullable=true,length=50)
	public Integer getNxFour() {
		return nxFour;
	}
	public void setNxFour(Integer nxFour) {
		this.nxFour = nxFour;
	}
	@Column(name ="xj_four",nullable=true,length=50)
	public Integer getXjFour() {
		return xjFour;
	}
	public void setXjFour(Integer xjFour) {
		this.xjFour = xjFour;
	}
	
	@Column(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="quarter")
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

}
