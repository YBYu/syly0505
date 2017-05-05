package com.zzc.web.otherTravel.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.zzc.web.otherTravel.entity.BoatMonthly;
import com.zzc.web.otherTravel.entity.GolfMonthly;
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.scenicmanage.entity.ScenicSpotWeek;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

/**
 * 
 * 
 * @date: 2016年12月16日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: OtherTravelMonthlyController.java
 * @Version: 1.0
 * @About:涉旅机构中 高尔夫后台代码...
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/otherTravelMonthlyController")
public class OtherTravelMonthlyController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(OtherTravelMonthlyController.class);

	// 调用时间工具
	TimeUtils timeUtils = new TimeUtils();
	private SystemService systemService;
	private String message = null;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(params = "addgm")
	public ModelAndView addGm(HttpServletRequest req) {
		String otid = ResourceUtil.getSessionUserName().getId();
		OtherTravelInfo ot = this.systemService.getEntity(
				OtherTravelInfo.class, otid);
		// String gMonthId = req.getParameter("gMonthId");

		req.setAttribute("otherTravelInfo", ot);
		String lastMonth = timeUtils.getLastmonth();
		// GolfMonthly g = this.systemService.get(GolfMonthly.class, gMonthId);
		req.setAttribute("lastMonth", lastMonth);
		// req.setAttribute("g", g);
		return new ModelAndView("otherTravel/savegm");
	}

	// 月报跳转页面
	@RequestMapping(params = "golfMonth")
	public ModelAndView golfMonth() {
		return new ModelAndView("otherTravel/golfMonthlylList");
	}

	// 跳转到月报审核页面
	@RequestMapping(params = "golfMonthCheck")
	public ModelAndView hotelMonthCheck() {
		return new ModelAndView("otherTravel/golfMonthlyCheckList");
	}

	/**
	 * 客户端编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "editPage")
	public ModelAndView editPage(HttpServletRequest request,
			HttpServletResponse response) {
		String otid = request.getParameter("otid");
		// 季报id
		String gMonthId = request.getParameter("gMonthId");
		// 存到请求域中
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		GolfMonthly golfMonthly = this.systemService.get(GolfMonthly.class,
				gMonthId);

		golfMonthly.setOtherTravelInfo(h);

		return new ModelAndView("otherTravelClient/edit/editGolfMonthly")
				.addObject("golfMonthly", golfMonthly);

	}

	/**
	 * 
	 * @date: 2016年12月26日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:跳转到到填报(代填报多个企业)
	 */
	@RequestMapping(params = "daitianbao")
	public String monthDaitianbao(GolfMonthly golfMonth,
			OtherTravelInfo otherTravelInfo, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		// 定义集合存储未填报的高尔夫月报
		List lists = new ArrayList<>();

		// 获取当月时间yyyy-mm
		String currentMonth = timeUtils.getCurrentMonth(new Date());
		// 获取上月时间
		String lastMonth = timeUtils.getLastMonth();
		// 根据datagrid查询涉旅机构信息
		CriteriaQuery cq = new CriteriaQuery(OtherTravelInfo.class, dataGrid);
		otherTravelInfo.setType(Status.golf);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				otherTravelInfo);
		// 获取easyUI的datagrid模型
		this.systemService.getDataGridReturn(cq, true);
		// 对原型进行遍历list集合
		System.out.println(dataGrid.getResults().size());
		for (Object o : dataGrid.getResults()) {
			// 对集合元素进行操作
			OtherTravelInfo ot = (OtherTravelInfo) o;
			// 对涉旅机构的月报判断是否为空
			if (ot.getGolfMonthList() != null) {
				List<GolfMonthly> list = ot.getGolfMonthList();
				for (GolfMonthly mothdata : list) {
					// 对月报进行遍历
					if (mothdata.getDates() == null) {
						break;
					}
					// 去当前月报的时间 转换为yyyy-MM格式
					String monthTime = timeUtils.getCurrentMonth(mothdata
							.getDates());
					if (monthTime.equals(lastMonth)) {
						// 将当前月报 加到涉旅机构信息中 在审核表中提现
						// ot.setg;
						ot.setGolfMonth(mothdata);
					}
					// 满足月份 跳出循环
					break;
				}

				// 月报不存在 的情况
				if (ot.getGolfMonth() == null) {
					// t=i++;
					// 创建一个 设置为代填报状态
					GolfMonthly hl = new GolfMonthly();
					hl.setDates(timeUtils.getLastMonthD());
					hl.setStatus(Status.noSumbit);
					ot.setGolfMonth(hl);
					lists.add(ot);
					// this.systemService.save(hl);
				}
			}
		}

		// TagUtil.datagrid(response, dataGrid);
		request.setAttribute("listss", lists);
		return "otherTravel/savegolfDaitianbao";
	}

	/**
	 * 
	 * @date: 2016年12月27日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 代填报多个企业
	 * @return:
	 */
	@ResponseBody
	@RequestMapping(params = "savgmdais")
	public AjaxJson savgmdais(HttpServletRequest req) {
		int a = 1;

		AjaxJson j = new AjaxJson();
		while (true) {
			String aim = "otid" + a;// otid
			String rnum = "receptionnum" + a;// 接待人次
			String bum = "businessincome" + a;// 营业收入
			// String dates="dates"+a;//月报时间
			String reporter = "reporter" + a;// 填报人
			if (null != req.getParameter(aim)
					&& req.getParameter(aim).length() > 0) {
				GolfMonthly golfMonth = new GolfMonthly();
				golfMonth.setOtid(req.getParameter(aim));
				OtherTravelInfo ot = systemService.getEntity(
						OtherTravelInfo.class, golfMonth.getOtid());
				golfMonth.setOtherTravelInfo(ot);
				golfMonth.setReceptionnum(Integer.valueOf(req
						.getParameter(rnum)));
				golfMonth.setBusinessincome(Double.valueOf(req
						.getParameter(bum)));
				golfMonth.setReporter(req.getParameter("reporter"));
				golfMonth.setStatus(Status.passAudit);
				golfMonth.setDates(timeUtils.getLastMonthD());
				// golfMonth.setReporter(ResourceUtil.getSessionUserName().getUserName());
				golfMonth.setWriteDate(new Date());
				golfMonth.setSmonth(timeUtils.getCurrentMonth(golfMonth
						.getDates()));
				systemService.saveOrUpdate(golfMonth);
				a++;
			} else {
				break;
			}

		}
		// 通过工具类获取用户对象

		j.setMsg("上报成功");
		systemService.addLog("代填报高尔夫月报成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}

	@ResponseBody
	@RequestMapping(params = "gdatagrid")
	public void datagrid(GolfMonthly golfMonth, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(GolfMonthly.class, dataGrid);
		// 查询条件组装器
		// com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
		// golfMonth);
		cq.add(Restrictions.eq("status", Status.passAudit));

		String name = golfMonth.getOtherTravelInfo() == null ? "" : golfMonth
				.getOtherTravelInfo().getName();
		if (name != null && name.length() != 0) {

			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_othercompany_info t where t.name like '%"
							+ name + "%')"));
		}
		if (golfMonth.getSmonth() != null
				&& golfMonth.getSmonth().length() != 0) {
			cq.add(Restrictions.like("smonth", "%" + golfMonth.getSmonth()
					+ "%"));
		}

		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new GolfMonthly());
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	// 月报审核列表
	// 涉旅机构基本信息+月报信息,这个月审核上个月的信息
	@RequestMapping(params = "gmonthdatagrid")
	public void monthDatagrid(GolfMonthly golfMonth,
			OtherTravelInfo otherTravelInfo, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		// 获取当月时间yyyy-mm
		String currentMonth = timeUtils.getCurrentMonth(new Date());
		// 获取上月时间
		String lastMonth = timeUtils.getLastMonth();
		// 根据datagrid查询涉旅机构信息
		CriteriaQuery cq = new CriteriaQuery(OtherTravelInfo.class, dataGrid);
		otherTravelInfo.setType(Status.golf);
		String status = otherTravelInfo.getGolfMonth() == null ? ""
				: otherTravelInfo.getGolfMonth().getStatus();
		// String
		// name=otherTravelInfo.getGolfMonth()==null?"":otherTravelInfo.getName();
		if (status != null && status.length() != 0
				&& !status.equals(Status.noSumbit)) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_golf_monthly t where t.status ="
							+ status + " and t.otravel_id=this_.id)"));
		} else if (status.equals(Status.noSumbit)) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and not exists(select * from t_golf_monthly t where t.status <>"
							+ status + " and t.otravel_id=this_.id)"));
		}
		if (otherTravelInfo.getName() != null
				&& otherTravelInfo.getName().length() != 0) {
			cq.add(Restrictions.like("name", "%" + otherTravelInfo.getName()
					+ "%"));
		}
		cq.add(Restrictions.eqOrIsNull("type", Status.golf));
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new OtherTravelInfo());
		// 获取easyUI的datagrid模型
		this.systemService.getDataGridReturn(cq, true);
		// 对原型进行遍历list集合
		System.out.println(dataGrid.getResults().size());
		for (Object o : dataGrid.getResults()) {
			// 对集合元素进行操作
			OtherTravelInfo ot = (OtherTravelInfo) o;
			// 对涉旅机构的月报判断是否为空
			if (ot.getGolfMonthList() != null) {
				List<GolfMonthly> list = ot.getGolfMonthList();
				for (GolfMonthly mothdata : list) {
					// 对月报进行遍历
					if (mothdata.getDates() == null) {
						break;
					}
					// 去当前月报的时间 转换为yyyy-MM格式
					String monthTime = timeUtils.getCurrentMonth(mothdata
							.getDates());
					// 如果是上月时间 则返回审核列表
					// mothdata.getMonth();
					if (monthTime.equals(lastMonth)) {
						// 将当前月报 加到涉旅机构信息中 在审核表中提现
						// ot.setg;
						ot.setGolfMonth(mothdata);
					}
					// 满足月份 跳出循环
					break;
				}
				// 月报不存在 的情况
				if (ot.getGolfMonth() == null) {
					// 创建一个 设置为代填报状态
					GolfMonthly hl = new GolfMonthly();
					hl.setDates(timeUtils.getLastMonthD());
					hl.setStatus(Status.noSumbit);
					ot.setGolfMonth(hl);
					// this.systemService.save(hl);
				}
			}
		}
		// 将datagrid返回给前端页面
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 
	 * @date: 2016年12月15日
	 * @Author:龙亚辉
	 * @Email: 502230926.com
	 * @param: 保存月报
	 * @return:
	 */
	@RequestMapping(params = "savegm")
	@ResponseBody
	public AjaxJson add(GolfMonthly golfMonthly, HttpServletRequest req) {
		String porjectPath = ResourceUtil.getPorjectPath();
		System.out.println(porjectPath);
		AjaxJson j = new AjaxJson();

		if (golfMonthly.getId() == null || golfMonthly.getId().length() == 0) {
			golfMonthly.setId(null);
			String smonth = timeUtils.getCurrentMonth(golfMonthly.getDates());
			golfMonthly.setSmonth(smonth);
			if (this.isFilled(smonth)) {
				j.setMsg("上月已填报月报!");
				return j;
			}

			// 通过工具类获取用户对象
			TSUser user = ResourceUtil.getSessionUserName();
			// id未涉旅机构的id
			golfMonthly.setOtid(user.getId());
			// hotelservice.loadAll(hotelmanage.getClass());
			golfMonthly.setStatus(Status.noAudit);
			golfMonthly.setWriteDate(new Date());
			OtherTravelInfo otherTravelInfo = systemService.getEntity(
					OtherTravelInfo.class, golfMonthly.getOtid());
			golfMonthly.setOtherTravelInfo(otherTravelInfo);
			systemService.saveOrUpdate(golfMonthly);
		} else { // 用户编辑
			Integer receptionnum = golfMonthly.getReceptionnum();
			Double businessincome = golfMonthly.getBusinessincome();
			golfMonthly = systemService.getEntity(GolfMonthly.class,
					golfMonthly.getId());
			golfMonthly.setReceptionnum(receptionnum);
			golfMonthly.setBusinessincome(businessincome);
			golfMonthly.setStatus(Status.noAudit);
			
			systemService.saveOrUpdate(golfMonthly);
		}
		j.setMsg("上报成功");
		systemService.addLog("填报高尔夫月报成功:"+golfMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}

	// 删除月报
	@RequestMapping(params = "delgm")
	@ResponseBody
	public AjaxJson del(GolfMonthly golfMonthly, HttpServletRequest req) {
		String monthId = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			golfMonthly = systemService.getEntity(GolfMonthly.class, monthId);
			systemService.delete(golfMonthly);
			message = "月报删除成功";
		} catch (Exception e) {
			message = "月报删除失败";
		}

		j.setMsg(message);
		return j;
	}

	// 高尔夫月报详情展示

	@RequestMapping(params = "detailgm")
	public ModelAndView detial(HttpServletRequest request,
			GolfMonthly golfMonthly, OtherTravelInfo otherTravelInfo,
			DataGrid dataGrid) {
		String otid = request.getParameter("otid");
		// 季报id
		String gMonthId = request.getParameter("gMonthId");
		// 存到请求域中
		// request.setAttribute("otid", otid);
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		golfMonthly = this.systemService.get(GolfMonthly.class, gMonthId);

		golfMonthly.setOtherTravelInfo(h);

		return new ModelAndView("otherTravel/showGmonthDetail").addObject(
				"golfMonthly", golfMonthly);
	}

	// 季报审核管理员操作
	@RequestMapping(params = "addstatusgm")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			GolfMonthly golfMonthly, OtherTravelInfo otherTravelInfo,
			String statusid) {
		AjaxJson j = new AjaxJson();

		// 季报的id
		String gMonthId = request.getParameter("gMonthId");
		request.setAttribute("gMonthId", gMonthId);
		try {
			if (StringUtil.isNotEmpty(gMonthId)) {
				golfMonthly = systemService.getEntity(GolfMonthly.class,
						gMonthId);

				// 1表示管理员审核通过
				golfMonthly.setStatus(Status.passAudit);
				// golfMonthly.set;
				systemService.saveOrUpdate(golfMonthly);
				// systemService.updateEntitie(scenicMonth);
				j.setMsg("管理员审核成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		systemService.addLog("审核通过高尔夫月报成功:"+golfMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return j;
	}

	@RequestMapping(params = "nocheckgm")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			OtherTravelInfo otherTravelInfo, GolfMonthly golfMonthly,
			String statusid) {
		AjaxJson j = new AjaxJson();
		String gMonthId = request.getParameter("gMonthId");
		request.setAttribute("gMonthId", gMonthId);
		if (StringUtil.isNotEmpty(gMonthId)) {
			golfMonthly = systemService.getEntity(GolfMonthly.class, gMonthId);
			// request.setAttribute("scenicdata", scenicdata);
			golfMonthly.setStatus(Status.notAudit);// 管理员审核未通过
			// golfMonthly.setAuditTime(new Date());
			systemService.save(golfMonthly);
		}
		systemService.addLog("审核退回高尔夫月报成功:"+golfMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return j;
	}

	// 审核页面
	@RequestMapping(params = "auditgm")
	public ModelAndView audit(HttpServletRequest request) {
		String otid = request.getParameter("otid");
		String gMonthId = request.getParameter("gMonthId");
		request.setAttribute("otid", otid);
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		GolfMonthly hq = this.systemService.get(GolfMonthly.class, gMonthId);

		hq.setOtherTravelInfo(h);

		return new ModelAndView("otherTravel/auditGmonth").addObject(
				"golfMonthly", hq);
	}

	@RequestMapping(params = "addsigngm")
	public ModelAndView addsign(HttpServletRequest request) {
		String otid = request.getParameter("otid");
		// 现在为空
		String gMonthId = request.getParameter("gMonthId");
		// 根据基本信息id获取基本信息
		OtherTravelInfo hm = this.systemService
				.get(OtherTravelInfo.class, otid);
		// 根绝月报id获取月报信息
		GolfMonthly s = this.systemService.get(GolfMonthly.class, gMonthId);
		// 将基本信息设置在域中

		if (s == null) {
			s = new GolfMonthly();

		}

		s.setOtid(otid);
		;

		s.setDates(timeUtils.getLastMonthD());
		s.setSmonth(timeUtils.getCurrentMonth(s.getDates()));
		s.setOtherTravelInfo(hm);
		request.setAttribute("otid", otid);
		request.setAttribute("golfMonthly", s);
		return new ModelAndView("otherTravel/daitianbaoGm");
	}

	// 保存
	@RequestMapping(params = "saveDaigm")
	@ResponseBody
	public AjaxJson addDai(GolfMonthly golfMonthly, HttpServletRequest req) {
		String otid = (String) req.getParameter("otid");
		golfMonthly.setOtid(otid);

		AjaxJson j = new AjaxJson();
		// 补全月报信息由

		// TSUser user = ResourceUtil.getSessionUserName();
		// 与涉旅机构的id进行关联

		;
		OtherTravelInfo otherTravelInfo = systemService.getEntity(
				OtherTravelInfo.class, golfMonthly.getOtid());
		golfMonthly.setOtherTravelInfo(otherTravelInfo);
		golfMonthly.setStatus(Status.passAudit);
		// 月报时间 上月时间
		golfMonthly.setDates(timeUtils.getLastMonthD());
		golfMonthly.setWriteDate(new Date());
		golfMonthly
				.setSmonth(timeUtils.getCurrentMonth(golfMonthly.getDates()));
		String gid = golfMonthly.getId();
		if (gid.length() == 0) {
			systemService.save(golfMonthly);
		} else {

			systemService.saveOrUpdate(golfMonthly);
		}

		j.setMsg("数据上报成功");
		systemService.addLog("代填报高尔夫月报成功:"+golfMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}

	// zancun
	@RequestMapping(params = "zancuncheckgm")
	@ResponseBody
	public AjaxJson saveZanStatus(HttpServletRequest request,
			GolfMonthly golfMonthly, String statusid) {
		AjaxJson j = new AjaxJson();
		String otid = request.getParameter("otid");
		request.setAttribute("otid", otid);
		// 根据id获取涉旅机构信息实体类
		OtherTravelInfo otherTravelInfo = this.systemService.getEntity(
				OtherTravelInfo.class, otid);
		// 将基本信息添加到月报信息中
		golfMonthly.setOtid(otid);
		golfMonthly.setOtherTravelInfo(otherTravelInfo);
		;
		golfMonthly.setDates(timeUtils.getLastMonthD());
		golfMonthly
				.setSmonth(timeUtils.getCurrentMonth(golfMonthly.getDates()));
		// 状态仍为未填报
		golfMonthly.setStatus(Status.noSumbit);
		// 更新
		request.setAttribute("golfMonthly", golfMonthly);
		String gid = golfMonthly.getId();
		if (gid.length() == 0) {
			systemService.save(golfMonthly);
		} else {

			systemService.saveOrUpdate(golfMonthly);
		}
		j.setMsg("上报成功");
		systemService.addLog("暂存高尔夫月报成功:"+golfMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return j;
	}

	@RequestMapping(params = "exportsxls")
	public String exportXls(GolfMonthly golfMonth, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(GolfMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, golfMonth,
				request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		// String hotelId = ResourceUtil.getSessionUserName().getId();
		List<GolfMonthly> golfMonthlys = this.systemService
				.getList(GolfMonthly.class);
		map.put(NormalExcelConstants.FILE_NAME, "高尔夫月报信息");
		map.put(NormalExcelConstants.CLASS, GolfMonthly.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("高尔夫月报列表",
				"导出人:管理员", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, golfMonthlys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	// **************************上高尔夫管理员界面,下面是高尔夫客户界面**********************************88
	@RequestMapping(params = "ckgolf")
	public String user(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		// List<TSDepart> departList = systemService.getList(TSDepart.class);
		// request.setAttribute("departsReplace",
		// RoletoJson.listToReplaceStr(departList, "departname", "id"));
		return "otherTravelClient/ckgolfMonthlylList";
	}

	/**
	 * 
	 * @date: 2016年12月19日
	 * @Author: 龙亚辉
	 * @Email: lkkeep@163.com
	 * @param:
	 * @return:gcdatagrid 指的是返回的是高尔夫用户月报信息 即用户本身的月报
	 */
	@ResponseBody
	@RequestMapping(params = "gcdatagrid")
	public void datagridClient(GolfMonthly golfMonth,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		// 1.从session中获取当前用户的 id
		String otid = ResourceUtil.getSessionUserName().getId();
		// System.out.println(otid);
		golfMonth.setOtid(otid);
		CriteriaQuery cq = new CriteriaQuery(GolfMonthly.class, dataGrid);
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, golfMonth);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		// List<OtherTravelInfo> cfeList = new ArrayList<OtherTravelInfo>();
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 
	 * @date: 2016年12月19日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:
	 */
	@RequestMapping(params = "exportsxlss")
	public String exportXlss(GolfMonthly golfMonth, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(GolfMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, golfMonth,
				request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		String otId = ResourceUtil.getSessionUserName().getId();
		List<GolfMonthly> golfMonthlys = this.systemService
				.findHql(
						"from com.zzc.web.otherTravel.entity.GolfMonthly th where th.otid=?",
						otId);
		map.put(NormalExcelConstants.FILE_NAME, "高尔夫月报信息");
		map.put(NormalExcelConstants.CLASS, GolfMonthly.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("高尔夫月报列表",
				"导出人:高尔夫用户", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, golfMonthlys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	/**
	 * 导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportGolf")
	public void exportGolf(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取参数
		String name = new String(request.getParameter("name").getBytes(
				"iso8859-1"), "utf-8");
		String status = request.getParameter("status");

		String excelName = "高尔夫月报统计.xls";
		String modelPath = "/com/zzc/web/export/model/otherTravel/" + excelName;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	othercompany.`name`, ");
		sql.append(" IF(golf.`status`='1','未填报',IF(golf.`status`='2','待审核',IF(golf.`status`='3','未通过',IF(golf.`status`='4','已通过','')))) `status`, ");
		sql.append(" 	golf.reception_num `recep_num`, ");
		sql.append(" 	golf.business_income `income`, ");
		sql.append(" golf.write_date ");
		sql.append(" FROM ");
		sql.append(" 	t_golf_monthly golf ");
		sql.append(" LEFT JOIN t_othercompany_info othercompany ON golf.otravel_id = othercompany.id where 1=1 and golf.status='4'  ");
		// 组装查询条件
		if (name != null && name.length() != 0) {
			sql.append(" and othercompany.`name` like '%" + name + "%'");
		}
		if (status != null && status.length() != 0) {
			sql.append(" and golf.status = '" + status + "'");
		}
		sql.append(" union all ");
		sql.append(" SELECT ");
		sql.append(" 	'总计' `name`, ");
		sql.append(" '' `status`, ");
		sql.append(" 	sum(golf.reception_num) `recep_num`, ");
		sql.append(" 	sum(golf.business_income) `income`, ");
		sql.append(" '' `write_date` ");
		sql.append(" FROM ");
		sql.append(" 	t_golf_monthly golf ");
		sql.append(" LEFT JOIN t_othercompany_info othercompany ON golf.otravel_id = othercompany.id and golf.status='4'  ");
		if (name != null && name.length() != 0) {
			sql.append(" and othercompany.`name` like '%" + name + "%'");
		}
		if (status != null && status.length() != 0) {
			sql.append(" and golf.status = '" + status + "'");
		}
		ExportService emds = new ExportService(5);

		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
		} catch (Exception e) {

		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(excelName, "UTF-8"));
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
	 * 检查上月报是否填报
	 * 
	 * @return
	 */
	public boolean isFilled(String smonth) {
		boolean tip = false;
		// 获取当前登录用户ID=涉旅ID
		String userId = ResourceUtil.getSessionUserName().getId();

		String sql = "select id from t_golf_monthly t where t.smonth like '"
				+ smonth + "' and t.otravel_id = '" + userId + "'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if (list.size() != 0)
			tip = true;
		return tip;
	}

}
