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

<t:formvalid formid="saveHoliday" refresh="false" dialog="true" callback="back" action="holidayController.do?save" usePlugin="password" layout="table">
	<input id="id" type="hidden" value="${user.id }">
	<input type="hidden" value="${user.userName}"  class="regionNum"  name="mname"/>

<table  style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<!--许可证号-->
             <tr>
             <td align="right" width="16%">年份<span class="filedzt"></span></td>
             <td class="value" width="16%" colspan="2"><input type="text" class="Wdate" datatype="n" value="${year }"  name="year"   ></td>
             <td align="right" width="16%">节日类型 </td>
                <td class="value" width="30%"><select id="cc" class="easyui-combobox" name="type" style="width:145px;">  
				<option selected="selected" value="0">元旦</option>    			      
			    <option value="1">清明</option>   
			    <option value="2">五一</option>   
			    <option value="3">端午</option>   
			    <option value="4">中秋</option>
			    <option value="5">春节黄金周</option>  
			    <option value="6">十一黄金周</option>  
			      </select></td>
               </tr>
            <!--法人信息-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">开始时间</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  name="startTime" class="easyui-datebox"  /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">结束时间</span></td>
                <td class="value" width="30%"><input type="text" class="easyui-datebox"  name="endTime"  ></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">美兰机场(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  datatype="num" value="0" name="meilanAir" errormsg="请输入正确的美兰机场(万人)" readOnly /></td>
                <td align="right" width="16%"><span class="filedzt">博鳌机场(万人)</span></td>
                <td class="value" width="30%"><input type="text"  datatype="decimals" name="boaoAir"  value="0" errormsg="请输入正确的博鳌机场(万人)" readOnly></td>
			</tr>
            <!--请输入正确的-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">凤凰机场(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" datatype="decimals" name="fenghuangAir" class="creditCode" errormsg="请输入正确的凤凰机场(万人)"/></td> 
				<td align="right" width="16%"><span class="filedzt">海峡办(万人)</span></td>
				<td class="value" width="30%" ><input type="text"  datatype="decimals" name="straitOffice" value="0" errormsg="请输入正确的海峡办(万人)" readOnly /></td>              
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
                <td align="right" width="16%"><span class="filedzt">酒店(万人)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"datatype="decimals" name="hotelNum" class="creditCode" errormsg="请输入正确的酒店(万人)"/></td> 
				<td align="right" width="16%"><span class="filedzt">酒店收入(万元)</span></td>
				<td class="value" width="30%" ><input type="text"  datatype="decimals" name="hotelIncome" errormsg="请输入正确的酒店收入(万元)" /></td>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">景区数量</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"   datatype="n" name="scenicNum" class="creditCode" errormsg="请输入正确的景区数量"/></td> 
				<td align="right" width="16%"><span class="filedzt">景区收入(万元)</span></td>
				<td class="value" width="30%" ><input type="text"  datatype="decimals"  name="scenicIncome" errormsg="请输入正确的景区收入(万元)" /></td>              
			</tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">乡村旅游(万人次)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  datatype="decimals" name="townTravelNum" class="creditCode"  errormsg="请输入正确的乡村旅游(万人次)"/></td> 
				<td align="right" width="16%"><span class="filedzt">乡村旅游收入(万元)</span></td>
				<td class="value" width="30%" ><input type="text" datatype="decimals"   name="townTravelIncome" errormsg="请输入正确的乡村旅游收入(万元)" /></td>              
			</tr> 
			 <tr>
				<td align="right" width="16%"><span class="filedzt">免税店数量</span></td>
				<td class="value" width="16%" colspan="2"><input type="text"  datatype="n" name="taxFreeNum" class="creditCode"  errormsg="请输入正确的免税店数量"/></td> 
				<td align="right" width="16%"><span class="filedzt">免税店收入(万元)</span></td>
				<td class="value" width="30%" ><input type="text" datatype="decimals"   name="taxFreeIncome" errormsg="请输入正确的免税店收入(万元)" /></td>              
			</tr>        
		</tbody>
	</table>
 </t:formvalid>
<!-- 单位是人天  type为1-->
<script>
/* window.setTimeout(function(e){debugger},3000); */
</script>

</body>
