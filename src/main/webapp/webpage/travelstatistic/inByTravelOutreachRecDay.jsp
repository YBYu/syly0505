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
			actionUrl="instatisticTravelquarterController.do?outGridbytravel"
			idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="traveldata.id" hidden="true"></t:dgCol>
			<t:dgCol title="许可证编号" field="traveldata.licensenum" query="true"
				width="15"></t:dgCol>
			<t:dgCol title="旅行社名称" field="traveldata.name" query="true"
				width="30"></t:dgCol>
			<t:dgCol title="年份" field="year" query="true" sortable="ture"
				width="10"></t:dgCol>
			<t:dgCol title="季度" field="quarter" query="true" sortable="ture"
				replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4" width="10"></t:dgCol>
			<t:dgCol title="外联人天" field="inGuestThree" sortable="ture" width="10"></t:dgCol>
			<t:dgCol title="接待人天" field="inGuestFour" sortable="ture" width="10"></t:dgCol>
			<%-- <t:dgCol title="美洲" field="americaTotalOne"  sortable="ture" width="10"></t:dgCol>
	<t:dgCol title="大洋洲" field="oceaniaTotalOne"  sortable="ture" width="10"></t:dgCol>
	<t:dgToolBar title="出境旅游大洲人次统计表（按旅行社）导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"> --%>

		</t:datagrid>
	</div>
</div>

<script type="text/javascript">



// 表格刷新方法
function reloadTable(){
	try{
		$("#leaveGrid").datagrid("reload");
	}catch(e){
		
	}
}
//导出方法
function doMigrateOut(){
	JeecgExcelExport("statisticTravelquarterController.do?exportsxls1","leaveGrid");
	
}
</script>
