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
		<input name="yearid" type="hidden" value="${yearid}">
	
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

 <table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<!--许可证号--> 
        	<div class="MainInfo_tit pdtb10">
            	<span>${scenicdata.name}年报数据  </span>
            </div>
        	<tr>
        
            	<td align="right" width="16%">年份</td>
                <td class="value" width="16%" colspan="2"><input type="text" name="year" readOnly="readOnly"
						class="inputSize" value="${yeardata.year}"></td>
                <td align="right" width="16%">资产总额（万元）</td>
                <td class="value" width="30%"><input type="text" value="${yeardata.assetstotal}" name="assetstotal" datatype="num" class="regionNum" errormsg="请输入资产总额"/><span class="red"></span></td>
            </tr>
            <!--单位名称-->
             
            <tr>   

				<td align="right" width="16%"><span class="filedzt">年度建设投资（万元）  </span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.buildinvest}" name="buildinvest" datatype="num" class="companyname" errormsg="请输入年度建设投资" /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">拨款（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.appropriation}" name="appropriation" datatype="num" class="password"  errormsg="请输入拨款"/></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区内部建设投资（万元）  </span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.inbuild}" name="inbuild" datatype="num" class="englishname" errormsg="请输入景区内部建设投资" /></td>
                <td align="right" width="16%"><span class="filedzt">贷款（万元）</span></td>
                <td class="value" width="30%"><input type="text"  value="${yeardata.loan}" name="loan" datatype="num" class="ogCode" errormsg="请输入贷款"/></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区外部建设投资（万元）  </span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.outbuild}" name="outbuild" datatype="num" class="creditCode" errormsg="请输入景区外部建设投资"/><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">自筹（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.fundsself}" name="fundsself" datatype="num" class="ogCode" errormsg="请输入自筹"/></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区经营总收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.totalincome}" name="totalincome" datatype="num" class="deligate" errormsg="请输入景区经营总收入" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%"></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">门票收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.ticketincome}" name="ticketincome" datatype="num" class="mobile" errormsg="请输入门票收入" /></td>
                <td align="right" width="16%"><span class="filedzt"> 商品收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.shopincome}" name="shopincome" datatype="num" class="email" errormsg="请输入商品收入"/></td>
			</tr>
            <!--网址、邮政编码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">餐饮收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.foodincome}" name="foodincome" datatype="num" class="weburl" errormsg="请输入餐饮收入" /></td>
                <td align="right" width="16%"><span class="filedzt">交通收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.trafficincome}" name="trafficincome" datatype="num" class="zipCode" errormsg="请输入交通收入"/></td>
			</tr>
            <!--单位负责人-->
			 <tr>
				<td align="right" width="16%"><span class="filedzt">演艺收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.showincome}" name="showincome" datatype="num" class="weburl" errormsg="请输入演艺收入" /></td>
                <td align="right" width="16%"><span class="filedzt">住宿收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.putUpincome}" name="putUpincome" datatype="num" class="zipCode" errormsg="请输入住宿收入"/></td>
			</tr>			
			
            <tr>
				<td align="right" width="16%"><span class="filedzt">其他收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.otherincome}" name="otherincome" datatype="num" class="responsible" errormsg="请输入其他收入" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%">
                </td>
			</tr>
           
           
           
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人次（万人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.receptionnum}" name="receptionnum" datatype="num" class="responsible" errormsg="请输入接待人次" /></td>
                <td align="right" width="16%"><span class="filedzt">其中免票人次（万人次） </span></td>
                <td class="value" width="30%">
                	<input type="text" value="${yeardata.exemptTicketnum}" name="exemptTicketnum" datatype="num" class="zipCode" errormsg="请输入其中免票人次" />
                </td>
			</tr>
            <!--旅行社协会会员-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">就业人数（人）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.jobnum}" name="jobnum" datatype="n" class="qqId" errormsg="请输入就业人数" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
				<td class="value" width="30%"></td>
			</tr>
			
			
			 <tr>
				<td align="right" width="16%"><span class="filedzt">固定用工（人）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.fixedworker}" name="fixedworker" datatype="n" errormsg="请输入固定用工"/></td>
                <td align="right" width="16%"><span class="filedzt">临时（季节性）用工（人次）</span></td>
				<td class="value" width="30%"><input type="text" value="${yeardata.tempworker}" name="tempworker" datatype="n" class="qqId" errormsg="请输入临时（季节性）用工" /></td>
			</tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">专职导游人数（人）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.guidenum}" name="guidenum" datatype="n" errormsg="请输入专职导游人数"/></td>
                <td align="right" width="16%">上报时间<span class="filedzt"></span></td>
				<td class="value" width="30%"><input type="text" value=<fmt:formatDate pattern="yyyy-MM-dd" value="${yeardata.creatTime}" />></td>
			</tr>
			<tr>
               	<td><span>备注</span></td>
                <td class="value" width="16%"  colspan="4"><textarea rows="10"   name="remarks" style="width:100%">${yeardata.remarks}</textarea></td>
            </tr>
			
            
		</tbody>
	</table>
 
</t:formvalid>
</body>