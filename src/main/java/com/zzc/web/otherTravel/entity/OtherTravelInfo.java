package com.zzc.web.otherTravel.entity;

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
import org.hibernate.annotations.OrderBy;

import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelTarget;


/**
 * 
 *                  
 * @date: 2016年11月28日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: OtherTravelInfo.java
 * @Version: 1.0
 * @About: 其他涉旅单位的实体类
 *
 */
@Entity
@Table(name = "t_othercompany_info")
@Inheritance(strategy = InheritanceType.JOINED)
@ExcelTarget("otherTravelInfo")
public class OtherTravelInfo  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	@Excel(name="企业名称",orderNum="1",needMerge=true)
	private String name;//企业名称
	@Excel(name="负责人",orderNum="3",needMerge=true)
	private String principal;//负责人
	@Excel(name="联系电话",orderNum="4",needMerge=true)
	private String phone;//联系电话
	@Excel(name="企业类型",orderNum="2",replace={"高尔夫项目_1","游艇项目_2","空中飞行项目_3","机场项目_4,动车项目_5"})	
	private String type;//其他涉旅企业类型，1-高尔夫项目；2-游艇项目；3-空中飞行项目；4-机场项目；5-动车项目
	@Excel(name="所属区",orderNum="5",replace={"市辖区_0","吉阳区_1","崖州区_2","天涯区_3","海棠区_4"})
	private String area;//所在区，0-市辖区；1-崖州区；2-海棠区；3-天涯区；4-吉阳区
	private String address;//详细地址
	private String postalcode;//邮政编码
	private String privince;//所在省，默认海南省
	private String city;//所在市，默认三亚市
	private String status;//z保留字 审核状态
	private Date auditTime;//信息审核时间
	private List<GolfMonthly> golfMonthList;
	private List<AirportMonthly> airportMonthList;
	private List<FlightMonthly> flightMonthList;
	private List<MotorcarMonthly> motorcarMonthList;
	private List<BoatMonthly> BoatMonthList;
	private GolfMonthly golfMonth;
	private AirportMonthly airportMonth;
	private FlightMonthly flightMonth;
	private MotorcarMonthly motorcarMonth;
	private BoatMonthly boatMonth;
	private String mobile;// 手机号码 填表人电话
	@Id
	@Column(name ="ID",nullable=false,length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) { 
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="principal")
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="area")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="postalcode")
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	@Column(name="privince")
	public String getPrivince() {
		return privince;
	}
	public void setPrivince(String privince) {
		this.privince = privince;
	}
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="audit_time")
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	@OrderBy(clause = "dates desc")
	@JsonIgnore
	@BatchSize(size=3)
	@OneToMany(mappedBy="otherTravelInfo",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public List<GolfMonthly> getGolfMonthList() {
		return golfMonthList;
	}
	public void setGolfMonthList(List<GolfMonthly> golfMonthList) {
		this.golfMonthList = golfMonthList;
	}
	@OrderBy(clause = "dates desc")
	@JsonIgnore
	@BatchSize(size=3)
	@OneToMany(mappedBy="otherTravelInfo",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public List<AirportMonthly> getAirportMonthList() {
		return airportMonthList;
	}
	public void setAirportMonthList(List<AirportMonthly> airportMonthList) {
		this.airportMonthList = airportMonthList;
	}
	@OrderBy(clause = "dates desc")
	@JsonIgnore
	@BatchSize(size=3)
	@OneToMany(mappedBy="otherTravelInfo",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public List<FlightMonthly> getFlightMonthList() {
		return flightMonthList;
	}
	public void setFlightMonthList(List<FlightMonthly> flightMonthList) {
		this.flightMonthList = flightMonthList;
	}
	@OrderBy(clause = "dates desc")
	@JsonIgnore
	@BatchSize(size=3)
	@OneToMany(mappedBy="otherTravelInfo",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public List<MotorcarMonthly> getMotorcarMonthList() {
		return motorcarMonthList;
	}
	public void setMotorcarMonthList(List<MotorcarMonthly> motorcarMonthList) {
		this.motorcarMonthList = motorcarMonthList;
	}
	@OrderBy(clause = "dates desc")
	@JsonIgnore
	@BatchSize(size=3)
	@OneToMany(mappedBy="otherTravelInfo",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public List<BoatMonthly> getBoatMonthList() {
		return BoatMonthList;
	}
	public void setBoatMonthList(List<BoatMonthly> boatMonthList) {
		BoatMonthList = boatMonthList;
	}
	@Transient
	public GolfMonthly getGolfMonth() {
		return golfMonth;
	}
	public void setGolfMonth(GolfMonthly golfMonth) {
		this.golfMonth = golfMonth;
	}
	@Transient
	public AirportMonthly getAirportMonth() {
		return airportMonth;
	}
	public void setAirportMonth(AirportMonthly airportMonth) {
		this.airportMonth = airportMonth;
	}
	@Transient
	public FlightMonthly getFlightMonth() {
		return flightMonth;
	}
	public void setFlightMonth(FlightMonthly flightMonth) {
		this.flightMonth = flightMonth;
	}
	@Transient
	public MotorcarMonthly getMotorcarMonth() {
		return motorcarMonth;
	}
	public void setMotorcarMonth(MotorcarMonthly motorcarMonth) {
		this.motorcarMonth = motorcarMonth;
	}
	@Transient
	public BoatMonthly getBoatMonth() {
		return boatMonth;
	}
	public void setBoatMonth(BoatMonthly boatMonth) {
		this.boatMonth = boatMonth;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
}
