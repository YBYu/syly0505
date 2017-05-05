package com.zzc.web.travel.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelTarget;
 

/**
 * @Title: 
 * @Description: 旅游社基本信息实体类
 * @author 冯勇齐
 * @date 2016-12-10 10:31:20
 * 
 */

@Entity
@Table(name = "t_travelAgency_info")
@SuppressWarnings("serial")
@ExcelTarget("traveldata")
public class Traveldata implements java.io.Serializable{
	
	// 主键
	private String id;
	//许可证编号 ->用户名
	@Excel(name="旅行社编码",needMerge=true)
	private String licensenum; 
	// 用户id
	private String userId;
	// 旅行社名称 ->单位名称
	@Excel(name="旅行社名称",needMerge=true)
	private String name;
	// 英文名称
	private String englishname;
	// 组织机构代码
	private String organizationnum;
	// ???
	private String number;
	//法人代表
	private String corporate;
	//行政区域代码
	private String adminnum;
	//统一社会信用代码
	private String province;
	// ???
	private String city;
	// 所在地区
	private String area;
	//详细地址
	private String address;
	// 邮政编码
	private String zonecode;
	//固定电话
	private String phone;
	// 固定电话分机
	private String phoneextension;
	//传真
	private String fax;
	// 传真分机
	private String faxextension;
	//手机
	private String mobile;
	//邮箱
	private String email;
	//邮政编码
	private String zipcode;
	//单位负责人
	private String principal;
	//企业登记注册类型
	private String registerstyle;
	//是否经营出境游
	private String businessexit;
	//是否经营边境游
	private String businessborder;
	//旅行社协会会员
	private String ismember;
	//qq
	private String qq;
	//网站
	private String weburl;
	//旅行社等级
	private String sceneSpotlevel;
	//工商登记注册时间
	private String tableDate;
	//填表人
	private String filler;
	//填表人电话
	private String fillerTel;
	//批准文号
	private String accommodationStar;
	// 旅行社经营场所
	private String travelAgencyType;
	
	private String accountnum;
	private Date createTime;
	
	// 填报时间
	private String fillDate;
	
	// 季报审核状态
	private String quarterStatus;
	
	private List<TravelAnnualFinance>travelAnnualList;    //一对多关联财务年报
	private TravelAnnualFinance travelAnnual;             //财务年报关系属性
	private List<TravelQuarterIn>travelQuarterOneList;     //一对多关联入境季报
	private TravelQuarterIn travelQuarterOne;              //入境季报关系属性
	private List<TravelQuarterOut>travelQuarterTowList;    //一对多关联出境季报
	private TravelQuarterOut travelQuarterTow;             //出境季报关系属性
	private List<TravelQuarterInland>travelQuarterThreeList; //一对多关联国内季报
	private TravelQuarterInland travelQuarterThree;       //国内季报关系属性
	
	private List<TraveldataAnnual> traveldataAnnualList;
	private TraveldataAnnual traveldataAnnual;
	
    // 五大洲汇总
    private String asia;
    private String europe;
    private String africa;
    private String oceania;
    private String america;
    
