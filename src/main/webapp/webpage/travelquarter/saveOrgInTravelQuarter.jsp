<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css"
	href="plug-in/style/yuebao/style.css">
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="margin-left: 38px;">
	<t:formvalid formid="formobj" refresh="false" dialog="true"
		action="travelQuarterController.do?saveOneByAdmin"
		usePlugin="password" layout="table" tiptype="1" callback="reloadTable"
		beforeSubmit="addSub()">
		<input name="travelid" type="hidden" value="${travelid}" />
		<input id="dataId" name="id" type="hidden" value="${quarteroneid}" />

		<!--  
<table width="640" cellpadding="0" cellspacing="0" style="border-top:1px solid #BDBDFF; border-right:1px solid #BDBDFF;">
-->
		<table id="tableReport" style="width: 750px; font-size: 12px;"
			cellpadding="0" cellspacing="1" class="formtable">
			<thead>
				<tr>
					<td width="90" colspan="2" style="text-align: center;">单位名称</td>
					<td width="40" colspan="3">
						<input type="text" id="Copname" name="name" class="inputSize"
							value="${travelData.name}" style="width: 280px;">
					</td>
					<td width="40" colspan="2" style="text-align: center;">许可证编号</td>
					<td width="44" colspan="3" style="text-align: left;">
						<input type="text" id="licenseNum" name="licensenum"
							class="inputSize" value="${travelData.licensenum}"
							style="width: 225px;">
					</td>
				</tr>
				<tr>
					<td width="90" colspan="2" style="text-align: center;">单位负责人</td>
					<td width="40" colspan="3">
						<input type="text" id="responsible" name="principal"
							class="inputSize" value="${travelData.principal}"
							style="width: 280px;">
					</td>
					<td width="40" colspan="2" style="text-align: center;">填报日期</td>
					<td width="44" colspan="3" style="text-align: left;">
						<input type="text" name="reportDate" class="easyui-datebox"
							value="${quarteroneData.reportDate}" style="width: 232px;">
					</td>
				</tr>
				<tr>
					<td width="90" colspan="2" style="text-align: center;">年份</td>
					<td width="40" colspan="3">
						<input type="text" name="year" readOnly="readOnly"
							class="inputSize" value="${quarteroneData.year}"
							style="width: 280px;">
					</td>
					<td width="40" colspan="2" style="text-align: center;">季度</td>
					<td width="44" colspan="3" style="text-align: left;">
						<select name="quarter" style="width: 232px"
							onfocus="this.defaultIndex=this.selectedIndex"
							onchange="this.selectedIndex=this.defaultIndex">
							<option value="1"
								<c:if test="${'1' eq quarteroneData.quarter }">selected</c:if>>第一季度</option>
							<option value="2"
								<c:if test="${'2' eq quarteroneData.quarter }">selected</c:if>>第二季度</option>
							<option value="3"
								<c:if test="${'3' eq quarteroneData.quarter }">selected</c:if>>第三季度</option>
							<option value="4"
								<c:if test="${'4' eq quarteroneData.quarter }">selected</c:if>>第四季度</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="90" colspan="2" style="text-align: center;">填表人</td>
					<td width="40" colspan="3">
						<input type="text" name="reportPerson" class="inputSize"
							value="${quarteroneData.reportPerson}" style="width: 280px;">
					</td>
					<td width="40" colspan="2" style="text-align: center;">填表人电话</td>
					<td width="44" colspan="3" style="text-align: left;">
						<input type="text" name="reportTelephone" class="inputSize"
							value="${quarteroneData.reportTelephone}" style="width: 225px;">
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="90" rowspan="3" colspan="2" style="text-align: center;">指&nbsp标&nbsp名&nbsp称</td>
					<td width="40" colspan="2" align="center" style="font-weight: 900;">人次数(人次)</td>
					<td width="40" colspan="2" align="center" style="font-weight: 900;">人天数(人天)</td>
					<td width="90" rowspan="3" style="text-align: center;" colspan="2">指&nbsp标&nbsp名&nbsp称</td>
					<td width="40" colspan="2" align="center" style="font-weight: 900;">人次数(人次)</td>
				</tr>
				<tr>
					<td width="40" colspan="2" align="center" style="font-weight: 900;">本季</td>
					<td width="40" colspan="2" align="center" style="font-weight: 900;">本季</td>
					<td width="40" colspan="2" align="center" style="font-weight: 900;">本季</td>
				</tr>
				<tr>
					<td width="40" align="center" style="font-weight: 900;">外联</td>
					<td width="40" align="center" style="font-weight: 900;">接待</td>
					<td width="40" align="center" style="font-weight: 900;">外联</td>
					<td width="40" align="center" style="font-weight: 900;">接待</td>
					<td width="40" align="center" style="font-weight: 900;">外联</td>
					<td width="40" align="center" style="font-weight: 900;">接待</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">入境游客</td>
					<td width="90">
						<input type="text" datatype="n" id="inGuestOne"
							value="${quarteroneData.inGuestOne}" readonly="readonly"
							style="background: #FAFAFA" name="inGuestOne" class="inputSize"
							style="width: 90px" style="width:90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="inGuestTwo"
							value="${quarteroneData.inGuestTwo}" readonly="readonly"
							style="background: #FAFAFA" name="inGuestTwo" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="inGuestThree"
							value="${quarteroneData.inGuestThree}" readonly="readonly"
							style="width: 140px; background: #FAFAFA" name="inGuestThree"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="inGuestFour"
							value="${quarteroneData.inGuestFour}" readonly="readonly"
							style="width: 140px; background: #FAFAFA" name="inGuestFour"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">英国</td>
					<td width="90">
						<input type="text" datatype="n" id="englandOne"
							value="${quarteroneData.englandOne}" name="englandOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="englandTwo"
							value="${quarteroneData.englandTwo}" name="englandTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">香港同胞</td>
					<td width="90">
						<input type="text" datatype="n" id="hkComOne"
							value="${quarteroneData.hkComOne}" name="hkComOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="hkComTwo"
							value="${quarteroneData.hkComTwo}" name="hkComTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="hkComThree"
							value="${quarteroneData.hkComThree}" name="hkComThree"
							style="width: 140px" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="hkComFour"
							value="${quarteroneData.hkComFour}" name="hkComFour"
							style="width: 140px" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">法国</td>
					<td width="90">
						<input type="text" datatype="n" id="frenchOne"
							value="${quarteroneData.frenchOne}" name="frenchOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="frenchTwo"
							value="${quarteroneData.frenchTwo}" name="frenchTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">澳门同胞</td>
					<td width="90">
						<input type="text" datatype="n" id="mcComOne"
							value="${quarteroneData.mcComOne}" name="mcComOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="mcComTwo"
							value="${quarteroneData.mcComTwo}" name="mcComTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="mcComThree"
							style="width: 140px" value="${quarteroneData.mcComThree}"
							name="mcComThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="mcComFour"
							style="width: 140px" value="${quarteroneData.mcComFour}"
							name="mcComFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">德国</td>
					<td width="90">
						<input type="text" datatype="n" id="germanyOne"
							value="${quarteroneData.germanyOne}" name="germanyOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="germanyTwo"
							value="${quarteroneData.germanyTwo}" name="germanyTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">台湾同胞</td>
					<td width="90">
						<input type="text" datatype="n" id="twComOne"
							value="${quarteroneData.twComOne}" name="twComOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="twComTwo"
							value="${quarteroneData.twComTwo}" name="twComTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="twComThree"
							value="${quarteroneData.twComThree}" style="width: 140px"
							name="twComThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="twComFour"
							value="${quarteroneData.twComFour}" style="width: 140px"
							name="twComFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">意大利</td>
					<td width="90">
						<input type="text" datatype="n" id="italyOne"
							value="${quarteroneData.italyOne}" name="italyOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="italyTwo"
							value="${quarteroneData.italyTwo}" name="italyTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">外国人</td>
					<td width="90">
						<input type="text" datatype="n" id="foreignersOne"
							value="${quarteroneData.foreignersOne}" readonly="readonly"
							name="foreignersOne" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="foreignersTwo"
							value="${quarteroneData.foreignersTwo}" readonly="readonly"
							name="foreignersTwo" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="foreignersThree"
							value="${quarteroneData.foreignersThree}" style="width: 140px"
							name="foreignersThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="foreignersFour"
							value="${quarteroneData.foreignersFour}" style="width: 140px"
							name="foreignersFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">瑞士</td>
					<td width="90">
						<input type="text" datatype="n" id="swissOne"
							value="${quarteroneData.swissOne}" name="swissOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="swissTwo"
							value="${quarteroneData.swissTwo}" name="swissTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
				<tr>
					<td width="90" align="center" colspan="2">亚洲小计</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="asiaTotalOne" value="${quarteroneData.asiaTotalOne}"
							readonly="readonly" name="asiaTotalOne" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" readonly="readonly"
							id="asiaTotalTwo" value="${quarteroneData.asiaTotalTwo}"
							style="background: #FAFAFA" name="asiaTotalTwo" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" readonly="readonly"
							style="visibility: hidden" id="asiaTotalThree"
							value="${quarteroneData.asiaTotalThree}" name="asiaTotalThree"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" readonly="readonly"
							style="visibility: hidden" id="asiaTotalFour"
							value="${quarteroneData.asiaTotalFour}" name="asiaTotalFour"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">瑞典</td>
					<td width="90">
						<input type="text" datatype="n" id="swedishOne"
							value="${quarteroneData.swedishOne}" name="swedishOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="swedishTwo"
							value="${quarteroneData.swedishTwo}" name="swedishTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
				<tr>
					<td width="90" align="center" colspan="2">日本</td>
					<td width="90">
						<input type="text" datatype="n" id="japanOne"
							value="${quarteroneData.japanOne}" name="japanOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="japanTwo"
							value="${quarteroneData.japanTwo}" name="japanTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" style="visibility: hidden"
							id="japanThree" value="${quarteroneData.japanThree}"
							name="japanThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" style="visibility: hidden"
							id="japanFour" value="${quarteroneData.japanFour}"
							name="japanFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">俄罗斯</td>
					<td width="90">
						<input type="text" datatype="n" id="russiaOne"
							value="${quarteroneData.russiaOne}" name="russiaOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="russiaTwo"
							value="${quarteroneData.russiaTwo}" name="russiaTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">韩国</td>
					<td width="90">
						<input type="text" datatype="n" id="koreaOne"
							value="${quarteroneData.koreaOne}" name="koreaOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="koreaTwo"
							value="${quarteroneData.koreaTwo}" name="koreaTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="koreaThree"
							style="visibility: hidden" value="${quarteroneData.koreaThree}"
							name="koreaThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="koreaFour"
							style="visibility: hidden" value="${quarteroneData.koreaFour}"
							name="koreaFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">西班牙</td>
					<td width="90">
						<input type="text" datatype="n" id="spainOne"
							value="${quarteroneData.spainOne}" name="spainOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="spainTwo"
							value="${quarteroneData.spainTwo}" name="spainTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<td width="90" align="center" colspan="2">蒙古</td>
				<td width="90">
					<input type="text" datatype="n" id="mongoOne"
						value="${quarteroneData.mongoOne}" name="mongoOne"
						class="inputSize" style="width: 90px" />
				</td>
				<td width="90">
					<input type="text" datatype="n" id="mongoTwo"
						value="${quarteroneData.mongoTwo}" name="mongoTwo"
						class="inputSize" style="width: 90px" />
				</td>
				<td width="90">
					<input type="text" datatype="n" id="mongoThree"
						value="${quarteroneData.mongoThree}" style="visibility: hidden"
						name="mongoThree" class="inputSize" style="width: 90px" />
				</td>
				<td width="90">
					<input type="text" datatype="n" id="mongoFour"
						value="${quarteroneData.mongoFour}" style="visibility: hidden"
						name="mongoFour" class="inputSize" style="width: 90px" />
				</td>
				<td width="90" align="center" colspan="2">欧洲其他</td>
				<td width="90">
					<input type="text" datatype="n" id="otherEuropeanOne"
						value="${quarteroneData.otherEuropeanOne}" name="otherEuropeanOne"
						class="inputSize" style="width: 90px" />
				</td>
				<td width="90">
					<input type="text" datatype="n" id="otherEuropeanTwo"
						value="${quarteroneData.otherEuropeanTwo}" name="otherEuropeanTwo"
						class="inputSize" style="width: 90px" />
				</td>
				</tr>

				<tr>
					<td width="90" align="center" colspan="2">印度尼西亚</td>
					<td width="90">
						<input type="text" datatype="n" id="indonxyOne"
							value="${quarteroneData.indonxyOne}" name="indonxyOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="indonxyTwo"
							value="${quarteroneData.indonxyTwo}" name="indonxyTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="indonxyThree"
							value="${quarteroneData.indonxyThree}" style="visibility: hidden"
							name="indonxyThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="indonxyFour"
							value="${quarteroneData.indonxyFour}" style="visibility: hidden"
							name="indonxyFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">美洲小计</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="totalAmericaOne" value="${quarteroneData.totalAmericaOne}"
							readonly="readonly" name="totalAmericaOne" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="totalAmericaTwo" value="${quarteroneData.totalAmericaTwo}"
							readonly="readonly" name="totalAmericaTwo" class="inputSize"
							style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">马来西亚</td>
					<td width="90">
						<input type="text" datatype="n" id="malaxyOne"
							value="${quarteroneData.malaxyOne}" name="malaxyOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="malaxyTwo"
							value="${quarteroneData.malaxyTwo}" name="malaxyTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="malaxyThree"
							value="${quarteroneData.malaxyThree}" style="visibility: hidden"
							name="malaxyThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="malaxyFour"
							value="${quarteroneData.malaxyFour}" style="visibility: hidden"
							name="malaxyFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">美国</td>
					<td width="90">
						<input type="text" datatype="n" id="usOne"
							value="${quarteroneData.usOne}" name="usOne" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="usTwo"
							value="${quarteroneData.usTwo}" name="usTwo" class="inputSize"
							style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">菲律宾</td>
					<td width="90">
						<input type="text" datatype="n" id="philipnOne"
							value="${quarteroneData.philipnOne}" name="philipnOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="philipnTwo"
							value="${quarteroneData.philipnTwo}" name="philipnTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="philipnThree"
							value="${quarteroneData.philipnThree}" style="visibility: hidden"
							name="philipnThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="philipnFour"
							value="${quarteroneData.philipnFour}" style="visibility: hidden"
							name="philipnFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">加拿大</td>
					<td width="90">
						<input type="text" datatype="n" id="canadaOne"
							value="${quarteroneData.canadaOne}" name="canadaOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="canadaTwo"
							value="${quarteroneData.canadaTwo}" name="canadaTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">新加坡</td>
					<td width="90">
						<input type="text" datatype="n" id="singaporeOne"
							value="${quarteroneData.singaporeOne}" name="singaporeOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="singaporeTwo"
							value="${quarteroneData.singaporeTwo}" name="singaporeTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="singaporeThree"
							value="${quarteroneData.singaporeThree}"
							style="visibility: hidden" name="singaporeThree"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="singaporeFour"
							value="${quarteroneData.singaporeFour}"
							style="visibility: hidden" name="singaporeFour" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">美洲其他</td>
					<td width="90">
						<input type="text" datatype="n" id="otherAmericanOne"
							value="${quarteroneData.otherAmericanOne}"
							name="otherAmericanOne" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="otherAmericanTwo"
							value="${quarteroneData.otherAmericanTwo}"
							name="otherAmericanTwo" class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">泰国</td>
					<td width="90">
						<input type="text" datatype="n" id="tailandOne"
							value="${quarteroneData.tailandOne}" name="tailandOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="tailandTwo"
							value="${quarteroneData.tailandTwo}" name="tailandTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="tailandThree"
							value="${quarteroneData.tailandThree}" style="visibility: hidden"
							name="tailandThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="tailandFour"
							value="${quarteroneData.tailandFour}" style="visibility: hidden"
							name="tailandFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">大洋洲小计</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="totalOceaniaOne" value="${quarteroneData.totalOceaniaOne}"
							readonly="readonly" name="totalOceaniaOne" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="totalOceaniaTwo" value="${quarteroneData.totalOceaniaTwo}"
							readonly="readonly" name="totalOceaniaTwo" class="inputSize"
							style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">印度</td>
					<td width="90">
						<input type="text" datatype="n" id="indiaOne"
							value="${quarteroneData.indiaOne}" name="indiaOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="indiaTwo"
							value="${quarteroneData.indiaTwo}" name="indiaTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="indiaThree"
							value="${quarteroneData.indiaThree}" style="visibility: hidden"
							name="indiaThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="indiaFour"
							value="${quarteroneData.indiaFour}" style="visibility: hidden"
							name="indiaFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">澳大利亚</td>
					<td width="90">
						<input type="text" datatype="n" id="australiaOne"
							value="${quarteroneData.australiaOne}" name="australiaOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="australiaTwo"
							value="${quarteroneData.australiaTwo}" name="australiaTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">越南</td>
					<td width="90">
						<input type="text" datatype="n" id="vietnamOne"
							value="${quarteroneData.vietnamOne}" name="vietnamOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="vietnamTwo"
							value="${quarteroneData.vietnamTwo}" name="vietnamTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="vietnamThree"
							value="${quarteroneData.vietnamThree}" style="visibility: hidden"
							name="vietnamThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="vietnamFour"
							value="${quarteroneData.vietnamFour}" style="visibility: hidden"
							name="vietnamFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">新西兰</td>
					<td width="90">
						<input type="text" datatype="n" id="newlandOne"
							value="${quarteroneData.newlandOne}" name="newlandOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="newlandTwo"
							value="${quarteroneData.newlandTwo}" name="newlandTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<!-- added by Fp -->
				<tr>
					<td width="90" align="center" colspan="2">缅甸</td>
					<td width="90">
						<input type="text" datatype="n" id="myanamarOne"
							value="${quarteroneData.myanamarOne}" name="myanamarOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="myanamarTwo"
							value="${quarteroneData.myanamarTwo}" name="myanamarTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="hollandThree"
							value="${quarteroneData.hollandThree}" style="visibility: hidden"
							name="hollandThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="hollandFour"
							value="${quarteroneData.hollandFour}" style="visibility: hidden"
							name="hollandFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">大洋洲其他</td>
					<td width="90">
						<input type="text" datatype="n" id="otherOceaniaOne"
							value="${quarteroneData.otherOceaniaOne}" name="otherOceaniaOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="otherOceaniaTwo"
							value="${quarteroneData.otherOceaniaTwo}" name="otherOceaniaTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">肯尼亚</td>
					<td width="90">
						<input type="text" datatype="n" id="kenyaOne"
							value="${quarteroneData.kenyaOne}" name="kenyaOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="kenyaTwo"
							value="${quarteroneData.kenyaTwo}" name="kenyaTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="southafricaThree"
							value="${quarteroneData.southafricaThree}"
							style="visibility: hidden" name="southafricaThree"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="southafricaFour"
							value="${quarteroneData.southafricaFour}"
							style="visibility: hidden" name="southafricaFour"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">非洲小计</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="totalAfricaOne" value="${quarteroneData.totalAfricaOne}"
							readonly="readonly" name="totalAfricaOne" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="totalAfricaTwo" value="${quarteroneData.totalAfricaTwo}"
							readonly="readonly" name="totalAfricaTwo" class="inputSize"
							style="width: 90px" />
					</td>
				<tr>
					<td width="90" align="center" colspan="2">亚洲其他</td>
					<td width="90">
						<input type="text" datatype="n" id="otherAsianOne"
							value="${quarteroneData.otherAsianOne}" name="otherAsianOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="otherAsianTwo"
							value="${quarteroneData.otherAsianTwo}" name="otherAsianTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="myanamarThree"
							value="${quarteroneData.myanamarThree}"
							style="visibility: hidden" name="myanamarThree" class="inputSize"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="myanamarFour"
							value="${quarteroneData.myanamarFour}" style="visibility: hidden"
							name="myanamarFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">南非</td>
					<td width="90">
						<input type="text" datatype="n" id="denmarkOne"
							value="${quarteroneData.southafricaOne}" name="southafricaOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="denmarkTwo"
							value="${quarteroneData.southafricaTwo}" name="southafricaTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">欧洲小计</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="totalEropeanOne" value="${quarteroneData.totalEropeanOne}"
							readonly="readonly" name="totalEropeanOne" class="europeTotal"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" style="background: #FAFAFA"
							id="totalEropeanTwo" value="${quarteroneData.totalEropeanTwo}"
							readonly="readonly" name="totalEropeanTwo" class="europeTotal"
							style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="otherAsianThree"
							value="${quarteroneData.otherAsianThree}"
							style="visibility: hidden" name="otherAsianThree"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="otherAsianFour"
							value="${quarteroneData.otherAsianFour}"
							style="visibility: hidden" name="otherAsianFour"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">埃及</td>
					<td width="90">
						<input type="text" datatype="n" id="egyptOne"
							value="${quarteroneData.egyptOne}" name="egyptOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="egyptTwo"
							value="${quarteroneData.egyptTwo}" name="egyptTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">丹麦</td>
					<td width="90">
						<input type="text" datatype="n" id="southafricaOne"
							value="${quarteroneData.denmarkOne}" name="denmarkOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="southafricaTwo"
							value="${quarteroneData.denmarkTwo}" name="denmarkTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="kenyaThree"
							value="${quarteroneData.denmarkThree}" style="visibility: hidden"
							name="kenyaThree" class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="kenyaFour"
							value="${quarteroneData.denmarkFour}" style="visibility: hidden"
							name="kenyaFour" class="inputSize" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">非洲其他</td>
					<td width="90">
						<input type="text" datatype="n" id="africaotherOne"
							value="${quarteroneData.africaotherOne}" name="africaotherOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="africaotherTwo"
							value="${quarteroneData.africaotherTwo}" name="africaotherTwo"
							class="inputSize" style="width: 90px" />
					</td>
				</tr>
				<tr>
					<td width="90" align="center" colspan="2">荷兰</td>
					<td width="90">
						<input type="text" datatype="n" id="hollandOne"
							value="${quarteroneData.hollandOne}" name="hollandOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="hollandTwo"
							value="${quarteroneData.hollandTwo}" name="hollandTwo"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="totalEropeanThree"
							value="${quarteroneData.totalEropeanThree}" readonly="readonly"
							style="visibility: hidden" name="totalEropeanThree"
							class="europeTotal" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="totalEropeanFour"
							value="${quarteroneData.totalEropeanFour}" readonly="readonly"
							style="visibility: hidden" name="totalEropeanFour"
							class="europeTotal" style="width: 90px" />
					</td>
					<td width="90" align="center" colspan="2">其他小计</td>
					<td width="90">
						<input type="text" datatype="n" id="totalOtherOne"
							value="${quarteroneData.totalOtherOne}" name="totalOtherOne"
							class="inputSize" style="width: 90px" />
					</td>
					<td width="90">
						<input type="text" datatype="n" id="totalOtherTwo"
							value="${quarteroneData.totalOtherTwo}" name="totalOtherTwo"
							class="inputSize" style="width: 90px" />
					</td>
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
}, 2000);

