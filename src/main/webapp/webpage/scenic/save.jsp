<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
  <!--  
<style type="text/css">
	 .location, .level{
		width:80%;
		padding:2px 0;
		background:#E7FAFC;
		}
	.text{
		width:80%;
		padding:2px 0;
		}	
	td{
		padding:10px 0;
		background:#9DDEE3;
		text-align:center;
		border-bottom:1px solid #ededed;
		border-left:1px solid #ededed;
		}
</style>
-->
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">

</head>
<body style="overflow-y: hidden" scroll="no">                       
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicController.do?save" usePlugin="password" layout="table">
	<input id="id" type="hidden" value="${scenicData.id }">
 <!--  
<table style="width:550px; margin:80px auto; background:#EAF9FD" cellpadding="0" cellspacing="0" class="formTable">
-->
	<table style="width:680px; font-size:12px;" margin:80px auto; cellpadding="0" cellspacing="2" class="formtable">

		<tbody>
		<div class="MainInfo_tit pdtb10">
            	景区基本信息  
            </div>
           <tr class="titTr">
				<td align="center" width="16%"><span class="tit" cellpadding="0" cellspacing="0">景区名称</span></td>				
				<td width="28%" colspan="3"><input type="text" name="name" validType="t_scenicspot_info_sta,name,id" class="text" datatype="*" errormsg="景区名称不能为空"/></td>
			</tr>
			<!-- <tr class="titTr">				
				<td align="center" width="16%"><span class="tit">景区编码</span></td>				
				<td width="28%" colspan="3"><input type="text" name="code" validType="t_scenicspot_info,code,id" class="text" datatype="*" errormsg="景区编码不能为空"/></td>
			</tr> -->
            <tr class="titTr">
				<td align="center" width="16%"><span class="tit">景区等级</span></td>								
				<td width="28%" colspan="3">
					<select id="type" name="level" class="level">
							<option value="6">未评定</option>
							<option value="5">AAAAA</option>
							<option value="4">AAAA</option>
							<option value="3">AAA</option>
							<option value="2">AA</option>
							<option value="1">A</option>
					</select>
				</td>
			</tr>
			<tr class="titTr">
				<td align="center" width="19%"><span class="tit">所在地区</span></td>								
				<td width="20%">
					<select id="type" name="location" disabled="disabled" class="location">
							<option value="">海南省</option>
                            <option value="">三亚市</option>
					</select>
				</td>
				<td width="20%">
					<select id="type2" name="city" disabled="disabled" class="location">
							<option value="460200">三亚市</option>
					</select>
				</td>
				<td width="20%">
					<select id="type3" name="area" class="location">
							<option value="0">市辖区</option>
							<option value="1">崖州区</option>
							<option value="2">海棠区</option>
							<option value="3">天涯区</option>
							<option value="4">吉阳区</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" width="16%"><span class="filedzt">所属湾区</span></td>
				<td class="value" width="16%" colspan="4">
				<select id="area" name="bayType" datatype="*" msg="请选择">
						<option value="">请选择 </option>
                    	<option value="0">市区</option>
                        <option value="1">亚龙湾</option>
                        <option value="2">大东海</option>
                        <option value="3">三亚湾</option>
                        <option value="4">海棠湾</option>
                    </select></td>
			</tr>
			
		</tbody>
	</table>
</t:formvalid>
</body>