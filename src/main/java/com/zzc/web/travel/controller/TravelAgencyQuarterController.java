package com.zzc.web.travel.controller;

import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.TravelQuarterIn;
import com.zzc.web.travel.entity.TravelQuarterInland;
import com.zzc.web.travel.entity.TravelQuarterOut;
import com.zzc.web.travel.entity.Traveldata;
import com.zzc.web.travel.entity.TraveldataAnnual;
import com.zzc.webservice.travelagency.PublicInterfaceSoapProxy;


@Scope("prototype")
@Controller
@RequestMapping("/travelQuarterController")
public class TravelAgencyQuarterController extends BaseController {

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

	@RequestMapping(params = "tolist")
	public String travelQuarterList(HttpServletRequest request) {
		return "/travelquarter/travelQuarterList";

	}

	@RequestMapping(params = "toAuditList")
	public String travelQuarterAuditList(HttpServletRequest request) {

		return "/travelquarter/travelQuarterAuditList";

	}

	@RequestMapping(params = "add")
	public String travelSaveQuarter() {

		return "travelquarter/saveTravelQuarter";
	}

	@RequestMapping(params = "addOrg")
	public String travelSaveOrgQuarter() {

		return "travelquarter/saveOrgOutTravelQuarter";
	}

	@RequestMapping(params = "addOrgIn")
	public String travelSaveOrgInQuarter() {

		return "travelquarter/saveOrgInTravelQuarter";
	}

