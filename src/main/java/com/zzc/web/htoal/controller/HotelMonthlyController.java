package com.zzc.web.htoal.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.ResourceUtil;
import com.zzc.core.util.StringUtil;
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.htoal.entity.HotelMonthly;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.service.Hotelservice;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.HotelGrade;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

/**
 * 
 * 
 * @date: 2016年11月29日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelMonthly.java
 * @Version: 1.0
 * @About: 酒店住宿情况月报
 */
@Scope("prototype")
@Controller
@RequestMapping("/hotelMonthlyController")
public class HotelMonthlyController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(HotelMonthlyController.class);
	private String message = null;
	private SystemService systemService;
	// private String hid=null;
	// 调用时间工具
	TimeUtils timeUtils = new TimeUtils();

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	private Hotelservice hotelservice;

	@Autowired
	public void setHotelservice(Hotelservice hotelservice) {
		this.hotelservice = hotelservice;
	}

	// 跳转到save页面
	@RequestMapping(params = "addorupdate")
	public ModelAndView hotelInfo(HttpServletRequest req) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		Hotelmanage hm = this.systemService.getEntity(Hotelmanage.class,
				hotelId);
		req.setAttribute("hotelmanage", hm);

		Calendar calendar = Calendar.getInstance();
		String year = "";
		String month = "";
		// 上个月是12月的情况
		if (calendar.get(Calendar.MONTH) == 0) {
			month = "12";
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		} else {
			month = String.valueOf(calendar.get(Calendar.MONTH));
			year = String.valueOf(calendar.get(Calendar.YEAR));
		}
		HotelMonthly hotelMonthly = new HotelMonthly();
		hotelMonthly.setYear(year);
		hotelMonthly.setMonth(month);
		hotelMonthly.setFiller(hm.getManager());
		hotelMonthly.setFillerTel(hm.getTelephone());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hotelMonthly.setReportDate(sdf.format(new Date()));

		req.setAttribute("hotelMonthly", hotelMonthly);
		return new ModelAndView("system/hotel/saveHotelMonthly");
	}

	// 月报跳转页面
	@RequestMapping(params = "hotelMonth")
	public ModelAndView role(HttpServletRequest request) {
		String year = "";
		String month = "";
		Calendar calendar = Calendar.getInstance();
		// 上个月是12月的情况
		if (calendar.get(Calendar.MONTH) == 0) {
			month = "12";
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		} else {
			month = String.valueOf(calendar.get(Calendar.MONTH));
			year = String.valueOf(calendar.get(Calendar.YEAR));
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		return new ModelAndView("system/hotel/hotelMonthList");
	}

	// 跳转到月报审核列表
	@RequestMapping(params = "hotelMonthCheck")
	public ModelAndView hotelMonthCheck() {
		return new ModelAndView("system/hotel/hotelMonthCheckList");
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson add(HotelMonthly hotelMonthly, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String tip = "添加成功!";
		try {
			// 新建判断
			if (hotelMonthly.getId() == null
					|| hotelMonthly.getId().length() == 0) {
				hotelMonthly.setId(null);
				if (this.isFilled()) {
					j.setMsg("上月已填报月报!");
					return j;
				}
			}
			// 与酒店的id进行关联
			TSUser user = ResourceUtil.getSessionUserName();
			Hotelmanage hotelmanage = this.systemService.getEntity(
					Hotelmanage.class, user.getId());
			hotelMonthly.setHotelId(hotelmanage.getId());
			// 设置审核状态
			if (this.isDistrictManager()) {
				hotelMonthly.setStatus(Status.noAudit);
				hotelMonthly.setDistrictStatus(Status.passAudit);
				hotelMonthly.setProvinceState(Status.noSumbit);
			} else {
				hotelMonthly.setStatus(Status.noAudit);
				hotelMonthly.setDistrictStatus(Status.noAudit);
				hotelMonthly.setProvinceState(Status.noSumbit);
			}

			hotelMonthly.setHotelmanage(hotelmanage);
			// 新建判断
			if (hotelMonthly.getId() == null
					|| hotelMonthly.getId().length() == 0) {
				// 加分
				int year = Integer.parseInt(hotelMonthly.getYear());
				int month = Integer.parseInt(hotelMonthly.getMonth());
				boolean rs = ReportTimeCheck.monthTimeCheck(year, month, 3);
				if (rs) {
					hotelMonthly.setScore(5);
					UserScoreUtils.scoreChange(5);
				} else {
					hotelMonthly.setScore(2);
					UserScoreUtils.scoreChange(2);
				}
			}

			// 计算年初至本月

			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			
			// 计算年初至本月的数据
			hotelMonthly = this.calcTotal(hotelMonthly, user.getId());

			systemService.saveOrUpdate(hotelMonthly);
			systemService.addLog("填报酒店月报成功:"+hotelMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "添加失败!";
			systemService.addLog("填报酒店月报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}
		j.setMsg(tip);
		return j;
	}

	@RequestMapping(params = "editByUser")
	public ModelAndView editByUser(HttpServletRequest request) {

		String monthId = request.getParameter("monthId");

		String hotelId = ResourceUtil.getSessionUserName().getId();
		Hotelmanage hm = this.systemService.getEntity(Hotelmanage.class,
				hotelId);
		request.setAttribute("hotelmanage", hm);

		HotelMonthly hotelMonthly = systemService.get(HotelMonthly.class,
				monthId);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hotelMonthly.setReportDate(sdf.format(new Date()));

		request.setAttribute("hotelMonthly", hotelMonthly);
		return new ModelAndView("system/hotel/saveHotelMonthly");
	}

	// ******************代填报*********************************//
	@RequestMapping(params = "saveDai")
	@ResponseBody
	public AjaxJson addDai(HotelMonthly hotelMonthly, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String tip = "添加成功!";
		
		String isTemp = request.getParameter("isTemp");
		
		try {
			// 新建判断
			if (hotelMonthly.getId() == null
					|| hotelMonthly.getId().length() == 0)
				hotelMonthly.setId(null);
			Hotelmanage hotelmanage = this.systemService.getEntity(
					Hotelmanage.class, hotelMonthly.getHotelId());
			hotelMonthly.setHotelId(hotelmanage.getId());
			
			// 设置审核状态
			if(isTemp == null || isTemp.length() == 0){
				// 判断是否区级审核
				if (this.isDistrictManager()) {
					hotelMonthly.setDistrictStatus(Status.passAudit);
					hotelMonthly.setStatus(Status.noAudit);
					hotelMonthly.setProvinceState(Status.noSumbit);
				} else {
					hotelMonthly.setStatus(Status.passAudit);
					hotelMonthly.setDistrictStatus(Status.noAudit);
					hotelMonthly.setProvinceState(Status.noSumbit);
				}
			}else{
				hotelMonthly.setStatus(Status.noSumbit);
				hotelMonthly.setDistrictStatus(Status.noSumbit);
				hotelMonthly.setProvinceState(Status.noSumbit);
			}
			
			TSUser user = ResourceUtil.getSessionUserName();
			hotelMonthly.setHotelmanage(hotelmanage);
			
			// 计算年初至本月的数据
			hotelMonthly = this.calcTotal(hotelMonthly, hotelMonthly.getHotelId());
			
			systemService.saveOrUpdate(hotelMonthly);
			systemService.addLog("代填报酒店月报成功:"+hotelMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "添加失败!";
			systemService.addLog("代填报酒店月报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}
		j.setMsg(tip);
		return j;
	}

	@RequestMapping(params = "datagrid")
	public void roleGrid(HotelMonthly hotelMonthly, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HotelMonthly.class, dataGrid);
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService
				.findOneForJdbc(
						"select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)",
						userId);
		// 区级管理员做判断
		if (userMap.get("rolename") != null
				&& userMap.get("rolename").toString().contains("区级")) {
			List<Map<String, Object>> list = systemService.findForJdbc(
					"select id from t_hotelmanage where w_county=?",
					ResourceUtil.getSessionUserName().getArea());
			if (list.size() != 0) {
				String[] parmas = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String id = list.get(i).get("id").toString();
					parmas[i] = id;
				}
				cq.add(Restrictions.in("hotelId", parmas));
			} else {
				cq.add(Restrictions.in("hotelId", new Object[] { "" }));
			}
		}
		// cq.add(Restrictions.eq("status",
		// "4"),Restrictions.eq("districtStatus", "4"));
		cq.add(Restrictions.or(Restrictions.eq("status", "4"),
				Restrictions.eq("districtStatus", "4")));
		// 默认显示为上月的报表
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		Calendar calendar = Calendar.getInstance();
		// 上个月是12月的情况
		if (year == null && month == null) {
			if (calendar.get(Calendar.MONTH) == 0) {
				month = "12";
				year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			} else {
				month = String.valueOf(calendar.get(Calendar.MONTH));
				year = String.valueOf(calendar.get(Calendar.YEAR));
			}
		}

		cq.add(Restrictions.eq("year", year));
		cq.add(Restrictions.eq("month", month));
		// 查询条件
		String hotelName = hotelMonthly.getHotelmanage() == null ? ""
				: hotelMonthly.getHotelmanage().getUnitname();
		/*
		 * String hotelType = hotelMonthly.getHotelmanage() == null ? "" :
		 * hotelMonthly.getHotelmanage().getHousegrade();
		 */
		String beginStar = request.getParameter("hotelmanage.housegrade_begin");
		String endStar = request.getParameter("hotelmanage.housegrade_end");
		String country = request.getParameter("hotelmanage.county");
		String bay = request.getParameter("hotelmanage.bay");
		String status = request.getParameter("status");
		String provinceState = request.getParameter("provinceState");
		if (hotelName != null && hotelName.length() != 0) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.unitname like '%"
							+ hotelName + "%' and t.id=hotel_id)"));
		}
		if (country != null && country.length() != 0) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.w_county ="
							+ country + "  and t.id=hotel_id)"));
		}
		if (bay != null && bay.length() != 0) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.bay_type ="
							+ bay + " and t.id=hotel_id)"));
		}
		/*
		 * if(hotelType != null && hotelType.length() != 0){
		 * cq.add(Restrictions.sqlRestriction(
		 * " 1=1 and exists(select * from t_hotelmanage t where t.housegrade = '"
		 * +hotelType+"' and t.id=hotel_id)")); }
		 */
		if (beginStar != null && beginStar.length() != 0 && endStar != null
				&& endStar.length() != 0) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.housegrade between '"
							+ beginStar
							+ "' and '"
							+ endStar
							+ "' and  t.id=hotel_id)"));
		}
		if (status != null && status.length() != 0) {
			cq.add(Restrictions.sqlRestriction("this_ .status =" + status));
		}
		if (provinceState != null && provinceState.length() != 0) {
			cq.add(Restrictions.sqlRestriction("this_ .province_state ="
					+ provinceState));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new HotelMonthly());
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	// 删除月报
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HotelMonthly hotelMonthly, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			hotelMonthly = hotelservice.getEntity(HotelMonthly.class, id);
			hotelservice.delete(hotelMonthly);
			message = "月报删除成功";
		} catch (Exception e) {
			message = "月报删除失败";
		}

		j.setMsg(message);
		return j;
	}

	// 月报审核列表
	// 酒店基本信息+月报信息,这个月审核上个月的信息
	@RequestMapping(params = "monthdatagrid")
	public void monthDatagrid(HotelMonthly hotelMonthly,
			Hotelmanage hotelManage, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		// 根据datagrid查询酒店信息
		CriteriaQuery cq = new CriteriaQuery(Hotelmanage.class, dataGrid);
		// 获取上月神机箭
		Calendar calendar = Calendar.getInstance();
		String year = "";
		String month = "";
		// 上个月是12月的情况
		if (calendar.get(Calendar.MONTH) == 0) {
			month = "12";
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		} else {
			month = String.valueOf(calendar.get(Calendar.MONTH));
			year = String.valueOf(calendar.get(Calendar.YEAR));
		}
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService
				.findOneForJdbc(
						"select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)",
						userId);
		// 区级管理员做判断
		if (userMap.get("rolename") != null
				&& userMap.get("rolename").toString().contains("区级")) {
			String countynum = ResourceUtil.getSessionUserName().getArea();
			if (countynum.equals("460201"))
				countynum = "0";
			else if (countynum.equals("460205"))
				countynum = "1";
			else if (countynum.equals("460202"))
				countynum = "2";
			else if (countynum.equals("460204"))
				countynum = "3";
			else if (countynum.equals("460203"))
				countynum = "4";
			cq.add(Restrictions.eq("county", countynum));
		}
		if (hotelManage.getUnitname() != null
				&& hotelManage.getUnitname().length() != 0) {
			cq.add(Restrictions.like("unitname",
					"%" + hotelManage.getUnitname() + "%"));
		}
		String beginStar = request.getParameter("housegrade_begin");
		String endStar = request.getParameter("housegrade_end");
		String country = request.getParameter("county");
		String bay = request.getParameter("bay");
		String status = request.getParameter("hotelmonth.status");
		String provinceState = request.getParameter("hotelmonth.provinceState");
		String districtStatus = request
				.getParameter("hotelmonth.districtStatus");
		/*
		 * if(hotelManage.getHousegrade() != null &&
		 * hotelManage.getHousegrade().length() != 0){
		 * cq.add(Restrictions.eq("housegrade", hotelManage.getHousegrade())); }
		 */
		if (beginStar != null && endStar != null && beginStar.length() != 0
				&& endStar.length() != 0) {
			cq.add(Restrictions.sqlRestriction(" this_ .housegrade between '"
					+ beginStar + "' and '" + endStar + "'"));
		}
		if (country != null && country.length() != 0) {
			cq.add(Restrictions.sqlRestriction(" this_ .w_county =" + country));
		}
		if (bay != null && bay.length() != 0) {
			cq.add(Restrictions.sqlRestriction(" this_ .bay_type =" + bay));
		}
		if (provinceState != null && provinceState.length() != 0) {
			if (provinceState.equals("1")) {
				cq.add(Restrictions
						.sqlRestriction(" 1=1 and not exists(select * from t_hotelmonthly t where t.hotel_id = this_.id and t.month="
								+ month + " and t.year=" + year + ")"));
			} else {
				cq.add(Restrictions
						.sqlRestriction(" 1=1 and exists(select * from t_hotelmonthly t where t.hotel_id = this_.id and t.province_state ='"
								+ provinceState
								+ "' and t.month="
								+ month
								+ " and t.year=" + year + ")"));
			}
		}
		if (districtStatus != null && districtStatus.length() != 0) {
			if (districtStatus.equals("1")) {
				cq.add(Restrictions
						.sqlRestriction(" 1=1 and not exists(select * from t_hotelmonthly t where t.hotel_id = this_.id and t.month="
								+ month + " and t.year=" + year + ")"));
			} else {
				cq.add(Restrictions
						.sqlRestriction(" 1=1 and exists(select * from t_hotelmonthly t where t.hotel_id = this_.id and t.district_status ='"
								+ districtStatus
								+ "' and t.month="
								+ month
								+ " and t.year=" + year + ")"));
			}
		}
		if (status != null && status.length() != 0) {
			if (status.equals("1")) {
				cq.add(Restrictions
						.sqlRestriction(" 1=1 and not exists(select * from t_hotelmonthly t where t.hotel_id = this_.id and t.month="
								+ month + " and t.year=" + year + ")"));
			} else {
				cq.add(Restrictions
						.sqlRestriction(" 1=1 and exists(select * from t_hotelmonthly t where t.hotel_id = this_.id and t.status ='"
								+ status
								+ "' and t.month="
								+ month
								+ " and t.year=" + year + ")"));
			}
		}
		/*
		 * if(hotelManage.getHotelmonth() != null &&
		 * hotelManage.getHotelmonth().getStatus() != null &&
		 * hotelManage.getHotelmonth().getStatus().length() != 0){
		 * cq.add(Restrictions.sqlRestriction(
		 * " 1=1 and exists(select * from t_hotelmonthly t where t.hotel_id = this_.id and t.status='"
		 * +hotelManage.getHotelmonth().getStatus()+"' )")); }
		 * if(hotelManage.getHotelmonth() != null &&
		 * hotelManage.getHotelmonth().getProvinceState() != null &&
		 * hotelManage.getHotelmonth().getProvinceState().length() != 0){
		 * cq.add(Restrictions.sqlRestriction(
		 * " 1=1 and exists(select * from t_hotelmonthly t where t.hotel_id = this_.id and t.provice_status='"
		 * +hotelManage.getHotelmonth().getProvinceState()+"' )")); }
		 */
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new Hotelmanage());
		// 获取easyUI的datagrid模型
		this.hotelservice.getDataGridReturn(cq, true);

		// 获取上月时间

		// 对原型进行遍历list集合
		for (Object o : dataGrid.getResults()) {
			// 对集合元素进行操作
			Hotelmanage hd = (Hotelmanage) o;
			List<HotelMonthly> list = hd.getHotelMonthly();
			if (hd.getHotelMonthly() != null && list != null
					&& list.size() != 0) {
				for (HotelMonthly mothdata : list) {
					// 对月报进行遍历
					if (mothdata.getMonth() == null
							|| mothdata.getYear() == null) {
						break;
					}
					if (year.equals(mothdata.getYear())
							&& month.equals(mothdata.getMonth())) {
						// 将当前月报 加到酒店信息中 在审核表中提现
						hd.setHotelmonth(mothdata);
						break;
					}
				}
				if (hd.getHotelmonth() == null) {
					HotelMonthly hl = new HotelMonthly();
					hl.setYear(year);
					hl.setMonth(month);
					hl.setStatus(Status.noSumbit);
					hl.setDistrictStatus(Status.noSumbit);
					hl.setProvinceState(Status.noSumbit);
					hd.setHotelmonth(hl);
				}
			} else {
				HotelMonthly hl = new HotelMonthly();
				hl.setYear(year);
				hl.setMonth(month);
				hl.setStatus(Status.noSumbit);
				hl.setDistrictStatus(Status.noSumbit);
				hl.setProvinceState(Status.noSumbit);
				hd.setHotelmonth(hl);
			}
		}
		if (this.isDistrictManager()) {
			request.setAttribute("isDistrictManager", "1");
		}

		// 将datagrid返回给前端页面
		TagUtil.datagrid(response, dataGrid);
	}

	// 月报详情展示
	@RequestMapping(params = "detail")
	public ModelAndView detial(HttpServletRequest request,
			HotelMonthly hotelMonthly, Hotelmanage hotelmanage,
			DataGrid dataGrid) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		// 月报id
		String monthId = request.getParameter("monthId");
		// 存到请求域中
		Hotelmanage hm = this.systemService.get(Hotelmanage.class,
				hotelmanageId);
		hotelMonthly = this.systemService.get(HotelMonthly.class, monthId);
		request.setAttribute("hotelMonthly", hotelMonthly);
		request.setAttribute("hotelmanage", hm);
		ModelAndView view = new ModelAndView("system/hotel/showMonthDetail");
		view.addObject(hotelMonthly);
		view.addObject(hm);
		return view;
	}

	// 月报审核管理员操作
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			HotelMonthly hotelMonthly, Hotelmanage otelmanage, String statusid) {
		AjaxJson j = new AjaxJson();

		String monthId = request.getParameter("monthId");
		request.setAttribute("monthId", monthId);
		try {
			if (StringUtil.isNotEmpty(monthId)) {
				HotelMonthly hotelMonth = systemService.getEntity(
						HotelMonthly.class, monthId);

				// 判断是否区级审核
				if (this.isDistrictManager()) {
					// 表示管理员审核通过
					hotelMonth.setDistrictStatus(Status.passAudit);
					// hotelMonthly.setStatus(Status.noAudit);
					// hotelMonthly.setProvinceState(Status.noSumbit);
					systemService.saveOrUpdate(hotelMonth);
				} else {
					// 表示管理员审核通过
					hotelMonth.setStatus(Status.passAudit);
					systemService.saveOrUpdate(hotelMonth);
				}
				j.setMsg("管理员审核成功");
				systemService.addLog("审核通过酒店月报成功:"+monthId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			systemService.addLog("审核通过酒店月报失败:"+monthId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}

	// 审核结果不通过
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		j.setMsg("操作成功");
		// System.out.println("+++++++++++++++++++++++++++++");
		String monthId = request.getParameter("monthId");
		request.setAttribute("monthId", monthId);
		if (StringUtil.isNotEmpty(monthId)) {
			HotelMonthly hotelMonth = this.systemService.getEntity(
					HotelMonthly.class, monthId);
			// 判断是否区级审核
			if (this.isDistrictManager()) {
				hotelMonth.setDistrictStatus(Status.notAudit);// 管理员审核未通过
			} else {
				hotelMonth.setStatus(Status.notAudit);// 管理员审核未通过
			}

			try {
				// 减分
				Integer score = hotelMonth.getScore() == null ? 0 : hotelMonth
						.getScore();
				if (score >= 2) {
					score = score - 2;
					UserScoreUtils.scoreChange(-2, hotelMonth.getHotelId());
				} else {
					UserScoreUtils.scoreChange((0 - score),
							hotelMonth.getHotelId());
					score = 0;
				}
				hotelMonth.setScore(score);
				systemService.addLog("审核退回酒店月报成功:"+monthId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			} catch (Exception e) {
				e.printStackTrace();
				systemService.addLog("审核退回酒店月报失败:"+monthId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			}
			systemService.saveOrUpdate(hotelMonth);
		}

		return j;
	}

	/**
	 * 
	 * @date: 2016年12月9日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return: 审核页面
	 * 
	 */
	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,
			HotelMonthly hotelMonthly, Hotelmanage hotelmanage,
			DataGrid dataGrid) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		String monthid = request.getParameter("monthId");
		request.setAttribute("hotelmanageId", hotelmanageId);
		Hotelmanage h = this.systemService
				.get(Hotelmanage.class, hotelmanageId);
		HotelMonthly m = this.systemService.get(HotelMonthly.class, monthid);
		ModelAndView view = new ModelAndView("system/hotel/auditmonth");
		view.addObject("hotelmanage", h);
		view.addObject("hotelMonthly", m);
		return view;
	}

	/**
	 * 
	 * @date: 2016年12月9日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return: 暂存功能
	 */
	@RequestMapping(params = "zancuncheck")
	@ResponseBody
	public AjaxJson saveZanStatus(HttpServletRequest request,
			HotelMonthly hotelMonthly) {
		AjaxJson j = new AjaxJson();
		String hotelId = hotelMonthly.getHotelId();
		String tip = "暂存成功!";
		try {
			// 新建判断
			if (hotelMonthly.getId() == null
					|| hotelMonthly.getId().length() == 0)
				hotelMonthly.setId(null);
			// 获取酒店信息
			Hotelmanage hotelmanage = this.systemService.getEntity(
					Hotelmanage.class, hotelId);
			hotelMonthly.setHotelId(hotelmanage.getId());
			// 设置审核状态
			hotelMonthly.setStatus(Status.noSumbit);
			hotelMonthly.setDistrictStatus(Status.noSumbit);
			hotelMonthly.setProvinceState(Status.noSumbit);
			hotelMonthly.setHotelmanage(hotelmanage);
			systemService.saveOrUpdate(hotelMonthly);
			systemService.addLog("暂存酒店月报成功:"+hotelMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "暂存失败!";
			systemService.addLog("暂存酒店月报失败", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		j.setMsg(tip);
		return j;
	}

	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request,
			HotelMonthly hotelMonthly, Hotelmanage hotelmanage) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		String monthId = request.getParameter("monthId");
		// 根据基本信息id获取基本信息
		hotelmanage = this.systemService.get(Hotelmanage.class, hotelmanageId);
		// 判断是否存在历史数据
		hotelMonthly = this.systemService.get(HotelMonthly.class, monthId);
		if (hotelMonthly == null)
			hotelMonthly = new HotelMonthly();

		Calendar calendar = Calendar.getInstance();
		String year = "";
		String month = "";
		// 上个月是12月的情况
		if (calendar.get(Calendar.MONTH) == 0) {
			month = "12";
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		} else {
			month = String.valueOf(calendar.get(Calendar.MONTH));
			year = String.valueOf(calendar.get(Calendar.YEAR));
		}
		// 获取上月的天数
		calendar.set(Calendar.YEAR, Integer.valueOf(year));
		calendar.set(Calendar.MONTH, Integer.valueOf(month) - 1);
		int days = calendar.getActualMaximum(Calendar.DATE);
		int housenum = hotelmanage.getHousenum() == null ? 0 : hotelmanage
				.getHousenum();
		int maxRooms = days * housenum;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hotelMonthly.setReportDate(sdf.format(new Date()));

		hotelMonthly.setYear(year);
		hotelMonthly.setMonth(month);
		ModelAndView view = new ModelAndView("system/hotel/daitianbaoMonth");
		view.addObject("hotelmanage", hotelmanage);
		view.addObject("hotelMonthly", hotelMonthly);
		return view;
	}

	/**
	 * 
	 * @date: 2016年12月12日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:
	 */
	@RequestMapping(params = "exportsxls")
	public String exportXls(HotelMonthly hotelMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(HotelMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelMonthly, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		List<HotelMonthly> hotelMonthlys = this.systemService
				.getList(HotelMonthly.class);
		map.put(NormalExcelConstants.FILE_NAME, "酒店月报信息");
		map.put(NormalExcelConstants.CLASS, HotelMonthly.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("酒店月报列表",
				"导出人:酒店管理员", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelMonthlys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	// *********************星级酒店客户端的月报****************************
	@RequestMapping(params = "ckhotel")
	public String user(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		// List<TSDepart> departList = systemService.getList(TSDepart.class);
		// request.setAttribute("departsReplace",
		// RoletoJson.listToReplaceStr(departList, "departname", "id"));
		return "system/hotelClient/ckHotelMonthList";
	}

	@ResponseBody
	@RequestMapping(params = "ckDatagrid")
	public void monthGrid(HotelMonthly hotelMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		hotelMonthly.setHotelId(hotelId);
		CriteriaQuery cq = new CriteriaQuery(HotelMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelMonthly);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "exportsxlss")
	public String exportXlss(HotelMonthly hotelMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(HotelMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelMonthly, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		String hotelId = ResourceUtil.getSessionUserName().getId();
		List<HotelMonthly> hotelMonthlys = this.hotelservice
				.findHql(
						" from com.zzc.web.htoal.entity.HotelMonthly th where th.hid=? ",
						hotelId);
		map.put(NormalExcelConstants.FILE_NAME, "酒店月报信息");
		map.put(NormalExcelConstants.CLASS, HotelMonthly.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("酒店月报列表",
				"导出人:酒店用户", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelMonthlys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	// ***************************非星级酒店客户端月报******************************************************8
	@RequestMapping(params = "ckhotelf")
	public String users(HttpServletRequest request) {
		return "system/hotelClient/fckHotelMonthList";
	}

	/**
	 * 
	 * @date: 2016年12月19日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:
	 */
	@ResponseBody
	@RequestMapping(params = "fckDatagrid")
	public void monthGrids(HotelMonthly hotelMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		hotelMonthly.setHotelId(hotelId);
		CriteriaQuery cq = new CriteriaQuery(HotelMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelMonthly);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);

		TagUtil.datagrid(response, dataGrid);

	}

	@RequestMapping(params = "exportsxlsss")
	public String exportXlsss(HotelMonthly hotelMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(HotelMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelMonthly, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		String hotelId = ResourceUtil.getSessionUserName().getId();
		List<HotelMonthly> hotelMonthlys = this.hotelservice
				.findHql(
						" from com.zzc.web.htoal.entity.HotelMonthly th where th.hid=? and th.housegrade=? ",
						hotelId, HotelGrade.grade);
		map.put(NormalExcelConstants.FILE_NAME, "非星级酒店月报信息");
		map.put(NormalExcelConstants.CLASS, HotelMonthly.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("非星级酒店月报列表",
				"导出人:酒店用户", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelMonthlys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	/**
	 * 导出月报
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportMonthly")
	public void exportMonthly(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取上月
		Calendar calendar = Calendar.getInstance();
		String year1 = "";
		String month1 = "";
		// 上个月是12月的情况
		if (calendar.get(Calendar.MONTH) == 0) {
			month1 = "12";
			year1 = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		} else {
			month1 = String.valueOf(calendar.get(Calendar.MONTH));
			year1 = String.valueOf(calendar.get(Calendar.YEAR));
		}
		// 获取参数
		/* String licensenum = request.getParameter("licensenum"); */
		String name = new String(request.getParameter("name").getBytes(
				"iso8859-1"), "utf-8");
		/* String type = request.getParameter("type"); */
		String beginStar = request.getParameter("beginStar");
		String endStar = request.getParameter("endStar");
		String country = request.getParameter("country");
		String bay = request.getParameter("bay");
		String status = request.getParameter("status");
		String provinceState = request.getParameter("provinceState");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year == null || year.length() == 0) {
			year = year1;
		}
		if (month == null || month.length() == 0) {
			month = month1;
		}
		String excelName = "酒店月报.xls";
		String modelPath = "/com/zzc/web/export/model/hotel/" + excelName;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	hotelmanage.unitname, ");
		sql.append(" 	hotelmanage.organizationNum, ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '0', ");
		sql.append(" 	'其他', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '1', ");
		sql.append(" 	'舒适', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '2', ");
		sql.append(" 	'高档', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '3', ");
		sql.append(" 	'豪华', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '4', ");
		sql.append(" 	'一星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '5', ");
		sql.append(" 	'二星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '6', ");
		sql.append(" 	'三星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '7', ");
		sql.append(" 	'四星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	hotelmanage.housegrade = '8', ");
		sql.append(" 	'五星', ");
		sql.append(" 	'' ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) enterpriseType, ");
		sql.append("  t.`year`, ");
		sql.append("  t.`month`, ");
		sql.append("  t.total_monthday, ");
		sql.append("  t.total_monthtime, ");
		sql.append("  t.total_income, ");
		sql.append("  t.room_income `客房收入`, ");
		sql.append(" t.catering_income `餐厅收入`, ");
		sql.append(" t.other_income `其他收入`, ");
		sql.append(" t.rent_num `可供出租间数`, ");
		sql.append(" t.actual_rentnum `实际出租间数`, ");
		sql.append(" t.rent_percent `出租率`, ");
		sql.append(" t.filler, ");
		sql.append(" t.report_date ");
		sql.append(" FROM ");
		sql.append(" 	t_hotelmonthly t ");
		sql.append(" LEFT JOIN t_hotelmanage hotelmanage ON t.hotel_id = hotelmanage.id  where  1=1  ");
		sql.append(" and (t.status = '4' or t.district_status = '4') ");
		// 组装查询条件
		if (status != null && status.length() != 0) {
			sql.append(" and t.status=" + status);
		}
		if (provinceState != null && provinceState.length() != 0) {
			sql.append(" and t.province_state=" + provinceState);
		}
		if (year != null && year.length() != 0 && month != null
				&& month.length() != 0) {
			sql.append(" and t.month=" + month + " and t.year=" + year);
		}
		if (name != null && name.length() != 0) {
			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.unitname like '%"
					+ name + "%') ");
		}
		if (country != null && country.length() != 0) {
			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.w_county ="
					+ country + ")");
		}
		if (bay != null && bay.length() != 0) {
			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.bay_type ="
					+ bay + ")");
		}

		if (beginStar != null && beginStar.length() != 0 && endStar != null
				&& endStar.length() != 0) {
			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.housegrade between '"
					+ beginStar + "' and '" + endStar + "')");
		}

		/*
		 * if(type != null && type.length() != 0){ sql.append(
		 * " and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.housegrade ='"
		 * +type+"') "); }
		 */

		sql.append(" UNION ALL ");
		sql.append(" SELECT  ");
		sql.append("	'合计',");
		sql.append("'',  ");
		sql.append("   ");
		sql.append(" '',");
		sql.append("   '',");
		sql.append("  '',");
		sql.append("  sum(t.total_monthday), ");
		sql.append("  sum(t.total_monthtime), ");
		sql.append(" sum(cast(t.total_income as decimal(10,5))),  ");
		sql.append(" sum(cast(t.room_income as decimal(10,5))) `客房收入`, ");
		sql.append(" sum(cast(t.catering_income as decimal(10,5))) `餐厅收入`, ");
		sql.append(" sum(cast(t.other_income as decimal(10,5))) `其他收入`, ");
		sql.append(" sum(t.rent_num) `可供出租间数`, ");
		sql.append(" sum(t.actual_rentnum) `实际出租间数`, ");
		sql.append(" ROUND((sum(t.actual_rentnum)/sum(t.rent_num))*100,2) `出租率`, ");
		sql.append("   '', ");
		sql.append("  '' ");
		sql.append(" FROM  ");
		sql.append("t_hotelmonthly t  ");
		sql.append("LEFT JOIN t_hotelmanage hotelmanage ON t.hotel_id = hotelmanage.id  where  1=1");
		sql.append(" and (t.status = '4' or t.district_status = '4') ");
		if (status != null && status.length() != 0) {
			sql.append(" and t.status=" + status);
		}
		if (provinceState != null && provinceState.length() != 0) {
			sql.append(" and t.province_state=" + provinceState);
		}
		if (year != null && year.length() != 0 && month != null
				&& month.length() != 0) {
			sql.append(" and t.month=" + month + " and t.year=" + year);
		}
		if (name != null && name.length() != 0) {
			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.unitname like '%"
					+ name + "%') ");
		}
		if (country != null && country.length() != 0) {
			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.w_county ="
					+ country + ")");
		}
		if (bay != null && bay.length() != 0) {
			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.bay_type ="
					+ bay + ")");
		}

		if (beginStar != null && beginStar.length() != 0 && endStar != null
				&& endStar.length() != 0) {
			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_id and hm.housegrade between '"
					+ beginStar + "' and '" + endStar + "')");
		}

		ExportService emds = new ExportService(16);

		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
		} catch (Exception e) {

		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}

	}

	@RequestMapping(params = "isdistrict")
	@ResponseBody
	public String isdistrict(HttpServletRequest request) {
		if (this.isDistrictManager())
			return "1";
		else
			return "0";
	}

	/**
	 * 判断当前用户是否为区级管理员
	 * 
	 * @return boolean
	 */
	public boolean isDistrictManager() {
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService
				.findOneForJdbc(
						"select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)",
						userId);
		// 区级管理员做判断
		if (userMap.get("rolename") != null
				&& userMap.get("rolename").toString().contains("区级")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 检查上月报是否填报
	 * 
	 * @return
	 */
	public boolean isFilled() {
		boolean tip = false;
		// 获取当前登录用户ID=酒店ID
		String userId = ResourceUtil.getSessionUserName().getId();
		// 获取上个月报表的年份和月
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		if ("0".equals(month)) {
			month = "12";
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		}
		String sql = "select id from t_hotelmonthly t where t.`month`='"
				+ month + "' and t.`year`='" + year + "' and t.hotel_id = '"
				+ userId + "'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if (list.size() != 0)
			tip = true;
		return tip;
	}

	/**
	 * 
	 * 撤回酒店月报
	 * 
	 * @return 成功标志
	 */
	@RequestMapping(params = "revocation")
	@ResponseBody
	public String revocationHotelInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String tip = "success";

		String id = request.getParameter("id");

		try {
			// 获取月报对象
			HotelMonthly hotelMonthly = systemService.get(HotelMonthly.class,
					id);
			hotelMonthly.setProvinceState(Status.noSumbit);
			hotelMonthly.setRevocated("1");

			systemService.saveOrUpdate(hotelMonthly);
			systemService.addLog("撤回酒店月报成功:"+hotelMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "failed";
			systemService.addLog("撤回酒店月报失败:"+id, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		return tip;
	}
	
	/**
	 * 计算年初至本月的数据
	 * @param hotelMonthly
	 * @return
	 */
	private HotelMonthly calcTotal(HotelMonthly hotelMonthly, String userId){

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	sum(t.total_monthtime) total_monthtime, ");
		sql.append(" 	sum(t.total_monthday) total_monthday, ");
		sql.append(" 	sum(t.inland_monthtime) inland_monthtime, ");
		sql.append(" 	sum(t.inland_monthday) inland_monthday, ");
		sql.append(" 	sum(t.entry_monthtime) entry_monthtime, ");
		sql.append(" 	sum(t.entry_monthday) entry_monthday, ");
		sql.append(" 	sum(t.hongkong_monthtime) hongkong_monthtime, ");
		sql.append(" 	sum(t.hongkong_monthday) hongkong_monthday, ");
		sql.append(" 	sum(t.macau_monthtime) macau_monthtime, ");
		sql.append(" 	sum(t.macau_monthday) macau_monthday, ");
		sql.append(" 	sum(t.taiwan_monthtime) taiwan_monthtime, ");
		sql.append(" 	sum(t.taiwan_monthday) taiwan_monthday, ");
		sql.append(" 	sum(t.foreign_monthtime) foreign_monthtime, ");
		sql.append(" 	sum(t.foreign_monthday) foreign_monthday, ");
		sql.append(" 	sum(t.asian_month) asian_month, ");
		sql.append(" 	sum(t.japan_month) japan_month, ");
		sql.append(" 	sum(t.korea_month) korea_month, ");
		sql.append(" 	sum(t.mongolia_month) mongolia_month, ");
		sql.append(" 	sum(t.indonesia_month) indonesia_month, ");
		sql.append(" 	sum(t.malaysia_month) malaysia_month, ");
		sql.append(" 	sum(t.philippines_month) philippines_month, ");
		sql.append(" 	sum(t.singapore_month) singapore_month, ");
		sql.append(" 	sum(t.thailand_month) thailand_month, ");
		sql.append(" 	sum(t.india_month) india_month, ");
		sql.append(" 	sum(t.vietnam_month) vietnam_month, ");
		sql.append(" 	sum(t.burma_month) burma_month, ");
		sql.append(" 	sum(t.northkorea_month) northkorea_month, ");
		sql.append(" 	sum(t.pakistan_month) pakistan_month, ");
		sql.append(" 	sum(t.laos_month) laos_month, ");
		sql.append(" 	sum(t.cambodia_month) cambodia_month, ");
		sql.append(" 	sum(t.nepal_month) nepal_month, ");
		sql.append(" 	sum(t.srilanka_month) srilanka_month, ");
		sql.append(" 	sum(t.kz_month) kz_month, ");
		sql.append(" 	sum(t.kyrghyzstan_month) kyrghyzstan_month, ");
		sql.append(" 	sum(t.uz_month) uz_month, ");
		sql.append(" 	sum(t.tajikistan_month) tajikistan_month, ");
		sql.append(" 	sum(t.asianother_month) asianother_month, ");
		sql.append(" 	sum(t.europe_month) europe_month, ");
		sql.append(" 	sum(t.england_month) england_month, ");
		sql.append(" 	sum(t.france_month) france_month, ");
		sql.append(" 	sum(t.germany_month) germany_month, ");
		sql.append(" 	sum(t.italy_month) italy_month, ");
		sql.append(" 	sum(t.switzerland_month) switzerland_month, ");
		sql.append(" 	sum(t.sweden_month) sweden_month, ");
		sql.append(" 	sum(t.russia_month) russia_month, ");
		sql.append(" 	sum(t.spain_month) spain_month, ");
		sql.append(" 	sum(t.ireland_month) ireland_month, ");
		sql.append(" 	sum(t.ukraine_month) ukraine_month, ");
		sql.append(" 	sum(t.belgium_month) belgium_month, ");
		sql.append(" 	sum(t.cz_month) cz_month, ");
		sql.append(" 	sum(t.austria_month) austria_month, ");
		sql.append(" 	sum(t.portugal_month) portugal_month, ");
		sql.append(" 	sum(t.holland_month) holland_month, ");
		sql.append(" 	sum(t.europeother_month) europeother_month, ");
		sql.append(" 	sum(t.america_month) america_month, ");
		sql.append(" 	sum(t.usa_month) usa_month, ");
		sql.append(" 	sum(t.canada_month) canada_month, ");
		sql.append(" 	sum(t.mexico_month) mexico_month, ");
		sql.append(" 	sum(t.brazil_month) brazil_month, ");
		sql.append(" 	sum(t.argentina_month) argentina_month, ");
		sql.append(" 	sum(t.chile_month) chile_month, ");
		sql.append(" 	sum(t.peru_month) peru_month, ");
		sql.append(" 	sum(t.americaother_month) americaother_month, ");
		sql.append(" 	sum(t.oceania_month) oceania_month, ");
		sql.append(" 	sum(t.australia_month) australia_month, ");
		sql.append(" 	sum(t.nz_month) nz_month, ");
		sql.append(" 	sum(t.oceaniaother_month) oceaniaother_month, ");
		sql.append(" 	sum(t.africa_month) africa_month, ");
		sql.append(" 	sum(t.other_income) other_income ");
		sql.append(" FROM ");
		sql.append(" 	t_hotelmonthly t where ");
		sql.append(" t.`year` = '" + year + "' and CAST(t.`month` as SIGNED)  <" + month+" and t.hotel_id = '"+userId+"' ");

		Map<String, Object> map = systemService.findOneForJdbc(
				sql.toString(), null);
		// sum(t.total_monthtime) total_monthtime,
		String total_monthtime = map.get("total_monthtime") == null
				|| map.get("total_monthtime").toString().length() == 0 ? "0"
				: map.get("total_monthtime").toString();
		hotelMonthly.setTotalMonthYearTime(String.valueOf(Integer.parseInt(hotelMonthly.getTotalMonthTime()
						+ Integer.parseInt(total_monthtime))));
		// sum(t.total_monthday) total_monthday,
		String total_monthday = map.get("total_monthday") == null
				|| map.get("total_monthday").toString().length() == 0 ? "0"
				: map.get("total_monthday").toString();
		hotelMonthly.setTotalMonthYearDay(String.valueOf(Integer.parseInt(hotelMonthly.getTotalMonthDay()) + Integer.parseInt(total_monthday)));
		// sum(t.inland_monthtime) inland_monthtime,
		String inland_monthtime = map.get("inland_monthtime") == null
				|| map.get("inland_monthtime").toString().length() == 0 ? "0"
				: map.get("inland_monthtime").toString();
		hotelMonthly.setInlandMonthYearTime(String.valueOf(Integer.parseInt(hotelMonthly.getInlandMonthTime()) + Integer.parseInt(inland_monthtime)));
		// sum(t.inland_monthday) inland_monthday,
		String inland_monthday = map.get("inland_monthday") == null
				|| map.get("inland_monthday").toString().length() == 0 ? "0"
				: map.get("inland_monthday").toString();
		hotelMonthly.setInlandMonthYearDay(String.valueOf(Integer.parseInt(hotelMonthly.getInlandMonthDay()) + Integer.parseInt(inland_monthday)));
		// sum(t.entry_monthtime) entry_monthtime,
		String entry_monthtime = map.get("entry_monthtime") == null
				|| map.get("entry_monthtime").toString().length() == 0 ? "0"
				: map.get("entry_monthtime").toString();
		hotelMonthly.setEntryMonthYearTime(String.valueOf(Integer.parseInt(hotelMonthly.getEntryMonthTime()) + Integer.parseInt(entry_monthtime)));
		// sum(t.entry_monthday) entry_monthday,
		String entry_monthday = map.get("entry_monthday") == null
				|| map.get("entry_monthday").toString().length() == 0 ? "0"
				: map.get("entry_monthday").toString();
		hotelMonthly.setEntryMonthYearDay(String.valueOf(Integer.parseInt(hotelMonthly.getEntryMonthDay()) + Integer.parseInt(entry_monthday)));
		// sum(t.hongkong_monthtime) hongkong_monthtime,
		String hongkong_monthtime = map.get("hongkong_monthtime") == null
				|| map.get("hongkong_monthtime").toString().length() == 0 ? "0"
				: map.get("hongkong_monthtime").toString();
		hotelMonthly.setHongkongMonthTime(String.valueOf(Integer.parseInt(hotelMonthly.getHongkongMonthTime()) + Integer.parseInt(hongkong_monthtime)));
		// sum(t.hongkong_monthday) hongkong_monthday,
		String hongkong_monthday = map.get("hongkong_monthday") == null
				|| map.get("hongkong_monthday").toString().length() == 0 ? "0"
				: map.get("hongkong_monthday").toString();
		hotelMonthly.setHongkongMonthYearDay(String.valueOf(Integer.parseInt(hotelMonthly.getHongkongMonthDay()) + Integer.parseInt(hongkong_monthday)));
		// sum(t.macau_monthtime) macau_monthtime,
		String macau_monthtime = map.get("macau_monthtime") == null
				|| map.get("macau_monthtime").toString().length() == 0 ? "0"
				: map.get("macau_monthtime").toString();
		hotelMonthly.setMacauMonthYearTime(String.valueOf(Integer.parseInt(hotelMonthly.getMacauMonthTime()) + Integer.parseInt(macau_monthtime)));
		// sum(t.macau_monthday) macau_monthday,
		String macau_monthday = map.get("macau_monthday") == null
				|| map.get("macau_monthday").toString().length() == 0 ? "0"
				: map.get("macau_monthday").toString();
		hotelMonthly.setMacauMonthYearDay(String.valueOf(Integer.parseInt(hotelMonthly.getMacauMonthDay()) + Integer.parseInt(macau_monthday)));
		// sum(t.taiwan_monthtime) taiwan_monthtime,
		String taiwan_monthtime = map.get("taiwan_monthtime") == null
				|| map.get("taiwan_monthtime").toString().length() == 0 ? "0"
				: map.get("taiwan_monthtime").toString();
		hotelMonthly.setTaiwanMonthYearTime(String.valueOf(Integer.parseInt(hotelMonthly.getTaiwanMonthTime()) + Integer.parseInt(taiwan_monthtime)));
		// sum(t.taiwan_monthday) taiwan_monthday,
		String taiwan_monthday = map.get("taiwan_monthday") == null
				|| map.get("taiwan_monthday").toString().length() == 0 ? "0"
				: map.get("taiwan_monthday").toString();
		hotelMonthly.setTaiwanMonthYearDay(String.valueOf(Integer.parseInt(hotelMonthly.getTaiwanMonthDay()) + Integer.parseInt(taiwan_monthday)));
		// sum(t.foreign_monthtime) foreign_monthtime,
		String foreign_monthtime = map.get("foreign_monthtime") == null
				|| map.get("foreign_monthtime").toString().length() == 0 ? "0"
				: map.get("foreign_monthtime").toString();
		hotelMonthly.setForeignMonthYearTime(String.valueOf(Integer.parseInt(hotelMonthly.getForeignMonthTime()) + Integer.parseInt(foreign_monthtime)));
		// sum(t.foreign_monthday) foreign_monthday,
		String foreign_monthday = map.get("foreign_monthday") == null
				|| map.get("foreign_monthday").toString().length() == 0 ? "0"
				: map.get("foreign_monthday").toString();
		hotelMonthly.setForeignMonthYearDay(String.valueOf(Integer.parseInt(hotelMonthly.getForeignMonthDay()) + Integer.parseInt(foreign_monthday)));
		// sum(t.asian_month) asian_month,
		String asian_month = map.get("asian_month") == null
				|| map.get("asian_month").toString().length() == 0 ? "0"
				: map.get("asian_month").toString();
		hotelMonthly.setAsianMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getAsianMonth()) + Integer.parseInt(asian_month)));
		// sum(t.japan_month) japan_month,
		String japan_month = map.get("japan_month") == null
				|| map.get("japan_month").toString().length() == 0 ? "0"
				: map.get("japan_month").toString();
		hotelMonthly.setJapanMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getJapanMonth()) + Integer.parseInt(japan_month)));
		// sum(t.korea_month) korea_month,
		String korea_month = map.get("korea_month") == null
				|| map.get("korea_month").toString().length() == 0 ? "0"
				: map.get("korea_month").toString();
		hotelMonthly.setKoreaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getKoreaMonth()) + Integer.parseInt(korea_month)));
		// sum(t.mongolia_month) mongolia_month,
		String mongolia_month = map.get("mongolia_month") == null
				|| map.get("mongolia_month").toString().length() == 0 ? "0"
				: map.get("mongolia_month").toString();
		hotelMonthly.setMongoliaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getMongoliaMonth()) + Integer.parseInt(mongolia_month)));
		// sum(t.indonesia_month) indonesia_month,
		String indonesia_month = map.get("indonesia_month") == null
				|| map.get("indonesia_month").toString().length() == 0 ? "0"
				: map.get("indonesia_month").toString();
		hotelMonthly.setIndonesiaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getIndonesiaMonth()) + Integer.parseInt(indonesia_month)));
		// sum(t.malaysia_month) malaysia_month,
		String malaysia_month = map.get("malaysia_month") == null
				|| map.get("malaysia_month").toString().length() == 0 ? "0"
				: map.get("malaysia_month").toString();
		hotelMonthly.setMalaysiaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getMalaysiaMonth()) + Integer.parseInt(malaysia_month)));
		// sum(t.philippines_month) philippines_month,
		String philippines_month = map.get("philippines_month") == null
				|| map.get("philippines_month").toString().length() == 0 ? "0"
				: map.get("philippines_month").toString();
		hotelMonthly.setPhilippinesMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getPhilippinesMonth()) + Integer.parseInt(philippines_month)));
		// sum(t.singapore_month) singapore_month,
		String singapore_month = map.get("singapore_month") == null
				|| map.get("singapore_month").toString().length() == 0 ? "0"
				: map.get("singapore_month").toString();
		hotelMonthly.setSingaporeMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getSingaporeMonth()) + Integer.parseInt(singapore_month)));
		// sum(t.thailand_month) thailand_month,
		String thailand_month = map.get("thailand_month") == null
				|| map.get("thailand_month").toString().length() == 0 ? "0"
				: map.get("thailand_month").toString();
		hotelMonthly.setThailandMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getThailandMonth()) + Integer.parseInt(thailand_month)));
		// sum(t.india_month) india_month,
		String india_month = map.get("india_month") == null
				|| map.get("india_month").toString().length() == 0 ? "0"
				: map.get("india_month").toString();
		hotelMonthly.setIndiaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getIndiaMonth()) + Integer.parseInt(india_month)));
		// sum(t.vietnam_month) vietnam_month,
		String vietnam_month = map.get("vietnam_month") == null
				|| map.get("vietnam_month").toString().length() == 0 ? "0"
				: map.get("vietnam_month").toString();
		hotelMonthly.setVietnamMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getVietnamMonth()) + Integer.parseInt(vietnam_month)));
		// sum(t.burma_month) burma_month,
		String burma_month = map.get("burma_month") == null
				|| map.get("burma_month").toString().length() == 0 ? "0"
				: map.get("burma_month").toString();
		hotelMonthly.setBurmaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getBurmaMonth()) + Integer.parseInt(burma_month)));
		// sum(t.northkorea_month) northkorea_month,
		String northkorea_month = map.get("northkorea_month") == null
				|| map.get("northkorea_month").toString().length() == 0 ? "0"
				: map.get("northkorea_month").toString();
		hotelMonthly.setNorthkoreaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getNorthkoreaMonth()) + Integer.parseInt(northkorea_month)));
		// sum(t.pakistan_month) pakistan_month,
		String pakistan_month = map.get("pakistan_month") == null
				|| map.get("pakistan_month").toString().length() == 0 ? "0"
				: map.get("pakistan_month").toString();
		hotelMonthly.setPakistanMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getPakistanMonth()) + Integer.parseInt(northkorea_month)));
		// sum(t.laos_month) laos_month,
		String laos_month = map.get("laos_month") == null
				|| map.get("laos_month").toString().length() == 0 ? "0"
				: map.get("laos_month").toString();
		hotelMonthly.setLaosMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getLaosMonth()) + Integer.parseInt(laos_month)));;
		// sum(t.cambodia_month) cambodia_month,
		String cambodia_month = map.get("cambodia_month") == null
				|| map.get("cambodia_month").toString().length() == 0 ? "0"
				: map.get("cambodia_month").toString();
		hotelMonthly.setCambodiaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getCambodiaMonth()) + Integer.parseInt(cambodia_month)));
		// sum(t.nepal_month) nepal_month,
		String nepal_month = map.get("nepal_month") == null
				|| map.get("nepal_month").toString().length() == 0 ? "0"
				: map.get("nepal_month").toString();
		hotelMonthly.setNepalMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getNepalMonth()) + Integer.parseInt(nepal_month)));
		// sum(t.srilanka_month) srilanka_month,
		String srilanka_month = map.get("srilanka_month") == null
				|| map.get("srilanka_month").toString().length() == 0 ? "0"
				: map.get("srilanka_month").toString();
		hotelMonthly.setSrilankaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getSrilankaMonth()) + Integer.parseInt(srilanka_month)));
		// sum(t.kz_month) kz_month,
		String kz_month = map.get("kz_month") == null
				|| map.get("kz_month").toString().length() == 0 ? "0" : map
				.get("kz_month").toString();
		hotelMonthly.setKzMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getKzMonth()) + Integer.parseInt(kz_month)));
		// sum(t.kyrghyzstan_month) kyrghyzstan_month,
		String kyrghyzstan_month = map.get("kyrghyzstan_month") == null
				|| map.get("kyrghyzstan_month").toString().length() == 0 ? "0"
				: map.get("kyrghyzstan_month").toString();
		hotelMonthly.setKyrghyzstanMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getKyrghyzstanMonth()) + Integer.parseInt(kyrghyzstan_month)));
		// sum(t.uz_month) uz_month,
		String uz_month = map.get("uz_month") == null
				|| map.get("uz_month").toString().length() == 0 ? "0" : map
				.get("uz_month").toString();
		hotelMonthly.setUzMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getUzMonth()) + Integer.parseInt(uz_month)));
		// sum(t.tajikistan_month) tajikistan_month,
		String tajikistan_month = map.get("tajikistan_month") == null
				|| map.get("tajikistan_month").toString().length() == 0 ? "0"
				: map.get("tajikistan_month").toString();
		hotelMonthly.setTajikistanMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getTajikistanMonth()) + Integer.parseInt(tajikistan_month)));
		// sum(t.asianother_month) asianother_month,
		String asianother_month = map.get("asianother_month") == null
				|| map.get("asianother_month").toString().length() == 0 ? "0"
				: map.get("asianother_month").toString();
		hotelMonthly.setAsianOtherMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getAsianOtherMonth()) + Integer.parseInt(asianother_month)));
		// sum(t.europe_month) europe_month,
		String europe_month = map.get("europe_month") == null
				|| map.get("europe_month").toString().length() == 0 ? "0"
				: map.get("europe_month").toString();
		hotelMonthly.setEuropeMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getEuropeMonth()) + Integer.parseInt(europe_month)));
		// sum(t.england_month) england_month,
		String england_month = map.get("england_month") == null
				|| map.get("england_month").toString().length() == 0 ? "0"
				: map.get("england_month").toString();
		hotelMonthly.setEnglandMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getEnglandMonth()) + Integer.parseInt(england_month)));
		// sum(t.france_month) france_month,
		String france_month = map.get("france_month") == null
				|| map.get("france_month").toString().length() == 0 ? "0"
				: map.get("france_month").toString();
		hotelMonthly.setFranceMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getFranceMonth()) + Integer.parseInt(france_month)));
		// sum(t.germany_month) germany_month,
		String germany_month = map.get("germany_month") == null
				|| map.get("germany_month").toString().length() == 0 ? "0"
				: map.get("germany_month").toString();
		hotelMonthly.setGermanyMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getGermanyMonth()) + Integer.parseInt(germany_month)));
		// sum(t.italy_month) italy_month,
		String italy_month = map.get("italy_month") == null
				|| map.get("italy_month").toString().length() == 0 ? "0"
				: map.get("italy_month").toString();
		hotelMonthly.setItalyMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getItalyMonth()) + Integer.parseInt(italy_month)));
		// sum(t.switzerland_month) switzerland_month,
		String switzerland_month = map.get("switzerland_month") == null
				|| map.get("switzerland_month").toString().length() == 0 ? "0"
				: map.get("switzerland_month").toString();
		hotelMonthly.setSwitzerlandMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getSwitzerlandMonth()) + Integer.parseInt(switzerland_month)));
		// sum(t.sweden_month) sweden_month,
		String sweden_month = map.get("sweden_month") == null
				|| map.get("sweden_month").toString().length() == 0 ? "0"
				: map.get("sweden_month").toString();
		hotelMonthly.setSwedenMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getSwedenMonth()) + Integer.parseInt(sweden_month)));
		// sum(t.russia_month) russia_month,
		String russia_month = map.get("russia_month") == null
				|| map.get("russia_month").toString().length() == 0 ? "0"
				: map.get("russia_month").toString();
		hotelMonthly.setRussiaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getRussiaMonth()) + Integer.parseInt(russia_month)));
		// sum(t.spain_month) spain_month,
		String spain_month = map.get("spain_month") == null
				|| map.get("spain_month").toString().length() == 0 ? "0"
				: map.get("spain_month").toString();
		hotelMonthly.setSpainMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getSpainMonth()) + Integer.parseInt(spain_month)));
		// sum(t.ireland_month) ireland_month,
		String ireland_month = map.get("ireland_month") == null
				|| map.get("ireland_month").toString().length() == 0 ? "0"
				: map.get("ireland_month").toString();
		hotelMonthly.setIrelandMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getIrelandMonth()) + Integer.parseInt(ireland_month)));
		// sum(t.ukraine_month) ukraine_month,
		String ukraine_month = map.get("ukraine_month") == null
				|| map.get("ukraine_month").toString().length() == 0 ? "0"
				: map.get("ukraine_month").toString();
		hotelMonthly.setUkraineMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getUkraineMonth()) + Integer.parseInt(ukraine_month)));
		// sum(t.belgium_month) belgium_month,
		String belgium_month = map.get("belgium_month") == null
				|| map.get("belgium_month").toString().length() == 0 ? "0"
				: map.get("belgium_month").toString();
		hotelMonthly.setBelgiumMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getBelgiumMonth()) + Integer.parseInt(belgium_month)));
		// sum(t.cz_month) cz_month,
		String cz_month = map.get("cz_month") == null
				|| map.get("cz_month").toString().length() == 0 ? "0" : map
				.get("cz_month").toString();
		hotelMonthly.setCzMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getCzMonth()) + Integer.parseInt(cz_month)));
		// sum(t.austria_month) austria_month,
		String austria_month = map.get("austria_month") == null
				|| map.get("austria_month").toString().length() == 0 ? "0"
				: map.get("austria_month").toString();
		hotelMonthly.setAustriaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getAustriaMonth()) + Integer.parseInt(austria_month)));
		// sum(t.portugal_month) portugal_month,
		String portugal_month = map.get("portugal_month") == null
				|| map.get("portugal_month").toString().length() == 0 ? "0"
				: map.get("portugal_month").toString();
		hotelMonthly.setPortugalMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getPortugalMonth()) + Integer.parseInt(portugal_month)));
		// sum(t.holland_month) holland_month,
		String holland_month = map.get("holland_month") == null
				|| map.get("holland_month").toString().length() == 0 ? "0"
				: map.get("holland_month").toString();
		hotelMonthly.setHollandMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getHollandMonth()) + Integer.parseInt(holland_month)));
		// sum(t.europeother_month) europeother_month,
		String europeother_month = map.get("europeother_month") == null
				|| map.get("europeother_month").toString().length() == 0 ? "0"
				: map.get("europeother_month").toString();
		hotelMonthly.setEuropeOtherMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getEuropeOtherMonth()) + Integer.parseInt(europeother_month)));
		// sum(t.america_month) america_month,
		String america_month = map.get("america_month") == null
				|| map.get("america_month").toString().length() == 0 ? "0"
				: map.get("america_month").toString();
		hotelMonthly.setAmericaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getAmericaMonth()) + Integer.parseInt(america_month)));
		// sum(t.usa_month) usa_month,
		String usa_month = map.get("usa_month") == null
				|| map.get("usa_month").toString().length() == 0 ? "0"
				: map.get("usa_month").toString();
		hotelMonthly.setUsaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getUsaMonth()) + Integer.parseInt(usa_month)));
		// sum(t.canada_month) canada_month,
		String canada_month = map.get("canada_month") == null
				|| map.get("canada_month").toString().length() == 0 ? "0"
				: map.get("canada_month").toString();
		hotelMonthly.setCanadaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getCanadaMonth()) + Integer.parseInt(canada_month)));
		// sum(t.mexico_month) mexico_month,
		String mexico_month = map.get("mexico_month") == null
				|| map.get("mexico_month").toString().length() == 0 ? "0"
				: map.get("mexico_month").toString();
		hotelMonthly.setMexicoMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getMexicoMonth()) + Integer.parseInt(mexico_month)));
		// sum(t.brazil_month) brazil_month,
		String brazil_month = map.get("brazil_month") == null
				|| map.get("brazil_month").toString().length() == 0 ? "0"
				: map.get("brazil_month").toString();
		hotelMonthly.setBrazilMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getBrazilMonth()) + Integer.parseInt(brazil_month)));
		// sum(t.argentina_month) argentina_month,
		String argentina_month = map.get("argentina_month") == null
				|| map.get("argentina_month").toString().length() == 0 ? "0"
				: map.get("argentina_month").toString();
		hotelMonthly.setArgentinaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getArgentinaMonth()) + Integer.parseInt(argentina_month)));
		// sum(t.chile_month) chile_month,
		String chile_month = map.get("chile_month") == null
				|| map.get("chile_month").toString().length() == 0 ? "0"
				: map.get("chile_month").toString();
		hotelMonthly.setChileMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getChileMonth()) + Integer.parseInt(chile_month)));
		// sum(t.peru_month) peru_month,
		String peru_month = map.get("peru_month") == null
				|| map.get("peru_month").toString().length() == 0 ? "0"
				: map.get("peru_month").toString();
		hotelMonthly.setPeruMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getPeruMonth()) + Integer.parseInt(peru_month)));
		// sum(t.americaother_month) americaother_month,
		String americaother_month = map.get("americaother_month") == null
				|| map.get("americaother_month").toString().length() == 0 ? "0"
				: map.get("americaother_month").toString();
		hotelMonthly.setAmericaOtherMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getAmericaOtherMonth()) + Integer.parseInt(americaother_month)));
		// sum(t.oceania_month) oceania_month,
		String oceania_month = map.get("oceania_month") == null
				|| map.get("oceania_month").toString().length() == 0 ? "0"
				: map.get("oceania_month").toString();
		hotelMonthly.setOceaniaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getOceaniaMonth()) + Integer.parseInt(oceania_month)));
		// sum(t.australia_month) australia_month,
		String australia_month = map.get("australia_month") == null
				|| map.get("australia_month").toString().length() == 0 ? "0"
				: map.get("australia_month").toString();
		hotelMonthly.setAustraliaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getAustraliaMonth()) + Integer.parseInt(australia_month)));
		// sum(t.nz_month) nz_month,
		String nz_month = map.get("nz_month") == null
				|| map.get("nz_month").toString().length() == 0 ? "0" : map
				.get("nz_month").toString();
		hotelMonthly.setNzMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getNzMonth()) + Integer.parseInt(nz_month)));
		// sum(t.oceaniaother_month) oceaniaother_month,
		String oceaniaother_month = map.get("oceaniaother_month") == null
				|| map.get("oceaniaother_month").toString().length() == 0 ? "0"
				: map.get("oceaniaother_month").toString();
		hotelMonthly.setOceaniaOtherMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getOceaniaOtherMonth()) + Integer.parseInt(oceaniaother_month)));
		// sum(t.africa_month) africa_month,
		String africa_month = map.get("africa_month") == null
				|| map.get("africa_month").toString().length() == 0 ? "0"
				: map.get("africa_month").toString();
		hotelMonthly.setAfricaMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getAfricaMonth()) + Integer.parseInt(africa_month)));
		// sum(t.other_income) other_income
		String other_income = map.get("other_income") == null
				|| map.get("other_income").toString().length() == 0 ? "0"
				: map.get("other_income").toString();
		hotelMonthly.setOtherMonthYear(String.valueOf(Integer.parseInt(hotelMonthly.getOtherMonth()) + Integer.parseInt(other_income)));
		
		return hotelMonthly;
	
	}
	
}
