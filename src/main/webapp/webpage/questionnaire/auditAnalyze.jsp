<!doctype html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">
<meta charset="utf-8">
        
<t:base type="jquery,easyui,tools"></t:base>
        
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts-more.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/modules/exporting.js"></script>           
</head>   
    <body>
    <t:formvalid formid="formobj" refresh="true" dialog="true" action="questionNaireController.do?audit" usePlugin="password" layout="table">
	<input name="analyzeid"  type="hidden" value="${analyzeid}">
	<input name="bianjiurl"  type="hidden" value="${bianjiurl}">
	<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
		    <div class="MainInfo_tit pdtb10">
            	${analyzedata.name}
            </div>	
            <c:forEach  varStatus="j"   items="${topiclist}"  var="list">
            <tr>
				<td align="middle" width="40px"><span class="filedzt"> ${j.index+1}</span></td>
				<td class="value" width="440px" colspan="2">${list.topicName}？</td>
			</tr>
            <c:forEach varStatus="i" items="${list.questionNaireOptionList}" var="a">
            <tr>
				<td align="middle" width="40px"><span class="filedzt"></span></td>
				<td class="value" width="440px" colspan="2">${a.optionName}</td>
<%--                 <td class="value" width="60px"><input name="topic_${list.id}" type="radio" value="${a.id}" datatype="*" errormsg="请选择"/></td> --%>
			</tr>  
			  </c:forEach>
			</c:forEach>
		</tbody>
	</table>
</t:formvalid>
</body>
</html>