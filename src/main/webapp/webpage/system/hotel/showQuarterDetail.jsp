<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="no">
	<div style="left: 30px"></div>
	<!-- 单位是人次 type为0 -->

	<t:formvalid formid="saveHotelMonthly" refresh="false" dialog="true"
		usePlugin="password" layout="table">

		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>

				<!--法人信息-->
				<tr>
					<td align="right" width="16%">年份</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelQuarter.year }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">季度</span>
					</td>
					<td class="value" width="30%">
						<input type="text"
							value="<c:if test="${hotelQuarter.quarter==1 }">第一季度</c:if><c:if test="${hotelQuarter.quarter==2 }">第二季度</c:if><c:if test="${hotelQuarter.quarter==3 }">第三季度</c:if><c:if test="${hotelQuarter.quarter==4 }">第四季度</c:if><c:if test="${hotelQuarter.quarter==4 }"></c:if>">
					</td>
				</tr>
				<!--许可证号-->
				<tr>
					<td align="right" width="16%">组织机构代码</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelQuarter.organizationNum}">
					</td>
					<td align="right" width="16%">单位名称</td>

					<td class="value" width="30%">
						<input type="text" value="${hotelQuarter.unitname }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">酒店星级</td>
					<td class="value" width="30%" colspan="4">
						<select id="cc" name="housegrade" datatype="*" errormsg="请选择">
							<option value=""></option>
							<option
								${hotelQuarter.hotelmanage.housegrade==8?'selected="selected"':''}
								value="8">五星</option>
							<option
								${hotelQuarter.hotelmanage.housegrade==7?'selected="selected"':''}
								value="7">四星</option>
							<option
								${hotelQuarter.hotelmanage.housegrade==6?'selected="selected"':''}
								value="6">三星</option>
							<option
								${hotelQuarter.hotelmanage.housegrade==5?'selected="selected"':''}
								value="5">二星</option>
							<option
								${hotelQuarter.hotelmanage.housegrade==4?'selected="selected"':''}
								value="4">一星</option>
							<option
								${hotelQuarter.hotelmanage.housegrade==3?'selected="selected"':''}
								value="3">豪华</option>
							<option
								${hotelQuarter.hotelmanage.housegrade==2?'selected="selected"':''}
								value="2">高档</option>
							<option
								${hotelQuarter.hotelmanage.housegrade==1?'selected="selected"':''}
								value="1">舒适</option>
							<option
								${hotelQuarter.hotelmanage.housegrade==0?'selected="selected"':''}
								value="0">其他</option>
						</select>
					</td>
					<%-- 				<td align="right" width="16%"><span class="filedzt">季度</span></td>
				<td class="value" width="30%" ><input type="text" value="${hotelQuarter.hotelmanage.housegrade }"></td>  --%>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">填表人</span>
					</td>

					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelQuarter.writer }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">填表日期</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelQuarter.writerDate }"
							class="easyui-datebox" />
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">单位负责人</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelQuarter.manager }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">填表人手机号</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelQuarter.mobile }">
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">总收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelQuarter.totalIncome }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">客房收入(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelQuarter.roomIncome }">
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">餐厅收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelQuarter.canteenIncome }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">其他收入(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelQuarter.otherIncome }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">客房数量</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelQuarter.roomnum }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">床位数量</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelQuarter.bednum }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">实际出租夜间数</span>
					</td>

					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelQuarter.realNights }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">可供出租夜间数</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelQuarter.canNights }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">平均房价(千元)</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" name="avgHotelPrice"
							value="${hotelQuarter.avgHotelPrice }">
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