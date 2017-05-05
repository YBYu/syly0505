package com.zzc.app.scenic.entity;

import java.io.Serializable;

/**
 * Created by CC on 2017/1/21.
 */
public class EnterpriseBaseInfoEntity implements Serializable {

	/**
	 * 企业名称
	 */
	private String enterpriseName;

	/**
	 * 企业编码
	 */
	private String enterpriseCode;

	/**
	 * 企业所属辖区
	 */
	private String enterpriseArea;

	/**
	 * 企业地址
	 */
	private String enterpriseAddress;

	/**
	 * 企业类型
	 */
	private String enterpriseType;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 角色名
	 */
	private String role;

	/**
	 * 用户id
	 */
	private String id;

	private String province;// 所在省份
	private String city;// 所在市
	private String zipcode;// 邮编
	private String orgproperty;// 机构性质
	private String url;// 网址
	private String consultphone;// 咨询电话
	private String scenictype; // 景区类型，1-自然景观，2-历史文化，3-度假休闲，4-主题游乐，5-博物馆，6-乡村旅游，7-工业旅游，8-红色旅游，9-科技教育，10-其他
	private String opentime;// 开业时间
	private String daylimit;// 日接待最大容量
	private String acreage;// 面积
	private String investunit;// 投资主题
	private String superiorunit;// 上级主管单位
	private String level; // 景区级别
	private String leveldate;// 等级公告时间
	private String chargename;// 负责人姓名
	private String chargephone;// 负责人电话
	private String chargeemail;// 负责人邮箱
	private String informantname;// 填报人姓名
	private String informantphone;// 填报人电话
	private String informantemail;// 填报人邮箱
	private String informantqq;// 填报人QQ
	private String scenictitle;// 景区所获称号
	private String status; // 审核状态
	private String bayType;// 所属湾区
	private String remarks;// 备注

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseArea() {
		return enterpriseArea;
	}

	public void setEnterpriseArea(String enterpriseArea) {
		this.enterpriseArea = enterpriseArea;
	}

	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}

	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getOrgproperty() {
		return orgproperty;
	}

	public void setOrgproperty(String orgproperty) {
		this.orgproperty = orgproperty;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getConsultphone() {
		return consultphone;
	}

	public void setConsultphone(String consultphone) {
		this.consultphone = consultphone;
	}

	public String getScenictype() {
		return scenictype;
	}

	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getDaylimit() {
		return daylimit;
	}

	public void setDaylimit(String daylimit) {
		this.daylimit = daylimit;
	}

	public String getAcreage() {
		return acreage;
	}

	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}

	public String getInvestunit() {
		return investunit;
	}

	public void setInvestunit(String investunit) {
		this.investunit = investunit;
	}

	public String getSuperiorunit() {
		return superiorunit;
	}

	public void setSuperiorunit(String superiorunit) {
		this.superiorunit = superiorunit;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLeveldate() {
		return leveldate;
	}

	public void setLeveldate(String leveldate) {
		this.leveldate = leveldate;
	}

	public String getChargename() {
		return chargename;
	}

	public void setChargename(String chargename) {
		this.chargename = chargename;
	}

	public String getChargephone() {
		return chargephone;
	}

	public void setChargephone(String chargephone) {
		this.chargephone = chargephone;
	}

	public String getChargeemail() {
		return chargeemail;
	}

	public void setChargeemail(String chargeemail) {
		this.chargeemail = chargeemail;
	}

	public String getInformantname() {
		return informantname;
	}

	public void setInformantname(String informantname) {
		this.informantname = informantname;
	}

	public String getInformantphone() {
		return informantphone;
	}

	public void setInformantphone(String informantphone) {
		this.informantphone = informantphone;
	}

	public String getInformantemail() {
		return informantemail;
	}

	public void setInformantemail(String informantemail) {
		this.informantemail = informantemail;
	}

	public String getInformantqq() {
		return informantqq;
	}

	public void setInformantqq(String informantqq) {
		this.informantqq = informantqq;
	}

	public String getScenictitle() {
		return scenictitle;
	}

	public void setScenictitle(String scenictitle) {
		this.scenictitle = scenictitle;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBayType() {
		return bayType;
	}

	public void setBayType(String bayType) {
		this.bayType = bayType;
	}

}