	@RequestMapping(params = "addOrgGuo")
	public String travelSaveOrgGuoneiQuarter() {

		return "travelquarter/saveOrgTravelQuarter";
	}
	/**
	 * 旅行社季报管理列表
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(TravelQuarterInland travelQuarterInland,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterInland.class,
				dataGrid);
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				travelQuarterInland);

		systemService.getDataGridReturn(cq, true);

		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 旅行社季报审核列表
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "auditdatagrid")
	public void auditDatagrid(Traveldata traveldata,
			TravelQuarterIn travelQuarterIn, TravelQuarterOut travelQuarterOut,
			TravelQuarterInland travelQuarterInland,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Traveldata.class, dataGrid);
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService
				.findOneForJdbc(
						"select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)",
						userId);
		// 区级管理员做判断
		if (userMap.get("rolename") != null
				&& userMap.get("rolename").toString().contains("区级")) {
			cq.add(Restrictions.eq("area", ResourceUtil.getSessionUserName()
					.getArea()));
		}
		String licensenumQuery = traveldata.getLicensenum() == null ? "" : traveldata.getLicensenum();
		String nameQuery = traveldata.getName() == null ? "" : traveldata.getName();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.like("licensenum", "%"+licensenumQuery+"%"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.like("name", "%"+nameQuery+"%"));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil
				.installHql(cq, new Traveldata());
		this.systemService.getDataGridReturn(cq, true);

		// 获取当前年和季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if("0".equals(nowSeason)){
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}
		
		for (Object o : dataGrid.getResults()) {
			Traveldata sd = (Traveldata) o;
			if (sd.getTravelQuarterOneList() != null) {
				for (TravelQuarterIn monthdata : sd.getTravelQuarterOneList()) {
					if(nowYear.equals(monthdata.getYear()) && nowSeason.equals(monthdata.getQuarter())){
						sd.setTravelQuarterOne(monthdata);
						break;
					}
				}
				if (sd.getTravelQuarterOne() == null) {
					TravelQuarterIn sc = new TravelQuarterIn();
					sc.setStatus("1");
					sd.setTravelQuarterOne(sc);
				}

			}

		}

		for (Object o : dataGrid.getResults()) {
			Traveldata sd = (Traveldata) o;
			if (sd.getTravelQuarterTowList() != null) {
				for (TravelQuarterOut monthdata : sd.getTravelQuarterTowList()) {
					if(nowYear.equals(monthdata.getYear()) && nowSeason.equals(monthdata.getQuarter())){
						sd.setTravelQuarterTow(monthdata);
						break;
					}
				}
				if (sd.getTravelQuarterTow() == null) {
					TravelQuarterOut sc = new TravelQuarterOut();
					sc.setStatus("1");
					sd.setTravelQuarterTow(sc);
				}

			}

		}
		for (Object o : dataGrid.getResults()) {
			Traveldata sd = (Traveldata) o;
			if (sd.getTravelQuarterThreeList() != null) {
				for (TravelQuarterInland monthdata : sd.getTravelQuarterThreeList()) {
					if(nowYear.equals(monthdata.getYear()) && nowSeason.equals(monthdata.getQuarter())){
						sd.setTravelQuarterThree(monthdata);
						break;
					}
				}
				if (sd.getTravelQuarterThree() == null) {
					TravelQuarterInland sc = new TravelQuarterInland();
					sc.setStatus("1");
					sd.setTravelQuarterThree(sc);
				}

			}

		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	private String getQuarter(){
		Calendar calendar = Calendar.getInstance();
		int nowMonth = calendar.get(Calendar.MONTH) +1;
		String quarter = "";
		if(nowMonth <= 3) quarter = "1";
		if(3 < nowMonth && nowMonth <= 6) quarter = "2";
		if(6 < nowMonth && nowMonth <= 9) quarter = "3";
		if(9 < nowMonth && nowMonth <= 12) quarter = "4";
		return quarter;
	}
	/**
	 * 旅行社季报入境代填报
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(params = "saveOne")
	@ResponseBody
	public AjaxJson saveTravel(HttpServletRequest request,
			TravelQuarterIn travelQuarterIn) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功!");
		try {
			//获取当前登录用户ID
			String userId = ResourceUtil.getSessionUserName().getId();
			//根据用户ID查找旅行社信息
			List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
			String travelid = traveldataList.get(0).getId();
			Traveldata d = this.systemService.get(Traveldata.class, travelid);

			travelQuarterIn.setStatus("2");
			travelQuarterIn.setTraveldata(d);
			
			//判断数据是否存在
			String tempId = travelQuarterIn.getId();
			if(tempId == null || tempId.length() == 0) {
				travelQuarterIn.setId(null);
				// 判断是否填报
				if(this.isEntryFilled()) {
					returnTip.setMsg("上季度季报已提交!");
					return returnTip;
				}
				
				// 添加积分
				int year = Integer.parseInt(travelQuarterIn.getYear());
				int quarter = Integer.parseInt(travelQuarterIn.getQuarter());
				boolean rs = ReportTimeCheck.quarterTimeCheck(year, quarter);
				if(rs){
					travelQuarterIn.setScore(25);
					UserScoreUtils.scoreChange(25);
				}else{
					travelQuarterIn.setScore(15);
					UserScoreUtils.scoreChange(15);
				}
				
			}else{
				try {
					String sql = "select score from t_travelAgency_quarterly1 where id = ?";
					List<Map<String, Object>> list = systemService.findForJdbc(
							sql, new Object[] { travelQuarterIn.getId() });
					travelQuarterIn.setScore(Integer.parseInt(list.get(0)
							.get("score").toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			travelQuarterIn.setTid(travelid);
			systemService.saveOrUpdate(travelQuarterIn);
			systemService.addLog("代填报旅行社入境季报成功:" + travelQuarterIn.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			returnTip.setMsg("保存失败!");
			systemService.addLog("代填报旅行社入境季报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}finally{
			return returnTip;
		}
	}
	/**
	 * 旅行社季报出境代填报
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(params = "saveTwo")
	@ResponseBody
	public AjaxJson saveTravel(HttpServletRequest request,
			TravelQuarterOut travelQuarterOut) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功!");
		try {
			//获取当前登录用户ID
			String userId = ResourceUtil.getSessionUserName().getId();
			//根据用户ID查找旅行社信息
			List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
			String travelid = traveldataList.get(0).getId();
			Traveldata d = this.systemService.get(Traveldata.class, travelid);

			travelQuarterOut.setStatus("2");
			travelQuarterOut.setTraveldata(d);
			//判断数据是否存在
			String tempId = travelQuarterOut.getId();
			if(tempId == null || tempId.length() == 0) {
				travelQuarterOut.setId(null);
				// 判断是否填报
				if(this.isLeaveFilled()) {
					returnTip.setMsg("上季度季报已提交!");
					return returnTip;
				}
				// 添加积分
				int year = Integer.parseInt(travelQuarterOut.getYear());
				int quarter = Integer.parseInt(travelQuarterOut.getQuarter());
				boolean rs = ReportTimeCheck.quarterTimeCheck(year, quarter);
				if(rs){
					travelQuarterOut.setScore(25);
					UserScoreUtils.scoreChange(25);
				}else{
					travelQuarterOut.setScore(15);
					UserScoreUtils.scoreChange(15);
				}
			}else{
				try {
					String sql = "select score from t_travelAgency_quarterly2 where id = ?";
					List<Map<String, Object>> list = systemService.findForJdbc(
							sql, new Object[] { travelQuarterOut.getId() });
					travelQuarterOut.setScore(Integer.parseInt(list.get(0)
							.get("score").toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			travelQuarterOut.setTid(travelid);
			systemService.saveOrUpdate(travelQuarterOut);
			systemService.addLog("代填报旅行社出境季报成功:" + travelQuarterOut.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("代填报旅行社出境季报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}finally{
			return returnTip;
		}
	}
	/**
	 * 旅行社季报国内代填报
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(params = "saveThree")
	@ResponseBody
	public AjaxJson saveTravel(HttpServletRequest request,TravelQuarterInland travelQuarterInland) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功!");
		try {
			//获取当前登录用户ID
			String userId = ResourceUtil.getSessionUserName().getId();
			//根据用户ID查找旅行社信息
			List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
			String travelid = traveldataList.get(0).getId();
			Traveldata d = this.systemService.get(Traveldata.class, travelid);

			travelQuarterInland.setStatus("2");
			travelQuarterInland.setTraveldata(d);
			//判断数据是否存在
			String tempId = travelQuarterInland.getId();
			if(tempId == null || tempId.length() == 0) {
				travelQuarterInland.setId(null);
				// 判断是否填报
				if(this.isInlandFilled()) {
					returnTip.setMsg("上季度季报已提交!");
					return returnTip;
				}
				// 添加积分
				int year = Integer.parseInt(travelQuarterInland.getYear());
				int quarter = Integer.parseInt(travelQuarterInland.getQuarter());
				boolean rs = ReportTimeCheck.quarterTimeCheck(year, quarter);
				if(rs){
					travelQuarterInland.setScore(25);
					UserScoreUtils.scoreChange(25);
				}else{
					travelQuarterInland.setScore(15);
					UserScoreUtils.scoreChange(15);
				}
			}else{
				try {
					String sql = "select score from t_travelAgency_quarterly3 where id = ?";
					List<Map<String, Object>> list = systemService.findForJdbc(
							sql, new Object[] { travelQuarterInland.getId() });
					travelQuarterInland.setScore(Integer.parseInt(list.get(0)
							.get("score").toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			travelQuarterInland.setTid(travelid);
			systemService.saveOrUpdate(travelQuarterInland);
			systemService.addLog("代填报旅行社国内季报成功:" + travelQuarterInland.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("代填报旅行社国内季报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}finally{
			return returnTip;
		}
	}
	/**
	 * 旅行社季报入境代填报jsp
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request,
			Traveldata traveldata) {
		String travelid = request.getParameter("travelid");
		String quarteroneid = request.getParameter("quarteroneid");
		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quarteroneid);
		// 旅行社信息
		Traveldata d = this.systemService.get(Traveldata.class, travelid);

		// 报表信息
		TravelQuarterIn s = this.systemService.get(TravelQuarterIn.class,
				quarteroneid);
		request.setAttribute("travelData", d);
		// 空值处理
		if(s == null){
			s = new TravelQuarterIn();
			//获取当前年和季度
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			// 对跨年的情况做处理
			if("0".equals(nowSeason)){
				nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
				nowSeason = "4";
			}
			s.setYear(nowYear);
			s.setQuarter(nowSeason);
			
			s.setReportPerson(d.getPrincipal());
			s.setReportTelephone(d.getMobile());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s.setReportDate(sdf.format(new Date()));
			
		}
		request.setAttribute("quarteroneData", s);

		return new ModelAndView("travelquarter/saveOrgInTravelQuarter");
	}
	/**
	 * 旅行社季报出境代填报jsp
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addsigntwo")
	public ModelAndView addsigntwo(HttpServletRequest request,
			Traveldata traveldata) {
		String travelid = request.getParameter("travelid");
		String quarteroneid = request.getParameter("quarteroneid");

		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quarteroneid);
		Traveldata d = this.systemService.get(Traveldata.class, travelid);
		TravelQuarterOut s = this.systemService.get(TravelQuarterOut.class,
				quarteroneid);
		request.setAttribute("travelData", d);
		// 空值处理
		if(s == null){
			s = new TravelQuarterOut();
			//获取当前年和季度
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			// 对跨年的情况做处理
			if("0".equals(nowSeason)){
				nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
				nowSeason = "4";
			}
			s.setYear(nowYear);
			s.setQuarter(nowSeason);
			
			s.setReportPerson(d.getPrincipal());
			s.setReportTelephone(d.getMobile());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s.setReportDate(sdf.format(new Date()));
		} 		
		request.setAttribute("quarterTwoData", s);
		return new ModelAndView("travelquarter/saveOrgOutTravelQuarter");
	}
	/**
	 * 旅行社季报国内审核状态，通过
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addsignthree")
	public ModelAndView addsignThree(HttpServletRequest request,
			Traveldata traveldata) {
		String travelid = request.getParameter("travelid");
		String quarteroneid = request.getParameter("quarteroneid");

		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quarteroneid);
		Traveldata d = this.systemService.get(Traveldata.class, travelid);
		TravelQuarterInland s = this.systemService.get(TravelQuarterInland.class,
				quarteroneid);
		request.setAttribute("travelData", d);
		// 空值处理
		if(s == null){
			s = new TravelQuarterInland();
			//获取当前年和季度
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			// 对跨年的情况做处理
			if("0".equals(nowSeason)){
				nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
				nowSeason = "4";
			}
			s.setYear(nowYear);
			s.setQuarter(nowSeason);
			
			s.setReportPerson(d.getPrincipal());
			s.setReportTelephone(d.getMobile());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s.setReportDate(sdf.format(new Date()));
		} 
		request.setAttribute("quarterThreeData", s);
		return new ModelAndView("travelquarter/saveOrgTravelQuarter");
	}
	/**
	 * 旅行社季报入境审核状态，通过
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			TravelQuarterIn travelQuarterIn, String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		String travelid = request.getParameter("travelid");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TravelQuarterIn travelQuarterInOne = systemService.getEntity(
					TravelQuarterIn.class, id);
			// TODO 定时器
//			String rs = this.addOrUpdateEntryTravelQuarter(travelQuarterInOne, travelid);
//			//验证接口是否通过
//			if(!"200".equals(rs)){
//				j.setMsg(rs);
//				return j;
//			}else{
//				j.setMsg("审核通过成功!");
//			}
			// 4表示审核通过
			travelQuarterInOne.setStatus("4");

			systemService.saveOrUpdate(travelQuarterInOne);
			systemService.addLog("审核旅行社入境季报成功:" + travelQuarterInOne.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}

		return j;
	}
	/**
	 * 旅行社季报出境审核状态，通过
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addstatustwo")
	@ResponseBody
	public AjaxJson saveStatusTwo(HttpServletRequest request,
			TravelQuarterOut travelQuarterOut, String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		String travelid = request.getParameter("travelid");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TravelQuarterOut travelQuarterOutTwo = systemService.getEntity(
					TravelQuarterOut.class, id);
			// TODO 定时器
//			String rs = addOrUpdateLeaveTravelQuarter(travelQuarterOutTwo, travelid);
//			//验证接口是否通过
//			if(!"200".equals(rs)){
//				j.setMsg(rs);
//				return j;
//			}else{
//				j.setMsg("审核通过成功!");
//			}
			// 4表示审核通过
			travelQuarterOutTwo.setStatus("4");

			systemService.saveOrUpdate(travelQuarterOutTwo);
			systemService.addLog("审核旅行社出境季报成功:" + travelQuarterOutTwo.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		j.setMsg("审核通过成功!");
		return j;
	}
	/**
	 * 旅行社季报国内审核状态，通过
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addstatusthree")
	@ResponseBody
	public AjaxJson saveStatusThree(HttpServletRequest request,
			TravelQuarterInland travelQuarterInland, String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		String travelid = request.getParameter("travelid");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TravelQuarterInland travelQuarterInlandThree = systemService
					.getEntity(TravelQuarterInland.class, id);
			// TODO 定时器
//			String rs = this.addOrUpdateInlandTravelQuarter(travelQuarterInlandThree, travelid);
			//验证接口是否通过
//			if(!"200".equals(rs)){
//				j.setMsg(rs);
//				return j;
//			}else{
//				j.setMsg("审核通过成功!");
//			}
			// 4表示审核通过
			travelQuarterInlandThree.setStatus("4");

			systemService.saveOrUpdate(travelQuarterInlandThree);
			systemService.addLog("审核旅行社国内季报成功:" + travelQuarterInlandThree.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		j.setMsg("审核通过成功!");
		return j;
	}
	/**
	 * 旅行社季报入境审核状态，退回
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			TravelQuarterIn travelQuarterIn, String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TravelQuarterIn travelQuarterInOne = systemService.getEntity(
					TravelQuarterIn.class, id);
			// 3-已审核（审核不通过）
			travelQuarterInOne.setStatus("3");
			
			// 分数减少
			Integer score = travelQuarterInOne.getScore();
			if(score >= 5){
				travelQuarterInOne.setScore(score - 5);
				UserScoreUtils.scoreChange(-5, travelQuarterInOne.getTid());
			}else{
				travelQuarterInOne.setScore(0);
			}
			systemService.saveOrUpdate(travelQuarterInOne);
			
			systemService.addLog("退回旅行社入境季报成功:" + travelQuarterInOne.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}
	/**
	 * 旅行社季报出境审核状态，退回
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "nochecktwo")
	@ResponseBody
	public AjaxJson saveNotStatusTwo(HttpServletRequest request,
			TravelQuarterOut travelQuarterOut, String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TravelQuarterOut travelQuarterOutOne = systemService.getEntity(
					TravelQuarterOut.class, id);
			// 3-已审核（审核不通过）
			travelQuarterOutOne.setStatus("3");
			// 分数减少
			Integer score = travelQuarterOutOne.getScore();
			if(score >= 5){
				travelQuarterOutOne.setScore(score - 5);
				UserScoreUtils.scoreChange(-5, travelQuarterOutOne.getTid());
			}else{
				travelQuarterOutOne.setScore(0);
			}
			systemService.saveOrUpdate(travelQuarterOutOne);
			
			systemService.addLog("退回旅行社出境季报成功:" + travelQuarterOutOne.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}
	/**
	 * 旅行社季报国内审核状态，退回
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "nocheckthree")
	@ResponseBody
	public AjaxJson saveNotStatusThree(HttpServletRequest request,
			TravelQuarterInland travelQuarterInland, String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TravelQuarterInland travelQuarterInlandthree = systemService
					.getEntity(TravelQuarterInland.class, id);
			// 3-已审核（审核不通过）
			travelQuarterInlandthree.setStatus("3");
			// 分数减少
			Integer score = travelQuarterInlandthree.getScore();
			if(score >= 5){
				travelQuarterInlandthree.setScore(score - 5);
				UserScoreUtils.scoreChange(-5, travelQuarterInlandthree.getTid());
			}else{
				travelQuarterInlandthree.setScore(0);
			}						
			systemService.saveOrUpdate(travelQuarterInlandthree);
			
			systemService.addLog("退回旅行社国内季报成功:" + travelQuarterInlandthree.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}

	@SuppressWarnings("finally")
	@RequestMapping(params = "zancuncheck")
	@ResponseBody
	public AjaxJson saveZanStatus(HttpServletRequest request,
			TravelQuarterIn travelQuarterIn, Traveldata traveldata,
			String statusid) {
		AjaxJson returnTip = new AjaxJson();
		try {
			String scenicdataid = request.getParameter("scenicdataid");
			request.setAttribute("scenicdataid", scenicdataid);
			// 设置旅行社信息
			Traveldata travelData = systemService.getEntity(Traveldata.class,
					scenicdataid);
			// 设置未填报
			travelQuarterIn.setStatus("1");
			travelQuarterIn.setTraveldata(travelData);
			// 判断数据是否
			if (travelQuarterIn.getId() == null
					|| travelQuarterIn.getId().length() == 0) {
				travelQuarterIn.setId(null);
			}
			travelQuarterIn.setTid(scenicdataid);
			systemService.saveOrUpdate(travelQuarterIn);
			returnTip.setMsg("保存成功!");
			
			systemService.addLog("暂存旅行社入境季报成功:" + travelQuarterIn.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("暂存旅行社入境季报失败", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} finally {
			return returnTip;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(params = "zancunchecktwo")
	@ResponseBody
	public AjaxJson saveZanStatusTwo(HttpServletRequest request,
			TravelQuarterOut travelQuarterOut, Traveldata traveldata,
			String statusid) {
		AjaxJson returnTip = new AjaxJson();
		try {
			String scenicdataid = request.getParameter("scenicdataid");
			request.setAttribute("scenicdataid", scenicdataid);
			// 设置旅行社信息
			Traveldata travelData = systemService.getEntity(Traveldata.class,
					scenicdataid);
			// 设置未填报
			travelQuarterOut.setStatus("1");
			travelQuarterOut.setTraveldata(travelData);
			// 判断数据是否
			if (travelQuarterOut.getId() == null
					|| travelQuarterOut.getId().length() == 0) {
				travelQuarterOut.setId(null);
			}
			travelQuarterOut.setTid(scenicdataid);
			systemService.saveOrUpdate(travelQuarterOut);
			returnTip.setMsg("保存成功!");
			
			systemService.addLog("暂存旅行社出境季报成功:" + travelQuarterOut.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("暂存旅行社出境季报失败", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} finally {
			return returnTip;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(params = "zancuncheckthree")
	@ResponseBody
	public AjaxJson saveZanStatusThree(HttpServletRequest request,
			TravelQuarterInland travelQuarterInland, Traveldata traveldata,
			String statusid) {
		AjaxJson returnTip = new AjaxJson();
		try {
			String scenicdataid = request.getParameter("scenicdataid");
			request.setAttribute("scenicdataid", scenicdataid);
			// 设置旅行社信息
			Traveldata travelData = systemService.getEntity(Traveldata.class,
					scenicdataid);
			// 设置未填报
			travelQuarterInland.setStatus("1");
			travelQuarterInland.setTraveldata(travelData);
			// 判断数据是否
			if (travelQuarterInland.getId() == null
					|| travelQuarterInland.getId().length() == 0) {
				travelQuarterInland.setId(null);
			}
			travelQuarterInland.setTid(scenicdataid);
			systemService.saveOrUpdate(travelQuarterInland);
			returnTip.setMsg("保存成功!");
			systemService.addLog("暂存旅行社国内季报成功:" + travelQuarterInland.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("暂存旅行社国内季报失败", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} finally {
			return returnTip;
		}
	}

	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,
			TravelQuarterIn travelQuarterIn, Traveldata traveldata,
			DataGrid dataGrid) {
		String travelid = request.getParameter("travelid");
		String quarteroneid = request.getParameter("quarteroneid");
		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quarteroneid);
		Traveldata s = this.systemService.get(Traveldata.class, travelid);
		TravelQuarterIn m = this.systemService.get(TravelQuarterIn.class,
				quarteroneid);

		request.setAttribute("travelData", s);
		request.setAttribute("quarteroneData", m);
		// 空值处理
		if(m == null) m = new TravelQuarterIn();
		travelQuarterIn.setTraveldata(s);
		request.setAttribute("travelQuarterIn", travelQuarterIn);

		return new ModelAndView("travelquarter/saveOrgInTravelQuarter");
	}

	@RequestMapping(params = "audittow")
	public ModelAndView audittow(HttpServletRequest request,
			TravelQuarterOut travelQuarterOut, Traveldata traveldata,
			DataGrid dataGrid) {
		String travelid = request.getParameter("travelid");
		String quartertwoid = request.getParameter("quartertwoid");
		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quartertwoid);
		Traveldata s = this.systemService.get(Traveldata.class, travelid);
		TravelQuarterOut m = this.systemService.get(TravelQuarterOut.class,
				quartertwoid);

		request.setAttribute("travelData", s);
		// 空值处理
		if(m == null) m = new TravelQuarterOut();
		request.setAttribute("quarterTwoData", m);
		m.setTraveldata(s);
		request.setAttribute("m", m);

		return new ModelAndView("travelquarter/saveOrgOutTravelQuarter");
	}

	@RequestMapping(params = "auditthree")
	public ModelAndView auditthree(HttpServletRequest request,
			TravelQuarterInland travelQuarterInland, Traveldata traveldata,
			DataGrid dataGrid) {
		String travelid = request.getParameter("travelid");
		String quarterthreeid = request.getParameter("quarterthreeid");
		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quarterthreeid);
		Traveldata s = this.systemService.get(Traveldata.class, travelid);
		TravelQuarterInland m = this.systemService.get(
				TravelQuarterInland.class, quarterthreeid);

		request.setAttribute("travelData", s);
		// 空值处理
		if(m == null) m = new TravelQuarterInland();
		request.setAttribute("quarterThreeData", m);
		m.setTraveldata(s);
		request.setAttribute("m", m);

		return new ModelAndView("travelquarter/saveOrgTravelQuarter");
	}

	@RequestMapping(params = "checkDetail")
	public ModelAndView checkDetail(HttpServletRequest request,
			HttpServletResponse response) {
		String travelId = request.getParameter("travelId");
		String entryId = request.getParameter("entryId");
		String exitId = request.getParameter("exitId");
		String inlandId = request.getParameter("inlandId");

		request.setAttribute("travelId", travelId);
		request.setAttribute("entryId", entryId);
		request.setAttribute("exitId", exitId);
		request.setAttribute("inlandId", inlandId);
		
		
		
		return new ModelAndView("travelquarter/quarterlyReportCollection");
	}
	
	/**
	 * @author Fp
	 * @param request
	 * @return 入境表格页面
	 */
	@RequestMapping(params = "entryReportManageOfAdmin")
	public ModelAndView entryReportManageOfAdmin(HttpServletRequest request) {
		//获取上一季度
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String season = this.getQuarter();
		season = String.valueOf(Integer.parseInt(season) - 1);
		// 对跨年的情况做处理
		if("0".equals(season)){
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			season = "4";
		}
		request.setAttribute("year", year);
		request.setAttribute("season", season);
		
		return new ModelAndView("travelquarter/quarterlyReportEntry");
	}
	/**
	 * @author Fp
	 * @param request
	 * @return 出境表格页面
	 */
	@RequestMapping(params = "leaveReportManageOfAdmin")
	public ModelAndView leaveReportManageOfAdmin(HttpServletRequest request) {
		//获取上一季度
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String season = this.getQuarter();
		season = String.valueOf(Integer.parseInt(season) - 1);
		// 对跨年的情况做处理
		if("0".equals(season)){
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			season = "4";
		}
		request.setAttribute("year", year);
		request.setAttribute("season", season);
		
		return new ModelAndView("travelquarter/quarterlyReportLeave");
	}
	/**
	 * @author Fp
	 * @param request
	 * @return 国内表格页面
	 */
	@RequestMapping(params = "inlandReportManageOfAdmin")
	public ModelAndView inlandReportManageOfAdmin(HttpServletRequest request) {
		//获取上一季度
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String season = this.getQuarter();
		season = String.valueOf(Integer.parseInt(season) - 1);
		// 对跨年的情况做处理
		if("0".equals(season)){
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			season = "4";
		}
		request.setAttribute("year", year);
		request.setAttribute("season", season);
		
		return new ModelAndView("travelquarter/quarterlyReportInland");
	}
	
