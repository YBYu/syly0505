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

/**
 * @Title: 
 * @Description: 旅游季报国内实体类
 * @author 冯勇齐
 * @date 2016-12-10 18:31:20
 * 
 */

@Entity      //3 旅行社组织接待国内旅游情况表
@Table(name = "t_travelAgency_quarterly3")
@SuppressWarnings("serial")
public class TravelQuarterInland implements java.io.Serializable{
	
	
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
    private Traveldata traveldata;        //关系属性
    private String year;
    private String quarter;
    // 国家系统审核意见
    private String guo;
    
    // 分数
    private Integer score;
    
    
    private String  total;
    private Integer totalCode;
    private Integer totalOne;
    private Integer totalTwo;
    private Integer totalThree;
    private Integer totalFour;
    private String  bjName;
    private Integer bjCode;
    private Integer bjOne;                 //组织人次数
    private Integer bjTwo;                 //接待人次数
    private Integer bjThree;               //组织人天数
    private Integer bjFour;                //接待人天数
    private String  tjName;
    private Integer tjCode;
    private Integer tjOne;       //组织人次数        
    private Integer tjTwo;       //接待人次数
    private Integer tjThree;     //组织人天数
    private Integer tjFour;      //接待人天数
    private String  hbName;
    private Integer hbCode;
    private Integer hbOne;       //组织人次数        
    private Integer hbTwo;       //接待人次数
    private Integer hbThree;     //组织人天数
    private Integer hbFour;      //接待人天数
    private String  sxName;
    private Integer sxCode;
    private Integer sxOne;         //组织人次数        
    private Integer sxTwo;         //接待人次数
    private Integer sxThree;       //组织人天数
    private Integer sxFour;        //接待人天数
    private String nmgName;
    private Integer nmgCode;
    private Integer nmgOne;          //组织人次数     
    private Integer nmgTwo;          //接待人次数
    private Integer nmgThree;        //组织人天数
    private Integer nmgFour;         //接待人天数
    private String lnName;
    private Integer lnCode;
    private Integer lnOne;          //组织人次数     
    private Integer lnTwo;          //接待人次数
    private Integer lnThree;        //组织人天数
    private Integer lnFour;          //接待人天数
    private String  jlName;
    private Integer jlCode;
    private Integer jlOne;           //组织人次数   
    private Integer jlTwo;           //接待人次数
    private Integer jlThree;         //组织人天数
    private Integer jlFour;          //接待人天数
    private String  hljName;
    private Integer hljCode;
    private Integer hljOne;          //组织人次数   
    private Integer hljTwo;          //接待人次数
    private Integer hljThree;        //组织人天数
    private Integer hljFour;          //接待人天数
    private String  shName;
    private Integer shCode;
    private Integer shOne;            //组织人次数   
    private Integer shTwo;            //接待人次数
    private Integer shThree;          //组织人天数
    private Integer shFour;            //接待人天数
    private String  jsName;
    private Integer jsCode;
    private Integer jsOne;            //组织人次数   
    private Integer jsTwo;            //接待人次数
    private Integer jsThree;          //组织人天数
    private Integer jsFour;           //接待人天数
    private String  zjName;
    private Integer zjCode;
    private Integer zjOne;           //组织人次数   
    private Integer zjTwo;           //接待人次数
    private Integer zjThree;          //组织人天数
    private Integer zjFour;          //接待人天数
    private String  ahName;
    private Integer ahCode;
    private Integer ahOne;    //组织人次数   
    private Integer ahTwo;    //接待人次数
    private Integer ahThree;  //组织人天数
    private Integer ahFour;   //接待人天数
    private String  fjName;
    private Integer fjCode;
    private Integer fjOne;    //组织人次数
    private Integer fjTwo;    //接待人次数
	private Integer fjThree;  //组织人天数
    private Integer fjFour;   //接待人天数
    private String  jxName;
    private Integer jxCode;
    private Integer jxOne;     //组织人次数
    private Integer jxTwo;     //接待人次数
    private Integer jxThree;   //组织人天数
    private Integer jxFour;    //接待人天数
    private String  sdName;
    private Integer sdCode;
    private Integer sdOne;       //组织人次数
    private Integer sdTwo;       //接待人次数
    private Integer sdThree;     //组织人天数
    private Integer sdFour;      //接待人天数
    private String  henanName;
    private Integer henanCode;
    private Integer henanOne;      //组织人次数
    private Integer henanTwo;     //接待人次数
    private Integer henanThree;    //组织人天数
    private Integer henanFour;    //接待人天数
    private String  hubeiName;
    private Integer hubeiCode;
    private Integer hubeiOne;     //组织人次数
    private Integer hubeiTwo;     //接待人次数
    private Integer hubeiThree;   //组织人天数
    private Integer hubeiFour;    //接待人天数  
    private String hlName;
    private Integer hlCode;
    private Integer hlOne;      //组织人次数
    private Integer hlTwo;      //接待人次数
    private Integer hlThree;    //组织人天数
    private Integer hlFour;     //接待人天数  
    private String  gdName;
    private Integer gdCode;
    private Integer gdOne;      //组织人次数
    private Integer gdTwo;      //接待人次数
    private Integer gdThree;    //组织人天数
    private Integer gdFour;     //接待人天数 
    private String  gxName;
    private Integer gxCode;
    private Integer gxOne;      //组织人次数
    private Integer gxTwo;      //接待人次数
    private Integer gxThree;     //组织人天数
    private Integer gxFour;      //接待人天数 
    private String  hainanName;
    private Integer hainanCode;
    private Integer hainanOne;    //组织人次数
    private Integer hainanTwo;    //接待人次数
    private Integer hainanThree;  //组织人天数
    private Integer hainanFour;   //接待人天数 
    private String  cqName;
    private Integer cqCode;
    private Integer cqOne;      //组织人次数
    private Integer cqTwo;      //接待人次数
    private Integer cqThree;    //组织人天数
    private Integer cqFour;     //接待人天数 
    private String  scName;
    private Integer scCode;
    private Integer scOne;      //组织人次数
    private Integer scTwo;      //接待人次数
    private Integer scThree;    //组织人天数
    private Integer scFour;     //接待人天数 
    private String  gzName;
    private Integer gzCode;
    private Integer gzOne;       //组织人次数
    private Integer gzTwo;       //接待人次数
    private Integer gzThree;     //组织人天数
    private Integer gzFour;      //接待人天数 
    private String  ynName;
    private Integer ynCode;
    private Integer ynOne;        //组织人次数
    private Integer ynTwo;        //接待人次数
    private Integer ynThree;      //组织人天数
    private Integer ynFour;       //接待人天数 
    private String  xzName;
    private Integer xzCode;
    private Integer xzOne;         //组织人次数
    private Integer xzTwo;         //接待人次数
    private Integer xzThree;       //组织人天数
    private Integer  xzFour;       //接待人天数 
    private String  shanxiName;
    private Integer shanxiCode;
    private Integer shanxiOne;     //组织人次数
    private Integer shanxiTwo;     //接待人次数
    private Integer shanxiThree;   //组织人天数
    private Integer shanxiFour;    //接待人天数 
    private String  gansuName;
    private Integer gansuCode;
    private Integer gansuOne;      //组织人次数
    private Integer gansuTwo;      //接待人次数
    private Integer gansuThree;    //组织人天数
    private Integer gansuFour;     //接待人天数
    private String  qinghaiName;
    private Integer qinghaiCode;
    private Integer qinghaiOne;   //组织人次数
    private Integer qinghaiTwo;   //接待人次数
    private Integer qinghaiThree; //组织人天数
    private Integer qinghaiFour;  //接待人天数
    private String  nxName;
    private Integer nxCode;
    private Integer nxOne;       //组织人次数
    private Integer nxTwo;       //接待人次数
    private Integer nxThree;     //组织人天数
    private Integer nxFour;      //接待人天数
    private String  xjName;
    private Integer xjCode;
    private Integer xjOne;       //组织人次数
    private Integer xjTwo;       //接待人次数
    private Integer xjThree;     //组织人天数
    private Integer xjFour;      //接待人天数
    private String  dayOrgName;
    private Integer dayOrgCode;
    private Integer dayOrgOne;      //一日游组织人次数
    private Integer dayOrgTwo;
    private Integer dayOrgThree;
    private Integer dayOrgFour;
    private String  nightTimeName;
    private Integer nightTimeCode;
    private Integer nightTimeOne;   //过夜游组织人次数
    private Integer nightTimeTwo;
    private Integer nightTimeThree;
    private Integer nightTimeFour;
    private String  nightOrgName;    
    private Integer nightOrgCode;
    private Integer nightOrgOne;    //过夜游组织人天数
    private Integer nightOrgTwo;
    private Integer nightOrgThree;
    private Integer nightOrgFour;


    
    
    


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
	@Column(name ="total",nullable=true,length=20)
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	@Column(name ="total_code",nullable=true,length=50)	
	public Integer getTotalCode() {
		return totalCode;
	}
	public void setTotalCode(Integer totalCode) {
		this.totalCode = totalCode;
	}
	@Column(name ="total_one",nullable=true,length=50)

