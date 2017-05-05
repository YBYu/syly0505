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
	} */

</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="saveot" refresh="false" dialog="true" callback="reloadTable" action="otherTravelController.do?addOtUser" layout="table">
	<!-- <input id="id" type="hidden"  name="id "> -->

<table style="width:660px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="16%"><span class="filedzt">企业名称</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" datatype="*" validType="t_othercompany_info,name"  name="name"></td>
                <td align="right" width="16%">企业类型</td>
                <td class="value" width="30%">
          <select id="cc" class="easyui-combobox"   name="type" style="width:145px;">   
			    <option selected="selected" value="1">高尔夫项目</option>   
			    <option value="2">游艇项目</option>   
			    <option value="3">空中飞行项目</option>   
			    <option value="4">机场项目</option>   
			    <option value="5">动车项目</option>
			    <!-- <option value="5">五星</option> -->   
			      </select>
                 </td>
			</tr>
        	<!--许可证号-->
        	<tr>
            	<td align="right" width="16%">城市</td>
                <td class="value" width="16%" colspan="2"><input  disabled="disabled" value="三亚市" type="text" datatype="*"  name="city"></td>
                <td align="right" width="16%">地区 </td>
                <td class="value" width="30%">
                <select id="cc" class="easyui-combobox"  name="area" style="width:145px;">   
			    <option selected="selected" value="0">市辖区</option>   
			    <option value="1">崖州区</option>   
			    <option value="2">海棠区</option>   
			    <option value="3">天涯区</option>   
			    <option value="4">吉阳区</option>
			      
			      </select>
                </td>
            </tr>
            <tr>
            <td align="right" width="16%">负责人<span class="filedzt"></span></td>
                <td class="value" width="30%" colspan="2"><input   type="text" datatype="*"  name="principal"></td>
				<td align="right" width="16%">联系电话<span class="filedzt">:</span></td>
				<td class="value" width="30%" ><input   type="text" datatype="c"  name="phone"></td>
                
			</tr>
			<tr>
				<td align="right" width="16%"><span class="filedzt">详细地址</span></td>
				<td class="value" width="16%" colspan="2"><input    type="text" datatype="*"  name="address"></td>
				<td align="right" width="16%">邮政编码<span class="filedzt"></span></td>
                <td class="value" width="30%"><input   type="text" datatype="p"  name="postalcode"></td>
              </tr>
             <tr>
				<td align="right" width="16%"><span class="filedzt">手机号码</span></td>
				<td class="value" width="16%" colspan="4"><input type="text" datatype="m"  name="mobile"></td>
              </tr>       
              
		</tbody>
	</table>
</t:formvalid>
<!-- <div style="padding:5px">
	 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tijiao();">确认</a> 
	  
	</div> -->
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
