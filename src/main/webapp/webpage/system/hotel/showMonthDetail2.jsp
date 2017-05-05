<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<div style="left: 30px"></div>

	<!-- 单位是人次 type为0 -->

	<t:formvalid formid="saveHotelMonthly" refresh="false" dialog="true"
		usePlugin="password" layout="table">
		<input id="id" type="hidden" value="${id }">


		<input id="id" type="hidden" name="huserId" value="${user.id }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<!--许可证号-->
				<tr>
					<td align="right" width="16%">组织机构代码</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelMonthly.organizationNum }"
							name="organizationNum" class="regionNum" datatype="*"
							errormsg="组织机构不能为空" />
						<span class="red">*</span>
					</td>
					<td align="right" width="16%">单位名称</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelMonthly.name } " name="name"
							class="accountNum" datatype="*" errormsg="请输入酒店名称">
						</span>
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">月报时间</span>
					</td>
					<td class="value" width="30%" colspan="4">
						<input type="text" value="${hotelMonthly.month }" name="month"
							errormsg="请输入月份">
					</td>
				</tr>
				<!--法人信息-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">国内(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelMonthly.domestic } "
							name="domestic" class="companyname" errormsg="请输入单位名称" />
						<span class="red">*</span>
					</td>
					<td align="right" width="16%">
						<span class="filedzt">入境(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelMonthly.entry }" name="entry">
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">外国人(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelMonthly.foreign } "
							name="foreign" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">香港(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelMonthly.hongkong } "
							name="hongkong">
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">澳门(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelMonthly.macao }" name="macao"
							class="creditCode" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">台湾(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelMonthly.taiwan }" name="taiwan" />
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">总数(人次)</span>
					</td>
					<td class="value" width="30%" colspan="4">
						<input type="text" value="${hotelMonthly.sum }" name="sum">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">营业收入(万元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelMonthly.operationIncome }"
							name="operationIncome" class="creditCode" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">客房收入(万元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelMonthly.roomIncome }"
							name="roomIncome" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">餐饮收入(万元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelMonthly.canteeIncome }"
							name="canteeIncome" class="creditCode" datatype="*"
							errormsg="请输入餐饮收入" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">其他收入(万元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelMonthly.otherIncome }"
							name="otherIncome" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">填表人</span>
					</td>
					<td class="value" width="16%" colspan="2">${hotelMonthly.hotelmanage.writer }</td>
					<td align="right" width="16%">
						<span class="filedzt">填表时间</span>
					</td>
					<td class="value" width="35%">${hotelMonthly.hotelmanage.w_time }
					</td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
	<!-- <div style="padding:5px">
	 <a href="javascript:void(0)"   style="margin-left: 615px"  class="easyui-linkbutton" onclick="tijiao();">保存</a>
</div -->
	>
	<!-- 单位是人天  type为1-->

</body>