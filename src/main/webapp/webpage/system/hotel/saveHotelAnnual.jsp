<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui,tools"></t:base>
</script>
</head>
<body style="" scroll="yes">
	<div style="left: 30px"></div>
	<!-- 单位是人次 type为0 -->

	<t:formvalid formid="saveHotelMonthly" refresh="false" dialog="true"
		action="hotelAnnualController.do?save" usePlugin="password"
		callback="back" layout="table" beforeSubmit="calc()">
		<input type="hidden" name="huserId" value="${hotelmanage.id }">
		<input name="id" type="hidden" value="${annualData.id }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">年份</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input readOnly="readOnly" type="text" value="${annualData.year}"
							name="year" class="creditCode" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">单位名称</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input readOnly="readOnly" type="text"
							value="${hotelmanage.unitname}" name="name" class="creditCode" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">酒店编码</span>
					</td>
					<td class="value" width="30%">
						<input type="text" readOnly="readOnly"
							value="${hotelmanage.organizationNum}" name="code" />
					</td>
				</tr>

				<tr>
					<td align="right" width="16%">
						<span>流动资产小计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.streamTotal}"
							name="streamTotal" />
					</td>
					<td align="right" width="16%">
						<span>营业费用(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.operationFee}" name="operationFee" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：存货(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.deposit}"
							name="deposit" />
					</td>
					<td align="right" width="16%">
						<span>营业税金及附加(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.oprFeeAndTax}" name="oprFeeAndTax" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>长期投资(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.longInvest}"
							name="longInvest" />
					</td>
					<td align="right" width="16%">
						<span>管理费用(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.manageCost}" name="manageCost" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>固定资产小计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.fixedAssets}"
							name="fixedAssets" />
					</td>
					<td align="right" width="16%">
						<span>税金(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.taxes}" name="taxes" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>固定资产原价(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.fixedPrice}"
							name="fixedPrice" />
					</td>
					<td align="right" width="16%">
						<span>差旅费(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.travelExpense}" name="travelExpense" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>累计折旧(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.depreciation}"
							name="depreciation" />
					</td>
					<td align="right" width="16%">
						<span>财务费用(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.financialCost}" name="financialCost" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：本年折旧(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.yearDepreciation}"
							name="yearDepreciation" />
					</td>
					<td align="right" width="16%">
						<span>其中：利息支出(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.interestCost}" name="interestCost" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>资产合计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.assetTotal}"
							name="assetTotal" />
					</td>
					<td align="right" width="16%">
						<span>销售费用(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.sellFee}" name="sellFee" />
					</td>
				</tr><tr>
					<td align="right" width="16%">
						<span>负债合计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.liabilitiesTotal}"
							name="liabilitiesTotal" />
					</td>
					<td align="right" width="16%">
						<span>公允价值变动收益(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.valueVariation}" name="valueVariation" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>所有者权益合计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.possessorTotal}"
							name="possessorTotal" />
					</td>
					<td align="right" width="16%">
						<span>营业利润(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.opreationIntrest}" name="opreationIntrest" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：实收资本</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.realIncome}"
							name="realIncome" />
					</td>
					<td align="right" width="16%">
						<span>投资收益(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.invest}" name="invest" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>营业总收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.operationIncome}"
							name="operationIncome" />
					</td>
					<td align="right" width="16%">
						<span>利润总额(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.profitTotal}" name="profitTotal" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：客房收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.roomIncome}"
							name="roomIncome" />
					</td>
					<td align="right" width="16%">
						<span>所得税(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.ownTax}" name="ownTax" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>餐饮收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.canteeIncome}"
							name="canteeIncome" />
					</td>
					<td align="right" width="16%">
						<span>本年应付职工薪酬(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.salary}" name="salary" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其他收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.otherIncome}"
							name="otherIncome" />
					</td>
					<td align="right" width="16%">
						<span>本年应交增值税(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.addedValueTax}" name="addedValueTax" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>营业外收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.extraneousIncome}"
							name="extraneousIncome" />
					</td>
					<td align="right" width="16%">
						<span>全部从业人员平均人数</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.people}" name="people" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：政府补贴(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.subsidies}"
							name="subsidies" />
					</td>
					<td align="right" width="16%">
						<span>其中：大专以上学历人数</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.college}" name="college" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>营业成本(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${annualData.operationCost}"
							name="operationCost" />
					</td>
					<td align="right" width="16%">
						<span>能耗成本(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${annualData.consumption}" name="consumption" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">客房数量</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="n1-8" name="room" datatype="n"
							value="${annualData.room }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">床位数量</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="n1-8" name="bed" datatype="n"
							value="${annualData.bed }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">实际出租夜间数</span>
					</td>

					<td class="value" width="16%" colspan="2">
						<input type="text" name="actualRent" datatype="n"
							value="${annualData.actualRent }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">可供出租夜间数</span>
					</td>
					<td class="value" width="30%">
						<input type="text" name="forHire" datatype="n"
							value="${annualData.forHire }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">填表人</span>
					</td>

					<td class="value" width="16%" colspan="2">
						<input type="text" name="reportPerson" datatype="*"
							value="${annualData.reportPerson }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">填表人电话</span>
					</td>
					<td class="value" width="30%">
						<input type="text" name="fillerTel" datatype="*"
							value="${annualData.fillerTel }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">负责人</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" name="person" datatype="*"
							value="${annualData.person }">
					</td>
				</tr>
			</tbody>

		</table>
	</t:formvalid>

</body>

<script>
function calc(){
// streamTotal 流动资产小计
var streamTotal = eval($("input[name='streamTotal']").val());
// deposit 存货
var deposit = eval($("input[name='deposit']").val());

if(streamTotal <= deposit){
	alert("流动资产小计应大于存货");
	return false;
}

// fixedPrice 固定资产原价
var fixedPrice = eval($("input[name='fixedPrice']").val());
// depreciation 累积折旧
var depreciation = eval($("input[name='depreciation']").val());
if(fixedPrice <= depreciation){
	alert("固定资产原价应大于累积折旧");
	return false;
}

// actualRent 实际出租间夜数
var actualRent = eval($("input[name='actualRent']").val());
// forHire 可供出租间夜数
var forHire = eval($("input[name='forHire']").val());
if(actualRent > forHire){
	alert("实际出租间夜数应不大于可供出租间夜数");
	return false;
}

//客房数量
var room = eval($("input[name='room']").val());
if(forHire <= room){
	alert("可供出租间夜数应大于客房数");
	return false;
}

// roomIncome 客房收入
var roomIncome = eval($("input[name='roomIncome']").val());
if(roomIncome == 0){
	alert("客房收入不能为0");
	return false;
}

// college 大专以上学历人数 
var college = eval($("input[name='college']").val());
// people 全部从业人员平均人数
var people = eval($("input[name='people']").val());
if(college == 0){
	alert("大专以上学历人数不能为0");
	return false;
}
if(people == 0){
	alert("全部从业人员平均人数不能为0");
	return false;
}

// liabilitiesTotal 负债合计
var liabilitiesTotal = eval($("input[name='liabilitiesTotal']").val());
// possessorTotal 所有者权益合计
var possessorTotal = eval($("input[name='possessorTotal']").val());
// assetTotal 资产合计
var assetTotal = eval($("input[name='assetTotal']").val());
$("input[name='assetTotal']").val((possessorTotal+liabilitiesTotal).toFixed(3));

// operationIncome 营业总收入
var operationIncome = eval($("input[name='operationIncome']").val());
// canteeIncome 餐饮收入
var canteeIncome = eval($("input[name='canteeIncome']").val());
// otherIncome 其他收入
var otherIncome = eval($("input[name='otherIncome']").val());
$("input[name='operationIncome']").val((roomIncome+canteeIncome+otherIncome).toFixed(3));
}
</script>