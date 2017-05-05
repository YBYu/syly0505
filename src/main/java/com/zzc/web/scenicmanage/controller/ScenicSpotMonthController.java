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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotMonth;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.ReportTimeCheck;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.UserScoreUtils;
import com.zzc.web.sylyUtils.WeekUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

/**
 * @Title: Controller
 * @Description: 景区月报信息管理
 * @author 冯勇齐
 * @date 2016-11-29 21:50:55
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/scenicMonthController")
public class ScenicSpotMonthController {

	private String message = null;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(ScenicSpotWeekController.class);
	// @Autowired
	private SystemService systemService;
	private ScenicData o;

	// private ScenicSpotMonthService scenicSpotMonthService;
	// public ScenicSpotMonthService getScenicSpotMonthService() {
	// return scenicSpotMonthService;
	// }
	// @Autowired
	// public void setScenicSpotMonthService(ScenicSpotMonthService
	// scenicSpotMonthService) {
	// this.scenicSpotMonthService = scenicSpotMonthService;
	// }
	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	/**
	 * 月报列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "tolist")
	public String scenicWeekList(HttpServletRequest request) {
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);   
	      //第几周  
	      if(month==0){
	    	  year=year-1;
	    	  month=12;
	      }
	      request.setAttribute("year", year);
	      request.setAttribute("month", month);
		return "/scenicmonth/scenicMonthList";

	}
/**
 * 月报审批列表
 * @param request
 * @return
 */
	@RequestMapping(params = "toAuditList")
	public String scenicWeekAuditList(HttpServletRequest request) {
		String roleName = ResourceUtil.getSessionUserName().getUserKey();
		request.setAttribute("roleName", roleName);
		return "/scenicmonth/scenicMonthAuditList";

	}
	/**
	 * 用户月报列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toUserMonthList")
	public String scenicUserMonthList(HttpServletRequest request) {
		String userId = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userId);
		if(list.size()>0){		
			request.setAttribute("scenicdata", "scenicdata");
			String scenicdataid=list.get(0).getId();
			Calendar calendar=Calendar.getInstance();
			int year=calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);   
		      //第几周  
		      if(month==0){
		    	  year=year-1;
		    	  month=12;
		      }
			 List<Map<String,Object>> list1=systemService.findForJdbc("select * from t_scenicspot_monthly tsw where tsw.month_id=? and tsw.year=? and tsw.month=?", scenicdataid,year,month);
			 if(list1.size()>0){
					request.setAttribute("scenicmonthdata", "have");
				} 
		}else{
			request.setAttribute("scenicdata", "null");
		}
		return "/scenicmonth/scenicUserMonthList";

	}

	@RequestMapping(params = "add")
	public String scenicMonth() {

		return "scenicmonth/savemonth";
	}
	
	//添加景区月报
	@RequestMapping(params = "addUser")
	public String scenicUserMonth(HttpServletRequest request,ScenicSpotMonth scenicmonth) {
		String userId = ResourceUtil.getSessionUserName().getId();
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userId);
		if(list.size()>0){
			ScenicData scenicData=list.get(0);
			Calendar calendar=Calendar.getInstance();
			int year=calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);   
		      //第几周  
		      if(month==0){
		    	  year=year-1;
		    	  month=12;
		      }
				request.setAttribute("scenicData",scenicData);
				request.setAttribute("year",year);
				request.setAttribute("month",month);
				request.setAttribute("scenicmonth", scenicmonth);
		}
		return "scenicmonth/saveUserMonth";  
		
	}
	/**
	 * 景区月报编辑
	 * @param request
	 * @param scenicSpotMonth
	 * @return
	 */
	@RequestMapping(params="updateScenic")
	public ModelAndView updateScenic(HttpServletRequest request,ScenicSpotMonth scenicSpotMonth){
		String scenicdataid = request.getParameter("scenicdataid");
		String monthid = request.getParameter("monthid");   
		request.setAttribute("monthid", monthid);
		request.setAttribute("scenicdataid", scenicdataid);
		 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotMonth m = this.systemService.get(ScenicSpotMonth.class, monthid);
		 request.setAttribute("scenicdata", s);  
		 request.setAttribute("monthdata", m);    
		return new ModelAndView("scenicmonth/updateUserMonth");
	}
	@RequestMapping(params="updateUser")
	@ResponseBody
	public AjaxJson updateUser(HttpServletRequest request,ScenicSpotMonth scenicSpotMonth){
		AjaxJson j=new AjaxJson();
		String scenicdataid= request.getParameter("scenicdataid");
		String monthid = request.getParameter("monthid");   
		ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		Date date=new Date();
		scenicSpotMonth.setReportdate(date);
		scenicSpotMonth.setScenicData(s);
		scenicSpotMonth.setId(monthid);
		scenicSpotMonth.setStatus(Status.PendingSubmission);
		systemService.saveOrUpdate(scenicSpotMonth);
		j.setMsg("更新成功");
		systemService.addLog("景区月报编辑"+scenicSpotMonth.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return j;
		
		
	}
	
	//月报录入
	@RequestMapping(params = "userSave")
	@ResponseBody
	public AjaxJson saveUserScenicMonth(HttpServletRequest request,
			ScenicSpotMonth scenicSpotMonth) {
		 
		AjaxJson j = new AjaxJson();
		  String scenicdataid = request.getParameter("scenicdataid");
			TSUser user = ResourceUtil.getSessionUserName();
			scenicSpotMonth.setUserMonthId(user.getId());
		//  ScenicData d = this.systemService.get(ScenicData.class, scenicSpotMonth.getUserMonthId());
			List<ScenicData> list = this.systemService.findByProperty(ScenicData.class, "userId", user.getId());
			for (ScenicData sd : list) {
				scenicSpotMonth.setScenicData(sd); 				
			}	
			Date date=new Date();
			scenicSpotMonth.setReportdate(date);
		  scenicSpotMonth.setStatus(Status.PendingSubmission);   
		  //填报后积分增加
		  if(scenicSpotMonth.getId()==null||scenicSpotMonth.getId().length()==0){
			  if(ReportTimeCheck.monthTimeCheck(Integer.parseInt(scenicSpotMonth.getYear()), Integer.parseInt(scenicSpotMonth.getMonth()), 5)){
				  scenicSpotMonth.setScore(5);
			  }else{
				  scenicSpotMonth.setScore(2);
			  }
			  
			UserScoreUtils.scoreChange(scenicSpotMonth.getScore());
		  }
		systemService.saveOrUpdate(scenicSpotMonth);
		systemService.addLog("景区月报录入"+scenicSpotMonth.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

                
		return j;   
	}
	
	/**
	 * 用户登录数据显示
	 * @param scenicSpotMonth
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	
	@RequestMapping(params = "userdatagrid")
	public void monthGrid(ScenicSpotMonth scenicSpotMonth, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String userid = ResourceUtil.getSessionUserName().getId();
		/*scenicSpotMonth.setUserMonthId(userid);*/
		List<ScenicData> list =systemService.findHql("from ScenicData sd where sd.userId=?", userid);
		if(list.size()>0){
			ScenicData scenicData=list.get(0);
			 CriteriaQuery cq = new CriteriaQuery(ScenicSpotMonth.class, dataGrid);
			cq.eq("scenicData", scenicData);
			 com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotMonth);
		     this.systemService.getDataGridReturn(cq, true);
			 TagUtil.datagrid(response, dataGrid);
		}
		
		
	//	}
		
	}
