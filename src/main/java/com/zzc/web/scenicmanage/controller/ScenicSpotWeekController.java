package com.zzc.web.scenicmanage.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.zzc.core.util.StringUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotMonth;
import com.zzc.web.scenicmanage.entity.ScenicSpotWeek;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.sylyUtils.WeekUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;


/**
 * @Title: Controller
 * @Description: 景区周报信息管理
 * @author 冯勇齐
 * @date 2016-11-28 21:50:55
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/scenicWeekController")
public class ScenicSpotWeekController extends BaseController {
	private String message = null;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ScenicSpotWeekController.class);
   // @Autowired
	private SystemService systemService;
	
    public SystemService getSystemService() {
		return systemService;
	}
    @Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
//	@Autowired
//	private ScenicSpotWeekService  scenicSpotWeekService;
//
//	public ScenicSpotWeekService getScenicSpotWeekService() {
//		return scenicSpotWeekService;
//	}
//	public void setScenicSpotWeekService(ScenicSpotWeekService scenicSpotWeekService) {
//		this.scenicSpotWeekService = scenicSpotWeekService;
//	}
	/**
	 * 添加景区周报
	 * @param request
	 * @return
	 */
	@RequestMapping(params="addUser")
	public String scenicUserWeek(HttpServletRequest request) {
		String userId = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userId);
		if(list.size()>0){
			ScenicData scenicData=list.get(0);
			 Calendar c = new GregorianCalendar();
			 int week=WeekUtils.getWeekOfYear(c.getTime())-1;
			 int year=c.get(Calendar.YEAR);
		      //第几周  
		      if(week==0){
		    	  year=year-1;
		      	week=52;
		      }
		      String weekRange=WeekUtils.getDayRange(year, week);
				request.setAttribute("scenicData",scenicData);
				request.setAttribute("year",year);
				request.setAttribute("week",week);
				request.setAttribute("weekRange",weekRange);
				

		      
		}
		
		
		return "scenicweek/saveUserWeek";  
	}
	
	
	
	
	/**
	 * 周报列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "tolist")
	public String scenicWeekList(HttpServletRequest request) {
		 Calendar c = new GregorianCalendar();
		 int week=WeekUtils.getWeekOfYear(c.getTime())-1;
		 int year=c.get(Calendar.YEAR);
	      //第几周  
	      if(week==0){
	    	  year=year-1;
	      	week=52;
	      }
	      request.setAttribute("week", week);
	      request.setAttribute("year", year);
		return "/scenicweek/scenicWeekList";

	}
	/**
	 * 周报审核列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toAuditList")
	public String scenicWeekAuditList(HttpServletRequest request) {
		String roleName = ResourceUtil.getSessionUserName().getUserKey();
		request.setAttribute("roleName", roleName);
		return "/scenicweek/scenicWeekAuditList";

	}
	
	
	/**
	 * 用户的周报列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toUserWeekList")
	public String scenicUserWeekList(HttpServletRequest request) {
		String userId = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userId);

		if(list.size()>0){	
			request.setAttribute("scenicdata", "scenicdata");
			String scenicdataid=list.get(0).getId();
			 Calendar c = new GregorianCalendar();
			 int week=WeekUtils.getWeekOfYear(c.getTime())-1;
			 int year=c.get(Calendar.YEAR);
		      //第几周  
		      if(week==0){
		    	  year=year-1;
		      	week=52;
		      }
			 List<Map<String,Object>> list1=systemService.findForJdbc("select * from t_scenicspot_weekly tsw where tsw.scenic_id=? and tsw.year=? and tsw.cycle=?", scenicdataid,year,week);
			 if(list1.size()>0){
					request.setAttribute("scenicweekdata", "have");
				} 
		}else{
			request.setAttribute("scenicdata", "null");
		}
		
		
		return "/scenicweek/scenicUserWeekList";

	}
	
	/**
	 * 景区用户周报列表
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "userDatagrid")
	public void userWeekDataGrid(ScenicSpotWeek scenicSpotWeek, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
	String userid = ResourceUtil.getSessionUserName().getId();
	List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userid);
		if(list.size()>0){
			ScenicData scenicData=list.get(0);
		 CriteriaQuery cq = new CriteriaQuery(ScenicSpotWeek.class, dataGrid);
		 cq.eq("scenicData", scenicData);
	     com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotWeek);
	     this.systemService.getDataGridReturn(cq, true);
		 TagUtil.datagrid(response, dataGrid);
		}
	}
	/**
	 * 景区用户周报录入
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "saveUser")
	@ResponseBody
	public AjaxJson saveUserScenic(HttpServletRequest request,
			ScenicSpotWeek scenicSpotWeek,ScenicData scenicData) {
		
		AjaxJson j = new AjaxJson();
		String scenicdataid = request.getParameter("scenicdataid");
		TSUser user = ResourceUtil.getSessionUserName();
		//scenicData.setUserId(user.getId());
		//scenicSpotWeek.setSid(user.getId());
		String sdid =scenicData.getId();
		  //ScenicData d = this.systemService.getEntity(ScenicData.class, user.getId());
		List<ScenicData> list = this.systemService.findByProperty(ScenicData.class, "userId", user.getId());
		for (ScenicData sd : list) {
			  scenicSpotWeek.setScenicData(sd); 
		}
		Date date=new Date();
		scenicSpotWeek.setReportdate(date);
		  scenicSpotWeek.setStatus(Status.PendingSubmission);
		  scenicSpotWeek.setSid(user.getId());
		  //填报后积分增加
		  if(scenicSpotWeek.getId()==null||scenicSpotWeek.getId().length()==0){
			  	if(!ReportTimeCheck.WeekTimeCheck(Integer.parseInt(scenicSpotWeek.getYear()), scenicSpotWeek.getCycle())){
			  		UserScoreUtils.scoreChange(-1);
			  	}
			
		  }
		systemService.addLog("景区周报录入:"+scenicSpotWeek.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		systemService.saveOrUpdate(scenicSpotWeek);
                
		return j;   
	}	
	
	
	
	/**
	 * 景区周报管理列表
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(ScenicSpotWeek scenicSpotWeek,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ScenicData scenicData) {
        CriteriaQuery cq = new CriteriaQuery(ScenicSpotWeek.class, dataGrid);
       cq.add( Restrictions.eq("status",Status.cityAudit));//过滤状态为区级审核或者市级审核的数据
       ///默认显示为上周的所有数据
       String year=request.getParameter("year");
       String week=request.getParameter("cycle");
       if(year==null||week==null){
    		 Calendar c = new GregorianCalendar();
    		 int week1=WeekUtils.getWeekOfYear(c.getTime())-1;
    		 int year1=c.get(Calendar.YEAR);
    	      //第几周  
    	      if(week1==0){
    	    	  year1=year1-1;
    	      		week1=52;
    	      }
    	      
    	      year=String.valueOf(year1);
    	      week=String.valueOf(week1);
       }
    	cq.add(Restrictions.eq("year",year));
       cq.add(Restrictions.eq("cycle",Integer.parseInt(week)));
    //查询条件过滤
        String name = scenicSpotWeek.getScenicData() == null ? "" : scenicSpotWeek.getScenicData().getName();
        String beginLevel=request.getParameter("scenicData.level_begin");
        String endLevel=request.getParameter("scenicData.level_end");
        String area1=scenicSpotWeek.getScenicData() == null ? "" : scenicSpotWeek.getScenicData().getArea();
        String scenicType=scenicSpotWeek.getScenicData() == null ? "" : scenicSpotWeek.getScenicData().getScenictype();
        if(name!=null&&name.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.name like '%"+name+"%' and t.id=scenic_id)"));
        }
        if(area1!=null&&area1.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.area like '%"+area1+"%' and t.id=scenic_id)"));
        }
        if(scenicType!=null&&scenicType.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.scenic_type like '%"+scenicType+"%' and t.id=scenic_id)"));
        }
        if(beginLevel!=null&&endLevel!=null&&beginLevel.length()!=0&&endLevel.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.level between'"+endLevel+"' and '"+beginLevel+"' and  t.id=scenic_id)"));
        }
      //区级管理员过滤
      		String roleName = ResourceUtil.getSessionUserName().getUserKey();
      		String area=ResourceUtil.getSessionUserName().getArea();
      		if(roleName.equals("区级管理员")){
      				scenicData.setArea(area);
      		}
		scenicSpotWeek.setScenicData(scenicData);
        //查询条件组装器
		
        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotWeek);     
        systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

	
	/**
	 * 景区周报审核列表
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "weekdatagrid")
	public void weekDataGrid(ScenicSpotWeek scenicSpotWeek,ScenicData scenicData,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(ScenicData.class, dataGrid); 
      //区级管理员过滤
      		String roleName = ResourceUtil.getSessionUserName().getUserKey();
      		String area=ResourceUtil.getSessionUserName().getArea();
      		if(roleName.equals("区级管理员")){
      				scenicData.setArea(area);
      		}
      		//获取上一周期
      		 Calendar c = new GregorianCalendar();
      		 int week=WeekUtils.getWeekOfYear(c.getTime())-1;
      		 int year=c.get(Calendar.YEAR);
      	      //第几周  
      	      if(week==0){
      	    	  year=year-1;
      	      	week=52;
      	      } 
      	     
        //查询条件组装器
        String status=scenicData.getSpotWeek()==null?"":scenicData.getSpotWeek().getStatus();
		if(status!=null&&status.length()!=0&&!status.equals(Status.notEdit)){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_weekly t where t.status ="+status+" and t.scenic_id=this_.id and t.cycle="+week+" and t.year="+year+")"));
		}else if(status.equals(Status.notEdit)){
			cq.add(Restrictions.sqlRestriction(" 1=1 and not exists(select * from t_scenicspot_weekly t where t.status <>"+status+" and t.scenic_id=this_.id and t.cycle="+week+" and t.year="+year+")"));
		}
		String begindate=request.getParameter("spotWeek.reportdate_begin");
		String enddate=request.getParameter("spotWeek.reportdate_end");
		if(begindate!=null&&enddate!=null&&begindate.length()!=0&&enddate.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_weekly t where t.reportdate between'"+begindate+"' and '"+enddate+"' and t.scenic_id=this_.id)"));
		}
		ScenicController.getScenicLevel(request, cq);
		  String name=scenicData.getName();
	        if(name != null && name .length() != 0){
	        	cq.add(Restrictions.sqlRestriction("this_ .name like '%"+scenicData.getName()+"%'"));
	        
			}
	  	  scenicData.setName(null);
			scenicData.setSpotWeek(null);
        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicData);
   	    systemService.getDataGridReturn(cq, true);    
   	
        for (Object o :  dataGrid.getResults()) {
     	   ScenicData sd=(ScenicData) o; 
     	 
     		  if(sd.getScenicWeekList()!=null){
     				for (ScenicSpotWeek weekdata : sd.getScenicWeekList()) {

     	  		       	if(week==weekdata.getCycle()&&String.valueOf(year).equals(weekdata.getYear())){
     	  		       		sd.setSpotWeek(weekdata);
     	  		       		break;
     			       	}	    
     	  		      
     				}
     				 if(sd.getSpotWeek()==null){
     					 ScenicSpotWeek sc = new ScenicSpotWeek(); 
     					 sc.setYear(String.valueOf(year));
     					 sc.setCycle(week);
     	          	   sc.setStatus(Status.notEdit);
     	          	 String weekRange=WeekUtils.getDayRange(year, week);
     		        sc.setWeekRange(weekRange);
     	          	   sd.setSpotWeek(sc);

     				}
     	   }
			
			
		   }
       
        TagUtil.datagrid(response, dataGrid);
    }
	
	/**
	 * 景区周报详情页面
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "weekdetial",method=RequestMethod.GET )  
	public ModelAndView detial(HttpServletRequest request,ScenicSpotWeek scenicSpotWeek,ScenicData scenicData) {
		String scenicdataid = request.getParameter("scenicdataid");
		String weekid = request.getParameter("weekid");   
		//request.setAttribute("scenicdataid", scenicdataid);  
		 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotWeek m = this.systemService.get(ScenicSpotWeek.class, weekid);

		 request.setAttribute("scenicdata", s);  
		 request.setAttribute("scenicweek", m);    
		// scenicSpotWeek.setScenicData(s);      
		  //request.setAttribute("scenicSpotWeek",scenicSpotWeek);
		
		return new ModelAndView("scenicweek/aduit2week");
	}  
	/**
	 * 周报编辑
	 * @param request
	 * @param scenicSpotWeek
	 * @return
	 */
	@RequestMapping(params="updateScenic")
	public ModelAndView updateScenic(HttpServletRequest request,ScenicSpotWeek scenicSpotWeek){
		String scenicdataid = request.getParameter("scenicdataid");
		String weekid = request.getParameter("weekid");   
		request.setAttribute("weekid", weekid);
		request.setAttribute("scenicdataid", scenicdataid);
		 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotWeek m = this.systemService.get(ScenicSpotWeek.class, weekid);
		 request.setAttribute("scenicdata", s);  
		 request.setAttribute("scenicweek", m);    
		return new ModelAndView("scenicweek/updateUserWeek");
	}
	@RequestMapping(params="updateUser")
	@ResponseBody
	public AjaxJson updateUser(HttpServletRequest request,ScenicSpotWeek scenicSpotWeek){
		AjaxJson j=new AjaxJson();
		String scenicdataid= request.getParameter("scenicdataid");
		String weekid = request.getParameter("weekid");   
		ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		Date date=new Date();
		scenicSpotWeek.setReportdate(date);
		scenicSpotWeek.setScenicData(s);
		scenicSpotWeek.setId(weekid);
		scenicSpotWeek.setStatus(Status.PendingSubmission);
		systemService.saveOrUpdate(scenicSpotWeek);
		systemService.addLog("景区周报编辑:"+scenicSpotWeek.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		j.setMsg("更新成功");
		return j;
		
		
	}
	
	/**
	 * 景区周报审核状态，通过
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			ScenicSpotWeek scenicSpotWeek,ScenicData scenicData,String statusid) {
		AjaxJson j = new AjaxJson();
		System.out.println("+++++++++++++++++++++++++++++");
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			ScenicSpotWeek scenicWeek = systemService.getEntity(ScenicSpotWeek.class,id);

			String roleName = ResourceUtil.getSessionUserName().getUserKey();
	        if(roleName.equals("区级管理员")){
	        	scenicWeek.setStatus(Status.areaAudit);//区级审核通过
	    		systemService.addLog("景区周报区级管理员审核通过:"+scenicSpotWeek.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}else{
				scenicWeek.setStatus(Status.cityAudit);//市级审核通过
	    		systemService.addLog("景区周报市级管理员审核通过:"+scenicSpotWeek.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			}
			
			systemService.saveOrUpdate(scenicWeek);
		}

		return j;
	}
	
	
	
	/**
	 * 景区周报审核状态，退回
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,ScenicData scenicData,
			ScenicSpotWeek scenicSpotWeek,String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			ScenicSpotWeek scenicWeek = systemService.getEntity(ScenicSpotWeek.class,id);
			String userId=scenicWeek.getScenicData().getUserId();
			//request.setAttribute("scenicdata", scenicdata);
			scenicWeek.setStatus(Status.notPass);
			UserScoreUtils.scoreChange(-1,userId);
    		systemService.addLog("景区周报审核退回:"+scenicSpotWeek.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			systemService.saveOrUpdate(scenicWeek);
		}

		return j;
	}
	
	
	
	/**
	 * 景区周报代填报
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson saveScenic(HttpServletRequest request,
			ScenicSpotWeek scenicSpotWeek,ScenicData scenicData) {
		
		AjaxJson j = new AjaxJson();
		String weekid;
		String scenicdataid = request.getParameter("scenicdataid");
		  ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
		     
			if(request.getParameter("weekid")!=null&&request.getParameter("weekid").toString().length()!=0&&!request.getParameter("weekid").toString().equals("undefined")){
				weekid=request.getParameter("weekid");
				scenicSpotWeek.setId(weekid);
			}
			Date date=new Date();
			scenicSpotWeek.setReportdate(date);
			String roleName=ResourceUtil.getSessionUserName().getUserKey();
			if(roleName.equals("区级管理员")){
				scenicSpotWeek.setStatus(Status.areaAudit);
	    		systemService.addLog("景区周报区级管理员代填报"+scenicSpotWeek.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

			}else{
				scenicSpotWeek.setStatus(Status.cityAudit);
	    		systemService.addLog("景区周报市级管理员代填报"+scenicSpotWeek.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

			}
		  
		  scenicSpotWeek.setScenicData(d); 
		systemService.saveOrUpdate(scenicSpotWeek);
                
		return j;   
	}
	
	/**
	 * 景区周报代填报jsp
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request,ScenicSpotWeek scenicSpotWeek,ScenicData scenicData) {
		String scenicdataid = request.getParameter("scenicdataid");
		String weekid = request.getParameter("weekid");
		request.setAttribute("scenicdataid", scenicdataid);
		request.setAttribute("weekid", weekid);
		 ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotWeek w = this.systemService.get(ScenicSpotWeek.class, weekid);
		 request.setAttribute("scenicdata", d);
		 if(w==null){
			 w=new ScenicSpotWeek();
			 Calendar c = new GregorianCalendar();
			 int week=WeekUtils.getWeekOfYear(c.getTime())-1;
			 int year=c.get(Calendar.YEAR);
		        if(week==0){
		        	year=year-1;
		        	week=52;
		        }
		        w.setYear(String.valueOf(year));
		        w.setCycle(week);
		        String weekRange=WeekUtils.getDayRange(year, week);
		        w.setWeekRange(weekRange);
		 }
		       request.setAttribute("weekdata", w);
		//this.saveScenic(request, scenicSpotMonth);
		return new ModelAndView("scenicweek/save2week");
	}
	
	 //审核页面
	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,ScenicSpotWeek scenicSpotWeek,ScenicData scenicData,DataGrid dataGrid) {
		String scenicdataid = request.getParameter("scenicdataid");
		String monthid = request.getParameter("monthid");
		request.setAttribute("scenicdataid", scenicdataid);  
		 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotMonth m = this.systemService.get(ScenicSpotMonth.class, monthid);

		 request.setAttribute("scenicdata", s);  
		 request.setAttribute("monthdata", m);
		 scenicSpotWeek.setScenicData(s);    
		  request.setAttribute("scenicSpotWeek",scenicSpotWeek);
		
		return new ModelAndView("scenicweek/aduit2week");
	}
	
	
	/**
	 * 景区周报暂存
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "zancuncheck")
	@ResponseBody 
	public AjaxJson saveZanStatus(HttpServletRequest request,ScenicData scenicData,
			ScenicSpotWeek scenicSpotWeek,String statusid) {
		AjaxJson j = new AjaxJson();
		String scenicdataid = request.getParameter("scenicdataid");
		String weekid;
	
		request.setAttribute("scenicdataid", scenicdataid);
			scenicData = systemService.getEntity(ScenicData.class,scenicdataid);
			scenicSpotWeek.setScenicData(scenicData);
			scenicSpotWeek.setStatus(Status.notEdit);
			if(request.getParameter("weekid")!=null&&request.getParameter("weekid").toString().length()!=0){
				weekid=request.getParameter("weekid");
				scenicSpotWeek.setId(weekid);
			}
			 request.setAttribute("weekdata", scenicSpotWeek);
	    		systemService.addLog("景区周报暂存"+scenicSpotWeek.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			systemService.saveOrUpdate(scenicSpotWeek);

		return j;
	}
	/**
	 * 周报删除
	 * @param scenicSpotWeek
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ScenicSpotWeek scenicSpotWeek, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			scenicSpotWeek = systemService.getEntity(ScenicSpotWeek.class, id);
			systemService.delete(scenicSpotWeek);
			message="周报删除成功";
    		systemService.addLog("景区周报删除成功"+scenicSpotWeek.getId(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message="周报删除失败";
    		systemService.addLog("景区周报删除失败"+scenicSpotWeek.getId(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		}
		
		j.setMsg(message);
		return j;
	}
	/**
	 * 用户重复周报提交验证
	 */
	@RequestMapping(params = "yanzheng")
	@ResponseBody
	public AjaxJson yanzheng(ScenicSpotWeek scenicSpotWeek, HttpServletRequest req) {
		String scenicdataid = req.getParameter("scenicdataid");
		String week = req.getParameter("week");
		String year = req.getParameter("year");
		AjaxJson j = new AjaxJson();
		 List<Map<String,Object>> list=systemService.findForJdbc("select * from t_scenicspot_weekly tsw where tsw.scenic_id=? and tsw.year=? and tsw.cycle=?", scenicdataid,year,week);
		 if(list.size()>0){
			 j.setMsg("false");
		 }else{
			 j.setMsg("true");
		 }
		 return j;
	}
	/**
	 * 周报导出
	 */
	@RequestMapping(params = "excelExport")
	public void exportWeekly(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取上一周
        Calendar c = new GregorianCalendar();
		 int week1=WeekUtils.getWeekOfYear(c.getTime())-1;
		 int year1=c.get(Calendar.YEAR);
	      //第几周  
	      if(week1==0){
	    	  year1=year1-1;
	      	week1=52;
	      }
        // 获取参数 
        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
        String beginLevel = new String(request.getParameter("beginLevel").getBytes("iso8859-1"),"utf-8");
        String endLevel = new String(request.getParameter("endLevel").getBytes("iso8859-1"),"utf-8");
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
        String week= new String(request.getParameter("week").getBytes("iso8859-1"),"utf-8");
        String area= new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
        String scenicType= new String(request.getParameter("scenicType").getBytes("iso8859-1"),"utf-8");
        if(week==null||week.length()==0){
        	week=String.valueOf(week1);
        }if(year==null||year.length()==0){
        	year=String.valueOf(year1);
        }
        String excelName = "景区周报信息.xls";
        String modelPath = "/com/zzc/web/export/model/scenic/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("select s.name,w.year,w.cycle,w.taking,w.ticket,w.receptionnum,w.overnum,w.compare,w.principal,w.preparer,w.telephone,w.reportdate,if(w.`status`='3','未填报',if(w.`status`='4','提交待审',if(w.`status`='5','退回修订',if(w.`status`='6','区级审核','市级审核')))) 'status' from t_scenicspot_weekly w left join t_scenicspot_info s on s.id=w.scenic_id where 1=1 and w.status in (7)");
        // 组装查询条件
        if(name !=null && name.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.name like '%"+name+"%'  and  s.id=w.scenic_id)");
        }
        if(area !=null && area.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.area ="+area+"  and  s.id=w.scenic_id)");
        }
        if(scenicType !=null && scenicType.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.scenic_type ="+scenicType+"  and  s.id=w.scenic_id)");
        }
        if(beginLevel !=null && beginLevel.length() != 0&&endLevel !=null && endLevel.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.level between '"+endLevel+"' and '"+beginLevel+"' and  s.id=w.scenic_id)");
        }
        if(year !=null && year.length() != 0){
        	sql.append(" and w.year ='"+year+"'");
        }
        if(week !=null && week.length() != 0){
        	sql.append(" and w.cycle ='"+week+"'");
        }    
        sql.append(" union all select '合计','','',sum(w.taking),sum(w.ticket),sum(w.receptionnum),sum(w.overnum),'','','','','','' from t_scenicspot_weekly w left join t_scenicspot_info s on s.id=w.scenic_id where 1=1 and w.status in (7)");
        // 组装查询条件
        if(name !=null && name.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.name like '%"+name+"%'  and  s.id=w.scenic_id)");
        }
        if(area !=null && area.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.area ="+area+"  and  s.id=w.scenic_id)");
        }
        if(scenicType !=null && scenicType.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.scenic_type ="+scenicType+"  and  s.id=w.scenic_id)");
        }
        if(beginLevel !=null && beginLevel.length() != 0&&endLevel !=null && endLevel.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.level between '"+endLevel+"' and '"+beginLevel+"' and  s.id=w.scenic_id)");
        }
        if(year !=null && year.length() != 0){
        	sql.append(" and w.year ='"+year+"'");
        }
        if(week !=null && week.length() != 0){
        	sql.append(" and w.cycle ='"+week+"'");
        }
        ExportService emds = new ExportService(13); 
        
        HSSFWorkbook workbook = null;
        try
        {
            workbook = emds.getNewModelInfos(modelPath, sql.toString());
        } catch (Exception e)
        {

        }

        response.reset();
        response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
	
        }
 
 
}
