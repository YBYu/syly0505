<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">
<!-- <style type="text/css"> -->

<!-- </style> -->
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicQuarterController.do?save" usePlugin="password" layout="table">
	<input name="scenicdataid" type="hidden" value="${scenicdataid}">
		<input name="quarterid" type="hidden" value="${quarterid}">
	
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<div class="MainInfo_tit pdtb10">
            	${scenicdata.name}-${quarterdata.year }-第${quarterdata.quarter}季度数据
            </div>
        	<tr>
            	<td align="right" width="10%">景区总收入（万元）</td>
                <td class="value" width="30%" colspan="2"><input type="text" value="${quarterdata.totalincome}" name="totalincome" class="regionNum" errormsg="许可证编号不能为空"/><span class="red"></span></td>
                
            </tr>
            <!--单位名称-->
            <tr>
				<td align="right" width="10%"><span class="filedzt">其中门票收入（万元）</span></td>
				<td class="value" width="30%" colspan="2"><input type="text" value="${quarterdata.ticketincome}" name="ticketincome" class="companyname" errormsg="请输入单位名称" /><span class="red"></span></td>
               
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="10%"><span class="filedzt">接待人次（万人次）</span></td>
				<td class="value" width="30%" colspan="2"><input type="text" value="${quarterdata.receptionnum}" name="receptionnum" class="englishname" errormsg="请输入英文名称" /></td>
               
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="10%"><span class="filedzt">其中免票人次（万人次）</span></td>
				<td class="value" width="30%" colspan="2"><input type="text" value="${quarterdata.exemptticketnum}" name="exemptticketnum" class="creditCode" errormsg="统一社会信用代码"/></td>
                
			</tr>
            <tr>
				<td align="right" width="10%"><span class="filedzt">上报时间</span></td>
				<td class="value" width="30%" colspan="2"><input type="text"  value=<fmt:formatDate pattern="yyyy-MM-dd" value="${quarterdata.reportdate}" /> ></td>
                
			</tr>
           
			<tr>
               	<td><span>备注</span></td>
                <td><textarea rows="10"  name="remarks" style="width:60%">${quarterdata.remarks}</textarea></td>
            </tr>
			
		</tbody>
	</table>
</t:formvalid>
</body>