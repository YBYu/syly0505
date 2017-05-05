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
import com.zzc.web.otherTravel.entity.AirportMonthly;
import com.zzc.web.otherTravel.entity.GolfMonthly;
import com.zzc.web.otherTravel.entity.MotorcarMonthly;
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

@Scope("prototype")
@Controller
@RequestMapping("/motorcarMonthlyController")
public class MotorcarMonthlyController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(MotorcarMonthlyController.class);

	private String message = null;
	private SystemService systemService;
	TimeUtils timeUtils = new TimeUtils();

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(params = "addcm")
	public ModelAndView addGm(HttpServletRequest req) {
		String otid = ResourceUtil.getSessionUserName().getId();
		OtherTravelInfo ot = this.systemService.getEntity(
				OtherTravelInfo.class, otid);
		req.setAttribute("otherTravelInfo", ot);
		String lastMonth = timeUtils.getLastmonth();

		req.setAttribute("lastMonth", lastMonth);
		return new ModelAndView("otherTravel/savecm");
	}

	/**
	 * 转跳到 动车月报页面
	 */
	@RequestMapping(params = "motorcarMonth")
	public ModelAndView motorcarMonth() {
		return new ModelAndView("otherTravel/motorcarMonthlyList");
	}

	// 跳转到月报审核页面
	@RequestMapping(params = "motorcarMonthCheck")
	public ModelAndView hotelMonthCheck() {
		return new ModelAndView("otherTravel/motorMonthlyCheckList");
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
		// 月报id
		String cmonthId = request.getParameter("cmonthId");
		// 存到请求域中
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		MotorcarMonthly motorcarMonthly = this.systemService.get(
				MotorcarMonthly.class, cmonthId);

		motorcarMonthly.setOtherTravelInfo(h);

		return new ModelAndView("otherTravelClient/edit/editCRHMonthly").addObject(
				"motorcarMonthly", motorcarMonthly);
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
	public String monthDaitianbao(MotorcarMonthly motorcarMonthly,
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
		otherTravelInfo.setType(Status.motorcar);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				otherTravelInfo);
		// 获取easyUI的datagrid模型
		this.systemService.getDataGridReturn(cq, true);
		// 对原型进行遍历list集合

		for (Object o : dataGrid.getResults()) {
			// 对集合元素进行操作
			OtherTravelInfo ot = (OtherTravelInfo) o;
			// 对涉旅机构的月报判断是否为空
			if (ot.getMotorcarMonthList() != null) {
				List<MotorcarMonthly> list = ot.getMotorcarMonthList();
				for (MotorcarMonthly mothdata : list) {
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
						ot.setMotorcarMonth(mothdata);
					}
					// 满足月份 跳出循环
					break;
				}

				// 月报不存在 的情况
				if (ot.getMotorcarMonth() == null) {

					// 创建一个 设置为代填报状态
					MotorcarMonthly hl = new MotorcarMonthly();
					hl.setDates(timeUtils.getLastMonthD());
					hl.setStatus(Status.noSumbit);
					ot.setMotorcarMonth(hl);
					lists.add(ot);

				}
			}
		}
		request.setAttribute("listss", lists);
		return "otherTravel/savemotorcarDaitianbao";
	}

	/**
	 * 
	 * @date: 2016年12月29日
	 * @Author:
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:管理月表
	 */
	@RequestMapping(params = "dataGrid")
	@ResponseBody
	public void dataGrid(MotorcarMonthly motorcarMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MotorcarMonthly.class, dataGrid);

		cq.add(Restrictions.eq("status", Status.passAudit));
		String name = motorcarMonthly.getOtherTravelInfo() == null ? ""
				: motorcarMonthly.getOtherTravelInfo().getName();
		if (name != null && name.length() != 0) {

			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_othercompany_info t where t.name like '%"
							+ name + "%' and t.id=otravel_id)"));
		}
		if (motorcarMonthly.getSmonth() != null
				&& motorcarMonthly.getSmonth().length() != 0) {
			cq.add(Restrictions.like("smonth",
					"%" + motorcarMonthly.getSmonth() + "%"));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new MotorcarMonthly());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
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
	@RequestMapping(params = "savcmdais")
	public AjaxJson savgmdais(HttpServletRequest req) {
		int a = 1;

		AjaxJson j = new AjaxJson();
		while (true) {
			String aim="otid"+a;//otid
			String  rnum ="innum"+a;//接待人次
			String bum ="outnum"+a;//出站人数
			String tnum="throughput"+a;//吞吐量
			String reporter="reporter"+a;//填报人
			if (null != req.getParameter(aim)
					&& req.getParameter(aim).length() > 0) {
				MotorcarMonthly motorcarMonth = new MotorcarMonthly();
				motorcarMonth.setOtid(req.getParameter(aim));
				OtherTravelInfo ot = systemService.getEntity(
						OtherTravelInfo.class, motorcarMonth.getOtid());
				motorcarMonth.setOtherTravelInfo(ot);
				motorcarMonth.setInnum(req.getParameter(rnum));
				motorcarMonth.setOutnum(req.getParameter(bum));
				motorcarMonth.setThroughput(req.getParameter(tnum));
				motorcarMonth.setReporter(req.getParameter(reporter));
				motorcarMonth.setStatus(Status.passAudit);
				motorcarMonth.setDates(timeUtils.getLastMonthD());
				motorcarMonth.setSmonth(timeUtils.getCurrentMonth(motorcarMonth
						.getDates()));
				// motorcarMonth.setReporter(ResourceUtil.getSessionUserName().getUserName());
				motorcarMonth.setWriteDate(new Date());
				systemService.saveOrUpdate(motorcarMonth);
				a++;
				j.setMsg("上报成功");
			} else {
				break;
			}

		}
		// 通过工具类获取用户对象
		systemService.addLog("代填报动车月报成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}

	// 月报审核列表
	// 机场基本信息+月报信息,这个月审核上个月的信息
	@RequestMapping(params = "cmonthdatagrid")
	public void monthDatagrid(MotorcarMonthly motorcarMonthly,
			OtherTravelInfo otherTravelInfo, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		// 获取当月时间yyyy-mm
		String currentMonth = timeUtils.getCurrentMonth(new Date());
		// 获取上月时间
		String lastMonth = timeUtils.getLastMonth();
		// 根据datagrid查询机场信息
		CriteriaQuery cq = new CriteriaQuery(OtherTravelInfo.class, dataGrid);
		otherTravelInfo.setType(Status.motorcar);
		String status = otherTravelInfo.getMotorcarMonth() == null ? ""
				: otherTravelInfo.getMotorcarMonth().getStatus();
		if (status != null && status.length() != 0
				&& !status.equals(Status.noSumbit)) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and exists(select * from t_motorcar_monthly t where t.status ="
							+ status + " and t.otravel_id=this_.id)"));
		} else if (status.equals(Status.noSumbit)) {
			cq.add(Restrictions
					.sqlRestriction(" 1=1 and not exists(select * from t_motorcar_monthly t where t.status <>"
							+ status + " and t.otravel_id=this_.id)"));
		}
		otherTravelInfo.setMotorcarMonth(null);
		if (otherTravelInfo.getName() != null
				&& otherTravelInfo.getName().length() != 0) {
			cq.add(Restrictions.like("name", "%" + otherTravelInfo.getName()
					+ "%"));
		}
		cq.add(Restrictions.eqOrIsNull("type", Status.motorcar));

		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new OtherTravelInfo());
		// 获取easyUI的datagrid模型
		this.systemService.getDataGridReturn(cq, true);
		// 对原型进行遍历list集合

		for (Object o : dataGrid.getResults()) {
			// 对集合元素进行操作
			OtherTravelInfo ot = (OtherTravelInfo) o;
			// 对机场的月报判断是否为空
			if (ot.getMotorcarMonthList() != null) {
				List<MotorcarMonthly> list = ot.getMotorcarMonthList();
				for (MotorcarMonthly mothdata : list) {
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
						// 将当前月报 加到机场信息中 在审核表中提现
						// ot.setg;
						System.out.println(mothdata.toString());
						ot.setMotorcarMonth(mothdata);
					}
					// 满足月份 跳出循环
					break;
				}
				// 月报不存在 的情况
				if (ot.getMotorcarMonth() == null) {
					// 创建一个 设置为代填报状态
					MotorcarMonthly hl = new MotorcarMonthly();
					hl.setDates(timeUtils.getLastMonthD());
					hl.setStatus(Status.noSumbit);
					ot.setMotorcarMonth(hl);
					// this.systemService.save(hl);
				}
			}
		}
		// 将datagrid返回给前端页面
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "savecm")
	@ResponseBody
	public AjaxJson add(MotorcarMonthly motorcarMonthly, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		// 通过工具类获取用户对象
		TSUser user = ResourceUtil.getSessionUserName();
		
		if (motorcarMonthly.getId() == null
				|| motorcarMonthly.getId().length() == 0) {
			motorcarMonthly.setId(null);
			// id未机场的id
			String smonth = timeUtils.getCurrentMonth(motorcarMonthly.getDates());
			motorcarMonthly.setSmonth(smonth);
			if (this.isFilled(smonth)) {
				j.setMsg("上月已填报月报!");
				return j;
			}

			motorcarMonthly.setOtid(user.getId());
			motorcarMonthly.setStatus(Status.noAudit);
			OtherTravelInfo otherTravelInfo = systemService.getEntity(
					OtherTravelInfo.class, motorcarMonthly.getOtid());
			motorcarMonthly.setOtherTravelInfo(otherTravelInfo);
			motorcarMonthly.setWriteDate(new Date());
			systemService.save(motorcarMonthly);
		}else{ // 编辑
			String innum = motorcarMonthly.getInnum();
			String outnum = motorcarMonthly.getOutnum();
			String throughput = motorcarMonthly.getThroughput();
			motorcarMonthly = systemService.getEntity(MotorcarMonthly.class, motorcarMonthly.getId());
			motorcarMonthly.setInnum(innum);
			motorcarMonthly.setOutnum(outnum);
			motorcarMonthly.setThroughput(throughput);
			motorcarMonthly.setStatus(Status.noAudit);
			systemService.saveOrUpdate(motorcarMonthly);
		}
		j.setMsg("上报成功");
		systemService.addLog("填报动车月报成功:"+motorcarMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}

	// 删除月报
	@RequestMapping(params = "delcm")
	@ResponseBody
	public AjaxJson del(HttpServletRequest req) {
		String monthId = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			// systemService.getEntity(MotorcarMonthly.class, monthId);
			MotorcarMonthly motorcarMonthly = this.systemService.getEntity(
					MotorcarMonthly.class, monthId);
			systemService.delete(motorcarMonthly);
			message = "月报删除成功";
		} catch (Exception e) {
			message = "月报删除失败";
		}

		j.setMsg(message);
		return j;
	}

	// 月报详情展示

	@RequestMapping(params = "detailcm")
	public ModelAndView detial(HttpServletRequest request,
			MotorcarMonthly motorcarMonthly, OtherTravelInfo otherTravelInfo,
			DataGrid dataGrid) {
		String otid = request.getParameter("otid");
		// 月报id
		String cmonthId = request.getParameter("cmonthId");
		// 存到请求域中
		// request.setAttribute("otid", otid);
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		motorcarMonthly = this.systemService.get(MotorcarMonthly.class,
				cmonthId);

		motorcarMonthly.setOtherTravelInfo(h);
		// request.setAttribute("hotelMonthly",hotelMonthly);

		return new ModelAndView("otherTravel/showcmonthDetail").addObject(
				"motorcarMonthly", motorcarMonthly);
	}

	// 月报
	@RequestMapping(params = "addstatuscm")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			MotorcarMonthly motorcarMonthly, OtherTravelInfo otherTravelInfo,
			String statusid) {
		AjaxJson j = new AjaxJson();

		// 月报的id
		String cmonthId = request.getParameter("cmonthId");
		request.setAttribute("cmonthId", cmonthId);
		try {
			if (StringUtil.isNotEmpty(cmonthId)) {
				motorcarMonthly = systemService.getEntity(
						MotorcarMonthly.class, cmonthId);

				// 1表示管理员审核通过
				motorcarMonthly.setStatus(Status.passAudit);
				// MotorcarMonthly.set;
				systemService.saveOrUpdate(motorcarMonthly);
				// systemService.updateEntitie(scenicMonth);
				j.setMsg("管理员审核成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		systemService.addLog("审核通过动车月报成功:"+motorcarMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return j;
	}

	@RequestMapping(params = "nocheckcm")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,
			OtherTravelInfo otherTravelInfo, MotorcarMonthly motorcarMonthly,
			String statusid) {
		AjaxJson j = new AjaxJson();
		String amonthId = request.getParameter("cmonthId");
		request.setAttribute("cmonthId", amonthId);
		if (StringUtil.isNotEmpty(amonthId)) {
			motorcarMonthly = systemService.getEntity(MotorcarMonthly.class,
					amonthId);
			// request.setAttribute("scenicdata", scenicdata);
			motorcarMonthly.setStatus(Status.notAudit);// 管理员审核未通过
			// MotorcarMonthly.setAuditTime(new Date());
			systemService.saveOrUpdate(motorcarMonthly);
		}
		systemService.addLog("审核退回动车月报成功:"+motorcarMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return j;
	}

	// 审核页面
	@RequestMapping(params = "auditcm")
	public ModelAndView audit(HttpServletRequest request,
			MotorcarMonthly motorcarMonthly, OtherTravelInfo otherTravelInfo,
			DataGrid dataGrid) {
		String otid = request.getParameter("otid");
		String cmonthId = request.getParameter("cmonthId");
		request.setAttribute("otid", otid);
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
		MotorcarMonthly hq = this.systemService.get(MotorcarMonthly.class,
				cmonthId);

		hq.setOtherTravelInfo(h);

		return new ModelAndView("otherTravel/auditcmonth").addObject(
				"motorcarMonthly", hq);
	}

	@RequestMapping(params = "addsigncm")
	public ModelAndView addsign(HttpServletRequest request) {
		String otid = request.getParameter("otid");
		// 现在为空
		String cmonthId = request.getParameter("cmonthId");

		// 根据基本信息id获取基本信息
		OtherTravelInfo hm = this.systemService
				.get(OtherTravelInfo.class, otid);
		// 根绝月报id获取月报信息
		MotorcarMonthly s = this.systemService.get(MotorcarMonthly.class,
				cmonthId);
		// 将基本信息设置在域中

		// request.setAttribute("hotelmanage", hm);
		if (s == null) {
			s = new MotorcarMonthly();

		}

		s.setOtid(otid);
		s.setDates(timeUtils.getLastMonthD());
		s.setSmonth(timeUtils.getCurrentMonth(s.getDates()));
		s.setOtherTravelInfo(hm);
		request.setAttribute("otherTravelInfo", hm);
		request.setAttribute("motorcarMonthly", s);
		// this.saveScenic(request, scenicSpotMonth);
		return new ModelAndView("otherTravel/daitianbaocm");
	}

	/**
	 * 
	 * @date: 2016年12月29日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:动车月报代填报
	 */
	@RequestMapping(params = "saveDaicm")
	@ResponseBody
	public AjaxJson addDai(MotorcarMonthly motorcarMonthly,
			HttpServletRequest req) {

		AjaxJson j = new AjaxJson();
		// 补全月报信息由
		// 与机场的id进行关联
		OtherTravelInfo otherTravelInfo = systemService.getEntity(
				OtherTravelInfo.class, motorcarMonthly.getOtid());
		motorcarMonthly.setOtherTravelInfo(otherTravelInfo);
		;
		motorcarMonthly.setDates(timeUtils.getLastMonthD());
		motorcarMonthly.setStatus(Status.passAudit);
		motorcarMonthly.setWriteDate(new Date());
		motorcarMonthly.setSmonth(timeUtils.getCurrentMonth(motorcarMonthly
				.getDates()));
		String cid = motorcarMonthly.getId();
		if (cid.length() == 0) {
			systemService.save(motorcarMonthly);
		} else {

			systemService.saveOrUpdate(motorcarMonthly);
		}
		// systemService.saveOrUpdate(hotelmanage);
		// System.out.println("添加成功");

		j.setMsg("数据上报成功");
		systemService.addLog("代填报动车月报成功:"+motorcarMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}

	// zancun
	@RequestMapping(params = "zancuncheckcm")
	@ResponseBody
	public AjaxJson saveZanStatus(HttpServletRequest request,
			MotorcarMonthly motorcarMonthly, String statusid) {
		AjaxJson j = new AjaxJson();
		String otid = request.getParameter("otid");
		request.setAttribute("otid", otid);
		// 根据id获取机场信息实体类
		OtherTravelInfo otherTravelInfo = this.systemService.getEntity(
				OtherTravelInfo.class, otid);
		// 将基本信息添加到月报信息中
		motorcarMonthly.setOtid(otid);
		motorcarMonthly.setOtherTravelInfo(otherTravelInfo);
		;
		motorcarMonthly.setDates(timeUtils.getLastMonthD());
		motorcarMonthly.setSmonth(timeUtils.getCurrentMonth(motorcarMonthly
				.getDates()));
		// 状态仍为未填报
		motorcarMonthly.setStatus(Status.noSumbit);
		request.setAttribute("motorcarMonthly", motorcarMonthly);
		// 更新
		String cid = motorcarMonthly.getId();
		if (cid.length() == 0) {
			systemService.save(motorcarMonthly);
		} else {

			systemService.saveOrUpdate(motorcarMonthly);
		}
		systemService.addLog("暂存动车月报成功:"+motorcarMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return j;
	}
//导出动车月报
	@RequestMapping(params = "exportsxls")
	public String exportXls(MotorcarMonthly motorcarMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(MotorcarMonthly.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				motorcarMonthly, request.getParameterMap());
		// List<CourseEntity> courses =
		// this.courseService.getListByCriteriaQuery(cq,false);
		String hotelId = ResourceUtil.getSessionUserName().getId();
		List<MotorcarMonthly> motorcarMonthlys = this.systemService
				.getList(MotorcarMonthly.class);
		map.put(NormalExcelConstants.FILE_NAME, "动车月报信息");
		map.put(NormalExcelConstants.CLASS, MotorcarMonthly.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("动车月报列表",
				"导出人:管理员", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, motorcarMonthlys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	// **************************************上机场管理员后台,下是机场客户后台***********************************
	@RequestMapping(params = "ckmotorcar")
	public String user(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		// List<TSDepart> departList = systemService.getList(TSDepart.class);
		// request.setAttribute("departsReplace",
		// RoletoJson.listToReplaceStr(departList, "departname", "id"));
		return "otherTravelClient/ckmotorcarMonthlyList";
	}

	@ResponseBody
	@RequestMapping(params = "acdatagrid")
	public void datagridClient(MotorcarMonthly motorcarMonthly,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		// 1.从session中获取当前用户的 id
		String otid = ResourceUtil.getSessionUserName().getId();
		// System.out.println(otid);
		motorcarMonthly.setOtid(otid);
		CriteriaQuery cq = new CriteriaQuery(MotorcarMonthly.class, dataGrid);
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				motorcarMonthly);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		// List<OtherTravelInfo> cfeList = new ArrayList<OtherTravelInfo>();
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "exportsxlss")
	public void exportXlss(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取参数
		/*
		 * String name = new
		 * String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
		 */
		String smonth = new String(request.getParameter("smonth").getBytes(
				"iso8859-1"), "utf-8");
		String status = new String(request.getParameter("status").getBytes(
				"iso8859-1"), "utf-8");

		String excelName = "动车月报统计.xls";
		String modelPath = "/com/zzc/web/export/model/otherTravel/" + excelName;
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT o.name,t.smonth, t.in_num, t.out_num, t.throughput, IF ( t.`status` = '1', '未填报', IF ( t.`status` = '2', '未审核', IF ( t.`status` = '3','管理员已审核(审核不通过)',IF (t.`status` = '4','管理员已审核(审核通过)','市级审核'))))'status' FROM t_motorcar_monthly t LEFT JOIN t_othercompany_info o ON t.otravel_id = o.ID where 1=1 and t.status='4' ");
		// 组装查询条件
		if (smonth != null && smonth.length() != 0) {
			sql.append("  and t.smonth LIKE '" + smonth + "' ");
		}

		if (status != null && status.length() != 0) {
			sql.append(" and t.status ='" + status + "'");
		}
		/*
		 * if(name !=null && name.length() != 0){
		 * sql.append(" and t.name ='"+name+"'"); }
		 */
		sql.append(" UNION SELECT '合计','',IFNULL(t.in_num,0) ,IFNULL(t.out_num,0) ,IFNULL(t.throughput,0) , '' FROM t_motorcar_monthly t LEFT JOIN t_othercompany_info o ON t.otravel_id = o.ID where 1=1 and t.status='4'  ");
		// 组装查询条件
		if (smonth != null && smonth.length() != 0) {
			sql.append("  and t.smonth LIKE '" + smonth + "' ");
		}

		if (status != null && status.length() != 0) {
			sql.append(" and t.status ='" + status + "'");
		}
		/*
		 * if(name !=null && name.length() != 0){
		 * sql.append(" and t.name ='"+name+"'"); }
		 */
		ExportService emds = new ExportService(3, 6);

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

		String sql = "select id from t_motorcar_monthly t where t.smonth like '"
				+ smonth + "' and t.otravel_id = '" + userId + "'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if (list.size() != 0)
			tip = true;
		return tip;
	}

}
