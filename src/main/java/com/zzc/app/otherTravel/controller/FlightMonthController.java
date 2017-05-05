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
import com.zzc.app.otherTravel.entity.FlightMonthlyReportEntity;
import com.zzc.web.otherTravel.entity.FlightMonthly;
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
 * @FileName: FlightMonthController.java
 * @Version: 1.0
 * @About: 保存/展示空中飞行项目接口
 */
@Controller
@RequestMapping("/appservice/appFlightMonthController")
public class FlightMonthController {
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
	public FlightMonthlyReportEntity ifhavemonthdata(String userId) {
		List<OtherTravelInfo> otherTravelInfolist = systemService.findHql(
				"from OtherTravelInfo ot  where ot.id=?", userId);
		String repsonse = null;
		FlightMonthlyReportEntity flightMonthlyReportEntity = new FlightMonthlyReportEntity();
		if (otherTravelInfolist.size() > 0) {
			OtherTravelInfo otherTravelInfo = otherTravelInfolist.get(0);
			String uid = otherTravelInfo.getId();
			String lastMonth = TimeUtils.getLastMonth();
			// 获取当前年份飞行项目的月报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"SELECT * FROM `t_flight_monthly` tf WHERE tf.otravel_id=? AND tf.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				FlightMonthly flightMonthly = systemService.get(
						FlightMonthly.class, (String) list.get(0).get("ID"));

				flightMonthlyReportEntity.setDates(String.valueOf(flightMonthly
						.getSmonth()));
				flightMonthlyReportEntity.setUserId(userId);
				flightMonthlyReportEntity.setBusinessincome(String
						.valueOf(flightMonthly.getBusinessincome()));
				flightMonthlyReportEntity.setFlighttimes(String
						.valueOf(flightMonthly.getFlighttimes()));
				flightMonthlyReportEntity.setReceptionnum(String
						.valueOf(flightMonthly.getReceptionnum()));
				flightMonthlyReportEntity.setReporter(String
						.valueOf(flightMonthly.getReporter()));
				String status = getstatus(String.valueOf(flightMonthly
						.getStatus()));
				flightMonthlyReportEntity.setStatus(status);
				if (null != flightMonthly.getWriteDate()) {

					flightMonthlyReportEntity.setWriteDate(TimeUtils
							.getMonth(flightMonthly.getWriteDate()));
					// flightMonthlyReportEntity;
				}
			} else {
				FlightMonthlyReportEntity flightMonthlyReportEntity1 = new FlightMonthlyReportEntity();
				flightMonthlyReportEntity1.setDates(TimeUtils.getLastMonth());
				flightMonthlyReportEntity1
						.setStatus(getstatus(Status.noSumbit));
				repsonse = JSON.toJSONString(flightMonthlyReportEntity1);
				return flightMonthlyReportEntity1;
			}

		}
		return flightMonthlyReportEntity;

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
		FlightMonthly flightMonthly = new FlightMonthly();
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
							"SELECT * FROM `t_flight_monthly` tf WHERE tf.oid=? AND tf.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				flightMonthly = systemService.get(FlightMonthly.class,
						(String) list.get(0).get("ID"));
			}
			flightMonthly.setOtherTravelInfo(otherTravelInfo);
			flightMonthly.setOtid(userId);
			flightMonthly.setDates(new SimpleDateFormat("yyyy-MM").parse(node
					.getString("dates")));
			flightMonthly.setBusinessincome(node.getString("businessincome")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("businessincome")));
			flightMonthly.setFlighttimes(node.getString("flighttimes").equals(
					"") ? 0 : Integer.valueOf(node.getString("flighttimes")));
			flightMonthly.setReceptionnum(node.getString("receptionnum")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("receptionnum")));
			flightMonthly.setSmonth(node.getString("dates"));
			flightMonthly.setReporter(node.getString("reporter"));
			// flightMonthly.setWriteDate(new Date());
			if (flag.equals("1")) {
				Date date = new Date();
				flightMonthly.setWriteDate(date);
				flightMonthly.setStatus(Status.noAudit);
				systemService.saveOrUpdate(flightMonthly);
				repsonse = "success";
			} else if (flag.equals("0")) {
				flightMonthly.setStatus(Status.noSumbit);
				systemService.saveOrUpdate(flightMonthly);
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
		List<FlightMonthly> monthList = this.systemService
				.findHql(
						"from FlightMonthly fm where fm.status <> 1 and fm.otherTravelInfo=?",
						otherTravelInfo);
		List<FlightMonthlyReportEntity> appMonthList = new ArrayList<>();
		FlightMonthly flightMonth[] = new FlightMonthly[monthList.size()];
		FlightMonthlyReportEntity flightMonthlyReportEntity[] = new FlightMonthlyReportEntity[monthList
				.size()];
		for (int i = 0; i < monthList.size(); i++) {
			flightMonth[i] = monthList.get(i);
			flightMonthlyReportEntity[i] = new FlightMonthlyReportEntity();
			flightMonthlyReportEntity[i].setDates(String.valueOf(flightMonth[i]
					.getSmonth()));
			flightMonthlyReportEntity[i].setUserId(userId);
			flightMonthlyReportEntity[i].setBusinessincome(String
					.valueOf(flightMonth[i].getBusinessincome()));
			flightMonthlyReportEntity[i].setFlighttimes(String
					.valueOf(flightMonth[i].getFlighttimes()));
			flightMonthlyReportEntity[i].setReceptionnum(String
					.valueOf(flightMonth[i].getReceptionnum()));
			flightMonthlyReportEntity[i].setReporter(String
					.valueOf(flightMonth[i].getReporter()));
			String status = getstatus(String
					.valueOf(flightMonth[i].getStatus()));
			flightMonthlyReportEntity[i].setStatus(status);
			flightMonthlyReportEntity[i].setWriteDate(TimeUtils
					.getMonth(flightMonth[i].getWriteDate()));
			appMonthList.add(flightMonthlyReportEntity[i]);
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
