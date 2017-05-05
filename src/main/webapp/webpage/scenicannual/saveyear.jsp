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
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicAnnualController.do?save" usePlugin="password" layout="table">
	<input name="scenicdataid" type="hidden" value="${scenicdataid}">
	
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

 <table style="width:750px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<!--许可证号--> 
        	<div class="MainInfo_tit pdtb10">
            	三亚亚龙湾热带天堂森林公园-2016年（10-12月）数据
            </div>
            
        	<tr>
        
            	<td align="right" width="16%">资产总额（万元）：</td>
                <td class="value" width="16%" colspan="2"><input type="text" value="" name="assetstotal" datatype="n" class="regionNum" errormsg="许可证编号不能为空"/><span class="red">*</span></td>
                <td align="right" width="16%"></td>
                <td class="value" width="30%"></td>
            </tr>
            <!--单位名称-->
             
            <tr>

				<td align="right" width="16%"><span class="filedzt">年度建设投资（万元）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="buildinvest" datatype="n" class="companyname" errormsg="请输入单位名称" /><span class="red">*</span></td>
                <td align="right" width="16%"><span class="filedzt">拨款（万元）：</span></td>
                <td class="value" width="30%"><input type="text" value="" name="appropriation" datatype="n" class="password"  errormsg="请输入密码"/></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区内部建设投资（万元）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="inbuild" datatype="n" class="englishname" errormsg="请输入英文名称" /></td>
                <td align="right" width="16%"><span class="filedzt">贷款（万元）：</span></td>
                <td class="value" width="30%"><input type="text" value="" name="loan" datatype="n" class="ogCode" errormsg="请输入组织机构代码"/></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区外部建设投资（万元）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="outbuild" datatype="n" class="creditCode" errormsg="统一社会信用代码"/><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">自筹（万元）：</span></td>
                <td class="value" width="30%"><input type="text" value="" name="fundsself" datatype="n" class="ogCode" errormsg="请输入组织机构代码"/></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区经营总收入（万元）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="totalincome" datatype="n" class="deligate" errormsg="请输入法人代表" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%"></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">门票收入（万元）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="ticketincome" datatype="n" class="mobile" errormsg="请输手机号" /></td>
                <td align="right" width="16%"><span class="filedzt"> 商品收入（万元）：</span></td>
                <td class="value" width="30%"><input type="text" value="" name="shopincome" datatype="n" class="email" errormsg="请输入电子邮箱"/></td>
			</tr>
            <!--网址、邮政编码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">餐饮收入（万元）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="foodincome" datatype="n" class="weburl" errormsg="请输网址" /></td>
                <td align="right" width="16%"><span class="filedzt">交通收入（万元）：</span></td>
                <td class="value" width="30%"><input type="text" value="" name="trafficincome" datatype="n" class="zipCode" errormsg="请输入邮政编码"/></td>
			</tr>
            <!--单位负责人-->
			 <tr>
				<td align="right" width="16%"><span class="filedzt">演艺收入（万元）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="showincome" datatype="n" class="weburl" errormsg="请输网址" /></td>
                <td align="right" width="16%"><span class="filedzt">住宿收入（万元）：</span></td>
                <td class="value" width="30%"><input type="text" value="" name="putUpincome" datatype="n" class="zipCode" errormsg="请输入邮政编码"/></td>
			</tr>			
			
            <tr>
				<td align="right" width="16%"><span class="filedzt">其他收入（万元）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="otherincome" class="responsible" errormsg="请输单位负责人" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%">
                </td>
			</tr>
           
           
           
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人次（万人次）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="receptionnum" datatype="n" class="responsible" errormsg="请输单位负责人" /></td>
                <td align="right" width="16%"><span class="filedzt">其中免票人次（万人次 ：</span></td>
                <td class="value" width="30%">
                	<input type="text" value="" name="exemptTicketnum" datatype="n" class="zipCode" errormsg="请输入邮政编码" />
                </td>
			</tr>
            <!--旅行社协会会员-->
            <tr>
				<td align="right" width="16%"><span class="filedzt"></span></td>
				<td class="value" width="16%" colspan="2"></td>
                <td align="right" width="16%"><span class="filedzt">就业人数（人）：</span></td>
				<td class="value" width="30%"><input type="text" value="" name="jobnum" datatype="n" class="qqId" errormsg="请输入QQ" /></td>
			</tr>
			
			
			 <tr>
				<td align="right" width="16%"><span class="filedzt">固定用工（人）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="fixedworker" datatype="n"/></td>
                <td align="right" width="16%"><span class="filedzt">临时（季节性）用工（人次）：：</span></td>
				<td class="value" width="30%"><input type="text" value="" name="tempworker" datatype="n" class="qqId" errormsg="请输入QQ" /></td>
			</tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">专职导游人数（人）：</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="" name="guidenum" datatype="n" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
				<td class="value" width="30%"><input type="text" value="" name="qq" class="qqId" errormsg="请输入QQ" /></td>
			</tr>
			<tr>
               	<td><span>备注：</span></td>
                <td class="value" width="16%"  colspan="4"><textarea rows="10" name="remarks" style="width:100%"></textarea></td>
            </tr>
			
            
		</tbody>
	</table>
 
</t:formvalid>
</body>