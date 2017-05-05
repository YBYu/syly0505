<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">
<!--  
<link rel="stylesheet" type="text/css" href="plug-in/style/yuebao/style.css">
webapp/webpage/js/calendar
-->
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<!-- <script type="text/javascript" src="webpage/js/calendar/WdatePicker.js"></script> -->
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicMonthController.do?save" usePlugin="password" layout="table">
	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">
	<input type="hidden" name="monthid" value="${monthid}" }>
	<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>

        	<!--许可证号-->
        	<div class="MainInfo_tit pdtb10">
            	${scenicdata.name}-月报数据  
            </div>
            <!--单位名称-->   
            <tr>
				<td align="right" width="16%"><span class="filedzt">年份</span></td>
				<td class="value" width="16%" colspan="2"><input name="year" type="text" value="${monthdata.year}"    /></td>
                <td align="right" width="16%"><span class="filedzt">月份</span></td>
                <td class="value" width="30%"><input type="text" name="month" value="${monthdata.month}"   ></td>
			</tr>
            <tr>
			<%-- 	<td align="right" width="16%"><span class="filedzt">时间</span></td>
				<td class="value" width="16%" colspan="2" >
				
				
				<input disabled="disabled" type="date" name="time" value="<fmt:formatDate value='${monthdata.time}' type="date" pattern="yyyy-MM"/>" /><span class="red">*</span></td> --%>
              <td align="right" width="16%">单位名称</td>
                <td class="value" width="32%" colspan="2"><input type="text" disabled="disabled"  value="${scenicdata.name}" name="name" class="regionNum" errormsg="许可证编号不能为空"/></td>
                <td align="right" width="16%"><span class="filedzt">法人单位编号</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.code}" disabled="disabled"  name="code" class="password"  errormsg="请输入正确法人单位编号"></td>
			</tr>
            <!--英文名称--> 
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="<fmt:formatNumber value="${monthdata.taking}" pattern="#0.#################"/>"  name="taking"   datatype="num" class="text" errormsg="请输入正确营业收入" /></td>
                <td align="right" width="16%"><span class="filedzt">其中门票收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${monthdata.ticket}" name="ticket" class="ogCode"  datatype="num"  errormsg="请输入正确组织机构代码"></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业税金及附加（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${monthdata.businesstax}" name="businesstax" class="creditCode" datatype="num" errormsg="请输入正确营业税金及附加"/></td>
                <td align="right" width="16%"><span class="filedzt">利润总额（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${monthdata.totalprofit}" name="totalprofit" class="ogCode" datatype="num" errormsg="请输入正确组织机构代码"></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">月末从业人员</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${monthdata.endemployee}" name="endemployee" class="deligate" datatype="n" errormsg="请输入正确年末从业人员" /></td>
                <td align="right" width="16%"><span class="filedzt">其中吸纳下岗职工（人）</span></td>
                <td class="value" width="30%"><input type="text" value="${monthdata.laidworker}" name="laidworker" class="adCode" datatype="n" errormsg="请输入正确其中吸纳下岗职工"></td>
			</tr>
            <!--所在地区、详细地址-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">其中转移农村劳动力（人）</span></td>
				<td class="value" width="16%" colspan="4">
                <input type="text" value="${monthdata.countrylabor}" name="countrylabor" class="address" datatype="n" errormsg="请输入正确其中转移农村劳动力"> 	
                </td>
                
			</tr>
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">固定资产原价（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${monthdata.price}" name="price" class="price" datatype="num" errormsg="请输入正确固定资产原价" /></td>
                <td align="right" width="16%"><span class="filedzt">固定资产净值（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${monthdata.asset}" name="asset" class="asset" datatype="num" errormsg="请输入正确固定资产净值"></td>
			</tr>
            <!--网址、邮政编码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${monthdata.receptionnum}"  name="receptionnum" class="weburl" datatype="n" errormsg="请输入正确接待人数" /></td>
                <td align="right" width="16%"><span class="filedzt">其中境外人数（人次）</span></td>
                <td class="value" width="30%"><input type="text" value="${monthdata.overnum}" name="overnum" class="overnum" datatype="n" errormsg="请输入正确其中境外人数"></td>
			</tr>
            <!--单位负责人-->
			 <tr>
				<td align="right" width="16%"><span class="filedzt">其中免票人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${monthdata.freeticket}" name="freeticket" class="weburl" datatype="n" errormsg="请输入正确免票人数" /></td>
                <td align="right" width="16%"><span class="filedzt">团散比</span></td>
                <td class="value" width="30%"><input type="text" value="${monthdata.compare}" name="compare" class="zipCode"   errormsg="请输入正确团散比"></td>
			</tr>
			
			   
			
			
            <tr>
				<td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" name="principal" value="${monthdata.principal}" class="responsible" datatype="*" errormsg="请输入正确单位负责人" /></td>
                <td align="right" width="16%"><span class="filedzt">填表人</span></td>
                <td class="value" width="30%">
                	<input type="text" value="${monthdata.preparer}" name="preparer" class="preparer" datatype="*" errormsg="请输入正确邮政编码">
                </td>
			</tr>
           
            <!--旅行社协会会员-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">电话</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${monthdata.telephone}" datatype="m" errormsg="请输入正确手机号"  name="telephone"></td>
                <td align="right" width="16%"><span class="filedzt">报出日期</span></td>
				<td class="value" width="30%"><input type="text" name="reportdate"   value=<fmt:formatDate pattern="yyyy-MM-dd" value="${monthdata.reportdate}" />   /></td>
			</tr>
            

		</tbody>
	</table>
	
	
</t:formvalid>
</body>
<script type="text/javascript">
</script>