    // 分值
    private Integer score;
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="licensenum",nullable=true,length=100)
	public String getLicensenum() {
		return licensenum;
	}
	public void setLicensenum(String licensenum) {
		this.licensenum = licensenum;
	}
	@Column(name ="name",nullable=true,length=200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name ="english_name",nullable=true,length=500)
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	@Column(name ="organization_num",nullable=true,length=100)
	public String getOrganizationnum() {
		return organizationnum;
	}
	public void setOrganizationnum(String organizationnum) {
		this.organizationnum = organizationnum;
	}
	@Column(name ="number",nullable=true,length=200)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Column(name ="corporate",nullable=true,length=50)
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	@Column(name ="admin_num",nullable=true,length=50)
	public String getAdminnum() {
		return adminnum;
	}
	public void setAdminnum(String adminnum) {
		this.adminnum = adminnum;
	}
	@Column(name ="province",nullable=true,length=10)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province; 
	}
	@Column(name ="city",nullable=true,length=10)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name ="area",nullable=true,length=10)
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name ="address",nullable=true,length=100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name ="zone_code",nullable=true,length=20)
	public String getZonecode() {
		return zonecode;
	}
	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}
	@Column(name ="phone",nullable=true,length=20)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name ="phone_extension",nullable=true,length=20)
	public String getPhoneextension() {
		return phoneextension;
	}
	public void setPhoneextension(String phoneextension) {
		this.phoneextension = phoneextension;
	}
	@Column(name ="fax",nullable=true,length=20)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(name ="fax_extension",nullable=true,length=20)
	public String getFaxextension() {
		return faxextension;
	}
	public void setFaxextension(String faxextension) {
		this.faxextension = faxextension;
	}
	@Column(name ="mobile",nullable=true,length=20)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name ="email",nullable=true,length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name ="zipcode",nullable=true,length=20)
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Column(name ="principal",nullable=true,length=20)
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	@Column(name ="registerstyle",nullable=true,length=10)
	public String getRegisterstyle() {
		return registerstyle;
	}
	public void setRegisterstyle(String registerstyle) {
		this.registerstyle = registerstyle;
	}
	@Column(name ="businessexit",nullable=true,length=4)
	public String getBusinessexit() {
		return businessexit;
	}
	public void setBusinessexit(String businessexit) {
		this.businessexit = businessexit;
	}
	@Column(name ="businessborder",nullable=true,length=4)
	public String getBusinessborder() {
		return businessborder;
	}
	public void setBusinessborder(String businessborder) {
		this.businessborder = businessborder;
	}
	@Column(name ="is_member",nullable=true,length=4)
	public String getIsmember() {
		return ismember;
	}
	public void setIsmember(String ismember) {   
		this.ismember = ismember;
	}
	@Column(name ="qq",nullable=true,length=20)
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
		
	@Column(name ="weburl",nullable=true,length=20)
	public String getWeburl() {
		return weburl;
	}
	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}
		
	@Column(name ="accountnum",nullable=true,length=60)

	public String getAccountnum() {
		return accountnum;
	}
	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}
	@OneToMany(mappedBy = "traveldata", cascade = CascadeType.REMOVE)
	public List<TravelAnnualFinance> getTravelAnnualList() {
		return travelAnnualList;
	}
	public void setTravelAnnualList(List<TravelAnnualFinance> travelAnnualList) {
		this.travelAnnualList = travelAnnualList;
	}
	public TravelAnnualFinance getTravelAnnual() {
		return travelAnnual;
	}
	public void setTravelAnnual(TravelAnnualFinance travelAnnual) {
		this.travelAnnual = travelAnnual;
	}
	@OneToMany(mappedBy = "traveldata", cascade = CascadeType.REMOVE)
	public List<TravelQuarterIn> getTravelQuarterOneList() {
		return travelQuarterOneList;
	}
	public void setTravelQuarterOneList(List<TravelQuarterIn> travelQuarterOneList) {
		this.travelQuarterOneList = travelQuarterOneList;
	}

	
	
	
	public TravelQuarterIn getTravelQuarterOne() {
		return travelQuarterOne;
	}
	public void setTravelQuarterOne(TravelQuarterIn travelQuarterOne) {
		this.travelQuarterOne = travelQuarterOne;
	}
	@OneToMany(mappedBy = "traveldata", cascade = CascadeType.REMOVE)
	public List<TravelQuarterOut> getTravelQuarterTowList() {
		return travelQuarterTowList;
	}
	public void setTravelQuarterTowList(List<TravelQuarterOut> travelQuarterTowList) {
		this.travelQuarterTowList = travelQuarterTowList;
	}
	public TravelQuarterOut getTravelQuarterTow() {
		return travelQuarterTow;
	}
	public void setTravelQuarterTow(TravelQuarterOut travelQuarterTow) {
		this.travelQuarterTow = travelQuarterTow;
	}
	@OneToMany(mappedBy = "traveldata", cascade = CascadeType.REMOVE)
	public List<TravelQuarterInland> getTravelQuarterThreeList() {
		return travelQuarterThreeList;
	}
	public void setTravelQuarterThreeList(
			List<TravelQuarterInland> travelQuarterThreeList) {
		this.travelQuarterThreeList = travelQuarterThreeList;
	}
	public TravelQuarterInland getTravelQuarterThree() {
		return travelQuarterThree;
	}
	public void setTravelQuarterThree(TravelQuarterInland travelQuarterThree) {
		this.travelQuarterThree = travelQuarterThree;
	}
	
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "scene_spotlevel")
	public String getSceneSpotlevel() {
		return sceneSpotlevel;
	}
	public void setSceneSpotlevel(String sceneSpotlevel) {
		this.sceneSpotlevel = sceneSpotlevel;
	}
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="table_date")
	public String getTableDate() {
		return tableDate;
	}
	public void setTableDate(String tableDate) {
		this.tableDate = tableDate;
	}
	@Column(name="filler")
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Column(name="filler_tel")
	public String getFillerTel() {
		return fillerTel;
	}
	public void setFillerTel(String fillerTel) {
		this.fillerTel = fillerTel;
	}
	@Column(name="accommodation_star")
	public String getAccommodationStar() {
		return accommodationStar;
	}
	public void setAccommodationStar(String accommodationStar) {
		this.accommodationStar = accommodationStar;
	}
	@Column(name="travelagency_type")
	public String getTravelAgencyType() {
		return travelAgencyType;
	}
	public void setTravelAgencyType(String travelAgencyType) {
		this.travelAgencyType = travelAgencyType;
	}
	@OneToMany(mappedBy = "traveldata", cascade = CascadeType.REMOVE)
	public List<TraveldataAnnual> getTraveldataAnnualList() {
		return traveldataAnnualList;
	}
	public void setTraveldataAnnualList(List<TraveldataAnnual> traveldataAnnualList) {
		this.traveldataAnnualList = traveldataAnnualList;
	}
	public TraveldataAnnual getTraveldataAnnual() {
		return traveldataAnnual;
	}
	public void setTraveldataAnnual(TraveldataAnnual traveldataAnnual) {
		this.traveldataAnnual = traveldataAnnual;
	}
	public String getAsia() {
		return asia;
	}
	public void setAsia(String asia) {
		this.asia = asia;
	}
	public String getEurope() {
		return europe;
	}
	public void setEurope(String europe) {
		this.europe = europe;
	}
	public String getAfrica() {
		return africa;
	}
	public void setAfrica(String africa) {
		this.africa = africa;
	}
	public String getOceania() {
		return oceania;
	}
	public void setOceania(String oceania) {
		this.oceania = oceania;
	}
	public String getAmerica() {
		return america;
	}
	public void setAmerica(String america) {
		this.america = america;
	}
	@Column
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name="quarter_status")
	public String getQuarterStatus() {
		return quarterStatus;
	}
	public void setQuarterStatus(String quarterStatus) {
		this.quarterStatus = quarterStatus;
	}
	@Column(name="fill_date",length=50)
	public String getFillDate() {
		return fillDate;
	}
	public void setFillDate(String fillDate) {
		this.fillDate = fillDate;
	}
}
