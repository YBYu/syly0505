<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="margin-left: 38px">
	<t:formvalid formid="formobj" refresh="false" dialog="true"
		action="travelQuarterController.do?saveTwoByAdmin"
		usePlugin="password" layout="table" tiptype="1" callback="reloadTable"
		beforeSubmit="addSub()">
		<input name="travelid" type="hidden" value="${travelid}">
		<input id="dataId" name="id" type="hidden" value="${quarteroneid}">
		<table id="tableReport" style="width: 750px; font-size: 12px;"
			cellpadding="0" cellspacing="1" class="formtable">
			<thead>
				<tr>
					<td width="156" colspan="2"
						style="background: none; text-align: center;">单位名称</td>
					<td width="160" colspan="2">
						<input type="text" id="Copname" name="name" class="inputSize"
							value="${travelData.name}" />
					</td>
					<td width="159" colspan="2" style="text-align: center;">许可证编号</td>
					<td width="160" style="background: none; text-align: left;">
						<input type="text" id="licenseNum" name="licensenum"
							class="inputSize" value="${travelData.licensenum}" />
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2"
						style="background: none; text-align: center;">单位负责人</td>
					<td width="160" colspan="2">
						<input type="text" id="responsible" name="principal"
							class="inputSize" value="${travelData.principal}" />
					</td>
					<td width="159" colspan="2" style="text-align: center;">填报日期</td>
					<td width="160" style="background: none; text-align: left;">
						<input type="text" name="reportDate" class="easyui-datebox"
							value="${quarterTwoData.reportDate}" style="width: 146px;">
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2" style="text-align: center;">年份</td>
					<td width="160" colspan="2">
						<input type="text" name="year" readOnly="readOnly"
							class="inputSize" value="${quarterTwoData.year}"
							style="width: 140px;">
					</td>
					<td width="159" colspan="2" style="text-align: center;">季度</td>
					<td width="160" style="text-align: left;">
						<select name="quarter" style="width: 146px"
							onfocus="this.defaultIndex=this.selectedIndex"
							onchange="this.selectedIndex=this.defaultIndex">
							<option value="1"
								<c:if test="${'1' eq quarterTwoData.quarter }">selected</c:if>>第一季度</option>
							<option value="2"
								<c:if test="${'2' eq quarterTwoData.quarter }">selected</c:if>>第二季度</option>
							<option value="3"
								<c:if test="${'3' eq quarterTwoData.quarter }">selected</c:if>>第三季度</option>
							<option value="4"
								<c:if test="${'4' eq quarterTwoData.quarter }">selected</c:if>>第四季度</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2"
						style="background: none; text-align: center;">填表人</td>
					<td width="160" colspan="2">
						<input type="text" name="reportPerson" class="inputSize"
							value="${quarterTwoData.reportPerson}">
					</td>
					<td width="159" colspan="2" style="text-align: center;">填表人电话</td>
					<td width="160" style="background: none; text-align: left;">
						<input type="text" name="reportTelephone" class="inputSize"
							value="${quarterTwoData.reportTelephone}" />
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="112" style="text-align: center;" rowspan="2" colspan="2">指标名称</td>
					<td width="80" align="center" style="font-weight: 900;">人数(人次)</td>
					<td width="80" align="center" style="font-weight: 900;">人天数(人天)</td>

					<td width="115" style="text-align: center;" rowspan="2" colspan="2">指标名称</td>
					<td width="160" align="center" style="font-weight: 900;">人数(人次)</td>
				</tr>
				<tr>
					<td width="80" style="text-align: center; font-weight: 900;">本季</td>
					<td width="80" style="text-align: center; font-weight: 900;">本季</td>
					<td width="160" style="text-align: center; font-weight: 900;">本季</td>
				</tr>
				<tr>
					<td width="112" colspan="2">出境游客</td>
					<td width="80">
						<input type="text" id="endOfAssetbal" readonly="readonly"
							value="${quarterTwoData.outOne}" name="outOne" class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.outTwo}" name="outTwo" class="inputSize" />
					</td>
					<td width="115" colspan="2">意大利</td>
					<td width="160">
						<input type="text" id="enterProfit"
							value="${quarterTwoData.italyOne}" name="italyOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">亚洲小计</td>
					<td width="80">
						<input type="text" id="endOfAssetbal" style="background: #FAFAFA"
							value="${quarterTwoData.asianTotalOne}" name="asianTotalOne"
							readonly="readonly" class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.asianTotalTwo}" name="asianTotalTwo"
							readonly="readonly" style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">瑞士</td>
					<td width="160">
						<input type="text" id="exitProfit"
							value="${quarterTwoData.switzerLandOne}" name="switzerLandOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">香港地区</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.hongKongOne}" name="hongKongOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.hongKongTwo}" name="hongKongTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">瑞典</td>
					<td width="160">
						<input type="text" id="enterProfit"
							value="${quarterTwoData.swedenOne}" name="swedenOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">澳门地区</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.macauOne}" name="macauOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.macauTwo}" name="macauTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">俄罗斯</td>
					<td width="160">
						<input type="text" id="manageCost"
							value="${quarterTwoData.russiaOne}" name="russiaOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">台湾地区</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.taiWanOne}" name="taiWanOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.taiWanTwo}" name="taiWanTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">西班牙</td>
					<td width="160">
						<input type="text" id="tax" value="${quarterTwoData.spainOne}"
							name="spainOne" class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">日本</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.japanOne}" name="japanOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.japanTwo}" name="japanTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">荷兰</td>
					<td width="160">
						<input type="text" id="travelExpense"
							value="${quarterTwoData.holLandOne}" name="holLandOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">韩国</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.koreaOne}" name="koreaOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.koreaTwo}" name="koreaTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">丹麦</td>
					<td width="160">
						<input type="text" id="financialCost"
							value="${quarterTwoData.denMarkOne}" name="denMarkOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">蒙古</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.mongoliaOne}" name="mongoliaOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.mongoliaTwo}" name="mongoliaTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">欧洲其他</td>
					<td width="160">
						<input type="text" id="interestPay"
							value="${quarterTwoData.europeOtherOne}" name="europeOtherOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">印度尼西亚</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.indonesiaOne}" name="indonesiaOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.indonesiaTwo}" name="indonesiaTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">美洲小计</td>
					<td width="160">
						<input type="text" id="fairValue" style="background: #FAFAFA"
							value="${quarterTwoData.americaTotalOne}" name="americaTotalOne"
							readonly="readonly" class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">马来西亚</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.malaysiaOne}" name="malaysiaOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.malaysiaTwo}" name="malaysiaTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">美国</td>
					<td width="160">
						<input type="text" id="fairValue" value="${quarterTwoData.usOne}"
							name="usOne" class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">菲律宾</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.philippinesOne}" name="philippinesOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.philippinesTwo}" name="philippinesTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">加拿大</td>
					<td width="160">
						<input type="text" value="${quarterTwoData.canadaOne}"
							name="canadaOne" class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">新加坡</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.singaporeOne}" name="singaporeOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.singaporeTwo}" name="singaporeTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">美洲其他</td>
					<td width="160">
						<input type="text" id="fairValue"
							value="${quarterTwoData.usOtherOne}" name="usOtherOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">泰国</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.thailandOne}" name="thailandOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.thailandTwo}" name="thailandTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">大洋洲小计</td>
					<td width="160">
						<input type="text" id="numOfCollage" style="background: #FAFAFA"
							value="${quarterTwoData.oceaniaTotalOne}" name="oceaniaTotalOne"
							class="inputSize">
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">印度</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.indiaOne}" name="indiaOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.indiaTwo}" name="indiaTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">新西兰</td>
					<td width="160">
						<input type="text" id="welfare"
							value="${quarterTwoData.zeaLandOne}" name="zeaLandOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">越南</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.vietnamOne}" name="vietnamOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.vietnamTwo}" name="vietnamTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">澳大利亚</td>
					<td width="160">
						<input type="text" id="incomeTax"
							value="${quarterTwoData.australianOne}" name="australianOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">缅甸</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.burmaOne}" name="burmaOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.burmaTwo}" name="burmaTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">大洋洲其他</td>
					<td width="160">
						<input type="text" id="payrolls"
							value="${quarterTwoData.oceaniaOtherOne}" name="oceaniaOtherOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">亚洲其他</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.asianOtherOne}" name="asianOtherOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.asianOtherTwo}" name="asianOtherTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">非洲小计</td>
					<td width="160">
						<input type="text" id="aveNumOfEmp" style="background: #FAFAFA"
							value="${quarterTwoData.africaOne}" name="africaOne"
							readonly="readonly" class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">欧洲小计</td>
					<td width="80">
						<input type="text" id="endOfAssetbal" style="background: #FAFAFA"
							value="${quarterTwoData.europeOne}" name="europeOne"
							readonly="readonly" class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.europeTwo}" name="europeTwo"
							readonly="readonly" style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">埃及</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterTwoData.egyptOne}" name="egyptOne"
							class="inputSize">
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">英国</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.englishOne}" name="englishOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.englishTwo}" name="englishTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">肯尼亚</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterTwoData.kenyaOne}" name="kenyaOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">法国</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.franchOne}" name="franchOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.franchTwo}" name="franchTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">南非</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterTwoData.southAfricaOne}" name="southAfricaOne"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112" colspan="2">德国</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.germanyOne}" name="germanyOne"
							class="inputSize" />
					</td>
					<td width="80">
						<input type="text" id="endOfAssetbal"
							value="${quarterTwoData.germanyTwo}" name="germanyTwo"
							style="visibility: hidden" class="inputSize" />
					</td>
					<td width="115" colspan="2">非洲其他</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterTwoData.africaOtherOne}" name="africaOtherOne"
							class="inputSize">
					</td>
				</tr>
				<tr>
					<td width="115" colspan="2">其他小计</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterTwoData.otherTotalOne}" name="otherTotalOne"
							class="inputSize">
					</td>
					<td width="80"></td>
					<td width="115" colspan="2"></td>
					<td width="160"></td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
