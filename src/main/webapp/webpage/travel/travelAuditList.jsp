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
			actionUrl="travelController.do?auditdatagrid" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="许可证编号" field="licensenum" query="true" width="20"></t:dgCol>
			<t:dgCol title="单位名称" sortable="false" query="true" field="name"
				width="20"></t:dgCol>
			<t:dgCol title="电话" field="phone" query="true"></t:dgCol>
			<t:dgCol title="状态" sortable="true" field="status"
				replace="正常_1,禁用_0,超级管理员_-1"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="30"></t:dgCol>
			<%-- <t:dgDelOpt title="删除" url="travelController.do?del&id={id}"/>   --%>
			<t:dgToolBar title="添加旅行社" langArg="common.traveldata"
				icon="icon-add" url="travelController.do?savetravel" funname="add"></t:dgToolBar>
			<t:dgToolBar title="编辑" langArg="common.traveldata" icon="icon-edit"
				url="travelController.do?addorupdate" funname="update"></t:dgToolBar>
		</t:datagrid>

	</div>
</div>

<script type="text/javascript">
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
	function szqm(id) {
		createwindow('设置签名', 'userController.do?addsign&id=' + id);
	}
</script>
