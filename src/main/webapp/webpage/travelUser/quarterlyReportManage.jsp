<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body>
	<t:tabs id="tabs2" iframe="false" width="100">
		<t:tab href="travelQuarterController.do?entryReportManageOfuser"
			icon="icon-search" title="入境报表管理" id="entry2"></t:tab>
		<t:tab href="travelQuarterController.do?leaveReportManageOfUser"
			icon="icon-search" title="出境报表管理" id="exit2"></t:tab>
		<t:tab href="travelQuarterController.do?inlandReportManageOfUser"
			icon="icon-search" title="国内报表管理" id="inland2"></t:tab>
	</t:tabs>
</body>
</html>