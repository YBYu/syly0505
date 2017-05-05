<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>停业状态</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="no">
	<t:formvalid formid="saveOnBuines" refresh="false" dialog="true"
		callback="back" action="hotelController.do?saveOnBuiness"
		usePlugin="password" layout="table">
		<input type="hidden" name="id" value="${id }">
	季度营业状态:
	<select name="onBuinessSeason">
			<option value=""></option>
			<option ${onBuinessSeason==1?'selected="selected"':''} value="1">开业</option>
			<option ${onBuinessSeason==2?'selected="selected"':''} value="0">停业</option>

		</select>
		<br />
	年度营业状态:
	<select name="onBuinessYear">
			<option value=""></option>
			<option ${onBuinessYear==1?'selected="selected"':''} value="1">开业</option>
			<option ${onBuinessYear==0?'selected="selected"':''} value="0">停业</option>
		</select>
	</t:formvalid>
</body>