	public Integer getTotalOne() {
		return totalOne;
	}
	public void setTotalOne(Integer totalOne) {
		this.totalOne = totalOne;
	}
	@Column(name ="total_two",nullable=true,length=50)
	public Integer getTotalTwo() {
		return totalTwo;
	}
	public void setTotalTwo(Integer totalTwo) {
		this.totalTwo = totalTwo;
	}
	@Column(name ="total_three",nullable=true,length=50)
	public Integer getTotalThree() {
		return totalThree;
	}
	public void setTotalThree(Integer totalThree) {
		this.totalThree = totalThree;
	}
	@Column(name ="total_four",nullable=true,length=50)
	public Integer getTotalFour() {
		return totalFour;
	}
	public void setTotalFour(Integer totalFour) {
		this.totalFour = totalFour;
	}
	@Column(name ="bj_name",nullable=true,length=20)
	public String getBjName() {
		return bjName;
	}
	public void setBjName(String bjName) {
		this.bjName = bjName;
	}
	@Column(name ="bj_code",nullable=true,length=50)
	public Integer getBjCode() {
		return bjCode;
	}
	public void setBjCode(Integer bjCode) {
		this.bjCode = bjCode;
	}
	@Column(name ="bj_one",nullable=true,length=50)
	public Integer getBjOne() {
		return bjOne;
	}
	public void setBjOne(Integer bjOne) {
		this.bjOne = bjOne;
	}
	@Column(name ="bj_two",nullable=true,length=50)
	public Integer getBjTwo() {
		return bjTwo;
	}
	public void setBjTwo(Integer bjTwo) {
		this.bjTwo = bjTwo;
	}
	@Column(name ="bj_three",nullable=true,length=50)
	public Integer getBjThree() {
		return bjThree;
	}
	public void setBjThree(Integer bjThree) {
		this.bjThree = bjThree;
	}
	@Column(name ="bj_four",nullable=true,length=50)
	public Integer getBjFour() {
		return bjFour;
	}
	public void setBjFour(Integer bjFour) {
		this.bjFour = bjFour;
	}
	@Column(name ="tj_name",nullable=true,length=20)
	public String getTjName() {
		return tjName;
	}
	public void setTjName(String tjName) {
		this.tjName = tjName;
	}
	@Column(name ="tj_code",nullable=true,length=50)
	public Integer getTjCode() {
		return tjCode;
	}
	public void setTjCode(Integer tjCode) {
		this.tjCode = tjCode;
	}
	@Column(name ="tj_one",nullable=true,length=50)
	public Integer getTjOne() {
		return tjOne;
	}
	public void setTjOne(Integer tjOne) {
		this.tjOne = tjOne;
	}
	@Column(name ="tj_two",nullable=true,length=50)
	public Integer getTjTwo() {
		return tjTwo;
	}
	public void setTjTwo(Integer tjTwo) {
		this.tjTwo = tjTwo;
	}
	@Column(name ="tj_three",nullable=true,length=50)
	public Integer getTjThree() {
		return tjThree;
	}
	public void setTjThree(Integer tjThree) {
		this.tjThree = tjThree;
	}
	@Column(name ="tj_four",nullable=true,length=50)
	public Integer getTjFour() {
		return tjFour;
	}
	public void setTjFour(Integer tjFour) {
		this.tjFour = tjFour;
	}
	@Column(name ="hb_name",nullable=true,length=20)
	public String getHbName() {
		return hbName;
	}
	public void setHbName(String hbName) {
		this.hbName = hbName;
	}
	@Column(name ="hb_code",nullable=true,length=50)
	public Integer getHbCode() {
		return hbCode;
	}
	public void setHbCode(Integer hbCode) {
		this.hbCode = hbCode;
	}
	@Column(name ="hb_one",nullable=true,length=50)
	public Integer getHbOne() {
		return hbOne;
	}
	public void setHbOne(Integer hbOne) {
		this.hbOne = hbOne;
	}
	@Column(name ="hb_two",nullable=true,length=50)
	public Integer getHbTwo() {
		return hbTwo;
	}
	public void setHbTwo(Integer hbTwo) {
		this.hbTwo = hbTwo;
	}
	@Column(name ="hb_three",nullable=true,length=50)
	public Integer getHbThree() {
		return hbThree;
	}
	public void setHbThree(Integer hbThree) {
		this.hbThree = hbThree;
	}
	@Column(name ="hb_four",nullable=true,length=50)
	public Integer getHbFour() {
		return hbFour;
	}
	public void setHbFour(Integer hbFour) {
		this.hbFour = hbFour;
	}
	@Column(name ="sx_name",nullable=true,length=20)
	public String getSxName() {
		return sxName;
	}
	public void setSxName(String sxName) {
		this.sxName = sxName;
	}
	@Column(name ="sx_code",nullable=true,length=50)
	public Integer getSxCode() {
		return sxCode;
	}
	public void setSxCode(Integer sxCode) {
		this.sxCode = sxCode;
	}
	@Column(name ="sx_one",nullable=true,length=50)
	public Integer getSxOne() {
		return sxOne;
	}
	public void setSxOne(Integer sxOne) {
		this.sxOne = sxOne;
	}
	@Column(name ="sx_two",nullable=true,length=50)
	public Integer getSxTwo() {
		return sxTwo;
	}
	public void setSxTwo(Integer sxTwo) {
		this.sxTwo = sxTwo;
	}
	@Column(name ="sx_three",nullable=true,length=50)
	public Integer getSxThree() {
		return sxThree;
	}
	public void setSxThree(Integer sxThree) {
		this.sxThree = sxThree;
	}
	@Column(name ="sx_four",nullable=true,length=50)
	public Integer getSxFour() {
		return sxFour;
	}
	public void setSxFour(Integer sxFour) {
		this.sxFour = sxFour;
	}
	@Column(name ="nmg_name",nullable=true,length=20)

