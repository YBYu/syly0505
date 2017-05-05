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
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicController.do?saveOrUpdate" usePlugin="password" layout="table">

			<input  id="scenicid" type="hidden" name="scenicid" value="${scenicid}"/>
		<input  type="hidden"  name="userId" value="${scenicdata.userId }"/>
		<input type="hidden" name="score" value="${scenicdata.score }">
<table style="width:780px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<tr>
        		<td align="right">景区名称</td>
        		<td><input type="text" value="${scenicdata.name}"name="name"  style="width: 262px"></td>
            	<td align="right" width="16%">景区编号</td>
                <td class="value" width="16%" colspan="2"><input type="text" placeholder=" 无须填写" value="${scenicdata.code}" readonly="readonly" name="code" class="regionNum" errormsg="许可证编号不能为空"/><span class="red"></span></td>
                
            </tr>
            <tr>
            <td align="right" width="16%">所属辖区 </td>
                <td class="value" width="30%">
                <select id="type3"   value="${scenicdata.area}" name="area" class="location" style="width: 146px">
							<option value="0" <c:if test="${'0' eq scenicdata.area}">selected</c:if>>市辖区</option>
							<option value="1" <c:if test="${'1' eq scenicdata.area}">selected</c:if>>崖州区</option>
							<option value="2" <c:if test="${'2' eq scenicdata.area}">selected</c:if>>海棠区</option>
							<option value="3" <c:if test="${'3' eq scenicdata.area}">selected</c:if>>天涯区</option>
							<option value="4" <c:if test="${'4' eq scenicdata.area}">selected</c:if>>吉阳区</option>
							<option value="5" <c:if test="${'5' eq scenicdata.area}">selected</c:if>>三亚市</option> 
							 
				</select>     
                </td>
				<td align="right" width="16%"><span class="filedzt">邮&nbsp;&nbsp;编</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.zipcode}" name="zipcode" class="companyname" datatype="p" errormsg="请输入正确的邮编" /><span class="red"></span></td>
			</tr>
			<tr>
                <td align="right" width="16%"><span class="filedzt">网&nbsp;&nbsp;址</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.url}" name="url" class="password"   /></td>
			
            <!--英文名称-->
            
				<td align="right" width="16%"><span class="filedzt">投资主体</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.investunit}" name="investunit" class="englishname" datatype="*"   /></td>
               </tr>
               <tr>
                <td align="right" width="16%"><span class="filedzt">上级主管单位</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.superiorunit}" name="superiorunit" class="ogCode" datatype="*"   /></td>
				<td align="right" width="16%"><span class="filedzt">所属湾区</span></td>
				<td class="value" width="16%" colspan="2">
				<select id="bayType" name="bayType"  datatype="num" errormsg="请选择">
						<option value="">请选择 </option>
                    	<option value="0" <c:if test="${'0' eq scenicdata.bayType }">selected</c:if>>市区</option>
                        <option value="1" <c:if test="${'1' eq scenicdata.bayType }">selected</c:if>>亚龙湾</option>
                        <option value="2" <c:if test="${'2' eq scenicdata.bayType }">selected</c:if>>大东海</option>
                        <option value="3" <c:if test="${'3' eq scenicdata.bayType }">selected</c:if>>三亚湾</option>
                        <option value="4" <c:if test="${'4' eq scenicdata.bayType }">selected</c:if>>海棠湾</option>
                    </select></td>
            <!--统一社会信用代码-->
           
				
					</tr>
					<tr>
						<td align="right" width="16%"><span class="filedzt">机构性质</span></td>
				<td class="value" width="16%" colspan="4">
				<select id="first"  name="first"  datatype="num" errormsg="请选择机构性质">
					<option  <c:if test="${empty ssp1.id}">selected</c:if>>请选择</option>
					<option value="1" <c:if test="${'1' eq ssp1.id}">selected</c:if>>行政单位</option>
					<option value="3" <c:if test="${'3' eq ssp1.id}">selected</c:if>>事业单位</option>
					<option value="5" <c:if test="${'5' eq ssp1.id}">selected</c:if>>企业</option>
					<option value="32" <c:if test="${'32' eq ssp1.id}">selected</c:if>>部队</option>
					<option value="34" <c:if test="${'34' eq ssp1.id}">selected</c:if>>其他</option>		
				</select>
				<select id="second" name="second">
					<c:if test="${'2' eq ssp2.id}">
					<option value="2" >行政单位</option>
				</c:if>
				<c:if test="${'4' eq ssp2.id}">
					<option value="4" >事业单位</option>
				</c:if>
				<c:if test="${'6' eq ssp2.id}">
					<option value="6" >内资</option>
				</c:if>
				<c:if test="${'22' eq ssp2.id}">
					<option value="22" >港澳台投资</option>
				</c:if>
				<c:if test="${'27' eq ssp2.id}">
					<option value="27" >外商投资</option>
				</c:if>
				<c:if test="${'33' eq ssp2.id}">
					<option value="33" >部队</option>
				</c:if>
				<c:if test="${'35' eq ssp2.id}">
					<option value="35" >其他</option>
				</c:if>
				
				</select>
				<select id="third" name="third">
				<c:if test="${'7' eq ssp3.id}">
					<option value="7" >国有</option>
				</c:if>
				<c:if test="${'8' eq ssp3.id}">
					<option value="8" >集体</option>
				</c:if>
				<c:if test="${'9' eq ssp3.id}">
					<option value="9" >股份合作</option>
				</c:if>
				<c:if test="${'10' eq ssp3.id}">
					<option value="10" >国有联营</option>
				</c:if>
				<c:if test="${'11' eq ssp3.id}">
					<option value="11" >集体联营</option>
				</c:if>
				<c:if test="${'12' eq ssp3.id}">
					<option value="12" >国有与集体联营</option>
				</c:if>
				<c:if test="${'13' eq ssp3.id}">
					<option value="13" >其他联营</option>
				</c:if>
				<c:if test="${'14' eq ssp3.id}">
					<option value="14" >国有独资公司</option>
				</c:if>
				<c:if test="${'15' eq ssp3.id}">
					<option value="15" >其他有限责任公司</option>
				</c:if>
				<c:if test="${'16' eq ssp3.id}">
					<option value="16" >股份有限公司</option>
				</c:if>
				<c:if test="${'17' eq ssp3.id}">
					<option value="17" >私营独资</option>
				</c:if>
				<c:if test="${'18' eq ssp3.id}">
					<option value="18" >私营合伙</option>
				</c:if>
				<c:if test="${'19' eq ssp3.id}">
					<option value="19" >私营有限责任公司</option>
				</c:if>
				<c:if test="${'20' eq ssp3.id}">
					<option value="20" >私营股份有限公司</option>
				</c:if>
				<c:if test="${'21' eq ssp3.id}">
					<option value="21" >其他</option>
				</c:if>
				<c:if test="${'23' eq ssp3.id}">
					<option value="23" >与港澳台商合资经营</option>
				</c:if>
				<c:if test="${'24' eq ssp3.id}">
					<option value="24" >与港澳台商合作经营</option>
				</c:if>
				<c:if test="${'25' eq ssp3.id}">
					<option value="25" >港澳台商独资</option>
				</c:if>
				<c:if test="${'26' eq ssp3.id}">
					<option value="26" >港澳台商投资股份有限公司</option>
				</c:if>
				<c:if test="${'28' eq ssp3.id}">
					<option value="28" >中外合资经营</option>
				</c:if>
				<c:if test="${'29' eq ssp3.id}">
					<option value="29" >中外合作经营</option>
				</c:if>
				<c:if test="${'30' eq ssp3.id}">
					<option value="30" >外资企业</option>
				</c:if>
				<c:if test="${'31' eq ssp3.id}">
					<option value="31" >外商投资股份有限公司</option>
				</c:if>
				
				</select>
				
				<%-- <select id="type3"   value="${scenicdata.orgproperty}" name="orgproperty" class="location" style="width: 146px">
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
				 --%>
				</td>
					</tr>
					 <tr>
                <td align="right" width="16%"><span class="filedzt">地&nbsp;&nbsp;址</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.address}" name="address" class="ogCode"  datatype="*" /></td>
				<td align="right" width="16%"><span class="filedzt">景区类型</span></td>
				<td class="value" width="16%" colspan="2">
				<select id="type" value="${scenicdata.scenictype}" name="scenictype" class="area" style="width: 146px">
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
		</tr>
				<tr>
                <td align="right" width="16%"><span class="filedzt">咨询电话</span></td>
                <td class="value" width="30%"><input type="text"  value="${scenicdata.consultphone}" name="consultphone" class="adCode"  datatype="c" errormsg="请填写电话号码0898-12345678格式！"/></td>
				<td align="right" width="16%"><span class="filedzt">开业时间</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text" value="${scenicdata.opentime}" onclick="WdatePicker()" name="opentime" class="Wdate" errormsg="请输手机号" /></td>
               </tr>
               <tr>
                
				<td align="right" width="16%"><span class="filedzt">等&nbsp;&nbsp;级</span></td>
				<td class="value" width="16%" >
				<select id="type" value="${scenicdata.level}" name="level"  class="level" style="width: 146px">
							<option value="6" <c:if test="${'6' eq scenicdata.level}">selected</c:if>>未评定</option>
							<option value="1" <c:if test="${'1' eq scenicdata.level}">selected</c:if>>AAAAA</option>
							<option value="2" <c:if test="${'2' eq scenicdata.level}">selected</c:if>>AAAA</option>
							<option value="3" <c:if test="${'3' eq scenicdata.level}">selected</c:if>>AAA</option>
							<option value="4" <c:if test="${'4' eq scenicdata.level}">selected</c:if>>AA</option>
							<option value="5" <c:if test="${'5' eq scenicdata.level}">selected</c:if>>A</option>
							
				</select>
				</td>
                <td align="right" width="16%"><span class="filedzt">日接待最大容量(万人)</span></td>
                <td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.daylimit}" name="daylimit" class="zipCode" datatype=num  errormsg="请输入数字"/></td>
		</tr>
					 <tr>
            <!--单位负责人-->
			
				<td align="right" width="16%"><span class="filedzt">等级公告时间</span></td>
				<td class="value" width="16%"  ><input type="text" value="${scenicdata.leveldate}" name="leveldate" class="Wdate"  onclick="WdatePicker()"   datatype="*" /></td>
               	
                <td align="right" width="16%"><span class="filedzt">面&nbsp;&nbsp;积(公顷)</span></td>
                <td class="value" width="30%"><input type="text" value="${scenicdata.acreage}" name="acreage" class="acreage"  datatype="num" errormsg="请输入数字"/></td>
		</tr>
               	 <tr>
				<td align="right" width="16%"><span class="filedzt">负责人姓名</span></td>
				<td class="value" width="16%"  ><input type="text" value="${scenicdata.chargename}" name="chargename" class="zipCode"  datatype="*" />
                </td>
                	
                <td align="right" width="16%"><span class="filedzt">负责人电话 </span></td>
                <td class="value" width="30%">
                <input type="text" value="${scenicdata.chargephone}" name="chargephone"  datatype="mc"/>
                </td>
                </tr>
                	<tr>
            <!--旅行社协会会员-->
				<td align="right" width="16%"><span class="filedzt">负责人邮箱</span></td>
				<td class="value" width="16%"  ><input type="text" value="${scenicdata.chargeemail}" name="chargeemail"  datatype="e"class="qqId"  /></td>
              
				<td align="right" width="16%"><span class="filedzt">上报人姓名</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.informantname}" name="informantname" class="qqId"   datatype="*" /></td>
                </tr>
                 <tr>
                <td align="right" width="16%"><span class="filedzt">上报人电话</span></td>
				<td class="value" width="30%"><input type="text" value="${scenicdata.informantphone}" name="informantphone"  datatype="mc"/></td>
			
			
				<td align="right" width="16%"><span class="filedzt">上报人邮箱</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${scenicdata.informantemail}" name="informantemail"  datatype="e" /></td>
                </tr>
			 <tr>
                <td align="right" width="16%"><span class="filedzt">上报人QQ</span></td>
				<td class="value" width="30%" colspan="4"><input type="text" value="${scenicdata.informantqq}" name="informantqq" class="responsible"  datatype="qq"errormsg="请输入QQ" /></td>				
                </tr>
				<tr>
					<td align="right" width="16%"><span class="filedzt">景区所获称号 </span></td>
                <td class="value" width="30%"colspan="4">
                
					<input type="checkbox" value="1" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'1' eq list }">checked</c:if></c:forEach>>国家级风景名胜区
					<input type="checkbox" value="2" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'2' eq list }">checked</c:if></c:forEach>>国家自然保护区
					<input type="checkbox" value="3" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'3' eq list }">checked</c:if></c:forEach>>世界遗产
					<input type="checkbox" value="4" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'4' eq list }">checked</c:if></c:forEach>>国家水利公园
					<input type="checkbox" value="5" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'5' eq list }">checked</c:if></c:forEach>>世界地质公园
					<input type="checkbox" value="6" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'6' eq list }">checked</c:if></c:forEach>>文明风景旅游区
					<input type="checkbox" value="7" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'7' eq list }">checked</c:if></c:forEach>>国家森林公园
					<input type="checkbox" value="8" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'8' eq list }">checked</c:if></c:forEach>>爱国主义教育基地
					<input type="checkbox" value="9" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'9' eq list }">checked</c:if></c:forEach>>国家文保单位
					<input type="checkbox" value="10" name="scenictitle" <c:forEach  items="${titlelist }" var="list"><c:if test="${'10' eq list }">checked</c:if></c:forEach>>历史文化名镇、名村
				 
                </td>
				
				</tr>

				<tr>
               	<td align="center"><span>备注</span></td>
                <td class="value" width="16%"  colspan="4"><textarea rows="10" name="remarks" style="width:100%">${scenicdata.remarks }</textarea></td>
            </tr>
		</tbody>
	</table>
	<input type="text" readonly="readonly"  value="门票信息" style="width: 500px;height: 40px;font-size: 20px">
