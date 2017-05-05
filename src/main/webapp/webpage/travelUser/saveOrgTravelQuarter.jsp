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
		action="travelQuarterController.do?saveThree" usePlugin="password"
		layout="table" tiptype="1" callback="reloadTable"
		beforeSubmit="addSub()">
		<!-- 隐藏参数 -->
		<input name="travelid" type="hidden" value="${travelid}" />
		<input id="dataId" name="id" type="hidden"
			value="${quarterthreeData.id}" />
		<input name="tid" type="hidden" value="${quarterthreeData.tid}" />
		<input type="hidden" value="1" name="isUser">
		<table id="tableReport" style="width: 750px; font-size: 12px;"
			cellpadding="0" cellspacing="1" class="formtable">
			<thead>
				<tr>
					<td width="156" style="background: none; text-align: center;">单位名称</td>
					<td width="160" colspan="2">
						<input type="text" readOnly="readOnly" name="name"
							class="inputSize" value="${travelData.name}" />
					</td>
					<td width="159" colspan="1" style="text-align: center;">许可证编号</td>
					<td width="160" style="background: none; text-align: left;">
						<input type="text" id="licenseNum" name="licensenum"
							readOnly="readOnly" value="${travelData.licensenum}" />
					</td>
				</tr>
				<tr>
					<td width="156" style="background: none; text-align: center;">单位负责人</td>
					<td width="160" colspan="2">
						<input type="text" id="responsible" name="principal"
							readOnly="readOnly" value="${travelData.principal}" />
					</td>
					<td width="159" colspan="1" style="text-align: center;">填报日期</td>
					<td width="160" style="background: none; text-align: left;">
						<input type="text" name="reportDate" class="easyui-datebox"
							value="${quarterthreeData.reportDate}" style="width: 146px;">
					</td>
				</tr>
				<tr>
					<td width="156" style="text-align: center;">年份</td>
					<td width="160" colspan="2">
						<input type="text" name="year" readOnly="readOnly"
							class="inputSize" value="${quarterthreeData.year}"
							style="width: 140px;">
					</td>
					<td width="159" style="text-align: center;">季度</td>
					<td width="160" style="text-align: left;">
						<select name="quarter" style="width: 146px"
							onfocus="this.defaultIndex=this.selectedIndex"
							onchange="this.selectedIndex=this.defaultIndex">
							<option value="1"
								<c:if test="${'1' eq quarterthreeData.quarter }">selected</c:if>>第一季度</option>
							<option value="2"
								<c:if test="${'2' eq quarterthreeData.quarter }">selected</c:if>>第二季度</option>
							<option value="3"
								<c:if test="${'3' eq quarterthreeData.quarter }">selected</c:if>>第三季度</option>
							<option value="4"
								<c:if test="${'4' eq quarterthreeData.quarter }">selected</c:if>>第四季度</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="156" style="background: none; text-align: center;">填表人</td>
					<td width="160" colspan="2">
						<input type="text" name="reportPerson" class="inputSize"
							value="${quarterthreeData.reportPerson }" />
					</td>
					<td width="159" colspan="1" style="text-align: center;">填表人电话</td>
					<td width="160" style="background: none; text-align: left;">
						<input type="text" name="reportTelephone" class="inputSize"
							value="${quarterthreeData.reportTelephone }" />
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="112" style="text-align: center;">指标名称</td>
					<td width="100" align="center" style="font-weight: 900;">组织(人次)</td>
					<td width="100" align="center" style="font-weight: 900;">接待(人次)</td>
					<td width="115" align="center" style="font-weight: 900;">组织(人天)</td>
					<td width="164" align="center" style="font-weight: 900;">接待(人天)</td>
				</tr>
				<tr>
					<td width="112">合计</td>
					<!-- <td width="44" align="center">01</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal" style="background: #FAFAFA"
							value="${quarterthreeData.totalOne}" name="totalOne"
							readonly="readonly" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal" style="background: #FAFAFA"
							value="${quarterthreeData.totalTwo}" name="totalTwo"
							readonly="readonly" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal" style="background: #FAFAFA"
							value="${quarterthreeData.totalThree}" name="totalThree"
							readonly="readonly" class="inputSize" />
					</td>
					<td width="164">
						<input type="text" id="endOfAssetbal" style="background: #FAFAFA"
							value="${quarterthreeData.totalFour}" name="totalFour"
							readonly="readonly" class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">北京</td>
					<!-- <td width="44" align="center">02</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.bjOne}" name="bjOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.bjTwo}" name="bjTwo" class="inputSize" />
					</td>

					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.bjThree}" name="bjThree"
							class="inputSize" />
					</td>

					<td width="164">
						<input type="text" id="exitProfit"
							value="${quarterthreeData.bjFour}" name="bjFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">天津</td>
					<!-- <td width="44" align="center">03</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.tjOne}" name="tjOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.tjTwo}" name="tjTwo" class="inputSize">
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.tjThree}" name="tjThree"
							class="inputSize" />
					</td>

					<td width="164">
						<input type="text" id="enterProfit"
							value="${quarterthreeData.tjFour}" name="tjFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">河北</td>
					<!-- <td width="44" align="center">04</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hbOne}" name="hbOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hbTwo}" name="hbTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hbThree}" name="hbThree"
							class="inputSize" />
					</td>

					<td width="164">
						<input type="text" id="manageCost"
							value="${quarterthreeData.hbFour}" name="hbFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">山西</td>
					<!-- <td width="44" align="center">05</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.sxOne}" name="sxOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.sxTwo}" name="sxTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.sxThree}" name="sxThree"
							class="inputSize" />
					</td>
					<td width="160">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.sxFour }" name="sxFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">内蒙古</td>
					<!-- <td width="44" align="center">06</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nmgOne}" name="nmgOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nmgTwo}" name="nmgTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nmgThree}" name="nmgThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="travelExpense"
							value="${quarterthreeData.nmgFour}" name="nmgFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">辽宁</td>
					<!-- <td width="44" align="center">07</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.lnOne}" name="lnOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.lnTwo}" name="lnTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.lnThree}" name="lnThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="financialCost"
							value="${quarterthreeData.lnFour}" name="lnFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">吉林</td>
					<!-- <td width="44" align="center">08</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jlOne}" name="jlOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jlTwo}" name="jlTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jlThree}" name="jlThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="interestPay"
							value="${quarterthreeData.jlFour}" name="jlFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">黑龙江</td>
					<!-- <td width="44" align="center">09</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hljOne}" name="hljOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hljTwo}" name="hljTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hljThree}" name="hljThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="fairValue"
							value="${quarterthreeData.hljFour}" name="hljFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">上海</td>
					<!-- <td width="44" align="center">10</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.shOne}" name="shOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.shTwo}" name="shTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.shThree}" name="shThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="fairValue"
							value="${quarterthreeData.shFour}" name="shFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">江苏</td>
					<!-- <td width="44" align="center">11</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jsOne}" name="jsOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jsTwo}" name="jsTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jsThree}" name="jsThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="fairValue"
							value="${quarterthreeData.jsFour}" name="jsFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">浙江</td>
					<!-- <td width="44" align="center">12</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.zjOne}" name="zjOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.zjTwo}" name="zjTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.zjThree}" name="zjThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="totalProfit"
							value="${quarterthreeData.zjFour}" name="zjFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">安徽</td>
					<!-- <td width="44" align="center">13</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.ahOne}" name="ahOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.ahTwo}" name="ahTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.ahThree}" name="ahThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="incomeTax"
							value="${quarterthreeData.ahFour}" name="ahFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">福建</td>
					<!-- <td width="44" align="center">14</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.fjOne}" name="fjOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.fjTwo}" name="fjTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.fjThree}" name="fjThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="welfare" value="${quarterthreeData.fjFour}"
							name="fjFour" class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">江西</td>
					<!-- <td width="44" align="center">15</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jxOne}" name="jxOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jxTwo}" name="jxTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.jxThree}" name="jxThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="payrolls"
							value="${quarterthreeData.jxFour}" name="jxFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">山东</td>
					<!-- <td width="44" align="center">16</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.sdOne}" name="sdOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.sdTwo}" name="sdTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.sdThree}" name="sdThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="aveNumOfEmp"
							value="${quarterthreeData.sdFour}" name="sdFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">河南</td>
					<!-- <td width="44" align="center">17</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.henanOne}" name="henanOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.henanTwo}" name="henanTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.henanThree}" name="henanThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.henanFour}" name="henanFour"
							class="inputSize" />
					</td>
				</tr>




				<tr>
					<td width="112">湖北</td>
					<!-- <td width="44" align="center">18</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hubeiOne}" name="hubeiOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hubeiTwo}" name="hubeiTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hubeiThree}" name="hubeiThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.hubeiFour}" name="hubeiFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">湖南</td>
					<!-- <td width="44" align="center">19</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hlOne}" name="hlOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hlTwo}" name="hlTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hlThree}" name="hlThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.hlFour}" name="hlFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">广东</td>
					<!-- <td width="44" align="center">20</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gdOne}" name="gdOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gdTwo}" name="gdTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gdThree}" name="gdThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.gdFour}" name="gdFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">广西</td>
					<!-- <td width="44" align="center">21</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gxOne}" name="gxOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gxTwo}" name="gxTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gxThree}" name="gxThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.gxFour}" name="gxFour"
							class="inputSize" />
					</td>
				</tr>






				<tr>
					<td width="112">海南</td>
					<!-- <td width="44" align="center">22</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hainanOne}" name="hainanOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hainanTwo}" name="hainanTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.hainanThree}" name="hainanThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.hainanFour}" name="hainanFour"
							class="inputSize" />
					</td>
				</tr>
				<tr>
					<td width="112">重庆</td>
					<!-- <td width="44" align="center">23</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.cqOne}" name="cqOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.cqTwo}" name="cqTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.cqThree}" name="cqThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.cqFour}" name="cqFour"
							class="inputSize" />
					</td>
				</tr>


				<tr>
					<td width="112">四川</td>
					<!-- <td width="44" align="center">24</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.scOne}" name="scOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.scTwo}" name="scTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.scThree}" name="scThree"
							class="inputSize" />
					</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.scFour}" name="scFour"
							class="inputSize" />
					</td>
				</tr>


				<tr>
					<td width="112">贵州</td>
					<!-- <td width="44" align="center">25</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gzOne}" name="gzOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gzTwo}" name="gzTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gzThree}" name="gzThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.gzFour}" name="gzFour"
							class="inputSize" />
					</td>
				</tr>



				<tr>
					<td width="112">云南</td>
					<!-- <td width="44" align="center">26</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.ynOne}" name="ynOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.ynTwo}" name="ynTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.ynThree}" name="ynThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.ynFour}" name="ynFour"
							class="inputSize" />
					</td>
				</tr>


				<tr>
					<td width="112">西藏</td>
					<!-- <td width="44" align="center">27</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.xzOne}" name="xzOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.xzTwo}" name="xzTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.xzThree}" name="xzThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.xzFour}" name="xzFour"
							class="inputSize" />
					</td>
				</tr>




				<tr>
					<td width="112">陕西</td>
					<!-- <td width="44" align="center">28</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.shanxiOne}" name="shanxiOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.shanxiTwo}" name="shanxiTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.shanxiThree}" name="shanxiThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.shanxiFour}" name="shanxiFour"
							class="inputSize" />
					</td>
				</tr>


				<tr>
					<td width="112">甘肃</td>
					<!-- <td width="44" align="center">29</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gansuOne}" name="gansuOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gansuTwo}" name="gansuTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.gansuThree}" name="gansuThree"
							class="inputSize" />
					</td>

					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.gansuFour}" name="gansuFour"
							class="inputSize" />
					</td>
				</tr>


				<tr>
					<td width="112">青海</td>
					<!-- <td width="44" align="center">30</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.qinghaiOne}" name="qinghaiOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.qinghaiTwo}" name="qinghaiTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.qinghaiThree}" name="qinghaiThree"
							class="inputSize" />
					</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.qinghaiFour}" name="qinghaiFour"
							class="inputSize" />
					</td>
				</tr>



				<tr>
					<td width="112">宁夏</td>
					<!-- <td width="44" align="center">31</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nxOne}" name="nxOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nxTwo}" name="nxTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nxThree}" name="nxThree"
							class="inputSize" />
					</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.nxFour}" name="nxFour"
							class="inputSize" />
					</td>
				</tr>

				<tr>
					<td width="112">新疆</td>
					<!-- <td width="44" align="center">32</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.xjOne}" name="xjOne" class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.xjTwo}" name="xjTwo" class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.xjThree}" name="xjThree"
							class="inputSize" />
					</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.xjFour}" name="xjFour"
							class="inputSize" />
					</td>
				</tr>

				<tr>
					<td width="112">一日游组织人次</td>
					<!-- <td width="44" align="center">33</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.dayOrgOne}" name="dayOrgOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.dayOrgTwo}" name="dayOrgTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.dayOrgThree}" name="dayOrgThree"
							class="inputSize" />
					</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.dayOrgFour}" name="dayOrgFour"
							class="inputSize" />
					</td>
				</tr>


				<tr>
					<td width="112">过夜游组织人次数</td>
					<!-- <td width="44" align="center">34</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nightTimeOne}" name="nightTimeOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nightTimeTwo}" name="nightTimeTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nightTimeThree}" name="nightTimeThree"
							class="inputSize" />
					</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.nightTimeFour}" name="nightTimeFour"
							class="inputSize" />
					</td>
				</tr>

				<tr>
					<td width="112">过夜游组织人天数</td>
					<!-- <td width="44" align="center">35</td> -->
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nightOrgOne}" name="nightOrgOne"
							class="inputSize" />
					</td>
					<td width="100">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nightOrgTwo}" name="nightOrgTwo"
							class="inputSize" />
					</td>
					<td width="115">
						<input type="text" id="endOfAssetbal"
							value="${quarterthreeData.nightOrgThree}" name="nightOrgThree"
							class="inputSize" />
					</td>
					<td width="160">
						<input type="text" id="numOfCollage"
							value="${quarterthreeData.nightOrgFour}" name="nightOrgFour"
							class="inputSize" />
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
}, 1000);

