package com.zzc.app.otherTravel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.zzc.app.otherTravel.entity.GolfMonthlyReportEntity;
import com.zzc.web.otherTravel.entity.GolfMonthly;
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.system.service.SystemService;

/**
 * 
 * 
 * @date: 2017年2月9日
 * @Author: 龙
 * @Email:
 * @FileName: golfMonthController.java
 * @Version: 1.0
 * @About: 保存/展示空中飞行项目接口
 */
@Controller
@RequestMapping("/appservice/appGolfMonthController")
public class GolfMonthController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 是否填报月报
	 * 
	 * @param userId
	 * @return 月报实体类
	 */
	@RequestMapping(params = "ifhavemonthdata", method = RequestMethod.GET)
	@ResponseBody
	public GolfMonthlyReportEntity ifhavemonthdata(String userId) {
		List<OtherTravelInfo> otherTravelInfolist = systemService.findHql(
				"from OtherTravelInfo ot  where ot.id=?", userId);
		String repsonse = null;
		GolfMonthlyReportEntity golfMonthlyReportEntity = new GolfMonthlyReportEntity();
		if (otherTravelInfolist.size() > 0) {
			OtherTravelInfo otherTravelInfo = otherTravelInfolist.get(0);
			String uid = otherTravelInfo.getId();
			String lastMonth = TimeUtils.getLastMonth();
			// 获取当前年份高尔夫项目的月报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"SELECT * FROM `t_golf_monthly` tg WHERE tg.oid=? AND tg.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				GolfMonthly golfMonthly = systemService.get(GolfMonthly.class,
						(String) list.get(0).get("ID"));

				golfMonthlyReportEntity.setDates(String.valueOf(golfMonthly
						.getSmonth()));
				golfMonthlyReportEntity.setUserId(userId);
				golfMonthlyReportEntity.setBusinessincome(String
						.valueOf(golfMonthly.getBusinessincome()));
				golfMonthlyReportEntity.setReceptionnum(String
						.valueOf(golfMonthly.getReceptionnum()));
				golfMonthlyReportEntity.setReporter(String.valueOf(golfMonthly
						.getReporter()));
				String status = getstatus(String.valueOf(golfMonthly
						.getStatus()));
				golfMonthlyReportEntity.setStatus(status);
				if (null != golfMonthly.getWriteDate()) {
					golfMonthlyReportEntity.setWriteDate(TimeUtils
							.getMonth(golfMonthly.getWriteDate()));
				}
				// flightMonthlyReportEntity;
			} else {
				GolfMonthlyReportEntity golfMonthlyReportEntity1 = new GolfMonthlyReportEntity();
				golfMonthlyReportEntity1.setDates(TimeUtils.getLastMonth());
				golfMonthlyReportEntity1.setStatus(getstatus(Status.noSumbit));
				repsonse = JSON.toJSONString(golfMonthlyReportEntity1);
				return golfMonthlyReportEntity1;
			}

		}
		return golfMonthlyReportEntity;

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
		GolfMonthly golfMonthly = new GolfMonthly();
		String userId = node.getString("userId");
		List<OtherTravelInfo> otherTravelInfolist = systemService.findHql(
				"from OtherTravelInfo ot  where ot.id=?", userId);
		if (otherTravelInfolist.size() > 0) {
			OtherTravelInfo otherTravelInfo = otherTravelInfolist.get(0);
			String uid = otherTravelInfo.getId();
			String lastMonth = TimeUtils.getLastMonth();
			// 获取当前年份飞行项目的月报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"SELECT * FROM `t_golf_monthly` tg WHERE tg.oid=? AND tg.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				golfMonthly = systemService.get(GolfMonthly.class,
						(String) list.get(0).get("ID"));
			}
			golfMonthly.setOtherTravelInfo(otherTravelInfo);
			golfMonthly.setOtid(userId);
			golfMonthly.setDates(new SimpleDateFormat("yyyy-MM").parse(node
					.getString("dates")));
			golfMonthly.setBusinessincome(node.getString("businessincome")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("businessincome")));
			golfMonthly.setReceptionnum(node.getString("receptionnum").equals(
					"") ? 0 : Integer.valueOf(node.getString("receptionnum")));
			golfMonthly.setSmonth(node.getString("dates"));
			golfMonthly.setReporter(node.getString("reporter"));
			if (flag.equals("1")) {
				Date date = new Date();
				golfMonthly.setWriteDate(date);
				golfMonthly.setStatus(Status.noAudit);
				systemService.saveOrUpdate(golfMonthly);
				repsonse = "success";
			} else if (flag.equals("0")) {
				golfMonthly.setStatus(Status.noSumbit);
				systemService.saveOrUpdate(golfMonthly);
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
		List<OtherTravelInfo> otherTravelInfolist = systemService.findHql(
				"from OtherTravelInfo ot  where ot.id=?", userId);
		OtherTravelInfo otherTravelInfo = otherTravelInfolist.get(0);
		List<GolfMonthly> monthList = this.systemService
				.findHql(
						"from GolfMonthly gf where gf.status <> 1 and gf.otherTravelInfo=?",
						otherTravelInfo);
		List<GolfMonthlyReportEntity> appMonthList = new ArrayList<>();
		GolfMonthly golfMonth[] = new GolfMonthly[monthList.size()];
		GolfMonthlyReportEntity golfMonthlyReportEntity[] = new GolfMonthlyReportEntity[monthList
				.size()];
		for (int i = 0; i < monthList.size(); i++) {
			golfMonth[i] = monthList.get(i);
			golfMonthlyReportEntity[i] = new GolfMonthlyReportEntity();
			golfMonthlyReportEntity[i].setDates(String.valueOf(golfMonth[i]
					.getSmonth()));
			golfMonthlyReportEntity[i].setUserId(userId);
			golfMonthlyReportEntity[i].setBusinessincome(String
					.valueOf(golfMonth[i].getBusinessincome()));
			golfMonthlyReportEntity[i].setReceptionnum(String
					.valueOf(golfMonth[i].getReceptionnum()));
			golfMonthlyReportEntity[i].setReporter(String.valueOf(golfMonth[i]
					.getReporter()));
			String status = getstatus(String.valueOf(golfMonth[i].getStatus()));
			golfMonthlyReportEntity[i].setStatus(status);
			golfMonthlyReportEntity[i].setWriteDate(TimeUtils
					.getMonth(golfMonth[i].getWriteDate()));
			appMonthList.add(golfMonthlyReportEntity[i]);
		}
		response = JSON.toJSONString(appMonthList);
		return response;
	}

	public static String getstatus(String status) {
		if (status.equals(Status.noSumbit)) {
			status = "未填报";
		} else if (status.equals(Status.noAudit)) {
			status = "未审核";
		} else if (status.equals(Status.notAudit)) {
			status = "管理员已审核(审核不通过)";
		} else if (status.equals(Status.passAudit)) {
			status = "管理员已审核(审核通过)";
		} else if (status.equals(Status.notAuditByP)) {
			status = "省已审核 审核结果:不通过";
		} else if (status.equals(Status.passAuditByP)) {
			status = "省已审核 审核结果:通过";
		}

		return status;
	}

}
