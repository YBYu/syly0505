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
public class STQuarterInlandRec implements java.io.Serializable{
	

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
    private Integer bjTwo;
    @Excel(name="上海",needMerge=true) 
    private Integer shTwo;
    



    @Excel(name="天津",needMerge=true) 
    private Integer tjTwo;

    @Excel(name="河北",needMerge=true) 
    private Integer hbTwo;
    @Excel(name="山西",needMerge=true)
    private Integer sxTwo;
    @Excel(name="内蒙古",needMerge=true) 
    private Integer nmgTwo;
    @Excel(name="辽宁",needMerge=true) 
    private Integer lnTwo;
    @Excel(name="吉林",needMerge=true) 
    private Integer jlTwo;
    @Excel(name="黑龙江",needMerge=true) 
    private Integer hljTwo;
    @Excel(name="江苏",needMerge=true) 
    private Integer jsTwo;
    @Excel(name="浙江",needMerge=true)
    private Integer zjTwo;
    @Excel(name="安徽",needMerge=true) 
    private Integer ahTwo;
    @Excel(name="福建",needMerge=true) 
    private Integer fjTwo; 
    @Excel(name="江西",needMerge=true)
    private Integer jxTwo;
    @Excel(name="山东",needMerge=true) 
    private Integer sdTwo;
    @Excel(name="河南",needMerge=true) 
    private Integer henanTwo;
    @Excel(name="湖北",needMerge=true)
    private Integer hubeiTwo;
    @Excel(name="湖南",needMerge=true)
    private Integer hlTwo;
    @Excel(name="广东",needMerge=true)
    private Integer gdTwo;
    @Excel(name="广西",needMerge=true) 
    private Integer gxTwo;
    @Excel(name="海南",needMerge=true)
    private Integer hainanTwo;
    @Excel(name="重庆",needMerge=true) 
    private Integer cqTwo;
   
    @Excel(name="四川",needMerge=true)
    private Integer scTwo;
    @Excel(name="贵州",needMerge=true)
    private Integer gzTwo;
    @Excel(name="云南",needMerge=true)
    private Integer ynTwo;
    @Excel(name="西藏",needMerge=true)
    private Integer xzTwo;
    @Excel(name="陕西",needMerge=true)
    private Integer shanxiTwo;
    @Excel(name="甘肃",needMerge=true)
    private Integer gansuTwo;
    @Excel(name="青海",needMerge=true)
    private Integer qinghaiTwo;
    @Excel(name="宁夏",needMerge=true) 
    private Integer nxTwo;
    @Excel(name="新疆",needMerge=true) 
    private Integer xjTwo;


    
    
    


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
	@Column(name ="bj_two",nullable=true,length=50)
	public Integer getBjTwo() {
		return bjTwo;
	}
	public void setBjTwo(Integer bjTwo) {
		this.bjTwo = bjTwo;
	}
	@Column(name ="tj_two",nullable=true,length=50)
	public Integer getTjTwo() {
		return tjTwo;
	}
	public void setTjTwo(Integer tjTwo) {
		this.tjTwo = tjTwo;
	}
	@Column(name ="hb_two",nullable=true,length=50)
	public Integer getHbTwo() {
		return hbTwo;
	}
	public void setHbTwo(Integer hbTwo) {
		this.hbTwo = hbTwo;
	}
	@Column(name ="sx_two",nullable=true,length=50)
	public Integer getSxTwo() {
		return sxTwo;
	}
	public void setSxTwo(Integer sxTwo) {
		this.sxTwo = sxTwo;
	}
	@Column(name ="nmg_two",nullable=true,length=50)
	public Integer getNmgTwo() {
		return nmgTwo;
	}
	public void setNmgTwo(Integer nmgTwo) {
		this.nmgTwo = nmgTwo;
	}
	@Column(name ="ln_two",nullable=true,length=50)
	public Integer getLnTwo() {
		return lnTwo;
	}
	public void setLnTwo(Integer lnTwo) {
		this.lnTwo = lnTwo;
	}
	@Column(name ="jl_two",nullable=true,length=50)
	public Integer getJlTwo() {
		return jlTwo;
	}
	public void setJlTwo(Integer jlTwo) {
		this.jlTwo = jlTwo;
	}
	@Column(name ="hlj_two",nullable=true,length=50)
	public Integer getHljTwo() {
		return hljTwo;
	}
	public void setHljTwo(Integer hljTwo) {
		this.hljTwo = hljTwo;
	}
	@Column(name ="sh_two",nullable=true,length=50)
	public Integer getShTwo() {
		return shTwo;
	}
	public void setShTwo(Integer shTwo) {
		this.shTwo = shTwo;
	}
	@Column(name ="js_two",nullable=true,length=50)
	public Integer getJsTwo() {
		return jsTwo;
	}
	public void setJsTwo(Integer jsTwo) {
		this.jsTwo = jsTwo;
	}
	@Column(name ="zj_two",nullable=true,length=50)
	public Integer getZjTwo() {
		return zjTwo;
	}
	public void setZjTwo(Integer zjTwo) {
		this.zjTwo = zjTwo;
	}
	@Column(name ="ah_two",nullable=true,length=50)
	public Integer getAhTwo() {
		return ahTwo;
	}
	public void setAhTwo(Integer ahTwo) {
		this.ahTwo = ahTwo;
	}
	@Column(name ="fj_two",nullable=true,length=50)
	public Integer getFjTwo() {
			return fjTwo;
		}
    public void setFjTwo(Integer fjTwo) {
			this.fjTwo = fjTwo;
		}
		
