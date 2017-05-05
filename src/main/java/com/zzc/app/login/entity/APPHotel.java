package com.zzc.app.login.entity;

public class APPHotel {

	private String id;
	// 标牌编码
	private String code;
	// 酒店法人
	private String legalPerson;
	// 组织机构代码
	private String organizationNum;
	// 单位名称
	private String unitname;
	// 所属市，默认三亚市
	private String city;
	// 所在地区，"市辖区_0","吉阳区_1","崖州区_2","天涯区_3","海棠区_4"
	private String county;
	// 所属湾区 0 市区1亚龙湾2大东海3三亚湾 4海棠湾
	private String bay;
	// 地址
	private String address;
	// 负责人
	private String manager;
	// 邮政编码
	private String zipcode;
	// 电话号码
	private String telephone;
	// 手机号码
	private String mobile;
	// 酒店星级，"其他_0","舒适_1","高档_2","豪华_3","一星_4","二星_5","三星_6","四星_7","五星_8"
	private String housegrade;
	// 房间数
	private String housenum;
	// 床位数
	private String bednum;
	// 工商注册登记时间
	private String registertime;
	// 企业注册登记类型
	private String registerstyle;
	// 填表人
	private String writer;
	// 传真
	private String fax;
	// 网址
	private String weburl;
	// 邮件
	private String email;
	// 市级审核状态
	private String status;
	// 区级审核状态
	private String districtStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getOrganizationNum() {
		return organizationNum;
	}

	public void setOrganizationNum(String organizationNum) {
		this.organizationNum = organizationNum;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getBay() {
		return bay;
	}

	public void setBay(String bay) {
		this.bay = bay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHousegrade() {
		return housegrade;
	}

	public void setHousegrade(String housegrade) {
		this.housegrade = housegrade;
	}

	public String getHousenum() {
		return housenum;
	}

	public void setHousenum(String housenum) {
		this.housenum = housenum;
	}

	public String getBednum() {
		return bednum;
	}

	public void setBednum(String bednum) {
		this.bednum = bednum;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public String getRegisterstyle() {
		return registerstyle;
	}

	public void setRegisterstyle(String registerstyle) {
		this.registerstyle = registerstyle;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDistrictStatus() {
		return districtStatus;
	}

	public void setDistrictStatus(String districtStatus) {
		this.districtStatus = districtStatus;
	}

}
