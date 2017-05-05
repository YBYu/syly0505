<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>星级酒店信息编辑</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="no">
	<t:formvalid formid="saveHotel" refresh="false" dialog="true"
		callback="back" action="hotelController.do?save" usePlugin="password"
		layout="table">
		<input type="hidden" name="id" value="${hotelmsg.id }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<!--许可证号-->
				<tr>
					<td align="right" width="16%">组织机构代码</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmsg.organizationNum}"
							name="organizationNum" class="regionNum" datatype="*1-18"
							errormsg="组织机构代码最大长度 18位" readonly="readonly" />
					</td>
					<td align="right" width="16%">单位名称</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmsg.unitname}" name="unitname"
							class="accountNum" datatype="*1-100" errormsg="请输入酒店名称最大长度100位">
					</td>
				</tr>
				<!--法人信息-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">酒店法人</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmsg.legalPerson}"
							name="legalPerson" class="companyname" datatype="*"
							errormsg="请输入法人名称" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">邮政编码</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmsg.zipcode}" name="zipcode"
							datatype="p">
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">固定电话</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmsg.telephone}" name="telephone"
							datatype="c" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">手机号码</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmsg.mobile}" datatype="m"
							errormsg="手机号码不正确" name="mobile">
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">酒店星级</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<select id="cc" name="housegrade" disabled="disabled">
							<option value=""></option>
							<option ${hotelmsg.housegrade==8?'selected="selected"':''}
								value="8">五星</option>
							<option ${hotelmsg.housegrade==7?'selected="selected"':''}
								value="7">四星</option>
							<option ${hotelmsg.housegrade==6?'selected="selected"':''}
								value="6">三星</option>
							<option ${hotelmsg.housegrade==5?'selected="selected"':''}
								value="5">二星</option>
							<option ${hotelmsg.housegrade==4?'selected="selected"':''}
								value="4">一星</option>
							<option ${hotelmsg.housegrade==3?'selected="selected"':''}
								value="3">豪华</option>
							<option ${hotelmsg.housegrade==2?'selected="selected"':''}
								value="2">高档</option>
							<option ${hotelmsg.housegrade==1?'selected="selected"':''}
								value="1">舒适</option>
							<option ${hotelmsg.housegrade==0?'selected="selected"':''}
								value="0">其他</option>
						</select>
					</td>
					<td align="right" width="16%">
						<span class="filedzt">传真号码</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmsg.fax}" name="fax"
							class="adCode" datatype="*" errormsg="请输入正确信息">
					</td>
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">房间数量</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmsg.housenum}" name="housenum"
							class="deligate" datatype="n1-8" errormsg="请输入正确信息" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">床位数量</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmsg.bednum}" name="bednum"
							class="adCode" datatype="n1-8" errormsg="请输入正确信息">
					</td>
				</tr>
				<!--所在地区、详细地址-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">所在地区</span>
					</td>
					<td class="value" width="16%" colspan="2">

						<select id="area" style="width: 140px" name="city">
							<option value="default" class="opt-tit">三亚市</option>
							<!-- <option value="海口市">海口市</option> -->
						</select>
					</td>
					<td align="right" width="16%">
						<span class="filedzt">所属区</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<select id="area" name="county" style="width: 140px;" datatype="*"
							msg="请选择">
							<option value=""></option>
							<option ${hotelmsg.county=='0'?'selected':'' } value="0">市辖区</option>
							<option ${hotelmsg.county=='1'?'selected':'' } value="1">崖州区</option>
							<option ${hotelmsg.county=='2'?'selected':'' } value="2">海棠区</option>
							<option ${hotelmsg.county=='3'?'selected':'' } value="3">天涯区</option>
							<option ${hotelmsg.county=='4'?'selected':'' } value="4">吉阳区</option>
						</select>
				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">所属湾区</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<select id="area" style="width: 140px" name="bay" datatype="*"
							msg="请选择">
							<option value=""></option>
							<option ${hotelmsg.bay=='0'?'selected':''} value="0"
								class="opt-tit">市区</option>
							<option ${hotelmsg.bay=='1'?'selected':''} value="1">亚龙湾</option>
							<option ${hotelmsg.bay=='2'?'selected':''} value="2">大东海</option>
							<option ${hotelmsg.bay=='3'?'selected':''} value="3">三亚湾</option>
							<option ${hotelmsg.bay=='4'?'selected':''} value="4">海棠湾</option>
						</select>
					</td>
					<td align="right" width="16%">
						<span class="filedzt">酒店网址</span>
					</td>
					<td class="value" width="16%" colspan="4">
						<input type="text" value="${hotelmsg.weburl}" name="weburl"
							class="weburl" />
					</td>

				</tr>
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">详细地址</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmsg.address}" name="address"
							datatype="s1-100" errormsg="请输入贵公司的详细地址" class="address">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">电子邮箱</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmsg.email}" name="email"
							class="email">
					</td>
				</tr>
				<!--网址、邮政编码-->

				<!--单位负责人-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">单位负责人</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmsg.manager}" name="manager"
							class="responsible" datytype="*1-25" errormsg="最大长25位" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">企业登记注册类型</span>
					</td>
					<td class="value" width="30%">
						<select id="registerstyle" name="registerstyle"
							style="width: 140px" draggable="true" datatype="*" msg="请选择">
							<option value=""></option>
							<option ${hotelmsg.registerstyle=="国有"?"selected":""} value="国有">国有</option>
							<option ${hotelmsg.registerstyle=="集体"?"selected":""} value="集体">集体</option>
							<option ${hotelmsg.registerstyle=="股份合作"?"selected":""}
								value="股份合作">股份合作</option>
							<option ${hotelmsg.registerstyle=="国有联营"?"selected":""}
								value="国有联营">国有联营</option>
							<option ${hotelmsg.registerstyle=="集体联营"?"selected":""}
								value="集体联营">集体联营</option>
							<option ${hotelmsg.registerstyle=="国有与集体联营"?"selected":""}
								value="国有与集体联营">国有与集体联营</option>
							<option ${hotelmsg.registerstyle=="其他联营"?"selected":""}
								value="其他联营">其他联营</option>
							<option ${hotelmsg.registerstyle=="国有独资公司"?"selected":""}
								value="国有独资公司">国有独资公司</option>
							<option ${hotelmsg.registerstyle=="其他有限责任公司"?"selected":""}
								value="其他有限责任公司">其他有限责任公司</option>
							<option ${hotelmsg.registerstyle=="私营独资"?"selected":""}
								value="私营独资">私营独资</option>
							<option ${hotelmsg.registerstyle=="私营合伙"?"selected":""}
								value="私营合伙">私营合伙</option>
							<option ${hotelmsg.registerstyle=="私营有限责任公司"?"selected":""}
								value="私营有限责任公司">私营有限责任公司</option>
							<option ${hotelmsg.registerstyle=="私营股份有限公司"?"selected":""}
								value="私营股份有限公司">私营股份有限公司</option>
							<option ${hotelmsg.registerstyle=="其他"?"selected":""} value="其他">其他</option>
							<option ${hotelmsg.registerstyle=="与港澳台商合资经营"?"selected":""}
								value="与港澳台商合资经营">与港澳台商合资经营</option>
							<option ${hotelmsg.registerstyle=="与港澳台商合作经营"?"selected":""}
								value="与港澳台商合作经营">与港澳台商合作经营</option>
							<option ${hotelmsg.registerstyle=="港澳台商独资"?"selected":""}
								value="港澳台商独资">港澳台商独资</option>
							<option ${hotelmsg.registerstyle=="港澳台商投资股份有限公司"?"selected":""}
								value="港澳台商投资股份有限公司">港澳台商投资股份有限公司</option>
							<option ${hotelmsg.registerstyle=="其他港澳台商投资"?"selected":""}
								value="其他港澳台商投资">其他港澳台商投资</option>
							<option ${hotelmsg.registerstyle=="中外合资经营"?"selected":""}
								value="中外合资经营">中外合资经营</option>
							<option ${hotelmsg.registerstyle=="中外合作经营"?"selected":""}
								value="中外合作经营">中外合作经营</option>
							<option ${hotelmsg.registerstyle=="外资企业"?"selected":""}
								value="外资企业">外资企业</option>
							<option ${hotelmsg.registerstyle=="外商投资股份有限公司"?"selected":""}
								value="外商投资股份有限公司">外商投资股份有限公司</option>
							<option ${hotelmsg.registerstyle=="其他外商投资"?"selected":""}
								value="其他外商投资">其他外商投资</option>
						</select>
						<%-- <input type="text" value="${hotelmsg.legalPerson}" name="legalPerson" class="registerstyle" datatype="*" errormsg="请输法人代表" /> --%>
					</td>
				</tr>
				<!--是否经营出境游-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">填表人</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" datatype="*" errormsg="请输入填表人"
							value="${hotelmsg.writer}" name="writer">
					</td>
					<td align="right" width="16%">
						<span class="filedzt">标牌编号</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmsg.code}" name="code"
							readonly="readonly">
					</td>

				</tr>

			</tbody>
		</table>
	</t:formvalid>
</body>