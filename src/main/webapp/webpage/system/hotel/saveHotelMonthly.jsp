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

	<t:formvalid formid="saveHotelMonthly" refresh="false" dialog="true"
		tiptype="1" callback="back" action="hotelMonthlyController.do?save"
		usePlugin="password" layout="table" beforeSubmit="addSub()">

		<input id="id" name="id" type="hidden" value="${hotelMonthly.id }">
		<input type="hidden" value="1" name="isUser">

		<table id="tableReport" width="680" cellpadding="0" cellspacing="1"
			class="formtable">
			<thead>
				<tr>
					<td width="113"></td>
					<td width="144">酒店名称</td>
					<td width="136">
						<input type="text" value="${hotelmanage.unitname }"
							name="hotelmanage.unitname" readOnly="readOnly" />
					</td>
					<td width="138">上报日期</td>
					<td width="135">
						<input type="text" value="${hotelMonthly.reportDate }"
							name="reportDate" class="easyui-datebox" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>年份</td>
					<td>
						<input type="text" value="${hotelMonthly.year }" name="year"
							datatype="n" readOnly="readOnly" />
					</td>
					<td>月份</td>
					<td>
						<input type="text" value="${hotelMonthly.month }" name="month"
							datatype="n" readOnly="readOnly" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>酒店编码</td>
					<td>
						<input type="text" value="${hotelmanage.organizationNum }"
							name="hotelmanage.organizationNum" readOnly="readOnly" />
					</td>
					<td>酒店负责人</td>
					<td>
						<input type="text" value="${hotelmanage.manager }"
							name="hotelmanage.manager" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>填表人</td>
					<td>
						<input type="text" value="" name="filler"
							datatype="*" />
					</td>
					<td>联系电话</td>
					<td>
						<input type="text" value=""
							name="fillerTel" datatype="*" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>实际出租间夜数</td>
					<td>
						<input type="text" value="${hotelMonthly.actualRentNum }"
							name="actualRentNum" datatype="n" class="rent" />
					</td>
					<td>可供出租间夜数</td>
					<td>
						<input type="text" value="" name="rentNum"
							datatype="n" class="rent" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>出租率(%)</td>
					<td height="30">
						<input height="30" value="${hotelMonthly.rentPercent }"
							name="rentPercent" style="border: 0" readonly="readonly" />
					</td>
					<td colspan="4"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>营业收入(万元)</td>
					<td>
						<input type="text" value="${hotelMonthly.totalIncome }"
							name="totalIncome" datatype="decimals4" class="auto"
							style="background: #FAFAFA" readonly="readonly" />
					</td>
					<td>客房收入(万元)</td>
					<td>
						<input type="text" value="${hotelMonthly.roomIncome }"
							name="roomIncome" datatype="decimals4" class="auto" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>餐饮收入(万元)</td>
					<td>
						<input type="text" value="${hotelMonthly.cateringIncome }"
							name="cateringIncome" datatype="decimals4" class="auto" />
					</td>
					<td>其他收入(万元)</td>
					<td>
						<input type="text" value="${hotelMonthly.otherIncome }"
							name="otherIncome" datatype="decimals4" class="auto" />
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td rowspan="2" align="center">指标名称</td>
					<td colspan="2" align="center">人数（人次）</td>
					<td colspan="2" align="center">人天数（人天）</td>
				</tr>
				<tr>
					<td align="center">本月</td>
					<td align="center">年初-本月</td>
					<td align="center">本月</td>
					<td align="center">年初-本月</td>
				</tr>
				<tr height="30">
					<td>总人数</td>
					<td>
						<input type="text" value="${hotelMonthly.totalMonthTime }"
							name="totalMonthTime" readonly="readonly"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.totalMonthYearTime }"
							name="totalMonthYearTime" readonly="readonly"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.totalMonthDay }"
							name="totalMonthDay" readonly="readonly"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.totalMonthYearDay }"
							name="totalMonthYearDay" readonly="readonly"
							style="background: #FAFAFA" />
					</td>
				</tr>
				<tr>
					<td>国内过夜游客</td>
					<td>
						<input type="text" value="${hotelMonthly.inlandMonthTime }"
							name="inlandMonthTime" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.inlandMonthYearTime }"
							name="inlandMonthYearTime" class="auto" readonly="readonly"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.inlandMonthDay }"
							name="inlandMonthDay" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.inlandMonthYearDay }"
							name="inlandMonthYearDay" class="auto" readonly="readonly"
							style="background: #FAFAFA" />
					</td>
				</tr>
				<tr>
					<td>入境过夜游客</td>
					<td>
						<input type="text" value="${hotelMonthly.entryMonthTime }"
							name="entryMonthTime" datatype="n" readonly="readonly"
							class="auto" style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.entryMonthYearTime }"
							name="entryMonthYearTime" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.entryMonthDay }"
							name="entryMonthDay" datatype="n" readonly="readonly"
							class="auto" style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.entryMonthYearDay }"
							name="entryMonthYearDay" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
				</tr>
				<tr>
					<td>香港同胞</td>
					<td>
						<input type="text" value="${hotelMonthly.hongkongMonthTime }"
							name="hongkongMonthTime" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.hongkongMonthYearTime }"
							name="hongkongMonthYearTime" style="background: #FAFAFA"
							readonly="readonly" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.hongkongMonthDay }"
							name="hongkongMonthDay" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.hongkongMonthYearDay }"
							name="hongkongMonthYearDay" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
				</tr>
				<tr>
					<td>澳门同胞</td>
					<td>
						<input type="text" value="${hotelMonthly.macauMonthTime }"
							name="macauMonthTime" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.macauMonthYearTime }"
							name="macauMonthYearTime" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.macauMonthDay }"
							name="macauMonthDay" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.macauMonthYearDay }"
							name="macauMonthYearDay" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
				</tr>
				<tr>
					<td>台湾同胞</td>
					<td>
						<input type="text" value="${hotelMonthly.taiwanMonthTime }"
							name="taiwanMonthTime" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.taiwanMonthYearTime }"
							name="taiwanMonthYearTime" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.taiwanMonthDay }"
							name="taiwanMonthDay" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.taiwanMonthYearDay }"
							name="taiwanMonthYearDay" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
				</tr>
				<tr>
					<td>外国人</td>
					<td>
						<input type="text" value="${hotelMonthly.foreignMonthTime }"
							name="foreignMonthTime" readonly="readonly" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.foreignMonthYearTime }"
							name="foreignMonthYearTime" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.foreignMonthDay }"
							name="foreignMonthDay" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.foreignMonthYearDay }"
							name="foreignMonthYearDay" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
				</tr>
				<tr>
					<td>1.亚洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.asianMonth }"
							name="asianMonth" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.asianMonthYear }"
							name="asianMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>日本</td>
					<td>
						<input type="text" value="${hotelMonthly.japanMonth }"
							name="japanMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.japanMonthYear }"
							name="japanMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>韩国</td>
					<td>
						<input type="text" value="${hotelMonthly.koreaMonth }"
							name="koreaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.koreaMonthYear }"
							name="koreaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>蒙古</td>
					<td>
						<input type="text" value="${hotelMonthly.mongoliaMonth }"
							name="mongoliaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.mongoliaMonthYear }"
							name="mongoliaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>印度尼西亚</td>
					<td>
						<input type="text" value="${hotelMonthly.indonesiaMonth }"
							name="indonesiaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.indonesiaMonthYear }"
							name="indonesiaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>马来西亚</td>
					<td>
						<input type="text" value="${hotelMonthly.malaysiaMonth }"
							name="malaysiaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.malaysiaMonthYear }"
							name="malaysiaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>菲律宾</td>
					<td>
						<input type="text" value="${hotelMonthly.philippinesMonth }"
							name="philippinesMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.philippinesMonthYear }"
							name="philippinesMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>新加坡</td>
					<td>
						<input type="text" value="${hotelMonthly.singaporeMonth }"
							name="singaporeMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.singaporeMonthYear }"
							name="singaporeMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>泰国</td>
					<td>
						<input type="text" value="${hotelMonthly.thailandMonth }"
							name="thailandMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.thailandMonthYear }"
							name="thailandMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>印度</td>
					<td>
						<input type="text" value="${hotelMonthly.indiaMonth }"
							name="indiaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.indiaMonthYear }"
							name="indiaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>越南</td>
					<td>
						<input type="text" value="${hotelMonthly.vietnamMonth }"
							name="vietnamMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.vietnamMonthYear }"
							name="vietnamMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>缅甸</td>
					<td>
						<input type="text" value="${hotelMonthly.burmaMonth }"
							name="burmaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.burmaMonthYear }"
							name="burmaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>朝鲜</td>
					<td>
						<input type="text" value="${hotelMonthly.northkoreaMonth }"
							name="northkoreaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.northkoreaMonthYear }"
							name="northkoreaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>巴基斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.pakistanMonth }"
							name="pakistanMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.pakistanMonthYear }"
							name="pakistanMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>老挝</td>
					<td>
						<input type="text" value="${hotelMonthly.laosMonth }"
							name="laosMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.laosMonthYear }"
							name="laosMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>柬埔寨</td>
					<td>
						<input type="text" value="${hotelMonthly.cambodiaMonth }"
							name="cambodiaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.cambodiaMonthYear }"
							name="cambodiaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>尼泊尔</td>
					<td>
						<input type="text" value="${hotelMonthly.nepalMonth }"
							name="nepalMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.nepalMonthYear }"
							name="nepalMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>斯里兰卡</td>
					<td>
						<input type="text" value="${hotelMonthly.srilankaMonth }"
							name="srilankaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.srilankaMonthYear }"
							name="srilankaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>哈萨克斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.kzMonth }" name="kzMonth"
							datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.kzMonthYear }"
							name="kzMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>吉尔吉斯斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.kyrghyzstanMonth }"
							name="kyrghyzstanMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.kyrghyzstanMonthYear }"
							name="kyrghyzstanMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>乌兹别克斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.uzMonth }" name="uzMonth"
							datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.uzMonthYear }"
							name="uzMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>塔吉克斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.tajikistanMonth }"
							name="tajikistanMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.tajikistanMonthYear }"
							name="tajikistanMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>亚洲其他</td>
					<td>
						<input type="text" value="${hotelMonthly.asianOtherMonth }"
							name="asianOtherMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.asianOtherMonthYear }"
							name="asianOtherMonthYear" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>2.欧洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.europeMonth }"
							name="europeMonth" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.europeMonthYear }"
							name="europeMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>英国</td>
					<td>
						<input type="text" value="${hotelMonthly.englandMonth }"
							name="englandMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.englandMonthYear }"
							name="englandMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>法国</td>
					<td>
						<input type="text" value="${hotelMonthly.franceMonth }"
							name="franceMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.franceMonthYear }"
							name="franceMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>德国</td>
					<td>
						<input type="text" value="${hotelMonthly.germanyMonthYear }"
							name="germanyMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.germanyMonthYear }"
							name="germanyMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>意大利</td>
					<td>
						<input type="text" value="${hotelMonthly.italyMonth }"
							name="italyMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.italyMonthYear }"
							name="italyMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>瑞士</td>
					<td>
						<input type="text" value="${hotelMonthly.switzerlandMonth }"
							name="switzerlandMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.switzerlandMonthYear }"
							name="switzerlandMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>瑞典</td>
					<td>
						<input type="text" value="${hotelMonthly.swedenMonth }"
							name="swedenMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.swedenMonthYear }"
							name="swedenMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>俄罗斯</td>
					<td>
						<input type="text" value="${hotelMonthly.russiaMonth }"
							name="russiaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.russiaMonthYear }"
							name="russiaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>西班牙</td>
					<td>
						<input type="text" value="${hotelMonthly.spainMonth }"
							name="spainMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.spainMonthYear }"
							name="spainMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>爱尔兰</td>
					<td>
						<input type="text" value="${hotelMonthly.irelandMonth }"
							name="irelandMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.irelandMonthYear }"
							name="irelandMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>乌克兰</td>
					<td>
						<input type="text" value="${hotelMonthly.ukraineMonth }"
							name="ukraineMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.ukraineMonthYear }"
							name="ukraineMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>比利时</td>
					<td>
						<input type="text" value="${hotelMonthly.belgiumMonth }"
							name="belgiumMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.belgiumMonthYear }"
							name="belgiumMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>捷克</td>
					<td>
						<input type="text" value="${hotelMonthly.czMonth }" name="czMonth"
							datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.czMonthYear }"
							name="czMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>奥地利</td>
					<td>
						<input type="text" value="${hotelMonthly.austriaMonth }"
							name="austriaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.austriaMonthYear }"
							name="austriaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>葡萄牙</td>
					<td>
						<input type="text" value="${hotelMonthly.portugalMonth }"
							name="portugalMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.portugalMonthYear }"
							name="portugalMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>荷兰</td>
					<td>
						<input type="text" value="${hotelMonthly.hollandMonth }"
							name="hollandMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.hollandMonthYear }"
							name="hollandMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>欧洲其他</td>
					<td>
						<input type="text" value="${hotelMonthly.europeOtherMonth }"
							name="europeOtherMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.europeOtherMonthYear }"
							name="europeOtherMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>3.美洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.americaMonth }"
							name="americaMonth" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.americaMonthYear }"
							name="americaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>美国</td>
					<td>
						<input type="text" value="${hotelMonthly.usaMonth }"
							name="usaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.usaMonthYear }"
							name="usaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>加拿大</td>
					<td>
						<input type="text" value="${hotelMonthly.canadaMonth }"
							name="canadaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.canadaMonthYear }"
							name="canadaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>墨西哥</td>
					<td>
						<input type="text" value="${hotelMonthly.mexicoMonth }"
							name="mexicoMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.mexicoMonthYear }"
							name="mexicoMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>巴西</td>
					<td>
						<input type="text" value="${hotelMonthly.brazilMonth }"
							name="brazilMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.brazilMonthYear }"
							name="brazilMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>阿根廷</td>
					<td>
						<input type="text" value="${hotelMonthly.argentinaMonth }"
							name="argentinaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.argentinaMonthYear }"
							name="argentinaMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>智利</td>
					<td>
						<input type="text" value="${hotelMonthly.chileMonth }"
							name="chileMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.chileMonthYear }"
							name="chileMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>秘鲁</td>
					<td>
						<input type="text" value="${hotelMonthly.peruMonth }"
							name="peruMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.peruMonthYear }"
							name="peruMonthYear" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>美洲其他</td>
					<td>
						<input type="text" value="${hotelMonthly.americaOtherMonth }"
							name="americaOtherMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.americaOtherMonthYear }"
							name="americaOtherMonthYear" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>4.大洋洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaMonth }"
							name="oceaniaMonth" readonly="readonly" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaMonthYear }"
							name="oceaniaMonthYear" readonly="readonly"
							style="background: #FAFAFA" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>澳大利亚</td>
					<td>
						<input type="text" value="${hotelMonthly.australiaMonth }"
							name="australiaMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.australiaMonthYear }"
							name="australiaMonthYear" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>新西兰</td>
					<td>
						<input type="text" value="${hotelMonthly.nzMonth }" name="nzMonth"
							datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaOtherMonth }"
							name="nzMonthYear" readonly="readonly"
							style="background: #FAFAFA" class="auto"/ >
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>大洋洲其他</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaOtherMonth }"
							name="oceaniaOtherMonth" datatype="n" class="auto" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaOtherMonthYear }"
							name="oceaniaOtherMonthYear" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>5.非洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.africaMonth }"
							name="africaMonth" datatype="n" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.africaMonthYear }"
							name="africaMonthYear" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>6.其他小计</td>
					<td>
						<input type="text" value="${hotelMonthly.otherMonth }"
							name="otherMonth" datatype="n" class="auto"
							style="background: #FAFAFA" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.otherMonthYear }"
							name="otherMonthYear" readonly="readonly"
							style="background: #FAFAFA" class="auto" />
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
</body>

