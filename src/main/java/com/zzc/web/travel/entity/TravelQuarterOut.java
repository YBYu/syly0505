package com.zzc.web.travel.entity;

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
import com.zzc.poi.excel.annotation.ExcelTarget;
 

/**
 * @Title: 
 * @Description: 旅游季报出境实体类
 * @author 冯勇齐
 * @date 2016-12-9 15:31:20
 * 
 */


@Entity      //2  旅行社组织出镜旅游情况
@Table(name = "t_travelAgency_quarterly2")
@SuppressWarnings("serial")
@ExcelTarget("travelQuarterOut")
public class TravelQuarterOut  implements java.io.Serializable{
	
	
	private String  id;
	private String tid;
    private String  indexname;
    private String  code;
    private Integer orgNum;
    private Integer orgReceiveNum;
    private Integer oneDayNum;
    private Integer nightNum;
    private Integer nightOrgNum;
    private String  status;
    @ExcelEntity()
    private Traveldata traveldata;
    private String reportPerson;
    private String reportTelephone;
    @Excel(name="年份",needMerge=true)
	private String year;
    @Excel(name="季度",needMerge=true)
	private String quarter;
    private String reportDate;
    
    // 分数
    private Integer score;
    // 国家系统审核意见
    private String guo;
    
    private String quarterTwo;
    //合计
    private Integer total;
    //合计指标名称代码
    private Integer totalCode;
    
    // 五大洲汇总
    private String asia;
    private String europe;
    private String africa;
    private String oceania;
    private String america;
    
    //出境游客
    private String outName;
    //代码
    private  Integer outCode;
    private  Integer outOne;
    private  Integer outTwo;
    private  Integer outThree;
    private  Integer outFour;
    //亚洲小计
    private  String asianTotalName;
    private  Integer asianTotalCode;
    @Excel(name="亚洲",needMerge=true)
    private  Integer asianTotalOne;
    private  Integer asianTotalTwo;
    //香港地区
    private  String hongKongName;
    private  Integer hongKongCode;
    private  Integer hongKongOne;
    private  Integer hongKongTwo;
    //澳门地区
    private  String macauName;
    private  Integer macauCode;
    private  Integer macauOne;
    private  Integer macauTwo;
    //台湾地区
    private  String taiWanName;
    private Integer taiWanCode;
    private Integer taiWanOne;
    private Integer taiWanTwo;
    //日本
    private String japan;
    private Integer japanCode;
    private Integer japanOne;
    private Integer japanTwo;
    //韩国
    private String korea;
    private Integer koreaCode;
    private Integer koreaOne;
    private Integer koreaTwo;
    //蒙古
    private String mongolia;
    private Integer mongoliaCode;
    private Integer mongoliaOne;
    private Integer mongoliaTwo;
    //印度尼西亚
    private String indonesia;
    private Integer indonesiaCode;
    private Integer indonesiaOne;
    private Integer indonesiaTwo;
    //马来西亚
    private String malaysia;
    private Integer malaysiaCode;
    private Integer malaysiaOne;
    private Integer malaysiaTwo;
    //菲律宾
    private String philippines;
    private Integer philippinesCode;
    private Integer philippinesOne;
    private Integer philippinesTwo;
    //新加坡
    private String singapore;
    private Integer singaporeCode;
    private Integer singaporeOne;
    private Integer singaporeTwo;
    //泰国
    private String thailand;
    
    private Integer thailandCode;
    
    private Integer thailandOne;
    
    private Integer thailandTwo;
    //印度
    private String india;
    private Integer indiaCode;
    private Integer indiaOne;
    private Integer indiaTwo;
    //
    private String vietnam;
    private Integer vietnamCode;
    private Integer vietnamOne;
    private Integer vietnamTwo;
    
    private String burma;
    private Integer burmaCode;
    private Integer burmaOne;
    private Integer burmaTwo;
    
    private String asianOther;
    private Integer asianOtherCode;
    private Integer asianOtherOne;
    private Integer asianOtherTwo;
    
    private String europeTotal;
    private Integer europeCode;
    @Excel(name="欧洲",needMerge=true)
    private Integer europeOne;
    private Integer europeTwo;
    
    private String france;
    private Integer franchCode;
    private Integer franchOne;
    private Integer franchTwo;
    
    private String germany;
    private Integer germanyCode;
    private Integer germanyOne;
    private Integer germanyTwo;
    
    private String italy;
    private Integer italyCode;
    private Integer italyOne;
    private Integer italyTwo;
    
    private String switzerLand;
    private Integer switzerLandCode;
    private Integer switzerLandOne;
    private Integer switzerLandTwo;
    
    private String sweden;
    private Integer swedenCode;
    private Integer swedenOne;
    private Integer swedenTwo;
    
    private String russia;
    private Integer russiaCode;
    private Integer russiaOne;
    private Integer russiTwo;
    
    private String spain;
    private Integer spainCode;
    private Integer spainOne;
    private Integer spainTwo;
    
    private String holLand;
    private Integer holLandCode;
    private Integer holLandOne;
    private Integer holLandTwo;
    

    private String denMark;
    private Integer denMarkCode;
    private Integer denMarkOne;
    private Integer denMarkTwo;

    private String europeOther;
    private Integer europeOtherCode;
    private Integer europeOtherOne;
    private Integer europeOtherTwo;

    private String americaTotal;
    private Integer americaTotalCode;
    @Excel(name="美洲",needMerge=true)
    private Integer americaTotalOne;
    private Integer americaTotalTwo;

    private String us;
    private Integer usCode;
    private Integer usOne;
    private Integer usTwo;

    private String usOther;
    private Integer usOtherCode;
    private Integer usOtherOne;
    private Integer usOtherTwo;

    private String oceaniaTotal;
    private Integer oceaniaTotalCode;
    @Excel(name="大洋洲",needMerge=true)
    private Integer oceaniaTotalOne;
    private Integer oceaniaTotalTwo;

    private String australian;
    private Integer australianCode;
    private Integer australianOne;
    private Integer australianTwo;

    private String zeaLand;
    private Integer zeaLandCode;
    private Integer zeaLandOne;
    private Integer zeaLandTwo;

    private String oceaniaOther;
    private Integer oceaniaOtherCode;
    private Integer oceaniaOtherOne;
    private Integer oceaniaOtherTwo;

    private String africaTotal;
    private Integer africaCode;
    @Excel(name="非洲",needMerge=true)
    private Integer africaOne;
    private Integer africaTwo;

    private String southAfrica;
    private Integer southAfricaCode;
    private Integer southAfricaOne;
    private Integer southAfricaTwo;