</script>
<script>
// 计算亚洲人外联次数
function calcAsiaTotalOne(){
	var asiaTotalOne=eval($("input[name='japanOne']").val())+eval($("input[name='koreaOne']").val())+eval($("input[name='mongoOne']").val())+eval($("input[name='indonxyOne']").val())+eval($("input[name='malaxyOne']").val())+eval($("input[name='philipnOne']").val())+eval($("input[name='singaporeOne']").val())+eval($("input[name='tailandOne']").val())+eval($("input[name='indiaOne']").val())+eval($("input[name='vietnamOne']").val())+eval($("input[name='myanamarOne']").val())+eval($("input[name='otherAsianOne']").val());
	$("input[name='asiaTotalOne']").val(isNaN(asiaTotalOne) ? null : asiaTotalOne);
}

// 计算欧洲外联人次数
function calcTotalEropeanOne(){
	var  totalEropeanOne=eval($("input[name='englandOne']").val())+eval($("input[name='frenchOne']").val())+eval($("input[name='germanyOne']").val())+eval($("input[name='italyOne']").val())+eval($("input[name='swissOne']").val())+eval($("input[name='swedishOne']").val())+eval($("input[name='russiaOne']").val())+eval($("input[name='spainOne']").val())+eval($("input[name='otherEuropeanOne']").val())+eval($("input[name='hollandOne']").val())+eval($("input[name='denmarkOne']").val());
	$("input[name='totalEropeanOne']").val(isNaN(totalEropeanOne) ? null : totalEropeanOne);
}

