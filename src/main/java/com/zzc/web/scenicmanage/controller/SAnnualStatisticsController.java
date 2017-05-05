package com.zzc.web.scenicmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.util.StringUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.tag.vo.datatable.SortDirection;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.system.service.SystemService;

/**
 * 
 * 
 * @date: 2016年12月25日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: SAnnualStatisticsController.java
 * @Version: 1.0
 * @About: 景区年报统计
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/sAnnualStatisticsController")
public class SAnnualStatisticsController extends BaseController {
	private static final Logger logger = Logger.getLogger(SAnnualStatisticsController.class);
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 
	 * @date: 2016年12月25日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param:
	 * @return:跳转到年报统计列表
	 */
	@RequestMapping(params = "tolist")
	public String scenicWeekList(HttpServletRequest request) {

		return "/scenicannualStatistics/scenicannualStatistics";

	}

	@RequestMapping(params = "datagrid")
	public void datagrid(ScenicSpotAnnual scenicSpotAnnual, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		/*Enumeration e = request.getParameterNames();
		Map<String, String> map = new HashMap();
		while (e.hasMoreElements()) {
			String key = e.nextElement().toString();
			map.put(key, request.getAttribute(key).toString());
			System.out.println(key+":" +request.getAttribute(key));
		}*/

		String sql = "SELECT tf.`name`,tf.id,ta.id as taId,tf.area,ta.total_income,ta.`year`, tf.scenic_type,tf.`level`,ta.ticket_income,ta.reception_num ,ta.exemptTicket_num FROM t_scenicSpot_annual ta left join  t_scenicSpot_info tf on ta.annual_id=tf.id"
				+ " where  1=1 ";
		if (StringUtil.isNotEmpty(getParam(request,"name"))) {
			sql += "and tf.name like '%"+getParam(request,"name")+"%'  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"area")))
			sql += "and tf.area ='"+getParam(request,"area")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))){
			sql += "and ta.`year` in('"+getParam(request,"year")+"') ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"level")))
			sql += "and tf.`level` in ('"+getParam(request,"level")+"') ";
		if (StringUtil.isNotEmpty(getParam(request,"org_property")))
			sql += "and tf.org_property ='"+getParam(request,"org_property")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"scenic_type")))
			sql += "and tf.scenic_type ='"+getParam(request,"scenic_type")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"order")))
			sql += "order by "+getParam(request,"scenic_type");
		;
		List list = systemService.findForJdbc(sql);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		Map dg = new HashMap();
		dg.put("results", list);
		dg.put("rows","10000");
		dg.put("total", list.size());
		dg.put("page", 1);
		dg.put("fields", "name,id,taId,area,total_income,year, scenic_type,level,ticket_income,reception_num ,exemptTicket_num ");
		JSONObject object = TagUtil.getJson(dg);
		try {
			PrintWriter pw=response.getWriter();
			pw.write(object.toString());
			pw.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private String getParam(HttpServletRequest request, String string) {
		return (String)request.getParameter(string);
	}

}
