package com.zzc.web.otherTravel.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.system.service.SystemService;
/**
 * 
 *                  
 * @date: 2017年2月12日
 * @Author: 龙
 * @Email: 502230926@qq.com
 * @FileName: ExportotherTravelController.java
 * @Version: 1.0
 * @About:  其他涉旅统计
 *
 */
@RequestMapping("/exportotherTravelController")
@Controller
public class ExportotherTravelController extends BaseController {
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	//统计下载页面
	@RequestMapping(params = "toexports")
	public ModelAndView toexports(HttpServletRequest request) {
		return new ModelAndView("otherTravelStaticMonth/otherTravelMonth");
	}
	

	
	//高尔夫统计
		@RequestMapping(params = "excelExport1")
		public void export1(HttpServletRequest request, HttpServletResponse response) throws Exception{
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        String excelName = "高尔夫月报统计.xls";
	        String modelPath = "/com/zzc/web/export/model/otherTravel/"+excelName;
			String year = request.getParameter("year");
		        // 获取要查询的月份
		     String month = request.getParameter("month");
		     if(year == null || year.length() == 0) year = "2017-";
		     if(month == null || month.length() == 0) month = "01";
		     String smonth=year.concat(month);
		    String sql = "SELECT t.smonth,IFNULL(SUM(t.reception_num), 0) reeptionnum,IFNULL(SUM(t.business_income), 0) businessincome "
		    		+ "FROM t_golf_monthly t WHERE t.smonth LIKE '"+ smonth+"'";
	        ExportService emds = new ExportService(3,3); 
	        
	        HSSFWorkbook workbook = null;
	        try
	        {
	            workbook = emds.getNewModelInfos(modelPath, sql);
	        } catch (Exception e)
	        {

	        }

	        response.reset();
	        response.setContentType("text/html;charset=UTF-8");

	        if (workbook != null)
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(smonth+excelName, "UTF-8"));
	            POIUtils.writeWorkbook(workbook, response.getOutputStream());
	        } else
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("Errors.xls", "UTF-8"));
	            response.getOutputStream().print("You have already downloaded the error excel!");
	        }
		     
			
		}
		
		
		//游艇统计
		@RequestMapping(params = "excelExport2")
		public void export2(HttpServletRequest request, HttpServletResponse response) throws Exception{
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        String excelName = "游艇月报统计.xls";
	        String modelPath = "/com/zzc/web/export/model/otherTravel/"+excelName;
			String year = request.getParameter("year");
		        // 获取要查询的月份
		     String month = request.getParameter("month");
		     if(year == null || year.length() == 0) year = "2017-";
		     if(month == null || month.length() == 0) month = "01";
		     String smonth=year.concat(month);
		    String sql = "SELECT t.smonth,IFNULL(SUM(t.recep_num), 0) reeptionnum,IFNULL(SUM(t.income), 0) income "
		    		+ "FROM t_boat_monthly t WHERE t.smonth LIKE '"+ smonth+"'";
	        ExportService emds = new ExportService(3,3); 
	        
	        HSSFWorkbook workbook = null;
	        try
	        {
	            workbook = emds.getNewModelInfos(modelPath, sql);
	        } catch (Exception e)
	        {

	        }

	        response.reset();
	        response.setContentType("text/html;charset=UTF-8");

	        if (workbook != null)
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(smonth+excelName, "UTF-8"));
	            POIUtils.writeWorkbook(workbook, response.getOutputStream());
	        } else
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("Errors.xls", "UTF-8"));
	            response.getOutputStream().print("You have already downloaded the error excel!");
	        }
		     
			
		}
		
		
		//动车统计
		@RequestMapping(params = "excelExport3")
		public void export3(HttpServletRequest request, HttpServletResponse response) throws Exception{
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        String excelName = "动车月报统计.xls";
	        String modelPath = "/com/zzc/web/export/model/otherTravel/"+excelName;
			String year = request.getParameter("year");
		        // 获取要查询的月份
		     String month = request.getParameter("month");
		     if(year == null || year.length() == 0) year = "2017-";
		     if(month == null || month.length() == 0) month = "01";
		     String smonth=year.concat(month);
		    String sql = "SELECT t.smonth,IFNULL(SUM(t.in_num), 0) innum,IFNULL(SUM(t.out_num), 0) outnum,IFNULL(SUM(t.throughput), 0) throughput "
		    		+ "FROM t_motorcar_monthly t WHERE t.smonth LIKE '"+ smonth+"'";
		    System.out.println(sql);
	        ExportService emds = new ExportService(3,4); 
	        
	        HSSFWorkbook workbook = null;
	        try
	        {
	            workbook = emds.getNewModelInfos(modelPath, sql);
	        } catch (Exception e)
	        {

	        }

	        response.reset();
	        response.setContentType("text/html;charset=UTF-8");

	        if (workbook != null)
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(smonth+excelName, "UTF-8"));
	            POIUtils.writeWorkbook(workbook, response.getOutputStream());
	        } else
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("Errors.xls", "UTF-8"));
	            response.getOutputStream().print("You have already downloaded the error excel!");
	        }
		     
			
		}
		
		
		//空中飞行统计
		@RequestMapping(params = "excelExport4")
		public void export4(HttpServletRequest request, HttpServletResponse response) throws Exception{
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        String excelName = "空中飞行月报统计.xls";
	        String modelPath = "/com/zzc/web/export/model/otherTravel/"+excelName;
			String year = request.getParameter("year");
		        // 获取要查询的月份
		     String month = request.getParameter("month");
		     if(year == null || year.length() == 0) year = "2017-";
		     if(month == null || month.length() == 0) month = "01";
		     String smonth=year.concat(month);
		    String sql = "SELECT t.smonth,IFNULL(SUM(t.reception_num), 0) receptionnum,IFNULL(SUM(t.business_income), 0) businessincome,IFNULL(SUM(t.flight_times), 0) flighttimes "
		    		+ "FROM t_flight_monthly t WHERE t.smonth LIKE '"+ smonth+"'";
	        ExportService emds = new ExportService(3,4); 
	        
	        HSSFWorkbook workbook = null;
	        try
	        {
	            workbook = emds.getNewModelInfos(modelPath, sql);
	        } catch (Exception e)
	        {

	        }

	        response.reset();
	        response.setContentType("text/html;charset=UTF-8");

	        if (workbook != null)
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(smonth+excelName, "UTF-8"));
	            POIUtils.writeWorkbook(workbook, response.getOutputStream());
	        } else
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("Errors.xls", "UTF-8"));
	            response.getOutputStream().print("You have already downloaded the error excel!");
	        }
		     
			
		}
		
		
		//机场统计
		@RequestMapping(params = "excelExport5")
		public void export5(HttpServletRequest request, HttpServletResponse response) throws Exception{
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        String excelName = "机场月报统计.xls";
	        String modelPath = "/com/zzc/web/export/model/otherTravel/"+excelName;
			String year = request.getParameter("year");
		        // 获取要查询的月份
		     String month = request.getParameter("month");
		     if(year == null || year.length() == 0) year = "2017-";
		     if(month == null || month.length() == 0) month = "01";
		     String smonth=year.concat(month);
		    String sql = "SELECT t.smonth,"
		    		+ "IFNULL(SUM(t.plan_sortie), 0) plansortie,IFNULL(SUM(t.actual_sortie), 0) actualsortie,IFNULL(SUM(t.plan_sortie_year), 0) plansortieyear, "
		    		+ "IFNULL(SUM(t.plan_out_traveller), 0) planouttraveller,IFNULL(SUM(t.actual_out_traveller), 0) actualouttraveller,IFNULL(SUM(t.plan_out_traveller_year), 0) planouttravelleryear, "
		    		+ "IFNULL(SUM(t.plan_out_will), 0) planoutwill,IFNULL(SUM(t.actual_out_will), 0) actualoutwill,IFNULL(SUM(t.plan_out_will_year), 0) planoutwillyear, "
		    		+ "IFNULL(SUM(t.plan_in_traveller), 0) planIntegerraveller,IFNULL(SUM(t.actual_in_traveller), 0) actualIntegerraveller,IFNULL(SUM(t.plan_in_traveller_year), 0) planIntegerravelleryear, "
		    		+ "IFNULL(SUM(t.plan_in_will), 0) planinwill,IFNULL(SUM(t.actual_in_will), 0) actualinwill,IFNULL(SUM(t.plan_in_will_year), 0) planinwillyear, "
		    		+ "IFNULL(SUM(t.plan_throughput_traveller), 0) planthroughputtraveller,IFNULL(SUM(t.actual_throughput_traveller), 0) actualthroughputtraveller,IFNULL(SUM(t.plan_throughput_traveller_year), 0) planthroughputtravelleryear, "
		    		+ "IFNULL(SUM(t.plan_throughput_will), 0) planthroughputwill,IFNULL(SUM(t.actual_throughput_will), 0) actualthroughputwill,IFNULL(SUM(t.plan_throughput_will_year), 0) planthroughputwillyear  "
		    		+ "FROM t_airport_monthly t WHERE t.smonth LIKE '"+ smonth+"'";
		    System.out.println(sql);
	        ExportService emds = new ExportService(3,22); 
	        
	        HSSFWorkbook workbook = null;
	        try
	        {
	            workbook = emds.getNewModelInfos(modelPath, sql);
	        } catch (Exception e)
	        {

	        }

	        response.reset();
	        response.setContentType("text/html;charset=UTF-8");

	        if (workbook != null)
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(smonth+excelName, "UTF-8"));
	            POIUtils.writeWorkbook(workbook, response.getOutputStream());
	        } else
	        {
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("Errors.xls", "UTF-8"));
	            response.getOutputStream().print("You have already downloaded the error excel!");
	        }
		     
			
		}	
	

}