window.setInterval(function(){
	calcTotalOne();
	calcTotalTwo();
	calcTotalThree();
	calcTotalFour();
},1000);

function calcTotalOne(){
	var totalOne=eval($("input[name='bjOne']").val())+eval($("input[name='tjOne']").val())+eval($("input[name='hbOne']").val())+eval($("input[name='sxOne']").val())+eval($("input[name='nmgOne']").val())+eval($("input[name='lnOne']").val())+eval($("input[name='jlOne']").val())+eval($("input[name='hljOne']").val())+eval($("input[name='shOne']").val())+eval($("input[name='jsOne']").val())+eval($("input[name='zjOne']").val())+eval($("input[name='ahOne']").val())+eval($("input[name='fjOne']").val())+eval($("input[name='jxOne']").val())+eval($("input[name='sdOne']").val())+eval($("input[name='henanOne']").val())+eval($("input[name='hubeiOne']").val())+eval($("input[name='hlOne']").val())+eval($("input[name='gdOne']").val())+eval($("input[name='gxOne']").val())+eval($("input[name='hainanOne']").val())+eval($("input[name='cqOne']").val())+eval($("input[name='scOne']").val())+eval($("input[name='gzOne']").val())+eval($("input[name='ynOne']").val())+eval($("input[name='xzOne']").val())+eval($("input[name='shanxiOne']").val())+eval($("input[name='gansuOne']").val())+eval($("input[name='qinghaiOne']").val())+eval($("input[name='nxOne']").val())+eval($("input[name='xjOne']").val())+eval($("input[name='dayOrgOne']").val())+eval($("input[name='nightTimeOne']").val());
	$("input[name='totalOne']").val(isNaN(totalOne) ? null : totalOne);
}

