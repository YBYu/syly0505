package com.zzc.web.touristoffices.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 三亚旅游局资料管理实体类
 * @author 冯勇齐
 * @date 2016-12-26 18:35:36
 */

@Entity
@Table(name = "t_tourist_offices")
@SuppressWarnings("serial")
public class TouristOffices implements java.io.Serializable {
	private String id;
	private String account;
	private String password;
	private String province;
	private String city;
	private String area;
	private String name;
	private String head;
	private String tel;
	private String qq;
	private String postcode;
	private String fax;
	private String add;
	private String phone;
	private String http;
	private String email;
	private Date createTime;
	private Date updateTime;
	private String state;
	private String status;
	
	// 是否需要调用新增接口
	private String needAdd;
	
	// 是否需要调用编辑接口
	private String needEdit;
	
	// 缺失字段补全
	
//	ID	int	
	private String originID;
//	ENCoName	varchar	单位英文名
	private String ENCoName;
//	Corporate	varchar	法人代表
	private String cororate;
//	Summary	varchar	简介（去除HTML）
	private String summary;
//	Brief	text	简介
	private String brief;
//	RegDate	datetime	成立时间
	private String regDate;
//	RegSum	varchar	注册资金
	private String regSum;
//	RegCoSum	varchar	资产总额
	private String regCoSum;
//	CoType	varchar	公司性质
	private String coType;
//	CoCode	varchar	组织机构代码
	private String coCode;
//	Costs	varchar	会费缴纳
	private String costs;
//	Flag	varchar	会员类别
	private String flag;
//	HighDirector	varchar	上级主管单位
	private String highDirector;
//	HighAddress	varchar	上级单位地址
	private String highAddress;
//	HighTel	varchar	上级电话
	private String highTel;
//	HighPost	varchar	上级单位邮编
	private String highPost;
//	WorkerNum	int	职工总人数
	private String workerNum;
//	FWorkerNum	int	专职人数
	private String fWorkerNum;
//	PWorkerNum	int	兼职人数
	private String pWorkerNum;
//	EndReg	varchar	会费缴纳时间
	private String endReg;

	
	
	@Id
	@Column(name ="ID",nullable=false,length=50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="account",nullable=true,length=20)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Column(name ="password",nullable=true,length=50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	@Column(name ="name",nullable=true,length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name ="head",nullable=true,length=10)
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	@Column(name ="tel",nullable=true,length=20)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name ="qq",nullable=true,length=20)
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	@Column(name ="postcode",nullable=true,length=10)
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Column(name ="fax",nullable=true,length=20)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(name ="address",nullable=true,length=100)
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	@Column(name ="phone",nullable=true,length=20)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name ="http",nullable=true,length=100)
	public String getHttp() {
		return http;
	}
	public void setHttp(String http) {
		this.http = http;
	}
	@Column(name ="email",nullable=true,length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name ="create_time",nullable=true)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name ="update_time",nullable=true)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name ="state",nullable=true,length=4)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name ="status",nullable=true,length=10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="origin_id",nullable=true,length=10)
	public String getOriginID() {
		return originID;
	}
	public void setOriginID(String originID) {
		this.originID = originID;
	}
	@Column(name="need_add",length=10)
	public String getNeedAdd() {
		return needAdd;
	}
	public void setNeedAdd(String needAdd) {
		this.needAdd = needAdd;
	}
	@Column(name="need_edit",length=10)
	public String getNeedEdit() {
		return needEdit;
	}
	public void setNeedEdit(String needEdit) {
		this.needEdit = needEdit;
	}

  
	
}
