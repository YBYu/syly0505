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
		action="scenicMonthController.do?save" usePlugin="password"
		layout="table">
		<input name="scenicdataid" type="hidden" value="${scenicdataid}">
		<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

		<!--  <table style="width:750px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
  -->
		<div class="container">
			<div class="MainInfo_con">
				<p class="red pdtb10">（若已上传上个月月报或者未到月报填报时间，提示不可填写）</p>
				<!--经营情况月报填报-->
				<div class="item item1 mgb20">
					<span class="on">经营情况月报填报</span>
					<div class="panel-body ItemCon">
						<table width="640">
							<tr>
								<td>
									<span>时间：</span>
									<lable class="gray">2016年10月</lable>
									<input type="text" name="time" id="LawCode" class="text"
										style="width: 188px;">
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<span style="width: 105px;">法人单位编码：</span>
									<input type="text" name="LawCode" id="LawCode" class="text"
										style="width: 188px;">
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<span style="width: 64px;">单位名称:</span>
									<input type="text" name="Copname" value="${scenicdata.name}"
										id="Copname" class="text" disabled="disabled"
										style="width: 229px">
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<span style="width: 135px;">营业收入（万元）：</span>
									<input type="text" name="taking" id="income" class="text"
										style="width: 158px">
								</td>
								<td>
									<span style="width: 180px;">营业税金及附加（万元）：</span>
									<input type="text" name="businesstax" id="BusinessTax"
										class="text" style="width: 113px">
								</td>
							</tr>
							<tr>
								<td>
									<span>营业税金及附加（万元）：</span>
									<input type="text" name="BusinessTax" id="BusinessTax"
										class="text" style="width: 113px">
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<span style="width: 135px;">利润总额（万元）：</span>
									<input type="text" name="totalprofit" id="ProfitSum"
										class="text" style="width: 158px">
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<span style="width: 150px;">年末从业人员（人）：</span>
									<input type="text" name="endemployee" id="employees"
										class="text" style="width: 143px">
								</td>
								<td>
									<span style="width: 180px;">其中吸纳下岗职工（人）：</span>
									<input type="text" name="laidworker" id="LaidOff" class="text"
										style="width: 113px">
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<span style="width: 195px;">其中转移农村劳动力（人）：</span>
									<input type="text" name="countrylabor" id="RuralLabor"
										class="text" style="width: 98px">
								</td>
							</tr>
							<tr>
								<td>
									<span style="width: 165px;">固定资产原价（万元）：</span>
									<input type="text" name="price" id="OgValue" class="text"
										style="width: 128px">
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<span style="width: 165px;">固定资产净值（万元）：</span>
									<input type="text" name="asset" id="NetValue" class="text"
										style="width: 128px">
								</td>
							</tr>
							<tr>
								<td>
									<span style="width: 49px;">团散比:</span>
									<input type="text" name="compare" id="GroupRate" class="text"
										style="width: 244px">
								</td>
							</tr>
							<tr>
								<td>
									<span style="width: 90px;">单位负责人：</span>
									<input type="text" name="principal" id="PerCharge" class="text"
										style="width: 203px">
								</td>
								<td>
									<span style="width: 60px;">填表人：</span>
									<input type="text" name="preparer" id="FillTable" class="text"
										style="width: 233px">
								</td>
							</tr>
							<tr>
								<td>
									<span style="width: 45px;">电话：</span>
									<input type="text" name="telephone" id="FillTable" class="text"
										style="width: 248px">
								</td>
								<td>
									<span style="width: 75px;">报出日期：</span>
									<input type="text" name="reportdate" id="SubDate"
										class="FillTable" style="width: 218px">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!--经营情况月报填报说明-->
				<div class="item item2">
					<span class="on">经营情况月报填报说明</span>
					<div class="panel-body ItemCon">
						<p>1.各类旅游区（点）都必须填报，包括被国家旅游局授予A级的旅游区（点）；</p>
						<p>2.每月五日前提交到系统;</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 
		</tbody>
	</table>   -->
	</t:formvalid>
</body>