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
<t:formvalid formid="saveot"   action="boatMonthlyController.do?savbmdais" callback="reloadTable" dialog="true"  layout="table">
	
<table style=" font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<c:if test="${empty listss}">
			<h3 style="color:#ff9b0b ; text-align: center;font-size:18px ">企业月报已经填报完成</h3>
			<script>
				$(document).ready(function(){
					window.parent.$(".ui_state_highlight")[0].style.display="none";
					
				});
			</script>
			</c:if>
		<c:forEach var="item"  items="${listss}" varStatus="status">
			<tr>
			<input  value="${item.id }" name="otid${status.count }" type="hidden">
			<%-- <input name="id" value="${status.count }" name="otid" type="text"> --%>
				<td align="right" width="16%">企业名称  </td>
                <td class="value" width="16%" colspan="2"><input disabled="disabled" value="${item.name }" type="text"   >
                <td align="right" width="16%">企业类型</td>
              
                <td class="value" width="30%"><input disabled="disabled" type="text" value="<c:if test="${item.type==1 }">高尔夫项目</c:if><c:if test="${item.type==2 }">游艇项目</c:if><c:if test="${item.type==3 }">空中飞行项目</c:if><c:if test="${item.type==4 }">机场项目</c:if><c:if test="${item.type==5 }">动车项目</c:if>"></td>	         
			</tr>
			<tr>
				<td align="right" width="16%"><span class="filedzt">接待人次（单位人次）</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="n1-9" errormsg="只能为1-9位整数"name="recepNum${status.count }"></td>
                <td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
                <td class="value" width="30%"><input   type="text" datatype="/^\d{1,9}(.\d{1,2})?$/"   errormsg="请输入1-9位数字(最多两位小数)"  name="income${status.count }"></td>
			</tr>
			<tr>
            	<td align="right" width="16%"><span class="filedzt">填报人</span></td>
 				<td class="value" width="16%" colspan="4"><input datatype="*"  type="text"  name="reporter${status.count }"></td>
            </tr>
		</c:forEach>

       	</tbody>
	</table>
</t:formvalid>
<!-- <div style="padding:5px">
	   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tijiao();">确认</a>
	  
	</div> -->
</body>
<script type="text/javascript">

</script>