<!-- 	<input type="button" value="添加门票信息" onclick="addTicket()" style="width: 150px;height: 60px;font-size: 20px"> -->
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addTicket()">添加门票信息</a>
	<table id="scenicTicket"style="width:750px;  font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
	<tr>
		<td>门票形式</td>
		<td><select name="tickettype" style="width: 160px"  datatype="n" errormsg="请选择门票形式">
			<option value="4" <c:if test="${'4' eq scenicdata.tickettype}">selected</c:if>>无任何票据</option>
			<option value="3" <c:if test="${'3' eq scenicdata.tickettype}">selected</c:if>>纸质+电子</option>
			<option value="2" <c:if test="${'2' eq scenicdata.tickettype}">selected</c:if>>电子</option>
			<option value="1" <c:if test="${'1' eq scenicdata.tickettype}">selected</c:if>>纸质</option>
		</select></td>
		<td>是否是一票制</td><td><input type="radio" name="ticketisOne" value="1" <c:if test="${'1' eq scenicdata.ticketisOne }">checked</c:if>  datatype="*" >是<input type="radio" name="ticketisOne" value="0" <c:if test="${'0' eq scenicdata.ticketisOne }">checked</c:if> datatype="*" >否</td>
	</tr>
		<tr style="height: 50px"><td>类型</td><td>起止时间</td><td>门票价格</td><td>营业时间</td></tr>
                				<tr id="ticket1">
	                				<td>
		                				<select id="type"  name="termType1"   style="width: 60px"  datatype="n" errormsg="请选择门票类型">
		                					<option value="null">请选择</option>
											<option value="4" <c:if test="${'4' eq scenicdata.termType1}">selected</c:if>>歇业</option>
											<option value="3" <c:if test="${'3' eq scenicdata.termType1}">selected</c:if>>平季</option>
											<option value="2" <c:if test="${'2' eq scenicdata.termType1}">selected</c:if>>淡季</option>
											<option value="1" <c:if test="${'1' eq scenicdata.termType1}">selected</c:if>>旺季</option>
										</select>
                				</td>
                				<td>
                				<input name="beginMonth1" value="${scenicdata.beginMonth1}"datatype="n" errormsg="请输入整数" style="width: 20px">月
                				<input name="beginDate1" value="${scenicdata.beginDate1}"datatype="n" errormsg="请输入整数" style="width: 20px">日~
                				<input name="endMonth1" value="${scenicdata.endMonth1}"datatype="n" errormsg="请输入整数" style="width: 20px">月
                				<input name="endDate1" value="${scenicdata.endDate1}"datatype="n" errormsg="请输入整数" style="width: 20px">日
                				</td>
                				<td>
                				<input name="price1" value="${scenicdata.price1}"datatype="num" errormsg="请输入整数" style="width: 60px">
                				</td>
                				<td>
								<input name="openHour1" value="${scenicdata.openHour1}"datatype="n" errormsg="请输入整数" style="width: 20px">时
                				<input name="openMinute1" value="${scenicdata.openMinute1}"datatype="n" errormsg="请输入整数" style="width: 20px">分~
                				<input name="closeHour1" value="${scenicdata.closeHour1}"datatype="n" errormsg="请输入整数" style="width: 20px">时
                				<input name="closeMinute1" value="${scenicdata.closeMinute1}"datatype="n" errormsg="请输入整数" style="width: 20px">分
							</td>
							<td>
							</td>
                			</tr>
                				<tr id="ticket2" style="display: none">
	                				<td>
		                				<select id="type"  name="termType2"   style="width: 60px"  >
		                					<option  value="null">请选择</option>
											<option value="4" <c:if test="${'4' eq scenicdata.termType2}">selected</c:if>>歇业</option>
											<option value="3" <c:if test="${'3' eq scenicdata.termType2}">selected</c:if>>平季</option>
											<option value="2" <c:if test="${'2' eq scenicdata.termType2}">selected</c:if>>淡季</option>
											<option value="1" <c:if test="${'1' eq scenicdata.termType2}">selected</c:if>>旺季</option>
										</select>
                				</td>
                				<td>
                				<input name="beginMonth2" value="${scenicdata.beginMonth2}"  style="width: 20px">月
                				<input name="beginDate2" value="${scenicdata.beginDate2}"  style="width: 20px">日~
                				<input name="endMonth2" value="${scenicdata.endMonth2}"  style="width: 20px">月
                				<input name="endDate2" value="${scenicdata.endDate2}"  style="width: 20px">日
                				</td>
                				<td>
                				<input name="price2" value="${scenicdata.price2}"  style="width: 60px">
                				</td>
                				<td>
								<input name="openHour2" value="${scenicdata.openHour2}" style="width: 20px">时
                				<input name="openMinute2" value="${scenicdata.openMinute2}"  style="width: 20px">分~
                				<input name="closeHour2" value="${scenicdata.closeHour2}"  style="width: 20px">时
                				<input name="closeMinute2" value="${scenicdata.closeMinute2}"  style="width: 20px">分
							</td>
							<td>
								<button type="button" onclick="delTicket(2) "  >删除</button>
							</td>
                			</tr>
                			
                			<tr id="ticket3" style="display: none">
	                				<td>
		                				<select id="type"  name="termType3"   style="width: 60px"   >
		                					<option value="null">请选择</option>
											<option value="4" <c:if test="${'4' eq scenicdata.termType3}">selected</c:if>>歇业</option>
											<option value="3" <c:if test="${'3' eq scenicdata.termType3}">selected</c:if>>平季</option>
											<option value="2" <c:if test="${'2' eq scenicdata.termType3}">selected</c:if>>淡季</option>
											<option value="1" <c:if test="${'1' eq scenicdata.termType3}">selected</c:if>>旺季</option>
										</select>
                				</td>
                				<td>
                				<input name="beginMonth3" value="${scenicdata.beginMonth3}"  style="width: 20px">月
                				<input name="beginDate3" value="${scenicdata.beginDate3}"  style="width: 20px">日~
                				<input name="endMonth3" value="${scenicdata.endMonth3}"  style="width: 20px">月
                				<input name="endDate3" value="${scenicdata.endDate3}"  style="width: 20px">日
                				</td>
                				<td>
                				<input name="price3" value="${scenicdata.price3}"  style="width: 60px">
                				</td>
                				<td>
								<input name="openHour3" value="${scenicdata.openHour3}"  style="width: 20px">时
                				<input name="openMinute3" value="${scenicdata.openMinute3}"  style="width: 20px">分~
                				<input name="closeHour3" value="${scenicdata.closeHour3}"  style="width: 20px">时
                				<input name="closeMinute3" value="${scenicdata.closeMinute3}"  style="width: 20px">分
							</td>
							<td>
								<button type="button" onclick="delTicket(3) "  >删除</button>
							</td>
                			</tr>
                			
                			<tr id="ticket4" style="display: none">
	                				<td>
		                				<select id="type"  name="termType4"   style="width: 60px"   >
		                					<option value="null">请选择</option>
											<option value="4" <c:if test="${'4' eq scenicdata.termType4}">selected</c:if>>歇业</option>
											<option value="3" <c:if test="${'3' eq scenicdata.termType4}">selected</c:if>>平季</option>
											<option value="2" <c:if test="${'2' eq scenicdata.termType4}">selected</c:if>>淡季</option>
											<option value="1" <c:if test="${'1' eq scenicdata.termType4}">selected</c:if>>旺季</option>
										</select>
                				</td>
                				<td>
                				<input name="beginMonth4" value="${scenicdata.beginMonth4}"  style="width: 20px">月
                				<input name="beginDate4" value="${scenicdata.beginDate4}" style="width: 20px">日~
                				<input name="endMonth4" value="${scenicdata.endMonth4}"  style="width: 20px">月
                				<input name="endDate4" value="${scenicdata.endDate4}"  style="width: 20px">日
                				</td>
                				<td>
                				<input name="price4" value="${scenicdata.price4}" style="width: 60px">
                				</td>
                				<td>
								<input name="openHour4" value="${scenicdata.openHour4}"  style="width: 20px">时
                				<input name="openMinute4" value="${scenicdata.openMinute4}" style="width: 20px">分~
                				<input name="closeHour4" value="${scenicdata.closeHour4}"  style="width: 20px">时
                				<input name="closeMinute4" value="${scenicdata.closeMinute4}" style="width: 20px">分
							</td>
							<td>
								<button type="button" onclick="delTicket(4) "  >删除</button>
							</td>
                			</tr>
                			
                			<%-- <c:if test="${empty scenicdata.termType5  or 'null' eq scenicdata.termType5}">
                			<tr id="ticket5"   >
                			</c:if>
                			<c:if test="${'1' eq scenicdata.termType5 or '2' eq scenicdata.termType5 or '3' eq scenicdata.termType5 or '4' eq scenicdata.termType5}">--%>
                				<tr id="ticket5" style="display: none" > 
	                				<td>
		                				<select id="type"  name="termType5"   style="width: 60px"  >
		                					<option value="null">请选择</option>
											<option value="4" <c:if test="${'4' eq scenicdata.termType5}">selected</c:if>>歇业</option>
											<option value="3" <c:if test="${'3' eq scenicdata.termType5}">selected</c:if>>平季</option>
											<option value="2" <c:if test="${'2' eq scenicdata.termType5}">selected</c:if>>淡季</option>
											<option value="1" <c:if test="${'1' eq scenicdata.termType5}">selected</c:if>>旺季</option>
										</select>
                				</td>
                				<td >
                				<input name="beginMonth5" value="${scenicdata.beginMonth5}" style="width: 20px">月
                				<input name="beginDate5" value="${scenicdata.beginDate5}" style="width: 20px">日~
                				<input name="endMonth5" value="${scenicdata.endMonth5}" style="width: 20px">月
                				<input name="endDate5" value="${scenicdata.endDate5}" style="width: 20px">日
                				</td>
                				<td>
                				<input name="price5" value="${scenicdata.price5}" style="width: 60px">
                				</td>
                				<td>
								<input name="openHour5" value="${scenicdata.openHour5}" style="width: 20px">时
                				<input name="openMinute5" value="${scenicdata.openMinute5}" style="width: 20px">分~
                				<input name="closeHour5" value="${scenicdata.closeHour5}" style="width: 20px">时
                				<input name="closeMinute5" value="${scenicdata.closeMinute5}" style="width: 20px">分
							</td>
							<td>
								<button type="button" onclick="delTicket(5) "  >删除</button>
							</td>
                			</tr>
                			 
	</table>
