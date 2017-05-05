package com.zzc.web.scenicmanage.entity;



/**
 * 
 *                  
 * @date: 2016年11月28日
 * @Author: 冯勇齐
 * @Email: 1006545339@qq.com
 * @FileName: 
 * @Version: 1.0
 * @About: 
 *景区基本信息年报实体类
 */
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "t_scenicSpot_infoAnnual")
@SuppressWarnings("serial")
public class ScenicAnnual  implements java.io.Serializable{

	private String  id;
	private Integer year;
	private String  code;
	private String  name; 
	private String  province;
	private String  city;
	private String  area;     //区
	private String  address;
	private String  zipcode;
	private Integer  orgproperty; 
	private String  url;
	private String  consultphone;  
	private String  scenictype;  //景区类型
	private Date    opentime;
	private Double  daylimit;
	private Double  acreage; 
	private String  investunit;
	private String  superiorunit;  
	private String  level;        //景区级别
	private Date    leveldate;
	private String  chargename;
	private String  chargephone; 
	private String  chargeemail;
	private String  informantname; 
	private String  informantphone;
	private String  informantemail;
	private String  informantqq;
	private byte    tickettype; 
	private byte    ticketisOne;
	private String  ticketvalidDate;
	private String  scenictitle;
	private String  remarks;
	private byte    hisreport;
	private byte     status;
	private ScenicData scenicData;
	
	
	
	
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	@Column(name ="sid",nullable=true,length=50)
//	public Integer getSid() {
//		return sid;
//	}
//	public void setSid(Integer sid) {
//		this.sid = sid;
//	}
	
	@Column(name ="year",nullable=true,length=10)
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Column(name ="code",nullable=true,length=50)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name ="name",nullable=true,length=200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name ="province",nullable=true,length=20)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name ="city",nullable=true,length=20)
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
	@Column(name ="address",nullable=true,length=200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name ="zip_code",nullable=true,length=20)
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Column(name ="org_property",nullable=true,length=10)
	public Integer getOrgproperty() {
		return orgproperty;
	}
	public void setOrgproperty(Integer orgproperty) {
		this.orgproperty = orgproperty;
	}
	@Column(name ="consult_phone",nullable=true,length=20)
	public String getConsultphone() {
		return consultphone;
	}
	public void setConsultphone(String consultphone) {
		this.consultphone = consultphone;
	}
	@Column(name ="url",nullable=true,length=200)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name ="scenic_type",nullable=true,length=12)
	public String getScenictype() {
		return scenictype;
	}
	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
	}
	@Column(name ="open_time",nullable=true)
	public Date getOpentime() {
		return opentime;
	}
	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}
	@Column(name ="day_limit",nullable=true)
	public Double getDaylimit() {
		return daylimit;
	}
	public void setDaylimit(Double daylimit) {
		this.daylimit = daylimit;
	}
	@Column(name ="invest_unit",nullable=true,length=200)
	public String getInvestunit() {
		return investunit;
	}
	public void setInvestunit(String investunit) {
		this.investunit = investunit;
	}
	@Column(name ="superior_unit",nullable=true,length=200)
	public String getSuperiorunit() {
		return superiorunit;
	}
	public void setSuperiorunit(String superiorunit) {
		this.superiorunit = superiorunit;
	}
	@Column(name ="acreage",nullable=true)
	public Double getAcreage() {
		return acreage;
	}
	public void setAcreage(Double acreage) {
		this.acreage = acreage;
	}
	@Column(name ="level",nullable=true,length=12)
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Column(name ="level_date",nullable=true)
	public Date getLeveldate() {
		return leveldate;
	}
	public void setLeveldate(Date leveldate) {
		this.leveldate = leveldate;
	}
	@Column(name ="charge_name",nullable=true,length=20)
	public String getChargename() {
		return chargename;
	}
	public void setChargename(String chargename) {
		this.chargename = chargename;
	}
	@Column(name ="charge_phone",nullable=true,length=20)
	public String getChargephone() {
		return chargephone;
	}
	public void setChargephone(String chargephone) {
		this.chargephone = chargephone;
	}
	@Column(name ="charge_email",nullable=true,length=50)
	public String getChargeemail() {
		return chargeemail;
	}
	public void setChargeemail(String chargeemail) {
		this.chargeemail = chargeemail;
	}
	@Column(name ="informant_name",nullable=true,length=20)
	public String getInformantname() {
		return informantname;
	}
	public void setInformantname(String informantname) {
		this.informantname = informantname;
	}
	@Column(name ="informant_phone",nullable=true,length=20)
	public String getInformantphone() {
		return informantphone;
	}
	public void setInformantphone(String informantphone) {
		this.informantphone = informantphone;
	}
	@Column(name ="informant_email",nullable=true,length=50)
	public String getInformantemail() {
		return informantemail;
	}
	public void setInformantemail(String informantemail) {
		this.informantemail = informantemail;
	}
	@Column(name ="informant_qq",nullable=true,length=20)
	public String getInformantqq() {
		return informantqq;
	}
	public void setInformantqq(String informantqq) {
		this.informantqq = informantqq;
	}
	@Column(name ="ticket_type",nullable=true,length=4)
	public byte getTickettype() {
		return tickettype;
	}
	public void setTickettype(byte tickettype) {
		this.tickettype = tickettype;
	}
	@Column(name ="ticket_isOne",nullable=true,length=4)
	public byte getTicketisOne() {
		return ticketisOne;
	}
	public void setTicketisOne(byte ticketisOne) {
		this.ticketisOne = ticketisOne;
	}
	@Column(name ="ticket_validDate",nullable=true,length=50)
	public String getTicketvalidDate() {
		return ticketvalidDate;
	}
	public void setTicketvalidDate(String ticketvalidDate) {
		this.ticketvalidDate = ticketvalidDate;
	}
	@Column(name ="scenic_title",nullable=true,length=100)
	public String getScenictitle() {
		return scenictitle;
	}
	public void setScenictitle(String scenictitle) {
		this.scenictitle = scenictitle;
	}
	@Column(name ="remarks",nullable=true,length=200)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name ="his_report",nullable=true,length=4)
	public byte getHisreport() {
		return hisreport;
	}
	public void setHisreport(byte hisreport) {
		this.hisreport = hisreport;
	}
	@Column(name ="status",nullable=true,length=4)
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="annual_id")
	public ScenicData getScenicData() {
		return scenicData;
	}
	public void setScenicData(ScenicData scenicData) {
		this.scenicData = scenicData;
	}
	
	
	
}
