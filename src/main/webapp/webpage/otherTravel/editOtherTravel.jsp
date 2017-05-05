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
<t:formvalid formid="saveHotel"  dialog="true" callback="back" action="otherTravelController.do?edits" layout="table">
	<input id="id" name="id" type="hidden" value="${otherTravel.id }">

<table style=" font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="16%"><span class="filedzt">企业名称：</span></td>
				<td class="value" width="16%" colspan="2"><input datatype="*"   type="text" value="${otherTravel.name }" name="name" disabled="disabled"></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%">
                <input     disabled="disabled"  type="text" name="type" value="<c:if test="${otherTravel.type==1 }">高尔夫项目</c:if><c:if test="${otherTravel.type==2 }">游艇项目</c:if><c:if test="${otherTravel.type==3 }">空中飞行项目</c:if><c:if test="${otherTravel.type==4 }">机场项目</c:if><c:if test="${otherTravel.type==5 }">动车项目</c:if>"   >  
			    
                <%-- <input  name="type" type="text" value="<c:if test="${otherTravel.type==1 }">高尔夫项目</c:if><c:if test="${otherTravel.type==2 }">游艇项目</c:if><c:if test="${otherTravel.type==3 }">空中飞行项目</c:if><c:if test="${otherTravel.type==4 }">机场项目</c:if><c:if test="${otherTravel.type==5 }">动车项目</c:if>"> </td> --%>
			</tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">所在市</td>
                <td class="value" width="16%" colspan="2"><input disabled="disabled" datatype="*" name="city"  type="text" value="三亚市"></td>
                <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <select id="cc" class="easyui-combobox"  name="area" style="width:145px;">   
			   <option value="0" <c:if test="${'0' eq otherTravel.area}">selected</c:if>>市辖区</option>
							<option value="1" <c:if test="${'1' eq otherTravel.area}">selected</c:if>>崖州区</option>
							<option value="2"  <c:if test="${'2' eq otherTravel.area}">selected</c:if>>海棠区</option>
							<option value="3" <c:if test="${'3' eq otherTravel.area}">selected</c:if>>天涯区</option>
							<option value="4" <c:if test="${'4' eq otherTravel.area}">selected</c:if>>吉阳区</option>	 </select>
                <%-- <input datatype="*"  type="text" name="area" value="<c:if test="${otherTravel.area==0 }">市辖区</c:if><c:if test="${otherTravel.area==1 }">崖州区</c:if><c:if test="${otherTravel.area==2 }">海棠区</c:if><c:if test="${otherTravel.area==3 }">天涯区</c:if><c:if test="${otherTravel.area==4 }">吉阳区</c:if>"><span></span></td> --%>
            </tr>
            <tr>
            <td align="right" width="16%">负责人<span class="filedzt"></span></td>
                <td class="value" width="30%" colspan="2"><input name="principal"  type="text"  value="${otherTravel.principal}" ></td>
				<td align="right" width="16%">固定电话<span class="filedzt"></span></td>
				<td class="value" width="30%" ><input  datatype="c"  type="text" name="phone"  value="${otherTravel.phone}" ></td>               
			</tr>
            <tr>
            <td align="right" width="16%">邮政编码<span class="filedzt">：</span></td>
                <td class="value" width="16%" colspan="2"><input datatype="*"  type="text" name="postalcode" value="${otherTravel.postalcode}"></td>
				<td align="right" width="16%"><span class="filedzt">详细地址</span></td>
				<td class="value" width="30%" ><input  datatype="*"  type="text" name="address" value="${otherTravel.address}"></td>
              <%--   <td align="right" width="16%"><span class="filedzt">床位数：</span></td>
                <td class="value" width="30%"><input   type="text" value="${otherTravel.bednum }"></td> --%>
			</tr>
            <!--所在地区、详细地址-->           
		</tbody>
	</table>
</t:formvalid>

</body>
<script type="text/javascript">

</script>
