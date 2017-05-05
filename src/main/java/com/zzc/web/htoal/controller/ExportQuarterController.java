package com.zzc.web.htoal.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.util.StringUtil;
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.htoal.entity.HotelQuarter;
import com.zzc.web.scenicmanage.util.ExcelExportUtils;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.sylyUtils.WeekUtils;
import com.zzc.web.system.service.SystemService;
@RequestMapping("/exportQuarterController")
@Controller
public class ExportQuarterController extends BaseController {
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	private String getParam(HttpServletRequest request, String string) {
		return (String)request.getParameter(string);
	}
	//统计下载页面
	@RequestMapping(params = "toexports")
	public ModelAndView toexports(HttpServletRequest request) {
		List<String> yearList = this.systemService.findListbySql("SELECT distinct th.`year` AS year FROM  t_hotel_quarterly th");		
		request.setAttribute("yearList", yearList);
		return new ModelAndView("hotelStaticQuarter/hotelQuarter");
	}
	@RequestMapping(params = "toexports1")
	public ModelAndView toexports1(HttpServletRequest request) {
		List<String> yearList = this.systemService.findListbySql("SELECT distinct th.`year` AS year FROM  t_hotel_quarterly th");		
		request.setAttribute("yearList", yearList);
		return new ModelAndView("hotelStaticQuarter/hotelQuarter1");
	}
	//星级饭店重要指标同比统计(指标排序表)
	@RequestMapping(params = "exports3")
	public void exports3(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
         
        // 获取参数 
         
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
        String quarter= new String(request.getParameter("quarter").getBytes("iso8859-1"),"utf-8");
        String lastYear=String.valueOf(Integer.valueOf(year)-1);
        String star=request.getParameter("star");
        String excelName ="星级饭店重要指标同比统计(指标排序表).xls";
        String modelPath ="/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        // 组装查询条件
         
        sql.append(" SELECT ");
        sql.append(" 	a.code1 code1, ");
        sql.append(" 	a.name1 name1, ");
        sql.append(" 	a.avgprice1 avgprice, ");
        sql.append(" 	a.avgrates1 avgrates, ");
        sql.append(" 	a.avgcanprice1 avgcanprice, ");
        sql.append(" 	a.avgcanincome1 avgcanincome, ");
        sql.append(" 	a.totalIncome1 totalIncome, ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		TRUNCATE ( ");
        sql.append(" 			(a.avgprice1 - a.avgprice2) / a.avgprice2, ");
        sql.append(" 			2 ");
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	) tbavgprice, ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		TRUNCATE ( ");
        sql.append(" 			(a.avgrates1 - a.avgrates2) / a.avgrates2, ");
        sql.append(" 			2 ");
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	) tbavgrates, ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		TRUNCATE ( ");
        sql.append(" 			( ");
        sql.append(" 				a.avgcanprice1 - a.avgcanprice2 ");
        sql.append(" 			) / a.avgcanprice2, ");
        sql.append(" 			2 ");
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	) tbavgcanprice, ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		TRUNCATE ( ");
        sql.append(" 			( ");
        sql.append(" 				a.avgcanincome1 - a.avgcanincome2 ");
        sql.append(" 			) / a.avgcanincome2, ");
        sql.append(" 			2 ");
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	) tbavgcanincome, ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		TRUNCATE ( ");
        sql.append(" 			( ");
        sql.append(" 				a.totalIncome1 - a.totalIncome2 ");
        sql.append(" 			) / a.totalIncome2, ");
        sql.append(" 			2 ");
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	) tbtotalIncome ");
        sql.append(" FROM ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			t. CODE code1, ");
        sql.append(" 			t.unitname name1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				TRUNCATE ( ");
        sql.append(" 					sum(q.roomIncome) / sum(q.realNights), ");
        sql.append(" 					4 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) avgprice1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				TRUNCATE ( ");
        sql.append(" 					sum(q.realNights) / sum(q.canNights) * 100, ");
        sql.append(" 					4 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) avgrates1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				TRUNCATE ( ");
        sql.append(" 					sum(q.roomIncome) / sum(q.canNights), ");
        sql.append(" 					4 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) avgcanprice1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				TRUNCATE ( ");
        sql.append(" 					sum(q.totalIncome) / sum(q.roomnum), ");
        sql.append(" 					4 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) avgcanincome1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				TRUNCATE (q.totalIncome, 2), ");
        sql.append(" 				0 ");
        sql.append(" 			) totalIncome1, ");
        sql.append(" 			( ");
        sql.append(" 				SELECT ");
        sql.append(" 					IFNULL( ");
        sql.append(" 						sum(q1.roomIncome) / sum(q1.realNights), ");
        sql.append(" 						0 ");
        sql.append(" 					) ");
        sql.append(" 				FROM ");
        sql.append(" 					t_hotel_quarterly q1 ");
        sql.append(" 				WHERE ");
        sql.append(" 					q1.hotel_qid = t.ID ");
        sql.append(" 				AND q1.`year` = "+lastYear);
        sql.append(" 				AND q.`quarter` =  "+quarter);
        sql.append(" 			) avgprice2, ");
        sql.append(" 			( ");
        sql.append(" 				SELECT ");
        sql.append(" 					IFNULL( ");
        sql.append(" 						sum(q1.realNights) / sum(q1.canNights), ");
        sql.append(" 						0 ");
        sql.append(" 					) ");
        sql.append(" 				FROM ");
        sql.append(" 					t_hotel_quarterly q1 ");
        sql.append(" 				WHERE ");
        sql.append(" 					q1.hotel_qid = t.ID ");
        sql.append(" 				AND q1.`year` = "+lastYear);
        sql.append(" 				AND q.`quarter` =  "+quarter);
        sql.append(" 			) avgrates2, ");
        sql.append(" 			( ");
        sql.append(" 				SELECT ");
        sql.append(" 					IFNULL( ");
        sql.append(" 						sum(q1.roomIncome) / sum(q1.canNights), ");
        sql.append(" 						0 ");
        sql.append(" 					) ");
        sql.append(" 				FROM ");
        sql.append(" 					t_hotel_quarterly q1 ");
        sql.append(" 				WHERE ");
        sql.append(" 					q1.hotel_qid = t.ID ");
        sql.append(" 				AND q1.`year` = "+lastYear);
        sql.append(" 				AND q.`quarter` =  "+quarter);
        sql.append(" 			) avgcanprice2, ");
        sql.append(" 			( ");
        sql.append(" 				SELECT ");
        sql.append(" 					IFNULL( ");
        sql.append(" 						sum(q1.totalIncome) / sum(q1.roomnum), ");
        sql.append(" 						0 ");
        sql.append(" 					) ");
        sql.append(" 				FROM ");
        sql.append(" 					t_hotel_quarterly q1 ");
        sql.append(" 				WHERE ");
        sql.append(" 					q1.hotel_qid = t.ID ");
        sql.append(" 				AND q1.`year` = "+lastYear);
        sql.append(" 				AND q.`quarter` =  "+quarter);
        sql.append(" 			) avgcanincome2, ");
        sql.append(" 			( ");
        sql.append(" 				SELECT ");
        sql.append(" 					IFNULL(q1.totalIncome, 0) ");
        sql.append(" 				FROM ");
        sql.append(" 					t_hotel_quarterly q1 ");
        sql.append(" 				WHERE ");
        sql.append(" 					q1.hotel_qid = t.ID ");
        sql.append(" 				AND q1.`year` = "+lastYear);
        sql.append(" 				AND q.`quarter` =  "+quarter);
        sql.append(" 			) totalIncome2 ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotel_quarterly q ");
        sql.append(" 		LEFT JOIN t_hotelmanage t ON t.ID = q.hotel_qid ");
        sql.append(" 		WHERE ");
        sql.append(" 			q.`year` = "+year);
        sql.append(" 		AND q.`quarter` = "+quarter);
        if(star==null||star.length()==0){
            sql.append("	 and t.housegrade in(4,5,6,7,8) ");
        }else{
        	sql.append("  and t.housegrade = "+star );
        }

