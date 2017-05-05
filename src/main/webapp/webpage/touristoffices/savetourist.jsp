<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">


<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<!-- <script type="text/javascript" src="webpage/js/calendar/WdatePicker.js"></script> -->
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="" scroll="">
	<t:formvalid formid="formobj" refresh="true" dialog="false"
		callback="resultmsg" action="touristOfficesController.do?saveOrUpdate"
		usePlugin="password" layout="table">
		<input name="id" type="hidden" value="${touristofficesid}">

		<table style="width: 650px; font-size: 12px;" cellpadding="0"
			cellspacing="1" class="formtable">

			<c:if test="${touristofficesdata.status==1}">
				<div class="MainInfo_tit pdtb10">资料已同步至国家旅游局星级酒店系统</div>
			</c:if>
			<c:if test="${touristofficesdata.status==0}">
				<%--  	<div class="MainInfo_tit pdtb10">
                         资料未同步至国家旅游局星级酒店系统
        </div>   --%>
			</c:if>
			<tbody>
				<tr>
					<td align="right" width="16%">账号</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${touristofficesdata.account}"
							name="account" datatype="*" class="account" errormsg="请输入账号" />
						<span class="red"></span>
					</td>
					<td align="right" width="16%">密码</td>
					<td class="value" width="30%">
						<input type="password" value="${touristofficesdata.password}"
							name="password" datatype="*" errormsg="请输入密码" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">地区</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${touristofficesdata.area}" name="area"
							datatype="*" errormsg="请输入地区" />
						<span class="red"></span>
					</td>
					<td align="right" width="16%">城市</td>
					<td class="value" width="30%">
						<input type="text" value="${touristofficesdata.city}" name="city"
							class="accountNum" datatype="*" errormsg="请输入城市" />
					</td>
				</tr>

				<tr>
					<td align="right" width="16%">名称</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${touristofficesdata.name}" name="name"
							datatype="*" errormsg="请输入名称" />
						<span class="red"></span>
					</td>
					<td align="right" width="16%">负责人</td>
					<td class="value" width="30%">
						<input type="text" value="${touristofficesdata.head}" name="head"
							datatype="*" class="accountNum" errormsg="请输入负责人" />
					</td>
				</tr>
				<!--单位名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">联系电话</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${touristofficesdata.tel}" name="tel"
							datatype="*" class="companyname" errormsg="请输入联系电话" />
						<span class="red"></span>
					</td>
					<td align="right" width="16%">
						<span class="filedzt">QQ</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${touristofficesdata.qq}" name="qq"
							datatype="qq" class="qq" errormsg="请输入QQ号" />
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">邮编</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${touristofficesdata.postcode}"
							name="postcode" datatype="*" class="englishname"
							errormsg="请输入邮政编码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">传真</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${touristofficesdata.fax}" name="fax"
							datatype="*" class="ogCode" errormsg="请输入传真" />
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">地址</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${touristofficesdata.add}" name="add"
							datatype="*" class="creditCode" errormsg="请输入地址" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">手机</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${touristofficesdata.phone}"
							name="phone" datatype="m" class="ogCode" errormsg="请输入手机号" />
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">网址</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${touristofficesdata.http}" name="http"
							datatype="url" class="deligate" errormsg="请输入网址" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">邮箱</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${touristofficesdata.email}"
							name="email" datatype="e" class="adCode" errormsg="请输入邮箱" />
					</td>
				</tr>
				<!--所在地区、详细地址-->

				<!--固定联系方式-->

				<!--
            <tr>
				<td align="right" width="16%"><span class="filedzt">报出日期</span></td>
				<td class="value" width="16%" colspan="2"><input  type="text"  name="reportdate" class="Wdate"  onClick="WdatePicker()"/></td>
                <td align="right" width="16%"></td>
                <td class="value" width="30%"></td>
			</tr>
           -->



			</tbody>
		</table>
		<table class="input">
			<tbody>
				<tr>
					<th>&nbsp;</th>
					<td>
						<input type="submit" class="button" value="提&nbsp;&nbsp;交">
						<%--
					<input type="button" class="button" value="同&nbsp;&nbsp;步" onclick="statuschange();">
		 	          --%>

					</td>
				</tr>
			</tbody>
		</table>

	</t:formvalid>
</body>
<script type="text/javascript">


function statuschange(){
		var formjson = $('#formobj', iframe).serialize();
			$.ajax ({
			url:"touristOfficesController.do?saveStatus",
			data:formjson,

			//data:{id:id},
			 type : "GET",
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
			}	
			 }
			);
		}
		
function resultmsg(data){
   alert(data.msg);
  

}		  

</script>