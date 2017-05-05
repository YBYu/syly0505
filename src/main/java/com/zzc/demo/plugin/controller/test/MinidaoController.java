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
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.MyBeanUtils;
import com.zzc.core.util.StringUtil;
import com.zzc.demo.plugin.entity.test.ZZCMinidaoEntity;
import com.zzc.demo.plugin.service.test.ZZCMinidaoServiceI;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.system.pojo.base.TSDepart;
import com.zzc.web.system.service.SystemService;

/**   
 * @Title: Controller
 * @Description: Minidao例子
 * @author fancq
 * @date 2013-12-23 21:18:59
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/jeecgMinidaoController")
public class MinidaoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MinidaoController.class);

	@Autowired
	private ZZCMinidaoServiceI jeecgMinidaoService;
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
	 * Minidao例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgMinidao")
	public ModelAndView jeecgMinidao(HttpServletRequest request) {
		return new ModelAndView("zzc/demo/test/jeecgMinidaoList");
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
	public void datagrid(ZZCMinidaoEntity jeecgMinidao,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<ZZCMinidaoEntity> list = jeecgMinidaoService.listAll(jeecgMinidao, dataGrid.getPage(), dataGrid.getRows());
		Integer count = jeecgMinidaoService.getCount();
		dataGrid.setTotal(count);
		dataGrid.setResults(list);
		String total_salary = String.valueOf(jeecgMinidaoService.getSumSalary());
		/*
		 * 说明：格式为 字段名:值(可选，不写该值时为分页数据的合计) 多个合计 以 , 分割
		 */
		dataGrid.setFooter("salary:"+(total_salary.equalsIgnoreCase("null")?"0.0":total_salary)+",age,email:合计");
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Minidao例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZZCMinidaoEntity jeecgMinidao, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jeecgMinidao = jeecgMinidaoService.getEntity(ZZCMinidaoEntity.class, jeecgMinidao.getId());
		message = "Minidao例子删除成功";
		jeecgMinidaoService.delete(jeecgMinidao);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Minidao例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZZCMinidaoEntity jeecgMinidao, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgMinidao.getId())) {
			message = "Minidao例子更新成功";
			ZZCMinidaoEntity t = jeecgMinidaoService.getEntity(ZZCMinidaoEntity.class, jeecgMinidao.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(jeecgMinidao, t);
				jeecgMinidaoService.update(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "Minidao例子更新失败";
			}
		} else {
			message = "Minidao例子添加成功";
			jeecgMinidao.setStatus("0");
			jeecgMinidaoService.insert(jeecgMinidao);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * Minidao例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZZCMinidaoEntity jeecgMinidao, HttpServletRequest req) {
		//获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		
		if (StringUtil.isNotEmpty(jeecgMinidao.getId())) {
			jeecgMinidao = jeecgMinidaoService.getEntity(ZZCMinidaoEntity.class, jeecgMinidao.getId());
			req.setAttribute("jeecgMinidaoPage", jeecgMinidao);
		}
		return new ModelAndView("zzc/demo/test/jeecgMinidao");
	}
}