</t:formvalid>
</body>
<script type="text/javascript">
//删除门票
	function delTicket(a){
	 
		$("#ticket"+a+" input").val("");
		$("#ticket"+a+" select").val("");
		$("#ticket"+a).remove();
	}
	//添加门票
	function addTicket(){
		/* var sid=$("#scenicid").val();
		var button = [{  
            name: "确定",
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	 saveObj();
            
		
            }
		}
		];
	 	createwindowbutton("添加门票", "scenicSpotTicketController.do?add&sid="+sid,button, 400, 300); */
	 		$("tr:hidden").each(function(){
	 			$(this).show();
	 				return false;
	 		});
	 	
	}
	//景区机构性质的加载
	$(document).ready(function(){
		var ticekt2='${scenicdata.termType2}';
	 	if(ticekt2=='1'||ticekt2=='2'||ticekt2=='3'||ticekt2=='4'){
	 		$("#ticket2").show();
	 	}
	 	var ticekt3='${scenicdata.termType3}';
	 	if(ticekt3=='1'||ticekt3=='2'||ticekt3=='3'||ticekt3=='4'){
	 		$("#ticket3").show();
	 	}
	 	var ticekt4='${scenicdata.termType4}';
		
	 	if(ticekt4=='1'||ticekt4=='2'||ticekt4=='3'||ticekt4=='4'){
	 		$("#ticket4").show();
	 	}
	 	var ticekt5='${scenicdata.termType5}';
	 	if(ticekt5=='1'||ticekt5=='2'||ticekt5=='3'||ticekt5=='4'){
	 		$("#ticket5").show();
	 	}
	 $("#first").change(function() {  
		var parentid =	$(this).val(); 
		 $("select[name='third']").empty();  
		$.ajax({
			type:"get",
			data:{"parentid":parentid},
	        url:"scenicController.do?propertieslist",       
	          success: function(data) {
	        	  var dataObj=eval("("+data+")"); 
	        	 $("#second").empty();
	        	
	        	 for(var i = 0; i < dataObj.length; i++) {  
                     var option = "<option value='"+dataObj[i].id+"'>"+dataObj[i].propertiesName+"</option>";
                     $("#second").append(option);  
                 }  
	         }  
		});
	 });
	 $("#second").change(function() {  
			var parentid =	$(this).val(); 
			$.ajax({
				type:"get",
				data:{"parentid":parentid},
		        url:"scenicController.do?propertieslist1",       
		          success: function(data) {
		        	  var dataObj=eval("("+data+")"); 
		        	 $("#third").empty();
		        	 for(var i = 0; i < dataObj.length; i++) {  
	                     var option = "<option value='"+dataObj[i].id+"'>"+dataObj[i].propertiesName+"</option>";
	                     $("#third").append(option);  
	                 }  
		         }  
			});
		 });

});

</script>