// 计算美洲外联人次数
function calcTotalAmericaOne(){
	var totalAmericaOne=eval($("input[name='usOne']").val())+eval($("input[name='canadaOne']").val())+eval($("input[name='otherAmericanOne']").val());
	$("input[name='totalAmericaOne']").val(isNaN(totalAmericaOne) ? null : totalAmericaOne);
}

// 计算大洋洲外联人次数
function calcTotalOceaniaOne(){
	var totalOceaniaOne=eval($("input[name='australiaOne']").val())+eval($("input[name='newlandOne']").val())+eval($("input[name='otherOceaniaOne']").val());
	$("input[name='totalOceaniaOne']").val(isNaN(totalOceaniaOne) ? null : totalOceaniaOne);
}

// 计算非洲外联人次数
function calcTotalAfricaOne(){
	var totalAfricaOne=eval($("input[name='africaotherOne']").val())+eval($("input[name='kenyaOne']").val())+eval($("input[name='egyptOne']").val())+eval($("input[name='southafricaOne']").val());
	$("input[name='totalAfricaOne']").val(isNaN(totalAfricaOne) ? null : totalAfricaOne);
}

// 计算亚洲接待人次数
function calcAsiaTotalTwo(){
	var asiaTotalTwo=eval($("input[name='japanTwo']").val())+eval($("input[name='koreaTwo']").val())+eval($("input[name='mongoTwo']").val())+eval($("input[name='indonxyTwo']").val())+eval($("input[name='malaxyTwo']").val())+eval($("input[name='philipnTwo']").val())+eval($("input[name='singaporeTwo']").val())+eval($("input[name='tailandTwo']").val())+eval($("input[name='indiaTwo']").val())+eval($("input[name='vietnamTwo']").val())+eval($("input[name='myanamarTwo']").val())+eval($("input[name='otherAsianTwo']").val());
	$("input[name='asiaTotalTwo']").val(isNaN(asiaTotalTwo) ? null : asiaTotalTwo);
}

