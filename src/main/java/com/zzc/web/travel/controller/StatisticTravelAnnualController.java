package com.zzc.web.travel.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.util.StringUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.system.service.SystemService;

/**
 * 
 *                  
 * @date: 2017年1月9日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: StatisticTravelquarterController.java
 * @Version: 1.0
 * @About: 
 *旅行社季报统计之国内统计
 */
@Scope("prototype")
@Controller
@RequestMapping("statisticTravelAnnualController")
public class StatisticTravelAnnualController extends BaseController {
	private String message = null;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(InStatisticTravelquarterController.class);
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	//统计下载页面
	@RequestMapping(params = "toexports")
	public ModelAndView toexports(HttpServletRequest request) {
		List<String> yearList = this.systemService.findListbySql("SELECT distinct ta.`year` AS year FROM  t_travelAgency_annual ta");		
		request.setAttribute("yearList", yearList);
		return new ModelAndView("travelstatisticAnnual/travelAnnual");
	}
	
	
	
	//组织人天和接待人天之和统计（按地区）（报送）
	@RequestMapping(params = "toInlandByAreaZuzhiJieDaiDay")
	public ModelAndView toInAimlist(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/innlandByAreaZuzhiJieDaiDay");
	}
	
	//旅行社国内游接待人次情况表(按旅行社 接待人次)
	@RequestMapping(params = "toJiedaiByTravel")
	public ModelAndView toInAimlistByTravel(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/inlandJiedaiByTravel");
	}
		private String getParam(HttpServletRequest request, String string) {
		return (String)request.getParameter(string);
	}
	
//*********************************导出*************************************************************	
/*	//旅行社年度财务状况
	@RequestMapping(params = "exportsxls1")
	public String exportXls1(HttpServletRequest request,HttpServletResponse response, ModelMap map
			) {
		
		String hql="from com.zzc.web.travel.entity.STTravelAnnualFinance tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		
	    List<STTravelAnnualFinance> sTTravelAnnualFinances = this.systemService.findHql(hql,null);
	    map.put(NormalExcelConstants.FILE_NAME,"旅行社年度财务状况");
	    map.put(NormalExcelConstants.CLASS,STTravelAnnualFinance.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("旅行社年度财务状况", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,sTTravelAnnualFinances);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}*/
	//旅行社国内游接待人天情况表(按旅行社)
	@RequestMapping(params = "exportsxls2")
	public String exportXls2(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String sql="SELECT	ta.`year`,"
				+ "IFNULL(SUM(ta.stream_total),0) AS streamTotal,"
				+ "IFNULL(SUM(ta.long_invest),0) AS longInvest,"
				+ "IFNULL(SUM(ta.fixed_assets),0) AS fixedAssets,"
				+"IFNULL(SUM(ta.fixed_price),0) AS fixedPrice,"
				+"IFNULL(SUM(ta.depreciation),0) AS depreciation,"
				+"IFNULL(SUM(ta.year_depreciation),0) AS yearDepreciation,"
				+"IFNULL(SUM(ta.asset_total),0) AS assetTotal,"
				+"IFNULL(SUM(ta.liabilities_total),0) AS liabilitiesTotal,"
				+"IFNULL(SUM(ta.possessor),0) AS possessor,"
				 +"IFNULL(SUM(ta.real_income),0) AS realIncome,"
				 +"IFNULL(SUM(ta.business_income),0) AS businessIncome,"
				 +"IFNULL(SUM(ta.into_income),0) AS intoIncome,"
				 +"IFNULL(SUM(ta.out_income),0) AS outIncome,"
				 +"IFNULL(SUM(ta.in_travel_income),0) AS inTravelIncome,"
				 +"IFNULL(SUM(ta.extra_gains),0) AS extraGains,"
				  +"IFNULL(SUM(ta.government_subsidies),0) AS governmentSubsidies,"
				  +"IFNULL(SUM(ta.cost),0) AS cost,"
				  +"IFNULL(SUM(ta.business_expenses),0) AS businessExpenses,"
				  +"IFNULL(SUM(ta.main_operation),0) AS mainOperation,"
				  +"IFNULL(SUM(ta.profit_in_travel),0) AS profitInTravel,"
				  +"IFNULL(SUM(ta.out_travel_business),0) AS outTravelBusiness,"
				  +"IFNULL(SUM(ta.in_travel_return),0) AS inTravelBusiness,"
				  
				  +"IFNULL(SUM(ta.manage_cost),0) AS manageCost,"
				  +"IFNULL(SUM(ta.taxes),0) AS taxes,"
				  +"IFNULL(SUM(ta.business_tex_add),0) AS businessTexAdd,"
				  +"IFNULL(SUM(ta.travel_expense),0) AS travelExpense,"
				  +"IFNULL(SUM(ta.financial_cost),0) AS financialCost,"
				  +"IFNULL(SUM(ta.interest_cost),0) AS interestCost,"
				  +"IFNULL(SUM(ta.selling_expense),0) AS sellingExpense,"
				  +"IFNULL(SUM(ta.value_variation),0) AS valueVariation,"
				  +"IFNULL(SUM(ta.operating_income),0) AS operatingIncome,"
				  +"IFNULL(SUM(ta.gross),0) AS gross,"
				  +"IFNULL(SUM(ta.invest),0) AS invest,"
				  +"IFNULL(SUM(ta.total_return),0) AS totalReturn,"
				  +"IFNULL(SUM(ta.income_tax),0) AS incomeTax,"
				  +"IFNULL(SUM(ta.employee_salary),0) AS employeeSalary,"
				  +"IFNULL(SUM(ta.add_tex),0) AS addTex,"
				  +"IFNULL(SUM(ta.num_average),0) AS numAverage,"
				  +"IFNULL(SUM(ta.college),0) AS college,"
				  +"IFNULL(SUM(ta.tour_num),0) AS tourNum,"
				  +"IFNULL(SUm(ta.lead_group),0) AS leadGroup,"
				  +"IFNULL(SUM(ta.store_num),0) AS storeNum,"
				  +"IFNULL(SUM(ta.branch_office),0) AS branchOffice,"
				  +"IFNULL(SUM(ta.subsidiary),0) AS subsidiary, "
				  +"IFNULL(SUM(ta.loss),0) AS loss "
			 +"FROM  t_travelAgency_annual ta "
				
			+"WHERE 1=1 " ;
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			sql += " and  ta.`year`="+getParam(request,"year")+"  ";
		}else{
			sql += " and  ta.`year` = '2014' ";
		}
				
		
		
		
		OutputStream os = response.getOutputStream();
 
