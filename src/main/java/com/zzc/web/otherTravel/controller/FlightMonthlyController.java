package com.zzc.web.otherTravel.controller;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.zzc.web.otherTravel.entity.FlightMonthly;
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

@Scope("prototype")
@Controller
@RequestMapping("/flightMonthlyController")
public class FlightMonthlyController extends BaseController{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(FlightMonthlyController.class);
	
	private String message = null;
	private SystemService systemService;
	TimeUtils timeUtils = new TimeUtils();
	
	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	/**
	 * 
	 * @date: 2016年12月29日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:跳至用户保存页面
	 */
	@RequestMapping(params = "addfm")
	public ModelAndView addGm(HttpServletRequest req) {
		String otid = ResourceUtil.getSessionUserName().getId();
		OtherTravelInfo  ot=this.systemService.getEntity(OtherTravelInfo .class, otid);
		req.setAttribute("otherTravelInfo", ot);
		String lastMonth = timeUtils.getLastmonth();
		
		req.setAttribute("lastMonth", lastMonth);
		return new ModelAndView("otherTravel/savefm");
	}
	/**
	 * 转跳到飞行月报页面
	 */
	@RequestMapping(params = "flightMonth")
	public ModelAndView flightMonth() {
		return new ModelAndView("otherTravel/flightMonthlyList");
	}
	//跳转到月报审核页面
	@RequestMapping(params = "flightMonthCheck")
	public ModelAndView flightMonthCheck() {
		return new ModelAndView("otherTravel/flightMonthlyCheckList");
	}
	/**
	 * 
	 * @date: 2016年12月26日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:跳转到到填报(代填报多个企业)
	 */
	@RequestMapping(params = "daitianbao")
	public String monthDaitianbao(FlightMonthly flightMonthly,
			OtherTravelInfo otherTravelInfo, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		//定义集合存储未填报的高尔夫月报
		List lists = new ArrayList<>();
		
		//获取当月时间yyyy-mm
		String currentMonth = timeUtils.getCurrentMonth(new Date());
		//获取上月时间
		String lastMonth = timeUtils.getLastMonth();
		//根据datagrid查询涉旅机构信息
		CriteriaQuery cq = new CriteriaQuery(OtherTravelInfo.class, dataGrid);
		otherTravelInfo.setType(Status.flight);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				otherTravelInfo);
		//获取easyUI的datagrid模型
		this.systemService.getDataGridReturn(cq, true);
		//对原型进行遍历list集合		
		
