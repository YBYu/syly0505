<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">
<!-- <style type="text/css"> -->

<!-- </style> -->
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="ideaBackController.do?auditSave" usePlugin="password" layout="table">

	<input name="ideabackid"  type="hidden" value="${ideabackid}">
	
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
		 
        	<div class="MainInfo_tit pdtb10">
            	${ideadata.ideaUserName}--意见反馈
            </div>
           
      
			<tr>
               	<td><span>意见反馈</span></td>
                <td><textarea rows="10" name="content" readOnly="true" style="width:95%">${ideadata.content}</textarea></td>
            </tr>
           
			<tr>
               	<td><span>回复内容</span></td>
                <td><textarea rows="10" name="replyContent" readOnly="true" style="width:95%">${ideadata.replyContent}</textarea></td>
            </tr>
			
            
		</tbody>
	</table>
</t:formvalid>
</body>