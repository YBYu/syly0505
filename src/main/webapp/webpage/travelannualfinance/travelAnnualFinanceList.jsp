<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="financeTable" title="" queryMode="group"
			actionUrl="travelAnnualFinanceController.do?datagrid" idField="id"
			fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="traveldata.id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="year" width="10" query="true"
				replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
			<t:dgCol title="单位名称" field="traveldata.name" query="true" width="30"></t:dgCol>
			<t:dgCol title="许可证编号" field="traveldata.licensenum" query="true"
				width="20"></t:dgCol>
			<t:dgCol title="填报时间" field="reportTime" width="10"></t:dgCol>
			<t:dgCol title="填报人" field="reportPerson" width="10"></t:dgCol>
			<%-- <t:dgCol title="审核状态" field="status" query="true" width="10" sortable="true" replace="市级审核未通过_3,市级待审核_2,市级未填报_1,市级审核通过_4,省级待审核_5,省级审核未通过_6,省级审核通过_7"  ></t:dgCol> --%>
			<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
			<t:dgFunOpt funname="detail(id,traveldata.id)" title="查看详情"></t:dgFunOpt>
			<t:dgToolBar title="导出财务年报" funname="exportExcel"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
//时间下拉框赋值
$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_travelagency_annual&columnName=year",
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

function detail(id,spotId){
		createdetailwindow('财务年报详情', 'travelAnnualFinanceController.do?audit&id='+id+'&load=detail&travelid='+spotId);
	}
	
function exportExcel(){
	var queryParams = $("#financeTable").datagrid('options').queryParams;
	var licensenum = "";
	var name = "";
	var year = "";
	if(typeof queryParams["traveldata.licensenum"] != "undefined"){
		licensenum = queryParams["traveldata.licensenum"];
		name = queryParams["traveldata.name"];
		year = queryParams.year;
	}
	window.open("travelAnnualFinanceController.do?exportTravelFinance&licensenum="+licensenum+"&name="+name+"&year="+year);
}
</script>
