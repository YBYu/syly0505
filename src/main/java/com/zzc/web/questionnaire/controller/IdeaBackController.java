package com.zzc.web.questionnaire.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.ResourceUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.questionnaire.entity.IdeaBack;
import com.zzc.web.system.service.SystemService;



/**
 * @Title: Controller
 * @Description: 意见反馈
 * @author 冯勇齐
 * @date 2016-1-10 13:27:23
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/ideaBackController")
public class IdeaBackController {
	
	
	private String message = null;
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}


	
	@RequestMapping(params = "toideaBackAuditList")
	public String questionAuditList(HttpServletRequest request) {

		return "ideaback/ideaBackAuditList";

	}

	// 本方法跳转到save.jsp页面
	@RequestMapping(params = "add")
	public String questionInfo() {

		return "ideaback/saveBackContent";
	}
	
	
	/**
	 * 保存意见反馈内容
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson saveContent(HttpServletRequest req,
			IdeaBack ideaBack) { 
		AjaxJson j = new AjaxJson();
		Date date = new Date();
	
		try {
			//本行需要接受用户id,该id由安卓客户端提供,该出由UUID暂时代替
			String aa =UUID.randomUUID().toString();
			String userid = ResourceUtil.getSessionUserName().getId();	
			ideaBack.setUserId(aa);
			ideaBack.setReplyUserId(userid);
			ideaBack.setBackType("0");
			ideaBack.setContent("系统开发已经收尾");
			ideaBack.setCreateDate(date);
			ideaBack.setStatus("0");
			ideaBack.setIdeaUserName("李四");  
			systemService.save(ideaBack);	
			 message="保存信息成功";
			} catch (Exception e) {
				// TODO: handle exception
			 message="保存信息失败";
			}
		
		  j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 保存回复内容
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "auditSave")
	@ResponseBody
	public AjaxJson auditRePlyContent(HttpServletRequest req,
			IdeaBack ideaBack) { 
		AjaxJson j = new AjaxJson();
	//	Date date = new Date();
	try {
		 
			String ideabackid = req.getParameter("ideabackid");
			//String replyContent =req.getParameter("replyContent");
			IdeaBack ib =this.systemService.get(IdeaBack.class, ideabackid);
		if(ideaBack.getReplyContent()!=null){	
				ib.setReplyContent(ideaBack.getReplyContent());  
				ib.setStatus("1");
		}
		ib.setReplyDate(new Date());
			systemService.saveOrUpdate(ib); 
			systemService.addLog("填报回复信息成功:"+ib.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			message="保存信息成功";
		} catch (Exception e) {
			message="保存信息失败";
			systemService.addLog("填报回复信息失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		 j.setMsg(message);
		return j;
	}

	
	
	
	/**
	 * 意见反馈列表
	 * @author 冯勇齐
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "ideaBackDatagrid")
	public void monthGrid(IdeaBack ideaBack,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		// String userid = ResourceUtil.getSessionUserName().getId();
		// scenicSpotMonth.setUserMonthId(userid);

		CriteriaQuery cq = new CriteriaQuery(IdeaBack.class, dataGrid);
		
		// 查询条件组装器
				if(ideaBack.getContent() != null && ideaBack.getContent().length()!= 0){
				String sql="this_ .content like '%"+ideaBack.getContent()+"%'";

				 
				cq.add(Restrictions.sqlRestriction(sql));
					}
				ideaBack.setContent(null);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,ideaBack);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);

		
       
	}
	
	
	/**
	 * 反馈回复保存jsp
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "backSave")
	public ModelAndView analyze(HttpServletRequest request,
			IdeaBack ideaBack) {
		String ideabackid = request.getParameter("ideabackid");
		request.setAttribute("ideabackid", ideabackid); 
		IdeaBack s = this.systemService.get(IdeaBack.class, ideabackid);
	
		request.setAttribute("ideadata", s);

		return new ModelAndView("ideaback/saveBackContent");
	}
	
	
	/**
	 * 反馈回复详情jsp
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "detailContent")
	public ModelAndView detailBackContent(HttpServletRequest request,
			IdeaBack ideaBack) {
		String ideabackid = request.getParameter("ideabackid");
		request.setAttribute("ideabackid", ideabackid); 
		IdeaBack s = this.systemService.get(IdeaBack.class, ideabackid);
	
		request.setAttribute("ideadata", s);

		return new ModelAndView("ideaback/detailBackContent");
	}
	
	
	
	/**
	 * 删除意见反馈
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(IdeaBack ideaBack, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			systemService.addLog("删除意见反馈信息成功:"+id, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			ideaBack = systemService.getEntity(IdeaBack.class, id);
			systemService.delete(ideaBack);
			message = "删除成功";
		} catch (Exception e) { 
			message = "删除失败";
			systemService.addLog("删除意见反馈信息失败:"+id, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}

		j.setMsg(message);
		return j;
	}
}
