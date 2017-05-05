<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<t:datagrid name="userList2" title="" queryMode="group" sortName="auditTime" sortOrder="desc" actionUrl="hotelAnnualController.do?datagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>  
	<t:dgCol title="年份" field="year" query="true"  sortable="ture" width="5" replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
	<t:dgCol title="酒店名称" field="hotelmanage.unitname"  query="true"   width="15"></t:dgCol>
	<t:dgCol title="酒店星级" field="hotelmanage.housegrade" width="5" query="true"  sortable="ture" replace="一星_4,二星_5,三星_6,四星_7,五星_8"></t:dgCol>		
	<t:dgCol title="所属辖区" field="hotelmanage.county"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>		
	<t:dgCol title="所属湾区" field="hotelmanage.bay"  query="true" replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>
	<%-- <t:dgCol title="上报时间" field="reportTime"  width="5" ></t:dgCol>  --%>
	<t:dgCol title="id" hidden="true" field="hotelmanage.id"   ></t:dgCol>
	<t:dgCol title="区级审核状态" field="districtStatus" query="true" width="10"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
	<t:dgCol title="市级审核状态" field="status" query="true" width="10" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>	
	<t:dgCol title="省级审核状态" field="countryState" query="true" width="10" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="15"></t:dgCol>
	<t:dgFunOpt funname="detail(hotelmanage.id,id)" title="查看详情" ></t:dgFunOpt>
<t:dgToolBar title="导出酒店年报" funname="exportExcel"></t:dgToolBar>
</t:datagrid>
</div>
</div>

<script type="text/javascript">

//时间下拉框赋值
$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_hotelmonthly&columnName=year",
		dataType:"json",
		success:function(data){
			var rs = data.split(",");
	
			var str = " <select> " ;   
			for(var i = 0; i < rs.length; i++){
				str  =  str  +   " <option value='" + rs[i] + "'> " + rs[i] +"年</option> ";
			}
			str = str + " </select> " ; 
			
			$("select[name='year']")[0].innerHTML = str;
			
			var year='${year}';
			$("select[name='year']").val(year);
			}  	
	 	}
	);
});

function userListsearch(){
	$.dataGrid("#userList2").reload();
}
function detail(id,annualId){
		createdetailwindow('酒店年报详情', 'hotelAnnualController.do?detail&hotelmanageId='+id+'&load=detail&annualId='+annualId);
	}
	
function doMigrateOut(title,url,id){
// 		url += '&ids='+ getListSelections();
	JeecgExcelExport("hotelAnnualController.do?exportsxls","userList2");
	}

function exportExcel(){
	var queryParams = $("#userList2").datagrid('options').queryParams;
	var year = "";
	var name = "";
	var housegrade = "";
	var county = "";
	var bay = "";
	var districtStatus = "";
	var status = "";
	var countryState = "";
	
	if(typeof queryParams["hotelmanage.unitname"] != "undefined"){
		name = queryParams["hotelmanage.unitname"];
		year = queryParams.year;
		housegrade = queryParams["hotelmanage.housegrade"];
		county = queryParams["hotelmanage.county"];
		bay = queryParams["hotelmanage.bay"];
		districtStatus = queryParams["hotelmanage.districtStatus"];
		status = queryParams["hotelmanage.status"];
		countryState = queryParams["hotelmanage.countryState"];
	}
	
	window.open("hotelAnnualController.do?exportAnnual&name="+name+"&year="+year+"&housegrade="+housegrade+"&county="+county+"&bay="+bay+"&districtStatus="+districtStatus+"&status="+status+"&countryState="+countryState);
}
</script>
