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
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicMonthController.do?userSave" usePlugin="password" layout="table">
	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">

	
	
	
	
	<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<tr>
				<td align="right" width="16%"><span class="filedzt">年份</span></td>
				<td class="value" width="16%" colspan="2"><input name="year" type="text" value="${year}"   datatype="n" errormsg="请输入正确的年份" /></td>
                <td align="right" width="16%"><span class="filedzt">月份</span></td>
                <td class="value" width="30%"><input type="text" name="month" value="${month}"   datatype="n" errormsg="请输入正确的月份"></td>
			</tr>
        	<%-- <tr>
            	<td align="right" width="16%">单位名称</td>
                <td class="value" width="32%" colspan="2"><input type="text" value="${scenicdata.name}" name="licensenum" class="regionNum"  errormsg="许可证编号不能为空"/><span class="red">*</span></td>
           		<td align="right" width="16%"><span class="filedzt">法人单位编号</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.code}" name="code" class="password"  errormsg="请输入密码"/></td>
            </tr> --%>
            <!--单位名称-->   
            <!--英文名称--> 
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${monthdata.taking}" name="taking" datatype="xs" class="englishname" errormsg="请输入数字" /></td>
                <td align="right" width="16%"><span class="filedzt">其中门票收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="ticket" datatype="xs" class="ogCode" errormsg="请输入数字"/></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业税金及附加（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicmonth.businesstax }" name="businesstax" datatype="xs" class="notnecessary" errormsg="请输入数字"/></td>
                <td align="right" width="16%"><span class="filedzt">利润总额（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicmonth.totalprofit }" name="totalprofit" datatype="decimals4" class="notnecessary" errormsg="请输入数字"/></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">月末从业人员</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicmonth.endemployee }" name="endemployee" datatype="n1-7" class="notnecessary" errormsg="请输入整数" /></td>
                <td align="right" width="16%"><span class="filedzt">其中吸纳下岗职工（人）</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicmonth.laidworker }" name="laidworker" class="notnecessary" datatype="n1-7" errormsg="请输入整数"/></td>
			</tr>
            <!--所在地区、详细地址-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">其中转移农村劳动力（人）</span></td>
				<td class="value" width="16%" colspan="4">
                <input type="text" value="${scenicmonth.countrylabor }" name="countrylabor" datatype="n1-7" class="notnecessary" errormsg="请输入整数"/> 	
                </td>
                
			</tr>
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">固定资产原价（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicmonth.price }" name="price" datatype="xs" class="notnecessary" errormsg="请输入数字" /></td>
                <td align="right" width="16%"><span class="filedzt">固定资产净值（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicmonth.asset}" name="asset" datatype="xs" class="notnecessary" errormsg="请输入数字"/></td>
			</tr>
            <!--网址、邮政编码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="receptionnum" datatype="n1-7" class="weburl" errormsg="请输入整数" /></td>
                <td align="right" width="16%"><span class="filedzt">其中境外人数（人次）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="overnum" class="overnum" datatype="n1-7" errormsg="请输入整数"/></td>
			</tr>
            <!--单位负责人-->
			 <tr>
				<td align="right" width="16%"><span class="filedzt">其中免票人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="freeticket" datatype="n1-7" class="weburl" errormsg="请输入整数" /></td>
                <td align="right" width="16%"><span class="filedzt">团散比</span></td>
                <td class="value" width="30%"><input type="text" value="" name="compare" class="zipCode"  errormsg="请输入整数"/></td>
			</tr>
			
			
			
			
            <tr>
				<td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicData.chargename }" name="principal" datatype="*" class="responsible" errormsg="请输单位负责人" /></td>
                <td align="right" width="16%"><span class="filedzt">填表人</span></td>
                <td class="value" width="30%">
                	<input type="text" value="${scenicData.informantname }" name="preparer" datatype="*"  class="preparer" errormsg="请输入填表人"/>
                </td>
			</tr>
           
            <!--旅行社协会会员-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">电话</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicData.informantphone }" name="telephone" datatype="m" class="qqId" errormsg="请输入电话号码" /></td>
                <!-- <td align="right" width="16%"><span class="filedzt">报出日期</span></td>
				<td class="value" width="30%"><input type="text" value="" name="reportdate" class="Wdate" onClick="WdatePicker()" errormsg="请输入日期" /></td> -->
			</tr>
            
		</tbody>
	</table>
	
	
</t:formvalid>
</body>
<script type="text/javascript">
$('.notnecessary').click(function(){
	$(this).val('');
});
</script>