	@Column(name ="jx_two",nullable=true,length=50)
	public Integer getJxTwo() {
		return jxTwo;
	}
	public void setJxTwo(Integer jxTwo) {
		this.jxTwo = jxTwo;
	}
	@Column(name ="sd_two",nullable=true,length=50)
	public Integer getSdTwo() {
		return sdTwo;
	}
	public void setSdTwo(Integer sdTwo) {
		this.sdTwo = sdTwo;
	}
	@Column(name ="henan_two",nullable=true,length=50)
	public Integer getHenanTwo() {
		return henanTwo;
	}
	public void setHenanTwo(Integer henanTwo) {
		this.henanTwo = henanTwo;
	}
	@Column(name ="hubei_two",nullable=true,length=50)
	public Integer getHubeiTwo() {
		return hubeiTwo;
	}
	public void setHubeiTwo(Integer hubeiTwo) {
		this.hubeiTwo = hubeiTwo;
	}
	@Column(name ="hl_two",nullable=true,length=50)
	public Integer getHlTwo() {
		return hlTwo;
	}
	public void setHlTwo(Integer hlTwo) {
		this.hlTwo = hlTwo;
	}
	public Integer getGdTwo() {
		return gdTwo;
	}
	public void setGdTwo(Integer gdTwo) {
		this.gdTwo = gdTwo;
	}
	@Column(name ="gx_two",nullable=true,length=50)
	public Integer getGxTwo() {
		return gxTwo;
	}
	public void setGxTwo(Integer gxTwo) {
		this.gxTwo = gxTwo;
	}
	@Column(name ="hainan_two",nullable=true,length=50)
	public Integer getHainanTwo() {
		return hainanTwo;
	}
	public void setHainanTwo(Integer hainanTwo) {
		this.hainanTwo = hainanTwo;
	}
	@Column(name ="cq_two",nullable=true,length=50)
	public Integer getCqTwo() {
		return cqTwo;
	}
	public void setCqTwo(Integer cqTwo) {
		this.cqTwo = cqTwo;
	}
	@Column(name ="sc_two",nullable=true,length=50)
	public Integer getScTwo() {
		return scTwo;
	}
	public void setScTwo(Integer scTwo) {
		this.scTwo = scTwo;
	}
	@Column(name ="gz_two",nullable=true,length=50)
	public Integer getGzTwo() {
		return gzTwo;
	}
	public void setGzTwo(Integer gzTwo) {
		this.gzTwo = gzTwo;
	}
	@Column(name ="yn_two",nullable=true,length=50)
	public Integer getYnTwo() {
		return ynTwo;
	}
	public void setYnTwo(Integer ynTwo) {
		this.ynTwo = ynTwo;
	}
	@Column(name ="xz_two",nullable=true,length=50)
	public Integer getXzTwo() {
		return xzTwo;
	}
	public void setXzTwo(Integer xzTwo) {
		this.xzTwo = xzTwo;
	}
	@Column(name ="shanxi_two",nullable=true,length=50)
	public Integer getShanxiTwo() {
		return shanxiTwo;
	}
	public void setShanxiTwo(Integer shanxiTwo) {
		this.shanxiTwo = shanxiTwo;
	}
	@Column(name ="gansu_two",nullable=true,length=50)
	public Integer getGansuTwo() {
		return gansuTwo;
	}
	public void setGansuTwo(Integer gansuTwo) {
		this.gansuTwo = gansuTwo;
	}
	@Column(name ="qinghai_two",nullable=true,length=50)
	public Integer getQinghaiTwo() {
		return qinghaiTwo;
	}
	public void setQinghaiTwo(Integer qinghaiTwo) {
		this.qinghaiTwo = qinghaiTwo;
	}
	@Column(name ="nx_two",nullable=true,length=50)
	public Integer getNxTwo() {
		return nxTwo;
	}
	public void setNxTwo(Integer nxTwo) {
		this.nxTwo = nxTwo;
	}
	@Column(name ="xj_two",nullable=true,length=50)
	public Integer getXjTwo() {
		return xjTwo;
	}
	public void setXjTwo(Integer xjTwo) {
		this.xjTwo = xjTwo;
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