</body>
<script>
window.setTimeout(function() {
	var ips = $("#tableReport tbody input");
	var dataId = $("#dataId")[0].value;
	//如果为空则代表新建，赋默认值
	if(dataId == null || dataId.length == 0){
		for (var i = 0; i < ips.length; i++) {
			ips[i].value = "0";
			// 添加点击事件，清除为0的默认数据
			ips[i].onclick = function(e){
				if(this.value == "0")
				this.value = "";
				this.onclick = null;
			}
		}
	} 
}, 1000);

window.setInterval(function(){
		asiaTotalOne();
		europeOne();
		americaTotalOne();
		africaOne();
		totalOceaniaOne();
		calcTotal();
},1000);

// 计算亚洲小计
function asiaTotalOne(){
	var asiaTotalOne=eval($("input[name='hongKongOne']").val())+eval($("input[name='macauOne']").val())+eval($("input[name='taiWanOne']").val())+eval($("input[name='japanOne']").val())+eval($("input[name='koreaOne']").val())+eval($("input[name='mongoliaOne']").val())+eval($("input[name='indonesiaOne']").val())+eval($("input[name='malaysiaOne']").val())+eval($("input[name='philippinesOne']").val())+eval($("input[name='singaporeOne']").val())+eval($("input[name='thailandOne']").val())+eval($("input[name='indiaOne']").val())+eval($("input[name='vietnamOne']").val())+eval($("input[name='burmaOne']").val())+eval($("input[name='asianOtherOne']").val());
	$("input[name='asianTotalOne']").val(isNaN(asiaTotalOne) ? null : asiaTotalOne);
}