    private String egypt;
    private Integer egyptCode;
    private Integer egyptOne;
    private Integer egyptTwo;

    private String kenya;
    private Integer kenyaCode;
    private Integer kenyaOne;
    private Integer kenyaTwo;

    private String africaOther;
    private Integer africaOtherCode;
    private Integer africaOtherOne;
    private Integer africaOtherTwo;

    private String otherTotal;
    private Integer otherTotalCode;
    private Integer otherTotalOne;
    private Integer otherTotalTwo;
    private String  english;
    private Integer englishOne;
    private Integer englishTwo;
    
    private String canada;
    private Integer canadaCode;
    private Integer canadaOne;
    private Integer canadaTwo;
    
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
	public String getQuarterTwo() {
		return quarterTwo;
	}
	public void setQuarterTwo(String quarterTwo) {
		this.quarterTwo = quarterTwo;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getTotalCode() {
		return totalCode;
	}
	public void setTotalCode(Integer totalCode) {
		this.totalCode = totalCode;
	}
	@Column(name ="out_guest_name",nullable=true,length=20)
	public String getOutName() {
		return outName;
	}
	public void setOutName(String outName) {
		this.outName = outName;
	}
	@Column(name ="out_guest_code",nullable=true,length=50)
	public Integer getOutCode() {
		return outCode;
	}
	public void setOutCode(Integer outCode) {
		this.outCode = outCode;
	}
	@Column(name ="out_guest_one",nullable=true,length=50)
	public Integer getOutOne() {
		return outOne;
	}
	public void setOutOne(Integer outOne) {
		this.outOne = outOne;
	}
	@Column(name ="out_guest_two",nullable=true,length=50)
	public Integer getOutTwo() {
		return outTwo;
	}
	public void setOutTwo(Integer outTwo) {
		this.outTwo = outTwo;
	}
	@Column(name ="out_guest_three",nullable=true,length=50)
	public Integer getOutThree() {
		return outThree;
	}
	public void setOutThree(Integer outThree) {
		this.outThree = outThree;
	}
	@Column(name ="out_guest_four",nullable=true,length=50)
	public Integer getOutFour() {
		return outFour;
	}
	public void setOutFour(Integer outFour) {
		this.outFour = outFour;
	}
	@Column(name ="asian_total_name",nullable=true,length=20)
	public String getAsianTotalName() {
		return asianTotalName;
	}
	public void setAsianTotalName(String asianTotalName) {
		this.asianTotalName = asianTotalName;
	}
	@Column(name ="asian_total_code",nullable=true,length=50)
	public Integer getAsianTotalCode() {
		return asianTotalCode;
	}
	public void setAsianTotalCode(Integer asianTotalCode) {
		this.asianTotalCode = asianTotalCode;
	}
	@Column(name ="asian_total_one",nullable=true,length=50)
	public Integer getAsianTotalOne() {
		return asianTotalOne;
	}
	public void setAsianTotalOne(Integer asianTotalOne) {
		this.asianTotalOne = asianTotalOne;
	}
	@Column(name ="asian_total_two",nullable=true,length=50)
	public Integer getAsianTotalTwo() {
		return asianTotalTwo;
	}
	public void setAsianTotalTwo(Integer asianTotalTwo) {
		this.asianTotalTwo = asianTotalTwo;
	}
	@Column(name ="hongkong_name",nullable=true,length=20)
	public String getHongKongName() {
		return hongKongName;
	}
	public void setHongKongName(String hongKongName) {
		this.hongKongName = hongKongName;
	}
	@Column(name ="hongkong_code",nullable=true,length=50)
	public Integer getHongKongCode() {
		return hongKongCode;
	}
	public void setHongKongCode(Integer hongKongCode) {
		this.hongKongCode = hongKongCode;
	}
	@Column(name ="hongkong_one",nullable=true,length=50)
	public Integer getHongKongOne() {
		return hongKongOne;
	}
	public void setHongKongOne(Integer hongKongOne) {
		this.hongKongOne = hongKongOne;
	}
	@Column(name ="hongkong_two",nullable=true,length=50)
	public Integer getHongKongTwo() {
		return hongKongTwo;
	}
	public void setHongKongTwo(Integer hongKongTwo) {
		this.hongKongTwo = hongKongTwo;
	}
	@Column(name ="macau_name",nullable=true,length=20)
	public String getMacauName() {
		return macauName;
	}
	public void setMacauName(String macauName) {
		this.macauName = macauName;
	}
	@Column(name ="macau_code",nullable=true,length=50)
	public Integer getMacauCode() {
		return macauCode;
	}
	public void setMacauCode(Integer macauCode) {
		this.macauCode = macauCode;
	}
	@Column(name ="macau_one",nullable=true,length=50)
	public Integer getMacauOne() {
		return macauOne;
	}
	public void setMacauOne(Integer macauOne) {
		this.macauOne = macauOne;
	}
	@Column(name ="macau_two",nullable=true,length=50)
	public Integer getMacauTwo() {
		return macauTwo;
	}
	public void setMacauTwo(Integer macauTwo) {
		this.macauTwo = macauTwo;
	}
	@Column(name ="taiwan_name",nullable=true,length=20)
	public String getTaiWanName() {
		return taiWanName;
	}
	public void setTaiWanName(String taiWanName) {
		this.taiWanName = taiWanName;
	}
	@Column(name ="taiwan_code",nullable=true,length=50)
	public Integer getTaiWanCode() {
		return taiWanCode;
	}
	public void setTaiWanCode(Integer taiWanCode) {
		this.taiWanCode = taiWanCode;
	}
	@Column(name ="taiwan_one",nullable=true,length=50)
	public Integer getTaiWanOne() {
		return taiWanOne;
	}
	public void setTaiWanOne(Integer taiWanOne) {
		this.taiWanOne = taiWanOne;
	}
	@Column(name ="taiwan_two",nullable=true,length=50)
	public Integer getTaiWanTwo() {
		return taiWanTwo;
	}
	public void setTaiWanTwo(Integer taiWanTwo) {
		this.taiWanTwo = taiWanTwo;
	}
	@Column(name ="Japan",nullable=true,length=20)
	public String getJapan() {
		return japan;
	}
	public void setJapan(String japan) {
		this.japan = japan;
	}
	@Column(name ="Japan_code",nullable=true,length=50)
	public Integer getJapanCode() {
		return japanCode;
	}
	public void setJapanCode(Integer japanCode) {
		this.japanCode = japanCode;
	}
	@Column(name ="Japan_one",nullable=true,length=50)
	public Integer getJapanOne() {
		return japanOne;
	}
	public void setJapanOne(Integer japanOne) {
		this.japanOne = japanOne;
	}
	@Column(name ="Japan_two",nullable=true,length=50)
	public Integer getJapanTwo() {
		return japanTwo;
	}
	public void setJapanTwo(Integer japanTwo) {
		this.japanTwo = japanTwo;
	}
	@Column(name ="Korea",nullable=true,length=20)
	public String getKorea() {
		return korea;
	}
	public void setKorea(String korea) {
		this.korea = korea;
	}
	@Column(name ="Korea_code",nullable=true,length=50)
	public Integer getKoreaCode() {
		return koreaCode;
	}
	public void setKoreaCode(Integer koreaCode) {
		this.koreaCode = koreaCode;
	}
	@Column(name ="Korea_one",nullable=true,length=50)
	public Integer getKoreaOne() {
		return koreaOne;
	}
	public void setKoreaOne(Integer koreaOne) {
		this.koreaOne = koreaOne;
	}
	@Column(name ="Korea_two",nullable=true,length=50)
	public Integer getKoreaTwo() {
		return koreaTwo;
	}
	public void setKoreaTwo(Integer koreaTwo) {
		this.koreaTwo = koreaTwo;
	}
	@Column(name ="Mongolia",nullable=true,length=20)
	public String getMongolia() {
		return mongolia;
	}
	public void setMongolia(String mongolia) {
		this.mongolia = mongolia;
	}
	@Column(name ="Mongolia_code",nullable=true,length=50)
	public Integer getMongoliaCode() {
		return mongoliaCode;
	}
	public void setMongoliaCode(Integer mongoliaCode) {
		this.mongoliaCode = mongoliaCode;
	}
	@Column(name ="Mongolia_one",nullable=true,length=50)
	public Integer getMongoliaOne() {
		return mongoliaOne;
	}
	public void setMongoliaOne(Integer mongoliaOne) {
		this.mongoliaOne = mongoliaOne;
	}
	@Column(name ="Mongolia_two",nullable=true,length=50)
	public Integer getMongoliaTwo() {
		return mongoliaTwo;
	}
	public void setMongoliaTwo(Integer mongoliaTwo) {
		this.mongoliaTwo = mongoliaTwo;
	}
	@Column(name ="Indonesia",nullable=true,length=20)
	public String getIndonesia() {
		return indonesia;
	}
	public void setIndonesia(String indonesia) {
		this.indonesia = indonesia;
	}
	@Column(name ="Indonesia_code",nullable=true,length=50)
	public Integer getIndonesiaCode() {
		return indonesiaCode;
	}
	public void setIndonesiaCode(Integer indonesiaCode) {
		this.indonesiaCode = indonesiaCode;
	}
	@Column(name ="Indonesia_one",nullable=true,length=50)
	public Integer getIndonesiaOne() {
		return indonesiaOne;
	}
	public void setIndonesiaOne(Integer indonesiaOne) {
		this.indonesiaOne = indonesiaOne;
	}
	@Column(name ="Indonesia_two",nullable=true,length=50)
	public Integer getIndonesiaTwo() {
		return indonesiaTwo;
	}
	public void setIndonesiaTwo(Integer indonesiaTwo) {
		this.indonesiaTwo = indonesiaTwo;
	}
	@Column(name ="Malaysia",nullable=true,length=20)
	public String getMalaysia() {
		return malaysia;
	}
	public void setMalaysia(String malaysia) {
		this.malaysia = malaysia;
	}
	@Column(name ="Malaysia_code",nullable=true,length=50)
	public Integer getMalaysiaCode() {
		return malaysiaCode;
	}
	public void setMalaysiaCode(Integer malaysiaCode) {
		this.malaysiaCode = malaysiaCode;
	}
	@Column(name ="Malaysia_one",nullable=true,length=50)
	public Integer getMalaysiaOne() {
		return malaysiaOne;
	}
	public void setMalaysiaOne(Integer malaysiaOne) {
		this.malaysiaOne = malaysiaOne;
	}
	@Column(name ="Malaysia_two",nullable=true,length=50)
	public Integer getMalaysiaTwo() {
		return malaysiaTwo;
	}
	public void setMalaysiaTwo(Integer malaysiaTwo) {
		this.malaysiaTwo = malaysiaTwo;
	}
	@Column(name ="Philippines",nullable=true,length=20)
	public String getPhilippines() {
		return philippines;
	}
	public void setPhilippines(String philippines) {
		this.philippines = philippines;
	}
	@Column(name ="Philippines_code",nullable=true,length=50)
	public Integer getPhilippinesCode() {
		return philippinesCode;
	}
	public void setPhilippinesCode(Integer philippinesCode) {
		this.philippinesCode = philippinesCode;
	}
	@Column(name ="Philippines_one",nullable=true,length=50)
	public Integer getPhilippinesOne() {
		return philippinesOne;
	}
	public void setPhilippinesOne(Integer philippinesOne) {
		this.philippinesOne = philippinesOne;
	}
	@Column(name ="Philippines_two",nullable=true,length=50)
	public Integer getPhilippinesTwo() {
		return philippinesTwo;
	}
	public void setPhilippinesTwo(Integer philippinesTwo) {
		this.philippinesTwo = philippinesTwo;
	}
	@Column(name ="Singapore",nullable=true,length=20)
	public String getSingapore() {
		return singapore;
	}
	public void setSingapore(String singapore) {
		this.singapore = singapore;
	}
	@Column(name ="Singapore_code",nullable=true,length=50)
	public Integer getSingaporeCode() {
		return singaporeCode;
	}
	public void setSingaporeCode(Integer singaporeCode) {
		this.singaporeCode = singaporeCode;
	}
	@Column(name ="Singapore_one",nullable=true,length=50)
	public Integer getSingaporeOne() {
		return singaporeOne;
	}
	public void setSingaporeOne(Integer singaporeOne) {
		this.singaporeOne = singaporeOne;
	}
	@Column(name ="Singapore_two",nullable=true,length=50)
	public Integer getSingaporeTwo() {
		return singaporeTwo;
	}
	public void setSingaporeTwo(Integer singaporeTwo) {
		this.singaporeTwo = singaporeTwo;
	}
	@Column(name ="Thailand",nullable=true,length=20)
	public String getThailand() {
		return thailand;
	}
	public void setThailand(String thailand) {
		this.thailand = thailand;
	}
	@Column(name ="Thailand_code",nullable=true,length=50)
	public Integer getThailandCode() {
		return thailandCode;
	}
	public void setThailandCode(Integer thailandCode) {
		this.thailandCode = thailandCode;
	}
	@Column(name ="Thailand_one",nullable=true,length=50)
	public Integer getThailandOne() {
		return thailandOne;
	}
	public void setThailandOne(Integer thailandOne) {
		this.thailandOne = thailandOne;
	}
	@Column(name ="Thailand_two",nullable=true,length=50)
	public Integer getThailandTwo() {
		return thailandTwo;
	}
	public void setThailandTwo(Integer thailandTwo) {
		this.thailandTwo = thailandTwo;
	}
	@Column(name ="India",nullable=true,length=20)
	public String getIndia() {
		return india;
	}
	public void setIndia(String india) {
		this.india = india;
	}
	@Column(name ="India_code",nullable=true,length=50)
	public Integer getIndiaCode() {
		return indiaCode;
	}
	public void setIndiaCode(Integer indiaCode) {
		this.indiaCode = indiaCode;
	}
	@Column(name ="India_one",nullable=true,length=50)
	public Integer getIndiaOne() {
		return indiaOne;
	}
	public void setIndiaOne(Integer indiaOne) {
		this.indiaOne = indiaOne;
	}
	@Column(name ="India_two",nullable=true,length=50)
	public Integer getIndiaTwo() {
		return indiaTwo;
	}
	public void setIndiaTwo(Integer indiaTwo) {
		this.indiaTwo = indiaTwo;
	}
	@Column(name ="Vietnam",nullable=true,length=20)
	public String getVietnam() {
		return vietnam;
	}
	public void setVietnam(String vietnam) {
		this.vietnam = vietnam;
	}
	@Column(name ="Vietnam_code",nullable=true,length=50)
	public Integer getVietnamCode() {
		return vietnamCode;
	}
	public void setVietnamCode(Integer vietnamCode) {
		this.vietnamCode = vietnamCode;
	}
	@Column(name ="Vietnam_one",nullable=true,length=50)
	public Integer getVietnamOne() {
		return vietnamOne;
	}
	public void setVietnamOne(Integer vietnamOne) {
		this.vietnamOne = vietnamOne;
	}
	@Column(name ="Vietnam_two",nullable=true,length=50)
	public Integer getVietnamTwo() {
		return vietnamTwo;
	}
	public void setVietnamTwo(Integer vietnamTwo) {
		this.vietnamTwo = vietnamTwo;
	}
	@Column(name ="Burma",nullable=true,length=20)
	public String getBurma() {
		return burma;
	}
	public void setBurma(String burma) {
		this.burma = burma;
	}
	@Column(name ="Burma_code",nullable=true,length=50)
	public Integer getBurmaCode() {
		return burmaCode;
	}
	public void setBurmaCode(Integer burmaCode) {
		this.burmaCode = burmaCode;
	}
	@Column(name ="Burma_one",nullable=true,length=50)
	public Integer getBurmaOne() {
		return burmaOne;
	}
	public void setBurmaOne(Integer burmaOne) {
		this.burmaOne = burmaOne;
	}
	@Column(name ="Burma_two",nullable=true,length=50)
	public Integer getBurmaTwo() {
		return burmaTwo;
	}
	public void setBurmaTwo(Integer burmaTwo) {
		this.burmaTwo = burmaTwo;
	}
	@Column(name ="Asianother",nullable=true,length=20)
	public String getAsianOther() {
		return asianOther;
	}
	public void setAsianOther(String asianOther) {
		this.asianOther = asianOther;
	}
	@Column(name ="Asian_other_code",nullable=true,length=50)
	public Integer getAsianOtherCode() {
		return asianOtherCode;
	}
	public void setAsianOtherCode(Integer asianOtherCode) {
		this.asianOtherCode = asianOtherCode;
	}
	@Column(name ="Asian_other_one",nullable=true,length=50)
	public Integer getAsianOtherOne() {
		return asianOtherOne;
	}
	public void setAsianOtherOne(Integer asianOtherOne) {
		this.asianOtherOne = asianOtherOne;
	}
	@Column(name ="Asian_other_two",nullable=true,length=50)
	public Integer getAsianOtherTwo() {
		return asianOtherTwo;
	}
	public void setAsianOtherTwo(Integer asianOtherTwo) {
		this.asianOtherTwo = asianOtherTwo;
	}
	@Column(name ="Europe_total",nullable=true,length=20)
	public String getEuropeTotal() {
		return europeTotal;
	}
	public void setEuropeTotal(String europeTotal) {
		this.europeTotal = europeTotal;
	}
	@Column(name ="Europe_code",nullable=true,length=50)
	public Integer getEuropeCode() {
		return europeCode;
	}
	public void setEuropeCode(Integer europeCode) {
		this.europeCode = europeCode;
	}
	@Column(name ="Europe_one",nullable=true,length=50)
	public Integer getEuropeOne() {
		return europeOne;
	}
	public void setEuropeOne(Integer europeOne) {
		this.europeOne = europeOne;
	}
	@Column(name ="Europe_two",nullable=true,length=50)
	public Integer getEuropeTwo() {
		return europeTwo;
	}
	public void setEuropeTwo(Integer europeTwo) {
		this.europeTwo = europeTwo;
	}
	@Column(name ="France",nullable=true,length=20)
	public String getFrance() {
		return france;
	}
	public void setFrance(String france) {
		this.france = france;
	}
	@Column(name ="France_code",nullable=true,length=50)
	public Integer getFranchCode() {
		return franchCode;
	}
	public void setFranchCode(Integer franchCode) {
		this.franchCode = franchCode;
	}
	@Column(name ="France_one",nullable=true,length=50)
	public Integer getFranchOne() {
		return franchOne;
	}
	public void setFranchOne(Integer franchOne) {
		this.franchOne = franchOne;
	}
	@Column(name ="France_two",nullable=true,length=50)
	public Integer getFranchTwo() {
		return franchTwo;
	}
	public void setFranchTwo(Integer franchTwo) {
		this.franchTwo = franchTwo;
	}
	@Column(name ="Germany",nullable=true,length=20)
	public String getGermany() {
		return germany;
	}
	public void setGermany(String germany) {
		this.germany = germany;
	}
	@Column(name ="Germany_code",nullable=true,length=50)
	public Integer getGermanyCode() {
		return germanyCode;
	}
	public void setGermanyCode(Integer germanyCode) {
		this.germanyCode = germanyCode;
	}
	@Column(name ="Germany_one",nullable=true,length=50)
	public Integer getGermanyOne() {
		return germanyOne;
	}
	public void setGermanyOne(Integer germanyOne) {
		this.germanyOne = germanyOne;
	}
	@Column(name ="Germany_two",nullable=true,length=50)
	public Integer getGermanyTwo() {
		return germanyTwo;
	}
	public void setGermanyTwo(Integer germanyTwo) {
		this.germanyTwo = germanyTwo;
	}
	@Column(name ="Italy",nullable=true,length=20)
	public String getItaly() {
		return italy;
	}
	public void setItaly(String italy) {
		this.italy = italy;
	}
	@Column(name ="Italy_code",nullable=true,length=50)
	public Integer getItalyCode() {
		return italyCode;
	}
	public void setItalyCode(Integer italyCode) {
		this.italyCode = italyCode;
	}
	@Column(name ="Italy_one",nullable=true,length=50)
	public Integer getItalyOne() {
		return italyOne;
	}
	public void setItalyOne(Integer italyOne) {
		this.italyOne = italyOne;
	}
	@Column(name ="Italy_two",nullable=true,length=50)
	public Integer getItalyTwo() {
		return italyTwo;
	}
	public void setItalyTwo(Integer italyTwo) {
		this.italyTwo = italyTwo;
	}
	@Column(name ="Switzerland",nullable=true,length=20)
	public String getSwitzerLand() {
		return switzerLand;
	}
	public void setSwitzerLand(String switzerLand) {
		this.switzerLand = switzerLand;
	}
	@Column(name ="Switzerland_code",nullable=true,length=50)
	public Integer getSwitzerLandCode() {
		return switzerLandCode;
	}
	public void setSwitzerLandCode(Integer switzerLandCode) {
		this.switzerLandCode = switzerLandCode;
	}
	@Column(name ="Switzerland_one",nullable=true,length=50)
	public Integer getSwitzerLandOne() {
		return switzerLandOne;
	}
	public void setSwitzerLandOne(Integer switzerLandOne) {
		this.switzerLandOne = switzerLandOne;
	}
	@Column(name ="Switzerland_two",nullable=true,length=50)
	public Integer getSwitzerLandTwo() {
		return switzerLandTwo;
	}
	public void setSwitzerLandTwo(Integer switzerLandTwo) {
		this.switzerLandTwo = switzerLandTwo;
	}
	@Column(name ="Sweden",nullable=true,length=20)
	public String getSweden() {
		return sweden;
	}
	public void setSweden(String sweden) {
		this.sweden = sweden;
	}
	@Column(name ="Sweden_code",nullable=true,length=50)
	public Integer getSwedenCode() {
		return swedenCode;
	}
	public void setSwedenCode(Integer swedenCode) {
		this.swedenCode = swedenCode;
	}
	@Column(name ="Sweden_one",nullable=true,length=50)
	public Integer getSwedenOne() {
		return swedenOne;
	}
	public void setSwedenOne(Integer swedenOne) {
		this.swedenOne = swedenOne;
	}
	@Column(name ="Sweden_two",nullable=true,length=50)
	public Integer getSwedenTwo() {
		return swedenTwo;
	}
	public void setSwedenTwo(Integer swedenTwo) {
		this.swedenTwo = swedenTwo;
	}
	@Column(name ="Russia",nullable=true,length=20)
	public String getRussia() {
		return russia;
	}
	public void setRussia(String russia) {
		this.russia = russia;
	}
	@Column(name ="Russia_code",nullable=true,length=50)
	public Integer getRussiaCode() {
		return russiaCode;
	}
	public void setRussiaCode(Integer russiaCode) {
		this.russiaCode = russiaCode;
	}
	@Column(name ="Russia_one",nullable=true,length=50)
	public Integer getRussiaOne() {
		return russiaOne;
	}
	public void setRussiaOne(Integer russiaOne) {
		this.russiaOne = russiaOne;
	}
	@Column(name ="Russia_two",nullable=true,length=50)
	public Integer getRussiTwo() {
		return russiTwo;
	}
	public void setRussiTwo(Integer russiTwo) {
		this.russiTwo = russiTwo;
	}
	@Column(name ="Spain",nullable=true,length=20)
	public String getSpain() {
		return spain;
	}
	public void setSpain(String spain) {
		this.spain = spain;
	}
	@Column(name ="Spain_code",nullable=true,length=50)
	public Integer getSpainCode() {
		return spainCode;
	}
	public void setSpainCode(Integer spainCode) {
		this.spainCode = spainCode;
	}
	@Column(name ="Spain_one",nullable=true,length=50)
	public Integer getSpainOne() {
		return spainOne;
	}
	public void setSpainOne(Integer spainOne) {
		this.spainOne = spainOne;
	}
	
	
	
	@Column(name ="Spain_two",nullable=true,length=50)
	public Integer getSpainTwo() {
		return spainTwo;
	}
	public void setSpainTwo(Integer spainTwo) {
		this.spainTwo = spainTwo;
	}
    
//	@Column(name ="hlj_one",nullable=true,length=50)
//	public Integer getSpaIntegerwo() {
//		return spaIntegerwo;
//	}
//	public void setSpaIntegerwo(Integer spaIntegerwo) {
//		this.spaIntegerwo = spaIntegerwo;
//	}
	@Column(name ="Holland",nullable=true,length=20)
	public String getHolLand() {
		return holLand;
	}
	public void setHolLand(String holLand) {
		this.holLand = holLand;
	}
	@Column(name ="Holland_code",nullable=true,length=50)
	public Integer getHolLandCode() {
		return holLandCode;
	}
	public void setHolLandCode(Integer holLandCode) {
		this.holLandCode = holLandCode;
	}
	@Column(name ="Holland_one",nullable=true,length=50)
	public Integer getHolLandOne() {
		return holLandOne;
	}
	public void setHolLandOne(Integer holLandOne) {
		this.holLandOne = holLandOne;
	}
	@Column(name ="Holland_two",nullable=true,length=50)
	public Integer getHolLandTwo() {
		return holLandTwo;
	}
	public void setHolLandTwo(Integer holLandTwo) {
		this.holLandTwo = holLandTwo;
	}
	@Column(name ="Denmark",nullable=true,length=20)
	public String getDenMark() {
		return denMark;
	}
	public void setDenMark(String denMark) {
		this.denMark = denMark;
	}
	@Column(name ="Denmark_code",nullable=true,length=50)
	public Integer getDenMarkCode() {
		return denMarkCode;
	}
	public void setDenMarkCode(Integer denMarkCode) {
		this.denMarkCode = denMarkCode;
	}
	@Column(name ="Denmark_one",nullable=true,length=50)
	public Integer getDenMarkOne() {
		return denMarkOne;
	}
	public void setDenMarkOne(Integer denMarkOne) {
		this.denMarkOne = denMarkOne;
	}
	@Column(name ="Denmark_two",nullable=true,length=50)
	public Integer getDenMarkTwo() {
		return denMarkTwo;
	}
	public void setDenMarkTwo(Integer denMarkTwo) {
		this.denMarkTwo = denMarkTwo;
	}
	@Column(name ="Europe_other",nullable=true,length=20)
	public String getEuropeOther() {
		return europeOther;
	}
	public void setEuropeOther(String europeOther) {
		this.europeOther = europeOther;
	}
	@Column(name ="Europe_other_code",nullable=true,length=50)
	public Integer getEuropeOtherCode() {
		return europeOtherCode;
	}
	public void setEuropeOtherCode(Integer europeOtherCode) {
		this.europeOtherCode = europeOtherCode;
	}
	@Column(name ="Europe_other_one",nullable=true,length=50)
	public Integer getEuropeOtherOne() {
		return europeOtherOne;
	}
	public void setEuropeOtherOne(Integer europeOtherOne) {
		this.europeOtherOne = europeOtherOne;
	}
	@Column(name ="Europe_other_two",nullable=true,length=50)
	public Integer getEuropeOtherTwo() {
		return europeOtherTwo;
	}
	public void setEuropeOtherTwo(Integer europeOtherTwo) {
		this.europeOtherTwo = europeOtherTwo;
	}
	@Column(name ="America_total",nullable=true,length=20)
	public String getAmericaTotal() {
		return americaTotal;
	}
	public void setAmericaTotal(String americaTotal) {
		this.americaTotal = americaTotal;
	}
	@Column(name ="America_total_code",nullable=true,length=50)
	public Integer getAmericaTotalCode() {
		return americaTotalCode;
	}
	public void setAmericaTotalCode(Integer americaTotalCode) {
		this.americaTotalCode = americaTotalCode;
	}
	@Column(name ="America_total_one",nullable=true,length=50)
	public Integer getAmericaTotalOne() {
		return americaTotalOne;
	}
	public void setAmericaTotalOne(Integer americaTotalOne) {
		this.americaTotalOne = americaTotalOne;
	}
	@Column(name ="America_total_two",nullable=true,length=50)
	public Integer getAmericaTotalTwo() {
		return americaTotalTwo;
	}
	public void setAmericaTotalTwo(Integer americaTotalTwo) {
		this.americaTotalTwo = americaTotalTwo;
	}
	@Column(name ="us",nullable=true,length=20)
	public String getUs() {
		return us;
	}
	public void setUs(String us) {
		this.us = us;
	}
	@Column(name ="us_code",nullable=true,length=50)
	public Integer getUsCode() {
		return usCode;
	}
	public void setUsCode(Integer usCode) {
		this.usCode = usCode;
	}
	@Column(name ="us_one",nullable=true,length=50)
	public Integer getUsOne() {
		return usOne;
	}
	public void setUsOne(Integer usOne) {
		this.usOne = usOne;
	}
	@Column(name ="us_two",nullable=true,length=50)
	public Integer getUsTwo() {
		return usTwo;
	}
	public void setUsTwo(Integer usTwo) {
		this.usTwo = usTwo;
	}
	@Column(name ="us_other",nullable=true,length=20)
	public String getUsOther() {
		return usOther;
	}
	public void setUsOther(String usOther) {
		this.usOther = usOther;
	}
	@Column(name ="us_other_code",nullable=true,length=50)
	public Integer getUsOtherCode() {
		return usOtherCode;
	}
	public void setUsOtherCode(Integer usOtherCode) {
		this.usOtherCode = usOtherCode;
	}
	@Column(name ="us_other_one",nullable=true,length=50)
	public Integer getUsOtherOne() {
		return usOtherOne;
	}
	public void setUsOtherOne(Integer usOtherOne) {
		this.usOtherOne = usOtherOne;
	}
	@Column(name ="us_other_two",nullable=true,length=50)
	public Integer getUsOtherTwo() {
		return usOtherTwo;
	}
	public void setUsOtherTwo(Integer usOtherTwo) {
		this.usOtherTwo = usOtherTwo;
	}
	@Column(name ="Oceania_total",nullable=true,length=20)
	public String getOceaniaTotal() {
		return oceaniaTotal;
	}
	public void setOceaniaTotal(String oceaniaTotal) {
		this.oceaniaTotal = oceaniaTotal;
	}
	@Column(name ="Oceania_total_code",nullable=true,length=50)
	public Integer getOceaniaTotalCode() {
		return oceaniaTotalCode;
	}
	public void setOceaniaTotalCode(Integer oceaniaTotalCode) {
		this.oceaniaTotalCode = oceaniaTotalCode;
	}
	@Column(name ="Oceania_total_one",nullable=true,length=50)
	public Integer getOceaniaTotalOne() {
		return oceaniaTotalOne;
	}
	public void setOceaniaTotalOne(Integer oceaniaTotalOne) {
		this.oceaniaTotalOne = oceaniaTotalOne;
	}
	@Column(name ="Oceania_total_two",nullable=true,length=50)
	public Integer getOceaniaTotalTwo() {
		return oceaniaTotalTwo;
	}
	public void setOceaniaTotalTwo(Integer oceaniaTotalTwo) {
		this.oceaniaTotalTwo = oceaniaTotalTwo;
	}
	@Column(name ="Australian",nullable=true,length=20)
	public String getAustralian() {
		return australian;
	}
	public void setAustralian(String australian) {
		this.australian = australian;
	}
	@Column(name ="Australian_code",nullable=true,length=50)
	public Integer getAustralianCode() {
		return australianCode;
	}
	public void setAustralianCode(Integer australianCode) {
		this.australianCode = australianCode;
	}
	@Column(name ="Australian_one",nullable=true,length=50)
	public Integer getAustralianOne() {
		return australianOne;
	}
	public void setAustralianOne(Integer australianOne) {
		this.australianOne = australianOne;
	}
	@Column(name ="Australian_two",nullable=true,length=50)
	public Integer getAustralianTwo() {
		return australianTwo;
	}
	public void setAustralianTwo(Integer australianTwo) {
		this.australianTwo = australianTwo;  
	}
	@Column(name ="Zealand",nullable=true,length=20)
	public String getZeaLand() {
		return zeaLand;
	}
	public void setZeaLand(String zeaLand) {
		this.zeaLand = zeaLand;
	}
	@Column(name ="Zealand_code",nullable=true,length=50)
	public Integer getZeaLandCode() {
		return zeaLandCode;
	}
	public void setZeaLandCode(Integer zeaLandCode) {
		this.zeaLandCode = zeaLandCode;
	}
	@Column(name ="Zealand_one",nullable=true,length=50)
	public Integer getZeaLandOne() {
		return zeaLandOne;
	}
	public void setZeaLandOne(Integer zeaLandOne) {
		this.zeaLandOne = zeaLandOne;
	}
	@Column(name ="Zealand_two",nullable=true,length=50)
	public Integer getZeaLandTwo() {
		return zeaLandTwo;
	}
	public void setZeaLandTwo(Integer zeaLandTwo) {
		this.zeaLandTwo = zeaLandTwo;
	}
	@Column(name ="Oceania_other",nullable=true,length=20)
	public String getOceaniaOther() {
		return oceaniaOther;
	}
	public void setOceaniaOther(String oceaniaOther) {
		this.oceaniaOther = oceaniaOther;
	}
	@Column(name ="Oceania_other_code",nullable=true,length=50)
	public Integer getOceaniaOtherCode() {
		return oceaniaOtherCode;
	}
	public void setOceaniaOtherCode(Integer oceaniaOtherCode) {
		this.oceaniaOtherCode = oceaniaOtherCode;
	}
	@Column(name ="Oceania_other_one",nullable=true,length=50)
	public Integer getOceaniaOtherOne() {
		return oceaniaOtherOne;
	}
	public void setOceaniaOtherOne(Integer oceaniaOtherOne) {
		this.oceaniaOtherOne = oceaniaOtherOne;
	}
	@Column(name ="Oceania_other_two",nullable=true,length=50)
	public Integer getOceaniaOtherTwo() {
		return oceaniaOtherTwo;
	}
	public void setOceaniaOtherTwo(Integer oceaniaOtherTwo) {
		this.oceaniaOtherTwo = oceaniaOtherTwo;
	}
	@Column(name ="Africa_total",nullable=true,length=20)
	public String getAfricaTotal() {
		return africaTotal;
	}
	public void setAfricaTotal(String africaTotal) {
		this.africaTotal = africaTotal;
	}
	@Column(name ="Africa_code",nullable=true,length=50)
	public Integer getAfricaCode() {
		return africaCode;
	}
	public void setAfricaCode(Integer africaCode) {
		this.africaCode = africaCode;
	}
	@Column(name ="Africa_one",nullable=true,length=50)
	public Integer getAfricaOne() {
		return africaOne;
	}
	public void setAfricaOne(Integer africaOne) {
		this.africaOne = africaOne;
	}
	@Column(name ="Africa_two",nullable=true,length=50)
	public Integer getAfricaTwo() {
		return africaTwo;
	}
	public void setAfricaTwo(Integer africaTwo) {
		this.africaTwo = africaTwo;
	}
	@Column(name ="SouthAfrica",nullable=true,length=20)
	public String getSouthAfrica() {
		return southAfrica;
	}
	public void setSouthAfrica(String southAfrica) {
		this.southAfrica = southAfrica;
	}
	@Column(name ="SouthAfrica_code",nullable=true,length=50)
	public Integer getSouthAfricaCode() {
		return southAfricaCode;
	}
	public void setSouthAfricaCode(Integer southAfricaCode) {
		this.southAfricaCode = southAfricaCode;
	}
	@Column(name ="SouthAfrica_one",nullable=true,length=50)
	public Integer getSouthAfricaOne() {
		return southAfricaOne;
	}
	public void setSouthAfricaOne(Integer southAfricaOne) {
		this.southAfricaOne = southAfricaOne;
	}
	@Column(name ="SouthAfrica_two",nullable=true,length=50)
	public Integer getSouthAfricaTwo() {
		return southAfricaTwo;
	}
	public void setSouthAfricaTwo(Integer southAfricaTwo) {
		this.southAfricaTwo = southAfricaTwo;
	}
	@Column(name ="Egypt",nullable=true,length=20)
	public String getEgypt() {
		return egypt;
	}
	public void setEgypt(String egypt) {
		this.egypt = egypt;
	}
	@Column(name ="Egypt_code",nullable=true,length=50)
	public Integer getEgyptCode() {
		return egyptCode;
	}
	public void setEgyptCode(Integer egyptCode) {
		this.egyptCode = egyptCode;
	}
	@Column(name ="Egypt_one",nullable=true,length=50)
	public Integer getEgyptOne() {
		return egyptOne;
	}
	public void setEgyptOne(Integer egyptOne) {
		this.egyptOne = egyptOne;
	}
	@Column(name ="Egypt_two",nullable=true,length=50)
	public Integer getEgyptTwo() {
		return egyptTwo;
	}
	public void setEgyptTwo(Integer egyptTwo) {
		this.egyptTwo = egyptTwo;
	}
	@Column(name ="Kenya",nullable=true,length=20)
	public String getKenya() {
		return kenya;
	}
	public void setKenya(String kenya) {
		this.kenya = kenya;
	}
	@Column(name ="Kenya_code",nullable=true,length=50)
	public Integer getKenyaCode() {
		return kenyaCode;
	}
	public void setKenyaCode(Integer kenyaCode) {
		this.kenyaCode = kenyaCode;
	}
	@Column(name ="Kenya_one",nullable=true,length=50)
	public Integer getKenyaOne() {
		return kenyaOne;
	}
	public void setKenyaOne(Integer kenyaOne) {
		this.kenyaOne = kenyaOne;
	}
	@Column(name ="Kenya_two",nullable=true,length=50)
	public Integer getKenyaTwo() {
		return kenyaTwo;
	}
	public void setKenyaTwo(Integer kenyaTwo) {
		this.kenyaTwo = kenyaTwo;
	}
	@Column(name ="Africa_other",nullable=true,length=20)
	public String getAfricaOther() {
		return africaOther;
	}
	public void setAfricaOther(String africaOther) {
		this.africaOther = africaOther;
	}
	@Column(name ="Africa_other_code",nullable=true,length=50)
	public Integer getAfricaOtherCode() {
		return africaOtherCode;
	}
	public void setAfricaOtherCode(Integer africaOtherCode) {
		this.africaOtherCode = africaOtherCode;
	}
	@Column(name ="Africa_other_one",nullable=true,length=50)
	public Integer getAfricaOtherOne() {
		return africaOtherOne;
	}
	public void setAfricaOtherOne(Integer africaOtherOne) {
		this.africaOtherOne = africaOtherOne;
	}
	@Column(name ="Africa_other_two",nullable=true,length=50)
	public Integer getAfricaOtherTwo() {
		return africaOtherTwo;
	}
	public void setAfricaOtherTwo(Integer africaOtherTwo) {
		this.africaOtherTwo = africaOtherTwo;
	}
	@Column(name ="other_total",nullable=true,length=20)
	public String getOtherTotal() {
		return otherTotal;
	}
	public void setOtherTotal(String otherTotal) {
		this.otherTotal = otherTotal;
	}
	@Column(name ="other_total_code",nullable=true,length=50)
	public Integer getOtherTotalCode() {
		return otherTotalCode;
	}
	public void setOtherTotalCode(Integer otherTotalCode) {
		this.otherTotalCode = otherTotalCode;
	}
	@Column(name ="other_total_one",nullable=true,length=50)
	public Integer getOtherTotalOne() {
		return otherTotalOne;
	}
	public void setOtherTotalOne(Integer otherTotalOne) {
		this.otherTotalOne = otherTotalOne;
	}
	@Column(name ="other_total_two",nullable=true,length=50)
	public Integer getOtherTotalTwo() {
		return otherTotalTwo;
	}
	public void setOtherTotalTwo(Integer otherTotalTwo) {
		this.otherTotalTwo = otherTotalTwo;
	}
		
	
	@Column(name ="english",nullable=true,length=50)
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	@Column(name ="english_one",nullable=true,length=50)
	public Integer getEnglishOne() {
		return englishOne;
	}
	public void setEnglishOne(Integer englishOne) {
		this.englishOne = englishOne;
	}
	@Column(name ="english_two",nullable=true,length=50)
	public Integer getEnglishTwo() {
		return englishTwo;
	}
	public void setEnglishTwo(Integer englishTwo) {
		this.englishTwo = englishTwo;
	}

	@Column(name ="indexname",nullable=true,length=120)
	public String getIndexname() {
		return indexname;
	}
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
	@Column(name ="code",nullable=true,length=60)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name ="org_num",nullable=true,length=120)
	public Integer getOrgNum() {
		return orgNum;
	}
	public void setOrgNum(Integer orgNum) {
		this.orgNum = orgNum;
	}
	@Column(name ="org_receive_num",nullable=true,length=120)
	public Integer getOrgReceiveNum() {
		return orgReceiveNum;
	}
	public void setOrgReceiveNum(Integer orgReceiveNum) {
		this.orgReceiveNum = orgReceiveNum;
	}
	@Column(name ="one_day_num",nullable=true,length=120)
	public Integer getOneDayNum() {
		return oneDayNum;
	}
	public void setOneDayNum(Integer oneDayNum) {
		this.oneDayNum = oneDayNum;
	}
	@Column(name ="night_num",nullable=true,length=120)
	public Integer getNightNum() {
		return nightNum;
	}
	public void setNightNum(Integer nightNum) {
		this.nightNum = nightNum;
	}
	@Column(name ="night_org_num",nullable=true,length=120)
	public Integer getNightOrgNum() {
		return nightOrgNum;
	}
	public void setNightOrgNum(Integer nightOrgNum) {
		this.nightOrgNum = nightOrgNum;
	}
	@Column(name ="status",nullable=true,length=10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name = "quarter")
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="tid",insertable=false,updatable=false)
	public Traveldata getTraveldata() {
		return traveldata;
	}
	public void setTraveldata(Traveldata traveldata) {
		this.traveldata = traveldata;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	@Column(name="report_date")
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	@Column(name ="report_person",nullable=true)
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	@Column(name ="report_telephone",nullable=true)
	public String getReportTelephone() {
		return reportTelephone;
	}
	public void setReportTelephone(String reportTelephone) {
		this.reportTelephone = reportTelephone;
	}
	@Column(name="canada")
	public String getCanada() {
		return canada;
	}
	public void setCanada(String canada) {
		this.canada = canada;
	}
	@Column(name="canada_code")
	public Integer getCanadaCode() {
		return canadaCode;
	}
	public void setCanadaCode(Integer canadaCode) {
		this.canadaCode = canadaCode;
	}
	@Column(name="canada_one")
	public Integer getCanadaOne() {
		return canadaOne;
	}
	public void setCanadaOne(Integer canadaOne) {
		this.canadaOne = canadaOne;
	}
	@Column(name="canada_two")
	public Integer getCanadaTwo() {
		return canadaTwo;
	}
	@Column
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name="guo",length=1000)
	public String getGuo() {
		return guo;
	}
	public void setGuo(String guo) {
		this.guo = guo;
	}
	
	public void setCanadaTwo(Integer canadaTwo) {
		this.canadaTwo = canadaTwo;
	}
	public String getAsia() {
		return asia;
	}
	public void setAsia(String asia) {
		this.asia = asia;
	}
	public String getEurope() {
		return europe;
	}
	public void setEurope(String europe) {
		this.europe = europe;
	}
	public String getAfrica() {
		return africa;
	}
	public void setAfrica(String africa) {
		this.africa = africa;
	}
	public String getOceania() {
		return oceania;
	}
	public void setOceania(String oceania) {
		this.oceania = oceania;
	}
	public String getAmerica() {
		return america;
	}
	public void setAmerica(String america) {
		this.america = america;
	}
	
	/**
	 * 获取亚洲人次
	 * @return 亚洲人次
	 */
	public int asiaSeason(){
		int count = hongKongOne+macauOne+taiWanOne+japanOne+koreaOne+mongoliaOne+indonesiaOne+malaysiaOne+philippinesOne+singaporeOne+thailandOne+vietnamOne+burmaOne+asianOtherOne;
		return count;
	}
	/**
	 * 获取亚洲人天
	 * @return 亚洲人天
	 */
	public int asiaSeasonDay(){
		int count = hongKongTwo+macauTwo+taiWanTwo+japanTwo+koreaTwo+mongoliaTwo+indonesiaTwo+malaysiaTwo+philippinesTwo+singaporeTwo+thailandTwo+vietnamTwo+burmaTwo+asianOtherTwo;
		return count;
	}
	
	/**
	 * 获取欧洲人次
	 * @return 欧洲人次
	 */
	public int europeSeason(){
		int count = englishOne+franchOne+germanyOne+italyOne+switzerLandOne+swedenOne+russiaOne+spainOne+holLandOne+denMarkOne+europeOtherOne;
		return count;
	}
	
	/**
	 * 获取美洲人次
	 * @return 美洲人次
	 */
	public int americaSeason(){
		int count = usOne+canadaOne+usOtherOne;
		return count;
	}
	
	/**
	 * 获取大洋洲人次
	 * @return 大洋洲人次
	 */
	public int oceaniaSeason(){
		int count = australianOne+zeaLandOne+oceaniaOtherOne;
		return count;
	}
	
	/**
	 * 获取非洲人次
	 * @return 非洲人次
	 */
	public int africaSeason(){
		int count = indonesiaOne+southAfricaOne+egyptOne+kenyaOne+africaOtherOne;
		return count;
	}	
}
