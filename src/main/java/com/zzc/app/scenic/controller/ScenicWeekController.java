package com.zzc.app.scenic.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.zzc.app.scenic.entity.ScenicWeek;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotWeek;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.sylyUtils.WeekUtils;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/appscenicWeekController")
public class ScenicWeekController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 是否有本周的周报数据
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "ifhaveweekdata", method = RequestMethod.GET)
	@ResponseBody
	public String ifhaveweekdata(String userId) {
		// 通过userId获取本景区
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		String repsonse = null;
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (scenicDatalist.size() > 0) {
			// 获取本景区
			ScenicData scenicData = scenicDatalist.get(0);
			String uid = scenicData.getId();

			Calendar c = new GregorianCalendar();
			int week = WeekUtils.getWeekOfYear(c.getTime()) - 1;
			int year = c.get(Calendar.YEAR);
			// 第几周
			if (week == 0) {
				year = year - 1;
				week = 52;
			}
			// 获取当前年份周期的景区的周报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"select * from t_scenicspot_weekly tsw where tsw.scenic_id=? and tsw.year=? and tsw.cycle=?",
							uid, year, week);

			if (list.size() > 0) {
				ScenicSpotWeek scenicSpotWeek = systemService.get(
						ScenicSpotWeek.class, (String) list.get(0).get("id"));
				ScenicWeek scenicWeek1 = new ScenicWeek();
				scenicWeek1
						.setTaking(String.valueOf(scenicSpotWeek.getTaking()));
				scenicWeek1
						.setTicket(String.valueOf(scenicSpotWeek.getTicket()));
				scenicWeek1.setReceptionnum(String.valueOf(scenicSpotWeek
						.getReceptionnum()));
				scenicWeek1.setOvernum(String.valueOf(scenicSpotWeek
						.getOvernum()));
				scenicWeek1.setCompare(scenicSpotWeek.getCompare());
				scenicWeek1.setPrincipal(scenicSpotWeek.getPrincipal());
				scenicWeek1.setPreparer(scenicSpotWeek.getPreparer());
				scenicWeek1.setTelephone(scenicSpotWeek.getTelephone());
				scenicWeek1
						.setReportdate(scenicSpotWeek.getReportdate() == null ? ""
								: sdf.format(scenicSpotWeek.getReportdate()));
				scenicWeek1.setYear(scenicSpotWeek.getYear());
				scenicWeek1.setCycle(String.valueOf(scenicSpotWeek.getCycle()));
				scenicWeek1.setUserId(userId);
				String status = getstatus(scenicSpotWeek.getStatus());
				scenicWeek1.setStatus(status);

				String node = "|" + year + "|" + week;
				repsonse = JSON.toJSONString(scenicWeek1) + node;

			} else {
				repsonse = "|" + year + "|" + week;
			}
		}
		return repsonse;
	}

	/**
	 * 周报提交
	 * 
	 * @param json
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(params = "weekdata", method = RequestMethod.POST)
	@ResponseBody
	public String editweekdata(HttpServletRequest request)
			throws ParseException {
		String json = request.getParameter("param");
		String flag = request.getParameter("flag");
		JSONObject node = JSONObject.fromObject(json);
		String repsonse = "error";
		ScenicSpotWeek scenicWeek = new ScenicSpotWeek();
		String userId = node.getString("userId");
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		if (scenicDatalist.size() > 0) {

			ScenicData scenicData = scenicDatalist.get(0);
			scenicWeek.setScenicData(scenicData);
			// 判断是否有周报
			String uid = scenicData.getId();
			Calendar c = new GregorianCalendar();
			int week = WeekUtils.getWeekOfYear(c.getTime()) - 1;
			int year = c.get(Calendar.YEAR);
			// 第几周
			if (week == 0) {
				year = year - 1;
				week = 52;
			}
			// 获取当前年份周期的景区的周报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"select * from t_scenicspot_weekly tsw where tsw.scenic_id=? and tsw.year=? and tsw.cycle=?",
							uid, year, week);
			if (list.size() > 0) {
				scenicWeek = systemService.get(ScenicSpotWeek.class,
						(String) list.get(0).get("id"));
			}
			scenicWeek.setTaking(node.getString("taking").equals("") ? 0.0
					: Double.valueOf(node.getString("taking")));
			scenicWeek.setTicket(node.getString("ticket").equals("") ? 0.0
					: Double.valueOf(node.getString("ticket")));
			scenicWeek.setReceptionnum(node.getString("receptionnum")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("receptionnum")));
			scenicWeek.setOvernum(node.getString("overnum").equals("") ? 0
					: Integer.valueOf(node.getString("overnum")));
			scenicWeek.setCompare(node.getString("compare"));
			scenicWeek.setPrincipal(node.getString("principal"));
			scenicWeek.setPreparer(node.getString("preparer"));
			scenicWeek.setTelephone(node.getString("telephone"));
			scenicWeek.setYear(node.getString("year"));
			scenicWeek.setCycle(node.getString("cycle").equals("") ? 0
					: Integer.valueOf(node.getString("cycle")));

			if (flag.equals("1")) {
				Date date = new Date();
				scenicWeek.setReportdate(date);
				scenicWeek.setStatus(Status.PendingSubmission);
				systemService.saveOrUpdate(scenicWeek);
				if (!ReportTimeCheck.WeekTimeCheck(
						Integer.parseInt(scenicWeek.getYear()),
						scenicWeek.getCycle())) {
					UserScoreUtils.scoreChange(-1, userId);
				}
				repsonse = "success";
			} else if (flag.equals("0")) {
				scenicWeek.setStatus(Status.notEdit);
				systemService.saveOrUpdate(scenicWeek);
				repsonse = "success";
			}

		}

		return repsonse;

	}

	/**
	 * 获取所有的周报数据
	 */
	@RequestMapping(params = "allweekdata", method = RequestMethod.GET)
	@ResponseBody
	public String getAllWeekData(HttpServletRequest request, String userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String response = null;
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		ScenicData scenicData = scenicDatalist.get(0);
		List<ScenicSpotWeek> weekList = systemService.findHql(
				"from ScenicSpotWeek ssw where ssw.scenicData=?", scenicData);
		List<ScenicWeek> appWeekList = new ArrayList<ScenicWeek>();

		ScenicSpotWeek scenicSpotWeek[] = new ScenicSpotWeek[weekList.size()];
		ScenicWeek scenicWeek[] = new ScenicWeek[weekList.size()];
		for (int i = 0; i < weekList.size(); i++) {
			scenicSpotWeek[i] = weekList.get(i);
			scenicWeek[i] = new ScenicWeek();
			scenicWeek[i].setTaking(String.valueOf(scenicSpotWeek[i]
					.getTaking()));
			scenicWeek[i].setTicket(String.valueOf(scenicSpotWeek[i]
					.getTicket()));
			scenicWeek[i].setReceptionnum(String.valueOf(scenicSpotWeek[i]
					.getReceptionnum()));
			scenicWeek[i].setOvernum(String.valueOf(scenicSpotWeek[i]
					.getOvernum()));
			scenicWeek[i].setCompare(scenicSpotWeek[i].getCompare());
			scenicWeek[i].setPrincipal(scenicSpotWeek[i].getPrincipal());
			scenicWeek[i].setPreparer(scenicSpotWeek[i].getPreparer());
			scenicWeek[i].setTelephone(scenicSpotWeek[i].getTelephone());
			scenicWeek[i]
					.setReportdate(scenicSpotWeek[i].getReportdate() == null ? ""
							: sdf.format(scenicSpotWeek[i].getReportdate()));
			scenicWeek[i].setYear(scenicSpotWeek[i].getYear());
			scenicWeek[i]
					.setCycle(String.valueOf(scenicSpotWeek[i].getCycle()));
			scenicWeek[i].setUserId(userId);
			String status = getstatus(scenicSpotWeek[i].getStatus());
			scenicWeek[i].setStatus(status);
			appWeekList.add(scenicWeek[i]);
		}

		response = JSON.toJSONString(appWeekList);
		return response;
	}

	public static String getstatus(String status) {
		if (status.equals(Status.undistributed)) {
			status = "未分配";
		} else if (status.equals(Status.distributedt)) {
			status = "已分配";
		} else if (status.equals(Status.notEdit)) {
			status = "未填报";
		} else if (status.equals(Status.PendingSubmission)) {
			status = "提交待审";
		} else if (status.equals(Status.notPass)) {
			status = "退回修订";
		} else if (status.equals(Status.areaAudit)) {
			status = "区级审核";
		} else if (status.equals(Status.cityAudit)) {
			status = "市级审核";
		} else if (status.equals(Status.provinceAudit)) {
			status = "省级审核";
		} else if (status.equals(Status.countryAudit)) {
			status = "国家审核";
		} else if (status.equals(Status.waitProAudit)) {
			status = "省级待审核";
		}
		return status;
	}

}
