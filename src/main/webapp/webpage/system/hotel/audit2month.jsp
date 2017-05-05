<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>酒店月报审核页面</title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
/* var hoteltAddPage  = {
		submitForm : function (){
			if(!$('#saveHotel').form('validate')){
				$.messager.alert('提示','表单还未填写完成!');
				return ;
			}
			//contentAddEditor.sync();
			
			$.post("hotelController/save",$("#saveHotel").serialize(), function(data){
				
				alert("添加成功");
			});
		},
		
} */
/* function tijiao(){
$.ajax({
	cache: true,
	type: "POST",
	url:"hotelMonthlyController.do?save",
	data:$('#saveHotelMonthly').serialize(),// formid
	async: false,
	error: function(request) {
		alert("Connection error");
	},
	success: function(data) {
		alert("月报添加成功");
	}
});
} */
</script>
</head>
<body style="" scroll="no">
	<div style="left: 30px"></div>
	<!-- 单位是人次 type为0 -->

	<t:formvalid formid="saveHotelMonthly" refresh="false" dialog="true"
		action="hotelMonthlyController.do?save" usePlugin="password"
		layout="table">
		<input id="id" type="hidden" value="${monthdata.id }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<tr>
					<td align="right" width="16%">月报时间：</td>
					<td class="value" width="16%" colspan="2">${monthdata.month}</span>
					</td>
					<td align="right" width="16%">单位 ：</td>
					<td class="value" width="30%">人次</td>
				</tr>
				<tr>
					<td align="right" width="16%">组织机构代码：</td>
					<td class="value" width="16%" colspan="2">${monthdata.organizationNum}</span>
					</td>
					<td align="right" width="16%">单位名称 ：</td>
					<td class="value" width="30%">${monthdata.name}</td>
				</tr>
				<tr>
					<%-- <td align="right" width="16%">酒店星级：</td>
                <td class="value" colspan="4" width="30%" >
                <c:if test="${monthdata.hotelmanage.housegrade==0 }">非星级</c:if>
                <c:if test="${monthdata.hotelmanage.housegrade==1 }">一星</c:if>
                <c:if test="${monthdata.hotelmanage.housegrade==2 }">二星</c:if>
                <c:if test="${monthdata.hotelmanage.housegrade==3 }">三星</c:if>
                <c:if test="${monthdata.hotelmanage.housegrade==4 }">四星</c:if>
                <c:if test="${monthdata.hotelmanage.housegrade==5 }">五星</c:if>
                </td> --%>
					<%-- 				<td align="right" width="16%"><span class="filedzt">季度:</span></td>
				<td class="value" width="30%" >${hotelQuarter.hotelmanage.housegrade }</td>  --%>
				</tr>
				<!--法人信息-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">国内:</span>
					</td>
					<td class="value" width="16%" colspan="2">${monthdata.domestic}</td>
					<td align="right" width="16%">
						<span class="filedzt">入境：</span>
					</td>
					<td class="value" width="30%">${monthdata.entry}</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">外国人：</span>
					</td>
					<td class="value" width="16%" colspan="2">${monthdata.foreign}</td>
					<td align="right" width="16%">
						<span class="filedzt">香港：</span>
					</td>
					<td class="value" width="30%">${monthdata.hongkong}</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">澳门：</span>
					</td>
					<td class="value" width="16%" colspan="2">${monthdata.macao}</td>
					<td align="right" width="16%">
						<span class="filedzt">台湾：</span>
					</td>
					<td class="value" width="30%">${monthdata.taiwan}</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">总数：</span>
					</td>
					<td class="value" width="16%">${monthdata.sum}</td>
					<td align="right" width="16%">
						<span class="filedzt">法人：</span>
					</td>
					<td class="value" width="30%">${monthdata.hotelmanage.legal_person}</td>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">所属市：</span>
					</td>
					<td class="value" width="16%">三亚市</td>
					<td align="right" width="16%">
						<span class="filedzt">所属县：</span>
					</td>
					<td class="value" width="30%">${monthdata.hotelmanage.w_county}
						<c:if test="${monthdata.hotelmanage.w_county==0 }">吉阳区</c:if>
						<c:if test="${monthdata.hotelmanage.w_county==1 }">崖州区</c:if>
						<c:if test="${monthdata.hotelmanage.w_county==2 }">天涯区</c:if>
						<c:if test="${monthdata.hotelmanage.w_county==3 }">海棠区</c:if>
					</td>
				</tr>
			</tbody>
		</table>>
 </t:formvalid>

	<!-- 单位是人天  type为1-->

</body>