package com.zzc.web.scenicmanage.entity;

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

import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelEntity;


@Entity
@Table(name = "t_scenicSpot_annual")

public class ScenicSpotAnnual implements java.io.Serializable{
	
	/**
	 * @Title: 
	 * @Description: 景区年报实体类
	 * @author 冯勇齐
	 * @date 2016-11-28 15:31:20
	 * 
	 */
	private static final long serialVersionUID = -59410018896397433L;
	private String    id;
	private String    annualid;
	private String    year;
	private String   sid;
	@Excel(name="资产总额(万元)")
	private double    assetstotal; 
	@Excel(name="年底建设投资(万元)")//资产总额
	private double    buildinvest;  
	@Excel(name="景区内部建设投资(万元)")//年底建设投资
	private double    inbuild;
	@Excel(name="景区外部建设投资(万元)")
	private double    outbuild;
	@Excel(name="拨款(万元)")
	private double    appropriation;
	@Excel(name="贷款(万元)")
	private double    loan;
	@Excel(name="自筹(万元)")
	private double    fundsself;
	@Excel(name="景区经营总收入(万元)")
	private double    totalincome;
	@Excel(name="门票收入(万元)")
	private double    ticketincome;
	@Excel(name="商品收入(万元)")//门票收入
	private double    shopincome;
	@Excel(name="食品收入(万元)")//商品收入
	private double    foodincome;
	@Excel(name="交通收入(万元)")
	private double    trafficincome;
	@Excel(name="住宿收入(万元)")
	private double    putUpincome;
	@Excel(name="演艺收入(万元)")
	private double    showincome;
	@Excel(name="其他收入(万元)")
	private double    otherincome;
	@Excel(name="接待人次")
	private double    receptionnum;
	@Excel(name="其中免票人次")
	private double    exemptTicketnum;
	@Excel(name="就业人数")
	private Integer   jobnum;
	@Excel(name="其中固定用工")
	private Integer   fixedworker;
	@Excel(name="其中临时用工")
	private Integer   tempworker;
	@Excel(name="专职导游人数")
	private Integer   guidenum;
	@Excel(name="备注")
	private String    remarks;
	private String      hisreport;
	private String      status;
	private Date      creatTime;
	private Integer score;//诚信积分
	private String provinceSubmit;//是否提交给上级系统
	private String comments;//审核意见 
	@ExcelEntity(name="ScenicData.name")
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
	public void setAnnualid(String annualid) {
		this.annualid = annualid;
	}
	@Column(name ="year",nullable=true,length=10) 
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name ="sid",nullable=true,length=50)
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	@Column(name ="assets_total",nullable=true)
	public double getAssetstotal() {
		return assetstotal;
	}
	public void setAssetstotal(double assetstotal) {
		this.assetstotal = assetstotal;
	}
	@Column(name ="build_invest",nullable=true)
	public double getBuildinvest() {
		return buildinvest;
	}
	public void setBuildinvest(double buildinvest) {
		this.buildinvest = buildinvest;
	}
	@Column(name ="in_build",nullable=true)
	public double getInbuild() {
		return inbuild;
	}
	public void setInbuild(double inbuild) {
		this.inbuild = inbuild;
	}
	@Column(name ="out_build",nullable=true)
	public double getOutbuild() {
		return outbuild;
	}
	public void setOutbuild(double outbuild) {
		this.outbuild = outbuild;
	}
	@Column(name ="appropriation",nullable=true)
	public double getAppropriation() {
		return appropriation;
	}
	public void setAppropriation(double appropriation) {
		this.appropriation = appropriation;
	}
	@Column(name ="loan",nullable=true)
	public double getLoan() {
		return loan;
	}
	public void setLoan(double loan) {
		this.loan = loan;
	}
	@Column(name ="funds_self",nullable=true)
	public double getFundsself() {
		return fundsself;
	}
	public void setFundsself(double fundsself) {
		this.fundsself = fundsself;
	}
	@Column(name ="total_income",nullable=true)
	public double getTotalincome() {
		return totalincome;
	}
	public void setTotalincome(double totalincome) {
		this.totalincome = totalincome;
	}
	@Column(name ="ticket_income",nullable=true)
	public double getTicketincome() {
		return ticketincome;
	}
	public void setTicketincome(double ticketincome) {
		this.ticketincome = ticketincome;
	}
	@Column(name ="shop_income",nullable=true)
	public double getShopincome() {
		return shopincome;
	}
	public void setShopincome(double shopincome) {
		this.shopincome = shopincome;
	}
	@Column(name ="food_income",nullable=true)
	public double getFoodincome() {
		return foodincome;
	}
	public void setFoodincome(double foodincome) {
		this.foodincome = foodincome;
	}
	@Column(name ="traffic_income",nullable=true)
	public double getTrafficincome() {
		return trafficincome;
	}
	public void setTrafficincome(double trafficincome) {
		this.trafficincome = trafficincome;
	}
	@Column(name ="putUp_income",nullable=true)
	public double getPutUpincome() {
		return putUpincome;
	}
	public void setPutUpincome(double putUpincome) {
		this.putUpincome = putUpincome;
	}
	@Column(name ="show_income",nullable=true)
	public double getShowincome() {
		return showincome;
	}
	public void setShowincome(double showincome) {
		this.showincome = showincome;
	}
	@Column(name ="other_income",nullable=true)
	public double getOtherincome() {
		return otherincome;
	}
	public void setOtherincome(double otherincome) {
		this.otherincome = otherincome;
	}
	@Column(name ="reception_num",nullable=true)
	public double getReceptionnum() {
		return receptionnum;
	}
	public void setReceptionnum(double receptionnum) {
		this.receptionnum = receptionnum;
	}
	@Column(name ="exemptTicket_num",nullable=true)
	public double getExemptTicketnum() {
		return exemptTicketnum;
	}
	public void setExemptTicketnum(double exemptTicketnum) {
		this.exemptTicketnum = exemptTicketnum;
	}
	@Column(name ="job_num",nullable=true,length=20)
	public Integer getJobnum() {
		return jobnum;
	}
	public void setJobnum(Integer jobnum) {
		this.jobnum = jobnum;
	}
	@Column(name ="fixed_worker",nullable=true,length=20)
	public Integer getFixedworker() {
		return fixedworker;
	}
	public void setFixedworker(Integer fixedworker) {
		this.fixedworker = fixedworker;
	}
	@Column(name ="temp_worker",nullable=true,length=20)
	public Integer getTempworker() {
		return tempworker;
	}
	public void setTempworker(Integer tempworker) {
		this.tempworker = tempworker;
	}
	@Column(name ="guide_num",nullable=true,length=20)
	public Integer getGuidenum() {
		return guidenum;
	}
	public void setGuidenum(Integer guidenum) {
		this.guidenum = guidenum;
	}
	@Column(name ="remarks",nullable=true,length=200)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name ="his_report",nullable=true,length=4)
	public String getHisreport() {
		return hisreport;
	}
	public void setHisreport(String hisreport) {
		this.hisreport = hisreport;
	}
	@Column(name ="status",nullable=true,length=12)
	public String getStatus() {
		return status;
	}  
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="score")
	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name ="province_submit")
	public String getProvinceSubmit() {
		return provinceSubmit;
	}
	public void setProvinceSubmit(String provinceSubmit) {
		this.provinceSubmit = provinceSubmit;
	}
	@Column(name ="comments",length=2000)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="annual_id")
	public ScenicData getScenicData() {
		return scenicData;
	}
	public void setScenicData(ScenicData scenicData) {
		this.scenicData = scenicData;
	}
	@Column(name ="creat_time",nullable=true,updatable=false)
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
	
}
