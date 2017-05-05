package com.zzc.web.notice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.ResourceUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.notice.entity.Notice;
import com.zzc.web.notice.service.NoticeService;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("noticeController")
public class NoticeController extends BaseController {
	
	private NoticeService noticeService;
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	private  SystemService systemService;
	 public SystemService getSystemService() {
			return systemService;
		}
	 @Autowired
		public void setSystemService(SystemService systemService) {
			this.systemService = systemService;
		}
	
	@RequestMapping(params = "notice")
	public ModelAndView notice(HttpServletRequest request, HttpServletResponse response,ModelAndView m){
		/*AutoAddUser.add("fpfd", GlobalParams.CHAOJIGUANLIYUAN);*/
		String range;
		if(request.getParameter("range")!=null){
				range=request.getParameter("range").toString();
		}else{
				range=null;
		}
		String userId=ResourceUtil.getSessionUserName().getId();
		  Map<String, Object> roleMap = systemService.findOneForJdbc("select * from t_s_role t where t.id=(select tr.roleid from t_s_role_user tr where tr.userid =?)", userId);
	        String roleName = roleMap.get("rolename").toString();
		if(roleName.equals("超级管理员")||roleName.equals("区级管理员")){
			m.setViewName("notice/noticeList");
		}else{
			m.setViewName("notice/noticeList1");
		}
		
		m.addObject("range", range);
		return m;
	}
	@RequestMapping(params = "dataGrid")
	public void dataGrid(HttpServletRequest request, HttpServletResponse response,Notice notice, DataGrid dataGrid,String noticerange){		
		CriteriaQuery cq = new CriteriaQuery(Notice.class, dataGrid);
		if(request.getParameter("noticerange")==null||request.getParameter("noticerange").toString().length()==0){
			cq.add();
		}else{		
			noticerange=request.getParameter("noticerange").toString();
			cq.add(Restrictions.eq("status","1"));
			cq.add(Restrictions.or(Restrictions.eq("range",noticerange),Restrictions.eq("range", "0")));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, notice);
		noticeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	
	}
	
	@RequestMapping(params = "addNotice")
	public ModelAndView addNotice(){
		Notice temp =new Notice();
		return new ModelAndView("notice/addNotice").addObject("notice", temp);
	}
	
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,HttpServletResponse response,Notice notice){
		AjaxJson j =new AjaxJson();
			java.util.Date now=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String createDate=sdf.format(now);	
			notice.setStatus("1");//设置正式发布
			notice.setSource("0");//设置为三亚旅游系统发布
			try {
				notice.setCreateDate(createDate);
				j.setMsg("发布成功");
				systemService.addLog("通知发布成功:", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				j.setMsg("发布失败");
				e.printStackTrace();
				systemService.addLog("通知发布失败:", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

			}
		
			this.noticeService.save(notice);
			return j;
	}
	@RequestMapping(params="	zancuncheck")
	@ResponseBody
	public AjaxJson zancun(HttpServletRequest request,HttpServletResponse response,Notice notice){
		AjaxJson j=new AjaxJson();
			java.util.Date now=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String createDate=sdf.format(now);	
			notice.setStatus("2");//设为草稿
			notice.setSource("0");//设置为三亚旅游系统发布
			try {
				notice.setCreateDate(createDate);
				j.setMsg("存为草稿成功");
			} catch (Exception e) {
				j.setMsg("保存失败");
				e.printStackTrace();
			}	
			this.noticeService.save(notice);
			return j;
	}
	@RequestMapping(params="del")
	@ResponseBody
		public Object del(String id){
		this.noticeService.deleteEntityById(Notice.class, id);	
		systemService.addLog("通知删除成功:", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return new AjaxJson();
		
	}
	@RequestMapping(params="detail",method=RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request,String id){
		Notice notice = noticeService.getEntity(Notice.class,id);
		request.setAttribute("notice", notice);
		return new ModelAndView("notice/noticeInfo").addObject("notice", notice);
	}
	@RequestMapping(params="getNotice" )
	@ResponseBody
	public Notice getNotice(HttpServletRequest request ){
		String id=request.getParameter("id");
		Notice notice = noticeService.getEntity(Notice.class,id);
		return notice;
	}
	@RequestMapping(params="edit")
	public ModelAndView audit(HttpServletRequest request,Notice notice,DataGrid dataGrid) {
		String id = request.getParameter("id");
		
		request.setAttribute("id", id);  
		
		  notice = noticeService.get(Notice.class, id);
		return new ModelAndView("notice/editNoticeInfo").addObject("notice", notice);
	}
	@RequestMapping(params = "editStatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			Notice notice,String statusid) {
		AjaxJson j =new AjaxJson();
		Date now=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String gengxin=sdf.format(now);
		notice.setSource("0");//设置为三亚旅游系统发布
		try {
			notice.setGengxin(gengxin);
			this.noticeService.saveOrUpdate(notice);
			j.setMsg("修改成功");
			systemService.addLog("通知编辑成功:", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			j.setMsg("修改失败");
			systemService.addLog("通知编辑失败:", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			e.printStackTrace();
		}
	
		
		return j;
	}
}
