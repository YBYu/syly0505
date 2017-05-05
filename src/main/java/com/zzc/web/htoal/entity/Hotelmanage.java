package com.zzc.web.htoal.entity;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelTarget;

/**
 * 
 *                  
 * @date: 2016年11月22日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: Hotelmanage.java
 * @Version: 1.0
 * @About: 
 *酒店信息管理的实体类
 */
@Entity
@Table(name = "t_hotelmanage")
@Inheritance(strategy = InheritanceType.JOINED)
@ExcelTarget("hotelMonthly")

public class Hotelmanage  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	@Excel(name="酒店法人",orderNum="4")
	private String legalPerson ;//法人代表
	@Excel(name="酒店编码",orderNum="2")
	private String organizationNum;//组织机构代码  UserName
//	@Excel(name="酒店名称",orderNum="1")
	private String unitname;// 单位名称
	@Excel(name="所属市",orderNum="6")
	private String city;// 所属市
	@Excel(name="所属县",orderNum="8",replace={"市辖区_0","吉阳区_1","崖州区_2","天涯区_3","海棠区_4"})
	private String county;//所属县
	@Excel(name="酒店地址",orderNum="9")
	private String address;//详细地址
	@Excel(name="酒店负责人",orderNum="5")
	private String manager;// 负责人
	//@Excel(name="酒店人数",orderNum="3")
	private String zipcode;// 邮政编码
	@Excel(name="酒店电话",orderNum="10")
	private String telephone;// 电话号码 
	//@Excel(name="酒店人数",orderNum="3")
	private String mobile;// 手机号码 填表人电话
	@Excel(name="酒店星级",orderNum="3",replace={"其他_0","舒适_1","高档_2","豪华_3","一星_4","二星_5","三星_6","四星_7","五星_8"})
	private String housegrade;// 酒店星级0非星级1到5代表一到五星
	private Integer housenum;// 房间数
	private Integer bednum;// 床位数
	private String huserId;// 用户当中的酒店用户id
    //	private TSDepart TSDepart = new TSDepart();// 部门
	private String status;//审核状态 0未审核1管理员已审核2
	private Date registertime;//工商注册登记时间
	private String registerstyle;//企业注册登记类型
	private String writer;//填表人
	private Date w_time;//填表时间
	@Excel(name="传真",orderNum="11")
	private String fax;//传真
	private Date verifyTime;//审核时间
	private Date verifyTime1;// 省系统审核时间
	private Date passTime;// 通过时间
	private String weburl;//网址
	private String email;
	private List<HotelMonthly> hotelMonthly;//月报 一对多
	private List<HotelQuarter> hotelQuarterList;//季报 一对多
	private List<HotelAnnual> hotelAnnualList;
	private HotelAnnual hotelAnnual;
	private HotelMonthly hotelmonth;
	private HotelQuarter hotelquarter;
	
	// 记录撤回前国家系统的审核状态
	private String revocateCountry;
	// 记录撤回前省系统的审核状态
	private String revocateProvince;
	// 分数
	private Integer score;
	
	// 季度营业状态 (0,停业；1 开业)
	private String onBuinessSeason;
	// 是否需要同步季度营业状态
	private String onBuinessSeasonSync;
	
	// 年度营业状态 (0,停业；1 开业)
	private String onBuinessYear;
	// 是否需要同步季度营业状态
	private String onBuinessYearSync;
	
	// 国家系统审核意见
	private String guo;
	
	// 用户名
	private String username;
	
	// 缺失字段补全
	// 标牌编码
	private String code;
	// 密码
	private String password;
	// 邮编
	private String postcode;
	// 行政区划码
	private String regionalismCode;
	// 报出日期
	private String fillDate;
	// 填表人电话
	private String fillerTel;
	// 电话分机号码
	private String telNo;
	// 传真分机号码
	private String faxNo;
	// 旅行社类别
	private String registrationType;
	// 单位类别
	private String unitType;
	// 旅游区（点）等级
	private String sceneSpotLevel;
	// 省审核
	private String sheng;
	// 市审核
	private String shi;
	// 县审核
	private String xian;
	
	// 控股情况
	private String shareHolding;
	
	// 信息来源 (1,省级系统；2,国家系统；3，都有)
	private String sourceType;
	
	// 省系统审核状态
	private String provinceStatus;
	
	// 国家系统审核状态
	private String countryStatus;
	
	// 区级审核状态
	private String districtStatus;
	
	@Excel(name="所属湾区",orderNum="7",replace={"市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"})
	private String bay;//所属湾区 0 市区1亚龙湾2大东海3三亚湾 4海棠湾
	
	@Id
	@Column(name ="ID",nullable=false,length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) { 
		this.id = id;
	}
	//年报
	@Transient
	public HotelAnnual getHotelAnnual() {
		return hotelAnnual;
	}
	public void setHotelAnnual(HotelAnnual hotelAnnual) {
		this.hotelAnnual = hotelAnnual;
	}
	//月报,月报,月报
	@Transient
	public HotelMonthly getHotelmonth() {
		return hotelmonth;
	}

	public void setHotelmonth(HotelMonthly hotelmonth) {
		this.hotelmonth = hotelmonth;
	}
	
	@Transient
	public HotelQuarter getHotelquarter() {
		return hotelquarter;
	}
	public void setHotelquarter(HotelQuarter hotelquarter) {
		this.hotelquarter = hotelquarter;
	}
	@Column(name="weburl")
	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	@Column(name="writer")
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Column(name="w_time")
	public Date getW_time() {
		return w_time;
	}

	public void setW_time(Date w_time) {
		this.w_time = w_time;
	}
	@Column(name="verifyTime")
	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	@Column(name="passTime")
	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	@Column(name="legal_person")
	public String getLegalPerson() {
		return legalPerson;
	}
	
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	@Column(name="organizationNum")
	public String getOrganizationNum() {
		return organizationNum;
	}
	
	public void setOrganizationNum(String organizationNum) {
		this.organizationNum = organizationNum;
	}
	@Column(name="unitname")
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	@Column(name="w_city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="w_county")
	public String getCounty() {
		return county;
	}
	
	public void setCounty(String county) {
		this.county = county;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="f_man")
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Column(name="zipcode")
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Column(name="telephone")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "housegrade")
	public String getHousegrade() {
		return housegrade;
	}
	public void setHousegrade(String housegrade) {
		this.housegrade = housegrade;
	}
	@Column(name = "housenum")
	public Integer getHousenum() {
		return housenum;
	}
	public void setHousenum(Integer housenum) {
		this.housenum = housenum;
	}
	@Column(name = "bednum")
	public Integer getBednum() {
		return bednum;
	}
	public void setBednum(Integer bednum) {
		this.bednum = bednum;
	}
	@Column(name = "huserId")
	public String getHuserId() {
		return huserId;
	}
	public void setHuserId(String huserId) {
		this.huserId = huserId;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "registertime")
	public Date getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}
	@Column(name = "registerstyle")
	public String getRegisterstyle() {
		return registerstyle;
	}
	public void setRegisterstyle(String registerstyle) {
		this.registerstyle = registerstyle;
	}
	/*public static long getSerialversionuid() {
		return serialVersionUID;
	}*/
	@OrderBy(clause = "reportDate desc")
	@JsonIgnore
	@BatchSize(size=3)
	@OneToMany(mappedBy="hotelmanage")
	public List<HotelMonthly> getHotelMonthly() {
		return hotelMonthly;
	}

	public void setHotelMonthly(List<HotelMonthly> hotelMonthly) {
		this.hotelMonthly = hotelMonthly;
	}
	@OrderBy(clause = "year desc,quarter desc")
	@BatchSize(size = 3)
	@OneToMany(mappedBy="hotelmanage",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public List<HotelQuarter> getHotelQuarterList() {
		return hotelQuarterList;
	}
	public void setHotelQuarterList(List<HotelQuarter> hotelQuarterList) {
		this.hotelQuarterList = hotelQuarterList;
	}
	@Fetch(value = FetchMode.SELECT)
	@OrderBy(clause = "year desc")
	@BatchSize(size = 3)
	//@OneToMany(mappedBy = "scenicData", cascade = CascadeType.REMOVE)
	@OneToMany(mappedBy="hotelmanage",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public List<HotelAnnual> getHotelAnnualList() {
		return hotelAnnualList;
	}
	public void setHotelAnnualList(List<HotelAnnual> hotelAnnualList) {
		this.hotelAnnualList = hotelAnnualList;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "bay_type")
	public String getBay() {
		return bay;
	}
	public void setBay(String bay) {
		this.bay = bay;
	}
	@Column(name = "fax")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	// 补全的字段
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="postcode")
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Column(name="regionalism_code")
	public String getRegionalismCode() {
		return regionalismCode;
	}
	public void setRegionalismCode(String regionalismCode) {
		this.regionalismCode = regionalismCode;
	}
	@Column(name="fill_date")
	public String getFillDate() {
		return fillDate;
	}
	public void setFillDate(String fillDate) {
		this.fillDate = fillDate;
	}
	@Column(name="filler_tel")
	public String getFillerTel() {
		return fillerTel;
	}
	public void setFillerTel(String fillerTel) {
		this.fillerTel = fillerTel;
	}
	@Column(name="tel_no")
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	@Column(name="fax_no")
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	@Column(name="registration_type")
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	@Column(name="unit_type")
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	@Column(name="scenespot_level")
	public String getSceneSpotLevel() {
		return sceneSpotLevel;
	}
	public void setSceneSpotLevel(String sceneSpotLevel) {
		this.sceneSpotLevel = sceneSpotLevel;
	}
	@Column(name="sheng",length=1000)
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	@Column(name="shi",length=1000)
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	@Column(name="xian",length=1000)
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	@Column(name="share_holding")
	public String getShareHolding() {
		return shareHolding;
	}
	public void setShareHolding(String shareHolding) {
		this.shareHolding = shareHolding;
	}
	@Column(name="source_type")
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	@Column(name="provice_status")
	public String getProvinceStatus() {
		return provinceStatus;
	}
	public void setProvinceStatus(String provinceStatus) {
		this.provinceStatus = provinceStatus;
	}
	@Column(name="country_status")
	public String getCountryStatus() {
		return countryStatus;
	}
	public void setCountryStatus(String countryStatus) {
		this.countryStatus = countryStatus;
	}
	@Column(name="district_status")
	public String getDistrictStatus() {
		return districtStatus;
	}
	public void setDistrictStatus(String districtStatus) {
		this.districtStatus = districtStatus;
	}
	@Column
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name="user_name",nullable=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="onbuiness_season", length=2)
	public String getOnBuinessSeason() {
		return onBuinessSeason;
	}
	public void setOnBuinessSeason(String onBuinessSeason) {
		this.onBuinessSeason = onBuinessSeason;
	}
	@Column(name="onbuiness_year", length=2)
	public String getOnBuinessYear() {
		return onBuinessYear;
	}
	public void setOnBuinessYear(String onBuinessYear) {
		this.onBuinessYear = onBuinessYear;
	}
	@Column(name="onbuinessseason_sync", length=2)
	public String getOnBuinessSeasonSync() {
		return onBuinessSeasonSync;
	}
	public void setOnBuinessSeasonSync(String onBuinessSeasonSync) {
		this.onBuinessSeasonSync = onBuinessSeasonSync;
	}
	@Column(name="onbuinessyear_sync", length=2)
	public String getOnBuinessYearSync() {
		return onBuinessYearSync;
	}
	public void setOnBuinessYearSync(String onBuinessYearSync) {
		this.onBuinessYearSync = onBuinessYearSync;
	}
	@Column
	public Date getVerifyTime1() {
		return verifyTime1;
	}
	public void setVerifyTime1(Date verifyTime1) {
		this.verifyTime1 = verifyTime1;
	}
	@Column(name="revocate_country",length=2)
	public String getRevocateCountry() {
		return revocateCountry;
	}
	public void setRevocateCountry(String revocateCountry) {
		this.revocateCountry = revocateCountry;
	}
	@Column(name="revocate_province",length=2)
	public String getRevocateProvince() {
		return revocateProvince;
	}
	public void setRevocateProvince(String revocateProvince) {
		this.revocateProvince = revocateProvince;
	}
	@Column(name="guo",length=1000)
	public String getGuo() {
		return guo;
	}
	public void setGuo(String guo) {
		this.guo = guo;
	}
	
}
