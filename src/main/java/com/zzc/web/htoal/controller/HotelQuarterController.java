package com.zzc.web.htoal.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.ExceptionUtil;
import com.zzc.core.util.ResourceUtil;
import com.zzc.core.util.StringUtil;
import com.zzc.poi.excel.ExcelImportUtil;
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.ImportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.htoal.entity.HotelQuarter;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.service.Hotelservice;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

/**
 * 
 * 
 * @date: 2016年12月5日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelMonthly.java
 * @Version: 1.0
 * @About: 酒店住宿情况季报
 */
@Scope("prototype")
@Controller
@RequestMapping("/hotelQuarterController")
public class HotelQuarterController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(HotelQuarterController.class);
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

	// 季报管理跳转页面
	@RequestMapping(params = "hotelQuarter")
	public ModelAndView hotelQuaterCheck(HttpServletRequest request) {
		
		Calendar calendar = Calendar.getInstance();
		// 上个月是12月的情况
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String quarter = this.getQuarter();
		quarter = String.valueOf(Integer.parseInt(quarter) - 1);
		// 对跨年的情况做处理
		if("0".equals(quarter)){
			year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
			quarter = "4";
		}
		request.setAttribute("year", year);
		request.setAttribute("quarter", quarter);
		return new ModelAndView("system/hotel/hotelQuarterList");
	}

	// 跳转到save保存页面
	@RequestMapping(params = "addorupdate")
	public String hotelInfo(HttpServletRequest req) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		Hotelmanage hm = this.systemService.getEntity(Hotelmanage.class,
				hotelId);
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
		req.setAttribute("year", nowYear);
		req.setAttribute("quarter", nowSeason);
		req.setAttribute("hotelmanage", hm);
		HotelQuarter hotelQuarter = new HotelQuarter();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hotelQuarter.setWriterDate(sdf.format(new Date()));
		req.setAttribute("quarterData", hotelQuarter);
		
		return "system/hotel/saveHotelQuarter";
	}

	@RequestMapping(params = "import")
	public String imports() {
		return "system/hotel/uploadQuarter";
	}

	/*
	 * @RequestMapping(params="export") public String exports(){ return
	 * "system/hotel/uploadQuarter"; }
	 */
	// 跳转到季报审核页面
	@RequestMapping(params = "hotelQCheck")
	public String hotelQuarterCheck() {
		return "system/hotel/hotelQuarterCheckList";
	}
	
	@RequestMapping(params = "editByUser")
	public String editByUser(HttpServletRequest req) {
		
		String quarterId = req.getParameter("quarterId");
		
		String hotelId = ResourceUtil.getSessionUserName().getId();
		Hotelmanage hm = this.systemService.getEntity(Hotelmanage.class,
				hotelId);
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
		req.setAttribute("year", nowYear);
		req.setAttribute("quarter", nowSeason);
		req.setAttribute("hotelmanage", hm);
		
		HotelQuarter hotelQuarter = systemService.get(HotelQuarter.class, quarterId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hotelQuarter.setWriterDate(sdf.format(new Date()));
		req.setAttribute("quarterData", hotelQuarter);
		return "system/hotel/saveHotelQuarter";
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson add(HotelQuarter hotelQuarter, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		// 通过工具类获取用户对象
		TSUser user = ResourceUtil.getSessionUserName();
		if(hotelQuarter.getId() == null || hotelQuarter.getId().length() == 0){
			if(this.isFilled()){
				j.setMsg("上季度已填报季报!");
				return j;
			}
			hotelQuarter.setId(null);
		}
		
		// id未酒店的id
		hotelQuarter.setHid(user.getId());
		// hotelservice.loadAll(hotelmanage.getClass());
		hotelQuarter.setStatus(Status.noAudit);
		hotelQuarter.setDistrictStatus(Status.noAudit);
		hotelQuarter.setCountryState(Status.noSumbit);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		hotelQuarter.setWriterDate(sdf.format(new Date()));
		Hotelmanage hotelmanage = systemService.getEntity(Hotelmanage.class,
				hotelQuarter.getHid());
		hotelQuarter.setHotelmanage(hotelmanage);
		
		// 加分
		int year = Integer.parseInt(hotelQuarter.getYear());
		int quarter = hotelQuarter.getQuarter();
		boolean rs = ReportTimeCheck.quarterTimeCheck(year, quarter);
		if(rs){
			hotelQuarter.setScore(10);
			UserScoreUtils.scoreChange(10);
		}else{
			hotelQuarter.setScore(5);
			UserScoreUtils.scoreChange(5);
		}
		
		systemService.saveOrUpdate(hotelQuarter);
		
		systemService.addLog("填报酒店季报成功:"+hotelQuarter.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);


		j.setMsg("添加成功");

		return j;
	}

	// 季报管理列表
	@RequestMapping(params = "datagrid")
	public void roleGrid(HotelQuarter hotelQuarter, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HotelQuarter.class, dataGrid);
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
				cq.add(Restrictions.in("hotelQid", parmas));
			} else {
				cq.add(Restrictions.in("hotelQid", new Object[] { "" }));
			}
		}
		// 查询条件
		cq.add(Restrictions.or(Restrictions.eq("status", "4"),Restrictions.eq("districtStatus", "4")));
		
		//默认显示为上季度的报表
		String year = request.getParameter("year");
		String quarter = request.getParameter("quarter");
		Calendar calendar = Calendar.getInstance();
		// 上个月是12月的情况
		if(year==null&&quarter==null){
			year = String.valueOf(calendar.get(Calendar.YEAR));
			quarter = this.getQuarter();
			quarter= String.valueOf(Integer.parseInt(quarter) - 1);
			// 对跨年的情况做处理
			if("0".equals(quarter)){
				year = String.valueOf(calendar.get(Calendar.YEAR) - 1);
				quarter = "4";
			}
		}
		
	  	cq.add(Restrictions.eq("year",year));
        cq.add(Restrictions.eq("quarter",Integer.valueOf(quarter)));
		/*String year = hotelQuarter.getYear();
		String quarter = hotelQuarter.getQuarter() == null ? "" : String.valueOf(hotelQuarter.getQuarter());*/
		String hotelName = hotelQuarter.getHotelmanage() == null ? "" : hotelQuarter.getHotelmanage().getUnitname();
		String beginStar=request.getParameter("hotelmanage.housegrade_begin");
		String endStar=request.getParameter("hotelmanage.housegrade_end");
		String country=request.getParameter("hotelmanage.county");
		String bay=request.getParameter("hotelmanage.bay");
		String status=request.getParameter("status");
		String countryState=request.getParameter("countryState");
		 
		if(hotelName != null && hotelName.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.unitname like '%"+hotelName+"%' and t.id=hotel_qid)"));
		}if(country != null && country.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.w_county ="+country+"  and t.id=hotel_qid)"));
		}
		if(bay != null && bay.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.bay_type ="+bay+" and t.id=hotel_qid)"));
		}
		/*if(hotelType != null && hotelType.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.housegrade = '"+hotelType+"' and t.id=hotel_id)"));
		}*/
		if(beginStar!=null&&beginStar.length()!=0&&endStar!=null&&endStar.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_hotelmanage t where t.housegrade between '"+beginStar+"' and '"+endStar+"' and  t.id=hotel_qid)"));
		}
		if(status != null && status.length() != 0){
			cq.add(Restrictions.sqlRestriction("this_ .status ="+status));
		}
		if(countryState != null && countryState.length() != 0){
			cq.add(Restrictions.sqlRestriction("this_ .country_state ="+countryState));
		}
		
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new HotelQuarter());
		// cq.in("hotelmanage.housegrade", new
		// String[]{"4","5","6","7","8"});//{"其他_0","舒适_1","高档_2","豪华_3","一星_4","二星_5","三星_6","四星_7","五星_8"}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "checkdatagrid")
	public void hotelQuarterCheckGrid(Hotelmanage hotelManage,
			HotelQuarter hotelQuarter, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Hotelmanage.class, dataGrid);

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
		// 查询条件
		if(hotelManage.getUnitname() != null && hotelManage.getUnitname().length() != 0){
        	cq.add(Restrictions.like("unitname", "%"+hotelManage.getUnitname()+"%"));
        }
        if(hotelManage.getHousegrade() != null && hotelManage.getHousegrade().length() != 0){
        	cq.add(Restrictions.eq("housegrade", hotelManage.getHousegrade()));
        }
        if(hotelManage.getCounty() != null && hotelManage.getCounty().length() != 0){
        	cq.add(Restrictions.eq("county", hotelManage.getCounty()));
        }
        if(hotelManage.getBay() != null && hotelManage.getBay().length() != 0){
        	cq.add(Restrictions.eq("bay", hotelManage.getBay()));
        }
        if(hotelManage.getHotelquarter() != null && hotelManage.getHotelquarter().getDistrictStatus() != null && hotelManage.getHotelquarter().getDistrictStatus().length() != 0){
        	if("1".equals(hotelManage.getHotelquarter().getDistrictStatus())){
            	cq.add(Restrictions.sqlRestriction(" 1=1 and not exists(select 1 from t_hotel_quarterly t where t.year ='"+nowYear+"' and t.quarter ='"+nowSeason+"' and t.hotel_qid=this_.id) "));
        	}else{
            	cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select 1 from t_hotel_quarterly t where t.district_status = '"+hotelManage.getHotelquarter().getDistrictStatus()+"' and t.year ='"+nowYear+"' and t.quarter ='"+nowSeason+"' and t.hotel_qid=this_.id) "));
        	}
        }
        if(hotelManage.getHotelquarter() != null && hotelManage.getHotelquarter().getStatus() != null && hotelManage.getHotelquarter().getStatus().length() != 0){
        	if("1".equals(hotelManage.getHotelquarter().getStatus())){
            	cq.add(Restrictions.sqlRestriction(" 1=1 and not exists(select 1 from t_hotel_quarterly t where t.year ='"+nowYear+"' and t.quarter ='"+nowSeason+"' and t.hotel_qid=this_.id) "));
        	}else{
            	cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select 1 from t_hotel_quarterly t where t.status = '"+hotelManage.getHotelquarter().getStatus()+"'  and t.year ='"+nowYear+"' and t.quarter ='"+nowSeason+"' and t.hotel_qid=this_.id) "));
        	}
        }
        if(hotelManage.getHotelquarter() != null && hotelManage.getHotelquarter().getCountryState() != null && hotelManage.getHotelquarter().getCountryState().length() != 0){
        	if("1".equals(hotelManage.getHotelquarter().getCountryState())){
            	cq.add(Restrictions.sqlRestriction(" 1=1 and not exists(select 1 from t_hotel_quarterly t where t.year ='"+nowYear+"' and t.quarter ='"+nowSeason+"' and t.hotel_qid=this_.id) "));
        	}else{
            	cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select 1 from t_hotel_quarterly t where t.country_state = '"+hotelManage.getHotelquarter().getCountryState()+"'  and t.year ='"+nowYear+"' and t.quarter ='"+nowSeason+"' and t.hotel_qid=this_.id) "));
        	}
        }
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new Hotelmanage());
		cq.in("housegrade", new String[] { "4", "5", "6", "7", "8" });// {"其他_0","舒适_1","高档_2","豪华_3","一星_4","二星_5","三星_6","四星_7","五星_8"}
		cq.add();
		// 获取easyUI的datagrid模型
		this.hotelservice.getDataGridReturn(cq, true);
		// 对季报的审核 当前季度审核上一季度
		// 1.获取当前季度 123 第一季度 审第四季度 以此类推
		Date date = new Date();
		int currerntSeason = TimeUtils.getSeason(date);
		
		
		
		// 对原型进行遍历list集合
		System.out.println(dataGrid.getResults().size());
		for (Object o : dataGrid.getResults()) {
			// 对集合元素进行操作
			Hotelmanage hd = (Hotelmanage) o;
			// 对酒店的月报判断是否为空
			if (hd.getHotelQuarterList() != null) {
				// 2.获取当前酒店的季报信息
				List<HotelQuarter> list = hd.getHotelQuarterList();
				for (HotelQuarter quarterdata : list) {
					// 对月报进行遍历
					// 没有季报信息
					if (quarterdata.getQuarter() == null) {
						break;
					}
					// 去当前月报的时间 转换为yyyy-MM格式
					// String monthTime =
					// timeUtils.getCurrentMonth(mothdata.getMonth());
					// 如果是上月时间 则返回审核列表
					// mothdata.getMonth();
					if (Integer.parseInt(nowSeason) == quarterdata.getQuarter() && nowYear.equals(quarterdata.getYear())) {
						// 加到酒店信息中 在审核表中提现
						hd.setHotelquarter(quarterdata);
					}
					// 满足月份 跳出循环
					break;
				}
				// 满足审核的季报不存在 的情况
				if (hd.getHotelquarter() == null) {
					// 创建一个 设置为代填报状态
					HotelQuarter hl = new HotelQuarter();
					hl.setQuarter(currerntSeason);
					hl.setYear(nowYear);
					hl.setStatus(Status.noSumbit);
					hl.setDistrictStatus(Status.noSumbit);
					hl.setCountryState(Status.noSumbit);
					// set到当前属性中
					hd.setHotelquarter(hl);
				}
			}
		}
		// 将datagrid返回给前端页面
		TagUtil.datagrid(response, dataGrid);
	}

	// 删除季报
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HotelQuarter hotelQuarter, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			hotelQuarter = hotelservice.getEntity(HotelQuarter.class, id);
			hotelservice.delete(hotelQuarter);
			message = "季报删除成功";
		} catch (Exception e) {
			message = "季报删除失败";
		}

		j.setMsg(message);
		return j;
	}

	// 季报详情展示

	@RequestMapping(params = "detail")
	public ModelAndView detial(HttpServletRequest request,
			HotelQuarter hotelQuarter, Hotelmanage hotelmanage,
			DataGrid dataGrid) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		// 季报id
		String quarterId = request.getParameter("quarterId");
		// 存到请求域中
		// request.setAttribute("hotelmanageId", hotelmanageId);
		Hotelmanage h = this.systemService
				.get(Hotelmanage.class, hotelmanageId);
		hotelQuarter = this.systemService.get(HotelQuarter.class, quarterId);

		// request.setAttribute("hotelmanage", h);
		// request.setAttribute("monthdata", m);
		hotelQuarter.setHotelmanage(h);
		// request.setAttribute("hotelMonthly",hotelMonthly);

		return new ModelAndView("system/hotel/showQuarterDetail").addObject(
				"hotelQuarter", hotelQuarter);
	}

	// 季报审核管理员操作
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			HotelQuarter HotelQuarter, Hotelmanage hotelmanage, String statusid) {
		AjaxJson j = new AjaxJson();

		// 季报的id
		String quarterId = request.getParameter("quarterId");
		request.setAttribute("quarterId", quarterId);
		try {
			if (StringUtil.isNotEmpty(quarterId)) {
				HotelQuarter hotelQuarter = systemService.getEntity(
						HotelQuarter.class, quarterId);
				// TODO 定时器
//				String rs = this.addQuarterReport(hotelQuarter, "add");
//				if(!"200".equals(rs)){
//					j.setMsg(rs);
//					return j;
//				}
				// 判断是否区级审核
				if(this.isDistrictManager()){
					hotelQuarter.setDistrictStatus(Status.passAudit);
				}else{
					// 表示管理员审核通过
					hotelQuarter.setStatus(Status.passAudit);
					hotelQuarter.setAuditTime(new Date());
				}
				this.systemService.saveOrUpdate(hotelQuarter);

				j.setMsg("管理员审核成功");
				systemService.addLog("审核通过酒店季报成功:"+hotelQuarter.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("管理员审核失败");
			systemService.addLog("审核通过酒店季报失败:"+quarterId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}

	// 审核结果不通过
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			Hotelmanage hotelmanage, HotelQuarter HotelQuarter, String statusid) {
		AjaxJson j = new AjaxJson();
		j.setMsg("操作成功");

		String quarterId = request.getParameter("quarterId");
		request.setAttribute("quarterId", quarterId);
		if (StringUtil.isNotEmpty(quarterId)) {
			HotelQuarter hotelQuarter = systemService.getEntity(
					HotelQuarter.class, quarterId);
			// request.setAttribute("scenicdata", scenicdata);
			// 判断是否区级审核
			if(this.isDistrictManager()){
				hotelQuarter.setDistrictStatus(Status.notAudit);// 管理员审核未通过
			}else{
				hotelQuarter.setStatus(Status.notAudit);// 管理员审核未通过
			}						
			
			hotelQuarter.setAuditTime(new Date());
			
			try {
				// 减分
				Integer score = hotelQuarter.getScore() == null ? 0
						: hotelQuarter.getScore();
				if (score >= 5) {
					score = score - 5;
					UserScoreUtils.scoreChange(-5, hotelQuarter.getHotelQid());
				} else {
					UserScoreUtils.scoreChange((0 - score),
							hotelQuarter.getHotelQid());
					score = 0;
				}
				hotelQuarter.setScore(score);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.systemService.saveOrUpdate(hotelQuarter);
			systemService.addLog("审核退回酒店季报成功:"+hotelQuarter.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}

	// 审核页面
	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,
			HotelQuarter hotelQuarter, Hotelmanage hotelmanage,
			DataGrid dataGrid) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		String quarterId = request.getParameter("quarterId");
		request.setAttribute("hotelmanageId", hotelmanageId);
		Hotelmanage h = this.systemService
				.get(Hotelmanage.class, hotelmanageId);
		HotelQuarter hq = this.systemService.get(HotelQuarter.class, quarterId);

		// request.setAttribute("scenicdata", s);
		hq.setHotelmanage(h);
		// request.setAttribute("monthdata", m);
		// scenicSpotMonth.setScenicData(s);
		// request.setAttribute("scenicSpotMonth",scenicSpotMonth);

		return new ModelAndView("system/hotel/auditQuarter").addObject(
				"hotelquarter", hq);
	}

	/**
	 * 批量导入季报数据
	 */
	@RequestMapping(params = "importExcel")
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, ModelAndView view) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		AjaxJson j = new AjaxJson();
		view.addObject(j);
		List<HotelQuarter> allRecords = new ArrayList<HotelQuarter>();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setHeadRows(1);
			params.setNeedSave(true);
			// 对集合进行操作
			try {
				List<HotelQuarter> listBlackList = ExcelImportUtil.importExcel(
						file.getInputStream(), HotelQuarter.class, params);
				allRecords.addAll(listBlackList);
				for (HotelQuarter hotelQuarter : listBlackList) {
					try {
						// 补全属性
						TSUser user = ResourceUtil.getSessionUserName();
						hotelQuarter.setHid(user.getId());

						// 保存实体类到数据库
						this.systemService.save(hotelQuarter);
						System.out.println("开以保存");

					} catch (Exception e) {
						e.printStackTrace();
						j.setSuccess(false);
					}

				}

			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
				j.setSuccess(false);
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
					j.setSuccess(false);
					return j;
				}
			}
		}
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
	public String exportsXls(HotelQuarter hotelQuarter,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(HotelQuarter.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelQuarter, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		List<HotelQuarter> hotelQuarters = this.systemService
				.getList(HotelQuarter.class);
		map.put(NormalExcelConstants.FILE_NAME, "酒店季报信息");
		map.put(NormalExcelConstants.CLASS, HotelQuarter.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("酒店季报列表",
				"导出人:酒店管理员", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelQuarters);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	/**
	 * 
	 * @date: 2016年12月13日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request,
			HotelQuarter hotelQuarter, Hotelmanage hotelmanage) {
		String hotelmanageId = request.getParameter("hotelmanageId");
		// 现在为空
		String quarterId = request.getParameter("quarterId");
		// 根据基本信息id获取基本信息
		Hotelmanage hm = this.systemService.get(Hotelmanage.class,
				hotelmanageId);
		// 根绝月报id获取月报信息
		HotelQuarter s = this.systemService.get(HotelQuarter.class, quarterId);
		// 将基本信息设置在域中

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s == null) {
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			// 对跨年的情况做处理
			if("0".equals(nowSeason)){
				nowYear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
				nowSeason = "4";
			}
			Date now = new Date();
			s = new HotelQuarter();
			s.setWriterDate(sdf.format(now));
			s.setQuarter(Integer.parseInt(nowSeason));
			s.setYear(nowYear);
		}
		s.setHid(hotelmanageId);
		// 返回当前季度
		// s.setHid(hm.getId());
		s.setHotelmanage(hm);
		request.setAttribute("hotelmanageId", hotelmanageId);
		request.setAttribute("quarterData", s);
		request.setAttribute("hotelmanage", hm);
		// this.saveScenic(request, scenicSpotMonth);
		return new ModelAndView("system/hotel/daitianbaoQuarter").addObject("hotelmanage", hm);
	}

	/**
	 * 
	 * @date: 2016年12月13日
	 * @Author:龙亚辉
	 * @Email: 5022309026.com
	 * @param:
	 * @return:
	 */
	@RequestMapping(params = "saveDai")
	@ResponseBody
	public AjaxJson addDai(HotelQuarter hotelQuarter, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		// 补全月报信息由
		if(hotelQuarter.getId() == null || hotelQuarter.getId().length() == 0){
			hotelQuarter.setId(null);
		}
		
		// 与酒店的id进行关联
		// HotelQuarter.setHid(hid);
		System.out.println(hotelQuarter.getHid());
		Hotelmanage hotelmanage = systemService.getEntity(Hotelmanage.class,
				hotelQuarter.getHid());
		hotelQuarter.setHotelmanage(hotelmanage);
		// 判断是否区级审核
		if(this.isDistrictManager()){
			hotelQuarter.setDistrictStatus(Status.passAudit);
			hotelQuarter.setStatus(Status.noAudit);
			hotelQuarter.setCountryState(Status.noSumbit);
		}else{
			hotelQuarter.setStatus(Status.passAudit);
			hotelQuarter.setDistrictStatus(Status.noAudit);
			hotelQuarter.setCountryState(Status.noSumbit);
		}
		systemService.saveOrUpdate(hotelQuarter);
		// systemService.saveOrUpdate(hotelmanage);
		System.out.println("添加成功");

		j.setMsg("添加成功");
		
		systemService.addLog("代填报酒店季报成功:"+hotelQuarter.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);


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
			HotelQuarter hotelQuarter, Hotelmanage hotelmanage, String statusid) {
		AjaxJson j = new AjaxJson();
		String hotelId = request.getParameter("hotelId");
		request.setAttribute("hotelId", hotelId);
		
		if(hotelQuarter.getId() == null || hotelQuarter.getId().length() == 0) hotelQuarter.setId(null);
		
		// 根据id获取酒店信息实体类
		hotelmanage = this.hotelservice.getEntity(Hotelmanage.class, hotelId);
		// 将基本信息添加到月报信息中
		hotelQuarter.setHid(hotelId);
		hotelQuarter.setHotelmanage(hotelmanage);
		// hotelQuarter.setMonth(timeUtils.getLastMonthD());
		// 状态仍为未填报
		hotelQuarter.setStatus(Status.noSumbit);
		hotelQuarter.setDistrictStatus(Status.noSumbit);
		hotelQuarter.setCountryState(Status.noSumbit);
		// 更新
		request.setAttribute("hotelQuarter", hotelQuarter);
		systemService.saveOrUpdate(hotelQuarter);
		systemService.addLog("暂存酒店季报成功:"+hotelQuarter.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

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
	public String exportXls(HotelQuarter hotelQuarter,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(HotelQuarter.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelQuarter, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		List<HotelQuarter> hotelQuarters = this.systemService
				.getList(HotelQuarter.class);
		map.put(NormalExcelConstants.FILE_NAME, "酒店月报信息");
		map.put(NormalExcelConstants.CLASS, HotelQuarter.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("酒店季报列表",
				"导出人:酒店管理员", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelQuarters);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	// ****************************客户季报*************************************
	@RequestMapping(params = "ckhotelQuarter")
	public String quarters(HttpServletRequest request) {
		return "system/hotelClient/ckHotelQuarterList";
	}

	@ResponseBody
	@RequestMapping(params = "ckDatagridQuarter")
	public void quarterGrid(HotelQuarter hotelQuarter,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		String hotelId = ResourceUtil.getSessionUserName().getId();
		hotelQuarter.setHid(hotelId);
		CriteriaQuery cq = new CriteriaQuery(HotelQuarter.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelQuarter);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);

		TagUtil.datagrid(response, dataGrid);

	}

	// 酒店用户自身季报导出
	@RequestMapping(params = "exportsxlss")
	public String exportXlss(HotelQuarter hotelQuarter,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(HotelQuarter.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelQuarter, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		String hotelId = ResourceUtil.getSessionUserName().getId();
		List<HotelQuarter> hotelQuarters = this.hotelservice
				.findHql(
						" from com.zzc.web.htoal.entity.HotelQuarter hq where hq.hid=? ",
						hotelId);
		map.put(NormalExcelConstants.FILE_NAME, "酒店季报信息");
		map.put(NormalExcelConstants.CLASS, HotelQuarter.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("酒店季报列表",
				"导出人:酒店", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, hotelQuarters);
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
	 * 检查季报是否填报
	 * @return
	 */
	public boolean isFilled(){
		boolean tip = false;
		//获取当前登录用户ID=酒店ID
		String userId = ResourceUtil.getSessionUserName().getId();
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
		String sql = "select id from t_hotel_quarterly t where t.`year`='"+nowYear+"' and t.`quarter` = '"+nowSeason+"' and t.hotel_qid = '"+userId+"'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if(list.size() != 0) tip = true;
		return tip;
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
	 * 导出季报
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportQuarter")
	public void exportQuarter(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
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
        
        // 获取参数 
	        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
	        String year = request.getParameter("year");
	        String quarter = request.getParameter("quarter");
	        String beginStar=request.getParameter("beginStar");
      		String endStar=request.getParameter("endStar");
      		String country=request.getParameter("country");
      		String bay=request.getParameter("bay");
      		String status=request.getParameter("status");
      		String countryState=request.getParameter("countryState");
        if(year==null||year.length()==0){
        	year=nowYear;
        } if(quarter==null||quarter.length()==0){
        	quarter=nowSeason;
        }
        String excelName = "酒店季报.xls";
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
        sql.append("  t.`quarter`, ");
        sql.append("  t.totalIncome, ");
        sql.append("  t.canteenIncome, ");
        sql.append(" t.roomIncome, ");
        sql.append(" t.otherIncome, ");
        sql.append(" t.roomnum `客房数量`, ");
        sql.append(" t.bednum `床位数量`, ");
        sql.append(" t.canNights `可供出租夜间数`, ");
        sql.append(" t.realNights `实际出租夜间数`, ");
        sql.append(" t.writer `填报人`, ");
        sql.append(" t.writerDate `填报时间` ");

        sql.append(" FROM ");
        sql.append(" 	t_hotel_quarterly t ");
        sql.append(" LEFT JOIN t_hotelmanage hotelmanage ON t.hotel_qid = hotelmanage.id where  1=1  ");
        sql.append(" and (t.status = '4' or t.district_status = '4') ");
        
        // 组装查询条件
       
        if(year != null && year.length() != 0){
        	sql.append(" and t.`year` like '%"+year+"%' ");
        }
        if(quarter != null && quarter.length() != 0){
        	sql.append(" and t.`quarter` = '"+quarter+"' ");
        }
        	if(status != null && status.length() != 0){
    			sql.append(" and t.status="+status);
    		}
    		if(countryState != null && countryState.length() != 0){
    			sql.append(" and t.country_state="+countryState);
    		}
    	    if(name != null && name.length() != 0){
            	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_qid and hm.unitname like '%"+name+"%') ");
            }
        	if(country != null && country.length() != 0){
    			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_qid and hm.w_county ="+country+")");
    		}
    		if(bay != null && bay.length() != 0){
    			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_qid and hm.bay_type ="+bay+")");
    		}
    	 
    		if(beginStar!=null&&beginStar.length()!=0&&endStar!=null&&endStar.length()!=0){
    			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_qid and hm.housegrade between '"+beginStar+"' and '"+endStar+"')");
    		}
		sql.append(" UNION ALL SELECT ");
        sql.append(" 	'合计' ,");
        sql.append(" '' ,");
        sql.append(" '' ,");
        sql.append(" '' ,");
        sql.append(" '' ,");
        sql.append(" IFNULL(sum(t.totalIncome),0), ");
		sql.append("IFNULL(sum(t.canteenIncome),0), ");
		sql.append(" IFNULL(sum(t.roomIncome),0), ");
		sql.append(" IFNULL(sum(t.otherIncome),0), ");
		sql.append(" IFNULL(sum(t.roomnum),0) `客房数量`, ");
		sql.append(" IFNULL(sum(t.bednum),0) `床位数量`, ");
		sql.append(" IFNULL(sum(t.canNights),0) `可供出租夜间数`, ");
		sql.append(" IFNULL(sum(t.realNights),0) `实际出租夜间数`, ");
		sql.append(" '' ,");
		sql.append(" '' ");
        sql.append(" FROM ");
        sql.append(" 	t_hotel_quarterly t ");
        sql.append(" LEFT JOIN t_hotelmanage hotelmanage ON t.hotel_qid = hotelmanage.id where  1=1  ");
        sql.append(" and (t.status = '4' or t.district_status = '4') ");
        if(year != null && year.length() != 0){
        	sql.append(" and t.`year` like '%"+year+"%' ");
        }
        if(quarter != null && quarter.length() != 0){
        	sql.append(" and t.`quarter` = '"+quarter+"' ");
        }
        	if(status != null && status.length() != 0){
    			sql.append(" and t.status="+status);
    		}
    		if(countryState != null && countryState.length() != 0){
    			sql.append(" and t.country_state="+countryState);
    		}
            if(name != null && name.length() != 0){
            	sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_qid and hm.unitname like '%"+name+"%') ");
            }
        	if(country != null && country.length() != 0){
    			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_qid and hm.w_county ="+country+")");
    		}
    		if(bay != null && bay.length() != 0){
    			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_qid and hm.bay_type ="+bay+")");
    		}
    	 
    		if(beginStar!=null&&beginStar.length()!=0&&endStar!=null&&endStar.length()!=0){
    			sql.append(" and exists(select hm.id from t_hotelmanage hm where hm.id = t.hotel_qid and hm.housegrade between '"+beginStar+"' and '"+endStar+"')");
    		}
        ExportService emds = new ExportService(15); 
        
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
	 *  撤回酒店季报
	 * @return 成功标志
	 */
	@RequestMapping(params = "revocation")
	@ResponseBody
	public String revocationHotelInfo(HttpServletRequest request, HttpServletResponse response){
		String tip = "success";
		
		String id = request.getParameter("id");
		
		try {
			// 获取季报对象
			HotelQuarter hotelQuarter = systemService.get(HotelQuarter.class, id);
			hotelQuarter.setCountryState(Status.noSumbit);
			hotelQuarter.setRevocated("1");
			systemService.saveOrUpdate(hotelQuarter);
			systemService.addLog("撤回酒店季报成功:"+hotelQuarter.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			tip = "failed";
			systemService.addLog("撤回酒店季报失败:"+id, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

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
		HotelQuarter hotelQuarter = systemService.get(HotelQuarter.class, id);
		String status = hotelQuarter == null || hotelQuarter.getCountryState() == null ? "" : hotelQuarter.getCountryState();
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
		modelMap.put("countryContent", hotelQuarter == null ? "" : hotelQuarter.getGuo());
		return new ModelAndView("/travel/countryContent").addAllObjects(modelMap);
	}
	
}
