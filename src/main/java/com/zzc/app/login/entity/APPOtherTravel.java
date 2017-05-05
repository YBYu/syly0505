package com.zzc.app.login.entity;

public class APPOtherTravel {
	private String id;
	// 企业名称
	private String name;
	// 负责人
	private String principal;
	// 联系电话
	private String phone;
	// 企业类型,高尔夫项目_1","游艇项目_2","空中飞行项目_3","机场项目_4,动车项目_5
	private String type;
	// 所属区,0-市辖区；1-崖州区；2-海棠区；3-天涯区；4-吉阳区
	private String area;
	// 详细地址
	private String address;
	// 邮政编码
	private String postalcode;
	// 所在省，默认海南省
	private String privince;
	// 所在市，默认三亚市
	private String city;

	private String tel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getPrivince() {
		return privince;
	}

	public void setPrivince(String privince) {
		this.privince = privince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
