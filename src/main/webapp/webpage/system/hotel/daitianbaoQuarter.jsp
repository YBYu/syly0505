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

	<t:formvalid formid="saveHotelQuarter" refresh="false" dialog="true"
		callback="reloadTable" action="hotelQuarterController.do?saveDai"
		usePlugin="password" layout="table">

		<input type="hidden" name="id" value="${quarterData.id }">

		<table cellpadding="0" cellspacing="1" class="formtable"
			id="tableReport">

			<!--法人信息-->
			<thead>
				<tr>
					<td align="right" width="16%">年份</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="n4-4" name="year"
							value="${quarterData.year }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">季度</span>
					</td>
					<td class="value" width="30%">
						<select id="area" name="quarter">
							<option value="1" ${quarterData.quarter==1?"selected":"" }
								class="opt-tit">第一季度</option>
							<option value="2" ${quarterData.quarter==2?"selected":"" }
								class="opt-tit">第二季度</option>
							<option value="3" ${quarterData.quarter==3?"selected":"" }
								class="opt-tit">第三季度</option>
							<option value="4" ${quarterData.quarter==4?"selected":"" }
								class="opt-tit">第四季度</option>
						</select>
						<%-- <input type="text" datatype="*" name="quarter" value="<c:if test="${quarterData.quarter==1 }">第一季度</c:if><c:if test="${quarterData.quarter==2 }">第二季度</c:if><c:if test="${quarterData.quarter==3 }">第三季度</c:if><c:if test="${quarterData.quarter==4 }">第四季度</c:if>" ></td> --%>
				</tr>
				<!--许可证号-->
				<tr>
					<td align="right" width="16%">组织机构代码</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" readonly="readOnly" name="organizationNum"
							value="${hotelmanage.organizationNum}">
					</td>
					<td align="right" width="16%">单位名称</td>

					<td class="value" width="30%">
						<input readonly="readOnly" type="text" name="unitname"
							value="${hotelmanage.unitname }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">酒店星级</td>
					<td class="value" width="30%" colspan="4">
						<select id="cc" name="housegrade" disabled>
							<option value=""></option>
							<option ${hotelmanage.housegrade==8?'selected="selected"':''}
								value="8">五星</option>
							<option ${hotelmanage.housegrade==7?'selected="selected"':''}
								value="7">四星</option>
							<option ${hotelmanage.housegrade==6?'selected="selected"':''}
								value="6">三星</option>
							<option ${hotelmanage.housegrade==5?'selected="selected"':''}
								value="5">二星</option>
							<option ${hotelmanage.housegrade==4?'selected="selected"':''}
								value="4">一星</option>
							<option ${hotelmanage.housegrade==3?'selected="selected"':''}
								value="3">豪华</option>
							<option ${hotelmanage.housegrade==2?'selected="selected"':''}
								value="2">高档</option>
							<option ${hotelmanage.housegrade==1?'selected="selected"':''}
								value="1">舒适</option>
							<option ${hotelmanage.housegrade==0?'selected="selected"':''}
								value="0">其他</option>
						</select>
					</td>
					<%-- 				<td align="right" width="16%"><span class="filedzt">季度</span></td>
				<td class="value" width="30%" ><input type="text" datatype="*" name="" value="${quarterData.hotelmanage.housegrade }"></td>  --%>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">填表人</span>
					</td>

					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="*" name="writer"
							value="">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">填表日期</span>
					</td>
					<td class="value" width="30%">
						<input type="text" class="easyui-datebox" name="writerDate"
							value="${quarterData.writerDate }" disabled="disabled">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">单位负责人</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" name="manager"
							value="${hotelmanage.manager }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">填表人手机号</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="*" name="mobile"
							value="">
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">总收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text"  name="totalIncome" datatype="decimals3"
							value="${quarterData.totalIncome }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">客房收入(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text"  name="roomIncome" datatype="decimals3"
							value="${quarterData.roomIncome }" class="avgprice">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">餐厅收入(千元)</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" name="canteenIncome"
							datatype="decimals3" value="${quarterData.canteenIncome }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">其他收入(千元)</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="decimals3" name="otherIncome"
							value="${quarterData.otherIncome }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">客房数量</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="n1-8" name="roomnum"
							value="${quarterData.roomnum }">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">床位数量</span>
					</td>
					<td class="value" width="30%">
						<input type="text" datatype="n1-8" name="bednum"
							value="${quarterData.bednum }">
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">实际出租夜间数</span>
					</td>

					<td class="value" width="16%" colspan="2">
						<input type="text" name="realNights" datatype="n"
							value="${quarterData.realNights }" class="avgprice">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">可供出租夜间数</span>
					</td>
					<td class="value" width="30%">
						<input type="text" name="canNights" datatype="n"
							value="${quarterData.canNights }">
					</td>

				</tr>
			</tbody>
			<tfoot>
				<div>
					<input type="hidden" value="${hotelmanageId }" name="hid">
				</div>
				<tr>
					<td align="right" width="16%" height="40">
						<span class="filedzt">平均房价(元)</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input name="avgHotelPrice" value="${quarterData.avgHotelPrice }"
							readonly="readonly" style="border: 0">
					</td>
				</tr>
			</tfoot>

		</table>
	</t:formvalid>
	<div style="padding: 5px">
		<script type="text/javascript">
		  window.setTimeout(function() {
				var ips = $("#tableReport tbody input");
				//如果为空则代表新建，赋默认值
				
					for (var i = 0; i < ips.length; i++) {
						ips[i].value = "0";
					}
			}, 1000);
	  
	  	$(".avgprice").change(function(){
	  		
	  		var avgHotelPrice=(eval($("input[name='roomIncome']").val())/eval($("input[name='realNights']").val()))*1000;
	  		$("input[name='avgHotelPrice']").val(parseFloat(avgHotelPrice, 10).toFixed(0));
	  		if(eval($("input[name='realNights']").val())==0){
	  			$("input[name='avgHotelPrice']").val(0);
	  		}
	  	});
	  </script>

	</div>
	<!-- 单位是人天  type为1-->

</body>