// 计算欧洲小计
function europeOne(){
	var europeOne=eval($("input[name='italyOne']").val())+eval($("input[name='switzerLandOne']").val())+eval($("input[name='swedenOne']").val())+eval($("input[name='russiaOne']").val())+eval($("input[name='spainOne']").val())+eval($("input[name='holLandOne']").val())+eval($("input[name='denMarkOne']").val())+eval($("input[name='europeOtherOne']").val())+eval($("input[name='englishOne']").val())+eval($("input[name='franchOne']").val())+eval($("input[name='germanyOne']").val());
	$("input[name='europeOne']").val(isNaN(europeOne) ? null : europeOne);
}

// 计算美洲小计
function americaTotalOne(){
	var americaTotalOne=eval($("input[name='usOne']").val())+eval($("input[name='usOtherOne']").val())+eval($("input[name='canadaOne']").val());
	$("input[name='americaTotalOne']").val(isNaN(americaTotalOne) ? null : americaTotalOne);
}

// 计算非洲小计
function africaOne(){
	var africaOne=eval($("input[name='southAfricaOne']").val())+eval($("input[name='egyptOne']").val())+eval($("input[name='kenyaOne']").val())+eval($("input[name='africaOtherOne']").val());
	$("input[name='africaOne']").val(isNaN(africaOne) ? null : africaOne);
}