// 计算欧洲接待人次数
function calcTotalEropeanTwo(){
	var  totalEropeanTwo=eval($("input[name='englandTwo']").val())+eval($("input[name='frenchTwo']").val())+eval($("input[name='germanyTwo']").val())+eval($("input[name='italyTwo']").val())+eval($("input[name='swissTwo']").val())+eval($("input[name='swedishTwo']").val())+eval($("input[name='russiaTwo']").val())+eval($("input[name='spainTwo']").val())+eval($("input[name='otherEuropeanTwo']").val())+eval($("input[name='hollandTwo']").val())+eval($("input[name='denmarkTwo']").val());
	$("input[name='totalEropeanTwo']").val(isNaN(totalEropeanTwo) ? null : totalEropeanTwo);
}

// 计算美洲接待人次数
function calcTotalAmericaTwo(){
	var totalAmericaTwo=eval($("input[name='usTwo']").val())+eval($("input[name='canadaTwo']").val())+eval($("input[name='otherAmericanTwo']").val());
	$("input[name='totalAmericaTwo']").val(isNaN(totalAmericaTwo) ? null : totalAmericaTwo);
}

// 计算大洋洲接待人次数
function calcTotalOceaniaTwo(){
	var totalOceaniaTwo=eval($("input[name='australiaTwo']").val())+eval($("input[name='newlandTwo']").val())+eval($("input[name='otherOceaniaTwo']").val());
	$("input[name='totalOceaniaTwo']").val(isNaN(totalOceaniaTwo) ? null : totalOceaniaTwo);
}

