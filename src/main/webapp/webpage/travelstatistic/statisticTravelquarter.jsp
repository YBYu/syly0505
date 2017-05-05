<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- context path -->
<t:base type="jquery,easyui,tools"></t:base>
<t:tabs id="tt" iframe="false">
	<t:tab
		href="statisticTravelquarterController.do?tolist&types=toContinent"
		icon="icon-search" title="出境旅游大洲人次统计表（按旅行社）" id="pnode"></t:tab>
	<t:tab
		href="statisticTravelquarterController.do?tolist&types=toContinentByt"
		icon="icon-search" title="旅行社出境游各洲人次" id="pnode"></t:tab>
	<t:tab
		href="statisticTravelquarterController.do?tolist&types=toaimByarea"
		icon="icon-search" title="出境旅游目的地国家人次统计表（按地区）" id="pnode"></t:tab>
</t:tabs>
