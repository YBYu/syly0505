package com.zzc.web.scenicmanage.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;

import com.zzc.poi.excel.annotation.Excel;
import com.zzc.web.system.pojo.base.TSBaseUser;

@Entity
@Table(name = "t_scenicspot_info")
@SuppressWarnings("serial")
public class ScenicData implements java.io.Serializable {

	private String id;
	private String userId;
	private Integer sid;
	private String code;//景区编号
	@Excel(name="景区名称",orderNum="1")
	private String name;//景区名称
	private String province;//所在省份
	private String city;//所在市
	private String area; // 区
	private String address;//所在地址
	private String zipcode;//邮编
	private Integer orgproperty;//机构性质
	private String url;//网址
	private String consultphone;//咨询电话
	private String scenictype; // 景区类型，1-自然景观，2-历史文化，3-度假休闲，4-主题游乐，5-博物馆，6-乡村旅游，7-工业旅游，8-红色旅游，9-科技教育，10-其他
	private String opentime;//开业时间
	private Double daylimit;//日接待最大容量
	private Double acreage;//面积
	private String investunit;//投资主题
	private String superiorunit;//上级主管单位
	private String level; // 景区级别
	private String leveldate;//等级公告时间
	private String chargename;//负责人姓名
	private String chargephone;//负责人电话
	private String chargeemail;//负责人邮箱
	private String informantname;//填报人姓名
	private String informantphone;//填报人电话
	private String informantemail;//填报人邮箱
	private String informantqq;//填报人QQ
	private String tickettype;//门票形式
	private byte ticketisOne;//是否单一制门票
	private String ticketvalidDate;//门票有效期
	private String scenictitle;//景区所获称号
	private String remarks;//备注
	private String status; //审核状态
	private String   bayType;//所属湾区
	private Date reportDate;//填报时间
	private String provinceSubmit;//是否提交给上级系统
	private String comments;//审核意见


