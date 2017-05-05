<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="userList2" title="" queryMode="group"
			actionUrl="travelQuarterController.do?datagrid" idField="id"
			fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="traveldata.id" hidden="true"></t:dgCol>
			<t:dgCol title="许可证编号" field="traveldata.licensenum" query="true"
				width="20"></t:dgCol>
			<t:dgCol title="单位名称" field="traveldata.name" query="true"
				replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>
			<t:dgCol title="电话" field="telePhone" query="true" width="20"></t:dgCol>
			<t:dgCol title="单位负责人" field="traveldata.level" query="true"
				sortable="ture" replace="一星_4,二星_5,三星_6,四星_7,五星_8"></t:dgCol>
			<t:dgCol title="时间" field="time" query="true" sortable="ture"
				replace="2016_0,2017_1"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
			<t:dgDelOpt title="删除"
				url="scenicWeekController.do?del&id={id}&name={name}" />
			<t:dgFunOpt funname="update" title="查看详情"></t:dgFunOpt>
			<%-- <t:dgToolBar title="查看周报详情" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> --%>
			<t:dgToolBar title="季报导出" langArg="common.user" icon="icon-put"
				url="excelController.do?toexcel" funname="doMigrateOut"></t:dgToolBar>
			<%-- <t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar> --%>

		</t:datagrid>



	</div>
</div>

<script type="text/javascript">

</script>
