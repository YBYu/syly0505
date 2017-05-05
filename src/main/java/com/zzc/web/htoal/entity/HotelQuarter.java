package com.zzc.web.htoal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zzc.core.common.entity.IdEntity;
import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelEntity;
import com.zzc.poi.excel.annotation.ExcelTarget;
/**
 * 
 *                  
 * @date: 2016年11月29日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelQuarter.java
 * @Version: 1.0
 * @About: 月报实体类
 *
 */
@Entity
@Table(name = "t_hotel_quarterly")
@Inheritance(strategy = InheritanceType.JOINED)
@ExcelTarget( value = "hotelQuarter")
public class HotelQuarter extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String hid;//对应酒店的id
	private String organizationNum;//组织机构代码
	@Excel( name="酒店名称")
	private String unitname;//酒店名称
	private String manager;//负责人
	@Excel( name="年份")
	private String year;
	@Excel(name="季度",replace={"第一季度_0,第二季度_1,第三季度_2,第四季度_3"})
	private Integer quarter;//0 1 2 3 第一二三四季度
	private String remarks;
	@Excel(name="审核状态",replace={"未审核_0,管理员审核通过_1,省审核通过_2,管理员审核不通过_3,省审核不通过_4"})
	private String status;//0未审核1管理员审核通过2.省审核通过3.管理员审核不通过4省审核不通过
	@Excel(name="填表人")
	private String writer;
	private String mobile;
	private String writerDate;
	@Excel(name="总收入(千元)")
	private double totalIncome; //OperationCost
	@Excel(name="餐饮收入(千元)")
	private double canteenIncome; // CateringIncome
	@Excel(name="房间收入(千元)")
	private double roomIncome; // RoomRevenue
	@Excel(name="其他收入(千元)")
	private double otherIncome;
	private Integer roomnum; //Room
	private Integer bednum; // Bed
	@Excel(name="实际出租夜间数")
	private Integer realNights;//实际出租夜间数
	@Excel(name="可供出租夜间数")
	private Integer canNights;//可供出租夜间数
	@ExcelEntity()
	private Hotelmanage hotelmanage;//对应的酒店基本信息
	private Date auditTime;//季报审核时间
	
	// 是否是撤回的数据；0-不是，1-是
	private String revocated;
	// 分数
	private Integer score;
	
	// 补全字段
	private String sheng;
	private String shi;
	private String xian;
	private String hotelQid;
	private String countryState;
	private String avgHotelPrice;//平均房价
	// 区级审核状态
	private String districtStatus;
	// 国家系统审核意见
	private String guo;
	
	
	@Column(name ="audit_time")
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	@Column(name ="hid")
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	@Column(name ="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name ="mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name ="manager")
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Column(name ="organizationNum")
	public String getOrganizationNum() {
		return organizationNum;
	}
	public void setOrganizationNum(String organizationNum) {
		this.organizationNum = organizationNum;
	}
	@Column(name ="unitname")
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	@Column(name ="quarter")
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	@Column(name ="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name ="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="totalIncome")
	public double getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}
	@Column(name ="canteenIncome")
	public double getCanteenIncome() {
		return canteenIncome;
	}
	public void setCanteenIncome(double canteenIncome) {
		this.canteenIncome = canteenIncome;
	}
	@Column(name ="roomIncome")
	public double getRoomIncome() {
		return roomIncome;
	}
	public void setRoomIncome(double roomIncome) {
		this.roomIncome = roomIncome;
	}
	@Column(name ="otherIncome")
	public double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(double otherIncome) {
		this.otherIncome = otherIncome;
	}
	@Column(name ="roomnum")
	public Integer getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(Integer roomnum) {
		this.roomnum = roomnum;
	}
	@Column(name ="bednum")
	public Integer getBednum() {
		return bednum;
	}
	public void setBednum(Integer bednum) {
		this.bednum = bednum;
	}
	@Column(name ="writer")
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Column(name ="writerDate")
	public String getWriterDate() {
		return writerDate;
	}
	public void setWriterDate(String writerDate) {
		this.writerDate = writerDate;
	}
	@Column(name ="realNights")
	public Integer getRealNights() {
		return realNights;
	}
	public void setRealNights(Integer realNights) {
		this.realNights = realNights;
	}
	@Column(name ="canNights")
	public Integer getCanNights() {
		return canNights;
	}
	public void setCanNights(Integer canNights) {
		this.canNights = canNights;
	}
	//多对一关系,一个酒店对应多个酒店月报,
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_qid")
	public Hotelmanage getHotelmanage() {
		return hotelmanage;
	}
	public void setHotelmanage(Hotelmanage hotelmanage) {
		this.hotelmanage = hotelmanage;
	}
	@Column(name="sheng",length=2000)
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	@Column(name="shi",length=2000)
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	@Column(name="xian",length=2000)
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	@Column(name="hotel_qid",insertable=false,updatable=false)
	public String getHotelQid() {
		return hotelQid;
	}
	public void setHotelQid(String hotelQid) {
		this.hotelQid = hotelQid;
	}
	@Column(name="country_state")
	public String getCountryState() {
		return countryState;
	}
	public void setCountryState(String countryState) {
		this.countryState = countryState;
	}
	@Column(name="district_status")
	public String getDistrictStatus() {
		return districtStatus;
	}
	public void setDistrictStatus(String districtStatus) {
		this.districtStatus = districtStatus;
	}
	@Column
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name="revocated",length=2)
	public String getRevocated() {
		return revocated;
	}
	public void setRevocated(String revocated) {
		this.revocated = revocated;
	}
	@Column(name="avg_hotelprice",length=50)
	public String getAvgHotelPrice() {
		return avgHotelPrice;
	}
	public void setAvgHotelPrice(String avgHotelPrice) {
		this.avgHotelPrice = avgHotelPrice;
	}
	@Column(name="guo",length=1000)
	public String getGuo() {
		return guo;
	}
	public void setGuo(String guo) {
		this.guo = guo;
	}
}
