<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>景区数据导出</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="true" beforeSubmit="upload">
      <table style="width: 550px" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="10%"><span class="filedzt">导出所有景区基本信息:</span></td>
			    <td align="right" width="10%"><span class="filedzt">导出年度状况数据:</span></td>
			    <td align="right" width="10%"><span class="filedzt">导出季度状况数据:</span></td>
			    
			</tr>
			<tr>
				<td align="right">
				<span class="filedzt">
				<a class="button" href="excelController.do?excelExport"><span>导出</span></a> 
                </span></td>
			 	<td align="right"><span class="filedzt"><a href="excelController.do?excelExport">导出</a></span></td>
				<td align="right"><span class="filedzt"><a href="excelController.do?excelExport">导出</a></span></td>
			</tr>
		</tbody>
	</table>
</t:formvalid>
</body>
</html>
