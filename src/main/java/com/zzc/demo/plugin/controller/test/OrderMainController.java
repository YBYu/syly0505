package com.zzc.demo.plugin.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.zzc.core.util.StringUtil;
import com.zzc.demo.plugin.entity.test.ZZCOrderCustomEntity;
import com.zzc.demo.plugin.entity.test.ZZCOrderMainEntity;
import com.zzc.demo.plugin.entity.test.ZZCOrderProductEntity;
import com.zzc.demo.plugin.page.ZZCOrderMainPage;
import com.zzc.demo.plugin.service.test.JeecgOrderMainServiceI;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.system.service.SystemService;

/**   
 * @Title: Controller
 * @Description: 订单信息
 * @author 张代浩
 * @date 2013-03-19 22:01:34
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/jeecgOrderMainController")
public class OrderMainController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OrderMainController.class);

	@Autowired
	private JeecgOrderMainServiceI jeecgOrderMainService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 订单信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgOrderMain")
	public ModelAndView jeecgOrderMain(HttpServletRequest request) {
		return new ModelAndView("zzc/demo/test/jeecgOrderMainList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZZCOrderMainEntity.class, dataGrid);
		this.jeecgOrderMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订单信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZZCOrderMainEntity jeecgOrderMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jeecgOrderMain = systemService.getEntity(ZZCOrderMainEntity.class, jeecgOrderMain.getId());
		message = "删除成功";
		jeecgOrderMainService.delMain(jeecgOrderMain);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单及明细信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZZCOrderMainEntity jeecgOrderMain ,ZZCOrderMainPage jeecgOrderMainPage,	
			HttpServletRequest request) {
		List<ZZCOrderProductEntity> jeecgOrderProducList =  jeecgOrderMainPage.getJeecgOrderProductList();
		List<ZZCOrderCustomEntity>  jeecgOrderCustomList = jeecgOrderMainPage.getJeecgOrderCustomList();
		Boolean jeecgOrderCustomShow = "true".equals(request.getParameter("jeecgOrderCustomShow"));
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgOrderMain.getId())) {
			message = "更新成功";
			jeecgOrderMainService.updateMain(jeecgOrderMain, jeecgOrderProducList, jeecgOrderCustomList,jeecgOrderCustomShow);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			jeecgOrderMainService.addMain(jeecgOrderMain, jeecgOrderProducList, jeecgOrderCustomList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 订单信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZZCOrderMainEntity jeecgOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgOrderMain.getId())) {
			jeecgOrderMain = jeecgOrderMainService.getEntity(ZZCOrderMainEntity.class, jeecgOrderMain.getId());
			req.setAttribute("jeecgOrderMainPage", jeecgOrderMain);
		}
		return new ModelAndView("zzc/demo/test/jeecgOrderMain");
	}
	/**
	 * 加载产品列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgOrderProductList")
	public ModelAndView jeecgOrderProductList(ZZCOrderMainEntity jeecgOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgOrderMain.getGoOrderCode())) {
			List<ZZCOrderProductEntity> jeecgOrderProductEntityList =  jeecgOrderMainService.findByProperty(ZZCOrderProductEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode());
			req.setAttribute("jeecgOrderProductList", jeecgOrderProductEntityList);
		}
		return new ModelAndView("zzc/demo/test/jeecgOrderProductList");
	}
	
	/**
	 * 加载客户列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgOrderCustomList")
	public ModelAndView jeecgOrderCustomList(ZZCOrderMainEntity jeecgOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgOrderMain.getGoOrderCode())) {
			List<ZZCOrderCustomEntity> jeecgJeecgOrderCustomEntityList =  jeecgOrderMainService.findByProperty(ZZCOrderCustomEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode());
			req.setAttribute("jeecgOrderCustomList", jeecgJeecgOrderCustomEntityList);
		}
		return new ModelAndView("zzc/demo/test/jeecgOrderCustomList");
	}
}
