package com.zzc.web.htoal.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import com.zzc.web.htoal.entity.HotelAnnual;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.service.Hotelservice;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

@Scope("prototype")
@Controller
@RequestMapping("/hotelAnnualController")
public class HotelAnnualController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(HotelAnnualController.class);
	private String message = null;
	TimeUtils timeUtils = new TimeUtils();
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	// 设置状态的工具类 类型为byte

	// Status status= new Status();

	private Hotelservice hotelservice;

	@Autowired
	public void setHotelservice(Hotelservice hotelservice) {
		this.hotelservice = hotelservice;
	}

	// 年报管理跳转页面
	@RequestMapping(params = "hotelAnnual")
	public ModelAndView hotelAnnual(HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		request.setAttribute("year", year);
		return new ModelAndView("system/hotel/hotelAnnualList");
	}

	// 跳转到save保存页面
	@RequestMapping(params = "addorupdate")
	public String hotelInfo(HttpServletRequest req) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		Hotelmanage hm = this.systemService.getEntity(Hotelmanage.class,
				hotelId);
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		
		HotelAnnual annual = new HotelAnnual();
		annual.setYear(year);
		req.setAttribute("annualData", annual);
		req.setAttribute("hotelmanage", hm);
		return "system/hotel/saveHotelAnnual";
	}
	
	@RequestMapping(params = "editByUser")
	public String editByUser(HttpServletRequest req) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		String annualId = req.getParameter("annualId");
		
		
		Hotelmanage hm = this.systemService.getEntity(Hotelmanage.class,
				hotelId);
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		
		HotelAnnual annual = systemService.get(HotelAnnual.class, annualId);
		annual.setYear(year);
		req.setAttribute("annualData", annual);
		req.setAttribute("hotelmanage", hm);
		return "system/hotel/saveHotelAnnual";
	}

	// 跳转到年报审核页面
	@RequestMapping(params = "hotelAnnualCheck")
	public ModelAndView hotelMonthCheck() {
		return new ModelAndView("system/hotel/hotelAnnualCheckList");
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson add(HotelAnnual hotelAnnual, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		if(hotelAnnual.getId() == null || hotelAnnual.getId().length() == 0){
			if(this.isFilled()){
				j.setMsg("已填报年报!");
				return j;
			}
			hotelAnnual.setId(null);
		}
		
		// 通过工具类获取用户对象
		TSUser user = ResourceUtil.getSessionUserName();
		// id未酒店的id
		hotelAnnual.setHid(user.getId());
		// hotelservice.loadAll(hotelmanage.getClass());
		hotelAnnual.setStatus(Status.noAudit);
		hotelAnnual.setDistrictStatus(Status.noAudit);
		hotelAnnual.setCountryState(Status.noSumbit);
		Hotelmanage hotelmanage = systemService.getEntity(Hotelmanage.class,
				hotelAnnual.getHid());
		hotelAnnual.setHotelmanage(hotelmanage);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hotelAnnual.setReportTime(sdf.format(new Date()));
		
		// 加分
		int year = Integer.parseInt(hotelAnnual.getYear());
		boolean rs = ReportTimeCheck.annualTimeCheck(year);
		if(rs){
			hotelAnnual.setScore(40);
			UserScoreUtils.scoreChange(40);
		}else{
			hotelAnnual.setScore(20);
			UserScoreUtils.scoreChange(20);
		}						
		
		systemService.saveOrUpdate(hotelAnnual);
		systemService.addLog("填报酒店财务年报成功:"+hotelAnnual.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		
		j.setMsg("添加成功");

		return j;
	}

	/**
	 * 
	 * @date: 2016年12月7日
	 * @Author: 龙亚辉
	 * @Email: lkkeep@163.com
	 * @param:
	 * @return:删除功能
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HotelAnnual hotelAnnual, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			hotelAnnual = hotelservice.getEntity(HotelAnnual.class, id);
			hotelservice.delete(hotelAnnual);
			message = "季报删除成功";
		} catch (Exception e) {
			message = "季报删除失败";
		}

		j.setMsg(message);
		return j;
	}

	/*
	 * 年报管理功能
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(HotelAnnual hotelAnnual, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(HotelAnnual.class, dataGrid);

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
				cq.add(Restrictions.in("hotelAid", parmas));
			} else {
				cq.add(Restrictions.in("hotelAid", new Object[] { "" }));
			}
		}
		// 查询条件
//		cq.add(Restrictions.or(Restrictions.eq("status", "4"),Restrictions.eq("districtStatus", "4")));
		String year = hotelAnnual.getYear();
		Calendar calendar = Calendar.getInstance();
		String lastYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		String hotelName = hotelAnnual.getHotelmanage() == null ? "" : hotelAnnual.getHotelmanage().getUnitname();
		String hotelGrade = hotelAnnual.getHotelmanage() == null ? "" : hotelAnnual.getHotelmanage().getHousegrade();
		String hotelCounty = hotelAnnual.getHotelmanage() == null ? "" : hotelAnnual.getHotelmanage().getCounty();
		String hotelBay = hotelAnnual.getHotelmanage() == null ? "" : hotelAnnual.getHotelmanage().getBay();
		String districtStatus = hotelAnnual.getDistrictStatus();
		String status = hotelAnnual.getStatus();
		String countryState = hotelAnnual.getCountryState();
		if(year != null && year.length() != 0){
			cq.add(Restrictions.like("year", "%"+year+"%"));
		}else{
			cq.add(Restrictions.like("year", "%"+lastYear+"%"));
		}
		if(hotelName != null && hotelName.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.unitname like '%"+hotelName+"%' and t.id=hotel_aid)"));
		}
		if(hotelGrade != null && hotelGrade.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.housegrade = '"+hotelGrade+"' and t.id=hotel_aid)"));
		}
		if(hotelCounty != null && hotelCounty.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.w_county = '"+hotelCounty+"' and t.id=hotel_aid)"));
		}
		if(hotelBay != null && hotelBay.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.bay_type = '"+hotelBay+"' and t.id=hotel_aid)"));
		}
		if(districtStatus != null && districtStatus.length() != 0){
			cq.add(Restrictions.eq("districtStatus", districtStatus));
		}
		if(status != null && status.length() != 0){
			cq.add(Restrictions.eq("status", status));
		}
		if(countryState != null && countryState.length() != 0){
			cq.add(Restrictions.eq("countryState", countryState));
		}
		
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new HotelAnnual());

		systemService.getDataGridReturn(cq, true);
		// cq.add();
		TagUtil.datagrid(response, dataGrid);
	}

	// 返回年报审核列表
	@RequestMapping(params = "annualDatagrid")
	public void annualDatagrid(Hotelmanage hotelmanage, HotelAnnual annual,

	HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cs = new CriteriaQuery(Hotelmanage.class, dataGrid);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.YEAR, -1);
		String lastYear = sdf.format(gc.getTime());
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
			cs.add(Restrictions.eq("county", countynum));
		}
		// 查询条件
		if(hotelmanage.getUnitname() != null && hotelmanage.getUnitname().length() != 0){
        	cs.add(Restrictions.like("unitname", "%"+hotelmanage.getUnitname()+"%"));
        }
        if(hotelmanage.getHousegrade() != null && hotelmanage.getHousegrade().length() != 0){
        	cs.add(Restrictions.eq("housegrade", hotelmanage.getHousegrade()));
        }
        if(hotelmanage.getCounty() != null && hotelmanage.getCounty().length() != 0){
        	cs.add(Restrictions.eq("county", hotelmanage.getCounty()));
        }
        if(hotelmanage.getBay() != null && hotelmanage.getBay().length() != 0){
        	cs.add(Restrictions.eq("bay", hotelmanage.getBay()));
        }
        if(hotelmanage.getHotelAnnual() != null && hotelmanage.getHotelAnnual().getDistrictStatus() != null && hotelmanage.getHotelAnnual().getDistrictStatus().length() != 0){
        	cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select 1 from t_hotel_annual t where t.district_status = '"+hotelmanage.getHotelAnnual().getDistrictStatus()+"' and t.year ='"+lastYear+"' and t.hotel_aid=this_.id) "));
        }
        if(hotelmanage.getHotelAnnual() != null && hotelmanage.getHotelAnnual().getStatus() != null && hotelmanage.getHotelAnnual().getStatus().length() != 0){
        	cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select 1 from t_hotel_annual t where t.status = '"+hotelmanage.getHotelAnnual().getStatus()+"'  and t.year ='"+lastYear+"' and t.hotel_aid=this_.id) "));
        }
        if(hotelmanage.getHotelAnnual() != null && hotelmanage.getHotelAnnual().getCountryState() != null && hotelmanage.getHotelAnnual().getCountryState().length() != 0){
        	cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select 1 from t_hotel_annual t where t.country_state = '"+hotelmanage.getHotelAnnual().getCountryState()+"'  and t.year ='"+lastYear+"' and t.hotel_aid=this_.id) "));
        }
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cs,
				new Hotelmanage());
		cs.in("housegrade", new String[] { "4", "5", "6", "7", "8" });// {"其他_0","舒适_1","高档_2","豪华_3","一星_4","二星_5","三星_6","四星_7","五星_8"}
		cs.add();
		systemService.getDataGridReturn(cs, true);
		
		for (Object o : dataGrid.getResults()) {
			Hotelmanage hm = (Hotelmanage) o;
			List<HotelAnnual> list = hm.getHotelAnnualList();
			if (list != null) {
				for (HotelAnnual hAnnual : list) {
					// String d2Number =
					// timeUtils.getCurrentMonth(hAnnual.getReportTime());
					String aunnalYear = hAnnual.getYear();
					if (lastYear.equals(aunnalYear)) {
						hm.setHotelAnnual(hAnnual);
					}
					break;
				}
				if (hm.getHotelAnnual() == null) {

					HotelAnnual ha = new HotelAnnual();
					// 未审核状态
					ha.setYear(lastYear);
					ha.setStatus(Status.noSumbit);
					ha.setDistrictStatus(Status.noSumbit);
					ha.setCountryState(Status.noSumbit);
					hm.setHotelAnnual(ha);
				}
			}

		}
		TagUtil.datagrid(response, dataGrid);
	}

	// 年报审核管理员操作
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			HotelAnnual hotelAnnual, Hotelmanage hotelmanage, String statusid) {
		AjaxJson j = new AjaxJson();
		j.setMsg("操作成功");
		// 季报的id
		String annualId = request.getParameter("annualId");
		request.setAttribute("annualId", annualId);
		try {
			if (StringUtil.isNotEmpty(annualId)) {
				// 根据id查询酒店年报类
				HotelAnnual ha = systemService.getEntity(HotelAnnual.class,
						annualId);
				// TODO 定时器
//				String rs = this.addYearReport(ha, "add");
//				if(!"200".equals(rs)){
//					j.setMsg(rs);
//					return j;
//				}
				// 判断是否区级审核
				if(this.isDistrictManager()){
					ha.setDistrictStatus(Status.passAudit);
				}else{
					// 表示管理员审核通过
					ha.setStatus(Status.passAudit);
					// 设置审核时间
					ha.setAuditTime(new Date());
				}
				
				systemService.saveOrUpdate(ha);
				j.setMsg("管理员审核成功");
				systemService.addLog("审核通过酒店财务年报成功:"+hotelAnnual.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("管理员审核失败");
			systemService.addLog("审核通过酒店财务年报失败:"+annualId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}

	// 审核结果不通过
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			Hotelmanage hotelmanage, HotelAnnual HotelAnnual, String statusid) {
		AjaxJson j = new AjaxJson();
		// System.out.println("+++++++++++++++++++++++++++++");
		String annualId = request.getParameter("annualId");
		request.setAttribute("annualId", annualId);
		if (StringUtil.isNotEmpty(annualId)) {
			HotelAnnual hotelAnnual = systemService.getEntity(
					HotelAnnual.class, annualId);
			// 判断是否区级审核
			if(this.isDistrictManager()){
				hotelAnnual.setDistrictStatus(Status.notAudit);// 管理员审核未通过
			}else{
				hotelAnnual.setStatus(Status.notAudit);// 管理员审核未通过
			}						
			
			try {
				//减分
				Integer score = hotelAnnual.getScore() == null ? 0
						: hotelAnnual.getScore();
				if (score >= 10) {
					score = score - 10;
					UserScoreUtils.scoreChange(-10, hotelAnnual.getHotelAid());
				} else {
					UserScoreUtils.scoreChange((0 - score),
							hotelAnnual.getHotelAid());
					score = 0;
				}
				hotelAnnual.setScore(score);
			} catch (Exception e) {
				
			}
			systemService.save(hotelAnnual);
			systemService.addLog("审核退回酒店财务年报成功:"+hotelAnnual.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}

	// 审核页面
	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,
			HotelAnnual hotelAnnual, Hotelmanage hotelmanage, DataGrid dataGrid) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		String annualId = request.getParameter("annualId");
		request.setAttribute("hotelmanageId", hotelmanageId);
		Hotelmanage h = this.systemService
				.get(Hotelmanage.class, hotelmanageId);
		HotelAnnual ha = this.systemService.get(HotelAnnual.class, annualId);

		// request.setAttribute("scenicdata", s);
		ha.setHotelmanage(hotelmanage);
		// request.setAttribute("monthdata", m);
		// scenicSpotMonth.setScenicData(s);
		// request.setAttribute("scenicSpotMonth",scenicSpotMonth);

		return new ModelAndView("system/hotel/auditAnnual").addObject(
				"hotelAnnual", ha);
	}

	/**
	 * 
	 * @date: 2016年12月7日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:返回审核页面
	 */
	@RequestMapping(params = "detail")
	public ModelAndView detial(HttpServletRequest request,
			HotelAnnual hotelAnnual, Hotelmanage hotelmanage, DataGrid dataGrid) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		// 季报id
		String annualId = request.getParameter("annualId");
		// 存到请求域中
		// request.setAttribute("hotelmanageId", hotelmanageId);
		Hotelmanage h = this.systemService
				.get(Hotelmanage.class, hotelmanageId);
		hotelAnnual = this.systemService.get(HotelAnnual.class, annualId);

		// request.setAttribute("hotelmanage", h);
		// request.setAttribute("monthdata", m);
		hotelAnnual.setHotelmanage(h);
		request.setAttribute("code", h.getCode());

		return new ModelAndView("system/hotel/showAnnualDetail").addObject(
				"hotelAnnual", hotelAnnual);
	}

	/**
	 * 代填报功能
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request,
			HotelAnnual hotelAnnual, Hotelmanage hotelmanage) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		String annualId = request.getParameter("annualId");
		// hid=hotelmanageId;
		// System.out.println(hid);
		request.setAttribute("hotelmanageId", hotelmanageId);
		request.setAttribute("annualId", annualId);
		// 根据基本信息id获取基本信息
		Hotelmanage hm = this.systemService.get(Hotelmanage.class,
				hotelmanageId);
		// 根绝月报id获取月报信息
		HotelAnnual s = this.systemService.get(HotelAnnual.class, annualId);
		// 将基本信息设置在域中

		request.setAttribute("hotelmanage", hm);
		if (s == null) {
			s = new HotelAnnual();

		}
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		// GregorianCalendar gc= new GregorianCalendar();
		s.setHid(hotelmanageId);
		s.setYear(timeUtils.getLastYear());
		// gc.setTime(new Date());
		// gc.add(GregorianCalendar.MONTH, -1);
		// String d3Number = sdf.format(gc.getTime());
		// System.out.println(d3Number);
		// s.setMonth(gc.getTime());
		s.setHotelmanage(hm);
		request.setAttribute("annualData", s);

		// this.saveScenic(request, scenicSpotMonth);
		return new ModelAndView("system/hotel/daitianbaoAnnual").addObject("annualData", s).addObject("hotelmanage", hm);
	}

	/**
	 * 
	 * @date: 2016年12月13日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:
	 */
	@RequestMapping(params = "saveDai")
	@ResponseBody
	public AjaxJson addDai(HotelAnnual hotelAnnual, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		if(hotelAnnual.getId() == null || hotelAnnual.getId().length() == 0) hotelAnnual.setId(null);
		System.out.println(hotelAnnual.getHid());
		Hotelmanage hotelmanage = systemService.getEntity(Hotelmanage.class,
				hotelAnnual.getHid());
		hotelAnnual.setHotelmanage(hotelmanage);
		// 代填报的是上个年度的年报
		hotelAnnual.setYear(TimeUtils.getLastYear());
		// 判断是否区级审核
		if(this.isDistrictManager()){
			hotelAnnual.setDistrictStatus(Status.passAudit);
			hotelAnnual.setStatus(Status.noAudit);
			hotelAnnual.setCountryState(Status.noSumbit);
		}else{
			hotelAnnual.setStatus(Status.passAudit);
			hotelAnnual.setDistrictStatus(Status.noAudit);
			hotelAnnual.setCountryState(Status.noSumbit);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hotelAnnual.setReportTime(sdf.format(new Date()));
		systemService.saveOrUpdate(hotelAnnual);

		j.setMsg("添加成功");
		systemService.addLog("代填报酒店财务年报成功:"+hotelAnnual.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);


		return j;
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
			HotelAnnual hotelAnnual, Hotelmanage hotelmanage, String statusid) {
		AjaxJson j = new AjaxJson();
		String hotelId = request.getParameter("hotelId");
		request.setAttribute("hotelId", hotelId);
		// 根据id获取酒店信息实体类
		hotelmanage = this.hotelservice.getEntity(Hotelmanage.class, hotelId);
		// 将基本信息添加到月报信息中
		hotelAnnual.setHid(hotelId);
		hotelAnnual.setHotelmanage(hotelmanage);
		// 判断是否区级审核
		hotelAnnual.setStatus(Status.noSumbit);
		hotelAnnual.setDistrictStatus(Status.noSumbit);
		hotelAnnual.setCountryState(Status.noSumbit);
		// 更新
		request.setAttribute("HotelAnnual", hotelAnnual);
		if(hotelAnnual.getId() == null || hotelAnnual.getId().length() == 0) hotelAnnual.setId(null);
		systemService.saveOrUpdate(hotelAnnual);
		systemService.addLog("暂存酒店财务年报成功:"+hotelAnnual.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		return j;
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
	public String exportXls(HotelAnnual hotelAnnual,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(HotelAnnual.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelAnnual, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		List<HotelAnnual> hotelAnnuals = this.systemService
				.getList(HotelAnnual.class);
		map.put(NormalExcelConstants.FILE_NAME, "酒店月报信息");
		map.put(NormalExcelConstants.CLASS, HotelAnnual.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("酒店年报列表",
				"导出人:酒店管理员", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelAnnuals);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	// *********************************客户酒店年报管理******************************************
	@RequestMapping(params = "ckhotelAnnual")
	public String quarters(HttpServletRequest request) {
		return "system/hotelClient/ckHotelAnnualList";
	}

	@ResponseBody
	@RequestMapping(params = "ckDatagridAnnual")
	public void quarterGrid(HotelAnnual hotelAnnual,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		CriteriaQuery cq = new CriteriaQuery(HotelAnnual.class, dataGrid);
		// 查询条件组装器
		hotelAnnual.setHid(hotelId);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelAnnual);

		systemService.getDataGridReturn(cq, true);

		TagUtil.datagrid(response, dataGrid);

	}

	@RequestMapping(params = "exportsxlss")
	public String exportXlss(HotelAnnual hotelAnnual,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(HotelAnnual.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelAnnual, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		String hotelId = ResourceUtil.getSessionUserName().getId();
		List<HotelAnnual> hotelAnnuals = this.systemService
				.findHql(
						" from com.zzc.web.htoal.entity.HotelAnnual ha where ha.hid=? ",
						hotelId);
		map.put(NormalExcelConstants.FILE_NAME, "酒店月报信息");
		map.put(NormalExcelConstants.CLASS, HotelAnnual.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("酒店年报列表",
				"导出人:酒店管理员", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelAnnuals);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	/**
	 * 判断当前用户是否为区级管理员
	 * @return boolean
	 */
	public boolean isDistrictManager(){
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
		}else{
			return false;
		}				
	}
	
	/**
	 * 判断年报是否填报
	 * @return
	 */
	public boolean isFilled(){
		boolean tip = false;
		//获取当前登录用户ID=酒店ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		String sql = "select * from t_hotel_annual t where t.`year`='"+year+"' and t.hotel_aid='"+userId+"'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if(list.size() != 0) tip = true;
		return tip;
	}
	
	/**
	 * 导出年报
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportAnnual")
	public void exportAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
        String year = request.getParameter("year");
        String housegrade = request.getParameter("housegrade");
        String county = request.getParameter("county");
        String bay = request.getParameter("bay");
        String districtStatus = request.getParameter("districtStatus");
        String status = request.getParameter("status");
        String countryState = request.getParameter("countryState");
        
        String excelName = "酒店年报.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
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
        sql.append(" t.operationIncome, ");
        sql.append(" t.canteeIncome, ");
        sql.append(" t.roomIncome, ");
        sql.append(" t.otherIncome, ");
        sql.append(" t.debt `年末资产负债(千元)`, ");
        sql.append(" t.streamTotal `流动资产小计(千元)`, ");
        sql.append(" t.longInvest `长期投资(千元)`, ");
        sql.append(" t.fixedAssets `固定资产小计(千元)`, ");
        sql.append(" t.fixedPrice `固定资产原价(千元)`, ");
        sql.append(" t.deposit `存货`, ");
        sql.append(" t.depreciation `累计折旧`, ");
        sql.append(" t.yearDepreciation `本年折旧`, ");
        sql.append(" t.assetTotal `资产合计(千元)`, ");
        sql.append(" t.liabilitiesTotal `负债合计(千元)`, ");
        sql.append(" t.possessorTotal `所有者权益合计(千元)`, ");
        sql.append(" t.realIncome `实收资本(千元)`, ");
        sql.append(" t.operationCost `营业成本(千元)`, ");
        sql.append(" t.operationFee `营业费用(千元)`, ");
        sql.append(" t.oprFeeAndTax `营业税金及附加(千元)`, ");
        sql.append(" t.taxes `税金(千元)`, ");
        sql.append(" t.travelExpense `差旅费(千元)`, ");
        sql.append(" t.financialCost `财务费用(千元)`, ");
        sql.append(" t.interestCost `利息支出(千元)`, ");
        sql.append(" t.valueVariation `公允价值变动收益(千元)`, ");
        sql.append(" t.opreationIntrest `营业利润(千元)`, ");
        sql.append(" t.invest `投资收益(千元)`, ");
        sql.append(" t.profitTotal `利润总额(千元)`, ");
        sql.append(" t.ownTax `所得税(千元)`, ");
        sql.append(" t.salary `本年应付职工薪酬(千元)`, ");
        sql.append(" t.people `全部从业人员平均人数`, ");
        sql.append(" t.college `大专以上学历人数`, ");
        sql.append(" t.room `客房数量`, ");
        sql.append(" t.bed `床位数量`, ");
        sql.append(" t.actual_rent `实际出租夜间数`, ");
        sql.append(" t.for_hire `可供出租夜间数`, ");
        sql.append(" t.filler `填报人`, ");
        sql.append(" t.reportTime `填报时间` ");
        sql.append(" FROM ");
        sql.append(" 	t_hotel_annual t ");
        sql.append(" LEFT JOIN t_hotelmanage hotelmanage ON t.hotel_aid = hotelmanage.id where  1=1  ");
        
        Calendar calendar = Calendar.getInstance();
        String lastYear = String.valueOf(calendar.get(Calendar.YEAR) -1 );
        
        // 组装查询条件
        if(name != null && name.length() != 0){
        	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_aid and hm.unitname like '%"+name+"%') ");
        }
        if(year != null && year.length() != 0){
        	sql.append(" and t.`year` like '%"+year+"%' ");
        }else{
        	sql.append(" and t.`year` like '%"+lastYear+"%' ");
        }
        if(housegrade != null && housegrade.length() != 0){
        	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_aid and hm.housegrade like '%"+housegrade+"%') ");
        }
        if(county != null && county.length() != 0){
        	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_aid and hm.w_county like '%"+county+"%') ");
        }
        if(bay != null && bay.length() != 0){
        	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_aid and hm.bay_type like '%"+bay+"%') ");
        }
        if(districtStatus != null && districtStatus.length() != 0){
        	sql.append(" and t.`district_status` like '%"+districtStatus+"%' ");
        }
        if(status != null && status.length() != 0){
        	sql.append(" and t.`status` like '%"+status+"%' ");
        }
        if(countryState != null && countryState.length() != 0){
        	sql.append(" and t.`country_status` like '%"+countryState+"%' ");
        }
        
		sql.append(" UNION ALL SELECT ");
        sql.append(" 	'合计', ");
        sql.append(" 	'', ");
		sql.append(" 	'', ");
        sql.append("  2'', ");
        sql.append(" IFNULL(sum(t.operationIncome),0), ");
		sql.append(" IFNULL(sum(t.canteeIncome),0), ");
		sql.append(" IFNULL(sum(t.roomIncome),0), ");
		sql.append(" IFNULL(sum(t.otherIncome),0), ");
		sql.append(" IFNULL(sum(t.debt),0), ");
        sql.append(" IFNULL(sum(t.streamTotal),0) , ");
        sql.append(" IFNULL(sum(t.longInvest),0) , ");
        sql.append(" IFNULL(sum(t.fixedAssets),0) , ");
        sql.append(" IFNULL(sum(t.fixedPrice),0) , ");
        sql.append(" IFNULL(sum(t.deposit),0), ");
        sql.append(" IFNULL(sum(t.depreciation),0), ");
        sql.append(" IFNULL(sum(t.yearDepreciation),0), ");
        sql.append(" IFNULL(sum(t.assetTotal),0), ");
        sql.append(" IFNULL(sum(t.liabilitiesTotal),0), ");
        sql.append(" IFNULL(sum(t.possessorTotal),0), ");
        sql.append(" IFNULL(sum(t.realIncome),0), ");
        sql.append(" IFNULL(sum(t.operationCost),0), ");
        sql.append(" IFNULL(sum(t.operationFee),0), ");
        sql.append(" IFNULL(sum(t.oprFeeAndTax),0), ");
        sql.append(" IFNULL(sum(t.taxes),0), ");
        sql.append(" IFNULL(sum(t.travelExpense),0), ");
        sql.append(" IFNULL(sum(t.financialCost),0), ");
        sql.append(" IFNULL(sum(t.interestCost),0), ");
        sql.append(" IFNULL(sum(t.valueVariation),0), ");
        sql.append(" IFNULL(sum(t.opreationIntrest),0), ");
        sql.append(" IFNULL(sum(t.invest),0), ");
        sql.append(" IFNULL(sum(t.profitTotal),0), ");
        sql.append(" IFNULL(sum(t.ownTax),0), ");
        sql.append(" IFNULL(sum(t.salary),0), ");
        sql.append(" IFNULL(sum(t.people),0), ");
        sql.append(" IFNULL(sum(t.college),0), ");
        sql.append(" IFNULL(sum(t.room),0), ");
        sql.append(" IFNULL(sum(t.bed),0), ");
        sql.append(" IFNULL(sum(t.actual_rent),0), ");
        sql.append(" IFNULL(sum(t.for_hire),0), ");
        sql.append(" '' `填报人`, ");
        sql.append(" '' `填报时间` ");
        sql.append(" FROM ");
        sql.append(" 	t_hotel_annual t ");
        sql.append(" LEFT JOIN t_hotelmanage hotelmanage ON t.hotel_aid = hotelmanage.id where  1=1  ");
        // 组装查询条件
        if(name != null && name.length() != 0){
        	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_aid and hm.unitname like '%"+name+"%') ");
        }
        if(year != null && year.length() != 0){
        	sql.append(" and t.`year` like '%"+year+"%' ");
        }else{
        	sql.append(" and t.`year` like '%"+lastYear+"%' ");
        }
        if(housegrade != null && housegrade.length() != 0){
        	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_aid and hm.housegrade like '%"+housegrade+"%') ");
        }
        if(county != null && county.length() != 0){
        	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_aid and hm.w_county like '%"+county+"%') ");
        }
        if(bay != null && bay.length() != 0){
        	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_aid and hm.bay_type like '%"+bay+"%') ");
        }
        if(districtStatus != null && districtStatus.length() != 0){
        	sql.append(" and t.`district_status` like '%"+districtStatus+"%' ");
        }
        if(status != null && status.length() != 0){
        	sql.append(" and t.`status` like '%"+status+"%' ");
        }
        if(countryState != null && countryState.length() != 0){
        	sql.append(" and t.`country_status` like '%"+countryState+"%' ");
        }
        ExportService emds = new ExportService(41); 
        
        HSSFWorkbook workbook = null;
        try
        {
            workbook = emds.getNewModelInfos(modelPath, sql.toString());
        } catch (Exception e)
        {

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
	
	/**
	 * 
	 *  撤回酒店年报
	 * @return 成功标志
	 */
	@RequestMapping(params = "revocation")
	@ResponseBody
	public String revocationHotelInfo(HttpServletRequest request, HttpServletResponse response){
		String tip = "success";
		
		String id = request.getParameter("id");
		
		try {
			// 获取年报对象
			HotelAnnual hotelAnnual = systemService.get(HotelAnnual.class, id);
			hotelAnnual.setCountryState(Status.noSumbit);
			hotelAnnual.setRevocated("1");
			systemService.saveOrUpdate(hotelAnnual);
			systemService.addLog("撤回酒店财务年报成功:"+hotelAnnual.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "failed";
			systemService.addLog("撤回酒店财务年报失败:"+id, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		return tip;
	}
	
	/**
	 * 
	 * 审核意见
	 * @param request
	 * @param response
	 * @return 审核意见视图
	 */
	@RequestMapping(params = "countryContent")
	public ModelAndView countryContent(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		HotelAnnual hotelAnnual = systemService.get(HotelAnnual.class, id);
		String status = hotelAnnual == null || hotelAnnual.getCountryState() == null ? "" : hotelAnnual.getCountryState();
		switch (status) {
		case "1":
			status = "未填报";
			break;
		case "2":
			status = "未审核";
			break;
		case "3":
			status = "已审核(审核不通过)";
			break;
		case "4":
			status = "已审核(审核通过)";
			break;
		}
		Map<String, String> modelMap = new HashMap<String, String>();
		modelMap.put("issueStatus", status);
		modelMap.put("countryContent", hotelAnnual == null ? "" : hotelAnnual.getGuo());
		return new ModelAndView("/travel/countryContent").addAllObjects(modelMap);
	}
	
}
