<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">

<t:base type="jquery,easyui,tools"></t:base>
</head>
<!-- <body style="overflow-y: hidden" scroll="no"> -->
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicWeekController.do?save" usePlugin="password" layout="table">
	<input id="monthid" type="hidden" value="${monthid}">
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->
<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
		
			<tr>
                <td align="right" width="16%"><span class="filedzt">景区名称</span></td>
                <td class="value" width="30%"><input type="text" readonly="readonly" value="${scenicdata.name}"name="name" class="name"  errormsg="请输入景区名称"/></td>
			
            
            
				<td align="right" width="16%"><span class="filedzt">景区编号</span></td>
				<td class="value" width="16%" colspan="2"><input readonly="readonly"  type="text" value="${scenicdata.code}" name="code" class="regionNum" errormsg="许可证编号不能为空" /></td>
               </tr>
		 <!--    <div class="MainInfo_tit pdtb10">
            	-数据
            </div> -->
        	<!--许可证号-->
        	<%-- 
        	<tr>
        		<td align="right" width="16%"><span class="filedzt">景区名称</span></td>
        		<td><input type="text" readonly="readonly"  value="${scenicdata.name}"name="name"></td>
            	<td align="right" width="16%"><span class="filedzt">景区编号</span></td>
                <td class="value" width="16%" colspan="2"><input readonly="readonly" type="text" value="${scenicdata.code}" name="code" class="regionNum" errormsg="许可证编号不能为空"/><span class="red"></span></td>
                
            </tr>  --%>
            <!--单位名称-->
            <tr>
            <td align="right" width="16%">所属辖区 </td>
                <td class="value" width="30%">
                <select id="type3"   readonly="readonly" value="${scenicdata.area}" name="area" class="location" style="width: 146px">
							<option value="0" <c:if test="${'0' eq scenicdata.area}">selected</c:if>>市辖区</option>
							<option value="1" <c:if test="${'1' eq scenicdata.area}">selected</c:if>>崖州区</option>
							<option value="2" <c:if test="${'2' eq scenicdata.area}">selected</c:if>>海棠区</option>
							<option value="3" <c:if test="${'3' eq scenicdata.area}">selected</c:if>>天涯区</option>
							<option value="4" <c:if test="${'4' eq scenicdata.area}">selected</c:if>>吉阳区</option>
							<option value="5" <c:if test="${'5' eq scenicdata.area}">selected</c:if>>三亚市</option> 
				</select>     
                </td>
				<td align="right" width="16%"><span class="filedzt">邮&nbsp;&nbsp;编</span></td>
				<td class="value" width="16%" colspan="2"><input readonly="readonly" type="text" value="${scenicdata.zipcode}" name="zipcode" class="companyname" errormsg="请输入单位名称" /><span class="red"></span></td>
			</tr>
			<tr>
                <td align="right" width="16%"><span class="filedzt">网&nbsp;&nbsp;址</span></td>
                <td class="value" width="30%"><input type="text" readonly="readonly" value="${scenicdata.url}" name="url" class="password"  errormsg="请输入密码"/></td>
			
            <!--英文名称-->
            
				<td align="right" width="16%"><span class="filedzt">投资主体</span></td>
				<td class="value" width="16%" colspan="2"><input readonly="readonly"  type="text" value="${scenicdata.investunit}" name="investunit" class="englishname" errormsg="请输入英文名称" /></td>
               </tr>
               <tr>
                <td align="right" width="16%"><span class="filedzt">上级主管单位</span></td>
                <td class="value" width="30%"><input readonly="readonly" type="text" value="${scenicdata.superiorunit}" name="superiorunit" class="ogCode" errormsg="请输入组织机构代码"/></td>
			
            <!--统一社会信用代码-->
           
				<td align="right" width="16%"><span class="filedzt">机构性质</span></td>
				<td class="value" width="16%" colspan="2">
				<c:if test="${not empty ssp1.propertiesName }">
				<input type="text" disabled="disabled" value="${ssp1.propertiesName }" style="width: 40px">-
				</c:if><c:if test="${not empty ssp2.propertiesName }"><input type="text" disabled="disabled" value="${ssp2.propertiesName }"style="width: 50px">-</c:if><c:if test="${not empty ssp3.propertiesName }"><input type="text" disabled="disabled" value="${ssp3.propertiesName }"style="width: 80px"></c:if>
			<%--  <select id="type3"  disabled="disabled"  value="${scenicdata.orgproperty}" name="orgproperty" class="location" style="width: 146px">
 						<option value="0" <c:if test="${empty scenicdata.orgproperty}">selected</c:if>>请选择</option>
 							<option value="2" <c:if test="${'2' eq scenicdata.orgproperty}">selected</c:if>>行政单位</option>
							<option value="4" <c:if test="${'4' eq scenicdata.orgproperty}">selected</c:if>>事业单位</option>
							<option value="35" <c:if test="${'35' eq scenicdata.orgproperty}">selected</c:if>>其他</option>
							<option value="1" <c:if test="${'8' eq scenicdata.orgproperty}">selected</c:if>>集体</option>
							<option value="2" <c:if test="${'9' eq scenicdata.orgproperty}">selected</c:if>>股份合作</option>
							<option value="3" <c:if test="${'10' eq scenicdata.orgproperty}">selected</c:if>>国有联营</option>
							<option value="4" <c:if test="${'11' eq scenicdata.orgproperty}">selected</c:if>>集体联营</option> 
				            <option value="5" <c:if test="${'14' eq scenicdata.orgproperty}">selected</c:if>>国有独资公司</option>
							<option value="6" <c:if test="${'15' eq scenicdata.orgproperty}">selected</c:if>>其他有限责任公司</option>
							<option value="7"  <c:if test="${'16' eq scenicdata.orgproperty}">selected</c:if>>股份有限公司</option>
							<option value="8" <c:if test="${'17' eq scenicdata.orgproperty}">selected</c:if>>私营独资</option>
							<option value="9" <c:if test="${'18' eq scenicdata.orgproperty}">selected</c:if>>私营合伙</option> 
				            <option value="10" <c:if test="${'19' eq scenicdata.orgproperty}">selected</c:if>>私营有限责任公司</option>
							<option value="11" <c:if test="${'20' eq scenicdata.orgproperty}">selected</c:if>>私营股份有限公司</option>
							<option value="12"  <c:if test="${'21' eq scenicdata.orgproperty}">selected</c:if>>其他</option>
							<option value="13" <c:if test="${'23' eq scenicdata.orgproperty}">selected</c:if>>与港澳台商合资经营</option>
							<option value="14" <c:if test="${'24' eq scenicdata.orgproperty}">selected</c:if>>与港澳台商合作经营</option> 
				            <option value="15" <c:if test="${'25' eq scenicdata.orgproperty}">selected</c:if>>港澳台商独资</option>
							<option value="16" <c:if test="${'26' eq scenicdata.orgproperty}">selected</c:if>>港澳台商投资股份有限公司</option>
							<option value="18" <c:if test="${'28' eq scenicdata.orgproperty}">selected</c:if>>中外合资经营</option>
							<option value="19" <c:if test="${'29' eq scenicdata.orgproperty}">selected</c:if>>中外合作经营</option> 
				            <option value="20" <c:if test="${'30' eq scenicdata.orgproperty}">selected</c:if>>外资企业</option>
							<option value="21" <c:if test="${'31' eq scenicdata.orgproperty}">selected</c:if>>外商投资股份有限公司</option>
                            <option value="23" <c:if test="${'12' eq scenicdata.orgproperty}">selected</c:if>>国有与集体联营</option> 
							
				</select> --%>
				
				</td>
					</tr>
					 <tr>
                <td align="right" width="16%"><span class="filedzt">地&nbsp;&nbsp;址</span></td>
                <td class="value" width="30%"><input type="text" readonly="readonly" value="${scenicdata.address}" name="address" class="ogCode" errormsg="请输入组织机构代码"/></td>
		
            <!--法人代表、行政区域代码-->
           
				<td align="right" width="16%"><span class="filedzt">景区类型</span></td>
				<td class="value" width="16%" colspan="2">
				<select id="type" readonly="readonly" value="${scenicdata.scenictype}" name="scenictype" class="area" style="width: 146px">
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
				
				
				
				
				
