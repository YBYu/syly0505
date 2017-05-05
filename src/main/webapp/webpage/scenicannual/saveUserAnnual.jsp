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
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicAnnualController.do?saveUser" usePlugin="password" layout="table">
	<input name="scenicdataid" type="hidden" value="${scenicdataid}">
	
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

 <table style="width:750px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	
        	<tr>
        <td align="right" width="16%">年份</td>
                <td class="value" width="16%" colspan="2"><input type="text" name="year" datatype="n" errormsg="请输入正确的年份"
						class="inputSize" value="${year}"></td>
                <td align="right" width="16%">资产总额（万元）</td>
                <td class="value" width="30%"><input type="text" value="" name="assetstotal" datatype="num" class="regionNum" errormsg="请输入正确的资产总额"/><span class="red"></span></td>
            </tr>
            <!--单位名称-->
             
            <tr>

				<td align="right" width="16%"><span class="filedzt">年度建设投资（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="buildinvest" datatype="xs"/><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">拨款（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="appropriation" datatype="xs" /></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区内部建设投资（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="inbuild" datatype="xs"/></td>
                <td align="right" width="16%"><span class="filedzt">贷款（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="loan" datatype="xs"/></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区外部建设投资（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="outbuild" datatype="xs"/><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">自筹（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="fundsself" datatype="xs"/></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区经营总收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="totalincome" datatype="xs" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%"></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">门票收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="ticketincome" datatype="xs" /></td>
                <td align="right" width="16%"><span class="filedzt"> 商品收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="shopincome" datatype="xs"/></td>
			</tr>
            <!--网址、邮政编码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">餐饮收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="foodincome" datatype="xs" /></td>
                <td align="right" width="16%"><span class="filedzt">交通收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="trafficincome" datatype="xs"/></td>
			</tr>
            <!--单位负责人-->
			 <tr>
				<td align="right" width="16%"><span class="filedzt">演艺收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="showincome" datatype="xs"/></td>
                <td align="right" width="16%"><span class="filedzt">住宿收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="" name="putUpincome" datatype="xs"/></td>
			</tr>			
			
            <tr>
				<td align="right" width="16%"><span class="filedzt">其他收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="otherincome" datatype="xs" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%">
                </td>
			</tr>
           
           
           
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人次（万人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="receptionnum"  datatype="xs" /></td>
                <td align="right" width="16%"><span class="filedzt">其中免票人次（万人次 ）</span></td>
                <td class="value" width="30%">
                	<input type="text" value="" name="exemptTicketnum"  datatype="xs" />
                </td>
			</tr>
            <!--旅行社协会会员-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">就业人数（人）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="jobnum"  datatype="n1-7" class="qqId" errormsg="请输入就业人数" /></td>
                <td align="right" width="16%"><span class="filedzt">专职导游人数（人）</span></td>
				<td class="value" width="30%"><input type="text" value="" name="guidenum"  datatype="n1-7"  errormsg="请输入专职导游人数"/></td>
			</tr>
			
			
			 <tr>
				<td align="right" width="16%"><span class="filedzt">其中固定用工（人）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="fixedworker" datatype="n1-7" errormsg="请输入固定用工"/></td>
                <td align="right" width="16%"><span class="filedzt">临时用工（人次）</span></td>
				<td class="value" width="30%"><input type="text" value="" name="tempworker"  datatype="n1-7" class="qqId" errormsg="请输入临时用工" /></td>
			</tr>
			
			<tr>
               	<td><span>备注：</span></td>
                <td class="value" width="16%"  colspan="4"><textarea rows="10" name="remarks" style="width:100%"></textarea></td>
            </tr>
			
            
		</tbody>
	</table>
 
</t:formvalid>
</body>