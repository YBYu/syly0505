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
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
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
@RequestMapping("/excelAnnualController")
public class ExcelAnnualController {
	
	
	
	
	
	
	//private   orgService orgService
    @Autowired
	private SystemService systemService;
	
    public SystemService getSystemService() {
		return systemService;
	}
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	
	

@RequestMapping(params="excelAnnualExport")
public void excelExport(HttpServletRequest request,DataGrid dataGrid ,HttpServletResponse response,ScenicSpotAnnual scenicSpotAnnual,ScenicData scenicData)
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
	CriteriaQuery cq = new CriteriaQuery(ScenicSpotAnnual.class, dataGrid);
	dataGrid.setRows(999999);
	dataGrid.setPage(1);
    //查询条件组装器
    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scenicSpotAnnual);  
  
	this.systemService.getDataGridReturn(cq,true);
	List<ScenicSpotAnnual> list = dataGrid.getResults();
	
//List<ScenicData> list = this.orgAndUserService.listUsers(
//				orgId, systemId, name, 0, 1000);
	String[] fields = {"assetstotal","buildinvest","inbuild","outbuild","appropriation","loan","fundsself","totalincome","ticketincome","shopincome","foodincome","trafficincome","putUpincome","showincome","otherincome","receptionnum","exemptTicketnum","jobnum","fixedworker","tempworker","guidenum","remarks"};
	String[] names = {"资产总额","年底建设投资","景区内部建设投资","景区外部建设投资","拨款","贷款","自筹","景区经营总收入","门票收入","商品收入","餐饮收入","交通收入","住宿收入","演艺收入","其他收入","接待人次","其中免票人次","就业人数","其中固定用工","其中临时用工","专职导游人数","备注"};
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
