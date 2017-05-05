package com.zzc.app.entity;

import java.io.Serializable;

/**
 * Created by CC on 2017/1/12. 企业信息实体类
 */
public class EnterpriseInfoEntity implements Serializable {
	private int imgHotel;// 图片
	private String name;// 企业名字
	private String level;// 企业级别
	private String address;// 企业地址
	private int imgCreditLevel; // 信用级别图片
	private String creditLevelDescription;// 信用度描述
	private int creditNum;// 信用值
	// 诚信积分
	private int score;

	private String telephone;

	public EnterpriseInfoEntity() {
	}

	public EnterpriseInfoEntity(int imgHotel, String name, String level,
			String address, int imgCreditLevel, String creditLevelDescription,
			int creditNum) {
		this.imgHotel = imgHotel;
		this.name = name;
		this.level = level;
		this.address = address;
		this.imgCreditLevel = imgCreditLevel;
		this.creditLevelDescription = creditLevelDescription;
		this.creditNum = creditNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getImgHotel() {
		return imgHotel;
	}

	public void setImgHotel(int imgHotel) {
		this.imgHotel = imgHotel;
	}

	public int getImgCreditLevel() {
		return imgCreditLevel;
	}

	public void setImgCreditLevel(int imgCreditLevel) {
		this.imgCreditLevel = imgCreditLevel;
	}

	public String getCreditLevelDescription() {
		return creditLevelDescription;
	}

	public void setCreditLevelDescription(String creditLevelDescription) {
		this.creditLevelDescription = creditLevelDescription;
	}

	public int getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(int creditNum) {
		this.creditNum = creditNum;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
