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
import com.zzc.app.scenic.entity.ScenicMonthlyReportEntity;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotMonth;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/appscenicMonthController")
public class ScenicMonthController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 是否有本月的月报数据
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "ifhavemonthdata", method = RequestMethod.GET)
	@ResponseBody
	public String ifhavemonthdata(String userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		String repsonse = null;
		if (scenicDatalist.size() > 0) {
			ScenicData scenicData = scenicDatalist.get(0);
			String uid = scenicData.getId();
			Calendar calendar = Calendar.getInstance();
			// 当前年份
			int year = calendar.get(Calendar.YEAR);
			// 第几周
			int month = calendar.get(Calendar.MONTH);
			if (month == 0) {
				year = year - 1;
				month = 12;
			}
			// 获取当前年份周期的景区的周报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"select * from t_scenicspot_monthly tsm where tsm.month_id=? and tsm.year=? and tsm.month=?",
							uid, year, month);

			if (list.size() > 0) {
				ScenicSpotMonth scenicSpotMonth = systemService.get(
						ScenicSpotMonth.class, (String) list.get(0).get("id"));

				ScenicMonthlyReportEntity scenicMonthlyReportEntity = new ScenicMonthlyReportEntity();
				scenicMonthlyReportEntity.setPeriod(scenicSpotMonth.getYear());
				scenicMonthlyReportEntity.setCycle(scenicSpotMonth.getMonth());
				scenicMonthlyReportEntity.setBusinessIncome(String
						.valueOf(scenicSpotMonth.getTaking()));
				scenicMonthlyReportEntity.setTicketIncome(String
						.valueOf(scenicSpotMonth.getTicket()));
				scenicMonthlyReportEntity.setTaxes(String
						.valueOf(scenicSpotMonth.getBusinesstax()));
				scenicMonthlyReportEntity.setTotalIncome(String
						.valueOf(scenicSpotMonth.getTotalprofit()));
				scenicMonthlyReportEntity.setEmployee(String
						.valueOf(scenicSpotMonth.getEndemployee()));
				scenicMonthlyReportEntity.setEmployeeLaidOff(String
						.valueOf(scenicSpotMonth.getLaidworker()));
				scenicMonthlyReportEntity.setEmployeeCountry(String
						.valueOf(scenicSpotMonth.getCountrylabor()));
				scenicMonthlyReportEntity.setFixedAssetsOriginal(String
						.valueOf(scenicSpotMonth.getPrice()));
				scenicMonthlyReportEntity.setFixedAssetsPure(String
						.valueOf(scenicSpotMonth.getAsset()));
				scenicMonthlyReportEntity.setPeopleCounts(String
						.valueOf(scenicSpotMonth.getReceptionnum()));
				scenicMonthlyReportEntity.setPeopleOutBounds(String
						.valueOf(scenicSpotMonth.getOvernum()));
				scenicMonthlyReportEntity.setPeopleWithoutTicket(String
						.valueOf(scenicSpotMonth.getFreeticket()));
				scenicMonthlyReportEntity.setRate(scenicSpotMonth.getCompare());
				scenicMonthlyReportEntity.setResponsiblePeople(scenicSpotMonth
						.getPrincipal());
				scenicMonthlyReportEntity.setWriter(scenicSpotMonth
						.getPreparer());
				scenicMonthlyReportEntity.setPhone(scenicSpotMonth
						.getTelephone());
				scenicMonthlyReportEntity.setReportDate(scenicSpotMonth
						.getReportdate() == null ? "" : sdf
						.format(scenicSpotMonth.getReportdate()));
				String status = ScenicWeekController.getstatus(scenicSpotMonth
						.getStatus());
				scenicMonthlyReportEntity.setStatus(status);
				scenicMonthlyReportEntity.setUserId(userId);

				String node = "|" + year + "|" + month;
				repsonse = JSON.toJSONString(scenicMonthlyReportEntity) + node;

			} else {
				repsonse = "|" + year + "|" + month;
			}

		}
		return repsonse;
	}

	/**
	 * 月报提交
	 * 
	 * @param json
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(params = "monthdata", method = RequestMethod.POST)
	@ResponseBody
	public String editmonthdata(HttpServletRequest request)
			throws ParseException {
		String json = request.getParameter("param");
		String flag = request.getParameter("flag");
		JSONObject node = JSONObject.fromObject(json);
		String repsonse = "error";
		ScenicSpotMonth scenicSpotMonth = new ScenicSpotMonth();
		String userId = node.getString("userId");
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);

		if (scenicDatalist.size() > 0) {
			ScenicData scenicData = scenicDatalist.get(0);
			// 判断是否有本月的月报
			String uid = scenicData.getId();
			Calendar calendar = Calendar.getInstance();
			// 当前年份
			int year = calendar.get(Calendar.YEAR);
			// 第几周
			int month = calendar.get(Calendar.MONTH);
			if (month == 0) {
				year = year - 1;
				month = 12;
			}
			// 获取当前年份周期的景区的周报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"select * from t_scenicspot_monthly tsm where tsm.month_id=? and tsm.year=? and tsm.month=?",
							uid, year, month);
			if (list.size() > 0) {
				scenicSpotMonth = systemService.get(ScenicSpotMonth.class,
						(String) list.get(0).get("id"));
			}
			scenicSpotMonth.setScenicData(scenicData);
			scenicSpotMonth.setYear(node.getString("period"));
			scenicSpotMonth.setMonth(node.getString("cycle"));
			scenicSpotMonth
					.setBusinesstax(node.getString("taxes").equals("") ? 0.0
							: Double.valueOf(node.getString("taxes")));
			scenicSpotMonth.setEndemployee(node.getString("employee")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("employee")));
			scenicSpotMonth
					.setTicket(node.getString("ticketIncome").equals("") ? 0.0
							: Double.valueOf(node.getString("ticketIncome")));
			scenicSpotMonth.setTaking(node.getString("businessIncome").equals(
					"") ? 0.0
					: Double.valueOf(node.getString("businessIncome")));
			scenicSpotMonth.setTotalprofit(node.getString("totalIncome")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("totalIncome")));
			scenicSpotMonth.setLaidworker(node.getString("employeeLaidOff")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("employeeLaidOff")));
			scenicSpotMonth.setCountrylabor(node.getString("employeeCountry")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("employeeCountry")));
			scenicSpotMonth.setPrice(node.getString("fixedAssetsOriginal")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("fixedAssetsOriginal")));
			scenicSpotMonth.setAsset(node.getString("fixedAssetsPure").equals(
					"") ? 0.0 : Double.valueOf(node
					.getString("fixedAssetsPure")));
			scenicSpotMonth.setReceptionnum(node.getString("peopleCounts")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("peopleCounts")));
			scenicSpotMonth.setOvernum(node.getString("peopleOutBounds")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("peopleOutBounds")));
			scenicSpotMonth.setFreeticket(node.getString("peopleWithoutTicket")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("peopleWithoutTicket")));
			scenicSpotMonth.setCompare(node.getString("rate"));
			scenicSpotMonth.setPrincipal(node.getString("responsiblePeople"));
			scenicSpotMonth.setPreparer(node.getString("writer"));
			scenicSpotMonth.setTelephone(node.getString("phone"));
			if (flag.equals("1")) {
				Date date = new Date();
				scenicSpotMonth.setReportdate(date);
				scenicSpotMonth.setStatus(Status.PendingSubmission);

				// 增加积分
				if (scenicSpotMonth.getId() == null
						|| scenicSpotMonth.getId().length() == 0) {
					if (ReportTimeCheck.monthTimeCheck(
							Integer.parseInt(scenicSpotMonth.getYear()),
							Integer.parseInt(scenicSpotMonth.getMonth()), 5)) {
						scenicSpotMonth.setScore(5);
					} else {
						scenicSpotMonth.setScore(2);
					}

					UserScoreUtils.scoreChange(scenicSpotMonth.getScore(),
							userId);
				}
				repsonse = "success";
				systemService.saveOrUpdate(scenicSpotMonth);
			} else if (flag.equals("0")) {
				scenicSpotMonth.setStatus(Status.notEdit);
				systemService.saveOrUpdate(scenicSpotMonth);
				repsonse = "success";
			}

		}
		return repsonse;

	}

	/**
	 * 获取所有的月报数据
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(params = "allmonthdata", method = RequestMethod.GET)
	@ResponseBody
	public String getAllMonthData(HttpServletRequest request, String userId)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String response = null;
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		ScenicData scenicData = scenicDatalist.get(0);
		List<ScenicSpotMonth> monthList = systemService.findHql(
				"from ScenicSpotMonth ssw where ssw.scenicData=?", scenicData);
		List<ScenicMonthlyReportEntity> appMonthList = new ArrayList<ScenicMonthlyReportEntity>();

		ScenicSpotMonth scenicSpotMonth[] = new ScenicSpotMonth[monthList
				.size()];
		ScenicMonthlyReportEntity scenicMonthlyReportEntity[] = new ScenicMonthlyReportEntity[monthList
				.size()];
		for (int i = 0; i < monthList.size(); i++) {
			scenicSpotMonth[i] = monthList.get(i);
			scenicMonthlyReportEntity[i] = new ScenicMonthlyReportEntity();

			scenicMonthlyReportEntity[i]
					.setPeriod(scenicSpotMonth[i].getYear());
			scenicMonthlyReportEntity[i]
					.setCycle(scenicSpotMonth[i].getMonth());
			scenicMonthlyReportEntity[i].setBusinessIncome(String
					.valueOf(scenicSpotMonth[i].getTaking()));
			scenicMonthlyReportEntity[i].setTicketIncome(String
					.valueOf(scenicSpotMonth[i].getTicket()));
			scenicMonthlyReportEntity[i].setTaxes(String
					.valueOf(scenicSpotMonth[i].getBusinesstax()));
			scenicMonthlyReportEntity[i].setTotalIncome(String
					.valueOf(scenicSpotMonth[i].getTotalprofit()));
			scenicMonthlyReportEntity[i].setEmployee(String
					.valueOf(scenicSpotMonth[i].getEndemployee()));
			scenicMonthlyReportEntity[i].setEmployeeLaidOff(String
					.valueOf(scenicSpotMonth[i].getLaidworker()));
			scenicMonthlyReportEntity[i].setEmployeeCountry(String
					.valueOf(scenicSpotMonth[i].getCountrylabor()));
			scenicMonthlyReportEntity[i].setFixedAssetsOriginal(String
					.valueOf(scenicSpotMonth[i].getPrice()));
			scenicMonthlyReportEntity[i].setFixedAssetsPure(String
					.valueOf(scenicSpotMonth[i].getAsset()));
			scenicMonthlyReportEntity[i].setPeopleCounts(String
					.valueOf(scenicSpotMonth[i].getReceptionnum()));
			scenicMonthlyReportEntity[i].setPeopleOutBounds(String
					.valueOf(scenicSpotMonth[i].getOvernum()));
			scenicMonthlyReportEntity[i].setPeopleWithoutTicket(String
					.valueOf(scenicSpotMonth[i].getFreeticket()));
			scenicMonthlyReportEntity[i].setRate(scenicSpotMonth[i]
					.getCompare());
			scenicMonthlyReportEntity[i]
					.setResponsiblePeople(scenicSpotMonth[i].getPrincipal());
			scenicMonthlyReportEntity[i].setWriter(scenicSpotMonth[i]
					.getPreparer());
			scenicMonthlyReportEntity[i].setPhone(scenicSpotMonth[i]
					.getTelephone());
			scenicMonthlyReportEntity[i].setReportDate(scenicSpotMonth[i]
					.getReportdate() == null ? "" : sdf
					.format(scenicSpotMonth[i].getReportdate()));
			String status = ScenicWeekController.getstatus(scenicSpotMonth[i]
					.getStatus());
			scenicMonthlyReportEntity[i].setStatus(status);
			scenicMonthlyReportEntity[i].setUserId(userId);

			appMonthList.add(scenicMonthlyReportEntity[i]);
		}

		response = JSON.toJSONString(appMonthList);
		return response;
	}
}