	private Date  monthTime;
	private String termType1;
	private String price1;
	private String beginMonth1;
	private String beginDate1;
	private String endMonth1;
	private String endDate1;
	private String openHour1;
	private String openMinute1;
	private String closeHour1;
	private String closeMinute1;
	private String termType2;
	private String price2;
	private String beginMonth2;
	private String beginDate2;
	private String endMonth2;
	private String endDate2;
	private String openHour2;
	private String openMinute2;
	private String closeHour2;
	private String closeMinute2;
	private String termType3;
	private String price3;
	private String beginMonth3;
	private String beginDate3;
	private String endMonth3;
	private String endDate3;
	private String openHour3;
	private String openMinute3;
	private String closeHour3;
	private String closeMinute3;
	private String termType4;
	private String price4;
	private String beginMonth4;
	private String beginDate4;
	private String endMonth4;
	private String endDate4;
	private String openHour4;
	private String openMinute4;
	private String closeHour4;
	private String closeMinute4;
	private String termType5;
	private String price5;
	private String beginMonth5;
	private String beginDate5;
	private String endMonth5;
	private String endDate5;
	private String openHour5;
	private String openMinute5;
	private String closeHour5;
	private String closeMinute5;
	private List<ScenicSpotWeek> scenicWeekList;
	private List<ScenicSpotMonth> scenicSpotMonth;
	private List<ScenicSpotAnnual> scenicSpotAnnual;
	private List<ScenicSpotQuarter>scenicQuarterList;
	private ScenicSpotMonth spotMonth;
	private ScenicSpotWeek spotWeek;
	private ScenicSpotAnnual scenicYear;
	private ScenicSpotQuarter spotQuarter;
	private TSBaseUser tSBaseUser;
	private Integer score;

@Id
	/*@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")*/
	@Column(name = "id", nullable = false, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	@Column(name = "user_id", nullable = true, length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
  
	@Column(name = "sid", nullable = true, length = 50)
	public Integer getSid() {
		return sid;
	}



	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "code", nullable = true, length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = true, length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "province", nullable = true, length = 20)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", nullable = true, length = 20)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "area", nullable = true, length = 12)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "address", nullable = true, length = 200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zip_code", nullable = true, length = 20)
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "org_property", nullable = true, length = 10)
	public Integer getOrgproperty() {
		return orgproperty;
	}

	public void setOrgproperty(Integer orgproperty) {
		this.orgproperty = orgproperty;
	}
	/*public ScenicSpotProperties getScenicSpotProperties() {
	return scenicSpotProperties;
}

public void setScenicSpotProperties(ScenicSpotProperties scenicSpotProperties) {
	this.scenicSpotProperties = scenicSpotProperties;
}*/


	@Column(name = "consult_phone", nullable = true, length = 20)
	public String getConsultphone() {
		return consultphone;
	}


	public void setConsultphone(String consultphone) {
		this.consultphone = consultphone;
	}

	@Column(name = "url", nullable = true, length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "scenic_type", nullable = true, length = 12)
	public String getScenictype() {
		return scenictype;
	}

	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
	}

	@Column(name = "open_time", nullable = true)
	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	@Column(name = "day_limit", nullable = true)
	public Double getDaylimit() {
		return daylimit;
	}

	public void setDaylimit(Double daylimit) {
		this.daylimit = daylimit;
	}

	@Column(name = "invest_unit", nullable = true, length = 200)
	public String getInvestunit() {
		return investunit;
	}

	public void setInvestunit(String investunit) {
		this.investunit = investunit;
	}

	@Column(name = "superior_unit", nullable = true, length = 200)
	public String getSuperiorunit() {
		return superiorunit;
	}

	public void setSuperiorunit(String superiorunit) {
		this.superiorunit = superiorunit;
	}

	@Column(name = "acreage", nullable = true)
	public Double getAcreage() {
		return acreage;
	}

	public void setAcreage(Double acreage) {
		this.acreage = acreage;
	}

	@Column(name = "level", nullable = true, length = 12)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "level_date", nullable = true)
	public String getLeveldate() {
		return leveldate;
	}

	public void setLeveldate(String leveldate) {
		this.leveldate = leveldate;
	}

	@Column(name = "charge_name", nullable = true, length = 20)
	public String getChargename() {
		return chargename;
	}

	public void setChargename(String chargename) {
		this.chargename = chargename;
	}

	@Column(name = "charge_phone", nullable = true, length = 20)
	public String getChargephone() {
		return chargephone;
	}

	public void setChargephone(String chargephone) {
		this.chargephone = chargephone;
	}

	@Column(name = "charge_email", nullable = true, length = 50)
	public String getChargeemail() {
		return chargeemail;
	}

	public void setChargeemail(String chargeemail) {
		this.chargeemail = chargeemail;
	}

	@Column(name = "informant_name", nullable = true, length = 20)
	public String getInformantname() {
		return informantname;
	}

	public void setInformantname(String informantname) {
		this.informantname = informantname;
	}

	@Column(name = "informant_phone", nullable = true, length = 20)
	public String getInformantphone() {
		return informantphone;
	}

	public void setInformantphone(String informantphone) {
		this.informantphone = informantphone;
	}

	@Column(name = "informant_email", nullable = true, length = 50)
	public String getInformantemail() {
		return informantemail;
	}

	public void setInformantemail(String informantemail) {
		this.informantemail = informantemail;
	}

	@Column(name = "informant_qq", nullable = true, length = 20)
	public String getInformantqq() {
		return informantqq;
	}

	public void setInformantqq(String informantqq) {
		this.informantqq = informantqq;
	}

	@Column(name = "ticket_type", nullable = true, length =200)
	public String getTickettype() {
		return tickettype;
	}

	public void setTickettype(String tickettype) {
		this.tickettype = tickettype;
	}

	@Column(name = "ticket_isOne", nullable = true, length = 4)
	public byte getTicketisOne() {
		return ticketisOne;
	}

	public void setTicketisOne(byte ticketisOne) {
		this.ticketisOne = ticketisOne;
	}

	@Column(name = "ticket_validDate", nullable = true, length = 50)
	public String getTicketvalidDate() {
		return ticketvalidDate;
	}

	public void setTicketvalidDate(String ticketvalidDate) {
		this.ticketvalidDate = ticketvalidDate;
	}

	@Column(name = "scenic_title", nullable = true, length = 100)
	public String getScenictitle() {
		return scenictitle;
	}

	public void setScenictitle(String scenictitle) {
		this.scenictitle = scenictitle;
	}

	@Column(name = "remarks", nullable = true, length = 500)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "status", nullable = true, length = 12)
	public String getStatus() {
		return status;
	}
	@Column(name = "comments", length = 2000)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OrderBy(clause = "year desc,cycle desc")
	@BatchSize(size = 3)
	@OneToMany(mappedBy = "scenicData", cascade = CascadeType.REMOVE)
	public List<ScenicSpotWeek> getScenicWeekList() {
		return scenicWeekList;
	}

	public void setScenicWeekList(List<ScenicSpotWeek> scenicWeekList) {
		this.scenicWeekList = scenicWeekList;
	}

	@OrderBy(clause = "reportdate desc")
	@BatchSize(size = 3)
	@OneToMany(mappedBy = "scenicData", cascade = CascadeType.REMOVE)
	public List<ScenicSpotMonth> getScenicSpotMonth() {
		return scenicSpotMonth;
	}
	@Fetch(value = FetchMode.SELECT)
	@OrderBy(clause = "year desc")
	@BatchSize(size = 3)
	@OneToMany(mappedBy = "scenicData", cascade = CascadeType.REMOVE)
	public List<ScenicSpotAnnual> getScenicSpotAnnual() {
		return scenicSpotAnnual;
	}

	public void setScenicSpotAnnual(List<ScenicSpotAnnual> scenicSpotAnnual) {
		this.scenicSpotAnnual = scenicSpotAnnual;
	}
	
		
	
	@OneToMany(mappedBy = "scenicData", cascade = CascadeType.REMOVE)
	public List<ScenicSpotQuarter> getScenicQuarterList() {
		return scenicQuarterList;
	}

	public void setScenicQuarterList(List<ScenicSpotQuarter> scenicQuarterList) {
		this.scenicQuarterList = scenicQuarterList;
	}

	public void setSpotMonth(ScenicSpotMonth spotMonth) {
		this.spotMonth = spotMonth;
	}

	public void setScenicSpotMonth(List<ScenicSpotMonth> scenicSpotMonth) {
		this.scenicSpotMonth = scenicSpotMonth;
	}

	@Transient
	public ScenicSpotMonth getSpotMonth() {
		return spotMonth;
	}

	@Transient
	public ScenicSpotWeek getSpotWeek() {
		return spotWeek;
	}

	public void setSpotWeek(ScenicSpotWeek spotWeek) {
		this.spotWeek = spotWeek;
	}

	@Transient
	public ScenicSpotAnnual getScenicYear() {
		return scenicYear;
	}

	public void setScenicYear(ScenicSpotAnnual scenicYear) {
		this.scenicYear = scenicYear;
	}
    
	@Transient
	public ScenicSpotQuarter getSpotQuarter() {
		return spotQuarter;
	}

	public void setSpotQuarter(ScenicSpotQuarter spotQuarter) {
		this.spotQuarter = spotQuarter;
	}
	@Column(name = "report_date", nullable = true)
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	@Column(name = "bay_type", nullable = true, length = 12)
	public String getBayType() {
		return bayType;
	}

	public void setBayType(String bayType) {
		this.bayType = bayType;
	}
	
	@Column(name = "province_submit")
	public String getProvinceSubmit() {
		return provinceSubmit;
	}

	public void setProvinceSubmit(String provinceSubmit) {
		this.provinceSubmit = provinceSubmit;
	}

	@Column(name = "month_time")
	public Date getMonthTime() {
		return monthTime;
	}
	public void setMonthTime(Date monthTime) {
		this.monthTime = monthTime;
	}
	
	
	public TSBaseUser gettSBaseUser() {
		return tSBaseUser;
	}

	public void settSBaseUser(TSBaseUser tSBaseUser) {
		this.tSBaseUser = tSBaseUser;
	}
	@Column(name = "term_type1", nullable = true, length = 11)
	public String getTermType1() {
		return termType1;
	}

	public void setTermType1(String termType1) {
		this.termType1 = termType1;
	}

	@Column(name = "price1", nullable = true, length = 11)
	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	@Column(name = "begin_month1", nullable = true, length = 11)
	public String getBeginMonth1() {
		return beginMonth1;
	}

	public void setBeginMonth1(String beginMonth1) {
		this.beginMonth1 = beginMonth1;
	}

	@Column(name = "begin_date1", nullable = true, length = 12)
	public String getBeginDate1() {
		return beginDate1;
	}

	public void setBeginDate1(String beginDate1) {
		this.beginDate1 = beginDate1;
	}

	@Column(name = "end_month1", nullable = true, length = 11)
	public String getEndMonth1() {
		return endMonth1;
	}

	public void setEndMonth1(String endMonth1) {
		this.endMonth1 = endMonth1;
	}

	@Column(name = "end_date1", nullable = true, length = 11)
	public String getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(String endDate1) {
		this.endDate1 = endDate1;
	}

	@Column(name = "open_hour1", nullable = true, length = 11)
	public String getOpenHour1() {
		return openHour1;
	}

	public void setOpenHour1(String openHour1) {
		this.openHour1 = openHour1;
	}

	@Column(name = "open_minute1", nullable = true, length = 11)
	public String getOpenMinute1() {
		return openMinute1;
	}

	public void setOpenMinute1(String openMinute1) {
		this.openMinute1 = openMinute1;
	}

	@Column(name = "close_hour1", nullable = true, length = 11)
	public String getCloseHour1() {
		return closeHour1;
	}

	public void setCloseHour1(String closeHour1) {
		this.closeHour1 = closeHour1;
	}

	@Column(name = "close_minute1", nullable = true, length = 11)
	public String getCloseMinute1() {
		return closeMinute1;
	}

	public void setCloseMinute1(String closeMinute1) {
		this.closeMinute1 = closeMinute1;
	}


	@Column(name = "term_type2", nullable = true, length = 12)
	public String getTermType2() {
		return termType2;
	}

	public void setTermType2(String termType2) {
		this.termType2 = termType2;
	}

	@Column(name = "price2", nullable = true, length = 12)
	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	@Column(name = "begin_month2", nullable = true, length = 12)
	public String getBeginMonth2() {
		return beginMonth2;
	}

	public void setBeginMonth2(String beginMonth2) {
		this.beginMonth2 = beginMonth2;
	}

	@Column(name = "begin_date2", nullable = true, length = 12)
	public String getBeginDate2() {
		return beginDate2;
	}

	public void setBeginDate2(String beginDate2) {
		this.beginDate2 = beginDate2;
	}

	@Column(name = "end_month2", nullable = true, length = 12)
	public String getEndMonth2() {
		return endMonth2;
	}

	public void setEndMonth2(String endMonth2) {
		this.endMonth2 = endMonth2;
	}

	@Column(name = "end_date2", nullable = true, length = 12)
	public String getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(String endDate2) {
		this.endDate2 = endDate2;
	}

	@Column(name = "open_hour2", nullable = true, length = 12)
	public String getOpenHour2() {
		return openHour2;
	}

	public void setOpenHour2(String openHour2) {
		this.openHour2 = openHour2;
	}

	@Column(name = "open_minute2", nullable = true, length = 12)
	public String getOpenMinute2() {
		return openMinute2;
	}

	public void setOpenMinute2(String openMinute2) {
		this.openMinute2 = openMinute2;
	}

	@Column(name = "close_hour2", nullable = true, length = 12)
	public String getCloseHour2() {
		return closeHour2;
	}

	public void setCloseHour2(String closeHour2) {
		this.closeHour2 = closeHour2;
	}

	@Column(name = "close_minute2", nullable = true, length = 12)
	public String getCloseMinute2() {
		return closeMinute2;
	}

	public void setCloseMinute2(String closeMinute2) {
		this.closeMinute2 = closeMinute2;
	}


	@Column(name = "term_type3", nullable = true, length = 33)
	public String getTermType3() {
		return termType3;
	}

	public void setTermType3(String termType3) {
		this.termType3 = termType3;
	}

	@Column(name = "price3", nullable = true, length = 33)
	public String getPrice3() {
		return price3;
	}

	public void setPrice3(String price3) {
		this.price3 = price3;
	}

	@Column(name = "begin_month3", nullable = true, length = 33)
	public String getBeginMonth3() {
		return beginMonth3;
	}

	public void setBeginMonth3(String beginMonth3) {
		this.beginMonth3 = beginMonth3;
	}

	@Column(name = "begin_date3", nullable = true, length = 32)
	public String getBeginDate3() {
		return beginDate3;
	}

	public void setBeginDate3(String beginDate3) {
		this.beginDate3 = beginDate3;
	}

	@Column(name = "end_month3", nullable = true, length = 33)
	public String getEndMonth3() {
		return endMonth3;
	}

	public void setEndMonth3(String endMonth3) {
		this.endMonth3 = endMonth3;
	}

	@Column(name = "end_date3", nullable = true, length = 33)
	public String getEndDate3() {
		return endDate3;
	}

	public void setEndDate3(String endDate3) {
		this.endDate3 = endDate3;
	}

	@Column(name = "open_hour3", nullable = true, length = 33)
	public String getOpenHour3() {
		return openHour3;
	}

	public void setOpenHour3(String openHour3) {
		this.openHour3 = openHour3;
	}

	@Column(name = "open_minute3", nullable = true, length = 33)
	public String getOpenMinute3() {
		return openMinute3;
	}

	public void setOpenMinute3(String openMinute3) {
		this.openMinute3 = openMinute3;
	}

	@Column(name = "close_hour3", nullable = true, length = 33)
	public String getCloseHour3() {
		return closeHour3;
	}

	public void setCloseHour3(String closeHour3) {
		this.closeHour3 = closeHour3;
	}

	@Column(name = "close_minute3", nullable = true, length = 33)
	public String getCloseMinute3() {
		return closeMinute3;
	}

	public void setCloseMinute3(String closeMinute3) {
		this.closeMinute3 = closeMinute3;
	}
	@Column(name = "term_type4", nullable = true, length = 14)
	public String getTermType4() {
		return termType4;
	}

	public void setTermType4(String termType4) {
		this.termType4 = termType4;
	}

	@Column(name = "price4", nullable = true, length = 14)
	public String getPrice4() {
		return price4;
	}

	public void setPrice4(String price4) {
		this.price4 = price4;
	}

	@Column(name = "begin_month4", nullable = true, length = 14)
	public String getBeginMonth4() {
		return beginMonth4;
	}

	public void setBeginMonth4(String beginMonth4) {
		this.beginMonth4 = beginMonth4;
	}

	@Column(name = "begin_date4", nullable = true, length = 14)
	public String getBeginDate4() {
		return beginDate4;
	}

	public void setBeginDate4(String beginDate4) {
		this.beginDate4 = beginDate4;
	}

	@Column(name = "end_month4", nullable = true, length = 14)
	public String getEndMonth4() {
		return endMonth4;
	}

	public void setEndMonth4(String endMonth4) {
		this.endMonth4 = endMonth4;
	}

	@Column(name = "end_date4", nullable = true, length = 14)
	public String getEndDate4() {
		return endDate4;
	}

	public void setEndDate4(String endDate4) {
		this.endDate4 = endDate4;
	}

	@Column(name = "open_hour4", nullable = true, length = 14)
	public String getOpenHour4() {
		return openHour4;
	}

	public void setOpenHour4(String openHour4) {
		this.openHour4 = openHour4;
	}

	@Column(name = "open_minute4", nullable = true, length = 14)
	public String getOpenMinute4() {
		return openMinute4;
	}

	public void setOpenMinute4(String openMinute4) {
		this.openMinute4 = openMinute4;
	}

	@Column(name = "close_hour4", nullable = true, length = 14)
	public String getCloseHour4() {
		return closeHour4;
	}

	public void setCloseHour4(String closeHour4) {
		this.closeHour4 = closeHour4;
	}

	@Column(name = "close_minute4", nullable = true, length = 14)
	public String getCloseMinute4() {
		return closeMinute4;
	}

	public void setCloseMinute4(String closeMinute4) {
		this.closeMinute4 = closeMinute4;
	}
	@Column(name = "term_type5", nullable = true, length = 15)
	public String getTermType5() {
		return termType5;
	}

	public void setTermType5(String termType5) {
		this.termType5 = termType5;
	}

	@Column(name = "price5", nullable = true, length = 15)
	public String getPrice5() {
		return price5;
	}

	public void setPrice5(String price5) {
		this.price5 = price5;
	}

	@Column(name = "begin_month5", nullable = true, length = 15)
	public String getBeginMonth5() {
		return beginMonth5;
	}

	public void setBeginMonth5(String beginMonth5) {
		this.beginMonth5 = beginMonth5;
	}

	@Column(name = "begin_date5", nullable = true, length = 15)
	public String getBeginDate5() {
		return beginDate5;
	}

	public void setBeginDate5(String beginDate5) {
		this.beginDate5 = beginDate5;
	}

	@Column(name = "end_month5", nullable = true, length = 15)
	public String getEndMonth5() {
		return endMonth5;
	}

	public void setEndMonth5(String endMonth5) {
		this.endMonth5 = endMonth5;
	}

	@Column(name = "end_date5", nullable = true, length = 15)
	public String getEndDate5() {
		return endDate5;
	}

	public void setEndDate5(String endDate5) {
		this.endDate5 = endDate5;
	}

	@Column(name = "open_hour5", nullable = true, length = 15)
	public String getOpenHour5() {
		return openHour5;
	}

	public void setOpenHour5(String openHour5) {
		this.openHour5 = openHour5;
	}

	@Column(name = "open_minute5", nullable = true, length = 15)
	public String getOpenMinute5() {
		return openMinute5;
	}

	public void setOpenMinute5(String openMinute5) {
		this.openMinute5 = openMinute5;
	}

	@Column(name = "close_hour5", nullable = true, length = 15)
	public String getCloseHour5() {
		return closeHour5;
	}

	public void setCloseHour5(String closeHour5) {
		this.closeHour5 = closeHour5;
	}

	@Column(name = "close_minute5", nullable = true, length = 15)
	public String getCloseMinute5() {
		return closeMinute5;
	}

	public void setCloseMinute5(String closeMinute5) {
		this.closeMinute5 = closeMinute5;
	}
	@Column(name = "score")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	/*	@Fetch(value = FetchMode.SELECT)
	@BatchSize(size = 3)
	@OneToMany(mappedBy = "scenicData", cascade = CascadeType.REMOVE)
	public List<ScenicSpotTicket> getScenicSpotTicket() {
		return scenicSpotTicket;
	}

	public void setScenicSpotTicket(List<ScenicSpotTicket> scenicSpotTicket) {
		this.scenicSpotTicket = scenicSpotTicket;
	}
*/
	@Transient
	public static ScenicDataSta copy(ScenicData s) {
		ScenicDataSta t = new ScenicDataSta();
		 t.setAcreage(s.getAcreage());
		 t.setAddress(s.getAddress());
		 t.setArea(s.getArea());
		 t.setBayType(s.getBayType());
		 t.setChargeemail(s.getChargeemail());
		 t.setChargename(s.getChargename());
		 t.setChargephone(s.getChargephone());
		 t.setCity(s.getCity());
		 t.setCode(s.getCode());
		 t.setConsultphone(s.getConsultphone());
		 t.setDaylimit(s.getDaylimit());
		 t.setId(s.getId());
		 t.setInformantemail(s.getInformantemail());
		 t.setInformantname(s.getInformantname());
		 t.setInformantphone(s.getInformantphone());
		 t.setInformantqq(s.getInformantqq());
		 t.setInvestunit(s.getInvestunit());
		 t.setLevel(s.getLevel());
		 t.setLeveldate(s.getLeveldate());
		 t.setMonthTime(s.getMonthTime());
		 t.setName(s.getName());
		 t.setOpentime(s.getOpentime());
		 t.setOrgproperty(s.getOrgproperty());
		 t.setProvince(s.getProvince());
		 t.setRemarks(s.getRemarks());
		 t.setZipcode(s.getZipcode());
		 t.setUrl(s.getUrl());
		 t.setSuperiorunit(s.getSuperiorunit());
		 t.setScenictitle(s.getScenictitle());
		 t.setUserId(s.getUserId());
		 t.setScenictype(s.getScenictype());
		 t.setTermType1(s.getTermType1());
		 t.setPrice1(s.getPrice1());
		 t.setBeginDate1(s.getBeginDate1());
		 t.setBeginMonth1(s.getBeginMonth1());
		 t.setEndDate1(s.getEndDate1());
		 t.setEndMonth1(s.getEndMonth1());
		 t.setOpenHour1(s.getOpenHour1());
		 t.setOpenMinute1(s.getOpenMinute1());
		 t.setCloseHour1(s.getCloseHour1());
		 t.setCloseMinute1(s.getCloseMinute1());
		 t.setStatus(s.getStatus());
		 t.setScore(s.getScore());
		 t.setComments(s.getComments());

		 t.setTermType2(s.getTermType2());
		 t.setPrice2(s.getPrice2());
		 t.setBeginDate2(s.getBeginDate2());
		 t.setBeginMonth2(s.getBeginMonth2());
		 t.setEndDate2(s.getEndDate2());
		 t.setEndMonth2(s.getEndMonth2());
		 t.setOpenHour2(s.getOpenHour2());
		 t.setOpenMinute2(s.getOpenMinute2());
		 t.setCloseHour2(s.getCloseHour2());
		 t.setCloseMinute2(s.getCloseMinute2());
		 
		 t.setTermType3(s.getTermType3());
		 t.setPrice3(s.getPrice3());
		 t.setBeginDate3(s.getBeginDate3());
		 t.setBeginMonth3(s.getBeginMonth3());
		 t.setEndDate3(s.getEndDate3());
		 t.setEndMonth3(s.getEndMonth3());
		 t.setOpenHour3(s.getOpenHour3());
		 t.setOpenMinute3(s.getOpenMinute3());
		 t.setCloseHour3(s.getCloseHour3());
		 t.setCloseMinute3(s.getCloseMinute3());
		 
		 t.setTermType4(s.getTermType4());
		 t.setPrice4(s.getPrice4());
		 t.setBeginDate4(s.getBeginDate4());
		 t.setBeginMonth4(s.getBeginMonth4());
		 t.setEndDate4(s.getEndDate4());
		 t.setEndMonth4(s.getEndMonth4());
		 t.setOpenHour4(s.getOpenHour4());
		 t.setOpenMinute4(s.getOpenMinute4());
		 t.setCloseHour4(s.getCloseHour4());
		 t.setCloseMinute4(s.getCloseMinute4());
		 
		 t.setTermType5(s.getTermType5());
		 t.setPrice5(s.getPrice5());
		 t.setBeginDate5(s.getBeginDate5());
		 t.setBeginMonth5(s.getBeginMonth5());
		 t.setEndDate5(s.getEndDate5());
		 t.setEndMonth5(s.getEndMonth5());
		 t.setOpenHour5(s.getOpenHour5());
		 t.setOpenMinute5(s.getOpenMinute5());
		 t.setCloseHour5(s.getCloseHour5());
		 t.setCloseMinute5(s.getCloseMinute5());
		return t;
	}
	
	  
	
	
	
	

}
