package com.zzc.web.scenicmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_scenicspot_properties")
public class ScenicSpotProperties {
	private int id;
	private int praentId;
	private String propertiesName;
	@Id
	@Column(name ="id",nullable=false,length=120)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name ="parent_id",nullable=false,length=120)
	public int getPraentId() {
		return praentId;
	}
	public void setPraentId(int praentId) {
		this.praentId = praentId;
	}
	@Column(name ="name",nullable=false,length=120)
	public String getPropertiesName() {
		return propertiesName;
	}
	public void setPropertiesName(String propertiesName) {
		this.propertiesName = propertiesName;
	}
	
	
}
