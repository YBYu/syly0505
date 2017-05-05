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
public class STQuarterInlandOrg implements java.io.Serializable{
	

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
    private Integer bjOne;
    @Excel(name="上海",needMerge=true) 
    private Integer shOne;
    



    @Excel(name="天津",needMerge=true) 
    private Integer tjOne;

    @Excel(name="河北",needMerge=true) 
    private Integer hbOne;
    @Excel(name="山西",needMerge=true)
    private Integer sxOne;
    @Excel(name="内蒙古",needMerge=true) 
    private Integer nmgOne;
    @Excel(name="辽宁",needMerge=true) 
    private Integer lnOne;
    @Excel(name="吉林",needMerge=true) 
    private Integer jlOne;
    @Excel(name="黑龙江",needMerge=true) 
    private Integer hljOne;
    @Excel(name="江苏",needMerge=true) 
    private Integer jsOne;
    @Excel(name="浙江",needMerge=true)
    private Integer zjOne;
    @Excel(name="安徽",needMerge=true) 
    private Integer ahOne;
    @Excel(name="福建",needMerge=true) 
    private Integer fjOne; 
    @Excel(name="江西",needMerge=true)
    private Integer jxOne;
    @Excel(name="山东",needMerge=true) 
    private Integer sdOne;
    @Excel(name="河南",needMerge=true) 
    private Integer henanOne;
    @Excel(name="湖北",needMerge=true)
    private Integer hubeiOne;
    @Excel(name="湖南",needMerge=true)
    private Integer hlOne;
    @Excel(name="广东",needMerge=true)
    private Integer gdOne;
    @Excel(name="广西",needMerge=true) 
    private Integer gxOne;
    @Excel(name="海南",needMerge=true)
    private Integer hainanOne;
    @Excel(name="重庆",needMerge=true) 
    private Integer cqOne;
   
    @Excel(name="四川",needMerge=true)
    private Integer scOne;
    @Excel(name="贵州",needMerge=true)
    private Integer gzOne;
    @Excel(name="云南",needMerge=true)
    private Integer ynOne;
    @Excel(name="西藏",needMerge=true)
    private Integer xzOne;
    @Excel(name="陕西",needMerge=true)
    private Integer shanxiOne;
    @Excel(name="甘肃",needMerge=true)
    private Integer gansuOne;
    @Excel(name="青海",needMerge=true)
    private Integer qinghaiOne;
    @Excel(name="宁夏",needMerge=true) 
    private Integer nxOne;
    @Excel(name="新疆",needMerge=true) 
    private Integer xjOne;


    
    
    


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
	@Column(name ="bj_one",nullable=true,length=50)
	public Integer getBjOne() {
		return bjOne;
	}
	public void setBjOne(Integer bjOne) {
		this.bjOne = bjOne;
	}
	@Column(name ="tj_one",nullable=true,length=50)
	public Integer getTjOne() {
		return tjOne;
	}
	public void setTjOne(Integer tjOne) {
		this.tjOne = tjOne;
	}
	@Column(name ="hb_one",nullable=true,length=50)
	public Integer getHbOne() {
		return hbOne;
	}
	public void setHbOne(Integer hbOne) {
		this.hbOne = hbOne;
	}
	@Column(name ="sx_one",nullable=true,length=50)
	public Integer getSxOne() {
		return sxOne;
	}
	public void setSxOne(Integer sxOne) {
		this.sxOne = sxOne;
	}
	@Column(name ="nmg_one",nullable=true,length=50)
	public Integer getNmgOne() {
		return nmgOne;
	}
	public void setNmgOne(Integer nmgOne) {
		this.nmgOne = nmgOne;
	}
	@Column(name ="ln_one",nullable=true,length=50)
	public Integer getLnOne() {
		return lnOne;
	}
	public void setLnOne(Integer lnOne) {
		this.lnOne = lnOne;
	}
	@Column(name ="jl_one",nullable=true,length=50)
	public Integer getJlOne() {
		return jlOne;
	}
	public void setJlOne(Integer jlOne) {
		this.jlOne = jlOne;
	}
	@Column(name ="hlj_one",nullable=true,length=50)
	public Integer getHljOne() {
		return hljOne;
	}
	public void setHljOne(Integer hljOne) {
		this.hljOne = hljOne;
	}
	@Column(name ="sh_one",nullable=true,length=50)
	public Integer getShOne() {
		return shOne;
	}
	public void setShOne(Integer shOne) {
		this.shOne = shOne;
	}
	@Column(name ="js_one",nullable=true,length=50)
	public Integer getJsOne() {
		return jsOne;
	}
	public void setJsOne(Integer jsOne) {
		this.jsOne = jsOne;
	}
	@Column(name ="zj_one",nullable=true,length=50)
	public Integer getZjOne() {
		return zjOne;
	}
	public void setZjOne(Integer zjOne) {
		this.zjOne = zjOne;
	}
	@Column(name ="ah_one",nullable=true,length=50)
	public Integer getAhOne() {
		return ahOne;
	}
	public void setAhOne(Integer ahOne) {
		this.ahOne = ahOne;
	}
	@Column(name ="fj_one",nullable=true,length=50)
	public Integer getFjOne() {
			return fjOne;
		}
    public void setFjOne(Integer fjOne) {
			this.fjOne = fjOne;
		}
		
