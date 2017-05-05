<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>

</head>
<body style="" scroll="yes">
	<div style="left: 30px"></div>

	<!-- 单位是人次 type为0 -->

	<t:formvalid formid="saveHotelEstimate" refresh="false" dialog="true"
		usePlugin="password" layout="table">
		<input id="id" type="hidden" value="${hotelEstimate.id }">

		<%-- <input id="id" type="hidden" name="huserId" value="${hotelmanage.id }"> --%>
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<!--许可证号-->

				<tr>
					<td align="right" width="16%">
						<span class="filedzt">年份</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.year}" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">月份</span>
					</td>
					<td class="value" width="30%" colspan="2">
						<input type="text" value="${hotelEstimate.month}">
					</td>
				</tr>

				<!--法人信息-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">地区编号</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" value="${hotelEstimate.countryCode}" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">国内(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.domestic}" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">入境(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.entry}">
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">外国人(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.foreignTimes}" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">香港(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.hongkong}">
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">澳门(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.macao}" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">台湾(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.taiwan}" />
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">总数(人次)</span>
					</td>
					<td class="value" width="30%" colspan="4">
						<input type="text" value="${hotelEstimate.sum}">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">国内(人天)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.domesticD}" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">入境(人天)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.entryD}">
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">外国人(人天)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.foreignDays}" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">香港(人天)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.hongkongD}">
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">澳门(人天)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.macaoD}" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">台湾(人天)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.taiwanD}" />
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">总数(人天)</span>
					</td>
					<td class="value" width="30%" colspan="4">
						<input type="text" value="${hotelEstimate.sumD}">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">填写人</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.bymanager}" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">填写日期</span>
					</td>
					<td class="value" width="30%">
						<input type="text" class="easyui-datebox"
							value="${hotelEstimate.createTime}" />
					</td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>

	<!-- 单位是人天  type为1-->
	<script>

</script>

</body>