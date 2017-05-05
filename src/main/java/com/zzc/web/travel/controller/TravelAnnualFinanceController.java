package com.zzc.web.travel.controller;

import java.net.URLEncoder;
import java.rmi.RemoteException;
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
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.scenicmanage.controller.ScenicSpotWeekController;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.TravelAnnualFinance;
import com.zzc.web.travel.entity.Traveldata;
import com.zzc.webservice.travelagency.PublicInterfaceSoapProxy;

/**
 * @Title: Controller
 * @Description: 旅行社财务年报数据管理
 * @author Fp
 * @date 2016-12-1 21:50:55
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/travelAnnualFinanceController")
public class TravelAnnualFinanceController extends BaseController {

	private String message = null;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(ScenicSpotWeekController.class);
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	/**
	 *  年报管理列表jsp
	 * @author Fp
	 * @param
	 * @param  
	 * @return
	 */
	@RequestMapping(params = "tolist")
	public String scenicWeekList(HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		request.setAttribute("year", calendar.get(Calendar.YEAR) - 1);
		return "/travelannualfinance/travelAnnualFinanceList";

	}
	/**
	 * 年报审核列表jsp
	 * @author Fp
	 * @param
	 * @param  
	 * @return
	 */
	@RequestMapping(params = "toAuditList")
	public String scenicWeekAuditList(HttpServletRequest request) {

		return "/travelannualfinance/travelAnnualFinanceAuditList";

	}
	/**
	 * 年报添加jsp
	 * @author Fp
	 * @param
	 * @param  
	 * @return
	 */
	@RequestMapping(params = "add")
	public String scenicMonth() {

		return "travelannualfinance/saveTravelAnnualFinance";
	}
	/**
	 *  年报管理
	 * @author Fp
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(TravelAnnualFinance travelAnnualFinance,
			Traveldata traveldata, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelAnnualFinance.class,
				dataGrid);
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
					"select id from t_travelagency_info where area=?",
					ResourceUtil.getSessionUserName().getArea());
			if (list.size() != 0) {
				String[] parmas = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String id = list.get(i).get("id").toString();
					parmas[i] = id;
				}
				cq.add(Restrictions.in("tid", parmas));
			} else {
				cq.add(Restrictions.in("tid", new Object[] { "" }));
			}
		}
		// 查询条件组装器
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		
		// TODO 待修改
		String licensenumQuery = travelAnnualFinance.getTraveldata() == null ? "" : travelAnnualFinance.getTraveldata().getLicensenum();
		String nameQuery = travelAnnualFinance.getTraveldata() == null ? "" : travelAnnualFinance.getTraveldata().getName();
		String yearQuery = travelAnnualFinance.getYear();
		String statusQuery = travelAnnualFinance.getStatus();
		// 组合查询条件
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid) "));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid) "));
		}
		if(yearQuery != null && yearQuery.length() != 0){
			cq.like("year", "%"+yearQuery+"%");
		}else{
			cq.like("year", "%"+year+"%");
		}
		if(statusQuery !=null && statusQuery.length() != 0){
			cq.add(Restrictions.like("status", statusQuery));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,new TravelAnnualFinance());
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 *  年报审核列表
	 * @author 冯勇齐
	 * @param
	 * @param  
	 * @return
	 */
	@RequestMapping(params = "auditdatagrid")
	public void auditDatagrid(TravelAnnualFinance travelAnnualFinance,
			Traveldata traveldata, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Traveldata.class, dataGrid);
		
		// 获取去年年份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.YEAR, -1);
		String lastYear = sdf.format(gc.getTime());
		
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService.findOneForJdbc("select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)", userId);
		// 区级管理员做判断
		if(userMap.get("rolename") != null && userMap.get("rolename").toString().contains("区级")){
			cq.add(Restrictions.eq("area", ResourceUtil.getSessionUserName().getArea()));
		}
		// 组合查询条件
		if(traveldata.getLicensenum() != null && traveldata.getLicensenum().length() != 0){
        	cq.add(Restrictions.like("licensenum", "%"+traveldata.getLicensenum()+"%"));
        }
		if(traveldata.getName() != null && traveldata.getName().length() != 0){
        	cq.add(Restrictions.like("name", "%"+traveldata.getName()+"%"));
        }
		if(traveldata.getTravelAnnual() != null && traveldata.getTravelAnnual().getStatus() != null && traveldata.getTravelAnnual().getStatus().length() != 0){
			if("1".equals(traveldata.getTravelAnnual().getStatus())){
				cq.add(Restrictions.sqlRestriction( "1=1 and not exists( select * from t_travelAgency_annual annual where annual.tid = this_.id and annual.year = '"+lastYear+"')" ));
			}else{
				cq.add(Restrictions.sqlRestriction( "1=1 and exists( select * from t_travelAgency_annual annual where annual.tid = this_.id and annual.year = '"+lastYear+"' and annual.status = '"+ traveldata.getTravelAnnual().getStatus() +"')" ));
			}
		}
		
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new Traveldata());
		this.systemService.getDataGridReturn(cq, true);

		for (Object o : dataGrid.getResults()) {
			Traveldata sd = (Traveldata) o;
			if (sd.getTravelAnnualList() != null) {
				for (TravelAnnualFinance monthdata : sd.getTravelAnnualList()) {
					if(lastYear.equals(monthdata.getYear())){
						sd.setTravelAnnual(monthdata);
						break;
					}
				}
				if (sd.getTravelAnnual() == null) {
					TravelAnnualFinance sc = new TravelAnnualFinance();
					sc.setStatus("1");
					sc.setYear(lastYear);
					sd.setTravelAnnual(sc);
				}

			}

		}

		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 *  年报审核页面
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request, Traveldata traveldata) {
		String travelid = request.getParameter("travelid");
		String id = request.getParameter("quarteroneid");

		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", id);
		Traveldata d = this.systemService.get(Traveldata.class, travelid);
		request.setAttribute("traveldataInfo", d);
		TravelAnnualFinance travelannualfinance = this.systemService.get(TravelAnnualFinance.class, id);
		if(travelannualfinance == null) {
			travelannualfinance = new TravelAnnualFinance();
			//获取当前年和季度
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			nowYear = String.valueOf(Integer.parseInt(nowYear) - 1);
			travelannualfinance.setYear(nowYear);
		}
		travelannualfinance.setReportPerson(d.getPrincipal());
		travelannualfinance.setTelephone(d.getMobile());
		request.setAttribute("travelAnnualdata", travelannualfinance);

		return new ModelAndView("travelannualfinance/saveTravelAnnualFinance");
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(params = "saveTemporary")
	@ResponseBody
	public AjaxJson saveTemporary(HttpServletRequest request, TravelAnnualFinance travelAnnualFinance) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功");
		try {
			String travelid = request.getParameter("travelid");
			Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
			travelAnnualFinance.setTraveldata(traveldata);
			travelAnnualFinance.setStatus("1");
			//判断是否新建
			String id = travelAnnualFinance.getId();
			if(id == null || id.length() == 0){
				travelAnnualFinance.setId(null);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String reportTime = sdf.format(new Date());
				travelAnnualFinance.setReportTime(reportTime);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String reportTime = sdf.format(new Date());
			travelAnnualFinance.setReportTime(reportTime);
			travelAnnualFinance.setTid(travelid);
			systemService.saveOrUpdate(travelAnnualFinance);
			systemService.addLog("暂存旅行社财务年报成功:"+travelAnnualFinance.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("暂存旅行社财务年报失败", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}finally{
			return returnTip;
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(params = "saveByAdmin")
	@ResponseBody
	public AjaxJson saveByAdmin(HttpServletRequest request, TravelAnnualFinance travelAnnualFinance) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功");
		try {
			String travelid = request.getParameter("travelid");
			Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
			travelAnnualFinance.setTraveldata(traveldata);
			//判断是否新建
			String id = travelAnnualFinance.getId();
			if(id == null || id.length() == 0){
				travelAnnualFinance.setId(null);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String reportTime = sdf.format(new Date());
				travelAnnualFinance.setReportTime(reportTime);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String reportTime = sdf.format(new Date());
			travelAnnualFinance.setReportTime(reportTime);
			travelAnnualFinance.setTid(travelid);
			travelAnnualFinance.setStatus(Status.passAudit);
			// 得分 0 
			travelAnnualFinance.setScore(0);
			systemService.saveOrUpdate(travelAnnualFinance);
			systemService.addLog("代填报旅行社财务年报成功:"+travelAnnualFinance.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("代填报旅行社财务年报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}finally{
			return returnTip;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson saveScenic(HttpServletRequest request, TravelAnnualFinance travelAnnualFinance) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功");
		try {
			// 获取当前登录用户ID
			String userId = ResourceUtil.getSessionUserName().getId();
			// 根据用户ID查找旅行社信息
			List<Traveldata> traveldataList = systemService.findByProperty(
					Traveldata.class, "userId", userId);
			String travelid = traveldataList.get(0).getId();
			Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
			travelAnnualFinance.setTraveldata(traveldata);
			travelAnnualFinance.setStatus("2");
			//判断是否新建
			String id = travelAnnualFinance.getId();
			if(id == null || id.length() == 0){
				if(this.isFilled()){
					returnTip.setMsg("去年年报已填报!");
					return returnTip;
				}
				// 添加积分
				int year = Integer.parseInt(travelAnnualFinance.getYear());
				boolean rs = ReportTimeCheck.annualTimeCheck(year);
				if(rs){
					travelAnnualFinance.setScore(40);
					UserScoreUtils.scoreChange(40);
				}else{
					travelAnnualFinance.setScore(20);
					UserScoreUtils.scoreChange(20);
				}
				travelAnnualFinance.setId(null);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String reportTime = sdf.format(new Date());
				travelAnnualFinance.setReportTime(reportTime);
			}else{
				try {
					String sql = "select score from t_travelagency_annual where id = ?";
					List<Map<String, Object>> list = systemService.findForJdbc(
							sql, new Object[] { travelAnnualFinance.getId() });
					travelAnnualFinance.setScore(Integer.parseInt(list.get(0)
							.get("score").toString()));
				} catch (Exception e) {
					e.printStackTrace();
					travelAnnualFinance.setScore(0);
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String reportTime = sdf.format(new Date());
			travelAnnualFinance.setReportTime(reportTime);
			travelAnnualFinance.setTid(travelid);
			systemService.saveOrUpdate(travelAnnualFinance);
			
			systemService.addLog("填报旅行社财务年报成功:"+travelAnnualFinance.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("填报旅行社财务年报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}finally{
			return returnTip;
		}
	}
	/**
	 *  审核页面
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,
			TravelAnnualFinance travelAnnualFinance, Traveldata traveldata,
			DataGrid dataGrid) {
		String id = request.getParameter("id");
		String travelid = request.getParameter("travelid");
		request.setAttribute("quarteroneid", id);
		Traveldata s = this.systemService.get(Traveldata.class, travelid);
		TravelAnnualFinance m = this.systemService.get(
				TravelAnnualFinance.class, id);
		
		// 非空处理
		if(m == null) m = new TravelAnnualFinance();
		
		request.setAttribute("traveldataInfo", s);
		request.setAttribute("travelAnnualdata", m);

		return new ModelAndView("travelannualfinance/saveTravelAnnualFinance");
	}
	 
	/**
	 *  审核页面
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			TravelAnnualFinance travelAnnualFinance, Traveldata traveldata,
			String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		String travelid = request.getParameter("travelid");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TravelAnnualFinance travelAnnual = systemService.getEntity(
					TravelAnnualFinance.class, id);
			// TODO 定时器
//			String rs = this.addOrUpdateTravelFinance(travelAnnual, travelid);
//			j.setMsg(rs);
//			//验证接口是否通过
//			if(!"200".equals(rs)) return j;
			// 4表示审核通过
			travelAnnual.setStatus("4");

			systemService.saveOrUpdate(travelAnnual);
			
			systemService.addLog("审核通过旅行社财务年报成功:"+travelAnnualFinance.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}
	/**
	 * 3-已审核（审核不通过）
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			Traveldata traveldata, TravelAnnualFinance travelAnnualFinance,
			String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TravelAnnualFinance travelAnnual = systemService.getEntity(
					TravelAnnualFinance.class, id);
			// 3-已审核（审核不通过）
			travelAnnual.setStatus("3");
			// 分数减少
			Integer score = travelAnnual.getScore();
			if(score >= 10){
				travelAnnual.setScore(score - 10);
				UserScoreUtils.scoreChange(-10, travelAnnual.getTid());
			}else{
				travelAnnual.setScore(0);
			}						
			systemService.saveOrUpdate(travelAnnual);
			systemService.addLog("审核退回旅行社财务年报成功:"+travelAnnualFinance.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}
	
	
	///////////////////////////////////////////用户层/////////////////////////////////////////////
	
	/**
	 * @author Fp
	 * @param request
	 * @return 财务年报填报页面
	 */
	@RequestMapping(params = "saveFinanceOfUser")
	public ModelAndView saveFinanceOfUser(HttpServletRequest request) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String traveldataid = traveldataList.get(0).getId();
		String monthid = request.getParameter("monthid");

		request.setAttribute("traveldataid", traveldataid);
		request.setAttribute("monthid", monthid);
		Traveldata traveldata = this.systemService.get(Traveldata.class, traveldataid);
		request.setAttribute("traveldata", traveldata);
		TravelAnnualFinance travelannualfinance = new TravelAnnualFinance();
		Calendar calendar = Calendar.getInstance();
		travelannualfinance.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
		travelannualfinance.setReportPerson(traveldata.getPrincipal());
		travelannualfinance.setTelephone(traveldata.getMobile());
		request.setAttribute("travelAnnualdata", travelannualfinance);
		return new ModelAndView("travelUser/saveTravelAnnualFinance");
	}
	
	@RequestMapping(params = "financeGridOfUser")
	public ModelAndView financeGridOfUser(HttpServletRequest request) {
		return new ModelAndView("travelUser/financeList");
	}
	
	/**
	 * 用户年报表格加载
	 * @param travelAnnualFinance
	 * @param traveldata
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridOfUser")
	public void datagridOfUser(TravelAnnualFinance travelAnnualFinance,
			Traveldata traveldata, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		CriteriaQuery cq = new CriteriaQuery(TravelAnnualFinance.class,dataGrid);
		
		String licensenumQuery = travelAnnualFinance.getTraveldata() == null ? "" : travelAnnualFinance.getTraveldata().getLicensenum();
		String nameQuery = travelAnnualFinance.getTraveldata() == null ? "" : travelAnnualFinance.getTraveldata().getName();
		String yearQuery = travelAnnualFinance.getYear();
		String stateQuery = travelAnnualFinance.getStatus();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid) "));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid) "));
		}
		if(yearQuery != null && yearQuery.length() != 0){
			cq.like("year", "%"+yearQuery+"%");
		}
		if(stateQuery != null && stateQuery.length() != 0){
			cq.eq("status", stateQuery);
		}
		cq.eq("tid", travelid);
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new TravelAnnualFinance());

		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 
	 * @author Fp
	 * @param request
	 * @return 财务年报编辑或新增页面
	 */
	@RequestMapping(params = "addOrEditFinanceReport")
	public ModelAndView addOrEditFinanceReport(HttpServletRequest request) {
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		// 根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(
				Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		
		// 获取报表ID
		String id = request.getParameter("id");
		
		// 旅行社信息
		Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
		// 报表信息
		TravelAnnualFinance travelannualfinance = null;
		if(id == null || id.length() == 0){ // 新建
			travelannualfinance = new TravelAnnualFinance();
			Calendar calendar = Calendar.getInstance();
			travelannualfinance.setYear(String.valueOf(calendar.get(Calendar.YEAR) - 1));
			travelannualfinance.setReportPerson(traveldata.getPrincipal());
			travelannualfinance.setTelephone(traveldata.getMobile());
		}else{ // 编辑
			travelannualfinance = systemService.getEntity(TravelAnnualFinance.class, id);
		}
		request.setAttribute("travelData", traveldata);
		request.setAttribute("travelAnnualdata", travelannualfinance);
		return new ModelAndView("travelUser/saveTravelAnnualFinance");
	}
	
	/**
	 * 
	 * 删除财务年报
	 * @author Fp
	 * @param travelannualfinance
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "deleteFinanceReport")
	@ResponseBody
	public AjaxJson deleteFinanceReport(TravelAnnualFinance travelannualfinance, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			travelannualfinance = systemService.getEntity(TravelAnnualFinance.class, travelannualfinance.getId());
			systemService.delete(travelannualfinance);
			message="删除成功!";
		} catch (Exception e) {
			message="删除失败!";
		}
		j.setMsg(message);
		return j;
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
		TravelAnnualFinance travelAnnualFinance = systemService.get(TravelAnnualFinance.class, id);
		String status = travelAnnualFinance == null || travelAnnualFinance.getStatus() == null ? "" : travelAnnualFinance.getStatus();
		switch (status) {
		case "1":
			status = "市级未填报";
			break;
		case "2":
			status = "市级待审核";
			break;
		case "3":
			status = "市级审核未通过";
			break;
		case "4":
			status = "市级审核通过";
			break;
		case "5":
			status = "省级待审核";
			break;
		case "6":
			status = "省级审核未通过";
			break;
		case "7":
			status = "省级审核通过";
			break;
		}
		Map<String, String> modelMap = new HashMap<String, String>();
		modelMap.put("issueStatus", status);
		modelMap.put("countryContent", travelAnnualFinance == null ? "" : travelAnnualFinance.getGuo());
		return new ModelAndView("/travel/countryContent").addAllObjects(modelMap);
	}
	
	////////////////////////////////////////调用webservice////////////////////////////////////////////
	public String addOrUpdateTravelFinance(TravelAnnualFinance travelannualfinance, String travelid){
		String tip = "200";
		
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = new PublicInterfaceSoapProxy();
		// 获取旅行社信息
		Traveldata traveldata = systemService.get(Traveldata.class, travelid);
		
//		AreID	地区编码
		String areaID = GlobalParams.areaID;
//		Code	许可证号
		String code = traveldata.getLicensenum();
//		TableDate	填报时间
		String tableDate = travelannualfinance.getReportTime();
//		UnitName	单位名称
		String unitName = traveldata.getName();
//		UnitMaster	单位负责人
		String unitMaster = traveldata.getPrincipal();
//		Filler	填表人
		String filler = travelannualfinance.getReportPerson();
//		FillerTel	填表人电话
		String fillerTel = travelannualfinance.getTelephone();
//		CurrentAssetsTotal	流动资产小计
		String currentAssetsTotal = String.valueOf(travelannualfinance.getStreamTotal());
//		LongTermInvest	长期投资
		String longTermInvest = String.valueOf(travelannualfinance.getLongInvest());
//		FixAssetsTotal	固定资产小计
		String fixAssetsTotal = String.valueOf(travelannualfinance.getFixedAssets());
//		FixAssetsInitialPrice	固定资产原价
		String fixAssetsInitialPrice = String.valueOf(travelannualfinance.getFixedPrice());
//		CapitalTotal	资产合计
		String capitalTotal = String.valueOf(travelannualfinance.getAssetTotal());
//		TotalLiabilities  	负债合计  
		String totalLiabilities = String.valueOf(travelannualfinance.getLiabilitiesTotal());
//		OwnerBenefitTotal	所有者权益合计
		String ownerBenefitTotal = String.valueOf(travelannualfinance.getPossessor());
//		RealCapital	实收资本
		String realCapital = String.valueOf(travelannualfinance.getRealIncome());
//		OperationIncome	营业收入
		String operationIncome = String.valueOf(travelannualfinance.getBusinessIncome());
//		InboundRevenue	入境旅游营业收入
		String inboundRevenue = String.valueOf(travelannualfinance.getIntoIncome());
//		OutboundRevenues	出境旅游营业收入
		String outboundRevenues = String.valueOf(travelannualfinance.getOutIncome());
//		DomesticRevenue	国内旅游营业收入
		String domesticRevenue = String.valueOf(travelannualfinance.getInTravelIncome());
//		OperationCost	营业成本
		String operationCost = String.valueOf(travelannualfinance.getCost());
//		OperationFee	营业费用  
		String operationFee = String.valueOf(travelannualfinance.getBusinessExpenses());
//		OperationTaxAddition	营业税金及附加
		String operationTaxAddition = String.valueOf(travelannualfinance.getBusinessTexAdd());
//		BusinessProfit	主营业务利润
		String businessProfit = String.valueOf(travelannualfinance.getMainOperation());
//		InbundProfit	入境旅游业务利润
		String inbundProfit = String.valueOf(travelannualfinance.getProfitInTravel());
//		OutboundProfit	出境旅游业务利润
		String outboundProfit = String.valueOf(travelannualfinance.getOutTravelBusiness());
//		DomesticProfit	国内旅游业务利润
		String domesticProfit = String.valueOf(travelannualfinance.getInTravelReturn());
//		ManagementFee	管理费用
		String managementFee = String.valueOf(travelannualfinance.getManageCost());
//		Tax	税金
		String tax = String.valueOf(travelannualfinance.getTaxes());
//		FinanceFee	财务费用
		String financeFee = String.valueOf(travelannualfinance.getFinancialCost());
//		InterestPay	利息支出
		String interestPay = String.valueOf(travelannualfinance.getInterestCost());
//		OperationProfit	营业利润
		String operationProfit = String.valueOf(travelannualfinance.getOperatingIncome());
//		ProfitTotal	利润总额
		String profitTotal = String.valueOf(travelannualfinance.getTotalReturn());
//		IncomeTax 	所得税
		String incomeTax = String.valueOf(travelannualfinance.getIncomeTax());
//		AnnualPayTotal	应付职工酬薪
		String annualPayTotal = String.valueOf(travelannualfinance.getEmployeeSalary());
//		YearendEmployment	全部从业人员年平均人数
		String yearendEmployment = String.valueOf(travelannualfinance.getNumAverage());
//		JuniorEmployment	其中大专以上学历人数
		String juniorEmployment = String.valueOf(travelannualfinance.getCollege());
//		AccumulatedDepreciation	累计折旧
		String accumulatedDepreciation = String.valueOf(travelannualfinance.getDepreciation());
//		YearDepreciation	其中：本年折旧
		String yearDepreciation = String.valueOf(travelannualfinance.getYearDepreciation());
//		TravelExpenses	差旅费
		String travelExpenses = String.valueOf(travelannualfinance.getTravelExpense());
//		Gains	公允价值变动收益
		String gains = String.valueOf(travelannualfinance.getValueVariation());
//		ReturnInvestment	投资收益
		String returnInvestment = String.valueOf(travelannualfinance.getInvest());
//		WhetherPerform	是否执行2006年《企业会计准则》
		String whetherPerform = travelannualfinance.getWhetherPerform();
//		NonIncome	营业外收入
		String nonIncome = String.valueOf(travelannualfinance.getExtraGains());
//		Subsidies	其中：政府补贴
		String subsidies = String.valueOf(travelannualfinance.getGovernmentSubsidies());
//		CostSales	销售费用
		String costSales = String.valueOf(travelannualfinance.getSellingExpense());
//		GrossProfit	毛利润
		String grossProfit = String.valueOf(travelannualfinance.getGross());
//		TheVAT	本年应交增值税
		String theVAT = String.valueOf(travelannualfinance.getAddTex());
//		LaborContract	签订劳动合同的导游人数
		String laborContract = String.valueOf(travelannualfinance.getTourNum());
//		LeaderNumber	领队人数
		String leaderNumber = String.valueOf(travelannualfinance.getLeadGroup());
//		StoresNumber	门店数量
		String storesNumber = String.valueOf(travelannualfinance.getStoreNum());
//		BranchNumber	分社数量
		String branchNumber = String.valueOf(travelannualfinance.getBranchOffice());
//		SubsidiariesNumber	子公司数量
		String subsidiariesNumber = String.valueOf(travelannualfinance.getSubsidiary());
//		Loss	由执行2006年《企业会计准则》企业填报：资产减值损失
		String loss = String.valueOf(travelannualfinance.getLoss());
//		City	旅行社所属地区编码
		String city = GlobalParams.areaID;
//		VerificationCode	接口密码
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
		try {
			String rs = travelAgencyService.insertIntoFinance(areaID, code, tableDate, unitName, unitMaster, filler, fillerTel, currentAssetsTotal, longTermInvest, fixAssetsTotal, fixAssetsInitialPrice, capitalTotal, totalLiabilities, ownerBenefitTotal, realCapital, operationIncome, inboundRevenue, outboundRevenues, domesticRevenue, operationCost, operationFee, operationTaxAddition, businessProfit, inbundProfit, outboundProfit, domesticProfit, managementFee, tax, financeFee, interestPay, operationProfit, profitTotal, incomeTax, annualPayTotal, yearendEmployment, juniorEmployment, accumulatedDepreciation, yearDepreciation, travelExpenses, gains, returnInvestment, whetherPerform, nonIncome, subsidies, costSales, grossProfit, theVAT, laborContract, leaderNumber, storesNumber, branchNumber, subsidiariesNumber, loss, city, verificationCode);
		} catch (RemoteException e) {
			e.printStackTrace();
			tip = "通信异常";
		}
		
		return tip;
	}
	
	/**
	 * 导出财务年报
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportTravelFinance")
	public void exportTravelFinance(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String licensenum = request.getParameter("licensenum");
        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
        String year = request.getParameter("year");
        
        String excelName = "财务年报.xls";
        String modelPath = "/com/zzc/web/export/model/travelagency/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("select travelagencyinfo.licensenum,travelagencyinfo.`name`,t.report_person,t.report_time,t.`year`,if(t.`status`='1','未填报',if(t.`status`='2','未审核',if(t.`status`='3','未通过','已通过'))) 'status',t.business_income,t.into_income,t.out_income,t.in_travel_income,t.operating_income from t_travelagency_annual t left join t_travelagency_info travelagencyinfo on t.tid = travelagencyinfo.id where 1=1");
        // 组装查询条件
        if(licensenum != null && licensenum.length() != 0){
        	sql.append(" and exists(select travelagencyinfo.id from t_travelagency_info travelagencyinfo where travelagencyinfo.id = t.tid and travelagencyinfo.licensenum like '%"+licensenum+"%')");
        }
        if(name != null && name.length() != 0){
        	sql.append(" and exists(select travelagencyinfo.id from t_travelagency_info travelagencyinfo where travelagencyinfo.id = t.tid and travelagencyinfo.name like '%"+name+"%')");
        }
        if(year != null && year.length() != 0){
        	sql.append(" and t.`year` like '%"+year+"%'");
        }
        sql.append(" UNION ALL select '合计','','','','','',IFNULL(sum(t.business_income),0),IFNULL(sum(t.into_income),0),IFNULL(sum(t.out_income),0),IFNULL(sum(t.in_travel_income),0),IFNULL(sum(t.operating_income),0) from t_travelagency_annual t left join t_travelagency_info travelagencyinfo on t.tid = travelagencyinfo.id where 1=1");
        // 组装查询条件
        if(licensenum != null && licensenum.length() != 0){
        	sql.append(" and exists(select travelagencyinfo.id from t_travelagency_info travelagencyinfo where travelagencyinfo.id = t.tid and travelagencyinfo.licensenum like '%"+licensenum+"%')");
        }
        if(name != null && name.length() != 0){
        	sql.append(" and exists(select travelagencyinfo.id from t_travelagency_info travelagencyinfo where travelagencyinfo.id = t.tid and travelagencyinfo.name like '%"+name+"%')");
        }
        if(year != null && year.length() != 0){
        	sql.append(" and t.`year` like '%"+year+"%'");
        }
        ExportService emds = new ExportService(11); 
        
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
		systemService.addLog("导出旅行社财务年报", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

	}
	
	/**
	 * 检查年报是否填报
	 * @return
	 */
	public boolean isFilled(){
		boolean tip = false;
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();		
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		String sql = "select * from t_travelagency_annual t where t.`year`='"+year+"' and t.tid = '"+travelid+"'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if(list.size() != 0) tip = true;
		return tip;
	}

	
	/**
	 * 
	 * 撤回旅行社年
	 * 
	 * @return 成功标志
	 */
	@RequestMapping(params = "revocation")
	@ResponseBody
	public String revocation(HttpServletRequest request,
			HttpServletResponse response) {
		String tip = "success";
		try {
			String id = request.getParameter("id");
			TravelAnnualFinance travelAnnualFinance = systemService.get(TravelAnnualFinance.class, id);
			travelAnnualFinance.setStatus("4");
			systemService.saveOrUpdate(travelAnnualFinance);
			systemService.addLog("撤回旅行社财务年报成功:"+travelAnnualFinance.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "failed";
			systemService.addLog("撤回旅行社财务年报失败:"+request.getParameter("id"), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		return tip;
	}
}