        sql.append(" 		GROUP BY ");
        sql.append(" 			t.`code` ");
        sql.append(" 	) a ");
        ExportService emds = new ExportService(2,12); 
        
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
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName, "UTF-8"));

	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
        
	
        }
	//星级饭店经营情况统计表（按审核通过的饭店分）
	@RequestMapping(params = "exports2")
	public void exports2(HttpServletRequest request, HttpServletResponse response,ModelMap map) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "星级酒店季报经营情况统计表(按审核通过的饭店分).xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        ExportService emds = new ExportService(2,12);
		StringBuffer sql=new StringBuffer();
		String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
        String quarter = new String(request.getParameter("quarter").getBytes("iso8859-1"),"utf-8");
        String star = new String(request.getParameter("star").getBytes("iso8859-1"),"utf-8");
        sql.append(" SELECT ");
        sql.append(" 	t.code, ");
        sql.append(" 	t.unitname, ");
        sql.append(" 	t.f_man, ");
        sql.append(" 	sum(q.totalIncome), ");
        sql.append(" 	sum(q.roomIncome), ");
        sql.append(" 	sum(q.canteenIncome), ");
        sql.append(" 	sum(q.otherIncome), ");
        sql.append(" 	sum(q.roomnum), ");
        sql.append(" 	sum(q.bednum), ");
        sql.append(" 	sum(q.realNights), ");
        sql.append(" 	sum(q.canNights), ");
        sql.append(" 	if(t.housegrade='4','一星级',if(t.housegrade='5','二星级',if(t.housegrade='6','三星级',if(t.housegrade='7','四星级',if(t.housegrade='8','五星级',''))))) ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmanage t ");
        sql.append(" LEFT JOIN t_hotel_quarterly q ON t.ID = q.hotel_qid ");
        sql.append(" WHERE q.`year`= "+year+" and q.`quarter`="+quarter);
        if(star==null||star.length()==0){
        	sql.append(" and t.housegrade in(4,5,6,7,8) ");
        }else{
        	sql.append(" and t.housegrade = "+star);
        }
        
        sql.append(" GROUP BY t.code ");
        sql.append("  ");
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
						+ new String((year+"第"+quarter+"季度星级饭店经营情况统计表（同环比）.xls").getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+"第"+quarter+"季度星级饭店经营情况统计表（同环比）.xls", "UTF-8"));

	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
        
    	
	}
		@RequestMapping(params = "exports1")
		public void exports1(HttpServletRequest request,HttpServletResponse response) throws Exception{
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        // 获取要查询的年
	        String year = request.getParameter("year");
	        String star = request.getParameter("star");
	        //环比的年份
	        String hYear=year;
	        // 获取要查询的季度
	        String quarter = request.getParameter("quarter");
	        String lastQuarter;
	        
	        Calendar calendar = Calendar.getInstance();
	        //如果为空,转换为当前年份
	        if(year == null || year.length() == 0) {
	        	year = String.valueOf(calendar.get(Calendar.YEAR));
	        	hYear=year;
	        }
	        if(quarter == null || quarter.length() == 0) quarter = "2";
	        if(quarter=="1"){
	        	lastQuarter="4";
	        	hYear=String.valueOf(Integer.parseInt(year) - 1);
	        }else{
	        	lastQuarter=String.valueOf((Integer.parseInt(quarter)-1));
	        }
	        String lastYear = String.valueOf(Integer.parseInt(year) - 1);
	        String excelName = "指标统计合.xls";
	        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
	        StringBuffer sql = new StringBuffer();
	        sql.append(" SELECT ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.totalIncome - tb.totalIncome ");
	        sql.append(" 			) / tb.totalIncome * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbtoalIncome, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.totalIncome - hb.totalIncome ");
	        sql.append(" 			) / hb.totalIncome * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbtoalIncome, ");
	        sql.append(" 	TRUNCATE (thisquarter.totalIncome, 3) thtotalIncome, ");
	        sql.append(" 	TRUNCATE (allq.totalIncome, 3) alltotalIncome, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.roomIncome - tb.roomIncome ");
	        sql.append(" 			) / tb.roomIncome * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbroomIncome, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.roomIncome - hb.roomIncome ");
	        sql.append(" 			) / hb.roomIncome * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbroomIncome, ");
	        sql.append(" 	TRUNCATE (thisquarter.roomIncome, 3) throomIncome, ");
	        sql.append(" 	TRUNCATE (allq.roomIncome, 3) allroomIncome, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.canteenIncome - tb.canteenIncome ");
	        sql.append(" 			) / tb.canteenIncome * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbcanteenIncome, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.canteenIncome - hb.canteenIncome ");
	        sql.append(" 			) / hb.canteenIncome * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbcanteenIncome, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		thisquarter.canteenIncome, ");
	        sql.append(" 		3 ");
	        sql.append(" 	) thcanteenIncome, ");
	        sql.append(" 	TRUNCATE (allq.canteenIncome, 3) allcanteenIncome, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.roomnum - tb.roomnum ");
	        sql.append(" 			) / tb.roomnum * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbroomnum, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.roomnum - hb.roomnum ");
	        sql.append(" 			) / hb.roomnum * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbroomnum, ");
	        sql.append(" 	TRUNCATE (thisquarter.roomnum, 3) throomnum, ");
	        sql.append(" 	TRUNCATE (allq.roomnum, 3) allroomnum, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.bednum - tb.bednum ");
	        sql.append(" 			) / tb.bednum * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbbednum, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.bednum - hb.bednum ");
	        sql.append(" 			) / hb.bednum * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbbednum, ");
	        sql.append(" 	TRUNCATE (thisquarter.bednum, 3) thbednum, ");
	        sql.append(" 	TRUNCATE (allq.bednum, 3) allbednum, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.canNights - tb.canNights ");
	        sql.append(" 			) / tb.canNights * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbcanNights, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.canNights - hb.canNights ");
	        sql.append(" 			) / hb.canNights * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbcanNights, ");
	        sql.append(" 	TRUNCATE (thisquarter.canNights, 3) thcanNights, ");
	        sql.append(" 	TRUNCATE (allq.canNights, 3) allcanNights, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.realNights - tb.realNights ");
	        sql.append(" 			) / tb.realNights * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbrealNights, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.realNights - hb.realNights ");
	        sql.append(" 			) / hb.realNights * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbrealNights, ");
	        sql.append(" 	TRUNCATE (thisquarter.realNights, 3) threalNights, ");
	        sql.append(" 	TRUNCATE (allq.realNights, 3) allrealNights, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.avgprice - tb.avgprice ");
	        sql.append(" 			) / tb.avgprice * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbavgprice, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.avgprice - hb.avgprice ");
	        sql.append(" 			) / hb.avgprice * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbavgprice, ");
	        sql.append(" 	TRUNCATE (thisquarter.avgprice, 3) thavgprice, ");
	        sql.append(" 	TRUNCATE (allq.avgprice, 3) allavgprice, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.avgrates - tb.avgrates ");
	        sql.append(" 			) / tb.avgrates * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbavgrates, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.avgrates - hb.avgrates ");
	        sql.append(" 			) / hb.avgrates * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbavgrates, ");
	        sql.append(" 	TRUNCATE (thisquarter.avgrates, 3) thavgrates, ");
	        sql.append(" 	TRUNCATE (allq.avgrates, 3) allavgrates, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.avgcanprice - tb.avgcanprice ");
	        sql.append(" 			) / tb.avgcanprice * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbavgcanprice, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.avgcanprice - hb.avgcanprice ");
	        sql.append(" 			) / hb.avgcanprice * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbavgcanprice, ");
	        sql.append(" 	TRUNCATE (thisquarter.avgcanprice, 3) thavgcanprice, ");
	        sql.append(" 	TRUNCATE (allq.avgcanprice, 3) allavgcanprice, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.avgincome - tb.avgincome ");
	        sql.append(" 			) / tb.avgincome * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) tbavgincome, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			( ");
	        sql.append(" 				thisquarter.avgincome - hb.avgincome ");
	        sql.append(" 			) / hb.avgincome * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		4 ");
	        sql.append(" 	) hbavgincome, ");
	        sql.append(" 	TRUNCATE (thisquarter.avgincome, 3) thavgincome, ");
	        sql.append(" 	TRUNCATE (allq.avgincome, 3) allavgincome ");
	        sql.append(" FROM ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.totalIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) totalIncome, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.roomIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) roomIncome, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.canteenIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) canteenIncome, ");
	        sql.append(" 			TRUNCATE (IFNULL(sum(t.roomnum), 0), 2) roomnum, ");
	        sql.append(" 			TRUNCATE (IFNULL(sum(t.bednum), 0), 2) bednum, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.canNights), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) canNights, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.realNights), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) realNights, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.roomIncome, 0)) / sum(IFNULL(t.realNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgprice, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.realNights, 0)) / sum(IFNULL(t.canNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgrates, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.roomIncome, 0)) / sum(IFNULL(t.canNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgcanprice, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.totalIncome, 0)) / sum(IFNULL(t.roomnum, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgincome ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly t  LEFT JOIN t_hotelmanage h ON t.hotel_qid=h.ID ");
	        if(star==null||star.length()==0){
	        	  sql.append(" 		WHERE h.housegrade in(4,5,6,7,8) and  ");
	        }else{
	        	  sql.append(" 		WHERE h.housegrade ="+star+" and  ");
	        }
	      
	        sql.append(" 			t.`year` =  "+year);
	        sql.append(" 		AND t.`quarter` =  "+quarter);
	        sql.append(" 	) thisquarter, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.totalIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) totalIncome, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.roomIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) roomIncome, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.canteenIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) canteenIncome, ");
	        sql.append(" 			TRUNCATE (IFNULL(sum(t.roomnum), 0), 2) roomnum, ");
	        sql.append(" 			TRUNCATE (IFNULL(sum(t.bednum), 0), 2) bednum, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.canNights), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) canNights, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.realNights), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) realNights, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.roomIncome, 0)) / sum(IFNULL(t.realNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgprice, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.realNights, 0)) / sum(IFNULL(t.canNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgrates, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.roomIncome, 0)) / sum(IFNULL(t.canNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgcanprice, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.totalIncome, 0)) / sum(IFNULL(t.roomnum, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgincome ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly t  LEFT JOIN t_hotelmanage h ON t.hotel_qid=h.ID ");
	        if(star==null||star.length()==0){
	        	  sql.append(" 		WHERE h.housegrade in(4,5,6,7,8) and  ");
	        }else{
	        	  sql.append(" 		WHERE h.housegrade ="+star+" and  ");
	        }
	        sql.append(" 			t.`year` = "+hYear);
	        sql.append(" 		AND t.`quarter` = "+lastQuarter);
	        sql.append(" 	) tb, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.totalIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) totalIncome, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.roomIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) roomIncome, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.canteenIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) canteenIncome, ");
	        sql.append(" 			TRUNCATE (IFNULL(sum(t.roomnum), 0), 2) roomnum, ");
	        sql.append(" 			TRUNCATE (IFNULL(sum(t.bednum), 0), 2) bednum, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.canNights), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) canNights, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.realNights), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) realNights, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.roomIncome, 0)) / sum(IFNULL(t.realNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgprice, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.realNights, 0)) / sum(IFNULL(t.canNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgrates, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.roomIncome, 0)) / sum(IFNULL(t.canNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgcanprice, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.totalIncome, 0)) / sum(IFNULL(t.roomnum, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgincome ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly t  LEFT JOIN t_hotelmanage h ON t.hotel_qid=h.ID ");
	        if(star==null||star.length()==0){
	        	  sql.append(" 		WHERE h.housegrade in(4,5,6,7,8) and  ");
	        }else{
	        	  sql.append(" 		WHERE h.housegrade ="+star+" and  ");
	        }
	        sql.append(" 			t.`year` = "+lastYear);
	        sql.append(" 		AND t.`quarter` = "+quarter);
	        sql.append(" 	) hb, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.totalIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) totalIncome, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.roomIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) roomIncome, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.canteenIncome), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) canteenIncome, ");
	        sql.append(" 			TRUNCATE (IFNULL(sum(t.roomnum), 0), 2) roomnum, ");
	        sql.append(" 			TRUNCATE (IFNULL(sum(t.bednum), 0), 2) bednum, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.canNights), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) canNights, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL(sum(t.realNights), 0), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) realNights, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.roomIncome, 0)) / sum(IFNULL(t.realNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgprice, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.realNights, 0)) / sum(IFNULL(t.canNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgrates, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.roomIncome, 0)) / sum(IFNULL(t.canNights, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgcanprice, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				IFNULL( ");
	        sql.append(" 					sum(IFNULL(t.totalIncome, 0)) / sum(IFNULL(t.roomnum, 0)), ");
	        sql.append(" 					0 ");
	        sql.append(" 				), ");
	        sql.append(" 				4 ");
	        sql.append(" 			) avgincome ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly t  LEFT JOIN t_hotelmanage h ON t.hotel_qid=h.ID ");
	        if(star==null||star.length()==0){
	        	  sql.append(" 		WHERE h.housegrade in(4,5,6,7,8) and  ");
	        }else{
	        	  sql.append(" 		WHERE h.housegrade ="+star+" and  ");
	        }
	        sql.append(" 			t.`year` = "+year);
	        sql.append(" 		AND t.`quarter` <= "+quarter);
	        sql.append(" 	) allq ");
	OutputStream os = response.getOutputStream();
	//response.setContentType("text/html;charset=UTF-8");
	response.setContentType("application/force-download");// 设置强制下载不打开
	
	response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode("指标统计.xls","UTF-8"));// 设置文件名
	Map<String, Object> parmass = this.systemService.findOneForJdbc(sql.toString(), null);

	
	HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  modelPath, parmass);
	wb.write(os);
           	
    	
	}
	/**
	 * 
	 * @date: 2017年2月7日
	 * @Author: long
	 * @Email: 
	 * @param: 
	 * @return:
	 * 导出出租率环比同比汇总统计
	 */
	@RequestMapping(params = "exportsxls1")
	public void exportsXls1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取要查询的年
        String year = request.getParameter("year");
        //环比的年份
        String hYear=year;
        // 获取要查询的月份
        String quarter = request.getParameter("quarter");
        String lastQuarter;
        
        Calendar calendar = Calendar.getInstance();
        //如果为空,转换为当前年份
        if(year == null || year.length() == 0) {
        	year = String.valueOf(calendar.get(Calendar.YEAR));
        	hYear=year;
        }
        if(quarter == null || quarter.length() == 0) quarter = "2";
        if(quarter=="1"){
        	lastQuarter="4";
        	hYear=String.valueOf(Integer.parseInt(year) - 1);
        }else{
        	lastQuarter=String.valueOf((Integer.parseInt(quarter)-1));
        }
        String lastYear = String.valueOf(Integer.parseInt(year) - 1);
       // = Integer.parseInt(quarter)
        String excelName = "出租率环比同比汇总统计.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        //ExportService emds = new ExportService(19); // 数字代表excel表格的列数,只导出1列时会有bug,暂时不做处理
        sql.append(" SELECT ");
        sql.append(" 	'三亚市' sanya, ");
        sql.append(" 	tt.pencent pencent1, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt.pencent - tt.huanbi) / tt.huanbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) huanbi1, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt.pencent - tt.tongbi)/ tt.tongbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) tongbi1, ");
        sql.append(" 	tt2.pencent pencent2, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt2.pencent - tt2.huanbi) / tt2.huanbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) huanbi2, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt2.pencent - tt2.tongbi) / tt2.tongbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) tongbi2, ");
        sql.append(" 	tt3.pencent pencent3, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt3.pencent - tt3.huanbi) / tt3.huanbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) huanbi3, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt3.pencent - tt3.tongbi) / tt3.tongbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) tongbi3, ");
        sql.append(" 	tt4.pencent pencent4, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt4.pencent - tt4.huanbi) / tt4.huanbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) huanbi4, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt4.pencent - tt4.tongbi) / tt4.tongbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) tongbi4, ");
        sql.append(" 	tt5.pencent pencent5, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt5.pencent - tt5.huanbi) / tt5.huanbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) huanbi5, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt5.pencent - tt5.tongbi) / tt5.tongbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) tongbi5, ");
        sql.append(" 	tt6.pencent pencent6, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt6.pencent - tt6.huanbi) / tt6.huanbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) huanbi6, ");
        sql.append(" 	TRUNCATE(IFNULL( ");
        sql.append(" 		(tt6.pencent - tt6.tongbi) / tt6.tongbi*100, ");
        sql.append(" 		0 ");
        sql.append(" 	),2) tongbi6 ");


