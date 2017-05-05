<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="leaveGrid" title="(接待人次)" queryMode="group"
			actionUrl="inlandStatisticTravelquarterController.do?inlandlistByTravel"
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
			<t:dgCol title="安徽" field="ahFour" sortable="ture"></t:dgCol>
			<t:dgCol title="北京" field="bjFour" sortable="ture"></t:dgCol>
			<t:dgCol title="福建" field="fjFour" sortable="ture"></t:dgCol>
			<t:dgCol title="甘肃" field="gansuFour" sortable="ture"></t:dgCol>
			<t:dgCol title="广东" field="gdFour" sortable="ture"></t:dgCol>
			<t:dgCol title="广西" field="gxFour" sortable="ture"></t:dgCol>
			<t:dgCol title="贵州" field="gzFour" sortable="ture"></t:dgCol>
			<t:dgCol title="海南" field="hnFour" sortable="ture"></t:dgCol>
			<t:dgCol title="河北" field="hbFour" sortable="ture"></t:dgCol>
			<t:dgCol title="河南" field="hainanFour" sortable="ture"></t:dgCol>
			<t:dgCol title="黑龙江" field="hljFour" sortable="ture"></t:dgCol>
			<t:dgCol title="湖北" field="hubeiFour" sortable="ture"></t:dgCol>
			<t:dgCol title="湖南" field="hlFour" sortable="ture"></t:dgCol>
			<t:dgCol title="吉林" field="jlFour" sortable="ture"></t:dgCol>
			<t:dgCol title="江苏" field="jsFour" sortable="ture"></t:dgCol>
			<t:dgCol title="江西" field="jxFour" sortable="ture"></t:dgCol>
			<t:dgCol title="辽宁" field="lnFour" sortable="ture"></t:dgCol>
			<t:dgCol title="内蒙古" field="nmgFour" sortable="ture"></t:dgCol>
			<t:dgCol title="宁夏" field="nxFour" sortable="ture"></t:dgCol>
			<t:dgCol title="青海" field="qhFour" sortable="ture"></t:dgCol>
			<t:dgCol title="山东" field="sdFour" sortable="ture"></t:dgCol>
			<t:dgCol title="山西" field="sxFour" sortable="ture"></t:dgCol>
			<t:dgCol title="陕西" field="shanxiFour" sortable="ture"></t:dgCol>
			<t:dgCol title="四川" field="scFour" sortable="ture"></t:dgCol>
			<t:dgCol title="天津" field="tjFour" sortable="ture"></t:dgCol>
			<t:dgCol title="西藏" field="xzFour" sortable="ture"></t:dgCol>
			<t:dgCol title="新疆" field="xjFour" sortable="ture"></t:dgCol>
			<t:dgCol title="云南" field="ynFour" sortable="ture"></t:dgCol>
			<t:dgCol title="浙江" field="zjFour" sortable="ture"></t:dgCol>
			<t:dgCol title="重庆" field="cqFour" sortable="ture"></t:dgCol>
			<%-- 	<t:dgCol title="新疆兵团" field="FournewlandOne"  sortable="ture" ></t:dgCol> --%>
			<t:dgCol title="合计" field="totalFour" sortable="ture"></t:dgCol>
			<%-- <t:dgCol title="南非" field="southafricaOne"  sortable="ture" ></t:dgCol>
	<t:dgCol title="埃及" field="egyptOne"  sortable="ture" ></t:dgCol>
	<t:dgCol title="肯尼亚" field="kenyaOne"  sortable="ture" ></t:dgCol>
	<t:dgCol title="非洲其他" field="totalAfricaOne"  sortable="ture" ></t:dgCol>
	<t:dgCol title="其他小计" field="totalOtherOne"  sortable="ture" ></t:dgCol> --%>
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
