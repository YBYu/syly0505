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
@RequestMapping("/scenicHigherStatisticsController") 
public class HigherStatisticsController {

	
	
	
	
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
	//返回景区年报高级统计列表
	@RequestMapping(params = "tolist")
	public String scenicHigherStatistic(HttpServletRequest request) {
		request.setAttribute("dataproperty", "name");
		request.setAttribute("count", "a");
		return "scenichigherstatistics/scenicStatisticsList";
					                                                                                
	}
	
	  //景区年高级统计年报数据
	@RequestMapping(params = "datagrid")
	public void datagrid(ScenicSpotAnnual scenicSpotAnnual, HttpServletRequest request, HttpServletResponse response,
				DataGrid dataGrid) {
	 
		     String count = request.getParameter("count");
		     String dataproperty=request.getParameter("dataproperty");
		     String area=request.getParameter("area");
		     String level=request.getParameter("level");
		     String beginYear=request.getParameter("year_begin");
		     String endYear=request.getParameter("year_end");
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
				     sql.append("	tf.scenic_type AS scenictype, ");
				     sql.append("	tf.org_property AS orgproperty, ");
				     sql.append("	tf.`level`, ");
				     sql.append(" count(`name`) num, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.assets_total),0), 2) assetstotal, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.build_invest),0), 2) buildinvest, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.in_build),0), 2) inbuild, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.out_build),0), 2) outbuild, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.appropriation),0), 2) appropriation, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.loan),0), 2) loan, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.funds_self),0), 2) fundsself, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.total_income),0), 2) totalincome, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.ticket_income),0), 2) ticketincome, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.shop_income),0), 2) shopincome, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.food_income),0), 2) foodincome, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.traffic_income),0), 2) trafficincome, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.putUp_income),0), 2) putUpincome, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.reception_num),0), 2) receptionnum, ");
				     sql.append("	TRUNCATE (IFNULL(SUM(ta.exemptTicket_num),0), 2) exemptTicketnum ");
				     sql.append(" ");
				     sql.append("FROM ");
				     sql.append("	t_scenicSpot_annual ta ");
				     sql.append("LEFT JOIN t_scenicSpot_info tf ON ta.annual_id = tf.id ");
				     sql.append("WHERE ");
				     sql.append("	1 = 1 "); 
		     }else if(count.equals("b")){
		    	 sql.append("SELECT ");
			     sql.append("	tf.`name`, ");
			     sql.append("	tf.id, ");
			     sql.append("	ta.id AS taId, ");
			     sql.append("	tf.area, ");
			     sql.append("	ta.`year`, ");
			     sql.append("	tf.scenic_type AS scenictype, ");
			     sql.append("	tf.org_property AS orgproperty, ");
			     sql.append("	tf.`level`, ");
			     sql.append(" count(`name`) num, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.assets_total),0), 2) assetstotal, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.build_invest),0), 2) buildinvest, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.in_build),0), 2) inbuild, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.out_build),0), 2) outbuild, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.appropriation),0), 2) appropriation, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.loan),0), 2) loan, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.funds_self),0), 2) fundsself, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.total_income),0), 2) totalincome, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.ticket_income),0), 2) ticketincome, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.shop_income),0), 2) shopincome, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.food_income),0), 2) foodincome, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.traffic_income),0), 2) trafficincome, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.putUp_income),0), 2) putUpincome, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.reception_num),0), 2) receptionnum, ");
			     sql.append("	TRUNCATE (IFNULL(avg(ta.exemptTicket_num),0), 2) exemptTicketnum ");
			     sql.append(" ");
			     sql.append("FROM ");
			     sql.append("	t_scenicSpot_annual ta ");
			     sql.append("LEFT JOIN t_scenicSpot_info tf ON ta.annual_id = tf.id ");
			     sql.append("WHERE ");
			     sql.append("	1 = 1 "); 
		     }else if(count.equals("c")){
		    	 sql.append("SELECT ");
			     sql.append("	tf.`name`, ");
			     sql.append("	tf.id, ");
			     sql.append("	ta.id AS taId, ");
			     sql.append("	tf.area, ");
			     sql.append("	ta.`year`, ");
			     sql.append("	tf.scenic_type AS scenictype, ");
			     sql.append("	tf.org_property AS orgproperty, ");
			     sql.append("	tf.`level`, ");
			     sql.append(" count(`name`) num, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.assets_total),0), 2) assetstotal, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.build_invest),0), 2) buildinvest, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.in_build),0), 2) inbuild, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.out_build),0), 2) outbuild, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.appropriation),0), 2) appropriation, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.loan),0), 2) loan, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.funds_self),0), 2) fundsself, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.total_income),0), 2) totalincome, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.ticket_income),0), 2) ticketincome, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.shop_income),0), 2) shopincome, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.food_income),0), 2) foodincome, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.traffic_income),0), 2) trafficincome, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.putUp_income),0), 2) putUpincome, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.reception_num),0), 2) receptionnum, ");
			     sql.append("	TRUNCATE (IFNULL(max(ta.exemptTicket_num),0), 2) exemptTicketnum ");
			     sql.append(" ");
			     sql.append("FROM ");
			     sql.append("	t_scenicSpot_annual ta ");
			     sql.append("LEFT JOIN t_scenicSpot_info tf ON ta.annual_id = tf.id ");
			     sql.append("WHERE ");
			     sql.append("	1 = 1 "); 
		     }else if(count.equals("d")){
		    	 sql.append("SELECT ");
			     sql.append("	tf.`name`, ");
			     sql.append("	tf.id, ");
			     sql.append("	ta.id AS taId, ");
			     sql.append("	tf.area, ");
			     sql.append("	ta.`year`, ");
			     sql.append("	tf.scenic_type AS scenictype, ");
			     sql.append("	tf.org_property AS orgproperty, ");
			     sql.append("	tf.`level`, ");
			     sql.append(" count(`name`) num, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.assets_total),0), 2) assetstotal, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.build_invest),0), 2) buildinvest, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.in_build),0), 2) inbuild, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.out_build),0), 2) outbuild, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.appropriation),0), 2) appropriation, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.loan),0), 2) loan, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.funds_self),0), 2) fundsself, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.total_income),0), 2) totalincome, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.ticket_income),0), 2) ticketincome, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.shop_income),0), 2) shopincome, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.food_income),0), 2) foodincome, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.traffic_income),0), 2) trafficincome, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.putUp_income),0), 2) putUpincome, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.reception_num),0), 2) receptionnum, ");
			     sql.append("	TRUNCATE (IFNULL(min(ta.exemptTicket_num),0), 2) exemptTicketnum ");
			     sql.append(" ");
			     sql.append("FROM ");
			     sql.append("	t_scenicSpot_annual ta ");
			     sql.append("LEFT JOIN t_scenicSpot_info tf ON ta.annual_id = tf.id ");
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
			dataGrid.setField("name,id,area,year,scenictype,orgproperty,level,num,assetstotal,buildinvest,inbuild,outbuild,appropriation,loan,fundsself,totalincome,ticketincome,shopincome,foodincome,trafficincome,putUpincome,receptionnum,exemptTicketnum");
			TagUtil.datagrid(response, dataGrid);
	}
       
	
}
