package com.zzc.web.travel.controller;

import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
import com.zzc.core.util.oConvertUtils;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.tag.vo.datatable.SortDirection;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.sylyUtils.AutoAddUser;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.pojo.base.TSRoleUser;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.Traveldata;
import com.zzc.web.travel.entity.TraveldataAnnual;
import com.zzc.webservice.ServiceInstance;
import com.zzc.webservice.travelagency.GetBasicInfoResponseGetBasicInfoResult;
import com.zzc.webservice.travelagency.PublicInterfaceSoapProxy;

@Scope("prototype")
@Controller
@RequestMapping("/travelController")
public class TravelInfoController extends BaseController {

	@SuppressWarnings("unused")//取消特定警告(变量没有使用)
	private static final Logger logger = Logger
			.getLogger(TravelInfoController.class);//日志
	private String message = null;

	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 旅行社信息录入
	 * 
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson saveTravel(HttpServletRequest req, Traveldata traveldata) {
		AjaxJson j = new AjaxJson();
		String isUser = req.getParameter("isUser");
		
		if (traveldata.getId() == null || traveldata.getId().length() == 0) {
			traveldata.setCreateTime(new Date());
			String username = traveldata.getEnglishname();
			String roleId = GlobalParams.LVXINGSHE;
			String realName = traveldata.getName();
			TSUser user = AutoAddUser.add(username, roleId, realName);
			List<TSUser> userList = systemService.findByProperty(TSUser.class,
					"userName", user.getUserName());
			traveldata.setUserId(userList.get(0).getId());
			traveldata.setScore(40);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			traveldata.setFillDate(sdf.format(new Date()));
			systemService.save(traveldata);
			AjaxJson msg = new AjaxJson();
			msg.setMsg("新增用户:" + user.getUserName() + ",请牢记用户名!");
			
			// 新增审核表的数据
			TraveldataAnnual traveldataannual = new TraveldataAnnual();
			traveldataannual.setFillDate(traveldata.getFillDate());
			traveldataannual.setTravelInfoId(traveldata.getId());
			traveldataannual.setStatus("1");
			traveldataannual.setUserId(traveldata.getUserId());
			traveldataannual.setLicensenum(traveldata.getLicensenum());
			traveldataannual
					.setOrganizationnum(traveldata.getOrganizationnum());
			traveldataannual.setProvince(traveldata.getProvince());
			traveldataannual.setAccommodationStar(traveldata
					.getAccommodationStar());
			traveldataannual.setName(traveldata.getName());
			traveldataannual.setEnglishname(traveldata.getEnglishname());
			traveldataannual.setAdminnum(traveldata.getAdminnum());
			traveldataannual.setCorporate(traveldata.getCorporate());
			traveldataannual.setArea(traveldata.getArea());
			traveldataannual.setNumber(traveldata.getNumber());
			traveldataannual.setCity(traveldata.getCity());
			traveldataannual.setZonecode(traveldata.getZonecode());
			traveldataannual.setSceneSpotlevel(traveldata.getSceneSpotlevel());
			traveldataannual.setPhone(traveldata.getPhone());
			traveldataannual.setPhoneextension(traveldata.getPhoneextension());
			traveldataannual.setFax(traveldata.getFax());
			traveldataannual.setFaxextension(traveldata.getFaxextension());
			traveldataannual.setAddress(traveldata.getAddress());
			traveldataannual.setTravelAgencyType(traveldata
					.getTravelAgencyType());
			traveldataannual.setMobile(traveldata.getMobile());
			traveldataannual.setEmail(traveldata.getEmail());
			traveldataannual.setWeburl(traveldata.getWeburl());
			traveldataannual.setZipcode(traveldata.getZipcode());
			traveldataannual.setPrincipal(traveldata.getPrincipal());
			traveldataannual.setRegisterstyle(traveldata.getRegisterstyle());
			traveldataannual.setBusinessexit(traveldata.getBusinessexit());
			traveldataannual.setBusinessborder(traveldata.getBusinessborder());
			traveldataannual.setIsmember(traveldata.getIsmember());
			traveldataannual.setQq(traveldata.getQq());
			traveldataannual.setFiller(traveldata.getFiller());
			traveldataannual.setFillerTel(traveldata.getFillerTel());
			Calendar calendar = Calendar.getInstance();
			traveldataannual
					.setYear(String.valueOf(calendar.get(Calendar.YEAR) - 1));
			traveldataannual.setFillDate(sdf.format(new Date()));
			systemService.saveOrUpdate(traveldataannual);
			
	        systemService.addLog("新增旅行社用户:" + user.getUserName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			return msg;
		} else {

			TraveldataAnnual traveldataannual = new TraveldataAnnual();

			List<TraveldataAnnual> list = systemService.findByProperty(
					TraveldataAnnual.class, "userId", traveldata.getUserId());
			if (list != null && list.size() != 0) {
				traveldataannual = list.get(0);
			}
			// 审核表与正式表id一致
//			traveldataannual.setId(traveldata.getId());
			traveldataannual.setFillDate(traveldata.getFillDate());
			traveldataannual.setTravelInfoId(traveldata.getId());
			traveldataannual.setStatus("2");
			traveldataannual.setUserId(traveldata.getUserId());
			traveldataannual.setLicensenum(traveldata.getLicensenum());
			traveldataannual
					.setOrganizationnum(traveldata.getOrganizationnum());
			traveldataannual.setProvince(traveldata.getProvince());
			traveldataannual.setAccommodationStar(traveldata
					.getAccommodationStar());
			traveldataannual.setName(traveldata.getName());
			traveldataannual.setEnglishname(traveldata.getEnglishname());
			traveldataannual.setAdminnum(traveldata.getAdminnum());
			traveldataannual.setCorporate(traveldata.getCorporate());
			traveldataannual.setArea(traveldata.getArea());
			traveldataannual.setNumber(traveldata.getNumber());
			traveldataannual.setCity(traveldata.getCity());
			traveldataannual.setZonecode(traveldata.getZonecode());
			traveldataannual.setSceneSpotlevel(traveldata.getSceneSpotlevel());
			traveldataannual.setPhone(traveldata.getPhone());
			traveldataannual.setPhoneextension(traveldata.getPhoneextension());
			traveldataannual.setFax(traveldata.getFax());
			traveldataannual.setFaxextension(traveldata.getFaxextension());
			traveldataannual.setAddress(traveldata.getAddress());
			traveldataannual.setTravelAgencyType(traveldata
					.getTravelAgencyType());
			traveldataannual.setMobile(traveldata.getMobile());
			traveldataannual.setEmail(traveldata.getEmail());
			traveldataannual.setWeburl(traveldata.getWeburl());
			traveldataannual.setZipcode(traveldata.getZipcode());
			traveldataannual.setPrincipal(traveldata.getPrincipal());
			traveldataannual.setRegisterstyle(traveldata.getRegisterstyle());
			traveldataannual.setBusinessexit(traveldata.getBusinessexit());
			traveldataannual.setBusinessborder(traveldata.getBusinessborder());
			traveldataannual.setIsmember(traveldata.getIsmember());
			traveldataannual.setQq(traveldata.getQq());
			traveldataannual.setFiller(traveldata.getFiller());
			traveldataannual.setFillerTel(traveldata.getFillerTel());
			Calendar calendar = Calendar.getInstance();
			traveldataannual
					.setYear(String.valueOf(calendar.get(Calendar.YEAR) - 1));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			traveldataannual.setFillDate(sdf.format(new Date()));
			traveldata.setFillDate(sdf.format(new Date()));
			String sql = "select t.score from t_travelagency_info t where t.id=?";
			Map<String, Object> map = systemService.findOneForJdbc(sql,
					new Object[] { traveldata.getId() });
			int score = map.get("score") == null
					|| map.get("score").toString().length() == 0 ? 0 : Integer
					.parseInt(map.get("score").toString());
			// 判断是否为用户新建
			if ("1".equals(isUser) && score == 40) {
				// 检查填报时间是否超时
				boolean result = ReportTimeCheck.annualTimeCheck(calendar
						.get(Calendar.YEAR));
				// 未超时，加40
				if (result) {
					UserScoreUtils.scoreChange(40);
				} else { // 超时加20
					UserScoreUtils.scoreChange(20);
					traveldata.setScore(20);
					systemService.saveOrUpdate(traveldata);
				}
			}

			systemService.saveOrUpdate(traveldataannual);
			j.setMsg("提交成功!");
			
	        systemService.addLog("更新旅行社基本信息年报:" + traveldataannual.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		return j;
	}

	/**
	 * 旅行社年报代填报
	 * 
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "fillInstead")
	@ResponseBody
	public AjaxJson fillInstead(HttpServletRequest req, Traveldata traveldata) {
		AjaxJson j = new AjaxJson();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 查询是否存在年报，如果存在则替换掉
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		
		String sql = "select id from t_travelagency_infoannual t where t.year = ? and t.travelinfo_id = ?";
		Map<String, Object> map = systemService.findOneForJdbc(sql, new Object[]{year, traveldata.getId()});
		
		TraveldataAnnual traveldataannual = new TraveldataAnnual();
		if(map != null && map.get("id") != null) traveldataannual.setId(map.get("id").toString());
		traveldataannual.setFillDate(sdf.format(new Date()));
		traveldataannual.setTravelInfoId(traveldata.getId());
		traveldataannual.setStatus("1");
		traveldataannual.setUserId(traveldata.getUserId());
		traveldataannual.setLicensenum(traveldata.getLicensenum());
		traveldataannual.setOrganizationnum(traveldata.getOrganizationnum());
		traveldataannual.setProvince(traveldata.getProvince());
		traveldataannual
				.setAccommodationStar(traveldata.getAccommodationStar());
		traveldataannual.setName(traveldata.getName());
		traveldataannual.setEnglishname(traveldata.getEnglishname());
		traveldataannual.setAdminnum(traveldata.getAdminnum());
		traveldataannual.setCorporate(traveldata.getCorporate());
		traveldataannual.setArea(traveldata.getArea());
		traveldataannual.setNumber(traveldata.getNumber());
		traveldataannual.setCity(traveldata.getCity());
		traveldataannual.setZonecode(traveldata.getZonecode());
		traveldataannual.setSceneSpotlevel(traveldata.getSceneSpotlevel());
		traveldataannual.setPhone(traveldata.getPhone());
		traveldataannual.setPhoneextension(traveldata.getPhoneextension());
		traveldataannual.setFax(traveldata.getFax());
		traveldataannual.setFaxextension(traveldata.getFaxextension());
		traveldataannual.setAddress(traveldata.getAddress());
		traveldataannual.setTravelAgencyType(traveldata.getTravelAgencyType());
		traveldataannual.setMobile(traveldata.getMobile());
		traveldataannual.setEmail(traveldata.getEmail());
		traveldataannual.setWeburl(traveldata.getWeburl());
		traveldataannual.setZipcode(traveldata.getZipcode());
		traveldataannual.setPrincipal(traveldata.getPrincipal());
		traveldataannual.setRegisterstyle(traveldata.getRegisterstyle());
		traveldataannual.setBusinessexit(traveldata.getBusinessexit());
		traveldataannual.setBusinessborder(traveldata.getBusinessborder());
		traveldataannual.setIsmember(traveldata.getIsmember());
		traveldataannual.setQq(traveldata.getQq());
		traveldataannual.setFiller(traveldata.getFiller());
		traveldataannual.setFillerTel(traveldata.getFillerTel());
		traveldataannual.setYear(String.valueOf(calendar.get(Calendar.YEAR) - 1));
		try {
			systemService.saveOrUpdate(traveldataannual);
			systemService.saveOrUpdate(traveldata);
			j.setMsg("提交成功!");
			
	        systemService.addLog("代填报旅行社基本信息年报:" + traveldataannual.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("提交失败!");
		}
		return j;
	}

	// 旅行社基本信息列表，数据填充
	@RequestMapping(params = "datagrid")
	public void datagrid(Traveldata traveldata, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Traveldata.class, dataGrid);
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService
				.findOneForJdbc(
						"select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)",
						userId);
		cq.addOrder("createTime", SortDirection.desc);
		// 查询条件组装器
		if (traveldata.getLicensenum() != null
				&& traveldata.getLicensenum().length() != 0) {
			cq.add(Restrictions.like("licensenum",
					"%" + traveldata.getLicensenum() + "%"));
		}
		if (traveldata.getName() != null && traveldata.getName().length() != 0) {
			cq.add(Restrictions.like("name", "%" + traveldata.getName() + "%"));
		}
		if (traveldata.getAddress() != null
				&& traveldata.getAddress().length() != 0) {
			cq.add(Restrictions.like("address", "%" + traveldata.getAddress()
					+ "%"));
		}
		if (traveldata.getCorporate() != null
				&& traveldata.getCorporate().length() != 0) {
			cq.add(Restrictions.like("corporate",
					"%" + traveldata.getCorporate() + "%"));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new Traveldata());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	// 旅行社基本信息列表，数据填充
	@RequestMapping(params = "travelUserDatagrid")
	public void travelUserDatagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Traveldata.class, dataGrid);
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		if (userId != null && userId.length() != 0) {
			cq.add(Restrictions.eq("userId", userId));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new Traveldata());
		this.systemService.getDataGridReturn(cq, true);
		for (Object temp : dataGrid.getResults()) {
			Traveldata traveldata = (Traveldata) temp;
			List<TraveldataAnnual> list = systemService.findByProperty(TraveldataAnnual.class, "travelInfoId", traveldata.getId());
			if(list != null && list.size() != 0){
				traveldata.setTraveldataAnnual(list.get(0));
			}
		}
		TagUtil.datagrid(response, dataGrid);
	}

	// 旅行社基本信息列表，数据填充
	@RequestMapping(params = "auditdatagrid")
	public void auditDatagrid(Traveldata traveldata,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Traveldata.class, dataGrid);
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil
				.installHql(cq, traveldata);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	// 旅行社基本信息年报
	@RequestMapping(params = "annualauditdatagrid")
	public void annualauditdatagrid(Traveldata traveldata,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Traveldata.class, dataGrid);
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		Map<String, Object> userMap = systemService
				.findOneForJdbc(
						"select rolename from t_s_role where id=(select roleid from t_s_role_user where userid=?)",
						userId);

		// 查询条件组装器
		if (traveldata.getLicensenum() != null
				&& traveldata.getLicensenum().length() != 0) {
			cq.add(Restrictions.like("licensenum",
					"%" + traveldata.getLicensenum() + "%"));
		}

		if (traveldata.getName() != null && traveldata.getName().length() != 0) {
			cq.add(Restrictions.like("name", "%" + traveldata.getName() + "%"));
		}
		
		if (traveldata.getArea() != null && traveldata.getArea().length() != 0) {
			cq.add(Restrictions.like("area", "%" + traveldata.getArea() + "%"));
		}
		
		if (traveldata.getName() != null && traveldata.getName().length() != 0) {
			cq.add(Restrictions.like("name", "%" + traveldata.getName() + "%"));
		}
		
		if (traveldata.getTraveldataAnnual() != null && traveldata.getTraveldataAnnual().getStatus() != null && traveldata.getTraveldataAnnual().getStatus().length() != 0) {
			// 未填报情况
			if("3".equals(traveldata.getTraveldataAnnual().getStatus())){
				cq.add(Restrictions.sqlRestriction( "1=1 and not exists( select * from t_travelagency_infoannual annual where annual.travelinfo_id = this_.id) " ));
			}else{
				cq.add(Restrictions.sqlRestriction( "1=1 and exists( select * from t_travelagency_infoannual annual where annual.travelinfo_id = this_.id and annual.status = '"+ traveldata.getTraveldataAnnual().getStatus() +"') " ));
			}
		}

		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new Traveldata());
		this.systemService.getDataGridReturn(cq, true);
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
		for (Object obj : dataGrid.getResults()) {
			Traveldata data = (Traveldata) obj;
			TraveldataAnnual traveldataAnnual = new TraveldataAnnual();
			traveldataAnnual.setStatus("3");
			data.setTraveldataAnnual(traveldataAnnual);
			if (data.getTraveldataAnnualList() != null
					&& data.getTraveldataAnnualList().size() != 0) {
				for (int i = 0; i < data.getTraveldataAnnualList().size(); i++) {
					TraveldataAnnual temp = data.getTraveldataAnnualList().get(
							i);
					if (year.equals(temp.getYear())) {
						data.setTraveldataAnnual(temp);
						break;
					}
				}
			}

		}
		TagUtil.datagrid(response, dataGrid);
	}

	// 菜单管理中的跳转
	@RequestMapping(params = "tolist")
	public String user(HttpServletRequest request) {
		return "/travel/travelList";
	}

	// 菜单管理中的跳转
	@RequestMapping(params = "toauditlist")
	public String toAuditList(HttpServletRequest request) {
		return "/travel/travelAuditList";
	}

	// 菜单管理中的跳转
	@RequestMapping(params = "travelList")
	public String travelList(HttpServletRequest request) {
		return "/travelUser/travelList";
	}

	
	/**
	 * 
	 * @param request
	 * @return 旅行社基本信息年报表格页面
	 */
	@RequestMapping(params = "totravelAnnualAuditList")
	public String totravelAnnualAuditList(HttpServletRequest request) {
		return "/travel/travelAnnualAuditList";
	}

	/**
	 * 
	 * @param request
	 * @return 旅行社基本信息年报代填报页面
	 */
	@RequestMapping(params = "tofilltravelinfo")
	public String tofilltravelinfo(HttpServletRequest request) {
		String id = request.getParameter("id");
		Traveldata traveldata = systemService.getEntity(Traveldata.class, id);
		request.setAttribute("traveldata", traveldata);
		return "/travel/filltravelinfo";
	}

	/**
	 * 
	 * @param request
	 * @return 审核页面
	 */
	@RequestMapping(params = "toCheckInfo")
	public String toCheckInfo(HttpServletRequest request) {
		String id = request.getParameter("id");
		TraveldataAnnual traveldataannual = systemService.getEntity(
				TraveldataAnnual.class, id);
		request.setAttribute("traveldataannual", traveldataannual);
		return "/travel/annualTravelInfo";
	}

	// 本方法跳转到savetravel.jsp 添加页面
	@RequestMapping(params = "savetravel")
	public String scenicInfo() {
		return "/travel/savetravel";
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ModelAndView model, Traveldata traveldata,
			HttpServletRequest req, HttpSession session) {
		model.setViewName("travel/updatetravel");
		List<Traveldata> departList = new ArrayList<Traveldata>();
		String departid = oConvertUtils.getString(req.getParameter("departid"));
		if (!StringUtil.isEmpty(departid)) {
			departList.add((Traveldata) systemService.getEntity(
					Traveldata.class, departid));
		} else {
			departList.addAll((List) systemService.getList(Traveldata.class));
		}
		req.setAttribute("departList", departList);
		List<String> orgIdList = new ArrayList<String>();
		if (StringUtil.isNotEmpty(traveldata.getId())) {
			traveldata = systemService.getEntity(Traveldata.class,
					traveldata.getId());

			// req.setAttribute("traveldata", traveldata);
			model.addObject("traveldata", traveldata);
			// session.setAttribute("traveldata",traveldata);
		}
		return model;
	}

	@RequestMapping(params = "checkTravelInfoPage")
	public ModelAndView checkTravelInfoPage(ModelAndView model,
			HttpServletRequest request, HttpSession session) {
		model.setViewName("travel/annualTravelInfo");
		String id = request.getParameter("id");
		TraveldataAnnual traveldataannual = systemService.getEntity(
				TraveldataAnnual.class, id);
		model.addObject("traveldataannual", traveldataannual);
		return model;
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Traveldata traveldata, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			traveldata = systemService.getEntity(Traveldata.class,
					traveldata.getId());
			TSUser user = systemService.getEntity(TSUser.class,
					traveldata.getUserId());
			String userId = user.getId();
			try {
				List<TSRoleUser> roleUser = systemService.findByProperty(
						TSRoleUser.class, "TSUser.id", user.getId());
				if (roleUser.size() > 0) {
					// 删除用户时先删除用户和角色关系表
					List<TSRoleUser> roleUserList = systemService
							.findByProperty(TSRoleUser.class, "TSUser.id",
									user.getId());
					if (roleUserList.size() >= 1) {
						for (TSRoleUser tRoleUser : roleUserList) {
							systemService.delete(tRoleUser);
						}
					}
					systemService.executeSql(
							"delete from t_s_user_org where user_id=?",
							user.getId()); // 删除 用户-机构 数据
					systemService.delete(user);
				} else {
					systemService.delete(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			systemService.addLog("删除旅行社信息成功:" + traveldata.getName(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			systemService.delete(traveldata);
			message = "删除旅行社信息成功";
			// 锁定账号
			try {
				AutoAddUser.lock(userId, req);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (Exception e) {
			message = "删除旅行社信息失败";
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 
	 * 旅行社基本信息年报审批
	 * 
	 * @author Fp
	 * @param traveldata
	 * @param req
	 * @return 旅行社年报审批结果
	 */
	@RequestMapping(params = "checkAnnualInfo")
	@ResponseBody
	public AjaxJson checkAnnualInfo(HttpServletRequest request) {
		String id = request.getParameter("id");
		String option = request.getParameter("option");
		TraveldataAnnual traveldataannual = systemService.getEntity(
				TraveldataAnnual.class, id);
		AjaxJson j = new AjaxJson();
		String message = "操作成功!";
		// 非空校验
		if (traveldataannual == null) {
			message = "操作失败,查询信息失败!";
			j.setMsg(message);
			return j;
		}
		try {
			if ("0".equals(option)) {// 驳回
				traveldataannual.setStatus("0");
				// 驳回则进行扣分
				Traveldata traveldata = systemService.get(Traveldata.class,
						traveldataannual.getTravelInfoId());
				Integer score = traveldata.getScore();
				if (score > 0)
					traveldata.setScore(score - 10);
				UserScoreUtils.scoreChange(-10, traveldata.getUserId());
				systemService.saveOrUpdate(traveldata);
				systemService.saveOrUpdate(traveldataannual);
			} else {// 通过
				traveldataannual.setStatus("1");
				Traveldata traveldata = new Traveldata();

				traveldata.setId(traveldataannual.getTravelInfoId());
				traveldata.setUserId(traveldataannual.getUserId());
				traveldata.setLicensenum(traveldataannual.getLicensenum());
				traveldata.setOrganizationnum(traveldataannual
						.getOrganizationnum());
				traveldata.setProvince(traveldataannual.getProvince());
				traveldata.setAccommodationStar(traveldataannual
						.getAccommodationStar());
				traveldata.setName(traveldataannual.getName());
				traveldata.setEnglishname(traveldataannual.getEnglishname());
				traveldata.setAdminnum(traveldataannual.getAdminnum());
				traveldata.setCorporate(traveldataannual.getCorporate());
				traveldata.setArea(traveldataannual.getArea());
				traveldata.setNumber(traveldataannual.getNumber());
				traveldata.setCity(traveldataannual.getCity());
				traveldata.setZonecode(traveldataannual.getZonecode());
				traveldata.setSceneSpotlevel(traveldataannual
						.getSceneSpotlevel());
				traveldata.setPhone(traveldataannual.getPhone());
				traveldata.setPhoneextension(traveldataannual
						.getPhoneextension());
				traveldata.setFax(traveldataannual.getFax());
				traveldata.setFaxextension(traveldataannual.getFaxextension());
				traveldata.setAddress(traveldataannual.getAddress());
				traveldata.setTravelAgencyType(traveldataannual
						.getTravelAgencyType());
				traveldata.setMobile(traveldataannual.getMobile());
				traveldata.setEmail(traveldataannual.getEmail());
				traveldata.setWeburl(traveldataannual.getWeburl());
				traveldata.setZipcode(traveldataannual.getZipcode());
				traveldata.setPrincipal(traveldataannual.getPrincipal());
				traveldata
						.setRegisterstyle(traveldataannual.getRegisterstyle());
				traveldata.setBusinessexit(traveldataannual.getBusinessexit());
				traveldata.setBusinessborder(traveldataannual
						.getBusinessborder());
				traveldata.setIsmember(traveldataannual.getIsmember());
				traveldata.setQq(traveldataannual.getQq());
				traveldata.setFiller(traveldataannual.getFiller());
				traveldata.setFillerTel(traveldataannual.getFillerTel());

				systemService.saveOrUpdate(traveldata);
				systemService.saveOrUpdate(traveldataannual);
				
				systemService.addLog("审批旅行社信息成功:" + traveldata.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			message = "操作失败!";
			e.printStackTrace();
		}

		j.setMsg(message);
		return j;
	}

	// /////////////////////////////////////用户层///////////////////////////////////

	@RequestMapping(params = "travelInfoManageOfUser")
	public ModelAndView travelInfoManageOfUser(HttpServletRequest request) {
		Traveldata traveldata = null;
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		// 根据用户ID查找旅行社信息
		List<Traveldata> traveldataList = systemService.findByProperty(
				Traveldata.class, "userId", userId);
		if (traveldataList == null || traveldataList.size() == 0) {
			traveldata = new Traveldata();
		} else {
			traveldata = traveldataList.get(0);
		}

		List<TraveldataAnnual> traveldataAnnualList = traveldata
				.getTraveldataAnnualList();
		if (traveldataAnnualList != null && traveldataAnnualList.size() != 0) {
			Calendar calendar = Calendar.getInstance();
			String year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			for (int i = 0; i < traveldataAnnualList.size(); i++) {
				TraveldataAnnual temp = traveldataAnnualList.get(i);
				if (year.equals(temp.getYear())) {
					switch (temp.getStatus()) {
					case "0":
						request.setAttribute("status", "市级审核未通过");
						break;
					case "1":
						request.setAttribute("status", "市级审核通过");
						break;
					case "2":
						request.setAttribute("status", "市级待审核");
						break;
					case "3":
						request.setAttribute("status", "市级未填报");
						break;
					case "4":
						request.setAttribute("status", "省级待审核");
						break;
					case "5":
						request.setAttribute("status", "省级审核未通过");
						request.setAttribute("countryContent", temp.getGuo());
						break;
					case "6":
						request.setAttribute("status", "省级审核通过");
						request.setAttribute("countryContent", temp.getGuo());
						break;
					default:
						request.setAttribute("status", "市级未填报");
						break;
					}
					if ("0".equals(temp.getStatus())
							|| "5".equals(temp.getStatus())
							|| "3".equals(temp.getStatus())) {

					} else {
						request.setAttribute("isFilled", "1");
						break;
					}
				}
			}
		} else {
			request.setAttribute("status", "市级未填报");
		}

		request.setAttribute("traveldata", traveldata);
		return new ModelAndView("/travelUser/updatetravel");
	}

	/**
	 * 
	 * 撤回旅行社基本信息
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
			TraveldataAnnual traveldataAnnual = systemService.get(
					TraveldataAnnual.class, id);
			traveldataAnnual.setStatus("1");
			systemService.saveOrUpdate(traveldataAnnual);
			systemService.addLog("撤回旅行社信息年报成功:" + traveldataAnnual.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "failed";
		}
		return tip;
	}
	
	@RequestMapping(params = "countryContent")
	public ModelAndView countryContent(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		TraveldataAnnual traveldataAnnual = systemService.get(TraveldataAnnual.class, id);
		String status = traveldataAnnual == null || traveldataAnnual.getStatus() == null ? "" : traveldataAnnual.getStatus();
		switch (status) {
		case "0":
			status = "市级审核未通过";
			break;
		case "1":
			status = "市级审核通过";
			break;
		case "2":
			status = "市级待审核";
			break;
		case "3":
			status = "市级未填报";
			break;
		case "4":
			status = "省级待审核";
			break;
		case "5":
			status = "省级审核未通过";
			break;
		case "6":
			status = "省级审核通过";
			break;
		}
		Map<String, String> modelMap = new HashMap<String, String>();
		modelMap.put("issueStatus", status);
		modelMap.put("countryContent", traveldataAnnual == null ? "" : traveldataAnnual.getGuo());
		return new ModelAndView("/travel/countryContent").addAllObjects(modelMap);
	}

	// /////////////////////////////////调用webservice////////////////////////////
	public String addOrUpdateTravelInfo(Traveldata traveldata) {
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = ServiceInstance
				.getTravelAgencyService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tip = "";
		String code = traveldata.getOrganizationnum();
		String tableDate = sdf.format(new Date());
		String unitName = traveldata.getName();
		String delegate = traveldata.getCorporate();
		String userName = traveldata.getLicensenum();
		String unitMaster = traveldata.getPrincipal();
		String filler = traveldata.getFiller();
		String fillerTel = traveldata.getFillerTel();
		String adessr = traveldata.getAddress();
		String province = traveldata.getProvince();
		String city = GlobalParams.areaID;
		String postcode = traveldata.getZipcode();
		String regionalismCode = traveldata.getAdminnum();
		String mobile = traveldata.getMobile();
		String tel = traveldata.getPhone();
		String telNo = traveldata.getPhoneextension();
		String fax = traveldata.getFax();
		String faxNo = traveldata.getFaxextension();
		String webSite = traveldata.getWeburl();
		String email = traveldata.getEmail();
		String registrationType = traveldata.getRegisterstyle();
		String unitType = traveldata.getIsmember();
		String accommodationStar = traveldata.getAccommodationStar();
		String travelAgencyType = traveldata.getTravelAgencyType();
		String sceneSpotLevel = traveldata.getSceneSpotlevel();
		String outBoundTourism = traveldata.getBusinessexit();
		String borderTour = traveldata.getBusinessexit();
		String englishName = traveldata.getEnglishname();
		String QQ = traveldata.getQq();
		String areaID = GlobalParams.areaID;
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		try {
			tip = travelAgencyService.insertIntoBasic(areaID, code, tableDate,
					unitName, delegate, userName, unitMaster, filler,
					fillerTel, adessr, province, city, postcode,
					regionalismCode, mobile, tel, telNo, fax, faxNo, webSite,
					email, registrationType, unitType, accommodationStar,
					travelAgencyType, sceneSpotLevel, outBoundTourism,
					borderTour, englishName, QQ, verificationCode);
		} catch (RemoteException e) {
			e.printStackTrace();
			tip = "通信异常";
		}
		return "添加成功".equals(tip) ? "200" : tip;
	}

	@RequestMapping(params = "syncTravelInfo")
	public void syncTravelInfo(HttpServletRequest request) {
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = ServiceInstance
				.getTravelAgencyService();
		GetBasicInfoResponseGetBasicInfoResult rs;
		try {
			rs = travelAgencyService.getBasicInfo("002008013",
					"sql*lyj_baiwang@2017");
			Document doc = DocumentHelper.parseText(rs.get_any()[1].toString());
			Element root = doc.getRootElement();
			List<Element> nodes = root.element("NewDataSet").elements();
			List<Traveldata> traveldataList = new ArrayList<Traveldata>();
			for (int i = 0; i < nodes.size(); i++) {
				Element element = (Element) nodes.get(i);
				Traveldata traveldata = new Traveldata();
				System.out.println("组织机构代码:"
						+ element.element("Code").getTextTrim());
				System.out.println("工商登记注册时间:"
						+ element.element("TableDate").getTextTrim());
				System.out.println("单位名称:"
						+ element.element("UnitName").getTextTrim());
				System.out.println("法定代表人:"
						+ element.element("Delegate").getTextTrim());
				System.out.println("用户名:"
						+ element.element("UserName").getTextTrim());
				System.out.println("单位负责人:"
						+ element.element("UnitMaster").getTextTrim());
				System.out.println("填表人:"
						+ element.element("Filler").getTextTrim());
				System.out.println("填表人电话:"
						+ element.element("FillerTel").getTextTrim());
				System.out.println("单位所在地:"
						+ element.element("Adessr").getTextTrim());
				System.out.println("统一社会信用代码:"
						+ element.element("Province").getTextTrim());
				System.out.println("所属地区:"
						+ element.element("City").getTextTrim());
				System.out.println("邮政编码:"
						+ element.element("Postcode").getTextTrim());
				System.out.println("行政区划码:"
						+ element.element("RegionalismCode").getTextTrim());
				System.out.println("电话号码:"
						+ element.element("mobile").getTextTrim());
				System.out.println("电话分机号码:"
						+ element.element("Tel").getTextTrim());
				System.out.println("移动电话:"
						+ element.element("TelNo").getTextTrim());
				System.out.println("传真号码:"
						+ element.element("Fax").getTextTrim());
				System.out.println("传真分机号码:"
						+ element.element("FaxNo").getTextTrim());
				System.out.println("互联网网址:"
						+ element.element("WebSite").getTextTrim());
				System.out.println("电子邮件信箱:"
						+ element.element("Email").getTextTrim());
				System.out.println("企业登记注册类型:"
						+ element.element("RegistrationType").getTextTrim());
				System.out.println("是否旅行社协会会员:"
						+ element.element("UnitType").getTextTrim());
				System.out.println("批准文号:"
						+ element.element("AccommodationStar").getTextTrim());
				System.out.println("旅行社经营场所:"
						+ element.element("TravelAgencyType").getTextTrim());
				System.out.println("旅行社等级:"
						+ element.element("SceneSpotLevel").getTextTrim());
				System.out.println("是否经营出境游:"
						+ element.element("OutBoundTourism").getTextTrim());
				System.out.println("是否经营边境游:"
						+ element.element("BorderTour").getTextTrim());
				System.out.println("英文名称:"
						+ element.element("EnglishName").getTextTrim());
				System.out
						.println("QQ号:" + element.element("QQ").getTextTrim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出旅行社基本信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportTravelInfo")
	public void exportTravelInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取参数
		String licensenum = request.getParameter("licensenum");
		String name = new String(request.getParameter("name").getBytes(
				"iso8859-1"), "utf-8");
		String address = new String(request.getParameter("address").getBytes(
				"iso8859-1"), "utf-8");
		String corporate = new String(request.getParameter("corporate")
				.getBytes("iso8859-1"), "utf-8");
		String phone = request.getParameter("phone");

		String excelName = "旅行社基本信息.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"
				+ excelName;
		StringBuffer sql = new StringBuffer();
		sql.append("select t.licensenum,t.`name`,t.address,t.corporate,t.phone from t_travelagency_info t where 1=1 ");
		// 组装查询条件
		if (licensenum != null && licensenum.length() != 0) {
			sql.append(" and t.licensenum like '%" + licensenum + "%'");
		}
		if (name != null && name.length() != 0) {
			sql.append(" and t.`name` like '%" + name + "%'");
		}
		if (address != null && address.length() != 0) {
			sql.append(" and t.address like '%" + address + "%'");
		}
		if (corporate != null && corporate.length() != 0) {
			sql.append(" and t.corporate like '%" + corporate + "%'");
		}
		if (phone != null && phone.length() != 0) {
			sql.append(" and t.phone like '%" + phone + "%'");
		}

		ExportService emds = new ExportService(5);

		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
		} catch (Exception e) {

		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");
		
		systemService.addLog("导出旅行社信息", Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);

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
