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
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicQuarterController.do?updateUser" usePlugin="password" layout="table">
	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">
	<input name="quarterid"  type="hidden" value="${quarterid }">
   <input name="sid" type="hidden" value="${quarterdata.sid }">
   <input name="status" type="hidden" value="${quarterdata.status }">
   <input name="score" type="hidden" value="${quarterdata.score }">
	
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        
            <tr>
					<td align="right" width="10%">年份</td>
					<td class="value" width="30%" colspan="2"><input type="text" name="year" readOnly="readOnly"
						class="inputSize" value="${quarterdata.year}"></td>
						 </tr>
						 <tr>
					<td  align="right" width="10%" >季度</td>
					<td class="value" width="30%" colspan="2"><select
						name="quarter" style="width: 146px" onfocus="this.defaultIndex=this.selectedIndex" onchange="this.selectedIndex=this.defaultIndex">
							<option value="1"
								<c:if test="${'1' eq quarterdata.quarter }">selected</c:if>>第一季度</option>
							<option value="2"
								<c:if test="${'2' eq quarterdata.quarter }">selected</c:if>>第二季度</option>
							<option value="3"
								<c:if test="${'3' eq quarterdata.quarter }">selected</c:if>>第三季度</option>
							<option value="4"
								<c:if test="${'4' eq quarterdata.quarter }">selected</c:if>>第四季度</option>
					</select></td>
				</tr>
        	<tr>
            	<td align="right" width="10%">景区总收入（万元）</td>
                <td class="value" width="30%" colspan="2"><input type="text" value="${quarterdata.totalincome}" name="totalincome" datatype="xs" /><span class="red"></span></td>
                
            </tr>
            <!--单位名称-->
            <tr>
				<td align="right" width="10%"><span class="filedzt">其中门票收入（万元）</span></td>
				<td class="value" width="30%" colspan="2"><input type="text" value="${quarterdata.ticketincome}" name="ticketincome" datatype="xs" /><span class="red"></span></td>
               
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="10%"><span class="filedzt">接待人次（万人次）</span></td>
				<td class="value" width="30%" colspan="2"><input type="text" value="${quarterdata.receptionnum}" name="receptionnum"  datatype="xs"  /></td>
               
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="10%"><span class="filedzt">其中免票人次（万人次）</span></td>
				<td class="value" width="30%" colspan="2"><input type="text" value="${quarterdata.exemptticketnum}" name="exemptticketnum" datatype="xs" /></td>
                
			</tr>
           
			<tr>
               	<td><span>备注</span></td>
                <td><textarea rows="10" name="remarks" style="width:60%">${quarterdata.remarks}</textarea></td>
            </tr>
			
            
		</tbody>
	</table>
</t:formvalid>
</body>