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
		callback="reloadTable()" action="hotelEstimateController.do?save"
		usePlugin="password" layout="table" beforeSubmit="doAdd()">
		<input id="id" name="id" type="hidden" value="${hotelEstimate.id }">

		<%-- <input id="id" type="hidden" name="huserId" value="${hotelmanage.id }"> --%>
		<table cellpadding="0" cellspacing="1" class="formtable"
			id="tableReport">
			<thead>
				<!--许可证号-->

				<tr>
					<td align="right" width="16%">
						<span class="filedzt">年份</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${year }" name="year"
							class="companyname" errormsg="请输入正确的年份" readonly="readonly" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">月份</span>
					</td>
					<td class="value" width="30%" colspan="2">
						<input type="text" value="${month }" name="month"
							errormsg="请输入正确的月份" readonly="readonly">
					</td>
				</tr>

				<!--法人信息-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">地区编号</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" name="countryCode" value="460100"
							readOnly="readOnly" errormsg="请输入正确的地区编号" />
						<!-- <select name="countryCode" value="460100" name="countryCode">
							<option value="460100">三亚市</option>
							<option value="460101">市辖区</option>
							<option value="460202">崖州区</option>
							<option value="460205">海棠区</option>
							<option value="460203">天涯区</option>
							<option value="460204">吉阳区</option> 
			</select> -->
					</td>
					<!-- <td align="right" width="16%"><span class="filedzt">类型</span></td>
                <td class="value" width="30%" colspan="4">
                <select id="cc" class="easyui-combobox" name="type" style="width:140px;">  			    			      
			    <option value="0">人次</option>   
			    <option value="1">人天</option>   
			      </select>
                </td> -->
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">国内(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.domestic }" datatype="n"
							name="domestic" errormsg="请输入正确的国内(人次)" class="rentian" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">入境(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.entry }" name="entry"
							class="rentian" style="border: 0;" />
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">外国人(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.foreignTimes }"
							datatype="n" name="foreignTimes" errormsg="请输入正确的外国人(人次)"
							class="rentian" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">香港(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.hongkong }" datatype="n"
							name="hongkong" errormsg="请输入正确的香港(人次)" class="rentian">
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">澳门(人次)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.macao }" datatype="n"
							name="macao" class="creditCode" errormsg="请输入正确的澳门(人次)"
							class="rentian" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">台湾(人次)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.taiwan }" datatype="n"
							name="taiwan" errormsg="请输入正确的台湾(人次)" class="rentian" />
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">总数(人次)</span>
					</td>
					<td class="value" width="30%" colspan="4">
						<input type="text" value="${hotelEstimate.sum }" name="sum"
							style="border: 0;">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">国内(人天)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.domesticD }"
							datatype="n" name="domesticD" errormsg="请输入正确的国内(人天)"
							class="rentian" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">入境(人天)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.entryD }" name="entryD"
							style="border: 0;" readOnly class="rentian">
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">外国人(人天)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.foreignDays }"
							datatype="n" name="foreignDays" errormsg="请输入正确的外国人(人天)"
							class="rentian" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">香港(人天)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.hongkongD }"
							datatype="n" name="hongkongD" errormsg="请输入正确的香港(人天)"
							class="rentian">
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">澳门(人天)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelEstimate.macaoD }" datatype="n"
							name="macaoD" class="creditCode" errormsg="请输入正确的澳门(人天)"
							class="rentian" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">台湾(人天)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelEstimate.taiwanD }" datatype="n"
							name="taiwanD" errormsg="请输入正确的台湾(人天)" class="rentian" />
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
			<tfoot>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">总数(人天)</span>
					</td>
					<td class="value" width="30%" colspan="2">
						<input type="text" value="${hotelEstimate.sumD }" name="sumD"
							style="border: 0;">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">填写日期</span>
					</td>
					<td class="value" width="30%">
						<input class="easyui-datebox" value="${hotelEstimate.createTime }"
							name="createTime" />
					</td>
				</tr>

			</tfoot>
			<tr>
				<%-- <td align="right" width="16%"><span class="filedzt">填写人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${hotelEstimate.bymanager }" datatype="*" name="bymanager" class="creditCode" errormsg="请输入正确的填写人" /></td>  --%>

			</tr>


			</tbody>
		</table>
	</t:formvalid>

	<script>
window.setTimeout(function() {
	var ips = $("#tableReport tbody input");
	var dataId = $("#id")[0].value;
	//如果为空则代表新建，赋默认值
	if(dataId == null || dataId.length == 0){
		for (var i = 0; i < ips.length; i++) {
			ips[i].value = "0";
		}
	} 
}, 1000);

	$(".rentian").change(function(){
		var entry=eval($("input[name='foreignTimes']").val())+eval($("input[name='hongkong']").val())+eval($("input[name='macao']").val())+eval($("input[name='taiwan']").val());
		$("input[name='entry']").val(entry);
		var sum=eval(entry)+eval($("input[name='domestic']").val());
		$("input[name='sum']").val(sum);

		var entryD=eval($("input[name='foreignDays']").val())+eval($("input[name='hongkongD']").val())+eval($("input[name='macaoD']").val())+eval($("input[name='taiwanD']").val());
		$("input[name='entryD']").val(entryD);
		var sumD=eval(entryD)+eval($("input[name='domesticD']").val());
		$("input[name='sumD']").val(sumD);
	});
	
function doAdd(){
	/* var entry=eval($("input[name='foreignTimes']").val())+eval($("input[name='hongkong']").val())+eval($("input[name='macao']").val())+eval($("input[name='taiwan']").val());
	$("input[name='entry']").val(entry);
	var sum=eval(entry)+eval($("input[name='domestic']").val());
	$("input[name='sum']").val(sum);

	var entryD=eval($("input[name='foreignDays']").val())+eval($("input[name='hongkongD']").val())+eval($("input[name='macaoD']").val())+eval($("input[name='taiwanD']").val());
	$("input[name='entryD']").val(entryD);
	var sumD=eval(entryD)+eval($("input[name='domesticD']").val());
	$("input[name='sumD']").val(sumD); */
}


</script>

</body>