		String filename="旅行社财务汇总统计.xls";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			filename =(getParam(request,"year")+filename);
		}
		
		//response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/force-download");// 设置强制下载不打开
		
		response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(filename,"UTF-8"));// 设置文件名
		Map<String, Object> parmass = this.systemService.findOneForJdbc(sql, null);

		
		HSSFWorkbook wb = this.replaceExcel("Sheet1",  "/com/zzc/web/export/model/财务状况汇总统计1.xls", parmass);
		wb.getSheetAt(0).getRow(0).getCell(0).setCellValue(getParam(request,"year")+"财务状况汇总统计");
		wb.write(os);
	    return null;
	
	}
	
	
	//财务状况汇总对比统计
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "exportsxls5")
	public void exportXls5(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "财务状况汇总对比统计1.xls";
        String modelPath = "/com/zzc/web/export/model/"+excelName;
        String year = request.getParameter("year");
        // 获取要查询的月份
        Calendar calendar = Calendar.getInstance();
        if(year == null || year.length() == 0) year = String.valueOf(calendar.get(Calendar.YEAR));
        String lastYear=String.valueOf(Integer.parseInt(year)-1);
		StringBuffer sql= new StringBuffer();

sql.append("SELECT	");
sql.append(" IFNULL(SUM(t1.streamTotal),0) AS streamTotal, ");
sql.append(" IFNULL(SUM(t1.longInvest),0) AS longInvest, ");
sql.append(" IFNULL(SUM(t1.fixedAssets),0) AS fixedAssets, ");
sql.append(" IFNULL(SUM(t1.fixedPrice),0) AS fixedPrice, ");
sql.append(" IFNULL(SUM(t1.depreciation),0) AS depreciation, ");
sql.append(" IFNULL(SUM(t1.yearDepreciation),0) AS yearDepreciation, ");
sql.append(" IFNULL(SUM(t1.assetTotal),0) AS assetTotal, ");
sql.append(" IFNULL(SUM(t1.liabilitiesTotal),0) AS liabilitiesTotal, ");
sql.append(" IFNULL(SUM(t1.possessor),0) AS possessor, ");
sql.append(" IFNULL(SUM(t1.realIncome),0) AS realIncome, ");
sql.append(" IFNULL(SUM(t1.businessIncome),0) AS businessIncome, ");
sql.append(" IFNULL(SUM(t1.intoIncome),0) AS intoIncome, ");
sql.append(" IFNULL(SUM(t1.outIncome),0) AS outIncome, ");
sql.append(" IFNULL(SUM(t1.inTravelIncome),0) AS inTravelIncome, ");
sql.append(" IFNULL(SUM(t1.extraGains),0) AS extraGains, ");
sql.append(" IFNULL(SUM(t1.governmentSubsidies),0) AS governmentSubsidies, ");
sql.append(" IFNULL(SUM(t1.cost),0) AS cost, ");
sql.append(" IFNULL(SUM(t1.businessExpenses),0) AS businessExpenses, ");
sql.append(" IFNULL(SUM(t1.mainOperation),0) AS mainOperation, ");
sql.append(" IFNULL(SUM(t1.profitInTravel),0) AS profitInTravel, ");
sql.append(" IFNULL(SUM(t1.outTravelBusiness),0) AS outTravelBusiness, ");
sql.append(" IFNULL(SUM(t1.inTravelBusiness),0) AS inTravelBusiness, ");
sql.append(" IFNULL(SUM(t2.leadGroup),0) AS leadGroup1, ");

sql.append(" IFNULL(SUM(t1.manageCost),0) AS manageCost, ");
sql.append(" IFNULL(SUM(t1.taxes),0) AS taxes, ");
sql.append(" IFNULL(SUM(t1.businessTexAdd),0) AS businessTexAdd, ");
sql.append(" IFNULL(SUM(t1.travelExpense),0) AS travelExpense, ");
sql.append(" IFNULL(SUM(t1.financialCost),0) AS financialCost, ");
sql.append(" IFNULL(SUM(t1.interestCost),0) AS interestCost, ");
sql.append(" IFNULL(SUM(t1.sellingExpense),0) AS sellingExpense, ");
sql.append(" IFNULL(SUM(t1.valueVariation),0) AS valueVariation, ");
sql.append(" IFNULL(SUM(t1.operatingIncome),0) AS operatingIncome, ");
sql.append(" IFNULL(SUM(t1.gross),0) AS gross, ");
sql.append(" IFNULL(SUM(t1.invest),0) AS invest, ");
sql.append(" IFNULL(SUM(t1.totalReturn),0) AS totalReturn, ");
sql.append(" IFNULL(SUM(t1.incomeTax),0) AS incomeTax, ");
sql.append(" IFNULL(SUM(t1.employeeSalary),0) AS employeeSalary, ");
sql.append(" IFNULL(SUM(t1.addTex),0) AS addTex, ");
sql.append(" IFNULL(SUM(t1.numAverage),0) AS numAverage, ");
sql.append(" IFNULL(SUM(t1.college),0) AS college, ");
sql.append(" IFNULL(SUM(t1.tourNum),0) AS tourNum, ");
sql.append(" IFNULL(SUM(t1.storeNum),0) AS storeNum, ");
sql.append(" IFNULL(SUM(t1.branchOffice),0) AS branchOffice, ");
sql.append(" IFNULL(SUM(t1.subsidiary),0) AS subsidiary,"); 
sql.append(" IFNULL(SUM(t1.loss),0) AS loss, ");



sql.append(" IFNULL(SUM(t2.streamTotal),0) AS streamTotal1, ");
sql.append(" IFNULL(SUM(t2.longInvest),0) AS longInvest1, ");
sql.append(" IFNULL(SUM(t2.fixedAssets),0) AS fixedAssets1, ");
sql.append(" IFNULL(SUM(t2.fixedPrice),0) AS fixedPrice1, ");
sql.append(" IFNULL(SUM(t2.depreciation),0) AS depreciation1, ");
sql.append(" IFNULL(SUM(t2.yearDepreciation),0) AS yearDepreciation1, ");
sql.append(" IFNULL(SUM(t2.assetTotal),0) AS assetTotal1, ");
sql.append(" IFNULL(SUM(t2.liabilitiesTotal),0) AS liabilitiesTotal1, ");
sql.append(" IFNULL(SUM(t2.possessor),0) AS possessor1, ");
sql.append(" IFNULL(SUM(t2.realIncome),0) AS realIncome1, ");
sql.append(" IFNULL(SUM(t2.businessIncome),0) AS businessIncome1, ");
sql.append(" IFNULL(SUM(t2.intoIncome),0) AS intoIncome1, ");
sql.append(" IFNULL(SUM(t2.outIncome),0) AS outIncome1, ");
sql.append(" IFNULL(SUM(t2.inTravelIncome),0) AS inTravelIncome1, ");
sql.append(" IFNULL(SUM(t2.extraGains),0) AS extraGains1, ");
sql.append(" IFNULL(SUM(t2.governmentSubsidies),0) AS governmentSubsidies1, ");
sql.append(" IFNULL(SUM(t2.cost),0) AS cost1, ");
sql.append(" IFNULL(SUM(t2.businessExpenses),0) AS businessExpenses1, ");
sql.append(" IFNULL(SUM(t2.mainOperation),0) AS mainOperation1, ");
sql.append(" IFNULL(SUM(t2.profitInTravel),0) AS profitInTravel1, ");
sql.append(" IFNULL(SUM(t2.outTravelBusiness),0) AS outTravelBusiness1, ");
sql.append(" IFNULL(SUM(t2.inTravelBusiness),0) AS inTravelBusiness1, ");

sql.append(" IFNULL(SUM(t2.manageCost),0) AS manageCost1, ");
sql.append(" IFNULL(SUM(t2.taxes),0) AS taxes1, ");
sql.append(" IFNULL(SUM(t2.businessTexAdd),0) AS businessTexAdd1, ");
sql.append(" IFNULL(SUM(t2.travelExpense),0) AS travelExpense1, ");
sql.append(" IFNULL(SUM(t2.financialCost),0) AS financialCost1, ");
sql.append(" IFNULL(SUM(t2.interestCost),0) AS interestCost1, ");
sql.append(" IFNULL(SUM(t2.sellingExpense),0) AS sellingExpense1, ");
sql.append(" IFNULL(SUM(t2.valueVariation),0) AS valueVariation1, ");
sql.append(" IFNULL(SUM(t2.operatingIncome),0) AS operatingIncome1, ");
sql.append(" IFNULL(SUM(t2.gross),0) AS gross1, ");
sql.append(" IFNULL(SUM(t2.invest),0) AS invest1, ");
sql.append(" IFNULL(SUM(t2.totalReturn),0) AS totalReturn1, ");
sql.append(" IFNULL(SUM(t2.incomeTax),0) AS incomeTax1, ");
sql.append(" IFNULL(SUM(t2.employeeSalary),0) AS employeeSalary1, ");
sql.append(" IFNULL(SUM(t2.addTex),0) AS addTex1, ");
sql.append(" IFNULL(SUM(t2.numAverage),0) AS numAverage1, ");
sql.append(" IFNULL(SUM(t2.college),0) AS college1, ");
sql.append(" IFNULL(SUM(t2.tourNum),0) AS tourNum1, ");
sql.append(" IFNULL(SUM(t2.storeNum),0) AS storeNum1, ");
sql.append(" IFNULL(SUM(t2.branchOffice),0) AS branchOffice1, ");
sql.append(" IFNULL(SUM(t2.subsidiary),0) AS subsidiary1,");
sql.append(" IFNULL(SUM(t2.loss),0) AS loss1, ");
sql.append(" IFNULL(SUM(t2.leadGroup),0) AS leadGroup1, ");

sql.append("IFNULL((SUM(t1.streamTotal)-SUM(t2.streamTotal))/SUM(t2.streamTotal),0)*100 streamTotal2,");
sql.append("IFNULL((SUM(t1.longInvest)-SUM(t2.longInvest))/SUM(t2.longInvest),0)*100 longInvest2,");
sql.append("IFNULL((SUM(t1.fixedAssets)-SUM(t2.fixedAssets))/SUM(t2.fixedAssets),0)*100 fixedAssets2,");
sql.append("IFNULL((SUM(t1.fixedPrice)-SUM(t2.fixedPrice))/SUM(t2.fixedPrice),0)*100 fixedPrice2,");
sql.append("IFNULL((SUM(t1.depreciation)-SUM(t2.depreciation))/SUM(t2.depreciation),0)*100 depreciation2,");


sql.append("IFNULL((SUM(t1.yearDepreciation)-SUM(t2.yearDepreciation))/SUM(t2.yearDepreciation),0)*100 yearDepreciation2,");
sql.append("IFNULL((SUM(t1.assetTotal)-SUM(t2.assetTotal))/SUM(t2.assetTotal),0)*100 assetTotal2,");
sql.append("IFNULL((SUM(t1.liabilitiesTotal)-SUM(t2.liabilitiesTotal))/SUM(t2.liabilitiesTotal),0)*100 liabilitiesTotal2,");
sql.append("IFNULL((SUM(t1.possessor)-SUM(t2.possessor))/SUM(t2.possessor),0)*100 possessor2,");
sql.append("IFNULL((SUM(t1.realIncome)-SUM(t2.realIncome))/SUM(t2.realIncome),0)*100 realIncome2,");

sql.append("IFNULL((SUM(t1.businessIncome)-SUM(t2.businessIncome))/SUM(t2.businessIncome),0)*100 businessIncome2,");
sql.append("IFNULL((SUM(t1.intoIncome)-SUM(t2.intoIncome))/SUM(t2.intoIncome),0)*100 intoIncome2,");
sql.append("IFNULL((SUM(t1.outIncome)-SUM(t2.outIncome))/SUM(t2.outIncome),0)*100 outIncome2,");
sql.append("IFNULL((SUM(t1.inTravelIncome)-SUM(t2.inTravelIncome))/SUM(t2.inTravelIncome),0)*100 inTravelIncome2,");
sql.append("IFNULL((SUM(t1.extraGains)-SUM(t2.extraGains))/SUM(t2.extraGains),0)*100 extraGains2,");

sql.append("IFNULL((SUM(t1.governmentSubsidies)-SUM(t2.governmentSubsidies))/SUM(t2.governmentSubsidies),0)*100 governmentSubsidies2,");
sql.append("IFNULL((SUM(t1.cost)-SUM(t2.cost))/SUM(t2.cost),0)*100 cost2,");
sql.append("IFNULL((SUM(t1.businessExpenses)-SUM(t2.businessExpenses))/SUM(t2.businessExpenses),0)*100 businessExpenses2,");
sql.append("IFNULL((SUM(t1.mainOperation)-SUM(t2.mainOperation))/SUM(t2.mainOperation),0)*100 mainOperation2,");
sql.append("IFNULL((SUM(t1.profitInTravel)-SUM(t2.profitInTravel))/SUM(t2.profitInTravel),0)*100 profitInTravel2,");


sql.append("IFNULL((SUM(t1.outTravelBusiness)-SUM(t2.outTravelBusiness))/SUM(t2.outTravelBusiness),0)*100 outTravelBusiness2,");
sql.append("IFNULL((SUM(t1.inTravelBusiness)-SUM(t2.inTravelBusiness))/SUM(t2.inTravelBusiness),0)*100 inTravelBusiness2,");
sql.append("IFNULL((SUM(t1.manageCost)-SUM(t2.manageCost))/SUM(t2.manageCost),0)*100 manageCost2,");
sql.append("IFNULL((SUM(t1.taxes)-SUM(t2.taxes))/SUM(t2.taxes),0)*100 taxes2,");
sql.append("IFNULL((SUM(t1.businessTexAdd)-SUM(t2.businessTexAdd))/SUM(t2.businessTexAdd),0)*100 businessTexAdd2,");


sql.append("IFNULL((SUM(t1.travelExpense)-SUM(t2.travelExpense))/SUM(t2.travelExpense),0)*100 travelExpense2,");
sql.append("IFNULL((SUM(t1.financialCost)-SUM(t2.financialCost))/SUM(t2.financialCost),0)*100 financialCost2,");
sql.append("IFNULL((SUM(t1.interestCost)-SUM(t2.interestCost))/SUM(t2.interestCost),0)*100 interestCost2,");
sql.append("IFNULL((SUM(t1.sellingExpense)-SUM(t2.sellingExpense))/SUM(t2.sellingExpense),0)*100 sellingExpense2,");
sql.append("IFNULL((SUM(t1.valueVariation)-SUM(t2.valueVariation))/SUM(t2.valueVariation),0)*100 valueVariation2,");


sql.append("IFNULL((SUM(t1.operatingIncome)-SUM(t2.operatingIncome))/SUM(t2.operatingIncome),0)*100 operatingIncome2,");
sql.append("IFNULL((SUM(t1.gross)-SUM(t2.gross))/SUM(t2.gross),0)*100 gross2,");
sql.append("IFNULL((SUM(t1.invest)-SUM(t2.invest))/SUM(t2.invest),0)*100 invest2,");
sql.append("IFNULL((SUM(t1.totalReturn)-SUM(t2.totalReturn))/SUM(t2.totalReturn),0)*100 totalReturn2,");
sql.append("IFNULL((SUM(t1.incomeTax)-SUM(t2.incomeTax))/SUM(t2.incomeTax),0)*100 incomeTax2,");


sql.append("IFNULL((SUM(t1.employeeSalary)-SUM(t2.employeeSalary))/SUM(t2.employeeSalary),0)*100 employeeSalary2,");
sql.append("IFNULL((SUM(t1.addTex)-SUM(t2.addTex))/SUM(t2.addTex),0)*100 addTex2,");
sql.append("IFNULL((SUM(t1.numAverage)-SUM(t2.numAverage))/SUM(t2.numAverage),0)*100 numAverage2,");
sql.append("IFNULL((SUM(t1.college)-SUM(t2.college))/SUM(t2.college),0)*100 college2,");
sql.append("IFNULL((SUM(t1.tourNum)-SUM(t2.tourNum))/SUM(t2.tourNum),0)*100 tourNum2,");


sql.append("IFNULL((SUM(t1.storeNum)-SUM(t2.storeNum))/SUM(t2.storeNum),0)*100 storeNum2,");
sql.append("IFNULL((SUM(t1.branchOffice)-SUM(t2.branchOffice))/SUM(t2.branchOffice),0)*100 branchOffice2,");
sql.append("IFNULL((SUM(t1.subsidiary)-SUM(t2.subsidiary))/SUM(t2.subsidiary),0)*100 subsidiary2,");
sql.append("IFNULL((SUM(t1.leadGroup)-SUM(t2.leadGroup))/SUM(t2.leadGroup),0)*100 leadGroup2,");
sql.append("IFNULL((SUM(t1.loss)-SUM(t2.loss))/SUM(t2.loss),0)*100 loss2 ");





sql.append("FROM ");

sql.append("(SELECT	ta.`year`, ");
sql.append(" IFNULL(SUM(ta.stream_total),0) AS streamTotal, ");
sql.append(" IFNULL(SUM(ta.long_invest),0) AS longInvest, ");
sql.append(" IFNULL(SUM(ta.fixed_assets),0) AS fixedAssets, ");
sql.append(" IFNULL(SUM(ta.fixed_price),0) AS fixedPrice, ");
sql.append(" IFNULL(SUM(ta.depreciation),0) AS depreciation, ");
sql.append(" IFNULL(SUM(ta.year_depreciation),0) AS yearDepreciation, ");
sql.append(" IFNULL(SUM(ta.asset_total),0) AS assetTotal, ");
sql.append(" IFNULL(SUM(ta.liabilities_total),0) AS liabilitiesTotal, ");
sql.append(" IFNULL(SUM(ta.possessor),0) AS possessor, ");
sql.append(" IFNULL(SUM(ta.real_income),0) AS realIncome, ");
sql.append(" IFNULL(SUM(ta.business_income),0) AS businessIncome, ");
sql.append(" IFNULL(SUM(ta.into_income),0) AS intoIncome, ");
sql.append(" IFNULL(SUM(ta.out_income),0) AS outIncome, ");
sql.append(" IFNULL(SUM(ta.in_travel_income),0) AS inTravelIncome, ");
sql.append(" IFNULL(SUM(ta.extra_gains),0) AS extraGains, ");
sql.append(" IFNULL(SUM(ta.government_subsidies),0) AS governmentSubsidies, ");
sql.append(" IFNULL(SUM(ta.cost),0) AS cost, ");
sql.append(" IFNULL(SUM(ta.business_expenses),0) AS businessExpenses, ");
sql.append(" IFNULL(SUM(ta.main_operation),0) AS mainOperation, ");
sql.append(" IFNULL(SUM(ta.profit_in_travel),0) AS profitInTravel, ");
sql.append(" IFNULL(SUM(ta.out_travel_business),0) AS outTravelBusiness, ");
sql.append(" IFNULL(SUM(ta.in_travel_return),0) AS inTravelBusiness, ");
sql.append(" IFNULL(SUM(ta.lead_group),0) AS leadGroup, ");


sql.append(" IFNULL(SUM(ta.manage_cost),0) AS manageCost, ");
sql.append(" IFNULL(SUM(ta.taxes),0) AS taxes, ");
sql.append(" IFNULL(SUM(ta.business_tex_add),0) AS businessTexAdd, ");
sql.append(" IFNULL(SUM(ta.travel_expense),0) AS travelExpense, ");
sql.append(" IFNULL(SUM(ta.financial_cost),0) AS financialCost, ");
sql.append(" IFNULL(SUM(ta.interest_cost),0) AS interestCost, ");
sql.append(" IFNULL(SUM(ta.selling_expense),0) AS sellingExpense, ");
sql.append(" IFNULL(SUM(ta.value_variation),0) AS valueVariation, ");
sql.append(" IFNULL(SUM(ta.operating_income),0) AS operatingIncome, ");
sql.append(" IFNULL(SUM(ta.gross),0) AS gross, ");
sql.append(" IFNULL(SUM(ta.invest),0) AS invest, ");
sql.append(" IFNULL(SUM(ta.total_return),0) AS totalReturn, ");
sql.append(" IFNULL(SUM(ta.income_tax),0) AS incomeTax, ");
sql.append(" IFNULL(SUM(ta.employee_salary),0) AS employeeSalary, ");
sql.append(" IFNULL(SUM(ta.add_tex),0) AS addTex, ");
sql.append(" IFNULL(SUM(ta.num_average),0) AS numAverage, ");
sql.append(" IFNULL(SUM(ta.college),0) AS college, ");
sql.append(" IFNULL(SUM(ta.tour_num),0) AS tourNum, ");
sql.append(" IFNULL(SUM(ta.store_num),0) AS storeNum, ");
sql.append(" IFNULL(SUM(ta.branch_office),0) AS branchOffice, ");
sql.append(" IFNULL(SUM(ta.subsidiary),0) AS subsidiary,"); 
sql.append(" IFNULL(SUM(ta.loss),0) AS loss "); 
sql.append(" FROM  t_travelAgency_annual ta ");
sql.append(" WHERE ta.`year`="+year+" ) t1, ");

sql.append(" (SELECT	ta.`year`, ");
sql.append(" IFNULL(SUM(ta.stream_total),0) AS streamTotal, ");
sql.append(" IFNULL(SUM(ta.long_invest),0) AS longInvest, ");
sql.append(" IFNULL(SUM(ta.fixed_assets),0) AS fixedAssets, ");
sql.append(" IFNULL(SUM(ta.fixed_price),0) AS fixedPrice, ");
sql.append(" IFNULL(SUM(ta.depreciation),0) AS depreciation, ");
sql.append(" IFNULL(SUM(ta.year_depreciation),0) AS yearDepreciation, ");
sql.append(" IFNULL(SUM(ta.asset_total),0) AS assetTotal, ");
sql.append(" IFNULL(SUM(ta.liabilities_total),0) AS liabilitiesTotal, ");
sql.append(" IFNULL(SUM(ta.possessor),0) AS possessor, ");
sql.append(" IFNULL(SUM(ta.real_income),0) AS realIncome, ");
sql.append(" IFNULL(SUM(ta.business_income),0) AS businessIncome, ");
sql.append(" IFNULL(SUM(ta.into_income),0) AS intoIncome, ");
sql.append(" IFNULL(SUM(ta.out_income),0) AS outIncome, ");
sql.append(" IFNULL(SUM(ta.in_travel_income),0) AS inTravelIncome, ");
sql.append(" IFNULL(SUM(ta.extra_gains),0) AS extraGains, ");
sql.append(" IFNULL(SUM(ta.government_subsidies),0) AS governmentSubsidies, ");
sql.append(" IFNULL(SUM(ta.cost),0) AS cost, ");
sql.append(" IFNULL(SUM(ta.business_expenses),0) AS businessExpenses, ");
sql.append(" IFNULL(SUM(ta.main_operation),0) AS mainOperation, ");
sql.append(" IFNULL(SUM(ta.profit_in_travel),0) AS profitInTravel, ");
sql.append(" IFNULL(SUM(ta.out_travel_business),0) AS outTravelBusiness, ");
sql.append(" IFNULL(SUM(ta.in_travel_return),0) AS inTravelBusiness, ");
sql.append(" IFNULL(SUM(ta.lead_group),0) AS leadGroup, ");


sql.append(" IFNULL(SUM(ta.manage_cost),0) AS manageCost, ");
sql.append(" IFNULL(SUM(ta.taxes),0) AS taxes, ");
sql.append(" IFNULL(SUM(ta.business_tex_add),0) AS businessTexAdd, ");
sql.append(" IFNULL(SUM(ta.travel_expense),0) AS travelExpense, ");
sql.append(" IFNULL(SUM(ta.financial_cost),0) AS financialCost, ");
sql.append(" IFNULL(SUM(ta.interest_cost),0) AS interestCost, ");
sql.append(" IFNULL(SUM(ta.selling_expense),0) AS sellingExpense, ");
sql.append(" IFNULL(SUM(ta.value_variation),0) AS valueVariation, ");
sql.append(" IFNULL(SUM(ta.operating_income),0) AS operatingIncome, ");
sql.append(" IFNULL(SUM(ta.gross),0) AS gross, ");
sql.append(" IFNULL(SUM(ta.invest),0) AS invest, ");
sql.append(" IFNULL(SUM(ta.total_return),0) AS totalReturn, ");
sql.append(" IFNULL(SUM(ta.income_tax),0) AS incomeTax, ");
sql.append(" IFNULL(SUM(ta.employee_salary),0) AS employeeSalary, ");
sql.append(" IFNULL(SUM(ta.add_tex),0) AS addTex, ");
sql.append(" IFNULL(SUM(ta.num_average),0) AS numAverage, ");
sql.append(" IFNULL(SUM(ta.college),0) AS college, ");
sql.append(" IFNULL(SUM(ta.tour_num),0) AS tourNum, ");
sql.append(" IFNULL(SUM(ta.store_num),0) AS storeNum, ");
sql.append(" IFNULL(SUM(ta.branch_office),0) AS branchOffice, ");
sql.append(" IFNULL(SUM(ta.subsidiary),0) AS subsidiary, ");
sql.append(" IFNULL(SUM(ta.loss),0) AS loss "); 
sql.append(" FROM  t_travelAgency_annual ta ");
sql.append(" WHERE ta.`year`="+lastYear+" ) t2 ");
				System.out.println(sql.toString());
	
		OutputStream os = response.getOutputStream();
		String filename=year+"年旅行社财务汇总对比统计.xls";
		
		response.setContentType("application/force-download");// 设置强制下载不打开
		
		response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(filename,"UTF-8"));// 设置文件名
		Map<String, Object> parmass = this.systemService.findOneForJdbc(sql.toString(), null);

		
		HSSFWorkbook wb = this.replaceExcel("Sheet1",  modelPath, parmass);
		wb.write(os);
		
	}
	//旅行社年度经营情况列表
	@RequestMapping(params = "excelExport3")
	public void excelExport3(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
        if(year==null||year.length()==0){
        	year="2016";
        }
        String excelName = "旅行社年度经营情况列表.xls";
        String modelPath = "/com/zzc/web/export/model/travelagency/"+excelName;
        StringBuffer sql = new StringBuffer();
       
        sql.append(" SELECT ");
        sql.append(" 	t.licensenum, ");
        sql.append(" 	t.`name`, ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq1.in_guest_one ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly1 tq1 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq1.tid = t.id ");
        sql.append(" 			AND tq1. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq1.in_guest_two ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly1 tq1 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq1.tid = t.id ");
        sql.append(" 			AND tq1. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq1.in_guest_three ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly1 tq1 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq1.tid = t.id ");
        sql.append(" 			AND tq1. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq1.in_guest_four ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly1 tq1 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq1.tid = t.id ");
        sql.append(" 			AND tq1. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq2.out_guest_one ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly2 tq2 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq2.tid = t.id ");
        sql.append(" 			AND tq2. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq2.out_guest_two ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly2 tq2 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq2.tid = t.id ");
        sql.append(" 			AND tq2. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq3.total_one ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly3 tq3 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq3.tid = t.id ");
        sql.append(" 			AND tq3. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq3.total_two ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly3 tq3 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq3.tid = t.id ");
        sql.append(" 			AND tq3. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq3.total_three ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly3 tq3 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq3.tid = t.id ");
        sql.append(" 			AND tq3. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				tq3.total_four ");
        sql.append(" 			FROM ");
        sql.append(" 				t_travelagency_quarterly3 tq3 ");
        sql.append(" 			WHERE ");
        sql.append(" 				tq3.tid = t.id ");
        sql.append(" 			AND tq3. YEAR =  "+year );
        sql.append(" 		), ");
        sql.append(" 		0 ");
        sql.append(" 	), ");
        sql.append(" 	IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.asset_total ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.liabilities_total ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.possessor ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.into_income ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.out_income ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.in_travel_income ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.in_travel_income+ta.out_income+ta.into_income ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append("   ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.profit_in_travel ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append("   ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.out_travel_business ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append("   ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.in_travel_return ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.profit_in_travel+ta.out_travel_business+ta.in_travel_return ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ), ");
        sql.append(" TRUNCATE ( ");
        sql.append(" 	IFNULL( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				SELECT ");
        sql.append(" 					ta.profit_in_travel + ta.out_travel_business + ta.in_travel_return ");
        sql.append(" 				FROM ");
        sql.append(" 					t_travelagency_annual ta ");
        sql.append(" 				WHERE ");
        sql.append(" 					ta.tid = t.id ");
        sql.append(" 				AND ta. YEAR = "+year);
        sql.append(" 			), ");
        sql.append(" 			0 ");
        sql.append(" 		) / IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				SELECT ");
        sql.append(" 					ta.in_travel_income + ta.out_income + ta.into_income ");
        sql.append(" 				FROM ");
        sql.append(" 					t_travelagency_annual ta ");
        sql.append(" 				WHERE ");
        sql.append(" 					ta.tid = t.id ");
        sql.append(" 				AND ta. YEAR = "+year);
        sql.append(" 			), ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		'非数字' ");
        sql.append(" 	), ");
        sql.append(" 	2 ");
        sql.append(" ), ");
        sql.append(" IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.business_tex_add ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ),IFNULL( ");
        sql.append(" (	 ");
        sql.append(" 		SELECT ");
        sql.append(" 			ta.income_tax ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_annual ta ");
        sql.append(" 		WHERE ");
        sql.append(" 			ta.tid = t.id ");
        sql.append(" 		AND ta. YEAR =  "+year );
        sql.append(" 	), ");
        sql.append(" 	0 ");
        sql.append(" ) ");
        sql.append(" FROM ");
        sql.append(" 	t_travelagency_info t ");
/*        sql.append(" GROUP BY ");
        sql.append(" 	t.`name` ");*/
        HSSFWorkbook workbook = null;
        ExportService emds = new ExportService(5,26); 
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
	//旅行社财务状况一览表
	@RequestMapping(params = "excelExport5")
	public void excelExport5(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
 
        String excelName = "旅行社财务状况一览表.xls";
        String modelPath = "/com/zzc/web/export/model/travelagency/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" 	travelagencyinfo.licensenum, ");
        sql.append(" 	travelagencyinfo.`name`, ");
        sql.append("  t.stream_total, ");
        sql.append(" t.long_invest, ");
        sql.append(" t.fixed_assets, ");
        sql.append(" t.fixed_price, ");
        sql.append(" t.depreciation, ");
        sql.append(" t.year_depreciation, ");
        sql.append(" t.asset_total, ");
        sql.append(" t.liabilities_total, ");
        sql.append(" t.possessor, ");
        sql.append(" t.gainloss, ");
        sql.append(" t.business_income, ");
        sql.append(" t.into_income, ");
        sql.append(" t.out_income, ");
        sql.append(" t.in_travel_income, ");
        sql.append(" t.extra_gains, ");
        sql.append(" t.government_subsidies, ");
        sql.append(" t.cost, ");
        sql.append(" t.business_expenses, ");
        sql.append(" t.business_tex_add, ");
        sql.append(" t.main_operation, ");
        sql.append(" t.profit_in_travel, ");
        sql.append(" t.out_travel_business, ");
        sql.append(" t.in_travel_return, ");
        sql.append(" t.manage_cost, ");
        sql.append(" t.taxes, ");
        sql.append(" t.travel_expense, ");
        sql.append(" t.financial_cost, ");
        sql.append(" t.interest_cost, ");
        sql.append(" t.selling_expense, ");
        sql.append(" t.value_variation, ");
        sql.append(" t.operating_income, ");
        sql.append(" t.gross, ");
        sql.append(" t.invest, ");
        sql.append(" t.total_return, ");
        sql.append(" t.income_tax, ");
        sql.append(" t.employee_salary, ");
        sql.append(" t.add_tex, ");
        sql.append(" t.num_average, ");
        sql.append(" t.college, ");
        sql.append(" t.tour_num, ");
        sql.append(" t.lead_group, ");
        sql.append(" t.store_num, ");
        sql.append(" t.branch_office, ");
        sql.append(" t.subsidiary, ");
        sql.append(" t.loss ");
        sql.append("  ");
        sql.append(" FROM ");
        sql.append(" 	t_travelagency_annual t ");
        sql.append(" LEFT JOIN t_travelagency_info travelagencyinfo ON t.tid = travelagencyinfo.id ");
        sql.append(" WHERE ");
        sql.append(" 	1 = 1 ");
        if(year!=null&&year.length()!=0){
        	sql.append(" and t.year= "+year);
        }
        HSSFWorkbook workbook = null;
        ExportService emds = new ExportService(2,47); 
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
 
	//旅行社财务状况对比一览表
	@RequestMapping(params = "excelExport6")
	public void excelExport6(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String year = new String(request.getParameter("year").getBytes("iso8859-1"),"utf-8");
        if(year==null||year.length()==0){
        	year="2016";
        }
        int year1=Integer.valueOf(year)-1;
        String excelName = "旅行社财务状况对比一览表.xls";
        String modelPath = "/com/zzc/web/export/model/travelagency/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" 	a.*, ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.streamTotal1 - a.streamTotal2 ");
        sql.append(" 			) / a.streamTotal2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
      /*  sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.streamTotal1 - a.streamTotal2 ");
        sql.append(" 			) / a.streamTotal2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");*/
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.longInvest1 - a.longInvest2 ");
        sql.append(" 			) / a.longInvest2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.fixedAssets1 - a.fixedAssets2 ");
        sql.append(" 			) / a.fixedAssets2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.fixedPrice1 - a.fixedPrice2 ");
        sql.append(" 			) / a.fixedPrice2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.depreciation1 - a.depreciation2 ");
        sql.append(" 			) / a.depreciation2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.yearDepreciation1 - a.yearDepreciation2 ");
        sql.append(" 			) / a.yearDepreciation2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.assetTotal1 - a.assetTotal2 ");
        sql.append(" 			) / a.assetTotal2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.liabilitiesTotal1 - a.liabilitiesTotal2 ");
        sql.append(" 			) / a.liabilitiesTotal2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.possessor1 - a.possessor2) / a.possessor2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.gainloss1 - a.gainloss2) / a.gainloss2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.businessIncome1 - a.businessIncome2 ");
        sql.append(" 			) / a.businessIncome2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.intoIncome1 - a.intoIncome2 ");
        sql.append(" 			) / a.intoIncome2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.outIncome1 - a.outIncome2) / a.outIncome2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.in_travelIncome1 - a.in_travelIncome2 ");
        sql.append(" 			) / a.in_travelIncome2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.extraGains1 - a.extraGains2 ");
        sql.append(" 			) / a.extraGains2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.governmentSubsidies1 - a.governmentSubsidies2 ");
        sql.append(" 			) / a.governmentSubsidies2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.cost1 - a.cost2) / a.cost2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.businessExpenses1 - a.businessExpenses2 ");
        sql.append(" 			) / a.businessExpenses2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.businessTexAdd1 - a.businessTexAdd2 ");
        sql.append(" 			) / a.businessTexAdd2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.mainOperation1 - a.mainOperation2 ");
        sql.append(" 			) / a.mainOperation2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.profitInTravel1 - a.profitInTravel2 ");
        sql.append(" 			) / a.profitInTravel2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.outTravelBusiness1 - a.outTravelBusiness2 ");
        sql.append(" 			) / a.outTravelBusiness2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.inTravelReturn1 - a.inTravelReturn2 ");
        sql.append(" 			) / a.inTravelReturn2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.manageCost1 - a.manageCost2 ");
        sql.append(" 			) / a.manageCost2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.taxes1 - a.taxes2) / a.taxes2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.travelExpense1 - a.travelExpense2 ");
        sql.append(" 			) / a.travelExpense2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.financialCost1 - a.financialCost2 ");
        sql.append(" 			) / a.financialCost2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.interestCost1 - a.interestCost2 ");
        sql.append(" 			) / a.interestCost2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.sellingExpense1 - a.sellingExpense2 ");
        sql.append(" 			) / a.sellingExpense2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.valueVariation1 - a.valueVariation2 ");
        sql.append(" 			) / a.valueVariation2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.operatingIncome1 - a.operatingIncome2 ");
        sql.append(" 			) / a.operatingIncome2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.gross1 - a.gross2) / a.gross2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.invest1 - a.invest2) / a.invest2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.totalReturn1 - a.totalReturn2 ");
        sql.append(" 			) / a.totalReturn2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.incomeTax1 - a.incomeTax2) / a.incomeTax2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.employeeSalary1 - a.employeeSalary2 ");
        sql.append(" 			) / a.employeeSalary2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.addTex1 - a.addTex2) / a.addTex2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.numAverage1 - a.numAverage2 ");
        sql.append(" 			) / a.numAverage2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.college1 - a.college2) / a.college2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.tourNum1 - a.tourNum2) / a.tourNum2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.leadGroup1 - a.leadGroup2) / a.leadGroup2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.storeNum1 - a.storeNum2) / a.storeNum2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.branchOffice1 - a.branchOffice2 ");
        sql.append(" 			) / a.branchOffice2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			( ");
        sql.append(" 				a.subsidiary1 - a.subsidiary2 ");
        sql.append(" 			) / a.subsidiary2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	), ");
        sql.append(" 	TRUNCATE ( ");
        sql.append(" 		IFNULL( ");
        sql.append(" 			(a.loss1 - a.loss2) / a.loss2, ");
        sql.append(" 			0 ");
        sql.append(" 		), ");
        sql.append(" 		2 ");
        sql.append(" 	) ");
        sql.append(" FROM ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			ti.licensenum, ");
        sql.append(" 			ti.`name`, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.stream_total ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) streamTotal1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.long_invest ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) longInvest1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.fixed_assets ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) fixedAssets1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.fixed_price ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) fixedPrice1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.depreciation ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) depreciation1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.year_depreciation ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) yearDepreciation1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.asset_total ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) assetTotal1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.liabilities_total ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) liabilitiesTotal1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.possessor ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) possessor1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.gainloss ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) gainloss1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.business_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) businessIncome1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.into_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) intoIncome1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.out_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) outIncome1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.in_travel_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) in_travelIncome1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.extra_gains ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) extraGains1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.government_subsidies ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) governmentSubsidies1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.cost ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) cost1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.business_expenses ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) businessExpenses1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.business_tex_add ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) businessTexAdd1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.main_operation ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) mainOperation1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.profit_in_travel ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) profitInTravel1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.out_travel_business ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) outTravelBusiness1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.in_travel_return ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) inTravelReturn1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.manage_cost ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) manageCost1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.taxes ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) taxes1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.travel_expense ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) travelExpense1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.financial_cost ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) financialCost1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.interest_cost ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) interestCost1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.selling_expense ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) sellingExpense1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.value_variation ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) valueVariation1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.operating_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) operatingIncome1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.gross ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) gross1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.invest ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) invest1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.total_return ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) totalReturn1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.income_tax ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) incomeTax1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.employee_salary ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) employeeSalary1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.add_tex ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) addTex1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.num_average ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) numAverage1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.college ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) college1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.tour_num ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) tourNum1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.lead_group ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) leadGroup1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.store_num ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) storeNum1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.branch_office ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) branchOffice1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.subsidiary ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) subsidiary1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.loss ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) loss1, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.stream_total ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) streamTotal2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.long_invest ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) longInvest2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.fixed_assets ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) fixedAssets2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.fixed_price ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) fixedPrice2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.depreciation ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) depreciation2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.year_depreciation ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) yearDepreciation2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.asset_total ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) assetTotal2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.liabilities_total ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) liabilitiesTotal2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.possessor ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) possessor2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.gainloss ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) gainloss2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.business_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) businessIncome2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.into_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) intoIncome2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.out_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) outIncome2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.in_travel_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) in_travelIncome2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.extra_gains ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) extraGains2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.government_subsidies ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) governmentSubsidies2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.cost ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) cost2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.business_expenses ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) businessExpenses2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.business_tex_add ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) businessTexAdd2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.main_operation ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) mainOperation2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.profit_in_travel ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) profitInTravel2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.out_travel_business ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) outTravelBusiness2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.in_travel_return ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) inTravelReturn2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.manage_cost ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) manageCost2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.taxes ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) taxes2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.travel_expense ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) travelExpense2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.financial_cost ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) financialCost2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.interest_cost ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) interestCost2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.selling_expense ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) sellingExpense2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.value_variation ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) valueVariation2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.operating_income ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) operatingIncome2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.gross ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) gross2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.invest ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) invest2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.total_return ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) totalReturn2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.income_tax ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) incomeTax2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.employee_salary ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) employeeSalary2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.add_tex ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) addTex2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.num_average ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) numAverage2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.college ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) college2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.tour_num ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) tourNum2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.lead_group ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) leadGroup2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.store_num ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) storeNum2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.branch_office ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) branchOffice2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.subsidiary ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) subsidiary2, ");
        sql.append(" 			IFNULL( ");
        sql.append(" 				( ");
        sql.append(" 					SELECT ");
        sql.append(" 						t.loss ");
        sql.append(" 					FROM ");
        sql.append(" 						t_travelagency_annual t ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.tid = ti.id ");
        sql.append(" 					AND t. YEAR = "+year1 );
        sql.append(" 				), ");
        sql.append(" 				0 ");
        sql.append(" 			) loss2 ");
        sql.append(" 		FROM ");
        sql.append(" 			t_travelagency_info ti ");
        sql.append(" 	) a ");
        HSSFWorkbook workbook = null;
        ExportService emds = new ExportService(2,137); 
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
 
	 

	
	
	public HSSFWorkbook replaceExcel(String sheetName,String modelPath,Map<String, Object> param) throws java.io.IOException{
		   POIFSFileSystem fs = new POIFSFileSystem(StatisticTravelAnnualController.class.getResourceAsStream(modelPath));  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        HSSFSheet sheet = wb.getSheet(sheetName);  
 replaceExcelDate(sheet, param);
 return wb;
}
	
	
   
    public void replaceExcelDate(HSSFSheet sheet,Map<String, Object> param){
			// 获取行数
    	HSSFRow row = null;
			int rowNum = sheet.getLastRowNum() + 1;
			for (int i = 0; i < rowNum; i++) {
			 row = sheet.getRow(i);
			
			// 获取行里面的总列数
			int columnNum = 0;
			if(row!=null){
				
			columnNum = row.getPhysicalNumberOfCells();
				
			}
			for (int j = 0; j < columnNum; j++) {
			HSSFCell cell = sheet.getRow(i).getCell(j);
			System.out.println("第"+i+"行"+"第:"+j+"列");
			String cellValue = cell.getStringCellValue();
			for (Entry<String, Object> entry : param.entrySet()) {
			String key = entry.getKey();
			if(key.equals(cellValue)){
			String value = entry.getValue().toString();
			setCellStrValue(sheet, i, j, value);
			}
			}
			}
			}
    }
    /** 
     * 设置字符串类型的数据 
     * @param rowIndex--行值 从0开始
     * @param cellnum--列值  从0开始
     * @param value--字符串类型的数据 
     * 
     * @author 龙亚辉
     * @Date: 
     */  
    public void setCellStrValue(HSSFSheet sheet,int rowIndex, int cellnum, String value) {  
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);  
        cell.setCellValue(value);  
    }
    
}
