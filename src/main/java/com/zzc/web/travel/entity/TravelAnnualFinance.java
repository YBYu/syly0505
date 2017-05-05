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

/**
 * @Title: 
 * @Description: 旅游财务年报实体类
 * @author 冯勇齐
 * @date 2016-12-2 10:31:20
 * 
 */
@Entity
@Table(name = "t_travelAgency_annual")
@SuppressWarnings("serial")
public class TravelAnnualFinance implements java.io.Serializable{
	
	
	
	
	
	private String   id;
	private String  tid;
	private String   name;
	private String   code;
	private String   person;
	private String     reportTime;
	private String   telephone;
	private String   reportPerson;
	private String year; //年报时间
	private double  debt;
	private double  inTravelReturn;
	private double  streamTotal;
	private double  manageCost;
	private double  longInvest;
	private double  taxes;                 //税金
	private double  fixedAssets;              //固定资产小计
	private double  travelExpense;
	private double  fixedPrice;
	private double  financialCost;
	private double  depreciation;
	private double  interestCost;
	private double  yearDepreciation;
	private double  sellingExpense;            //销售费用
	private double  assetTotal;
	private double  valueVariation;
	private double  liabilitiesTotal;
	private double  operatingIncome;             //营业利润
	private double  possessor;
	private double  gross;
	private double  realIncome;
	private double  invest;                     //投资收益
	private double  gainloss;                   //增益损失
	private double  totalReturn;                  //利润总额
	private double  businessIncome;
	private double  incomeTax;                    //所得税
	private double  intoIncome;
	private double  salary;                      //工资福利费
	private double  outIncome;
	private double  employeeSalary;
	private double  inTravelIncome;
	private double  addTex;
	private double  extraGains;
	private Integer  numAverage;
	private double  governmentSubsidies;
	private Integer  college;
	private double  cost;
	private Integer  tourNum;
	private double  businessExpenses;
	private Integer  leadGroup;
	private double  businessTexAdd;
	private Integer  groupOrg;
	private double  mainOperation;
	private Integer  storeNum;
	private double  profitInTravel;
	private Integer  branchOffice;
	private double  outTravelBusiness;
	private Integer  subsidiary;                               //子公司数量
	private String   cityStatus;
	private String   countyStatus;
	private String   provinceStatus;
	private String   status;
	private Traveldata traveldata;
	
	// 是否执行2006年《企业会计准则》
	private String whetherPerform;
	// 由执行2006年《企业会计准则》企业填报：资产减值损失
	private String loss;
	
