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
<t:formvalid formid="saveot" refresh="true" dialog="true" callback="reloadTable" action="otherTravelMonthlyController.do?savegm" layout="table">
<input   type="hidden" value="${golfMonthly.id }" name="id"> 
<table style="font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="16%"><span class="filedzt">企业名称</span></td>
				<td class="value" width="16%" colspan="2"><input disabled="disabled" value="${golfMonthly.otherTravelInfo.name }"  type="text" datatype="*"  ></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%"><input disabled="disabled" type="text" value="<c:if test="${golfMonthly.otherTravelInfo.type==1 }">高尔夫项目</c:if><c:if test="${golfMonthly.otherTravelInfo.type==2 }">游艇项目</c:if><c:if test="${golfMonthly.otherTravelInfo.type==3 }">空中飞行项目</c:if><c:if test="${golfMonthly.otherTravelInfo.type==4 }">机场项目</c:if><c:if test="${golfMonthly.otherTravelInfo.type==5 }" >动车项目</c:if>">
	 <input   type="hidden" value="${otid }" name="otid"> 
                </td>	         
			</tr>
			<tr>
            	<td align="right" width="16%">月报时间</td>
                <td class="value" width="16%" colspan="4">
                <input  value="${golfMonthly.smonth}" type="text"  datatype="*"     disabled="disabled" ></td>
                <%-- <input  value="<fmt:formatDate value="${golfMonthly.dates }" type="date"/>" type="text"    errormsg="请输入月份" datatype="*" ></td> --%>
                
            </tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">所在市</td>
                <td class="value" width="16%" colspan="4"><input  value="${golfMonthly.otherTravelInfo.city }" type="text" datatype="*" ></td>
                
            </tr>
            <!--法人信息-->
            <tr>
				<td align="right" width="16%">联系电话<span class="filedzt"></span></td>
				<td class="value" width="16%" colspan="2"><input value="${golfMonthly.otherTravelInfo.phone}" type="text"  readonly="readonly"></td>
                <td align="right" width="16%">邮政编码<span class="filedzt"></span></td>
                <td class="value" width="30%"><input value="${golfMonthly.otherTravelInfo.postalcode}"  type="text" readonly="readonly"></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人次（单位：人次）</span></td>
				<td class="value" width="16%" colspan="2"><input value="${golfMonthly.receptionnum}" type="text"datatype="n1-9" errormsg="只能为1-9位整数"name="receptionnum"></td>
                <td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
                <td class="value" width="30%"><input value="${golfMonthly.businessincome}" type="text" datatype="num" name="businessincome"></td>
			</tr>
            <tr>
            	<td align="right" width="16%"><span class="filedzt">填报人</span></td>
 				<td class="value" width="16%" colspan="4"><input  value="${golfMonthly.reporter}" datatype="*" type="text"  name="reporter"></td>
               <!-- <td align="right" width="16%" ><span class="filedzt">填报时间</span></td>
  			   <td class="value" width="30%" ><input class="easyui-datebox"  type="text" datatype="*" name="writeDate"></td> -->
            </tr>
		</tbody>
	</table>
</t:formvalid>
</body>
<script type="text/javascript">
</script>
