package com.zzc.web.scenicmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;






@Entity
@Table(name = "t_scenicmanage")
@SuppressWarnings("serial")
public class Scenic implements java.io.Serializable {
	
	
	private java.lang.String id;
	private java.lang.String scenicname;
	private java.lang.String  code;
	private java.lang.String  scenicgrade; 
	private java.lang.String  location;
	private java.lang.String scenictype;
	
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="scenic_id",nullable=false,length=120)
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	@Column(name ="scenicname",nullable=true,length=60)
	public java.lang.String getScenicname() {
		return scenicname;
	}
	public void setScenicname(java.lang.String scenicname) {
		this.scenicname = scenicname;
	}
	@Column(name ="code",nullable=true,length=60)
	public java.lang.String getCode() {
		return code;
	}
	
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	@Column(name ="scenicgrade",nullable=true,length=60)
	public java.lang.String getScenicgrade() {
		return scenicgrade;
	}
	public void setScenicgrade(java.lang.String scenicgrade) {
		this.scenicgrade = scenicgrade;
	}
	@Column(name ="location",nullable=true,length=60)
	public java.lang.String getLocation() {
		return location;
	}
	public void setLocation(java.lang.String location) {
		this.location = location;
	}
	@Column(name ="scenictype",nullable=true,length=60)
	public java.lang.String getScenictype() {
		return scenictype;
	}
	public void setScenictype(java.lang.String scenictype) {
		this.scenictype = scenictype;
	}
	
	
}
