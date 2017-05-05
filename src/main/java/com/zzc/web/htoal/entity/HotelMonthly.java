package com.zzc.web.htoal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zzc.core.common.entity.IdEntity;
//import org.hibernate.annotations.CascadeType;

/**
 * 
 *                  
 * @date: 2016年11月29日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelMonthly.java
 * @Version: 1.0
 * @About: 
 *酒店住宿月报
 */
@Entity
@Table(name = "t_hotelmonthly")
@SuppressWarnings("serial")
public class HotelMonthly extends IdEntity implements java.io.Serializable {
	// 年份
	private String year;
	// 月份
	private String month;
	// 酒店id
	private String hotelId;
	// 审核状态
	private String status;
	// 月报日期
	private String reportDate;
	// 填报人
	private String filler;
	// 填报人电话
	private String fillerTel;
	// 实际出租房间
	private String actualRentNum;
	// 可出租房间数量
	private String rentNum;
	// 出租率
	private String rentPercent;
	// 客房收入
	private String roomIncome;
	// 餐饮收入
	private String cateringIncome;
	// 其他收入
	private String otherIncome;
	// 总收入
	private String totalIncome;
	// 平均房价
	private String averageRoomPrice;
	// 省系统审核状态
	private String provinceState;
	// 区级审核状态
	private String districtStatus;
	
	// 是否是撤回的数据；0-不是，1-是
	private String revocated;
	// 分数
	private Integer score;
	
	// 总人数
	private String totalMonthTime;
	private String totalMonthYearTime;
	private String totalMonthDay;
	private String totalMonthYearDay;
	
	// 国内过夜游客
	private String inlandMonthTime;
	private String inlandMonthYearTime;
	private String inlandMonthDay;
	private String inlandMonthYearDay;
	
	// 入境过夜游客
	private String entryMonthTime;
	private String entryMonthYearTime;
	private String entryMonthDay;
	private String entryMonthYearDay;
	
	// 香港
	private String hongkongMonthTime;
	private String hongkongMonthYearTime;
	private String hongkongMonthDay;
	private String hongkongMonthYearDay;
	
	// 澳门
	private String macauMonthTime;
	private String macauMonthYearTime;
	private String macauMonthDay;
	private String macauMonthYearDay;

	// 台湾
	private String taiwanMonthTime;
	private String taiwanMonthYearTime;
	private String taiwanMonthDay;
	private String taiwanMonthYearDay;
	
	// 外国人
	private String foreignMonthTime;
	private String foreignMonthYearTime;
	private String foreignMonthDay;
	private String foreignMonthYearDay;
	
	private String asianMonth;
	private String asianMonthYear;
	
	private String japanMonth;
	private String japanMonthYear;
	
	private String koreaMonth;
	private String koreaMonthYear;
	
	private String mongoliaMonth;
	private String mongoliaMonthYear;
	
	private String indonesiaMonth;
	private String indonesiaMonthYear;
	
	private String malaysiaMonth;
	private String malaysiaMonthYear;
	
	private String philippinesMonth;
	private String philippinesMonthYear;
	
	private String singaporeMonth;
	private String singaporeMonthYear;
	
	private String thailandMonth;
	private String thailandMonthYear;
	
	private String indiaMonth;
	private String indiaMonthYear;
	
	//越南
	private String vietnamMonth;
	private String vietnamMonthYear;
	
	// 缅甸
	private String burmaMonth;
	private String burmaMonthYear;
	
	// 朝鲜
	private String northkoreaMonth;
	private String northkoreaMonthYear;
	
	// 巴基斯坦
	private String pakistanMonth;
	private String pakistanMonthYear;
	
	// 亚洲其他
	private String aisaOtherMonth;
	private String aisaOtherMonthYear;
	
	// TODO
	// 老挝
	private String laosMonth;
	private String laosMonthYear;
	
	// 柬埔寨
	private String cambodiaMonth;
	private String cambodiaMonthYear;
	
	// 尼泊尔
	private String nepalMonth;
	private String nepalMonthYear;
	
	// 斯里兰卡
	private String srilankaMonth;
	private String srilankaMonthYear;
	
	// 哈萨克斯坦
	private String kzMonth;
	private String kzMonthYear;

	// 吉尔吉斯斯坦
	private String kyrghyzstanMonth;
	private String kyrghyzstanMonthYear;
	
	// 乌兹别克斯坦
	private String uzMonth;
	private String uzMonthYear;
	
	// 塔吉克斯坦
	private String tajikistanMonth;
	private String tajikistanMonthYear;
	
	private String asianOtherMonth;
	private String asianOtherMonthYear;
	
	// 欧洲
	private String europeMonth;
	private String europeMonthYear;
	
	private String englandMonth;
	private String englandMonthYear;
	
	private String franceMonth;
	private String franceMonthYear;
	
	private String germanyMonth;
	private String germanyMonthYear;
	
	private String italyMonth;
	private String italyMonthYear;
	
	// 瑞士
	private String switzerlandMonth;
	private String switzerlandMonthYear;
	
	// 瑞典
	private String swedenMonth;
	private String swedenMonthYear;
	
	private String russiaMonth;
	private String russiaMonthYear;
	
	// 西班牙
	private String spainMonth;
	private String spainMonthYear;
	
	// TODO
	// 爱尔兰
	private String irelandMonth;
	private String irelandMonthYear;
	
