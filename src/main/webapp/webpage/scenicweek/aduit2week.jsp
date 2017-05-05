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
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicMonthController.do?save" usePlugin="password" layout="table">
	<!-- 
	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">
     -->
	<input id="id" type="hidden" value="${scenicSpotWeek.id }">
	
	        <div class="MainInfo_tit mgb20" style="margin-bottom: 0;margin-top: 20px">
            	${scenicdata.name}-周报数据
            </div>
	
	<table style="width:750px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<!--许可证号-->
        	 <tr>
				<td align="right" width="16%"><span class="filedzt">年份</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  value="${scenicweek.year }" name="year"  /></td>
                <td align="right" width="16%"></td>
                <td class="value" width="30%"></td>
			</tr>
        	<tr>
            	<td align="right" width="16%">周期(${scenicweek.weekRange})</td>
                <td class="value" width="16%" colspan="2"><input type="text" value="${scenicweek.cycle}" disabled="disabled" name="licensenum" class="regionNum" errormsg="许可证编号不能为空"/><span class="red"></span></td>
                <td align="right" width="16%">单位名称 </td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.name}" disabled="disabled" name="accountNum" class="accountNum" errormsg="请输入账号"></td>
            </tr>
            <!--单位名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  value="<fmt:formatNumber value="${scenicweek.taking}" pattern="#.#"/>"  disabled="disabled" name="taking" class="companyname" errormsg="请输入单位名称" /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">其中门票收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="<fmt:formatNumber value="${scenicweek.ticket}" pattern="#.#"/>" disabled="disabled" name="ticket" class="password" datatype="money " errormsg="请输入密码"></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicweek.receptionnum}" disabled="disabled" name="receptionnum" class="englishname" errormsg="请输入英文名称" /></td>
                <td align="right" width="16%"><span class="filedzt">其中境外人数（人次）</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicweek.overnum}" disabled="disabled" name="overnum" class="ogCode" errormsg="请输入组织机构代码"></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">团散比</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  value="${scenicweek.compare}" disabled="disabled" name="compare" class="creditCode" errormsg="请输入团散比"/></td>
                <td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicweek.principal}" disabled="disabled" name="principal" class="ogCode" errormsg="请输入组织机构代码"></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">填表人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicweek.preparer}" name="preparer" class="deligate" errormsg="请输入法人代表" /></td>
                <td align="right" width="16%"><span class="filedzt">电话</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicweek.telephone}" disabled="disabled" name="telephone" class="adCode" errormsg="请输入行政区域代码"></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">报出日期</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"   value=<fmt:formatDate pattern="yyyy-MM-dd" value="${scenicweek.reportdate}" />  ></td>
                <td align="right" width="16%"></td>
                <td class="value" width="30%"></td>
			</tr>
            <!--网址、邮政编码-->
            
			
            
		</tbody>
	</table>
	
	
</t:formvalid>
</body>
<script type="text/javascript">


	
	

</script>

