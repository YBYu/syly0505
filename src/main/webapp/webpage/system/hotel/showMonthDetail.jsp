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
		usePlugin="password" layout="table">

		<input id="id" type="hidden" value="${hotelMonthly.id }">

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
							name="reportDate" class="easyui-datebox" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>年份</td>
					<td>
						<input type="text" value="${hotelMonthly.year }" name="year"
							datatype="n" />
					</td>
					<td>月份</td>
					<td>
						<input type="text" value="${hotelMonthly.month }" name="month"
							datatype="n" />
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
							name="hotelmanage.manager" readOnly="readOnly" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>填表人</td>
					<td>
						<input type="text" value="${hotelMonthly.filler }" name="filler"
							datatype="*" />
					</td>
					<td>联系电话</td>
					<td>
						<input type="text" value="${hotelMonthly.fillerTel }"
							name="fillerTel" datatype="m" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>实际出租间夜数</td>
					<td>
						<input type="text" value="${hotelMonthly.actualRentNum }"
							name="actualRentNum" datatype="n" />
					</td>
					<td>可供出租间夜数</td>
					<td>
						<input type="text" value="${hotelMonthly.rentNum }" name="rentNum"
							datatype="n" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>出租率</td>
					<td>
						<input type="text" value="${hotelMonthly.rentPercent }"
							name="rentPercent" datatype="*" />
					</td>
					<td colspan="2"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>营业收入</td>
					<td>
						<input type="text" value="${hotelMonthly.totalIncome }"
							name="totalIncome" datatype="*" />
					</td>
					<td>客房收入</td>
					<td>
						<input type="text" value="${hotelMonthly.roomIncome }"
							name="roomIncome" datatype="*" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>餐饮收入</td>
					<td>
						<input type="text" value="${hotelMonthly.cateringIncome }"
							name="cateringIncome" datatype="*" />
					</td>
					<td>其他收入</td>
					<td>
						<input type="text" value="${hotelMonthly.otherIncome }"
							name="otherIncome" datatype="*" />
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
				<tr>
					<td>总人数</td>
					<td>
						<input type="text" value="${hotelMonthly.totalMonthTime }"
							name="totalMonthTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.totalMonthYearTime }"
							name="totalMonthYearTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.totalMonthDay }"
							name="totalMonthDay" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.totalMonthYearDay }"
							name="totalMonthYearDay" datatype="*" />
					</td>
				</tr>
				<tr>
					<td>国内过夜游客</td>
					<td>
						<input type="text" value="${hotelMonthly.inlandMonthTime }"
							name="inlandMonthTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.inlandMonthYearTime }"
							name="inlandMonthYearTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.inlandMonthDay }"
							name="inlandMonthDay" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.inlandMonthYearDay }"
							name="inlandMonthYearDay" datatype="*" />
					</td>
				</tr>
				<tr>
					<td>入境过夜游客</td>
					<td>
						<input type="text" value="${hotelMonthly.entryMonthTime }"
							name="entryMonthTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.entryMonthYearTime }"
							name="entryMonthYearTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.entryMonthDay }"
							name="entryMonthDay" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.entryMonthYearDay }"
							name="entryMonthYearDay" datatype="*" />
					</td>
				</tr>
				<tr>
					<td>香港同胞</td>
					<td>
						<input type="text" value="${hotelMonthly.hongkongMonthTime }"
							name="hongkongMonthTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.hongkongMonthYearTime }"
							name="hongkongMonthYearTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.hongkongMonthDay }"
							name="hongkongMonthDay" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.hongkongMonthYearDay }"
							name="hongkongMonthYearDay" datatype="*" />
					</td>
				</tr>
				<tr>
					<td>澳门同胞</td>
					<td>
						<input type="text" value="${hotelMonthly.macauMonthTime }"
							name="macauMonthTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.macauMonthYearTime }"
							name="macauMonthYearTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.macauMonthDay }"
							name="macauMonthDay" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.macauMonthYearDay }"
							name="macauMonthYearDay" datatype="*" />
					</td>
				</tr>
				<tr>
					<td>台湾同胞</td>
					<td>
						<input type="text" value="${hotelMonthly.taiwanMonthTime }"
							name="taiwanMonthTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.taiwanMonthYearTime }"
							name="taiwanMonthYearTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.taiwanMonthDay }"
							name="taiwanMonthDay" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.taiwanMonthYearDay }"
							name="taiwanMonthYearDay" datatype="*" />
					</td>
				</tr>
				<tr>
					<td>外国人</td>
					<td>
						<input type="text" value="${hotelMonthly.foreignMonthTime }"
							name="foreignMonthTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.foreignMonthYearTime }"
							name="foreignMonthYearTime" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.foreignMonthDay }"
							name="foreignMonthDay" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.foreignMonthYearDay }"
							name="foreignMonthYearDay" datatype="*" />
					</td>
				</tr>
				<tr>
					<td>1.亚洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.asianMonth }"
							name="asianMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.asianMonthYear }"
							name="asianMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>日本</td>
					<td>
						<input type="text" value="${hotelMonthly.japanMonth }"
							name="japanMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.japanMonthYear }"
							name="japanMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>韩国</td>
					<td>
						<input type="text" value="${hotelMonthly.koreaMonth }"
							name="koreaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.koreaMonthYear }"
							name="koreaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>蒙古</td>
					<td>
						<input type="text" value="${hotelMonthly.mongoliaMonth }"
							name="mongoliaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.mongoliaMonthYear }"
							name="mongoliaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>印度尼西亚</td>
					<td>
						<input type="text" value="${hotelMonthly.indonesiaMonth }"
							name="indonesiaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.indonesiaMonthYear }"
							name="indonesiaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>马来西亚</td>
					<td>
						<input type="text" value="${hotelMonthly.malaysiaMonth }"
							name="malaysiaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.malaysiaMonthYear }"
							name="malaysiaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>菲律宾</td>
					<td>
						<input type="text" value="${hotelMonthly.philippinesMonth }"
							name="philippinesMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.philippinesMonthYear }"
							name="philippinesMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>新加坡</td>
					<td>
						<input type="text" value="${hotelMonthly.singaporeMonth }"
							name="singaporeMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.singaporeMonthYear }"
							name="singaporeMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>泰国</td>
					<td>
						<input type="text" value="${hotelMonthly.thailandMonth }"
							name="thailandMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.thailandMonthYear }"
							name="thailandMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>印度</td>
					<td>
						<input type="text" value="${hotelMonthly.indiaMonth }"
							name="indiaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.indiaMonthYear }"
							name="indiaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>越南</td>
					<td>
						<input type="text" value="${hotelMonthly.vietnamMonth }"
							name="vietnamMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.vietnamMonthYear }"
							name="vietnamMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>缅甸</td>
					<td>
						<input type="text" value="${hotelMonthly.burmaMonth }"
							name="burmaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.burmaMonthYear }"
							name="burmaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>朝鲜</td>
					<td>
						<input type="text" value="${hotelMonthly.northkoreaMonth }"
							name="northkoreaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.northkoreaMonthYear }"
							name="northkoreaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>巴基斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.pakistanMonth }"
							name="pakistanMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.pakistanMonthYear }"
							name="pakistanMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>老挝</td>
					<td>
						<input type="text" value="${hotelMonthly.laosMonth }"
							name="laosMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.laosMonthYear }"
							name="laosMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>柬埔寨</td>
					<td>
						<input type="text" value="${hotelMonthly.cambodiaMonth }"
							name="cambodiaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.cambodiaMonthYear }"
							name="cambodiaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>尼泊尔</td>
					<td>
						<input type="text" value="${hotelMonthly.nepalMonth }"
							name="nepalMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.nepalMonthYear }"
							name="nepalMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>斯里兰卡</td>
					<td>
						<input type="text" value="${hotelMonthly.srilankaMonth }"
							name="srilankaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.srilankaMonthYear }"
							name="srilankaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>哈萨克斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.kzMonth }" name="kzMonth"
							datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.kzMonthYear }"
							name="kzMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>吉尔吉斯斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.kyrghyzstanMonth }"
							name="kyrghyzstanMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.kyrghyzstanMonthYear }"
							name="kyrghyzstanMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>乌兹别克斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.uzMonth }" name="uzMonth"
							datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.uzMonthYear }"
							name="uzMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>塔吉克斯坦</td>
					<td>
						<input type="text" value="${hotelMonthly.tajikistanMonth }"
							name="tajikistanMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.tajikistanMonthYear }"
							name="tajikistanMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>亚洲其他</td>
					<td>
						<input type="text" value="${hotelMonthly.asianOtherMonth }"
							name="asianOtherMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.asianOtherMonthYear }"
							name="asianOtherMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>2.欧洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.europeMonth }"
							name="europeMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.europeMonthYear }"
							name="europeMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>英国</td>
					<td>
						<input type="text" value="${hotelMonthly.englandMonth }"
							name="englandMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.englandMonthYear }"
							name="englandMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>法国</td>
					<td>
						<input type="text" value="${hotelMonthly.franceMonth }"
							name="franceMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.franceMonthYear }"
							name="franceMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>德国</td>
					<td>
						<input type="text" value="${hotelMonthly.germanyMonthYear }"
							name="germanyMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.germanyMonthYear }"
							name="germanyMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>意大利</td>
					<td>
						<input type="text" value="${hotelMonthly.italyMonth }"
							name="italyMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.italyMonthYear }"
							name="italyMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>瑞士</td>
					<td>
						<input type="text" value="${hotelMonthly.switzerlandMonth }"
							name="switzerlandMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.switzerlandMonthYear }"
							name="switzerlandMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>瑞典</td>
					<td>
						<input type="text" value="${hotelMonthly.swedenMonth }"
							name="swedenMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.swedenMonthYear }"
							name="swedenMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>俄罗斯</td>
					<td>
						<input type="text" value="${hotelMonthly.russiaMonth }"
							name="russiaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.russiaMonthYear }"
							name="russiaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>西班牙</td>
					<td>
						<input type="text" value="${hotelMonthly.spainMonth }"
							name="spainMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.spainMonthYear }"
							name="spainMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>爱尔兰</td>
					<td>
						<input type="text" value="${hotelMonthly.irelandMonth }"
							name="irelandMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.irelandMonthYear }"
							name="irelandMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>乌克兰</td>
					<td>
						<input type="text" value="${hotelMonthly.ukraineMonth }"
							name="ukraineMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.ukraineMonthYear }"
							name="ukraineMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>比利时</td>
					<td>
						<input type="text" value="${hotelMonthly.belgiumMonth }"
							name="belgiumMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.belgiumMonthYear }"
							name="belgiumMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>捷克</td>
					<td>
						<input type="text" value="${hotelMonthly.czMonth }" name="czMonth"
							datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.czMonthYear }"
							name="czMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>奥地利</td>
					<td>
						<input type="text" value="${hotelMonthly.austriaMonth }"
							name="austriaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.austriaMonthYear }"
							name="austriaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>葡萄牙</td>
					<td>
						<input type="text" value="${hotelMonthly.portugalMonth }"
							name="portugalMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.portugalMonthYear }"
							name="portugalMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>荷兰</td>
					<td>
						<input type="text" value="${hotelMonthly.hollandMonth }"
							name="hollandMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.hollandMonthYear }"
							name="hollandMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>欧洲其他</td>
					<td>
						<input type="text" value="${hotelMonthly.europeOtherMonth }"
							name="europeOtherMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.europeOtherMonthYear }"
							name="europeOtherMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>3.美洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.americaMonth }"
							name="americaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.americaMonthYear }"
							name="americaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>美国</td>
					<td>
						<input type="text" value="${hotelMonthly.usaMonth }"
							name="usaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.usaMonthYear }"
							name="usaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>加拿大</td>
					<td>
						<input type="text" value="${hotelMonthly.canadaMonth }"
							name="canadaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.canadaMonthYear }"
							name="canadaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>墨西哥</td>
					<td>
						<input type="text" value="${hotelMonthly.mexicoMonth }"
							name="mexicoMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.mexicoMonthYear }"
							name="mexicoMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>巴西</td>
					<td>
						<input type="text" value="${hotelMonthly.brazilMonth }"
							name="brazilMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.brazilMonthYear }"
							name="brazilMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>阿根廷</td>
					<td>
						<input type="text" value="${hotelMonthly.argentinaMonth }"
							name="argentinaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.argentinaMonthYear }"
							name="argentinaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>智利</td>
					<td>
						<input type="text" value="${hotelMonthly.chileMonth }"
							name="chileMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.chileMonthYear }"
							name="chileMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>秘鲁</td>
					<td>
						<input type="text" value="${hotelMonthly.peruMonth }"
							name="peruMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.peruMonthYear }"
							name="peruMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>美洲其他</td>
					<td>
						<input type="text" value="${hotelMonthly.americaOtherMonth }"
							name="americaOtherMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.americaOtherMonthYear }"
							name="americaOtherMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>4.大洋洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaMonth }"
							name="oceaniaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaMonthYear }"
							name="oceaniaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>澳大利亚</td>
					<td>
						<input type="text" value="${hotelMonthly.australiaMonth }"
							name="australiaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.australiaMonthYear }"
							name="australiaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>新西兰</td>
					<td>
						<input type="text" value="${hotelMonthly.nzMonth }" name="nzMonth"
							datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaOtherMonth }"
							name="nzMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>大洋洲其他</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaOtherMonth }"
							name="oceaniaOtherMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.oceaniaOtherMonthYear }"
							name="oceaniaOtherMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>5.非洲小计</td>
					<td>
						<input type="text" value="${hotelMonthly.africaMonth }"
							name="africaMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.africaMonthYear }"
							name="africaMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>6.其他小计</td>
					<td>
						<input type="text" value="${hotelMonthly.otherMonth }"
							name="otherMonth" datatype="*" />
					</td>
					<td>
						<input type="text" value="${hotelMonthly.otherMonthYear }"
							name="otherMonthYear" datatype="*" />
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
</body>