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
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

@Scope("prototype")
@Controller
@RequestMapping("/boatMonthlyController")
public class BoatMonthlyController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(BoatMonthlyController.class);

	// 调用时间工具
	TimeUtils timeUtils = new TimeUtils();
	private SystemService systemService;
	private String message = null;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(params = "addbm")
	public ModelAndView addbm(HttpServletRequest req) {
		String otid = ResourceUtil.getSessionUserName().getId();
		OtherTravelInfo ot = this.systemService.getEntity(
				OtherTravelInfo.class, otid);
		req.setAttribute("otherTravelInfo", ot);
		String lastMonth = timeUtils.getLastmonth();

		req.setAttribute("lastMonth", lastMonth);
		return new ModelAndView("otherTravel/savebm");
	}

	// 月报跳转页面
	@RequestMapping(params = "boatMonth")
	public ModelAndView rolel() {
		return new ModelAndView("otherTravel/boatMonthlylList");
	}

	// 跳转到月报审核页面
	@RequestMapping(params = "boatMonthCheck")
	public ModelAndView hotelMonthCheckbm() {
		return new ModelAndView("otherTravel/boatMonthlyCheckList");
	}

	/**
	 * 
	 * @date: 2016年12月30日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:跳转到到填报(代填报多个企业)
	 */
	@RequestMapping(params = "daitianbao")
	public String monthDaitianbao(BoatMonthly boatMonthly,
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
		otherTravelInfo.setType(Status.boat);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				otherTravelInfo);
		// 获取easyUI的datagrid模型
		this.systemService.getDataGridReturn(cq, true);
		// 对原型进行遍历list集合

		for (Object o : dataGrid.getResults()) {
			// 对集合元素进行操作
			OtherTravelInfo ot = (OtherTravelInfo) o;
			// 对涉旅机构的月报判断是否为空
			if (ot.getGolfMonthList() != null) {
				List<BoatMonthly> list = ot.getBoatMonthList();
				for (BoatMonthly mothdata : list) {
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
						ot.setBoatMonth(mothdata);
					}
					// 满足月份 跳出循环
					break;
				}

				// 月报不存在 的情况
				if (ot.getBoatMonth() == null) {
					// t=i++;
					// 创建一个 设置为代填报状态
					BoatMonthly hl = new BoatMonthly();
					hl.setDates(timeUtils.getLastMonthD());
					hl.setStatus(Status.noSumbit);
					ot.setBoatMonth(hl);
					lists.add(ot);

				}
			}
		}

		// TagUtil.datagrid(response, dataGrid);
		request.setAttribute("listss", lists);
		return "otherTravel/saveboatDaitianbao";
	}
	//游艇数据列表
	@ResponseBody
	@RequestMapping(params = "bdatagrid")
	public void datagrid(BoatMonthly boatMonthly, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BoatMonthly.class, dataGrid);
		// 查询条件组装器

		cq.add(Restrictions.eq("status", Status.passAudit));
		String name = boatMonthly.getOtherTravelInfo() == null ? ""
				: boatMonthly.getOtherTravelInfo().getName();
		if (name != null && name.length() != 0) {

			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_othercompany_info t where t.name like '%"
							+ name + "%' and t.id=otravel_id)"));
		}
		if (boatMonthly.getSmonth() != null
				&& boatMonthly.getSmonth().length() != 0) {
			cq.add(Restrictions.like("smonth", "%" + boatMonthly.getSmonth()
					+ "%"));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new BoatMonthly());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	// 月报审核列表
	// 涉旅机构基本信息+月报信息,这个月审核上个月的信息
	@RequestMapping(params = "bmonthdatagrid")
	public void monthDatagrid(BoatMonthly boatMonthly,
			OtherTravelInfo otherTravelInfo, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		// 获取当月时间yyyy-mm
		String currentMonth = timeUtils.getCurrentMonth(new Date());
		// 获取上月时间
		String lastMonth = timeUtils.getLastMonth();
		// 根据datagrid查询涉旅机构信息
		CriteriaQuery cq = new CriteriaQuery(OtherTravelInfo.class, dataGrid);
		otherTravelInfo.setType(Status.boat);
		String status = otherTravelInfo.getBoatMonth() == null ? ""
				: otherTravelInfo.getBoatMonth().getStatus();
		// otherTravelInfo.getBoatMonth().setStatus(status);
		if (status != null && status.length() != 0
				&& !status.equals(Status.noSumbit)) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_boat_monthly  t where t.status ="
							+ status + " and t.otravel_id=this_.id)"));
		} else if (status.equals(Status.noSumbit)) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and not exists(select * from t_boat_monthly t  where t.status<>"
							+ status + " and t.otravel_id=this_.id)"));
		}
		if (otherTravelInfo.getName() != null
				&& otherTravelInfo.getName().length() != 0) {
			cq.add(Restrictions.like("name", "%" + otherTravelInfo.getName()
					+ "%"));
		}
		cq.add(Restrictions.eqOrIsNull("type", Status.boat));

		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new OtherTravelInfo());

		otherTravelInfo.setBoatMonth(null);

		// 获取easyUI的datagrid模型
		this.systemService.getDataGridReturn(cq, true);
		// 对原型进行遍历list集合
		System.out.println(dataGrid.getResults().size());
		for (Object o : dataGrid.getResults()) {
			// 对集合元素进行操作
			OtherTravelInfo ot = (OtherTravelInfo) o;
			// 对涉旅机构的月报判断是否为空
			if (ot.getGolfMonthList() != null) {
				List<BoatMonthly> list = ot.getBoatMonthList();
				for (BoatMonthly mothdata : list) {
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
						ot.setBoatMonth(mothdata);
					}
					// 满足月份 跳出循环
					break;
				}
				// 月报不存在 的情况
				if (ot.getBoatMonth() == null) {
					// 创建一个 设置为代填报状态
					BoatMonthly hl = new BoatMonthly();
					hl.setDates(timeUtils.getLastMonthD());
					hl.setStatus(Status.noSumbit);
					ot.setBoatMonth(hl);
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
	@RequestMapping(params = "savebm")
	@ResponseBody
	public AjaxJson addbm(BoatMonthly boatMonthly, HttpServletRequest req) {

		AjaxJson j = new AjaxJson();
		// 通过工具类获取用户对象
		TSUser user = ResourceUtil.getSessionUserName();
		// id未涉旅机构的id

		if (boatMonthly.getId() == null || boatMonthly.getId().length() == 0) {
			String smonth = timeUtils.getCurrentMonth(boatMonthly.getDates());
			boatMonthly.setSmonth(smonth);
			boatMonthly.setId(null);
			if (this.isFilled(smonth)) {
				j.setMsg("上月已填报月报!");
				return j;
			}
			boatMonthly.setOtid(user.getId());
			boatMonthly.setStatus(Status.noAudit);
			OtherTravelInfo otherTravelInfo = systemService.getEntity(
					OtherTravelInfo.class, boatMonthly.getOtid());
			boatMonthly.setOtherTravelInfo(otherTravelInfo);
			boatMonthly.setWriteDate(new Date());
			systemService.saveOrUpdate(boatMonthly);
			System.out.println("添加成功");

			j.setMsg("上报成功");
		} else {
			Double income = boatMonthly.getIncome();
			Integer recepNum = boatMonthly.getRecepNum();
			boatMonthly = systemService.getEntity(BoatMonthly.class,
					boatMonthly.getId());
			boatMonthly.setIncome(income);
			boatMonthly.setRecepNum(recepNum);
			boatMonthly.setStatus(Status.noAudit);
			systemService.saveOrUpdate(boatMonthly);
			System.out.println("添加成功");

			j.setMsg("上报成功");
		}
		
		systemService.addLog("填报游艇月报成功:"+boatMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		return j;
	}

	// 删除月报
	@RequestMapping(params = "delbm")
	@ResponseBody
	public AjaxJson delbm(BoatMonthly boatMonthly, HttpServletRequest req) {
		String monthId = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			boatMonthly = systemService.getEntity(BoatMonthly.class, monthId);
			systemService.delete(boatMonthly);
			message = "月报删除成功";
		} catch (Exception e) {
			message = "月报删除失败";
		}

		j.setMsg(message);
		return j;
	}

	// 季报详情展示

	@RequestMapping(params = "detailbm")
	public ModelAndView detialbm(HttpServletRequest request,
			BoatMonthly boatMonthly, OtherTravelInfo otherTravelInfo,
			DataGrid dataGrid) {
		String otid = request.getParameter("otid");
		// 季报id
		String bmonthId = request.getParameter("bmonthId");
		// 存到请求域中
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		boatMonthly = this.systemService.get(BoatMonthly.class, bmonthId);
		boatMonthly.setOtherTravelInfo(h);

		return new ModelAndView("otherTravel/showbmonthDetail").addObject(
				"boatMonthly", boatMonthly);
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
		String bmonthId = request.getParameter("bmonthId");
		// 存到请求域中
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		BoatMonthly boatMonthly = this.systemService.get(BoatMonthly.class,
				bmonthId);
		boatMonthly.setOtherTravelInfo(h);

		return new ModelAndView("otherTravelClient/edit/editBoatMonthly")
				.addObject("boatMonthly", boatMonthly);
	}

	// 游艇月报审核管理员操作
	@RequestMapping(params = "addstatusbm")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			BoatMonthly boatMonthly, OtherTravelInfo otherTravelInfo,
			String statusid) {
		AjaxJson j = new AjaxJson();

		// 季报的id
		String bmonthId = request.getParameter("bmonthId");
		request.setAttribute("bmonthId", bmonthId);
		try {
			if (StringUtil.isNotEmpty(bmonthId)) {
				boatMonthly = systemService.getEntity(BoatMonthly.class,
						bmonthId);

				// 1表示管理员审核通过
				boatMonthly.setStatus(Status.passAudit);
				// boatMonthly.set;
				systemService.saveOrUpdate(boatMonthly);
				// systemService.updateEntitie(scenicMonth);
				j.setMsg("管理员审核成功");
				systemService.addLog("审核通过游艇月报成功:"+boatMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			systemService.addLog("审核通过游艇月报失败:"+bmonthId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}

		return j;
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
	@RequestMapping(params = "savbmdais")
	public AjaxJson savgmdais(HttpServletRequest req) {
		int a = 1;

		AjaxJson j = new AjaxJson();
		while (true) {
			String aim = "otid" + a;// otid
			String rnum = "recepNum" + a;// 接待人次
			String bum = "income" + a;// 营业收入
			String reporter = "reporter" + a;// 填报人
			// String dates="dates"+a;//月报时间

			if (null != req.getParameter(aim)
					&& req.getParameter(aim).length() > 0) {
				BoatMonthly boatMonth = new BoatMonthly();
				boatMonth.setOtid(req.getParameter(aim));
				OtherTravelInfo ot = systemService.getEntity(
						OtherTravelInfo.class, boatMonth.getOtid());
				boatMonth.setOtherTravelInfo(ot);
				boatMonth.setRecepNum(Integer.valueOf(req.getParameter(rnum)));
				boatMonth.setIncome(Double.valueOf(req.getParameter(bum)));
				boatMonth.setReporter(req.getParameter(reporter));
				boatMonth.setStatus(Status.passAudit);
				boatMonth.setDates(timeUtils.getLastMonthD());
				boatMonth.setSmonth(timeUtils.getCurrentMonth(boatMonth
						.getDates()));
				// boatMonth.setReporter(ResourceUtil.getSessionUserName().getUserName());
				boatMonth.setWriteDate(new Date());
				systemService.saveOrUpdate(boatMonth);
				a++;
			} else {
				break;
			}

		}
		// 通过工具类获取用户对象

		j.setMsg("上报成功");
		systemService.addLog("代填报游艇月报成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}

	// 审核不通过
	@RequestMapping(params = "nocheckbm")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			OtherTravelInfo otherTravelInfo, BoatMonthly boatMonthly,
			String statusid) {
		AjaxJson j = new AjaxJson();
		String bmonthId = request.getParameter("bmonthId");
		request.setAttribute("bmonthId", bmonthId);
		if (StringUtil.isNotEmpty(bmonthId)) {
			boatMonthly = systemService.getEntity(BoatMonthly.class, bmonthId);
			// request.setAttribute("scenicdata", scenicdata);
			boatMonthly.setStatus(Status.notAudit);// 管理员审核未通过
			// boatMonthly.setAuditTime(new Date());
			systemService.save(boatMonthly);
			systemService.addLog("审核退回游艇月报成功:"+boatMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}

		return j;
	}

	// 审核页面
	@RequestMapping(params = "auditbm")
	public ModelAndView audit(HttpServletRequest request,
			BoatMonthly boatMonthly, OtherTravelInfo otherTravelInfo,
			DataGrid dataGrid) {
		String otid = request.getParameter("otid");
		String bmonthId = request.getParameter("bmonthId");
		request.setAttribute("otid", otid);
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		BoatMonthly hq = this.systemService.get(BoatMonthly.class, bmonthId);

		// request.setAttribute("scenicdata", s);
		hq.setOtherTravelInfo(h);
		// request.setAttribute("monthdata", m);
		// scenicSpotMonth.setScenicData(s);
		// request.setAttribute("scenicSpotMonth",scenicSpotMonth);

		return new ModelAndView("otherTravel/auditbmonth").addObject(
				"boatMonthly", hq);
	}
//返回审核页面
	@RequestMapping(params = "addsignbm")
	public ModelAndView addsigns(HttpServletRequest request) {
		String otid = request.getParameter("otid");
		// 现在为空
		String bmonthId = request.getParameter("bmonthId");
		// hid=hotelmanageId;
		// System.out.println(hid);
		// request.setAttribute("hotelmanageId", hotelmanageId);
		// request.setAttribute("quarterId", quarterId);
		// 根据基本信息id获取基本信息
		OtherTravelInfo hm = this.systemService
				.get(OtherTravelInfo.class, otid);
		// 根绝月报id获取月报信息
		BoatMonthly s = this.systemService.get(BoatMonthly.class, bmonthId);
		// 将基本信息设置在域中

		// request.setAttribute("hotelmanage", hm);
		if (s == null) {
			s = new BoatMonthly();

		}

		s.setOtid(otid);
		;
		s.setDates(timeUtils.getLastMonthD());
		s.setSmonth(timeUtils.getCurrentMonth(s.getDates()));
		s.setOtherTravelInfo(hm);
		request.setAttribute("otid", otid);
		request.setAttribute("boatMonthly", s);
		// this.saveScenic(request, scenicSpotMonth);
		return new ModelAndView("otherTravel/daitianbaobm");
	}

	// 保存
	@RequestMapping(params = "saveDaibm")
	@ResponseBody
	public AjaxJson addDai(BoatMonthly boatMonthly, HttpServletRequest req) {

		AjaxJson j = new AjaxJson();
		// 补全月报信息由
		// 与涉旅机构的id进行关联
		// System.out.println(boatMonthly.getOtid());
		OtherTravelInfo otherTravelInfo = systemService.getEntity(
				OtherTravelInfo.class, boatMonthly.getOtid());
		boatMonthly.setOtherTravelInfo(otherTravelInfo);
		;
		boatMonthly.setDates(timeUtils.getLastMonthD());
		boatMonthly.setStatus(Status.passAudit);
		boatMonthly
				.setSmonth(timeUtils.getCurrentMonth(boatMonthly.getDates()));
		boatMonthly.setWriteDate(new Date());
		String bid = boatMonthly.getId();
		if (bid.length() == 0) {
			systemService.save(boatMonthly);
		} else {

			systemService.saveOrUpdate(boatMonthly);
		}

		j.setMsg("数据上报成功");
		systemService.addLog("代填报游艇月报成功:"+boatMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}

	// 暂存
	@RequestMapping(params = "zancuncheckbm")
	@ResponseBody
	public AjaxJson saveZanStatus(HttpServletRequest request,
			BoatMonthly boatMonthly, String statusid) {
		AjaxJson j = new AjaxJson();
		// 这里的otid指的是涉旅机构的id
		String otid = request.getParameter("otid");
		request.setAttribute("otid", otid);
		// 根据id获取涉旅机构信息实体类
		OtherTravelInfo otherTravelInfo = this.systemService.getEntity(
				OtherTravelInfo.class, otid);
		// 将基本信息添加到月报信息中
		boatMonthly.setOtid(otid);
		boatMonthly.setOtherTravelInfo(otherTravelInfo);
		;
		boatMonthly.setDates(timeUtils.getLastMonthD());
		boatMonthly
				.setSmonth(timeUtils.getCurrentMonth(boatMonthly.getDates()));
		// 状态仍为未填报
		boatMonthly.setStatus(Status.noSumbit);
		// 更新
		String bid = boatMonthly.getId();
		if (bid.length() == 0) {
			systemService.save(boatMonthly);
		} else {

			systemService.saveOrUpdate(boatMonthly);
		}
		systemService.addLog("暂存游艇月报成功:"+boatMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return j;
	}
	//导出游艇月报
	@RequestMapping(params = "exportsxls")
	public String exportXls(BoatMonthly boatMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(BoatMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				boatMonthly, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		String hotelId = ResourceUtil.getSessionUserName().getId();
		List<BoatMonthly> boatMonthlys = this.systemService
				.findHql(
						"from com.zzc.web.otherTravel.entity.BoatMonthly th where th.otid=? ",
						hotelId);
		map.put(NormalExcelConstants.FILE_NAME, "游艇月报信息");
		map.put(NormalExcelConstants.CLASS, BoatMonthly.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("游艇月报列表",
				"导出人:游艇用户", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, boatMonthlys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	// **************************************上游艇管理员后台,下是游艇客户后台***********************************
	
	@RequestMapping(params = "ckboat")
	public String user(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		// List<TSDepart> departList = systemService.getList(TSDepart.class);
		// request.setAttribute("departsReplace",
		// RoletoJson.listToReplaceStr(departList, "departname", "id"));
		return "otherTravelClient/ckboatMonthlylList";
	}
	//客户端列表
	@ResponseBody
	@RequestMapping(params = "bcdatagrid")
	public void datagridClient(BoatMonthly boatMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		// 1.从session中获取当前用户的 id
		String otid = ResourceUtil.getSessionUserName().getId();
		// System.out.println(otid);
		boatMonthly.setOtid(otid);
		CriteriaQuery cq = new CriteriaQuery(BoatMonthly.class, dataGrid);
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				boatMonthly);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		// List<OtherTravelInfo> cfeList = new ArrayList<OtherTravelInfo>();
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "exportsxlss")
	public String exportXlss(BoatMonthly boatMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(BoatMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				boatMonthly, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		String otId = ResourceUtil.getSessionUserName().getId();
		List<BoatMonthly> boatMonthlys = this.systemService
				.findHql(
						"from com.zzc.web.otherTravel.entity.BoatMonthly th where th.otid=? ",
						otId);
		map.put(NormalExcelConstants.FILE_NAME, "游艇月报信息");
		map.put(NormalExcelConstants.CLASS, BoatMonthly.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("游艇月报列表",
				"导出人:用户", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, boatMonthlys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	/**
	 * 导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportBoat")
	public void exportBoat(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取参数
		String name = new String(request.getParameter("name").getBytes(
				"iso8859-1"), "utf-8");
		String status = request.getParameter("status");

		String excelName = "游艇月报统计.xls";
		String modelPath = "/com/zzc/web/export/model/otherTravel/" + excelName;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	othercompany.`name`, ");
		sql.append(" IF(boatmonth.`status`='1','未填报',IF(boatmonth.`status`='2','待审核',IF(boatmonth.`status`='3','未通过',IF(boatmonth.`status`='4','已通过','')))) `status`, ");
		sql.append(" 	boatmonth.recep_num `recep_num`, ");
		sql.append(" 	boatmonth.income `income`, ");
		sql.append(" 	boatmonth.write_date ");
		sql.append(" FROM ");
		sql.append(" 	t_boat_monthly boatmonth ");
		sql.append(" LEFT JOIN t_othercompany_info othercompany ON boatmonth.otravel_id = othercompany.id where 1=1 and  boatmonth.status='4'");
		// 组装查询条件
		if (name != null && name.length() != 0) {
			sql.append(" and othercompany.`name` like '%" + name + "%'");
		}
		if (status != null && status.length() != 0) {
			sql.append(" and boatmonth.status = '" + status + "'");
		}
		sql.append(" union all ");
		sql.append(" SELECT ");
		sql.append(" 	'总计' `name`, ");
		sql.append(" ''  `status`, ");
		sql.append(" 	sum(boatmonth.recep_num) `recep_num`, ");
		sql.append(" 	sum(boatmonth.income) `income`, ");
		sql.append(" '' `write_date` ");
		sql.append(" FROM ");
		sql.append(" 	t_boat_monthly boatmonth ");
		sql.append(" LEFT JOIN t_othercompany_info othercompany ON boatmonth.otravel_id = othercompany.id where 1=1 and boatmonth.status='4' ");
		if (name != null && name.length() != 0) {
			sql.append(" and othercompany.`name` like '%" + name + "%'");
		}
		if (status != null && status.length() != 0) {
			sql.append(" and boatmonth.status = '" + status + "'");
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

		String sql = "select id from t_boat_monthly t where t.smonth like '"
				+ smonth + "' and t.otravel_id = '" + userId + "'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if (list.size() != 0)
			tip = true;
		return tip;
	}

}