	public String getNmgName() {
		return nmgName;
	}
	public void setNmgName(String nmgName) {
		this.nmgName = nmgName;
	}
	@Column(name ="nmg_code",nullable=true,length=50)
	public Integer getNmgCode() {
		return nmgCode;
	}
	public void setNmgCode(Integer nmgCode) {
		this.nmgCode = nmgCode;
	}
	@Column(name ="nmg_one",nullable=true,length=50)
	public Integer getNmgOne() {
		return nmgOne;
	}
	public void setNmgOne(Integer nmgOne) {
		this.nmgOne = nmgOne;
	}
	@Column(name ="nmg_two",nullable=true,length=50)
	public Integer getNmgTwo() {
		return nmgTwo;
	}
	public void setNmgTwo(Integer nmgTwo) {
		this.nmgTwo = nmgTwo;
	}
	@Column(name ="nmg_three",nullable=true,length=50)
	public Integer getNmgThree() {
		return nmgThree;
	}
	public void setNmgThree(Integer nmgThree) {
		this.nmgThree = nmgThree;
	}
	@Column(name ="nmg_four",nullable=true,length=50)
	public Integer getNmgFour() {
		return nmgFour;
	}
	public void setNmgFour(Integer nmgFour) {
		this.nmgFour = nmgFour;
	}
	@Column(name ="ln_name",nullable=true,length=20)
	public String getLnName() {
		return lnName;
	}
	public void setLnName(String lnName) {
		this.lnName = lnName;
	}
	@Column(name ="ln_code",nullable=true,length=50)
	public Integer getLnCode() {
		return lnCode;
	}
	public void setLnCode(Integer lnCode) {
		this.lnCode = lnCode;
	}
	@Column(name ="ln_one",nullable=true,length=50)
	public Integer getLnOne() {
		return lnOne;
	}
	public void setLnOne(Integer lnOne) {
		this.lnOne = lnOne;
	}
	@Column(name ="ln_two",nullable=true,length=50)
	public Integer getLnTwo() {
		return lnTwo;
	}
	public void setLnTwo(Integer lnTwo) {
		this.lnTwo = lnTwo;
	}
	@Column(name ="ln_three",nullable=true,length=50)
	public Integer getLnThree() {
		return lnThree;
	}
	public void setLnThree(Integer lnThree) {
		this.lnThree = lnThree;
	}
	@Column(name ="ln_four",nullable=true,length=50)
	public Integer getLnFour() {
		return lnFour;
	}
	public void setLnFour(Integer lnFour) {
		this.lnFour = lnFour;
	}
	@Column(name ="jl_name",nullable=true,length=20)
	public String getJlName() {
		return jlName;
	}
	public void setJlName(String jlName) {
		this.jlName = jlName;
	}
	@Column(name ="jl_code",nullable=true,length=50)
	public Integer getJlCode() {
		return jlCode;
	}
	public void setJlCode(Integer jlCode) {
		this.jlCode = jlCode;
	}
	@Column(name ="jl_one",nullable=true,length=50)
	public Integer getJlOne() {
		return jlOne;
	}
	public void setJlOne(Integer jlOne) {
		this.jlOne = jlOne;
	}
	@Column(name ="jl_two",nullable=true,length=50)
	public Integer getJlTwo() {
		return jlTwo;
	}
	public void setJlTwo(Integer jlTwo) {
		this.jlTwo = jlTwo;
	}
	@Column(name ="jl_three",nullable=true,length=50)
	public Integer getJlThree() {
		return jlThree;
	}
	public void setJlThree(Integer jlThree) {
		this.jlThree = jlThree;
	}
	@Column(name ="jl_four",nullable=true,length=50)
	public Integer getJlFour() {
		return jlFour;
	}
	public void setJlFour(Integer jlFour) {
		this.jlFour = jlFour;
	}
	@Column(name ="hlj_name",nullable=true,length=20)
	public String getHljName() {
		return hljName;
	}
	public void setHljName(String hljName) {
		this.hljName = hljName;
	}
	@Column(name ="hlj_code",nullable=true,length=50)
	public Integer getHljCode() {
		return hljCode;
	}
	public void setHljCode(Integer hljCode) {
		this.hljCode = hljCode;
	}
	@Column(name ="hlj_one",nullable=true,length=50)
	public Integer getHljOne() {
		return hljOne;
	}
	public void setHljOne(Integer hljOne) {
		this.hljOne = hljOne;
	}
	@Column(name ="hlj_two",nullable=true,length=50)
	public Integer getHljTwo() {
		return hljTwo;
	}
	public void setHljTwo(Integer hljTwo) {
		this.hljTwo = hljTwo;
	}
	@Column(name ="hlj_three",nullable=true,length=50)
	public Integer getHljThree() {
		return hljThree;
	}
	public void setHljThree(Integer hljThree) {
		this.hljThree = hljThree;
	}
	@Column(name ="hlj_four",nullable=true,length=50)
	public Integer getHljFour() {
		return hljFour;
	}
	public void setHljFour(Integer hljFour) {
		this.hljFour = hljFour;
	}
	@Column(name ="sh_name",nullable=true,length=20)
	public String getShName() {
		return shName;
	}
	public void setShName(String shName) {
		this.shName = shName;
	}
	@Column(name ="sh_code",nullable=true,length=50)
	public Integer getShCode() {
		return shCode;
	}
	public void setShCode(Integer shCode) {
		this.shCode = shCode;
	}
	@Column(name ="sh_one",nullable=true,length=50)
	public Integer getShOne() {
		return shOne;
	}
	public void setShOne(Integer shOne) {
		this.shOne = shOne;
	}
	@Column(name ="sh_two",nullable=true,length=50)
	public Integer getShTwo() {
		return shTwo;
	}
	public void setShTwo(Integer shTwo) {
		this.shTwo = shTwo;
	}
	@Column(name ="sh_three",nullable=true,length=50)
	public Integer getShThree() {
		return shThree;
	}
	public void setShThree(Integer shThree) {
		this.shThree = shThree;
	}
	@Column(name ="sh_four",nullable=true,length=50)
	public Integer getShFour() {
		return shFour;
	}
	public void setShFour(Integer shFour) {
		this.shFour = shFour;
	}
	@Column(name ="js_name",nullable=true,length=20)
	public String getJsName() {
		return jsName;
	}
	public void setJsName(String jsName) {
		this.jsName = jsName;
	}
	@Column(name ="js_code",nullable=true,length=50)
	public Integer getJsCode() {
		return jsCode;
	}
	public void setJsCode(Integer jsCode) {
		this.jsCode = jsCode;
	}
	@Column(name ="js_one",nullable=true,length=50)
	public Integer getJsOne() {
		return jsOne;
	}
	public void setJsOne(Integer jsOne) {
		this.jsOne = jsOne;
	}
	@Column(name ="js_two",nullable=true,length=50)
	public Integer getJsTwo() {
		return jsTwo;
	}
	public void setJsTwo(Integer jsTwo) {
		this.jsTwo = jsTwo;
	}
	@Column(name ="js_three",nullable=true,length=50)
	public Integer getJsThree() {
		return jsThree;
	}
	public void setJsThree(Integer jsThree) {
		this.jsThree = jsThree;
	}
	@Column(name ="js_four",nullable=true,length=50)
	public Integer getJsFour() {
		return jsFour;
	}
	public void setJsFour(Integer jsFour) {
		this.jsFour = jsFour;
	}
	@Column(name ="zj_name",nullable=true,length=20)
	public String getZjName() {
		return zjName;
	}
	public void setZjName(String zjName) {
		this.zjName = zjName;
	}
	@Column(name ="zj_code",nullable=true,length=50)
	public Integer getZjCode() {
		return zjCode;
	}
	public void setZjCode(Integer zjCode) {
		this.zjCode = zjCode;
	}
	@Column(name ="zj_one",nullable=true,length=50)
	public Integer getZjOne() {
		return zjOne;
	}
	public void setZjOne(Integer zjOne) {
		this.zjOne = zjOne;
	}
	@Column(name ="zj_two",nullable=true,length=50)
	public Integer getZjTwo() {
		return zjTwo;
	}
	public void setZjTwo(Integer zjTwo) {
		this.zjTwo = zjTwo;
	}
	@Column(name ="zj_three",nullable=true,length=50)
	public Integer getZjThree() {
		return zjThree;
	}
	public void setZjThree(Integer zjThree) {
		this.zjThree = zjThree;
	}
	@Column(name ="zj_four",nullable=true,length=50)
	public Integer getZjFour() {
		return zjFour;
	}
	public void setZjFour(Integer zjFour) {
		this.zjFour = zjFour;
	}
	@Column(name ="ah_name",nullable=true,length=20)
	public String getAhName() {
		return ahName;
	}
	public void setAhName(String ahName) {
		this.ahName = ahName;
	}
	@Column(name ="ah_code",nullable=true,length=50)
	public Integer getAhCode() {
		return ahCode;
	}
	public void setAhCode(Integer ahCode) {
		this.ahCode = ahCode;
	}
	@Column(name ="ah_one",nullable=true,length=50)
	public Integer getAhOne() {
		return ahOne;
	}
	public void setAhOne(Integer ahOne) {
		this.ahOne = ahOne;
	}
	@Column(name ="ah_two",nullable=true,length=50)
	public Integer getAhTwo() {
		return ahTwo;
	}
	public void setAhTwo(Integer ahTwo) {
		this.ahTwo = ahTwo;
	}
	@Column(name ="ah_three",nullable=true,length=50)
	public Integer getAhThree() {
		return ahThree;
	}
	public void setAhThree(Integer ahThree) {
		this.ahThree = ahThree;
	}
	@Column(name ="ah_four",nullable=true,length=50)
	public Integer getAhFour() {
		return ahFour;
	}
	public void setAhFour(Integer ahFour) {
		this.ahFour = ahFour;
	}
	@Column(name ="fj_name",nullable=true,length=20)
	public String getFjName() {
		return fjName;
	}
	public void setFjName(String fjName) {
		this.fjName = fjName;
	}
	@Column(name ="fj_code",nullable=true,length=50)
	public Integer getFjCode() {
		return fjCode;
	}
	public void setFjCode(Integer fjCode) {
		this.fjCode = fjCode;
	}
	@Column(name ="fj_one",nullable=true,length=50)
	public Integer getFjOne() {
		return fjOne;
	}
	public void setFjOne(Integer fjOne) {
		this.fjOne = fjOne;
	}
	@Column(name ="fj_two",nullable=true,length=50)
	public Integer getFjTwo() {
			return fjTwo;
		}
    public void setFjTwo(Integer fjTwo) {
			this.fjTwo = fjTwo;
		}
		
