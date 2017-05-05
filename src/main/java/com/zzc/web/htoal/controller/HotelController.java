package com.zzc.web.htoal.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.HotelmanageSta;
import com.zzc.web.htoal.service.Hotelservice;
import com.zzc.web.sylyUtils.AutoAddUser;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.sylyUtils.HotelGrade;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

/**
 * 
 * 
 * @date: 2016年11月22日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelController.java
 * @Version: 1.0
 * @About: 酒店信息的表现层
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/hotelController")
public class HotelController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(HotelController.class);
	private String message = null;
	private SystemService systemService;
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

	/**
	 * 
	 * @date: 2016年12月21日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:管理员添加酒店页面
	 */
	@RequestMapping(params = "addUser")
	public String addUser(HttpServletRequest request) {
		return "system/hotel/addHotelUser";
	}

	/**
	 * 酒店基本信息年报列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "hotel")
	public String hotel(HttpServletRequest request) {

		return "system/hotel/hotelLists";
	}

	/**
	 * 酒店信息列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "hotelList")
	public String hotelList(HttpServletRequest request) {
		return "system/hotel/hotelList";
	}
	
	/**
	 * 客户端非星级酒店信息列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "userNotStarHotelList")
	public String userNotStarHotelList(HttpServletRequest request) {
		return "system/hotelClient/notStarHotelList";
	}
	
	/**
	 * 客户端星级酒店信息列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "starHotelList")
	public String starHotelList(HttpServletRequest request) {
		return "system/hotelClient/starHotelList";
	}

	/**
	 * 酒店年报数据
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "datagrid")
	public void datagrid(Hotelmanage hotelmanage, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Hotelmanage.class, dataGrid);
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
		if (hotelmanage.getUnitname() != null
				&& hotelmanage.getUnitname().length() != 0) {
			cq.add(Restrictions.like("unitname",
					"%" + hotelmanage.getUnitname() + "%"));
		}
		if (hotelmanage.getHousegrade() != null
				&& hotelmanage.getHousegrade().length() != 0) {
			cq.add(Restrictions.eq("housegrade", hotelmanage.getHousegrade()));
		}
		if (hotelmanage.getCounty() != null
				&& hotelmanage.getCounty().length() != 0) {
			cq.add(Restrictions.eq("county", hotelmanage.getCounty()));
		}
		if (hotelmanage.getBay() != null && hotelmanage.getBay().length() != 0) {
			cq.add(Restrictions.eq("bay", hotelmanage.getBay()));
		}
		if (hotelmanage.getOnBuinessSeason() != null
				&& hotelmanage.getOnBuinessSeason().length() != 0) {
			cq.add(Restrictions.eq("onBuinessSeason",
					hotelmanage.getOnBuinessSeason()));
		}
		if (hotelmanage.getOnBuinessYear() != null
				&& hotelmanage.getOnBuinessYear().length() != 0) {
			cq.add(Restrictions.eq("onBuinessYear",
					hotelmanage.getOnBuinessYear()));
		}
		if (hotelmanage.getDistrictStatus() != null
				&& hotelmanage.getDistrictStatus().length() != 0) {
			cq.add(Restrictions.eq("districtStatus",
					hotelmanage.getDistrictStatus()));
		}
		if (hotelmanage.getStatus() != null
				&& hotelmanage.getStatus().length() != 0) {
			cq.add(Restrictions.eq("status", hotelmanage.getStatus()));
		}
		if (hotelmanage.getProvinceStatus() != null
				&& hotelmanage.getProvinceStatus().length() != 0) {
			cq.add(Restrictions.eq("provinceStatus",
					hotelmanage.getProvinceStatus()));
		}
		if (hotelmanage.getCountryStatus() != null
				&& hotelmanage.getCountryStatus().length() != 0) {
			cq.add(Restrictions.eq("countryStatus",
					hotelmanage.getCountryStatus()));
		}
		cq.add(Restrictions.sqlRestriction(" 1=1 order by str_to_date(fill_date,'%Y-%m-%d %H:%i:%s') desc"));

		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new Hotelmanage());

		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 酒店基本信息数据
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "datagridsta")
	public void datagridsta(HotelmanageSta hotelmanage,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HotelmanageSta.class, dataGrid);
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
		if (hotelmanage.getUnitname() != null
				&& hotelmanage.getUnitname().length() != 0) {
			cq.add(Restrictions.like("unitname",
					"%" + hotelmanage.getUnitname() + "%"));
		}
		if (hotelmanage.getHousegrade() != null
				&& hotelmanage.getHousegrade().length() != 0) {
			cq.add(Restrictions.eq("housegrade", hotelmanage.getHousegrade()));
		}
		if (hotelmanage.getCounty() != null
				&& hotelmanage.getCounty().length() != 0) {
			cq.add(Restrictions.eq("county", hotelmanage.getCounty()));
		}
		if (hotelmanage.getBay() != null && hotelmanage.getBay().length() != 0) {
			cq.add(Restrictions.eq("bay", hotelmanage.getBay()));
		}
		if (hotelmanage.getOnBuinessSeason() != null
				&& hotelmanage.getOnBuinessSeason().length() != 0) {
			cq.add(Restrictions.eq("onBuinessSeason",
					hotelmanage.getOnBuinessSeason()));
		}
		if (hotelmanage.getOnBuinessYear() != null
				&& hotelmanage.getOnBuinessYear().length() != 0) {
			cq.add(Restrictions.eq("onBuinessYear",
					hotelmanage.getOnBuinessYear()));
		}
		if (hotelmanage.getDistrictStatus() != null
				&& hotelmanage.getDistrictStatus().length() != 0) {
			cq.add(Restrictions.eq("districtStatus",
					hotelmanage.getDistrictStatus()));
		}
		if (hotelmanage.getStatus() != null
				&& hotelmanage.getStatus().length() != 0) {
			cq.add(Restrictions.eq("status", hotelmanage.getStatus()));
		}
		if (hotelmanage.getProvinceStatus() != null
				&& hotelmanage.getProvinceStatus().length() != 0) {
			cq.add(Restrictions.eq("provinceStatus",
					hotelmanage.getProvinceStatus()));
		}
		if (hotelmanage.getCountryStatus() != null
				&& hotelmanage.getCountryStatus().length() != 0) {
			cq.add(Restrictions.eq("countryStatus",
					hotelmanage.getCountryStatus()));
		}
		cq.add(Restrictions.sqlRestriction(" 1=1 order by str_to_date(fill_date,'%Y-%m-%d %H:%i:%s') desc"));
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new HotelmanageSta());

		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 客户端非星级酒店信息年报列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "notStarDatagridSta")
	public void notStarDatagridSta(HotelmanageSta hotelmanage,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HotelmanageSta.class, dataGrid);
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		if (userId != null && userId.length() != 0) {
			cq.add(Restrictions.eq("id",userId));
		}
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,new HotelmanageSta());
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 客户端星级酒店信息年报列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "starDatagridSta")
	public void starDatagridSta(HotelmanageSta hotelmanage,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HotelmanageSta.class, dataGrid);
		// 获取当前登录用户ID
		String userId = ResourceUtil.getSessionUserName().getId();
		if (userId != null && userId.length() != 0) {
			cq.add(Restrictions.eq("id",userId));
		}
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,new HotelmanageSta());
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	// 跳转到save页面
	/**
	 * 星级酒店用户登录不全信息页面
	 * 
	 * @param req
	 * @param hotelmanage
	 * @return
	 */
	@RequestMapping(params = "add")
	public String hotelInfo(HttpServletRequest req) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		HotelmanageSta hm = this.systemService.getEntity(HotelmanageSta.class,
				hotelId);
		req.setAttribute("hotelmanage", hm);
		return "system/hotel/saveHotel";
	}

	/**
	 * 管理员 编辑酒店页面
	 * 
	 * @param req
	 * @param hotelmanage
	 * @return
	 */
	@RequestMapping(params = "adds")
	public String hotelInfos(HttpServletRequest req, HotelmanageSta hotelmanage) {
		if (StringUtil.isNotEmpty(hotelmanage.getId())) {
			hotelmanage = this.systemService.getEntity(HotelmanageSta.class,
					hotelmanage.getId());

		}
		req.setAttribute("hotelmsg", hotelmanage);
		return "system/hotel/editHotel";
	}

	@RequestMapping(params = "rollbackEdit")
	public String rollbackEdit(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		HotelmanageSta hotelmanage = this.systemService.getEntity(
				HotelmanageSta.class, id);
		request.setAttribute("hotelmsg", hotelmanage);
		return "system/hotel/editHotel";
	}

	/**
	 * 
	 * @date: 2016年12月20日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:非星级酒店用户登录修改信息页面
	 */
	@RequestMapping(params = "addf")
	public String hotelInfof(HttpServletRequest req) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		HotelmanageSta hm = this.systemService.getEntity(HotelmanageSta.class,
				hotelId);
		req.setAttribute("hotelmanage", hm);
		return "system/hotel/saveHotelf";
	}

	/**
	 * 
	 * @date: 2016年12月21日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com.com
	 * @param:
	 * @return:管理员增加酒店用户 以及同步酒店基本信息
	 */
	@RequestMapping(params = "addhoteluser")
	@ResponseBody
	public AjaxJson addhotelUser(HotelmanageSta hotelmanage,
			HttpServletRequest req) {

		AjaxJson j = new AjaxJson();
		// 编码自动生成
		String county = hotelmanage.getCounty();
		// 本区域内酒店个数
		Long countyNum = systemService
				.getCountForJdbc("select count(1) from t_hotelmanage_sta  where w_county= '"
						+ county + "'");
		// 区级编码替换
		String countynum = null;
		if (county.equals("0"))
			countynum = "460201";
		else if (county.equals("1"))
			countynum = "460205";
		else if (county.equals("2"))
			countynum = "460202";
		else if (county.equals("3"))
			countynum = "460204";
		else if (county.equals("4"))
			countynum = "460203";
		hotelmanage.setUsername("B1" + countynum
				+ String.format("%03d", countyNum + 1));
		// hotelmanage.setOrganizationNum(code);
		String username = hotelmanage.getUsername();
		String realname = hotelmanage.getUnitname();
		String roleId;
		if (HotelGrade.isXINGJI(hotelmanage.getHousegrade())) {
			roleId = GlobalParams.XINGJIJIUDIAN;
		} else {
			roleId = GlobalParams.FEIXINGJIJIUDIAN;
		}
		TSUser hotelUser = AutoAddUser.interfaceAdd(username, roleId, realname);
		String hotelId = hotelUser.getId();
		hotelmanage.setStatus(Status.noSumbit);
		hotelmanage.setId(hotelId);
		hotelmanage.setHuserId(hotelId);
		hotelmanage.setUsername(username);

		// 设置营业状态
		hotelmanage.setOnBuinessSeason("1");
		hotelmanage.setOnBuinessYear("1");

		// 设置分数
		hotelmanage.setScore(40);

		systemService.save(hotelmanage);

		j.setMsg("酒店用户:" + hotelUser.getUserName() + "添加成功");
		
		systemService.addLog("添加酒店用户成功:"+hotelUser.getUserName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		return j;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson add(HotelmanageSta hotelmanage, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();

		String isUser = req.getParameter("isUser");
		// 通过工具类获取用户对象
		TSUser user = ResourceUtil.getSessionUserName();
		// 用户登录
		if ("1".equals(isUser)) {
			Map<String, Object> map = systemService
					.findOneForJdbc(
							"select fill_date,housegrade,district_status,status from t_hotelmanage_sta where id=?",
							user.getId());
			hotelmanage.setHousegrade(map.get("housegrade") == null ? "" : map
					.get("housegrade").toString());

			// 判断今年用户是否已经填报
			if (map.get("fill_date") != null
					&& !"".equals(map.get("fill_date"))) {
				Integer year = Integer.parseInt(map.get("fill_date").toString()
						.substring(0, 4));

				Calendar calendar = Calendar.getInstance();
				// 同年已经填报过
				if (year == calendar.get(Calendar.YEAR)) {
					String districtStatus = map.get("district_status") == null ? ""
							: map.get("district_status").toString();
					String status = map.get("status") == null ? "" : map.get(
							"status").toString();
					if ("3".equals(districtStatus) || "3".equals(status)) {

					} else {
						j.setMsg("今年已填报基本信息年报");
						return j;
					}
				}
			}

			hotelmanage.setId(user.getId());
			// 查询审核时间，判断数据是否通过国家级审核
			map = systemService
					.findOneForJdbc(
							"select revocate_country,revocate_province,verifyTime,verifyTime1,score,user_name from t_hotelmanage_sta where id=?",
							user.getId());
			if (map.get("verifyTime") != null
					&& !"".equals(map.get("verifyTime"))) {
				hotelmanage.setVerifyTime(new Date());
			}
			if (map.get("verifyTime1") != null
					&& !"".equals(map.get("verifyTime1"))) {
				hotelmanage.setVerifyTime1(new Date());
			}
			if (map.get("revocate_country") != null) {
				hotelmanage.setRevocateCountry(map.get("revocate_country")
						.toString());
			}
			if (map.get("revocate_province") != null) {
				hotelmanage.setRevocateProvince(map.get("revocate_province")
						.toString());
			}
			hotelmanage.setStatus(Status.noAudit);
			hotelmanage.setDistrictStatus(Status.noAudit);
			hotelmanage.setScore(Integer.parseInt(map.get("score").toString()));
			hotelmanage.setUsername(map.get("user_name").toString());
		} else {
			// 查询审核时间，判断数据是否通过国家级审核
			Map<String, Object> map = systemService
					.findOneForJdbc(
							"select revocate_country,revocate_province,user_name,housegrade,verifyTime,verifyTime1,score,onbuiness_season,onbuiness_year from t_hotelmanage_sta where id=?",
							hotelmanage.getId());
			if (map.get("verifyTime") != null
					&& !"".equals(map.get("verifyTime"))) {
				hotelmanage.setVerifyTime(new Date());
			}
			if (map.get("verifyTime1") != null
					&& !"".equals(map.get("verifyTime1"))) {
				hotelmanage.setVerifyTime1(new Date());
			}
			if (map.get("revocate_country") != null) {
				hotelmanage.setRevocateCountry(map.get("revocate_country")
						.toString());
			}
			if (map.get("revocate_province") != null) {
				hotelmanage.setRevocateProvince(map.get("revocate_province")
						.toString());
			}
			hotelmanage.setUsername(map.get("user_name").toString());
			hotelmanage.setHousegrade(map.get("housegrade").toString());
			// 设置审核状态
			if (this.isDistrictManager()) {
				hotelmanage.setCountryStatus(Status.noSumbit);
				hotelmanage.setProvinceStatus(Status.noSumbit);
				hotelmanage.setDistrictStatus(Status.passAudit);
				hotelmanage.setStatus(Status.noAudit);
			} else {
				hotelmanage.setCountryStatus(Status.noSumbit);
				hotelmanage.setProvinceStatus(Status.noSumbit);
				hotelmanage.setDistrictStatus(Status.noAudit);
				hotelmanage.setStatus(Status.passAudit);
			}

			hotelmanage.setScore(Integer.parseInt(map.get("score").toString()));
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fillDate = sdf.format(new Date());
		// 基本信息不建议代填报
		hotelmanage.setHuserId(user.getId());
		hotelmanage.setProvinceStatus(Status.noSumbit);
		hotelmanage.setCountryStatus(Status.noSumbit);
		hotelmanage.setW_time(new Date());
		hotelmanage.setRegistertime(new Date());
		hotelmanage.setSourceType("3");
		hotelmanage.setFillDate(fillDate);
		;
		// 设置营业状态
		hotelmanage.setOnBuinessSeason("1");
		hotelmanage.setOnBuinessYear("1");

		// 如果不是用户则直接创建酒店实例
		if (!"1".equals(isUser)) {
			Hotelmanage hotelCopy = HotelmanageSta.copy(hotelmanage);
			systemService.saveOrUpdate(hotelCopy);
		}

		// 调用service添加到数据库
		systemService.saveOrUpdate(hotelmanage);
		
		systemService.addLog("填报酒店基本信息年报成功:"+hotelmanage.getUnitname(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);


		j.setMsg("填报成功");

		return j;
	}

	/**
	 * 跳转到详情页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "detail", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, String id) {
		// String id = request.getParameter("id");
		// 根据id获得实体类
		Hotelmanage hotelmanage = hotelservice.getEntity(Hotelmanage.class, id);
		// Date fornartTime = timeUtils.getFornartTime(hotelmanage.getW_time());
		// hotelmanage.setW_time(fornartTime);
		request.setAttribute("hotelmanage", hotelmanage);
		return new ModelAndView("system/hotel/show").addObject("hotelmanage",
				hotelmanage);
	}

	/**
	 * 跳转到详情页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "detailc", method = RequestMethod.GET)
	public ModelAndView showc(HttpServletRequest request, String id) {
		// String id = request.getParameter("id");
		// 根据id获得实体类
		HotelmanageSta hotelmanage = hotelservice.getEntity(
				HotelmanageSta.class, id);
		// Date fornartTime = timeUtils.getFornartTime(hotelmanage.getW_time());
		// hotelmanage.setW_time(fornartTime);
		request.setAttribute("hotelmanage", hotelmanage);
		return new ModelAndView("system/hotel/show").addObject("hotelmanage",
				hotelmanage);
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Hotelmanage hotelmanage, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		HotelmanageSta hotelmanage1 = null;
		try {
			hotelmanage1 = hotelservice.getEntity(HotelmanageSta.class,
					hotelmanage.getId());
			hotelmanage = hotelservice.getEntity(Hotelmanage.class,
					hotelmanage.getId());
			String userId= hotelmanage.getId();
			systemService.addLog("删除酒店:"+hotelmanage.getUnitname(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			if (hotelmanage != null) {
				hotelservice.delete(hotelmanage);
			}
			if(hotelmanage1 != null ){
				hotelservice.delete(hotelmanage1);
			}
			message = "删除酒店信息成功";
			// 锁定账号
			AutoAddUser.lock(userId, req);
		} catch (Exception e) {
			message = "删除酒店信息失败";
		}
		


		j.setMsg(message);
		return j;
	}

	/**
	 * 更新状态
	 */
	/*
	 * @RequestMapping(params = "check",method=RequestMethod.GET)
	 * 
	 * @ResponseBody public AjaxJson check(String id, HttpServletRequest req) {
	 * //String id = req.getParameter("id"); //String id = hotelmanage.getId();
	 * AjaxJson j = new AjaxJson(); try { Hotelmanage hotelmanage =
	 * hotelservice.getEntity(Hotelmanage.class, id);
	 * hotelmanage.setStatus("1"); hotelmanage.setVerifyTime(new Date());
	 * hotelservice.updateEntitie(hotelmanage);; message="管理员已通过审核"; } catch
	 * (Exception e) { e.printStackTrace(); message="管理员审核失败"; }
	 * 
	 * j.setMsg(message); return j; }
	 */
	/**
	 * 审核状态2.0
	 */
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			HotelmanageSta hotelmanage, String statusid) {
		AjaxJson j = new AjaxJson();

		// 季报的id
		String hotelId = request.getParameter("id");
		request.setAttribute("hotelId", hotelId);
		try {
			if (StringUtil.isNotEmpty(hotelId)) {
				hotelmanage = systemService.getEntity(HotelmanageSta.class,
						hotelId);
				// 接口进行同步
				Hotelmanage hotelmanages = HotelmanageSta.copy(hotelmanage);

				// 审核时间为空则代表新建
				if (hotelmanages.getVerifyTime() == null) {

				}

				// TODO
				// if("200".equals(rs) && "200".equals(prs)){
				// j.setMsg("管理员审核成功!");
				// }else{
				// j.setMsg(rs);
				// return j;
				// }
				// 判断是否区级审核
				if (this.isDistrictManager()) {
					// 1表示管理员审核通过
					hotelmanage.setDistrictStatus(Status.passAudit);
					systemService.saveOrUpdate(hotelmanage);

					// 审核通过后添加或更新 正式表
					hotelmanages.setDistrictStatus(Status.passAudit);
					systemService.saveOrUpdate(hotelmanages);
				} else {
					// 1表示管理员审核通过
					hotelmanage.setStatus(Status.passAudit);
					// hotelmanage.setVerifyTime(new Date());
					// hotelmanage.setVerifyTime1(new Date());
					systemService.saveOrUpdate(hotelmanage);

					// 审核通过后添加或更新 正式表
					hotelmanages.setStatus(Status.passAudit);
					// hotelmanages.setVerifyTime(new Date());
					// hotelmanages.setVerifyTime1(new Date());
					systemService.saveOrUpdate(hotelmanages);
				}
			}
			systemService.addLog("审核通过酒店基本信息年报成功:"+hotelmanage.getUnitname(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			systemService.addLog("审核通过酒店基本信息年报失败", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}

	// 审核结果不通过
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			HotelmanageSta hotelmanage, String statusid) {
		AjaxJson j = new AjaxJson();
		String hotelId = request.getParameter("id");
		request.setAttribute("hotelId", hotelId);
		if (StringUtil.isNotEmpty(hotelId)) {
			hotelmanage = systemService
					.getEntity(HotelmanageSta.class, hotelId);

			if (this.isDistrictManager()) {
				hotelmanage.setDistrictStatus(Status.notAudit);
			} else {
				hotelmanage.setStatus(Status.notAudit);// 管理员审核未通过
			}

			try {
				// 减分
				Integer score = hotelmanage.getScore() == null ? 0
						: hotelmanage.getScore();
				if (score >= 10) {
					score = score - 10;
					UserScoreUtils.scoreChange(-10, hotelmanage.getId());
				} else {
					score = 0;
				}
				hotelmanage.setScore(score);
			} catch (Exception e) {
				e.printStackTrace();
			}
			systemService.saveOrUpdate(hotelmanage);
			systemService.addLog("审核退回酒店基本信息年报成功:"+hotelmanage.getUnitname(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}

	// 审核页面
	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,
			HotelmanageSta hotelmanage, DataGrid dataGrid) {
		String hotelmanageId = request.getParameter("id");

		request.setAttribute("hotelmanageId", hotelmanageId);
		hotelmanage = this.systemService.get(HotelmanageSta.class,
				hotelmanageId);

		return new ModelAndView("system/hotel/auditHotel").addObject(
				"hotelmanage", hotelmanage);
	}

	@RequestMapping(params = "onbuiness")
	public ModelAndView onbuiness(HttpServletRequest request) {
		String id = request.getParameter("id");

		Hotelmanage hotelmanage = systemService.get(Hotelmanage.class, id);

		String onBuinessSeason = hotelmanage.getOnBuinessSeason();
		String onBuinessYear = hotelmanage.getOnBuinessYear();

		request.setAttribute("id", id);
		request.setAttribute("onBuinessSeason", onBuinessSeason);
		request.setAttribute("onBuinessYear", onBuinessYear);

		ModelAndView model = new ModelAndView("system/hotel/onbuiness");
		model.addObject("onBuinessSeason", onBuinessSeason);
		model.addObject("onBuinessYear", onBuinessYear);
		model.addObject("id", id);

		return model;
	}

	@RequestMapping(params = "saveOnBuiness")
	@ResponseBody
	public AjaxJson saveOnBuiness(HttpServletRequest request) {
		AjaxJson tip = new AjaxJson();
		tip.setMsg("保存成功");

		try {
			String id = request.getParameter("id");

			Hotelmanage hotelmanage = systemService.get(Hotelmanage.class, id);

			String onBuinessSeason = request.getParameter("onBuinessSeason");
			String onBuinessYear = request.getParameter("onBuinessYear");

			if (!onBuinessSeason.equals(hotelmanage.getOnBuinessSeason()))
				hotelmanage.setOnBuinessSeasonSync("1");
			if (!onBuinessYear.equals(hotelmanage.getOnBuinessYear()))
				hotelmanage.setOnBuinessYearSync("1");

			hotelmanage.setOnBuinessSeason(onBuinessSeason);
			hotelmanage.setOnBuinessYear(onBuinessYear);

			// if("0".equals(onBuinessSeason) || "0".equals(onBuinessYear)){
			// TSUser user = systemService.getEntity(TSUser.class, id);
			// if("admin".equals(user.getUserName())){
			// message = "超级管理员[admin]不可操作";
			// }
			// String lockValue=req.getParameter("lockvalue");
			//
			// user.setStatus(new Short(lockValue));
			// try{
			// userService.updateEntitie(user);
			// if("0".equals(lockValue)){
			// message = "用户：" + user.getUserName() + "锁定成功!";
			// }else if("1".equals(lockValue)){
			// message = "用户：" + user.getUserName() + "激活成功!";
			// }
			// systemService.addLog(message, Globals.Log_Type_UPDATE,
			// Globals.Log_Leavel_INFO);
			// }catch(Exception e){
			// message = "操作失败!";
			// }
			// }

			systemService.saveOrUpdate(hotelmanage);
			systemService.addLog("酒店停业操作成功:"+hotelmanage.getUnitname(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip.setMsg("保存失败");
			systemService.addLog("酒店停业操作失败", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		return tip;
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
	public String exportXls(Hotelmanage hotelmanage,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(Hotelmanage.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelmanage, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		List<Hotelmanage> hotelmanages = this.systemService
				.getList(Hotelmanage.class);
		map.put(NormalExcelConstants.FILE_NAME, "酒店基本信息信息");
		map.put(NormalExcelConstants.CLASS, Hotelmanage.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("酒店信息列表",
				"导出人:酒店管理员", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelmanages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	/**
	 * 导出酒店基本信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportHotelInfo")
	public void exportHotelInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取参数
		String unitname = new String(request.getParameter("unitname").getBytes(
				"iso8859-1"), "utf-8");
		String housegrade = new String(request.getParameter("housegrade")
				.getBytes("iso8859-1"), "utf-8");
		String status = new String(request.getParameter("status").getBytes(
				"iso8859-1"), "utf-8");
		String county = request.getParameter("county");
		String bay = request.getParameter("bay");
		String onBuinessSeason = request.getParameter("onBuinessSeason");
		String onBuinessYear = request.getParameter("onBuinessYear");
		String districtStatus = request.getParameter("districtStatus");
		String provinceStatus = request.getParameter("provinceStatus");
		String countryStatus = request.getParameter("countryStatus");

		String excelName = "酒店基本信息.xls";
		String modelPath = "/com/zzc/web/export/model/hotel/" + excelName;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	t.unitname, ");
		sql.append(" 	t.user_name, ");
		sql.append(" 	t.address, ");
		sql.append(" 	t.legal_person, ");
		sql.append(" 	t.registerstyle, ");
		sql.append(" 	t.f_man `单位负责人`, ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '0', ");
		sql.append(" 	'其他', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '1', ");
		sql.append(" 	'舒适', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '2', ");
		sql.append(" 	'高档', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '3', ");
		sql.append(" 	'豪华', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '4', ");
		sql.append(" 	'一星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '5', ");
		sql.append(" 	'二星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '6', ");
		sql.append(" 	'三星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '7', ");
		sql.append(" 	'四星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '8', ");
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
		sql.append(" ) `酒店等级`, ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.w_county = '0', ");
		sql.append(" 	'市区', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.w_county = '1', ");
		sql.append(" 	'亚龙湾', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.w_county = '2', ");
		sql.append(" 	'大东海', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.w_county = '3', ");
		sql.append(" 	'三亚湾', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.w_county = '4', ");
		sql.append(" 	'海棠湾', ");
		sql.append(" 	'' ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) `所属地区`, ");
		sql.append("  t.bay_type `所属湾区`, ");
		sql.append("  t.telephone `联系电话`, ");
		sql.append("  t.mobile `手机号码`, ");
		sql.append("  t.fax `电子传真`, ");
		sql.append("  t.zipcode `邮编`, ");
		sql.append("  t.email `邮箱`, ");
		sql.append("  t.weburl `网址`, ");
		sql.append("  t.writer `填表人`, ");
		sql.append("  t.fill_date `填报日期` ");
		sql.append(" FROM ");
		sql.append(" 	t_hotelmanage t ");
		sql.append(" WHERE ");
		sql.append(" 	1 = 1 ");

		// 组装查询条件
		if (unitname != null && unitname.length() != 0) {
			sql.append(" and t.unitname like '%" + unitname + "%'");
		}
		if (housegrade != null && housegrade.length() != 0) {
			sql.append(" and t.housegrade ='" + housegrade + "'");
		}
		if (county != null && county.length() != 0) {
			sql.append(" and t.w_county ='" + county + "'");
		}
		if (bay != null && bay.length() != 0) {
			sql.append(" and t.bay_type ='" + bay + "'");
		}
		if (onBuinessSeason != null && onBuinessSeason.length() != 0) {
			sql.append(" and t.onbuiness_season ='" + onBuinessSeason + "'");
		}
		if (onBuinessYear != null && onBuinessYear.length() != 0) {
			sql.append(" and t.onbuiness_year ='" + onBuinessYear + "'");
		}
		if (districtStatus != null && districtStatus.length() != 0) {
			sql.append(" and t.district_status ='" + districtStatus + "'");
		}
		if (provinceStatus != null && provinceStatus.length() != 0) {
			sql.append(" and t.provice_status ='" + provinceStatus + "'");
		}
		if (countryStatus != null && countryStatus.length() != 0) {
			sql.append(" and t.country_status ='" + countryStatus + "'");
		}
		if (status != null && status.length() != 0) {
			sql.append(" and t.status ='" + status + "'");
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
		systemService.addLog("导出酒店基本信息:", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

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
	 * 撤回酒店基本信息
	 * 
	 * @return 成功标志
	 */
	@RequestMapping(params = "revocation")
	@ResponseBody
	public String revocationHotelInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String tip = "success";

		String hotelId = request.getParameter("id");

		try {
			// 获取酒店对象
			HotelmanageSta hotelmanageSta = systemService.get(
					HotelmanageSta.class, hotelId);
			// 省和国家系统的审核状态
			String provinceStatus = hotelmanageSta.getProvinceStatus();
			String countryStatus = hotelmanageSta.getCountryStatus();
			// 记录之前的审核状态
			hotelmanageSta.setRevocateCountry(provinceStatus);
			hotelmanageSta.setRevocateProvince(countryStatus);
			// 更新成未审核
			hotelmanageSta.setProvinceStatus(Status.noSumbit);
			hotelmanageSta.setCountryStatus(Status.noSumbit);
			systemService.saveOrUpdate(hotelmanageSta);
			systemService.addLog("撤回酒店基本信息年报成功:"+hotelmanageSta.getUnitname(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "failed";
			systemService.addLog("撤回酒店基本信息年报失败:"+hotelId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

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
		HotelmanageSta hotelmanageSta = systemService.get(HotelmanageSta.class, id);
		String status = hotelmanageSta == null || hotelmanageSta.getCountryStatus() == null ? "" : hotelmanageSta.getCountryStatus();
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
		modelMap.put("countryContent", hotelmanageSta == null ? "" : hotelmanageSta.getGuo());
		return new ModelAndView("/travel/countryContent").addAllObjects(modelMap);
	}

}