<script>
window.setTimeout(function() {
	var ips = $("#tableReport tbody input");
	var dataId = $("#id")[0].value;
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
	var totalIncome=eval($("input[name='roomIncome']").val())+eval($("input[name='cateringIncome']").val())+eval($("input[name='otherIncome']").val());
	$("input[name='totalIncome']").val(isNaN(totalIncome) ? null : totalIncome);
	var asianMonth=eval($("input[name='japanMonth']").val())+eval($("input[name='koreaMonth']").val())+eval($("input[name='mongoliaMonth']").val())+eval($("input[name='indonesiaMonth']").val())+eval($("input[name='malaysiaMonth']").val())+eval($("input[name='philippinesMonth']").val())+eval($("input[name='singaporeMonth']").val())+eval($("input[name='thailandMonth']").val())+eval($("input[name='indiaMonth']").val())+eval($("input[name='vietnamMonth']").val())+eval($("input[name='burmaMonth']").val())+eval($("input[name='northkoreaMonth']").val())+eval($("input[name='pakistanMonth']").val())+eval($("input[name='laosMonth']").val())+eval($("input[name='cambodiaMonth']").val())+eval($("input[name='nepalMonth']").val())+eval($("input[name='srilankaMonth']").val())+eval($("input[name='kzMonth']").val())+eval($("input[name='kyrghyzstanMonth']").val())+eval($("input[name='uzMonth']").val())+eval($("input[name='tajikistanMonth']").val())+eval($("input[name='asianOtherMonth']").val());
	$("input[name='asianMonth']").val(isNaN(asianMonth) ? null : asianMonth);
	var europeMonth=eval($("input[name='englandMonth']").val())+eval($("input[name='franceMonth']").val())+eval($("input[name='germanyMonth']").val())+eval($("input[name='italyMonth']").val())+eval($("input[name='switzerlandMonth']").val())+eval($("input[name='swedenMonth']").val())+eval($("input[name='russiaMonth']").val())+eval($("input[name='spainMonth']").val())+eval($("input[name='irelandMonth']").val())+eval($("input[name='ukraineMonth']").val())+eval($("input[name='belgiumMonth']").val())+eval($("input[name='czMonth']").val())+eval($("input[name='austriaMonth']").val())+eval($("input[name='portugalMonth']").val())+eval($("input[name='hollandMonth']").val())+eval($("input[name='europeOtherMonth']").val());
	$("input[name='europeMonth']").val(isNaN(europeMonth) ? null : europeMonth);
	var americaMonth=eval($("input[name='usaMonth']").val())+eval($("input[name='canadaMonth']").val())+eval($("input[name='mexicoMonth']").val())+eval($("input[name='brazilMonth']").val())+eval($("input[name='argentinaMonth']").val())+eval($("input[name='chileMonth']").val())+eval($("input[name='peruMonth']").val())+eval($("input[name='americaOtherMonth']").val());
	$("input[name='americaMonth']").val(isNaN(americaMonth) ? null : americaMonth);
	var oceaniaMonth=eval($("input[name='australiaMonth']").val())+eval($("input[name='nzMonth']").val())+eval($("input[name='oceaniaOtherMonth']").val());
	$("input[name='oceaniaMonth']").val(isNaN(oceaniaMonth) ? null : oceaniaMonth);
	
	var foreignMonthTime=eval($("input[name='asianMonth']").val())+eval($("input[name='europeMonth']").val())+eval($("input[name='americaMonth']").val())+eval($("input[name='oceaniaMonth']").val())+eval($("input[name='africaMonth']").val())+eval($("input[name='otherMonth']").val());
	$("input[name='foreignMonthTime']").val(isNaN(foreignMonthTime) ? null : foreignMonthTime);
	var entryMonthTime=eval($("input[name='hongkongMonthTime']").val())+eval($("input[name='macauMonthTime']").val())+eval($("input[name='taiwanMonthTime']").val())+eval($("input[name='foreignMonthTime']").val());
	$("input[name='entryMonthTime']").val(isNaN(entryMonthTime) ? null : entryMonthTime);
	
	var totalMonthTime=eval($("input[name='inlandMonthTime']").val())+eval($("input[name='entryMonthTime']").val());
	$("input[name='totalMonthTime']").val(isNaN(totalMonthTime) ? null : totalMonthTime);
	
	var entryMonthDay=eval($("input[name='hongkongMonthDay']").val())+eval($("input[name='macauMonthDay']").val())+eval($("input[name='taiwanMonthDay']").val())+eval($("input[name='foreignMonthDay']").val());
	$("input[name='entryMonthDay']").val(isNaN(entryMonthDay) ? null : entryMonthDay);

	var totalMonthDay=eval($("input[name='inlandMonthDay']").val())+eval($("input[name='entryMonthDay']").val());
	$("input[name='totalMonthDay']").val(isNaN(totalMonthDay) ? null : totalMonthDay);
},1000);

