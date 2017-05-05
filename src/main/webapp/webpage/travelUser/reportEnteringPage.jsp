<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body>
	<t:tabs id="tabs" iframe="false" width="100">
		<t:tab
			href="travelQuarterController.do?entryReportOfUser&quarteroneid=&travelid="
			icon="icon-search" title="入境报表" id="entry1"></t:tab>
		<t:tab
			href="travelQuarterController.do?leaveReportOfUser&quarteroneid=&travelid="
			icon="icon-search" title="出境报表" id="exit1"></t:tab>
		<t:tab
			href="travelQuarterController.do?inlandReportOfUser&quarteroneid=&travelid="
			icon="icon-search" title="国内报表" id="inland1"></t:tab>
	</t:tabs>
</body>
</html>