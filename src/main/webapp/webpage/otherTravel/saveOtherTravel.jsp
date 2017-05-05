<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
function tijiao(){
	$.ajax({
		cache: true,
		type: "POST",
		url:"otherTravelController.do?save",
		data:$('#saveot').serialize(),// formid
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(data) {
			alert("信息补全成功");
		}
	});
	}

</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="saveot" refresh="false" dialog="true" action="otherTravelController.do?save" layout="table">
	<input  type="hidden" value="${otherTravelInfo.id}" name="id ">

<table style="width:660px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="16%"><span class="filedzt">企业名称</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="*" value="${otherTravelInfo.name}" name="name"></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%">
                 <select id="cc" class="easyui-combobox"  name="type" style="width:145px;">   
			    <option value="1" <c:if test="${'1' eq otherTravelInfo.type}">selected</c:if>>高尔夫项目</option>
							<option value="2" <c:if test="${'2' eq otherTravelInfo.type}">selected</c:if>>游艇项目</option>
							<option value="3"  <c:if test="${'3' eq otherTravelInfo.type}">selected</c:if>>空中飞行项目</option>
							<option value="4" <c:if test="${'4' eq otherTravelInfo.type}">selected</c:if>>机场项目</option>
							<option value="5" <c:if test="${'5' eq otherTravelInfo.type}">selected</c:if>>动车项目</option>	  
			      </select>
                <%-- <input   type="text" datatype="*" name="type" disabled="disabled" value="<c:if test="${otherTravelInfo.type==1 }">高尔夫项目 </c:if><c:if test="${otherTravelInfo.type==1 }">高尔夫项目 </c:if><c:if test="${otherTravelInfo.type==2 }">游艇项目</c:if><c:if test="${otherTravelInfo.type==3 }">空中飞行项目 </c:if><c:if test="${otherTravelInfo.type==4 }">机场项目 </c:if><c:if test="${otherTravelInfo.type==5 }">动车项目 </c:if>" ></td> --%>
          <%-- <select id="cc" class="easyui-combobox"   name="type" style="width:145px;">   
			    <option selected="<c:if test="${otherTravel.type==1 }">selected</c:if>"  value="1">高尔夫项目</option>   
			    <option selected="<c:if test="${otherTravel.type==2 }">selected</c:if>" value="2" >游艇项目</option>   
			    <option selected="<c:if test="${otherTravel.type==3 }">selected</c:if>"  value="3">空中飞行项目</option>   
			    <option selected="<c:if test="${otherTravel.type==4 }">selected</c:if>"  value="4">机场项目</option>   
			    <option selected="<c:if test="${otherTravel.type==5 }">selected</c:if>" value="5">动车项目</option>
			    <!-- <option value="5">五星</option> -->   
			      </select> --%>
                 
			</tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">所在市</td>
                <td class="value" width="16%" colspan="2"><input  value="${otherTravelInfo.city}"  type="text" datatype="*"  name="city"></td>
                <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%">
                <%-- <input  value="<c:if test="${otherTravelInfo.area ==0 }">市辖区</c:if><c:if test="${otherTravelInfo.area ==1 }">崖州区</c:if><c:if test="${otherTravelInfo.area ==2 }">海棠区</c:if><c:if test="${otherTravelInfo.area ==3 }">天涯区</c:if><c:if test="${otherTravelInfo.area ==4 }">吉阳区</c:if>" type="text" disabled="disabled" datatype="*"> --%>
                 <select id="cc" class="easyui-combobox"  name="area" style="width:145px;">   
			    <option value="0" <c:if test="${'0' eq otherTravelInfo.area}">selected</c:if>>市辖区</option>
							<option value="1" <c:if test="${'1' eq otherTravelInfo.area}">selected</c:if>>崖州区</option>
							<option value="2"  <c:if test="${'2' eq otherTravelInfo.area}">selected</c:if>>海棠区</option>
							<option value="3" <c:if test="${'3' eq otherTravelInfo.area}">selected</c:if>>天涯区</option>
							<option value="4" <c:if test="${'4' eq otherTravelInfo.area}">selected</c:if>>吉阳区</option>	  
			      </select> 
                </td>
            </tr>
            <!--法人信息-->
            <tr>
            <td align="right" width="16%">负责人<span class="filedzt"></span></td>
                <td class="value" width="30%" colspan="2"><input   type="text" datatype="*" value="${otherTravelInfo.principal}" name="principal"></td>
				<td align="right" width="16%">固定电话<span class="filedzt">:</span></td>
				<td class="value" width="30%" ><input   type="text" datatype="c" value="${otherTravelInfo.phone}" name="phone"></td>
                
			</tr>
		
              <tr>
				<td align="right" width="15%" ><span class="filedzt">手机号码</span></td>
                <td class="value" width="30%" colspan="4"><input type="text" value="${otherTravelInfo.mobile}" datatype="m" errormsg="手机号码不正确" name="mobile"></td>
			</tr>	
      
        
            <tr>
				<td align="right" width="16%"><span class="filedzt">详细地址</span></td>
				<td class="value" width="16%" colspan="2"><input    type="text" datatype="*" value="${otherTravelInfo.address}" name="address"></td>
				<td align="right" width="16%">邮政编码<span class="filedzt"></span></td>
                <td class="value" width="30%"><input   type="text" datatype="p" value="${otherTravelInfo.postalcode}" name="postalcode"></td>
              </tr>
                   
              
		</tbody>
	</table>
</t:formvalid>
<div style="padding:5px">
	 <a href="javascript:void(0)"  class="easyui-linkbutton" onclick="tijiao();">确认</a> 
	  
	</div>
</body>
<script type="text/javascript">
/* 
	 function check(){
		var id = $("#id").val();
		$.ajax({
			url:"hotelController.do?check&id="+id,
		//data:{id:id">,
			type:'get',
			dataType:"json",
			success:function(data){
				alert(data.msg);
				reloadTable();
			}
			 
			}
	} */
</script>