	// 分数
	private Integer score;
	// 国家系统审核意见
	private String guo;
	
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
//	@Column(name ="tid",nullable=true,length=50)
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	@Column(name ="name",nullable=true,length=60)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name ="code",nullable=true,length=60)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name ="person",nullable=true,length=60)
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	@Column(name ="report_time",nullable=true)
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	@Column(name ="telephone",nullable=true,length=60)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name ="report_person",nullable=true,length=60)
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	@Column(name ="debt",nullable=true)
	public double getDebt() {
		return debt;
	}
	public void setDebt(double debt) {
		this.debt = debt;
	}
	@Column(name ="in_travel_return",nullable=true)
	public double getInTravelReturn() {
		return inTravelReturn;
	}
	public void setInTravelReturn(double inTravelReturn) {
		this.inTravelReturn = inTravelReturn;
	}
	@Column(name ="stream_total",nullable=true)
	public double getStreamTotal() {
		return streamTotal;
	}
	public void setStreamTotal(double streamTotal) {
		this.streamTotal = streamTotal;
	}
	@Column(name ="manage_cost",nullable=true)
	public double getManageCost() {
		return manageCost;
	}
	public void setManageCost(double manageCost) {
		this.manageCost = manageCost;
	}
	@Column(name ="long_invest",nullable=true)
	public double getLongInvest() {
		return longInvest;
	}
	public void setLongInvest(double longInvest) {
		this.longInvest = longInvest;
	}
	@Column(name ="taxes",nullable=true)
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	@Column(name ="fixed_assets",nullable=true)
	public double getFixedAssets() {
		return fixedAssets;
	}
	public void setFixedAssets(double fixedAssets) {
		this.fixedAssets = fixedAssets;
	}
	@Column(name ="travel_expense",nullable=true)
	public double getTravelExpense() {
		return travelExpense;
	}
	public void setTravelExpense(double travelExpense) {
		this.travelExpense = travelExpense;
	}
	@Column(name ="fixed_price",nullable=true)
	public double getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	@Column(name ="financial_cost",nullable=true)
	public double getFinancialCost() {
		return financialCost;
	}
	public void setFinancialCost(double financialCost) {
		this.financialCost = financialCost;
	}
	@Column(name ="depreciation",nullable=true)
	public double getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(double depreciation) {
		this.depreciation = depreciation;
	}
	@Column(name ="interest_cost",nullable=true)
	public double getInterestCost() {
		return interestCost;
	}
	public void setInterestCost(double interestCost) {
		this.interestCost = interestCost;
	}
	@Column(name ="year_depreciation",nullable=true)
	public double getYearDepreciation() {
		return yearDepreciation;
	}
	public void setYearDepreciation(double yearDepreciation) {
		this.yearDepreciation = yearDepreciation;
	}
	@Column(name ="selling_expense",nullable=true)	
	public double getSellingExpense() {
		return sellingExpense;
	}
	public void setSellingExpense(double sellingExpense) {
		this.sellingExpense = sellingExpense;
	}
	@Column(name ="asset_total",nullable=true)
	public double getAssetTotal() {
		return assetTotal;
	}
	public void setAssetTotal(double assetTotal) {
		this.assetTotal = assetTotal;
	}
	@Column(name ="value_variation",nullable=true)
	public double getValueVariation() {
		return valueVariation;
	}
	public void setValueVariation(double valueVariation) {
		this.valueVariation = valueVariation;
	}
	@Column(name ="liabilities_total",nullable=true)
	public double getLiabilitiesTotal() {
		return liabilitiesTotal;
	}
	public void setLiabilitiesTotal(double liabilitiesTotal) {
		this.liabilitiesTotal = liabilitiesTotal;
	}
	@Column(name ="operating_income",nullable=true)
	public double getOperatingIncome() {
		return operatingIncome;
	}
	public void setOperatingIncome(double operatingIncome) {
		this.operatingIncome = operatingIncome;
	}
	@Column(name ="possessor",nullable=true)
	public double getPossessor() {
		return possessor;
	}
	public void setPossessor(double possessor) {
		this.possessor = possessor;
	}
	@Column(name ="gross",nullable=true)
	public double getGross() {
		return gross;
	}
	public void setGross(double gross) {
		this.gross = gross;
	}
	@Column(name ="real_income",nullable=true)
	public double getRealIncome() {
		return realIncome;
	}
	public void setRealIncome(double realIncome) {
		this.realIncome = realIncome;
	}
	@Column(name ="invest",nullable=true)
	public double getInvest() {
		return invest;
	}
	public void setInvest(double invest) {
		this.invest = invest;
	}
	@Column(name ="gainloss",nullable=true)
	public double getGainloss() {
		return gainloss;
	}
	public void setGainloss(double gainloss) {
		this.gainloss = gainloss;
	}
	@Column(name ="total_return",nullable=true)
	public double getTotalReturn() {
		return totalReturn;
	}
	public void setTotalReturn(double totalReturn) {
		this.totalReturn = totalReturn;
	}
	@Column(name ="business_income",nullable=true)
	public double getBusinessIncome() {
		return businessIncome;
	}
	public void setBusinessIncome(double businessIncome) {
		this.businessIncome = businessIncome;
	}
	@Column(name ="income_tax",nullable=true)
	public double getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}
	@Column(name ="into_income",nullable=true)
	public double getIntoIncome() {
		return intoIncome;
	}
	public void setIntoIncome(double intoIncome) {
		this.intoIncome = intoIncome;
	}
	@Column(name ="salary",nullable=true)
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Column(name ="out_income",nullable=true)
	public double getOutIncome() {
		return outIncome;
	}
	public void setOutIncome(double outIncome) {
		this.outIncome = outIncome;
	}
	@Column(name ="employee_salary",nullable=true)
	public double getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	@Column(name ="in_travel_income",nullable=true)
	public double getInTravelIncome() {
		return inTravelIncome;
	}
	public void setInTravelIncome(double inTravelIncome) {
		this.inTravelIncome = inTravelIncome;
	}
	@Column(name ="add_tex",nullable=true)
	public double getAddTex() {
		return addTex;
	}
	public void setAddTex(double addTex) {
		this.addTex = addTex;
	}
	@Column(name ="extra_gains",nullable=true)
	public double getExtraGains() {
		return extraGains;
	}
	public void setExtraGains(double extraGains) {
		this.extraGains = extraGains;
	}
	@Column(name ="num_average",nullable=true)
	public Integer getNumAverage() {
		return numAverage;
	}
	public void setNumAverage(Integer numAverage) {
		this.numAverage = numAverage;
	}
	@Column(name ="government_subsidies",nullable=true)
	public double getGovernmentSubsidies() {
		return governmentSubsidies;
	}
	public void setGovernmentSubsidies(double governmentSubsidies) {
		this.governmentSubsidies = governmentSubsidies;
	}
	@Column(name ="college",nullable=true)
	public Integer getCollege() {
		return college;
	}
	public void setCollege(Integer college) {
		this.college = college;
	}
	@Column(name ="cost",nullable=true)
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Column(name ="tour_num",nullable=true)
	public Integer getTourNum() {
		return tourNum;
	}
	public void setTourNum(Integer tourNum) {
		this.tourNum = tourNum;
	}
	@Column(name ="business_expenses",nullable=true)
	public double getBusinessExpenses() {
		return businessExpenses;
	}
	public void setBusinessExpenses(double businessExpenses) {
		this.businessExpenses = businessExpenses;
	}
	@Column(name ="lead_group",nullable=true)
	public Integer getLeadGroup() {
		return leadGroup;
	}
	public void setLeadGroup(Integer leadGroup) {
		this.leadGroup = leadGroup;
	}
	@Column(name ="business_tex_add",nullable=true)
	public double getBusinessTexAdd() {
		return businessTexAdd;
	}
	public void setBusinessTexAdd(double businessTexAdd) {
		this.businessTexAdd = businessTexAdd;
	}
	@Column(name ="group_org",nullable=true)
	public Integer getGroupOrg() {
		return groupOrg;
	}
	public void setGroupOrg(Integer groupOrg) {
		this.groupOrg = groupOrg;
	}
	@Column(name ="main_operation",nullable=true)
	public double getMainOperation() {
		return mainOperation;
	}
	public void setMainOperation(double mainOperation) {
		this.mainOperation = mainOperation;
	}
	@Column(name ="store_num",nullable=true)
	public Integer getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}
	@Column(name ="profit_in_travel",nullable=true)
	public double getProfitInTravel() {
		return profitInTravel;
	}
	public void setProfitInTravel(double profitInTravel) {
		this.profitInTravel = profitInTravel;
	}
	@Column(name ="branch_office",nullable=true)
	public Integer getBranchOffice() {
		return branchOffice;
	}
	public void setBranchOffice(Integer branchOffice) {
		this.branchOffice = branchOffice;
	}
	@Column(name ="out_travel_business",nullable=true)
	public double getOutTravelBusiness() {
		return outTravelBusiness;
	}
	public void setOutTravelBusiness(double outTravelBusiness) {
		this.outTravelBusiness = outTravelBusiness;
	}
	@Column(name ="subsidiary",nullable=true)
	public Integer getSubsidiary() {
		return subsidiary;
	} 
	public void setSubsidiary(Integer subsidiary) {
		this.subsidiary = subsidiary;
	}
	
	@Column(name ="city_status",nullable=true , length=12)
	public String getCityStatus() {
		return cityStatus;
	}
	public void setCityStatus(String cityStatus) {
		this.cityStatus = cityStatus;
	}
	@Column(name ="county_status",nullable=true , length=12)
	public String getCountyStatus() {
		return countyStatus;
	}
	public void setCountyStatus(String countyStatus) {
		this.countyStatus = countyStatus;
	}
	@Column(name ="province_status",nullable=true , length=12)
	public String getProvinceStatus() {
		return provinceStatus;  
	}
	public void setProvinceStatus(String provinceStatus) {
		this.provinceStatus = provinceStatus;
	}
	@Column(name ="status",nullable=true , length=12)
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="tid",insertable=false,updatable=false)
	public Traveldata getTraveldata() {
		return traveldata;
	}
	public void setTraveldata(Traveldata traveldata) {
		this.traveldata = traveldata;
	}
	
	@Column(name="whether_perform")
	public String getWhetherPerform() {
		return whetherPerform;
	}
	public void setWhetherPerform(String whetherPerform) {
		this.whetherPerform = whetherPerform;
	}
	@Column(name="loss")
	public String getLoss() {
		return loss;
	}
	public void setLoss(String loss) {
		this.loss = loss;
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

}