// 计算非洲接待人次数
function calcTotalAfricaTwo(){
	var totalAfricaTwo=eval($("input[name='africaotherTwo']").val())+eval($("input[name='kenyaTwo']").val())+eval($("input[name='egyptTwo']").val())+eval($("input[name='southafricaTwo']").val());
	$("input[name='totalAfricaTwo']").val(isNaN(totalAfricaTwo) ? null : totalAfricaTwo);
}

// 计算外国人总数
function foreignersOne(){
	var foreignersOne=eval($("input[name='asiaTotalOne']").val())+eval($("input[name='totalEropeanOne']").val())+eval($("input[name='totalAmericaOne']").val())+eval($("input[name='totalOceaniaOne']").val())+eval($("input[name='totalAfricaOne']").val())+eval($("input[name='totalOtherOne']").val());
	$("input[name='foreignersOne']").val(isNaN(foreignersOne) ? null : foreignersOne);
}
function foreignersTwo(){
	var foreignersTwo=eval($("input[name='asiaTotalTwo']").val())+eval($("input[name='totalEropeanTwo']").val())+eval($("input[name='totalAmericaTwo']").val())+eval($("input[name='totalOceaniaTwo']").val())+eval($("input[name='totalAfricaTwo']").val())+eval($("input[name='totalOtherTwo']").val());
	$("input[name='foreignersTwo']").val(isNaN(foreignersTwo) ? null : foreignersTwo);
}

