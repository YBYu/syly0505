<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css"
	href="plug-in/style/yuebao/style.css">
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="">
	<t:formvalid formid="formobj" refresh="false" dialog="true"
		action="travelAnnualFinanceController.do?saveByAdmin"
		usePlugin="password" layout="table" tiptype="1" callback="reloadTable">
		<input name="traveldataid" type="hidden" value="${traveldataid}" />
		<input name="travelid" type="hidden" value="${travelid}" />

		<table style="width: 680px; font-size: 12px;" cellpadding="0"
			cellspacing="1" class="formtable">
			<tr>
				<td width="120">单位名称</td>
				<td width="100">
					<input type="text" name="name" datatype="*" class="inputSize"
						value="${traveldata.name}" />
				</td>
				<td width="120">许可证编号</td>
				<td width="100">
					<input type="text" name="code" datatype="*" class="inputSize"
						value="${traveldata.licensenum}" />
				</td>
			<tr>
			<tr>
				<%-- <td width="120">填报时间</td>
				<td width="100"><input type="text" name="reportTime" datatype="*"
					class="easyui-datebox" value="${travelAnnualdata.reportTime}" /></td> --%>
				<td width="120">年份</td>
				<td width="100" colspan="3">
					<input type="text" name="year" datatype="n" readOnly="readOnly"
						value="${travelAnnualdata.year}">
				</td>
			<tr>
			<tr>
				<td width="120">填表人</td>
				<td width="100">
					<input type="text" name="reportPerson" datatype="*"
						class="inputSize" value="${travelAnnualdata.reportPerson}" />
				</td>
				<td width="120">填表人电话</td>
				<td width="100">
					<input type="text" name="telephone" datatype="m" class="inputSize"
						value="${travelAnnualdata.telephone}" />
				</td>
			<tr>
			<tr>
				<td width="120">年末资产负债</td>
				<td width="100">
					<input type="text" name="debt" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.debt}" autofocus="autofocus" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">主营业务利润</td>
				<td width="100">
					<input type="text" name="inTravelReturn" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.inTravelReturn}" />
					<span class="mUnit">千元</span>
				</td>

			</tr>
			<tr>
				<td width="120">流动资产小计</td>
				<td width="100">
					<input type="text" name="streamTotal" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.streamTotal}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">管理费用</td>
				<td width="100">
					<input type="text" name="manageCost" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.manageCost}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">长期投资</td>
				<td width="100">
					<input type="text" id="longInvest" name="longInvest"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.longInvest}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">税金</td>
				<td width="100">
					<input type="text" id="taxes" name="taxes"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.taxes}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">固定资产小计</td>
				<td width="100">
					<input type="text" name="fixedAssets" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.fixedAssets}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">差旅费</td>
				<td width="100">
					<input type="text" id="travelExpense" name="travelExpense"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.travelExpense}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">固定资产原价</td>
				<td width="100">
					<input type="text" id="fixedPrice" name="fixedPrice"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.fixedPrice}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">财务费用</td>
				<td width="100">
					<input type="text" id="managst" name="financialCost"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.financialCost}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<!--添加-->

			<tr>
				<td width="120">累计折旧</td>
				<td width="100">
					<input type="text" name="depreciation" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.depreciation}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">利息支出</td>
				<td width="100">
					<input type="text" name="interestCost" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.interestCost}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">本年折旧</td>
				<td width="100">
					<input type="text" name="yearDepreciation"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.yearDepreciation}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">销售费用</td>
				<td width="100">
					<input type="text" name="sellingExpense" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.sellingExpense}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">资产合计</td>
				<td width="100">
					<input type="text" name="assetTotal" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.assetTotal}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">公允价值变动收益</td>
				<td width="100">
					<input type="text" name="valueVariation" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.valueVariation}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">负债合计</td>
				<td width="100">
					<input type="text" name="liabilitiesTotal"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.liabilitiesTotal}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">营业利润</td>
				<td width="100">
					<input type="text" name="operatingIncome" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.operatingIncome}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">所有者权益合计</td>
				<td width="100">
					<input type="text" name="possessor" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.possessor}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">毛利润</td>
				<td width="100">
					<input type="text" name="gross" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.gross}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">实收资本</td>
				<td width="100">
					<input type="text" name="realIncome" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.realIncome}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">投资收益</td>
				<td width="100">
					<input type="text" name="invest" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.invest}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">权益及分配</td>
				<td width="100">
					<input type="text" name="gainloss" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.gainloss}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">利润总额</td>
				<td width="100">
					<input type="text" name="totalReturn" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.totalReturn}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">营业收入</td>
				<td width="100">
					<input type="text" name="businessIncome" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.businessIncome}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">所得税</td>
				<td width="100">
					<input type="text" name="incomeTax" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.incomeTax}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">入境旅游营业收入</td>
				<td width="100">
					<input type="text" name="intoIncome" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.intoIncome}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">工资福利费</td>
				<td width="100">
					<input type="text" name="salary" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.salary}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">出境旅游营业收入</td>
				<td width="100">
					<input type="text" name="outIncome" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.outIncome}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">本年应付职工薪酬</td>
				<td width="100">
					<input type="text" name="employeeSalary" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.employeeSalary}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">国内旅游营业收入</td>
				<td width="100">
					<input type="text" name="inTravelIncome" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.inTravelIncome}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">本年应付增值税</td>
				<td width="100">
					<input type="text" name="addTex" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.addTex}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
			<tr>
				<td width="120">营业外收入</td>
				<td width="100">
					<input type="text" name="extraGains" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.extraGains}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">全部从业人员年平均人数</td>
				<td width="100">
					<input type="text" name="numAverage" style="text-align: right"
						datatype="n" errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!"
						class="inputSize" value="${travelAnnualdata.numAverage}" />
					<span class="mUnit"></span>
				</td>
			</tr>
			<tr>
				<td width="120">政府补贴</td>
				<td width="100">
					<input type="text" name="governmentSubsidies"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.governmentSubsidies}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">大专以上学历人数</td>
				<td width="100">
					<input type="text" name="college" style="text-align: right"
						datatype="n" errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!"
						class="inputSize" value="${travelAnnualdata.college}" />
					<span class="mUnit"></span>
				</td>
			</tr>
			<tr>
				<td width="120">营业成本</td>
				<td width="100">
					<input type="text" name="cost" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.cost}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">签订劳动合同的导游人数</td>
				<td width="100">
					<input type="text" name="tourNum" style="text-align: right"
						datatype="n" errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!"
						class="inputSize" value="${travelAnnualdata.tourNum}" />
					<span class="mUnit"></span>
				</td>
			</tr>
			<tr>
				<td width="120">营业费用</td>
				<td width="100">
					<input type="text" name="businessExpenses"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.businessExpenses}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">领队人数</td>
				<td width="100">
					<input type="text" name="leadGroup" style="text-align: right"
						datatype="n" errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!"
						class="inputSize" value="${travelAnnualdata.leadGroup}" />
					<span class="mUnit"></span>
				</td>
			</tr>
			<tr>
				<td width="120">营业税金及附加</td>
				<td width="100">
					<input type="text" name="businessTexAdd" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.businessTexAdd}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">分支机构数量</td>
				<td width="100">
					<input type="text" name="groupOrg" style="text-align: right"
						datatype="n" errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!"
						class="inputSize" value="${travelAnnualdata.groupOrg}" />
					<span class="mUnit"></span>
				</td>
			</tr>
			<tr>
				<td width="120">主营业务利润</td>
				<td width="100">
					<input type="text" name="mainOperation" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.mainOperation}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">门店数量</td>
				<td width="100">
					<input type="text" name="storeNum" style="text-align: right"
						datatype="n" errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!"
						class="inputSize" value="${travelAnnualdata.storeNum}" />
					<span class="mUnit"></span>
				</td>
			</tr>
			<tr>
				<td width="120">入境旅游业务利润</td>
				<td width="100">
					<input type="text" name="profitInTravel" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.profitInTravel}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">分社数量</td>
				<td width="100">
					<input type="text" name="branchOffice" style="text-align: right"
						datatype="n" errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!"
						class="inputSize" value="${travelAnnualdata.branchOffice}" />
					<span class="mUnit"></span>
				</td>
			</tr>
			<tr>
				<td width="120">出境旅游业务利润</td>
				<td width="100">
					<input type="text" name="outTravelBusiness"
						style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.outTravelBusiness}" />
					<span class="mUnit">千元</span>
				</td>
				<td width="120">子公司数量</td>
				<td width="100">
					<input type="text" name="subsidiary" style="text-align: right"
						datatype="n" errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!"
						class="inputSize" value="${travelAnnualdata.subsidiary}" />
					<span class="mUnit"></span>
				</td>
			</tr>
			<tr>
				<td>是否执行《企业会计准则》</td>
				<td>
					<span>
						<input type="radio" value="是" name="whetherPerform"
							<c:if test="${'是' == travelAnnualdata.whetherPerform}">checked="checked"</c:if>>
						是
					</span>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>
						<input type="radio" value="否" name="whetherPerform"
							<c:if test="${'否' == travelAnnualdata.whetherPerform}">checked="checked"</c:if>>
						否
					</span>
				</td>
				<td>资产减值损失：</td>
				<td>
					<input type="text" name="loss" style="text-align: right"
						datatype="/^0{1}([.]\d{1,3})?$|^[1-9]\d*([.]{1}[0-9]{1,3})?$/"
						errormsg="请输入正确的数据!" nullmsg="请输入正确的数据!" class="inputSize"
						value="${travelAnnualdata.loss}" />
					<span class="mUnit">千元</span>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>