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
import com.zzc.app.scenic.entity.ScenicSeasonalReportEntity;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotQuarter;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/appscenicquarterController")
public class ScenicQuarterController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 是否有本季的季报数据
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "ifhavequarterdata", method = RequestMethod.GET)
	@ResponseBody
	public String ifhavequarterdata(String userId) {
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
			// 第几季度
			int nowMonth = calendar.get(Calendar.MONTH) + 1;
			int season = 0;
			if (nowMonth <= 3)
				season = 1;
			if (3 < nowMonth && nowMonth <= 6)
				season = 2;
			if (6 < nowMonth && nowMonth <= 9)
				season = 3;
			if (9 < nowMonth && nowMonth <= 12)
				season = 4;
			int quarter = season - 1;
			if (quarter == 0) {
				year = year - 1;
				quarter = 4;
			}
			// 获取当前年份周期的景区的季报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"select * from t_scenicspot_quarterly tsm where tsm.quarter_id=? and tsm.year=? and tsm.quarter=?",
							uid, year, quarter);

			if (list.size() > 0) {
				ScenicSpotQuarter scenicSpotQuarter = systemService
						.get(ScenicSpotQuarter.class,
								(String) list.get(0).get("id"));
				ScenicSeasonalReportEntity scenicQuarterlyReportEntity = new ScenicSeasonalReportEntity();
				scenicQuarterlyReportEntity.setPeriod(String
						.valueOf(scenicSpotQuarter.getYear()));
				scenicQuarterlyReportEntity.setCycle(String
						.valueOf(scenicSpotQuarter.getQuarter()));
				scenicQuarterlyReportEntity.setIncome(String
						.valueOf(scenicSpotQuarter.getTotalincome()));
				scenicQuarterlyReportEntity.setIncomewithTicket(String
						.valueOf(scenicSpotQuarter.getTicketincome()));
				scenicQuarterlyReportEntity.setPeopleCounts(String
						.valueOf(scenicSpotQuarter.getReceptionnum()));
				scenicQuarterlyReportEntity.setPeopleWithoutTicket(String
						.valueOf(scenicSpotQuarter.getExemptticketnum()));
				scenicQuarterlyReportEntity.setRemarks(scenicSpotQuarter
						.getRemarks());
				String status = ScenicWeekController
						.getstatus(scenicSpotQuarter.getStatus());
				scenicQuarterlyReportEntity.setStatus(status);
				scenicQuarterlyReportEntity.setReportDate(scenicSpotQuarter
						.getReportdate() == null ? "" : sdf
						.format(scenicSpotQuarter.getReportdate()));

				String node = "|" + year + "|" + quarter;
				repsonse = JSON.toJSONString(scenicQuarterlyReportEntity)
						+ node;

			} else {
				repsonse = "|" + year + "|" + quarter;
			}

		}
		return repsonse;
	}

	/**
	 * 季报提交
	 * 
	 * @param json
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(params = "quarterdata", method = RequestMethod.POST)
	@ResponseBody
	public String editquarterdata(HttpServletRequest request)
			throws ParseException {
		String json = request.getParameter("param");
		String flag = request.getParameter("flag");
		JSONObject node = JSONObject.fromObject(json);
		String repsonse = "error";
		ScenicSpotQuarter scenicSpotQuarter = new ScenicSpotQuarter();
		String userId = node.getString("userId");
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (scenicDatalist.size() > 0) {
			ScenicData scenicData = scenicDatalist.get(0);
			scenicSpotQuarter.setScenicData(scenicData);
			// 判断季报是否填写
			String uid = scenicData.getId();
			Calendar calendar = Calendar.getInstance();
			// 当前年份
			int year = calendar.get(Calendar.YEAR);
			// 第几季度
			int nowMonth = calendar.get(Calendar.MONTH) + 1;
			int season = 0;
			if (nowMonth <= 3)
				season = 1;
			if (3 < nowMonth && nowMonth <= 6)
				season = 2;
			if (6 < nowMonth && nowMonth <= 9)
				season = 3;
			if (9 < nowMonth && nowMonth <= 12)
				season = 4;
			int quarter = season - 1;
			if (quarter == 0) {
				year = year - 1;
				quarter = 4;
			}
			// 获取当前年份周期的景区的季报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"select * from t_scenicspot_quarterly tsm where tsm.quarter_id=? and tsm.year=? and tsm.quarter=?",
							uid, year, quarter);

			if (list.size() > 0) {
				scenicSpotQuarter = systemService.get(ScenicSpotQuarter.class,
						(String) list.get(0).get("id"));
			}
			scenicSpotQuarter.setYear(node.getString("period").equals("") ? 0
					: Integer.valueOf(node.getString("period")));
			scenicSpotQuarter.setQuarter(node.getString("cycle").equals("") ? 0
					: Integer.valueOf(node.getString("cycle")));
			scenicSpotQuarter.setTotalincome(node.getString("income")
					.equals("") ? 0.0
					: Double.valueOf(node.getString("income")));
			scenicSpotQuarter.setTicketincome(node
					.getString("incomewithTicket").equals("") ? 0.0 : Double
					.valueOf(node.getString("incomewithTicket")));
			scenicSpotQuarter.setReceptionnum(node.getString("peopleCounts")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("peopleCounts")));
			scenicSpotQuarter.setExemptticketnum(node.getString(
					"peopleWithoutTicket").equals("") ? 0.0 : Double
					.valueOf(node.getString("peopleWithoutTicket")));
			scenicSpotQuarter.setRemarks(node.getString("remarks"));
			if (flag.equals("1")) {
				scenicSpotQuarter.setStatus(Status.PendingSubmission);
				Date date = new Date();
				scenicSpotQuarter.setReportdate(date);
				systemService.saveOrUpdate(scenicSpotQuarter);
				// 增加积分
				if (ReportTimeCheck.quarterTimeCheck(
						scenicSpotQuarter.getYear(),
						scenicSpotQuarter.getQuarter())) {
					scenicSpotQuarter.setScore(10);
				} else {
					scenicSpotQuarter.setScore(5);
				}
				UserScoreUtils
						.scoreChange(scenicSpotQuarter.getScore(), userId);
				repsonse = "success";
			} else if (flag.equals("0")) {
				scenicSpotQuarter.setStatus(Status.notEdit);
				systemService.saveOrUpdate(scenicSpotQuarter);
				repsonse = "success";
			}

		}
		return repsonse;

	}

	/**
	 * 获取所有的季报数据
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(params = "allquarterdata", method = RequestMethod.GET)
	@ResponseBody
	public String getAllQuarterData(HttpServletRequest request, String userId)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String response = null;
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		ScenicData scenicData = scenicDatalist.get(0);
		List<ScenicSpotQuarter> quarterList = systemService
				.findHql("from ScenicSpotQuarter ssw where ssw.scenicData=?",
						scenicData);
		List<ScenicSeasonalReportEntity> appQuarterList = new ArrayList<ScenicSeasonalReportEntity>();

		ScenicSpotQuarter scenicSpotQuarter[] = new ScenicSpotQuarter[quarterList
				.size()];
		ScenicSeasonalReportEntity scenicSeasonalReportEntity[] = new ScenicSeasonalReportEntity[quarterList
				.size()];
		for (int i = 0; i < quarterList.size(); i++) {
			scenicSpotQuarter[i] = quarterList.get(i);
			scenicSeasonalReportEntity[i] = new ScenicSeasonalReportEntity();

			scenicSeasonalReportEntity[i].setPeriod(String
					.valueOf(scenicSpotQuarter[i].getYear()));
			scenicSeasonalReportEntity[i].setCycle(String
					.valueOf(scenicSpotQuarter[i].getQuarter()));
			scenicSeasonalReportEntity[i].setIncome(String
					.valueOf(scenicSpotQuarter[i].getTotalincome()));
			scenicSeasonalReportEntity[i].setIncomewithTicket(String
					.valueOf(scenicSpotQuarter[i].getTicketincome()));
			scenicSeasonalReportEntity[i].setPeopleCounts(String
					.valueOf(scenicSpotQuarter[i].getReceptionnum()));
			scenicSeasonalReportEntity[i].setPeopleWithoutTicket(String
					.valueOf(scenicSpotQuarter[i].getExemptticketnum()));
			scenicSeasonalReportEntity[i].setRemarks(scenicSpotQuarter[i]
					.getRemarks());
			String status = ScenicWeekController.getstatus(scenicSpotQuarter[i]
					.getStatus());
			scenicSeasonalReportEntity[i].setStatus(status);
			scenicSeasonalReportEntity[i].setReportDate(scenicSpotQuarter[i]
					.getReportdate() == null ? "" : sdf
					.format(scenicSpotQuarter[i].getReportdate()));

			appQuarterList.add(scenicSeasonalReportEntity[i]);
		}

		response = JSON.toJSONString(appQuarterList);
		return response;
	}

}
