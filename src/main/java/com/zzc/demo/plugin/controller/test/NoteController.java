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
import com.zzc.demo.plugin.entity.test.ZZCNoteEntity;
import com.zzc.demo.plugin.service.test.ZZCNoteServiceI;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.system.pojo.base.TSDepart;
import com.zzc.web.system.service.SystemService;

/**   
 * @Title: Controller
 * @Description: 公告
 * @author 张代浩
 * @date 2013-03-12 14:06:34
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/jeecgNoteController")
public class NoteController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NoteController.class);

	@Autowired
	private ZZCNoteServiceI jeecgNoteService;
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
	 * 公告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgNote")
	public ModelAndView jeecgNote(HttpServletRequest request) {
		return new ModelAndView("zzc/demo/test/jeecgNoteList");
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
	public void datagrid(ZZCNoteEntity jeecgNote,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZZCNoteEntity.class, dataGrid);
		//查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgNote);
		this.jeecgNoteService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除公告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZZCNoteEntity jeecgNote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jeecgNote = systemService.getEntity(ZZCNoteEntity.class, jeecgNote.getId());
		message = "删除成功";
		jeecgNoteService.delete(jeecgNote);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZZCNoteEntity jeecgNote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgNote.getId())) {
			message = "更新成功";
			jeecgNoteService.saveOrUpdate(jeecgNote);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			jeecgNoteService.save(jeecgNote);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 公告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZZCNoteEntity jeecgNote, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgNote.getId())) {
			jeecgNote = jeecgNoteService.getEntity(ZZCNoteEntity.class, jeecgNote.getId());
			req.setAttribute("jeecgNotePage", jeecgNote);
		}
		return new ModelAndView("zzc/demo/test/jeecgNote");
	}
	/**
	 * 公告列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "addorupdateNoBtn")
	public ModelAndView addorupdateNoBtn(ZZCNoteEntity jeecgNote, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgNote.getId())) {
			jeecgNote = jeecgNoteService.getEntity(ZZCNoteEntity.class, jeecgNote.getId());
			req.setAttribute("jeecgNotePage", jeecgNote);
		}
		return new ModelAndView("zzc/demo/test/jeecgNoteNoBtn");
	}
}
