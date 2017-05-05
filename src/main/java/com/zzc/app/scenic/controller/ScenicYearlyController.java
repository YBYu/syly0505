package com.zzc.app.scenic.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zzc.app.scenic.entity.ScenicYearlyReportEntity;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/appscenicannualController")
public class ScenicYearlyController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 是否有本年的年报数据
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "ifhaveannualdata", method = RequestMethod.GET)
	@ResponseBody
	public String ifhaveannualdata(String userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		String repsonse = null;
		if (scenicDatalist.size() > 0) {
			ScenicData scenicData = scenicDatalist.get(0);
			String uid = scenicData.getId();
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR) - 1;
			// 获取上一年的年报
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"select * from t_scenicspot_annual tsm where tsm.annual_id=? and tsm.year=? ",
							uid, year);

			if (list.size() > 0) {
				ScenicSpotAnnual scenicSpotAnnual = systemService.get(
						ScenicSpotAnnual.class, (String) list.get(0).get("id"));
				ScenicYearlyReportEntity scenicYearlyReportEntity = new ScenicYearlyReportEntity();
				scenicYearlyReportEntity.setPeriod(scenicSpotAnnual.getYear());
				scenicYearlyReportEntity.setTotalAssets(String
						.valueOf(scenicSpotAnnual.getAssetstotal()));
				scenicYearlyReportEntity.setConstrustionInvest(String
						.valueOf(scenicSpotAnnual.getBuildinvest()));
				scenicYearlyReportEntity.setAppropriation(String
						.valueOf(scenicSpotAnnual.getAppropriation()));
				scenicYearlyReportEntity.setLoan(String
						.valueOf(scenicSpotAnnual.getLoan()));
				scenicYearlyReportEntity.setSelfCollected(String
						.valueOf(scenicSpotAnnual.getFundsself()));
				scenicYearlyReportEntity.setInsideInvest(String
						.valueOf(scenicSpotAnnual.getInbuild()));
				scenicYearlyReportEntity.setOutsideInvest(String
						.valueOf(scenicSpotAnnual.getOutbuild()));
				scenicYearlyReportEntity.setTotalIncome(String
						.valueOf(scenicSpotAnnual.getTotalincome()));
				scenicYearlyReportEntity.setIncomeWithTicket(String
						.valueOf(scenicSpotAnnual.getTicketincome()));
				scenicYearlyReportEntity.setIncomeWithGoods(String
						.valueOf(scenicSpotAnnual.getShopincome()));
				scenicYearlyReportEntity.setIncomeWithFoods(String
						.valueOf(scenicSpotAnnual.getFoodincome()));
				scenicYearlyReportEntity.setIncomeWithTraffic(String
						.valueOf(scenicSpotAnnual.getTrafficincome()));
				scenicYearlyReportEntity.setIncomeWithStay(String
						.valueOf(scenicSpotAnnual.getPutUpincome()));
				scenicYearlyReportEntity.setIncomeWithShow(String
						.valueOf(scenicSpotAnnual.getShowincome()));
				scenicYearlyReportEntity.setIncomeWithOthers(String
						.valueOf(scenicSpotAnnual.getOtherincome()));
				scenicYearlyReportEntity.setPeopleCounts(String
						.valueOf(scenicSpotAnnual.getReceptionnum()));
				scenicYearlyReportEntity.setPeopleWithoutTicket(String
						.valueOf(scenicSpotAnnual.getExemptTicketnum()));
				scenicYearlyReportEntity.setEmployee(String
						.valueOf(scenicSpotAnnual.getJobnum()));
				scenicYearlyReportEntity.setEmployeeFixed(String
						.valueOf(scenicSpotAnnual.getFixedworker()));
				scenicYearlyReportEntity.setEmployeeTmp(String
						.valueOf(scenicSpotAnnual.getTempworker()));
				scenicYearlyReportEntity.setGuideCounts(String
						.valueOf(scenicSpotAnnual.getGuidenum()));
				scenicYearlyReportEntity.setRemarks(scenicSpotAnnual
						.getRemarks());
				String status = ScenicWeekController.getstatus(scenicSpotAnnual
						.getStatus());
				scenicYearlyReportEntity.setStatus(status);
				scenicYearlyReportEntity.setUserId(userId);
				scenicYearlyReportEntity.setReportDate(scenicSpotAnnual
						.getCreatTime() == null ? "" : sdf
						.format(scenicSpotAnnual.getCreatTime()));
				String node = "|" + year + "|";
				repsonse = JSON.toJSONString(scenicYearlyReportEntity) + node;

			} else {
				repsonse = "|" + year + "|";
			}

		}
		return repsonse;
	}

	/**
	 * 年报提交
	 * 
	 * @param json
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(params = "annualdata", method = RequestMethod.POST)
	@ResponseBody
	public String editannualdata(HttpServletRequest request)
			throws ParseException {
		String json = request.getParameter("param");
		String flag = request.getParameter("flag");
		JSONObject node = JSONObject.fromObject(json);
		String repsonse = "error";
		ScenicSpotAnnual scenicSpotAnnual = new ScenicSpotAnnual();
		String userId = node.getString("userId");
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		if (scenicDatalist.size() > 0) {
			ScenicData scenicData = scenicDatalist.get(0);
			scenicSpotAnnual.setScenicData(scenicData);
			// 判断上一年年报是否填写
			String uid = scenicData.getId();
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR) - 1;
			// 获取上一年的年报
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"select * from t_scenicspot_annual tsm where tsm.annual_id=? and tsm.year=? ",
							uid, year);

			if (list.size() > 0) {
				scenicSpotAnnual = systemService.get(ScenicSpotAnnual.class,
						(String) list.get(0).get("id"));
			}

			scenicSpotAnnual.setYear(node.getString("period"));
			scenicSpotAnnual.setAssetstotal(node.getString("totalAssets")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("totalAssets")));
			scenicSpotAnnual.setBuildinvest(node
					.getString("construstionInvest").equals("") ? 0.0 : Double
					.valueOf(node.getString("construstionInvest")));
			scenicSpotAnnual.setAppropriation(node.getString("appropriation")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("appropriation")));
			scenicSpotAnnual.setLoan(node.getString("loan").equals("") ? 0.0
					: Double.valueOf(node.getString("loan")));
			scenicSpotAnnual.setFundsself(node.getString("selfCollected")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("selfCollected")));
			scenicSpotAnnual.setInbuild(node.getString("insideInvest").equals(
					"") ? 0.0 : Double.valueOf(node.getString("insideInvest")));
			scenicSpotAnnual.setOutbuild(node.getString("outsideInvest")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("outsideInvest")));
			scenicSpotAnnual.setTotalincome(node.getString("totalIncome")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("totalIncome")));
			scenicSpotAnnual.setTicketincome(node.getString("incomeWithTicket")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("incomeWithTicket")));
			scenicSpotAnnual.setShopincome(node.getString("incomeWithGoods")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("incomeWithGoods")));
			scenicSpotAnnual.setFoodincome(node.getString("incomeWithFoods")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("incomeWithFoods")));
			scenicSpotAnnual.setTrafficincome(node.getString(
					"incomeWithTraffic").equals("") ? 0.0 : Double.valueOf(node
					.getString("incomeWithTraffic")));
			scenicSpotAnnual.setPutUpincome(node.getString("incomeWithStay")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("incomeWithStay")));
			scenicSpotAnnual.setShowincome(node.getString("incomeWithShow")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("incomeWithShow")));
			scenicSpotAnnual.setOtherincome(node.getString("incomeWithOthers")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("incomeWithOthers")));
			scenicSpotAnnual.setReceptionnum(node.getString("peopleCounts")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("peopleCounts")));
			scenicSpotAnnual.setExemptTicketnum(node.getString(
					"peopleWithoutTicket").equals("") ? 0.0 : Double
					.valueOf(node.getString("peopleWithoutTicket")));
			scenicSpotAnnual
					.setJobnum(node.getString("employee").equals("") ? 0
							: Integer.valueOf(node.getString("employee")));
			scenicSpotAnnual.setFixedworker(node.getString("employeeFixed")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("employeeFixed")));
			scenicSpotAnnual.setTempworker(node.getString("employeeTmp")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("employeeTmp")));
			scenicSpotAnnual.setGuidenum(node.getString("guideCounts").equals(
					"") ? 0 : Integer.valueOf(node.getString("guideCounts")));
			scenicSpotAnnual.setRemarks(node.getString("remarks"));
			if (flag.equals("1")) {
				Date date = new Date();
				scenicSpotAnnual.setCreatTime(date);
				scenicSpotAnnual.setStatus(Status.PendingSubmission);
				systemService.saveOrUpdate(scenicSpotAnnual);
				// 增加积分

				if (ReportTimeCheck.annualTimeCheck(Integer
						.parseInt(scenicSpotAnnual.getYear()))) {// 如果填报时间在应填报时间之前
					scenicSpotAnnual.setScore(40);// 增加40分
				} else {
					scenicSpotAnnual.setScore(20);
				}
				UserScoreUtils.scoreChange(scenicSpotAnnual.getScore(), userId);
				repsonse = "success";
			} else if (flag.equals("0")) {
				scenicSpotAnnual.setStatus(Status.notEdit);
				systemService.saveOrUpdate(scenicSpotAnnual);
				repsonse = "success";
			}

		}
		return repsonse;

	}

	/**
	 * 获取所有的年报数据
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(params = "allannualdata", method = RequestMethod.GET)
	@ResponseBody
	public String getAllAnnualData(HttpServletRequest request, String userId)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String response = null;
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		ScenicData scenicData = scenicDatalist.get(0);
		List<ScenicSpotAnnual> annualList = systemService.findHql(
				"from ScenicSpotAnnual ssw where ssw.scenicData=?", scenicData);
		List<ScenicYearlyReportEntity> appAnnualList = new ArrayList<ScenicYearlyReportEntity>();

		ScenicSpotAnnual scenicSpotAnnual[] = new ScenicSpotAnnual[annualList
				.size()];
		ScenicYearlyReportEntity scenicYearlyReportEntity[] = new ScenicYearlyReportEntity[annualList
				.size()];
		for (int i = 0; i < annualList.size(); i++) {
			scenicSpotAnnual[i] = annualList.get(i);
			scenicYearlyReportEntity[i] = new ScenicYearlyReportEntity();

			scenicYearlyReportEntity[i]
					.setPeriod(scenicSpotAnnual[i].getYear());
			scenicYearlyReportEntity[i].setTotalAssets(String
					.valueOf(scenicSpotAnnual[i].getAssetstotal()));
			scenicYearlyReportEntity[i].setAppropriation(String
					.valueOf(scenicSpotAnnual[i].getAppropriation()));
			scenicYearlyReportEntity[i].setLoan(String
					.valueOf(scenicSpotAnnual[i].getLoan()));
			scenicYearlyReportEntity[i].setSelfCollected(String
					.valueOf(scenicSpotAnnual[i].getFundsself()));
			scenicYearlyReportEntity[i].setConstrustionInvest(String
					.valueOf(scenicSpotAnnual[i].getBuildinvest()));
			scenicYearlyReportEntity[i].setInsideInvest(String
					.valueOf(scenicSpotAnnual[i].getInbuild()));
			scenicYearlyReportEntity[i].setOutsideInvest(String
					.valueOf(scenicSpotAnnual[i].getOutbuild()));
			scenicYearlyReportEntity[i].setTotalIncome(String
					.valueOf(scenicSpotAnnual[i].getTotalincome()));
			scenicYearlyReportEntity[i].setIncomeWithTicket(String
					.valueOf(scenicSpotAnnual[i].getTicketincome()));
			scenicYearlyReportEntity[i].setIncomeWithGoods(String
					.valueOf(scenicSpotAnnual[i].getShopincome()));
			scenicYearlyReportEntity[i].setIncomeWithFoods(String
					.valueOf(scenicSpotAnnual[i].getFoodincome()));
			scenicYearlyReportEntity[i].setIncomeWithTraffic(String
					.valueOf(scenicSpotAnnual[i].getTrafficincome()));
			scenicYearlyReportEntity[i].setIncomeWithStay(String
					.valueOf(scenicSpotAnnual[i].getPutUpincome()));
			scenicYearlyReportEntity[i].setIncomeWithShow(String
					.valueOf(scenicSpotAnnual[i].getShowincome()));
			scenicYearlyReportEntity[i].setIncomeWithOthers(String
					.valueOf(scenicSpotAnnual[i].getOtherincome()));
			scenicYearlyReportEntity[i].setPeopleCounts(String
					.valueOf(scenicSpotAnnual[i].getReceptionnum()));
			scenicYearlyReportEntity[i].setPeopleWithoutTicket(String
					.valueOf(scenicSpotAnnual[i].getExemptTicketnum()));
			scenicYearlyReportEntity[i].setEmployee(String
					.valueOf(scenicSpotAnnual[i].getJobnum()));
			scenicYearlyReportEntity[i].setEmployeeFixed(String
					.valueOf(scenicSpotAnnual[i].getFixedworker()));
			scenicYearlyReportEntity[i].setEmployeeTmp(String
					.valueOf(scenicSpotAnnual[i].getTempworker()));
			scenicYearlyReportEntity[i].setGuideCounts(String
					.valueOf(scenicSpotAnnual[i].getGuidenum()));
			scenicYearlyReportEntity[i].setRemarks(scenicSpotAnnual[i]
					.getRemarks());
			String status = ScenicWeekController.getstatus(scenicSpotAnnual[i]
					.getStatus());
			scenicYearlyReportEntity[i].setStatus(status);
			scenicYearlyReportEntity[i].setUserId(userId);
			scenicYearlyReportEntity[i].setReportDate(scenicSpotAnnual[i]
					.getCreatTime() == null ? "" : sdf
					.format(scenicSpotAnnual[i].getCreatTime()));

			appAnnualList.add(scenicYearlyReportEntity[i]);
		}

		response = JSON.toJSONString(appAnnualList);
		return response;
	}
}
