<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>

<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">

<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="false" dialog="true" action="scenicController.do?saveUser" usePlugin="password" layout="table">
			<input  type="hidden" name="scenicid" value="${scenicid}"/>

<table style="width:850px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
		
		
		    <div class="MainInfo_tit pdtb10">
            	${scenicdata.name}
            </div>
        	<!--许可证号-->
        	<%-- 
        	<input type="hidden" value="${scenicdata.name}" name="name" class="regionNum" errormsg="许可证编号不能为空"/>
        	--%>
        	<tr>
            	<td align="right" width="16%">景区编号</td>
                <td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.code}" name="code" class="regionNum" errormsg="许可证编号不能为空"/><span class="red"></span></td>
                <td align="right" width="16%">所属辖区 </td>
                <td class="value" width="30%">
                <select id="type3" name="area" value="${scenicdata.area}" name="area" class="location">
							<option value="0" <c:if test="${'0' eq scenicdata.area}">selected</c:if>>市辖区</option>
							<option value="1" <c:if test="${'1' eq scenicdata.area}">selected</c:if>>崖州区</option>
							<option value="2" <c:if test="${'2' eq scenicdata.area}">selected</c:if>>海棠区</option>
							<option value="3" <c:if test="${'3' eq scenicdata.area}">selected</c:if>>天涯区</option>
							<option value="4" <c:if test="${'4' eq scenicdata.area}">selected</c:if>>吉阳区</option> 
							<option value="5" <c:if test="${'5' eq scenicdata.area}">selected</c:if>>三亚市</option> 
				</select>     
                </td>
            </tr>
            <!--单位名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">邮&nbsp;&nbsp;编</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.zipcode}" name="zipcode" class="companyname" errormsg="请输入单位名称" /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">网&nbsp;&nbsp;址</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.url}" name="url" class="password"  /></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">投资主体</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.investunit}" name="investunit" class="englishname" errormsg="请输入英文名称" /></td>
                <td align="right" width="16%"><span class="filedzt">上级主管单位</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.superiorunit}" name="superiorunit" class="ogCode" errormsg="请输入组织机构代码"/></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">机构性质</span></td>
				<td class="value" width="16%" colspan="2">
				
				<select id="type3" name="area" value="${scenicdata.orgproperty}" name="orgproperty" class="location">
							<option value="0" <c:if test="${'0' eq scenicdata.orgproperty}">selected</c:if>>国有</option>
							<option value="1" <c:if test="${'1' eq scenicdata.orgproperty}">selected</c:if>>集体</option>
							<option value="2" <c:if test="${'2' eq scenicdata.orgproperty}">selected</c:if>>股份合作</option>
							<option value="3" <c:if test="${'3' eq scenicdata.orgproperty}">selected</c:if>>国有联营</option>
							<option value="4" <c:if test="${'4' eq scenicdata.orgproperty}">selected</c:if>>集体联营</option> 
				            <option value="5" <c:if test="${'5' eq scenicdata.orgproperty}">selected</c:if>>国有独资公司</option>
							<option value="6" <c:if test="${'6' eq scenicdata.orgproperty}">selected</c:if>>其他有限责任公司</option>
							<option value="7"  <c:if test="${'7' eq scenicdata.orgproperty}">selected</c:if>>股份有限公司</option>
							<option value="8" <c:if test="${'8' eq scenicdata.orgproperty}">selected</c:if>>私营独资</option>
							<option value="9" <c:if test="${'9' eq scenicdata.orgproperty}">selected</c:if>>私营合伙</option> 
				            <option value="10" <c:if test="${'10' eq scenicdata.orgproperty}">selected</c:if>>私营有限责任公司</option>
							<option value="11" <c:if test="${'11' eq scenicdata.orgproperty}">selected</c:if>>私营股份有限公司</option>
							<option value="12"  <c:if test="${'12' eq scenicdata.orgproperty}">selected</c:if>>其他</option>
							<option value="13" <c:if test="${'13' eq scenicdata.orgproperty}">selected</c:if>>与港澳台商合资经营</option>
							<option value="14" <c:if test="${'14' eq scenicdata.orgproperty}">selected</c:if>>与港澳台商合作经营</option> 
				            <option value="15" <c:if test="${'15' eq scenicdata.orgproperty}">selected</c:if>>港澳台商独资</option>
							<option value="16" <c:if test="${'16' eq scenicdata.orgproperty}">selected</c:if>>港澳台商投资股份有限公司</option>
							<option value="17"  <c:if test="${'17' eq scenicdata.orgproperty}">selected</c:if>>其他港澳台商投资</option>
							<option value="18" <c:if test="${'18' eq scenicdata.orgproperty}">selected</c:if>>中外合资经营</option>
							<option value="19" <c:if test="${'19' eq scenicdata.orgproperty}">selected</c:if>>中外合作经营</option> 
				            <option value="20" <c:if test="${'20' eq scenicdata.orgproperty}">selected</c:if>>外资企业</option>
							<option value="21" <c:if test="${'21' eq scenicdata.orgproperty}">selected</c:if>>外商投资股份有限公司</option>
							<option value="22"  <c:if test="${'22' eq scenicdata.orgproperty}">selected</c:if>>其他外商投资</option>
                            <option value="23" <c:if test="${'23' eq scenicdata.orgproperty}">selected</c:if>>国有与集体联营</option> 
							<option value="24" <c:if test="${'24' eq scenicdata.orgproperty}">selected</c:if>>其他联营</option> 
							
				</select> 
				
				</td>
                <td align="right" width="16%"><span class="filedzt">地&nbsp;&nbsp;址</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.address}" name="address" class="ogCode" errormsg="请输入组织机构代码"/></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区类型</span></td>
				<td class="value" width="16%" colspan="2">
				<select id="type" value="${scenicdata.scenictype}" name="scenictype" class="area">
							<option value="1" <c:if test="${'1' eq scenicdata.scenictype}">selected</c:if>>自然景观</option>
							<option value="2" <c:if test="${'2' eq scenicdata.scenictype}">selected</c:if>>历史文化</option>
							<option value="3" <c:if test="${'3' eq scenicdata.scenictype}">selected</c:if>>度假休闲</option>
							<option value="4" <c:if test="${'4' eq scenicdata.scenictype}">selected</c:if>>主题游乐</option>
							<option value="5" <c:if test="${'5' eq scenicdata.scenictype}">selected</c:if>>博物馆</option>	
							<option value="6" <c:if test="${'6' eq scenicdata.scenictype}">selected</c:if>>乡村旅游</option>
							<option value="7" <c:if test="${'7' eq scenicdata.scenictype}">selected</c:if>>工业旅游</option>
							<option value="8" <c:if test="${'8' eq scenicdata.scenictype}">selected</c:if>>红色旅游</option>
							<option value="9" <c:if test="${'9' eq scenicdata.scenictype}">selected</c:if>>科技旅游</option>
							<option value="10" <c:if test="${'10' eq scenicdata.scenictype}">selected</c:if>>其他</option>
				</select>			

                <td align="right" width="16%"><span class="filedzt">咨询电话</span></td>
                <td class="value" width="30%"><input type="text"  value="${scenicdata.consultphone}" name="consultphone" class="adCode" errormsg="请输入行政区域代码"/></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
       
            <tr>
				<td align="right" width="16%"><span class="filedzt">开业时间</span></td>
				<td class="value" width="16%" colspan="2"><input class="Wdate" onClick="WdatePicker()" style="width: 150px" id="opentime" name="opentime"
		ignore="ignore" value="<fmt:formatDate value='${scenicdata.opentime}' type="date" pattern="yyyy-MM-dd"/>"></td>
                
                
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%"></td>
			</tr>
            <!--网址、邮政编码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">等&nbsp;&nbsp;级</span></td>
				<td class="value" width="16%" colspan="2">
				<select id="type" value="${scenicdata.level}" name="level"  class="level">
							<option value="1" <c:if test="${'1' eq scenicdata.level}">selected</c:if>>AAAAA</option>
							<option value="2" <c:if test="${'2' eq scenicdata.level}">selected</c:if>>AAAA</option>
							<option value="3" <c:if test="${'3' eq scenicdata.level}">selected</c:if>>AAA</option>
							<option value="4" <c:if test="${'4' eq scenicdata.level}">selected</c:if>>AA</option>
							<option value="5" <c:if test="${'5' eq scenicdata.level}">selected</c:if>>A</option>
							<option value="6" <c:if test="${'6' eq scenicdata.level}">selected</c:if>>未评定</option>		
				</select>
				
				</td>
                <td align="right" width="16%"><span class="filedzt">日接待最大容量</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.daylimit}" name="daylimit" class="zipCode" errormsg="请输入邮政编码"/></td>
			</tr>
            <!--单位负责人-->
			 <tr>
				<td align="right" width="16%"><span class="filedzt">等级公告时间</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="<fmt:formatDate value='${scenicdata.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" name="leveldate" class="weburl" errormsg="请输网址" /></td>
                <td align="right" width="16%"><span class="filedzt">面&nbsp;&nbsp;积</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.acreage}" name="acreage" class="acreage" errormsg="请输入邮政编码"/></td>
			</tr>
			
			
			
            <tr>
				<td align="right" width="16%"><span class="filedzt">负责人姓名</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.chargename}" name="chargename" class="zipCode" errormsg="请输入邮政编码"/>
                </td>
                <td align="right" width="16%"><span class="filedzt">负责人电话 </span></td>
                <td class="value" width="30%">
                <input type="text" value="${scenicdata.chargephone}" name="chargephone"/>
                </td>
			</tr>
           
            <!--旅行社协会会员-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">负责人邮箱</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.chargeemail}" name="chargeemail" class="qqId" errormsg="请输入QQ" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
				<td class="value" width="30%"></td>
			</tr>
			
			
			 <tr>
				<td align="right" width="16%"><span class="filedzt">上报人姓名</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.informantname}" name="informantname" class="qqId" errormsg="请输入QQ" /></td>
                <td align="right" width="16%"><span class="filedzt">上报人电话</span></td>
				<td class="value" width="30%"><input type="text" value="${scenicdata.informantphone}" name="informantphone"/></td>
			</tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">上报人邮箱</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.informantemail}" name="informantemail" class="qqId" errormsg="请输入QQ" /></td>
                <td align="right" width="16%"><span class="filedzt">上报人QQ</span></td>
				<td class="value" width="30%"><input type="text" value="${scenicdata.informantqq}" name="informantqq" class="responsible" errormsg="请输单位负责人" /></td>
			</tr>
			
						
			 <tr>
				<td align="right" width="16%"><span class="filedzt">门票形式</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.tickettype}" name="tickettype" class="zipCode" errormsg="请输入邮政编码"/></td>
                <td align="right" width="16%"><span class="filedzt">景区所获称号 </span></td>
                <td class="value" width="30%">
                <input type="text" value="${scenicdata.scenictitle}" name="scenictitle" class="zipCode" errormsg="请输入邮政编码"/>
                </td>
			</tr>
			
			<tr>
				<td align="right" width="16%"><span class="filedzt">门票有效期</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.ticketvalidDate}" name="ticketvalidDate" class="responsible" errormsg="请输单位负责人" /></td>
                <td align="right" width="16%"><span class="filedzt"> </span></td>
                <td class="value" width="30%">
                	
                </td>
			</tr>
			

			<tr>
               	<td><span>备注</span></td>
                <td class="value" width="16%"  colspan="4"><textarea rows="10" name="remarks" style="width:100%"></textarea></td>
            </tr>
			
            
		</tbody>
	</table>
</t:formvalid>
</body>