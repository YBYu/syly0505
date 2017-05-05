<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<table width="440" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="66">审核状态:</td>
			<td width="370" style="padding-top:10px"><input value="${issueStatus }" readOnly
				style="width: 350px; border: 1px solid #D7D7D7; border-radius: 3px; padding: 7px 0 7px 5px; line-height: 14PX; font-size: 12px; display: inline-block"></td>
		</tr>
		<tr>
			<td height="60">审核意见:</td>
			<td valign="middle" style="padding-top:10px"><textarea readOnly
					style="height: 60px; width: 350px; border: 1px solid #D7D7D7; border-radius: 3px; padding: 7px 0 7px 5px; line-height: 14PX; font-size: 12px; display: inline-block">${countryContent }</textarea></td>
		</tr>
	</table>
</body>
</html>
