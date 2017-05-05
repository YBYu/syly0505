package com.zzc.web.scenicmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.util.StringUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.system.service.SystemService;



 

@Scope("prototype")
@Controller
@RequestMapping("/quarterStatisticsController") 
public class QuarterStatisticsController {
	
	
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ScenicSpotWeekController.class);
	private SystemService systemService;
 
	public SystemService getSystemService(){
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	//返回景区季报高级统计列表
	@RequestMapping(params = "tolist")
	public String scenicHigherStatistic(HttpServletRequest request) {
		request.setAttribute("dataproperty", "name");
		request.setAttribute("count", "a");
		return "scenichigherstatistics/scenicQuarterStatisticsList";
					                                                                                
	}
	
	 //景区年高级统计季报数据
	@RequestMapping(params = "datagrid")
	public void datagrid(ScenicSpotAnnual scenicSpotAnnual, HttpServletRequest request, HttpServletResponse response,
				DataGrid dataGrid) {
	     String count = request.getParameter("count");
	     String dataproperty=request.getParameter("dataproperty");
	     String area=request.getParameter("area");
	     String level=request.getParameter("level");
	     String beginYear=request.getParameter("year_begin");
	     String endYear=request.getParameter("year_end");
	     String quarter=request.getParameter("quarter");
	     StringBuffer sql=new StringBuffer();
	     if(count==null||count.length()==0){
	    	 count="a";
	     }
	    	//初始化总和
	     if(count.equals("a")){
	    	 sql.append("SELECT ");
	    	 sql.append("	tf.`name`, ");
	    	 sql.append("	tf.id, ");
	    	 sql.append("	ta.id AS taId, ");
	    	 sql.append("	tf.area, ");
	    	 sql.append("	ta.`year`, ");
	    	 sql.append("	ta.`quarter`, ");
	    	 sql.append("	tf.scenic_type AS scenictype, ");
	    	 sql.append("	tf.org_property AS orgproperty, ");
	    	 sql.append("	tf.`level`, ");
	    	 sql.append("	count(`name`) num, ");
	    	 sql.append("	TRUNCATE (IFNULL(sum(total_income),0), 2) AS totalIncome, ");
	    	 sql.append("	TRUNCATE (IFNULL(sum(ticket_income),0), 2) AS ticketIncome, ");
	    	 sql.append("	TRUNCATE (IFNULL(sum(reception_num),0), 2) AS receptionNum, ");
	    	 sql.append("	TRUNCATE (IFNULL(sum(exempt_ticket_num),0), 2) AS exemptTicketNum ");
	    	 sql.append("FROM ");
	    	 sql.append("	t_scenicSpot_quarterly ta ");
	    	 sql.append("LEFT JOIN t_scenicSpot_info tf ON ta.quarter_id = tf.id ");
	    	 sql.append("WHERE ");
	    	 sql.append("	1 = 1 ");
	     }else if(count.equals("b")){
	    	 sql.append("SELECT ");
	    	 sql.append("	tf.`name`, ");
	    	 sql.append("	tf.id, ");
	    	 sql.append("	ta.id AS taId, ");
	    	 sql.append("	tf.area, ");
	    	 sql.append("	ta.`year`, ");
	    	 sql.append("	ta.`quarter`, ");
	    	 sql.append("	tf.scenic_type AS scenictype, ");
	    	 sql.append("	tf.org_property AS orgproperty, ");
	    	 sql.append("	tf.`level`, ");
	    	 sql.append("	count(`name`) num, ");
	    	 sql.append("	TRUNCATE (IFNULL(avg(total_income),0), 2) AS totalIncome, ");
	    	 sql.append("	TRUNCATE (IFNULL(avg(ticket_income),0), 2) AS ticketIncome, ");
	    	 sql.append("	TRUNCATE (IFNULL(avg(reception_num),0), 2) AS receptionNum, ");
	    	 sql.append("	TRUNCATE (IFNULL(avg(exempt_ticket_num),0), 2) AS exemptTicketNum ");
	    	 sql.append("FROM ");
	    	 sql.append("	t_scenicSpot_quarterly ta ");
	    	 sql.append("LEFT JOIN t_scenicSpot_info tf ON ta.quarter_id = tf.id ");
	    	 sql.append("WHERE ");
	    	 sql.append("	1 = 1 ");
	     }else if(count.equals("c")){
	    	 sql.append("SELECT ");
	    	 sql.append("	tf.`name`, ");
	    	 sql.append("	tf.id, ");
	    	 sql.append("	ta.id AS taId, ");
	    	 sql.append("	tf.area, ");
	    	 sql.append("	ta.`year`, ");
	    	 sql.append("	ta.`quarter`, ");
	    	 sql.append("	tf.scenic_type AS scenictype, ");
	    	 sql.append("	tf.org_property AS orgproperty, ");
	    	 sql.append("	tf.`level`, ");
	    	 sql.append("	count(`name`) num, ");
	    	 sql.append("	TRUNCATE (IFNULL(max(total_income),0), 2) AS totalIncome, ");
	    	 sql.append("	TRUNCATE (IFNULL(max(ticket_income),0), 2) AS ticketIncome, ");
	    	 sql.append("	TRUNCATE (IFNULL(max(reception_num),0), 2) AS receptionNum, ");
	    	 sql.append("	TRUNCATE (IFNULL(max(exempt_ticket_num),0), 2) AS exemptTicketNum ");
	    	 sql.append("FROM ");
	    	 sql.append("	t_scenicSpot_quarterly ta ");
	    	 sql.append("LEFT JOIN t_scenicSpot_info tf ON ta.quarter_id = tf.id ");
	    	 sql.append("WHERE ");
	    	 sql.append("	1 = 1 ");
	     }else if(count.equals("d")){
	    	 sql.append("SELECT ");
	    	 sql.append("	tf.`name`, ");
	    	 sql.append("	tf.id, ");
	    	 sql.append("	ta.id AS taId, ");
	    	 sql.append("	tf.area, ");
	    	 sql.append("	ta.`year`, ");
	    	 sql.append("	ta.`quarter`, ");
	    	 sql.append("	tf.scenic_type AS scenictype, ");
	    	 sql.append("	tf.org_property AS orgproperty, ");
	    	 sql.append("	tf.`level`, ");
	    	 sql.append("	count(`name`) num, ");
	    	 sql.append("	TRUNCATE (IFNULL(min(total_income),0), 2) AS totalIncome, ");
	    	 sql.append("	TRUNCATE (IFNULL(min(ticket_income),0), 2) AS ticketIncome, ");
	    	 sql.append("	TRUNCATE (IFNULL(min(reception_num),0), 2) AS receptionNum, ");
	    	 sql.append("	TRUNCATE (IFNULL(min(exempt_ticket_num),0), 2) AS exemptTicketNum ");
	    	 sql.append("FROM ");
	    	 sql.append("	t_scenicSpot_quarterly ta ");
	    	 sql.append("LEFT JOIN t_scenicSpot_info tf ON ta.quarter_id = tf.id ");
	    	 sql.append("WHERE ");
	    	 sql.append("	1 = 1 ");
	     }
	   
	     if(area!=null&&area.length()!=0){
	    	 	sql.append(" and tf.area="+area);
	     }  if(level!=null&&level.length()!=0){
	    	 	sql.append(" and tf.level="+level);
	     }if(beginYear!=null&&beginYear.length()!=0){
	    	 	sql.append(" and ta.`year` >="+beginYear );
	     }if(endYear!=null&&endYear.length()!=0){
	    	 	sql.append(" and ta.`year` <="+endYear );
	     }if(quarter!=null&&quarter.length()!=0){
	    	 	sql.append(" and ta.quarter="+quarter);
	     }
			if(dataproperty!=null&&dataproperty.length()!=0){
				if(dataproperty.equals("area")){
					sql.append(" GROUP BY tf.area");
				}else 	if(dataproperty.equals("name")){
					sql.append(" GROUP BY tf.name");
				}	if(dataproperty.equals("scenictype")){
					sql.append(" GROUP BY tf.scenic_type");
				}	if(dataproperty.equals("orgproperty")){
					sql.append(" GROUP BY tf.org_property");
				}	if(dataproperty.equals("level")){
					sql.append(" GROUP BY tf.level");
				}	if(dataproperty.equals("year")){
					sql.append(" GROUP BY ta.year");
				}if(dataproperty.equals("quarter")){
					sql.append(" GROUP BY ta.quarter");
				}
			}
			else{
				sql.append(" GROUP BY tf.name");
				
			}
		
			int pageNow = dataGrid.getPage();
			int pageSize = dataGrid.getRows();
			int limitmin=(pageNow-1)*10;
			int limitmax=pageNow*10;
			sql.append(" limit "+limitmin+","+limitmax);
			List list = systemService.findForJdbc(sql.toString());	
			String sqll=sql.toString().substring(0,sql.toString().indexOf("limit"));
			//总条数
		Long total=systemService.getCountForJdbc("SELECT count(*) from  ("+sqll+") a");
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		dataGrid.setResults(list);
		dataGrid.setTotal(Integer.parseInt(total.toString()));
		dataGrid.setRows(pageSize);
		dataGrid.setPage(pageNow);
		dataGrid.setField("name,id,area,year,quarter,scenictype,orgproperty,level,num,totalIncome,ticketIncome,receptionNum,exemptTicketNum");
		TagUtil.datagrid(response, dataGrid);
		}    
	
    
	

}
