<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<!--  
<link rel="stylesheet" type="text/css" href="plug-in/style/yuebao/style.css">
webapp/webpage/js/calendar
-->
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<!-- <script type="text/javascript" src="webpage/js/calendar/WdatePicker.js"></script> -->
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicWeekController.do?saveUser" usePlugin="password" layout="table"  >
	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">

		<input name="weekid"  type="hidden" value="${weekid}">
		<input name="weekRange"  type="hidden" value="${weekRange}">
	
	
	
	<table style="width:800px;height:280px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
		 	<tr>
				<td align="right" width="16%"><span class="filedzt">年份</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  value="${year }" name="year"    readonly="readonly"/></td>
                <td align="right" width="16%">周期</td>
                <td class="value" width="30%"><input type="text" value="${week }"  name="cycle" class="regionNum"    readonly="readonly"  />(${weekRange })<span class="red"></span></td>
			</tr>
        	<%-- <tr>
            	<td align="right" width="16%">周期</td>
                <td class="value" width="16%" colspan="2"><input type="text" value=""  name="cycle" class="regionNum" datatype="n" errormsg="请输入整数"/><span class="red"></span></td>
                <td align="right" width="16%">单位名称 </td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.name}" name="accountNum" class="accountNum" errormsg="请输入账号"/></td>
            </tr> --%>
            <!--单位名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="taking" class="companyname" errormsg="请输入数字" datatype="num" /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">其中门票收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="ticket" class="password" datatype="num" errormsg="请输入数字"/></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="receptionnum" class="englishname" datatype="n" errormsg="请输入整数" /></td>
                <td align="right" width="16%"><span class="filedzt">其中境外人数（人次）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="overnum" class="ogCode" datatype="n" errormsg="请输入整数"/></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">团散比</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="compare" class="creditCode"  errormsg="请输入信息"/></td>
                <td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicData.chargename }" name="principal" class="ogCode" datatype=* errormsg="请输入信息"/></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">填表人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicData.informantname }" name="preparer" class="deligate" datatype=* errormsg="请输入信息" /></td>
                <td align="right" width="16%"><span class="filedzt">电话</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicData.informantphone }" name="telephone" class="adCode" datatype="m" errormsg="请输入正确的手机号"/></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
           <!--  <tr>
				<td align="right" width="16%"><span class="filedzt">报出日期</span></td>
				<td class="value" width="16%" colspan="2"><input  type="text"  name="reportdate" class="Wdate"  datatype="*" errormsg="请输入信息" onClick="WdatePicker()"/></td>
                <td align="right" width="16%"></td>
                <td class="value" width="30%"></td>
			</tr> -->
            <!--网址、邮政编码-->
            
			
            
		</tbody>
	</table>
	
	
</t:formvalid>
</body>
<script type="text/javascript">
	 

	
	

</script>


