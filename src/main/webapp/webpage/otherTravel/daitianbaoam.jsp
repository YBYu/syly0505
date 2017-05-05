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
<body style="" scroll="yes" >
<t:formvalid formid="saveAirportMonth"  action="airportMonthlyController.do?saveDaiam"  callback="reloadTable" dialog="true"  layout="table">
	<input  value="${airportMonthly.otid }" type="hidden" name="otid">
	<input  value="${airportMonthly.id }" type="hidden" name="id">

<table style=" font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="16%"><span class="filedzt">企业名称1</span></td>
				<td class="value" width="16%" colspan="2"><input value="${airportMonthly.otherTravelInfo.name }"   type="text" datatype="*"  disabled="disabled"></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%"><input disabled="disabled" type="text" value="<c:if test="${airportMonthly.otherTravelInfo.type==1 }">高尔夫项目</c:if><c:if test="${airportMonthly.otherTravelInfo.type==2 }">游艇项目</c:if><c:if test="${airportMonthly.otherTravelInfo.type==3 }">空中飞行项目</c:if><c:if test="${airportMonthly. otherTravelInfo.type==4 }">机场项目</c:if><c:if test="${airportMonthly. otherTravelInfo.type==5 }" >动车项目</c:if>"></td>	         
			</tr>
			<tr>
            	<td align="right" width="16%">月报时间</td>
                <!--  <td class="value" width="16%" colspan="4"> -->
                <td class="value" width="16%" colspan="4">
                <input  value="${airportMonthly.smonth}" type="text"     disabled="disabled" datatype="*" ></td>
                
            </tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">所在市</td>
                <td class="value" width="16%" colspan="2"><input disabled="disabled" value="${airportMonthly.otherTravelInfo.city }" type="text" datatype="*" "></td>
                <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <input disabled="disabled" value="<c:if test="${airportMonthly.otherTravelInfo.area ==0 }">市辖区</c:if><c:if test="${airportMonthly. otherTravelInfo.area ==1 }">崖州区</c:if><c:if test="${airportMonthly. otherTravelInfo.area ==2 }">海棠区</c:if><c:if test="${airportMonthly. otherTravelInfo.area ==3 }">天涯区</c:if><c:if test="${airportMonthly. otherTravelInfo.area ==4 }">吉阳区</c:if>" type="text" datatype="*" ">
                </td>
            </tr>
            <!--法人信息-->
            <tr>
				<td align="right" width="16%">联系电话<span class="filedzt">:</span></td>
				<td class="value" width="16%" colspan="2"><input value="${airportMonthly.otherTravelInfo.phone}"  type="text"  name="phone" readonly="readonly"></td>
                <td align="right" width="16%">邮政编码<span class="filedzt"></span></td>
                <td class="value" width="30%"><input value="${airportMonthly.otherTravelInfo.postalcode}"   type="text" name="postalcode " readonly="readonly"></td>
			</tr>   
			<tr>
				<td align="right" width="16%"><span class="filedzt">填报人</span></td>
				<td class="value" width="16%" colspan="4"><input value="${airportMonthly.reporter }"  type="text"  name="reporter"></td>
                <%-- <td align="right" width="16%"><span class="filedzt">填报时间</span></td>
                <td class="value" width="30%"><input  value="${airportMonthly.reportdate }"    type="text" class="easyui-datebox" name="reportdate"></td> --%>
			</tr>         <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划运输起降(单位降次)</span></td>
				<td class="value" width="16%" colspan="2"><input  value="${airportMonthly.plansortie }" type="text" datatype="n1-9" errormsg="只能为1-9位整数" name="plansortie"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际运输起降(单位降次)</span></td>
                <td class="value" width="30%"><input  value="${airportMonthly.actualsortie }" type="text" datatype="n1-9" errormsg="只能为1-9位整数" name="actualsortie"></td>
			</tr>
			<tr>
      	 <td align="right" width="16%">全年计划完成的运输(单位降次)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"  value="${airportMonthly.plansortieyear }" datatype="n1-9" errormsg="只能为1-9位整数" name="plansortieyear"  ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划出发运量-旅客(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input  value="${airportMonthly.planouttraveller }" type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="planouttraveller"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际出发运量-旅客(万人)</span></td>
                <td class="value" width="30%"><input   type="text" value="${airportMonthly.actualouttraveller }" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="actualouttraveller"></td>
			</tr>
		<tr>
      	 <td align="right" width="16%">全年计划完成的到达运量-旅客(万人)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"  value="${airportMonthly.planouttravelleryear }" name="planouttravelleryear"  datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)" ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的出发运量-货邮行(吨)</span></td>
				<td class="value" width="16%" colspan="2"><input  value="${airportMonthly.planoutwill }" type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="planoutwill"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的出发运量-货邮行(吨)</span></td>
                <td class="value" width="30%"><input   type="text" value="${airportMonthly.actualoutwill }" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="actualoutwill"></td>
			</tr>
			<tr>
      	 <td align="right" width="16%">全年计划完成的出发货运-货邮行(吨)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"  value="${airportMonthly.planoutwillyear }" name="planoutwillyear"  datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)" ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的到达运量-旅客(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" value="${airportMonthly.planIntegerraveller }" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="planIntegerraveller"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的到达运量-旅客(万人)</span></td>
                <td class="value" width="30%"><input   type="text" value="${airportMonthly.actualIntegerraveller }" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="actualIntegerraveller"></td>
			</tr>
		<tr>
      	 <td align="right" width="16%">全年计划完成的到达运量-旅客(万人)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"  value="${airportMonthly.planIntegerravelleryear }"  name="planIntegerravelleryear"  datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的到达运量-货邮行(吨)</span></td>
				<td class="value" width="16%" colspan="2"><input  value="${airportMonthly.planinwill }" type="text" name="planinwill" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  ></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的到达运量-货邮行(吨)</span></td>
                <td class="value" width="30%"><input  value="${airportMonthly.actualinwill }" type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="actualinwill"></td>
			</tr>
		<tr>
      	 <td align="right" width="16%">全年计划完成的到达运量-货邮行(吨)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"  value="${airportMonthly.planinwillyear }" name="planinwillyear"  datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的吞吐量-旅客(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input value="${airportMonthly.planthroughputtraveller }"  type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="planthroughputtraveller"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的吞吐量-旅客(万人)</span></td>
                <td class="value" width="30%"><input   type="text" value="${airportMonthly.actualthroughputtraveller }" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="actualthroughputtraveller"></td>
			</tr>
	<tr>
      	 <td align="right" width="16%">全年计划完成的吞吐量-旅客(万人)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"  value="${airportMonthly.planthroughputtravelleryear }" name="planthroughputtravelleryear"  datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  ></td>     
           </tr>
			
			<tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的吞吐量-货邮行(吨)</span></td>
				<td class="value" width="16%" colspan="2"><input  value="${airportMonthly.planthroughputwill }" type="text" name="planthroughputwill"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的吞吐量-货邮行(吨)</span></td>
                <td class="value" width="30%"><input   type="text" value="${airportMonthly.actualthroughputwill }" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="actualthroughputwill"></td>
			</tr>
			<tr>
      	 <td align="right" width="16%">全年计划完成的吞吐量-货邮行(吨)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"  value="${airportMonthly.planthroughputwillyear }" name="planthroughputwillyear"  datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  ></td>     
          </tr>
			
       	</tbody>
	</table>
</t:formvalid>
<!-- <div style="padding:5px">
	   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tijiao();">确认</a>
	  
	</div> -->
</body>
<script type="text/javascript">

</script>
