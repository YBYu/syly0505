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
			actionUrl="instatisticTravelquarterController.do?inGridbytravel"
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
			<t:dgCol title="香港同胞" field="hkComTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="澳门同胞" field="mcComTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="台湾同胞" field="twComTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="日本" field="japanTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="韩国" field="koreaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="蒙古" field="mongoTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="印度尼西亚" field="indonxyTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="马来西亚" field="malaxyTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="菲律宾" field="philipnTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="新加坡" field="singaporeTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="泰国" field="tailandTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="印度" field="indiaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="越南" field="vietnamTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="缅甸" field="myanamarTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="亚洲其他" field="otherAsianTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="英国" field="englandTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="法国" field="frenchTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="德国" field="germanyTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="意大利" field="italyTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="瑞士" field="swissTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="瑞典" field="swedishTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="俄罗斯" field="russiaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="西班牙" field="spainTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="荷兰" field="hollandTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="丹麦" field="denmarkTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="欧洲其他" field="otherEuropeanTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="美国" field="usTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="加拿大" field="canadaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="美洲其他" field="otherAmericanTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="澳大利亚" field="australiaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="新西兰" field="newlandTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="大洋洲其他" field="otherOceaniaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="南非" field="southafricaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="埃及" field="egyptTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="肯尼亚" field="kenyaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="非洲其他" field="totalAfricaTwo" sortable="ture"></t:dgCol>
			<t:dgCol title="其他小计" field="totalOtherTwo" sortable="ture"></t:dgCol>
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