function calcTotalTwo(){
	 var totalTwo=eval($("input[name='bjTwo']").val())+eval($("input[name='tjTwo']").val())+eval($("input[name='hbTwo']").val())+eval($("input[name='sxTwo']").val())+eval($("input[name='nmgTwo']").val())+eval($("input[name='lnTwo']").val())+eval($("input[name='jlTwo']").val())+eval($("input[name='hljTwo']").val())+eval($("input[name='shTwo']").val())+eval($("input[name='jsTwo']").val())+eval($("input[name='zjTwo']").val())+eval($("input[name='ahTwo']").val())+eval($("input[name='fjTwo']").val())+eval($("input[name='jxTwo']").val())+eval($("input[name='sdTwo']").val())+eval($("input[name='henanTwo']").val())+eval($("input[name='hubeiTwo']").val())+eval($("input[name='hlTwo']").val())+eval($("input[name='gdTwo']").val())+eval($("input[name='gxTwo']").val())+eval($("input[name='hainanTwo']").val())+eval($("input[name='cqTwo']").val())+eval($("input[name='scTwo']").val())+eval($("input[name='gzTwo']").val())+eval($("input[name='ynTwo']").val())+eval($("input[name='xzTwo']").val())+eval($("input[name='shanxiTwo']").val())+eval($("input[name='gansuTwo']").val())+eval($("input[name='qinghaiTwo']").val())+eval($("input[name='nxTwo']").val())+eval($("input[name='xjTwo']").val());
	$("input[name='totalTwo']").val(isNaN(totalTwo) ? null : totalTwo);
}