/**
 * 管理员登录月报管理
 * @param scenicSpotMonth
 * @param scenicData
 * @param request
 * @param response
 * @param dataGrid
 */
	@RequestMapping(params = "datagrid")
	public void datagrid(ScenicSpotMonth scenicSpotMonth,
			ScenicData scenicData, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {

		CriteriaQuery cs = new CriteriaQuery(ScenicSpotMonth.class, dataGrid);
        cs.add(Restrictions.eq("status",Status.cityAudit));//过滤状态为区级审核或者市级审核的数据
        ///默认显示为上周的所有数据
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        if(year==null||month==null){
     		Calendar calendar = Calendar.getInstance(); 
		 	int year1=calendar.get(Calendar.YEAR);
	        //第几月
	        int month1 = calendar.get(Calendar.MONTH);   
	        if(month1==0){
	        	year1=year1-1;
	        	month1=12;
	        }
     	      
     	      year=String.valueOf(year1);
     	     month=String.valueOf(month1);
        }
     	cs.add(Restrictions.eq("year",year));
        cs.add(Restrictions.eq("month",month));
        
		//查询条件过滤
        String name = scenicSpotMonth.getScenicData() == null ? "" : scenicSpotMonth.getScenicData().getName();
        String beginLevel=request.getParameter("scenicData.level_begin");
        String endLevel=request.getParameter("scenicData.level_end");
        String area1=scenicSpotMonth.getScenicData() == null ? "" : scenicSpotMonth.getScenicData().getArea();
        String scenicType=scenicSpotMonth.getScenicData() == null ? "" : scenicSpotMonth.getScenicData().getScenictype();
        if(name!=null&&name.length()!=0){
			cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.name like '%"+name+"%' and t.id=month_id)"));
        }
        if(area1!=null&&area1.length()!=0){
			cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.area like '%"+area1+"%' and t.id=month_id)"));
        }
        if(scenicType!=null&&scenicType.length()!=0){
			cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.scenic_type like '%"+scenicType+"%' and t.id=month_id)"));
        }
        if(beginLevel!=null&&endLevel!=null&&beginLevel.length()!=0&&endLevel.length()!=0){
			cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_info t where t.level between'"+endLevel+"' and '"+beginLevel+"' and  t.id=month_id)"));
        }
        //区级管理员过滤
			String roleName = ResourceUtil.getSessionUserName().getUserKey();
			String area=ResourceUtil.getSessionUserName().getArea();
			if(roleName.equals("区级管理员")){
					scenicData.setArea(area);
			}
		  scenicSpotMonth.setScenicData(scenicData);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil
				.installHql(cs, scenicSpotMonth);
		systemService.getDataGridReturn(cs, true);
		
		TagUtil.datagrid(response, dataGrid);
	} 
