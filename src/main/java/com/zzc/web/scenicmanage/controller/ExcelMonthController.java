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
import com.zzc.web.otherTravel.entity.BoatMonthly;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotMonth;
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
@RequestMapping("/excelMonthController")
public class ExcelMonthController {
	
	
	
	
	
	
	
	//private   orgService orgService
    @Autowired
	private SystemService systemService;
	
    public SystemService getSystemService() {
		return systemService;
	}
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

@RequestMapping(params="excelMonthExport")
public void excelExport(HttpServletRequest request,DataGrid dataGrid ,ScenicSpotMonth scenicSpotMonth,HttpServletResponse response,ScenicData scenicData)
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
	CriteriaQuery cq = new CriteriaQuery(ScenicSpotMonth.class, dataGrid);
	dataGrid.setRows(999999);
	dataGrid.setPage(1);
    //查询条件组装器
    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotMonth);  
  
	this.systemService.getDataGridReturn(cq,true);
	List<ScenicSpotMonth> list = dataGrid.getResults();
	
//List<ScenicData> list = this.orgAndUserService.listUsers(
//				orgId, systemId, name, 0, 1000);
	String[] fields = {"taking","ticket","businesstax","totalprofit","endemployee","laidworker","countrylabor","price","asset","receptionnum","overnum","freeticket","compare","principal","preparer","telephone","reportdate"};
	String[] names = {"营业收入","其中门票收入","营业税金及附加","利润总额","年末从业人员数","其中吸纳下岗职工","其中转移农村劳动力","固定资产原价","固定资产净值","接待人数","其中境外人数","其中免票人数","团散比","单位负责人","填表人","联系电话","报出日期"};
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
@RequestMapping(params="excelmonth")
public String excelMonthExport(HttpServletRequest request,DataGrid dataGrid ,ScenicSpotMonth scenicSpotMonth,HttpServletResponse response,ScenicData scenicData,ModelMap map)
{
	CriteriaQuery cq = new CriteriaQuery(ScenicSpotMonth.class, dataGrid);
	scenicSpotMonth.setStatus("4");
	dataGrid.setRows(999999);
	dataGrid.setPage(1);
    //查询条件组装器
    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotMonth);  
  
	this.systemService.getDataGridReturn(cq,true);
	List<ScenicSpotMonth> list = dataGrid.getResults();
	/* CriteriaQuery cq = new CriteriaQuery(ScenicSpotMonth.class, dataGrid);
	    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotMonth, request.getParameterMap());
	    String scenicid = ResourceUtil.getSessionUserName().getId();*/
	   /* List<ScenicSpotMonth> scenicSpotMonths = this.systemService.findHql("from com.zzc.web.scenicmanage.entity.ScenicSpotMonth th where th.id=? ",scenicid );*/
	String username=ResourceUtil.getSessionUserName().getUserName();
	map.put(NormalExcelConstants.FILE_NAME,"景区月报信息");
	    map.put(NormalExcelConstants.CLASS,ScenicSpotMonth.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("景区月报列表", username,
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