// 计算人次总数
function inGuestOne(){
	var inGuestOne=eval($("input[name='foreignersOne']").val())+eval($("input[name='hkComOne']").val())+eval($("input[name='mcComOne']").val())+eval($("input[name='twComOne']").val())+eval($("input[name='totalOtherOne']").val());
	$("input[name='inGuestOne']").val(isNaN(inGuestOne) ? null : inGuestOne);
}
function inGuestTwo(){
	var inGuestTwo=eval($("input[name='foreignersTwo']").val())+eval($("input[name='hkComTwo']").val())+eval($("input[name='mcComTwo']").val())+eval($("input[name='twComTwo']").val())+eval($("input[name='totalOtherTwo']").val());
	$("input[name='inGuestTwo']").val(isNaN(inGuestTwo) ? null : inGuestTwo);
}

function inGuestThree(){
	var inGuestThree=eval($("input[name='foreignersThree']").val())+eval($("input[name='hkComThree']").val())+eval($("input[name='mcComThree']").val())+eval($("input[name='twComThree']").val());
	$("input[name='inGuestThree']").val(isNaN(inGuestThree) ? null : inGuestThree);
}
function inGuestFour(){
	var inGuestFour=eval($("input[name='foreignersFour']").val())+eval($("input[name='hkComFour']").val())+eval($("input[name='mcComFour']").val())+eval($("input[name='twComFour']").val());
	$("input[name='inGuestFour']").val(isNaN(inGuestFour) ? null : inGuestFour);
}

