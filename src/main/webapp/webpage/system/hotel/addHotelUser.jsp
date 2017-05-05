<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
/* var hoteltAddPage  = {
		submitForm : function (){
			if(!$('#saveHotel').form('validate')){
				$.messager.alert('提示','表单还未填写完成!');
				return ;
			}
			//contentAddEditor.sync();
			
			$.post("hotelController/save",$("#saveHotel").serialize(), function(data){
				
				alert("添加成功");
			});
		},
		
} */
/* function tijiao(){
	var list = $("table input");
	debugger
	for (var i = 0; i < list.length; i++) {
		if(list[i].value==null||list[i].value.length==0) {
			alert("亲,信息不能为空!!!!");
			return;
		}
	}
$.ajax({
	cache: true,
	type: "POST",
	url:"hotelController.do?save",
	data:$('#saveHotel').serialize(),// formid
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
<body style="" scroll="no">
	<t:formvalid formid="saveHotel" refresh="false" dialog="true"
		action="hotelController.do?addhoteluser" callback="back"
		usePlugin="password" layout="table" beforeSubmit="yanzheng()">

		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<!--许可证号-->
				<tr>
					<td align="right" width="16%">组织机构代码</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" name="organizationNum" class="regionNum"
							datatype="*" errormsg="组织机构不能为空" />
					</td>
					<td align="right" width="16%">单位名称</td>
					<td class="value" width="30%" colspan="4">
						<input type="text" name="unitname" class="accountNum" datatype="*"
							validate validType="t_hotelmanage,unitname,ID" errormsg="请输入酒店名称">
					</td>
				</tr>
				<!--法人信息-->
				<%--  <tr>
				<td align="right" width="16%"><span class="filedzt">法人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${hotelmanage.legalPerson}" name="legalPerson" class="companyname"  datatype="*" errormsg="请输入法人名称" /></td>
                <td align="right" width="16%"><span class="filedzt">邮政编码</span></td>
                <td class="value" width="30%"><input type="text" value="${hotelmanage.zipcode}" name="zipcode" ></td>
			</tr> --%>
				<!--英文名称-->
				<%-- <tr>
				<td align="right" width="16%"><span class="filedzt">电话号码</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${hotelmanage.telephone}" name="telephone"  datatype="n" errormsg="办公室电话不正确" /></td>
                <td align="right" width="16%"><span class="filedzt">手机号码</span></td>
                <td class="value" width="30%"><input type="text" value="${hotelmanage.mobile}" datatype="m" errormsg="手机号码不正确" name="mobile"></td>
			</tr> --%>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">酒店星级</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<select id="cc" name="housegrade" datatype="*" msg="请选择">
							<option value=""></option>
							<option value="8">五星</option>
							<option value="7">四星</option>
							<option value="6">三星</option>
							<option value="5">二星</option>
							<option value="4">一星</option>
							<option value="3">豪华</option>
							<option value="2">高档</option>
							<option value="1">舒适</option>
							<option value="0">其他</option>
						</select>
					</td>
					<!-- 				<td align="right" width="16%"><span class="filedzt">电子传真</span></td> -->
					<!--                 <td class="value" width="30%"><input type="text" name="fax" class="adCode" datatype="c" errormsg="请输入正确信息"></td>                -->
				</tr>
				<!--法人代表、行政区域代码-->
				<%--  <tr>
				<td align="right" width="16%"><span class="filedzt">房间数</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" datatype="n" value="${hotelmanage.housenum}" name="housenum" class="deligate" errormsg="请输入法人代表" /></td>
                <td align="right" width="16%"><span class="filedzt">床位数</span></td>
                <td class="value" width="30%"><input type="text" datatype="n" value="${hotelmanage.bednum}" name="bednum" class="adCode" errormsg="请输入行政区域代码"></td>
			</tr> --%>
				<!--所在地区、详细地址-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">所在地区</span>
					</td>
					<td class="value" width="16%" colspan="2">

						<select id="area" name="city">
							<option value="default" class="opt-tit">三亚市</option>
							<!-- <option value="海口市">海口市</option> -->
						</select>
					</td>
					<td align="right" width="16%">
						<span class="filedzt">所属区</span>
					</td>
					<td class="value" width="16%" colspan="2">

						<select id="area" name="county" datatype="*" msg="请选择">
							<%-- <option selected="selected" value="${hotelmanage.county}"><c:if  test="${hotelmanage.county ==0 }">吉阳区</c:if><c:if  test="${hotelmanage.county ==1 }">崖州区</c:if><c:if  test="${hotelmanage.county ==2 }">天涯区</c:if><c:if  test="${hotelmanage.county ==3 }">海棠区</c:if></option> --%>
							<option value=""></option>
							<option value="0">市辖区</option>
							<option value="1">崖州区</option>
							<option value="2">海棠区</option>
							<option value="3">天涯区</option>
							<option value="4">吉阳区</option>
						</select>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">所属湾区</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<select id="area" name="bay" datatype="*" msg="请选择">
							<option value=""></option>
							<option value="0">市区</option>
							<option value="1">亚龙湾</option>
							<option value="2">大东海</option>
							<option value="3">三亚湾</option>
							<option value="4">海棠湾</option>
						</select>
					</td>
					<td align="right" width="16%">
						<span class="filedzt">标牌编号(星级酒店填写)</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" value="${hotelmanage.code}" name="code" />
					</td>

				</tr>

			</tbody>
		</table>
	</t:formvalid>
	<script type="text/javascript">
		function  yanzheng(){
			var star=$("#cc").val();
			var code=$("input[name='code']").val();
			if(star=="4"||star=="5"||star=="6"||star=="7"||star=="8"){
				if(code==null||code.length==0){
					alert("星级酒店需填写标牌编号");
					return false;
				}
			}else{
				$("input[name='code']").val("");
			}
		}
	</script>
</body>