/* $(".auto").change(function(){
	var totalIncome=eval($("input[name='roomIncome']").val())+eval($("input[name='cateringIncome']").val())+eval($("input[name='otherIncome']").val());
	$("input[name='totalIncome']").val(isNaN(totalIncome) ? null : totalIncome);
	var asianMonth=eval($("input[name='japanMonth']").val())+eval($("input[name='koreaMonth']").val())+eval($("input[name='mongoliaMonth']").val())+eval($("input[name='indonesiaMonth']").val())+eval($("input[name='malaysiaMonth']").val())+eval($("input[name='philippinesMonth']").val())+eval($("input[name='singaporeMonth']").val())+eval($("input[name='thailandMonth']").val())+eval($("input[name='indiaMonth']").val())+eval($("input[name='vietnamMonth']").val())+eval($("input[name='burmaMonth']").val())+eval($("input[name='northkoreaMonth']").val())+eval($("input[name='pakistanMonth']").val())+eval($("input[name='laosMonth']").val())+eval($("input[name='cambodiaMonth']").val())+eval($("input[name='nepalMonth']").val())+eval($("input[name='srilankaMonth']").val())+eval($("input[name='kzMonth']").val())+eval($("input[name='kyrghyzstanMonth']").val())+eval($("input[name='uzMonth']").val())+eval($("input[name='tajikistanMonth']").val())+eval($("input[name='asianOtherMonth']").val());
	$("input[name='asianMonth']").val(isNaN(asianMonth) ? null : asianMonth);
	var europeMonth=eval($("input[name='englandMonth']").val())+eval($("input[name='franceMonth']").val())+eval($("input[name='germanyMonth']").val())+eval($("input[name='italyMonth']").val())+eval($("input[name='switzerlandMonth']").val())+eval($("input[name='swedenMonth']").val())+eval($("input[name='russiaMonth']").val())+eval($("input[name='spainMonth']").val())+eval($("input[name='irelandMonth']").val())+eval($("input[name='ukraineMonth']").val())+eval($("input[name='belgiumMonth']").val())+eval($("input[name='czMonth']").val())+eval($("input[name='austriaMonth']").val())+eval($("input[name='portugalMonth']").val())+eval($("input[name='hollandMonth']").val())+eval($("input[name='europeOtherMonth']").val());
	$("input[name='europeMonth']").val(isNaN(europeMonth) ? null : europeMonth);
	var americaMonth=eval($("input[name='usaMonth']").val())+eval($("input[name='canadaMonth']").val())+eval($("input[name='mexicoMonth']").val())+eval($("input[name='brazilMonth']").val())+eval($("input[name='argentinaMonth']").val())+eval($("input[name='chileMonth']").val())+eval($("input[name='peruMonth']").val())+eval($("input[name='americaOtherMonth']").val());
	$("input[name='americaMonth']").val(isNaN(americaMonth) ? null : americaMonth);
	var oceaniaMonth=eval($("input[name='australiaMonth']").val())+eval($("input[name='nzMonth']").val())+eval($("input[name='oceaniaOtherMonth']").val());
	$("input[name='oceaniaMonth']").val(isNaN(oceaniaMonth) ? null : oceaniaMonth);
	
	var foreignMonthTime=eval($("input[name='asianMonth']").val())+eval($("input[name='europeMonth']").val())+eval($("input[name='americaMonth']").val())+eval($("input[name='oceaniaMonth']").val())+eval($("input[name='africaMonth']").val())+eval($("input[name='otherMonth']").val());
	$("input[name='foreignMonthTime']").val(isNaN(foreignMonthTime) ? null : foreignMonthTime);
	var entryMonthTime=eval($("input[name='hongkongMonthTime']").val())+eval($("input[name='macauMonthTime']").val())+eval($("input[name='taiwanMonthTime']").val())+eval($("input[name='foreignMonthTime']").val());
	$("input[name='entryMonthTime']").val(isNaN(entryMonthTime) ? null : entryMonthTime);
	
	var totalMonthTime=eval($("input[name='inlandMonthTime']").val())+eval($("input[name='entryMonthTime']").val());
	$("input[name='totalMonthTime']").val(isNaN(totalMonthTime) ? null : totalMonthTime);
	
	var entryMonthDay=eval($("input[name='hongkongMonthDay']").val())+eval($("input[name='macauMonthDay']").val())+eval($("input[name='taiwanMonthDay']").val())+eval($("input[name='foreignMonthDay']").val());
	$("input[name='entryMonthDay']").val(isNaN(entryMonthDay) ? null : entryMonthDay);

	var totalMonthDay=eval($("input[name='inlandMonthDay']").val())+eval($("input[name='entryMonthDay']").val());
	$("input[name='totalMonthDay']").val(isNaN(totalMonthDay) ? null : totalMonthDay);
	
	
}); */
$('.rent').change(function(){
	var rentNum=$("input[name='rentNum']").val();
	var actualRentNum=$("input[name='actualRentNum']").val();
	if(eval(rentNum)<eval(actualRentNum)){
		alert("实际出租客房数不能大于可供出租客房数");
		return false;
	}
	var rentPercent =eval($("input[name='actualRentNum']").val())/eval($("input[name='rentNum']").val())*100;
	$("input[name='rentPercent']").val(isNaN(parseInt(rentPercent, 10).toFixed(0)) ? null : parseInt(rentPercent, 10).toFixed(0));
});
function addSub(){
	if(eval($("input[name='foreignMonthTime']").val())>eval($("input[name='foreignMonthDay']").val())){
		alert("外国人人次数不能大于人天数");
		return false;
	}if(eval($("input[name='totalMonthTime']").val())>eval($("input[name='totalMonthDay']").val())){
		alert("总人数人次数不能大于人天数");
		return false;
	} if(eval($("input[name='inlandMonthTime']").val())>eval($("input[name='inlandMonthDay']").val())){
		alert("国内过夜游客人次数不能大于人天数");
		return false;
	}if(eval($("input[name='entryMonthTime']").val())>eval($("input[name='entryMonthDay']").val())){
		alert("入境过夜游客人次数不能大于人天数");
		return false;
	}if(eval($("input[name='hongkongMonthTime']").val())>eval($("input[name='hongkongMonthDay']").val())){
		alert("香港同胞人次数不能大于人天数");
		return false;
	}if(eval($("input[name='macauMonthTime']").val())>eval($("input[name='macauMonthDay']").val())){
		alert("澳门同胞人次数不能大于人天数");
		return false;
	}if(eval($("input[name='taiwanMonthTime']").val())>eval($("input[name='taiwanMonthDay']").val())){
		alert("台湾同胞人次数不能大于人天数");
		return false;
	}
	var actualRentNum=$("input[name='actualRentNum']").val();
	if(eval($("input[name='totalMonthDay']").val())<eval(actualRentNum)){
		alert("总人天数不得小于实际出租客房数");
		return false;
	}
}

</script>