/**
 * 管理员登陆月报审核列表
 * @param scenicSpotMonth
 * @param scenicData
 * @param request
 * @param response
 * @param dataGrid
 */
	// 月报审核列表
	@RequestMapping(params = "monthdatagrid")
	public void monthDatagrid(ScenicSpotMonth scenicSpotMonth,
			ScenicData scenicData, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cs = new CriteriaQuery(ScenicData.class, dataGrid);	
		String begindate=request.getParameter("spotMonth.reportdate_begin");
		String enddate=request.getParameter("spotMonth.reportdate_end");
		//获取上一周期的年份和月份
		Calendar calendar = Calendar.getInstance(); 
		Integer year=calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);   
        if(month==0){
        	year=year-1;
        	month=12;
        }
		//区级管理员过滤
				String roleName = ResourceUtil.getSessionUserName().getUserKey();
				String area=ResourceUtil.getSessionUserName().getArea();
				if(roleName.equals("区级管理员")){
						scenicData.setArea(area);
				}
		String status=scenicData.getSpotMonth()==null?"":scenicData.getSpotMonth().getStatus();
		if(status!=null&&status.length()!=0&&!status.equals(Status.notEdit)){
			cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_monthly t where t.status ="+status+" and t.month_id=this_.id and t.month="+month+" and t.year="+year+")"));
		}else if(status.equals(Status.notEdit)){
			cs.add(Restrictions.sqlRestriction(" 1=1 and not exists(select * from t_scenicspot_monthly t where t.status <>"+status+" and t.month_id=this_.id and t.month="+month+" and t.year="+year+")"));
		}
		if(begindate!=null&&enddate!=null&&begindate.length()!=0&&enddate.length()!=0){
			cs.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_scenicspot_monthly t where t.reportdate between'"+begindate+"' and '"+enddate+"' and t.month_id=this_.id)"));
		}
		ScenicController.getScenicLevel(request, cs);
		  String name=scenicData.getName();
	        if(name != null && name .length() != 0){
	        	cs.add(Restrictions.sqlRestriction("this_ .name like '%"+scenicData.getName()+"%'"));
			}
	        cs.add(Restrictions.sqlRestriction("1=1  "));
	        scenicData.setName(null);
		scenicData.setSpotMonth(null);
		
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil
				.installHql(cs, scenicData);
		systemService.getDataGridReturn(cs, true);
		
		for (Object o : dataGrid.getResults()) {
			ScenicData sd = (ScenicData) o;
			if (sd.getScenicSpotMonth() != null) {
				for (ScenicSpotMonth monthdata : sd.getScenicSpotMonth()) {
					if(monthdata.getMonth()==null){
						break;
					}
					String month1 =String.valueOf(month);
					if (month1.equals(monthdata.getMonth())&&year.toString().equals(monthdata.getYear())) {
						sd.setSpotMonth(monthdata);
						 break;  
					}
				}
               if(sd.getSpotMonth()==null){
            	   ScenicSpotMonth sc = new ScenicSpotMonth(); 
            	   sc.setYear(String.valueOf(year));
            	   sc.setMonth(String.valueOf(month));
            	   sc.setStatus(Status.notEdit);
            	   sd.setSpotMonth(sc);
               }

			}

		}
		List<ScenicData> rs = dataGrid.getResults();
		 
		for (int i = 0; i < rs.size(); i++) {
			Date repor1 = rs.get(i).getSpotMonth().getReportdate();
			for (int j = i + 1; j < rs.size(); j++) {
				Date repor2 = rs.get(j).getSpotMonth().getReportdate();
				ScenicData temp=null;
				if (repor1 != null && repor2 != null) {
					if (repor1.after(repor2)) {
						temp = rs.get(i);
						rs.set(i, rs.get(j));
						rs.set(j, temp);
					}
				}else{
					temp = rs.get(i);
					rs.set(i, rs.get(j));
					rs.set(j, temp);
				}

			}
		}
 
		dataGrid.setRows(10);
		TagUtil.datagrid(response, dataGrid);
	}  
