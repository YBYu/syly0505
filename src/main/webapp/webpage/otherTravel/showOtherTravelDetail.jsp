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
<t:formvalid formid="saveHotel" refresh="false" callback="back" dialog="true"  action="otherTravelController.do?save" layout="table">
	<input id="id" name="id" type="hidden" value="${otherTravel.id }">

<table style="width:660px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="16%"><span class="filedzt">企业名称</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" value="${otherTravel.name }" name="name"></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%"><input  name="type" type="text" value="<c:if test="${otherTravel.type==1 }">高尔夫项目</c:if><c:if test="${otherTravel.type==2 }">游艇项目</c:if><c:if test="${otherTravel.type==3 }">空中飞行项目</c:if><c:if test="${otherTravel.type==4 }">机场项目</c:if><c:if test="${otherTravel.type==5 }">动车项目</c:if>"> </td>
			</tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">所在市</td>
                <td class="value" width="16%" colspan="2"><input name="city"  type="text" value="${otherTravel.city }"></td>
                <td align="right" width="16%">所在区 </td>
                <td class="value" width="30%"><input   type="text" name="area" value="<c:if test="${otherTravel.area==0 }">市辖区</c:if><c:if test="${otherTravel.area==1 }">崖州区</c:if><c:if test="${otherTravel.area==2 }">海棠区</c:if><c:if test="${otherTravel.area==3 }">天涯区</c:if><c:if test="${otherTravel.area==4 }">吉阳区</c:if>"><span></span></td>
            </tr>
            <tr>
            <td align="right" width="16%">负责人<span class="filedzt"></span></td>
                <td class="value" width="30%" colspan="2"><input name="principal"  type="text"  value="${otherTravel.principal}" ></td>
				<td align="right" width="16%">固定电话<span class="filedzt">:</span></td>
				<td class="value" width="30%" ><input   type="text" name="phone"  value="${otherTravel.phone}" ></td>
                
			</tr>
			 <tr>
				<td align="right" width="15%" ><span class="filedzt">手机号码</span></td>
                <td class="value" width="30%" colspan="4"><input type="text" value="${otherTravel.mobile}" datatype="m" errormsg="手机号码不正确" name="mobile"></td>
			</tr>
            <tr>
            <td align="right" width="16%">邮政编码<span class="filedzt">：</span></td>
                <td class="value" width="16%" colspan="2"><input   type="text" name="postalcode" value="${otherTravel.postalcode}"></td>
				<td align="right" width="16%"><span class="filedzt">详细地址</span></td>
				<td class="value" width="30%" ><input    type="text" name="address" value="${otherTravel.address}"></td>
              <%--   <td align="right" width="16%"><span class="filedzt">床位数：</span></td>
                <td class="value" width="30%"><input   type="text" value="${otherTravel.bednum }"></td> --%>
			</tr>
            <!--所在地区、详细地址-->

            <%-- <tr>
				<td align="right" width="16%">手机</td>
				<td class="value" width="16%" colspan="2"><input   type="text" value="${otherTravel.mobile }"></td>
                <td align="right" width="16%"><span class="filedzt">电子邮箱：</span></td>
                <td class="value" width="30%"><input   type="text" value="${otherTravel.email }"></td>
			</tr> --%>
            <!--网址、邮政编码-->
            
            <!--单位负责人-->
<%--             <tr>
				<td align="right" width="16%"><span class="filedzt">单位地址：</span></td>
				<td class="value" width="16%" colspan="2"> <input    type="text" value="${otherTravel.hotelmanage.address }"></td>
                <td align="right" width="16%"><span class="filedzt">企业登记注册类型：</span></td>
                <td class="value" width="16%" colspan="2"><input    type="text" value="${otherTravel.hotelmanage.registerstyle }"></td> --%>
                	<!-- <select id="corRegister" name="registerstyle">
                    	<option value="default" class="opt-tit">国有</option>
                        <option value="私有">私有</option>
                    </select> -->
                </td>
			</tr>
            <!--是否经营出境游-->
            <%--  <tr>
				<td align="right" width="16%"><span class="filedzt">填表人：</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" value="${otherTravel.hotelmanage.writer }"></td>
                <td align="right" width="16%"><span class="filedzt">填表时间：</span></td>
				<td class="value" width="35%"> <input    type="text" value="${otherTravel.hotelmanage.w_time }"> </td>
			</tr> --%>
            <!--旅行社协会会员-->
           <!--  <tr>
				<td align="right" width="16%"><span class="filedzt">旅行社协会会员：</span></td>
				<td class="value" width="16%" colspan="2"><input type="radio" value="1" name="yes">是</td>
                <td align="right" width="16%"><span class="filedzt">QQ：</span></td>
				<td class="value" width="30%"><input type="text" value="" name="qq" class="qqId" errormsg="请输入QQ" /></td>
			</tr> -->         
		</tbody>
	</table>
</t:formvalid>
<div style="padding:5px">
	  <!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="check();">确认审核</a> -->
	  
	</div>
</body>
<script type="text/javascript">
/* 
	 function check(){
		var id = $("#id").val();
		$.ajax({
			url:"hotelController.do?check&id="+id,
		//data:{id:id}">,
			type:'get',
			dataType:"json",
			success:function(data){
				alert(data.msg);
				reloadTable();
			}
			 
			}
	} */
</script>
