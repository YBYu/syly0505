package com.zzc.web.otherTravel.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import org.springframework.ui.ModelMap;
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
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.otherTravel.entity.AirportMonthly;
import com.zzc.web.otherTravel.entity.FlightMonthly;
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.system.service.SystemService;

@Scope("prototype")
@Controller
@RequestMapping("/airportMonthlyController")
public class AirportMonthlyController extends BaseController{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AirportMonthlyController.class);
	
	private String message = null;
	private SystemService systemService;
	TimeUtils timeUtils = new TimeUtils();
	
	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	@RequestMapping(params = "addam")
	public ModelAndView addGm(HttpServletRequest req) {
		String otid = ResourceUtil.getSessionUserName().getId();
		OtherTravelInfo  ot=this.systemService.getEntity(OtherTravelInfo .class, otid);
		req.setAttribute("otherTravelInfo", ot);
		//返回上个月的时间
		String lastMonth = timeUtils.getLastmonth();
		
		req.setAttribute("lastMonth", lastMonth);
		return new ModelAndView("otherTravel/saveam");
	}
	
	/**
	 * 转跳到机场月报页面
	 */
	@RequestMapping(params = "airportMonth")
	public ModelAndView airportMonth() {
		return new ModelAndView("otherTravel/airportMonthlyList");
	}
	//跳转到月报审核页面
	@RequestMapping(params = "airportMonthCheck")
	public ModelAndView airmonthcheck() {
		return new ModelAndView("otherTravel/airportMonthlyCheckList");
	}
	@ResponseBody
	@RequestMapping(params = "adatagrid")
	public void dataGrid(AirportMonthly airportMonthly, HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(AirportMonthly.class, dataGrid);
		cq.add(Restrictions.eq("status", Status.passAudit));
		 String name=  airportMonthly.getOtherTravelInfo()==null?"":airportMonthly.getOtherTravelInfo().getName();
	      if(name != null && name.length() != 0){
	     
	    	  cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_othercompany_info t where t.name like '%"+name+"%' and t.id=otravel_id)"));
	      }
	      if(airportMonthly.getSmonth()!= null && airportMonthly.getSmonth().length() != 0){
	        	cq.add(Restrictions.like("smonth", "%"+airportMonthly.getSmonth()+"%"));
	        }
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new AirportMonthly());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	   //月报审核列表
		//机场基本信息+月报信息,这个月审核上个月的信息
		@RequestMapping(params = "amonthdatagrid")
		public void monthDatagrid(AirportMonthly airportMonthly,
				OtherTravelInfo otherTravelInfo, HttpServletRequest request,
				HttpServletResponse response, DataGrid dataGrid) {
			//获取当月时间yyyy-mm
			//String currentMonth = timeUtils.getCurrentMonth(new Date());
			//获取上月时间
			String lastMonth = timeUtils.getLastMonth();
			//根据datagrid查询机场信息
			CriteriaQuery cq = new CriteriaQuery(OtherTravelInfo.class, dataGrid);
			otherTravelInfo.setType(Status.airport);
			String status=otherTravelInfo.getAirportMonth()==null?"":otherTravelInfo.getAirportMonth().getStatus();
			if(status!=null&&status.length()!=0&&!status.equals(Status.noSumbit)){
				cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_airport_monthly t where t.status ="+status+" and t.otravel_id=this_.id)"));
			}else if(status.equals(Status.noSumbit)){
				cq.add(Restrictions.sqlRestriction(" 1=1 and not exists(select * from t_airport_monthly t where t.status <>"+status+" and t.otravel_id=this_.id)"));
			}
			otherTravelInfo.setAirportMonth(null);
			if(otherTravelInfo.getName()!= null && otherTravelInfo.getName().length() != 0){
	        	cq.add(Restrictions.like("name", "%"+otherTravelInfo.getName()+"%"));
	        }
			cq.add(Restrictions.eqOrIsNull("type", Status.airport));
			
			com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new	OtherTravelInfo());
			//获取easyUI的datagrid模型
			this.systemService.getDataGridReturn(cq, true);
			//对原型进行遍历list集合		
			for (Object o : dataGrid.getResults()) {
				//对集合元素进行操作
				OtherTravelInfo ot=(OtherTravelInfo) o;
				//对机场的月报判断是否为空
				if(ot.getAirportMonthList() != null){
					List<AirportMonthly> list = ot.getAirportMonthList();
					for (AirportMonthly mothdata :list ) {
						//对月报进行遍历
						if(mothdata.getDates()==null){
							break;
						}
						//去当前月报的时间 转换为yyyy-MM格式
						String monthTime = timeUtils.getCurrentMonth(mothdata.getDates());
						//如果是上月时间 则返回审核列表
					//	mothdata.getMonth();
						if(monthTime.equals(lastMonth)){
							//将当前月报 加到机场信息中 在审核表中提现
							//ot.setg;
							ot.setAirportMonth(mothdata);
						}
						//满足月份 跳出循环
						break;
					}
					//月报不存在 的情况  
					if(ot.getAirportMonth()==null){
						//创建一个 设置为代填报状态
						AirportMonthly hl= new AirportMonthly();
						hl.setDates(timeUtils.getLastMonthD());
						hl.setStatus(Status.noSumbit);
						ot.setAirportMonth(hl);
						//this.systemService.save(hl);
					}
				}
			}
			//将datagrid返回给前端页面
			TagUtil.datagrid(response, dataGrid);
		}
		@RequestMapping(params="saveam")
		@ResponseBody
		public AjaxJson add(AirportMonthly airportMonthly,HttpServletRequest req){
			
			AjaxJson j = new AjaxJson();
			//通过工具类获取用户对象
		
			
				if(airportMonthly.getId() == null || airportMonthly.getId().length() == 0) {
					String smonth = timeUtils.getCurrentMonth(airportMonthly.getDates());
					airportMonthly.setId(null);
					if(this.isFilled(smonth)){
						j.setMsg("上月已填报月报!");
						return j;
					}
					airportMonthly.setSmonth(smonth);
					
					airportMonthly.setStatus(Status.noAudit);
					OtherTravelInfo  otherTravelInfo  = systemService.getEntity(OtherTravelInfo .class, airportMonthly.getOtid());
					airportMonthly.setOtherTravelInfo(otherTravelInfo);
					airportMonthly.setReportdate(new Date());
					systemService.save(airportMonthly);
					
					j.setMsg("上报成功");
				
				
			} else{
				airportMonthly.setStatus(Status.noAudit);
				OtherTravelInfo  otherTravelInfo  = systemService.getEntity(OtherTravelInfo .class, airportMonthly.getOtid());
				airportMonthly.setOtherTravelInfo(otherTravelInfo);
				airportMonthly.setReportdate(new Date());
				systemService.saveOrUpdate(airportMonthly);
				
			}
			
			systemService.addLog("填报机场月报成功:"+airportMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			
			return j;
			
		}
		
			//删除月报
		@RequestMapping(params = "delam")
		@ResponseBody
		public AjaxJson del(AirportMonthly airportMonthly, HttpServletRequest req) {
			String monthId = req.getParameter("id");
			AjaxJson j = new AjaxJson();
			try {
				airportMonthly = systemService.getEntity(AirportMonthly.class, monthId);
				systemService.delete(airportMonthly);
				message="月报删除成功";
			} catch (Exception e) {
				message="月报删除失败";
			}
			
			j.setMsg(message);
			return j;
		}
		//季报详情展示
		
		@RequestMapping(params = "detailam")
		public ModelAndView detial(HttpServletRequest request,AirportMonthly airportMonthly,OtherTravelInfo otherTravelInfo,DataGrid dataGrid) {
			String otid = request.getParameter("otid");
			//季报id
			String amonthId = request.getParameter("amonthId");
			//存到请求域中
			//request.setAttribute("otid", otid);  
			OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
			airportMonthly = this.systemService.get(AirportMonthly.class, amonthId);

			 //request.setAttribute("hotelmanage", h);  
			// request.setAttribute("monthdata", m);
			airportMonthly.setOtherTravelInfo(h);   
			  //request.setAttribute("hotelMonthly",hotelMonthly);
			
			return new ModelAndView("otherTravel/showamonthDetail").addObject("airportMonthly",airportMonthly);
		}	
		
	//月报编辑
		
		@RequestMapping(params = "editam")
		public ModelAndView editam(HttpServletRequest request,AirportMonthly airportMonthly,OtherTravelInfo otherTravelInfo,DataGrid dataGrid) {
			String otid = request.getParameter("otid");
			//月报id
			String amonthId = request.getParameter("amonthId");
			//存到请求域中
			//request.setAttribute("otid", otid);  
			OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
			airportMonthly = this.systemService.get(AirportMonthly.class, amonthId);
			airportMonthly.setOtherTravelInfo(h);   
		//	request.setAttribute("airportMonthly", airportMonthly);
			return new ModelAndView("otherTravelClient/edit/editAirportMonthly").addObject("airportMonthly",airportMonthly);
		}	
		//季报审核管理员操作
		@RequestMapping(params = "addstatusam")
		@ResponseBody
		public AjaxJson saveStatus(HttpServletRequest request,
				AirportMonthly airportMonthly,OtherTravelInfo otherTravelInfo,String statusid) {
			AjaxJson j = new AjaxJson();
		
			//季报的id
			String amonthId = request.getParameter("amonthId");
			request.setAttribute("amonthId", amonthId);
			try {
				if (StringUtil.isNotEmpty(amonthId)) {
						 airportMonthly = systemService.getEntity(AirportMonthly.class,amonthId);

						
						//1表示管理员审核通过
						airportMonthly.setStatus(Status.passAudit);
						//airportMonthly.set;
						systemService.saveOrUpdate(airportMonthly);
						//systemService.updateEntitie(scenicMonth);
						j.setMsg("管理员审核成功");
						systemService.addLog("审核通过机场月报成功:"+airportMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				systemService.addLog("审核通过机场月报成功:"+amonthId, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}


			return j;
		}
		@RequestMapping(params = "nocheckam")
		@ResponseBody
		public AjaxJson saveNotStatus(HttpServletRequest request,OtherTravelInfo otherTravelInfo,
				AirportMonthly airportMonthly,String statusid) {
			AjaxJson j = new AjaxJson();
			String amonthId = request.getParameter("amonthId");
			request.setAttribute("amonthId", amonthId);
			if (StringUtil.isNotEmpty(amonthId)) {
				 airportMonthly = systemService.getEntity(AirportMonthly.class,amonthId);
				//request.setAttribute("scenicdata", scenicdata);
				airportMonthly.setStatus(Status.notAudit);//管理员审核未通过
				//airportMonthly.setAuditTime(new Date());
				systemService.saveOrUpdate(airportMonthly);
				
				systemService.addLog("审核退回机场月报成功:"+airportMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			}

			return j;
		}
		 //审核页面
		@RequestMapping(params = "auditam")
		public ModelAndView audit(HttpServletRequest request,AirportMonthly airportMonthly,OtherTravelInfo otherTravelInfo,DataGrid dataGrid) {
			String otid = request.getParameter("otid");
			String gMonthId = request.getParameter("amonthId");
			request.setAttribute("otid", otid);  
			OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
			AirportMonthly hq = this.systemService.get(AirportMonthly.class, gMonthId);
			
			 //request.setAttribute("scenicdata", s); 
			hq.setOtherTravelInfo(h);
			
			return new ModelAndView("otherTravel/auditamonth").addObject("airportMonthly", hq);
		}
		@RequestMapping(params = "addsignam")
		public ModelAndView addsign(HttpServletRequest request) {
			String otid = request.getParameter("otid");
			//现在为空
			String amonthId = request.getParameter("amonthId");
			//根据基本信息id获取基本信息
			OtherTravelInfo  hm = this.systemService.get(OtherTravelInfo.class, otid);
			//根绝月报id获取月报信息
			AirportMonthly  s = this.systemService.get(AirportMonthly .class, amonthId);
			//将基本信息设置在域中
			
			 if(s==null){
				 s=new AirportMonthly ();
				 
			 }
				
				s.setOtid(otid);;
				
			    s.setOtherTravelInfo(hm);
			    s.setDates(timeUtils.getLastMonthD());
			    s.setSmonth(timeUtils.getCurrentMonth(s.getDates()));
			  //  System.out.println(s.getSmonth());
			 request.setAttribute("airportMonthly", s);
			//this.saveScenic(request, scenicSpotMonth);
			return new ModelAndView("otherTravel/daitianbaoam");    
		}
		//保存
		@RequestMapping(params="saveDaiam")
		@ResponseBody
		public AjaxJson addDai(AirportMonthly airportMonthly,HttpServletRequest req){
			
			AjaxJson j = new AjaxJson();
			//补全月报信息由
			
			
			//与机场的id进行关联			
			OtherTravelInfo otherTravelInfo = systemService.getEntity(OtherTravelInfo.class, airportMonthly.getOtid());
			airportMonthly.setOtherTravelInfo(otherTravelInfo);;
			airportMonthly.setStatus(Status.passAudit);
			airportMonthly.setDates(timeUtils.getLastMonthD());
			airportMonthly.setSmonth(timeUtils.getCurrentMonth(airportMonthly.getDates()));
			airportMonthly.setReportdate(new Date());
			String aid = airportMonthly.getId();
			if(aid.length()==0){
				systemService.save(airportMonthly);
			}else{
				
				systemService.saveOrUpdate(airportMonthly);
			}
		
			
			j.setMsg("数据上报成功");
			
			systemService.addLog("代填报机场月报成功:"+airportMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			
			return j;
		}
		//暂存
		@RequestMapping(params = "zancuncheckam")
		@ResponseBody 
		public AjaxJson saveZanStatus(HttpServletRequest request,
				AirportMonthly airportMonthly,String statusid) {
			AjaxJson j = new AjaxJson();
			String otid=request.getParameter("otid");
			request.setAttribute("otid", otid);
			//根据id获取机场信息实体类
			OtherTravelInfo otherTravelInfo =this.systemService.getEntity(OtherTravelInfo .class, otid);
			//将基本信息添加到月报信息中
			airportMonthly.setOtid(otid);
			airportMonthly.setOtherTravelInfo(otherTravelInfo);;
			airportMonthly.setDates(timeUtils.getLastMonthD());
			airportMonthly.setSmonth(timeUtils.getCurrentMonth(airportMonthly.getDates()));
			//状态仍为未填报
			airportMonthly.setStatus(Status.noSumbit);
			String aid = airportMonthly.getId();
			if(aid.length()==0){
				systemService.save(airportMonthly);
				systemService.addLog("暂存机场月报成功:"+airportMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}else{
				
				systemService.saveOrUpdate(airportMonthly);
				systemService.addLog("暂存机场月报成功:"+airportMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
			return j;
		}
		
		//导出(带合计)
		@RequestMapping(params = "excelExport11")
		public void excelExport11(HttpServletRequest request, HttpServletResponse response) throws Exception{

			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        
	        // 获取参数 
	        String smonth= new String(request.getParameter("smonth").getBytes("iso8859-1"),"utf-8");
	        String status= new String(request.getParameter("status").getBytes("iso8859-1"),"utf-8");
	        
	        String excelName = "机场月报统计(带合计).xls";
	        String modelPath = "/com/zzc/web/export/model/otherTravel/"+excelName;
	        StringBuffer sql = new StringBuffer();
	        sql.append(" SELECT ");
	        sql.append(" 	t.smonth, ");
	        sql.append(" 	ot.`name`, ");
	        sql.append(" IF ( ");
	        sql.append(" 	t.`status` = '1', ");
	        sql.append(" 	'未填报', ");
	        sql.append("  ");
	        sql.append(" IF ( ");
	        sql.append(" 	t.`status` = '2', ");
	        sql.append(" 	'未审核', ");
	        sql.append("  ");
	        sql.append(" IF ( ");
	        sql.append(" t.`status` = '3', ");
	        sql.append(" 	'管理员已审核(审核不通过)', ");
	        sql.append("  ");
	        sql.append(" IF ( ");
	        sql.append(" 	t.`status` = '4', ");
	        sql.append(" 	'管理员已审核(审核通过)', ");
	        sql.append(" 	'市级审核' ");
	        sql.append(" ) ");
	        sql.append(" ) ");
	        sql.append(" ) ");
	        sql.append(" ) 'status', ");
	        sql.append(" 	t.plan_sortie plansortie, ");
	        sql.append(" 	t.actual_sortie actualsortie, ");
	        sql.append(" 	t.plan_sortie_year plansortieyear, ");
	        sql.append(" 	t.plan_out_traveller planouttraveller, ");
	        sql.append(" 	t.actual_out_traveller actualouttraveller, ");
	        sql.append(" 	t.plan_out_traveller_year planouttravelleryear, ");
	        sql.append(" 	t.plan_out_will planoutwill, ");
	        sql.append(" 	t.actual_out_will actualoutwill, ");
	        sql.append(" 	t.plan_out_will_year planoutwillyear, ");
	        sql.append(" 	t.plan_in_traveller planIntegerraveller, ");
	        sql.append(" 	t.actual_in_traveller actualIntegerraveller, ");
	        sql.append(" 	t.plan_in_traveller_year planIntegerravelleryear, ");
	        sql.append(" 	t.plan_in_will planinwill, ");
	        sql.append(" 	t.actual_in_will actualinwill, ");
	        sql.append(" 	t.plan_in_will_year planinwillyear, ");
	        sql.append(" 	t.plan_throughput_traveller planthroughputtraveller, ");
	        sql.append(" 	t.actual_throughput_traveller actualthroughputtraveller, ");
	        sql.append(" 	t.plan_throughput_traveller_year planthroughputtravelleryear, ");
	        sql.append(" 	t.plan_throughput_will planthroughputwill, ");
	        sql.append(" 	t.actual_throughput_will actualthroughputwill, ");
	        sql.append(" 	t.plan_throughput_will_year planthroughputwillyear ");
	        sql.append(" FROM ");
	        sql.append(" 	t_airport_monthly t LEFT JOIN t_othercompany_info ot ON ot.ID=t.otravel_id  ");
	        sql.append(" WHERE 1=1 and t.status='4'  ");
	        if(smonth !=null && smonth.length() != 0){
	        	 sql.append("  and t.smonth LIKE '"+smonth+"' ");
	        }
	        
	        if(status !=null && status.length() != 0){
	        	sql.append(" and t.status ='"+status+"'");
	        }
	        
	        sql.append(" UNION SELECT '合计' ,'','', ");
	        sql.append(" IFNULL(SUM(t.plan_sortie), 0) plansortie,IFNULL(SUM(t.actual_sortie), 0) actualsortie,IFNULL(SUM(t.plan_sortie_year), 0) plansortieyear,  ");
	        sql.append(" 		    		IFNULL(SUM(t.plan_out_traveller), 0) planouttraveller,IFNULL(SUM(t.actual_out_traveller), 0) actualouttraveller,IFNULL(SUM(t.plan_out_traveller_year), 0) planouttravelleryear,  ");
	        sql.append(" 		    		IFNULL(SUM(t.plan_out_will), 0) planoutwill,IFNULL(SUM(t.actual_out_will), 0) actualoutwill,IFNULL(SUM(t.plan_out_will_year), 0) planoutwillyear,  ");
	        sql.append(" 		    		IFNULL(SUM(t.plan_in_traveller), 0) planIntegerraveller,IFNULL(SUM(t.actual_in_traveller), 0) actualIntegerraveller,IFNULL(SUM(t.plan_in_traveller_year), 0) planIntegerravelleryear,  ");
	        sql.append(" 		    		IFNULL(SUM(t.plan_in_will), 0) planinwill,IFNULL(SUM(t.actual_in_will), 0) actualinwill,IFNULL(SUM(t.plan_in_will_year), 0) planinwillyear,  ");
	        sql.append(" 		    		IFNULL(SUM(t.plan_throughput_traveller), 0) planthroughputtraveller,IFNULL(SUM(t.actual_throughput_traveller), 0) actualthroughputtraveller,IFNULL(SUM(t.plan_throughput_traveller_year), 0) planthroughputtravelleryear, ");
	        sql.append(" 		    		IFNULL(SUM(t.plan_throughput_will), 0) planthroughputwill,IFNULL(SUM(t.actual_throughput_will), 0) actualthroughputwill,IFNULL(SUM(t.plan_throughput_will_year), 0) planthroughputwillyear   ");
	        sql.append(" 		    		FROM t_airport_monthly t WHERE  1=1  and t.status='4'  ");
	        if(smonth !=null && smonth.length() != 0){
	        	 sql.append("  and t.smonth LIKE '"+smonth+"' ");
	        }
	        if(status !=null && status.length() != 0){
	        	sql.append(" and t.status ='"+status+"'");
	        }

	        ExportService emds = new ExportService(3,24); 
	        
	        HSSFWorkbook workbook = null;
	        try
	        {
	            workbook = emds.getNewModelInfos(modelPath, sql.toString());
	        } catch (Exception e)
	        {

	        }

	        response.reset();
	        response.setContentType("text/html;charset=UTF-8");

	        if (workbook != null)
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName, "UTF-8"));
	            POIUtils.writeWorkbook(workbook, response.getOutputStream());
	        } else
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("Errors.xls", "UTF-8"));
	            response.getOutputStream().print("You have already downloaded the error excel!");
	        }
		
	        }

		
		//**************************************上机场管理员后台,下是机场客户后台***********************************
		@RequestMapping(params = "ckairport")
		public String user(HttpServletRequest request) {
			// 给部门查询条件中的下拉框准备数据
			//List<TSDepart> departList = systemService.getList(TSDepart.class);
			//request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
			return "otherTravelClient/ckairportMonthlyList";
		}
		//机场数据
		@ResponseBody
		@RequestMapping(params = "acdatagrid")
		public void datagridClient(AirportMonthly airportMonthly,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
			//1.从session中获取当前用户的 id
			String otid = ResourceUtil.getSessionUserName().getId();
			//System.out.println(otid);
			airportMonthly.setOtid(otid);
	        CriteriaQuery cq = new CriteriaQuery(AirportMonthly.class, dataGrid);
	        //查询条件组装器
	        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, airportMonthly);      
	        cq.add();
	        this.systemService.getDataGridReturn(cq, true);
	        //List<OtherTravelInfo> cfeList = new ArrayList<OtherTravelInfo>();
	        TagUtil.datagrid(response, dataGrid);
	    }
		//机场月报导出
		@RequestMapping(params = "exportsxlss")
		public String exportXlss(AirportMonthly airportMonthly,HttpServletRequest request,HttpServletResponse response
				, DataGrid dataGrid,ModelMap map) {
		
		    CriteriaQuery cq = new CriteriaQuery(FlightMonthly.class, dataGrid);
		    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, airportMonthly, request.getParameterMap());
		    //List<CourseEntity> courses = this.courseService.getListByCriteriaQuery(cq,false);
		    String otId = ResourceUtil.getSessionUserName().getId();
		    List<AirportMonthly> airportMonthlys = this.systemService.findHql("from com.zzc.web.otherTravel.entity.AirportMonthly th where th.otid=? ",otId );
		    map.put(NormalExcelConstants.FILE_NAME,"机场月报信息");
		    map.put(NormalExcelConstants.CLASS,AirportMonthly.class);
		    map.put(NormalExcelConstants.PARAMS,new ExportParams("机场项目月报列表", "导出人:机场企业用户",
		            "导出信息"));
		    map.put(NormalExcelConstants.DATA_LIST,airportMonthlys);
		    return NormalExcelConstants.JEECG_EXCEL_VIEW;
		
		}
		
		
		/**
		 * 
		 * 检查上月报是否填报
		 * @return
		 */
		public boolean isFilled(String smonth){
			boolean tip = false;
			//获取当前登录用户ID=涉旅ID
			String userId = ResourceUtil.getSessionUserName().getId();
			
			
			String sql = "select id from t_airport_monthly t where t.smonth like '"+smonth+"' and t.otravel_id = '"+userId+"'";
			List<Map<String, Object>> list = systemService.findForJdbc(sql);
			if(list.size() != 0) tip = true;
			return tip;
		}

}
