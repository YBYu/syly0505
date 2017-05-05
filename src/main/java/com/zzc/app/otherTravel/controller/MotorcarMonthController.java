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
import com.zzc.app.otherTravel.entity.MotorcarMonthlyReportEntity;
import com.zzc.web.otherTravel.entity.MotorcarMonthly;
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
 * @About: 保存/展示动车项目接口
 */
@Controller
@RequestMapping("/appservice/appMotorcarMonthController")
public class MotorcarMonthController {
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
	public MotorcarMonthlyReportEntity ifhavemonthdata(String userId) {
		List<OtherTravelInfo> otherTravelInfolist = systemService.findHql(
				"from OtherTravelInfo ot  where ot.id=?", userId);
		String repsonse = null;
		MotorcarMonthlyReportEntity motorcarMonthlyReportEntity = new MotorcarMonthlyReportEntity();
		if (otherTravelInfolist.size() > 0) {
			OtherTravelInfo otherTravelInfo = otherTravelInfolist.get(0);
			String uid = otherTravelInfo.getId();
			String lastMonth = TimeUtils.getLastMonth();
			// 获取当前年份动车项目的月报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"SELECT * FROM `t_motorcar_monthly` tm WHERE tm.otravel_id=? AND tm.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				MotorcarMonthly motorcarMonthly = systemService.get(
						MotorcarMonthly.class, (String) list.get(0).get("ID"));

				motorcarMonthlyReportEntity.setDates(String
						.valueOf(motorcarMonthly.getSmonth()));
				motorcarMonthlyReportEntity.setUserId(userId);
				motorcarMonthlyReportEntity.setInnum(String
						.valueOf(motorcarMonthly.getInnum()));
				motorcarMonthlyReportEntity.setOutnum(String
						.valueOf(motorcarMonthly.getOutnum()));
				motorcarMonthlyReportEntity.setThroughput(String
						.valueOf(motorcarMonthly.getThroughput()));
				motorcarMonthlyReportEntity.setReporter(String
						.valueOf(motorcarMonthly.getReporter()));
				String status = getstatus(String.valueOf(motorcarMonthly
						.getStatus()));
				motorcarMonthlyReportEntity.setStatus(status);
				if (null != motorcarMonthly.getWriteDate()) {
					motorcarMonthlyReportEntity.setWriteDate(TimeUtils
							.getMonth(motorcarMonthly.getWriteDate()));
				}
				// flightMonthlyReportEntity;
			} else {
				MotorcarMonthlyReportEntity motorcarMonthlyReportEntity1 = new MotorcarMonthlyReportEntity();
				motorcarMonthlyReportEntity1.setDates(TimeUtils.getLastMonth());
				motorcarMonthlyReportEntity1
						.setStatus(getstatus(Status.noSumbit));
				repsonse = JSON.toJSONString(motorcarMonthlyReportEntity1);
				return motorcarMonthlyReportEntity1;
			}

		}
		return motorcarMonthlyReportEntity;

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
		MotorcarMonthly motorcarMonthly = new MotorcarMonthly();
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
							"SELECT * FROM `t_motorcar_monthly` tm WHERE tm.oid=? AND tm.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				motorcarMonthly = systemService.get(MotorcarMonthly.class,
						(String) list.get(0).get("ID"));
			}
			motorcarMonthly.setOtherTravelInfo(otherTravelInfo);
			motorcarMonthly.setOtid(userId);
			motorcarMonthly.setDates(new SimpleDateFormat("yyyy-MM").parse(node
					.getString("dates")));
			motorcarMonthly.setInnum(node.getString("innum"));
			motorcarMonthly.setOutnum(node.getString("outnum"));
			motorcarMonthly.setThroughput(node.getString("throughput"));
			motorcarMonthly.setSmonth(node.getString("dates"));
			motorcarMonthly.setReporter(node.getString("reporter"));
			if (flag.equals("1")) {
				Date date = new Date();
				motorcarMonthly.setWriteDate(date);
				motorcarMonthly.setStatus(Status.noAudit);
				systemService.saveOrUpdate(motorcarMonthly);
				repsonse = "success";
			} else if (flag.equals("0")) {
				motorcarMonthly.setStatus(Status.noSumbit);
				systemService.saveOrUpdate(motorcarMonthly);
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
		List<MotorcarMonthly> monthList = this.systemService
				.findHql(
						"from MotorcarMonthly mc where mc.status<>1 and mc.otherTravelInfo=? ",
						otherTravelInfo);
		List<MotorcarMonthlyReportEntity> appMonthList = new ArrayList<>();
		MotorcarMonthly motorcarMonth[] = new MotorcarMonthly[monthList.size()];
		MotorcarMonthlyReportEntity motorcarMonthlyReportEntity[] = new MotorcarMonthlyReportEntity[monthList
				.size()];
		for (int i = 0; i < monthList.size(); i++) {
			motorcarMonth[i] = monthList.get(i);
			motorcarMonthlyReportEntity[i] = new MotorcarMonthlyReportEntity();
			motorcarMonthlyReportEntity[i].setDates(String
					.valueOf(motorcarMonth[i].getSmonth()));
			motorcarMonthlyReportEntity[i].setUserId(userId);
			motorcarMonthlyReportEntity[i].setInnum(String
					.valueOf(motorcarMonth[i].getInnum()));
			motorcarMonthlyReportEntity[i].setOutnum(String
					.valueOf(motorcarMonth[i].getOutnum()));
			motorcarMonthlyReportEntity[i].setThroughput(String
					.valueOf(motorcarMonth[i].getThroughput()));
			motorcarMonthlyReportEntity[i].setReporter(String
					.valueOf(motorcarMonth[i].getReporter()));
			String status = getstatus(String.valueOf(motorcarMonth[i]
					.getStatus()));
			motorcarMonthlyReportEntity[i].setStatus(status);

			motorcarMonthlyReportEntity[i].setWriteDate(TimeUtils
					.getMonth(motorcarMonth[i].getWriteDate()));

			appMonthList.add(motorcarMonthlyReportEntity[i]);
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
