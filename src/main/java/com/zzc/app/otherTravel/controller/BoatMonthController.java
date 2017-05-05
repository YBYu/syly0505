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
import com.zzc.app.otherTravel.entity.BoatMonthlyReportEntity;
import com.zzc.web.otherTravel.entity.BoatMonthly;
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
@RequestMapping("/appservice/appBoatMonthController")
public class BoatMonthController {
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
	public BoatMonthlyReportEntity ifhavemonthdata(String userId) {
		List<OtherTravelInfo> otherTravelInfolist = systemService.findHql(
				"from OtherTravelInfo ot  where ot.id=?", userId);
		String repsonse = null;
		BoatMonthlyReportEntity boatMonthlyReportEntity = new BoatMonthlyReportEntity();
		if (otherTravelInfolist.size() > 0) {
			OtherTravelInfo otherTravelInfo = otherTravelInfolist.get(0);
			String uid = otherTravelInfo.getId();
			String lastMonth = TimeUtils.getLastMonth();
			// 获取当前年份高尔夫项目的月报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"SELECT * FROM `t_boat_monthly` tb WHERE tb.oid=? AND tb.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				BoatMonthly boatMonthly = systemService.get(BoatMonthly.class,
						(String) list.get(0).get("ID"));

				boatMonthlyReportEntity.setDates(String.valueOf(boatMonthly
						.getSmonth()));
				boatMonthlyReportEntity.setUserId(userId);
				boatMonthlyReportEntity.setIncome(String.valueOf(boatMonthly
						.getIncome()));
				boatMonthlyReportEntity.setRecepNum(String.valueOf(boatMonthly
						.getRecepNum()));
				boatMonthlyReportEntity.setReporter(String.valueOf(boatMonthly
						.getReporter()));
				String status = getstatus(String.valueOf(boatMonthly
						.getStatus()));
				boatMonthlyReportEntity.setStatus(status);
				if (null != boatMonthly.getWriteDate()) {
					boatMonthlyReportEntity.setWriteDate(TimeUtils
							.getMonth(boatMonthly.getWriteDate()));
				}
			} else {
				BoatMonthlyReportEntity boatMonthlyReportEntity1 = new BoatMonthlyReportEntity();
				boatMonthlyReportEntity1.setDates(TimeUtils.getLastMonth());
				boatMonthlyReportEntity1.setStatus(getstatus(Status.noSumbit));
				repsonse = JSON.toJSONString(boatMonthlyReportEntity1);
				return boatMonthlyReportEntity1;
			}

		}
		return boatMonthlyReportEntity;

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
		BoatMonthly boatMonthly = new BoatMonthly();
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
							"SELECT * FROM `t_boat_monthly` tb WHERE tb.oid=? AND tb.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				boatMonthly = systemService.get(BoatMonthly.class,
						(String) list.get(0).get("ID"));
			}
			boatMonthly.setOtherTravelInfo(otherTravelInfo);
			boatMonthly.setOtid(userId);
			boatMonthly.setDates(new SimpleDateFormat("yyyy-MM").parse(node
					.getString("dates")));
			boatMonthly.setIncome(node.getString("income").equals("") ? 0.0
					: Double.valueOf(node.getString("income")));
			boatMonthly.setRecepNum(node.getString("recepNum").equals("") ? 0
					: Integer.valueOf(node.getString("recepNum")));
			boatMonthly.setSmonth(node.getString("dates"));
			boatMonthly.setReporter(node.getString("reporter"));
			if (flag.equals("1")) {
				Date date = new Date();
				boatMonthly.setWriteDate(date);
				boatMonthly.setStatus(Status.noAudit);
				systemService.saveOrUpdate(boatMonthly);
				repsonse = "success";
			} else if (flag.equals("0")) {
				boatMonthly.setStatus(Status.noSumbit);
				systemService.saveOrUpdate(boatMonthly);
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
		List<BoatMonthly> monthList = this.systemService
				.findHql(
						"from BoatMonthly bt where bt.status <> 1 and bt.otherTravelInfo=?",
						otherTravelInfo);
		List<BoatMonthlyReportEntity> appMonthList = new ArrayList<>();
		BoatMonthly boatMonth[] = new BoatMonthly[monthList.size()];
		BoatMonthlyReportEntity boatMonthlyReportEntity[] = new BoatMonthlyReportEntity[monthList
				.size()];
		for (int i = 0; i < monthList.size(); i++) {
			boatMonth[i] = monthList.get(i);
			boatMonthlyReportEntity[i] = new BoatMonthlyReportEntity();
			boatMonthlyReportEntity[i].setDates(String.valueOf(boatMonth[i]
					.getSmonth()));
			boatMonthlyReportEntity[i].setUserId(userId);
			boatMonthlyReportEntity[i].setIncome(String.valueOf(boatMonth[i]
					.getIncome()));
			boatMonthlyReportEntity[i].setRecepNum(String.valueOf(boatMonth[i]
					.getRecepNum()));
			boatMonthlyReportEntity[i].setReporter(String.valueOf(boatMonth[i]
					.getReporter()));
			String status = getstatus(String.valueOf(boatMonth[i].getStatus()));
			boatMonthlyReportEntity[i].setStatus(status);
			boatMonthlyReportEntity[i].setWriteDate(TimeUtils
					.getMonth(boatMonth[i].getWriteDate()));
			appMonthList.add(boatMonthlyReportEntity[i]);
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
