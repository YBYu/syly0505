<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
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
/*  function tijiao(){
$.ajax({
	cache: true,
	type: "POST",
	url:"hotelAnnualController.do?save",
	data:$('#saveHotelMonthly').serialize(),// formid
	async: false,
	error: function(request) {
		alert("Connection error");
	},
	success: function(data) {
		alert("年报添加成功");
	}
});
}  */
</script>
</head>
<body style="" scroll="yes">
	<div style="left: 30px"></div>
	<!-- 单位是人次 type为0 -->

	<t:formvalid formid="saveHotelMonthly" refresh="false" dialog="true"
		usePlugin="password" layout="table">
		<input id="id" type="hidden" value="${id }">
		<table style="width: 70%;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tbody>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">年份：</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" value="" name="year" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">单位名称：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="" name="name" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">酒店编码：</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="" name="code" />
					</td>
				</tr>

				<tr>
					<td align="right" width="16%">
						<span class="filedzt">年末资产负债(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="debt" class="creditCode" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">流动资产小计(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="streamTotal" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">长期投资(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="longInvest" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">固定资产小计(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="fixedAssets" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">固定资产原价(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="number" value="" name="fixedPrice" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>

				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">累计折旧(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="depreciation"
							class="creditCode" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">本年折旧(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="yearDepreciation" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">资产合计(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="assetTotal" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">负债合计(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="liabilitiesTotal" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">所有者权益合计(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="possessorTotal"
							class="creditCode" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">实收资本(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="realIncome" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">营业总收入(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="operationIncome"
							class="creditCode" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">客房收入(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="roomIncome" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">餐饮收入(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="canteeIncome"
							class="creditCode" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">其他收入(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="otherIncome" />
					</td>
				</tr>
				<tr>

					<td align="right" width="16%">
						<span class="filedzt">营业成本(千元)：</span>
					</td>
					<td class="value" width="30%" colspan="4">
						<input type="number" value="" name="operationCost" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">营业费用(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="operationFee"
							class="operationFee" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">营业税金及附加(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="oprFeeAndTax" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">税金(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="taxes" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">差旅费(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="travelExpense" />
					</td>
				</tr>

				<tr>
					<td align="right" width="16%">
						<span class="filedzt">财务费用(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="financialCost"
							class="creditCode" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">利息支出(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="interestCost" />
					</td>
				</tr>

				<tr>
					<td align="right" width="16%">
						<span class="filedzt">公允价值变动收益(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="valueVariation"
							class="creditCode" errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">营业利润(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="opreationIntrest" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">投资收益(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="invest" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">利润总额(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="profitTotal" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">所得税(千元)：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="ownTax" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">本年应付职工薪酬(千元)：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="salary" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">全部从业人员平均人数：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="number" value="" name="people" class="creditCode"
							errormsg="统一社会信用代码" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">大专以上学历人数：</span>
					</td>
					<td class="value" width="30%">
						<input type="number" value="" name="college" />
					</td>
				</tr>

			</tbody>

		</table>
	</t:formvalid>
	<!-- <div style="padding:5px">
	 <a href="javascript:void(0)"   style="margin-left: 615px"  class="easyui-linkbutton" onclick="tijiao();">保存</a>
</div> -->
	<!-- 单位是人天  type为1-->

</body>