// 计算大洋洲小计
function totalOceaniaOne(){
	 var totalOceaniaOne=eval($("input[name='oceaniaOtherOne']").val())+eval($("input[name='zeaLandOne']").val())+eval($("input[name='australianOne']").val());
	 $("input[name='oceaniaTotalOne']").val(isNaN(totalOceaniaOne) ? null : totalOceaniaOne);
}

// 总计
function calcTotal(){
	var asiaTotalOne=eval($("input[name='hongKongOne']").val())+eval($("input[name='macauOne']").val())+eval($("input[name='taiWanOne']").val())+eval($("input[name='japanOne']").val())+eval($("input[name='koreaOne']").val())+eval($("input[name='mongoliaOne']").val())+eval($("input[name='indonesiaOne']").val())+eval($("input[name='malaysiaOne']").val())+eval($("input[name='philippinesOne']").val())+eval($("input[name='singaporeOne']").val())+eval($("input[name='thailandOne']").val())+eval($("input[name='indiaOne']").val())+eval($("input[name='vietnamOne']").val())+eval($("input[name='burmaOne']").val())+eval($("input[name='asianOtherOne']").val());
	var europeOne=eval($("input[name='italyOne']").val())+eval($("input[name='switzerLandOne']").val())+eval($("input[name='swedenOne']").val())+eval($("input[name='russiaOne']").val())+eval($("input[name='spainOne']").val())+eval($("input[name='holLandOne']").val())+eval($("input[name='denMarkOne']").val())+eval($("input[name='europeOtherOne']").val())+eval($("input[name='englishOne']").val())+eval($("input[name='franchOne']").val())+eval($("input[name='germanyOne']").val());
	var americaTotalOne=eval($("input[name='usOne']").val())+eval($("input[name='usOtherOne']").val())+eval($("input[name='canadaOne']").val());	
	var totalOceaniaOne=eval($("input[name='oceaniaOtherOne']").val())+eval($("input[name='zeaLandOne']").val())+eval($("input[name='australianOne']").val());
	var africaOne=eval($("input[name='southAfricaOne']").val())+eval($("input[name='egyptOne']").val())+eval($("input[name='kenyaOne']").val())+eval($("input[name='africaOtherOne']").val());
	
	$("input[name='outOne']").val(isNaN(asiaTotalOne+europeOne+americaTotalOne+totalOceaniaOne+africaOne) ? null : (asiaTotalOne+europeOne+americaTotalOne+totalOceaniaOne+africaOne));
}

function addSub(){
		asiaTotalOne();
		europeOne();
		americaTotalOne();
		africaOne();
		totalOceaniaOne();
		calcTotal();
	
	  if(eval($("input[name='outOne']").val())>eval($("input[name='outTwo']").val())){
			alert("人次数不能大于人天数");
			return false;
		}
}
</script>