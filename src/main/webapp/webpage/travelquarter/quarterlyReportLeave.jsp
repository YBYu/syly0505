<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="leaveGrid" title="" queryMode="group"
			actionUrl="travelQuarterController.do?leaveGridOfAdmin" idField="id"
			fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="traveldata.id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="year" query="true" sortable="ture"
				width="5"
				replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
			<t:dgCol title="季度" field="quarter" query="true" sortable="ture"
				replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4" width="10"></t:dgCol>
			<t:dgCol title="单位名称" field="traveldata.name" query="true" width="25"></t:dgCol>
			<t:dgCol title="许可证编号" field="traveldata.licensenum" query="true"
				width="15"></t:dgCol>
			<t:dgCol title="填报人" field="reportPerson" query="false" width="10"></t:dgCol>
			<t:dgCol title="填报时间" field="reportDate" query="false" width="10"></t:dgCol>
			<t:dgCol title="审核状态" field="status" query="true" width="10"
				replace="<font color=\"red\">市级未填报</font>_1,<font color=\"#d2980c\">市级未审核</font>_2,市级已审核(审核不通过)_3,市级已审核(审核通过)_4,国家系统待审核_5,国家系统未通过_6,国家系统已通过_7" ></t:dgCol>
			<%-- <t:dgCol title="亚洲(人次)" field="asia" query="false"  sortable="ture" width="5"></t:dgCol>
	<t:dgCol title="欧洲(人次)" field="europe" query="false"  sortable="ture" width="5"></t:dgCol>
	<t:dgCol title="非洲(人次)" field="africa" query="false"  sortable="ture" width="5"></t:dgCol>
	<t:dgCol title="大洋洲(人次)" field="oceania" query="false"  sortable="ture" width="5"></t:dgCol>
	<t:dgCol title="美洲(人次)" field="america" query="false"  sortable="ture" width="5"></t:dgCol> --%>
			<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
			<t:dgFunOpt funname="checkDetail(id,traveldata.id)" title="查看详情"></t:dgFunOpt>
			<t:dgToolBar title="导出出境季报" funname="exportExcel"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
//时间下拉框赋值
$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_travelagency_quarterly2&columnName=year",
		dataType:"json",
		success:function(data){
			var rs = data.split(",");
	
			var str = " <select> " ;   
			for(var i = 0; i < rs.length; i++){
				str  =  str  +   " <option value='" + rs[i] + "'> " + rs[i] +"年</option> ";
			}
			str = str + " </select> " ; 
			
			$("select[name='year']")[0].innerHTML = str;
			
			var quarter='${season}';
			var year='${year}';
			$("select[name='quarter']").find('option:selected').remove();
			$("select[name='quarter']").val(quarter);
			$("select[name='year']").val(year);
			}  	
	 	}
	);
});

// 查看详情
function checkDetail(rid,tid){ //rid 报表id, tid 旅行社id
	createdetailwindow("出境报表详情","travelQuarterController.do?checkLeaveReportByAdmin&id="+rid+"&travelid="+tid,850,500);
}


// 表格刷新方法
function reloadTable(){
	try{
		$("#leaveGrid").datagrid("reload");
	}catch(e){
		
	}
}

function exportExcel(){
	var queryParams = $("#leaveGrid").datagrid('options').queryParams;
	var licensenum = "";
	var name = "";
	var year = "";
	var quarter = "";
	var status = "";
	if(typeof queryParams["traveldata.licensenum"] != "undefined"){
		licensenum = queryParams["traveldata.licensenum"];
		name = queryParams["traveldata.name"];
		status = queryParams.status;
	}
		year = queryParams.year;
		quarter = queryParams.quarter;
	window.open("travelQuarterController.do?exportLeaveQuarter&licensenum="+licensenum+"&name="+name+"&year="+year+"&quarter="+quarter+"&status="+status);
}

</script>
