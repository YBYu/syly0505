<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="">
	<t:formvalid formid="formobj" refresh="false" dialog="true"
		callback="reloadTable" action="travelController.do?save"
		usePlugin="password" layout="table" tiptype="1" beforeSubmit="calc()">
		<table style="width: 650px; font-size: 12px; margin-left: 15px"
			cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<!--许可证号-->
				<tr>
					<td align="right">许可证号</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="licensenum" datatype="*"
							class="regionNum" nullmsg="请输入许可证编号" />
					</td>
					<td align="right">
						<span class="filedzt">单位名称</span>
					</td>
					<td class="value">
						<input type="text" value="" name="name" datatype="*"
							class="companyname" nullmsg="请输入单位名称" />
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right">
						<span class="filedzt">英文名称</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="englishname" />
					</td>
					<td align="right">
						<span class="filedzt">组织机构代码</span>
					</td>
					<td class="value">
						<input type="text" value="" name="organizationnum" />
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right">
						<span class="filedzt">统一社会信用代码</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="province" />
					</td>
					<td align="right">
						<span class="filedzt">行政区域代码</span>
					</td>
					<td class="value">
						<input type="text" value="460200" name="adminnum"
							readOnly="460200" />
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right">
						<span class="filedzt">法人代表</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="corporate" datatype="*"
							class="deligate" nullmsg="请输入法人代表" />
					</td>
					<td align="right">
						<span class="filedzt">所在地区</span>
					</td>
					<td class="value">
						<select id="type3" name="area" class="location"
							style="width: 145px">
							<option value="0">市辖区</option>
							<option value="1">崖州区</option>
							<option value="2">海棠区</option>
							<option value="3">天涯区</option>
							<option value="4">吉阳区</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">批准文号</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="accommodationStar" datatype="*"
							nullmsg="请输入批准文号" />
					</td>
					<td align="right">
						<span class="filedzt">旅行社等级</span>
					</td>
					<td class="value">
						<select name="sceneSpotlevel" class="location"
							style="width: 145px">
							<option value="0">未评定</option>
							<option value="1">A级</option>
							<option value="2">AA级</option>
							<option value="3">AAA级</option>
							<option value="4">AAAA级</option>
							<option value="5">AAAAA级</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">固定电话</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="phone" datatype="*"
							class="creditCode" nullmsg="请输入固定电话" />
					</td>
					<td align="right">
						<span class="filedzt">分机</span>
					</td>
					<td class="value">
						<input type="text" value="" name="phoneextension" />
					</td>
				</tr>
				<tr>
				<tr>
					<td align="right">
						<span class="filedzt">传真</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="fax"
							datatype="/^(\d{3,4}-)?\d{7,8}$/" class="creditCode"
							nullmsg="请输入传真" errormsg="请输入正确的传真号码" />
					</td>
					<td align="right">
						<span class="filedzt">分机</span>
					</td>
					<td class="value">
						<input type="text" value="" name="faxextension" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">详细地址</span>
					</td>
					<td class="value" colspan="4">
						<input type="text" value="" name="address" datatype="*"
							nullmsg="请输入详细地址" style="width: 460px" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">经营场所</span>
					</td>
					<td class="value" colspan="4">
						<input type="text" value="" name="travelAgencyType" datatype="*"
							nullmsg="请输入经营场所" style="width: 460px" />
					</td>
				</tr>
				<!--手机、电子邮箱-->
				<tr>
					<td align="right">
						<span class="filedzt">手机</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="mobile" datatype="m"
							class="mobile" errormsg="请输入正确的手机号码" nullmsg="请输手机号" />
					</td>
					<td align="right">
						<span class="filedzt">电子邮箱</span>
					</td>
					<td class="value">
						<input type="text" value="" name="email" />
					</td>
				</tr>
				<!--网址、邮政编码-->
				<tr>
					<td align="right">
						<span class="filedzt">网址</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="weburl" />
					</td>
					<td align="right">
						<span class="filedzt">邮政编码</span>
					</td>
					<td class="value">
						<input type="text" value="" name="zipcode" class="zipcode"
							datatype="*" nullmsg="请输入邮政编码" />
					</td>
				</tr>
				<!--单位负责人-->
				<tr>
					<td align="right">
						<span class="filedzt">单位负责人</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="principal" datatype="*"
							class="responsible" nullmsg="请输单位负责人" />
					</td>
					<td align="right">
						<span class="filedzt">企业登记注册类型</span>
					</td>
					<td class="value">

						<select id="type3" value="${registerstyle}" name="registerstyle"
							class="location" style="width: 145px">
							<option value="国有"
								<c:if test="${'国有' eq registerstyle}">selected</c:if>>国有</option>
							<option value="集体"
								<c:if test="${'集体' eq registerstyle}">selected</c:if>>集体</option>
							<option value="股份合作"
								<c:if test="${'股份合作' eq registerstyle}">selected</c:if>>股份合作</option>
							<option value="国有联营"
								<c:if test="${'国有联营' eq registerstyle}">selected</c:if>>国有联营</option>
							<option value="集体联营"
								<c:if test="${'集体联营' eq registerstyle}">selected</c:if>>集体联营</option>
							<option value="国有独资公司"
								<c:if test="${'国有独资公司' eq registerstyle}">selected</c:if>>国有独资公司</option>
							<option value="其他有限责任公司"
								<c:if test="${'其他有限责任公司' eq registerstyle}">selected</c:if>>其他有限责任公司</option>
							<option value="股份有限公司"
								<c:if test="${'股份有限公司' eq registerstyle}">selected</c:if>>股份有限公司</option>
							<option value="私营独资"
								<c:if test="${'私营独资' eq registerstyle}">selected</c:if>>私营独资</option>
							<option value="私营合伙"
								<c:if test="${'私营合伙' eq registerstyle}">selected</c:if>>私营合伙</option>
							<option value="私营有限责任公司"
								<c:if test="${'私营有限责任公司' eq registerstyle}">selected</c:if>>私营有限责任公司</option>
							<option value="私营股份有限公司"
								<c:if test="${'私营股份有限公司' eq registerstyle}">selected</c:if>>私营股份有限公司</option>
							<option value="其他"
								<c:if test="${'其他' eq registerstyle}">selected</c:if>>其他</option>
							<option value="与港澳台商合资经营"
								<c:if test="${'与港澳台商合资经营' eq registerstyle}">selected</c:if>>与港澳台商合资经营</option>
							<option value="与港澳台商合作经营"
								<c:if test="${'与港澳台商合作经营' eq registerstyle}">selected</c:if>>与港澳台商合作经营</option>
							<option value="港澳台商投资股份有限公司"
								<c:if test="${'港澳台商投资股份有限公司' eq registerstyle}">selected</c:if>>港澳台商独资</option>
							<option value="港澳台商投资股份有限公司"
								<c:if test="${'港澳台商投资股份有限公司' eq registerstyle}">selected</c:if>>港澳台商投资股份有限公司</option>
							<option value="其他港澳台商投资"
								<c:if test="${'其他港澳台商投资' eq registerstyle}">selected</c:if>>其他港澳台商投资</option>
							<option value="中外合资经营"
								<c:if test="${'中外合资经营' eq registerstyle}">selected</c:if>>中外合资经营</option>
							<option value="中外合作经营"
								<c:if test="${'中外合作经营' eq registerstyle}">selected</c:if>>中外合作经营</option>
							<option value="外资企业"
								<c:if test="${'外资企业' eq registerstyle}">selected</c:if>>外资企业</option>
							<option value="外商投资股份有限公司"
								<c:if test="${'外商投资股份有限公司' eq registerstyle}">selected</c:if>>外商投资股份有限公司</option>
							<option value="其他外商投资"
								<c:if test="${'其他外商投资' eq registerstyle}">selected</c:if>>其他外商投资</option>
							<option value="国有与集体联营"
								<c:if test="${'国有与集体联营' eq registerstyle}">selected</c:if>>国有与集体联营</option>
							<option value="其他联营"
								<c:if test="${'其他联营' eq registerstyle}">selected</c:if>>其他联营</option>
						</select>
					</td>
				</tr>
				<!--是否经营出境游-->
				<tr>
					<td align="right">
						<span class="filedzt">是否经营出境游</span>
					</td>
					<td class="value" colspan="2">
						<span>
							<input type="radio" value="1" name="businessexit" />
							是
						</span>
						<span>
							<input type="radio" value="2" name="businessexit" />
							否
						</span>
					</td>
					<td align="right">
						<span class="filedzt">是否经营边境游</span>
					</td>
					<td class="value" width="35%">
						<input type="radio" value="1" name="businessborder" />
						是
						<input type="radio" value="2" name="businessborder" />
						否
					</td>
				</tr>
				<!--旅行社协会会员-->
				<tr>
					<td align="right">
						<span class="filedzt">旅行社协会会员</span>
					</td>
					<td class="value" colspan="2">
						<input type="radio" value="1" name="ismember" />
						是
						<input type="radio" value="2" name="ismember" />
						否
					</td>
					<td align="right">
						<span class="filedzt">QQ</span>
					</td>
					<td class="value">
						<input type="text" value="" name="qq" class="qqId" datatype="*"
							nullmsg="请输入QQ" />
					</td>
				</tr>
				<!-- 填报人、填报人电话 -->
				<tr>
					<td align="right">
						<span class="filedzt">填报人</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="" name="filler" datatype="*"
							class="mobile" />
					</td>
					<td align="right">
						<span class="filedzt">填报人电话</span>
					</td>
					<td class="value">
						<input type="text" value="" name="fillerTel" datatype="m"
							errormsg="请输入正确的手机号码" nullmsg="请输手机号" />
					</td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
</body>

<script>
	function calc(){
		// 统一社会信用代码
		var province = $("input[name='province']");
		// 组织机构代码
		var organizationnum = $("input[name='organizationnum']");
		if((province.val() == null || province.val().length == 0) && (organizationnum.val() == null || organizationnum.val().length == 0)){
			alert("请填写统一社会信用代码或组织机构代码");
			return false;
		}
		if((province.val() != null && province.val().length != 0) && (organizationnum.val() != null && organizationnum.val().length != 0)){
			alert("统一社会信用代码与组织机构代码只允许填写一项");
			return false;
		}
	}
</script>