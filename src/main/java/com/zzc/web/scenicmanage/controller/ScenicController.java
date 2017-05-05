package com.zzc.web.scenicmanage.controller;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicDataSta;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.scenicmanage.entity.ScenicSpotProperties;
import com.zzc.web.scenicmanage.entity.ScenicSpotQuarter;
import com.zzc.web.scenicmanage.entity.ScenicSpotTicket;
import com.zzc.web.scenicmanage.service.ScenicService;
import com.zzc.web.sylyUtils.AutoAddUser;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.LogService;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.TravelAnnualFinance;
import com.zzc.web.travel.entity.Traveldata;
import com.zzc.webservice.ReadJson;
import com.zzc.webservice.travelagency.PublicInterfaceSoapProxy;

/**
 * @Title: Controller
 * @Description: 景区基本信息管理
 * @author 冯勇齐
 * @date 2016-11-23 21:50:55
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/scenicController")
public class ScenicController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(ScenicController.class);
	@Autowired
	private SystemService systemService;

	private ScenicService scenicService;

	@Autowired
	public void setScenicService(ScenicService scenicService) {
		this.scenicService = scenicService;
	}

	@Autowired
	private LogService logservice;

	/**
	 * 景区基本信息管理模块，需要有id和自动生成一个唯一的用户账号。 将这两个数据后期会同步到用户管理模块中去；
	 * 
	 * @return
	 */
	// 本方法跳转到save.jsp页面
	@RequestMapping(params = "add")
	public String scenicInfo() {
		return "scenic/save";
	}

	@RequestMapping(params = "addUser")
	public String scenicUserSave() {
		return "scenic/scenicUserSave";
	}

	//跳转到基本信息年报列表
	@RequestMapping(params = "tolist")
	public String user(HttpServletRequest request) {
		String roleName = ResourceUtil.getSessionUserName().getUserKey();
		request.setAttribute("roleName", roleName);
		return "/scenic/scenicList";
	}
	//跳转到基本信息列表
	@RequestMapping(params = "toAnnuallist")
	public String annualList(HttpServletRequest request) {
		return "/scenic/scenicAnnualList";
	}
	//用户登录列表
	@RequestMapping(params = "touserlist")
	public String userScenic(HttpServletRequest request) {

		return "/scenic/userScenicList";
	}

	/**
	 * 景区用户基本信息列表
	 * 
	 * @param
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "userdatagrid")
	public void monthGrid(ScenicDataSta scenicData, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String userid = ResourceUtil.getSessionUserName().getId();
		scenicData.setUserId(userid);
		CriteriaQuery cq = new CriteriaQuery(ScenicDataSta.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil
				.installHql(cq, scenicData);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);

		// }

	}

	/**
	 * 景区基本信息年报
	 * 
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(ScenicDataSta scenicData, HttpServletRequest request,ScenicData scenicData1,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ScenicDataSta.class, dataGrid);
		//区级管理员过滤
		String roleName = ResourceUtil.getSessionUserName().getUserKey();
		String area=ResourceUtil.getSessionUserName().getArea();
		if(roleName.equals("区级管理员")){
				scenicData.setArea(area);
		}
		getScenicLevel(request, cq);
		// 查询条件组装器
		if(scenicData.getName() != null && scenicData.getName() .length() != 0){
			String sql="this_ .name like '%"+scenicData.getName()+"%'";
			cq.add(Restrictions.sqlRestriction(sql));
		}
		scenicData.setName(null);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil
				.installHql(cq,scenicData);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 审核
	 */
	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,
			ScenicDataSta scenicDatasta, DataGrid dataGrid) {
		String scenicid = request.getParameter("id");
		request.setAttribute("scenicid", scenicid);
		scenicDatasta = this.systemService.get(ScenicDataSta.class, scenicid);
		if(scenicDatasta.getScenictitle()!=null){
			String [] scenicTitle=scenicDatasta.getScenictitle().split(",");
			List<String> titlelist=new ArrayList<String>();
			for(int i=0;i<scenicTitle.length;i++){
				titlelist.add(scenicTitle[i]);
			}
			request.setAttribute("titlelist", titlelist);
		}
	
		//景区机构性质的设置
		if (scenicDatasta.getOrgproperty() != null) {
			ScenicSpotProperties ssp3 = systemService.get(
					ScenicSpotProperties.class, scenicDatasta.getOrgproperty());
			ScenicSpotProperties ssp2 = systemService.get(
					ScenicSpotProperties.class, ssp3.getPraentId());
			ScenicSpotProperties ssp1 = systemService.get(
					ScenicSpotProperties.class, ssp2.getPraentId());
			request.setAttribute("ssp1", ssp1);
			request.setAttribute("ssp2", ssp2);
			request.setAttribute("ssp3", ssp3);
		}
		return new ModelAndView("scenic/scenicAnnualListAudit").addObject(
				"scenicdata", scenicDatasta);
	}
	/**
	 * 基本信息年报审核
	 * @param request
	 * @param scenicDatasta
	 * @param statusid
	 * @return
	 */
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			ScenicDataSta scenicDatasta, String statusid) {
		AjaxJson j = new AjaxJson();
		String scenicid = request.getParameter("id");
		request.setAttribute("scenicid", scenicid);
		String roleName=ResourceUtil.getSessionUserName().getUserKey();
		if(roleName.equals("超级管理员")){
			try {
				if (StringUtil.isNotEmpty(scenicid)) {
					scenicDatasta = systemService.getEntity(ScenicDataSta.class,
							scenicid);
									scenicDatasta.setStatus(Status.cityAudit);
									scenicDatasta.setProvinceSubmit("1");
									systemService.saveOrUpdate(scenicDatasta);
									ScenicData scenicData=ScenicDataSta.copy(scenicDatasta);
									systemService.saveOrUpdate(scenicData);
									systemService.addLog("市级管理员景区审核通过:"+ scenicDatasta.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

				
				}
			} catch (Exception e) {
				j.setMsg("通信错误");
				e.printStackTrace();
			}
		
		}else if(roleName.equals("区级管理员")){
			scenicDatasta = systemService.getEntity(ScenicDataSta.class,
					scenicid);
			scenicDatasta.setStatus(Status.areaAudit);
			systemService.saveOrUpdate(scenicDatasta);
			ScenicData data = ScenicDataSta.copy(scenicDatasta);
			data.setStatus(Status.areaAudit);
			j.setMsg("区级管理员审核通过");
			systemService.addLog("区级管理员景区审核通过:"+ scenicDatasta.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		return j;
	}

	// 审核结果不通过
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			ScenicDataSta scenicData, String statusid) {
		AjaxJson j = new AjaxJson();
		String scenicid = request.getParameter("id");
		request.setAttribute("scenicid", scenicid);
		if (StringUtil.isNotEmpty(scenicid)) {
			scenicData = systemService.getEntity(ScenicDataSta.class, scenicid);
			String userId=scenicData.getUserId();
			scenicData.setStatus(Status.notPass);// 管理员审核未通过
			//诚信积分
			if(scenicData.getScore()>0){
				scenicData.setScore(scenicData.getScore()-10);
				UserScoreUtils.scoreChange(-10,userId);
			}
			if(scenicData.getScore()<=0){
				scenicData.setScore(0);
			}
			
			systemService.saveOrUpdate(scenicData);
			systemService.addLog("景区审核不通过:"+ scenicData.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}

		return j;
	}
	 /**
	  * 年报的审核撤回
	  */
	 @RequestMapping(params = "back")
	 @ResponseBody
		public AjaxJson back(HttpServletRequest request) {
		 AjaxJson j=new AjaxJson();
			String scenicid = request.getParameter("scenicid");
			ScenicDataSta s = this.systemService.get(ScenicDataSta.class, scenicid);
			 s.setStatus(Status.cityAudit);
			 systemService.saveOrUpdate(s);
			 ScenicData m=ScenicDataSta.copy(s);
			 systemService.saveOrUpdate(m);
			j.setMsg("退回成功");
			systemService.addLog("景区年报撤回成功:"+ s.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			return j;
		}
	/**
	 * 
	 * 基本信息
	 */
	@RequestMapping(params = "annualdatagrid")
	public void annualdatagrid(ScenicData scenicData,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ScenicData.class, dataGrid);
		//区级管理员过滤
				String roleName = ResourceUtil.getSessionUserName().getUserKey();
				String area=ResourceUtil.getSessionUserName().getArea();
				if(roleName.equals("区级管理员")){
						scenicData.setArea(area);
				}
				getScenicLevel(request, cq);
		// 查询条件组装器
				if(scenicData.getName() != null && scenicData.getName() .length() != 0){
					String sql="this_ .name like '%"+scenicData.getName()+"%'";
					cq.add(Restrictions.sqlRestriction(sql));
				}
				scenicData.setName(null);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil
				.installHql(cq, scenicData);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 管理员景区录入
	 * 
	 * @param
	 * @param req
	 * @return
	 */
	// 本方法保存景区基本信息,本方法用户已存在的情况已经完成判断,但在前端页面不显示
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson saveScenic(HttpServletRequest req, ScenicDataSta scenicData) {
		AjaxJson j = new AjaxJson();
		String username = scenicData.getName();
		String realName = scenicData.getName();
		String roleId = GlobalParams.JINGQUSHUJUSHANGBAOYUAN;
		TSUser scenicUser = AutoAddUser.add(username, roleId, realName);
		scenicData.setUserId(scenicUser.getId());
		scenicData.setStatus(Status.notEdit);
		j.setMsg("景区用户:" + scenicUser.getUserName() + "添加成功");
		if (StringUtil.isNotEmpty(scenicData.getId())) {
			ScenicDataSta scenic = scenicService.get(ScenicDataSta.class,
					scenicData.getId());
			scenicService.saveOrUpdate(scenic);
			
		} else {
			ScenicDataSta scenics = systemService.findUniqueByProperty(
					ScenicDataSta.class, "name", scenicData.getName());
				scenicService.saveOrUpdate(scenicData);


		}
		systemService.addLog("添加景区用户成功:"+ scenicUser.getUserName() , Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		return j;
	}
	
	/**
	 * 景区基本用户录入
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(params = "saveUser")
	@ResponseBody
	public AjaxJson saveUserScenic(HttpServletRequest req,
			ScenicDataSta scenicData) {
		AjaxJson j = new AjaxJson();

		TSUser user = ResourceUtil.getSessionUserName();
		scenicData.setUserId(user.getId());
		scenicData.setStatus(Status.notEdit);
		systemService.saveOrUpdate(scenicData);

		return j;
	}

	/**
	 * 景区信息删除
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ScenicData scenicData, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		ScenicDataSta scenicData1 = null;
		scenicData1 = systemService.getEntity(ScenicDataSta.class,
				scenicData.getId());
		String userId= scenicData1.getUserId();
		systemService.addLog("删除景区:"+scenicData1.getName(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		if (scenicData1 != null) {
			systemService.delete(scenicData1);
		}
		scenicData = systemService.getEntity(ScenicData.class,
				scenicData.getId());
		if (scenicData != null) {
			systemService.delete(scenicData);
		}
		/*String code=scenicData.getCode();
		boolean flag=ReadJson.delScenic(code);
		if(flag){*/
			j.setMsg("景区删除成功");
			// 锁定账号
						AutoAddUser.lock(userId, req);
		/*}*/
		return j;
	}

	/**
	 * 景区基本信息详情jsp
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(params = "detialinfo")
	public ModelAndView detialinfo(HttpServletRequest request,
			ScenicData scenicData, DataGrid dataGrid) {

		String scenicid = request.getParameter("scenicid");
		request.setAttribute("scenicid", scenicid);
		ScenicData s = this.systemService.get(ScenicData.class, scenicid);
		if(s.getScenictitle()!=null){
			String [] scenicTitle=s.getScenictitle().split(",");
			List<String>titlelist =new ArrayList<String>();
			for(int i=0;i<scenicTitle.length;i++){
				titlelist.add(scenicTitle[i]);
			}
			request.setAttribute("titlelist", titlelist);
		}
		
	
		if (s.getOrgproperty() != null) {
			ScenicSpotProperties ssp3 = systemService.get(
					ScenicSpotProperties.class, s.getOrgproperty());
			ScenicSpotProperties ssp2 = systemService.get(
					ScenicSpotProperties.class, ssp3.getPraentId());
			ScenicSpotProperties ssp1 = systemService.get(
					ScenicSpotProperties.class, ssp2.getPraentId());
			request.setAttribute("ssp1", ssp1);
			request.setAttribute("ssp2", ssp2);
			request.setAttribute("ssp3", ssp3);
		}

		request.setAttribute("scenicdata", s);

		return new ModelAndView("scenic/scenicDataDetail");
	}
	/**
	 * 景区年报信息详情jsp
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "detialinfosta")
	public ModelAndView detialinfoSta(HttpServletRequest request,
			ScenicDataSta scenicDataSta, DataGrid dataGrid) {

		String scenicid = request.getParameter("scenicid");
		request.setAttribute("scenicid", scenicid);
		ScenicDataSta s = this.systemService.get(ScenicDataSta.class, scenicid);
		if(s.getScenictitle()!=null){
			String [] scenicTitle=s.getScenictitle().split(",");
			List<String> titlelist=new ArrayList<String>();
			for(int i=0;i<scenicTitle.length;i++){
				titlelist.add(scenicTitle[i]);
				
			}
			request.setAttribute("titlelist", titlelist);
		}
		
		
		if (s.getOrgproperty() != null) {
			ScenicSpotProperties ssp3 = systemService.get(
					ScenicSpotProperties.class, s.getOrgproperty());
			ScenicSpotProperties ssp2 = systemService.get(
					ScenicSpotProperties.class, ssp3.getPraentId());
			ScenicSpotProperties ssp1 = systemService.get(
					ScenicSpotProperties.class, ssp2.getPraentId());
			request.setAttribute("ssp1", ssp1);
			request.setAttribute("ssp2", ssp2);
			request.setAttribute("ssp3", ssp3);
		}

		request.setAttribute("scenicdata", s);

		return new ModelAndView("scenic/scenicDataDetail");
	}
	/**
	 * 景区年报审核意见 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "comments")
	public ModelAndView comments(HttpServletRequest request,
			ScenicDataSta scenicDataSta, DataGrid dataGrid) {

		String scenicid = request.getParameter("scenicid");
		ScenicDataSta s = this.systemService.get(ScenicDataSta.class, scenicid);

		request.setAttribute("scenicdata", s);

		return new ModelAndView("scenic/scenicComments");
	}
	/**
	 * 景区基本信息编辑jsp
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "scenicmodify")
	public ModelAndView scenicUpdate(HttpServletRequest request,
			ScenicDataSta scenicData, DataGrid dataGrid) {

		String scenicid = request.getParameter("scenicid");
		request.setAttribute("scenicid", scenicid);
		ScenicDataSta s = this.systemService.get(ScenicDataSta.class, scenicid);
		if(s.getScenictitle()!=null){
			String [] scenicTitle=s.getScenictitle().split(",");
			List<String> titlelist=new ArrayList<String>();
			for(int i=0;i<scenicTitle.length;i++){
				titlelist.add(scenicTitle[i]);
			}
			request.setAttribute("titlelist", titlelist);
		}
		if (s.getOrgproperty() != null) {
			ScenicSpotProperties ssp3 = systemService.get(
					ScenicSpotProperties.class, s.getOrgproperty());
			ScenicSpotProperties ssp2 = systemService.get(
					ScenicSpotProperties.class, ssp3.getPraentId());
			ScenicSpotProperties ssp1 = systemService.get(
					ScenicSpotProperties.class, ssp2.getPraentId());
			if (ssp1 == null||ssp1.getId()==0) {
				ssp1 = ssp2;
				ssp2 = ssp3;
			}
			request.setAttribute("ssp1", ssp1);
			request.setAttribute("ssp2", ssp2);
			request.setAttribute("ssp3", ssp3);
		}
		request.setAttribute("scenicdata", s);

		return new ModelAndView("scenic/scenicUpdate");
	}
	/**
	 * 判断是否是本年第一次填报
	 */
	@RequestMapping(params = "iffirst")
	@ResponseBody
	public String ifFirst(HttpServletRequest request,
			ScenicDataSta scenicData, DataGrid dataGrid) {
		String reported=null;
		String scenicid = request.getParameter("scenicid");
		request.setAttribute("scenicid", scenicid);
		ScenicDataSta s = this.systemService.get(ScenicDataSta.class, scenicid);
		//一年只能填报一次
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yy");
		if(s.getReportDate()!=null){
			if(Integer.valueOf(sdf.format(date))>=Integer.valueOf( sdf.format(s.getReportDate()))&&!s.getStatus().equals(Status.notPass)&&!s.getStatus().equals(Status.notEdit)){
			reported="notfirst";
			}       
		}
		return reported;
	}
	/**
	 * 基本信息年报填写
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "saveOrUpdate")
	@ResponseBody
	public AjaxJson saveOrUpdateScenic(HttpServletRequest request,
			ScenicDataSta scenicData) {
		AjaxJson j = new AjaxJson();
		String scenicid = request.getParameter("scenicid");
		String orgproperty = request.getParameter("third");
		if (orgproperty == null) {
			orgproperty = request.getParameter("second");
		}
		scenicData.setOrgproperty(Integer.parseInt(orgproperty));
		scenicData.setId(scenicid);
		String userId=ResourceUtil.getSessionUserName().getId();
	      Map<String, Object> roleMap = systemService.findOneForJdbc("select * from t_s_role t where t.id=(select tr.roleid from t_s_role_user tr where tr.userid =?)", userId);
	        String roleName = roleMap.get("rolename").toString();
			
		
		if(roleName.equals("超级管理员")){
			
				scenicData.setStatus(Status.cityAudit);//市级管理员审核成功
				scenicData.setProvinceSubmit("1");
				Date date=new Date();
				scenicData.setReportDate(date);
				systemService.saveOrUpdate(scenicData);			
				ScenicData scenicData1=ScenicDataSta.copy(scenicData);
				systemService.saveOrUpdate(scenicData1);
				j.setMsg("年报填报成功并且市级管理员审核成功");
				systemService.addLog("景区年报市级管理员代填报:"+ scenicData.getName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

				
		}else if(roleName.equals("区级管理员")) {
			scenicData.setStatus(Status.areaAudit);
			j.setMsg("年报填报成功并且通过区级管理员审核");
			Date date=new Date();
			scenicData.setReportDate(date);
			systemService.saveOrUpdate(scenicData);
			systemService.addLog("景区年报区级管理员代填报:"+ scenicData.getName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}else{
			scenicData.setStatus(Status.PendingSubmission);
			Date date=new Date();
			//诚信积分
		  	Calendar calendar=Calendar.getInstance();
			  if(ReportTimeCheck.annualTimeCheck(calendar.get(Calendar.YEAR-1))){//如果填报时间在应填报时间之前
				  scenicData.setScore(40);//增加40分
			  }else{
				  scenicData.setScore(20);
			  }
				  UserScoreUtils.scoreChange(scenicData.getScore());
			
			scenicData.setReportDate(date);
			
			
			systemService.saveOrUpdate(scenicData);
			j.setMsg("年报填报成功");
			systemService.addLog("景区年报填报:"+ scenicData.getName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}
		
		return j;
	}

	/**
	 * 机构性质
	 */
	@RequestMapping(params = "propertieslist")
	@ResponseBody
	public List<ScenicSpotProperties> properties(HttpServletRequest request,
			int parentid) {
		parentid = Integer.parseInt(request.getParameter("parentid"));
		List<ScenicSpotProperties> list = systemService.findHql(
				"from ScenicSpotProperties ssp where ssp.praentId=?", parentid);
		return list;

	}

	@RequestMapping(params = "propertieslist1")
	@ResponseBody
	public List<ScenicSpotProperties> properties1(HttpServletRequest request,
			int parentid) {
		parentid = Integer.parseInt(request.getParameter("parentid"));
		List<ScenicSpotProperties> list = systemService.findHql(
				"from ScenicSpotProperties ssp where ssp.praentId=?", parentid);
		return list;

	}
	/**
	 * 同步数据
	 * @param req
	 * @throws ParseException 
	 */
	@RequestMapping(params = "tongbu")
	@ResponseBody
	public AjaxJson saveScenic1(HttpServletRequest req) throws ParseException {
		AjaxJson j=new AjaxJson();
		List<String> list=ReadJson.instsOfProvince();
		try {
			for(int i=0;i<list.size();i++){
				String code=list.get(i);
				ScenicDataSta scenicData=ReadJson.findInstByCode(code);
				String realName = scenicData.getName();
				String roleId = GlobalParams.JINGQUSHUJUSHANGBAOYUAN;
				TSUser scenicUser = AutoAddUser.interfaceAdd(scenicData.getCode(), roleId, realName);
				scenicData.setUserId(scenicUser.getId());
				if (StringUtil.isNotEmpty(scenicData.getId())) {
					ScenicData scenic = scenicService.get(ScenicData.class,
							scenicData.getId());
					scenicService.saveOrUpdate(scenic);

				} else {
					ScenicDataSta scenics = systemService.findUniqueByProperty(
							ScenicDataSta.class, "code", scenicData.getCode());		 
					if (scenics == null) {
						if(scenicData.getArea().equals("460201")){
							scenicData.setArea("0");
						}else if(scenicData.getArea().equals("460202")){
							scenicData.setArea("2");
						}
						else if(scenicData.getArea().equals("460203")){
							scenicData.setArea("4");
						}
						else if(scenicData.getArea().equals("460204")){
							scenicData.setArea("3");
						}else if(scenicData.getArea().equals("460205")){
							scenicData.setArea("1");
						}else if(scenicData.getArea().equals("460200")){
							scenicData.setArea("5");
						}
						if(scenicData.getStatus().equals(Status.cityAudit)){
							scenicData.setProvinceSubmit("1");
						}
						scenicData.setBayType("0");
						scenicService.saveOrUpdate(scenicData);
						ScenicData ssta=ScenicDataSta.copy(scenicData);
						systemService.saveOrUpdate(ssta);
					
					} 
				}
			}
		
			j.setMsg("同步完成");
			systemService.addLog("景区年报同步成功:", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			j.setMsg("同步失败");
			systemService.addLog("景区年报同步失败:", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);		}
		
	
	
		return j;
	}	
	/**
	 * 景区等级
	 */
	public static void getScenicLevel(HttpServletRequest request,CriteriaQuery cq){
		String beginType=request.getParameter("level_begin");
		String endType=request.getParameter("level_end");
		if(beginType!=null&&endType!=null&&beginType.length()!=0&&endType.length()!=0){
		beginType=	beginType.toUpperCase();
		endType=	endType.toUpperCase();
			if(beginType.equals("A")){
				beginType="5";
			}else if(beginType.equals("AA")){
				beginType="4";
			}else if(beginType.equals("AAA")){
				beginType="3";
			}else if(beginType.equals("AAAA")){
				beginType="2";
			}else if(beginType.equals("AAAAA")){
				beginType="1";
			}
			if(endType.equals("A")){
				endType="5";
			}else if(endType.equals("AA")){
				endType="4";
			}else if(endType.equals("AAA")){
				endType="3";
			}else if(endType.equals("AAAA")){
				endType="2";
			}else if(endType.equals("AAAAA")){
				endType="1";
			}
			cq.add(Restrictions.sqlRestriction("this_ .level between '"+endType+"' and '"+beginType+"'"));
		}
	}
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "excelExport")
	public void exportSceniclInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
        String code = new String(request.getParameter("code").getBytes("iso8859-1"),"utf-8");
        String area = new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
        String bayType = new String(request.getParameter("bayType").getBytes("iso8859-1"),"utf-8");
        String beginLevel = new String(request.getParameter("level_begin").getBytes("iso8859-1"),"utf-8");
        String endLevel = new String(request.getParameter("level_end").getBytes("iso8859-1"),"utf-8");
        String status = new String(request.getParameter("status").getBytes("iso8859-1"),"utf-8");
        
        String excelName = "景区基本信息.xls";
        String modelPath = "/com/zzc/web/export/model/scenic/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("select t.name,t.code,if(t.area='0','市辖区',if(t.area='1','崖州区',if(t.area='2','海棠区',if(t.area='3','天涯区',if(t.area='4','吉阳区',if(t.area='5','三亚市','')))))),if(t.bay_type='0','市区',if(t.bay_type='3','三亚湾',if(t.bay_type='4','海棠湾',if(t.bay_type='1','亚龙湾',if(t.bay_type='2','大东海',''))))),t.address,t.zip_code,t.url,t.invest_unit,t.superior_unit,if(t.scenic_type='1','自然景观',if(t.scenic_type='2','历史文化',if(t.scenic_type='3','度假休闲',if(t.scenic_type='4','主题游乐',if(t.scenic_type='5','博物馆',if(t.scenic_type='6','乡村旅游',if(t.scenic_type='7','工业旅游',if(t.scenic_type='8','红色旅游',if(t.scenic_type='9','科技旅游','其他'))))))))),t.consult_phone,t.open_time,if(t.`level`='1','AAAAA',if(t.`level`='2','AAAA',if(t.`level`='3','AAA',if(t.`level`='4','AA',if(t.`level`='5','A','未评定'))))),t.day_limit,t.level_date,t.acreage,t.charge_name,t.charge_email,t.charge_phone,t.informant_name,t.informant_phone,t.informant_email,t.informant_qq,if(t.ticket_isOne='1','是','否'),if(t.org_property='2','行政单位',if(t.org_property='4','事业单位',if(t.org_property='7','国有',if(t.org_property='8','集体',if(t.org_property='9','股份合作',if(t.org_property='10','国有联营',if(t.org_property='11','集体联营',if(t.org_property='12','国有与集体联营',if(t.org_property='13','其他联营',if(t.org_property='14','国有独资公司',if(t.org_property='15','其他有限责任公司',if(t.org_property='16','股份有限公司',if(t.org_property='17','私营独资',if(t.org_property='18','私营合伙',if(t.org_property='19','私营有限责任公司',if(t.org_property='20','私营股份有限公司',if(t.org_property='23','与港澳台商合资经营',if(t.org_property='24','与港澳台商合作经营',if(t.org_property='25','港澳台商独资',if(t.org_property='26','港澳台商投资股份有限公司',if(t.org_property='28','中外合资经营',if(t.org_property='29','中外合作经营',if(t.org_property='30','外资企业',if(t.org_property='31','外商投资股份有限公司',if(t.org_property='33','部队','其他'))))))))))))))))))))))))) from t_scenicspot_info_sta t where 1=1 ");
        // 组装查询条件
        if(name !=null && name.length() != 0){
        	sql.append(" and t.name like '%"+name+"%'");
        }
        if(code !=null && code.length() != 0){
        	sql.append(" and t.code ='"+code+"'");
        }
        if(area !=null && area.length() != 0){
        	sql.append(" and t.area ='"+area+"'");
        }
        if(bayType !=null && bayType.length() != 0){
        	sql.append(" and t.bay_type ='"+bayType+"'");
        }
        if(beginLevel !=null && beginLevel.length() != 0&&endLevel!=null&&endLevel.length()!=0){
        	sql.append(" and t.level between '"+endLevel+"' and '"+beginLevel+"'");
        }
        if(status !=null && status.length() != 0){
        	sql.append(" and t.status ='"+status+"'");
        }
        
        ExportService emds = new ExportService(25); 
        
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
       
}

	////////////////////////////////////////同步数据////////////////////////////////////////////
	/**
	 * @param scenicdata
	 * @param travelid
	 * @return
	 */
	/*public String addOrUpdateTravelFinance(ScenicData scenicdata, String id){
		String tip = "200";
		//Name 景区名称
		String name=String.valueOf(scenicdata.getName());
		//address 地址
		String address=String.valueOf(scenicdata.getAddress());
		//post 邮编
		String post=String.valueOf(scenicdata.getZipcode());
		//nature_id 机构性质代码，参见一、e)i机构性质
		String natureId=String.valueOf(scenicdata.getOrgproperty());
		//website 网址
		String website=String.valueOf(scenicdata.getUrl());
		//consult_phone 咨询电话
		String consultPhone=String.valueOf(scenicdata.getConsultphone());
//		type_id 景区类型代码，参见一、e)ii景区类型
		String typeId=String.valueOf(scenicdata.getScenictype());
//		establish 开业时间
		String establish=String.valueOf(scenicdata.getOpentime());
//		invest 投资主体
		String invest=String.valueOf(scenicdata.getInvestunit());
//		higher_authority 上级主管单位
		String higherAuthority=String.valueOf(scenicdata.getSuperiorunit());
//		level_id 等级代码，参见一、e)iii景区等级
		String levelId=String.valueOf(scenicdata.getLevel());
//		level_date 等级公告时间
		String levelDate=String.valueOf(scenicdata.getLeveldate());
//		capacity 日接待最大容量（万人）
		String capacity=String.valueOf(scenicdata.getDaylimit());
//		area 面积（公顷）
		String area=String.valueOf(scenicdata.getAcreage());
//		duty 负责人姓名
		String duty=String.valueOf(scenicdata.getChargename());
//		duty_phone 负责人电话
		String dutyPhone=String.valueOf(scenicdata.getChargephone());
//		duty_email 负责人邮箱
		String dutyEmail=String.valueOf(scenicdata.getChargeemail());
//		contact 填报人姓名
		String contact=String.valueOf(scenicdata.getInformantname());
//		phone 填报人电话
		String phone=String.valueOf(scenicdata.getInformantphone());
//		email 填报人邮箱
		String email=String.valueOf(scenicdata.getInformantemail());
//		qq 填报人qq
		String qq=String.valueOf(scenicdata.getInformantqq());
//		term_type1 门票类型（淡旺季）一、e)iv门票类型（淡旺季）
		String termType=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getType());
//		price1 价格
		String price1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getPrice());
//		begin_month1 开始月份
		String beginMonth1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getBeginMonth());
//		begin_date1 开始日期
		String beginDate1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getBeginDate());
//		end_month1 结束月份
		String endMonth1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getEndMonth());
//		end_date1 结束日期
		String endDate1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getEndDate());
//		open_hour1 开门（时）
		String openHour1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getOpenHour());
//		open_minute1 开门（分）
		String openMinute1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getOpenMinute());
//		close_hour1 关门（时）
		String closeHour1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getCloseHour());
//		close_minute1 关门（分）
		String closeMinute1=String.valueOf(scenicdata.getScenicSpotTicket().get(0).getCloseMinute());
//		ticket_id 门票形式代码，参见一、e)iv门票类型（淡旺季）
	

		
		return tip;
	}
	*/
	