	@Column(name ="fj_three",nullable=true,length=50)
	public Integer getFjThree() {
		return fjThree;
	}
	public void setFjThree(Integer fjThree) {
		this.fjThree = fjThree;
	}
	@Column(name ="fj_four",nullable=true,length=50)
	public Integer getFjFour() {
		return fjFour;
	}
	public void setFjFour(Integer fjFour) {
		this.fjFour = fjFour;
	}
	@Column(name ="jx_name",nullable=true,length=20)
	public String getJxName() {
		return jxName;
	}
	public void setJxName(String jxName) {
		this.jxName = jxName;
	}
	@Column(name ="jx_code",nullable=true,length=50)
	public Integer getJxCode() {
		return jxCode;
	}
	public void setJxCode(Integer jxCode) {
		this.jxCode = jxCode;
	}
	@Column(name ="jx_one",nullable=true,length=50)
	public Integer getJxOne() {
		return jxOne;
	}
	public void setJxOne(Integer jxOne) {
		this.jxOne = jxOne;
	}
	@Column(name ="jx_two",nullable=true,length=50)
	public Integer getJxTwo() {
		return jxTwo;
	}
	public void setJxTwo(Integer jxTwo) {
		this.jxTwo = jxTwo;
	}
	@Column(name ="jx_three",nullable=true,length=50)
	public Integer getJxThree() {
		return jxThree;
	}
	public void setJxThree(Integer jxThree) {
		this.jxThree = jxThree;
	}
	@Column(name ="jx_four",nullable=true,length=50)
	public Integer getJxFour() {
		return jxFour;
	}
	public void setJxFour(Integer jxFour) {
		this.jxFour = jxFour;
	}
	@Column(name ="sd_name",nullable=true,length=20)
	public String getSdName() {
		return sdName;
	}
	public void setSdName(String sdName) {
		this.sdName = sdName;
	}
	@Column(name ="sd_code",nullable=true,length=50)
	public Integer getSdCode() {
		return sdCode;
	}
	public void setSdCode(Integer sdCode) {
		this.sdCode = sdCode;
	}
	@Column(name ="sd_one",nullable=true,length=50)
	public Integer getSdOne() {
		return sdOne;
	}
	public void setSdOne(Integer sdOne) {
		this.sdOne = sdOne;
	}
	@Column(name ="sd_two",nullable=true,length=50)
	public Integer getSdTwo() {
		return sdTwo;
	}
	public void setSdTwo(Integer sdTwo) {
		this.sdTwo = sdTwo;
	}
	@Column(name ="sd_three",nullable=true,length=50)
	public Integer getSdThree() {
		return sdThree;
	}
	public void setSdThree(Integer sdThree) {
		this.sdThree = sdThree;
	}
	@Column(name ="sd_four",nullable=true,length=50)
	public Integer getSdFour() {
		return sdFour;
	}
	public void setSdFour(Integer sdFour) {
		this.sdFour = sdFour;
	}
	@Column(name ="henan_name",nullable=true,length=20)
	public String getHenanName() {
		return henanName;
	}
	public void setHenanName(String henanName) {
		this.henanName = henanName;
	}
	@Column(name ="henan_code",nullable=true,length=50)
	public Integer getHenanCode() {
		return henanCode;
	}
	public void setHenanCode(Integer henanCode) {
		this.henanCode = henanCode;
	}
	@Column(name ="henan_one",nullable=true,length=50)
	public Integer getHenanOne() {
		return henanOne;
	}
	public void setHenanOne(Integer henanOne) {
		this.henanOne = henanOne;
	}
	@Column(name ="henan_two",nullable=true,length=50)
	public Integer getHenanTwo() {
		return henanTwo;
	}
	public void setHenanTwo(Integer henanTwo) {
		this.henanTwo = henanTwo;
	}
	@Column(name ="henan_three",nullable=true,length=50)
	public Integer getHenanThree() {
		return henanThree;
	}
	public void setHenanThree(Integer henanThree) {
		this.henanThree = henanThree;
	}
	@Column(name ="henan_four",nullable=true,length=50)
	public Integer getHenanFour() {
		return henanFour;
	}
	public void setHenanFour(Integer henanFour) {
		this.henanFour = henanFour;
	}
	@Column(name ="hubei_name",nullable=true,length=20)
	public String getHubeiName() {
		return hubeiName;
	}
	public void setHubeiName(String hubeiName) {
		this.hubeiName = hubeiName;
	}
	@Column(name ="hubei_code",nullable=true,length=50)
	public Integer getHubeiCode() {
		return hubeiCode;
	}
	public void setHubeiCode(Integer hubeiCode) {
		this.hubeiCode = hubeiCode;
	}
	@Column(name ="hubei_one",nullable=true,length=50)
	public Integer getHubeiOne() {
		return hubeiOne;
	}
	public void setHubeiOne(Integer hubeiOne) {
		this.hubeiOne = hubeiOne;
	}
	@Column(name ="hubei_two",nullable=true,length=50)
	public Integer getHubeiTwo() {
		return hubeiTwo;
	}
	public void setHubeiTwo(Integer hubeiTwo) {
		this.hubeiTwo = hubeiTwo;
	}
	@Column(name ="hubei_three",nullable=true,length=50)
	public Integer getHubeiThree() {
		return hubeiThree;
	}
	public void setHubeiThree(Integer hubeiThree) {
		this.hubeiThree = hubeiThree;
	}
	@Column(name ="hubei_four",nullable=true,length=50)
	public Integer getHubeiFour() {
		return hubeiFour;
	}
	public void setHubeiFour(Integer hubeiFour) {
		this.hubeiFour = hubeiFour;
	}
	@Column(name ="hl_name",nullable=true,length=20)
	public String getHlName() {
		return hlName;
	}
	public void setHlName(String hlName) {
		this.hlName = hlName;
	}
	@Column(name ="hl_code",nullable=true,length=50)
	public Integer getHlCode() {
		return hlCode;
	}
	public void setHlCode(Integer hlCode) {
		this.hlCode = hlCode;
	}
	@Column(name ="hl_one",nullable=true,length=50)
	public Integer getHlOne() {
		return hlOne;
	}
	public void setHlOne(Integer hlOne) {
		this.hlOne = hlOne;
	}
	@Column(name ="hl_two",nullable=true,length=50)
	public Integer getHlTwo() {
		return hlTwo;
	}
	public void setHlTwo(Integer hlTwo) {
		this.hlTwo = hlTwo;
	}
	@Column(name ="hl_three",nullable=true,length=50)
	public Integer getHlThree() {
		return hlThree;
	}
	public void setHlThree(Integer hlThree) {
		this.hlThree = hlThree;
	}
	@Column(name ="hl_four",nullable=true,length=50)
	public Integer getHlFour() {
		return hlFour;
	}
	public void setHlFour(Integer hlFour) {
		this.hlFour = hlFour;
	}
	@Column(name ="gd_name",nullable=true,length=20)
	public String getGdName() {
		return gdName;
	}
	public void setGdName(String gdName) {
		this.gdName = gdName;
	}
	@Column(name ="gd_code",nullable=true,length=50)
	public Integer getGdCode() {
		return gdCode;
	}
	public void setGdCode(Integer gdCode) {
		this.gdCode = gdCode;
	}
	@Column(name ="gd_one",nullable=true,length=50)
	public Integer getGdOne() {
		return gdOne;
	}
	public void setGdOne(Integer gdOne) {
		this.gdOne = gdOne;
	}
	@Column(name ="gd_two",nullable=true,length=50)
	public Integer getGdTwo() {
		return gdTwo;
	}
	public void setGdTwo(Integer gdTwo) {
		this.gdTwo = gdTwo;
	}
	@Column(name ="gd_three",nullable=true,length=50)
	public Integer getGdThree() {
		return gdThree;
	}
	public void setGdThree(Integer gdThree) {
		this.gdThree = gdThree;
	}
	@Column(name ="gd_four",nullable=true,length=50)
	public Integer getGdFour() {
		return gdFour;
	}
	public void setGdFour(Integer gdFour) {
		this.gdFour = gdFour;
	}
	@Column(name ="gx_name",nullable=true,length=20)
	public String getGxName() {
		return gxName;
	}
	public void setGxName(String gxName) {
		this.gxName = gxName;
	}
	@Column(name ="gx_code",nullable=true,length=50)
	public Integer getGxCode() {
		return gxCode;
	}
	public void setGxCode(Integer gxCode) {
		this.gxCode = gxCode;
	}
	@Column(name ="gx_one",nullable=true,length=50)
	public Integer getGxOne() {
		return gxOne;
	}
	public void setGxOne(Integer gxOne) {
		this.gxOne = gxOne;
	}
	@Column(name ="gx_two",nullable=true,length=50)
	public Integer getGxTwo() {
		return gxTwo;
	}
	public void setGxTwo(Integer gxTwo) {
		this.gxTwo = gxTwo;
	}
	@Column(name ="gx_three",nullable=true,length=50)
	public Integer getGxThree() {
		return gxThree;
	}
	public void setGxThree(Integer gxThree) {
		this.gxThree = gxThree;
	}
	@Column(name ="gx_four",nullable=true,length=50)
	public Integer getGxFour() {
		return gxFour;
	}
	public void setGxFour(Integer gxFour) {
		this.gxFour = gxFour;
	}
	@Column(name ="hainan_name",nullable=true,length=20)
	public String getHainanName() {
		return hainanName;
	}
	public void setHainanName(String hainanName) {
		this.hainanName = hainanName;
	}
	@Column(name ="hainan_code",nullable=true,length=50)
	public Integer getHainanCode() {
		return hainanCode;
	}
	public void setHainanCode(Integer hainanCode) {
		this.hainanCode = hainanCode;
	}
	@Column(name ="hainan_one",nullable=true,length=50)
	public Integer getHainanOne() {
		return hainanOne;
	}
	public void setHainanOne(Integer hainanOne) {
		this.hainanOne = hainanOne;
	}
	@Column(name ="hainan_two",nullable=true,length=50)
	public Integer getHainanTwo() {
		return hainanTwo;
	}
	public void setHainanTwo(Integer hainanTwo) {
		this.hainanTwo = hainanTwo;
	}
	@Column(name ="hainan_three",nullable=true,length=50)
	public Integer getHainanThree() {
		return hainanThree;
	}
	public void setHainanThree(Integer hainanThree) {
		this.hainanThree = hainanThree;
	}
	@Column(name ="hainan_four",nullable=true,length=50)
	public Integer getHainanFour() {
		return hainanFour;
	}
	public void setHainanFour(Integer hainanFour) {
		this.hainanFour = hainanFour;
	}
	@Column(name ="cq_name",nullable=true,length=20)
	public String getCqName() {
		return cqName;
	}
	public void setCqName(String cqName) {
		this.cqName = cqName;
	}
	@Column(name ="cq_code",nullable=true,length=50)
	public Integer getCqCode() {
		return cqCode;
	}
	public void setCqCode(Integer cqCode) {
		this.cqCode = cqCode;
	}
	@Column(name ="cq_one",nullable=true,length=50)
	public Integer getCqOne() {
		return cqOne;
	}
	public void setCqOne(Integer cqOne) {
		this.cqOne = cqOne;
	}
	@Column(name ="cq_two",nullable=true,length=50)
	public Integer getCqTwo() {
		return cqTwo;
	}
	public void setCqTwo(Integer cqTwo) {
		this.cqTwo = cqTwo;
	}
	@Column(name ="cq_three",nullable=true,length=50)
	public Integer getCqThree() {
		return cqThree;
	}
	public void setCqThree(Integer cqThree) {
		this.cqThree = cqThree;
	}
	@Column(name ="cq_four",nullable=true,length=50)
	public Integer getCqFour() {
		return cqFour;
	}
	public void setCqFour(Integer cqFour) {
		this.cqFour = cqFour;
	}
	@Column(name ="sc_name",nullable=true,length=20)
	public String getScName() {
		return scName;
	}
	public void setScName(String scName) {
		this.scName = scName;
	}
	@Column(name ="sc_code",nullable=true,length=50)
	public Integer getScCode() {
		return scCode;
	}
	public void setScCode(Integer scCode) {
		this.scCode = scCode;
	}	
	@Column(name ="sc_one",nullable=true,length=50)
	public Integer getScOne() {
		return scOne;
	}
	public void setScOne(Integer scOne) {
		this.scOne = scOne;
	}
	@Column(name ="sc_two",nullable=true,length=50)
	public Integer getScTwo() {
		return scTwo;
	}
	public void setScTwo(Integer scTwo) {
		this.scTwo = scTwo;
	}
	@Column(name ="sc_three",nullable=true,length=50)
	public Integer getScThree() {
		return scThree;
	}
	public void setScThree(Integer scThree) {
		this.scThree = scThree;
	}
	@Column(name ="sc_four",nullable=true,length=50)
	public Integer getScFour() {
		return scFour;
	}
	public void setScFour(Integer scFour) {
		this.scFour = scFour;
	}
	@Column(name ="gz_name",nullable=true,length=20)
	public String getGzName() {
		return gzName;
	}
	public void setGzName(String gzName) {
		this.gzName = gzName;
	}
	@Column(name ="gz_code",nullable=true,length=50)
	public Integer getGzCode() {
		return gzCode;
	}
	public void setGzCode(Integer gzCode) {
		this.gzCode = gzCode;
	}
	@Column(name ="gz_one",nullable=true,length=50)
	public Integer getGzOne() {
		return gzOne;
	}
	public void setGzOne(Integer gzOne) {
		this.gzOne = gzOne;
	}
	@Column(name ="gz_two",nullable=true,length=50)
	public Integer getGzTwo() {
		return gzTwo;
	}
	public void setGzTwo(Integer gzTwo) {
		this.gzTwo = gzTwo;
	}
	@Column(name ="gz_three",nullable=true,length=50)
	public Integer getGzThree() {
		return gzThree;
	}
	public void setGzThree(Integer gzThree) {
		this.gzThree = gzThree;
	}
	@Column(name ="gz_four",nullable=true,length=50)
	public Integer getGzFour() {
		return gzFour;
	}
	public void setGzFour(Integer gzFour) {
		this.gzFour = gzFour;
	}
	@Column(name ="yn_name",nullable=true,length=20)
	public String getYnName() {
		return ynName;
	}
	public void setYnName(String ynName) {
		this.ynName = ynName;
	}
	@Column(name ="yn_code",nullable=true,length=50)
	public Integer getYnCode() {
		return ynCode;
	}
	public void setYnCode(Integer ynCode) {
		this.ynCode = ynCode;
	}
	@Column(name ="yn_one",nullable=true,length=50)
	public Integer getYnOne() {
		return ynOne;
	}
	public void setYnOne(Integer ynOne) {
		this.ynOne = ynOne;
	}
	@Column(name ="yn_two",nullable=true,length=50)
	public Integer getYnTwo() {
		return ynTwo;
	}
	public void setYnTwo(Integer ynTwo) {
		this.ynTwo = ynTwo;
	}
	@Column(name ="yn_three",nullable=true,length=50)
	public Integer getYnThree() {
		return ynThree;
	}
	public void setYnThree(Integer ynThree) {
		this.ynThree = ynThree;
	}
	@Column(name ="yn_four",nullable=true,length=50)
	public Integer getYnFour() {
		return ynFour;
	}
	public void setYnFour(Integer ynFour) {
		this.ynFour = ynFour;
	}
	@Column(name ="xz_name",nullable=true,length=20)
	public String getXzName() {
		return xzName;
	}
	public void setXzName(String xzName) {
		this.xzName = xzName;
	}
	@Column(name ="xz_code",nullable=true,length=50)
	public Integer getXzCode() {
		return xzCode;
	}
	public void setXzCode(Integer xzCode) {
		this.xzCode = xzCode;
	}
	@Column(name ="xz_one",nullable=true,length=50)
	public Integer getXzOne() {
		return xzOne;
	}
	public void setXzOne(Integer xzOne) {
		this.xzOne = xzOne;
	}
	@Column(name ="xz_two",nullable=true,length=50)
	public Integer getXzTwo() {
		return xzTwo;
	}
	public void setXzTwo(Integer xzTwo) {
		this.xzTwo = xzTwo;
	}
	@Column(name ="xz_three",nullable=true,length=50)
	public Integer getXzThree() {
		return xzThree;
	}
	public void setXzThree(Integer xzThree) {
		this.xzThree = xzThree;
	}
	@Column(name ="xz_four",nullable=true,length=50)
	public Integer getXzFour() {
		return xzFour;
	}
	public void setXzFour(Integer xzFour) {
		this.xzFour = xzFour;
	}
	@Column(name ="shanxi_name",nullable=true,length=20)
	public String getShanxiName() {
		return shanxiName;
	}
	public void setShanxiName(String shanxiName) {
		this.shanxiName = shanxiName;
	}
	@Column(name ="shanxi_code",nullable=true,length=50)
	public Integer getShanxiCode() {
		return shanxiCode;
	}
	public void setShanxiCode(Integer shanxiCode) {
		this.shanxiCode = shanxiCode;
	}
	@Column(name ="shanxi_one",nullable=true,length=50)
	public Integer getShanxiOne() {
		return shanxiOne;
	}
	public void setShanxiOne(Integer shanxiOne) {
		this.shanxiOne = shanxiOne;
	}
	@Column(name ="shanxi_two",nullable=true,length=50)
	public Integer getShanxiTwo() {
		return shanxiTwo;
	}
	public void setShanxiTwo(Integer shanxiTwo) {
		this.shanxiTwo = shanxiTwo;
	}
	@Column(name ="shanxi_three",nullable=true,length=50)
	public Integer getShanxiThree() {
		return shanxiThree;
	}
	public void setShanxiThree(Integer shanxiThree) {
		this.shanxiThree = shanxiThree;
	}
	@Column(name ="shanxi_four",nullable=true,length=50)
	public Integer getShanxiFour() {
		return shanxiFour;
	}
	public void setShanxiFour(Integer shanxiFour) {
		this.shanxiFour = shanxiFour;
	}
	@Column(name ="gansu_name",nullable=true,length=20)
	public String getGansuName() {
		return gansuName;
	}
	public void setGansuName(String gansuName) {
		this.gansuName = gansuName;
	}
	@Column(name ="gansu_code",nullable=true,length=50)
	public Integer getGansuCode() {
		return gansuCode;
	}
	public void setGansuCode(Integer gansuCode) {
		this.gansuCode = gansuCode;
	}
	@Column(name ="gansu_one",nullable=true,length=50)
	public Integer getGansuOne() {
		return gansuOne;
	}
	public void setGansuOne(Integer gansuOne) {
		this.gansuOne = gansuOne;
	}
	@Column(name ="gansu_two",nullable=true,length=50)
	public Integer getGansuTwo() {
		return gansuTwo;
	}
	public void setGansuTwo(Integer gansuTwo) {
		this.gansuTwo = gansuTwo;
	}
	@Column(name ="gansu_three",nullable=true,length=50)
	public Integer getGansuThree() {
		return gansuThree;
	}
	public void setGansuThree(Integer gansuThree) {
		this.gansuThree = gansuThree;
	}
	@Column(name ="gansu_four",nullable=true,length=50)
	public Integer getGansuFour() {
		return gansuFour;
	}
	public void setGansuFour(Integer gansuFour) {
		this.gansuFour = gansuFour;
	}
	@Column(name ="qinghai_name",nullable=true,length=20)
	public String getQinghaiName() {
		return qinghaiName;
	}
	public void setQinghaiName(String qinghaiName) {
		this.qinghaiName = qinghaiName;
	}
	@Column(name ="qinghai_code",nullable=true,length=50)
	public Integer getQinghaiCode() {
		return qinghaiCode;
	}
	public void setQinghaiCode(Integer qinghaiCode) {
		this.qinghaiCode = qinghaiCode;
	}
	@Column(name ="qinghai_one",nullable=true,length=50)
	public Integer getQinghaiOne() {
		return qinghaiOne;
	}
	public void setQinghaiOne(Integer qinghaiOne) {
		this.qinghaiOne = qinghaiOne;
	}
	@Column(name ="qinghai_two",nullable=true,length=50)
	public Integer getQinghaiTwo() {
		return qinghaiTwo;
	}
	public void setQinghaiTwo(Integer qinghaiTwo) {
		this.qinghaiTwo = qinghaiTwo;
	}
	@Column(name ="qinghai_three",nullable=true,length=50)
	public Integer getQinghaiThree() {
		return qinghaiThree;
	}
	public void setQinghaiThree(Integer qinghaiThree) {
		this.qinghaiThree = qinghaiThree;
	}
	@Column(name ="qinghai_four",nullable=true,length=50)
	public Integer getQinghaiFour() {
		return qinghaiFour;
	}
	public void setQinghaiFour(Integer qinghaiFour) {
		this.qinghaiFour = qinghaiFour;
	}
	@Column(name ="nx_name",nullable=true,length=20)
	public String getNxName() {
		return nxName;
	}
	public void setNxName(String nxName) {
		this.nxName = nxName;
	}
	@Column(name ="nx_code",nullable=true,length=50)
	public Integer getNxCode() {
		return nxCode;
	}
	public void setNxCode(Integer nxCode) {
		this.nxCode = nxCode;
	}
	@Column(name ="nx_one",nullable=true,length=50)
	public Integer getNxOne() {
		return nxOne;
	}
	public void setNxOne(Integer nxOne) {
		this.nxOne = nxOne;
	}
	@Column(name ="nx_two",nullable=true,length=50)
	public Integer getNxTwo() {
		return nxTwo;
	}
	public void setNxTwo(Integer nxTwo) {
		this.nxTwo = nxTwo;
	}
	@Column(name ="nx_three",nullable=true,length=50)
	public Integer getNxThree() {
		return nxThree;
	}
	public void setNxThree(Integer nxThree) {
		this.nxThree = nxThree;
	}
	@Column(name ="nx_four",nullable=true,length=50)
	public Integer getNxFour() {
		return nxFour;
	}
	public void setNxFour(Integer nxFour) {
		this.nxFour = nxFour;
	}
	@Column(name ="xj_name",nullable=true,length=20)
	public String getXjName() {
		return xjName;
	}
	public void setXjName(String xjName) {
		this.xjName = xjName;
	}
	@Column(name ="xj_code",nullable=true,length=50)
	public Integer getXjCode() {
		return xjCode;
	}
	public void setXjCode(Integer xjCode) {
		this.xjCode = xjCode;
	}
	@Column(name ="xj_one",nullable=true,length=50)
	public Integer getXjOne() {
		return xjOne;
	}
	public void setXjOne(Integer xjOne) {
		this.xjOne = xjOne;
	}
	@Column(name ="xj_two",nullable=true,length=50)
	public Integer getXjTwo() {
		return xjTwo;
	}
	public void setXjTwo(Integer xjTwo) {
		this.xjTwo = xjTwo;
	}
	@Column(name ="xj_three",nullable=true,length=50)
	public Integer getXjThree() {
		return xjThree;
	}
	public void setXjThree(Integer xjThree) {
		this.xjThree = xjThree;
	}
	@Column(name ="xj_four",nullable=true,length=50)
	public Integer getXjFour() {
		return xjFour;
	}
	public void setXjFour(Integer xjFour) {
		this.xjFour = xjFour;
	}
	@Column(name ="day_org_name",nullable=true,length=20)
	public String getDayOrgName() {
		return dayOrgName;
	}
	public void setDayOrgName(String dayOrgName) {
		this.dayOrgName = dayOrgName;
	}
	@Column(name ="day_org_code",nullable=true,length=50)
	public Integer getDayOrgCode() {
		return dayOrgCode;
	}
	public void setDayOrgCode(Integer dayOrgCode) {
		this.dayOrgCode = dayOrgCode;
	}
	@Column(name ="day_org_one",nullable=true,length=50)
	public Integer getDayOrgOne() {
		return dayOrgOne;
	}
	public void setDayOrgOne(Integer dayOrgOne) {
		this.dayOrgOne = dayOrgOne;
	}
	@Column(name ="day_org_two",nullable=true,length=50)
	public Integer getDayOrgTwo() {
		return dayOrgTwo;
	}
	public void setDayOrgTwo(Integer dayOrgTwo) {
		this.dayOrgTwo = dayOrgTwo;
	}
	@Column(name ="day_org_three",nullable=true,length=50)
	public Integer getDayOrgThree() {
		return dayOrgThree;
	}
	public void setDayOrgThree(Integer dayOrgThree) {
		this.dayOrgThree = dayOrgThree;
	}
	@Column(name ="day_org_four",nullable=true,length=50)
	public Integer getDayOrgFour() {
		return dayOrgFour;
	}
	public void setDayOrgFour(Integer dayOrgFour) {
		this.dayOrgFour = dayOrgFour;
	}
	@Column(name ="night_time_name",nullable=true,length=20)
	public String getNightTimeName() {
		return nightTimeName;
	}
	public void setNightTimeName(String nightTimeName) {
		this.nightTimeName = nightTimeName;
	}
	@Column(name ="night_time_code",nullable=true,length=50)
	public Integer getNightTimeCode() {
		return nightTimeCode;
	}
	public void setNightTimeCode(Integer nightTimeCode) {
		this.nightTimeCode = nightTimeCode;
	}
	@Column(name ="night_time_one",nullable=true,length=50)
	public Integer getNightTimeOne() {
		return nightTimeOne;   
	}
	public void setNightTimeOne(Integer nightTimeOne) {
		this.nightTimeOne = nightTimeOne;
	}
	@Column(name ="night_time_two",nullable=true,length=50)
	public Integer getNightTimeTwo() {
		return nightTimeTwo;
	}
	public void setNightTimeTwo(Integer nightTimeTwo) {
		this.nightTimeTwo = nightTimeTwo;
	}
	@Column(name ="night_time_three",nullable=true,length=50)
	public Integer getNightTimeThree() {
		return nightTimeThree;
	}
	public void setNightTimeThree(Integer nightTimeThree) {
		this.nightTimeThree = nightTimeThree;
	}
	@Column(name ="night_time_four",nullable=true,length=50)
	public Integer getNightTimeFour() {
		return nightTimeFour;
	}
	public void setNightTimeFour(Integer nightTimeFour) {
		this.nightTimeFour = nightTimeFour;
	}
	@Column(name ="night_org_name",nullable=true,length=20)
	public String getNightOrgName() {
		return nightOrgName;
	}
	public void setNightOrgName(String nightOrgName) {
		this.nightOrgName = nightOrgName;
	}
	@Column(name ="night_org_code",nullable=true,length=50)
	public Integer getNightOrgCode() {
		return nightOrgCode;
	}
	public void setNightOrgCode(Integer nightOrgCode) {
		this.nightOrgCode = nightOrgCode;
	}
	@Column(name ="night_org_one",nullable=true,length=50)
	public Integer getNightOrgOne() {
		return nightOrgOne;
	}
	public void setNightOrgOne(Integer nightOrgOne) {
		this.nightOrgOne = nightOrgOne;
	}	
	@Column(name ="night_org_two",nullable=true,length=50)
    public Integer getNightOrgTwo() {
		return nightOrgTwo;
	}
	public void setNightOrgTwo(Integer nightOrgTwo) {
		this.nightOrgTwo = nightOrgTwo;
	}
	@Column(name ="night_org_three",nullable=true,length=50)
	public Integer getNightOrgThree() {
		return nightOrgThree;
	}
	public void setNightOrgThree(Integer nightOrgThree) {
		this.nightOrgThree = nightOrgThree;
	}
	@Column(name ="night_org_four",nullable=true,length=50)
	public Integer getNightOrgFour() {
		return nightOrgFour;
	}
	public void setNightOrgFour(Integer nightOrgFour) {
		this.nightOrgFour = nightOrgFour;
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
	@Column
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name="guo",length=1000)
	public String getGuo() {
		return guo;
	}
	public void setGuo(String guo) {
		this.guo = guo;
	}
}
