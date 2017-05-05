package com.zzc.web.scenicmanage.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
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
import com.zzc.core.util.ResourceUtil;
import com.zzc.core.util.StringUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.scenicmanage.entity.ScenicSpotQuarter;
import com.zzc.web.scenicmanage.util.TimeUtils;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
import com.zzc.webservice.ReadJson;






/**
 * @Title: Controller
 * @Description: 景区季报信息管理
 * @author 冯勇齐
 * @date 2016-11-28 21:50:55
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/scenicQuarterController")
public class ScenicSpotQuarterController   extends BaseController {

	
	
	
	private TimeUtils timeUtils;
	
	
	
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
	
    //景区季报列表
    @RequestMapping(params = "tolist")
	public String scenicWeekList(HttpServletRequest request) {
    	Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowSeason = this.getQuarter();
		nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
		if(nowSeason.equals("0")){
			nowYear=String.valueOf(calendar.get(Calendar.YEAR)-1);
			nowSeason="4";
		}
		request.setAttribute("year", nowYear);
		request.setAttribute("quarter", nowSeason);
		return "/scenicquarter/scenicquarterList";

	}
    //景区季报审核列表
	@RequestMapping(params = "toAuditList")
	public String scenicWeekAuditList(HttpServletRequest request) {
		String roleName = ResourceUtil.getSessionUserName().getUserKey();
		request.setAttribute("roleName", roleName);
		return "/scenicquarter/scenicquarterAuditList";

	}
	
	//用户季报列表
	@RequestMapping(params = "toUserQuaeterList")
	public String scenicUserQuarterList(HttpServletRequest request) {
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
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			if(nowSeason.equals("0")){
				nowYear=String.valueOf(calendar.get(Calendar.YEAR)-1);
				nowSeason="4";
			}
			List<Map<String,Object>> list1=systemService.findForJdbc("select * from t_scenicspot_quarterly tsw where tsw.quarter_id=? and tsw.year=? and tsw.quarter=?", scenicdataid,nowYear,nowSeason);
			 if(list1.size()>0){
					request.setAttribute("scenicquarterdata", "have");
				} 
		}else{
			request.setAttribute("scenicdata", "null");
		}
		return "/scenicquarter/scenicUserQuarterList";

	}
	
	//添加季报
	@RequestMapping(params = "add")
	public String scenicaddWeek(HttpServletRequest request) {
	
		return "/scenicquarter/save2quarter";

	}
	//用户季报录入

	@RequestMapping(params = "addUser")
	public String scenicaddQuarter(HttpServletRequest request) {
		String userId = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userId);
		if(list.size()>0){
			ScenicData scenicData=list.get(0);
			 Calendar calendar = Calendar.getInstance();
				String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
				String nowSeason = this.getQuarter();
				nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
				if(nowSeason.equals("0")){
					nowYear=String.valueOf(calendar.get(Calendar.YEAR)-1);
					nowSeason="4";
				}
				request.setAttribute("scenicData",scenicData);
				request.setAttribute("year",nowYear);
				request.setAttribute("quarter",nowSeason);
		}
		return "/scenicquarter/saveUserQuarter";
		
	}
	
	/**
	 * 景区用户季报填报
	 * 
	 * @param 
	 * @param req
	 * @return
	 */	
    @RequestMapping(params = "saveUser")
	@ResponseBody
	public AjaxJson saveScenicUserQuarter(HttpServletRequest request, ScenicSpotQuarter scenicSpotQuarter) {

		AjaxJson j = new AjaxJson();
		//String scenicdataid = request.getParameter("scenicdataid");
			TSUser user = ResourceUtil.getSessionUserName();
			List<ScenicData> list = this.systemService.findByProperty(ScenicData.class, "userId", user.getId());
			for (ScenicData sd : list) {
				scenicSpotQuarter.setScenicData(sd); 
			}
			Date date=new Date();
			scenicSpotQuarter.setReportdate(date);
			scenicSpotQuarter.setSid(user.getId());
			scenicSpotQuarter.setStatus(Status.PendingSubmission);  
		// 填报后积分增加
		if (scenicSpotQuarter.getId() == null
				|| scenicSpotQuarter.getId().length() == 0) {
			if (ReportTimeCheck.quarterTimeCheck(scenicSpotQuarter.getYear(),
					scenicSpotQuarter.getQuarter())) {
				scenicSpotQuarter.setScore(10);
			} else {
				scenicSpotQuarter.setScore(5);
			}
			UserScoreUtils.scoreChange(scenicSpotQuarter.getScore());
		}
			systemService.saveOrUpdate(scenicSpotQuarter);
    		systemService.addLog("景区季报录入"+scenicSpotQuarter.getId(), Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);

			return j;
	}
	
    
    /**
	 * 景区用户季报列表
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "userDatagrid")
	public void quarterDataGrid(ScenicSpotQuarter scenicSpotQuarter, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String userid = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userid);
		if(list.size()>0){
			ScenicData scenicData=list.get(0);
			 CriteriaQuery cq = new CriteriaQuery(ScenicSpotQuarter.class, dataGrid);
		    
		     cq.eq("scenicData",scenicData);
		     com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotQuarter);
		     this.systemService.getDataGridReturn(cq, true);

			 TagUtil.datagrid(response, dataGrid);
		}
		
		
	
		
	}	
	
	
	/**
	 * 景区季报管理列表
	 * 
	 * @param 
	 * @param req
	 * @return
	 */  
	@RequestMapping(params = "datagrid")
	public void  datagrid(ScenicSpotQuarter scenicSpotQuarter, ScenicData scenicData,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        
		CriteriaQuery cq = new CriteriaQuery(ScenicSpotQuarter.class, dataGrid);
		cq.add(Restrictions.or(Restrictions.eq("status",Status.cityAudit),Restrictions.eq("status",Status.provinceAudit),Restrictions.eq("status",Status.countryAudit)));//过滤状态为区级审核或者市级审核的数据
		cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.level in( 1,2,3,4,5) and t.id=quarter_id)"));
		String year=request.getParameter("year");
     String quarter=request.getParameter("quarter");
     if(year==null||quarter==null){
  		Calendar calendar = Calendar.getInstance(); 
	 	int year1=calendar.get(Calendar.YEAR);
        int season1 = Integer.parseInt(this.getQuarter());
        Integer seasonInteger =season1-1;
       if(seasonInteger==0){
    	   year1=year1-1;
    	   seasonInteger=4;
       }
       
       year=String.valueOf(year1);
       quarter=String.valueOf(seasonInteger);
     }
  	cq.add(Restrictions.eq("year",Integer.parseInt(year)));
     cq.add(Restrictions.eq("quarter",Integer.parseInt(quarter)));
      
     //查询条件过滤
        String name = scenicSpotQuarter.getScenicData() == null ? "" : scenicSpotQuarter.getScenicData().getName();
        String beginLevel=request.getParameter("scenicData.level_begin");
        String endLevel=request.getParameter("scenicData.level_end");
        String area1=scenicSpotQuarter.getScenicData() == null ? "" : scenicSpotQuarter.getScenicData().getArea();
        String scenicType=scenicSpotQuarter.getScenicData() == null ? "" : scenicSpotQuarter.getScenicData().getScenictype();
        if(name!=null&&name.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.name like '%"+name+"%' and t.id=quarter_id)"));
        }
        if(area1!=null&&area1.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.area like '%"+area1+"%' and t.id=quarter_id)"));
        }
        if(scenicType!=null&&scenicType.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.scenic_type like '%"+scenicType+"%' and t.id=quarter_id)"));
        }
        if(beginLevel!=null&&endLevel!=null&&beginLevel.length()!=0&&endLevel.length()!=0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.level between'"+endLevel+"' and '"+beginLevel+"' and  t.id=quarter_id)"));
        }
        //区级管理员过滤
			String roleName = ResourceUtil.getSessionUserName().getUserKey();
			String area=ResourceUtil.getSessionUserName().getArea();
			if(roleName.equals("区级管理员")){
					scenicData.setArea(area);
			}
		  scenicSpotQuarter.setScenicData(scenicData);
        //查询条件组装器
        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotQuarter);
         systemService.getDataGridReturn(cq, true);
         TagUtil.datagrid(response, dataGrid);
    }  
	
	/**
	 * 景区季报审核列表
	 * 
	 * @param 
	 * @param req
	 * @return
	 */
	 @RequestMapping(params = "quarterdatagrid")
	 public void quarterDatagrid(ScenicSpotQuarter scenicSpotQuarter, ScenicData scenicData,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
	        CriteriaQuery cq = new CriteriaQuery(ScenicData.class, dataGrid);
	    	cq.add(Restrictions.sqlRestriction("this_ .level in (1,2,3,4,5)"));
	        //获取当前季度和年份
            Date date = new Date();
            Calendar calendar = Calendar.getInstance(); 
		 	Integer year=calendar.get(Calendar.YEAR);
            int season = TimeUtils.getSeason(date);
	       Integer seasonInteger =season-1;
	       if(seasonInteger==0){
	    	   year=year-1;
	    	   seasonInteger=4;
	       }
	      //区级管理员过滤
			String roleName = ResourceUtil.getSessionUserName().getUserKey();
			String area=ResourceUtil.getSessionUserName().getArea();
			if(roleName.equals("区级管理员")){
					scenicData.setArea(area);
			}
			 //查询条件组装器
	    	String status=scenicData.getSpotQuarter()==null?"":scenicData.getSpotQuarter().getStatus();
			if(status!=null&&status.length()!=0&&!status.equals(Status.notEdit)){
				cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_quarterly t where t.status ="+status+" and t.quarter_id=this_.id and t.quarter= "+seasonInteger+" and t.year= "+year+")"));
			}else if(status.equals(Status.notEdit)){
				cq.add(Restrictions.sqlRestriction(" 1=1 and not exists(select * from t_scenicspot_quarterly t where t.status <>"+status+" and t.quarter_id=this_.id and t.quarter= "+seasonInteger+" and  t.year= "+year+")"));
			}
			
			String begindate=request.getParameter("spotQuarter.reportdate_begin");
			String enddate=request.getParameter("spotQuarter.reportdate_end");
			if(begindate!=null&&enddate!=null&&begindate.length()!=0&&enddate.length()!=0){
				cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_quarterly t where t.reportdate between'"+begindate+"' and '"+enddate+"' and t.quarter_id=this_.id)"));
			}
			ScenicController.getScenicLevel(request, cq);
			  String name=scenicData.getName();
		        if(name != null && name .length() != 0){
		        	cq.add(Restrictions.sqlRestriction("this_ .name like '%"+scenicData.getName()+"%'"));
				}
		        scenicData.setName(null);
	       
			scenicData.setSpotQuarter(null);
			com.zzc.core.extend.hqlsearch.HqlGenerateUtil
					.installHql(cq, scenicData);
	         systemService.getDataGridReturn(cq, true);

	         for (Object o : dataGrid.getResults()) {
	 			ScenicData sd = (ScenicData) o;
	 			if (sd.getScenicQuarterList() != null) {
	 				for (ScenicSpotQuarter quarterdata : sd.getScenicQuarterList()) {
	 					//String d2Number = sdf.format(monthdata.getTime());
	 					if (year.equals(quarterdata.getYear())&&seasonInteger.equals(quarterdata.getQuarter())){
	 						sd.setSpotQuarter(quarterdata);
	 						 break;
	 					 }
	 					
	 						
	 							
	 				}
	                if(sd.getSpotQuarter()==null){
	                	ScenicSpotQuarter sq = new ScenicSpotQuarter();
	             	sq.setYear(year);
	             	sq.setQuarter(seasonInteger);
	             	   sq.setStatus(Status.notEdit);
	             	   sd.setSpotQuarter(sq);
	                }

	 			}

	 		}	         
	         
	        TagUtil.datagrid(response, dataGrid);
	    }
	 /**
	  * 获取当前的季度
	  *  
	  */
	 private String getQuarter(){
			Calendar calendar = Calendar.getInstance();
			int nowMonth = calendar.get(Calendar.MONTH) +1;
			String quarter = "";
			if(nowMonth <= 3) quarter = "1";
			if(3 < nowMonth && nowMonth <= 6) quarter = "2";
			if(6 < nowMonth && nowMonth <= 9) quarter = "3";
			if(9 < nowMonth && nowMonth <= 12) quarter = "4";
			return quarter;
		}
	 
	   /**
		 * 景区季报代填报jsp
		 * 
		 * @param 
		 * @param req
		 * @return
		 */
	    @RequestMapping(params = "addsign")
		public ModelAndView addsign(HttpServletRequest request,ScenicSpotQuarter scenicSpotQuarter,ScenicData scenicData) {
			String scenicdataid = request.getParameter("scenicdataid");
			String quarterid = request.getParameter("quarterid");

			request.setAttribute("scenicdataid", scenicdataid);
			request.setAttribute("quarterid", quarterid);
			 ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
			 ScenicSpotQuarter s = this.systemService.get(ScenicSpotQuarter.class, quarterid);
			 request.setAttribute("scenicdata", d);
			 if(s==null){
				 s=new ScenicSpotQuarter();
				 Calendar calendar = Calendar.getInstance();
					String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
					String nowSeason = this.getQuarter();
					nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
					if(nowSeason.equals("0")){
						nowYear=String.valueOf(calendar.get(Calendar.YEAR)-1);
						nowSeason="4";
					}
					s.setYear(Integer.valueOf(nowYear));
					s.setQuarter(Integer.valueOf(nowSeason));
			 }
				 
			 request.setAttribute("quarterdata", s);
			return new ModelAndView("scenicquarter/save2quarter");    
		}
	 /**
	  * 景区季报管理员编辑
	  */
		 @RequestMapping(params = "edit")
			public ModelAndView edit(HttpServletRequest request) {
				String scenicdataid = request.getParameter("scenicdataid");
				String quarterid = request.getParameter("quarterid");
				request.setAttribute("scenicdataid", scenicdataid);  
				request.setAttribute("quarterid", quarterid);  
				 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
				 ScenicSpotQuarter m = this.systemService.get(ScenicSpotQuarter.class, quarterid);
				 
				 request.setAttribute("scenicdata", s);  
				 request.setAttribute("quarterdata", m);
				
				return new ModelAndView("scenicquarter/save2quarter");
			}
		 /**
		  * 季报的审核撤回
		  */
		 @RequestMapping(params = "back")
		 @ResponseBody
			public AjaxJson back(HttpServletRequest request) {
			 AjaxJson j=new AjaxJson();
				String quarterid = request.getParameter("quarterid");
				 ScenicSpotQuarter m = this.systemService.get(ScenicSpotQuarter.class, quarterid);
				 m.setStatus(Status.cityAudit);
				 systemService.saveOrUpdate(m);
				j.setMsg("退回成功");
				return j;
			}
	   /**
		 * 景区季报详情jsp
		 * 
		 * @param 
		 * @param req
		 * @return
		 */
	 @RequestMapping(params = "detial")
		public ModelAndView detial(HttpServletRequest request,ScenicSpotQuarter scenicSpotQuarter,ScenicData scenicData,DataGrid dataGrid) {
			String scenicdataid = request.getParameter("scenicdataid");
			String quarterid = request.getParameter("quarterid");
			request.setAttribute("scenicdataid", scenicdataid);  
			 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			 ScenicSpotQuarter m = this.systemService.get(ScenicSpotQuarter.class, quarterid);

			 request.setAttribute("scenicdata", s);  
			 request.setAttribute("quarterdata", m);
			 scenicSpotQuarter.setScenicData(s);       
			  request.setAttribute("scenicSpotQuarter",scenicSpotQuarter);
			
			return new ModelAndView("scenicquarter/aduitquarter");
		}
	 /*
	  * 景区季报审核意见
	  */
	 @RequestMapping(params = "getComments")
		public ModelAndView getComments(HttpServletRequest request,ScenicSpotQuarter scenicSpotQuarter,ScenicData scenicData,DataGrid dataGrid) {
			String quarterid = request.getParameter("quarterid");
			 ScenicSpotQuarter m = this.systemService.get(ScenicSpotQuarter.class, quarterid);

			 request.setAttribute("quarterdata", m);
			
			return new ModelAndView("scenicquarter/quarterComments");
		}
	 
	   /**
		 * 景区季报代填报
		 * 
		 * @param 
		 * @param req
		 * @return
		 */
	    @RequestMapping(params = "save")
		@ResponseBody
		public AjaxJson saveScenic(HttpServletRequest request, ScenicSpotQuarter scenicSpotQuarter) {

			AjaxJson j = new AjaxJson();
			String scenicdataid = request.getParameter("scenicdataid");
			  ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
			  	String quarterid;
				 
			       if(request.getParameter("quarterid")!=null&&request.getParameter("quarterid").toString().length()!=0){
						quarterid=request.getParameter("quarterid");
						scenicSpotQuarter.setId(quarterid);
					}
			      Date date=new Date();
			      scenicSpotQuarter.setReportdate(date);
				scenicSpotQuarter.setScenicData(d); 
				 String roleName=ResourceUtil.getSessionUserName().getUserKey();
					if(roleName.equals("区级管理员")){
						scenicSpotQuarter.setStatus(Status.areaAudit);
						systemService.saveOrUpdate(scenicSpotQuarter);
			    		systemService.addLog("景区季报区级管理员代填报"+scenicSpotQuarter.getId(), Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);

					}else{
					
					 
								scenicSpotQuarter.setStatus(Status.cityAudit);
								scenicSpotQuarter.setProvinceSubmit("1");
								systemService.saveOrUpdate(scenicSpotQuarter);
								j.setMsg("填报成功");
					    		systemService.addLog("景区季报市级管理员代填报"+scenicSpotQuarter.getId(), Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);

						
					}
		
			return j;
		}
	    
	    /**
		 * 景区季报审核状态，通过
		 * 
		 * @param 
		 * @param req
		 * @return
		 */
	    @RequestMapping(params = "addstatus")
		@ResponseBody
		public AjaxJson saveStatus(HttpServletRequest request,
				ScenicSpotQuarter scenicSpotQuarter,ScenicData scenicData,String statusid) {
			AjaxJson j = new AjaxJson();
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			if (StringUtil.isNotEmpty(id)) {
				ScenicSpotQuarter scenicQuarter = systemService.getEntity(ScenicSpotQuarter.class,id);
				String roleName = ResourceUtil.getSessionUserName().getUserKey();
		        if(roleName.equals("区级管理员")){
		        	scenicQuarter.setStatus(Status.areaAudit);//4表示区级审核通过
		        	systemService.saveOrUpdate(scenicQuarter);
		    		systemService.addLog("景区季报区级管理员审核通过"+scenicSpotQuarter.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);

				}else{
					
					 
							scenicQuarter.setStatus(Status.cityAudit);//5表示市级审核通过
							scenicQuarter.setProvinceSubmit("1");
							systemService.saveOrUpdate(scenicQuarter);
							j.setMsg("市级管理员审核成功");
				    		systemService.addLog("景区季报市级管理员审核通过"+scenicSpotQuarter.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);

						 
				}
			}
			

			return j;
		}
	    /**
		 * 景区季报审核状态，退回
		 * 
		 * @param 
		 * @param req
		 * @return
		 */
		@RequestMapping(params = "nocheck")
		@ResponseBody
		public AjaxJson saveNotStatus(HttpServletRequest request,ScenicData scenicData,
				ScenicSpotQuarter scenicSpotQuarter,String statusid) {
			AjaxJson j = new AjaxJson();
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			if (StringUtil.isNotEmpty(id)) {
				ScenicSpotQuarter scenicQuarter = systemService.getEntity(ScenicSpotQuarter.class,id);
				String userId=scenicQuarter.getScenicData().getUserId();
				//3-已审核（审核不通过）
				scenicQuarter.setStatus(Status.notPass);
				//诚信积分的增减
				if(scenicQuarter.getScore()>0){
					scenicQuarter.setScore(scenicQuarter.getScore()-5);
					UserScoreUtils.scoreChange(-5,userId);
				}
				if(scenicQuarter.getScore()<=0){
					scenicQuarter.setScore(0);
				}
				systemService.saveOrUpdate(scenicQuarter);
	    		systemService.addLog("景区季报市级管理员审核退回"+scenicSpotQuarter.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);

			}

			return j;
		}
		
		/**
		 * 景区季报暂存
		 * 
		 * @param 
		 * @param req
		 * @return
		 */
		
		@RequestMapping(params = "zancuncheck")
		@ResponseBody 
		public AjaxJson saveZanStatus(HttpServletRequest request,ScenicData scenicData,
				ScenicSpotQuarter scenicSpotQuarter,String statusid) {
			AjaxJson j = new AjaxJson();
			String scenicdataid = request.getParameter("scenicdataid");
			request.setAttribute("scenicdataid", scenicdataid);
			String quarterid;
				ScenicData scenicdata = systemService.getEntity(ScenicData.class,scenicdataid);
				scenicSpotQuarter.setScenicData(scenicdata);
				//获取quarterid
				if(request.getParameter("quarterid")!=null&&request.getParameter("quarterid").toString().length()!=0){
					quarterid=request.getParameter("quarterid");
					scenicSpotQuarter.setId(quarterid);
				}

			    //设置状态
				scenicSpotQuarter.setStatus(Status.notEdit);
				 request.setAttribute("quarterdata", scenicSpotQuarter);
				systemService.saveOrUpdate(scenicSpotQuarter);
				j.setMsg("暂存成功");
	    		systemService.addLog("景区季报暂存"+scenicSpotQuarter.getId(), Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);

			return j;
		}
		
		
		 //审核页面
		@RequestMapping(params = "audit")
		public ModelAndView audit(HttpServletRequest request,ScenicSpotQuarter scenicSpotQuarter,ScenicData scenicData,DataGrid dataGrid) {
			String scenicdataid = request.getParameter("scenicdataid");
			String quarterid = request.getParameter("quarterid");
			request.setAttribute("scenicdataid", scenicdataid);  
			 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			 ScenicSpotQuarter q = this.systemService.get(ScenicSpotQuarter.class, quarterid);

			 request.setAttribute("scenicdata", s);     
			 request.setAttribute("quarterdata", q);
			 scenicSpotQuarter.setScenicData(s);    
			  request.setAttribute("scenicSpotQuarter",scenicSpotQuarter);
			
			return new ModelAndView("scenicquarter/aduitquarter");
		}		
		/**
		 * 删除季报
		 * @param scenicSpotQuarter
		 * @param req
		 * @return
		 */
		@RequestMapping(params = "del")
		@ResponseBody
		public AjaxJson del(ScenicSpotQuarter scenicSpotQuarter, HttpServletRequest req) {
			String id = req.getParameter("id");
			AjaxJson j = new AjaxJson();
			try {
				scenicSpotQuarter = systemService.getEntity(ScenicSpotQuarter.class, id);
				systemService.delete(scenicSpotQuarter);
				message="季报删除成功";
	    		systemService.addLog("景区季报删除成功"+scenicSpotQuarter.getId(), Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);

			} catch (Exception e) {
				message="季报删除失败";
	    		systemService.addLog("景区季报删除失败"+scenicSpotQuarter.getId(), Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);

			}
			
			j.setMsg(message);
			return j;
		}
		/**
		 * User更新
		 */
		@RequestMapping(params="updateScenic")
		public ModelAndView updateScenic(HttpServletRequest request,ScenicSpotQuarter scenicSpotQuarter){
			String scenicdataid = request.getParameter("scenicdataid");
			String quarterid = request.getParameter("quarterid");   
			request.setAttribute("quarterid", quarterid);
			request.setAttribute("scenicdataid", scenicdataid);
			 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			 ScenicSpotQuarter m = this.systemService.get(ScenicSpotQuarter.class, quarterid);
			 request.setAttribute("scenicdata", s);  
			 request.setAttribute("quarterdata", m);    
			return new ModelAndView("scenicquarter/updateUserQuarter");
		}
		@RequestMapping(params="updateUser")
		@ResponseBody
		public AjaxJson updateUser(HttpServletRequest request,ScenicSpotQuarter scenicSpotQuarter){
			AjaxJson j=new AjaxJson();
			String scenicdataid= request.getParameter("scenicdataid");
			String quarterid = request.getParameter("quarterid");   
			ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
			Date date=new Date();
			scenicSpotQuarter.setReportdate(date);
			scenicSpotQuarter.setScenicData(s);
			scenicSpotQuarter.setId(quarterid);
			scenicSpotQuarter.setStatus(Status.PendingSubmission);
			systemService.saveOrUpdate(scenicSpotQuarter);
			j.setMsg("更新成功");
    		systemService.addLog("景区季报编辑"+scenicSpotQuarter.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
			return j;
			
			
		}
		/**
		 * 季报导出
		 */
		@RequestMapping(params = "excelExport")
		public void exportQuarterly(HttpServletRequest request, HttpServletResponse response) throws Exception{

			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        //获取默认的上周期的时间
	        Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowSeason = this.getQuarter();
			nowSeason = String.valueOf(Integer.parseInt(nowSeason) - 1);
			if(nowSeason.equals("0")){
				nowYear=String.valueOf(calendar.get(Calendar.YEAR)-1);
				nowSeason="4";
			}
	        
	        // 获取参数 
	        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
	        String beginLevel = new String(request.getParameter("beginLevel").getBytes("iso8859-1"),"utf-8");
	        String endLevel = new String(request.getParameter("endLevel").getBytes("iso8859-1"),"utf-8");
	        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
	        String quarter= new String(request.getParameter("quarter").getBytes("iso8859-1"),"utf-8");
	        String area= new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
	        String scenicType= new String(request.getParameter("scenicType").getBytes("iso8859-1"),"utf-8");
	        if(year==null||year.length()==0){
	        	year=nowYear;
	        }
	        if(quarter==null||quarter.length()==0){
	        	quarter=nowSeason;
	        }
	        
	        String excelName = "景区季报信息.xls";
	        String modelPath = "/com/zzc/web/export/model/scenic/"+excelName;
	        StringBuffer sql = new StringBuffer();
	        sql.append("select s.name,w.year,w.quarter,w.total_income,w.ticket_income,w.reception_num,w.exempt_ticket_num,w.remarks,w.reportdate,if(w.`status`='1','未分配',if(w.`status`='2','已分配',if(w.`status`='3','未填报',if(w.`status`='4','提交待审',if(w.`status`='5','退回修订',if(w.`status`='6','区级审核',if(w.`status`='7','市级审核',if(w.`status`='8','省级审核','国家审核')))))))) 'status' from t_scenicspot_quarterly w left join t_scenicspot_info s on s.id=w.quarter_id where 1=1 and w.status in (7,8,9)");
	        // 组装查询条件
	        if(name !=null && name.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.name like '%"+name+"%'  and  s.id=w.quarter_id)");
	        }
	        if(area !=null && area.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.area ="+area+"  and  s.id=w.quarter_id)");
	        }
	        if(scenicType !=null && scenicType.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.scenic_type ="+scenicType+"  and  s.id=w.quarter_id)");
	        }
	        if(beginLevel !=null && beginLevel.length() != 0&&endLevel !=null && endLevel.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.level between '"+endLevel+"' and '"+beginLevel+"' and  s.id=w.quarter_id)");
	        }
	        if(year !=null && year.length() != 0){
	        	sql.append(" and w.year ='"+year+"'");
	        }
	        if(quarter !=null && quarter.length() != 0){
	        	sql.append(" and w.quarter ='"+quarter+"'");
	        }    
	        sql.append(" union all ");
	        sql.append(" select '合计','','',sum(w.total_income),sum(w.ticket_income),sum(w.reception_num),sum(w.exempt_ticket_num),'','','' from t_scenicspot_quarterly w left join t_scenicspot_info s on s.id=w.quarter_id where 1=1 and w.status in (7,8,9)");
	        if(name !=null && name.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.name like '%"+name+"%'  and  s.id=w.quarter_id)");
	        }
	        if(area !=null && area.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.area ="+area+"  and  s.id=w.quarter_id)");
	        }
	        if(scenicType !=null && scenicType.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.scenic_type ="+scenicType+"  and  s.id=w.quarter_id)");
	        }
	        if(beginLevel !=null && beginLevel.length() != 0&&endLevel !=null && endLevel.length() != 0){
	        	sql.append(" and exists(select * from t_scenicspot_info s where s.level between '"+endLevel+"' and '"+beginLevel+"' and  s.id=w.quarter_id)");
	        }
	        if(year !=null && year.length() != 0){
	        	sql.append(" and w.year ='"+year+"'");
	        }
	        if(quarter !=null && quarter.length() != 0){
	        	sql.append(" and w.quarter ='"+quarter+"'");
	        }    
	        ExportService emds = new ExportService(10); 
	        
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
		 * 同步季度经营数据
		 */
	    @RequestMapping(params = "tongbu")
			@ResponseBody
			public AjaxJson tongbu(HttpServletRequest request) {
	    	AjaxJson aj=new AjaxJson();
	    	List<String> list=ReadJson.instsOfProvince();
	    	try {
	    		for(int i=0;i<list.size();i++){
					for(int j=2014;j<2018;j++){
						int year=j;
						for(int k=1;k<=4;k++){
							int quarter=k;
							String code=list.get(i);
							List<ScenicData> list1 = this.systemService.findHql("from ScenicData ssa where ssa.code=?", code);
							ScenicSpotQuarter scenicSpotQuarter=ReadJson.findSeason(year, code, quarter);
							if(scenicSpotQuarter!=null){
								ScenicData d=list1.get(0);
								scenicSpotQuarter.setScenicData(d); 
								scenicSpotQuarter.setYear(year);
								scenicSpotQuarter.setQuarter(quarter);
								if(scenicSpotQuarter.getStatus().equals(Status.cityAudit)){
									scenicSpotQuarter.setProvinceSubmit("1");
								}
								systemService.saveOrUpdate(scenicSpotQuarter);
							}
						}
						}
						
				}
				aj.setMsg("同步完成");
	    		systemService.addLog("景区季报同步成功", Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				aj.setMsg("同步失败");
	    		systemService.addLog("景区季报同步失败", Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
			}
		

				return aj;
			}
}
