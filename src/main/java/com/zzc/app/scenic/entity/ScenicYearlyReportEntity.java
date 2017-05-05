package com.zzc.app.scenic.entity;

import java.io.Serializable;

/**
 * Created by CC on 2017/1/20. 景区年报实体类
 */
public class ScenicYearlyReportEntity implements Serializable {

	private String period; // 填报周期 填报的年份
	private String totalAssets; // 资产总额
	private String construstionInvest; // 建设投资
	private String insideInvest; // 景区内部投资
	private String outsideInvest; // 景区外部投资
	private String totalIncome; // 总收入
	private String incomeWithTicket; // 门票收入
	private String incomeWithGoods; // 商品收入
	private String incomeWithFoods; // 餐饮收入
	private String incomeWithTraffic; // 交通收入
	private String incomeWithStay; // 住宿收入
	private String incomeWithShow; // 演艺收入
	private String incomeWithOthers; // 其他收入
	private String peopleCounts; // 接待人次
	private String peopleWithoutTicket; // 免票人次
	private String employee; // 就业人数
	private String employeeFixed; // 固定用工
	private String employeeTmp; // 临时用工
	private String guideCounts; // 导游人数
	private String remarks; // 备注
	private String userId;
	private String status;// 审核状态
	private String reportDate; // 报出日期
	private String appropriation;// 拨款
	private String loan;// 贷款
	private String selfCollected;// 自筹

	public String getReportDate() {
		return reportDate;
	}

	public String getAppropriation() {
		return appropriation;
	}

	public void setAppropriation(String appropriation) {
		this.appropriation = appropriation;
	}

	public String getLoan() {
		return loan;
	}

	public void setLoan(String loan) {
		this.loan = loan;
	}

	public String getSelfCollected() {
		return selfCollected;
	}

	public void setSelfCollected(String selfCollected) {
		this.selfCollected = selfCollected;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ScenicYearlyReportEntity(String period, String totalAssets,
			String construstionInvest, String insideInvest,
			String outsideInvest, String totalIncome, String incomeWithTicket,
			String incomeWithGoods, String incomeWithFoods,
			String incomeWithTraffic, String incomeWithStay,
			String incomeWithShow, String incomeWithOthers,
			String peopleCounts, String peopleWithoutTicket, String employee,
			String employeeFixed, String employeeTmp, String guideCounts,
			String remarks, String userId, String status) {
		super();
		this.period = period;
		this.totalAssets = totalAssets;
		this.construstionInvest = construstionInvest;
		this.insideInvest = insideInvest;
		this.outsideInvest = outsideInvest;
		this.totalIncome = totalIncome;
		this.incomeWithTicket = incomeWithTicket;
		this.incomeWithGoods = incomeWithGoods;
		this.incomeWithFoods = incomeWithFoods;
		this.incomeWithTraffic = incomeWithTraffic;
		this.incomeWithStay = incomeWithStay;
		this.incomeWithShow = incomeWithShow;
		this.incomeWithOthers = incomeWithOthers;
		this.peopleCounts = peopleCounts;
		this.peopleWithoutTicket = peopleWithoutTicket;
		this.employee = employee;
		this.employeeFixed = employeeFixed;
		this.employeeTmp = employeeTmp;
		this.guideCounts = guideCounts;
		this.remarks = remarks;
		this.userId = userId;
		this.status = status;
	}

	public ScenicYearlyReportEntity() {
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}

	public String getConstrustionInvest() {
		return construstionInvest;
	}

	public void setConstrustionInvest(String construstionInvest) {
		this.construstionInvest = construstionInvest;
	}

	public String getInsideInvest() {
		return insideInvest;
	}

	public void setInsideInvest(String insideInvest) {
		this.insideInvest = insideInvest;
	}

	public String getOutsideInvest() {
		return outsideInvest;
	}

	public void setOutsideInvest(String outsideInvest) {
		this.outsideInvest = outsideInvest;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getIncomeWithTicket() {
		return incomeWithTicket;
	}

	public void setIncomeWithTicket(String incomeWithTicket) {
		this.incomeWithTicket = incomeWithTicket;
	}

	public String getIncomeWithGoods() {
		return incomeWithGoods;
	}

	public void setIncomeWithGoods(String incomeWithGoods) {
		this.incomeWithGoods = incomeWithGoods;
	}

	public String getIncomeWithFoods() {
		return incomeWithFoods;
	}

	public void setIncomeWithFoods(String incomeWithFoods) {
		this.incomeWithFoods = incomeWithFoods;
	}

	public String getIncomeWithTraffic() {
		return incomeWithTraffic;
	}

	public void setIncomeWithTraffic(String incomeWithTraffic) {
		this.incomeWithTraffic = incomeWithTraffic;
	}

	public String getIncomeWithStay() {
		return incomeWithStay;
	}

	public void setIncomeWithStay(String incomeWithStay) {
		this.incomeWithStay = incomeWithStay;
	}

	public String getIncomeWithShow() {
		return incomeWithShow;
	}

	public void setIncomeWithShow(String incomeWithShow) {
		this.incomeWithShow = incomeWithShow;
	}

	public String getIncomeWithOthers() {
		return incomeWithOthers;
	}

	public void setIncomeWithOthers(String incomeWithOthers) {
		this.incomeWithOthers = incomeWithOthers;
	}

	public String getPeopleCounts() {
		return peopleCounts;
	}

	public void setPeopleCounts(String peopleCounts) {
		this.peopleCounts = peopleCounts;
	}

	public String getPeopleWithoutTicket() {
		return peopleWithoutTicket;
	}

	public void setPeopleWithoutTicket(String peopleWithoutTicket) {
		this.peopleWithoutTicket = peopleWithoutTicket;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEmployeeFixed() {
		return employeeFixed;
	}

	public void setEmployeeFixed(String employeeFixed) {
		this.employeeFixed = employeeFixed;
	}

	public String getEmployeeTmp() {
		return employeeTmp;
	}

	public void setEmployeeTmp(String employeeTmp) {
		this.employeeTmp = employeeTmp;
	}

	public String getGuideCounts() {
		return guideCounts;
	}

	public void setGuideCounts(String guideCounts) {
		this.guideCounts = guideCounts;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "ScenicYearlyReportEntity [period=" + period + ", totalAssets="
				+ totalAssets + ", construstionInvest=" + construstionInvest
				+ ", insideInvest=" + insideInvest + ", outsideInvest="
				+ outsideInvest + ", totalIncome=" + totalIncome
				+ ", incomeWithTicket=" + incomeWithTicket
				+ ", incomeWithGoods=" + incomeWithGoods + ", incomeWithFoods="
				+ incomeWithFoods + ", incomeWithTraffic=" + incomeWithTraffic
				+ ", incomeWithStay=" + incomeWithStay + ", incomeWithShow="
				+ incomeWithShow + ", incomeWithOthers=" + incomeWithOthers
				+ ", peopleCounts=" + peopleCounts + ", peopleWithoutTicket="
				+ peopleWithoutTicket + ", employee=" + employee
				+ ", employeeFixed=" + employeeFixed + ", employeeTmp="
				+ employeeTmp + ", guideCounts=" + guideCounts + ", remarks="
				+ remarks + ", userId=" + userId + "]";
	}
}
