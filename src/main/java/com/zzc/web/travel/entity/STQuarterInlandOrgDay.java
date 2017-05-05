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
public class STQuarterInlandOrgDay implements java.io.Serializable{
	

	private String  id;
    private String tid;
 
    private String  indexname;
    private String  code;
    private Integer personTime;
    private Integer personDay;
    private String  reportPerson;
    private String  reportTelephThree;
    private String  reportDate;
    private String status;
    @Excel(name="年份",needMerge=true) 
    private String year;
    @Excel(name="季度",needMerge=true) 
    private String quarter;
    
    @ExcelEntity()
    private Traveldata traveldata;        //关系属性
    
    @Excel(name="北京",needMerge=true) 
    private Integer bjThree;
    @Excel(name="上海",needMerge=true) 
    private Integer shThree;
    



    @Excel(name="天津",needMerge=true) 
    private Integer tjThree;

    @Excel(name="河北",needMerge=true) 
    private Integer hbThree;
    @Excel(name="山西",needMerge=true)
    private Integer sxThree;
    @Excel(name="内蒙古",needMerge=true) 
    private Integer nmgThree;
    @Excel(name="辽宁",needMerge=true) 
    private Integer lnThree;
    @Excel(name="吉林",needMerge=true) 
    private Integer jlThree;
    @Excel(name="黑龙江",needMerge=true) 
    private Integer hljThree;
    @Excel(name="江苏",needMerge=true) 
    private Integer jsThree;
    @Excel(name="浙江",needMerge=true)
    private Integer zjThree;
    @Excel(name="安徽",needMerge=true) 
    private Integer ahThree;
    @Excel(name="福建",needMerge=true) 
    private Integer fjThree; 
    @Excel(name="江西",needMerge=true)
    private Integer jxThree;
    @Excel(name="山东",needMerge=true) 
    private Integer sdThree;
    @Excel(name="河南",needMerge=true) 
    private Integer henanThree;
    @Excel(name="湖北",needMerge=true)
    private Integer hubeiThree;
    @Excel(name="湖南",needMerge=true)
    private Integer hlThree;
    @Excel(name="广东",needMerge=true)
    private Integer gdThree;
    @Excel(name="广西",needMerge=true) 
    private Integer gxThree;
    @Excel(name="海南",needMerge=true)
    private Integer hainanThree;
    @Excel(name="重庆",needMerge=true) 
    private Integer cqThree;
   
