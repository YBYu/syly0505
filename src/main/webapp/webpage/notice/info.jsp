<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>DEMO示例</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="noticeController.do?save">
	<input id="id" name="id" type="hidden" value="${jgDemo.id }">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable" id = "formtableId">
		
		<tr id= "add_phnoe">
			<td align="right" nowrap><label class="Validform_label"> 标题: </label></td>
			<td class="value"><input  class="inputxt" name="mobilePhone" value="${jgDemo.mobilePhone}" datatype="m" errormsg="手机号码不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 内容: </label></td>
			<t:ckeditor name="contents" isfinder="false"
	type="width:1100,height:570" value="${contents}"></t:ckeditor>
		</tr>
		
		<tr>
			<td align="right"><label class="Validform_label"> 工资: </label></td>
			<td class="value"><input class="inputxt" name="salary" value="${jgDemo.salary}" datatype="d" errormsg="工资格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		
	</table>
</t:formvalid>
<t:authFilter name="formtableId"></t:authFilter>
</body>