function calcTotalThree(){
	var totalThree=eval($("input[name='bjThree']").val())+eval($("input[name='tjThree']").val())+eval($("input[name='hbThree']").val())+eval($("input[name='sxThree']").val())+eval($("input[name='nmgThree']").val())+eval($("input[name='lnThree']").val())+eval($("input[name='jlThree']").val())+eval($("input[name='hljThree']").val())+eval($("input[name='shThree']").val())+eval($("input[name='jsThree']").val())+eval($("input[name='zjThree']").val())+eval($("input[name='ahThree']").val())+eval($("input[name='fjThree']").val())+eval($("input[name='jxThree']").val())+eval($("input[name='sdThree']").val())+eval($("input[name='henanThree']").val())+eval($("input[name='hubeiThree']").val())+eval($("input[name='hlThree']").val())+eval($("input[name='gdThree']").val())+eval($("input[name='gxThree']").val())+eval($("input[name='hainanThree']").val())+eval($("input[name='cqThree']").val())+eval($("input[name='scThree']").val())+eval($("input[name='gzThree']").val())+eval($("input[name='ynThree']").val())+eval($("input[name='xzThree']").val())+eval($("input[name='shanxiThree']").val())+eval($("input[name='gansuThree']").val())+eval($("input[name='qinghaiThree']").val())+eval($("input[name='nxThree']").val())+eval($("input[name='xjThree']").val())+eval($("input[name='dayOrgThree']").val())+eval($("input[name='nightTimeThree']").val());
	$("input[name='totalThree']").val(isNaN(totalThree) ? null : totalThree);
}