    @Excel(name="四川",needMerge=true)
    private Integer scThree;
    @Excel(name="贵州",needMerge=true)
    private Integer gzThree;
    @Excel(name="云南",needMerge=true)
    private Integer ynThree;
    @Excel(name="西藏",needMerge=true)
    private Integer xzThree;
    @Excel(name="陕西",needMerge=true)
    private Integer shanxiThree;
    @Excel(name="甘肃",needMerge=true)
    private Integer gansuThree;
    @Excel(name="青海",needMerge=true)
    private Integer qinghaiThree;
    @Excel(name="宁夏",needMerge=true) 
    private Integer nxThree;
    @Excel(name="新疆",needMerge=true) 
    private Integer xjThree;


    
    
    


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
	@Column(name ="report_telephThree",nullable=true)
	public String getReportTelephThree() {
		return reportTelephThree;
	}
	public void setReportTelephThree(String reportTelephThree) {
		this.reportTelephThree = reportTelephThree;
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
	@Column(name ="bj_three",nullable=true,length=50)
	public Integer getBjThree() {
		return bjThree;
	}
	public void setBjThree(Integer bjThree) {
		this.bjThree = bjThree;
	}
	@Column(name ="tj_three",nullable=true,length=50)
	public Integer getTjThree() {
		return tjThree;
	}
	public void setTjThree(Integer tjThree) {
		this.tjThree = tjThree;
	}
	@Column(name ="hb_three",nullable=true,length=50)
	public Integer getHbThree() {
		return hbThree;
	}
	public void setHbThree(Integer hbThree) {
		this.hbThree = hbThree;
	}
	@Column(name ="sx_three",nullable=true,length=50)
	public Integer getSxThree() {
		return sxThree;
	}
	public void setSxThree(Integer sxThree) {
		this.sxThree = sxThree;
	}
	@Column(name ="nmg_three",nullable=true,length=50)
	public Integer getNmgThree() {
		return nmgThree;
	}
	public void setNmgThree(Integer nmgThree) {
		this.nmgThree = nmgThree;
	}
	@Column(name ="ln_three",nullable=true,length=50)
	public Integer getLnThree() {
		return lnThree;
	}
	public void setLnThree(Integer lnThree) {
		this.lnThree = lnThree;
	}
	@Column(name ="jl_three",nullable=true,length=50)
	public Integer getJlThree() {
		return jlThree;
	}
	public void setJlThree(Integer jlThree) {
		this.jlThree = jlThree;
	}
	@Column(name ="hlj_three",nullable=true,length=50)
	public Integer getHljThree() {
		return hljThree;
	}
	public void setHljThree(Integer hljThree) {
		this.hljThree = hljThree;
	}
	@Column(name ="sh_three",nullable=true,length=50)
	public Integer getShThree() {
		return shThree;
	}
	public void setShThree(Integer shThree) {
		this.shThree = shThree;
	}
	@Column(name ="js_three",nullable=true,length=50)
	public Integer getJsThree() {
		return jsThree;
	}
	public void setJsThree(Integer jsThree) {
		this.jsThree = jsThree;
	}
	@Column(name ="zj_three",nullable=true,length=50)
	public Integer getZjThree() {
		return zjThree;
	}
	public void setZjThree(Integer zjThree) {
		this.zjThree = zjThree;
	}
	@Column(name ="ah_three",nullable=true,length=50)
	public Integer getAhThree() {
		return ahThree;
	}
	public void setAhThree(Integer ahThree) {
		this.ahThree = ahThree;
	}
	@Column(name ="fj_three",nullable=true,length=50)
	public Integer getFjThree() {
			return fjThree;
		}
    public void setFjThree(Integer fjThree) {
			this.fjThree = fjThree;
		}
		
	@Column(name ="jx_three",nullable=true,length=50)
	public Integer getJxThree() {
		return jxThree;
	}
	public void setJxThree(Integer jxThree) {
		this.jxThree = jxThree;
	}
	@Column(name ="sd_three",nullable=true,length=50)
	public Integer getSdThree() {
		return sdThree;
	}
	public void setSdThree(Integer sdThree) {
		this.sdThree = sdThree;
	}
	@Column(name ="henan_three",nullable=true,length=50)
	public Integer getHenanThree() {
		return henanThree;
	}
	public void setHenanThree(Integer henanThree) {
		this.henanThree = henanThree;
	}
	@Column(name ="hubei_three",nullable=true,length=50)
	public Integer getHubeiThree() {
		return hubeiThree;
	}
	public void setHubeiThree(Integer hubeiThree) {
		this.hubeiThree = hubeiThree;
	}
	@Column(name ="hl_three",nullable=true,length=50)
	public Integer getHlThree() {
		return hlThree;
	}
	public void setHlThree(Integer hlThree) {
		this.hlThree = hlThree;
	}
	public Integer getGdThree() {
		return gdThree;
	}
	public void setGdThree(Integer gdThree) {
		this.gdThree = gdThree;
	}
	@Column(name ="gx_three",nullable=true,length=50)
	public Integer getGxThree() {
		return gxThree;
	}
	public void setGxThree(Integer gxThree) {
		this.gxThree = gxThree;
	}
	@Column(name ="hainan_three",nullable=true,length=50)
	public Integer getHainanThree() {
		return hainanThree;
	}
	public void setHainanThree(Integer hainanThree) {
		this.hainanThree = hainanThree;
	}
	@Column(name ="cq_three",nullable=true,length=50)
	public Integer getCqThree() {
		return cqThree;
	}
	public void setCqThree(Integer cqThree) {
		this.cqThree = cqThree;
	}
	@Column(name ="sc_three",nullable=true,length=50)
	public Integer getScThree() {
		return scThree;
	}
	public void setScThree(Integer scThree) {
		this.scThree = scThree;
	}
	@Column(name ="gz_three",nullable=true,length=50)
	public Integer getGzThree() {
		return gzThree;
	}
	public void setGzThree(Integer gzThree) {
		this.gzThree = gzThree;
	}
	@Column(name ="yn_three",nullable=true,length=50)
	public Integer getYnThree() {
		return ynThree;
	}
	public void setYnThree(Integer ynThree) {
		this.ynThree = ynThree;
	}
	@Column(name ="xz_three",nullable=true,length=50)
	public Integer getXzThree() {
		return xzThree;
	}
	public void setXzThree(Integer xzThree) {
		this.xzThree = xzThree;
	}
	@Column(name ="shanxi_three",nullable=true,length=50)
	public Integer getShanxiThree() {
		return shanxiThree;
	}
	public void setShanxiThree(Integer shanxiThree) {
		this.shanxiThree = shanxiThree;
	}
	@Column(name ="gansu_three",nullable=true,length=50)
	public Integer getGansuThree() {
		return gansuThree;
	}
	public void setGansuThree(Integer gansuThree) {
		this.gansuThree = gansuThree;
	}
	@Column(name ="qinghai_three",nullable=true,length=50)
	public Integer getQinghaiThree() {
		return qinghaiThree;
	}
	public void setQinghaiThree(Integer qinghaiThree) {
		this.qinghaiThree = qinghaiThree;
	}
	@Column(name ="nx_three",nullable=true,length=50)
	public Integer getNxThree() {
		return nxThree;
	}
	public void setNxThree(Integer nxThree) {
		this.nxThree = nxThree;
	}
	@Column(name ="xj_three",nullable=true,length=50)
	public Integer getXjThree() {
		return xjThree;
	}
	public void setXjThree(Integer xjThree) {
		this.xjThree = xjThree;
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
