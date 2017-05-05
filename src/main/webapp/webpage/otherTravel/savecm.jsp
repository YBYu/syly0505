<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
/* function tijiao(){
	$.ajax({
		cache: true,
		type: "POST",
		url:"motorcarMonthlyController.do?savecm",
		data:$('#saveot').serialize(),// formid
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(data) {
			alert("信息补全成功");
		}
	});
	} */

</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="saveot" refresh="false" action="motorcarMonthlyController.do?savecm" callback="back" dialog="true"  layout="table">
	<input  type="hidden" value="${otherTravelInfo.id }" name="otid">

<table style=" font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="16%"><span class="filedzt">企业名称</span></td>
				<td class="value" width="16%" colspan="2"><input value="${otherTravelInfo.name }"  type="text" datatype="*"  ></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%"><input value="<c:if test="${otherTravelInfo.type ==1 }">高尔夫项目</c:if><c:if test="${otherTravelInfo.type ==2 }">游艇项目</c:if><c:if test="${otherTravelInfo.type ==3 }">空中飞行项目</c:if><c:if test="${otherTravelInfo.type ==4 }">机场项目</c:if><c:if test="${otherTravelInfo.type ==5 }">动车项目</c:if>"  type="text" datatype="*""></td>	         
			</tr>
			<tr>
            	<td align="right" width="16%">月报时间</td>
                <td class="value" width="16%" colspan="4"><input value="${lastMonth }" class="easyui-datebox"  type="text"  name="dates" errormsg="请输入月份" ></td>
                <%-- <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <input  value="${otherTravelInfo.city }" type="area" datatype="*"">
                </td> --%>
            </tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">所在市</td>
                <td class="value" width="16%" colspan="2"><input  value="${otherTravelInfo.city }" type="text" datatype="*""></td>
                <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <input  value="<c:if test="${otherTravelInfo.area ==0 }">市辖区</c:if><c:if test="${otherTravelInfo.area ==1 }">崖州区</c:if><c:if test="${otherTravelInfo.area ==2 }">海棠区</c:if><c:if test="${otherTravelInfo.area ==3 }">天涯区</c:if><c:if test="${otherTravelInfo.area ==4 }">吉阳区</c:if>" type="text"  datatype="*">
                </td>
            </tr>
            <!--法人信息-->
            <tr>
				<td align="right" width="16%">联系电话<span class="filedzt"></span></td>
				<td class="value" width="16%" colspan="2"><input value="${otherTravelInfo.phone }"  type="text"  ></td>
                <td align="right" width="16%">邮政编码<span class="filedzt"></span></td>
                <td class="value" width="30%"><input  value="${otherTravelInfo.postalcode }" type="text" ></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">进站人数(万人次)</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="innum"></td>
                <td align="right" width="16%"><span class="filedzt">出站人数(万人次)</span></td>
                <td class="value" width="30%"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="outnum"></td>
			</tr>
            <!--统一社会信用代码-->
         <tr>
				<td align="right" width="16%"><span class="filedzt">吞吐量(万人次)</span></td>
				<td class="value" width="16%" colspan="4"><input   type="text" datatype="decimals4"   errormsg="请输入1-9位数字(最多四位小数)"  name="throughput"></td>
               
			</tr>
		   <tr>
            	<td align="right" width="16%"><span class="filedzt">填报人</span></td>
 				<td class="value" width="16%" colspan="4"><input datatype="*"   type="text"  name="reporter"></td>
               <!-- <td align="right" width="16%" ><span class="filedzt">填报时间</span></td>
  			   <td class="value" width="30%" ><input class="easyui-datebox"  type="text" datatype="*" name="writeDate"></td> -->
            </tr>	
           

           

              
            <!--是否经营出境游-->
           
                  
		</tbody>
	</table>
</t:formvalid>
<!-- <div style="padding:5px">
	  <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tijiao();">确认</a> 
	  
	</div> -->
</body>
<script type="text/javascript">
 
</script>
