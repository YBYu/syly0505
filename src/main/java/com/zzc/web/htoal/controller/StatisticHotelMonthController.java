package com.zzc.web.htoal.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.util.StringUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.htoal.controller.sql.HotelSQL;
import com.zzc.web.scenicmanage.util.ExcelExportUtils;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.system.service.SystemService;

@Scope("prototype")
@Controller
@RequestMapping("/statisticHotelMonthController")
public class StatisticHotelMonthController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(StatisticHotelMonthController.class);
	private String message = null;
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	// 统计下载页面
	@RequestMapping(params = "toexports")
	public ModelAndView toexports(HttpServletRequest request) {
		List<String> yearList = this.systemService
				.findListbySql("SELECT distinct th.`year` AS year FROM  t_hotelmonthly th order by th.`year` desc");
		request.setAttribute("yearList", yearList);
		return new ModelAndView("hotelStaticMonth/hotelMonth");
	}

	private String getParam(HttpServletRequest request, String string) {
		return (String) request.getParameter(string);
	}

	/**
	 * 
	 * 接待入境过夜游客分国别情况
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "jieDaiRujingByCountry")
	public void jieDaiRujingByCountry(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String area = request.getParameter("area");
		String bay = request.getParameter("bay");
		String star = request.getParameter("star");
		
		HotelSQL.year = year;
		HotelSQL.month = month;
		HotelSQL.area = area;
		HotelSQL.bay = bay;
		HotelSQL.star = star;
		String lastYear = String.valueOf(Integer.parseInt(year) - 1);
		HotelSQL.lastYear = lastYear;
		
		
		ExportService emds = new ExportService(1,0,2);
		
		String excelName = "接待入境过夜游客分国别情况.xls";
		String modelPath = "/com/zzc/web/export/model/hotel/newmodel/"+ excelName;
		
		HSSFWorkbook workbook = null;
		// 查询今年月统计
		try {
			String sql = HotelSQL.getJieDaiRujingByCountry1().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询去年月统计
		try {
			String sql = HotelSQL.getJieDaiRujingByCountry2().toString();
			emds = new ExportService(1,2,1);
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询今年总计
		try {
			String sql = HotelSQL.getJieDaiRujingByCountry3().toString();
			emds = new ExportService(1,4,1);
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询去年总计
		try {
			String sql = HotelSQL.getJieDaiRujingByCountry4().toString();
			emds = new ExportService(1,5,1);
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 强制执行公式
		workbook.getSheetAt(0).setForceFormulaRecalculation(true);
		workbook.getSheetAt(0).getRow(0).getCell(1).setCellValue(year+"年"+month+"月");
		workbook.getSheetAt(0).getRow(0).getCell(2).setCellValue(lastYear+"年"+month+"月");
		workbook.getSheetAt(0).getRow(0).getCell(4).setCellValue(year+"年初至"+month+"月");
		workbook.getSheetAt(0).getRow(0).getCell(5).setCellValue(lastYear+"年初至"+month+"月");
		
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
	 * 
	 * 地方接待过夜游客情况汇总
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "totalJiedaiByArea")
	public void totalJiedaiByArea (HttpServletRequest request, HttpServletResponse response) throws Exception{
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String area = request.getParameter("area");
		String bay = request.getParameter("bay");
		String star = request.getParameter("star");
		
		HotelSQL.year = year;
		HotelSQL.month = month;
		HotelSQL.area = area;
		HotelSQL.bay = bay;
		HotelSQL.star = star;
		String lastYear = String.valueOf(Integer.parseInt(year) - 1);
		HotelSQL.lastYear = lastYear;
		
		
		ExportService emds = new ExportService(1,1,1);
		
		String excelName = "地方接待过夜游客情况汇总.xls";
		String modelPath = "/com/zzc/web/export/model/hotel/newmodel/"+ excelName;
		
		HSSFWorkbook workbook = null;
		// 查询今年人次
		try {
			String sql = HotelSQL.getTotalJiedaiByArea1().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询今年总计人次
		try {
			emds = new ExportService(1,2,1);
			String sql = HotelSQL.getTotalJiedaiByArea2().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询去年人次
		try {
			emds = new ExportService(1,4,1);
			String sql = HotelSQL.getTotalJiedaiByArea3().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询去年总计人次
		try {
			emds = new ExportService(1,5,1);
			String sql = HotelSQL.getTotalJiedaiByArea4().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询今年人天
		try {
			emds = new ExportService(8,1,1);
			String sql = HotelSQL.getTotalJiedaiByArea5().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询今年总计人天
		try {
			emds = new ExportService(8,2,1);
			String sql = HotelSQL.getTotalJiedaiByArea6().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询去年人天
		try {
			emds = new ExportService(8,4,1);
			String sql = HotelSQL.getTotalJiedaiByArea7().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查询去年总计人天
		try {
			emds = new ExportService(8,5,1);
			String sql = HotelSQL.getTotalJiedaiByArea8().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 强制执行公式
		workbook.getSheetAt(0).setForceFormulaRecalculation(true);
		workbook.getSheetAt(0).getRow(0).getCell(1).setCellValue(year+"年"+month+"月接待人数（人次）");
		workbook.getSheetAt(0).getRow(0).getCell(2).setCellValue(lastYear+"年"+month+"月接待人数（人次）");
		workbook.getSheetAt(0).getRow(0).getCell(3).setCellValue(lastYear+"年"+month+"月同比增长（%）");
		workbook.getSheetAt(0).getRow(0).getCell(4).setCellValue(year+"年初-"+month+"月累计接待人数（人次）");
		workbook.getSheetAt(0).getRow(0).getCell(5).setCellValue(lastYear+"年初-"+month+"月累计接待人数（人次）");
		workbook.getSheetAt(0).getRow(0).getCell(6).setCellValue(lastYear+"年初-"+month+"月累计同比增长（%）");
		
		workbook.getSheetAt(0).getRow(7).getCell(1).setCellValue(year+"年"+month+"月接待人数（人天）");
		workbook.getSheetAt(0).getRow(7).getCell(2).setCellValue(lastYear+"年"+month+"月接待人数（人天）");
		workbook.getSheetAt(0).getRow(7).getCell(3).setCellValue(lastYear+"年"+month+"月同比增长（%）");
		workbook.getSheetAt(0).getRow(7).getCell(4).setCellValue(year+"年初-"+month+"月累计接待人数（人天）");
		workbook.getSheetAt(0).getRow(7).getCell(5).setCellValue(lastYear+"年初-"+month+"月累计接待人数（人天）");
		workbook.getSheetAt(0).getRow(7).getCell(6).setCellValue(lastYear+"年初-"+month+"月累计同比增长（%）");
		
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
	 * 分地区接待入境过夜游客分国别情况汇总
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "jiedaiByCountryAndArea")
	public void jiedaiByCountryAndArea (HttpServletRequest request, HttpServletResponse response) throws Exception{
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String area = request.getParameter("area");
		String bay = request.getParameter("bay");
		String star = request.getParameter("star");
		
		HotelSQL.year = year;
		HotelSQL.month = month;
		HotelSQL.area = area;
		HotelSQL.bay = bay;
		HotelSQL.star = star;
		String lastYear = String.valueOf(Integer.parseInt(year) - 1);
		HotelSQL.lastYear = lastYear;
		
		String excelName = "分地区接待入境过夜游客分国别情况汇总.xls";
		String modelPath = "/com/zzc/web/export/model/hotel/newmodel/"+ excelName;
		
		ExportService emds = new ExportService(56);

		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, HotelSQL.getJiedaiByCountryAndArea().toString());
		} catch (Exception e) {

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
	 * 旅游饭店接待外国人分国别(地区)人数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "foreignsJiedaiPeopleTimes")
	public void foreignsJiedaiPeopleTimes (HttpServletRequest request, HttpServletResponse response) throws Exception{
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String area = request.getParameter("area");
		String bay = request.getParameter("bay");
		String star = request.getParameter("star");
		
		HotelSQL.year = year;
		HotelSQL.month = month;
		HotelSQL.area = area;
		HotelSQL.bay = bay;
		HotelSQL.star = star;
		String lastYear = String.valueOf(Integer.parseInt(year) - 1);
		HotelSQL.lastYear = lastYear;
		
		String excelName = "旅游饭店接待外国人分国别(地区)人数.xls";
		String modelPath = "/com/zzc/web/export/model/hotel/newmodel/"+ excelName;
		
		ExportService emds = null;

		HSSFWorkbook workbook = null;
		// 本月
		try {
			emds = new ExportService(1,1,1);
			String sql = HotelSQL.getForeignsJiedaiPeopleTimes1().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
		// 累积
		try {
			emds = new ExportService(1,2,1);
			String sql = HotelSQL.getForeignsJiedaiPeopleTimes2().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
		workbook.setForceFormulaRecalculation(true);
		
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
	 * 星级非星级住宿接待情况
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "allHotelJiedai")
	public void allHotelJiedai (HttpServletRequest request, HttpServletResponse response) throws Exception{
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String area = request.getParameter("area");
		String bay = request.getParameter("bay");
		String star = request.getParameter("star");
		
		HotelSQL.year = year;
		HotelSQL.month = month;
		HotelSQL.area = area;
		HotelSQL.bay = bay;
		HotelSQL.star = star;
		String lastYear = String.valueOf(Integer.parseInt(year) - 1);
		HotelSQL.lastYear = lastYear;
		
		String excelName = "星级非星级住宿接待情况.xls";
		String modelPath = "/com/zzc/web/export/model/hotel/newmodel/"+ excelName;
		
		ExportService emds = null;

		HSSFWorkbook workbook = null;
		// 本月人次
		try {
			emds = new ExportService(1,1,1);
			String sql = HotelSQL.getAllHotelJiedai1().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}				
		// 累积人次
		try {
			emds = new ExportService(1,2,1);
			String sql = HotelSQL.getAllHotelJiedai2().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 本月人天
		try {
			emds = new ExportService(1,3,1);
			String sql = HotelSQL.getAllHotelJiedai3().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}				
		// 累积人天
		try {
			emds = new ExportService(1,4,1);
			String sql = HotelSQL.getAllHotelJiedai4().toString();
			workbook = emds.getNewModelInfos2(modelPath, sql.toString(), workbook);
		} catch (Exception e) {
			e.printStackTrace();
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
	 * 
	 * (三亚市)房间出租率汇总
	 * @param year 年，month 月
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "occupancyRate")
	public void occupancyRate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "(三亚市)房间出租率汇总.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/newmodel/"+excelName;
        
        String year = request.getParameter("year");
		String month = request.getParameter("month");
		String area = request.getParameter("area");
		String bay = request.getParameter("bay");
		String star = request.getParameter("star");
        
        Calendar calendar = Calendar.getInstance();
        if(year.equals(String.valueOf(calendar.get(Calendar.YEAR)))){
        	 if(month == null || month.length() == 0) month = String.valueOf(calendar.get(Calendar.MONTH )+1);
        }
       
        
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlTotal = new StringBuffer();
        ExportService emds = new ExportService(5); // 数字代表excel表格的列数,只导出1列时会有bug,暂时不做处理
        for (int i = 1; i <= Integer.parseInt(month); i++) {
        	sql.append(" SELECT ");
        	sql.append(" 	'"+i+"月' MONTH, ");
        	sql.append(" 	'三亚市' cityname, ");
        	sql.append(" 	CAST(IFNULL(sum(t.actual_rentnum),0) AS CHAR) actualrent, ");
        	sql.append(" 	CAST(IFNULL(sum(t.rent_num),0) AS CHAR)rentnum, ");
        	sql.append(" 	ROUND ( ");
        	sql.append(" 		( ");
        	sql.append(" 			IFNULL( ");
        	sql.append(" 				( ");
        	sql.append(" 					sum(t.actual_rentnum) / sum(t.rent_num) ");
        	sql.append(" 				) * 100, ");
        	sql.append(" 				0 ");
        	sql.append(" 			) ");
        	sql.append(" 		), ");
        	sql.append(" 		2 ");
        	sql.append(" 	) percent ");
        	sql.append(" FROM ");
        	sql.append(" 	t_hotelmonthly t  LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        	sql.append(" WHERE ");
        	sql.append(" 	t.`year` = '"+year+"' ");
        	sql.append(" AND t.`month` = '"+i+"' ");
        	if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        	// 判断是否为最后一个月份
        	if(i != Integer.parseInt(month)) sql.append(" union all ");
		}
        
        
        sqlTotal.append(" SELECT ");
        sqlTotal.append(" 	'总计' MONTH, ");
        sqlTotal.append(" 	'' cityname,");
        sqlTotal.append(" 	sum(t.actualrent) actualrent, ");
        sqlTotal.append(" 	sum(t.rentnum) rentnum, ");
        sqlTotal.append(" 	ROUND ( ");
        sqlTotal.append(" 		( ");
        sqlTotal.append(" 			IFNULL( ");
        sqlTotal.append(" 				( ");
        sqlTotal.append(" 					sum(t.actualrent) / sum(t.rentnum) ");
        sqlTotal.append(" 				) * 100, ");
        sqlTotal.append(" 				0 ");
        sqlTotal.append(" 			) ");
        sqlTotal.append(" 		), ");
        sqlTotal.append(" 		2 ");
        sqlTotal.append(" 	) percent ");
        sqlTotal.append(" FROM ");
        sqlTotal.append(" 	( ");
        sqlTotal.append(new String(sql));
        sqlTotal.append(" ) t ");
        
        sql.append(" union all");
        sql.append(new String(sqlTotal));

        // 根据数据表,页面查询条件, 抓取数据库中对应的数据
        HSSFWorkbook workbook = null;
        try
        {
            workbook = emds.getNewModelInfos(modelPath, sql.toString());
        } catch (Exception e)
        {
        	e.printStackTrace();
        }

        response.reset();
        response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String((year+"年第"+month+"月房间出租率汇总.xls").getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+"年第"+month+"月房间出租率汇总.xls", "UTF-8"));

	        }
			workbook.setSheetName(0, year+"年第"+month+"月房间出租率汇总.xls");
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
        
	}
	
	
	// (三亚市)房间出租率汇总
	@RequestMapping(params = "exportsxls1")
	public AjaxJson exportXls1(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		/* List list = new ArrayList<>(); */
		AjaxJson j = new AjaxJson();
		String year = getParam(request, "year");
		String fileName = "";
		try {
			// fileName = java.net.URLEncoder.encode("报名统计表", "ISO8859_1");
			fileName = new String(("(三亚市)房间出租率汇总").getBytes(), "ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel;charset=ISO8859_1");
		response.setHeader("content-disposition", "attachment;filename="
				+ fileName + ".xls");
		OutputStream fOut = null;
		try {
			fOut = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查询今年的数据
		String sql = "SELECT  th.`year` AS year,th.`month` AS month,SUM(th.rent_num) AS rent,SUM(th.actual_rentnum) AS real,(SUM(th.rent_num)/SUM(th.actual_rentnum)) AS ratio "
				+ "FROM t_hotelmonthly th WHERE 1=1  ";

		if (StringUtil.isNotEmpty(year)) {
			Integer tempyear = Integer.parseInt(year) - 1;
			String lastyear = String.valueOf(tempyear);
			sql += " AND th.`year`=" + year + " GROUP BY th.`month`";

		} else {
			/*
			 * sql=
			 * "SELECT th.`year` AS year,th.`month` AS month,SUM(th.rent_num) AS rent,SUM(th.actual_rentnum) AS real,(SUM(th.rent_num)/SUM(th.actual_rentnum)) AS ratio "
			 * +
			 * "FROM t_hotelmonthly  th WHERE th.`year`=2015 GROUP BY th.`month`"
			 * ;
			 */
			sql = "SELECT th.`month` AS month,SUM(th.rent_num) AS rent,SUM(th.actual_rentnum) AS real,(SUM(th.rent_num)/SUM(th.actual_rentnum)) AS ratio "
					+ "FROM t_hotelmonthly  th  GROUP BY th.`month`";

		}
		List<Object> list = this.systemService.findListbySql(sql);

		if (list == null || list.size() == 0) {
			message = "没有符合条件的数据";
			j.setMsg(message);

		} else {
			// 导出符合条件的数据
			/*
			 * String[] fields = {"year","month","real","rent","ratio"};
			 * String[] names = {"年份","月份","实际出租间夜数","可供出租间夜数","出租率(%)"};
			 */
			String[] fields = { "month", "real", "rent", "ratio" };
			String[] names = { "月份", "实际出租间夜数", "可供出租间夜数", "出租率(%)" };
			Workbook workbook;
			try {
				workbook = ExcelExportUtils.getInstance().inExcel(list, fields,
						names);
				workbook.write(fOut);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;

	}

	/*
	 * //旅行社国内游接待人天情况表(按旅行社)
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping(params = "exportsxls2") public String
	 * exportXls2(HttpServletRequest request,HttpServletResponse response ,
	 * ModelMap map) throws Exception {
	 * 
	 * +"IFNULL(SUM(ta.year_depreciation),0) AS yearDepreciation,"
	 * +"IFNULL(SUM(ta.asset_total),0) AS assetTotal,"
	 * +"IFNULL(SUM(ta.liabilities_total),0) AS liabilitiesTotal,"
	 * +"IFNULL(SUM(ta.possessor),0) AS possessor,"
	 * +"IFNULL(SUM(ta.real_income),0) AS realIncome,"
	 * +"IFNULL(SUM(ta.business_income),0) AS businessIncome,"
	 * +"IFNULL(SUM(ta.into_income),0) AS intoIncome,"
	 * +"IFNULL(SUM(ta.out_income),0) AS outIncome,"
	 * +"IFNULL(SUM(ta.in_travel_income),0) AS inTravelIncome,"
	 * +"IFNULL(SUM(ta.extra_gains),0) AS extraGains,"
	 * +"IFNULL(SUM(ta.government_subsidies),0) AS governmentSubsidies,"
	 * +"IFNULL(SUM(ta.cost),0) AS cost,"
	 * +"IFNULL(SUM(ta.business_expenses),0) AS businessExpenses,"
	 * +"IFNULL(SUM(ta.main_operation),0) AS mainOperation,"
	 * +"IFNULL(SUM(ta.profit_in_travel),0) AS profitInTravel,"
	 * +"IFNULL(SUM(ta.out_travel_business),0) AS outTravelBusiness,"
	 * +"IFNULL(SUM(ta.manage_cost),0) AS manageCost,"
	 * +"IFNULL(SUM(ta.taxes),0) AS taxes,"
	 * +"IFNULL(SUM(ta.business_tex_add),0) AS businessTexAdd,"
	 * +"IFNULL(SUM(ta.travel_expense),0) AS travelExpense,"
	 * +"IFNULL(SUM(ta.financial_cost),0) AS financialCost,"
	 * +"IFNULL(SUM(ta.interest_cost),0) AS interestCost,"
	 * +"IFNULL(SUM(ta.selling_expense),0) AS sellingExpense,"
	 * +"IFNULL(SUM(ta.value_variation),0) AS valueVariation,"
	 * +"IFNULL(SUM(ta.operating_income),0) AS operatingIncome,"
	 * +"IFNULL(SUM(ta.gross),0) AS gross,"
	 * +"IFNULL(SUM(ta.invest),0) AS invest,"
	 * +"IFNULL(SUM(ta.total_return),0) AS totalReturn,"
	 * +"IFNULL(SUM(ta.income_tax),0) AS incomeTax,"
	 * +"IFNULL(SUM(ta.employee_salary),0) AS employeeSalary,"
	 * +"IFNULL(SUM(ta.add_tex),0) AS addTex,"
	 * +"IFNULL(SUM(ta.num_average),0) AS numAverage,"
	 * +"IFNULL(SUM(ta.college),0) AS college,"
	 * +"IFNULL(SUM(ta.tour_num),0) AS tourNum,"
	 * +"IFNULL(SUM(ta.store_num),0) AS storeNum,"
	 * +"IFNULL(SUM(ta.branch_office),0) AS branchOffice,"
	 * +"IFNULL(SUM(ta.subsidiary),0) AS subsidiary "
	 * +"FROM  t_travelAgency_annual ta "
	 * 
	 * +"WHERE 1=1 " ; if (StringUtil.isNotEmpty(getParam(request,"year"))) {
	 * sql += " and  ta.`year`="+getParam(request,"year")+"  "; }else{ sql +=
	 * " and  ta.`year` = '2014' "; }
	 * 
	 * 
	 * 
	 * 
	 * OutputStream os = response.getOutputStream(); // byte[] buffer = new
	 * byte[1024]; // BufferedInputStream bis = new
	 * BufferedInputStream(this.replaceExcel(fs, wb, sheet, sheetName, row,
	 * modelPath, param)); // int i = bis.read(buffer); // while (i != -1) { //
	 * os.write(buffer, 0, i); // i = bis.read(buffer); // } String
	 * filename="旅行社财务汇总统计.xls"; if
	 * (StringUtil.isNotEmpty(getParam(request,"year"))) { filename
	 * =(getParam(request,"year")+filename); }
	 * 
	 * //response.setContentType("text/html;charset=UTF-8");
	 * response.setContentType("application/force-download");// 设置强制下载不打开
	 * 
	 * response.addHeader("Content-Disposition",
	 * "attachment;fileName="+URLEncoder.encode(filename,"UTF-8"));// 设置文件名
	 * Map<String, Object> parmass = this.systemService.findOneForJdbc(sql,
	 * null);
	 * 
	 * 
	 * HSSFWorkbook wb =POIUtils.replaceExcel("Sheet1",
	 * "/com/zzc/web/export/model/财务状况汇总统计1.xls", parmass); wb.write(os); return
	 * null;
	 * 
	 * }
	 */

}
