<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>节假日统计</title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">

function save(){
$.ajax({
	cache: true,
	type: "POST",
	url:"holidayController.do?save",
	data:$('#saveHoliday').serialize(),// formid
	async: false,
	error: function(request) {
		alert("Connection error");
	},
	success: function(data) {
		alert(data);
	}
});
}
</script>
</head>
<body style="overflow-y: hidden" scroll="no">
	<div>
		<h3 style="color: blue">节假日信息录入</h3>
	</div>
	<t:formvalid formid="saveHoliday" refresh="false" dialog="true"
		action="hotelController.do?save" usePlugin="password" layout="table">
		<%-- <input id="id" type="hidden" value="${user.id }"> --%>


		<table style="width: 950px; font-size: 12px;" cellpadding="0"
			cellspacing="1" class="formtable">
			<tbody>
				<tr>
					<td align="right" width="10%">
						<span class="filedzt">填表年份：</span>
					</td>
					<td class="value" width="10%" colspan="2">
						<span>
							<input type="text" value="" name="year">
					</td>
					<td align="right" width="40%">
						<span class="filedzt">假日类型0元旦、1清明、2五一、3端午、4中秋，5春节黄金周、6十一黄金周）：</span>
					</td>
					<td class="value" width="35%">
						<input type="text" value="" name="type">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">开始时间：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="date" value="" name="startTime" class="mobile" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">结束时间：</span>
					</td>
					<td class="value" width="30%">
						<input type="date" value="" name="endTime" class="endTime">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">美兰机场（万）：</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="0" name="meilanAir" class="meilanAir"
							readOnly="readOnly" />
						<span class="red">*</span>
					</td>
					<td align="right" width="16%">凤凰机场（万） ：</td>
					<td class="value" width="30%">
						<input type="text" value="" name="fenghuangAir"
							class="fenghuangAir">
					</td>
				</tr>
				<!--法人信息-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">博鳌机场（万）:</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="0" name="boaoAir" class="boaoAir"
							readOnly="readOnly" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">海峡办（万人次）：</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="0" name="straitOffice"
							readOnly="readOnly">
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">景区（万人次）：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="" name="scenicNum" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">景区收入（万元）”：</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="" name="scenicIncome">
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">酒店（万人次）：</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" value="" name="hotelNum" class="hotelNum" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">酒店收入（万元）：</span>
					</td>
					<td class="value" width="30%" colspan="2">
						<input type="text" value="" name="hotelIncome" class="hotelIncome" />
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">乡村旅游（万人次：）</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="" name="townTravelNum"
							class="townTravelNum">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">乡村旅游收入（万元）：</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="" name="townTravelIncome"
							class="townTravelIncome">
					</td>
				</tr>
				<!--所在地区、详细地址-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">免税店（万人次））：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="" name="taxFreeNum" class="taxFreeNum" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">免税店收入（万元）：</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="" name="taxFreeIncome"
							class="taxFreeIncome">
					</td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
	<div style="padding: 5px">
		<a href="javascript:void(0)" class="button" onclick="save();">保存</a>
	</div>
</body>