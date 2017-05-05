<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>

</head>
<body style="" scroll="yes" >
<t:formvalid formid="saveot" refresh="false" action="airportMonthlyController.do?saveam" callback="back" dialog="true"  layout="table">
	<input type="hidden" value="${otherTravelInfo.id} " name="otid">

<table style=" font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="16%"><span class="filedzt">企业名称</span></td>
				<td class="value" width="16%" colspan="2"><input disabled="disabled" value="${otherTravelInfo.name }"  type="text" datatype="*"  ></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%"><input disabled="disabled" value="<c:if test="${otherTravelInfo.type ==1 }">高尔夫项目</c:if><c:if test="${otherTravelInfo.type ==2 }">游艇项目</c:if><c:if test="${otherTravelInfo.type ==3 }">空中飞行项目</c:if><c:if test="${otherTravelInfo.type ==4 }">机场项目</c:if><c:if test="${otherTravelInfo.type ==5 }">动车项目</c:if>"  type="text" datatype="*" ></td>	         
			</tr>
			<tr>
		
			
            	<td align="right" width="16%">月报时间</td>
                <td class="value" width="16%" colspan="4"><input  value="${lastMonth }" class="easyui-datebox"  type="text"  name="dates"   errormsg="请输入月份"  ></td>
                
            </tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">所在市</td>
                <td class="value" width="16%" colspan="2"><input disabled="disabled" value="${otherTravelInfo.city }" type="text"  datatype="*" ></td>
                <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <input disabled="disabled" value="<c:if test="${otherTravelInfo.area ==0 }">市辖区</c:if><c:if test="${otherTravelInfo.area ==1 }">崖州区</c:if><c:if test="${otherTravelInfo.area ==2 }">海棠区</c:if><c:if test="${otherTravelInfo.area ==3 }">天涯区</c:if><c:if test="${otherTravelInfo.area ==4 }">吉阳区</c:if>" type="text" datatype="*">
                </td>
            </tr>
            <!--法人信息-->
            <tr>
				<td align="right" width="16%">联系电话</td>
				<td class="value" width="16%" colspan="2"><input  value="${otherTravelInfo.phone }"  type="text"   name="phone " readonly="readonly"></td>
                <td align="right" width="16%">邮政编码<span class="filedzt"></span></td>
                <td class="value" width="30%"><input    type="text"   value="${otherTravelInfo.postalcode }" readonly="readonly" ></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划运输起降(单位降次)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="plansortie"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际运输起降(单位降次)</span></td>
                <td class="value" width="30%"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="actualsortie"></td>
			</tr>
			<tr>
      	 <td align="right" width="16%">全年计划完成的运输起(单位降次)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"   name="plansortieyear"  datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)" ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划出发运量-旅客(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="planouttraveller"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际出发运量-旅客(万人)</span></td>
                <td class="value" width="30%"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="actualouttraveller"></td>
			</tr>
		<tr>
      	 <td align="right" width="16%">全年计划完成的到达运量-旅客(万人)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"   name="planouttravelleryear"  datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)" ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的出发运量-货邮行(吨)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="planoutwill"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的出发运量-货邮行(吨)</span></td>
                <td class="value" width="30%"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="actualoutwill"></td>
			</tr>
			<tr>
      	 <td align="right" width="16%">全年计划完成的出发货运-货邮行(吨)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"   name="planoutwillyear"  datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)" ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的到达运量-旅客(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="planIntegerraveller"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的到达运量-旅客(万人)</span></td>
                <td class="value" width="30%"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="actualIntegerraveller"></td>
			</tr>
		<tr>
      	 <td align="right" width="16%">全年计划完成的到达运量-旅客(万人)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"   name="planIntegerravelleryear"  datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的到达运量-货邮行(吨)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="planinwill"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的到达运量-货邮行(吨)</span></td>
                <td class="value" width="30%"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="actualinwill"></td>
			</tr>
		<tr>
      	 <td align="right" width="16%">全年计划完成的到达运量-货邮行(吨)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"   name="planinwillyear"  datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  ></td>
               
           </tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的吞吐量-旅客(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="planthroughputtraveller"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的吞吐量-旅客(万人)</span></td>
                <td class="value" width="30%"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="actualthroughputtraveller"></td>
			</tr>
	<tr>
      	 <td align="right" width="16%">全年计划完成的吞吐量-旅客(万人)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"   name="planthroughputtravelleryear"  datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  ></td>     
           </tr>
			
			<tr>
				<td align="right" width="16%"><span class="filedzt">本月计划完成的吞吐量-货邮行(吨)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="planthroughputwill"></td>
                <td align="right" width="16%"><span class="filedzt">本月实际完成的吞吐量-货邮行(吨)</span></td>
                <td class="value" width="30%"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="actualthroughputwill"></td>
			</tr>
			<tr>
      	 <td align="right" width="16%">全年计划完成的吞吐量-货邮行(吨)</td>
         <td class="value" width="16%" colspan="4"><input  type="text"   name="planthroughputwillyear"  datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  ></td>     
           </tr>
			<tr>
				<td align="right" width="16%"><span class="filedzt">填报人</span></td>
				<td class="value" width="16%" colspan="4"><input   type="text" datatype="*" name="reporter"></td>
                <!-- <td align="right" width="16%"><span class="filedzt">填报时间</span></td>
                <td class="value" width="30%"><input class="easyui-datebox"  type="text" datatype="*" name="reportdate"></td> -->
			</tr>
       	</tbody>
	</table>
</t:formvalid>
<!-- <div style="padding:5px">
	   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tijiao();">确认</a>
	  
	</div> -->
</body>
<script type="text/javascript">

function today(){//构建方法
	
    var today=new Date();//new 出当前时间
    var h=today.getFullYear();//获取年
    var m=today.getMonth();//获取月
    var d=today.getDate();//获取日
    var H = today.getHours();//获取时        
    var M = today.getMinutes();//获取分
    var S = today.getSeconds();//获取秒
    return h+"-"+m+"-"+d; //返回 年-月-日 时:分:秒
}
document.getElementById("today").value = today();//将获取到的 年-月-日 时:分:秒 赋给input文本输入框
</script>