	@Column(name ="jx_one",nullable=true,length=50)
	public Integer getJxOne() {
		return jxOne;
	}
	public void setJxOne(Integer jxOne) {
		this.jxOne = jxOne;
	}
	@Column(name ="sd_one",nullable=true,length=50)
	public Integer getSdOne() {
		return sdOne;
	}
	public void setSdOne(Integer sdOne) {
		this.sdOne = sdOne;
	}
	@Column(name ="henan_one",nullable=true,length=50)
	public Integer getHenanOne() {
		return henanOne;
	}
	public void setHenanOne(Integer henanOne) {
		this.henanOne = henanOne;
	}
	@Column(name ="hubei_one",nullable=true,length=50)
	public Integer getHubeiOne() {
		return hubeiOne;
	}
	public void setHubeiOne(Integer hubeiOne) {
		this.hubeiOne = hubeiOne;
	}
	@Column(name ="hl_one",nullable=true,length=50)
	public Integer getHlOne() {
		return hlOne;
	}
	public void setHlOne(Integer hlOne) {
		this.hlOne = hlOne;
	}
	public Integer getGdOne() {
		return gdOne;
	}
	public void setGdOne(Integer gdOne) {
		this.gdOne = gdOne;
	}
	@Column(name ="gx_one",nullable=true,length=50)
	public Integer getGxOne() {
		return gxOne;
	}
	public void setGxOne(Integer gxOne) {
		this.gxOne = gxOne;
	}
	@Column(name ="hainan_one",nullable=true,length=50)
	public Integer getHainanOne() {
		return hainanOne;
	}
	public void setHainanOne(Integer hainanOne) {
		this.hainanOne = hainanOne;
	}
	@Column(name ="cq_one",nullable=true,length=50)
	public Integer getCqOne() {
		return cqOne;
	}
	public void setCqOne(Integer cqOne) {
		this.cqOne = cqOne;
	}
	@Column(name ="sc_one",nullable=true,length=50)
	public Integer getScOne() {
		return scOne;
	}
	public void setScOne(Integer scOne) {
		this.scOne = scOne;
	}
	@Column(name ="gz_one",nullable=true,length=50)
	public Integer getGzOne() {
		return gzOne;
	}
	public void setGzOne(Integer gzOne) {
		this.gzOne = gzOne;
	}
	@Column(name ="yn_one",nullable=true,length=50)
	public Integer getYnOne() {
		return ynOne;
	}
	public void setYnOne(Integer ynOne) {
		this.ynOne = ynOne;
	}
	@Column(name ="xz_one",nullable=true,length=50)
	public Integer getXzOne() {
		return xzOne;
	}
	public void setXzOne(Integer xzOne) {
		this.xzOne = xzOne;
	}
	@Column(name ="shanxi_one",nullable=true,length=50)
	public Integer getShanxiOne() {
		return shanxiOne;
	}
	public void setShanxiOne(Integer shanxiOne) {
		this.shanxiOne = shanxiOne;
	}
	@Column(name ="gansu_one",nullable=true,length=50)
	public Integer getGansuOne() {
		return gansuOne;
	}
	public void setGansuOne(Integer gansuOne) {
		this.gansuOne = gansuOne;
	}
	@Column(name ="qinghai_one",nullable=true,length=50)
	public Integer getQinghaiOne() {
		return qinghaiOne;
	}
	public void setQinghaiOne(Integer qinghaiOne) {
		this.qinghaiOne = qinghaiOne;
	}
	@Column(name ="nx_one",nullable=true,length=50)
	public Integer getNxOne() {
		return nxOne;
	}
	public void setNxOne(Integer nxOne) {
		this.nxOne = nxOne;
	}
	@Column(name ="xj_one",nullable=true,length=50)
	public Integer getXjOne() {
		return xjOne;
	}
	public void setXjOne(Integer xjOne) {
		this.xjOne = xjOne;
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
