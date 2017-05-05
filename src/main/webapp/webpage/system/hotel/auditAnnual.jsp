<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="yes">
	<div style="left: 30px"></div>
	<!-- 单位是人次 type为0 -->

	<t:formvalid formid="saveHotelMonthly" refresh="false" dialog="true"
		usePlugin="password" layout="table">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">年份：</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" value="${hotelAnnual.year}" name="year"
							class="creditCode" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">单位名称：</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelAnnual.name}" name="name"
							class="creditCode" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">酒店编码：</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelAnnual.code}" name="code" />
					</td>
				</tr>

				<tr>
					<td align="right" width="16%">
						<span>流动资产小计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.streamTotal}"
							name="streamTotal" />
					</td>
					<td align="right" width="16%">
						<span>营业费用(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.operationFee}" name="operationFee" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：存货(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.deposit}"
							name="deposit" />
					</td>
					<td align="right" width="16%">
						<span>营业税金及附加(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.oprFeeAndTax}" name="oprFeeAndTax" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>长期投资(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.longInvest}"
							name="longInvest" />
					</td>
					<td align="right" width="16%">
						<span>管理费用(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.manageCost}" name="manageCost" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>固定资产小计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.fixedAssets}"
							name="fixedAssets" />
					</td>
					<td align="right" width="16%">
						<span>税金(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.taxes}" name="taxes" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>固定资产原价(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.fixedPrice}"
							name="fixedPrice" />
					</td>
					<td align="right" width="16%">
						<span>差旅费(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.travelExpense}" name="travelExpense" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>累计折旧(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.depreciation}"
							name="depreciation" />
					</td>
					<td align="right" width="16%">
						<span>财务费用(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.financialCost}" name="financialCost" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：本年折旧(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.yearDepreciation}"
							name="yearDepreciation" />
					</td>
					<td align="right" width="16%">
						<span>其中：利息支出(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.interestCost}" name="interestCost" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>资产合计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.assetTotal}"
							name="assetTotal" />
					</td>
					<td align="right" width="16%">
						<span>销售费用(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.sellFee}" name="sellFee" />
					</td>
				</tr><tr>
					<td align="right" width="16%">
						<span>负债合计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.liabilitiesTotal}"
							name="liabilitiesTotal" />
					</td>
					<td align="right" width="16%">
						<span>公允价值变动收益(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.valueVariation}" name="valueVariation" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>所有者权益合计(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.possessorTotal}"
							name="possessorTotal" />
					</td>
					<td align="right" width="16%">
						<span>营业利润(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.opreationIntrest}" name="opreationIntrest" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：实收资本</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.realIncome}"
							name="realIncome" />
					</td>
					<td align="right" width="16%">
						<span>投资收益(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.invest}" name="invest" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>营业总收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.operationIncome}"
							name="operationIncome" />
					</td>
					<td align="right" width="16%">
						<span>利润总额(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.profitTotal}" name="profitTotal" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：客房收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.roomIncome}"
							name="roomIncome" />
					</td>
					<td align="right" width="16%">
						<span>所得税(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.ownTax}" name="ownTax" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>餐饮收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.canteeIncome}"
							name="canteeIncome" />
					</td>
					<td align="right" width="16%">
						<span>本年应付职工薪酬(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.salary}" name="salary" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其他收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.otherIncome}"
							name="otherIncome" />
					</td>
					<td align="right" width="16%">
						<span>本年应交增值税(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.addedValueTax}" name="addedValueTax" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>营业外收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.extraneousIncome}"
							name="extraneousIncome" />
					</td>
					<td align="right" width="16%">
						<span>全部从业人员平均人数</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.people}" name="people" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>其中：政府补贴(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.subsidies}"
							name="subsidies" />
					</td>
					<td align="right" width="16%">
						<span>其中：大专以上学历人数</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.college}" name="college" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span>营业成本(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="decimals3" value="${hotelAnnual.operationCost}"
							name="operationCost" />
					</td>
					<td align="right" width="16%">
						<span>能耗成本(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3"
							value="${hotelAnnual.consumption}" name="consumption" />
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">客房数量</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="n1-8" name="room" datatype="n"
							value="${hotelAnnual.room }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">床位数量</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="n1-8" name="bed" datatype="n"
							value="${hotelAnnual.bed }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">实际出租夜间数</span>
					</td>

					<td class="value" width="16%" colspan="2">
						<input type="text" name="actualRent" datatype="n"
							value="${hotelAnnual.actualRent }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">可供出租夜间数</span>
					</td>
					<td class="value" width="30%">
						<input type="text" name="forHire" datatype="n"
							value="${hotelAnnual.forHire }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">填表人</span>
					</td>

					<td class="value" width="16%" colspan="2">
						<input type="text" name="reportPerson" datatype="*"
							value="${hotelAnnual.reportPerson }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">填表人电话</span>
					</td>
					<td class="value" width="30%">
						<input type="text" name="fillerTel" datatype="*"
							value="${hotelAnnual.fillerTel }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">负责人</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" name="person" datatype="*"
							value="${hotelAnnual.person }">
					</td>
				</tr>
			</tbody>

		</table>
	</t:formvalid>
	<div style="padding: 5px">
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="check();">确认审核</a> -->

	</div>
	<!-- 单位是人天  type为1-->

</body>