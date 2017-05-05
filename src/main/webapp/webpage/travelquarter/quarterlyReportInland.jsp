<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="inlandGrid" title="" queryMode="group"
			actionUrl="travelQuarterController.do?inlandGridOfAdmin" idField="id"
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
			<%-- <t:dgCol title="组织(人次)" field="totalOne" query="false"  sortable="ture" width="5"></t:dgCol>
	<t:dgCol title="接待(人次)" field="totalTwo" query="false"  sortable="ture" width="5"></t:dgCol>
	<t:dgCol title="组织(人天)" field="totalThree" query="false"  sortable="ture" width="5"></t:dgCol>
	<t:dgCol title="接待(人天)" field="totalFour" query="false"  sortable="ture" width="5"></t:dgCol> --%>
			<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
			<%-- <t:dgDelOpt title="删除" url="travelQuarterController.do?deleteInlandReport&id={id}"/> --%>
			<t:dgFunOpt funname="checkDetail(id,traveldata.id)" title="查看详情"></t:dgFunOpt>
			<t:dgToolBar title="导出国内季报" funname="exportExcel"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
//时间下拉框赋值
$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_travelagency_quarterly3&columnName=year",
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
//新建
function add(){
	var button = [{  
        name: "确定",
        callback: function(){
        	iframe = this.iframe.contentWindow;
        	var formjson = $('#formobj', iframe.document).serialize();
			$.ajax ({
				url:"travelQuarterController.do?saveThree",
				data:formjson,
				dataType:"json",
				success:function(data){    
					alert(data.msg);
					reloadTable();
					}  	
			 	}
			);
        },
        focus: true
    }];
createwindowbutton("新建入境报表","travelQuarterController.do?addOrEditInlandReport",button,850,500);
}

// 编辑
function edit(){
	var selection = $("#inlandGrid").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	} 
	var button = [{  
        name: "确定",
        callback: function(){
        	iframe = this.iframe.contentWindow;
        	var formjson = $('#formobj', iframe.document).serialize();
			$.ajax ({
				url:"travelQuarterController.do?saveThree",
				data:formjson,
				dataType:"json",
				success:function(data){    
					alert(data.msg);
					reloadTable();
					}  	
			 	}
			);
        },
        focus: true
    }];
createwindowbutton("编辑国内报表","travelQuarterController.do?addOrEditInlandReport&id="+selection.id,button,850,500);
}

// 查看详情
function checkDetail(rid,tid){
	createdetailwindow("国内报表详情","travelQuarterController.do?checkInlandReportByAdmin&id="+rid+"&travelid="+tid,850,500);
}


// 表格刷新方法
function reloadTable(){
	try{
		$("#inlandGrid").datagrid("reload");
	}catch(e){
		
	}
}

function exportExcel(){
	var queryParams = $("#inlandGrid").datagrid('options').queryParams;
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
	window.open("travelQuarterController.do?exportInlandQuarter&licensenum="+licensenum+"&name="+name+"&year="+year+"&quarter="+quarter+"&status="+status);
}
</script>