function calcTotalFour(){
	var totalFour=eval($("input[name='bjFour']").val())+eval($("input[name='tjFour']").val())+eval($("input[name='hbFour']").val())+eval($("input[name='sxFour']").val())+eval($("input[name='nmgFour']").val())+eval($("input[name='lnFour']").val())+eval($("input[name='jlFour']").val())+eval($("input[name='hljFour']").val())+eval($("input[name='shFour']").val())+eval($("input[name='jsFour']").val())+eval($("input[name='zjFour']").val())+eval($("input[name='ahFour']").val())+eval($("input[name='fjFour']").val())+eval($("input[name='jxFour']").val())+eval($("input[name='sdFour']").val())+eval($("input[name='henanFour']").val())+eval($("input[name='hubeiFour']").val())+eval($("input[name='hlFour']").val())+eval($("input[name='gdFour']").val())+eval($("input[name='gxFour']").val())+eval($("input[name='hainanFour']").val())+eval($("input[name='cqFour']").val())+eval($("input[name='scFour']").val())+eval($("input[name='gzFour']").val())+eval($("input[name='ynFour']").val())+eval($("input[name='xzFour']").val())+eval($("input[name='shanxiFour']").val())+eval($("input[name='gansuFour']").val())+eval($("input[name='qinghaiFour']").val())+eval($("input[name='nxFour']").val())+eval($("input[name='xjFour']").val());
	$("input[name='totalFour']").val(isNaN(totalFour) ? null : totalFour);
}

