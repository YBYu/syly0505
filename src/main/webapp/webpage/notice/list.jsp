<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<t:datagrid name="noticeList" title="通知列表" autoLoadData="true" actionUrl="noticeController.do?datagrid" sortName="publishDate" fitColumns="true"
	idField="id" fit="true" queryMode="group" checkbox="true" queryBuilder="true">
	<%--   update-end--Author:tanghan  Date:20130713 for添加checkbox--%>
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="标题" field="title" query="true" frozenColumn="true"  width="120"></t:dgCol>
	<t:dgCol title="发布者" sortable="false" field="publishUser" query="true"  width="120"></t:dgCol>
	<t:dgCol title="发布状态" field="nStatus" width="120"></t:dgCol>
	<t:dgCol title="发布日期" field="publishDate" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group" width="200"></t:dgCol>
	<%--update-end--Author:fangwenrong  Date:20150510 for：添加各项显示长度，解决显示排版问题--%>
<!--	<t:dgFunOpt exp="status#eq#0" operationCode="szqm" funname="szqm(id)" title="审核" />-->
	<t:dgDelOpt operationCode="del" title="删除" url="noticeController.do?del&id={id}" />
	<t:dgToolBar operationCode="add" title="添加" icon="icon-add" url="noticeController.do?addorupdate" funname="add"></t:dgToolBar>
	<t:dgToolBar operationCode="mobileAdd" title="mobile录入" icon="icon-add" url="noticeController.do?addorupdatemobile" funname="addMobile"></t:dgToolBar>
	<t:dgToolBar operationCode="edit" title="编辑" icon="icon-edit" url="noticeController.do?addorupdate" funname="update"></t:dgToolBar>
	<t:dgToolBar operationCode="edit" title="mobile编辑" icon="icon-edit" url="noticeController.do?addorupdatemobile" funname="updateMobile"></t:dgToolBar>
	<t:dgToolBar operationCode="detail" title="查看" icon="icon-search" url="noticeController.do?addorupdate" funname="detail"></t:dgToolBar>
	<t:dgToolBar operationCode="print" title="打印" icon="icon-print" url="noticeController.do?print" funname="detail"></t:dgToolBar>
	<t:dgToolBar title="批量删除" icon="icon-remove" url="noticeController.do?doDeleteALLSelect" funname="deleteALLSelect"></t:dgToolBar>
	<t:dgToolBar title="Xml导入测试" icon="icon-put" url="transdata.do?doMigrateIn" funname="doMigrateIn"></t:dgToolBar>
	<t:dgToolBar title="xml导出测试" icon="icon-putout" url="transdata.do?doMigrateOut" funname="doMigrateOut"></t:dgToolBar>
	<t:dgToolBar  title="重新加载页面" icon="icon-print" url="#" funname="testReloadPage"></t:dgToolBar>
</t:datagrid></div>
</div>
<script type="text/javascript">
	function getListSelections(){
		var ids = '';
		var rows = $("#noticeList").datagrid("getSelections");
		for(var i=0;i<rows.length;i++){
			ids+=rows[i].id;
			ids+=',';
		}
		ids = ids.substring(0,ids.length-1);
		return ids;
	}	
	//表单 sql导出
	function doMigrateOut(title,url,id){
		url += '&ids='+ getListSelections();
		window.location.href= url;
	}
	
</script>