/**
 * 月报审核
 * @param request
 * @param scenicSpotMonth
 * @param scenicData
 * @param statusid
 * @return
 */
	@RequestMapping(params = "addstatus")
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			ScenicSpotMonth scenicSpotMonth,ScenicData scenicData,String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			ScenicSpotMonth scenicMonth = systemService.getEntity(ScenicSpotMonth.class,id);
			String roleName = ResourceUtil.getSessionUserName().getUserKey();
	        if(roleName.equals("区级管理员")){
	        	scenicMonth.setStatus(Status.areaAudit);//4表示区级审核通过
	    		systemService.addLog("景区月报区级管理员审核通过"+scenicSpotMonth.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			}else{
				scenicMonth.setStatus(Status.cityAudit);//5表示市级审核通过
	    		systemService.addLog("景区月报市级管理员审核通过"+scenicSpotMonth.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			}
		
			systemService.saveOrUpdate(scenicMonth);
		}

		return j;
	}
	/**
	 *  审核退回
	 * @param request
	 * @param scenicData
	 * @param scenicSpotMonth
	 * @param statusid
	 * @return
	 */
	@RequestMapping(params = "nocheck")
	@ResponseBody
	public AjaxJson saveNotStatus(HttpServletRequest request,ScenicData scenicData,
			ScenicSpotMonth scenicSpotMonth,String statusid) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			ScenicSpotMonth scenicMonth = systemService.getEntity(ScenicSpotMonth.class,id);
			String userId=scenicMonth.getScenicData().getUserId();
			//3-已审核（审核不通过）
			scenicMonth.setStatus(Status.notPass);
			//诚信积分
			if(scenicMonth.getScore()>=2){
				scenicMonth.setScore(scenicMonth.getScore()-2);
				UserScoreUtils.scoreChange(-2,userId);
			}else{
				UserScoreUtils.scoreChange(-scenicMonth.getScore(),userId);
				scenicMonth.setScore(0);
				
			}
			systemService.saveOrUpdate(scenicMonth);
    		systemService.addLog("景区月报管理员审核退回"+scenicSpotMonth.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}

		return j;
	}
	
	
	/**
	 * 月报暂存
	 * @param request
	 * @param scenicData
	 * @param scenicSpotMonth
	 * @param statusid
	 * @return
	 */
	@RequestMapping(params = "zancuncheck")
	@ResponseBody 
	public AjaxJson saveZanStatus(HttpServletRequest request,ScenicData scenicData,
			ScenicSpotMonth scenicSpotMonth,String statusid) {
		AjaxJson j = new AjaxJson();
		String scenicdataid = request.getParameter("scenicdataid");
		request.setAttribute("scenicdataid", scenicdataid);
		String monthid;
					   
			ScenicData scenicdata = systemService.getEntity(ScenicData.class,scenicdataid);
			//5-暂存状态
			scenicSpotMonth.setScenicData(scenicdata);
			if(request.getParameter("monthid")!=null&&request.getParameter("monthid").toString().length()!=0){
				monthid=request.getParameter("monthid");
				scenicSpotMonth.setId(monthid);
			}
			scenicSpotMonth.setStatus(Status.notEdit);
			 request.setAttribute("monthdata", scenicSpotMonth);
			systemService.saveOrUpdate(scenicSpotMonth);
    		systemService.addLog("景区月报暂存"+scenicSpotMonth.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		

		return j;
	}
	/**
	 * 管理员待填报
	 * @param request
	 * @param scenicSpotMonth
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson saveScenic(HttpServletRequest request,
			ScenicSpotMonth scenicSpotMonth) {
		 
		AjaxJson j = new AjaxJson();
		  String scenicdataid = request.getParameter("scenicdataid");
		  ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
		  String monthid;
		  //未填报，2
			if(request.getParameter("monthid")!=null&&request.getParameter("monthid").toString().length()!=0){
				monthid=request.getParameter("monthid");
				scenicSpotMonth.setId(monthid);
			}
			Date date=new Date();
			scenicSpotMonth.setReportdate(date);
		  scenicSpotMonth.setScenicData(d); 
		  String roleName=ResourceUtil.getSessionUserName().getUserKey();
			if(roleName.equals("区级管理员")){
				scenicSpotMonth.setStatus(Status.areaAudit);
	    		systemService.addLog("景区月报区级管理员代填报"+scenicSpotMonth.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

			}else{
				scenicSpotMonth.setStatus(Status.cityAudit);
	    		systemService.addLog("景区月报市级管理员代填报"+scenicSpotMonth.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

			}
		systemService.saveOrUpdate(scenicSpotMonth);
                
		return j;   
	}
	//审核景区月报
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request,ScenicSpotMonth scenicSpotMonth,ScenicData scenicData) {
		String scenicdataid = request.getParameter("scenicdataid");
		String monthid = request.getParameter("monthid");

		request.setAttribute("scenicdataid", scenicdataid);
		request.setAttribute("monthid", monthid);
		 ScenicData d = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotMonth s = this.systemService.get(ScenicSpotMonth.class, monthid);
		 request.setAttribute("scenicdata", d);
		 if(s==null){
			 s=new ScenicSpotMonth();
			 Calendar calendar = Calendar.getInstance(); 
			 	int year=calendar.get(Calendar.YEAR);
		        //第几月
		        int month = calendar.get(Calendar.MONTH);   
		        if(month==0){
		        	s.setYear(String.valueOf(year-1));
		        	s.setMonth(String.valueOf(12));
		        }else{
		        s.setYear(String.valueOf(year));
		      s.setMonth(String.valueOf(month));
		        }
		 }
		
		 request.setAttribute("monthdata", s);
		return new ModelAndView("scenicmonth/aduit2month");    
	}
	
	 //审核页面
	@RequestMapping(params = "audit")
	public ModelAndView audit(HttpServletRequest request,ScenicSpotMonth scenicSpotMonth,ScenicData scenicData,DataGrid dataGrid) {
		String scenicdataid = request.getParameter("scenicdataid");
		String monthid = request.getParameter("monthid");
		request.setAttribute("scenicdataid", scenicdataid);  
		 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotMonth m = this.systemService.get(ScenicSpotMonth.class, monthid);

		 request.setAttribute("scenicdata", s);     
		 request.setAttribute("monthdata", m);
		  scenicSpotMonth.setScenicData(s);    
		  request.setAttribute("scenicSpotMonth",scenicSpotMonth);
		
		return new ModelAndView("scenicmonth/aduitmonth");
	}
	/**
	 * 详情
	 * @param request
	 * @param scenicSpotMonth
	 * @param scenicData
	 * @param dataGrid
	 * @return
	 */
	
	@RequestMapping(params = "detial")
	public ModelAndView detial(HttpServletRequest request,ScenicSpotMonth scenicSpotMonth,ScenicData scenicData,DataGrid dataGrid) {
		String scenicdataid = request.getParameter("scenicdataid");
		String monthid = request.getParameter("monthid");
		request.setAttribute("scenicdataid", scenicdataid);  
		 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotMonth m = this.systemService.get(ScenicSpotMonth.class, monthid);

		 request.setAttribute("scenicdata", s);  
		 request.setAttribute("monthdata", m);
		  scenicSpotMonth.setScenicData(s);       
		  request.setAttribute("scenicSpotMonth",scenicSpotMonth);
		
		return new ModelAndView("scenicmonth/aduitmonth");
	}   
	
	//景区月报详情
	@RequestMapping(params = "detialinfo")
	public ModelAndView detialinfo(HttpServletRequest request,ScenicSpotMonth scenicSpotMonth,ScenicData scenicData,DataGrid dataGrid) {
		String scenicdataid = request.getParameter("scenicdataid");
		
		String monthid = request.getParameter("monthid");
		request.setAttribute("scenicdataid", scenicdataid);  
		 ScenicData s = this.systemService.get(ScenicData.class, scenicdataid);
		 ScenicSpotMonth m = this.systemService.get(ScenicSpotMonth.class, monthid);

		 request.setAttribute("scenicdata", s);  
		 request.setAttribute("monthdata", m);
		  scenicSpotMonth.setScenicData(s);       
		  request.setAttribute("scenicSpotMonth",scenicSpotMonth);
		
		return new ModelAndView("scenicmonth/aduitmonth");
	}   
	/**
	 *  月报删除
	 * @param scenicSpotMonth
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ScenicSpotMonth scenicSpotMonth, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			scenicSpotMonth = systemService.getEntity(ScenicSpotMonth.class, id);
			systemService.delete(scenicSpotMonth);
			message="月报删除成功";
    		systemService.addLog("景区月报删除成功"+scenicSpotMonth.getId(), Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message="月报删除失败";
    		systemService.addLog("景区月报删除失败"+scenicSpotMonth.getId(), Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);

		}
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 *月报导出
	 */
	@RequestMapping(params = "excelExport")
	public void exportMonthly(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取上一月
        Calendar calendar=Calendar.getInstance();
		int year1=calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);   
	      //第几周  
	      if(month1==0){
	    	  year1=year1-1;
	    	  month1=12;
	      }
        // 获取参数 
        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
        String beginLevel = new String(request.getParameter("beginLevel").getBytes("iso8859-1"),"utf-8");
        String endLevel = new String(request.getParameter("endLevel").getBytes("iso8859-1"),"utf-8");
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
        String month= new String(request.getParameter("month").getBytes("iso8859-1"),"utf-8");
        String area= new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
        String scenicType= new String(request.getParameter("scenicType").getBytes("iso8859-1"),"utf-8");
        if(year.length()==0||year==null){
        	year=String.valueOf(year);
        }
        if(month.length()==0||month==null){
        	month=String.valueOf(month);
        }
        String excelName = "景区月报信息.xls";
        String modelPath = "/com/zzc/web/export/model/scenic/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("select s.name,w.year,w.month,w.taking,w.ticket,w.businesstax,w.totalprofit,w.endemployee,w.laidworker,w.countrylabor,w.price,w.asset,w.receptionnum,w.overnum,w.freeticket,w.compare,w.principal,w.preparer,w.telephone,w.reportdate,if(w.`status`='3','未填报',if(w.`status`='4','提交待审',if(w.`status`='5','退回修订',if(w.`status`='6','区级审核','市级审核')))) 'status' from t_scenicspot_monthly w left join t_scenicspot_info s on s.id=w.month_id where 1=1 and w.status in (7)");
        // 组装查询条件
        if(name !=null && name.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.name like '%"+name+"%'  and  s.id=w.month_id)");
        }
        if(area !=null && area.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.area ="+area+"  and  s.id=w.month_id)");
        }
        if(scenicType !=null && scenicType.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.scenic_type ="+scenicType+"  and  s.id=w.month_id)");
        }
        if(beginLevel !=null && beginLevel.length() != 0&&endLevel !=null && endLevel.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.level between '"+endLevel+"' and '"+beginLevel+"' and  s.id=w.month_id)");
        }
        if(year !=null && year.length() != 0){
        	sql.append(" and w.year ='"+year+"'");
        }
        if(month !=null && month.length() != 0){
        	sql.append(" and w.month ='"+month+"'");
        }    
        sql.append(" UNION all select '合计','','',sum(w.taking),sum(w.ticket),sum(w.businesstax),sum(w.totalprofit),sum(w.endemployee),sum(w.laidworker),sum(w.countrylabor),sum(w.price),sum(w.asset),sum(w.receptionnum),sum(w.overnum),sum(w.freeticket),sum(w.compare),'','','','','' from t_scenicspot_monthly w left join t_scenicspot_info s on s.id=w.month_id where 1=1 and w.status in (7)");
        // 组装查询条件
        if(name !=null && name.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.name like '%"+name+"%'  and  s.id=w.month_id)");
        }
        if(area !=null && area.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.area ="+area+"  and  s.id=w.month_id)");
        }
        if(scenicType !=null && scenicType.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.scenic_type ="+scenicType+"  and  s.id=w.month_id)");
        }
        if(beginLevel !=null && beginLevel.length() != 0&&endLevel !=null && endLevel.length() != 0){
        	sql.append(" and exists(select * from t_scenicspot_info s where s.level between '"+endLevel+"' and '"+beginLevel+"' and  s.id=w.month_id)");
        }
        if(year !=null && year.length() != 0){
        	sql.append(" and w.year ='"+year+"'");
        }
        if(month !=null && month.length() != 0){
        	sql.append(" and w.month ='"+month+"'");
        }  
        ExportService emds = new ExportService(21); 
        
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
	public static void main(String[] args) {
		  String yrate=""+2017+"0"+(1+1)+"05";
		  System.out.println(yrate);
	}
}