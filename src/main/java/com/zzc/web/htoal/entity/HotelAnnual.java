package com.zzc.web.htoal.entity;

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

import com.zzc.core.common.entity.IdEntity;
import com.zzc.poi.excel.annotation.Excel;
import com.zzc.poi.excel.annotation.ExcelTarget;
/**
 * 
 *                  
 * @date: 2016年12月3日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelAnnual.java
 * @Version: 1.0
 * @About: 
 *酒店年报实体类
 */

@Entity
@Table(name = "t_hotel_annual")
@SuppressWarnings("serial")
@ExcelTarget("hotelAnnual")
public class HotelAnnual   implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String  id;
	private String  hid;//酒店ID
	@Excel(name="单位名称",orderNum="2")
	private String   name;//单位名称
	@Excel(name="单位名称",orderNum="3")
	private String   code;//酒店编码  标牌编号
	@Excel(name="年报年份",orderNum="1")
	private String year;//年报年份
	private String   person;//单位负责人
	private String   reportTime;//年报时间
	private String   telephone;//电话
	private String   reportPerson;//填表人
	@Excel(name="年末资产负债",orderNum="4")
	private double  debt;//年末资产负债
	@Excel(name="实际出租间夜数",orderNum="5")
	private int  realNights;//实际出租间夜数
	@Excel(name="可提供出租夜数",orderNum="6")
	private int  canNights;//可提供出租夜数
	@Excel(name="流动资产小计",orderNum="7")
	private double  streamTotal;//流动资产小计
	@Excel(name="管理费用",orderNum="8")
	private double  manageCost;//管理费用
	@Excel(name="管理费用",orderNum="9")
	private double  longInvest;//长期投资
	@Excel(name="税金",orderNum="10")
	private double  taxes;//税金
	@Excel(name="固定资产",orderNum="11")
	private double  fixedAssets;//固定资产
	@Excel(name="差旅费",orderNum="12")
	private double  travelExpense;//差旅费
	@Excel(name="固定资产原价",orderNum="13")
	private double  fixedPrice;//固定资产原价
	@Excel(name="财务费用",orderNum="14")
	private double  financialCost;//财务费用
	private double  depreciation;//累计折旧
	private double  interestCost;//利息支出
	private double  yearDepreciation;//本年折旧
	//private double  sellingExpense;//
	private double  assetTotal;//资产合计
	private double  valueVariation;//公允价值变动收益
	private double  liabilitiesTotal;//负债合计
	private double  operationIncome;//营业收入
	private double  possessorTotal;//所有者权益合计
	private double  oprFeeAndTax;//营业税金及附加	
	//private double  gross;//毛利润
	private double  realIncome;//实收资本
	private double  invest;//投资收益
	private double roomIncome;
	private double canteeIncome;
	private double otherIncome;
	private double  operationCost;//营业成本
	private double  operationFee;//营业费用
	private double fianceCost;//财务费用
	private double opreationIntrest;//营业利润
	private double ownTax;//所得税
	private double profitTotal;//利润总额
	private double salary;//本年应付职工薪酬
	private int college;//大专以上学历人数
	private int people;//全部从业人员平均人数
	private String status;//状态
	private Date auditTime;//审核时间
	private Hotelmanage hotelmanage;
	
	private String hotelAid;
	
	// 是否是撤回的数据；0-不是，1-是
	private String revocated;
	
	// 分数
	private java.lang.Integer score;
	
	// 缺失字段补全
	// 填报人
	private String filler;
	// 填报人电话
	private String fillerTel;
	// 存货
	private double deposit;
	// 国家资本
	private String nationalCapital;
	// 集体资本
	private String collectiveCapital;
	// 个人资本
	private String personalCapital;
	// 法人资本
	private String corporateCapital;
	// 港澳台资本
	private String HMTCapital;
	// 外商资本
	private String foreignerCapital;
	// 主营利润
	private String businessProfit;
	// 管理费用
	private String managementFee;
	// 应付工资
	private String annualPayTotal;
	// 客房数
	private String room;
	// 床位数
	private String bed;
	// 实际出租夜数
	private String actualRent;
	// 可供出租夜数
	private String forHire;
	// 审核时间
	private String shenHeDateTime;
	// 省
	private String sheng;
	// 市
	private String shi;
	// 县
	private String xian;
	
	private String countryState;
	
	// 区级审核状态
	private String districtStatus;
	// 国家系统审核意见
	private String guo;
	
	// 销售费用
	private double sellFee;
	// 本年应交增值税
	private double addedValueTax;
	// 营业外收入
	private double extraneousIncome;
	// 政府补贴
	private double subsidies;
	// 能耗成本
	private double consumption;
	
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_aid")
	public Hotelmanage getHotelmanage() {
		return hotelmanage;
	}
	public void setHotelmanage(Hotelmanage hotelmanage) {
		this.hotelmanage = hotelmanage;
	}
	@Column(name="hotel_aid",insertable=false,updatable=false)
	public String getHotelAid() {
		return hotelAid;
	}
	public void setHotelAid(String hotelAid) {
		this.hotelAid = hotelAid;
	}
	@Column(name="hid")
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="person")
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	@Column(name="reportTime")
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	@Column(name="telephone")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name="reportPerson")
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	
	@Column(name="canNights")
	public int getCanNights() {
		return canNights;
	}
	public void setCanNights(int canNights) {
		this.canNights = canNights;
	}
	@Column(name="debt")
	public double getDebt() {
		return debt;
	}
	public void setDebt(double debt) {
		this.debt = debt;
	}
	@Column(name="realNights")
	public int getRealNights() {
		return realNights;
	}
	
	public void setRealNights(int realNights) {
		this.realNights = realNights;
	}
	@Column(name="possessorTotal")
	public double getPossessorTotal() {
		return possessorTotal;
	}
	public void setPossessorTotal(double possessorTotal) {
		this.possessorTotal = possessorTotal;
	}
	@Column(name="realIncome")
	public double getRealIncome() {
		return realIncome;
	}
	public void setRealIncome(double realIncome) {
		this.realIncome = realIncome;
	}
	@Column(name="streamTotal")
	public double getStreamTotal() {
		return streamTotal;
	}
	public void setStreamTotal(double streamTotal) {
		this.streamTotal = streamTotal;
	}
	@Column(name="manageCost")
	public double getManageCost() {
		return manageCost;
	}
	public void setManageCost(double manageCost) {
		this.manageCost = manageCost;
	}
	@Column(name="longInvest")
	public double getLongInvest() {
		return longInvest;
	}
	public void setLongInvest(double longInvest) {
		this.longInvest = longInvest;
	}
	@Column(name="taxes")
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	@Column(name="oprFeeAndTax")
	public double getOprFeeAndTax() {
		return oprFeeAndTax;
	}
	public void setOprFeeAndTax(double oprFeeAndTax) {
		this.oprFeeAndTax = oprFeeAndTax;
	}
	@Column(name="yearDepreciation")
	public double getYearDepreciation() {
		return yearDepreciation;
	}
	public void setYearDepreciation(double yearDepreciation) {
		this.yearDepreciation = yearDepreciation;
	}
	@Column(name="fixedAssets")
	public double getFixedAssets() {
		return fixedAssets;
	}
	public void setFixedAssets(double fixedAssets) {
		this.fixedAssets = fixedAssets;
	}
	@Column(name="travelExpense")
	public double getTravelExpense() {
		return travelExpense;
	}
	public void setTravelExpense(double travelExpense) {
		this.travelExpense = travelExpense;
	}
	@Column(name="fixedPrice")
	public double getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	@Column(name="financialCost")
	public double getFinancialCost() {
		return financialCost;
	}
	public void setFinancialCost(double financialCost) {
		this.financialCost = financialCost;
	}
	@Column(name="depreciation")
	public double getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(double depreciation) {
		this.depreciation = depreciation;
	}
	@Column(name="interestCost")
	public double getInterestCost() {
		return interestCost;
	}
	public void setInterestCost(double interestCost) {
		this.interestCost = interestCost;
	}
	@Column(name="assetTotal")
	public double getAssetTotal() {
		return assetTotal;
	}
	
	public void setAssetTotal(double assetTotal) {
		this.assetTotal = assetTotal;
	}
	@Column(name="valueVariation")
	public double getValueVariation() {
		return valueVariation;
	}
	public void setValueVariation(double valueVariation) {
		this.valueVariation = valueVariation;
	}
	@Column(name="liabilitiesTotal")
	public double getLiabilitiesTotal() {
		return liabilitiesTotal;
	}
	public void setLiabilitiesTotal(double liabilitiesTotal) {
		this.liabilitiesTotal = liabilitiesTotal;
	}
	@Column(name="operationIncome")
	public double getOperationIncome() {
		return operationIncome;
	}
	public void setOperationIncome(double operationIncome) {
		this.operationIncome = operationIncome;
	}
	@Column(name="invest")
	public double getInvest() {
		return invest;
	}
	public void setInvest(double invest) {
		this.invest = invest;
	}
	@Column(name="roomIncome")
	public double getRoomIncome() {
		return roomIncome;
	}
	public void setRoomIncome(double roomIncome) {
		this.roomIncome = roomIncome;
	}
	@Column(name="canteeIncome")
	public double getCanteeIncome() {
		return canteeIncome;
	}
	public void setCanteeIncome(double canteeIncome) {
		this.canteeIncome = canteeIncome;
	}
	@Column(name="otherIncome")
	public double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(double otherIncome) {
		this.otherIncome = otherIncome;
	}
	@Column(name="operationCost")
	public double getOperationCost() {
		return operationCost;
	}
	public void setOperationCost(double operationCost) {
		this.operationCost = operationCost;
	}
	@Column(name="operationFee")
	public double getOperationFee() {
		return operationFee;
	}
	public void setOperationFee(double operationFee) {
		this.operationFee = operationFee;
	}
	@Column(name="fianceCost")
	public double getFianceCost() {
		return fianceCost;
	}
	public void setFianceCost(double fianceCost) {
		this.fianceCost = fianceCost;
	}
	@Column(name="opreationIntrest")
	public double getOpreationIntrest() {
		return opreationIntrest;
	}
	public void setOpreationIntrest(double opreationIntrest) {
		this.opreationIntrest = opreationIntrest;
	}
	@Column(name="ownTax")
	public double getOwnTax() {
		return ownTax;
	}
	public void setOwnTax(double ownTax) {
		this.ownTax = ownTax;
	}
	@Column(name="profitTotal")
	public double getProfitTotal() {
		return profitTotal;
	}
	
	public void setProfitTotal(double profitTotal) {
		this.profitTotal = profitTotal;
	}
	@Column(name="salary")
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Column(name="college")
	public int getCollege() {
		return college;
	}
	
	public void setCollege(int college) {
		this.college = college;
	}
	@Column(name="people")
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="auditTime")
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	// 缺失字段补全
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
	@Column(name="deposit")
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	@Column(name="national_capital")
	public String getNationalCapital() {
		return nationalCapital;
	}
	public void setNationalCapital(String nationalCapital) {
		this.nationalCapital = nationalCapital;
	}
	@Column(name="collective_capital")
	public String getCollectiveCapital() {
		return collectiveCapital;
	}
	public void setCollectiveCapital(String collectiveCapital) {
		this.collectiveCapital = collectiveCapital;
	}
	@Column(name="personal_capital")
	public String getPersonalCapital() {
		return personalCapital;
	}
	public void setPersonalCapital(String personalCapital) {
		this.personalCapital = personalCapital;
	}
	@Column(name="corporate_capital")
	public String getCorporateCapital() {
		return corporateCapital;
	}
	public void setCorporateCapital(String corporateCapital) {
		this.corporateCapital = corporateCapital;
	}
	@Column(name="hmt_capital")
	public String getHMTCapital() {
		return HMTCapital;
	}
	public void setHMTCapital(String hMTCapital) {
		HMTCapital = hMTCapital;
	}
	@Column(name="foreigner_capital")
	public String getForeignerCapital() {
		return foreignerCapital;
	}
	public void setForeignerCapital(String foreignerCapital) {
		this.foreignerCapital = foreignerCapital;
	}
	@Column(name="business_profit")
	public String getBusinessProfit() {
		return businessProfit;
	}
	public void setBusinessProfit(String businessProfit) {
		this.businessProfit = businessProfit;
	}
	@Column(name="management_fee")
	public String getManagementFee() {
		return managementFee;
	}
	public void setManagementFee(String managementFee) {
		this.managementFee = managementFee;
	}
	@Column(name="annual_paytotal")
	public String getAnnualPayTotal() {
		return annualPayTotal;
	}
	public void setAnnualPayTotal(String annualPayTotal) {
		this.annualPayTotal = annualPayTotal;
	}
	@Column
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	@Column
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	@Column(name="actual_rent")
	public String getActualRent() {
		return actualRent;
	}
	public void setActualRent(String actualRent) {
		this.actualRent = actualRent;
	}
	@Column(name="for_hire")
	public String getForHire() {
		return forHire;
	}
	public void setForHire(String forHire) {
		this.forHire = forHire;
	}
	@Column(name="shenhe_datetime")
	public String getShenHeDateTime() {
		return shenHeDateTime;
	}
	public void setShenHeDateTime(String shenHeDateTime) {
		this.shenHeDateTime = shenHeDateTime;
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
	public java.lang.Integer getScore() {
		return score;
	}
	public void setScore(java.lang.Integer score) {
		this.score = score;
	}
	@Column(name="revocated",length=2)
	public String getRevocated() {
		return revocated;
	}
	public void setRevocated(String revocated) {
		this.revocated = revocated;
	}
	@Column(name="guo",length=1000)
	public String getGuo() {
		return guo;
	}
	public void setGuo(String guo) {
		this.guo = guo;
	}
	@Column(name="sell_fee")
	public double getSellFee() {
		return sellFee;
	}
	public void setSellFee(double sellFee) {
		this.sellFee = sellFee;
	}
	@Column(name="added_value_tax")
	public double getAddedValueTax() {
		return addedValueTax;
	}
	public void setAddedValueTax(double addedValueTax) {
		this.addedValueTax = addedValueTax;
	}
	@Column(name="extraneous_income")
	public double getExtraneousIncome() {
		return extraneousIncome;
	}
	public void setExtraneousIncome(double extraneousIncome) {
		this.extraneousIncome = extraneousIncome;
	}
	@Column(name="subsidies")
	public double getSubsidies() {
		return subsidies;
	}
	public void setSubsidies(double subsidies) {
		this.subsidies = subsidies;
	}
	@Column(name="consumption")
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
}
