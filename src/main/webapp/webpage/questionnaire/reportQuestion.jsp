<!doctype html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>

        <meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">
        
<t:base type="jquery,easyui,tools"></t:base>
        <style>
body{ text-align:center} 
.div{ margin:0 auto; width:400px; height:100px; border:1px solid #F00}


        </style> 
        
        
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts-more.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/modules/exporting.js"></script>           

</head>   

    <body>

    <t:formvalid formid="formobj" refresh="true" dialog="true" action="questionNaireController.do?audit" usePlugin="password" layout="table">
	<input name="analyzeid"  type="hidden" value="${analyzeid}">
	
	<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<div class="MainInfo_tit pdtb10">
            	链接发布成功！
            </div>	


<tr> 
<td> 
<textarea readonly rows="6" cols="90" id="registerUrl"
 onclick=copyinput()>
 <%--String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() --%>
<%--http://+<%=request.getRemoteAddr();%>+":"+<%=request.getRemotePort()%>/questionNaireController.do?auditwenjuan&analyzeid=${analyzeid} 
  --%> 
  <%-- 
  http://${head}/syly/questionNaireController.do?guestreportquestion&analyzeid=${analyzeid}
  --%>
 <t:mutiLang langKey="zzc.platform.appBase"/>questionNaireController.do?guestreportquestion&analyzeid=${analyzeid}
  
 </textarea>     
</td> 
<td> <input type="button" value="复制链接" onclick="copyinput()"/> </td>  
</tr> 
            
		</tbody>
	</table>
	
	
</t:formvalid>



<script language="javascript">

function copyToClipBoard(){ 
    var clipBoardContent=""; 
    clipBoardContent+=document.title; 
    clipBoardContent+=""; 
    clipBoardContent+=this.location.href; 
    window.clipboardData.setData("Text",clipBoardContent); 
    alert("复制成功，请粘贴到你的QQ/MSN上推荐给你的好友"); 
  }   

function copyinput() {
 
var input=document.getElementById("registerUrl");//input的ID值 
input.select(); //选择对象 
document.execCommand("Copy"); //执行浏览器复制命令 
} 

</script>

	
	
	
	
	

    </body>

</html>
