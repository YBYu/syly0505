<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="">
<div style="width: 600px;padding-top: 50px">
	<div style="padding:0 15px 15px  15px">
	审核状态:<c:if test="${'5' eq yeardata.status }">退回修订</c:if>
	<c:if test="${'8' eq yeardata.status }">省级审核通过</c:if>
	</div>
	<div style="padding:0 15px 15px 15px;">
	审核意见:
	<textarea rows="" cols="" style="margin: 0px; width: 478px; height: 168px;"><c:if test="${empty yeardata.comments }">暂无审核意见</c:if><c:if test="${not empty yeardata.comments }">${ yeardata.comments }</c:if></textarea>
	</div>
</div>
</body>