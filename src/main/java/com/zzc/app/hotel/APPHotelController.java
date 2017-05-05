package com.zzc.app.hotel;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zzc.app.hotel.entity.HotelAnnual;
import com.zzc.app.hotel.entity.HotelQuarter;
import com.zzc.app.login.entity.APPHotel;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.HotelmanageSta;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/apphotelcontroller")
public class APPHotelController {

	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 
	 * 检查季报是否填报
	 * 
	 * @param request
	 * @param response
	 * @return 是否填报
	 */
	@RequestMapping(params = "checkQuarterReportIsFilled")
	@ResponseBody
	public HotelQuarter checkQuarterReportIsFilled(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 获取用户ID
		String userId = request.getParameter("userId");

		// 获取上一次的年和季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if ("0".equals(nowSeason)) {
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}

		// 用户ID即为酒店ID
		String HotelId = userId;

		String sql = "SELECT ID FROM T_HOTEL_QUARTERLY T WHERE T.`QUARTER`=? AND T.`YEAR`=? AND T.HID=?";
		List<Map<String, Object>> list = systemService.findForJdbc(sql,
				new Object[] { nowSeason, nowYear, HotelId });

		HotelQuarter hotelQuarter = new HotelQuarter();
		com.zzc.web.htoal.entity.HotelQuarter oriHotelQuarter = new com.zzc.web.htoal.entity.HotelQuarter();

		// year; // 年份
		hotelQuarter.setYear(nowYear);
		// quarter;//1 2 3 4 第一二三四季度
		hotelQuarter.setQuarter(nowSeason);

		// 是否存在数据
		if (list.size() == 0) {
			return hotelQuarter;
		} else {
			oriHotelQuarter = systemService.getEntity(
					com.zzc.web.htoal.entity.HotelQuarter.class, list.get(0)
							.get("ID").toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// userId; // 用户id
			hotelQuarter.setUserId(userId);
			// writer; // 填报人
			hotelQuarter.setWriter(oriHotelQuarter.getWriter());
			// mobile; // 手机号码
			hotelQuarter.setMobile(oriHotelQuarter.getMobile());
			// writerDate; // 填报日期
			hotelQuarter.setWriterDate(oriHotelQuarter.getWriterDate());
			// totalIncome; //总收入(万元)
			hotelQuarter.setTotalIncome(String.valueOf(oriHotelQuarter
					.getTotalIncome()));
			// canteenIncome; // 餐厅收入(万元)
			hotelQuarter.setCanteenIncome(String.valueOf(oriHotelQuarter
					.getCanteenIncome()));
			// roomIncome; // 客房收入(万元)
			hotelQuarter.setRoomIncome(String.valueOf(oriHotelQuarter
					.getRoomIncome()));
			// otherIncome; // 其他收入(万元)
			hotelQuarter.setOtherIncome(String.valueOf(oriHotelQuarter
					.getOtherIncome()));
			// roomnum; // 客房数量
			hotelQuarter
					.setRoomnum(String
							.valueOf(oriHotelQuarter.getRoomnum() == null ? ""
									: oriHotelQuarter.getRoomnum()));
			// bednum; // 床位数量
			hotelQuarter
					.setBednum(String
							.valueOf(oriHotelQuarter.getBednum() == null ? ""
									: oriHotelQuarter.getBednum()));
			// realNights;//实际出租夜间数
			hotelQuarter.setRealNights(String.valueOf(oriHotelQuarter
					.getRealNights() == null ? "" : oriHotelQuarter
					.getRealNights()));
			// canNights;//可供出租夜间数
			hotelQuarter.setCanNights(String.valueOf(oriHotelQuarter
					.getCanNights() == null ? "" : oriHotelQuarter
					.getCanNights()));
			hotelQuarter.setStatus(this.getStatus(oriHotelQuarter));
			// 平均房价
			hotelQuarter.setAvgHotelPrice(oriHotelQuarter.getAvgHotelPrice());
		}

		return hotelQuarter;
	}

	/**
	 * 
	 * 酒店季报填报
	 * 
	 * @param request
	 * @param response
	 * @return 是否成功标志
	 */
	@RequestMapping(params = "fillQuarterReport")
	@ResponseBody
	public String fillQuarterReport(HttpServletRequest request,
			HttpServletResponse response) {
		String tip = "success";
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String msg = request.getParameter("param");
		String flag = request.getParameter("flag");

		HotelQuarter hotelQuarter = null;

		try {
			hotelQuarter = JSON.parseObject(msg, HotelQuarter.class);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "数据转化失败!";
			return tip;
		}

		String userId = hotelQuarter.getUserId();
		// 用户ID即为酒店ID
		String hotelId = userId;
		// 获取上一次的年和季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if ("0".equals(nowSeason)) {
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}

		// 用户ID即为酒店ID
		String HotelId = userId;