	// 乌克兰
	private String ukraineMonth;
	private String ukraineMonthYear;
	
	// 比利时
	private String belgiumMonth;
	private String belgiumMonthYear;
	
	// 捷克
	private String czMonth;
	private String czMonthYear;
	
	// 奥地利
	private String austriaMonth;
	private String austriaMonthYear;
	
	// 葡萄牙
	private String portugalMonth;
	private String portugalMonthYear;
	
	// 荷兰
	private String hollandMonth;
	private String hollandMonthYear;
	
	private String europeOtherMonth;
	private String europeOtherMonthYear;
	
	// 美洲小计
	private String americaMonth;
	private String americaMonthYear;
	
	// 美国
	private String usaMonth;
	private String usaMonthYear;
	
	private String canadaMonth;
	private String canadaMonthYear;
	
	// TODO
	// 墨西哥
	private String mexicoMonth;
	private String mexicoMonthYear;
	
	// 巴西
	private String brazilMonth;
	private String brazilMonthYear;
	
	// 阿根廷
	private String argentinaMonth;
	private String argentinaMonthYear;
	
	// 智利
	private String chileMonth;
	private String chileMonthYear;
	
	// 秘鲁
	private String peruMonth;
	private String peruMonthYear;
	
	private String americaOtherMonth;
	private String americaOtherMonthYear;
	
	// 大洋洲
	private String oceaniaMonth;
	private String oceaniaMonthYear;
	
	private String australiaMonth;
	private String australiaMonthYear;
	
	// 新西兰
	private String nzMonth;
	private String nzMonthYear;
	
	private String oceaniaOtherMonth;
	private String oceaniaOtherMonthYear;
	
	private String africaMonth;
	private String africaMonthYear;
	
	private String otherMonth;
	private String otherMonthYear;
	
	Hotelmanage hotelmanage;
	
	// 多对一关系,一个酒店对应多个酒店月报,
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel_id",insertable=false,updatable=false)
	public Hotelmanage getHotelmanage() {
		return hotelmanage;
	}
	public void setHotelmanage(Hotelmanage hotelmanage) {
		this.hotelmanage = hotelmanage;
	}
	