window.setInterval(function(){
	calcAsiaTotalOne();
	calcTotalEropeanOne();
	calcTotalAmericaOne();
	calcTotalOceaniaOne();
	calcTotalAfricaOne();
	calcAsiaTotalTwo();
	calcTotalEropeanTwo();
	calcTotalAmericaTwo();
	calcTotalOceaniaTwo();
	calcTotalAfricaTwo();
	
	foreignersOne();
	foreignersTwo();
	
	inGuestOne();
	inGuestTwo();
	inGuestThree();
	inGuestFour();
},1000);


function addSub(){
	calcAsiaTotalOne();
	calcTotalEropeanOne();
	calcTotalAmericaOne();
	calcTotalOceaniaOne();
	calcTotalAfricaOne();
	calcAsiaTotalTwo();
	calcTotalEropeanTwo();
	calcTotalAmericaTwo();
	calcTotalOceaniaTwo();
	calcTotalAfricaTwo();
	
	foreignersOne();
	foreignersTwo();
	
	inGuestOne();
	inGuestTwo();
	inGuestThree();
	inGuestFour();

	// 校验人次是否小于人天
	 if(eval($("input[name='hongKongOne']").val())>eval($("input[name='hongKongThree']").val())){
			alert("人次数不能大于人天数");
			return false;
		}   if(eval($("input[name='macauOne']").val())>eval($("input[name='macauThree']").val())){
			alert("人次数不能大于人天数");
			return false;
		}   if(eval($("input[name='taiWanOne']").val())>eval($("input[name='taiWanThree']").val())){
			alert("人次数不能大于人天数");
			return false;
		}	if(eval($("input[name='foreignersOne']").val())>eval($("input[name='foreignersThree']").val())){
			alert("人次数不能大于人天数");
			return false;
		}	
	if(eval($("input[name='hongKongTwo']").val())>eval($("input[name='hongKongFour']").val())){
				alert("人次数不能大于人天数");
				return false;
			}   if(eval($("input[name='macauTwo']").val())>eval($("input[name='macauFour']").val())){
				alert("人次数不能大于人天数");
				return false;
			}   if(eval($("input[name='taiWanTwo']").val())>eval($("input[name='taiWanFour']").val())){
				alert("人次数不能大于人天数");
				return false;
			} 	if(eval($("input[name='foreignersTwo']").val())>eval($("input[name='foreignersFour']").val())){
			alert("人次数不能大于人天数");
			return false;	 
}
}

</script>