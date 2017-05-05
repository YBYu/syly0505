<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>

</head>
<body style="" scroll="yes">
<div style="left: 30px"> </div>

<!-- 单位是人次 type为0 -->

<t:formvalid formid="saveHoliday" refresh="false" dialog="true"  usePlugin="password" layout="table">
	<input id="id" type="hidden" value="${holiday.id }">


<table cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
             <tr>
             <td align="right" width="16%">节日类型 </td>
                <td class="value" width="30%"  colspan="2">
                <input type="text" value="<c:if test="${holiday.type ==0 }">元旦</c:if><c:if test="${holiday.type ==1 }">清明</c:if><c:if test="${holiday.type ==2 }">五一</c:if><c:if test="${holiday.type ==3 }">端午</c:if><c:if test="${holiday.type ==4 }">中秋</c:if><c:if test="${holiday.type ==5 }">春节黄金周</c:if><c:if test="${holiday.type ==6 }">十一 黄金周</c:if>" class="Wdate"  name="type"  ></td> 
                <td align="right" width="16%"><span class="filedzt">节日年份</span></td>
                <td class="value" width="30%"><input type="text" value="${holiday.year }"  class="Wdate"  name="year"  ></td>
               </tr>
            <!--法人信息-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">开始时间</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="<fmt:formatDate value="${holiday.startTime }" type="date"/>"    name="startTime"   /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">结束时间</span></td>
                <td class="value" width="30%"><input type="text" value="<fmt:formatDate value="${holiday.endTime }" type="date"/>"  name="endTime" ></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">美兰机场(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${holiday.meilanAir }"   datatype="num" name="meilanAir"   /></td>
                <td align="right" width="16%"><span class="filedzt">博鳌机场(万人)</span></td>
                <td class="value" width="30%"><input type="text" value="${holiday.boaoAir }"   datatype="num" name="boaoAir"></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">凤凰机场(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${holiday.fenghuangAir }" datatype="num" name="fenghuangAir" class="creditCode" errormsg="统一社会信用代码"/></td> 
				<td align="right" width="16%"><span class="filedzt">海峡办(万人)</span></td>
				<td class="value" width="30%" ><input type="text" value="${holiday.straitOffice }"   datatype="num" name="straitOffice"  /></td>              
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
                <td align="right" width="16%"><span class="filedzt">酒店(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${holiday.hotelNum }" datatype="num" name="hotelNum" class="creditCode" errormsg="统一社会信用代码"/></td> 
				<td align="right" width="16%"><span class="filedzt">酒店收入(万元)</span></td>
				<td class="value" width="30%" ><input type="text" value="${holiday.hotelIncome }"   datatype="num" name="hotelIncome"  /></td>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">景区数量</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${holiday.scenicNum }"   datatype="num" name="scenicNum" class="creditCode" errormsg="统一社会信用代码"/></td> 
				<td align="right" width="16%"><span class="filedzt">景区收入(万元)</span></td>
				<td class="value" width="30%" ><input type="text" value="${holiday.scenicIncome }"   datatype="num"  name="scenicIncome"  /></td>              
			</tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">乡村旅游(万人次)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${holiday.townTravelNum }"   datatype="num" name="townTravelNum" class="creditCode"  errormsg="请输入餐饮收入"/></td> 
				<td align="right" width="16%"><span class="filedzt">乡村旅游收入(万元)</span></td>
				<td class="value" width="30%" ><input type="text" value="${holiday.townTravelIncome }" datatype="num"  name="townTravelIncome"  /></td>              
			</tr> 
			 <tr>
				<td align="right" width="16%"><span class="filedzt">免税店数量</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${holiday.taxFreeNum }"   datatype="num" name="taxFreeNum" class="creditCode"  errormsg="请输入餐饮收入"/></td> 
				<td align="right" width="16%"><span class="filedzt">免税店收入(万元)</span></td>
				<td class="value" width="30%" ><input type="text"  value="${holiday.taxFreeIncome }" datatype="nun"   name="taxFreeIncome"  /></td>              
			</tr>        
		</tbody>
	</table>
 </t:formvalid>
<!-- <div style="padding:5px">
	 <a href="javascript:void(0)"   style="margin-left: 615px"  class="easyui-linkbutton" onclick="tijiao();">保存</a>
</div> -->
<!-- 单位是人天  type为1-->
<script>
/* window.setTimeout(function(e){debugger},3000); */
</script>

</body>