	@Column(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="month")
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Column(name="hotel_id")
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="report_date")
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	@Column(name="filler")
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Column(name="filler_tel")
	public String getFillerTel() {
		return fillerTel;
	}
	public void setFillerTel(String fillerTel) {
		this.fillerTel = fillerTel;
	}
	@Column(name="actual_rentnum")
	public String getActualRentNum() {
		return actualRentNum;
	}
	public void setActualRentNum(String actualRentNum) {
		this.actualRentNum = actualRentNum;
	}
	@Column(name="rent_num")
	public String getRentNum() {
		return rentNum;
	}
	public void setRentNum(String rentNum) {
		this.rentNum = rentNum;
	}
	@Column(name="rent_percent")
	public String getRentPercent() {
		return rentPercent;
	}
	public void setRentPercent(String rentPercent) {
		this.rentPercent = rentPercent;
	}
	@Column(name="room_income")
	public String getRoomIncome() {
		return roomIncome;
	}
	public void setRoomIncome(String roomIncome) {
		this.roomIncome = roomIncome;
	}
	@Column(name="catering_income")
	public String getCateringIncome() {
		return cateringIncome;
	}
	public void setCateringIncome(String cateringIncome) {
		this.cateringIncome = cateringIncome;
	}
	@Column(name="other_income")
	public String getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}
	@Column(name="total_monthtime")
	public String getTotalMonthTime() {
		return totalMonthTime;
	}
	public void setTotalMonthTime(String totalMonthTime) {
		this.totalMonthTime = totalMonthTime;
	}
	@Column(name="total_monthyeartime")
	public String getTotalMonthYearTime() {
		return totalMonthYearTime;
	}
	public void setTotalMonthYearTime(String totalMonthYearTime) {
		this.totalMonthYearTime = totalMonthYearTime;
	}
	@Column(name="total_monthday")
	public String getTotalMonthDay() {
		return totalMonthDay;
	}
	public void setTotalMonthDay(String totalMonthDay) {
		this.totalMonthDay = totalMonthDay;
	}
	@Column(name="total_monthyearday")
	public String getTotalMonthYearDay() {
		return totalMonthYearDay;
	}
	public void setTotalMonthYearDay(String totalMonthYearDay) {
		this.totalMonthYearDay = totalMonthYearDay;
	}
	@Column(name="inland_monthtime")
	public String getInlandMonthTime() {
		return inlandMonthTime;
	}
	public void setInlandMonthTime(String inlandMonthTime) {
		this.inlandMonthTime = inlandMonthTime;
	}
	@Column(name="inland_monthyeartime")
	public String getInlandMonthYearTime() {
		return inlandMonthYearTime;
	}
	public void setInlandMonthYearTime(String inlandMonthYearTime) {
		this.inlandMonthYearTime = inlandMonthYearTime;
	}
	@Column(name="inland_monthday")
	public String getInlandMonthDay() {
		return inlandMonthDay;
	}
	public void setInlandMonthDay(String inlandMonthDay) {
		this.inlandMonthDay = inlandMonthDay;
	}
	@Column(name="inland_monthyearday")
	public String getInlandMonthYearDay() {
		return inlandMonthYearDay;
	}
	public void setInlandMonthYearDay(String inlandMonthYearDay) {
		this.inlandMonthYearDay = inlandMonthYearDay;
	}
	@Column(name="entry_monthtime")
	public String getEntryMonthTime() {
		return entryMonthTime;
	}
	public void setEntryMonthTime(String entryMonthTime) {
		this.entryMonthTime = entryMonthTime;
	}
	@Column(name="entry_monthyeartime")
	public String getEntryMonthYearTime() {
		return entryMonthYearTime;
	}
	public void setEntryMonthYearTime(String entryMonthYearTime) {
		this.entryMonthYearTime = entryMonthYearTime;
	}
	@Column(name="entry_monthday")
	public String getEntryMonthDay() {
		return entryMonthDay;
	}
	public void setEntryMonthDay(String entryMonthDay) {
		this.entryMonthDay = entryMonthDay;
	}
	@Column(name="entry_monthyearday")
	public String getEntryMonthYearDay() {
		return entryMonthYearDay;
	}
	public void setEntryMonthYearDay(String entryMonthYearDay) {
		this.entryMonthYearDay = entryMonthYearDay;
	}
	@Column(name="hongkong_monthtime")
	public String getHongkongMonthTime() {
		return hongkongMonthTime;
	}
	public void setHongkongMonthTime(String hongkongMonthTime) {
		this.hongkongMonthTime = hongkongMonthTime;
	}
	@Column(name="hongkong_monthyeartime")
	public String getHongkongMonthYearTime() {
		return hongkongMonthYearTime;
	}
	public void setHongkongMonthYearTime(String hongkongMonthYearTime) {
		this.hongkongMonthYearTime = hongkongMonthYearTime;
	}
	@Column(name="hongkong_monthday")
	public String getHongkongMonthDay() {
		return hongkongMonthDay;
	}
	public void setHongkongMonthDay(String hongkongMonthDay) {
		this.hongkongMonthDay = hongkongMonthDay;
	}
	@Column(name="hongkong_monthyearday")
	public String getHongkongMonthYearDay() {
		return hongkongMonthYearDay;
	}
	public void setHongkongMonthYearDay(String hongkongMonthYearDay) {
		this.hongkongMonthYearDay = hongkongMonthYearDay;
	}
	@Column(name="macau_monthtime")
	public String getMacauMonthTime() {
		return macauMonthTime;
	}
	public void setMacauMonthTime(String macauMonthTime) {
		this.macauMonthTime = macauMonthTime;
	}
	@Column(name="macau_monthyeartime")
	public String getMacauMonthYearTime() {
		return macauMonthYearTime;
	}
	public void setMacauMonthYearTime(String macauMonthYearTime) {
		this.macauMonthYearTime = macauMonthYearTime;
	}
	@Column(name="macau_monthday")
	public String getMacauMonthDay() {
		return macauMonthDay;
	}
	public void setMacauMonthDay(String macauMonthDay) {
		this.macauMonthDay = macauMonthDay;
	}
	@Column(name="macau_monthyearday")
	public String getMacauMonthYearDay() {
		return macauMonthYearDay;
	}
	public void setMacauMonthYearDay(String macauMonthYearDay) {
		this.macauMonthYearDay = macauMonthYearDay;
	}
	@Column(name="taiwan_monthtime")
	public String getTaiwanMonthTime() {
		return taiwanMonthTime;
	}
	public void setTaiwanMonthTime(String taiwanMonthTime) {
		this.taiwanMonthTime = taiwanMonthTime;
	}
	@Column(name="taiwan_monthyeartime")
	public String getTaiwanMonthYearTime() {
		return taiwanMonthYearTime;
	}
	public void setTaiwanMonthYearTime(String taiwanMonthYearTime) {
		this.taiwanMonthYearTime = taiwanMonthYearTime;
	}
	@Column(name="taiwan_monthday")
	public String getTaiwanMonthDay() {
		return taiwanMonthDay;
	}
	public void setTaiwanMonthDay(String taiwanMonthDay) {
		this.taiwanMonthDay = taiwanMonthDay;
	}
	@Column(name="taiwan_monthyearday")
	public String getTaiwanMonthYearDay() {
		return taiwanMonthYearDay;
	}
	public void setTaiwanMonthYearDay(String taiwanMonthYearDay) {
		this.taiwanMonthYearDay = taiwanMonthYearDay;
	}
	@Column(name="foreign_monthtime")
	public String getForeignMonthTime() {
		return foreignMonthTime;
	}
	public void setForeignMonthTime(String foreignMonthTime) {
		this.foreignMonthTime = foreignMonthTime;
	}
	@Column(name="foreign_monthyeartime")
	public String getForeignMonthYearTime() {
		return foreignMonthYearTime;
	}
	public void setForeignMonthYearTime(String foreignMonthYearTime) {
		this.foreignMonthYearTime = foreignMonthYearTime;
	}
	@Column(name="foreign_monthday")
	public String getForeignMonthDay() {
		return foreignMonthDay;
	}
	public void setForeignMonthDay(String foreignMonthDay) {
		this.foreignMonthDay = foreignMonthDay;
	}
	@Column(name="foreign_monthyearday")
	public String getForeignMonthYearDay() {
		return foreignMonthYearDay;
	}
	public void setForeignMonthYearDay(String foreignMonthYearDay) {
		this.foreignMonthYearDay = foreignMonthYearDay;
	}
	@Column(name="asian_month")
	public String getAsianMonth() {
		return asianMonth;
	}
	public void setAsianMonth(String asianMonth) {
		this.asianMonth = asianMonth;
	}
	@Column(name="asian_monthyear")
	public String getAsianMonthYear() {
		return asianMonthYear;
	}
	public void setAsianMonthYear(String asianMonthYear) {
		this.asianMonthYear = asianMonthYear;
	}
	@Column(name="japan_month")
	public String getJapanMonth() {
		return japanMonth;
	}
	public void setJapanMonth(String japanMonth) {
		this.japanMonth = japanMonth;
	}
	@Column(name="japan_monthyear")
	public String getJapanMonthYear() {
		return japanMonthYear;
	}
	public void setJapanMonthYear(String japanMonthYear) {
		this.japanMonthYear = japanMonthYear;
	}
	@Column(name="korea_month")
	public String getKoreaMonth() {
		return koreaMonth;
	}
	public void setKoreaMonth(String koreaMonth) {
		this.koreaMonth = koreaMonth;
	}
	@Column(name="korea_monthyear")
	public String getKoreaMonthYear() {
		return koreaMonthYear;
	}
	public void setKoreaMonthYear(String koreaMonthYear) {
		this.koreaMonthYear = koreaMonthYear;
	}
	@Column(name="mongolia_month")
	public String getMongoliaMonth() {
		return mongoliaMonth;
	}
	public void setMongoliaMonth(String mongoliaMonth) {
		this.mongoliaMonth = mongoliaMonth;
	}
	@Column(name="mongolia_monthyear")
	public String getMongoliaMonthYear() {
		return mongoliaMonthYear;
	}
	public void setMongoliaMonthYear(String mongoliaMonthYear) {
		this.mongoliaMonthYear = mongoliaMonthYear;
	}
	@Column(name="indonesia_month")
	public String getIndonesiaMonth() {
		return indonesiaMonth;
	}
	public void setIndonesiaMonth(String indonesiaMonth) {
		this.indonesiaMonth = indonesiaMonth;
	}
	@Column(name="indonesia_monthyear")
	public String getIndonesiaMonthYear() {
		return indonesiaMonthYear;
	}
	public void setIndonesiaMonthYear(String indonesiaMonthYear) {
		this.indonesiaMonthYear = indonesiaMonthYear;
	}
	@Column(name="malaysia_month")
	public String getMalaysiaMonth() {
		return malaysiaMonth;
	}
	public void setMalaysiaMonth(String malaysiaMonth) {
		this.malaysiaMonth = malaysiaMonth;
	}
	@Column(name="malaysia_monthyear")
	public String getMalaysiaMonthYear() {
		return malaysiaMonthYear;
	}
	public void setMalaysiaMonthYear(String malaysiaMonthYear) {
		this.malaysiaMonthYear = malaysiaMonthYear;
	}
	@Column(name="philippines_month")
	public String getPhilippinesMonth() {
		return philippinesMonth;
	}
	public void setPhilippinesMonth(String philippinesMonth) {
		this.philippinesMonth = philippinesMonth;
	}
	@Column(name="philippines_monthyear")
	public String getPhilippinesMonthYear() {
		return philippinesMonthYear;
	}
	public void setPhilippinesMonthYear(String philippinesMonthYear) {
		this.philippinesMonthYear = philippinesMonthYear;
	}
	@Column(name="singapore_month")
	public String getSingaporeMonth() {
		return singaporeMonth;
	}
	public void setSingaporeMonth(String singaporeMonth) {
		this.singaporeMonth = singaporeMonth;
	}
	@Column(name="singapore_monthyear")
	public String getSingaporeMonthYear() {
		return singaporeMonthYear;
	}
	public void setSingaporeMonthYear(String singaporeMonthYear) {
		this.singaporeMonthYear = singaporeMonthYear;
	}
	@Column(name="thailand_month")
	public String getThailandMonth() {
		return thailandMonth;
	}
	public void setThailandMonth(String thailandMonth) {
		this.thailandMonth = thailandMonth;
	}
	@Column(name="thailand_monthyear")
	public String getThailandMonthYear() {
		return thailandMonthYear;
	}
	public void setThailandMonthYear(String thailandMonthYear) {
		this.thailandMonthYear = thailandMonthYear;
	}
	@Column(name="india_month")
	public String getIndiaMonth() {
		return indiaMonth;
	}
	public void setIndiaMonth(String indiaMonth) {
		this.indiaMonth = indiaMonth;
	}
	@Column(name="india_monthyear")
	public String getIndiaMonthYear() {
		return indiaMonthYear;
	}
	public void setIndiaMonthYear(String indiaMonthYear) {
		this.indiaMonthYear = indiaMonthYear;
	}
	@Column(name="vietnam_month")
	public String getVietnamMonth() {
		return vietnamMonth;
	}
	public void setVietnamMonth(String vietnamMonth) {
		this.vietnamMonth = vietnamMonth;
	}
	@Column(name="vietnam_monthyear")
	public String getVietnamMonthYear() {
		return vietnamMonthYear;
	}
	public void setVietnamMonthYear(String vietnamMonthYear) {
		this.vietnamMonthYear = vietnamMonthYear;
	}
	@Column(name="burma_month")
	public String getBurmaMonth() {
		return burmaMonth;
	}
	public void setBurmaMonth(String burmaMonth) {
		this.burmaMonth = burmaMonth;
	}
	@Column(name="burma_monthyear")
	public String getBurmaMonthYear() {
		return burmaMonthYear;
	}
	public void setBurmaMonthYear(String burmaMonthYear) {
		this.burmaMonthYear = burmaMonthYear;
	}
	@Column(name="northkorea_month")
	public String getNorthkoreaMonth() {
		return northkoreaMonth;
	}
	public void setNorthkoreaMonth(String northkoreaMonth) {
		this.northkoreaMonth = northkoreaMonth;
	}
	@Column(name="northkorea_monthyear")
	public String getNorthkoreaMonthYear() {
		return northkoreaMonthYear;
	}
	public void setNorthkoreaMonthYear(String northkoreaMonthYear) {
		this.northkoreaMonthYear = northkoreaMonthYear;
	}
	@Column(name="pakistan_month")
	public String getPakistanMonth() {
		return pakistanMonth;
	}
	public void setPakistanMonth(String pakistanMonth) {
		this.pakistanMonth = pakistanMonth;
	}
	@Column(name="pakistan_monthyear")
	public String getPakistanMonthYear() {
		return pakistanMonthYear;
	}
	public void setPakistanMonthYear(String pakistanMonthYear) {
		this.pakistanMonthYear = pakistanMonthYear;
	}
	@Column(name="asianother_month")
	public String getAsianOtherMonth() {
		return asianOtherMonth;
	}
	public void setAsianOtherMonth(String asianOtherMonth) {
		this.asianOtherMonth = asianOtherMonth;
	}
	@Column(name="asianother_monthyear")
	public String getAsianOtherMonthYear() {
		return asianOtherMonthYear;
	}
	public void setAsianOtherMonthYear(String asianOtherMonthYear) {
		this.asianOtherMonthYear = asianOtherMonthYear;
	}
	@Column(name="europe_month")
	public String getEuropeMonth() {
		return europeMonth;
	}
	public void setEuropeMonth(String europeMonth) {
		this.europeMonth = europeMonth;
	}
	@Column(name="europe_monthyear")
	public String getEuropeMonthYear() {
		return europeMonthYear;
	}
	public void setEuropeMonthYear(String europeMonthYear) {
		this.europeMonthYear = europeMonthYear;
	}
	@Column(name="england_month")
	public String getEnglandMonth() {
		return englandMonth;
	}
	public void setEnglandMonth(String englandMonth) {
		this.englandMonth = englandMonth;
	}
	@Column(name="england_monthyear")
	public String getEnglandMonthYear() {
		return englandMonthYear;
	}
	public void setEnglandMonthYear(String englandMonthYear) {
		this.englandMonthYear = englandMonthYear;
	}
	@Column(name="france_month")
	public String getFranceMonth() {
		return franceMonth;
	}
	public void setFranceMonth(String franceMonth) {
		this.franceMonth = franceMonth;
	}
	@Column(name="france_monthyear")
	public String getFranceMonthYear() {
		return franceMonthYear;
	}
	public void setFranceMonthYear(String franceMonthYear) {
		this.franceMonthYear = franceMonthYear;
	}
	@Column(name="germany_month")
	public String getGermanyMonth() {
		return germanyMonth;
	}
	public void setGermanyMonth(String germanyMonth) {
		this.germanyMonth = germanyMonth;
	}
	@Column(name="germany_monthyear")
	public String getGermanyMonthYear() {
		return germanyMonthYear;
	}
	public void setGermanyMonthYear(String germanyMonthYear) {
		this.germanyMonthYear = germanyMonthYear;
	}
	@Column(name="italy_month")
	public String getItalyMonth() {
		return italyMonth;
	}
	public void setItalyMonth(String italyMonth) {
		this.italyMonth = italyMonth;
	}
	@Column(name="italy_monthyear")
	public String getItalyMonthYear() {
		return italyMonthYear;
	}
	public void setItalyMonthYear(String italyMonthYear) {
		this.italyMonthYear = italyMonthYear;
	}
	@Column(name="switzerland_month")
	public String getSwitzerlandMonth() {
		return switzerlandMonth;
	}
	public void setSwitzerlandMonth(String switzerlandMonth) {
		this.switzerlandMonth = switzerlandMonth;
	}
	@Column(name="switzerland_monthyear")
	public String getSwitzerlandMonthYear() {
		return switzerlandMonthYear;
	}
	public void setSwitzerlandMonthYear(String switzerlandMonthYear) {
		this.switzerlandMonthYear = switzerlandMonthYear;
	}
	@Column(name="sweden_month")
	public String getSwedenMonth() {
		return swedenMonth;
	}
	public void setSwedenMonth(String swedenMonth) {
		this.swedenMonth = swedenMonth;
	}
	@Column(name="sweden_monthyear")
	public String getSwedenMonthYear() {
		return swedenMonthYear;
	}
	public void setSwedenMonthYear(String swedenMonthYear) {
		this.swedenMonthYear = swedenMonthYear;
	}
	@Column(name="russia_month")
	public String getRussiaMonth() {
		return russiaMonth;
	}
	public void setRussiaMonth(String russiaMonth) {
		this.russiaMonth = russiaMonth;
	}
	@Column(name="russia_monthyear")
	public String getRussiaMonthYear() {
		return russiaMonthYear;
	}
	public void setRussiaMonthYear(String russiaMonthYear) {
		this.russiaMonthYear = russiaMonthYear;
	}
	@Column(name="spain_month")
	public String getSpainMonth() {
		return spainMonth;
	}
	public void setSpainMonth(String spainMonth) {
		this.spainMonth = spainMonth;
	}
	@Column(name="spain_monthyear")
	public String getSpainMonthYear() {
		return spainMonthYear;
	}
	public void setSpainMonthYear(String spainMonthYear) {
		this.spainMonthYear = spainMonthYear;
	}
	@Column(name="europeother_month")
	public String getEuropeOtherMonth() {
		return europeOtherMonth;
	}
	public void setEuropeOtherMonth(String europeOtherMonth) {
		this.europeOtherMonth = europeOtherMonth;
	}
	@Column(name="europeother_monthyear")
	public String getEuropeOtherMonthYear() {
		return europeOtherMonthYear;
	}
	public void setEuropeOtherMonthYear(String europeOtherMonthYear) {
		this.europeOtherMonthYear = europeOtherMonthYear;
	}
	@Column(name="america_month")
	public String getAmericaMonth() {
		return americaMonth;
	}
	public void setAmericaMonth(String americaMonth) {
		this.americaMonth = americaMonth;
	}
	@Column(name="america_monthyear")
	public String getAmericaMonthYear() {
		return americaMonthYear;
	}
	public void setAmericaMonthYear(String americaMonthYear) {
		this.americaMonthYear = americaMonthYear;
	}
	@Column(name="canada_month")
	public String getCanadaMonth() {
		return canadaMonth;
	}
	public void setCanadaMonth(String canadaMonth) {
		this.canadaMonth = canadaMonth;
	}
	@Column(name="canada_monthyear")
	public String getCanadaMonthYear() {
		return canadaMonthYear;
	}
	public void setCanadaMonthYear(String canadaMonthYear) {
		this.canadaMonthYear = canadaMonthYear;
	}
	@Column(name="americaother_month")
	public String getAmericaOtherMonth() {
		return americaOtherMonth;
	}
	public void setAmericaOtherMonth(String americaOtherMonth) {
		this.americaOtherMonth = americaOtherMonth;
	}
	@Column(name="americaother_monthyear")
	public String getAmericaOtherMonthYear() {
		return americaOtherMonthYear;
	}
	public void setAmericaOtherMonthYear(String americaOtherMonthYear) {
		this.americaOtherMonthYear = americaOtherMonthYear;
	}
	@Column(name="oceania_month")
	public String getOceaniaMonth() {
		return oceaniaMonth;
	}
	public void setOceaniaMonth(String oceaniaMonth) {
		this.oceaniaMonth = oceaniaMonth;
	}
	@Column(name="oceania_monthyear")
	public String getOceaniaMonthYear() {
		return oceaniaMonthYear;
	}
	public void setOceaniaMonthYear(String oceaniaMonthYear) {
		this.oceaniaMonthYear = oceaniaMonthYear;
	}
	@Column(name="australia_month")
	public String getAustraliaMonth() {
		return australiaMonth;
	}
	public void setAustraliaMonth(String australiaMonth) {
		this.australiaMonth = australiaMonth;
	}
	@Column(name="australia_monthyear")
	public String getAustraliaMonthYear() {
		return australiaMonthYear;
	}
	public void setAustraliaMonthYear(String australiaMonthYear) {
		this.australiaMonthYear = australiaMonthYear;
	}
	@Column(name="africa_month")
	public String getAfricaMonth() {
		return africaMonth;
	}
	public void setAfricaMonth(String africaMonth) {
		this.africaMonth = africaMonth;
	}
	@Column(name="africa_monthyear")
	public String getAfricaMonthYear() {
		return africaMonthYear;
	}
	public void setAfricaMonthYear(String africaMonthYear) {
		this.africaMonthYear = africaMonthYear;
	}
	@Column(name="other_month")
	public String getOtherMonth() {
		return otherMonth;
	}
	public void setOtherMonth(String otherMonth) {
		this.otherMonth = otherMonth;
	}
	@Column(name="other_monthyear")
	public String getOtherMonthYear() {
		return otherMonthYear;
	}
	public void setOtherMonthYear(String otherMonthYear) {
		this.otherMonthYear = otherMonthYear;
	}
	@Column(name="usa_month")
	public String getUsaMonth() {
		return usaMonth;
	}
	public void setUsaMonth(String usaMonth) {
		this.usaMonth = usaMonth;
	}
	@Column(name="usa_monthyear")
	public String getUsaMonthYear() {
		return usaMonthYear;
	}
	public void setUsaMonthYear(String usaMonthYear) {
		this.usaMonthYear = usaMonthYear;
	}
	@Column(name="nz_month")
	public String getNzMonth() {
		return nzMonth;
	}
	public void setNzMonth(String nzMonth) {
		this.nzMonth = nzMonth;
	}
	@Column(name="nz_monthyear")
	public String getNzMonthYear() {
		return nzMonthYear;
	}
	public void setNzMonthYear(String nzMonthYear) {
		this.nzMonthYear = nzMonthYear;
	}
	@Column(name="oceaniaother_month")
	public String getOceaniaOtherMonth() {
		return oceaniaOtherMonth;
	}
	public void setOceaniaOtherMonth(String oceaniaOtherMonth) {
		this.oceaniaOtherMonth = oceaniaOtherMonth;
	}
	@Column(name="oceaniaother_monthyear")
	public String getOceaniaOtherMonthYear() {
		return oceaniaOtherMonthYear;
	}
	public void setOceaniaOtherMonthYear(String oceaniaOtherMonthYear) {
		this.oceaniaOtherMonthYear = oceaniaOtherMonthYear;
	}
	
	
	@Column(name="laos_month")
	public String getLaosMonth() {
		return laosMonth;
	}
	public void setLaosMonth(String laosMonth) {
		this.laosMonth = laosMonth;
	}
	@Column(name="laos_monthyear")
	public String getLaosMonthYear() {
		return laosMonthYear;
	}
	public void setLaosMonthYear(String laosMonthYear) {
		this.laosMonthYear = laosMonthYear;
	}
	@Column(name="cambodia_month")
	public String getCambodiaMonth() {
		return cambodiaMonth;
	}
	public void setCambodiaMonth(String cambodiaMonth) {
		this.cambodiaMonth = cambodiaMonth;
	}
	@Column(name="cambodia_monthyear")
	public String getCambodiaMonthYear() {
		return cambodiaMonthYear;
	}
	public void setCambodiaMonthYear(String cambodiaMonthYear) {
		this.cambodiaMonthYear = cambodiaMonthYear;
	}
	@Column(name="nepal_month")
	public String getNepalMonth() {
		return nepalMonth;
	}
	public void setNepalMonth(String nepalMonth) {
		this.nepalMonth = nepalMonth;
	}
	@Column(name="nepal_monthyear")
	public String getNepalMonthYear() {
		return nepalMonthYear;
	}
	public void setNepalMonthYear(String nepalMonthYear) {
		this.nepalMonthYear = nepalMonthYear;
	}
	@Column(name="srilanka_month")
	public String getSrilankaMonth() {
		return srilankaMonth;
	}
	public void setSrilankaMonth(String srilankaMonth) {
		this.srilankaMonth = srilankaMonth;
	}
	@Column(name="srilanka_monthyear")
	public String getSrilankaMonthYear() {
		return srilankaMonthYear;
	}
	public void setSrilankaMonthYear(String srilankaMonthYear) {
		this.srilankaMonthYear = srilankaMonthYear;
	}
	@Column(name="kz_month")
	public String getKzMonth() {
		return kzMonth;
	}
	public void setKzMonth(String kzMonth) {
		this.kzMonth = kzMonth;
	}
	@Column(name="kz_monthyear")
	public String getKzMonthYear() {
		return kzMonthYear;
	}
	public void setKzMonthYear(String kzMonthYear) {
		this.kzMonthYear = kzMonthYear;
	}
	@Column(name="kyrghyzstan_month")
	public String getKyrghyzstanMonth() {
		return kyrghyzstanMonth;
	}
	public void setKyrghyzstanMonth(String kyrghyzstanMonth) {
		this.kyrghyzstanMonth = kyrghyzstanMonth;
	}
	@Column(name="kyrghyzstan_monthyear")
	public String getKyrghyzstanMonthYear() {
		return kyrghyzstanMonthYear;
	}
	public void setKyrghyzstanMonthYear(String kyrghyzstanMonthYear) {
		this.kyrghyzstanMonthYear = kyrghyzstanMonthYear;
	}
	@Column(name="uz_month")
	public String getUzMonth() {
		return uzMonth;
	}
	public void setUzMonth(String uzMonth) {
		this.uzMonth = uzMonth;
	}
	@Column(name="uz_monthyear")
	public String getUzMonthYear() {
		return uzMonthYear;
	}
	public void setUzMonthYear(String uzMonthYear) {
		this.uzMonthYear = uzMonthYear;
	}
	@Column(name="tajikistan_month")
	public String getTajikistanMonth() {
		return tajikistanMonth;
	}
	public void setTajikistanMonth(String tajikistanMonth) {
		this.tajikistanMonth = tajikistanMonth;
	}
	@Column(name="tajikistan_monthyear")
	public String getTajikistanMonthYear() {
		return tajikistanMonthYear;
	}
	public void setTajikistanMonthYear(String tajikistanMonthYear) {
		this.tajikistanMonthYear = tajikistanMonthYear;
	}
	@Column(name="ireland_month")
	public String getIrelandMonth() {
		return irelandMonth;
	}
	public void setIrelandMonth(String irelandMonth) {
		this.irelandMonth = irelandMonth;
	}
	@Column(name="ireland_monthyear")
	public String getIrelandMonthYear() {
		return irelandMonthYear;
	}
	public void setIrelandMonthYear(String irelandMonthYear) {
		this.irelandMonthYear = irelandMonthYear;
	}
	@Column(name="ukraine_month")
	public String getUkraineMonth() {
		return ukraineMonth;
	}
	public void setUkraineMonth(String ukraineMonth) {
		this.ukraineMonth = ukraineMonth;
	}
	@Column(name="ukraine_monthyear")
	public String getUkraineMonthYear() {
		return ukraineMonthYear;
	}
	public void setUkraineMonthYear(String ukraineMonthYear) {
		this.ukraineMonthYear = ukraineMonthYear;
	}
	@Column(name="belgium_month")
	public String getBelgiumMonth() {
		return belgiumMonth;
	}
	public void setBelgiumMonth(String belgiumMonth) {
		this.belgiumMonth = belgiumMonth;
	}
	@Column(name="belgium_monthyear")
	public String getBelgiumMonthYear() {
		return belgiumMonthYear;
	}
	public void setBelgiumMonthYear(String belgiumMonthYear) {
		this.belgiumMonthYear = belgiumMonthYear;
	}
	@Column(name="cz_month")
	public String getCzMonth() {
		return czMonth;
	}
	public void setCzMonth(String czMonth) {
		this.czMonth = czMonth;
	}
	@Column(name="cz_monthyear")
	public String getCzMonthYear() {
		return czMonthYear;
	}
	public void setCzMonthYear(String czMonthYear) {
		this.czMonthYear = czMonthYear;
	}
	@Column(name="austria_month")
	public String getAustriaMonth() {
		return austriaMonth;
	}
	public void setAustriaMonth(String austriaMonth) {
		this.austriaMonth = austriaMonth;
	}
	@Column(name="austria_monthyear")
	public String getAustriaMonthYear() {
		return austriaMonthYear;
	}
	public void setAustriaMonthYear(String austriaMonthYear) {
		this.austriaMonthYear = austriaMonthYear;
	}
	@Column(name="portugal_month")
	public String getPortugalMonth() {
		return portugalMonth;
	}
	public void setPortugalMonth(String portugalMonth) {
		this.portugalMonth = portugalMonth;
	}
	@Column(name="portugal_monthyear")
	public String getPortugalMonthYear() {
		return portugalMonthYear;
	}
	public void setPortugalMonthYear(String portugalMonthYear) {
		this.portugalMonthYear = portugalMonthYear;
	}
	@Column(name="holland_month")
	public String getHollandMonth() {
		return hollandMonth;
	}
	public void setHollandMonth(String hollandMonth) {
		this.hollandMonth = hollandMonth;
	}
	@Column(name="holland_monthyear")
	public String getHollandMonthYear() {
		return hollandMonthYear;
	}
	public void setHollandMonthYear(String hollandMonthYear) {
		this.hollandMonthYear = hollandMonthYear;
	}
	@Column(name="mexico_month")
	public String getMexicoMonth() {
		return mexicoMonth;
	}
	public void setMexicoMonth(String mexicoMonth) {
		this.mexicoMonth = mexicoMonth;
	}
	@Column(name="mexico_monthyear")
	public String getMexicoMonthYear() {
		return mexicoMonthYear;
	}
	public void setMexicoMonthYear(String mexicoMonthYear) {
		this.mexicoMonthYear = mexicoMonthYear;
	}
	@Column(name="brazil_month")
	public String getBrazilMonth() {
		return brazilMonth;
	}
	public void setBrazilMonth(String brazilMonth) {
		this.brazilMonth = brazilMonth;
	}
	@Column(name="brazil_monthyear")
	public String getBrazilMonthYear() {
		return brazilMonthYear;
	}
	public void setBrazilMonthYear(String brazilMonthYear) {
		this.brazilMonthYear = brazilMonthYear;
	}
	@Column(name="argentina_month")
	public String getArgentinaMonth() {
		return argentinaMonth;
	}
	public void setArgentinaMonth(String argentinaMonth) {
		this.argentinaMonth = argentinaMonth;
	}
	@Column(name="argentina_monthyear")
	public String getArgentinaMonthYear() {
		return argentinaMonthYear;
	}
	public void setArgentinaMonthYear(String argentinaMonthYear) {
		this.argentinaMonthYear = argentinaMonthYear;
	}
	@Column(name="chile_month")
	public String getChileMonth() {
		return chileMonth;
	}
	public void setChileMonth(String chileMonth) {
		this.chileMonth = chileMonth;
	}
	@Column(name="chile_monthyear")
	public String getChileMonthYear() {
		return chileMonthYear;
	}
	public void setChileMonthYear(String chileMonthYear) {
		this.chileMonthYear = chileMonthYear;
	}
	@Column(name="peru_month")
	public String getPeruMonth() {
		return peruMonth;
	}
	public void setPeruMonth(String peruMonth) {
		this.peruMonth = peruMonth;
	}
	@Column(name="peru_monthyear")
	public String getPeruMonthYear() {
		return peruMonthYear;
	}
	public void setPeruMonthYear(String peruMonthYear) {
		this.peruMonthYear = peruMonthYear;
	}
	@Column(name="total_income")
	public String getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}
	@Column(name="aisaother_month")
	public String getAisaOtherMonth() {
		return aisaOtherMonth;
	}
	public void setAisaOtherMonth(String aisaOtherMonth) {
		this.aisaOtherMonth = aisaOtherMonth;
	}
	@Column(name="aisaother_monthyear")
	public String getAisaOtherMonthYear() {
		return aisaOtherMonthYear;
	}
	public void setAisaOtherMonthYear(String aisaOtherMonthYear) {
		this.aisaOtherMonthYear = aisaOtherMonthYear;
	}
	@Column(name="average_roomprice")
	public String getAverageRoomPrice() {
		return averageRoomPrice;
	}
	public void setAverageRoomPrice(String averageRoomPrice) {
		this.averageRoomPrice = averageRoomPrice;
	}
	@Column(name="province_state")
	public String getProvinceState() {
		return provinceState;
	}
	public void setProvinceState(String provinceState) {
		this.provinceState = provinceState;
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
}