<!--  				
				<input type="text" value="${scenicdata.scenictype}" name="scenictype" class="deligate" errormsg="请输入法人代表" /></td>
-->				</tr>
				<tr>
                <td align="right" width="16%"><span class="filedzt">咨询电话</span></td>
                <td class="value" width="30%"><input type="text" readonly="readonly"  value="${scenicdata.consultphone}" name="consultphone" class="adCode" errormsg="请输入行政区域代码"/></td>
		
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            
				<td align="right" width="16%"><span class="filedzt">开业时间</span></td>
				<td class="value" width="16%" colspan="2"><input readonly="readonly" type="text" value="${scenicdata.opentime} "   name="opentime"  errormsg="请输手机号" /></td>
               </tr>
               <tr>
                
				<td align="right" width="16%"><span class="filedzt">等&nbsp;&nbsp;级</span></td>
				<td class="value" width="16%" >
				<select id="type" value="${scenicdata.level}" name="level"  class="level" style="width: 146px" readonly="readonly">
							<option value="1" <c:if test="${'1' eq scenicdata.level}">selected</c:if>>AAAAA</option>
							<option value="2" <c:if test="${'2' eq scenicdata.level}">selected</c:if>>AAAA</option>
							<option value="3" <c:if test="${'3' eq scenicdata.level}">selected</c:if>>AAA</option>
							<option value="4" <c:if test="${'4' eq scenicdata.level}">selected</c:if>>AA</option>
							<option value="5" <c:if test="${'5' eq scenicdata.level}">selected</c:if>>A</option>
							<option value="6" <c:if test="${'6' eq scenicdata.level}">selected</c:if>>未评定</option>	
				</select>
				
				</td>
					
                <td align="right" width="16%"><span class="filedzt">日接待最大容量(万人)</span></td>
                <td class="value" width="16%" colspan="2"><input readonly="readonly" type="text" value="${scenicdata.daylimit}" name="daylimit" class="zipCode" errormsg="请输入邮政编码"/></td>
			</tr>
					 <tr>
				<td align="right" width="16%"><span class="filedzt">等级公告时间</span></td>
				<td class="value" width="16%"  ><input type="text" readonly="readonly" value="${scenicdata.leveldate} " name="leveldate" class="weburl" errormsg="请输网址" /></td>
               	
                <td align="right" width="16%"><span class="filedzt">面&nbsp;&nbsp;积(公顷)</span></td>
                <td class="value" width="30%"><input type="text" readonly="readonly"  value="${scenicdata.acreage}" name="acreage" class="acreage" errormsg="请输入邮政编码"/></td>
			</tr>
               	 <tr>
				<td align="right" width="16%"><span class="filedzt">负责人姓名</span></td>
				<td class="value" width="16%"  ><input type="text" readonly="readonly" value="${scenicdata.chargename}" name="chargename" class="zipCode" errormsg="请输入邮政编码"/>
                </td>
                	
                <td align="right" width="16%"><span class="filedzt">负责人电话 </span></td>
                <td class="value" width="30%">
                <input type="text" value="${scenicdata.chargephone}" name="chargephone" readonly="readonly" />
                </td>
                </tr>
                	<tr>
            <!--旅行社协会会员-->
				<td align="right" width="16%"><span class="filedzt">负责人邮箱</span></td>
				<td class="value" width="16%"  ><input type="text" readonly="readonly" value="${scenicdata.chargeemail}" name="chargeemail" class="qqId" errormsg="请输入QQ" /></td>
              
				<td align="right" width="16%"><span class="filedzt">上报人姓名</span></td>
				<td class="value" width="16%" colspan="2"><input readonly="readonly" type="text" value="${scenicdata.informantname}" name="informantname" class="qqId" errormsg="请输入QQ" /></td>
                </tr>
                 <tr>
                <td align="right" width="16%"><span class="filedzt">上报人电话</span></td>
				<td class="value" width="30%"><input type="text" readonly="readonly" value="${scenicdata.informantphone}" name="informantphone"/></td>
			
			
				<td align="right" width="16%"><span class="filedzt">上报人邮箱</span></td>
				<td class="value" width="16%" colspan="2"><input readonly="readonly" type="text" value="${scenicdata.informantemail}" name="informantemail" class="qqId" errormsg="请输入QQ" /></td>
                </tr>
			 <tr>
                <td align="right" width="16%"><span class="filedzt">上报人QQ</span></td>
				<td class="value" width="30%" ><input type="text" readonly="readonly" value="${scenicdata.informantqq}" name="informantqq" class="responsible" errormsg="请输单位负责人" /></td>
				<td align="right" width="16%"><span class="filedzt">所属湾区</span></td>
				<td class="value" width="16%" colspan="2">
				<select id="area" name="bayType"  >
						<option value="">请选择 </option>
                    	<option value="0" <c:if test="${'0' eq scenicdata.bayType }">selected</c:if>>市区</option>
                        <option value="1" <c:if test="${'1' eq scenicdata.bayType }">selected</c:if>>亚龙湾</option>
                        <option value="2" <c:if test="${'2' eq scenicdata.bayType }">selected</c:if>>大东海</option>
                        <option value="3" <c:if test="${'3' eq scenicdata.bayType }">selected</c:if>>三亚湾</option>
                        <option value="4" <c:if test="${'4' eq scenicdata.bayType }">selected</c:if>>海棠湾</option>
                    </select></td>
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
                <tr  >
                	<td align="center"><span>门票信息</span></td>
                	<td class="value" width="16%"  colspan="4"  >
                		<table style="width: 530px" >
							<tr>
								<td>门票形式</td>
								<td><select name="tickettype" style="width: 160px"
									 >
										<option value="4"
											<c:if test="${'4' eq scenicdata.tickettype}">selected</c:if>>无任何票据</option>
										<option value="3"
											<c:if test="${'3' eq scenicdata.tickettype}">selected</c:if>>纸质+电子</option>
										<option value="2"
											<c:if test="${'2' eq scenicdata.tickettype}">selected</c:if>>电子</option>
										<option value="1"
											<c:if test="${'1' eq scenicdata.tickettype}">selected</c:if>>纸质</option>
								</select></td>
								<td>是否是一票制</td>
								<td>
								
								<input type="radio" name="ticketisOne"  <c:if test="${'1' eq scenicdata.ticketisOne }">checked</c:if> >是<input
									type="radio" name="ticketisOne"  <c:if test="${'0' eq scenicdata.ticketisOne }">checked</c:if>>否</td>
							</tr>
							<tr><td>类型</td><td>起止时间</td><td>门票价格</td><td>营业时间</td></tr>
                				<c:if test="${scenicdata.termType1!='null' and not empty scenicdata.termType1}">
                			<tr>
                				<td><c:if test="${'1' eq scenicdata.termType1}">旺季</c:if>
                					<c:if test="${'2' eq scenicdata.termType1}">淡季</c:if>
                					<c:if test="${'3' eq scenicdata.termType1}">平季</c:if>
                					<c:if test="${'4' eq scenicdata.termType1}">歇业</c:if>
                				</td>
                				<td>${scenicdata.beginMonth1}月${scenicdata.beginDate1}日~${scenicdata.endMonth1}月${scenicdata.endDate1}日</td>
                				<td>${scenicdata.price1}</td>
                				<td>${scenicdata.openHour1}时${scenicdata.openMinute1}分~${scenicdata.closeHour1}时${scenicdata.closeMinute1}分</td>
                			</tr>
                			</c:if>
                			<c:if test="${scenicdata.termType2!='null'  and not empty scenicdata.termType2}">
                			<tr>
                				<td><c:if test="${'1' eq scenicdata.termType2}">旺季</c:if>
                					<c:if test="${'2' eq scenicdata.termType2}">淡季</c:if>
                					<c:if test="${'3' eq scenicdata.termType2}">平季</c:if>
                					<c:if test="${'4' eq scenicdata.termType2}">歇业</c:if></td>
                				<td>${scenicdata.beginMonth2}月${scenicdata.beginDate2}日~${scenicdata.endMonth2}月${scenicdata.endDate2}日</td>
                				<td>${scenicdata.price2}</td>
                				<td>${scenicdata.openHour2}时${scenicdata.openMinute2}分~${scenicdata.closeHour2}时${scenicdata.closeMinute2}分</td>
                			</tr>
                			</c:if>
                				<c:if test="${scenicdata.termType3!='null'  and not empty scenicdata.termType3}">
                			<tr>
                				<td><c:if test="${'1' eq scenicdata.termType3}">旺季</c:if>
                					<c:if test="${'2' eq scenicdata.termType3}">淡季</c:if>
                					<c:if test="${'3' eq scenicdata.termType3}">平季</c:if>
                					<c:if test="${'4' eq scenicdata.termType3}">歇业</c:if></td>
                				<td>${scenicdata.beginMonth3}月${scenicdata.beginDate3}日~${scenicdata.endMonth3}月${scenicdata.endDate3}日</td>
                				<td>${scenicdata.price3}</td>
                				<td>${scenicdata.openHour3}时${scenicdata.openMinute3}分~${scenicdata.closeHour3}时${scenicdata.closeMinute3}分</td>
                			</tr>
                			</c:if>
                				<c:if test="${scenicdata.termType4!='null'  and not empty scenicdata.termType4}">
                			<tr>
                				<td><c:if test="${'1' eq scenicdata.termType4}">旺季</c:if>
                					<c:if test="${'2' eq scenicdata.termType4}">淡季</c:if>
                					<c:if test="${'3' eq scenicdata.termType4}">平季</c:if>
                					<c:if test="${'4' eq scenicdata.termType4}">歇业</c:if></td>
                				<td>${scenicdata.beginMonth4}月${scenicdata.beginDate4}日~${scenicdata.endMonth4}月${scenicdata.endDate4}日</td>
                				<td>${scenicdata.price4}</td>
                				<td>${scenicdata.openHour4}时${scenicdata.openMinute4}分~${scenicdata.closeHour4}时${scenicdata.closeMinute4}分</td>
                			</tr>
                			</c:if>
                				<c:if test="${scenicdata.termType5!='null'  and not empty scenicdata.termType5} ">
                			<tr>
                				<td><c:if test="${'1' eq scenicdata.termType5}">旺季</c:if>
                					<c:if test="${'2' eq scenicdata.termType5}">淡季</c:if>
                					<c:if test="${'3' eq scenicdata.termType5}">平季</c:if>
                					<c:if test="${'4' eq scenicdata.termType5}">歇业</c:if></td>
                				<td>${scenicdata.beginMonth5}月${scenicdata.beginDate5}日~${scenicdata.endMonth5}月${scenicdata.endDate5}日</td>
                				<td>${scenicdata.price5}</td>
                				<td>${scenicdata.openHour5}时${scenicdata.openMinute5}分~${scenicdata.closeHour5}时${scenicdata.closeMinute5}分</td>
                			</tr>
                			</c:if>
                			<%-- <c:forEach items="${scenicdata.scenicSpotTicket}" var="ticket">
                			<tr>
                				<td>${ticket.type}</td>
                				<td>${ticket.beginMonth}月${ticket.beginDate}日~${ticket.endMonth}月${ticket.endDate}日</td>
                				<td>${ticket.price}</td>
                				<td>${ticket.openHour}时${ticket.openMinute}分~${ticket.closeHour}时${ticket.closeMinute}分</td>
                				</tr>
                			</c:forEach> --%>               			
                		</table>
                	 </td>
                </tr>
			<tr>
               	<td align="center"><span>备注</span></td>
                <td class="value" width="16%"  colspan="4"><textarea rows="10" name="remarks" style="width:100%" readonly="readonly">${scenicdata.remarks }</textarea></td>
            </tr>
		</tbody>
	</table>
</t:formvalid>
</body>
