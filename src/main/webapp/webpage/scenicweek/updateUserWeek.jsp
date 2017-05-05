<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<!--  
<link rel="stylesheet" type="text/css" href="plug-in/style/yuebao/style.css">
webapp/webpage/js/calendar
-->
<link rel="stylesheet" type="text/css" href="plug-in/style/zhoubao/style.css">
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<!-- <script type="text/javascript" src="webpage/js/calendar/WdatePicker.js"></script> -->
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicWeekController.do?updateUser" usePlugin="password" layout="table">

	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">
   <input name="weekid" type="hidden" value="${weekid }">
   <input name="sid" type="hidden" value="${scenicweek.sid }">
   <input name="status" type="hidden" value="${scenicweek.status }">
   <input name="score" type="hidden" value="${scenicweek.score }">
   <input name="weekRange" type="hidden" value="${scenicweek.weekRange}">
	
	        <div class="MainInfo_tit mgb20">
            	${scenicdata.name}-周报数据
            </div>
	<table style="width:750px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<!--许可证号-->
        	 <tr>
				<td align="right" width="16%"><span class="filedzt">年份</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  readonly="readonly" value="${scenicweek.year}" name="year"  /></td>
                <td align="right" width="16%"></td>
                <td class="value" width="30%"></td>
			</tr>
        	<tr>
            	<td align="right" width="16%">周期(${scenicweek.weekRange})</td>
                <td class="value" width="16%" colspan="2"><input type="text" value="${scenicweek.cycle}" readonly="readonly"  name="cycle" class="regionNum" errormsg="许可证编号不能为空"/><span class="red"></span></td>
                <td align="right" width="16%">单位名称 </td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.name}" disabled="disabled" name="accountNum" class="accountNum" errormsg="请输入账号"></td>
            </tr>
            <!--单位名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicweek.taking}"  name="taking" class="companyname"  datatype="xs" /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">其中门票收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicweek.ticket}"  name="ticket" class="password"  datatype="xs"  ></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicweek.receptionnum}"  name="receptionnum" class="englishname" datatype="n1-7" errormsg="只能为1-7位整数"/></td>
                <td align="right" width="16%"><span class="filedzt">其中境外人数（人次）</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicweek.overnum}"  name="overnum" class="ogCode" datatype="n1-7" errormsg="只能为1-7位整数"></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">团散比</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  value="${scenicweek.compare}"  name="compare" class="creditCode"   errormsg="请输入信息"/></td>
                <td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicweek.principal}"  name="principal" class="ogCode" datatype="*" errormsg="请输入信息"></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">填表人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicweek.preparer}" name="preparer" class="deligate" datatype="*" errormsg="请输入信息" /></td>
                <td align="right" width="16%"><span class="filedzt">电话</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicweek.telephone}"  name="telephone" class="adCode" datatype="m" errormsg="请输入正确的电话"></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
           <%--  <tr>
				<td align="right" width="16%"><span class="filedzt">报出日期</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" onClick="WdatePicker()" value="${scenicweek.reportdate}" name="reportdate" class="Wdate" datatype="*" errormsg="请输入信息" /></td>
                <td align="right" width="16%"></td>
                <td class="value" width="30%"></td>
			</tr> --%>
            <!--网址、邮政编码-->
            
			
            
		</tbody>
	</table>
	
	
</t:formvalid>
</body>
<script type="text/javascript">


	
	

</script>

