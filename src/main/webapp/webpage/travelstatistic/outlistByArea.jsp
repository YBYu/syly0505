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
			actionUrl="statisticTravelquarterController.do?outbyarea"
			idField="id" fit="true">

			<t:dgCol title="地区" field="area" query="true"
				replace="市辖区_0,吉阳区_1,崖州区_2,天涯区_3,海棠区_4"></t:dgCol>
			<t:dgCol title="年份" field="year" query="true" sortable="ture"></t:dgCol>
			<t:dgCol title="季度" field="quarter" query="true" sortable="ture"
				replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4"></t:dgCol>
			<t:dgCol title="亚洲" field="sumasian" sortable="ture"></t:dgCol>
			<t:dgCol title="欧洲" field="sumeurope" sortable="ture"></t:dgCol>
			<t:dgCol title="非洲" field="sumafrica" sortable="ture"></t:dgCol>
			<t:dgCol title="美洲" field="sumamerica" sortable="ture"></t:dgCol>
			<t:dgCol title="大洋洲" field="sumocean" sortable="ture"></t:dgCol>
			<t:dgToolBar title="出境旅游大洲人次统计表（按地区）导出" langArg="common.user"
				icon="icon-put" funname="exports"></t:dgToolBar>

		</t:datagrid>
	</div>
</div>

<script type="text/javascript">

function exports(){
	
	window.open("statisticTravelquarterController.do?exportssss");
}

// 表格刷新方法
function reloadTable(){
	try{
		$("#leaveGrid").datagrid("reload");
	}catch(e){
		
	}
}
//导出方法
/* function doMigrateOut(){
	JeecgExcelExport("statisticTravelquarterController.do?exportsxls1","leaveGrid");
	
} */
</script>