function addSub(){
	calcTotalOne();
	calcTotalTwo();
	calcTotalThree();
	calcTotalFour();
					if(eval($("input[name='bjOne']").val())>eval($("input[name='bjThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='tjOne']").val())>eval($("input[name='tjThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hbOne']").val())>eval($("input[name='hbThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='sxOne']").val())>eval($("input[name='sxThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='nmgOne']").val())>eval($("input[name='nmgThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='lnOne']").val())>eval($("input[name='lnThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='jlOne']").val())>eval($("input[name='jlThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hlOne']").val())>eval($("input[name='hlThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='shOne']").val())>eval($("input[name='shThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='jsOne']").val())>eval($("input[name='jsThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='zjOne']").val())>eval($("input[name='zjThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='ahOne']").val())>eval($("input[name='ahThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='fjOne']").val())>eval($("input[name='fjThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='jxOne']").val())>eval($("input[name='jxThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='sdOne']").val())>eval($("input[name='sdThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='henanOne']").val())>eval($("input[name='henanThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hubeiOne']").val())>eval($("input[name='hubeiThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hlOne']").val())>eval($("input[name='hlThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='gdOne']").val())>eval($("input[name='gdThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='gxOne']").val())>eval($("input[name='gxThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hainanOne']").val())>eval($("input[name='hainanThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='cqOne']").val())>eval($("input[name='cqThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='scOne']").val())>eval($("input[name='scThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='gzOne']").val())>eval($("input[name='gzThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='ynOne']").val())>eval($("input[name='ynThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='xzOne']").val())>eval($("input[name='xzThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='shanxiOne']").val())>eval($("input[name='shanxiThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='gansuOne']").val())>eval($("input[name='gansuThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='qinghaiOne']").val())>eval($("input[name='qinghaiThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='nxOne']").val())>eval($("input[name='nxThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='xjOne']").val())>eval($("input[name='xjThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='dayOrgOne']").val())>eval($("input[name='dayOrgThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='nightTimeOne']").val())>eval($("input[name='nightTimeThree']").val())){
						alert("人次数不能大于人天数");
						return false;
					}	
					
					
					if(eval($("input[name='bjTwo']").val())>eval($("input[name='bjFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='tjTwo']").val())>eval($("input[name='tjFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hbTwo']").val())>eval($("input[name='hbFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='sxTwo']").val())>eval($("input[name='sxFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='nmgTwo']").val())>eval($("input[name='nmgFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='lnTwo']").val())>eval($("input[name='lnFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='jlTwo']").val())>eval($("input[name='jlFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hlTwo']").val())>eval($("input[name='hlFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='shTwo']").val())>eval($("input[name='shFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='jsTwo']").val())>eval($("input[name='jsFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='zjTwo']").val())>eval($("input[name='zjFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='ahTwo']").val())>eval($("input[name='ahFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='fjTwo']").val())>eval($("input[name='fjFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='jxTwo']").val())>eval($("input[name='jxFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='sdTwo']").val())>eval($("input[name='sdFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='henanTwo']").val())>eval($("input[name='henanFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hubeiTwo']").val())>eval($("input[name='hubeiFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hlTwo']").val())>eval($("input[name='hlFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='gdTwo']").val())>eval($("input[name='gdFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='gxTwo']").val())>eval($("input[name='gxFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='hainanTwo']").val())>eval($("input[name='hainanFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='cqTwo']").val())>eval($("input[name='cqFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='scTwo']").val())>eval($("input[name='scFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='gzTwo']").val())>eval($("input[name='gzFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='ynTwo']").val())>eval($("input[name='ynFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='xzTwo']").val())>eval($("input[name='xzFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='shanxiTwo']").val())>eval($("input[name='shanxiFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='gansuTwo']").val())>eval($("input[name='gansuFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='qinghaiTwo']").val())>eval($("input[name='qinghaiFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='nxTwo']").val())>eval($("input[name='nxFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='xjTwo']").val())>eval($("input[name='xjFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='dayOrgTwo']").val())>eval($("input[name='dayOrgFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}if(eval($("input[name='nightTimeTwo']").val())>eval($("input[name='nightTimeFour']").val())){
						alert("人次数不能大于人天数");
						return false;
					}
}
</script>