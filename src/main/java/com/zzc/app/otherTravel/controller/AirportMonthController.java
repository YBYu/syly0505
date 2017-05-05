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
import com.zzc.app.otherTravel.entity.AirportMonthlyReportEntity;
import com.zzc.web.otherTravel.entity.AirportMonthly;
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
 * @FileName: AirportMonthController.java
 * @Version: 1.0
 * @About: 保存/展示空中飞行项目接口
 */
@Controller
@RequestMapping("/appservice/appAirportMonthController")
public class AirportMonthController {
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
	@RequestMapping(params = "ifhavemonthdata")
	@ResponseBody
	public AirportMonthlyReportEntity ifhavemonthdata(String userId) {
		List<OtherTravelInfo> otherTravelInfolist = systemService.findHql(
				"from OtherTravelInfo ot  where ot.id=?", userId);
		String repsonse = null;
		AirportMonthlyReportEntity airportMonthlyReportEntity = new AirportMonthlyReportEntity();
		if (otherTravelInfolist.size() > 0) {
			OtherTravelInfo otherTravelInfo = otherTravelInfolist.get(0);
			String uid = otherTravelInfo.getId();
			String lastMonth = TimeUtils.getLastMonth();
			// 获取当前年份飞行项目的月报列表
			List<Map<String, Object>> list = systemService
					.findForJdbc(
							"SELECT * FROM `t_airport_monthly` tf WHERE tf.oid=? AND tf.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				AirportMonthly airportMonthly = systemService.get(
						AirportMonthly.class, (String) list.get(0).get("ID"));

				airportMonthlyReportEntity.setDates(String
						.valueOf(airportMonthly.getSmonth()));
				airportMonthlyReportEntity.setUserId(userId);
				airportMonthlyReportEntity.setPlansortie(String
						.valueOf(airportMonthly.getPlansortie()));
				airportMonthlyReportEntity.setActualsortie(String
						.valueOf(airportMonthly.getActualsortie()));
				airportMonthlyReportEntity.setPlansortieyear(String
						.valueOf(airportMonthly.getPlansortieyear()));
				airportMonthlyReportEntity.setPlanouttraveller(String
						.valueOf(airportMonthly.getPlanouttraveller()));
				airportMonthlyReportEntity.setActualouttraveller(String
						.valueOf(airportMonthly.getActualouttraveller()));

				airportMonthlyReportEntity.setPlanouttravelleryear(String
						.valueOf(airportMonthly.getPlanouttravelleryear()));
				airportMonthlyReportEntity.setPlanoutwill(String
						.valueOf(airportMonthly.getPlanoutwill()));
				airportMonthlyReportEntity.setActualoutwill(String
						.valueOf(airportMonthly.getActualoutwill()));
				airportMonthlyReportEntity.setPlanoutwillyear(String
						.valueOf(airportMonthly.getPlanoutwillyear()));
				airportMonthlyReportEntity.setPlanIntegerraveller(String
						.valueOf(airportMonthly.getPlanIntegerraveller()));

				airportMonthlyReportEntity.setActualIntegerraveller(String
						.valueOf(airportMonthly.getActualIntegerraveller()));
				airportMonthlyReportEntity.setPlanIntegerravelleryear(String
						.valueOf(airportMonthly.getPlanIntegerravelleryear()));
				airportMonthlyReportEntity.setPlaninwill(String
						.valueOf(airportMonthly.getPlaninwill()));
				airportMonthlyReportEntity.setActualinwill(String
						.valueOf(airportMonthly.getActualinwill()));
				airportMonthlyReportEntity.setPlaninwillyear(String
						.valueOf(airportMonthly.getPlaninwillyear()));

				airportMonthlyReportEntity.setPlanthroughputtraveller(String
						.valueOf(airportMonthly.getPlanthroughputtraveller()));
				airportMonthlyReportEntity
						.setActualthroughputtraveller(String
								.valueOf(airportMonthly
										.getActualthroughputtraveller()));
				airportMonthlyReportEntity
						.setPlanthroughputtravelleryear(String
								.valueOf(airportMonthly
										.getPlanthroughputtravelleryear()));
				airportMonthlyReportEntity.setPlanthroughputwill(String
						.valueOf(airportMonthly.getPlanthroughputwill()));
				airportMonthlyReportEntity.setActualthroughputwill(String
						.valueOf(airportMonthly.getActualthroughputwill()));

				airportMonthlyReportEntity.setPlanthroughputwillyear(String
						.valueOf(airportMonthly.getPlanthroughputwillyear()));
				airportMonthlyReportEntity.setReporter(String
						.valueOf(airportMonthly.getReporter()));
				if (null != airportMonthly.getReportdate()) {

					airportMonthlyReportEntity.setReportdate(TimeUtils
							.getMonth(airportMonthly.getReportdate()));

				}
				String status = getstatus(String.valueOf(airportMonthly
						.getStatus()));

				airportMonthlyReportEntity.setStatus(status);
				// flightMonthlyReportEntity;
			} else {
				AirportMonthlyReportEntity airportMonthlyReportEntity1 = new AirportMonthlyReportEntity();
				airportMonthlyReportEntity1.setDates(TimeUtils.getLastMonth());
				airportMonthlyReportEntity1
						.setStatus(getstatus(Status.noSumbit));
				repsonse = JSON.toJSONString(airportMonthlyReportEntity1);
				return airportMonthlyReportEntity1;
			}

		}
		return airportMonthlyReportEntity;

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
		AirportMonthly airportMonthly = new AirportMonthly();
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
							"SELECT * FROM `t_airport_monthly` tf WHERE tf.oid=? AND tf.smonth LIKE ?",
							uid, lastMonth);
			if (list.size() > 0) {
				airportMonthly = systemService.get(AirportMonthly.class,
						(String) list.get(0).get("ID"));
			}
			airportMonthly.setOtherTravelInfo(otherTravelInfo);
			airportMonthly.setSmonth(node.getString("dates"));
			airportMonthly.setOtid(userId);
			airportMonthly.setDates(new SimpleDateFormat("yyyy-MM").parse(node
					.getString("dates")));
			airportMonthly.setPlansortie(node.getString("plansortie")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("plansortie")));
			airportMonthly.setActualsortie(node.getString("actualsortie")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("actualsortie")));

			airportMonthly.setPlansortieyear(node.getString("plansortieyear")
					.equals("") ? 0 : Integer.valueOf(node
					.getString("plansortieyear")));
			airportMonthly.setPlanouttraveller(node.getString(
					"planouttraveller").equals("") ? 0.0 : Double.valueOf(node
					.getString("planouttraveller")));
			airportMonthly.setActualouttraveller(node.getString(
					"actualouttraveller").equals("") ? 0.0 : Double
					.valueOf(node.getString("actualouttraveller")));
			airportMonthly.setPlanouttravelleryear(node.getString(
					"planouttravelleryear").equals("") ? 0.0 : Double
					.valueOf(node.getString("planouttravelleryear")));
			airportMonthly.setPlanoutwill(node.getString("planoutwill").equals(
					"") ? 0.0 : Double.valueOf(node.getString("planoutwill")));

			airportMonthly.setActualoutwill(node.getString("actualoutwill")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("actualoutwill")));
			airportMonthly.setPlanoutwillyear(node.getString("planoutwillyear")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("planoutwillyear")));
			airportMonthly.setPlanIntegerraveller(node.getString(
					"planIntegerraveller").equals("") ? 0.0 : Double
					.valueOf(node.getString("planIntegerraveller")));
			airportMonthly.setActualIntegerraveller(node.getString(
					"actualIntegerraveller").equals("") ? 0.0 : Double
					.valueOf(node.getString("actualIntegerraveller")));
			airportMonthly.setPlanIntegerravelleryear(node.getString(
					"planIntegerravelleryear").equals("") ? 0.0 : Double
					.valueOf(node.getString("planIntegerravelleryear")));

			airportMonthly.setPlaninwill(node.getString("planinwill")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("planinwill")));
			airportMonthly.setActualinwill(node.getString("actualinwill")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("actualinwill")));
			airportMonthly.setPlaninwillyear(node.getString("planinwillyear")
					.equals("") ? 0.0 : Double.valueOf(node
					.getString("planinwillyear")));
			airportMonthly.setPlanthroughputtraveller(node.getString(
					"planthroughputtraveller").equals("") ? 0.0 : Double
					.valueOf(node.getString("planthroughputtraveller")));
			airportMonthly.setActualthroughputtraveller(node.getString(
					"actualthroughputtraveller").equals("") ? 0.0 : Double
					.valueOf(node.getString("actualthroughputtraveller")));

			airportMonthly.setPlanthroughputtravelleryear(node.getString(
					"planthroughputtravelleryear").equals("") ? 0.0 : Double
					.valueOf(node.getString("planthroughputtravelleryear")));
			airportMonthly.setPlanthroughputwill(node.getString(
					"planthroughputwill").equals("") ? 0.0 : Double
					.valueOf(node.getString("planthroughputwill")));
			airportMonthly.setActualthroughputwill(node.getString(
					"actualthroughputwill").equals("") ? 0.0 : Double
					.valueOf(node.getString("actualthroughputwill")));
			airportMonthly.setPlanthroughputwillyear(node.getString(
					"planthroughputwillyear").equals("") ? 0.0 : Double
					.valueOf(node.getString("planthroughputwillyear")));
			airportMonthly.setReporter(node.getString("reporter"));

			// airportMonthly.setReportdate(new Date());
			// airportMonthly.setReporter();

			// airportMonthly.setStatus(Status.noAudit);
			// airportMonthly.setReporter(node.getString("repoter"));
			if (flag.equals("1")) {
				Date date = new Date();
				airportMonthly.setReportdate(date);
				airportMonthly.setStatus(Status.noAudit);

				systemService.saveOrUpdate(airportMonthly);

				repsonse = "success";
			} else if (flag.equals("0")) {
				airportMonthly.setStatus(Status.noSumbit);
				systemService.saveOrUpdate(airportMonthly);
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

	@RequestMapping(params = "allmonthdata")
	@ResponseBody
	public String getAllMonthData(HttpServletRequest request)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String userId = request.getParameter("userId");
		String response = null;
		List<OtherTravelInfo> otherTravelInfolist = systemService.findHql(
				"from OtherTravelInfo ot  where ot.id=?", userId);
		OtherTravelInfo otherTravelInfo = otherTravelInfolist.get(0);
		List<AirportMonthly> monthList = this.systemService
				.findHql(
						"from AirportMonthly fm where  fm.status<>1 and fm.otherTravelInfo=?",
						otherTravelInfo);
		List<AirportMonthlyReportEntity> appMonthList = new ArrayList<>();
		AirportMonthly airportMonth[] = new AirportMonthly[monthList.size()];
		AirportMonthlyReportEntity airportMonthlyReportEntity[] = new AirportMonthlyReportEntity[monthList
				.size()];
		for (int i = 0; i < monthList.size(); i++) {
			airportMonth[i] = monthList.get(i);
			airportMonthlyReportEntity[i] = new AirportMonthlyReportEntity();
			airportMonthlyReportEntity[i].setDates(String
					.valueOf(airportMonth[i].getSmonth()));
			airportMonthlyReportEntity[i].setUserId(userId);
			airportMonthlyReportEntity[i].setPlansortie(String
					.valueOf(airportMonth[i].getPlansortie()));
			airportMonthlyReportEntity[i].setActualsortie(String
					.valueOf(airportMonth[i].getActualsortie()));
			airportMonthlyReportEntity[i].setPlansortieyear(String
					.valueOf(airportMonth[i].getPlansortieyear()));
			airportMonthlyReportEntity[i].setPlanouttraveller(String
					.valueOf(airportMonth[i].getPlanouttraveller()));
			airportMonthlyReportEntity[i].setActualouttraveller(String
					.valueOf(airportMonth[i].getActualouttraveller()));

			airportMonthlyReportEntity[i].setPlanouttravelleryear(String
					.valueOf(airportMonth[i].getPlanouttravelleryear()));
			airportMonthlyReportEntity[i].setPlanoutwill(String
					.valueOf(airportMonth[i].getPlanoutwill()));
			airportMonthlyReportEntity[i].setActualoutwill(String
					.valueOf(airportMonth[i].getActualoutwill()));
			airportMonthlyReportEntity[i].setPlanoutwillyear(String
					.valueOf(airportMonth[i].getPlanoutwillyear()));
			airportMonthlyReportEntity[i].setPlanIntegerraveller(String
					.valueOf(airportMonth[i].getPlanIntegerraveller()));

			airportMonthlyReportEntity[i].setActualIntegerraveller(String
					.valueOf(airportMonth[i].getActualIntegerraveller()));
			airportMonthlyReportEntity[i].setPlanIntegerravelleryear(String
					.valueOf(airportMonth[i].getPlanIntegerravelleryear()));
			airportMonthlyReportEntity[i].setPlaninwill(String
					.valueOf(airportMonth[i].getPlaninwill()));
			airportMonthlyReportEntity[i].setActualinwill(String
					.valueOf(airportMonth[i].getActualinwill()));
			airportMonthlyReportEntity[i].setPlaninwillyear(String
					.valueOf(airportMonth[i].getPlaninwillyear()));

			airportMonthlyReportEntity[i].setPlanthroughputtraveller(String
					.valueOf(airportMonth[i].getPlanthroughputtraveller()));
			airportMonthlyReportEntity[i].setActualthroughputtraveller(String
					.valueOf(airportMonth[i].getActualthroughputtraveller()));
			airportMonthlyReportEntity[i].setPlanthroughputtravelleryear(String
					.valueOf(airportMonth[i].getPlanthroughputtravelleryear()));
			airportMonthlyReportEntity[i].setPlanthroughputwill(String
					.valueOf(airportMonth[i].getPlanthroughputwill()));
			airportMonthlyReportEntity[i].setActualthroughputwill(String
					.valueOf(airportMonth[i].getActualthroughputwill()));

			airportMonthlyReportEntity[i].setPlanthroughputwillyear(String
					.valueOf(airportMonth[i].getPlanthroughputwillyear()));
			airportMonthlyReportEntity[i].setReporter(String
					.valueOf(airportMonth[i].getReporter()));
			airportMonthlyReportEntity[i].setReportdate(TimeUtils
					.getMonth(airportMonth[i].getReportdate()));
			String status = getstatus(String.valueOf(airportMonth[i]
					.getStatus()));
			airportMonthlyReportEntity[i].setStatus(status);
			appMonthList.add(airportMonthlyReportEntity[i]);

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
