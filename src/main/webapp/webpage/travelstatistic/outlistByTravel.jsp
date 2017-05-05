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
			actionUrl="statisticTravelquarterController.do?outlistByTravel"
			idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="traveldata.id" hidden="true"></t:dgCol>
			<t:dgCol title="许可证编号" field="traveldata.licensenum" query="true"></t:dgCol>
			<t:dgCol title="旅行社名称" field="traveldata.name" query="true"></t:dgCol>
			<t:dgCol title="地区" field="area" query="true"
				replace="市辖区_0,吉阳区_1,崖州区_2,天涯区_3,海棠区_4"></t:dgCol>
			<t:dgCol title="年份" field="year" query="true" sortable="ture"></t:dgCol>
			<t:dgCol title="季度" field="quarter" query="true" sortable="ture"
				replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4"></t:dgCol>
			<t:dgCol title="香港同胞" field="hongKongOne" sortable="ture"></t:dgCol>
			<t:dgCol title="澳门同胞" field="macauOne" sortable="ture"></t:dgCol>
			<t:dgCol title="台湾同胞" field="taiWanOne" sortable="ture"></t:dgCol>
			<t:dgCol title="日本" field="japanOne" sortable="ture"></t:dgCol>
			<t:dgCol title="韩国" field="koreaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="蒙古" field="mongoliaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="印度尼西亚" field="indonesiaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="马来西亚" field="malaysiaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="菲律宾" field="philippinesOne" sortable="ture"></t:dgCol>
			<t:dgCol title="新加坡" field="singaporeOne" sortable="ture"></t:dgCol>
			<t:dgCol title="泰国" field="thailandOne" sortable="ture"></t:dgCol>
			<t:dgCol title="印度" field="indiaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="越南" field="vietnamOne" sortable="ture"></t:dgCol>
			<t:dgCol title="缅甸" field="burmaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="亚洲其他" field="asianOtherOne" sortable="ture"></t:dgCol>
			<t:dgCol title="英国" field="englishOne" sortable="ture"></t:dgCol>
			<t:dgCol title="法国" field="franchOne" sortable="ture"></t:dgCol>
			<t:dgCol title="德国" field="germanyOne" sortable="ture"></t:dgCol>
			<t:dgCol title="意大利" field="italyOne" sortable="ture"></t:dgCol>
			<t:dgCol title="瑞士" field="switzerLandOne" sortable="ture"></t:dgCol>
			<t:dgCol title="瑞典" field="swedenOne" sortable="ture"></t:dgCol>
			<t:dgCol title="俄罗斯" field="russiaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="西班牙" field="spainOne" sortable="ture"></t:dgCol>
			<t:dgCol title="荷兰" field="holLandOne" sortable="ture"></t:dgCol>
			<t:dgCol title="丹麦" field="denMarkOne" sortable="ture"></t:dgCol>
			<t:dgCol title="欧洲其他" field="europeOtherOne" sortable="ture"></t:dgCol>
			<t:dgCol title="美国" field="usOne" sortable="ture"></t:dgCol>
			<t:dgCol title="加拿大" field="sumcanada" sortable="ture"></t:dgCol>
			<t:dgCol title="美洲其他" field="usOtherOne" sortable="ture"></t:dgCol>
			<t:dgCol title="澳大利亚" field="australianOne" sortable="ture"></t:dgCol>
			<t:dgCol title="新西兰" field="zeaLandOne" sortable="ture"></t:dgCol>
			<t:dgCol title="大洋洲其他" field="oceaniaOtherOne" sortable="ture"></t:dgCol>
			<t:dgCol title="南非" field="southAfricaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="埃及" field="egyptOne" sortable="ture"></t:dgCol>
			<t:dgCol title="肯尼亚" field="kenyaOne" sortable="ture"></t:dgCol>
			<t:dgCol title="非洲其他" field="africaOtherOne" sortable="ture"></t:dgCol>
			<t:dgCol title="其他小计" field="otherTotalOne" sortable="ture"></t:dgCol>
			<%-- <t:dgToolBar title="出境旅游大洲人次统计表（按旅行社）导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar> --%>

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
/* function doMigrateOut(){
	JeecgExcelExport("statisticTravelquarterController.do?exportsxls1","leaveGrid");
	
} */
</script>
