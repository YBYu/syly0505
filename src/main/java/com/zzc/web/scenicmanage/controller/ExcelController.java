package com.zzc.web.scenicmanage.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.util.ResourceUtil;
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.web.scenicmanage.entity.ScenicAnnual;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.scenicmanage.entity.ScenicSpotMonth;
import com.zzc.web.scenicmanage.entity.ScenicSpotQuarter;
import com.zzc.web.scenicmanage.entity.ScenicSpotWeek;
import com.zzc.web.scenicmanage.util.ExcelExportUtils;
import com.zzc.web.system.service.SystemService;
/**
 * @Title: Controller
 * @Description: 景区基本信息excel
 * @author 冯勇齐
 * @date 2016-11-25 21:50:55
 * 
 */




@Scope("prototype")
@Controller
@RequestMapping("/excelController")
public class ExcelController {

	//private   orgService orgService
	    @Autowired
		private SystemService systemService;
		
	    public SystemService getSystemService() {
			return systemService;
		}
		public void setSystemService(SystemService systemService) {
			this.systemService = systemService;
		}
	
	@RequestMapping(params="excelExport")
	public void excelExport(HttpServletRequest request,DataGrid dataGrid ,HttpServletResponse response,ScenicData scenicData)
	{
		String fileName = "";
		//Org o = this.orgService.findOne(Long.valueOf(orgId));
		//this.systemService.find
		try {
			//fileName = java.net.URLEncoder.encode("报名统计表", "ISO8859_1");
			fileName=new String(("导出用户").getBytes(), "ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		response.setContentType("application/vnd.ms-excel;charset=ISO8859_1"); 
		response.setHeader("content-disposition", "attachment;filename="+fileName+".xls");  
		OutputStream fOut = null;
		try {
			fOut = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		//List<UserAccount> list = this.userAccountService.listUserAccount(orgId, orgId, 0, 1000);
		CriteriaQuery cq = new CriteriaQuery(ScenicData.class, dataGrid);
		dataGrid.setRows(999999);
		dataGrid.setPage(1);
        //查询条件组装器
        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicData);  
      
		this.systemService.getDataGridReturn(cq,true);
		List<ScenicData> list = dataGrid.getResults();
		
	//List<ScenicData> list = this.orgAndUserService.listUsers(
	//				orgId, systemId, name, 0, 1000);
		String[] fields = {"code","name","level","scenictype","address","zipcode","url","consultphone","opentime","daylimit","acreage","investunit","superiorunit"};
		String[] names = {"景区 编号","景区名称","景区等级","景区类型","地址","邮编","网址","咨询电话","开业时间","日接待最大容量","面积","投资主体","上级主管单位"};
		Workbook workbook;
		try {
			workbook = ExcelExportUtils.getInstance().inExcel(list, fields, names);
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
		}
		finally
		{
			try {
				fOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	@RequestMapping(params="excelquarter")
	public String excelQuarterExport(HttpServletRequest request,DataGrid dataGrid ,ScenicSpotQuarter scenicSpotQuarter,HttpServletResponse response,ScenicData scenicData,ModelMap map)
	{
		CriteriaQuery cq = new CriteriaQuery(ScenicSpotQuarter.class, dataGrid);
		scenicSpotQuarter.setStatus("4");
		dataGrid.setRows(999999);
		dataGrid.setPage(1);
	    //查询条件组装器
	    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotQuarter);  
		this.systemService.getDataGridReturn(cq,true);
		List<ScenicSpotMonth> list = dataGrid.getResults();
		String username=ResourceUtil.getSessionUserName().getUserName();
		map.put(NormalExcelConstants.FILE_NAME,"景区季报信息");
		    map.put(NormalExcelConstants.CLASS,ScenicSpotQuarter.class);
		    map.put(NormalExcelConstants.PARAMS,new ExportParams("景区季报列表", "导出人:"+username,
		            "导出信息"));
		    map.put(NormalExcelConstants.DATA_LIST,list);
		    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	} 
	@RequestMapping(params="excelweek")
	public String excelWeekExport(HttpServletRequest request,DataGrid dataGrid ,ScenicSpotWeek scenicSpotWeek,HttpServletResponse response,ScenicData scenicData,ModelMap map)
	{
		CriteriaQuery cq = new CriteriaQuery(ScenicSpotWeek.class, dataGrid);
		scenicSpotWeek.setStatus("4");
		dataGrid.setRows(999999);
		dataGrid.setPage(1);
	    //查询条件组装器
	    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotWeek);  
		this.systemService.getDataGridReturn(cq,true);
		List<ScenicSpotMonth> list = dataGrid.getResults();
		String username=ResourceUtil.getSessionUserName().getUserName();
		map.put(NormalExcelConstants.FILE_NAME,"景区周报信息");
		    map.put(NormalExcelConstants.CLASS,ScenicSpotWeek.class);
		    map.put(NormalExcelConstants.PARAMS,new ExportParams("景区周报列表", "导出人:"+username,
		            "导出信息"));
		    map.put(NormalExcelConstants.DATA_LIST,list);
		    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	} 
	@RequestMapping(params="excelannual")
	public String excelAnnualExport(HttpServletRequest request,DataGrid dataGrid ,ScenicSpotAnnual scenicSpotAnnual,HttpServletResponse response,ScenicData scenicData,ModelMap map)
	{
		CriteriaQuery cq = new CriteriaQuery(ScenicSpotAnnual.class, dataGrid);
		scenicSpotAnnual.setStatus("7");
		dataGrid.setRows(999999);
		dataGrid.setPage(1);
	    //查询条件组装器
	    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotAnnual);  
		this.systemService.getDataGridReturn(cq,true);
		List<ScenicSpotMonth> list = dataGrid.getResults();
		String username=ResourceUtil.getSessionUserName().getUserName();
		map.put(NormalExcelConstants.FILE_NAME,"景区年报信息");
		    map.put(NormalExcelConstants.CLASS,ScenicSpotAnnual.class);
		    map.put(NormalExcelConstants.PARAMS,new ExportParams("景区年报列表", "导出人:"+username,
		            "导出信息"));
		    map.put(NormalExcelConstants.DATA_LIST,list);
		    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	} 
	    //菜单管理中的跳转
		@RequestMapping(params="toexcel")
		public String excel(HttpServletRequest request) {
			// 给部门查询条件中的下拉框准备数据
			//List<TSDepart> departList = systemService.getList(TSDepart.class);
			//request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
			//return "/scenic/scenicList";
			   return "/scenic/excel";
		}
}
	
	
	
	
	
	
	
	
		
	