	/**
	 * 
	 * 管理员的入境季报表格
	 * @param travelQuarterInland
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "entryGridOfAdmin")
	public void entryGridOfAdmin(TravelQuarterIn travelQuarterIn,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterIn.class,
				dataGrid);
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService.findOneForJdbc("select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)", userId);
		// 区级管理员做判断
		if (userMap.get("rolename") != null
				&& userMap.get("rolename").toString().contains("区级")) {
			List<Map<String, Object>> list = systemService.findForJdbc(
					"select id from t_travelagency_info where area=?",
					ResourceUtil.getSessionUserName().getArea());
			if(list.size() != 0){
				String[] parmas = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String id = list.get(i).get("id").toString();
					parmas[i] = id;
				}
				cq.add(Restrictions.in("tid", parmas));
			}else{
				cq.add(Restrictions.in("tid", new Object[]{""}));
			}
		}
		//获取上一季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if("0".equals(nowSeason)){
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}
		// 查询条件组装器
		// TODO 待修改
		String licensenumQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getName();
		String yearQuery = travelQuarterIn.getYear();
		String quarterQuery = travelQuarterIn.getQuarter();
		String statusQuery = travelQuarterIn.getStatus();
		
		
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		if(yearQuery !=null && yearQuery.length() != 0){
			cq.add(Restrictions.like("year", "%"+yearQuery+"%"));
		}else{
			cq.add(Restrictions.like("year", "%"+nowYear+"%"));
		}
		if(quarterQuery !=null && quarterQuery.length() != 0){
			cq.add(Restrictions.like("quarter", quarterQuery));
		}else{
			cq.add(Restrictions.like("quarter", nowSeason));
		}
		if(statusQuery !=null && statusQuery.length() != 0){
			cq.add(Restrictions.like("status", statusQuery));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new TravelQuarterIn());
		
		systemService.getDataGridReturn(cq, true);
		
		// 五大洲汇总统计
		/*for (Iterator<TravelQuarterIn> iterator = dataGrid.getResults().iterator(); iterator.hasNext();) {
			TravelQuarterIn temp = iterator.next();
			temp.setAsia(String.valueOf(temp.asiaSeason()));
			temp.setEurope(String.valueOf(temp.europeSeason()));
			temp.setAfrica(String.valueOf(temp.americaSeason()));
			temp.setOceania(String.valueOf(temp.oceaniaSeason()));
			temp.setAmerica(String.valueOf(temp.africaSeason()));
		}*/
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 
	 * 管理员的出境季报表格
	 * @param travelQuarterInland
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "leaveGridOfAdmin")
	public void leaveGridOfAdmin(TravelQuarterOut travelQuarterOut,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterOut.class,
				dataGrid);
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService.findOneForJdbc("select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)", userId);
		// 区级管理员做判断
		if (userMap.get("rolename") != null && userMap.get("rolename").toString().contains("区级")) {
			List<Map<String, Object>> list = systemService.findForJdbc("select id from t_travelagency_info where area=?", ResourceUtil.getSessionUserName().getArea());
			if(list.size() != 0){
				String[] parmas = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String id = list.get(i).get("id").toString();
					parmas[i] = id;
				}
				cq.add(Restrictions.in("tid", parmas));
			}else{
				cq.add(Restrictions.in("tid", new Object[]{""}));
			}
		}
		//获取上一季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if ("0".equals(nowSeason)) {
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}
		// 查询条件组装器
		// TODO 待修改
		String licensenumQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getName();
		String yearQuery = travelQuarterOut.getYear();
		String quarterQuery = travelQuarterOut.getQuarter();
		String statusQuery = travelQuarterOut.getStatus();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		if(yearQuery !=null && yearQuery.length() != 0){
			cq.add(Restrictions.like("year", "%"+yearQuery+"%"));
		}else{
			cq.add(Restrictions.like("year", "%"+nowYear+"%"));
		}
		if(quarterQuery !=null && quarterQuery.length() != 0){
			cq.add(Restrictions.like("quarter", quarterQuery));
		}else{
			cq.add(Restrictions.like("quarter", nowSeason));
		}
		if(statusQuery !=null && statusQuery.length() != 0){
			cq.add(Restrictions.like("status", statusQuery));
		}
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,new TravelQuarterOut());
		systemService.getDataGridReturn(cq, true);
		// 五大洲汇总统计
		/*for (Iterator<TravelQuarterOut> iterator = dataGrid.getResults().iterator(); iterator.hasNext();) {
			TravelQuarterOut temp = iterator.next();
			temp.setAsia(String.valueOf(temp.asiaSeason()));
			temp.setEurope(String.valueOf(temp.europeSeason()));
			temp.setAfrica(String.valueOf(temp.americaSeason()));
			temp.setOceania(String.valueOf(temp.oceaniaSeason()));
			temp.setAmerica(String.valueOf(temp.africaSeason()));
		}*/
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 
	 * 管理员的国内季报表格
	 * @param travelQuarterInland
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "inlandGridOfAdmin")
	public void inlandGridOfAdmin(TravelQuarterInland travelQuarterInland,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterInland.class,dataGrid);
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService.findOneForJdbc("select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)", userId);
		// 区级管理员做判断
		if (userMap.get("rolename") != null
				&& userMap.get("rolename").toString().contains("区级")) {
			List<Map<String, Object>> list = systemService.findForJdbc(
					"select id from t_travelagency_info where area=?",
					ResourceUtil.getSessionUserName().getArea());
			if(list.size() != 0){
				String[] parmas = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String id = list.get(i).get("id").toString();
					parmas[i] = id;
				}
				cq.add(Restrictions.in("tid", parmas));
			}else{
				cq.add(Restrictions.in("tid", new Object[]{""}));
			}
		}
		//获取上一季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if ("0".equals(nowSeason)) {
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}
		// 查询条件组装器
		// TODO 待修改
		String licensenumQuery = travelQuarterInland.getTraveldata() == null ? "" : travelQuarterInland.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterInland.getTraveldata() == null ? "" : travelQuarterInland.getTraveldata().getName();
		String yearQuery = travelQuarterInland.getYear();
		String quarterQuery = travelQuarterInland.getQuarter();
		String statusQuery = travelQuarterInland.getStatus();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		if(yearQuery !=null && yearQuery.length() != 0){
			cq.add(Restrictions.like("year", "%"+yearQuery+"%"));
		}else{
			cq.add(Restrictions.like("year", "%"+nowYear+"%"));
		}
		if(quarterQuery !=null && quarterQuery.length() != 0){
			cq.add(Restrictions.like("quarter", quarterQuery));
		}else{
			cq.add(Restrictions.like("quarter", nowSeason));
		}
		if(statusQuery !=null && statusQuery.length() != 0){
			cq.add(Restrictions.like("status", statusQuery));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new TravelQuarterInland());
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 
	 * @author Fp
	 * @param request
	 * @return 管理员入境报表查看
	 */
	@RequestMapping(params = "checkEntryReportByAdmin")
	public ModelAndView checkEntryReportByAdmin(HttpServletRequest request) {
		// 旅行社ID
		String travelid = request.getParameter("travelid");
		// 获取报表ID
		String id = request.getParameter("id");
		
		// 旅行社信息
		Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
		// 报表信息
		TravelQuarterIn travelquarterin = null;
		if(id == null || id.length() == 0){ // 新建
			travelquarterin = new TravelQuarterIn();
		}else{ // 编辑
			travelquarterin = systemService.getEntity(TravelQuarterIn.class, id);
		}
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quarteroneData", travelquarterin);
		return new ModelAndView("travelUser/saveOrgInTravelQuarter");
	}
	
	/**
	 * 
	 * @author Fp
	 * @param request
	 * @return 管理员出境报表查看
	 */
	@RequestMapping(params = "checkLeaveReportByAdmin")
	public ModelAndView checkLeaveReportByAdmin(HttpServletRequest request) {
		// 旅行社ID
		String travelid = request.getParameter("travelid");
		// 获取报表ID
		String id = request.getParameter("id");
		
		// 旅行社信息
		Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
		// 报表信息
		TravelQuarterOut travelquarterout = null;
		if(id == null || id.length() == 0){ // 新建
			travelquarterout = new TravelQuarterOut();
		}else{ // 编辑
			travelquarterout = systemService.getEntity(TravelQuarterOut.class, id);
		}
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quartertwoData", travelquarterout);
		return new ModelAndView("travelUser/saveOrgOutTravelQuarter");
	}
	
	/**
	 * 
	 * @author Fp
	 * @param request
	 * @return 管理员国内报表查看
	 */
	@RequestMapping(params = "checkInlandReportByAdmin")
	public ModelAndView checkInlandReportByAdmin(HttpServletRequest request) {
		// 旅行社ID
		String travelid = request.getParameter("travelid");
		// 获取报表ID
		String id = request.getParameter("id");
		
		// 旅行社信息
		Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
		// 报表信息
		TravelQuarterInland travelquarterinland = null;
		if(id == null || id.length() == 0){ // 新建
			travelquarterinland = new TravelQuarterInland();
		}else{ // 编辑
			travelquarterinland = systemService.getEntity(TravelQuarterInland.class, id);
		}
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quarterthreeData", travelquarterinland);
		return new ModelAndView("travelUser/saveOrgTravelQuarter");
	}
	
	///////////////////////////////////////用户层/////////////////////////////////////
	
	/**
	 * 
	 *	季报填报
	 * @author Fp
	 * @param request
	 * @param response
	 * @return 季报填报页面
	 */
	@RequestMapping(params = "reportEnteringPage")
	public ModelAndView quarterlyReportEntering(HttpServletRequest request,HttpServletResponse response) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		request.setAttribute("travelId", traveldataList.get(0).getId());
		request.setAttribute("entryId", "");
		request.setAttribute("exitId", "");
		request.setAttribute("inlandId", "");
		return new ModelAndView("travelUser/reportEnteringPage");
	}
	
	/**
	 * 
	 * 返回入境报表填报页面
	 * @author Fp
	 * @param request
	 * @param traveldata
	 * @return 入境报表填报页面
	 */
	@RequestMapping(params = "entryReportOfUser")
	public ModelAndView entryReport(HttpServletRequest request) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		String quarteroneid = request.getParameter("quarteroneid");
		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quarteroneid);
		// 旅行社信息
		Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);

		// 报表信息
		TravelQuarterIn travelquarterin = new TravelQuarterIn();
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quarteroneData", travelquarterin);

		return new ModelAndView("travelUser/saveOrgInTravelQuarter");
	}

	/**
	 * 
	 * 返回出境报表填报页面
	 * @author Fp
	 * @param request
	 * @param traveldata
	 * @return 出境报表填报页面
	 */
	@RequestMapping(params = "leaveReportOfUser")
	public ModelAndView leaveReport(HttpServletRequest request) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		String quarteroneid = request.getParameter("quarteroneid");
		
		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quarteroneid);
		Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
		TravelQuarterOut travelquarterout = new TravelQuarterOut();
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quarterTwoData", travelquarterout);
		return new ModelAndView("travelUser/saveOrgOutTravelQuarter");
	}

	/**
	 * 
	 * 返回国内报表填报页面
	 * @author Fp
	 * @param request
	 * @param traveldata
	 * @return 国内报表填报页面
	 */
	@RequestMapping(params = "inlandReportOfUser")
	public ModelAndView inlandReport(HttpServletRequest request) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		String quarteroneid = request.getParameter("quarteroneid");

		request.setAttribute("travelid", travelid);
		request.setAttribute("quarteroneid", quarteroneid);
		Traveldata traveldata = this.systemService.get(Traveldata.class, travelid);
		TravelQuarterInland travelquarterinland = new TravelQuarterInland();
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quarterThreeData", travelquarterinland);
		return new ModelAndView("travelUser/saveOrgTravelQuarter");
	}
	
	/**
	 * 
	 * 返回季报管理页面
	 * @author Fp
	 * @param request
	 * @return 季报管理页面
	 */
	@RequestMapping(params = "quarterManageOfUser")
	public ModelAndView quarterManageOfUser(HttpServletRequest request) {
		return new ModelAndView("travelUser/quarterlyReportManage");
	}
	
	/**
	 * @author Fp
	 * @param request
	 * @return 入境表格页面
	 */
	@RequestMapping(params = "entryReportManageOfuser")
	public ModelAndView entryReportManageOfuser(HttpServletRequest request) {
		return new ModelAndView("travelUser/quarterlyReportEntry");
	}
	/**
	 * @author Fp
	 * @param request
	 * @return 出境表格页面
	 */
	@RequestMapping(params = "leaveReportManageOfUser")
	public ModelAndView leaveReportManageOfUser(HttpServletRequest request) {
		return new ModelAndView("travelUser/quarterlyReportLeave");
	}
	/**
	 * @author Fp
	 * @param request
	 * @return 国内表格页面
	 */
	@RequestMapping(params = "inlandReportManageOfUser")
	public ModelAndView inlandReportManageOfUser(HttpServletRequest request) {
		return new ModelAndView("travelUser/quarterlyReportInland");
	}
	
	/**
	 * 
	 * 用户的入境季报表格
	 * @param travelQuarterInland
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "entryGridOfuser")
	public void entryGridOfuser(TravelQuarterIn travelQuarterIn,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterIn.class,dataGrid);
		// 查询条件组装器
		String licensenumQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getName();
		String yearQuery = travelQuarterIn.getYear();
		String quarterQuery = travelQuarterIn.getQuarter();
		String statusQuery = travelQuarterIn.getStatus();
		
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		if(yearQuery !=null && yearQuery.length() != 0){
			cq.add(Restrictions.like("year", "%"+yearQuery+"%"));
		}
		if(quarterQuery !=null && quarterQuery.length() != 0){
			cq.add(Restrictions.like("quarter", quarterQuery));
		}
		if(statusQuery !=null && statusQuery.length() != 0){
			cq.add(Restrictions.like("status", statusQuery));
		}
		cq.eq("tid", travelid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new TravelQuarterIn());
		
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 
	 * 用户的出境季报表格
	 * @param travelQuarterInland
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "leaveGridOfUser")
	public void leaveGridOfUser(TravelQuarterOut travelQuarterOut,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterOut.class,dataGrid);
		String licensenumQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getName();
		String yearQuery = travelQuarterOut.getYear();
		String quarterQuery = travelQuarterOut.getQuarter();
		String statusQuery = travelQuarterOut.getStatus();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		if(yearQuery !=null && yearQuery.length() != 0){
			cq.add(Restrictions.like("year", "%"+yearQuery+"%"));
		}
		if(quarterQuery !=null && quarterQuery.length() != 0){
			cq.add(Restrictions.like("quarter", quarterQuery));
		}
		if(statusQuery !=null && statusQuery.length() != 0){
			cq.add(Restrictions.like("status", statusQuery));
		}
		cq.eq("tid", travelid);
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new TravelQuarterOut());
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 
	 * 用户的国内季报表格
	 * @param travelQuarterInland
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "inlandGridOfUser")
	public void inlandGridOfUser(TravelQuarterInland travelQuarterInland,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterInland.class,dataGrid);
		String licensenumQuery = travelQuarterInland.getTraveldata() == null ? "" : travelQuarterInland.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterInland.getTraveldata() == null ? "" : travelQuarterInland.getTraveldata().getName();
		String yearQuery = travelQuarterInland.getYear();
		String quarterQuery = travelQuarterInland.getQuarter();
		String statusQuery = travelQuarterInland.getStatus();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		if(yearQuery !=null && yearQuery.length() != 0){
			cq.add(Restrictions.like("year", "%"+yearQuery+"%"));
		}
		if(quarterQuery !=null && quarterQuery.length() != 0){
			cq.add(Restrictions.like("quarter", quarterQuery));
		}
		if(statusQuery !=null && statusQuery.length() != 0){
			cq.add(Restrictions.like("status", statusQuery));
		}
		cq.eq("tid", travelid);
		cq.add();
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new TravelQuarterInland());
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 
	 * @author Fp
	 * @param request
	 * @return 入境报表编辑或新增页面
	 */
	@RequestMapping(params = "addOrEditEntryReport")
	public ModelAndView addOrSaveEntryReport(HttpServletRequest request) {
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
		TravelQuarterIn travelquarterin = null;
		if(id == null || id.length() == 0){ // 新建
			travelquarterin = new TravelQuarterIn();
			// 获取当前年和季度
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			// 对跨年的情况做处理
			if("0".equals(nowSeason)){
				nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
				nowSeason = "4";
			}
			travelquarterin.setYear(nowYear);
			travelquarterin.setQuarter(nowSeason);
			
			travelquarterin.setReportPerson(traveldata.getPrincipal());
			travelquarterin.setReportTelephone(traveldata.getMobile());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			travelquarterin.setReportDate(sdf.format(new Date()));
		}else{ // 编辑
			travelquarterin = systemService.getEntity(TravelQuarterIn.class, id);
		}
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quarteroneData", travelquarterin);
		return new ModelAndView("travelUser/saveOrgInTravelQuarter");
	}
	
	/**
	 * 
	 * 删除入境报表
	 * @author Fp
	 * @param travelquarterin
	 * @param request
	 * @return 
	 */
	@RequestMapping(params = "deleteEntryReport")
	@ResponseBody
	public AjaxJson deleteEntryReport(TravelQuarterIn travelquarterin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			travelquarterin = systemService.getEntity(TravelQuarterIn.class, travelquarterin.getId());
			systemService.delete(travelquarterin);
			message="删除成功!";
		} catch (Exception e) {
			message="删除失败!";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 
	 * @author Fp
	 * @param request
	 * @return 出境报表编辑或新增页面
	 */
	@RequestMapping(params = "addOrEditLeaveReport")
	public ModelAndView addOrEditLeaveReport(HttpServletRequest request) {
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
		TravelQuarterOut travelquarterout = null;
		if(id == null || id.length() == 0){ // 新建
			travelquarterout = new TravelQuarterOut();
			// 获取当前年和季度
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			// 对跨年的情况做处理
			if("0".equals(nowSeason)){
				nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
				nowSeason = "4";
			}			
			travelquarterout.setYear(nowYear);
			travelquarterout.setQuarter(nowSeason);
			
			travelquarterout.setReportPerson(traveldata.getPrincipal());
			travelquarterout.setReportTelephone(traveldata.getMobile());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			travelquarterout.setReportDate(sdf.format(new Date()));
		}else{ // 编辑
			travelquarterout = systemService.getEntity(TravelQuarterOut.class, id);
		}
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quartertwoData", travelquarterout);
		return new ModelAndView("travelUser/saveOrgOutTravelQuarter");
	}
	
	/**
	 * 
	 * 删除出境报表
	 * @author Fp
	 * @param travelquarterout
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "deleteLeaveReport")
	@ResponseBody
	public AjaxJson deleteLeaveReport(TravelQuarterOut travelquarterout, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			travelquarterout = systemService.getEntity(TravelQuarterOut.class, travelquarterout.getId());
			systemService.delete(travelquarterout);
			message="删除成功!";
		} catch (Exception e) {
			message="删除失败!";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 
	 * @author Fp
	 * @param request
	 * @return 国内报表编辑或新增页面
	 */
	@RequestMapping(params = "addOrEditInlandReport")
	public ModelAndView addOrEditInlandReport(HttpServletRequest request) {
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
		TravelQuarterInland travelquarterinland = null;
		if(id == null || id.length() == 0){ // 新建
			travelquarterinland = new TravelQuarterInland();
			// 获取当前年和季度
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			// 对跨年的情况做处理
			if("0".equals(nowSeason)){
				nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
				nowSeason = "4";
			}	
			travelquarterinland.setYear(nowYear);
			travelquarterinland.setQuarter(nowSeason);
			
			travelquarterinland.setReportPerson(traveldata.getPrincipal());
			travelquarterinland.setReportTelephone(traveldata.getMobile());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			travelquarterinland.setReportDate(sdf.format(new Date()));
		}else{ // 编辑
			travelquarterinland = systemService.getEntity(TravelQuarterInland.class, id);
		}
		request.setAttribute("travelData", traveldata);
		request.setAttribute("quarterthreeData", travelquarterinland);
		return new ModelAndView("travelUser/saveOrgTravelQuarter");
	}
	
	/**
	 * 
	 * 删除国内报表
	 * @author Fp
	 * @param travelquarterinland
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "deleteInlandReport")
	@ResponseBody
	public AjaxJson deleteInlandReport(TravelQuarterInland travelquarterinland, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			travelquarterinland = systemService.getEntity(TravelQuarterInland.class, travelquarterinland.getId());
			systemService.delete(travelquarterinland);
			message="删除成功!";
		} catch (Exception e) {
			message="删除失败!";
		}
		j.setMsg(message);
		return j;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(params = "saveOneByAdmin")
	@ResponseBody
	public AjaxJson saveOneByAdmin(HttpServletRequest request,
			TravelQuarterIn travelQuarterIn) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功!");
		try {
			String travelid = request.getParameter("travelid");
			Traveldata d = this.systemService.get(Traveldata.class, travelid);

			travelQuarterIn.setStatus("4");
			travelQuarterIn.setTraveldata(d);
			
			//判断数据是否存在
			String tempId = travelQuarterIn.getId();
			if(tempId == null || tempId.length() == 0) travelQuarterIn.setId(null);
			travelQuarterIn.setTid(travelid);
			systemService.saveOrUpdate(travelQuarterIn);
			systemService.addLog("填报旅行社入境季报成功:" + travelQuarterIn.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("填报旅行社入境季报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}finally{
			return returnTip;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(params = "saveTwoByAdmin")
	@ResponseBody
	public AjaxJson saveTwoByAdmin(HttpServletRequest request,
			TravelQuarterOut travelQuarterOut) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功!");
		try {
			String travelid = request.getParameter("travelid");
			Traveldata d = this.systemService.get(Traveldata.class, travelid);

			travelQuarterOut.setStatus("4");
			travelQuarterOut.setTraveldata(d);
			//判断数据是否存在
			String tempId = travelQuarterOut.getId();
			if(tempId == null || tempId.length() == 0) travelQuarterOut.setId(null);
			travelQuarterOut.setTid(travelid);
			systemService.saveOrUpdate(travelQuarterOut);
			systemService.addLog("填报旅行社出境季报成功:" + travelQuarterOut.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("填报旅行社出境季报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}finally{
			return returnTip;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(params = "saveThreeByAdmin")
	@ResponseBody
	public AjaxJson saveThreeByAdmin(HttpServletRequest request,TravelQuarterInland travelQuarterInland) {
		AjaxJson returnTip = new AjaxJson();
		returnTip.setMsg("保存成功!");
		try {
			String travelid = request.getParameter("travelid");
			Traveldata d = this.systemService.get(Traveldata.class, travelid);

			travelQuarterInland.setStatus("4");
			travelQuarterInland.setTraveldata(d);
			//判断数据是否存在
			String tempId = travelQuarterInland.getId();
			if(tempId == null || tempId.length() == 0) travelQuarterInland.setId(null);
			travelQuarterInland.setTid(travelid);
			systemService.saveOrUpdate(travelQuarterInland);
			systemService.addLog("填报旅行社国内季报成功:" + travelQuarterInland.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			returnTip.setMsg("保存失败!");
			systemService.addLog("填报旅行社国内季报失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}finally{
			return returnTip;
		}
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
		String type = request.getParameter("type");
		
		Map<String, String> modelMap = new HashMap<String, String>();
		if("1".equals(type)){
			TravelQuarterIn travelQuarterIn = systemService.get(TravelQuarterIn.class, id);
			String status = travelQuarterIn == null || travelQuarterIn.getStatus() == null ? "" : travelQuarterIn.getStatus();
			switch (status) {
			case "1":
				status = "市级未填报";
				break;
			case "2":
				status = "市级未审核";
				break;
			case "3":
				status = "市级已审核(审核不通过)";
				break;
			case "4":
				status = "市级已审核(审核通过)";
				break;
			case "5":
				status = "国家系统待审核";
				break;
			case "6":
				status = "国家系统未通过";
				break;
			case "7":
				status = "国家系统已通过";
				break;
			}
			modelMap.put("issueStatus", status);
			modelMap.put("countryContent", travelQuarterIn == null ? "" : travelQuarterIn.getGuo());
		}else if("2".equals(type)){
			TravelQuarterOut travelQuarterOut = systemService.get(TravelQuarterOut.class, id);
			String status = travelQuarterOut == null || travelQuarterOut.getStatus() == null ? "" : travelQuarterOut.getStatus();
			switch (status) {
			case "1":
				status = "市级未填报";
				break;
			case "2":
				status = "市级未审核";
				break;
			case "3":
				status = "市级已审核(审核不通过)";
				break;
			case "4":
				status = "市级已审核(审核通过)";
				break;
			case "5":
				status = "国家系统待审核";
				break;
			case "6":
				status = "国家系统未通过";
				break;
			case "7":
				status = "国家系统已通过";
				break;
			}
			modelMap.put("issueStatus", status);
			modelMap.put("countryContent", travelQuarterOut == null ? "" : travelQuarterOut.getGuo());
		}else if("3".equals(type)){
			TravelQuarterInland travelQuarterInland = systemService.get(TravelQuarterInland.class, id);
			String status = travelQuarterInland == null || travelQuarterInland.getStatus() == null ? "" : travelQuarterInland.getStatus();
			switch (status) {
			case "1":
				status = "市级未填报";
				break;
			case "2":
				status = "市级未审核";
				break;
			case "3":
				status = "市级已审核(审核不通过)";
				break;
			case "4":
				status = "市级已审核(审核通过)";
				break;
			case "5":
				status = "国家系统待审核";
				break;
			case "6":
				status = "国家系统未通过";
				break;
			case "7":
				status = "国家系统已通过";
				break;
			}
			modelMap.put("issueStatus", status);
			modelMap.put("countryContent", travelQuarterInland == null ? "" : travelQuarterInland.getGuo());
		}

		return new ModelAndView("/travel/countryContent").addAllObjects(modelMap);
	}
	
	//////////////////////////////////////////调用webservice/////////////////////////////////////////
	
	/**
	 * 
	 * 保存国内报表接口方法
	 * @author Fp
	 * @param travelQuarterInland
	 * @return 调用结果
	 */
	public String addOrUpdateInlandTravelQuarter(TravelQuarterInland travelQuarterInland, String travelid){
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = new PublicInterfaceSoapProxy();
		
		// 获取旅行社信息
		Traveldata traveldata = systemService.get(Traveldata.class, travelid);
		
		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> map = null;
		String tip = "200";
		String areaID = GlobalParams.areaID;
		String code = traveldata.getLicensenum();
		String unitMaster = traveldata.getPrincipal();
		String filler = travelQuarterInland.getReportPerson();
		String fillerTel = travelQuarterInland.getReportTelephone();
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
		//安徽（人天，人次）	002001
		map = new HashMap<>();
		map.put("sortID", "002001");
		map.put("Season", String.valueOf(travelQuarterInland.getAhOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getAhTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getAhThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getAhFour()));
		list.add(map);
//		北京（人天，人次）	002002
		map = new HashMap<>();
		map.put("sortID", "002002");
		map.put("Season", String.valueOf(travelQuarterInland.getBjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getBjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getBjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getBjFour()));
		list.add(map);
//		福建（人天，人次）	002003
		map = new HashMap<>();
		map.put("sortID", "002003");
		map.put("Season", String.valueOf(travelQuarterInland.getFjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getFjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getFjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getFjFour()));
		list.add(map);
//		甘肃（人天，人次）	002004
		map = new HashMap<>();
		map.put("sortID", "002004");
		map.put("Season", String.valueOf(travelQuarterInland.getGansuOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getGansuTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getGansuThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getGansuFour()));
		list.add(map);
//		广东（人天，人次）	002005
		map = new HashMap<>();
		map.put("sortID", "002005");
		map.put("Season", String.valueOf(travelQuarterInland.getGdOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getGdTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getGdThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getGdFour()));
		list.add(map);
//		广西（人天，人次）	002006
		map = new HashMap<>();
		map.put("sortID", "002006");
		map.put("Season", String.valueOf(travelQuarterInland.getGxOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getGxTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getGxThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getGxFour()));
		list.add(map);
//		贵州（人天，人次）	002007
		map = new HashMap<>();
		map.put("sortID", "002007");
		map.put("Season", String.valueOf(travelQuarterInland.getGzOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getGzTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getGzThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getGzFour()));
		list.add(map);
//		海南（人天，人次）	002008
		map = new HashMap<>();
		map.put("sortID", "002008");
		map.put("Season", String.valueOf(travelQuarterInland.getHainanOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHainanTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHainanThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHainanFour()));
		list.add(map);
//		河北（人天，人次）	002009
		map = new HashMap<>();
		map.put("sortID", "002009");
		map.put("Season", String.valueOf(travelQuarterInland.getHbOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHbTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHbThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHbFour()));
		list.add(map);
//		河南（人天，人次）	002010
		map = new HashMap<>();
		map.put("sortID", "002010");
		map.put("Season", String.valueOf(travelQuarterInland.getHenanOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHenanTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHenanThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHenanFour()));
		list.add(map);
//		黑龙江（人天，人次）	002011
		map = new HashMap<>();
		map.put("sortID", "002011");
		map.put("Season", String.valueOf(travelQuarterInland.getHljOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHljTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHljThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHljFour()));
		list.add(map);
//		湖北（人天，人次）	002012
		map = new HashMap<>();
		map.put("sortID", "002012");
		map.put("Season", String.valueOf(travelQuarterInland.getHubeiOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHubeiTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHubeiThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHubeiFour()));
		list.add(map);
//		湖南（人天，人次）	002013
		map = new HashMap<>();
		map.put("sortID", "002013");
		map.put("Season", String.valueOf(travelQuarterInland.getHlOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHlTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHlThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHlFour()));
		list.add(map);
//		吉林（人天，人次）	002014
		map = new HashMap<>();
		map.put("sortID", "002014");
		map.put("Season", String.valueOf(travelQuarterInland.getJlOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getJlTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getJlThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getJlFour()));
		list.add(map);
//		江苏（人天，人次）	002015
		map = new HashMap<>();
		map.put("sortID", "002015");
		map.put("Season", String.valueOf(travelQuarterInland.getJsOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getJsTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getJsThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getJsFour()));
		list.add(map);
//		江西（人天，人次）	002016
		map = new HashMap<>();
		map.put("sortID", "002016");
		map.put("Season", String.valueOf(travelQuarterInland.getJxOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getJxTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getJxThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getJxFour()));
		list.add(map);
//		辽宁（人天，人次）	002017
		map = new HashMap<>();
		map.put("sortID", "002017");
		map.put("Season", String.valueOf(travelQuarterInland.getLnOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getLnTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getLnThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getLnFour()));
		list.add(map);
//		内蒙古（人天，人次）	002018
		map = new HashMap<>();
		map.put("sortID", "002018");
		map.put("Season", String.valueOf(travelQuarterInland.getNmgOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getNmgTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getNmgThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getNmgFour()));
		list.add(map);
//		宁夏（人天，人次）	002019
		map = new HashMap<>();
		map.put("sortID", "002019");
		map.put("Season", String.valueOf(travelQuarterInland.getNxOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getNxTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getNxThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getNxFour()));
		list.add(map);
//		青海（人天，人次）	002020
		map = new HashMap<>();
		map.put("sortID", "002020");
		map.put("Season", String.valueOf(travelQuarterInland.getQinghaiOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getQinghaiTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getQinghaiThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getQinghaiFour()));
		list.add(map);
//		山东（人天，人次）	002021
		map = new HashMap<>();
		map.put("sortID", "002021");
		map.put("Season", String.valueOf(travelQuarterInland.getSdOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getSdTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getSdThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getSdFour()));
		list.add(map);
//		山西（人天，人次）	002022
		map = new HashMap<>();
		map.put("sortID", "002022");
		map.put("Season", String.valueOf(travelQuarterInland.getJxOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getJxTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getJxThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getJxFour()));
		list.add(map);
//		陕西（人天，人次）	002023
		map = new HashMap<>();
		map.put("sortID", "002023");
		map.put("Season", String.valueOf(travelQuarterInland.getShanxiOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getShanxiTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getShanxiThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getShanxiFour()));
		list.add(map);
//		上海（人天，人次）	002024
		map = new HashMap<>();
		map.put("sortID", "002024");
		map.put("Season", String.valueOf(travelQuarterInland.getShOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getShTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getShThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getShFour()));
		list.add(map);
//		四川（人天，人次）	002025
		map = new HashMap<>();
		map.put("sortID", "002025");
		map.put("Season", String.valueOf(travelQuarterInland.getScOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getScTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getScThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getScFour()));
		list.add(map);
//		天津（人天，人次）	002026
		map = new HashMap<>();
		map.put("sortID", "002026");
		map.put("Season", String.valueOf(travelQuarterInland.getTjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getTjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getTjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getTjFour()));
		list.add(map);
//		西藏（人天，人次）	002027
		map = new HashMap<>();
		map.put("sortID", "002027");
		map.put("Season", String.valueOf(travelQuarterInland.getXzOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getXzTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getXzThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getXzFour()));
		list.add(map);
//		新疆（人天，人次）	002028
		map = new HashMap<>();
		map.put("sortID", "002028");
		map.put("Season", String.valueOf(travelQuarterInland.getXjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getXjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getXjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getXjFour()));
		list.add(map);
//		云南（人天，人次）	002029
		map = new HashMap<>();
		map.put("sortID", "002029");
		map.put("Season", String.valueOf(travelQuarterInland.getYnOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getYnTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getYnThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getYnFour()));
		list.add(map);
//		浙江（人天，人次）	002030
		map = new HashMap<>();
		map.put("sortID", "002030");
		map.put("Season", String.valueOf(travelQuarterInland.getZjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getZjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getZjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getZjFour()));
		list.add(map);
//		重庆（人天，人次）	002031
		map = new HashMap<>();
		map.put("sortID", "002031");
		map.put("Season", String.valueOf(travelQuarterInland.getCqOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getCqTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getCqThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getCqFour()));
		list.add(map);
		
		try {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map<String, String> map2 = (Map<String, String>) iterator.next();
				String sortID = map2.get("sortID");
				String seasonDay = map2.get("SeasonDay");
				String seasonDay_boy = map2.get("SeasonDay_boy");
				String season = map2.get("Season");
				String season_boy = map2.get("Season_boy");
				String rs = travelAgencyService.insertIntoInternalTourism(areaID, code, unitMaster, filler, fillerTel, sortID, seasonDay, seasonDay_boy, season, season_boy, verificationCode);
				System.out.println(sortID +" : "+rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			tip = "通信异常";
		}
		return tip;
	}
	
	/**
	 * 
	 * 出境报表接口
	 * @author Fp
	 * @param travelQuarterInland
	 * @param travelid
	 * @return 出境接口调用结果
	 */
	public String addOrUpdateLeaveTravelQuarter(TravelQuarterOut travelQuarterOut, String travelid){
		String tip = "200";
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = new PublicInterfaceSoapProxy();

		// 获取旅行社信息
		Traveldata traveldata = systemService.get(Traveldata.class, travelid);

		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = null;
		String areaID = GlobalParams.areaID;
		String code = traveldata.getLicensenum();
		String unitMaster = traveldata.getPrincipal();
		String filler = travelQuarterOut.getReportPerson();
		String fillerTel = travelQuarterOut.getReportTelephone();
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
//		出境游客（人天总数）	001001
		map = new HashMap<>();
		map.put("sortID", "001001");
		map.put("Season", String.valueOf(travelQuarterOut.getOutOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getOutTwo()));
		list.add(map);
//		香港地区（人次）	001001001001
		map = new HashMap<>();
		map.put("sortID", "001001001001");
		map.put("Season", String.valueOf(travelQuarterOut.getHongKongOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getHongKongTwo()));
		list.add(map);
//		澳门地区（人次）	001001001002
		map = new HashMap<>();
		map.put("sortID", "001001001002");
		map.put("Season", String.valueOf(travelQuarterOut.getMacauOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getMacauTwo()));
		list.add(map);
//		台湾地区（人次）	001001001003
		map = new HashMap<>();
		map.put("sortID", "001001001003");
		map.put("Season", String.valueOf(travelQuarterOut.getTaiWanOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getTaiWanTwo()));
		list.add(map);
//		日本（人次）	001001001004
		map = new HashMap<>();
		map.put("sortID", "001001001004");
		map.put("Season", String.valueOf(travelQuarterOut.getJapanOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getJapanTwo()));
		list.add(map);
//		韩国（人次）	001001001005
		map = new HashMap<>();
		map.put("sortID", "001001001005");
		map.put("Season", String.valueOf(travelQuarterOut.getKoreaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getKoreaTwo()));
		list.add(map);
//		蒙古（人次）	001001001006
		map = new HashMap<>();
		map.put("sortID", "001001001006");
		map.put("Season", String.valueOf(travelQuarterOut.getMongoliaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getMongoliaTwo()));
		list.add(map);
//		印度尼西亚（人次）	001001001007
		map = new HashMap<>();
		map.put("sortID", "001001001007");
		map.put("Season", String.valueOf(travelQuarterOut.getIndonesiaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getIndonesiaTwo()));
		list.add(map);
//		马来西亚（人次）	001001001008
		map = new HashMap<>();
		map.put("sortID", "001001001008");
		map.put("Season", String.valueOf(travelQuarterOut.getMalaysiaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getMalaysiaTwo()));
		list.add(map);
//		菲律宾（人次）	001001001009
		map = new HashMap<>();
		map.put("sortID", "001001001009");
		map.put("Season", String.valueOf(travelQuarterOut.getPhilippinesOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getPhilippinesTwo()));
		list.add(map);
//		新加坡（人次）	001001001010
		map = new HashMap<>();
		map.put("sortID", "001001001010");
		map.put("Season", String.valueOf(travelQuarterOut.getSingaporeOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getSingaporeTwo()));
		list.add(map);
//		泰国（人次）	001001001011
		map = new HashMap<>();
		map.put("sortID", "001001001011");
		map.put("Season", String.valueOf(travelQuarterOut.getThailandOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getThailandTwo()));
		list.add(map);
//		印度（人次）	001001001012
		map = new HashMap<>();
		map.put("sortID", "001001001012");
		map.put("Season", String.valueOf(travelQuarterOut.getIndiaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getIndiaTwo()));
		list.add(map);
//		越南（人次）	001001001013
		map = new HashMap<>();
		map.put("sortID", "001001001013");
		map.put("Season", String.valueOf(travelQuarterOut.getVietnamOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getVietnamTwo()));
		list.add(map);
//		缅甸（人次）	001001001014
		map = new HashMap<>();
		map.put("sortID", "001001001014");
		map.put("Season", String.valueOf(travelQuarterOut.getBurmaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getBurmaTwo()));
		list.add(map);
//		亚洲其他（人次）	001001001015
		map = new HashMap<>();
		map.put("sortID", "001001001015");
		map.put("Season", String.valueOf(travelQuarterOut.getAsianOtherOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getAsianOtherTwo()));
		list.add(map);
//		英国（人次）	001001002001
		map = new HashMap<>();
		map.put("sortID", "001001002001");
		map.put("Season", String.valueOf(travelQuarterOut.getEnglishOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getEnglishTwo()));
		list.add(map);
//		法国（人次）	001001002002
		map = new HashMap<>();
		map.put("sortID", "001001002002");
		map.put("Season", String.valueOf(travelQuarterOut.getFranchOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getFranchTwo()));
		list.add(map);
//		德国（人次）	001001002003
		map = new HashMap<>();
		map.put("sortID", "001001002003");
		map.put("Season", String.valueOf(travelQuarterOut.getGermanyOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getGermanyTwo()));
		list.add(map);
//		意大利（人次）	001001002004
		map = new HashMap<>();
		map.put("sortID", "001001002004");
		map.put("Season", String.valueOf(travelQuarterOut.getItalyOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		瑞士（人次）	001001002005
		map = new HashMap<>();
		map.put("sortID", "001001002005");
		map.put("Season", String.valueOf(travelQuarterOut.getSwitzerLandOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		瑞典（人次）	001001002006
		map = new HashMap<>();
		map.put("sortID", "001001002006");
		map.put("Season", String.valueOf(travelQuarterOut.getSwedenOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		俄罗斯（人次）	001001002007
		map = new HashMap<>();
		map.put("sortID", "001001002007");
		map.put("Season", String.valueOf(travelQuarterOut.getRussiaOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		西班牙（人次）	001001002008
		map = new HashMap<>();
		map.put("sortID", "001001002008");
		map.put("Season", String.valueOf(travelQuarterOut.getSpainOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		荷兰（人次）	001001002009
		map = new HashMap<>();
		map.put("sortID", "001001002009");
		map.put("Season", String.valueOf(travelQuarterOut.getHolLandOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		丹麦（人次）	001001002010
		map = new HashMap<>();
		map.put("sortID", "001001002010");
		map.put("Season", String.valueOf(travelQuarterOut.getDenMarkOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		欧洲其他（人次）	001001002011
		map = new HashMap<>();
		map.put("sortID", "001001002011");
		map.put("Season", String.valueOf(travelQuarterOut.getEuropeOtherOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		美国（人次）	001001003001
		map = new HashMap<>();
		map.put("sortID", "001001003001");
		map.put("Season", String.valueOf(travelQuarterOut.getUsOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		加拿大（人次）	001001003002
		map = new HashMap<>();
		map.put("sortID", "001001003002");
		map.put("Season", String.valueOf(travelQuarterOut.getCanadaOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		美洲其他（人次）	001001003003
		map = new HashMap<>();
		map.put("sortID", "001001003003");
		map.put("Season", String.valueOf(travelQuarterOut.getUsOtherOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		澳大利亚（人次）	001001004001
		map = new HashMap<>();
		map.put("sortID", "001001004001");
		map.put("Season", String.valueOf(travelQuarterOut.getAustralianOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		新西兰（人次）	001001004002
		map = new HashMap<>();
		map.put("sortID", "001001004002");
		map.put("Season", String.valueOf(travelQuarterOut.getZeaLandOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		大洋洲其他（人次）	001001004003
		map = new HashMap<>();
		map.put("sortID", "001001004003");
		map.put("Season", String.valueOf(travelQuarterOut.getOceaniaOtherOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		南非（人次）	001001005001
		map = new HashMap<>();
		map.put("sortID", "001001005001");
		map.put("Season", String.valueOf(travelQuarterOut.getSouthAfricaOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		埃及（人次）	001001005002
		map = new HashMap<>();
		map.put("sortID", "001001005002");
		map.put("Season", String.valueOf(travelQuarterOut.getEgyptOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		肯尼亚（人次）	001001005003
		map = new HashMap<>();
		map.put("sortID", "001001005003");
		map.put("Season", String.valueOf(travelQuarterOut.getKenyaOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		非洲其他（人次）	001001005004
		map = new HashMap<>();
		map.put("sortID", "001001005004");
		map.put("Season", String.valueOf(travelQuarterOut.getAfricaOtherOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		其他小计（人次）	001001006
		map = new HashMap<>();
		map.put("sortID", "001001006");
		map.put("Season", String.valueOf(travelQuarterOut.getOtherTotalOne()));
		map.put("SeasonDay", "0");
		list.add(map);
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map2 = (Map<String, String>) iterator.next();
			String sortID = map2.get("sortID");
			String season = map2.get("Season");
			String seasonDay = map2.get("SeasonDay");
			try {
				String rs = travelAgencyService.insertIntoOutboundTravel(areaID, code, unitMaster, filler, fillerTel, sortID, seasonDay, season, verificationCode);
				System.out.println(sortID+" : "+rs);
			} catch (RemoteException e) {
				e.printStackTrace();
				tip = "通信异常";
			}
		}
		return tip;
	}
	
	/**
	 * 
	 * 入境报表
	 * @author Fp
	 * @param travelQuarterIn
	 * @param travelid
	 * @return
	 */
	public String addOrUpdateEntryTravelQuarter(TravelQuarterIn travelQuarterIn, String travelid){
		String tip = "200";
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = new PublicInterfaceSoapProxy();

		// 获取旅行社信息
		Traveldata traveldata = systemService.get(Traveldata.class, travelid);

		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = null;
		String areaID = GlobalParams.areaID;
		String code = traveldata.getLicensenum();
		String unitMaster = traveldata.getPrincipal();
		String filler = travelQuarterIn.getReportPerson();
		String fillerTel = travelQuarterIn.getReportTelephone();
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
//		香港（人天，人次）	001001001001
		map = new HashMap<>();
		map.put("sortID", "001001001001");
		map.put("Season", String.valueOf(travelQuarterIn.getHkComOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getHkComTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getHkComThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getHkComFour()));
		list.add(map);
//		澳门（人天，人次）	001001001002
		map = new HashMap<>();
		map.put("sortID", "001001001002");
		map.put("Season", String.valueOf(travelQuarterIn.getMcComOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getMcComTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getMcComThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getMcComFour()));
		list.add(map);
//		台湾（人天，人次）	001001001003
		map = new HashMap<>();
		map.put("sortID", "001001001003");
		map.put("Season", String.valueOf(travelQuarterIn.getTwComOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getTwComTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getTwComThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getTwComFour()));
		list.add(map);
//		外国人（人天总数）	001001007
		map = new HashMap<>();
		map.put("sortID", "001001007");
		map.put("Season", String.valueOf(travelQuarterIn.getForeignersOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getForeignersTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getForeignersThree()));
		map.put("SeasonAdmitDay", String.valueOf(+travelQuarterIn.getForeignersFour()));
		list.add(map);
//		日本（人次）	001001001004
		map = new HashMap<>();
		map.put("sortID", "001001001004");
		map.put("Season", String.valueOf(travelQuarterIn.getJapanOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getJapanTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getJapanThree()));
		map.put("SeasonAdmitDay", String.valueOf(+travelQuarterIn.getJapanFour()));
		list.add(map);
//		韩国（人次）	001001001005
		map = new HashMap<>();
		map.put("sortID", "001001001005");
		map.put("Season", String.valueOf(travelQuarterIn.getKoreaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getKoreaTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getKoreaThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getKoreaFour()));
		list.add(map);
//		蒙古（人次）	001001001006
		map = new HashMap<>();
		map.put("sortID", "001001001006");
		map.put("Season", String.valueOf(travelQuarterIn.getMongoOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getMongoTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getMongoThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getMongoFour()));
		list.add(map);
//		印度尼西亚（人次）	001001001007
		map = new HashMap<>();
		map.put("sortID", "001001001007");
		map.put("Season", String.valueOf(travelQuarterIn.getIndonxyOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getIndonxyTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getIndonxyThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getIndonxyFour()));
		list.add(map);
//		马来西亚（人次）	001001001008
		map = new HashMap<>();
		map.put("sortID", "001001001008");
		map.put("Season", String.valueOf(travelQuarterIn.getMalaxyOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getMalaxyTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getMalaxyThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getMalaxyFour()));
		list.add(map);
//		菲律宾（人次）	001001001009
		map = new HashMap<>();
		map.put("sortID", "001001001009");
		map.put("Season", String.valueOf(travelQuarterIn.getPhilipnOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getPhilipnTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getPhilipnThree()));
		map.put("SeasonAdmitDay", String.valueOf(+travelQuarterIn.getPhilipnFour()));
		list.add(map);
//		新加坡（人次）	001001001010
		map = new HashMap<>();
		map.put("sortID", "001001001010");
		map.put("Season", String.valueOf(travelQuarterIn.getSingaporeOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSingaporeTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getSingaporeThree()));
		map.put("SeasonAdmitDay", String.valueOf(+travelQuarterIn.getSingaporeFour()));
		list.add(map);
//		泰国（人次）	001001001011
		map = new HashMap<>();
		map.put("sortID", "001001001011");
		map.put("Season", String.valueOf(travelQuarterIn.getTailandOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getTailandTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getTailandThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getTailandFour()));
		list.add(map);
//		印度（人次）	001001001012
		map = new HashMap<>();
		map.put("sortID", "001001001012");
		map.put("Season", String.valueOf(travelQuarterIn.getIndiaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getIndiaTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getIndiaThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getIndiaFour()));
		list.add(map);
//		越南（人次）	001001001013
		map = new HashMap<>();
		map.put("sortID", "001001001013");
		map.put("Season", String.valueOf(travelQuarterIn.getVietnamOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getVietnamTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getVietnamThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getVietnamFour()));
		list.add(map);
//		缅甸（人次）	001001001014
		map = new HashMap<>();
		map.put("sortID", "001001001014");
		map.put("Season", String.valueOf(travelQuarterIn.getMyanamarOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getMyanamarTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getMyanamarThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getMyanamarFour()));
		list.add(map);
//		亚洲其他（人次）	001001001015
		map = new HashMap<>();
		map.put("sortID", "001001001015");
		map.put("Season", String.valueOf(travelQuarterIn.getOtherAsianOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getOtherAsianTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getOtherAsianThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getOtherAsianFour()));
		list.add(map);
//		英国（人次）	001001002001
		map = new HashMap<>();
		map.put("sortID", "001001002001");
		map.put("Season", String.valueOf(travelQuarterIn.getEnglandOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getEnglandTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		法国（人次）	001001002002
		map = new HashMap<>();
		map.put("sortID", "001001002002");
		map.put("Season", String.valueOf(travelQuarterIn.getFrenchOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getFrenchTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		德国（人次）	001001002003
		map = new HashMap<>();
		map.put("sortID", "001001002003");
		map.put("Season", String.valueOf(travelQuarterIn.getGermanyOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getGermanyTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		意大利（人次）	001001002004
		map = new HashMap<>();
		map.put("sortID", "001001002004");
		map.put("Season", String.valueOf(travelQuarterIn.getItalyOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getItalyTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		瑞士（人次）	001001002005
		map = new HashMap<>();
		map.put("sortID", "001001002005");
		map.put("Season", String.valueOf(travelQuarterIn.getSwissOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSwissTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		瑞典（人次）	001001002006
		map = new HashMap<>();
		map.put("sortID", "001001002006");
		map.put("Season", String.valueOf(travelQuarterIn.getSwedishOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSwedishTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		俄罗斯（人次）	001001002007
		map = new HashMap<>();
		map.put("sortID", "001001002007");
		map.put("Season", String.valueOf(travelQuarterIn.getRussiaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getRussiaTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		西班牙（人次）	001001002008
		map = new HashMap<>();
		map.put("sortID", "001001002008");
		map.put("Season", String.valueOf(travelQuarterIn.getSpainOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSpainTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		荷兰（人次）	001001002009
		map = new HashMap<>();
		map.put("sortID", "001001002009");
		map.put("Season", String.valueOf(travelQuarterIn.getHollandOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getHollandTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getHollandThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getHollandFour()));
		list.add(map);
////		丹麦（人次）	001001002010
		map = new HashMap<>();
		map.put("sortID", "001001002010");
		map.put("Season", String.valueOf(travelQuarterIn.getDenmarkOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getDenmarkTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		欧洲其他（人次）	001001002011
		map = new HashMap<>();
		map.put("sortID", "001001002011");
		map.put("Season", String.valueOf(travelQuarterIn.getOtherEuropeanOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getOtherEuropeanTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		美国（人次）	001001003001
		map = new HashMap<>();
		map.put("sortID", "001001003001");
		map.put("Season", String.valueOf(travelQuarterIn.getUsOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getUsTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		加拿大（人次）	001001003002
		map = new HashMap<>();
		map.put("sortID", "001001003002");
		map.put("Season", String.valueOf(travelQuarterIn.getCanadaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getCanadaTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		美洲其他（人次）	001001003003
		map = new HashMap<>();
		map.put("sortID", "001001003003");
		map.put("Season", String.valueOf(travelQuarterIn.getOtherAmericanOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getOtherAmericanTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		澳大利亚（人次）	001001004001
		map = new HashMap<>();
		map.put("sortID", "001001004001");
		map.put("Season", String.valueOf(travelQuarterIn.getAustraliaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getAustraliaTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		新西兰（人次）	001001004002
		map = new HashMap<>();
		map.put("sortID", "001001004002");
		map.put("Season", String.valueOf(travelQuarterIn.getNewlandOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getNewlandOne()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		大洋洲其他（人次）	001001004003
		map = new HashMap<>();
		map.put("sortID", "001001004003");
		map.put("Season", String.valueOf(travelQuarterIn.getOtherOceaniaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getOtherOceaniaTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		南非（人次）	001001005001
		map = new HashMap<>();
		map.put("sortID", "001001005001");
		map.put("Season", String.valueOf(travelQuarterIn.getSouthafricaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSouthafricaTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getSouthafricaThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getSouthafricaFour()));
		list.add(map);
//		埃及（人次）	001001005002
		map = new HashMap<>();
		map.put("sortID", "001001005002");
		map.put("Season", String.valueOf(travelQuarterIn.getEgyptOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getEgyptTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		肯尼亚（人次）	001001005003
		map = new HashMap<>();
		map.put("sortID", "001001005003");
		map.put("Season", String.valueOf(travelQuarterIn.getKenyaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getKenyaTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getKenyaThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getKenyaFour()));
		list.add(map);
//		非洲其他（人次）	001001005004
		map = new HashMap<>();
		map.put("sortID", "001001005004");
		map.put("Season", String.valueOf(travelQuarterIn.getAfricaotherOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getAfricaotherTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		其他小计（人次）	001001006
		map = new HashMap<>();
		map.put("sortID", "001001006");
		map.put("Season", String.valueOf(travelQuarterIn.getTotalOtherOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getTotalOtherTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map2 = (Map<String, String>) iterator.next();
			String sortID = map2.get("sortID");
			String season = map2.get("Season");
			String seasonAdmit = map2.get("SeasonAdmit");
			String seasonDay = map2.get("SeasonDay");
			String seasonAdmitDay = map2.get("SeasonAdmitDay");
			try {
				String rs = travelAgencyService.insertIntoInboundTravel(areaID, code, unitMaster, filler, fillerTel, sortID, seasonDay, seasonAdmitDay, season, seasonAdmit, verificationCode);
				System.out.println(sortID+" : "+rs);
			} catch (RemoteException e) {
				e.printStackTrace();
				tip = "通信异常";
				System.out.println("通信异常:"+sortID);
			}
		}
		return tip;
	}
	
	/**
	 * 导出入境季报
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportEntryQuarter")
	public void exportEntryQuarter(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String licensenum = request.getParameter("licensenum");
        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
        String year = request.getParameter("year");
        String quarter = request.getParameter("quarter");
        String status = request.getParameter("status");
        
        String excelName = "入境季报.xls";
        String modelPath = "/com/zzc/web/export/model/travelagency/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("select travelagencyinfo.licensenum,travelagencyinfo.`name`,t.report_person,t.report_date,t.`year`,t.`quarter`,if(t.`status`='1','未填报',if(t.`status`='2','未审核',if(t.`status`='3','未通过','已通过'))) 'status',t.asiatotal_one,t.asiatotal_two,t.other_eropean_one,t.other_eropean_two,t.total_america_one,t.total_america_two,t.total_oceania_one,t.total_oceania_two,t.total_african_one,t.total_african_two from t_travelagency_quarterly1 t left join t_travelagency_info travelagencyinfo on t.tid = travelagencyinfo.id where 1=1 ");
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
        if(quarter != null && quarter.length() != 0){
        	sql.append(" and t.`quarter` like '%"+quarter+"%'");
        }
        if(status != null && status.length() != 0){
        	sql.append(" and t.`status` like '%"+status+"%'");
        }
        sql.append(" UNION ALL select '合计','','','','','','',IFNULL(sum(t.asiatotal_one),0),IFNULL(sum(t.asiatotal_two),0),IFNULL(sum(t.other_eropean_one),0),IFNULL(sum(t.other_eropean_two),0) ,IFNULL(sum(t.total_america_one),0),IFNULL(sum(t.total_america_two),0),IFNULL(sum(t.total_oceania_one),0),IFNULL(sum(t.total_oceania_two),0),IFNULL(sum(t.total_african_one),0),IFNULL(sum(t.total_african_two),0) from t_travelagency_quarterly1 t left join t_travelagency_info travelagencyinfo on t.tid = travelagencyinfo.id where 1=1");
        ExportService emds = new ExportService(17); 
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
        if(quarter != null && quarter.length() != 0){
        	sql.append(" and t.`quarter` like '%"+quarter+"%'");
        }
        if(status != null && status.length() != 0){
        	sql.append(" and t.`status` like '%"+status+"%'");
        }
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
		systemService.addLog("导出旅行社入境季报", Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);

	}
	
	/**
	 * 导出出境季报
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportLeaveQuarter")
	public void exportLeaveQuarter(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String licensenum = request.getParameter("licensenum");
        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
        String year = request.getParameter("year");
        String quarter = request.getParameter("quarter");
        String status = request.getParameter("status");
        
        String excelName = "出境季报.xls";
        String modelPath = "/com/zzc/web/export/model/travelagency/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("select travelagencyinfo.licensenum,travelagencyinfo.`name`,t.report_person,t.report_date,t.`year`,t.`quarter`,if(t.`status`='1','未填报',if(t.`status`='2','未审核',if(t.`status`='3','未通过','已通过'))) 'status',t.asian_total_one,t.Europe_one,t.America_total_one,t.Oceania_total_one,t.Africa_one  from t_travelagency_quarterly2 t left join t_travelagency_info travelagencyinfo on t.tid = travelagencyinfo.id where 1=1 ");
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
        if(quarter != null && quarter.length() != 0){
        	sql.append(" and t.`quarter` like '%"+quarter+"%'");
        }
        if(status != null && status.length() != 0){
        	sql.append(" and t.`status` like '%"+status+"%'");
        }
        sql.append(" UNION ALL ");
        sql.append("select '合计','','','','','','',IFNULL(sum(t.asian_total_one),0),IFNULL(sum(t.Europe_one),0),IFNULL(sum(t.America_total_one),0),IFNULL(sum(t.Oceania_total_one),0),IFNULL(sum(t.Africa_one),0)  from t_travelagency_quarterly2 t left join t_travelagency_info travelagencyinfo on t.tid = travelagencyinfo.id where 1=1");
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
        if(quarter != null && quarter.length() != 0){
        	sql.append(" and t.`quarter` like '%"+quarter+"%'");
        }
        if(status != null && status.length() != 0){
        	sql.append(" and t.`status` like '%"+status+"%'");
        }
        ExportService emds = new ExportService(12); 
        
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
		systemService.addLog("导出旅行社出境季报", Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);

	}
	
	/**
	 * 导出国内季报
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportInlandQuarter")
	public void exportInlandQuarter(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String licensenum = request.getParameter("licensenum");
        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
        String year = request.getParameter("year");
        String quarter = request.getParameter("quarter");
        String status = request.getParameter("status");
        
        String excelName = "国内季报.xls";
        String modelPath = "/com/zzc/web/export/model/travelagency/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("select travelagencyinfo.licensenum,travelagencyinfo.`name`,t.report_person,t.report_date,t.`year`,t.`quarter`,if(t.`status`='1','未填报',if(t.`status`='2','未审核',if(t.`status`='3','未通过','已通过'))) 'status',t.total_one,t.total_two,t.total_three,t.total_four from t_travelagency_quarterly3 t left join t_travelagency_info travelagencyinfo on t.tid = travelagencyinfo.id where 1=1 ");
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
        if(quarter != null && quarter.length() != 0){
        	sql.append(" and t.`quarter` like '%"+quarter+"%'");
        }
        if(status != null && status.length() != 0){
        	sql.append(" and t.`status` like '%"+status+"%'");
        }
        sql.append(" UNION ALL select '合计','','','','','','' ,IFNULL(sum(t.total_one),0),IFNULL(sum(t.total_two),0),IFNULL(sum(t.total_three),0),IFNULL(sum(t.total_four),0)from t_travelagency_quarterly3 t left join t_travelagency_info travelagencyinfo on t.tid = travelagencyinfo.id where 1=1");
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
        if(quarter != null && quarter.length() != 0){
        	sql.append(" and t.`quarter` like '%"+quarter+"%'");
        }
        if(status != null && status.length() != 0){
        	sql.append(" and t.`status` like '%"+status+"%'");
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
		systemService.addLog("导出旅行社国内季报", Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);

	}
	
	/**
	 * 
	 * 检查入境季报是否填报
	 * @return
	 */
	public boolean isEntryFilled(){
		boolean tip = false;
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		// 获取当前年和季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if("0".equals(nowSeason)){
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}				
		String sql = "select id from t_travelagency_quarterly1 t where t.`year`='"+nowYear+"' and t.`quarter` = '"+nowSeason+"' and t.tid = '"+travelid+"'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if(list.size() != 0) tip = true;
		return tip;
	}
	
	public boolean isLeaveFilled(){
		boolean tip = false;
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		// 获取当前年和季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if("0".equals(nowSeason)){
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}				
		String sql = "select id from t_travelagency_quarterly2 t where t.`year`='"+nowYear+"' and t.`quarter` = '"+nowSeason+"' and t.tid = '"+travelid+"'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if(list.size() != 0) tip = true;
		return tip;
	}
	
	public boolean isInlandFilled(){
		boolean tip = false;
		//获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		//根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(Traveldata.class, "userId", userId);
		String travelid = traveldataList.get(0).getId();
		// 获取当前年和季度
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		// 对跨年的情况做处理
		if("0".equals(nowSeason)){
			nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			nowSeason = "4";
		}				
		String sql = "select id from t_travelagency_quarterly3 t where t.`year`='"+nowYear+"' and t.`quarter` = '"+nowSeason+"' and t.tid = '"+travelid+"'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if(list.size() != 0) tip = true;
		return tip;
	}
	
	/**
	 * 
	 * 撤回旅行社季报
	 * 
	 * @return 成功标志
	 */
	@RequestMapping(params = "revocation")
	@ResponseBody
	public String revocation(HttpServletRequest request,
			HttpServletResponse response) {
		String tip = "success";
		try {
			String entryId = request.getParameter("entryId");
			String leaveId = request.getParameter("leaveId");
			String inlandId = request.getParameter("inlandId");
			
			TravelQuarterIn travelQuarterIn =  systemService.get(TravelQuarterIn.class, entryId);
			TravelQuarterOut travelQuarterOut = systemService.get(TravelQuarterOut.class, leaveId);
			TravelQuarterInland travelQuarterInland = systemService.get(TravelQuarterInland.class, inlandId);
			travelQuarterIn.setStatus("4");
			travelQuarterOut.setStatus("4");
			travelQuarterInland.setStatus("4");
			systemService.saveOrUpdate(travelQuarterIn);
			systemService.saveOrUpdate(travelQuarterOut);
			systemService.saveOrUpdate(travelQuarterInland);
			systemService.addLog("撤回旅行社季报成功:"+travelQuarterIn.getTid(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "failed";
			systemService.addLog("撤回旅行社季报失败", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		return tip;
	}
}
