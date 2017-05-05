<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="plug-in/style/zhoubao/style.css">
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<!-- <script type="text/javascript" src="webpage/js/calendar/WdatePicker.js"></script> -->
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicWeekController.do?save" usePlugin="password" layout="table">
	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">
		<input name="weekid" type="hidden"  value="${weekid}">
		<input name="weekRange" type="hidden" value="${weekdata.weekRange}">
	  <div class="MainInfo_tit mgb20" style="margin-bottom: 0;margin-top: 20px">
            	${scenicdata.name}-周报数据
            </div>
	<table style="width:750px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		
		<tbody>
            	
				<tr>
				<td align="right" width="16%">年份</td>
               	 <td class="value" width="16%" colspan="2"><input type="text" readonly="readonly" value="${weekdata.year}"  name="year"  /><span class="red"></span></td>
					<td align="right" width="16%">周期</td>
                <td class="value" width="30%"><input type="text" value="${weekdata.cycle}" name="cycle" readonly="readonly">(${weekdata.weekRange})</td>
                </tr>
                <tr>
				<%-- 	
				</tr>
        	<tr>
            	<td align="right" width="16%">周期</td>
                <td class="value" width="16%" colspan="2"><input type="text" value=""  name="cycle" datatype=n  errormsg="周期只能为数字"/><span class="red"></span></td> --%>
                <td align="right" width="16%">单位名称 </td>
                <td class="value" width="30%" colspan="4"><input type="text" value="${scenicdata.name}" name="accountNum" class="accountNum" errormsg="请输入账号" readonly="readonly" style="width:434px"/></td>
            </tr>
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${weekdata.taking}" name="taking" class="companyname" datatype="xs"    /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">其中门票收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${weekdata.ticket }" name="ticket" class="password"  datatype="xs"  /></td>
			</tr>
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${weekdata.receptionnum }" name="receptionnum" class="englishname" datatype="n1-7" errormsg="只能为1-7位整数" /></td>
                <td align="right" width="16%"><span class="filedzt">其中境外人数（人次）</span></td>
                <td class="value" width="30%"><input type="text" value="${weekdata.overnum }" name="overnum" class="ogCode" datatype="n1-7" errormsg="只能为1-7位整数"/></td>
			</tr>
            <tr>
				<td align="right" width="16%"><span class="filedzt">团散比</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${weekdata.compare }" name="compare" class="creditCode"  /></td>
                <td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
                <td class="value" width="30%"><input type="text" value="<c:if test='${empty weekdata.principal}'>${scenicdata.chargename }</c:if><c:if test='${not empty weekdata.principal}'>${weekdata.principal }</c:if>" name="principal" class="ogCode" datatype="*" errormsg="请输入单位负责人"/></td>
			</tr>
            <tr>
				<td align="right" width="16%"><span class="filedzt">填表人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="<c:if test='${empty weekdata.preparer}'>${scenicdata.informantname }</c:if><c:if test='${not empty weekdata.preparer}'>${weekdata.preparer }</c:if>" name="preparer" class="deligate" datatype="*" errormsg="请输入法人代表" /></td>
                <td align="right" width="16%"><span class="filedzt">电话</span></td>
                <td class="value" width="30%"><input type="text" value="<c:if test='${empty weekdata.telephone}'>${scenicdata.informantphone }</c:if><c:if test='${not empty weekdata.telephone}'>${weekdata.telephone }</c:if>" name="telephone" class="adCode" datatype="m" errormsg="请输入正确的电话"/></td>
			</tr>
            
			
            
		</tbody>
	</table>
	
	
</t:formvalid>
</body>
<script type="text/javascript">


	
	

</script>


