<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">


</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="saveflightMonth" refresh="false"  action="flightMonthlyController.do?savefm" callback="reloadTable" dialog="true" layout="table">
	<input id="id" value="${flightMonthly.id }" type="hidden" name="id">
	<input   type="hidden" value="${flightMonthly.otid }" name="otid">
		<input  value="${flightMonthly.dates }" type="hidden" name="dates">
	

<table style=" font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
<tbody>
		 <tr>
				<td align="right" width="16%"><span class="filedzt">企业名称</span></td>
				<td class="value" width="16%" colspan="2"><input disabled="disabled" value="${flightMonthly.otherTravelInfo.name }"  type="text" datatype="*" ></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%"><input type="text" disabled="disabled" value="<c:if test="${flightMonthly.otherTravelInfo.type==1 }">高尔夫项目</c:if><c:if test="${flightMonthly.otherTravelInfo.type==2 }">游艇项目</c:if><c:if test="${flightMonthly.otherTravelInfo.type==3 }">空中飞行项目</c:if><c:if test="${flightMonthly.otherTravelInfo.type==4 }">机场项目</c:if><c:if test="${flightMonthly.otherTravelInfo.type==5 }" >动车项目</c:if>"></td>	         
			</tr>
			<tr>
            	<td align="right" width="16%">月报时间</td>
                <td class="value" width="16%" colspan="4">
                 <input  value="${flightMonthly.smonth}" type="text"  name="smonth"    readonly="readonly" ></td>
                <%-- <input  value="<fmt:formatDate value="${flightMonthly.dates }" type="date"/>" type="text"  datatype="*"  ></td> --%>
                <%-- <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <input  value="${otherTravelInfo.city }" type="area" datatype="*"">
                </td> --%>
            </tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">所在市</td>
                <td class="value" width="16%" colspan="2"><input disabled="disabled"  value="${flightMonthly.otherTravelInfo.city }" type="text" datatype="*"  ></td>
                <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <input  value="<c:if test="${flightMonthly.otherTravelInfo.area ==0 }">市辖区</c:if><c:if test="${flightMonthly. otherTravelInfo.area ==1 }">崖州区</c:if><c:if test="${flightMonthly. otherTravelInfo.area ==2 }">海棠区</c:if><c:if test="${flightMonthly. otherTravelInfo.area ==3 }">天涯区</c:if><c:if test="${flightMonthly. otherTravelInfo.area ==4 }">吉阳区</c:if>" disabled="disabled" type="text" datatype="*"">
                </td>
            </tr>
            <!--法人信息-->
            <tr>
				<td align="right" width="16%">联系电话<span class="filedzt">:</span></td>
				<td class="value" width="16%" colspan="2"><input value="${flightMonthly.otherTravelInfo.phone}" type="text" readonly="readonly"  ></td>
                <td align="right" width="16%">邮政编码<span class="filedzt"></span></td>
                <td class="value" width="30%"><input value="${flightMonthly.otherTravelInfo.postalcode}" type="text" readonly="readonly" ></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人次（单位：人次）</span></td>
				<td class="value" width="16%" colspan="2"><input value="${flightMonthly.receptionnum}" type="text" datatype="n1-9" errormsg="只能为1-9位整数" name="receptionnum"></td>
                <td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
                <td class="value" width="30%"><input value="${flightMonthly.businessincome}" type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="businessincome"></td>
			</tr>    
			<tr>
            	<td align="right" width="16%">飞行架次</td>
                <td class="value" width="16%" colspan="4"><input  value="${flightMonthly.flighttimes }" type="text"  datatype="n1-9" errormsg="只能为1-9位整数"  name="flighttimes"   ></td>
                <%-- <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <input  value="${otherTravelInfo.city }" type="area" datatype="*"">
                </td> --%>
            </tr> 
           <tr>
            	<td align="right" width="16%"><span class="filedzt">填报人</span></td>
 				<td class="value" width="16%" colspan="4"><input  value="${flightMonthly.reporter }" datatype="*" type="text"  name="reporter"></td>
               <!-- <td align="right" width="16%" ><span class="filedzt">填报时间</span></td>
  			   <td class="value" width="30%" ><input class="easyui-datebox"  type="text" name="writeDate"></td> -->
            </tr>
		</tbody>
	</table>
</t:formvalid>
