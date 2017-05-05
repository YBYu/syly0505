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
<link href="webpage/css/public.css" rel="stylesheet" type="text/css"/>
<link href="webpage/css/layout.css" rel="stylesheet" type="text/css"/>
</head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<body>
<div id="alertMessage"></div>
<div id="successLogin"></div>
<div class="text_success"><img src="plug-in/login/images/loader_green.gif" alt="Please wait" /> <span><t:mutiLang langKey="common.login.success.wait"/></span></div>
    
<div class="top log">
    <div class="logo"><a href="###"><img src="webpage/images/sanya_logo.png" alt="三亚旅游统计"></a></div>
</div>

<div id="login" class="loginBg">
    <div class="loginBox">
        <div class="whiteBox">
            <form>
            <h2>用户登录</h2>
            <ul>
              <li><span>账号</span>
                  <input class="user no_bor" id="userName" type="text" value=""/>
                  <i id="del"><img src="webpage/images/icon_del.png"></i>
              </li>
              <li><span>密码</span>
                  <input class="pass no_bor" id="password" type="password" name="1" value=""/>
                  
                  <i id="lock"><img src="webpage/images/icon_lock.png"></i>
              </li>
            </ul>
            <p class="forget"><a>忘记登录密码</a></p>
            <ul class="clearfix">
              <li class="yzm"><span>验证码</span> <input id="randCode" class="code no_bor" type="text" value="" /></li>
              <li class="ewm"><img id="randCodeImage" src="randCodeImage" /></li>
            </ul>
            <!-- 
            <span class="error">你输入的验证码错误</span>
             -->
            
            <p class="pt20"><input class="button" id="but_login" type="button" value="登 录"></p>
            
            <input hidden="true" type="checkbox" id="on_off" name="remember" checked="checked" class="on_off_checkbox" value="0" />
            
            </form>
        </div>
    </div>
</div>

<!--foot-->
<div class="footer-login">Copyright© 2016 三亚旅游统计管理系统 All Right Reserved</div>

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
	})

//$(function(){
//	$('.button').click(function(){
//		window.location.href="index.html";
//	})
//})

</script>
</body>
</html>
