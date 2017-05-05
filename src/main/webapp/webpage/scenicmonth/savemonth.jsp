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
<script type="text/javascript" src="webpage/js/calendar/WdatePicker.js"></script>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicMonthController.do?save" usePlugin="password" layout="table">
	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">
	<table style="width:750px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">单位名称</td>
                <td class="value" width="32%" colspan="4"><input type="text" value="${scenicdata.name}" name="licensenum" class="regionNum" errormsg="许可证编号不能为空"/><span class="red">*</span></td>
            </tr>
            <!--单位名称-->   
            <tr>
				<td align="right" width="16%"><span class="filedzt">时间</span></td>
				<td class="value" width="16%" colspan="2" >
				
				
				<input disabled="disabled" type="date" name="time" value="<fmt:formatDate value='${monthdata.time}' type="date" pattern="yyyy-MM"/>" /><span class="red">*</span></td>
                <td align="right" width="16%"><span class="filedzt">法人单位编号</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.code}" name="code" class="password"  errormsg="请输入密码"></td>
			</tr>
            <!--英文名称--> 
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${monthdata.taking}" name="taking" class="englishname" errormsg="请输入英文名称" /></td>
                <td align="right" width="16%"><span class="filedzt">其中门票收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="ticket" class="ogCode" errormsg="请输入组织机构代码"></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业税金及附加（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="businesstax" class="creditCode" errormsg="统一社会信用代码"/></td>
                <td align="right" width="16%"><span class="filedzt">利润总额（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="totalprofit" class="ogCode" errormsg="请输入组织机构代码"></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">年末从业人员</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="endemployee" class="deligate" errormsg="请输入法人代表" /></td>
                <td align="right" width="16%"><span class="filedzt">其中吸纳下岗职工（人）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="laidworker" class="adCode" errormsg="请输入行政区域代码"></td>
			</tr>
            <!--所在地区、详细地址-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">其中转移农村劳动力（人）</span></td>
				<td class="value" width="16%" colspan="4">
                <input type="text" value="" name="countrylabor" class="address" errormsg="请输入详细地址"> 	
                </td>
                
			</tr>
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">固定资产原价（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="price" class="price" errormsg="请输手机号" /></td>
                <td align="right" width="16%"><span class="filedzt">固定资产净值（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="asset" class="asset" errormsg="请输入电子邮箱"></td>
			</tr>
            <!--网址、邮政编码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="receptionnum" class="weburl" errormsg="请输网址" /></td>
                <td align="right" width="16%"><span class="filedzt">其中境外人数（人次）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="overnum" class="overnum" errormsg="请输入邮政编码"></td>
			</tr>
            <!--单位负责人-->
			 <tr>
				<td align="right" width="16%"><span class="filedzt">其中免票人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="freeticket" class="weburl" errormsg="请输网址" /></td>
                <td align="right" width="16%"><span class="filedzt">团散比</span></td>
                <td class="value" width="30%"><input type="text" value="" name="compare" class="zipCode" errormsg="请输入邮政编码"></td>
			</tr>
			
			
			
			
            <tr>
				<td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="principal" class="responsible" errormsg="请输单位负责人" /></td>
                <td align="right" width="16%"><span class="filedzt">填表人</span></td>
                <td class="value" width="30%">
                	<input type="text" value="" name="preparer" class="preparer" errormsg="请输入邮政编码">
                </td>
			</tr>
           
            <!--旅行社协会会员-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">电话</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="1" name="yes"></td>
                <td align="right" width="16%"><span class="filedzt">报出日期</span></td>
				<td class="value" width="30%"><input type="text" value="" name="qq" class="Wdate" onclick="WdatePicker()"  errormsg="请输入QQ" /></td>
			</tr>
            
		</tbody>
	</table>
	
	
</t:formvalid>
</body>
<script type="text/javascript">


	
	

</script>


