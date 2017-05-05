package com.zzc.app.scenic.entity;

import java.io.Serializable;

/**
 * Created by CC on 2017/1/16. 用户类，用来保存登陆用户的信息
 */
public class User implements Serializable {

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

	public User() {
	}

	public User(String enterpriseName, String enterpriseCode,
			String enterpriseArea, String enterpriseAddress,
			String enterpriseType, String userName, String realName,
			String role, String id) {
		this.enterpriseName = enterpriseName;
		this.enterpriseCode = enterpriseCode;
		this.enterpriseArea = enterpriseArea;
		this.enterpriseAddress = enterpriseAddress;
		this.enterpriseType = enterpriseType;
		this.userName = userName;
		this.realName = realName;
		this.role = role;
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "User{" + "enterpriseName='" + enterpriseName + '\''
				+ ", enterpriseCode='" + enterpriseCode + '\''
				+ ", enterpriseArea='" + enterpriseArea + '\''
				+ ", enterpriseAddress='" + enterpriseAddress + '\''
				+ ", enterpriseType='" + enterpriseType + '\'' + ", userName='"
				+ userName + '\'' + ", realName='" + realName + '\''
				+ ", role='" + role + '\'' + ", id='" + id + '\'' + '}';
	}
}
