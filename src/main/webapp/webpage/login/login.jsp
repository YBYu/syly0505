<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.zzc.core.util.SysThemesUtil,com.zzc.core.enums.SysThemesEnum"%> 
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<%
String lang = com.zzc.core.util.BrowserUtils.getBrowserLanguage(request);
String langurl = "plug-in/mutiLang/" + lang +".js";
SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link href="./css/login/public.css" rel="stylesheet" type="text/css"/>
<link href="./css/login/layout.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="./css/login/images/sanya.jpg" media="screen" />
</head>
<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<body>
<!-- <div id="alertMessage"></div> -->
<div id="successLogin"></div>
<div class="top log" style="margin-top:5%">
    <div class="logo">
    <img src="./css/login/images/sanya_logo.png" style="margin-top:-30px;width:700px;">
    </div>
    <span style="float:left;">
    	<img id="appImg" src="./css/login/images/app.png" style="width: 120px; height: 120px; margin-top: -120px;visibility:hidden;">
    </span>
</div>

<div id="login" class="loginBg" style="margin-top:40px">
    <div class="loginBox">
        <div class="whiteBox" id="whiteBox">
		<div class="text_success" style="display:none;position: absolute;top: 214px;width: 280px;  background: rgba(193, 186, 186, 0.52);z-index: 100;border-radius: 5px;"><img src="plug-in/login/images/loader_green.gif" alt="Please wait" /> <span>正在登录请稍后...</div>
            <form name="formLogin" id="formLogin" action="loginController.do?login" check="loginController.do?checkuser" method="post">
            <h2>用户登录</h2>
            <img id="scanIcon" title="手机端下载" src="./css/login/images/scan_icon.png" style="width: 55px; height: 55px; margin-top: -68px; margin-left: 251px;position: absolute">
            <ul>
            <input name="userKey" type="hidden" id="userKey" value="D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900" />
              <li><span>账号</span>
                  <input class="user no_bor" name="userName" type="text" id="userName" title="" iscookie="true" value="admin" />
                  <i id="del"><img src="./css/login/images/icon_del.png"></i>
              </li>
              <li><span>密码</span>
                  <input  class="pass no_bor"  name="password" type="password" id="password" title="" value="123456" nullmsg=""/>
                   <i id="lock"><img src="css/login/images/icon_lock.png"></i>
              </li>
            </ul>
            <!-- 暂时屏蔽
            <p class="forget"><a>忘记登录密码</a></p>
             -->
            <ul class="clearfix">
              <li class="yzm"><span>验证码</span> <input id="randCode" name="randCode" class="code no_bor" type="text" value="" /></li>
              <li class="ewm"><img id="randCodeImage" src="randCodeImage" /></li>
            </ul>
            
            <span class="error" style="display:none;" id="alertMessage"></span>
            
            
            <p class="pt20"><input class="button" id="but_login" type="button" value="登 录"></p>
            
            <input style="visibility:hidden" type="checkbox" id="on_off" name="remember" checked="checked" class="on_off_checkbox" value="0" />
            
            </form>
        </div>
    </div>
</div>

<!--foot-->
<div class="footer-login" style="position:fixed;bottom:0px;width:100%">Copyright© 2016 三亚旅游统计管理系统 All Right Reserved</div>

 <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="plug-in/login/js/jquery-jrumble.js"></script>
    <script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
    <script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
    <script type="text/javascript" src="plug-in/login/js/login.js"></script>
<script type="text/javascript">
//图标显示与消失
$(function(){
	$('#del').hide();
	$('.pass2').hide();
	$('.user').focus(function(){
		$('#del').show();
		$('#del').click(function(){
			$('.user').val('');
			})
		})
	$('#lock').click(function(){
		if($('.pass').attr('name') == 1){
		$('.pass').hide();
		$('.pass2').show();
		var val = $('.pass').val();
		$('.pass2').val(val);
		$('.pass').attr('name',2);
		}else{
		$('.pass').show();
		$('.pass2').hide();
		var val2 = $('.pass2').val();
		$('.pass').val(val2);
		$('.pass').attr('name',1);
			}
		})
	});

//$(function(){
//	$('.button').click(function(){
//		window.location.href="index.html";
//	})
//})

window.onload = function(e){
	var leftOffset = $("#whiteBox").offset().left;
	$("#appImg")[0].style.marginLeft=(leftOffset+100)+"px";
	
	var topOffset = $(".logo").offset().top - 60;
	$("#login")[0].style.marginTop = topOffset+"px";
	
	var clientWidth = document.body.scrollWidth;
	$("body")[0].style.width = clientWidth+"px";
	
    $("#scanIcon").hover(function(){
        $("#appImg")[0].style.visibility = "visible";
    },function (){  
        $("#appImg")[0].style.visibility = "hidden";
    });  
};

var width = 0; // 当前宽度
window.setInterval(function(){
	try{
		var leftOffset = $("#whiteBox").offset().left;
		$("#appImg")[0].style.marginLeft=(leftOffset+100)+"px";
		
		var topOffset = $(".logo").offset().top - 60;
		$("#login")[0].style.marginTop = topOffset+"px";
		
		var clientWidth = document.body.scrollWidth;
		if(clientWidth > width) width = clientWidth;
		$("body")[0].style.width = width+"px";
	}catch(e){
		
	}
},1000);


</script>
</body>
</html>