		for (Object o : dataGrid.getResults()) {
			//对集合元素进行操作
			OtherTravelInfo ot=(OtherTravelInfo) o;
			//对涉旅机构的月报判断是否为空
			if(ot.getFlightMonthList() != null){
				List<FlightMonthly> list = ot.getFlightMonthList();
				for (FlightMonthly mothdata :list ) {
					//对月报进行遍历
					if(mothdata.getDates()==null){
						break;
					}
					//去当前月报的时间 转换为yyyy-MM格式
					String monthTime = timeUtils.getCurrentMonth(mothdata.getDates());
					if(monthTime.equals(lastMonth)){
						//将当前月报 加到涉旅机构信息中 在审核表中提现
						//ot.setg;
						ot.setFlightMonth(mothdata);
					}
					//满足月份 跳出循环
					break;
				}

				
				//月报不存在 的情况  
				if(ot.getFlightMonth()==null){
					
					//创建一个 设置为代填报状态
					FlightMonthly hl= new FlightMonthly();
					hl.setDates(timeUtils.getLastMonthD());
					hl.setStatus(Status.noSumbit);
					ot.setFlightMonth(hl);
					
					lists.add(ot);
					
				}
			}
		}
		
		
		request.setAttribute("listss", lists);
		return "otherTravel/saveflightDaitianbao";
	}
	/**
	 * 
	 * @date: 2016年12月27日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 代填报多个企业
	 * @return:
	 */
	@ResponseBody
	@RequestMapping(params = "savfmdais")
	public AjaxJson savgmdais(HttpServletRequest req){
		int a=1;
	
		AjaxJson j = new AjaxJson();
		while(true){
			String aim="otid"+a;//otid
			String  rnum ="receptionnum"+a;//接待人次
			String bum ="businessincome"+a;//营业收入
			String tnum="flighttimes"+a;//飞行架次
			String reporter="reporter"+a;//填报人
			
			if(null!=req.getParameter(aim)&&req.getParameter(aim).length()>0){
				FlightMonthly flightMonth = new FlightMonthly();
				flightMonth.setOtid(req.getParameter(aim));
				OtherTravelInfo ot = systemService.getEntity(OtherTravelInfo.class, flightMonth.getOtid());
				flightMonth.setOtherTravelInfo(ot);
				flightMonth.setReceptionnum(Integer.valueOf(req.getParameter(rnum)));
				flightMonth.setBusinessincome(Double.valueOf(req.getParameter(bum)));
				flightMonth.setFlighttimes(Integer.valueOf(req.getParameter(tnum)));
				flightMonth.setReporter(req.getParameter("reporter"));
				flightMonth.setStatus(Status.passAudit);
				flightMonth.setDates(timeUtils.getLastMonthD());
				//flightMonth.setReporter(ResourceUtil.getSessionUserName().getUserName());
				flightMonth.setWriteDate(new Date());
				flightMonth.setSmonth(timeUtils.getCurrentMonth(flightMonth.getDates()));
				systemService.saveOrUpdate(flightMonth);
				a++;
			}else{
				break;
			}
			
		}
		//通过工具类获取用户对象
		
	
		
		j.setMsg("上报成功");
		systemService.addLog("代填报空中飞行月报成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		return j;
	}
	//空中飞行数据列表
	@RequestMapping(params = "dataGrid")
	@ResponseBody
	public void dataGrid(FlightMonthly flightMonthly, HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(FlightMonthly.class, dataGrid);
		
		cq.add(Restrictions.eq("status", Status.passAudit));
		 String name=  flightMonthly.getOtherTravelInfo()==null?"":flightMonthly.getOtherTravelInfo().getName();
	      if(name != null && name.length() != 0){
	     
	    	  cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_othercompany_info t where t.name like '%"+name+"%' and t.id=otravel_id)"));
	      }
	      if(flightMonthly.getSmonth()!= null && flightMonthly.getSmonth().length() != 0){
	        	cq.add(Restrictions.like("smonth", "%"+flightMonthly.getSmonth()+"%"));
	        }
//	      cq.add(Restrictions.eqOrIsNull("type", Status.flight));
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new FlightMonthly());
		
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	   //月报审核列表
		//机场基本信息+月报信息,这个月审核上个月的信息
		@RequestMapping(params = "fmonthdatagrid")
		public void monthDatagrid(FlightMonthly flightMonthly,
				OtherTravelInfo otherTravelInfo, HttpServletRequest request,
				HttpServletResponse response, DataGrid dataGrid) {
			//获取当月时间yyyy-mm
			String currentMonth = timeUtils.getCurrentMonth(new Date());
			//获取上月时间
			String lastMonth = timeUtils.getLastMonth();
			//根据datagrid查询机场信息
			CriteriaQuery cq = new CriteriaQuery(OtherTravelInfo.class, dataGrid);
			otherTravelInfo.setType(Status.flight);
			String status=otherTravelInfo.getFlightMonth()==null?"":otherTravelInfo.getFlightMonth().getStatus();
			if(status!=null&&status.length()!=0&&!status.equals(Status.noSumbit)){
				cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_flight_monthly t where t.status ="+status+" and t.otravel_id=this_.id)"));
			}else if(status.equals(Status.noSumbit)){
				cq.add(Restrictions.sqlRestriction(" 1=1 and not exists(select * from t_flight_monthly t where t.status <>"+status+" and t.otravel_id=this_.id)"));
			}
			otherTravelInfo.setFlightMonth(null);
			if(otherTravelInfo.getName()!= null && otherTravelInfo.getName().length() != 0){
	        	cq.add(Restrictions.like("name", "%"+otherTravelInfo.getName()+"%"));
	        }
			cq.add(Restrictions.eqOrIsNull("type", Status.flight));
			
			com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				new	OtherTravelInfo());
			
			//获取easyUI的datagrid模型
			this.systemService.getDataGridReturn(cq, true);
			//对原型进行遍历list集合		
			System.out.println(dataGrid.getResults().size());
			for (Object o : dataGrid.getResults()) {
				//对集合元素进行操作
				OtherTravelInfo ot=(OtherTravelInfo) o;
				//对机场的月报判断是否为空
				if(ot.getFlightMonthList() != null){
					List<FlightMonthly> list = ot.getFlightMonthList();
					for (FlightMonthly mothdata :list ) {
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
							ot.setFlightMonth(mothdata);
						}
						//满足月份 跳出循环
						break;
					}
					//月报不存在 的情况  
					if(ot.getFlightMonth()==null){
						//创建一个 设置为代填报状态
						FlightMonthly hl= new FlightMonthly();
						hl.setDates(timeUtils.getLastMonthD());
						hl.setStatus(Status.noSumbit);
						ot.setFlightMonth(hl);
						//this.systemService.save(hl);
					}
				}
			}
			//将datagrid返回给前端页面
			TagUtil.datagrid(response, dataGrid);
		}
		//用户录入月报
		@RequestMapping(params="savefm")
		@ResponseBody
		public AjaxJson add(FlightMonthly flightMonthly,HttpServletRequest req){
			AjaxJson j = new AjaxJson();
			//通过工具类获取用户对象
			TSUser user = ResourceUtil.getSessionUserName();
			//id为机场的id
			
			if(flightMonthly.getId() == null || flightMonthly.getId().length() == 0) {
				String smonth = timeUtils.getCurrentMonth(flightMonthly.getDates());
				flightMonthly.setSmonth(smonth);
				flightMonthly.setId(null);
				if(this.isFilled(smonth)){
					j.setMsg("上月已填报月报!");
					return j;
				}
			flightMonthly.setOtid(user.getId());
			flightMonthly.setStatus(Status.noAudit);
			OtherTravelInfo  otherTravelInfo  = systemService.getEntity(OtherTravelInfo .class, flightMonthly.getOtid());
			flightMonthly.setOtherTravelInfo(otherTravelInfo);
			flightMonthly.setWriteDate(new Date());
			systemService.saveOrUpdate(flightMonthly);
			System.out.println("添加成功");
			
			j.setMsg("上报成功");
			}else{
				flightMonthly.setStatus(Status.noAudit);
				OtherTravelInfo  otherTravelInfo  = systemService.getEntity(OtherTravelInfo .class, flightMonthly.getOtid());
				flightMonthly.setOtherTravelInfo(otherTravelInfo);
				flightMonthly.setWriteDate(new Date());
				systemService.saveOrUpdate(flightMonthly);
				j.setMsg("编辑成功");
			}
			systemService.addLog("填报空中飞行月报成功:"+flightMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			return j;

		}
			//删除月报
		@RequestMapping(params = "delfm")
		@ResponseBody
		public AjaxJson del(HttpServletRequest req) {
			String monthId = req.getParameter("id");
			AjaxJson j = new AjaxJson();
			try {
				// systemService.getEntity(FlightMonthly.class, monthId);
				FlightMonthly flightMonthly = this.systemService.getEntity(FlightMonthly.class, monthId);
				systemService.delete(flightMonthly);
				message="月报删除成功";
			} catch (Exception e) {
				message="月报删除失败";
			}
			
			j.setMsg(message);
			return j;
		}
		//季报详情展示
		
		@RequestMapping(params = "detailfm")
		public ModelAndView detial(HttpServletRequest request,FlightMonthly flightMonthly,OtherTravelInfo otherTravelInfo,DataGrid dataGrid) {
			String otid = request.getParameter("otid");
			//季报id
			String fmonthId = request.getParameter("fmonthId");
			//存到请求域中
			//request.setAttribute("otid", otid);  
			OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
			flightMonthly = this.systemService.get(FlightMonthly.class, fmonthId);

			
			//flightMonthly.setOtherTravelInfo(h);   
			 
			
			return new ModelAndView("otherTravel/showfmonthDetail").addObject("flightMonthly",flightMonthly);
		}	
		//编辑
		@RequestMapping(params = "editfm")
		public ModelAndView editfm(HttpServletRequest request,FlightMonthly flightMonthly,OtherTravelInfo otherTravelInfo,DataGrid dataGrid) {
			String otid = request.getParameter("otid");
			//季报id
			String fmonthId = request.getParameter("fmonthId");
			//存到请求域中
			//request.setAttribute("otid", otid);  
			OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
			flightMonthly = this.systemService.get(FlightMonthly.class, fmonthId);

			flightMonthly.setOtherTravelInfo(h);   
			
			return new ModelAndView("otherTravelClient/edit/editFlightMonthly").addObject("flightMonthly",flightMonthly);
		}	
		
		//月报报审核管理员操作
		@RequestMapping(params = "addstatusfm")
		@ResponseBody
		public AjaxJson saveStatus(HttpServletRequest request,
				FlightMonthly flightMonthly,OtherTravelInfo otherTravelInfo,String statusid) {
			AjaxJson j = new AjaxJson();
			//季报的id
			String fmonthId = request.getParameter("fmonthId");
			request.setAttribute("fmonthId", fmonthId);
			try {
				if (StringUtil.isNotEmpty(fmonthId)) {
						 flightMonthly = systemService.getEntity(FlightMonthly.class,fmonthId);
						//1表示管理员审核通过
						flightMonthly.setStatus(Status.passAudit);
						//flightMonthly.set;
						systemService.saveOrUpdate(flightMonthly);
						//systemService.updateEntitie(scenicMonth);
						j.setMsg("管理员审核成功");
					}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			systemService.addLog("审核通过空中飞行月报成功:"+flightMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			return j;
		}
		@RequestMapping(params = "nocheckfm")
		@ResponseBody
		public AjaxJson saveNotStatus(HttpServletRequest request,OtherTravelInfo otherTravelInfo,
				FlightMonthly flightMonthly,String statusid) {
			AjaxJson j = new AjaxJson();
			String amonthId = request.getParameter("fmonthId");
			request.setAttribute("fmonthId", amonthId);
			if (StringUtil.isNotEmpty(amonthId)) {
				 flightMonthly = systemService.getEntity(FlightMonthly.class,amonthId);
				//request.setAttribute("scenicdata", scenicdata);
				flightMonthly.setStatus(Status.notAudit);//管理员审核未通过
				//flightMonthly.setAuditTime(new Date());
				systemService.saveOrUpdate(flightMonthly);
			}
			systemService.addLog("审核退回空中飞行月报成功:"+flightMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			return j;
		}
		 //审核页面
		@RequestMapping(params = "auditfm")
		public ModelAndView audit(HttpServletRequest request,FlightMonthly flightMonthly,OtherTravelInfo otherTravelInfo,DataGrid dataGrid) {
			String otid = request.getParameter("otid");
			String fmonthId = request.getParameter("fmonthId");
			request.setAttribute("otid", otid);  
			OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otid);
			FlightMonthly hq = this.systemService.get(FlightMonthly.class, fmonthId);
			
			hq.setOtherTravelInfo(h);
			
			return new ModelAndView("otherTravel/auditfmonth").addObject("flightMonthly", hq);
		}
		@RequestMapping(params = "addsignfm")
		public ModelAndView addsign(HttpServletRequest request) {
			String otid = request.getParameter("otid");
			//现在为空
			String fmonthId = request.getParameter("fmonthId");
			//hid=hotelmanageId;
			//System.out.println(hid);
			//request.setAttribute("hotelmanageId", hotelmanageId);
			//request.setAttribute("quarterId", quarterId);
			//根据基本信息id获取基本信息
			OtherTravelInfo  hm = this.systemService.get(OtherTravelInfo.class, otid);
			//根绝月报id获取月报信息
			FlightMonthly  s = this.systemService.get(FlightMonthly .class, fmonthId);
			//将基本信息设置在域中
			
			 //request.setAttribute("hotelmanage", hm);
			 if(s==null){
				 s=new FlightMonthly ();
				 
			 }
				
				s.setOtid(otid);;
				
				s.setDates(timeUtils.getLastMonthD());
			    s.setSmonth(timeUtils.getCurrentMonth(s.getDates()));
			    s.setOtherTravelInfo(hm);
			 request.setAttribute("flightMonthly", s);
			return new ModelAndView("otherTravel/daitianbaofm");    
		}
		/**
		 * 
		 * @date: 2016年12月29日
		 * @Author: 龙亚辉
		 * @Email: 502230926@qq.com
		 * @param: 
		 * @return:
		 * 保存代填报
		 */
		@RequestMapping(params="saveDaifm")
		@ResponseBody
		public AjaxJson addDai(FlightMonthly flightMonthly,HttpServletRequest req){
			AjaxJson j = new AjaxJson();
			//补全月报信息由
			//与机场的id进行关联			
			OtherTravelInfo otherTravelInfo = systemService.getEntity(OtherTravelInfo.class, flightMonthly.getOtid());
			flightMonthly.setOtherTravelInfo(otherTravelInfo);;
			flightMonthly.setStatus(Status.passAudit);
			flightMonthly.setDates(timeUtils.getLastMonthD());
			flightMonthly.setWriteDate(new Date());
			flightMonthly.setSmonth(timeUtils.getCurrentMonth(flightMonthly.getDates()));
			String fid = flightMonthly.getId();
			if(fid.length()==0){
				systemService.save(flightMonthly);
			}else{
				
				systemService.saveOrUpdate(flightMonthly);
			}	
			
			j.setMsg("上报数据成功");
			
			systemService.addLog("填报空中飞行月报成功:"+flightMonthly.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			
			return j;
		}
		/**
		 * 
		 * @date: 2016年12月29日
		 * @Author: 龙亚辉
		 * @Email: 502230926@qq.com
		 * @param: 
		 * @return:
		 * 暂存
		 */
		@RequestMapping(params = "zancuncheckfm")
		@ResponseBody 
		public AjaxJson saveZanStatus(HttpServletRequest request,
				FlightMonthly flightMonthly,String statusid) {
			AjaxJson j = new AjaxJson();
			String otid=request.getParameter("otid");
			request.setAttribute("otid", otid);
			//根据id获取机场信息实体类
			OtherTravelInfo otherTravelInfo =this.systemService.getEntity(OtherTravelInfo .class, otid);
			//将基本信息添加到月报信息中
			flightMonthly.setOtid(otid);
			flightMonthly.setOtherTravelInfo(otherTravelInfo);;
			flightMonthly.setDates(timeUtils.getLastMonthD());
			flightMonthly.setSmonth(timeUtils.getCurrentMonth(flightMonthly.getDates()));
			//状态仍为未填报
			flightMonthly.setStatus(Status.noSumbit);
			//更新
			String fid = flightMonthly.getId();
			if(fid.length()==0){
				systemService.save(flightMonthly);
			}else{
				
				systemService.saveOrUpdate(flightMonthly);
			}
			systemService.addLog("暂存空中飞行月报成功:"+flightMonthly.getId(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			return j;
		}
		//月报信息导出
		@RequestMapping(params = "exportsxls")
		public String exportXls(FlightMonthly FlightMonthly,HttpServletRequest request,HttpServletResponse response
				, DataGrid dataGrid,ModelMap map) {
		
		    CriteriaQuery cq = new CriteriaQuery(FlightMonthly.class, dataGrid);
		    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, FlightMonthly, request.getParameterMap());
		    //List<CourseEntity> courses = this.courseService.getListByCriteriaQuery(cq,false);
		    String hotelId = ResourceUtil.getSessionUserName().getId();
		    List<FlightMonthly> flightMonthlys = this.systemService.getList(FlightMonthly.class);
		    map.put(NormalExcelConstants.FILE_NAME,"空中飞行月报信息");
		    map.put(NormalExcelConstants.CLASS,FlightMonthly.class);
		    map.put(NormalExcelConstants.PARAMS,new ExportParams("空中飞行项目月报列表", "导出人:管理员",
		            "导出信息"));
		    map.put(NormalExcelConstants.DATA_LIST,flightMonthlys);
		    return NormalExcelConstants.JEECG_EXCEL_VIEW;
		
		}
		//**************************************上空中飞行管理员后台,下是空中飞行客户后台***********************************
		//跳至用户月报管理
		@RequestMapping(params = "ckflight")
		public String user(HttpServletRequest request) {
			// 给部门查询条件中的下拉框准备数据
			//List<TSDepart> departList = systemService.getList(TSDepart.class);
			//request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
			return "otherTravelClient/ckflightMonthlyList";
		}
		//月报管理
		@ResponseBody
		@RequestMapping(params = "fcdatagrid")
		public void datagridClient(FlightMonthly flightMonthly,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
			//1.从session中获取当前用户的 id
			String otid = ResourceUtil.getSessionUserName().getId();
			//System.out.println(otid);
			flightMonthly.setOtid(otid);
	        CriteriaQuery cq = new CriteriaQuery(FlightMonthly.class, dataGrid);
	        //查询条件组装器
	        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, flightMonthly);      
	        cq.add();
	        this.systemService.getDataGridReturn(cq, true);
	        //List<OtherTravelInfo> cfeList = new ArrayList<OtherTravelInfo>();
	        TagUtil.datagrid(response, dataGrid);
	    }
		//月报导出
		@RequestMapping(params = "exportsxlss")
		public void exportXlss( HttpServletRequest request,HttpServletResponse response)throws Exception {
		
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        
	        // 获取参数 
	        String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
	        String smonth= new String(request.getParameter("smonth").getBytes("iso8859-1"),"utf-8");
	        String status= new String(request.getParameter("status").getBytes("iso8859-1"),"utf-8");
	        
	        String excelName = "空中飞行月报统计.xls";
	        String modelPath = "/com/zzc/web/export/model/otherTravel/"+excelName;
	        StringBuffer sql = new StringBuffer();
	        sql.append("  SELECT o.name,t.smonth, t.reception_num, t.business_income, t.flight_times, IF ( t.`status` = '1', '未填报', IF ( t.`status` = '2', '未审核', IF ( t.`status` = '3','管理员已审核(审核不通过)',IF (t.`status` = '4','管理员已审核(审核通过)','市级审核'))))'status' FROM t_flight_monthly t LEFT JOIN t_othercompany_info o ON t.otravel_id = o.ID where 1=1 and t.status='4' ");
	        // 组装查询条件
	        if(smonth !=null && smonth.length() != 0){
	        	 sql.append("  and t.smonth LIKE '"+smonth+"' ");
	        }
	        
	        if(status !=null && status.length() != 0){
	        	sql.append(" and t.status ='"+status+"'");
	        }
	        if(name !=null && name.length() != 0){
	        	sql.append(" and t.name ='"+name+"'");
	        }
	        sql.append(" union SELECT '合计','', IFNULL(sum(t.reception_num),0),IFNULL(sum(t.business_income),0),IFNULL(sum(t.flight_times),0) , ''  FROM t_flight_monthly t LEFT JOIN t_othercompany_info o ON t.otravel_id = o.ID where 1=1 and t.status='4'  ");
	     // 组装查询条件
	        if(smonth !=null && smonth.length() != 0){
	        	 sql.append("  and t.smonth LIKE '"+smonth+"' ");
	        }
	        
	        if(status !=null && status.length() != 0){
	        	sql.append(" and t.status ='"+status+"'");
	        }
	        if(name !=null && name.length() != 0){
	        	sql.append(" and t.name ='"+name+"'");
	        }
	        ExportService emds = new ExportService(3,6); 
	        
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
		
		/**
		 * 
		 * 检查上月报是否填报
		 * @return
		 */
		public boolean isFilled(String smonth){
			boolean tip = false;
			//获取当前登录用户ID=涉旅ID
			String userId = ResourceUtil.getSessionUserName().getId();
			
			
			String sql = "select id from t_flight_monthly t where t.smonth like '"+smonth+"' and t.otravel_id = '"+userId+"'";
			List<Map<String, Object>> list = systemService.findForJdbc(sql);
			if(list.size() != 0) tip = true;
			return tip;
		}

}