		try {
			// 组装保存对象
			com.zzc.web.htoal.entity.HotelQuarter newHotelQuarter = new com.zzc.web.htoal.entity.HotelQuarter();
			String sql = "SELECT id FROM T_HOTEL_QUARTERLY T WHERE T.`QUARTER`=? AND T.`YEAR`=? AND T.HID=?";
			List<Map<String, Object>> list = systemService.findForJdbc(sql,
					new Object[] { nowSeason, nowYear, HotelId });
			if (list == null || list.size() == 0) {
				newHotelQuarter.setId(null);
			} else {
				newHotelQuarter.setId(list.get(0).get("id").toString());
			}
			newHotelQuarter.setHid(hotelId);
			newHotelQuarter.setHotelQid(hotelId);
			newHotelQuarter.setYear(hotelQuarter.getYear());
			newHotelQuarter.setQuarter(Integer.parseInt(hotelQuarter
					.getQuarter() == null
					|| hotelQuarter.getQuarter().length() == 0 ? "0"
					: hotelQuarter.getQuarter()));
			newHotelQuarter.setWriter(hotelQuarter.getWriter());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			newHotelQuarter.setWriterDate(sdf.format(new Date()));
			newHotelQuarter.setTotalIncome(Double.parseDouble(hotelQuarter
					.getTotalIncome() == null
					|| hotelQuarter.getTotalIncome().length() == 0 ? "0"
					: hotelQuarter.getTotalIncome()));
			newHotelQuarter.setCanteenIncome(Double.parseDouble(hotelQuarter
					.getCanteenIncome() == null
					|| hotelQuarter.getCanteenIncome().length() == 0 ? "0"
					: hotelQuarter.getCanteenIncome()));
			newHotelQuarter.setRoomIncome(Double.parseDouble(hotelQuarter
					.getRoomIncome() == null
					|| hotelQuarter.getRoomIncome().length() == 0 ? "0"
					: hotelQuarter.getRoomIncome()));
			newHotelQuarter.setOtherIncome(Double.parseDouble(hotelQuarter
					.getOtherIncome() == null
					|| hotelQuarter.getOtherIncome().length() == 0 ? "0"
					: hotelQuarter.getOtherIncome()));
			newHotelQuarter.setRoomnum(Integer.parseInt(hotelQuarter
					.getRoomnum() == null
					|| hotelQuarter.getRoomnum().length() == 0 ? "0"
					: hotelQuarter.getRoomnum()));
			newHotelQuarter
					.setBednum(Integer.parseInt(hotelQuarter.getBednum() == null
							|| hotelQuarter.getBednum().length() == 0 ? "0"
							: hotelQuarter.getBednum()));
			newHotelQuarter.setRealNights(Integer.parseInt(hotelQuarter
					.getRealNights() == null
					|| hotelQuarter.getRealNights().length() == 0 ? "0"
					: hotelQuarter.getRealNights()));
			newHotelQuarter.setCanNights(Integer.parseInt(hotelQuarter
					.getCanNights() == null
					|| hotelQuarter.getCanNights().length() == 0 ? "0"
					: hotelQuarter.getCanNights()));
			newHotelQuarter.setStatus("1".equals(flag) ? Status.noAudit
					: Status.noSumbit);
			newHotelQuarter.setMobile(hotelQuarter.getMobile());
			Hotelmanage hotelmanage = systemService.getEntity(
					Hotelmanage.class, hotelId);
			newHotelQuarter.setHotelmanage(hotelmanage);
			newHotelQuarter.setManager(hotelmanage.getManager());
			newHotelQuarter.setUnitname(hotelmanage.getUnitname());
			newHotelQuarter
					.setOrganizationNum(hotelmanage.getOrganizationNum());
			newHotelQuarter.setCountryState(Status.noSumbit);
			newHotelQuarter.setAvgHotelPrice(hotelQuarter.getAvgHotelPrice());

			// 增加积分
			int year = Integer.parseInt(hotelQuarter.getYear());
			int quarter = Integer.parseInt(hotelQuarter.getQuarter());
			boolean rs = ReportTimeCheck.quarterTimeCheck(year, quarter);
			if (rs) {
				newHotelQuarter.setScore(10);
				UserScoreUtils.scoreChange(10, hotelId);
			} else {
				newHotelQuarter.setScore(5);
				UserScoreUtils.scoreChange(5, hotelId);
			}
			systemService.saveOrUpdate(newHotelQuarter);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "保存异常!";
		}
		return tip;
	}

	/**
	 * 
	 * 检查年报是否填报
	 * 
	 * @param request
	 * @param response
	 * @return 是否填报
	 */
	@RequestMapping(params = "checkAnnualReportIsFilled")
	@ResponseBody
	public HotelAnnual checkAnnualReportIsFilled(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 获取用户ID
		String userId = request.getParameter("userId");

		// 获取上一次的年和季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);

		// 用户ID即为酒店ID
		String HotelId = userId;

		String sql = "SELECT ID FROM T_HOTEL_ANNUAL T WHERE T.`YEAR`=? AND T.HID=?";
		List<Map<String, Object>> list = systemService.findForJdbc(sql,
				new Object[] { nowYear, HotelId });

		HotelAnnual hotelAnnual = new HotelAnnual();
		com.zzc.web.htoal.entity.HotelAnnual oriHotelAnnual = new com.zzc.web.htoal.entity.HotelAnnual();

		// year; // 年份
		hotelAnnual.setYear(nowYear);

		// 是否存在数据
		if (list.size() == 0) {
			return hotelAnnual;
		} else {
			oriHotelAnnual = systemService.getEntity(
					com.zzc.web.htoal.entity.HotelAnnual.class, list.get(0)
							.get("ID").toString());
			// userId;// 用户id
			hotelAnnual.setUserId(userId);
			// debt;//年末资产负债
			hotelAnnual.setDebt(String.valueOf(oriHotelAnnual.getDebt()));
			// realNights;//实际出租间夜数
			hotelAnnual.setRealNights(String.valueOf(oriHotelAnnual
					.getRealNights()));
			// canNights;//可提供出租夜数
			hotelAnnual.setCanNights(String.valueOf(oriHotelAnnual
					.getCanNights()));
			// streamTotal;//流动资产小计
			hotelAnnual.setStreamTotal(String.valueOf(oriHotelAnnual
					.getStreamTotal()));
			// manageCost;//管理费用
			hotelAnnual.setManageCost(String.valueOf(oriHotelAnnual
					.getManageCost()));
			// longInvest;//长期投资
			hotelAnnual.setLongInvest(String.valueOf(oriHotelAnnual
					.getLongInvest()));
			// taxes;//税金
			hotelAnnual.setTaxes(String.valueOf(oriHotelAnnual.getTaxes()));
			// fixedAssets;//固定资产
			hotelAnnual.setFixedAssets(String.valueOf(oriHotelAnnual
					.getFixedAssets()));
			// travelExpense;//差旅费
			hotelAnnual.setTravelExpense(String.valueOf(oriHotelAnnual
					.getTravelExpense()));
			// fixedPrice;//固定资产原价
			hotelAnnual.setFixedPrice(String.valueOf(oriHotelAnnual
					.getFixedPrice()));
			// financialCost;//财务费用
			hotelAnnual.setFinancialCost(String.valueOf(oriHotelAnnual
					.getFinancialCost()));
			// depreciation;//累计折旧
			hotelAnnual.setDepreciation(String.valueOf(oriHotelAnnual
					.getDepreciation()));
			// interestCost;//利息支出
			hotelAnnual.setInterestCost(String.valueOf(oriHotelAnnual
					.getInterestCost()));
			// yearDepreciation;//本年折旧
			hotelAnnual.setYearDepreciation(String.valueOf(oriHotelAnnual
					.getYearDepreciation()));
			// assetTotal;//资产合计
			hotelAnnual.setAssetTotal(String.valueOf(oriHotelAnnual
					.getAssetTotal()));
			// valueVariation;//公允价值变动收益
			hotelAnnual.setValueVariation(String.valueOf(oriHotelAnnual
					.getValueVariation()));
			// liabilitiesTotal;//负债合计
			hotelAnnual.setLiabilitiesTotal(String.valueOf(oriHotelAnnual
					.getLiabilitiesTotal()));
			// operationIncome;//营业收入
			hotelAnnual.setOperationIncome(String.valueOf(oriHotelAnnual
					.getOperationIncome()));
			// possessorTotal;//所有者权益合计
			hotelAnnual.setPossessorTotal(String.valueOf(oriHotelAnnual
					.getPossessorTotal()));
			// oprFeeAndTax;//营业税金及附加
			hotelAnnual.setOprFeeAndTax(String.valueOf(oriHotelAnnual
					.getOprFeeAndTax()));
			// realIncome;//实收资本
			hotelAnnual.setRealIncome(String.valueOf(oriHotelAnnual
					.getRealIncome()));
			// invest;//投资收益
			hotelAnnual.setInvest(String.valueOf(oriHotelAnnual.getInvest()));
			// roomIncome; // 客房收入
			hotelAnnual.setRoomIncome(String.valueOf(oriHotelAnnual
					.getRoomIncome()));
			// canteeIncome; // 餐厅收入
			hotelAnnual.setCanteeIncome(String.valueOf(oriHotelAnnual
					.getCanteeIncome()));
			// otherIncome; // 其他收入
			hotelAnnual.setOtherIncome(String.valueOf(oriHotelAnnual
					.getOtherIncome()));
			// operationCost;//营业成本
			hotelAnnual.setOperationCost(String.valueOf(oriHotelAnnual
					.getOperationCost()));
			// operationFee;//营业费用
			hotelAnnual.setOperationFee(String.valueOf(oriHotelAnnual
					.getOperationFee()));
			// fianceCost;//财务费用
			hotelAnnual.setFianceCost(String.valueOf(oriHotelAnnual
					.getFianceCost()));
			;
			// opreationIntrest;//营业利润
			hotelAnnual.setOpreationIntrest(String.valueOf(oriHotelAnnual
					.getOpreationIntrest()));
			// ownTax;//所得税
			hotelAnnual.setOwnTax(String.valueOf(oriHotelAnnual.getOwnTax()));
			// profitTotal;//利润总额
			hotelAnnual.setProfitTotal(String.valueOf(oriHotelAnnual
					.getProfitTotal()));
			// salary;//本年应付职工薪酬
			hotelAnnual.setSalary(String.valueOf(oriHotelAnnual.getSalary()));
			// college;//大专以上学历人数
			hotelAnnual.setCollege(String.valueOf(oriHotelAnnual.getCollege()));
			// people;//全部从业人员平均人数
			hotelAnnual.setPeople(String.valueOf(oriHotelAnnual.getPeople()));
			// deposit; // 存货
			hotelAnnual.setDeposit(String.valueOf(oriHotelAnnual.getDeposit()));
			// room; // 客房数
			hotelAnnual.setRoom(oriHotelAnnual.getRoom());
			// bed; // 床位数
			hotelAnnual.setBed(oriHotelAnnual.getBed());
			// actualRent; // 实际出租夜数
			hotelAnnual.setActualRent(oriHotelAnnual.getActualRent());
			// forHire; // 可供出租夜数
			hotelAnnual.setForHire(oriHotelAnnual.getForHire());
			hotelAnnual.setStatus(this.getStatus(oriHotelAnnual));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				hotelAnnual.setReportDate(sdf.format(oriHotelAnnual
						.getReportTime()));
			} catch (Exception e) {
				hotelAnnual.setReportDate("");
			}
		}

		return hotelAnnual;
	}

	/**
	 * 酒店年报填报
	 * 
	 * @param request
	 * @param response
	 * @return 是否成功标志
	 */
	@RequestMapping(params = "fillAnnualReport")
	@ResponseBody
	public String fillAnnualReport(HttpServletRequest request,
			HttpServletResponse response) {

		String tip = "success";
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String msg = request.getParameter("param");
		String flag = request.getParameter("flag");

		HotelAnnual hotelAnnual = null;

		try {
			hotelAnnual = JSON.parseObject(msg, HotelAnnual.class);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "数据转化失败!";
			return tip;
		}

		String userId = hotelAnnual.getUserId();
		// 用户ID即为酒店ID
		String hotelId = userId;

		try {
			// 组装保存对象
			com.zzc.web.htoal.entity.HotelAnnual newHotelAnnual = new com.zzc.web.htoal.entity.HotelAnnual();
			// 获取上一次的年和季度
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			String sql = "SELECT id FROM T_HOTEL_ANNUAL T WHERE T.`YEAR`=? AND T.HID=?";
			List<Map<String, Object>> list = systemService.findForJdbc(sql,
					new Object[] { nowYear, hotelId });
			if (list == null || list.size() == 0) {
				newHotelAnnual.setId(null);
			} else {
				newHotelAnnual.setId(list.get(0).get("id").toString());
			}
			// userId;// 用户id
			newHotelAnnual.setHid(hotelId);
			newHotelAnnual.setHotelAid(hotelId);
			newHotelAnnual.setYear(nowYear);
			Hotelmanage hotelmanage = systemService.getEntity(
					Hotelmanage.class, hotelId);
			newHotelAnnual.setHotelmanage(hotelmanage);
			newHotelAnnual.setName(hotelmanage.getUnitname());
			newHotelAnnual.setStatus("1".equals(flag) ? Status.noAudit
					: Status.noSumbit);
			// debt;//年末资产负债
			newHotelAnnual
					.setDebt(Double.parseDouble(hotelAnnual.getDebt() == null
							|| hotelAnnual.getDebt().length() == 0 ? "0"
							: hotelAnnual.getDebt()));
			// realNights;//实际出租间夜数
			newHotelAnnual.setRealNights(Integer.parseInt(hotelAnnual
					.getRealNights() == null
					|| hotelAnnual.getRealNights().length() == 0 ? "0"
					: hotelAnnual.getRealNights()));
			// canNights;//可提供出租夜数
			newHotelAnnual.setCanNights(Integer.parseInt(hotelAnnual
					.getCanNights() == null
					|| hotelAnnual.getCanNights().length() == 0 ? "0"
					: hotelAnnual.getCanNights()));
			// streamTotal;//流动资产小计
			newHotelAnnual.setStreamTotal(Double.parseDouble(hotelAnnual
					.getStreamTotal() == null
					|| hotelAnnual.getStreamTotal().length() == 0 ? "0"
					: hotelAnnual.getStreamTotal()));
			// newHotelAnnual;//管理费用
			newHotelAnnual.setManageCost(Double.parseDouble(hotelAnnual
					.getManageCost() == null
					|| hotelAnnual.getManageCost().length() == 0 ? "0"
					: hotelAnnual.getManageCost()));
			// longInvest;//长期投资
			newHotelAnnual.setLongInvest(Double.parseDouble(hotelAnnual
					.getLongInvest() == null
					|| hotelAnnual.getLongInvest().length() == 0 ? "0"
					: hotelAnnual.getLongInvest()));
			// taxes;//税金
			newHotelAnnual
					.setTaxes(Double.parseDouble(hotelAnnual.getTaxes() == null
							|| hotelAnnual.getTaxes().length() == 0 ? "0"
							: hotelAnnual.getTaxes()));
			// fixedAssets;//固定资产
			newHotelAnnual.setFixedAssets(Double.parseDouble(hotelAnnual
					.getFixedAssets() == null
					|| hotelAnnual.getFixedAssets().length() == 0 ? "0"
					: hotelAnnual.getFixedAssets()));
			// travelExpense;//差旅费
			newHotelAnnual.setTravelExpense(Double.parseDouble(hotelAnnual
					.getTravelExpense() == null
					|| hotelAnnual.getTravelExpense().length() == 0 ? "0"
					: hotelAnnual.getTravelExpense()));
			// fixedPrice;//固定资产原价
			newHotelAnnual.setFixedPrice(Double.parseDouble(hotelAnnual
					.getFixedPrice() == null
					|| hotelAnnual.getFixedPrice().length() == 0 ? "0"
					: hotelAnnual.getFixedPrice()));
			// financialCost;//财务费用
			newHotelAnnual.setFinancialCost(Double.parseDouble(hotelAnnual
					.getFinancialCost() == null
					|| hotelAnnual.getFinancialCost().length() == 0 ? "0"
					: hotelAnnual.getFinancialCost()));
			// depreciation;//累计折旧
			newHotelAnnual.setDepreciation(Double.parseDouble(hotelAnnual
					.getDepreciation() == null
					|| hotelAnnual.getDepreciation().length() == 0 ? "0"
					: hotelAnnual.getDepreciation()));
			// interestCost;//利息支出
			newHotelAnnual.setInterestCost(Double.parseDouble(hotelAnnual
					.getInterestCost() == null
					|| hotelAnnual.getInterestCost().length() == 0 ? "0"
					: hotelAnnual.getInterestCost()));
			// yearDepreciation;//本年折旧
			newHotelAnnual.setYearDepreciation(Double.parseDouble(hotelAnnual
					.getYearDepreciation() == null
					|| hotelAnnual.getYearDepreciation().length() == 0 ? "0"
					: hotelAnnual.getYearDepreciation()));
			// assetTotal;//资产合计
			newHotelAnnual.setAssetTotal(Double.parseDouble(hotelAnnual
					.getAssetTotal() == null
					|| hotelAnnual.getAssetTotal().length() == 0 ? "0"
					: hotelAnnual.getAssetTotal()));
			// valueVariation;//公允价值变动收益
			newHotelAnnual.setValueVariation(Double.parseDouble(hotelAnnual
					.getValueVariation() == null
					|| hotelAnnual.getValueVariation().length() == 0 ? "0"
					: hotelAnnual.getValueVariation()));
			// liabilitiesTotal;//负债合计
			newHotelAnnual.setLiabilitiesTotal(Double.parseDouble(hotelAnnual
					.getLiabilitiesTotal() == null
					|| hotelAnnual.getLiabilitiesTotal().length() == 0 ? "0"
					: hotelAnnual.getLiabilitiesTotal()));
			// operationIncome;//营业收入
			newHotelAnnual.setOperationIncome(Double.parseDouble(hotelAnnual
					.getOperationIncome() == null
					|| hotelAnnual.getOperationIncome().length() == 0 ? "0"
					: hotelAnnual.getOperationIncome()));
			// possessorTotal;//所有者权益合计
			newHotelAnnual.setPossessorTotal(Double.parseDouble(hotelAnnual
					.getPossessorTotal() == null
					|| hotelAnnual.getPossessorTotal().length() == 0 ? "0"
					: hotelAnnual.getPossessorTotal()));
			// oprFeeAndTax;//营业税金及附加
			newHotelAnnual.setOprFeeAndTax(Double.parseDouble(hotelAnnual
					.getOprFeeAndTax() == null
					|| hotelAnnual.getOprFeeAndTax().length() == 0 ? "0"
					: hotelAnnual.getOprFeeAndTax()));
			// realIncome;//实收资本
			newHotelAnnual.setRealIncome(Double.parseDouble(hotelAnnual
					.getRealIncome() == null
					|| hotelAnnual.getRealIncome().length() == 0 ? "0"
					: hotelAnnual.getRealIncome()));
			// invest;//投资收益
			newHotelAnnual
					.setInvest(Double.parseDouble(hotelAnnual.getInvest() == null
							|| hotelAnnual.getInvest().length() == 0 ? "0"
							: hotelAnnual.getInvest()));
			// roomIncome; // 客房收入
			newHotelAnnual.setRoomIncome(Double.parseDouble(hotelAnnual
					.getRoomIncome() == null
					|| hotelAnnual.getRoomIncome().length() == 0 ? "0"
					: hotelAnnual.getRoomIncome()));
			// canteeIncome; // 餐厅收入
			newHotelAnnual.setCanteeIncome(Double.parseDouble(hotelAnnual
					.getCanteeIncome() == null
					|| hotelAnnual.getCanteeIncome().length() == 0 ? "0"
					: hotelAnnual.getCanteeIncome()));
			// otherIncome; // 其他收入
			newHotelAnnual.setOtherIncome(Double.parseDouble(hotelAnnual
					.getOtherIncome() == null
					|| hotelAnnual.getOtherIncome().length() == 0 ? "0"
					: hotelAnnual.getOtherIncome()));
			// operationCost;//营业成本
			newHotelAnnual.setOperationCost(Double.parseDouble(hotelAnnual
					.getOperationCost() == null
					|| hotelAnnual.getOperationCost().length() == 0 ? "0"
					: hotelAnnual.getOperationCost()));
			// operationFee;//营业费用
			newHotelAnnual.setOperationFee(Double.parseDouble(hotelAnnual
					.getOperationFee() == null
					|| hotelAnnual.getOperationFee().length() == 0 ? "0"
					: hotelAnnual.getOperationFee()));
			// fianceCost;//财务费用
			newHotelAnnual.setFianceCost(Double.parseDouble(hotelAnnual
					.getFianceCost() == null
					|| hotelAnnual.getFianceCost().length() == 0 ? "0"
					: hotelAnnual.getFianceCost()));
			;
			// opreationIntrest;//营业利润
			newHotelAnnual.setOpreationIntrest(Double.parseDouble(hotelAnnual
					.getOpreationIntrest() == null
					|| hotelAnnual.getOpreationIntrest().length() == 0 ? "0"
					: hotelAnnual.getOpreationIntrest()));
			// ownTax;//所得税
			newHotelAnnual
					.setOwnTax(Double.parseDouble(hotelAnnual.getOwnTax() == null
							|| hotelAnnual.getOwnTax().length() == 0 ? "0"
							: hotelAnnual.getOwnTax()));
			// profitTotal;//利润总额
			newHotelAnnual.setProfitTotal(Double.parseDouble(hotelAnnual
					.getProfitTotal() == null
					|| hotelAnnual.getProfitTotal().length() == 0 ? "0"
					: hotelAnnual.getProfitTotal()));
			// salary;//本年应付职工薪酬
			newHotelAnnual
					.setSalary(Double.parseDouble(hotelAnnual.getSalary() == null
							|| hotelAnnual.getSalary().length() == 0 ? "0"
							: hotelAnnual.getSalary()));
			// college;//大专以上学历人数
			newHotelAnnual
					.setCollege(Integer.parseInt(hotelAnnual.getCollege() == null
							|| hotelAnnual.getCollege().length() == 0 ? "0"
							: hotelAnnual.getCollege()));
			// people;//全部从业人员平均人数
			newHotelAnnual
					.setPeople(Integer.parseInt(hotelAnnual.getPeople() == null
							|| hotelAnnual.getPeople().length() == 0 ? "0"
							: hotelAnnual.getPeople()));
			// deposit; // 存货
			newHotelAnnual.setDeposit(Double.parseDouble(hotelAnnual.getDeposit() == null || hotelAnnual.getDeposit().length() == 0 ? "0" : String.valueOf(hotelAnnual.getDeposit())));
			// room; // 客房数
			newHotelAnnual.setRoom(hotelAnnual.getRoom());
			// bed; // 床位数
			newHotelAnnual.setBed(hotelAnnual.getBed());
			// actualRent; // 实际出租夜数
			newHotelAnnual.setActualRent(hotelAnnual.getActualRent());
			// forHire; // 可供出租夜数
			newHotelAnnual.setForHire(hotelAnnual.getForHire());
			newHotelAnnual.setCountryState(Status.noSumbit);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			newHotelAnnual.setReportTime(sdf.format(new Date()));

			// 增加积分
			int year = Integer.parseInt(hotelAnnual.getYear());
			boolean rs = ReportTimeCheck.annualTimeCheck(year);
			if (rs) {
				newHotelAnnual.setScore(40);
				UserScoreUtils.scoreChange(40, hotelId);
			} else {
				newHotelAnnual.setScore(20);
				UserScoreUtils.scoreChange(20, hotelId);
			}
			systemService.saveOrUpdate(newHotelAnnual);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "保存异常!";
		}
		return tip;
	}

	/**
	 * 
	 * 根据用户ID获取季报集合
	 * 
	 * @param request
	 * @param response
	 * @return 季报集合
	 */
	@RequestMapping(params = "getQuarterReportList")
	@ResponseBody
	public List<HotelQuarter> getQuarterReportList(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		// 酒店ID就是用户ID
		String hotelId = userId;

		// 返回的季报集合
		List<HotelQuarter> quarterList = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 获取季报集合
		List<com.zzc.web.htoal.entity.HotelQuarter> list = systemService
				.findByProperty(com.zzc.web.htoal.entity.HotelQuarter.class,
						"hotelQid", hotelId);
		for (com.zzc.web.htoal.entity.HotelQuarter oriHotelQuarter : list) {
			HotelQuarter hotelQuarter = new HotelQuarter();

			// userId; // 用户id
			hotelQuarter.setUserId(userId);
			hotelQuarter.setYear(oriHotelQuarter.getYear());
			hotelQuarter.setQuarter(oriHotelQuarter.getQuarter() == null ? ""
					: String.valueOf(oriHotelQuarter.getQuarter()));
			// writer; // 填报人
			hotelQuarter.setWriter(oriHotelQuarter.getWriter());
			// mobile; // 手机号码
			hotelQuarter.setMobile(oriHotelQuarter.getMobile());
			// writerDate; // 填报日期
			hotelQuarter.setWriterDate(oriHotelQuarter.getWriterDate());
			// totalIncome; //总收入(万元)
			hotelQuarter.setTotalIncome(String.valueOf(oriHotelQuarter
					.getTotalIncome()));
			// canteenIncome; // 餐厅收入(万元)
			hotelQuarter.setCanteenIncome(String.valueOf(oriHotelQuarter
					.getCanteenIncome()));
			// roomIncome; // 客房收入(万元)
			hotelQuarter.setRoomIncome(String.valueOf(oriHotelQuarter
					.getRoomIncome()));
			// otherIncome; // 其他收入(万元)
			hotelQuarter.setOtherIncome(String.valueOf(oriHotelQuarter
					.getOtherIncome()));
			// roomnum; // 客房数量
			hotelQuarter
					.setRoomnum(String.valueOf(oriHotelQuarter.getRoomnum()));
			// bednum; // 床位数量
			hotelQuarter.setBednum(String.valueOf(oriHotelQuarter.getBednum()));
			// realNights;//实际出租夜间数
			hotelQuarter.setRealNights(String.valueOf(oriHotelQuarter
					.getRealNights()));
			// canNights;//可供出租夜间数
			hotelQuarter.setCanNights(String.valueOf(oriHotelQuarter
					.getCanNights()));
			hotelQuarter.setStatus(this.getStatus(oriHotelQuarter));
			hotelQuarter.setAvgHotelPrice(oriHotelQuarter.getAvgHotelPrice());
			quarterList.add(hotelQuarter);
		}
		return quarterList;
	}

	/**
	 * 
	 * 根据用户ID获取年报集合
	 * 
	 * @param request
	 * @param response
	 * @return 年报集合
	 */
	@RequestMapping(params = "getAnnualReportList")
	@ResponseBody
	public List<HotelAnnual> getAnnualReportList(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		// 酒店ID就是用户ID
		String hotelId = userId;

		// 返回的季报集合
		List<HotelAnnual> annualList = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 获取季报集合
		List<com.zzc.web.htoal.entity.HotelAnnual> list = systemService
				.findByProperty(com.zzc.web.htoal.entity.HotelAnnual.class,
						"hotelAid", hotelId);
		for (com.zzc.web.htoal.entity.HotelAnnual oriHotelAnnual : list) {
			HotelAnnual hotelAnnual = new HotelAnnual();
			hotelAnnual.setYear(oriHotelAnnual.getYear());
			// userId;// 用户id
			hotelAnnual.setUserId(userId);
			// debt;//年末资产负债
			hotelAnnual.setDebt(String.valueOf(oriHotelAnnual.getDebt()));
			// realNights;//实际出租间夜数
			hotelAnnual.setRealNights(String.valueOf(oriHotelAnnual
					.getRealNights()));
			// canNights;//可提供出租夜数
			hotelAnnual.setCanNights(String.valueOf(oriHotelAnnual
					.getCanNights()));
			// streamTotal;//流动资产小计
			hotelAnnual.setStreamTotal(String.valueOf(oriHotelAnnual
					.getStreamTotal()));
			// manageCost;//管理费用
			hotelAnnual.setManageCost(String.valueOf(oriHotelAnnual
					.getManageCost()));
			// longInvest;//长期投资
			hotelAnnual.setLongInvest(String.valueOf(oriHotelAnnual
					.getLongInvest()));
			// taxes;//税金
			hotelAnnual.setTaxes(String.valueOf(oriHotelAnnual.getTaxes()));
			// fixedAssets;//固定资产
			hotelAnnual.setFixedAssets(String.valueOf(oriHotelAnnual
					.getFixedAssets()));
			// travelExpense;//差旅费
			hotelAnnual.setTravelExpense(String.valueOf(oriHotelAnnual
					.getTravelExpense()));
			// fixedPrice;//固定资产原价
			hotelAnnual.setFixedPrice(String.valueOf(oriHotelAnnual
					.getFixedPrice()));
			// financialCost;//财务费用
			hotelAnnual.setFinancialCost(String.valueOf(oriHotelAnnual
					.getFinancialCost()));
			// depreciation;//累计折旧
			hotelAnnual.setDepreciation(String.valueOf(oriHotelAnnual
					.getDepreciation()));
			// interestCost;//利息支出
			hotelAnnual.setInterestCost(String.valueOf(oriHotelAnnual
					.getInterestCost()));
			// yearDepreciation;//本年折旧
			hotelAnnual.setYearDepreciation(String.valueOf(oriHotelAnnual
					.getYearDepreciation()));
			// assetTotal;//资产合计
			hotelAnnual.setAssetTotal(String.valueOf(oriHotelAnnual
					.getAssetTotal()));
			// valueVariation;//公允价值变动收益
			hotelAnnual.setValueVariation(String.valueOf(oriHotelAnnual
					.getValueVariation()));
			// liabilitiesTotal;//负债合计
			hotelAnnual.setLiabilitiesTotal(String.valueOf(oriHotelAnnual
					.getLiabilitiesTotal()));
			// operationIncome;//营业收入
			hotelAnnual.setOperationIncome(String.valueOf(oriHotelAnnual
					.getOperationIncome()));
			// possessorTotal;//所有者权益合计
			hotelAnnual.setPossessorTotal(String.valueOf(oriHotelAnnual
					.getPossessorTotal()));
			// oprFeeAndTax;//营业税金及附加
			hotelAnnual.setOprFeeAndTax(String.valueOf(oriHotelAnnual
					.getOprFeeAndTax()));
			// realIncome;//实收资本
			hotelAnnual.setRealIncome(String.valueOf(oriHotelAnnual
					.getRealIncome()));
			// invest;//投资收益
			hotelAnnual.setInvest(String.valueOf(oriHotelAnnual.getInvest()));
			// roomIncome; // 客房收入
			hotelAnnual.setRoomIncome(String.valueOf(oriHotelAnnual
					.getRoomIncome()));
			// canteeIncome; // 餐厅收入
			hotelAnnual.setCanteeIncome(String.valueOf(oriHotelAnnual
					.getCanteeIncome()));
			// otherIncome; // 其他收入
			hotelAnnual.setOtherIncome(String.valueOf(oriHotelAnnual
					.getOtherIncome()));
			// operationCost;//营业成本
			hotelAnnual.setOperationCost(String.valueOf(oriHotelAnnual
					.getOperationCost()));
			// operationFee;//营业费用
			hotelAnnual.setOperationFee(String.valueOf(oriHotelAnnual
					.getOperationFee()));
			// fianceCost;//财务费用
			hotelAnnual.setFianceCost(String.valueOf(oriHotelAnnual
					.getFianceCost()));
			;
			// opreationIntrest;//营业利润
			hotelAnnual.setOpreationIntrest(String.valueOf(oriHotelAnnual
					.getOpreationIntrest()));
			// ownTax;//所得税
			hotelAnnual.setOwnTax(String.valueOf(oriHotelAnnual.getOwnTax()));
			// profitTotal;//利润总额
			hotelAnnual.setProfitTotal(String.valueOf(oriHotelAnnual
					.getProfitTotal()));
			// salary;//本年应付职工薪酬
			hotelAnnual.setSalary(String.valueOf(oriHotelAnnual.getSalary()));
			// college;//大专以上学历人数
			hotelAnnual.setCollege(String.valueOf(oriHotelAnnual.getCollege()));
			// people;//全部从业人员平均人数
			hotelAnnual.setPeople(String.valueOf(oriHotelAnnual.getPeople()));
			// deposit; // 存货
			hotelAnnual.setDeposit(String.valueOf(oriHotelAnnual.getDeposit()));
			// room; // 客房数
			hotelAnnual.setRoom(oriHotelAnnual.getRoom());
			// bed; // 床位数
			hotelAnnual.setBed(oriHotelAnnual.getBed());
			// actualRent; // 实际出租夜数
			hotelAnnual.setActualRent(oriHotelAnnual.getActualRent());
			// forHire; // 可供出租夜数
			hotelAnnual.setForHire(oriHotelAnnual.getForHire());
			hotelAnnual.setStatus(this.getStatus(oriHotelAnnual));
			;
			try {
				hotelAnnual.setReportDate(sdf.format(oriHotelAnnual
						.getReportTime()));
			} catch (Exception e) {
				hotelAnnual.setReportDate("");
			}
			annualList.add(hotelAnnual);
		}
		return annualList;
	}

	/**
	 * 
	 * 获取当前季度
	 * 
	 * @return
	 */
	private String getQuarter() {
		Calendar calendar = Calendar.getInstance();
		int nowMonth = calendar.get(Calendar.MONTH) + 1;
		String quarter = "";
		if (nowMonth <= 3)
			quarter = "1";
		if (3 < nowMonth && nowMonth <= 6)
			quarter = "2";
		if (6 < nowMonth && nowMonth <= 9)
			quarter = "3";
		if (9 < nowMonth && nowMonth <= 12)
			quarter = "4";
		return quarter;
	}

	/**
	 * 获取审核状态
	 * 
	 * @param hotelAnnual
	 * @return 审核状态
	 */
	private String getStatus(com.zzc.web.htoal.entity.HotelAnnual hotelAnnual) {
		String status = "";
		String countryStatus = hotelAnnual.getCountryState();
		status = hotelAnnual.getStatus();
		// 是否进入国家审核阶段
		if ("1".equals(countryStatus)) {
			switch (status) {
			case "1":
				status = "市级未填报";
				break;
			case "2":
				status = "市级待审核";
				break;
			case "3":
				status = "市级未通过";
				break;
			case "4":
				status = "市级已通过";
				break;
			}
		} else {
			switch (status) {
			case "1":
				status = "国家级未填报";
				break;
			case "2":
				status = "国家级待审核";
				break;
			case "3":
				status = "国家级未通过";
				break;
			case "4":
				status = "国家级已通过";
				break;
			}
		}
		return status;
	}

	private String getStatus(com.zzc.web.htoal.entity.HotelQuarter hotelQuarter) {
		String status = "";
		String countryStatus = hotelQuarter.getCountryState();
		status = hotelQuarter.getStatus();
		// 是否进入国家审核阶段
		if ("1".equals(countryStatus)) {
			switch (status) {
			case "1":
				status = "市级未填报";
				break;
			case "2":
				status = "市级待审核";
				break;
			case "3":
				status = "市级未通过";
				break;
			case "4":
				status = "市级已通过";
				break;
			}
		} else {
			switch (status) {
			case "1":
				status = "国家级未填报";
				break;
			case "2":
				status = "国家级待审核";
				break;
			case "3":
				status = "国家级未通过";
				break;
			case "4":
				status = "国家级已通过";
				break;
			}
		}
		return status;
	}

	/**
	 * 
	 * 添加酒店基本信息年报
	 * 
	 * @param hotelId
	 * @return 是否成功标志
	 */
	@RequestMapping(params = "fillHotelBasicInfo")
	@ResponseBody
	public String fillHotelBasicInfo(HttpServletRequest request,
			HttpServletResponse response) {

		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		String tip = "success";
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String msg = request.getParameter("param");
		String userId = request.getParameter("userId");

		// 检查是否已经填报年报
		boolean isFilled = this.checkAnnualIsFilled(userId);
		if (isFilled)
			return "edited";

		APPHotel hotelInfo = null;

		try {
			hotelInfo = JSON.parseObject(msg, APPHotel.class);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "数据转化失败!";
			return tip;
		}

		HotelmanageSta hotelmanageSta = systemService.get(HotelmanageSta.class,
				userId);
		// legalPerson
		hotelmanageSta.setLegalPerson(hotelInfo.getLegalPerson());
		// unitname
		hotelmanageSta.setUnitname(hotelInfo.getUnitname());
		// address
		hotelmanageSta.setAddress(hotelInfo.getAddress());
		// manager
		hotelmanageSta.setManager(hotelInfo.getManager());
		// zipcode
		hotelmanageSta.setZipcode(hotelInfo.getZipcode());
		// telephone
		hotelmanageSta.setTelephone(hotelInfo.getTelephone());
		// mobile
		hotelmanageSta.setMobile(hotelInfo.getMobile());
		// housenum
		hotelmanageSta
				.setHousenum(Integer.parseInt(hotelInfo.getHousenum() == null ? "0"
						: hotelInfo.getHousenum()));
		// bednum
		hotelmanageSta
				.setBednum(Integer.parseInt(hotelInfo.getBednum() == null ? "0"
						: hotelInfo.getBednum()));
		// fax
		hotelmanageSta.setFax(hotelInfo.getFax());
		// weburl
		hotelmanageSta.setWeburl(hotelInfo.getWeburl());
		// email
		hotelmanageSta.setEmail(hotelInfo.getEmail());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		hotelmanageSta.setFillDate(sdf.format(new Date()));
		hotelmanageSta.setStatus(Status.noSumbit);

		String county = hotelInfo.getCounty();
		switch (county) {
		case "市辖区":
			county = "0";
			break;
		case "崖州区":
			county = "1";
			break;
		case "海棠区":
			county = "2";
			break;
		case "天涯区":
			county = "3";
			break;
		case "吉阳区":
			county = "4";
			break;
		}
		hotelmanageSta.setCounty(county);

		String bay = hotelInfo.getBay();
		switch (bay) {
		case "市区":
			bay = "0";
			break;
		case "亚龙湾":
			bay = "1";
			break;
		case "大东海":
			bay = "2";
			break;
		case "三亚湾":
			bay = "3";
			break;
		case "海棠湾":
			bay = "4";
			break;
		}
		hotelmanageSta.setBay(bay);

		systemService.saveOrUpdate(hotelmanageSta);

		return tip;

	}

	/**
	 * 检查酒店年报是否填报
	 * 
	 * @param hotelId
	 * @return 是否填报
	 */
	public boolean checkAnnualIsFilled(String hotelId) {
		boolean flag = false;
		try {
			String sql = "select t.fill_date from t_hotelmanage_sta t where t.id = ? and t.status='4'";
			Map<String, Object> map = systemService.findOneForJdbc(sql,
					new Object[] { hotelId });
			String dateStr = map.get("fill_date") == null ? "" : map.get(
					"fill_date").toString();
			if (dateStr.length() == 0)
				return flag;
			Integer year = Integer.parseInt(dateStr.substring(0, 4));
			Calendar calendar = Calendar.getInstance();
			Integer nowYear = calendar.get(Calendar.YEAR);

			if (year >= nowYear)
				flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			flag = true;
		}
		return flag;
	}

}
