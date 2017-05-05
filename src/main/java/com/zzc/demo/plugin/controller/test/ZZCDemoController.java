package com.zzc.demo.plugin.controller.test;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.annotation.config.AutoMenu;
import com.zzc.core.annotation.config.AutoMenuOperation;
import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.MyBeanUtils;
import com.zzc.core.util.StringUtil;
import com.zzc.demo.plugin.entity.test.CKEditorEntity;
import com.zzc.demo.plugin.entity.test.ZZCDemo;
import com.zzc.demo.plugin.service.test.ZZCDemoServiceI;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.system.pojo.base.TSDepart;
import com.zzc.web.system.service.SystemService;

/**   
 * @Title: Controller
 * @Description: 单表模型（DEMO）
 * @author 张代浩
 * @date 2013-01-23 17:12:40
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/jeecgDemoController")
@AutoMenu(name = "常用Demo", url = "jeecgDemoController.do?jeecgDemo")
public class ZZCDemoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZZCDemoController.class);

	@Autowired
	private ZZCDemoServiceI jeecgDemoService;
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
	 * popup 例子
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "popup")
	public ModelAndView popup(HttpServletRequest request) {
		return new ModelAndView("zzc/demo/jeecgDemo/popup");
	}
	/**
	 * popup 例子
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "selectUserList")
	public ModelAndView selectUserList(HttpServletRequest request) {
		String departsReplace = "";
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		for (TSDepart depart : departList) {
			if (departsReplace.length() > 0) {
				departsReplace += ",";
			}
			departsReplace += depart.getDepartname() + "_" + depart.getId();
		}
		request.setAttribute("departsReplace", departsReplace);
		return new ModelAndView("zzc/demo/jeecgDemo/selectUserList");
	}
	
	/**
	 * ckeditor 例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "ckeditor")
	public ModelAndView ckeditor(HttpServletRequest request) {
//		CKEditorEntity t = jeecgDemoService.get(CKEditorEntity.class, "1");
		CKEditorEntity t = jeecgDemoService.loadAll(CKEditorEntity.class).get(0);
		request.setAttribute("cKEditorEntity", t);
		if(t.getContents() == null ){
			request.setAttribute("contents", "");
		}else {
			request.setAttribute("contents", new String (t.getContents()));
		}
		return new ModelAndView("zzc/demo/jeecgDemo/ckeditor");
	}
	/**
	 * ckeditor saveCkeditor
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveCkeditor")
	@ResponseBody
	public AjaxJson saveCkeditor(HttpServletRequest request,CKEditorEntity cKEditor , String contents) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(cKEditor.getId())) {
			CKEditorEntity t =jeecgDemoService.get(CKEditorEntity.class, cKEditor.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(cKEditor, t);
				t.setContents(contents.getBytes());
				jeecgDemoService.saveOrUpdate(t);
				j.setMsg("更新成功");
			} catch (Exception e) {
				e.printStackTrace();
				j.setMsg("更新失败");
			}
		} else {
			cKEditor.setContents(contents.getBytes());
			jeecgDemoService.save(cKEditor);
		}
		return j;
	}

	/**
	 * JeecgDemo 打印预览跳转
	 * @param jeecgDemo
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "print")
	public ModelAndView print(ZZCDemo jeecgDemo, HttpServletRequest req) {
		// 获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);

		if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
			jeecgDemo = jeecgDemoService.getEntity(ZZCDemo.class, jeecgDemo
					.getId());
			req.setAttribute("jgDemo", jeecgDemo);
			if ("0".equals(jeecgDemo.getSex()))
				req.setAttribute("sex", "男");
			if ("1".equals(jeecgDemo.getSex()))
				req.setAttribute("sex", "女");
		}
		return new ModelAndView("zzc/demo/jeecgDemo/jeecgDemoPrint");
	}

	/**
	 * JeecgDemo例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgDemo")
	public ModelAndView jeecgDemo(HttpServletRequest request) {
		return new ModelAndView("zzc/demo/jeecgDemo/jeecgDemoList");
	}
	/**
	 * JeecgDemo例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgDemo2")
	public ModelAndView jeecgDemo2(HttpServletRequest request) {
		//request.setAttribute("sex", "1");
		request.setAttribute("status", "1");
		return new ModelAndView("zzc/demo/jeecgDemo/jeecgDemoList2");
	}
	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ZZCDemo jeecgDemo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZZCDemo.class, dataGrid);
		//查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgDemo,request.getParameterMap());
		this.jeecgDemoService.getDataGridReturn(cq, true);
		String total_salary = String.valueOf(jeecgDemoService.findOneForJdbc("select sum(salary) as ssum from jeecg_demo").get("ssum"));
		/*
		 * 说明：格式为 字段名:值(可选，不写该值时为分页数据的合计) 多个合计 以 , 分割
		 */
		dataGrid.setFooter("salary:"+(total_salary.equalsIgnoreCase("null")?"0.0":total_salary)+",age,email:合计");
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "datagrid2")
	public void datagrid2(ZZCDemo jeecgDemo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZZCDemo.class, dataGrid);
		//查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgDemo,request.getParameterMap());
		this.jeecgDemoService.getDataGridReturn(cq, true);
		//String total_salary = String.valueOf(jeecgDemoService.findOneForJdbc("select sum(salary) as ssum from jeecg_demo").get("ssum"));
		//request.setAttribute("maptest", "1");
		/*
		 * 说明：格式为 字段名:值(可选，不写该值时为分页数据的合计) 多个合计 以 , 分割
		 */
		//dataGrid.setFooter("salary:"+(total_salary.equalsIgnoreCase("null")?"0.0":total_salary)+",age,email:合计");
		
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 权限列表
	 */
	@RequestMapping(params = "combox")
	@ResponseBody
	public List<ZZCDemo> combox(HttpServletRequest request, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZZCDemo.class);
		List<ZZCDemo> ls = this.jeecgDemoService.getListByCriteriaQuery(cq, false);
		return ls;
	}
	/**
	 * 删除JeecgDemo例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZZCDemo jeecgDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jeecgDemo = systemService.getEntity(ZZCDemo.class, jeecgDemo.getId());
		message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被删除 成功";
		jeecgDemoService.delete(jeecgDemo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加JeecgDemo例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	@AutoMenuOperation(name="添加",code = "add")
	public AjaxJson save(ZZCDemo jeecgDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
			message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被更新成功";
			ZZCDemo t =jeecgDemoService.get(ZZCDemo.class, jeecgDemo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
				jeecgDemoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被添加成功";
			jeecgDemo.setStatus("0");
			jeecgDemoService.save(jeecgDemo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}
	
	
	/**
	 * 审核报错
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAuthor")
	@ResponseBody
	public AjaxJson saveAuthor(ZZCDemo jeecgDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
			message = "测试-用户申请成功";
			ZZCDemo t =jeecgDemoService.get(ZZCDemo.class, jeecgDemo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
				t.setStatus("1");
				jeecgDemoService.saveOrUpdate(t);
				j.setMsg(message);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return j;
	}

	/**
	 * JeecgDemo例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZZCDemo jeecgDemo, HttpServletRequest req) {
		//获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		
		Map sexMap = new HashMap();
		sexMap.put(0, "男");
		sexMap.put(1, "女");
		req.setAttribute("sexMap", sexMap);
		
		if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
			jeecgDemo = jeecgDemoService.getEntity(ZZCDemo.class, jeecgDemo.getId());
			req.setAttribute("jgDemo", jeecgDemo);
		}
		return new ModelAndView("zzc/demo/jeecgDemo/jeecgDemo");
	}

	/**
	 * JeecgDemo例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdatemobile")
	public ModelAndView addorupdatemobile(ZZCDemo jeecgDemo, HttpServletRequest req) {
		//获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		
		Map sexMap = new HashMap();
		sexMap.put(0, "男");
		sexMap.put(1, "女");
		req.setAttribute("sexMap", sexMap);
		
		if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
			jeecgDemo = jeecgDemoService.getEntity(ZZCDemo.class, jeecgDemo.getId());
			req.setAttribute("jgDemo", jeecgDemo);
		}
		return new ModelAndView("zzc/demo/jeecgDemo/jeecgDemomobile");
	}
	
	/**
	 * 设置签名跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doCheck")
	public ModelAndView doCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			ZZCDemo jeecgDemo = jeecgDemoService.getEntity(ZZCDemo.class,id);
			request.setAttribute("jeecgDemo", jeecgDemo);
		}
		return new ModelAndView("zzc/demo/jeecgDemo/jeecgDemo-check");
	}

	/**
	 * 全选删除Demo实例管理
	 * 
	 * @return
	 * @author tanghan
	 * @date 2013-07-13 14:53:00
	 */
	@RequestMapping(params = "doDeleteALLSelect")
	@ResponseBody
	public AjaxJson doDeleteALLSelect(ZZCDemo jeecgDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String ids = request.getParameter("ids");
		String[] entitys = ids.split(",");
	    List<ZZCDemo> list = new ArrayList<ZZCDemo>();
		for(int i=0;i<entitys.length;i++){
			jeecgDemo = systemService.getEntity(ZZCDemo.class, entitys[i]);
            list.add(jeecgDemo);			
		}
		message = "删除成功";
		jeecgDemoService.deleteAllEntitie(list);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	@RequestMapping(params = "goDemo")
	public ModelAndView goDemo(HttpServletRequest request) {
		return new ModelAndView("zzc/demo/jeecgDemo/"+request.getParameter("demoPage"));
	}
}
