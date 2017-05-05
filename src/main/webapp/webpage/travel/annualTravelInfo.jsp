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
		usePlugin="password" layout="table" tiptype="1">
		<input id="id" type="hidden" value="${traveldataannual.id }" name="id">
		<input id="createTime" type="hidden"
			value="${traveldataannual.createTime }" name="createTime">
		<input id="userId" type="hidden" value="${traveldataannual.userId }"
			name="userId">

		<table style="width: 650px; font-size: 12px; margin-left: 15px"
			cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<!--许可证号-->
				<tr>
					<td align="right">许可证号</td>
					<td class="value" colspan="2">
						<input type="text" readOnly="readOnly"
							value="${traveldataannual.licensenum}" name="licensenum"
							class="regionNum" nullmsg="请输入许可证编号" />
					</td>
					<td align="right">
						<span class="filedzt">单位名称</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.name}" name="name"
							class="companyname" nullmsg="请输入单位名称" ignore="ignore" />
						</span>
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right">
						<span class="filedzt">英文名称</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.englishname}"
							name="englishname" class="englishname" nullmsg="请输入英文名称"
							ignore="ignore" />
					</td>
					<td align="right">
						<span class="filedzt">组织机构代码</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.organizationnum}"
							name="organizationnum" class="ogCode" nullmsg="请输入组织机构代码"
							ignore="ignore" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">统一社会信用代码</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.province}"
							name="province" class="creditCode" nullmsg="统一社会信用代码"
							ignore="ignore" />
					</td>
					<td align="right">
						<span class="filedzt">行政区域代码</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.adminnum}"
							name="adminnum" class="adCode" nullmsg="请输入行政区域代码" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">法人代表</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.corporate}"
							name="corporate" class="deligate" nullmsg="请输入法人代表" />
					</td>
					<td align="right">
						<span class="filedzt">所在地区</span>
					</td>
					<td class="value">
						<select id="type3" name="area" value="${traveldataannual.area}"
							name="area" class="location" style="width: 145px">
							<option value="0"
								<c:if test="${'0' eq traveldataannual.area}">selected</c:if>>市辖区</option>
							<option value="1"
								<c:if test="${'1' eq traveldataannual.area}">selected</c:if>>崖州区</option>
							<option value="2"
								<c:if test="${'2' eq traveldataannual.area}">selected</c:if>>海棠区</option>
							<option value="3"
								<c:if test="${'3' eq traveldataannual.area}">selected</c:if>>天涯区</option>
							<option value="4"
								<c:if test="${'4' eq traveldataannual.area}">selected</c:if>>吉阳区</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">批准文号</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.accommodationStar}"
							name="accommodationStar" datatype="*" class="adCode"
							nullmsg="请输入批准文号" />
					</td>
					<td align="right">
						<span class="filedzt">旅行社等级</span>
					</td>
					<td class="value">
						<select name="sceneSpotlevel" class="location"
							style="width: 145px">
							<option value="0"
								<c:if test="${'0' == traveldataannual.sceneSpotlevel}">selected</c:if>>未评定</option>
							<option value="1"
								<c:if test="${'1' == traveldataannual.sceneSpotlevel}">selected</c:if>>A级</option>
							<option value="2"
								<c:if test="${'2' == traveldataannual.sceneSpotlevel}">selected</c:if>>AA级</option>
							<option value="3"
								<c:if test="${'3' == traveldataannual.sceneSpotlevel}">selected</c:if>>AAA级</option>
							<option value="4"
								<c:if test="${'4' == traveldataannual.sceneSpotlevel}">selected</c:if>>AAAA级</option>
							<option value="5"
								<c:if test="${'5' == traveldataannual.sceneSpotlevel}">selected</c:if>>AAAAA级</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">固定电话</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.phone }" name="phone"
							datatype="*" class="creditCode" nullmsg="请输入固定电话" />
					</td>
					<td align="right">
						<span class="filedzt">分机</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.phoneextension }"
							name="phoneextension" class="adCode" />
					</td>
				</tr>
				<tr>
				<tr>
					<td align="right">
						<span class="filedzt">传真</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.fax }" name="fax"
							datatype="/^(\d{3,4}-)?\d{7,8}$/" class="creditCode"
							nullmsg="请输入传真" errormsg="请输入正确的传真号码" />
					</td>
					<td align="right">
						<span class="filedzt">分机</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.faxextension }"
							name="faxextension" class="adCode" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">详细地址</span>
					</td>
					<td class="value" colspan="4">
						<input type="text" value="${traveldataannual.address}"
							name="address" class="address" nullmsg="请输入详细地址"
							style="width: 460px" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="filedzt">经营场所</span>
					</td>
					<td class="value" colspan="4">
						<input type="text" value="${traveldataannual.travelAgencyType}"
							name="travelAgencyType" class="address" nullmsg="请输入经营场所"
							style="width: 460px" />
					</td>
				</tr>
				<!--手机、电子邮箱-->
				<tr>
					<td align="right">
						<span class="filedzt">手机</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.mobile}"
							name="mobile" class="mobile" nullmsg="请输手机号" />
					</td>
					<td align="right">
						<span class="filedzt">电子邮箱</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.email}" name="email"
							class="email" nullmsg="请输入电子邮箱" />
					</td>
				</tr>

				<tr>
					<td align="right">
						<span class="filedzt">网址</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.weburl}"
							name="weburl" class="weburl" nullmsg="请输网址" />
					</td>
					<td align="right">
						<span class="filedzt">邮政编码</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.zipcode}"
							name="zipcode" class="zipCode" nullmsg="请输入邮政编码" />
					</td>
				</tr>
				<!--单位负责人-->
				<tr>
					<td align="right">
						<span class="filedzt">单位负责人</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.principal}"
							name="principal" class="responsible" nullmsg="请输单位负责人" />
					</td>
					<td align="right">
						<span class="filedzt">企业登记注册类型</span>
					</td>
					<td class="value">
						<select id="type3" name="registerstyle"
							value="${traveldataannual.registerstyle}" class="location"
							style="width: 145px">
							<option value="国有"
								<c:if test="${'国有' eq traveldataannual.registerstyle}">selected</c:if>>国有</option>
							<option value="集体"
								<c:if test="${'集体' eq traveldataannual.registerstyle}">selected</c:if>>集体</option>
							<option value="股份合作"
								<c:if test="${'股份合作' eq traveldataannual.registerstyle}">selected</c:if>>股份合作</option>
							<option value="国有联营"
								<c:if test="${'国有联营' eq traveldataannual.registerstyle}">selected</c:if>>国有联营</option>
							<option value="集体联营"
								<c:if test="${'集体联营' eq traveldataannual.registerstyle}">selected</c:if>>集体联营</option>
							<option value="国有独资公司"
								<c:if test="${'国有独资公司' eq traveldataannual.registerstyle}">selected</c:if>>国有独资公司</option>
							<option value="其他有限责任公司"
								<c:if test="${'其他有限责任公司' eq traveldataannual.registerstyle}">selected</c:if>>其他有限责任公司</option>
							<option value="股份有限公司"
								<c:if test="${'股份有限公司' eq traveldataannual.registerstyle}">selected</c:if>>股份有限公司</option>
							<option value="私营独资"
								<c:if test="${'私营独资' eq traveldataannual.registerstyle}">selected</c:if>>私营独资</option>
							<option value="私营合伙"
								<c:if test="${'私营合伙' eq traveldataannual.registerstyle}">selected</c:if>>私营合伙</option>
							<option value="私营有限责任公司"
								<c:if test="${'私营有限责任公司' eq traveldataannual.registerstyle}">selected</c:if>>私营有限责任公司</option>
							<option value="私营股份有限公司"
								<c:if test="${'私营股份有限公司' eq traveldataannual.registerstyle}">selected</c:if>>私营股份有限公司</option>
							<option value="其他"
								<c:if test="${'其他' eq traveldataannual.registerstyle}">selected</c:if>>其他</option>
							<option value="与港澳台商合资经营"
								<c:if test="${'与港澳台商合资经营' eq traveldataannual.registerstyle}">selected</c:if>>与港澳台商合资经营</option>
							<option value="与港澳台商合作经营"
								<c:if test="${'与港澳台商合作经营' eq traveldataannual.registerstyle}">selected</c:if>>与港澳台商合作经营</option>
							<option value="港澳台商投资股份有限公司"
								<c:if test="${'港澳台商投资股份有限公司' eq traveldataannual.registerstyle}">selected</c:if>>港澳台商独资</option>
							<option value="港澳台商投资股份有限公司"
								<c:if test="${'港澳台商投资股份有限公司' eq traveldataannual.registerstyle}">selected</c:if>>港澳台商投资股份有限公司</option>
							<option value="其他港澳台商投资"
								<c:if test="${'其他港澳台商投资' eq traveldataannual.registerstyle}">selected</c:if>>其他港澳台商投资</option>
							<option value="中外合资经营"
								<c:if test="${'中外合资经营' eq traveldataannual.registerstyle}">selected</c:if>>中外合资经营</option>
							<option value="中外合作经营"
								<c:if test="${'中外合作经营' eq traveldataannual.registerstyle}">selected</c:if>>中外合作经营</option>
							<option value="外资企业"
								<c:if test="${'外资企业' eq traveldataannual.registerstyle}">selected</c:if>>外资企业</option>
							<option value="外商投资股份有限公司"
								<c:if test="${'外商投资股份有限公司' eq traveldataannual.registerstyle}">selected</c:if>>外商投资股份有限公司</option>
							<option value="其他外商投资"
								<c:if test="${'其他外商投资' eq traveldataannual.registerstyle}">selected</c:if>>其他外商投资</option>
							<option value="国有与集体联营"
								<c:if test="${'国有与集体联营' eq traveldataannual.registerstyle}">selected</c:if>>国有与集体联营</option>
							<option value="其他联营"
								<c:if test="${'其他联营' eq traveldataannual.registerstyle}">selected</c:if>>其他联营</option>
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
							<input type="radio" value="1" name="businessexit"
								<c:if test="${'1' == traveldataannual.businessexit}">checked="checked"</c:if>>
							是
						</span>
						<span>
							<input type="radio" value="2" name="businessexit"
								<c:if test="${'2' == traveldataannual.businessexit}">checked="checked"ked</c:if>>
							否
						</span>
					</td>
					<td align="right">
						<span class="filedzt">是否经营边境游</span>
					</td>
					<td class="value" width="35%">
						<input type="radio" value="1" name="businessborder"
							<c:if test="${'1' == traveldataannual.businessborder}">checked="checked"</c:if>>
						是
						<input type="radio" value="2" name="businessborder"
							<c:if test="${'2' == traveldataannual.businessborder}">checked="checked"</c:if>>
						否
					</td>
				</tr>
				<!--旅行社协会会员-->
				<tr>
					<td align="right">
						<span class="filedzt">旅行社协会会员</span>
					</td>
					<td class="value" colspan="2">
						<input type="radio" value="1" name="ismember"
							<c:if test="${'1' == traveldataannual.ismember}">checked="checked"</c:if> />
						是
						<input type="radio" value="2" name="ismember"
							<c:if test="${'2' == traveldataannual.ismember}">checked="checked"</c:if> />
						否
					</td>
					<td align="right">
						<span class="filedzt">QQ：</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.qq}" name="qq"
							class="qqId" nullmsg="请输入QQ" />
					</td>
				</tr>
				<!-- 填报人、填报人电话 -->
				<tr>
					<td align="right">
						<span class="filedzt">填报人</span>
					</td>
					<td class="value" colspan="2">
						<input type="text" value="${traveldataannual.filler}"
							name="filler" datatype="*" class="mobile" />
					</td>
					<td align="right">
						<span class="filedzt">填报人电话</span>
					</td>
					<td class="value">
						<input type="text" value="${traveldataannual.fillerTel}"
							name="fillerTel" datatype="m" errormsg="请输入正确的手机号码"
							nullmsg="请输手机号" />
					</td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
</body>