sql.append("FROM ");
	sql.append("( ");
		sql.append("SELECT ");
			sql.append("t.housegrade, ");
			sql.append("TRUNCATE ( ");
				sql.append("( ");
					sql.append("IFNULL( ");
						sql.append("( ");
							sql.append("sum(th.realNights) / sum(th.canNights) ");
						sql.append(") * 100, ");
						sql.append("0 ");
					sql.append(") ");
				sql.append("), ");
				sql.append("2 ");
			sql.append(") pencent, ");
			sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '4' ");
				sql.append("AND th.`year` = "+lastYear+" ");
				sql.append("AND th.`quarter` = "+quarter+" ");
			sql.append(") tongbi, ");
	sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '4' ");
				sql.append("AND th.`year` = "+hYear+" ");
				sql.append("AND th.`quarter` = "+lastQuarter+" ");
			sql.append(") huanbi ");
		sql.append("FROM ");
			sql.append("t_hotel_quarterly th ");
		sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append("WHERE ");
			sql.append("t.housegrade = '4' ");
		sql.append("AND th.`year` = "+year+" ");
		sql.append("AND th.`quarter` = "+quarter+" ");
	sql.append(") tt, ");
	
	
	//sql.append("FROM ");
	sql.append("( ");
		sql.append("SELECT ");
			sql.append("t.housegrade, ");
			sql.append("TRUNCATE ( ");
				sql.append("( ");
					sql.append("IFNULL( ");
						sql.append("( ");
							sql.append("sum(th.realNights) / sum(th.canNights) ");
						sql.append(") * 100, ");
						sql.append("0 ");
					sql.append(") ");
				sql.append("), ");
				sql.append("2 ");
			sql.append(") pencent, ");
			sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '5' ");
				sql.append("AND th.`year` = "+lastYear+" ");
				sql.append("AND th.`quarter` = "+quarter+" ");
			sql.append(") tongbi, ");
	sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '5' ");
				sql.append("AND th.`year` = "+hYear+" ");
				sql.append("AND th.`quarter` ="+lastQuarter+" "); 
			sql.append(") huanbi ");
		sql.append("FROM ");
			sql.append("t_hotel_quarterly th ");
		sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append("WHERE ");
			sql.append("t.housegrade = '5' ");
		sql.append("AND th.`year` = "+year+" ");
		sql.append("AND th.`quarter` = "+quarter+" ");
	sql.append(") tt2, ");
	
	//sql.append("FROM ");
	sql.append("( ");
		sql.append("SELECT ");
			sql.append("t.housegrade, ");
			sql.append("TRUNCATE ( ");
				sql.append("( ");
					sql.append("IFNULL( ");
						sql.append("( ");
							sql.append("sum(th.realNights) / sum(th.canNights)");
						sql.append(") * 100,");
						sql.append("0");
					sql.append(")");
				sql.append("),");
				sql.append("2");
			sql.append(") pencent,");
			sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE (");
						sql.append("(");
							sql.append("IFNULL(");
								sql.append("(");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '6' ");
				sql.append("AND th.`year` = "+lastYear+" ");
				sql.append("AND th.`quarter` = "+quarter+" ");
			sql.append(") tongbi, ");
	sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '6' ");
				sql.append("AND th.`year` = "+hYear+" "); 
				sql.append("AND th.`quarter` = "+lastQuarter+" ");
			sql.append(") huanbi ");
		sql.append("FROM ");
			sql.append("t_hotel_quarterly th ");
		sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append("WHERE ");
			sql.append("t.housegrade = '6' ");
		sql.append("AND th.`year` = "+year+" ");
		sql.append("AND th.`quarter` = "+quarter+" ");
	sql.append(") tt3, ");
	
	
	//sql.append("FROM ");
	sql.append("( ");
		sql.append("SELECT ");
			sql.append("t.housegrade, ");
			sql.append("TRUNCATE ( ");
				sql.append("( ");
					sql.append("IFNULL( ");
						sql.append("( ");
							sql.append("sum(th.realNights) / sum(th.canNights) ");
						sql.append(") * 100, ");
						sql.append("0 ");
					sql.append(") ");
				sql.append("), ");
				sql.append("2 ");
			sql.append(") pencent, ");
			sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '7' ");
				sql.append("AND th.`year` = "+lastYear+" ");
				sql.append("AND th.`quarter` = "+quarter+" "); 
			sql.append(") tongbi, ");
	sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '7' ");
				sql.append("AND th.`year` = "+hYear+" ");
				sql.append("AND th.`quarter` = "+lastQuarter+" ");
			sql.append(") huanbi ");
		sql.append("FROM ");
			sql.append("t_hotel_quarterly th ");
		sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append("WHERE ");
			sql.append("t.housegrade = '7' ");
		sql.append("AND th.`year` = "+year+" ");
		sql.append("AND th.`quarter` = "+quarter+" ");
	sql.append(") tt4, ");
	
	//sql.append("FROM ");
	sql.append("( ");
		sql.append("SELECT ");
			sql.append("t.housegrade, ");
			sql.append("TRUNCATE ( ");
				sql.append("( ");
					sql.append("IFNULL( ");
						sql.append("( ");
							sql.append("sum(th.realNights) / sum(th.canNights) ");
						sql.append(") * 100, ");
						sql.append("0 ");
					sql.append(") ");
				sql.append("), ");
				sql.append("2 ");
			sql.append(") pencent, ");
			sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '8' ");
				sql.append("AND th.`year` = "+lastYear+" ");
				sql.append("AND th.`quarter` = "+quarter+" ");
			sql.append(") tongbi, ");
	sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade = '8' ");
				sql.append("AND th.`year` = "+hYear+" ");
				sql.append("AND th.`quarter` ="+lastQuarter+" ");
			sql.append(") huanbi ");
		sql.append("FROM ");
			sql.append("t_hotel_quarterly th ");
		sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append("WHERE ");
			sql.append("t.housegrade = '8' ");
		sql.append("AND th.`year` = "+year+" ");
		sql.append("AND th.`quarter` = "+quarter+" ");
	sql.append(") tt5, ");
	
	
	//sql.append("FROM ");
	sql.append("( ");
		sql.append("SELECT ");
			sql.append("t.housegrade, ");
			sql.append("TRUNCATE ( ");
				sql.append("( ");
					sql.append("IFNULL( ");
						sql.append("( ");
							sql.append("sum(th.realNights) / sum(th.canNights) ");
						sql.append(") * 100, ");
						sql.append("0 ");
					sql.append(") ");
				sql.append("), ");
				sql.append("2 ");
			sql.append(") pencent, ");
			sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade in(4,5,6,7,8) ");
				sql.append("AND th.`year` = "+lastYear+" ");
				sql.append("AND th.`quarter` = "+quarter+" ");
			sql.append(") tongbi, ");
	sql.append("( ");
				sql.append("SELECT ");
					sql.append("TRUNCATE ( ");
						sql.append("( ");
							sql.append("IFNULL( ");
								sql.append("( ");
									sql.append("sum(th.realNights) / sum(th.canNights) ");
								sql.append(") * 100, ");
								sql.append("0 ");
							sql.append(") ");
						sql.append("), ");
						sql.append("2 ");
					sql.append(") pencent1 ");
				sql.append("FROM ");
					sql.append("t_hotel_quarterly th ");
				sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
				sql.append("WHERE ");
					sql.append("t.housegrade in(4,5,6,7,8) ");
				sql.append("AND th.`year` = "+hYear+" ");
				sql.append("AND th.`quarter` = "+lastQuarter+" ");
			sql.append(") huanbi ");
		sql.append("FROM ");
			sql.append("t_hotel_quarterly th ");
		sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append("WHERE ");
			sql.append("t.housegrade in(4,5,4,7,8) ");
		sql.append("AND th.`year` = "+year+" ");
		sql.append("AND th.`quarter` = "+quarter+" ");
	sql.append(") tt6 ");
	OutputStream os = response.getOutputStream();
	//response.setContentType("text/html;charset=UTF-8");
	response.setContentType("application/force-download");// 设置强制下载不打开
	
	response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode("出租率环比同比汇总统计.xls","UTF-8"));// 设置文件名
	Map<String, Object> parmass = this.systemService.findOneForJdbc(sql.toString(), null);

	
	HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  modelPath, parmass);
	wb.write(os);
           	
    	
	}
	 /* @param: 
		 * @return:
		 * 房价环比同比汇总统计（excel表）
		 */
		@RequestMapping(params = "exportsxls6")
		public void exportsXls6(HttpServletRequest request,HttpServletResponse response) throws Exception{
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        // 获取要查询的年
	        String year = request.getParameter("year");
	        //环比的年份
	        String hYear=year;
	        // 获取要查询的月份
	        String quarter = request.getParameter("quarter");
	        String lastQuarter;
	        
	        Calendar calendar = Calendar.getInstance();
	        //如果为空,转换为当前年份
	        if(year == null || year.length() == 0) {
	        	year = String.valueOf(calendar.get(Calendar.YEAR));
	        	hYear=year;
	        }
	        if(quarter == null || quarter.length() == 0) quarter = "2";
	        if(quarter=="1"){
	        	lastQuarter="4";
	        	hYear=String.valueOf(Integer.parseInt(year) - 1);
	        }else{
	        	lastQuarter=String.valueOf((Integer.parseInt(quarter)-1));
	        }
	        String lastYear = String.valueOf(Integer.parseInt(year) - 1);
	       // = Integer.parseInt(quarter)
	        String excelName = "房价环比同比汇总统计.xls";
	        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
	        StringBuffer sql = new StringBuffer();
	        sql.append(" SELECT ");
	        sql.append(" 	'三亚市' sanya, ");
	        sql.append(" 	tt.pencent pencent1, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt.pencent - tt.huanbi) / tt.huanbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) huanbi1, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt.pencent - tt.tongbi) / tt.tongbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) tongbi1, ");
	        sql.append(" 	tt2.pencent pencent2, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt2.pencent - tt2.huanbi) / tt2.huanbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) huanbi2, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt2.pencent - tt2.tongbi) / tt2.tongbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) tongbi2, ");
	        sql.append(" 	tt3.pencent pencent3, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt3.pencent - tt3.huanbi) / tt3.huanbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) huanbi3, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt3.pencent - tt3.tongbi) / tt3.tongbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) tongbi3, ");
	        sql.append(" 	tt4.pencent pencent4, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt4.pencent - tt4.huanbi) / tt4.huanbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) huanbi4, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt4.pencent - tt4.tongbi) / tt4.tongbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) tongbi4, ");
	        sql.append(" 	tt5.pencent pencent5, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt5.pencent - tt5.huanbi) / tt5.huanbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) huanbi5, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt5.pencent - tt5.tongbi) / tt5.tongbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) tongbi5, ");
	        sql.append(" 	tt6.pencent pencent6, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt6.pencent - tt6.huanbi) / tt6.huanbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) huanbi6, ");
	        sql.append(" 	TRUNCATE ( ");
	        sql.append(" 		IFNULL( ");
	        sql.append(" 			(tt6.pencent - tt6.tongbi) / tt6.tongbi * 100, ");
	        sql.append(" 			0 ");
	        sql.append(" 		), ");
	        sql.append(" 		2 ");
	        sql.append(" 	) tongbi6 ");
	        sql.append(" FROM ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			t.housegrade, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				( ");
	        sql.append(" 					IFNULL( ");
	        sql.append(" 						( ");
	        sql.append(" 							sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 						) * 1000, ");
	        sql.append(" 						0 ");
	        sql.append(" 					) ");
	        sql.append(" 				), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) pencent, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '4' ");
	        sql.append("AND th.`year` = "+lastYear+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 			) tongbi, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '4' ");
	        sql.append("AND th.`year` = "+hYear+" ");
			sql.append("AND th.`quarter` = "+lastQuarter+" ");
	        sql.append(" 			) huanbi ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly th ");
	        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			t.housegrade = '4' ");
			sql.append("AND th.`year` = "+year+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 	) tt, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			t.housegrade, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				( ");
	        sql.append(" 					IFNULL( ");
	        sql.append(" 						( ");
	        sql.append(" 							sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 						) * 1000, ");
	        sql.append(" 						0 ");
	        sql.append(" 					) ");
	        sql.append(" 				), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) pencent, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '5' ");
			sql.append("AND th.`year` = "+lastYear+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 			) tongbi, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '5' ");
			sql.append("AND th.`year` = "+hYear+" ");
			sql.append("AND th.`quarter` ="+lastQuarter+" "); 
	        sql.append(" 			) huanbi ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly th ");
	        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			t.housegrade = '5' ");
	        sql.append("AND th.`year` = "+year+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 	) tt2, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			t.housegrade, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				( ");
	        sql.append(" 					IFNULL( ");
	        sql.append(" 						( ");
	        sql.append(" 							sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 						) * 1000, ");
	        sql.append(" 						0 ");
	        sql.append(" 					) ");
	        sql.append(" 				), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) pencent, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '6' ");
	        sql.append("AND th.`year` = "+lastYear+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 			) tongbi, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '6' ");
	        sql.append("AND th.`year` = "+hYear+" "); 
			sql.append("AND th.`quarter` = "+lastQuarter+" ");
	        sql.append(" 			) huanbi ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly th ");
	        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			t.housegrade = '6' ");
	        sql.append("AND th.`year` = "+year+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 	) tt3, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			t.housegrade, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				( ");
	        sql.append(" 					IFNULL( ");
	        sql.append(" 						( ");
	        sql.append(" 							sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 						) * 1000, ");
	        sql.append(" 						0 ");
	        sql.append(" 					) ");
	        sql.append(" 				), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) pencent, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '7' ");
	        sql.append("AND th.`year` = "+lastYear+" ");
			sql.append("AND th.`quarter` = "+quarter+" "); 
	        sql.append(" 			) tongbi, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '7' ");
	        sql.append("AND th.`year` = "+hYear+" ");
			sql.append("AND th.`quarter` = "+lastQuarter+" ");
	        sql.append(" 			) huanbi ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly th ");
	        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			t.housegrade = '7' ");
	        sql.append("AND th.`year` = "+year+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 	) tt4, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			t.housegrade, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				( ");
	        sql.append(" 					IFNULL( ");
	        sql.append(" 						( ");
	        sql.append(" 							sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 						) * 1000, ");
	        sql.append(" 						0 ");
	        sql.append(" 					) ");
	        sql.append(" 				), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) pencent, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '8' ");
	        sql.append("AND th.`year` = "+lastYear+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 			) tongbi, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade = '8' ");
	        sql.append("AND th.`year` = "+hYear+" ");
			sql.append("AND th.`quarter` ="+lastQuarter+" ");
	        sql.append(" 			) huanbi ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly th ");
	        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			t.housegrade = '8' ");
	        sql.append("AND th.`year` = "+year+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 	) tt5, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			t.housegrade, ");
	        sql.append(" 			TRUNCATE ( ");
	        sql.append(" 				( ");
	        sql.append(" 					IFNULL( ");
	        sql.append(" 						( ");
	        sql.append(" 							sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 						) * 1000, ");
	        sql.append(" 						0 ");
	        sql.append(" 					) ");
	        sql.append(" 				), ");
	        sql.append(" 				2 ");
	        sql.append(" 			) pencent, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade IN (4, 5, 6, 7, 8) ");
	        sql.append("AND th.`year` = "+lastYear+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 			) tongbi, ");
	        sql.append(" 			( ");
	        sql.append(" 				SELECT ");
	        sql.append(" 					TRUNCATE ( ");
	        sql.append(" 						( ");
	        sql.append(" 							IFNULL( ");
	        sql.append(" 								( ");
	        sql.append(" 									sum(th.roomIncome) / sum(th.realNights) ");
	        sql.append(" 								) * 1000, ");
	        sql.append(" 								0 ");
	        sql.append(" 							) ");
	        sql.append(" 						), ");
	        sql.append(" 						2 ");
	        sql.append(" 					) pencent1 ");
	        sql.append(" 				FROM ");
	        sql.append(" 					t_hotel_quarterly th ");
	        sql.append(" 				LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 				WHERE ");
	        sql.append(" 					t.housegrade IN (4, 5, 6, 7, 8) ");
	        sql.append("AND th.`year` = "+hYear+" ");
			sql.append("AND th.`quarter` = "+lastQuarter+" ");
	        sql.append(" 			) huanbi ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotel_quarterly th ");
	        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			t.housegrade IN (4, 5, 4, 7, 8) ");
	        sql.append("AND th.`year` = "+year+" ");
			sql.append("AND th.`quarter` = "+quarter+" ");
	        sql.append(" 	) tt6 ");
	        	
		OutputStream os = response.getOutputStream();
		//response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/force-download");// 设置强制下载不打开
		
		response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode("房价环比同比汇总统计.xls","UTF-8"));// 设置文件名
		Map<String, Object> parmass = this.systemService.findOneForJdbc(sql.toString(), null);

		
		HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  modelPath, parmass);
		wb.write(os);
	           	
	    	
		}
	/**
	 * 
	 * @date: 2017年2月7日
	 * @Author: long
	 * @Email:
	 * @param: 
	 * @return:
	 * 星级饭店重要指标同比统计（指标排序表）
	 */
	@RequestMapping(params="exportsxls2")
	public void excelExport(HttpServletRequest request,HttpServletResponse response){
		 // 获取要查询的年
        String year = request.getParameter("year");
        // 获取要查询的月份
        String quarter = request.getParameter("quarter");
        Calendar calendar = Calendar.getInstance();
        if(year == null || year.length() == 0) year = String.valueOf(calendar.get(Calendar.YEAR));
        if(quarter == null || quarter.length() == 0) quarter = "2";
        String lastYear = String.valueOf(Integer.parseInt(year) - 1);
		String fileName = "";
		
		try {
			
			fileName=new String((year+"年第"+quarter+"星级饭店重要指标同比统计").getBytes(), "ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}  
		response.setContentType("application/vnd.ms-excel;charset=ISO8859_1"); 
		response.setHeader("content-disposition", "attachment;filename="+fileName+".xls");  
		OutputStream fOut = null;
		try {
			fOut = response.getOutputStream();
		} catch (IOException e) {
		 
			e.printStackTrace();
		}
		
		//定义结果集的语句
		StringBuffer sql = new StringBuffer();

        sql.append("SELECT ");
    	sql.append("IFNULL(t1.hname,0) nameee, ");
    	sql.append("IFNULL(t1.codes,0) codes, ");
    	sql.append("IFNULL(t1.av,0) av, ");
        sql.append("IFNULL(t1.pingtan,0) pingtan, ");
        sql.append("IFNULL(t1.income,0) income, ");
        sql.append("IFNULL(t1.rates,0) rates, ");
    	sql.append("IFNULL((t1.av-t2.av)/t2.av,0) zengzhang, ");
        sql.append("IFNULL((t1.rates-t2.rates)/t2.rates,0) jiajia, ");
        sql.append("IFNULL((t1.pingtan-t2.pingtan)/t2.pingtan,0) ping, ");
        sql.append("IFNULL((t1.income-t2.income)/t2.income,0) yingye ");

        sql.append("FROM ");
    	sql.append("( ");
    	sql.append("SELECT ");
		sql.append("t.unitname hname, ");
		sql.append("t.organizationNum codes, ");
		sql.append("t.housegrade housegrade, ");
		sql.append("th.realNights / th.canNights rates, ");
		sql.append("IFNULL( ");
		sql.append("th.roomIncome / th.realNights, ");
		sql.append("0 ");
		sql.append(") pingtan, ");
		sql.append("IFNULL( ");
		sql.append("th.roomIncome / th.canNights, ");
		sql.append("0 ");
		sql.append(") av, ");
    	sql.append("th.totalIncome income,");		
    	sql.append("t.ID id ");
		sql.append("FROM ");
		sql.append("t_hotel_quarterly th ");
		sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append("WHERE ");
		sql.append("t.housegrade in(4,5,4,7,8)  ");
		sql.append("AND th.`year` = "+year+" ");
		sql.append("AND th.`quarter` = "+quarter+" ");
    	sql.append(") t1, ");
    	sql.append("( ");
    	sql.append("SELECT ");
		sql.append("t.unitname hname, ");
		sql.append("t.organizationNum codes, ");
		sql.append("t.housegrade housegrade, ");
		sql.append("th.realNights / th.canNights rates, ");
		sql.append("IFNULL( ");
		sql.append("th.roomIncome / th.realNights, ");
		sql.append("0 ");
		sql.append(") pingtan, ");
		sql.append("th.totalIncome income,");
		sql.append("IFNULL( ");
		sql.append("th.roomIncome / th.canNights, ");
		sql.append("0 ");
		sql.append(") av, ");
		
		sql.append("t.ID id ");
		sql.append("FROM ");
		sql.append("t_hotel_quarterly th ");
		sql.append("LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append("WHERE ");
		sql.append("t.housegrade in(4,5,4,7,8) ");
		sql.append("AND th.`year` = "+lastYear+" ");
		sql.append("AND th.`quarter` ="+quarter+" ");
    	sql.append(") t2 ");
    	
        sql.append("WHERE ");
    	sql.append("t1.id = t2.id ");
    	List<Object> list = this.systemService.findListbySql(sql.toString());
    	String[] fields = {"nameee","codes","av","pingtan","income","rates","zengzhang","jiajia","ping","yingye"};
		String[] names = {"单位名称","标牌编号","平均房价(千元/间夜)","每间出租客房收入(千元)","营业收入(千元)","出租率","平均房价同比","出租率同比","每间客房平摊营业收入同比","营业额同比"};
		Workbook workbook;
		try {
			workbook = ExcelExportUtils.getInstance().inExcel(list, fields, names);
			workbook.write(fOut);  
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		finally
		{
			try {
				fOut.close();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		
	}
	
	
	@RequestMapping(params="exportsxls4")
	public void excelExport3(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "综合统计.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        String year = request.getParameter("year");
        // 获取要查询的月份
        String quarter = request.getParameter("quarter");
        Calendar calendar = Calendar.getInstance();
        if(year == null || year.length() == 0) year = String.valueOf(calendar.get(Calendar.YEAR));
        if(quarter == null || quarter.length() == 0) quarter = "1";
       
		StringBuffer sql= new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	t1.num num1, ");
		sql.append(" 	t1.income income1, ");
		sql.append(" 	t1.roomincome roomincome1, ");
		sql.append(" 	t1.canteenIncome canteenIncome1, ");
		sql.append(" 	h1.housenum housenum1, ");
		sql.append(" 	h1.bednum bednum1, ");
		sql.append(" 	t1.canNights canNights1, ");
		sql.append(" 	t1.realNights realNights1, ");
		sql.append(" 	t1.fangjia fangjia1, ");
		sql.append(" 	t1.rates rates1, ");
		sql.append(" 	t1.roomAVG roomAVG1, ");
		sql.append(" 	t1.pingAVG pingAVG1, ");
		sql.append(" 	t2.num num2, ");
		sql.append(" 	t2.income income2, ");
		sql.append(" 	t2.roomincome roomincome2, ");
		sql.append(" 	t2.canteenIncome canteenIncome2, ");
		sql.append(" 	h2.housenum housenum2, ");
		sql.append(" 	h2.bednum bednum2, ");
		sql.append(" 	t2.canNights canNights2, ");
		sql.append(" 	t2.realNights realNights2, ");
		sql.append(" 	t2.fangjia fangjia2, ");
		sql.append(" 	t2.rates rates2, ");
		sql.append(" 	t2.roomAVG roomAVG2, ");
		sql.append(" 	t2.pingAVG pingAVG2, ");
		sql.append(" 	t3.num num3, ");
		sql.append(" 	t3.income income3, ");
		sql.append(" 	t3.roomincome roomincome3, ");
		sql.append(" 	t3.canteenIncome canteenIncome3, ");
		sql.append(" 	h3.housenum housenum3, ");
		sql.append(" 	h3.bednum bednum3, ");
		sql.append(" 	t3.canNights canNights3, ");
		sql.append(" 	t3.realNights realNights3, ");
		sql.append(" 	t3.fangjia fangjia3, ");
		sql.append(" 	t3.rates rates3, ");
		sql.append(" 	t3.roomAVG roomAVG3, ");
		sql.append(" 	t3.pingAVG pingAVG3, ");
		sql.append(" 	t4.num num4, ");
		sql.append(" 	t4.income income4, ");
		sql.append(" 	t4.roomincome roomincome4, ");
		sql.append(" 	t4.canteenIncome canteenIncome4, ");
		sql.append(" 	h4.housenum housenum4, ");
		sql.append(" 	h4.bednum bednum4, ");
		sql.append(" 	t4.canNights canNights4, ");
		sql.append(" 	t4.realNights realNights4, ");
		sql.append(" 	t4.fangjia fangjia4, ");
		sql.append(" 	t4.rates rates4, ");
		sql.append(" 	t4.roomAVG roomAVG4, ");
		sql.append(" 	t4.pingAVG pingAVG4, ");
		sql.append(" 	t5.num num5, ");
		sql.append(" 	t5.income income5, ");
		sql.append(" 	t5.roomincome roomincome5, ");
		sql.append(" 	t5.canteenIncome canteenIncome5, ");
		sql.append(" 	h5.housenum housenum5, ");
		sql.append(" 	h5.bednum bednum5, ");
		sql.append(" 	t5.canNights canNights5, ");
		sql.append(" 	t5.realNights realNights5, ");
		sql.append(" 	t5.fangjia fangjia5, ");
		sql.append(" 	t5.rates rates5, ");
		sql.append(" 	t5.roomAVG roomAVG5, ");
		sql.append(" 	t5.pingAVG pingAVG5 ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			COUNT(*) num,");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.totalIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) income, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.roomIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomincome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canteenIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canteenIncome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.realNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) realNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.realNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) fangjia, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.realNights) / SUM(th.canNights)*100, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) rates, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.canNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomAVG, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.totalIncome) / (SELECT sum(t.housenum) FROM t_hotelmanage t WHERE t.housegrade=' 4 ')* 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) pingAVG ");
		sql.append(" 		FROM ");
		sql.append(" 			t_hotel_quarterly th ");
		sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append(" 		WHERE ");
		sql.append(" 			t.housegrade = '4' ");
        sql.append("AND th.`year` = "+year+" ");
        sql.append("AND th.`quarter` ="+quarter+")t1,");
        
    	sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			COUNT(*) num,");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.totalIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) income, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.roomIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomincome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canteenIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canteenIncome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.realNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) realNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.realNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) fangjia, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.realNights) / SUM(th.canNights)*100, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) rates, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.canNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomAVG, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.totalIncome) / (SELECT sum(t.housenum) FROM t_hotelmanage t WHERE t.housegrade='5')* 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) pingAVG ");
		sql.append(" 		FROM ");
		sql.append(" 			t_hotel_quarterly th ");
		sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append(" 		WHERE ");
		sql.append(" 			t.housegrade = '5' ");
        sql.append("AND th.`year` = "+year+" ");
        sql.append("AND th.`quarter` ="+quarter+")t2,");
 
        
    	sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			COUNT(*) num,");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.totalIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) income, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.roomIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomincome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canteenIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canteenIncome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.realNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) realNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.realNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) fangjia, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.realNights) / SUM(th.canNights)*100, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) rates, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.canNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomAVG, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.totalIncome) / (SELECT sum(t.housenum) FROM t_hotelmanage t WHERE t.housegrade='6')* 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) pingAVG ");
		sql.append(" 		FROM ");
		sql.append(" 			t_hotel_quarterly th ");
		sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append(" 		WHERE ");
		sql.append(" 			t.housegrade = '6' ");
        sql.append("AND th.`year` = "+year+" ");
        sql.append("AND th.`quarter` ="+quarter+")t3,");
        
    	sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			COUNT(*) num,");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.totalIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) income, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.roomIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomincome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canteenIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canteenIncome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.realNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) realNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.realNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) fangjia, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.realNights) / SUM(th.canNights)*100, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) rates, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.canNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomAVG, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.totalIncome) / (SELECT sum(t.housenum) FROM t_hotelmanage t WHERE t.housegrade='7')* 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) pingAVG ");
		sql.append(" 		FROM ");
		sql.append(" 			t_hotel_quarterly th ");
		sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append(" 		WHERE ");
		sql.append(" 			t.housegrade = '7' ");
        sql.append("AND th.`year` = "+year+" ");
        sql.append("AND th.`quarter` ="+quarter+")t4,");
        
        
    	sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			COUNT(*) num,");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.totalIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) income, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.roomIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomincome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canteenIncome), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canteenIncome, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.canNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) canNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL(SUM(th.realNights), 0), ");
		sql.append(" 				2 ");
		sql.append(" 			) realNights, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.realNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) fangjia, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.realNights) / SUM(th.canNights)*100, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) rates, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.roomIncome) / SUM(th.canNights) * 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) roomAVG, ");
		sql.append(" 			TRUNCATE ( ");
		sql.append(" 				IFNULL( ");
		sql.append(" 					SUM(th.totalIncome) / (SELECT sum(t.housenum) FROM t_hotelmanage t WHERE t.housegrade='8')* 1000, ");
		sql.append(" 					0 ");
		sql.append(" 				), ");
		sql.append(" 				2 ");
		sql.append(" 			) pingAVG ");
		sql.append(" 		FROM ");
		sql.append(" 			t_hotel_quarterly th ");
		sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
		sql.append(" 		WHERE ");
		sql.append(" 			t.housegrade = '8' ");
        sql.append("AND th.`year` = "+year+" ");
        sql.append("AND th.`quarter` ="+quarter+")t5,");
        
        
        sql.append(" ( ");
        sql.append(" 		SELECT ");
        sql.append(" 			SUM(t.bednum) bednum, ");
        sql.append(" 			sum(t.housenum) housenum ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotelmanage t ");
        sql.append(" 		WHERE ");
        sql.append(" 			t.housegrade = '4' ");
        sql.append(" 	) h1, ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			SUM(t.bednum) bednum, ");
        sql.append(" 			sum(t.housenum) housenum ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotelmanage t ");
        sql.append(" 		WHERE ");
        sql.append(" 			t.housegrade = '5' ");
        sql.append(" 	) h2, ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			SUM(t.bednum) bednum, ");
        sql.append(" 			sum(t.housenum) housenum ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotelmanage t ");
        sql.append(" 		WHERE ");
        sql.append(" 			t.housegrade = '6' ");
        sql.append(" 	) h3, ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			SUM(t.bednum) bednum, ");
        sql.append(" 			sum(t.housenum) housenum ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotelmanage t ");
        sql.append(" 		WHERE ");
        sql.append(" 			t.housegrade = '7' ");
        sql.append(" 	) h4, ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			SUM(t.bednum) bednum, ");
        sql.append(" 			sum(t.housenum) housenum ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotelmanage t ");
        sql.append(" 		WHERE ");
        sql.append(" 			t.housegrade = '8' ");
        sql.append(" 	) h5 "); 
        
    	OutputStream os = response.getOutputStream();
    	//response.setContentType("text/html;charset=UTF-8");
    	response.setContentType("application/force-download");// 设置强制下载不打开
    	
    	response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(year+"第"+quarter+"季度三亚市星级饭店经营情况统计表.xls","UTF-8"));// 设置文件名
    	Map<String, Object> parmass = this.systemService.findOneForJdbc(sql.toString(), null);

    	
    	HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  modelPath, parmass);
    	wb.getSheetAt(0).getRow(0).getCell(0).setCellValue(year+"第"+quarter+"季度三亚市星级饭店经营情况统计表");
    	wb.write(os);
		
		

	}
	
	
	
	/**
	 * 
	 * 星级饭店经营情况统计表(同环比)
	 * @param year 年，month 月
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="exportsxls3")
	public void overnightCollect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "星级饭店经营情况统计表（同环比）.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        ExportService emds = new ExportService(4,19); // 数字代表excel表格的列数,只导出1列时会有bug,暂时不做处理
        
        // 获取要查询的年
        String year = request.getParameter("year");
        //环比的年份
        String hYear=year;
        // 获取要查询的月份
        String quarter = request.getParameter("quarter");
        String lastQuarter;
        String area=request.getParameter("area");
        String star=request.getParameter("star");
        Calendar calendar = Calendar.getInstance();
        //如果为空,转换为当前年份
        if(year == null || year.length() == 0) {
        	year = String.valueOf(calendar.get(Calendar.YEAR));
        	hYear=year;
        }
        if(quarter == null || quarter.length() == 0) quarter = "2";
        if(quarter=="1"){
        	lastQuarter="4";
        	hYear=String.valueOf(Integer.parseInt(year) - 1);
        }else{
        	lastQuarter=String.valueOf((Integer.parseInt(quarter)-1));
        }
        String lastYear = String.valueOf(Integer.parseInt(year) - 1);
        
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" 	'三亚市', ");
        sql.append(" 	t4.nums, ");
        sql.append(" 	TRUNCATE (t1.income, 2) income, ");
        sql.append(" 	TRUNCATE (t1.cantee, 2) cantee, ");
        sql.append(" 	TRUNCATE (t1.room * 1000, 2) room, ");
        sql.append(" 	TRUNCATE (t1.roomPrice * 1000, 2) roomPrice, ");
        sql.append(" 	TRUNCATE (t1.rates, 2) rates, ");
        sql.append(" 	TRUNCATE (t1.avgPrice * 1000, 2) avgPrice, ");
        sql.append(" 	TRUNCATE (t1.pingPrice * 1000, 2) pingPrice, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(t1.roomPrice - t2.roomPrice) / t2.roomPrice * 100, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) roomPriceHb, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(t1.roomPrice - t3.roomPrice) / t3.roomPrice * 100, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) roomPriceTb, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(t1.rates - t2.rates) / t2.rates * 100, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) ratesHb, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(t1.rates - t3.rates) / t3.rates * 100, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) ratesTb, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(t1.avgPrice - t2.avgPrice) / t2.avgPrice * 100, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) avgPriceHb, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(t1.avgPrice - t3.avgPrice) / t3.avgPrice * 100, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) avgPriceTb, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(t1.pingPrice - t2.pingPrice) / t2.pingPrice * 100, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) pingPriceHb, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(t1.pingPrice - t3.pingPrice) / t3.pingPrice * 100, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) pingPriceTb, ");
        sql.append(" 	t1.roomnum, ");
        sql.append(" 	t1.bednum ");
        sql.append(" FROM ");
        sql.append(" 	( ");
        sql.append(" 		 ");
        sql.append(" 		SELECT ");
        sql.append(" 			t.w_county diqu, ");
        sql.append(" 			SUM(th.totalIncome) income, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.canteenIncome) / SUM(th.totalIncome) ");
        sql.append(" 					) * 100 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) cantee, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.totalIncome) ");
        sql.append(" 					) * 100 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) room, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.realNights) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) roomPrice, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.canNights) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) avgPrice, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				SUM(th.realNights) / SUM(th.cannights), ");
        sql.append(" 				0 ");
        sql.append(" 			) * 100 rates, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.totalIncome) / SUM(th.roomnum) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) pingPrice, ");
        sql.append(" 			IFNULL(SUM(th.roomnum), 0) roomnum, ");
        sql.append(" 			IFNULL(SUM(th.bednum), 0) bednum ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotel_quarterly th ");
        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
        sql.append(" 		WHERE ");
        sql.append(" 			1 = 1 ");
        sql.append(" 		AND t.housegrade IN (4, 5, 6, 7, 8) ");
        sql.append("AND th.`year`='"+year+"' ");
    	sql.append("AND th.`quarter`='"+quarter+"' ");
        sql.append(" 	) t1, ");
        sql.append(" 	( ");
        sql.append(" 		 ");
        sql.append(" 		SELECT ");
        sql.append(" 			t.w_county diqu, ");
        sql.append(" 			SUM(th.totalIncome) income, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.canteenIncome) / SUM(th.totalIncome) ");
        sql.append(" 					) * 100 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) cantee, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.totalIncome) ");
        sql.append(" 					) * 100 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) room, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.realNights) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) roomPrice, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.canNights) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) avgPrice, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				SUM(th.realNights) / SUM(th.cannights), ");
        sql.append(" 				0 ");
        sql.append(" 			) * 100 rates, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.totalIncome) / SUM(th.roomnum) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) pingPrice, ");
        sql.append(" 			IFNULL(SUM(th.roomnum), 0) roomnum, ");
        sql.append(" 			IFNULL(SUM(th.bednum), 0) bednum ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotel_quarterly th ");
        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
        sql.append(" 		WHERE ");
        sql.append(" 			1 = 1 ");
        sql.append(" 		AND t.housegrade IN (4, 5, 6, 7, 8) ");
        sql.append(" AND th.`year`='"+hYear+"' ");;
    	sql.append(" AND th.`quarter`='"+lastQuarter+"' ");
        sql.append(" 	) t2, ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			t.w_county diqu, ");
        sql.append(" 			SUM(th.totalIncome) income, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.canteenIncome) / SUM(th.totalIncome) ");
        sql.append(" 					) * 100 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) cantee, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.totalIncome) ");
        sql.append(" 					) * 100 ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) room, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.realNights) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) roomPrice, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.roomIncome) / SUM(th.canNights) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) avgPrice, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				SUM(th.realNights) / SUM(th.cannights), ");
        sql.append(" 				0 ");
        sql.append(" 			) * 100 rates, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					( ");
        sql.append(" 						SUM(th.totalIncome) / SUM(th.roomnum) ");
        sql.append(" 					) ");
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) pingPrice, ");
        sql.append(" 			IFNULL(SUM(th.roomnum), 0) roomnum, ");
        sql.append(" 			IFNULL(SUM(th.bednum), 0) bednum ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotel_quarterly th ");
        sql.append(" 		LEFT JOIN t_hotelmanage t ON th.hotel_qid = t.ID ");
        sql.append(" 		WHERE ");
        sql.append(" 			1 = 1 ");
        sql.append(" 		AND t.housegrade IN (4, 5, 6, 7, 8) ");
        sql.append(" AND th.`year`='"+lastYear+"' ");
    	sql.append(" AND th.`quarter`='"+quarter+"' ");
        sql.append(" 	) t3, ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			COUNT(t.unitname) nums ");
        sql.append(" 		FROM ");
        sql.append(" 			t_hotelmanage t ");
        sql.append(" 		WHERE ");
        sql.append(" 			1 = 1 ");
        sql.append(" 		AND t.housegrade IN (4, 5, 6, 7, 8) ");
        sql.append(" 	) t4 ");


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
						+ new String((year+"第"+quarter+"季度星级饭店经营情况统计表（同环比）.xls").getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+"第"+quarter+"季度星级饭店经营情况统计表（同环比）.xls", "UTF-8"));

	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
    	
	}
	
	//星级饭店经营情况统计表（按地区星级分）
	@RequestMapping(params = "excelExport7")
	public void excelExport7(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
         
        // 获取参数 
         
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
        String quarter= new String(request.getParameter("quarter").getBytes("iso8859-1"),"utf-8");
      
        String excelName ="星级饭店经营情况统计表(按地区星级分).xls";
        String modelPath ="/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        // 组装查询条件
        sql.append("   SELECT ");
        sql.append("         '三亚市', ");
        sql.append(" s1.code1 code1, ");
        sql.append("         s1.avgprice1 avgprice1, ");
        sql.append("         s1.avgrates1 avgrates1, ");
        sql.append("         s2.code1 code2, ");
        sql.append("         s2.avgprice1 avgprice2, ");
        sql.append("         s2.avgrates1 avgrates2, ");
        sql.append("         s3.code1 code3, ");
        sql.append("         s3.avgprice1 avgprice3, ");
        sql.append("         s3.avgrates1 avgrates3, ");
        sql.append("         s4.code1 code4, ");
        sql.append("         s4.avgprice1 avgprice4, ");
        sql.append("         s4.avgrates1 avgrates4, ");
        sql.append("         s5.code1 code5, ");
        sql.append("         s5.avgprice1 avgprice5, ");
        sql.append("         s5.avgrates1 avgrates5      ");
        sql.append("     FROM ");
        sql.append("         (    SELECT ");
        sql.append("             count(t.`code`) code1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.roomIncome) / sum(q.realNights) * 1000, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgprice1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.realNights) / sum(q.canNights) * 100, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgrates1     ");
        sql.append("         FROM ");
        sql.append("             t_hotelmanage t     ");
        sql.append("         LEFT JOIN ");
        sql.append("             t_hotel_quarterly q  ");
        sql.append("                 ON t.ID = q.hotel_qid     ");
        sql.append("         WHERE ");
        sql.append("             t.housegrade = '4'     ");
        sql.append("             AND q. YEAR =    "+year);
        sql.append("             AND q. QUARTER = "+quarter+"   ) s1, ");
        sql.append("         (    SELECT ");
        sql.append("             count(t.`code`) code1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.roomIncome) / sum(q.realNights) * 1000, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgprice1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.realNights) / sum(q.canNights) * 100, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgrates1     ");
        sql.append("         FROM ");
        sql.append("             t_hotelmanage t     ");
        sql.append("         LEFT JOIN ");
        sql.append("             t_hotel_quarterly q  ");
        sql.append("                 ON t.ID = q.hotel_qid     ");
        sql.append("         WHERE ");
        sql.append("             t.housegrade = '5'     ");
        sql.append("             AND q. YEAR =    "+year);
        sql.append("             AND q. QUARTER = "+quarter+"   ) s2, ");
        sql.append("         (    SELECT ");
        sql.append("             count(t.`code`) code1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.roomIncome) / sum(q.realNights) * 1000, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgprice1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.realNights) / sum(q.canNights) * 100, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgrates1     ");
        sql.append("         FROM ");
        sql.append("             t_hotelmanage t     ");
        sql.append("         LEFT JOIN ");
        sql.append("             t_hotel_quarterly q  ");
        sql.append("                 ON t.ID = q.hotel_qid     ");
        sql.append("         WHERE ");
        sql.append("             t.housegrade = '6'     ");
        sql.append("             AND q. YEAR =    "+year);
        sql.append("             AND q. QUARTER = "+quarter+"   ) s3, ");
        sql.append("         (    SELECT ");
        sql.append("             count(t.`code`) code1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.roomIncome) / sum(q.realNights) * 1000, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgprice1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.realNights) / sum(q.canNights) * 100, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgrates1     ");
        sql.append("         FROM ");
        sql.append("             t_hotelmanage t     ");
        sql.append("         LEFT JOIN ");
        sql.append("             t_hotel_quarterly q  ");
        sql.append("                 ON t.ID = q.hotel_qid     ");
        sql.append("         WHERE ");
        sql.append("             t.housegrade = '7'     ");
        sql.append("             AND q. YEAR =    "+year);
        sql.append("             AND q. QUARTER = "+quarter+"   ) s4, ");
        sql.append("         (    SELECT ");
        sql.append("             count(t.`code`) code1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.roomIncome) / sum(q.realNights) * 1000, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgprice1, ");
        sql.append("             IFNULL(      TRUNCATE (       sum(q.realNights) / sum(q.canNights) * 100, ");
        sql.append("             2      ), ");
        sql.append("             0     ) avgrates1     ");
        sql.append("         FROM ");
        sql.append("             t_hotelmanage t     ");
        sql.append("         LEFT JOIN ");
        sql.append("             t_hotel_quarterly q  ");
        sql.append("                 ON t.ID = q.hotel_qid     ");
        sql.append("         WHERE ");
        sql.append("             t.housegrade = '8'     ");
        sql.append("             AND q. YEAR =    "+year);
        sql.append("             AND q. QUARTER = "+quarter+"   ) s5 ");
        sql.append("         ");
        ExportService emds = new ExportService(7,16); 
        
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
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName, "UTF-8"));

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
