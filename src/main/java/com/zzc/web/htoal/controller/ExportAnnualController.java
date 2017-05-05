package com.zzc.web.htoal.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

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
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.system.service.SystemService;
@RequestMapping("/exportAnnualController")
@Controller
public class ExportAnnualController extends BaseController {
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
			List<String> yearList = this.systemService.findListbySql("SELECT distinct th.`year` AS year FROM  t_hotel_annual th");		
			request.setAttribute("yearList", yearList);
			return new ModelAndView("hotelStaticAnnual/hotelAnnual");
		}
	//财务状况表汇总（按地区）.xls
	@RequestMapping(params = "excelExport1")
	public void export1(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
       /* String area = new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");*/
        String excelName = "财务状况表汇总（按地区）.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT '三亚市', sum(t.streamTotal),sum(t.deposit),sum(t.longInvest),sum(t.fixedAssets),sum(t.fixedPrice),sum(t.depreciation),sum(t.yearDepreciation),sum(t.assetTotal),sum(t.liabilitiesTotal),sum(t.possessorTotal),sum(t.realIncome),sum(t.operationIncome),sum(t.roomIncome),sum(t.canteeIncome),sum(t.otherIncome),'0' yywsr,'0' zfbt,sum(t.operationCost),'0' nhcb,sum(t.operationFee),sum(t.oprFeeAndTax),sum(t.management_fee),sum(t.taxes),sum(t.travelExpense),sum(t.financialCost),sum(t.interestCost),'0' xsfy,sum(t.valueVariation),sum(t.opreationIntrest),sum(t.invest),sum(t.profitTotal),sum(t.ownTax),sum(t.salary),'0' zzs,sum(t.people),sum(t.college),'0' cyrylsl,sum(t.actual_rent),sum(t.for_hire),sum(t.room),sum(t.bed),'0' zcjzss FROM `t_hotel_annual` t LEFT JOIN t_hotelmanage h ON t.hotel_aid=h.ID  where 1=1");
        // if(h.w_county='0','市辖区', if(h.w_county='1','吉阳区', if(h.w_county='2','崖州区', if(h.w_county='3','天涯区', if(h.w_county='4','海棠区','三亚市'))))),
        // 组装查询条件
        if(year !=null && year.length() != 0){
        	sql.append(" and  t.year="+year);
        } 
      /*  if(area !=null && area.length() != 0){
        	sql.append(" and  h.w_county="+area);
        	 sql.append(" GROUP BY h.w_county");
        }*/
       
        
        
        ExportService emds = new ExportService(2,43); 
        
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
						+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
        
	
	}
	//财务状况表汇总-1（按星级饭店）.xls
	@RequestMapping(params = "excelExport2")
	public void export2(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
       /* String area = new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");*/
        String excelName = "财务状况表汇总-1（按星级饭店）.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT t.code,t.name,t.streamTotal,t.deposit,t.longInvest,t.fixedAssets,t.fixedPrice,t.depreciation,t.yearDepreciation,t.assetTotal,t.liabilitiesTotal,t.possessorTotal,t.realIncome,t.operationIncome,t.roomIncome,t.canteeIncome,t.otherIncome,'0' yywsr,'0' zfbt,t.operationCost,'0' nhcb FROM `t_hotel_annual` t  LEFT JOIN t_hotelmanage h ON t.hotel_aid=h.ID where 1=1");
        // 组装查询条件
        if(year !=null && year.length() != 0){
        	sql.append(" and  t.year="+year);
        }/* if(area !=null && area.length() != 0){
        	sql.append(" and  h.w_county="+area);
        }
        */
        
        ExportService emds = new ExportService(2,21); 
        
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
						+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
        
	
	}
	//财务状况表汇总-2（按星级饭店）.xls
	@RequestMapping(params = "excelExport3")
	public void export3(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
       /* String area = new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");*/
        String excelName = "财务状况表汇总-2（按星级饭店）.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT t.code,t.name,t.operationFee,t.oprFeeAndTax,t.management_fee,t.taxes,t.travelExpense,t.financialCost,t.interestCost,'0' xsfy,t.valueVariation,t.opreationIntrest,t.invest,t.profitTotal,t.ownTax,t.salary,'0' bnyjzzs,t.people,t.college,'0' cyrylsl,t.actual_rent,t.for_hire,t.room,t.bed,'0' fzxqyhjzz,'0' yzxqyhjzz FROM `t_hotel_annual` t LEFT JOIN t_hotelmanage h ON t.hotel_aid=h.ID  where 1=1  ");
        // 组装查询条件
        if(year !=null && year.length() != 0){
        	sql.append(" and  t.year="+year);
        } /*if(area !=null && area.length() != 0){
        	sql.append(" and  h.w_county="+area);
        }*/
        
        
        ExportService emds = new ExportService(2,26); 
        
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
						+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
        
	
	}
	//财务状况汇总表（分星级）（excel表)
	@RequestMapping(params = "excelExport4")
	public void export4(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String year=request.getParameter("year");
        String lastYear=String.valueOf(Integer.valueOf(year)-1);
		   String excelName = "财务状况汇总表(分星级).xls";
	        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
	        StringBuffer sql = new StringBuffer();
	        sql.append(" SELECT ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates1, ");
	        sql.append(" 	IFNULL(TRUNCATE(th1.taxes, 2), 0) taxes1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest1, ");
	        sql.append(" 	IFNULL(TRUNCATE(th1.people, 2), 0) people1, ");
	        sql.append(" 	IFNULL(TRUNCATE(th1.college, 2), 0) college1, ");
	        sql.append(" 	IFNULL(TRUNCATE(th1.room, 2), 0) room1, ");
	        sql.append(" 	IFNULL(TRUNCATE(th1.bed, 2), 0) bed1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent1, ");
	        sql.append(" 	IFNULL(TRUNCATE(th1.avgprice, 2), 0) avgprice1, ");
	        sql.append(" 	IFNULL(TRUNCATE(th1.avgrates, 2), 0) avgrates1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates1, ");
	        sql.append(" 	IFNULL(TRUNCATE(th1.avgtaxes, 2), 0) avgtaxes1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th1.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys1, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates11, ");
	        sql.append(" 	IFNULL(TRUNCATE(la1.taxes, 2), 0) taxes11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest11, ");
	        sql.append(" 	IFNULL(TRUNCATE(la1.people, 2), 0) people11, ");
	        sql.append(" 	IFNULL(TRUNCATE(la1.college, 2), 0) college11, ");
	        sql.append(" 	IFNULL(TRUNCATE(la1.room, 2), 0) room11, ");
	        sql.append(" 	IFNULL(TRUNCATE(la1.bed, 2), 0) bed11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent11, ");
	        sql.append(" 	IFNULL(TRUNCATE(la1.avgprice, 2), 0) avgprice11, ");
	        sql.append(" 	IFNULL(TRUNCATE(la1.avgrates, 2), 0) avgrates11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates11, ");
	        sql.append(" 	IFNULL(TRUNCATE(la1.avgtaxes, 2), 0) avgtaxes11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la1.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys11, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates2, ");
	        sql.append(" 	IFNULL(TRUNCATE(th2.taxes, 2), 0) taxes2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest2, ");
	        sql.append(" 	IFNULL(TRUNCATE(th2.people, 2), 0) people2, ");
	        sql.append(" 	IFNULL(TRUNCATE(th2.college, 2), 0) college2, ");
	        sql.append(" 	IFNULL(TRUNCATE(th2.room, 2), 0) room2, ");
	        sql.append(" 	IFNULL(TRUNCATE(th2.bed, 2), 0) bed2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent2, ");
	        sql.append(" 	IFNULL(TRUNCATE(th2.avgprice, 2), 0) avgprice2, ");
	        sql.append(" 	IFNULL(TRUNCATE(th2.avgrates, 2), 0) avgrates2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates2, ");
	        sql.append(" 	IFNULL(TRUNCATE(th2.avgtaxes, 2), 0) avgtaxes2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th2.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys2, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates22, ");
	        sql.append(" 	IFNULL(TRUNCATE(la2.taxes, 2), 0) taxes22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest22, ");
	        sql.append(" 	IFNULL(TRUNCATE(la2.people, 2), 0) people22, ");
	        sql.append(" 	IFNULL(TRUNCATE(la2.college, 2), 0) college22, ");
	        sql.append(" 	IFNULL(TRUNCATE(la2.room, 2), 0) room22, ");
	        sql.append(" 	IFNULL(TRUNCATE(la2.bed, 2), 0) bed22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent22, ");
	        sql.append(" 	IFNULL(TRUNCATE(la2.avgprice, 2), 0) avgprice22, ");
	        sql.append(" 	IFNULL(TRUNCATE(la2.avgrates, 2), 0) avgrates22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates22, ");
	        sql.append(" 	IFNULL(TRUNCATE(la2.avgtaxes, 2), 0) avgtaxes22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la2.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys22, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates3, ");
	        sql.append(" 	IFNULL(TRUNCATE(th3.taxes, 2), 0) taxes3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest3, ");
	        sql.append(" 	IFNULL(TRUNCATE(th3.people, 2), 0) people3, ");
	        sql.append(" 	IFNULL(TRUNCATE(th3.college, 2), 0) college3, ");
	        sql.append(" 	IFNULL(TRUNCATE(th3.room, 2), 0) room3, ");
	        sql.append(" 	IFNULL(TRUNCATE(th3.bed, 2), 0) bed3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent3, ");
	        sql.append(" 	IFNULL(TRUNCATE(th3.avgprice, 2), 0) avgprice3, ");
	        sql.append(" 	IFNULL(TRUNCATE(th3.avgrates, 2), 0) avgrates3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates3, ");
	        sql.append(" 	IFNULL(TRUNCATE(th3.avgtaxes, 2), 0) avgtaxes3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th3.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys3, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates33, ");
	        sql.append(" 	IFNULL(TRUNCATE(la3.taxes, 2), 0) taxes33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest33, ");
	        sql.append(" 	IFNULL(TRUNCATE(la3.people, 2), 0) people33, ");
	        sql.append(" 	IFNULL(TRUNCATE(la3.college, 2), 0) college33, ");
	        sql.append(" 	IFNULL(TRUNCATE(la3.room, 2), 0) room33, ");
	        sql.append(" 	IFNULL(TRUNCATE(la3.bed, 2), 0) bed33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent33, ");
	        sql.append(" 	IFNULL(TRUNCATE(la3.avgprice, 2), 0) avgprice33, ");
	        sql.append(" 	IFNULL(TRUNCATE(la3.avgrates, 2), 0) avgrates33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates33, ");
	        sql.append(" 	IFNULL(TRUNCATE(la3.avgtaxes, 2), 0) avgtaxes33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la3.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys33, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates4, ");
	        sql.append(" 	IFNULL(TRUNCATE(th4.taxes, 2), 0) taxes4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest4, ");
	        sql.append(" 	IFNULL(TRUNCATE(th4.people, 2), 0) people4, ");
	        sql.append(" 	IFNULL(TRUNCATE(th4.college, 2), 0) college4, ");
	        sql.append(" 	IFNULL(TRUNCATE(th4.room, 2), 0) room4, ");
	        sql.append(" 	IFNULL(TRUNCATE(th4.bed, 2), 0) bed4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent4, ");
	        sql.append(" 	IFNULL(TRUNCATE(th4.avgprice, 2), 0) avgprice4, ");
	        sql.append(" 	IFNULL(TRUNCATE(th4.avgrates, 2), 0) avgrates4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates4, ");
	        sql.append(" 	IFNULL(TRUNCATE(th4.avgtaxes, 2), 0) avgtaxes4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th4.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys4, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates44, ");
	        sql.append(" 	IFNULL(TRUNCATE(la4.taxes, 2), 0) taxes44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest44, ");
	        sql.append(" 	IFNULL(TRUNCATE(la4.people, 2), 0) people44, ");
	        sql.append(" 	IFNULL(TRUNCATE(la4.college, 2), 0) college44, ");
	        sql.append(" 	IFNULL(TRUNCATE(la4.room, 2), 0) room44, ");
	        sql.append(" 	IFNULL(TRUNCATE(la4.bed, 2), 0) bed44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent44, ");
	        sql.append(" 	IFNULL(TRUNCATE(la4.avgprice, 2), 0) avgprice44, ");
	        sql.append(" 	IFNULL(TRUNCATE(la4.avgrates, 2), 0) avgrates44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates44, ");
	        sql.append(" 	IFNULL(TRUNCATE(la4.avgtaxes, 2), 0) avgtaxes44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la4.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys44, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates5, ");
	        sql.append(" 	IFNULL(TRUNCATE(th5.taxes, 2), 0) taxes5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest5, ");
	        sql.append(" 	IFNULL(TRUNCATE(th5.people, 2), 0) people5, ");
	        sql.append(" 	IFNULL(TRUNCATE(th5.college, 2), 0) college5, ");
	        sql.append(" 	IFNULL(TRUNCATE(th5.room, 2), 0) room5, ");
	        sql.append(" 	IFNULL(TRUNCATE(th5.bed, 2), 0) bed5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent5, ");
	        sql.append(" 	IFNULL(TRUNCATE(th5.avgprice, 2), 0) avgprice5, ");
	        sql.append(" 	IFNULL(TRUNCATE(th5.avgrates, 2), 0) avgrates5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates5, ");
	        sql.append(" 	IFNULL(TRUNCATE(th5.avgtaxes, 2), 0) avgtaxes5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th5.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys5, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates55, ");
	        sql.append(" 	IFNULL(TRUNCATE(la5.taxes, 2), 0) taxes55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest55, ");
	        sql.append(" 	IFNULL(TRUNCATE(la5.people, 2), 0) people55, ");
	        sql.append(" 	IFNULL(TRUNCATE(la5.college, 2), 0) college55, ");
	        sql.append(" 	IFNULL(TRUNCATE(la5.room, 2), 0) room55, ");
	        sql.append(" 	IFNULL(TRUNCATE(la5.bed, 2), 0) bed55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent55, ");
	        sql.append(" 	IFNULL(TRUNCATE(la5.avgprice, 2), 0) avgprice55, ");
	        sql.append(" 	IFNULL(TRUNCATE(la5.avgrates, 2), 0) avgrates55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates55, ");
	        sql.append(" 	IFNULL(TRUNCATE(la5.avgtaxes, 2), 0) avgtaxes55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets55, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la5.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys55 ,");
	        
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates6, ");
	        sql.append(" 	IFNULL(TRUNCATE(th6.taxes, 2), 0) taxes6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest6, ");
	        sql.append(" 	IFNULL(TRUNCATE(th6.people, 2), 0) people6, ");
	        sql.append(" 	IFNULL(TRUNCATE(th6.college, 2), 0) college6, ");
	        sql.append(" 	IFNULL(TRUNCATE(th6.room, 2), 0) room6, ");
	        sql.append(" 	IFNULL(TRUNCATE(th6.bed, 2), 0) bed6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent6, ");
	        sql.append(" 	IFNULL(TRUNCATE(th6.avgprice, 2), 0) avgprice6, ");
	        sql.append(" 	IFNULL(TRUNCATE(th6.avgrates, 2), 0) avgrates6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates6, ");
	        sql.append(" 	IFNULL(TRUNCATE(th6.avgtaxes, 2), 0) avgtaxes6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (th6.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys6, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.assetTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) assetTotal66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.fixedPrice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) fixedPrice66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.depreciation, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) depreciation66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.streamTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) streamTotal66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.liabilitiesTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) liabilitiesTotal66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.possessorTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) possessorTotal66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.operationIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) operationIncome66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.profitTotal, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) profitTotal66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.roomIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomIncome66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.roomrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) roomrates66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.canteeIncome, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteeIncome66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.canteerates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) canteerates66, ");
	        sql.append(" 	IFNULL(TRUNCATE(la6.taxes, 2), 0) taxes66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.opreationIntrest, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) opreationIntrest66, ");
	        sql.append(" 	IFNULL(TRUNCATE(la6.people, 2), 0) people66, ");
	        sql.append(" 	IFNULL(TRUNCATE(la6.college, 2), 0) college66, ");
	        sql.append(" 	IFNULL(TRUNCATE(la6.room, 2), 0) room66, ");
	        sql.append(" 	IFNULL(TRUNCATE(la6.bed, 2), 0) bed66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.for_hire, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) for_hire66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.actual_rent, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) actual_rent66, ");
	        sql.append(" 	IFNULL(TRUNCATE(la6.avgprice, 2), 0) avgprice66, ");
	        sql.append(" 	IFNULL(TRUNCATE(la6.avgrates, 2), 0) avgrates66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.avgcanprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgcanprice66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.avgroomprice, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgroomprice66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.avglaborrates, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avglaborrates66, ");
	        sql.append(" 	IFNULL(TRUNCATE(la6.avgtaxes, 2), 0) avgtaxes66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.avgprofit, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgprofit66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.avgfixedAssets, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgfixedAssets66, ");
	        sql.append(" 	IFNULL( ");
	        sql.append(" 		TRUNCATE (la6.avgbygdzccyys, 2), ");
	        sql.append(" 		0 ");
	        sql.append(" 	) avgbygdzccyys66 ");
	        
	        sql.append(" FROM ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+year);
	        sql.append(" 		AND t.housegrade = '4' ");
	        sql.append(" 	) th1, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+lastYear);
	        sql.append(" 		AND t.housegrade = '4' ");
	        sql.append(" 	) la1, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+year);
	        sql.append(" 		AND t.housegrade = '5' ");
	        sql.append(" 	) th2, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+lastYear);
	        sql.append(" 		AND t.housegrade = '5' ");
	        sql.append(" 	) la2, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+year);
	        sql.append(" 		AND t.housegrade = '6' ");
	        sql.append(" 	) th3, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+lastYear);
	        sql.append(" 		AND t.housegrade = '6' ");
	        sql.append(" 	) la3, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+year);
	        sql.append(" 		AND t.housegrade = '7' ");
	        sql.append(" 	) th4, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+lastYear);
	        sql.append(" 		AND t.housegrade = '7' ");
	        sql.append(" 	) la4, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+year);
	        sql.append(" 		AND t.housegrade = '8' ");
	        sql.append(" 	) th5, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+lastYear);
	        sql.append(" 		AND t.housegrade = '8' ");
	        sql.append(" 	) la5 ,");
	        
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+year);
	        sql.append(" 		AND t.housegrade in(4,5,6,7,8) ");
	        sql.append(" 	) th6, ");
	        sql.append(" 	( ");
	        sql.append(" 		SELECT ");
	        sql.append(" 			sum(a.assetTotal) assetTotal, ");
	        sql.append(" 			sum(a.fixedPrice) fixedPrice, ");
	        sql.append(" 			sum(a.depreciation) depreciation, ");
	        sql.append(" 			sum(a.streamTotal) streamTotal, ");
	        sql.append(" 			sum(a.liabilitiesTotal) liabilitiesTotal, ");
	        sql.append(" 			sum(a.possessorTotal) possessorTotal, ");
	        sql.append(" 			sum(a.operationIncome) operationIncome, ");
	        sql.append(" 			sum(a.profitTotal) profitTotal, ");
	        sql.append(" 			sum(a.roomIncome) roomIncome, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.operationIncome) roomrates, ");
	        sql.append(" 			sum(a.canteeIncome) canteeIncome, ");
	        sql.append(" 			sum(a.canteeIncome) / sum(a.operationIncome) canteerates, ");
	        sql.append(" 			sum(a.taxes) taxes, ");
	        sql.append(" 			sum(a.opreationIntrest) opreationIntrest, ");
	        sql.append(" 			sum(a.people) people, ");
	        sql.append(" 			sum(a.college) college, ");
	        sql.append(" 			sum(a.room) room, ");
	        sql.append(" 			sum(a.bed) bed, ");
	        sql.append(" 			sum(a.for_hire) for_hire, ");
	        sql.append(" 			sum(a.actual_rent) actual_rent, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent) avgprice, ");
	        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) avgrates, ");
	        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire) avgcanprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.room) avgroomprice, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.people) avglaborrates, ");
	        sql.append(" 			sum(a.taxes) / sum(a.people) avgtaxes, ");
	        sql.append(" 			sum(a.profitTotal) / sum(a.people) avgprofit, ");
	        sql.append(" 			sum(a.fixedAssets) / sum(a.people) avgfixedAssets, ");
	        sql.append(" 			sum(a.operationIncome) / sum(a.fixedAssets) * 100 avgbygdzccyys ");
	        sql.append(" 		FROM ");
	        sql.append(" 			t_hotelmanage t ");
	        sql.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        sql.append(" 		WHERE ");
	        sql.append(" 			a.`year` = "+lastYear);
	        sql.append(" 		AND t.housegrade in(4,5,6,7,8) ");
	        sql.append(" 	) la6 ");
	        
		OutputStream os = response.getOutputStream();
		response.setContentType("application/force-download");// 设置强制下载不打开
		
		response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode("财务状况汇总表.xls","UTF-8"));// 设置文件名
		Map<String, Object> parmass = this.systemService.findOneForJdbc(sql.toString(), null);
		HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  modelPath, parmass);
			wb.setForceFormulaRecalculation(true);
		wb.write(os);
	}
	//各地饭店数量汇总（分星级）（excel表）.xls
	@RequestMapping(params = "excelExport5")
	public void export5(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
       // String area = new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
        String excelName = "各地饭店数量汇总（分星级）（excel表）.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        	sql.append("SELECT ");
        /*	if(area.length()!=0&&area!=null){
        		sql.append(" DISTINCT if(hh.w_county='0','市辖区', if(hh.w_county='1','吉阳区', if(hh.w_county='2','崖州区', if(hh.w_county='3','天涯区', if(hh.w_county='4','海棠区','三亚市'))))),");
        	}else{*/
        		sql.append(" DISTINCT '三亚市',");
        	/*}*/
        	
        		sql.append("IFNULL(star1.fd1, 0) sfd1, ").
        		append("IFNULL(star1.kf1, 0) skf1, ").
        		append("IFNULL(star1.cw1, 0) scw1, ").
        		append("IFNULL(star2.fd2, 0) sfd2, ").
        		append("IFNULL(star2.kf2, 0) skf2, ").
        		append("IFNULL(star2.cw2, 0) scw2, ").
        		append("IFNULL(star3.fd3, 0) sfd3, ").
        		append("IFNULL(star3.kf3, 0) skf3, ").
        		append("IFNULL(star3.cw3, 0) scw3, ").
        		append("IFNULL(star4.fd4, 0) sfd4, ").
        		append("IFNULL(star4.kf4, 0) skf4, ").
        		append("IFNULL(star4.cw4, 0) scw4, ").
        		append("IFNULL(star5.fd5, 0) sfd5, ").
        		append("IFNULL(star5.kf5, 0) skf5, ").
        		append("IFNULL(star5.cw5, 0) scw5, ").
        		append("( ").
        		append("IFNULL(star1.fd1, 0) + IFNULL(star2.fd2, 0) + IFNULL(star3.fd3, 0) + IFNULL(star4.fd4, 0) + IFNULL(star5.fd5, 0) ").
        		append(") fdsum, ").
        		append("( ").
        		append("IFNULL(star1.kf1, 0) + IFNULL(star2.kf2, 0) + IFNULL(star3.kf3, 0) + IFNULL(star4.kf4, 0) + IFNULL(star5.kf5, 0) ").
        		append(") kfsum, ").
        		append("( ").
        		append("IFNULL(star1.cw1, 0) + IFNULL(star2.cw2, 0) + IFNULL(star3.cw3, 0) + IFNULL(star4.cw4, 0) + IFNULL(star5.cw5, 0) ").
        		append(") cwsum ").
        		append("FROM ").
        		append("t_hotel_annual tha LEFT JOIN t_hotelmanage hh ON tha.hotel_aid=hh.ID,").
        		append("( ").
        		append("SELECT ").
        		append("SUM(t.id) fd1, ").
        		append("sum(t.room) kf1, ").
        		append("sum(t.bed) cw1 ").
        		append("FROM ").
        		append("t_hotel_annual t ").
        		append("LEFT JOIN t_hotelmanage h ON t.hotel_aid = h.ID ").
        		append("WHERE ").
        		append("h.housegrade = '4' ");
        	/*	if(area!=null&&area.length()!=0){
        			sql.append(" and h.w_county="+area);
        		}*/
        		sql.append(") star1, ").
        		append("( ").
        		append("SELECT ").
        		append("SUM(t.id) fd2, ").
        		append("sum(t.room) kf2, ").
        		append("sum(t.bed) cw2 ").
        		append("FROM ").
        		append("t_hotel_annual t ").
        		append("LEFT JOIN t_hotelmanage h ON t.hotel_aid = h.ID ").
        		append("WHERE ").
        		append("h.housegrade = '5' ");
        	/*	if(area!=null&&area.length()!=0){
        			sql.append(" and h.w_county="+area);
        		}*/
        		sql.append(") star2, ").
        		append("( ").
        		append("SELECT ").
        		append("SUM(t.id) fd3, ").
        		append("sum(t.room) kf3, ").
        		append("sum(t.bed) cw3 ").
        		append("FROM ").
        		append("t_hotel_annual t ").
        		append("LEFT JOIN t_hotelmanage h ON t.hotel_aid = h.ID ").
        		append("WHERE ").
        		append("h.housegrade = '6' ");
        	/*	if(area!=null&&area.length()!=0){
        			sql.append(" and h.w_county="+area);
        		}*/
        		sql.append(") star3, ").
        		append("( ").
        		append("SELECT ").
        		append("SUM(t.id) fd4, ").
        		append("sum(t.room) kf4, ").
        		append("sum(t.bed) cw4 ").
        		append("FROM ").
        		append("t_hotel_annual t ").
        		append("LEFT JOIN t_hotelmanage h ON t.hotel_aid = h.ID ").
        		append("WHERE ").
        		append("h.housegrade = '7' ");
        	/*	if(area!=null&&area.length()!=0){
        			sql.append(" and h.w_county="+area);
        		}*/
        		sql.append(") star4, ").
        		append("( ").
        		append("SELECT ").
        		append("SUM(t.id) fd5, ").
        		append("sum(t.room) kf5, ").
        		append("sum(t.bed) cw5 ").
        		append("FROM ").
        		append("t_hotel_annual t ").
        		append("LEFT JOIN t_hotelmanage h ON t.hotel_aid = h.ID ").
        		append("WHERE ").
        		append("h.housegrade = '8' ");
        		/*if(area!=null&&area.length()!=0){
        			sql.append(" and h.w_county="+area);
        		}*/
        		sql.append(") star5 ").
        		append("where 1=1");

        // 组装查询条件
        if(year !=null && year.length() != 0){
        	sql.append(" and  tha.year="+year);
        }/*if(area!=null&&area.length()!=0){
			sql.append(" and hh.w_county="+area);
		}*/
        
        
        ExportService emds = new ExportService(3,19); 
        
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
						+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
	
	}
	//基本经济指标汇总表(excel表)
	@RequestMapping(params = "excelExport6")
	public void export6(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取参数 
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
        String area = new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
        String excelName = "基本经济指标汇总表（excel表）.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        StringBuffer sql = new StringBuffer();
        
        sql.append("SELECT '三亚市' ,sum(t.operationIncome),sum(t.roomIncome),(sum(t.roomIncome)/sum(t.operationIncome))*100 kfpercent,sum(t.canteeIncome),(sum(t.canteeIncome)/sum(t.operationIncome))*100 cypercent,sum(t.fixedPrice),sum(t.profitTotal),sum(t.taxes),sum(t.people),sum(t.college),sum(t.for_hire),sum(t.actual_rent),sum(t.oprFeeAndTax) from t_hotel_annual t LEFT JOIN t_hotelmanage h ON t.hotel_aid=h.ID  where 1=1 ");
        // 组装查询条件
        //if(h.w_county='0','市辖区', if(h.w_county='1','吉阳区', if(h.w_county='2','崖州区', if(h.w_county='3','天涯区' if(h.w_county='4','海棠区','三亚市'))))),
        if(year !=null && year.length() != 0){
        	sql.append(" and  t.year="+year);
        }
    /*    if(area!=null&&area.length()!=0){
        	sql.append(" and h.w_county="+area);
        	sql.append(" GROUP BY h.w_county");
        }*/
        
        
        ExportService emds = new ExportService(2,14); 
        
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
						+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
	
	}
	//基本情况表汇总（按星级饭店）
		@RequestMapping(params = "excelExport7")
		public void export7(HttpServletRequest request, HttpServletResponse response) throws Exception{

			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        
	        // 获取参数 
	        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
	       // String area = new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
	        String excelName = "基本情况表汇总（按星级饭店）.xls";
	        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
	        StringBuffer sql = new StringBuffer();
	        sql.append("SELECT t.code,t.name,t.room,t.bed ,h.regionalism_code,h.address,h.organizationNum,h.registerstyle,h.legal_person,h.telephone,h.fax,h.mobile,h.zipcode,h.email,h.weburl,h.writer,h.filler_tel FROM `t_hotel_annual` t LEFT JOIN t_hotelmanage h ON t.hotel_aid=h.ID where 1=1 ");
	        // 组装查询条件
	        if(year !=null && year.length() != 0){
	        	sql.append(" and  t.year="+year);
	        } /*if(area !=null && area.length() != 0){
	        	sql.append(" and  h.w_county="+area);
	        }*/
	        
	        
	        ExportService emds = new ExportService(1,17); 
	        
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
							+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
		        }else{
		            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

		        }
				POIUtils.writeWorkbook(workbook, response.getOutputStream());
			} else {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode("Errors.xls", "UTF-8"));
				response.getOutputStream().print(
						"You have already downloaded the error excel!");
			}
	        
		
		}
		//基本情况汇总表（分星级）（excel表）
				@RequestMapping(params = "excelExport8")
				public void export8(HttpServletRequest request, HttpServletResponse response) throws Exception{

					request.setCharacterEncoding("UTF-8");
			        response.setCharacterEncoding("UTF-8");
			        response.setContentType("text/html;charset=UTF-8");
			        
			        // 获取参数 
			        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
			        String excelName = "基本情况汇总表(分星级).xls";
			        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
			        ExportService emds = null;
			        StringBuffer[] sql = new StringBuffer[10];
			        HSSFWorkbook workbook = null;
			        for(int i=4;i<=8;i++){
			        	sql[i]=new StringBuffer();
			        		sql[i].append(" SELECT ");
					        sql[i].append(" 	count(*) ");
					        sql[i].append(" FROM ");
					        sql[i].append(" 	t_hotelmanage t ");
					        sql[i].append(" WHERE ");
					        sql[i].append(" 	t.housegrade = "+i);
			        		sql[i].append(" UNION ALL ");
			        		sql[i].append(" 	SELECT ");
			        		sql[i].append(" 		IFNULL(sum(a.room), 0) ");
			        		sql[i].append(" 	FROM ");
			        		sql[i].append(" 		t_hotelmanage t ");
			        		sql[i].append(" 	LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
			        		sql[i].append(" 	WHERE ");
			        		sql[i].append(" 		t.housegrade = "+i);
			        		sql[i].append(" 	AND a.`year` =  "+year);
			        		sql[i].append(" 	UNION ALL ");
			        		sql[i].append(" 		SELECT ");
			        		sql[i].append(" 			IFNULL(sum(a.bed), 0) ");
			        		sql[i].append(" 		FROM ");
			        		sql[i].append(" 			t_hotelmanage t ");
			        		sql[i].append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
			        		sql[i].append(" 		WHERE ");
			        		sql[i].append(" 			t.housegrade = "+i);
			        		sql[i].append(" 		AND a.`year` =  "+year);
			        		sql[i].append(" 		UNION ALL ");
			        		sql[i].append(" 			SELECT ");
			        		sql[i].append(" 					TRUNCATE(IFNULL(sum(a.fixedPrice), 0),2) ");
			        		sql[i].append(" 			FROM ");
			        		sql[i].append(" 				t_hotelmanage t ");
			        		sql[i].append(" 			LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
			        		sql[i].append(" 			WHERE ");
			        		sql[i].append(" 				t.housegrade = "+i);
			        		sql[i].append(" 			AND a.`year` =  "+year);
			        		sql[i].append(" 			UNION ALL ");
			        		sql[i].append(" 				SELECT ");
			        		sql[i].append(" 						TRUNCATE(IFNULL(sum(a.assetTotal), 0),2) ");
			        		sql[i].append(" 				FROM ");
			        		sql[i].append(" 					t_hotelmanage t ");
			        		sql[i].append(" 				LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
			        		sql[i].append(" 				WHERE ");
			        		sql[i].append(" 					t.housegrade = "+i);
			        		sql[i].append(" 				AND a.`year` =  "+year);
			        		sql[i].append(" 				UNION ALL ");
			        		sql[i].append(" 					SELECT ");
			        		sql[i].append(" 							TRUNCATE(IFNULL(sum(a.liabilitiesTotal), 0),2) ");
			        		sql[i].append(" 					FROM ");
			        		sql[i].append(" 						t_hotelmanage t ");
			        		sql[i].append(" 					LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
			        		sql[i].append(" 					WHERE ");
			        		sql[i].append(" 						t.housegrade = "+i);
			        		sql[i].append(" 					AND a.`year` =  "+year);
			        		sql[i].append(" 					UNION ALL ");
			        		sql[i].append(" 						SELECT ");
			        		sql[i].append(" 								TRUNCATE(IFNULL(sum(a.possessorTotal), 0),2) ");
			        		sql[i].append(" 						FROM ");
			        		sql[i].append(" 							t_hotelmanage t ");
			        		sql[i].append(" 						LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
			        		sql[i].append(" 						WHERE ");
			        		sql[i].append(" 							t.housegrade = "+i);
			        		sql[i].append(" 						AND a.`year` =  "+year);
			        		sql[i].append(" 						UNION ALL ");
			        		sql[i].append(" 							SELECT ");
			        		sql[i].append(" 								count(*) ");
			        		sql[i].append(" 							FROM ");
			        		sql[i].append(" 								t_hotelmanage t ");
			        		sql[i].append(" 							WHERE ");
			        		sql[i].append(" 								t.housegrade = "+i);
			        		sql[i].append(" 							AND t.registerstyle = '国有' ");
			        		sql[i].append(" 							UNION ALL ");
			        		sql[i].append(" 								SELECT ");
			        		sql[i].append(" 									count(*) ");
			        		sql[i].append(" 								FROM ");
			        		sql[i].append(" 									t_hotelmanage t ");
			        		sql[i].append(" 								WHERE ");
			        		sql[i].append(" 									t.housegrade = "+i);
			        		sql[i].append(" 								AND t.registerstyle = '集体' ");
			        		sql[i].append(" 								UNION ALL ");
			        		sql[i].append(" 									SELECT ");
			        		sql[i].append(" 										count(*) ");
			        		sql[i].append(" 									FROM ");
			        		sql[i].append(" 										t_hotelmanage t ");
			        		sql[i].append(" 									WHERE ");
			        		sql[i].append(" 										t.housegrade = "+i);
			        		sql[i].append(" 									AND t.registerstyle = '股份合作' ");
			        		sql[i].append(" 									UNION ALL ");
			        		sql[i].append(" 										SELECT ");
			        		sql[i].append(" 											count(*) ");
			        		sql[i].append(" 										FROM ");
			        		sql[i].append(" 											t_hotelmanage t ");
			        		sql[i].append(" 										WHERE ");
			        		sql[i].append(" 											t.housegrade = "+i);
			        		sql[i].append(" 										AND t.registerstyle = '国有联营' ");
			        		sql[i].append(" 										UNION ALL ");
			        		sql[i].append(" 											SELECT ");
			        		sql[i].append(" 												count(*) ");
			        		sql[i].append(" 											FROM ");
			        		sql[i].append(" 												t_hotelmanage t ");
			        		sql[i].append(" 											WHERE ");
			        		sql[i].append(" 												t.housegrade = "+i);
			        		sql[i].append(" 											AND t.registerstyle = '集体联营' ");
			        		sql[i].append(" 											UNION ALL ");
			        		sql[i].append(" 												SELECT ");
			        		sql[i].append(" 													count(*) ");
			        		sql[i].append(" 												FROM ");
			        		sql[i].append(" 													t_hotelmanage t ");
			        		sql[i].append(" 												WHERE ");
			        		sql[i].append(" 													t.housegrade = "+i);
			        		sql[i].append(" 												AND t.registerstyle = '国有与集体联营' ");
			        		sql[i].append(" 												UNION ALL ");
			        		sql[i].append(" 													SELECT ");
			        		sql[i].append(" 														count(*) ");
			        		sql[i].append(" 													FROM ");
			        		sql[i].append(" 														t_hotelmanage t ");
			        		sql[i].append(" 													WHERE ");
			        		sql[i].append(" 														t.housegrade = "+i);
			        		sql[i].append(" 													AND t.registerstyle = '其他联营' ");
			        		sql[i].append(" 													UNION ALL ");
			        		sql[i].append(" 														SELECT ");
			        		sql[i].append(" 															count(*) ");
			        		sql[i].append(" 														FROM ");
			        		sql[i].append(" 															t_hotelmanage t ");
			        		sql[i].append(" 														WHERE ");
			        		sql[i].append(" 															t.housegrade = "+i);
			        		sql[i].append(" 														AND t.registerstyle = '国有独资公司' ");
			        		sql[i].append(" 														UNION ALL ");
			        		sql[i].append(" 															SELECT ");
			        		sql[i].append(" 																count(*) ");
			        		sql[i].append(" 															FROM ");
			        		sql[i].append(" 																t_hotelmanage t ");
			        		sql[i].append(" 															WHERE ");
			        		sql[i].append(" 																t.housegrade = "+i);
			        		sql[i].append(" 															AND t.registerstyle = '其他有限责任公司' ");
			        		sql[i].append(" 															UNION ALL ");
			        		sql[i].append(" 																SELECT ");
			        		sql[i].append(" 																	count(*) ");
			        		sql[i].append(" 																FROM ");
			        		sql[i].append(" 																	t_hotelmanage t ");
			        		sql[i].append(" 																WHERE ");
			        		sql[i].append(" 																	t.housegrade = "+i);
			        		sql[i].append(" 																AND t.registerstyle = '股份有限公司' ");
			        		sql[i].append(" 																UNION ALL ");
			        		sql[i].append(" 																	SELECT ");
			        		sql[i].append(" 																		count(*) ");
			        		sql[i].append(" 																	FROM ");
			        		sql[i].append(" 																		t_hotelmanage t ");
			        		sql[i].append(" 																	WHERE ");
			        		sql[i].append(" 																		t.housegrade = "+i);
			        		sql[i].append(" 																	AND t.registerstyle = '私营独资' ");
			        		sql[i].append(" 																	UNION ALL ");
			        		sql[i].append(" 																		SELECT ");
			        		sql[i].append(" 																			count(*) ");
			        		sql[i].append(" 																		FROM ");
			        		sql[i].append(" 																			t_hotelmanage t ");
			        		sql[i].append(" 																		WHERE ");
			        		sql[i].append(" 																			t.housegrade = "+i);
			        		sql[i].append(" 																		AND t.registerstyle = '私营合伙' ");
			        		sql[i].append(" 																		UNION ALL ");
			        		sql[i].append(" 																			SELECT ");
			        		sql[i].append(" 																				count(*) ");
			        		sql[i].append(" 																			FROM ");
			        		sql[i].append(" 																				t_hotelmanage t ");
			        		sql[i].append(" 																			WHERE ");
			        		sql[i].append(" 																				t.housegrade = "+i);
			        		sql[i].append(" 																			AND t.registerstyle = '私营有限责任公司' ");
			        		sql[i].append(" 																			UNION ALL ");
			        		sql[i].append(" 																				SELECT ");
			        		sql[i].append(" 																					count(*) ");
			        		sql[i].append(" 																				FROM ");
			        		sql[i].append(" 																					t_hotelmanage t ");
			        		sql[i].append(" 																				WHERE ");
			        		sql[i].append(" 																					t.housegrade = "+i);
			        		sql[i].append(" 																				AND t.registerstyle = '私营股份有限公司' ");
			        		sql[i].append(" 																				UNION ALL ");
			        		sql[i].append(" 																					SELECT ");
			        		sql[i].append(" 																						count(*) ");
			        		sql[i].append(" 																					FROM ");
			        		sql[i].append(" 																						t_hotelmanage t ");
			        		sql[i].append(" 																					WHERE ");
			        		sql[i].append(" 																						t.housegrade = "+i);
			        		sql[i].append(" 																					AND t.registerstyle = '其他' ");
			        		sql[i].append(" 																					UNION ALL ");
			        		sql[i].append(" 																						SELECT ");
			        		sql[i].append(" 																							count(*) ");
			        		sql[i].append(" 																						FROM ");
			        		sql[i].append(" 																							t_hotelmanage t ");
			        		sql[i].append(" 																						WHERE ");
			        		sql[i].append(" 																							t.housegrade = "+i);
			        		sql[i].append(" 																						AND t.registerstyle = '与港澳台商合资经营' ");
			        		sql[i].append(" 																						UNION ALL ");
			        		sql[i].append(" 																							SELECT ");
			        		sql[i].append(" 																								count(*) ");
			        		sql[i].append(" 																							FROM ");
			        		sql[i].append(" 																								t_hotelmanage t ");
			        		sql[i].append(" 																							WHERE ");
			        		sql[i].append(" 																								t.housegrade = "+i);
			        		sql[i].append(" 																							AND t.registerstyle = '与港澳台商合作经营' ");
			        		sql[i].append(" 																							UNION ALL ");
			        		sql[i].append(" 																								SELECT ");
			        		sql[i].append(" 																									count(*) ");
			        		sql[i].append(" 																								FROM ");
			        		sql[i].append(" 																									t_hotelmanage t ");
			        		sql[i].append(" 																								WHERE ");
			        		sql[i].append(" 																									t.housegrade = "+i);
			        		sql[i].append(" 																								AND t.registerstyle = '港澳台商独资' ");
			        		sql[i].append(" 																								UNION ALL ");
			        		sql[i].append(" 																									SELECT ");
			        		sql[i].append(" 																										count(*) ");
			        		sql[i].append(" 																									FROM ");
			        		sql[i].append(" 																										t_hotelmanage t ");
			        		sql[i].append(" 																									WHERE ");
			        		sql[i].append(" 																										t.housegrade = "+i);
			        		sql[i].append(" 																									AND t.registerstyle = '港澳台商投资股份有限公司' ");
			        		sql[i].append(" 																									UNION ALL ");
			        		sql[i].append(" 																										SELECT ");
			        		sql[i].append(" 																											count(*) ");
			        		sql[i].append(" 																										FROM ");
			        		sql[i].append(" 																											t_hotelmanage t ");
			        		sql[i].append(" 																										WHERE ");
			        		sql[i].append(" 																											t.housegrade = "+i);
			        		sql[i].append(" 																										AND t.registerstyle = '中外合资经营' ");
			        		sql[i].append(" 																										UNION ALL ");
			        		sql[i].append(" 																											SELECT ");
			        		sql[i].append(" 																												count(*) ");
			        		sql[i].append(" 																											FROM ");
			        		sql[i].append(" 																												t_hotelmanage t ");
			        		sql[i].append(" 																											WHERE ");
			        		sql[i].append(" 																												t.housegrade = "+i);
			        		sql[i].append(" 																											AND t.registerstyle = '中外合作经营' ");
			        		sql[i].append(" 																											UNION ALL ");
			        		sql[i].append(" 																												SELECT ");
			        		sql[i].append(" 																													count(*) ");
			        		sql[i].append(" 																												FROM ");
			        		sql[i].append(" 																													t_hotelmanage t ");
			        		sql[i].append(" 																												WHERE ");
			        		sql[i].append(" 																													t.housegrade = "+i);
			        		sql[i].append(" 																												AND t.registerstyle = '外资企业' ");
			        		sql[i].append(" 																												UNION ALL ");
			        		sql[i].append(" 																													SELECT ");
			        		sql[i].append(" 																														count(*) ");
			        		sql[i].append(" 																													FROM ");
			        		sql[i].append(" 																														t_hotelmanage t ");
			        		sql[i].append(" 																													WHERE ");
			        		sql[i].append(" 																														t.housegrade = "+i);
			        		sql[i].append(" 																													AND t.registerstyle = '外商投资股份有限公司' ");
			        		sql[i].append(" 																													UNION ALL ");
			        		sql[i].append(" 																														SELECT ");
			        		sql[i].append(" 																															count(*) ");
			        		sql[i].append(" 																														FROM ");
			        		sql[i].append(" 																															t_hotelmanage t ");
			        		sql[i].append(" 																														WHERE ");
			        		sql[i].append(" 																															t.housegrade = "+i);
			        	
			        		sql[i].append(" 																														AND t.registerstyle = '其他外商投资' ");
			        		emds = new ExportService(2,i,1);
			        		if(i==4){
			        			workbook = emds.getNewModelInfos2(modelPath, sql[i].toString());
			        		}else{
			        			workbook = emds.getNewModelInfos2(modelPath, sql[i].toString(),workbook);
			        		}
			        		
			        		
			        		
			        		
			        }
			        StringBuffer sqll=new StringBuffer();
			        sqll.append(" SELECT ");
			        sqll.append(" 	count(*) ");
			        sqll.append(" FROM ");
			        sqll.append(" 	t_hotelmanage t ");
			        sqll.append(" WHERE ");
			        sqll.append(" 	t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" UNION ALL ");
	        		sqll.append(" 	SELECT ");
	        		sqll.append(" 		IFNULL(sum(a.room), 0) ");
	        		sqll.append(" 	FROM ");
	        		sqll.append(" 		t_hotelmanage t ");
	        		sqll.append(" 	LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        		sqll.append(" 	WHERE ");
	        		sqll.append(" 		t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 	AND a.`year` =  "+year);
	        		sqll.append(" 	UNION ALL ");
	        		sqll.append(" 		SELECT ");
	        		sqll.append(" 			IFNULL(sum(a.bed), 0) ");
	        		sqll.append(" 		FROM ");
	        		sqll.append(" 			t_hotelmanage t ");
	        		sqll.append(" 		LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        		sqll.append(" 		WHERE ");
	        		sqll.append(" 			t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 		AND a.`year` =  "+year);
	        		sqll.append(" 		UNION ALL ");
	        		sqll.append(" 			SELECT ");
	        		sqll.append(" 					TRUNCATE(IFNULL(sum(a.fixedPrice), 0),2) ");
	        		sqll.append(" 			FROM ");
	        		sqll.append(" 				t_hotelmanage t ");
	        		sqll.append(" 			LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        		sqll.append(" 			WHERE ");
	        		sqll.append(" 				t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 			AND a.`year` =  "+year);
	        		sqll.append(" 			UNION ALL ");
	        		sqll.append(" 				SELECT ");
	        		sqll.append(" 						TRUNCATE(IFNULL(sum(a.assetTotal), 0),2) ");
	        		sqll.append(" 				FROM ");
	        		sqll.append(" 					t_hotelmanage t ");
	        		sqll.append(" 				LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        		sqll.append(" 				WHERE ");
	        		sqll.append(" 					t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 				AND a.`year` =  "+year);
	        		sqll.append(" 				UNION ALL ");
	        		sqll.append(" 					SELECT ");
	        		sqll.append(" 							TRUNCATE(IFNULL(sum(a.liabilitiesTotal), 0),2) ");
	        		sqll.append(" 					FROM ");
	        		sqll.append(" 						t_hotelmanage t ");
	        		sqll.append(" 					LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        		sqll.append(" 					WHERE ");
	        		sqll.append(" 						t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 					AND a.`year` =  "+year);
	        		sqll.append(" 					UNION ALL ");
	        		sqll.append(" 						SELECT ");
	        		sqll.append(" 								TRUNCATE(IFNULL(sum(a.possessorTotal), 0),2) ");
	        		sqll.append(" 						FROM ");
	        		sqll.append(" 							t_hotelmanage t ");
	        		sqll.append(" 						LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
	        		sqll.append(" 						WHERE ");
	        		sqll.append(" 							t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 						AND a.`year` =  "+year);
	        		sqll.append(" 						UNION ALL ");
	        		sqll.append(" 							SELECT ");
	        		sqll.append(" 								count(*) ");
	        		sqll.append(" 							FROM ");
	        		sqll.append(" 								t_hotelmanage t ");
	        		sqll.append(" 							WHERE ");
	        		sqll.append(" 								t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 							AND t.registerstyle = '国有' ");
	        		sqll.append(" 							UNION ALL ");
	        		sqll.append(" 								SELECT ");
	        		sqll.append(" 									count(*) ");
	        		sqll.append(" 								FROM ");
	        		sqll.append(" 									t_hotelmanage t ");
	        		sqll.append(" 								WHERE ");
	        		sqll.append(" 									t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 								AND t.registerstyle = '集体' ");
	        		sqll.append(" 								UNION ALL ");
	        		sqll.append(" 									SELECT ");
	        		sqll.append(" 										count(*) ");
	        		sqll.append(" 									FROM ");
	        		sqll.append(" 										t_hotelmanage t ");
	        		sqll.append(" 									WHERE ");
	        		sqll.append(" 										t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 									AND t.registerstyle = '股份合作' ");
	        		sqll.append(" 									UNION ALL ");
	        		sqll.append(" 										SELECT ");
	        		sqll.append(" 											count(*) ");
	        		sqll.append(" 										FROM ");
	        		sqll.append(" 											t_hotelmanage t ");
	        		sqll.append(" 										WHERE ");
	        		sqll.append(" 											t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 										AND t.registerstyle = '国有联营' ");
	        		sqll.append(" 										UNION ALL ");
	        		sqll.append(" 											SELECT ");
	        		sqll.append(" 												count(*) ");
	        		sqll.append(" 											FROM ");
	        		sqll.append(" 												t_hotelmanage t ");
	        		sqll.append(" 											WHERE ");
	        		sqll.append(" 												t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 											AND t.registerstyle = '集体联营' ");
	        		sqll.append(" 											UNION ALL ");
	        		sqll.append(" 												SELECT ");
	        		sqll.append(" 													count(*) ");
	        		sqll.append(" 												FROM ");
	        		sqll.append(" 													t_hotelmanage t ");
	        		sqll.append(" 												WHERE ");
	        		sqll.append(" 													t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 												AND t.registerstyle = '国有与集体联营' ");
	        		sqll.append(" 												UNION ALL ");
	        		sqll.append(" 													SELECT ");
	        		sqll.append(" 														count(*) ");
	        		sqll.append(" 													FROM ");
	        		sqll.append(" 														t_hotelmanage t ");
	        		sqll.append(" 													WHERE ");
	        		sqll.append(" 														t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 													AND t.registerstyle = '其他联营' ");
	        		sqll.append(" 													UNION ALL ");
	        		sqll.append(" 														SELECT ");
	        		sqll.append(" 															count(*) ");
	        		sqll.append(" 														FROM ");
	        		sqll.append(" 															t_hotelmanage t ");
	        		sqll.append(" 														WHERE ");
	        		sqll.append(" 															t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 														AND t.registerstyle = '国有独资公司' ");
	        		sqll.append(" 														UNION ALL ");
	        		sqll.append(" 															SELECT ");
	        		sqll.append(" 																count(*) ");
	        		sqll.append(" 															FROM ");
	        		sqll.append(" 																t_hotelmanage t ");
	        		sqll.append(" 															WHERE ");
	        		sqll.append(" 																t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 															AND t.registerstyle = '其他有限责任公司' ");
	        		sqll.append(" 															UNION ALL ");
	        		sqll.append(" 																SELECT ");
	        		sqll.append(" 																	count(*) ");
	        		sqll.append(" 																FROM ");
	        		sqll.append(" 																	t_hotelmanage t ");
	        		sqll.append(" 																WHERE ");
	        		sqll.append(" 																	t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																AND t.registerstyle = '股份有限公司' ");
	        		sqll.append(" 																UNION ALL ");
	        		sqll.append(" 																	SELECT ");
	        		sqll.append(" 																		count(*) ");
	        		sqll.append(" 																	FROM ");
	        		sqll.append(" 																		t_hotelmanage t ");
	        		sqll.append(" 																	WHERE ");
	        		sqll.append(" 																		t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																	AND t.registerstyle = '私营独资' ");
	        		sqll.append(" 																	UNION ALL ");
	        		sqll.append(" 																		SELECT ");
	        		sqll.append(" 																			count(*) ");
	        		sqll.append(" 																		FROM ");
	        		sqll.append(" 																			t_hotelmanage t ");
	        		sqll.append(" 																		WHERE ");
	        		sqll.append(" 																			t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																		AND t.registerstyle = '私营合伙' ");
	        		sqll.append(" 																		UNION ALL ");
	        		sqll.append(" 																			SELECT ");
	        		sqll.append(" 																				count(*) ");
	        		sqll.append(" 																			FROM ");
	        		sqll.append(" 																				t_hotelmanage t ");
	        		sqll.append(" 																			WHERE ");
	        		sqll.append(" 																				t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																			AND t.registerstyle = '私营有限责任公司' ");
	        		sqll.append(" 																			UNION ALL ");
	        		sqll.append(" 																				SELECT ");
	        		sqll.append(" 																					count(*) ");
	        		sqll.append(" 																				FROM ");
	        		sqll.append(" 																					t_hotelmanage t ");
	        		sqll.append(" 																				WHERE ");
	        		sqll.append(" 																					t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																				AND t.registerstyle = '私营股份有限公司' ");
	        		sqll.append(" 																				UNION ALL ");
	        		sqll.append(" 																					SELECT ");
	        		sqll.append(" 																						count(*) ");
	        		sqll.append(" 																					FROM ");
	        		sqll.append(" 																						t_hotelmanage t ");
	        		sqll.append(" 																					WHERE ");
	        		sqll.append(" 																						t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																					AND t.registerstyle = '其他' ");
	        		sqll.append(" 																					UNION ALL ");
	        		sqll.append(" 																						SELECT ");
	        		sqll.append(" 																							count(*) ");
	        		sqll.append(" 																						FROM ");
	        		sqll.append(" 																							t_hotelmanage t ");
	        		sqll.append(" 																						WHERE ");
	        		sqll.append(" 																							t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																						AND t.registerstyle = '与港澳台商合资经营' ");
	        		sqll.append(" 																						UNION ALL ");
	        		sqll.append(" 																							SELECT ");
	        		sqll.append(" 																								count(*) ");
	        		sqll.append(" 																							FROM ");
	        		sqll.append(" 																								t_hotelmanage t ");
	        		sqll.append(" 																							WHERE ");
	        		sqll.append(" 																								t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																							AND t.registerstyle = '与港澳台商合作经营' ");
	        		sqll.append(" 																							UNION ALL ");
	        		sqll.append(" 																								SELECT ");
	        		sqll.append(" 																									count(*) ");
	        		sqll.append(" 																								FROM ");
	        		sqll.append(" 																									t_hotelmanage t ");
	        		sqll.append(" 																								WHERE ");
	        		sqll.append(" 																									t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																								AND t.registerstyle = '港澳台商独资' ");
	        		sqll.append(" 																								UNION ALL ");
	        		sqll.append(" 																									SELECT ");
	        		sqll.append(" 																										count(*) ");
	        		sqll.append(" 																									FROM ");
	        		sqll.append(" 																										t_hotelmanage t ");
	        		sqll.append(" 																									WHERE ");
	        		sqll.append(" 																										t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																									AND t.registerstyle = '港澳台商投资股份有限公司' ");
	        		sqll.append(" 																									UNION ALL ");
	        		sqll.append(" 																										SELECT ");
	        		sqll.append(" 																											count(*) ");
	        		sqll.append(" 																										FROM ");
	        		sqll.append(" 																											t_hotelmanage t ");
	        		sqll.append(" 																										WHERE ");
	        		sqll.append(" 																											t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																										AND t.registerstyle = '中外合资经营' ");
	        		sqll.append(" 																										UNION ALL ");
	        		sqll.append(" 																											SELECT ");
	        		sqll.append(" 																												count(*) ");
	        		sqll.append(" 																											FROM ");
	        		sqll.append(" 																												t_hotelmanage t ");
	        		sqll.append(" 																											WHERE ");
	        		sqll.append(" 																												t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																											AND t.registerstyle = '中外合作经营' ");
	        		sqll.append(" 																											UNION ALL ");
	        		sqll.append(" 																												SELECT ");
	        		sqll.append(" 																													count(*) ");
	        		sqll.append(" 																												FROM ");
	        		sqll.append(" 																													t_hotelmanage t ");
	        		sqll.append(" 																												WHERE ");
	        		sqll.append(" 																													t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																												AND t.registerstyle = '外资企业' ");
	        		sqll.append(" 																												UNION ALL ");
	        		sqll.append(" 																													SELECT ");
	        		sqll.append(" 																														count(*) ");
	        		sqll.append(" 																													FROM ");
	        		sqll.append(" 																														t_hotelmanage t ");
	        		sqll.append(" 																													WHERE ");
	        		sqll.append(" 																														t.housegrade  in(4,5,6,7,8) ");
	        		sqll.append(" 																													AND t.registerstyle = '外商投资股份有限公司' ");
	        		sqll.append(" 																													UNION ALL ");
	        		sqll.append(" 																														SELECT ");
	        		sqll.append(" 																															count(*) ");
	        		sqll.append(" 																														FROM ");
	        		sqll.append(" 																															t_hotelmanage t ");
	        		sqll.append(" 																														WHERE ");
	        		sqll.append(" 																															t.housegrade  in(4,5,6,7,8) ");
	        	
	        		sqll.append(" 																														AND t.registerstyle = '其他外商投资' ");
	        		emds = new ExportService(2,9,1);
	        		workbook = emds.getNewModelInfos2(modelPath, sqll.toString(),workbook);
	        		
	        		
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
		//年报数据排序（excel表）
				@RequestMapping(params = "excelExport9")
				public void export9(HttpServletRequest request, HttpServletResponse response) throws Exception{

					request.setCharacterEncoding("UTF-8");
			        response.setCharacterEncoding("UTF-8");
			        response.setContentType("text/html;charset=UTF-8");
			        
			        // 获取参数 
			        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
			       // String area = new String(request.getParameter("area").getBytes("iso8859-1"),"utf-8");
			        String excelName = "年报数据排序（excel表）.xls";
			        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
			        StringBuffer sql = new StringBuffer();
			        sql.append("SELECT  t.`code`,t.`name`,t.streamTotal from t_hotel_annual t LEFT JOIN t_hotelmanage h on t.hotel_aid=h.ID   where h.housegrade in (4,5,6,7,8) ");
			        // 组装查询条件
			        if(year !=null && year.length() != 0){
			        	sql.append(" and  t.year="+year);
			        }
			       /* if(area !=null && area.length() != 0){
			        	sql.append(" and  h.w_county="+area);
			        }*/
			        sql.append(" ORDER BY t.streamTotal DESC  ");
			        
			        ExportService emds = new ExportService(2,3); 
			        
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
									+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
				        }else{
				            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

				        }
						POIUtils.writeWorkbook(workbook, response.getOutputStream());
					} else {
						response.addHeader("Content-Disposition", "attachment;filename="
								+ URLEncoder.encode("Errors.xls", "UTF-8"));
						response.getOutputStream().print(
								"You have already downloaded the error excel!");
					}
			        
				
				}
				//年报数据排序
				@RequestMapping(params = "excelExport10")
				public void export10(HttpServletRequest request, HttpServletResponse response) throws Exception{

					request.setCharacterEncoding("UTF-8");
			        response.setCharacterEncoding("UTF-8");
			        response.setContentType("text/html;charset=UTF-8");
			        
			        // 获取参数 
			        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
			        String excelName = "年报数据排序（excel表）.xls";
			        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
			        StringBuffer sql = new StringBuffer();
			        sql.append("SELECT  t.`code`,t.`name`,t.streamTotal from t_hotel_annual t LEFT JOIN t_hotelmanage h on t.hotel_aid=h.ID   where h.housegrade in (4,5,6,7,8) ");
			        // 组装查询条件
			        if(year !=null && year.length() != 0){
			        	sql.append(" and  t.year="+year);
			        }
			      
			        
			        ExportService emds = new ExportService(2,3); 
			        
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
									+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
				        }else{
				            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

				        }
						POIUtils.writeWorkbook(workbook, response.getOutputStream());
					} else {
						response.addHeader("Content-Disposition", "attachment;filename="
								+ URLEncoder.encode("Errors.xls", "UTF-8"));
						response.getOutputStream().print(
								"You have already downloaded the error excel!");
					}
			        
				
				}
				
				//主要经济指标汇总表（excel表）
				@RequestMapping(params = "excelExport11")
				public void export11(HttpServletRequest request, HttpServletResponse response) throws Exception{

					request.setCharacterEncoding("UTF-8");
			        response.setCharacterEncoding("UTF-8");
			        response.setContentType("text/html;charset=UTF-8");
			        
			        // 获取参数 
			        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
			        String excelName = "主要经济指标汇总表（excel表）.xls";
			        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
			        StringBuffer sql = new StringBuffer();
			        sql.append(" SELECT ");
			        sql.append(" 	'三亚市', ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.operationIncome) / sum(a.people), ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) ldscl, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.profitTotal) / sum(a.people), ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) sxls, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.taxes) / sum(a.people), ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) sjsj, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.fixedPrice) / sum(a.people), ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) zygdzczz, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.operationIncome) / sum(a.fixedPrice) * 100, ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) gdzccyysr, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.roomIncome) / sum(a.actual_rent), ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) pjfj, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.actual_rent) / sum(a.for_hire) * 100, ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) pjczl, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.roomIncome) / sum(a.for_hire), ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) kfczsr, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			sum(a.operationIncome) / sum(a.room), ");
			        sql.append(" 			4 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) ptyysr ");
			        sql.append(" FROM ");
			        sql.append(" 	t_hotelmanage t ");
			        sql.append(" LEFT JOIN t_hotel_annual a ON t.ID = a.hotel_aid ");
			        // 组装查询条件
			        if(year !=null && year.length() != 0){
			        	sql.append(" and  a.year="+year);
			        }
			        
			        
			        ExportService emds = new ExportService(2,10); 
			        
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
									+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
				        }else{
				            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

				        }
						POIUtils.writeWorkbook(workbook, response.getOutputStream());
					} else {
						response.addHeader("Content-Disposition", "attachment;filename="
								+ URLEncoder.encode("Errors.xls", "UTF-8"));
						response.getOutputStream().print(
								"You have already downloaded the error excel!");
					}
			        
				
				}
				
				//重要指标同比统计
				@RequestMapping(params = "excelExport12")
				public void export12(HttpServletRequest request, HttpServletResponse response) throws Exception{

					request.setCharacterEncoding("UTF-8");
			        response.setCharacterEncoding("UTF-8");
			        response.setContentType("text/html;charset=UTF-8");
			        
			        // 获取参数 
			        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
			        String lastYear=String.valueOf(Integer.valueOf(year)-1);
			        String excelName = "星级饭店重要指标同比统计.xls";
			        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
			        StringBuffer sql = new StringBuffer();

			        sql.append(" SELECT ");
			        sql.append(" 	a.code1 CODE, ");
			        sql.append(" 	a.name1 NAME, ");
			        sql.append(" 	a.avgprice1 avgprice, ");
			        sql.append(" 	a.avgrate1 avgrate, ");
			        sql.append(" 	a.avgpro1 avgpro, ");
			        sql.append(" 	a.avgtax1 avgtax, ");
			        sql.append(" 	a.avgldrates1 avgldrates, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			(a.avgprice1 - a.avgprice2) / a.avgprice2, ");
			        sql.append(" 			2 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) tbavgprice, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			(a.avgrate1 - a.avgrate2) / a.avgrate2, ");
			        sql.append(" 			2 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) tbavgrate, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			(a.avgpro1 - a.avgpro2) / a.avgpro2, ");
			        sql.append(" 			2 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) tbavgpro, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			(a.avgtax1 - a.avgtax2) / a.avgtax2, ");
			        sql.append(" 			2 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) tbavgtax, ");
			        sql.append(" 	IFNULL( ");
			        sql.append(" 		TRUNCATE ( ");
			        sql.append(" 			( ");
			        sql.append(" 				a.avgldrates1 - a.avgldrates2 ");
			        sql.append(" 			) / a.avgldrates2, ");
			        sql.append(" 			2 ");
			        sql.append(" 		), ");
			        sql.append(" 		0 ");
			        sql.append(" 	) tbavgldrates ");
			        sql.append(" FROM ");
			        sql.append(" 	( ");
			        sql.append(" 		SELECT ");
			        sql.append(" 			t. CODE code1, ");
			        sql.append(" 			t.unitname name1, ");
			        sql.append(" 			IFNULL( ");
			        sql.append(" 				TRUNCATE ( ");
			        sql.append(" 					sum(a.roomIncome) / sum(a.actual_rent), ");
			        sql.append(" 					4 ");
			        sql.append(" 				), ");
			        sql.append(" 				0 ");
			        sql.append(" 			) avgprice1, ");
			        sql.append(" 			IFNULL( ");
			        sql.append(" 				TRUNCATE ( ");
			        sql.append(" 					sum(a.actual_rent) / sum(a.for_hire), ");
			        sql.append(" 					4 ");
			        sql.append(" 				), ");
			        sql.append(" 				0 ");
			        sql.append(" 			) avgrate1, ");
			        sql.append(" 			IFNULL( ");
			        sql.append(" 				TRUNCATE ( ");
			        sql.append(" 					sum(a.profitTotal) / sum(a.people), ");
			        sql.append(" 					4 ");
			        sql.append(" 				), ");
			        sql.append(" 				0 ");
			        sql.append(" 			) avgpro1, ");
			        sql.append(" 			IFNULL( ");
			        sql.append(" 				TRUNCATE ( ");
			        sql.append(" 					sum(a.taxes) / sum(a.people), ");
			        sql.append(" 					4 ");
			        sql.append(" 				), ");
			        sql.append(" 				0 ");
			        sql.append(" 			) avgtax1, ");
			        sql.append(" 			IFNULL( ");
			        sql.append(" 				TRUNCATE ( ");
			        sql.append(" 					sum(a.operationIncome) / sum(a.people), ");
			        sql.append(" 					4 ");
			        sql.append(" 				), ");
			        sql.append(" 				0 ");
			        sql.append(" 			) avgldrates1, ");
			        sql.append(" 			( ");
			        sql.append(" 				SELECT ");
			        sql.append(" 					IFNULL( ");
			        sql.append(" 						sum(a1.roomIncome) / sum(a1.actual_rent), ");
			        sql.append(" 						0 ");
			        sql.append(" 					) ");
			        sql.append(" 				FROM ");
			        sql.append(" 					t_hotel_annual a1 ");
			        sql.append(" 				WHERE ");
			        sql.append(" 					t.id = a1.hotel_aid ");
			        sql.append(" 				AND a1.`year` =  "+lastYear);
			        sql.append(" 			) avgprice2, ");
			        sql.append(" 			( ");
			        sql.append(" 				SELECT ");
			        sql.append(" 					IFNULL( ");
			        sql.append(" 						sum(a1.actual_rent) / sum(a1.for_hire), ");
			        sql.append(" 						0 ");
			        sql.append(" 					) ");
			        sql.append(" 				FROM ");
			        sql.append(" 					t_hotel_annual a1 ");
			        sql.append(" 				WHERE ");
			        sql.append(" 					t.id = a1.hotel_aid ");
			        sql.append(" 				AND a1.`year` =  "+lastYear);
			        sql.append(" 			) avgrate2, ");
			        sql.append(" 			( ");
			        sql.append(" 				SELECT ");
			        sql.append(" 					IFNULL( ");
			        sql.append(" 						sum(a1.profitTotal) / sum(a1.people), ");
			        sql.append(" 						0 ");
			        sql.append(" 					) ");
			        sql.append(" 				FROM ");
			        sql.append(" 					t_hotel_annual a1 ");
			        sql.append(" 				WHERE ");
			        sql.append(" 					t.id = a1.hotel_aid ");
			        sql.append(" 				AND a1.`year` =  "+lastYear);
			        sql.append(" 			) avgpro2, ");
			        sql.append(" 			( ");
			        sql.append(" 				SELECT ");
			        sql.append(" 					IFNULL( ");
			        sql.append(" 						sum(a1.taxes) / sum(a1.people), ");
			        sql.append(" 						0 ");
			        sql.append(" 					) ");
			        sql.append(" 				FROM ");
			        sql.append(" 					t_hotel_annual a1 ");
			        sql.append(" 				WHERE ");
			        sql.append(" 					t.id = a1.hotel_aid ");
			        sql.append(" 				AND a1.`year` =  "+lastYear);
			        sql.append(" 			) avgtax2, ");
			        sql.append(" 			( ");
			        sql.append(" 				SELECT ");
			        sql.append(" 					IFNULL( ");
			        sql.append(" 						sum(a1.operationIncome) / sum(a1.people), ");
			        sql.append(" 						0 ");
			        sql.append(" 					) ");
			        sql.append(" 				FROM ");
			        sql.append(" 					t_hotel_annual a1 ");
			        sql.append(" 				WHERE ");
			        sql.append(" 					t.id = a1.hotel_aid ");
			        sql.append(" 				AND a1.`year` =  "+lastYear);
			        sql.append(" 			) avgldrates2 ");
			        sql.append(" 		FROM ");
			        sql.append(" 			t_hotel_annual a ");
			        sql.append(" 		LEFT JOIN t_hotelmanage t ON t.ID = a.hotel_aid ");
			        sql.append(" 		WHERE ");
			        sql.append(" 			a.`year` =  "+year);
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
									+ new String((year+excelName).getBytes("UTF-8"),"iso-8859-1"));
				        }else{
				            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(year+excelName, "UTF-8"));

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
