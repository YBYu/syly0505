package com.zzc.web.htoal.controller;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.htoal.entity.HotelEstimate;
import com.zzc.web.htoal.entity.HotelMonthly;
import com.zzc.web.htoal.service.Hotelservice;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.TimeUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
/**
 * 
 *                  
 * @date: 2016年11月29日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HotelMonthly.java
 * @Version: 1.0
 * @About: 
 *酒店住宿情况月报
 */
@Scope("prototype")
@Controller
@RequestMapping("/hotelEstimateController")
public class HotelEstimateController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(HotelEstimateController.class);
	private String message = null;
	private SystemService systemService;
	//private String hid=null;
	//调用时间工具
	TimeUtils timeUtils = new TimeUtils();
	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	private Hotelservice hotelservice;
	@Autowired
	public void setHotelservice(Hotelservice hotelservice) {
		this.hotelservice = hotelservice;
	}
	//跳转到save页面
	@RequestMapping(params="addorupdate")
	public ModelAndView hotelInfo(HttpServletRequest req,HotelEstimate hotelEstimate){
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		hotelEstimate.setCreateTime(sdf.format(date));
		
		return new ModelAndView("system/hotel/saveHotelEstimate").addObject("year", year).addObject("month", month).addObject("hotelEstimate",hotelEstimate);
	}
	//预计接待数据管理跳转页面
	@RequestMapping(params = "hotelEstimate")
	public ModelAndView role(HttpServletRequest req) {
		Calendar calendar = Calendar.getInstance();
		String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
		String nowMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		// 查询当前时间是否已经填报过
		String sql = "select 1 from t_hotelEstimate t where t.year=? and t.t_month=?";
		List<Map<String, Object>> list = systemService.findForJdbc(sql, new Object[]{nowYear, nowMonth});
		if(list != null && list.size() != 0){
			req.setAttribute("havedata", "havedata");
		}
		return new ModelAndView("system/hotel/hotelEstimateList");
	}
	
	/**
	 * 编辑功能
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "editEstimate")
	public ModelAndView editEstimate(HttpServletRequest request) {
		String id = request.getParameter("id");
		HotelEstimate hotelEstimate = systemService.get(HotelEstimate.class, id);
		String year = hotelEstimate.getYear();
		String month = hotelEstimate.getMonth();

		request.setAttribute("hotelEstimate", hotelEstimate);
		return new ModelAndView("system/hotel/saveHotelEstimate").addObject("year", year).addObject("month", month).addObject("hotelEstimate", hotelEstimate);
	}
	
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson add(HotelEstimate hotelEstimate,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		
		String id = hotelEstimate.getId();
		// 处于新建时做数据重复判断
		if(id == null || id.length() == 0){
			hotelEstimate.setId(null);
			// 获取当前时间
	/*		Calendar calendar = Calendar.getInstance();
			String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
			String nowMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
			// 查询当前时间是否已经填报过
			String sql = "select 1 from t_hotelEstimate t where t.year=? and t.t_month=?";
			List<Map<String, Object>> list = systemService.findForJdbc(sql, new Object[]{nowYear, nowMonth});
			if(list != null && list.size() != 0){
				// 存在数据
				j.setMsg("当月已填报数据");
				return j;
			}*/
		}
		// 状态未同步
		hotelEstimate.setSourceStatus("0");
		TSUser user = ResourceUtil.getSessionUserName();
		hotelEstimate.setBymanager(user.getRealName());
		systemService.saveOrUpdate(hotelEstimate);
		j.setMsg("添加成功");
		systemService.addLog("填报预计接待数据成功:"+hotelEstimate.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		return j;
	}
	
	
	@RequestMapping(params = "datagrid")
	public void roleGrid(HotelEstimate hotelEstimate, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HotelEstimate.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				hotelEstimate);
		cq.add(Restrictions.sqlRestriction(" 1=1 order by str_to_date(create_time,'%Y-%m-%d %H:%i:%s') desc"));
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	//删除月报
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HotelEstimate hotelEstimate, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			hotelEstimate = hotelservice.getEntity(HotelMonthly.class, id);
			hotelservice.delete(hotelEstimate);
			message="预计接待详情删除成功";
		} catch (Exception e) {
			message="预计接待详情删除失败";
		}
		
		j.setMsg(message);
		return j;
	}
	
	//预计接待情况详情展示
	@RequestMapping(params = "detail")
	public String detial(HttpServletRequest request) {
		String hotelEstimateId = request.getParameter("hotelEstimateId");
		
		//根据id查询所对应的实体类信息  
		HotelEstimate h = this.systemService.get(HotelEstimate.class, hotelEstimateId);

		//存到请求域中
		  request.setAttribute("hotelEstimate",h);
		
		return  "system/hotel/showHotelEstimateDetail";
	}
	@RequestMapping(params = "exportsxls")
	public String exportXls(HotelEstimate hotelEstimate,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap map) {
	
	    CriteriaQuery cq = new CriteriaQuery(HotelEstimate.class, dataGrid);
	    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hotelEstimate, request.getParameterMap());
	    List<HotelEstimate> hotelEstimates = this.systemService.getListByCriteriaQuery(cq,false);
	    map.put(NormalExcelConstants.FILE_NAME,"酒店预计接待信息");
	    map.put(NormalExcelConstants.CLASS,HotelEstimate.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("酒店预计接待列表", "导出人:酒店管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,hotelEstimates);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
		
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportData")
	public void exportData(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        
        String excelName = "预计接待数据.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("select t.`year`,t.t_month,t.countryCode,t.bymanager,t.create_time,t.t_sums,t.t_sums_d from t_hotelestimate t where 1=1 ");
        if(year != null && year.length() != 0){
        	sql.append(" and t.`year` like '%"+year+"%' ");
        }
        if(month != null && month.length() != 0){
        	sql.append(" and t.t_month = '"+month+"' ");
        }
        sql.append(" union all ");
        sql.append("select '' `合计`,'','','','',sum(t.t_sums),sum(t.t_sums_d) from t_hotelestimate t where 1=1 ");
        if(year != null && year.length() != 0){
        	sql.append(" and t.`year` like '%"+year+"%' ");
        }
        if(month != null && month.length() != 0){
        	sql.append(" and t.t_month = '"+month+"' ");
        }
        ExportService emds = new ExportService(7); 
        
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
