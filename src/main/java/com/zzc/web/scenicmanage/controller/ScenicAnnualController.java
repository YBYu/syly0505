package com.zzc.web.scenicmanage.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sun.org.mozilla.javascript.internal.regexp.SubString;

import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.ResourceUtil;
import com.zzc.core.util.StringUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.tag.vo.datatable.SortDirection;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.htoal.entity.HotelAnnual;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.scenicmanage.entity.ScenicSpotMonth;
import com.zzc.web.scenicmanage.entity.ScenicSpotQuarter;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
import com.zzc.webservice.ReadJson;




/**
 * @Title: Controller
 * @Description: 景区年报信息管理
 * @author 冯勇齐
 * @date 2016-11-28 21:50:55
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/scenicAnnualController")
public class ScenicAnnualController {
	
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
	
    //年报列表
    @RequestMapping(params = "tolist")
	public String scenicWeekList(HttpServletRequest request) {
    	Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR)-1;
		request.setAttribute("year", year);
		return "/scenicannual/scenicannualList";

	}
    //年报审核列表
	@RequestMapping(params = "toAuditList")
	public String scenicWeekAuditList(HttpServletRequest request) {
		String roleName = ResourceUtil.getSessionUserName().getUserKey();
		request.setAttribute("roleName", roleName);
		return "/scenicannual/scenicannualAuditList";

	}
	//景区用户年报列表
	@RequestMapping(params = "toUserAnnualList")
	public String scenicUserAnnualList(HttpServletRequest request) {
		String userId = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userId);
		if(list.size()>0){		
			request.setAttribute("scenicdata", "scenicdata");
			ScenicData scenicData=list.get(0);
			String level=scenicData.getLevel();
			if(!level.equals("1")&&!level.equals("2")&&!level.equals("3")&&!level.equals("4")&&!level.equals("5")){
				request.setAttribute("notLevel", "notLevel");
			}
			String scenicdataid=list.get(0).getId();
			Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR)-1);
			List<Map<String,Object>> list1=systemService.findForJdbc("select * from t_scenicspot_annual tsw where tsw.annual_id=? and tsw.year=? ", scenicdataid,nowYear);
			 if(list1.size()>0){
					request.setAttribute("scenicannualdata", "have");
				} 
		}else{
			request.setAttribute("scenicdata", "null");
		}
		return "/scenicannual/scenicUserAnnualList";

	}
	//
	@RequestMapping(params = "add")
	public String   annualYear() {

		return "scenicannual/saveannual";
	}
	
	//年报录入页面
	
	@RequestMapping(params = "addUser")
	public String   annualUserYear(HttpServletRequest request) {
		String userId = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userId);
		if(list.size()>0){
			ScenicData scenicData=list.get(0);
			Calendar calendar=Calendar.getInstance();
			int year=calendar.get(Calendar.YEAR)-1;
				request.setAttribute("year",year);
				request.setAttribute("scenicData", scenicData);
		}
		return "scenicannual/saveUserAnnual";
	}
 
   //年报录入
	
	@RequestMapping(params = "saveUser")
	@ResponseBody
	public AjaxJson saveUserYear(HttpServletRequest request,
			ScenicSpotAnnual scenicSpotAnnual) {
		
		AjaxJson j = new AjaxJson();
		String scenicdataid = request.getParameter("scenicdataid");
		TSUser user = ResourceUtil.getSessionUserName();
		scenicSpotAnnual.setSid(user.getId());

			List<ScenicData> list = this.systemService.findByProperty(ScenicData.class, "userId", user.getId());
			for (ScenicData sd : list) {
				scenicSpotAnnual.setScenicData(sd); 
			}	  
			Date date=new Date();
			scenicSpotAnnual.setCreatTime(date);
			
		  scenicSpotAnnual.setStatus(Status.PendingSubmission);
		  //填报后积分增加
		  if(scenicSpotAnnual.getId()==null||scenicSpotAnnual.getId().length()==0){
			  	
			  if(ReportTimeCheck.annualTimeCheck(Integer.parseInt(scenicSpotAnnual.getYear()))){//如果填报时间在应填报时间之前
					scenicSpotAnnual.setScore(40);//增加40分
			  }else{
				  scenicSpotAnnual.setScore(20);
			  }
				  UserScoreUtils.scoreChange(scenicSpotAnnual.getScore());
			  }
		systemService.saveOrUpdate(scenicSpotAnnual);
		systemService.addLog("景区年报录入"+scenicSpotAnnual.getId(), Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
       
		return j;   
	}
	
	
	
	
	//用户年报列表
	@RequestMapping(params = "userdatagrid")
	public void monthGrid(ScenicSpotAnnual scenicSpotAnnual, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String userid = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userid);
		if(list.size()>0){
			ScenicData scenicData=list.get(0);

			 CriteriaQuery cq = new CriteriaQuery(ScenicSpotAnnual.class, dataGrid);
			 cq.eq("scenicData", scenicData);
		     com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotAnnual);
		     this.systemService.getDataGridReturn(cq, true);
			 TagUtil.datagrid(response, dataGrid);
		}
		
	}	
	
	
	
	
	
	//年报信息列表
	@RequestMapping(params = "datagrid")
	public void  datagrid(ScenicSpotAnnual scenicSpotAnnual, ScenicData scenicData,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(ScenicSpotAnnual.class, dataGrid);
        cq.add(Restrictions.or(Restrictions.eq("status",Status.cityAudit),Restrictions.eq("status",Status.provinceAudit),Restrictions.eq("status",Status.countryAudit)));//过滤状态为区级审核或者市级审核的数据
		cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.level in( 1,2,3,4,5) and t.id=annual_id)"));
     		Calendar calendar = Calendar.getInstance(); 
     		int year1=calendar.get(Calendar.YEAR)-1;
        //查询条件过滤
        String name = scenicSpotAnnual.getScenicData() == null ? "" : scenicSpotAnnual.getScenicData().getName();
        String beginLevel=request.getParameter("scenicData.level_begin");
        String endLevel=request.getParameter("scenicData.level_end");
        String beginYear = request.getParameter("year_begin");
        String endYear =request.getParameter("year_end");
        if(beginYear==null&&endYear==null){
        	beginYear=String.valueOf(year1);
        	endYear=String.valueOf(year1);
        }
        String area1=scenicSpotAnnual.getScenicData() == null ? "" : scenicSpotAnnual.getScenicData().getArea();
        String scenicType=scenicSpotAnnual.getScenicData() == null ? "" : scenicSpotAnnual.getScenicData().getScenictype();
        if(beginYear!=null&&endYear!=null&&beginYear.length()!=0&&endYear.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and this_.year between '"+beginYear+"' and '"+endYear+"'"));
        }
        if(name!=null&&name.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.name like '%"+name+"%' and t.id=annual_id)"));
        }
        if(area1!=null&&area1.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.area like '%"+area1+"%' and t.id=annual_id)"));
        }
        if(scenicType!=null&&scenicType.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.scenic_type like '%"+scenicType+"%' and t.id=annual_id)"));
        }
        if(beginLevel!=null&&endLevel!=null&&beginLevel.length()!=0&&endLevel.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.level between'"+endLevel+"' and '"+beginLevel+"' and  t.id=annual_id)"));
        }
       
        //区级管理员过滤
      		String roleName = ResourceUtil.getSessionUserName().getUserKey();
      		String area=ResourceUtil.getSessionUserName().getArea();
      		if(roleName.equals("区级管理员")){
      				scenicData.setArea(area);
      		}
        scenicSpotAnnual.setScenicData(scenicData);
        if(StringUtil.isEmpty(dataGrid.getSort())){
        	dataGrid.setSort("creatTime");
        	dataGrid.setOrder(SortDirection.desc);
        }
      
        //查询条件组装器
        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotAnnual);  
         systemService.getDataGridReturn(cq, true);

        TagUtil.datagrid(response, dataGrid);
    }
	//年报审核列表
	 @RequestMapping(params = "annualdatagrid")
	 public void quarterDatagrid(ScenicSpotAnnual scenicSpotAnnual, ScenicData scenicData,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		 CriteriaQuery cq = new CriteriaQuery(ScenicData.class, dataGrid);
	    	cq.add(Restrictions.sqlRestriction("this_ .level in (1,2,3,4,5)"));
		 //获取当前年份前一年
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			GregorianCalendar gc= new GregorianCalendar();
			gc.setTime(new Date());
			gc.add(GregorianCalendar.YEAR, -1);
			String lastYear = sdf.format(gc.getTime());
		//区级管理员过滤
			String roleName = ResourceUtil.getSessionUserName().getUserKey();
			String area=ResourceUtil.getSessionUserName().getArea();
			if(roleName.equals("区级管理员")){
					scenicData.setArea(area);
			}
			 //查询条件组装器
		 String status=scenicData.getScenicYear()==null?"":scenicData.getScenicYear().getStatus();
			if(status!=null&&status.length()!=0&&!status.equals(Status.notEdit)){
				cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_annual t where t.status ="+status+" and t.annual_id=this_.id and t.year="+lastYear+")"));
			}else if(status.equals(Status.notEdit)){
				cq.add(Restrictions.sqlRestriction(" 1=1 and not exists(select * from t_scenicspot_annual t where t.status <>"+status+" and t.annual_id=this_.id and t.year="+lastYear+")"));
			}
			
			String begindate=request.getParameter("scenicyear.creatTime_begin");
			String enddate=request.getParameter("scenicyear.creatTime_end");
			if(begindate!=null&&enddate!=null&&begindate.length()!=0&&enddate.length()!=0){
				cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_annual t where t.creat_time between'"+begindate+"' and '"+enddate+"' and t.annual_id=this_.id)"));
			}
			ScenicController.getScenicLevel(request, cq);
			
			String name=scenicData.getName();
	        if(name != null && name .length() != 0){
	        	cq.add(Restrictions.sqlRestriction("this_ .name like '%"+scenicData.getName()+"%'"));
			}
	        scenicData.setName(null);
			scenicData.setScenicYear(null);
	        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicData);
	   	    systemService.getDataGridReturn(cq, true);
	    	
	        for (Object o :  dataGrid.getResults()) {
	     	   ScenicData sd=(ScenicData) o;   
	     	  List<ScenicSpotAnnual> list = sd.getScenicSpotAnnual();
	     	  if(list!=null){
	     		 if(sd.getScenicSpotAnnual()!=null){  
	 				for (ScenicSpotAnnual data : list) {
	 					String aunnalYear = data.getYear();
	 			       	if(lastYear.equals(aunnalYear)){
	 			       		sd.setScenicYear(data);
	 	 			       break;  

	 			      	}	    
	 				}
	 				 if(sd.getScenicYear()==null){
	 					 ScenicSpotAnnual sc = new ScenicSpotAnnual(); 
	 					 sc.setYear(lastYear);
	 	            	   sc.setStatus(Status.notEdit);
	 	            	   sd.setScenicYear(sc);
	 	               }
	     	  }
				  
				}
			   }  
	        TagUtil.datagrid(response, dataGrid);
	    }
	 
	 
	 	//年报审核
	    @RequestMapping(params = "addstatus")
		@ResponseBody
		public AjaxJson saveStatus(HttpServletRequest request,
				ScenicSpotAnnual scenicSpotAnnual,ScenicData scenicData,String statusid) {
			AjaxJson j = new AjaxJson();
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			if (StringUtil.isNotEmpty(id)) {
				ScenicSpotAnnual scenicAnnual = systemService.getEntity(ScenicSpotAnnual.class,id);

				String roleName = ResourceUtil.getSessionUserName().getUserKey();
		        if(roleName.equals("区级管理员")){
		        	scenicAnnual.setStatus(Status.areaAudit);//4表示区级审核通过
		        	systemService.saveOrUpdate(scenicAnnual);
		    		systemService.addLog("景区年报区级管理员审核通过"+scenicSpotAnnual.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);

				}else{
							scenicAnnual.setStatus(Status.cityAudit);//5表示市级审核通过
							scenicAnnual.setProvinceSubmit("1");
							systemService.saveOrUpdate(scenicAnnual);
							j.setMsg("市级管理员审核成功");
				    		systemService.addLog("景区年报市级管理员审核通过"+scenicSpotAnnual.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);

				}
			}

			return j;
		}
	    //审核退回
		@RequestMapping(params = "nocheck")
		@ResponseBody
		public AjaxJson saveNotStatus(HttpServletRequest request,ScenicData scenicData,
				ScenicSpotAnnual scenicSpotAnnual,String statusid) {
			AjaxJson j = new AjaxJson();
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			if (StringUtil.isNotEmpty(id)) {
				ScenicSpotAnnual scenicAnnual = systemService.getEntity(ScenicSpotAnnual.class,id);
				String userId=scenicAnnual.getScenicData().getUserId();
				scenicAnnual.setStatus(Status.notPass);
				//诚信积分改变
				if(scenicAnnual.getScore()>0){
					scenicAnnual.setScore(scenicAnnual.getScore()-10);
					UserScoreUtils.scoreChange(-10,userId);
				}
				if(scenicAnnual.getScore()<=0){
					scenicSpotAnnual.setScore(0);
				}
				systemService.saveOrUpdate(scenicAnnual);
				j.setMsg("审核不通过");
	    		systemService.addLog("景区年报区级管理员审核退回"+scenicSpotAnnual.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);

			}
			
			return j;
		}
		//年报代填报
		@RequestMapping(params = "save")
		@ResponseBody
		public AjaxJson saveYear(HttpServletRequest request,
				ScenicSpotAnnual scenicSpotAnnual) {
			
			AjaxJson j = new AjaxJson();
			String scenicdataid = request.getParameter("scenicdataid");
			  ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
				Date date = new Date();
			  scenicSpotAnnual.setCreatTime(date);      
			  String yearid;
				if(request.getParameter("yearid")!=null&&request.getParameter("yearid").toString().length()!=0){
					yearid=request.getParameter("yearid");
					scenicSpotAnnual.setId(yearid);
				}
				scenicSpotAnnual.setScenicData(d); 
				String roleName=ResourceUtil.getSessionUserName().getUserKey();
				if(roleName.equals("区级管理员")){
					scenicSpotAnnual.setStatus(Status.areaAudit);
					systemService.saveOrUpdate(scenicSpotAnnual);
		    		systemService.addLog("景区年报区级管理员代填报"+scenicSpotAnnual.getId(), Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);

				}else{
					
							scenicSpotAnnual.setStatus(Status.cityAudit);
							scenicSpotAnnual.setProvinceSubmit("1");
							systemService.saveOrUpdate(scenicSpotAnnual);
							j.setMsg("市级管理员审核成功");
				    		systemService.addLog("景区年报市级管理员代填报"+scenicSpotAnnual.getId(), Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);

				}
			  
		
	                
			return j;   
		}
	 //返回代填报页面
	 @RequestMapping(params = "addsign")
		public ModelAndView addsign(HttpServletRequest request,ScenicSpotAnnual scenicSpotAnnual,ScenicData scenicData) {
			String scenicdataid = request.getParameter("scenicdataid");
			request.setAttribute("scenicdataid", scenicdataid);
			String yearid = request.getParameter("yearid");
			 ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
			 request.setAttribute("scenicdata", d);
			 request.setAttribute("yearid", yearid);
			 ScenicSpotAnnual s = this.systemService.get(ScenicSpotAnnual.class, yearid);
			 if(s==null){
				 s=new ScenicSpotAnnual();
				 Calendar calendar = Calendar.getInstance();
					String nowYear = String.valueOf(calendar.get(Calendar.YEAR)-1);
					s.setYear(nowYear);
			 }
			 request.setAttribute("yeardata", s);
			return new ModelAndView("scenicannual/aduityear2");
		}
	 /**
	  * 景区季报管理员编辑
	  */
		 @RequestMapping(params = "edit")
			public ModelAndView edit(HttpServletRequest request) {
				String scenicdataid = request.getParameter("scenicdataid");
				String yearid = request.getParameter("yearid");
				 ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
				 request.setAttribute("scenicdataid", scenicdataid);
				 request.setAttribute("scenicdata", d);
				 request.setAttribute("yearid", yearid);
				 ScenicSpotAnnual s = this.systemService.get(ScenicSpotAnnual.class, yearid);
				 request.setAttribute("yeardata", s);
				
				return new ModelAndView("scenicannual/aduityear2");
			}
		 /**
		  * 年报的审核撤回
		  */
		 @RequestMapping(params = "back")
		 @ResponseBody
			public AjaxJson back(HttpServletRequest request) {
			 AjaxJson j=new AjaxJson();
				String yearid = request.getParameter("yearid");
				ScenicSpotAnnual m = this.systemService.get(ScenicSpotAnnual.class, yearid);
				 m.setStatus(Status.cityAudit);
				 systemService.saveOrUpdate(m);
				j.setMsg("退回成功");
	    		systemService.addLog("景区年报审核撤回"+m.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
				return j;
			}
		 //审核页面
		@RequestMapping(params = "audit")
		public ModelAndView audit(HttpServletRequest request,ScenicSpotAnnual scenicSpotAnnual,ScenicData scenicData,DataGrid dataGrid) {
			String scenicdataid = request.getParameter("scenicdataid");
			String monthid = request.getParameter("monthid");
			request.setAttribute("scenicdataid", scenicdataid);
			request.setAttribute("monthid", monthid);  

			 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			 ScenicSpotAnnual y = this.systemService.get(ScenicSpotAnnual.class, monthid);

			 request.setAttribute("scenicdata", s);  
			 request.setAttribute("yeardata", y);
//			 scenicSpotAnnual.setScenicData(s);    
//			  request.setAttribute("scenicSpotAnnual",scenicSpotAnnual);
//			
			return new ModelAndView("scenicannual/aduityear");
		}
		
		  // 景区年报详情
		@RequestMapping(params = "detial")
		public ModelAndView detial(HttpServletRequest request,DataGrid dataGrid) {
			String scenicdataid = request.getParameter("scenicdataid");
			String yearid = request.getParameter("yearid");
			request.setAttribute("scenicdataid", scenicdataid);  
			 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			 ScenicSpotAnnual y = this.systemService.get(ScenicSpotAnnual.class, yearid);
			 request.setAttribute("scenicdata", s);  
			  request.setAttribute("yeardata",y);
			
			return new ModelAndView("scenicannual/aduityear");
		}   
		  /**
		   * 景区年报审核意见
		   * @param request
		   * @param dataGrid
		   * @return
		   */
			@RequestMapping(params = "comments")
			public ModelAndView comments(HttpServletRequest request,DataGrid dataGrid) {
				String scenicdataid = request.getParameter("scenicdataid");
				String yearid = request.getParameter("yearid");
				request.setAttribute("scenicdataid", scenicdataid);  
				 ScenicSpotAnnual y = this.systemService.get(ScenicSpotAnnual.class, yearid);
				  request.setAttribute("yeardata",y);
				
				return new ModelAndView("scenicannual/annualComments");
			}   
		// 景区年报详情
		@RequestMapping(params = "detialinfo")
		public ModelAndView detialinfo(HttpServletRequest request,ScenicSpotAnnual scenicSpotAnnual,ScenicData scenicData,DataGrid dataGrid) {
			String scenicdataid = request.getParameter("scenicdataid");
			String monthid = request.getParameter("monthid");
			request.setAttribute("scenicdataid", scenicdataid);  
			 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			 ScenicSpotAnnual m = this.systemService.get(ScenicSpotAnnual.class, monthid);

			 request.setAttribute("scenicdata", s);  
			 request.setAttribute("monthdata", m);
			 scenicSpotAnnual.setScenicData(s);         
			  request.setAttribute("scenicSpotAnnual",scenicSpotAnnual);
			
			return new ModelAndView("scenicannual/saveannual");
		}   
	//暂存年报
		@RequestMapping(params = "zancuncheck")
		@ResponseBody 
		public AjaxJson saveZanStatus(HttpServletRequest request,ScenicData scenicData,
				ScenicSpotAnnual scenicSpotAnnual,String statusid) {
			AjaxJson j = new AjaxJson();
			String scenicdataid = request.getParameter("scenicdataid");
			request.setAttribute("scenicdataid", scenicdataid);
			String yearid;
						   
				ScenicData scenicdata = systemService.getEntity(ScenicData.class,scenicdataid);
				//5-暂存状态
				scenicSpotAnnual.setScenicData(scenicdata);
			
				if(request.getParameter("yearid")!=null&&request.getParameter("yearid").toString().length()!=0){
					yearid=request.getParameter("yearid");
					scenicSpotAnnual.setId(yearid);
				}
				scenicSpotAnnual.setStatus(Status.notEdit);
				 request.setAttribute("yeardata", scenicSpotAnnual);
				systemService.saveOrUpdate(scenicSpotAnnual);
	    		systemService.addLog("景区年报暂存"+scenicSpotAnnual.getId(), Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);


			return j;
		}
		//删除年报
		@RequestMapping(params = "del")
		@ResponseBody
		public AjaxJson del(ScenicSpotAnnual scenicSpotAnnual, HttpServletRequest req) {
			String id = req.getParameter("id");
			AjaxJson j = new AjaxJson();
			try {
				scenicSpotAnnual = systemService.getEntity(ScenicSpotAnnual.class, id);
				systemService.delete(scenicSpotAnnual);
				message="年报删除成功";
	    		systemService.addLog("景区年报删除成功"+scenicSpotAnnual.getId(), Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);

			} catch (Exception e) {
				message="年报删除失败";
	    		systemService.addLog("景区年报删除失败"+scenicSpotAnnual.getId(), Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);

			}
			
			j.setMsg(message);
			return j;
		}
		/**
		 * User更新
		 */
		@RequestMapping(params="updateScenic")
		public ModelAndView updateScenic(HttpServletRequest request,ScenicSpotAnnual scenicSpotAnnual){
			String scenicdataid = request.getParameter("scenicdataid");
			String yearid = request.getParameter("annualid");   
			request.setAttribute("yearid", yearid);
			request.setAttribute("scenicdataid", scenicdataid);
			 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			 ScenicSpotAnnual m = this.systemService.get(ScenicSpotAnnual.class, yearid);
			 request.setAttribute("scenicdata", s);  
			 request.setAttribute("yeardata", m);    
			return new ModelAndView("scenicannual/updateUserAnnual");
		}
		@RequestMapping(params="updateUser")
		@ResponseBody
		public AjaxJson updateUser(HttpServletRequest request,ScenicSpotAnnual scenicSpotAnnual){
			AjaxJson j=new AjaxJson();
			String scenicdataid= request.getParameter("scenicdataid");
			String yearid = request.getParameter("yearid");   
			ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			Date date=new Date();
			scenicSpotAnnual.setCreatTime(date);
			scenicSpotAnnual.setScenicData(s);
			scenicSpotAnnual.setId(yearid);
			scenicSpotAnnual.setStatus(Status.PendingSubmission);
			systemService.saveOrUpdate(scenicSpotAnnual);
			j.setMsg("更新成功");
    		systemService.addLog("景区年报编辑成功"+scenicSpotAnnual.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);

			return j;
			
			
		}
		
		/**
		 * 年报导出
		 */
		@RequestMapping(params = "excelExport")
		public void exportAnnually(HttpServletRequest request, HttpServletResponse response) throws Exception{

			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	    	Calendar calendar=Calendar.getInstance();
			int year=calendar.get(Calendar.YEAR)-1;
	        
	        // 获取参数 
	        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
	        String beginLevel = new String(request.getParameter("beginLevel").getBytes("iso8859-1"),"utf-8");
	        String endLevel = new String(request.getParameter("endLevel").getBytes("iso8859-1"),"utf-8");
	        String beginYear = new String(request.getParameter("beginYear").getBytes("iso8859-1"),"utf-8");
	        String endYear = new String(request.getParameter("endYear").getBytes("iso8859-1"),"utf-8");
	        String area= new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
	        String scenicType= new String(request.getParameter("scenicType").getBytes("iso8859-1"),"utf-8");
	        if(beginYear==null||beginYear.length()==0){
	        	beginYear=String.valueOf(year);
	        }
	        if(endYear==null||endYear.length()==0){
	        	endYear=String.valueOf(year);
	        }
	        
	        String excelName = "景区年报信息.xls";
	        String modelPath = "/com/zzc/web/export/model/scenic/"+excelName;
	        StringBuffer sql = new StringBuffer();
	        sql.append("select s.name,w.year,w.assets_total,w.build_invest,w.in_build,w.out_build,w.appropriation,w.loan,w.funds_self,w.total_income,w.ticket_income,w.shop_income,w.food_income,w.traffic_income,w.putUp_income,w.show_income,w.other_income,w.reception_num,w.exemptTicket_num,w.job_num,w.fixed_worker,w.temp_worker,w.guide_num,w.remarks,w.creat_time,if(w.`status`='1','未分配',if(w.`status`='2','已分配',if(w.`status`='3','未填报',if(w.`status`='4','提交待审',if(w.`status`='5','退回修订',if(w.`status`='6','区级审核',if(w.`status`='7','市级审核',if(w.`status`='8','省级审核','国家审核')))))))) 'status' from t_scenicspot_annual w left join t_scenicspot_info s on s.id=w.annual_id where 1=1 and w.status in (7,8,9)");
	        // 组装查询条件
	        if(name !=null && name.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.name like '%"+name+"%'  and  s.id=w.annual_id)");
	        }
	        if(area !=null && area.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.area ="+area+"  and  s.id=w.annual_id)");
	        }
	        if(scenicType !=null && scenicType.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.scenic_type ="+scenicType+"  and  s.id=w.annual_id)");
	        }
	        if(beginLevel !=null && beginLevel.length() != 0&&endLevel !=null && endLevel.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.level between '"+endLevel+"' and '"+beginLevel+"' and  s.id=w.annual_id)");
	        }
	        if(beginYear !=null && beginYear.length() != 0&&endYear!=null&&endYear.length()!=0){
	        	sql.append(" and w.year between '"+beginYear+"' and '"+endYear+"'");
	        }
	        sql.append(" UNION all select '合计','',sum(w.assets_total),sum(w.build_invest),sum(w.in_build),sum(w.out_build),sum(w.appropriation),sum(w.loan),sum(w.funds_self),sum(w.total_income),sum(w.ticket_income),sum(w.shop_income),sum(w.food_income),sum(w.traffic_income),sum(w.putUp_income),sum(w.show_income),sum(w.other_income),sum(w.reception_num),sum(w.exemptTicket_num),sum(w.job_num),sum(w.fixed_worker),sum(w.temp_worker),sum(w.guide_num),'','','' from t_scenicspot_annual w left join t_scenicspot_info s on s.id=w.annual_id where 1=1 and w.status in (7,8,9)");
	        if(name !=null && name.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.name like '%"+name+"%'  and  s.id=w.annual_id)");
	        }
	        if(area !=null && area.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.area ="+area+"  and  s.id=w.annual_id)");
	        }
	        if(scenicType !=null && scenicType.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.scenic_type ="+scenicType+"  and  s.id=w.annual_id)");
	        }
	        if(beginLevel !=null && beginLevel.length() != 0&&endLevel !=null && endLevel.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.level between '"+endLevel+"' and '"+beginLevel+"' and  s.id=w.annual_id)");
	        }
	        if(beginYear !=null && beginYear.length() != 0&&endYear!=null&&endYear.length()!=0){
	        	sql.append(" and w.year between '"+beginYear+"' and '"+endYear+"'");
	        }
	        ExportService emds = new ExportService(26); 
	        
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
/**
 * 同步年报数据
 */
		@RequestMapping(params = "tongbu")
		@ResponseBody
		public AjaxJson tongbu(HttpServletRequest request
				) {
			AjaxJson aj=new AjaxJson();
			List<String> list=ReadJson.instsOfProvince();
			try {
				for(int i=0;i<list.size();i++){
					for(int j=2014;j<2018;j++){
						int year=j;
						String code=list.get(i);
						List<ScenicData> list1 = this.systemService.findHql("from ScenicData ssa where ssa.code=?", code);
						ScenicSpotAnnual scenicSpotAnnual=ReadJson.findAnnual(year, code);
						if(scenicSpotAnnual!=null){
							ScenicData d=list1.get(0);
							  scenicSpotAnnual.setScenicData(d); 
							  scenicSpotAnnual.setYear(String.valueOf(year));
							  
							  if(scenicSpotAnnual.getStatus().equals(Status.cityAudit)){
								  scenicSpotAnnual.setProvinceSubmit("1");
							  }
							systemService.saveOrUpdate(scenicSpotAnnual);
						}
							
						
					}
					
				}
				aj.setMsg("同步成功");
	    		systemService.addLog("景区年报同步成功", Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				aj.setMsg("同步失败");
				systemService.addLog("景区年报同步失败", Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
			}
		

			return aj;
		}

		
}
