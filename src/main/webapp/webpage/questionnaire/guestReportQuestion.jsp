<!doctype html>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>

<meta charset="utf-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />     
<meta name="apple-mobile-web-app-capable" content="yes" />    
<meta name="format-detection" content="telephone=no" />  -->
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<t:base type="jquery,easyui,tools"></t:base>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">

<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}

.inquiry {
	padding: 15px;
}

.inquiry-head h3 {
	text-align: center;
	margin-bottom: 10px;
	color: #03F;
	font-size: 25px;
	font-family:微软雅黑;
	font-weight:bold;
}

.inquiry-head p {
	line-height: 2.4em;
}

.tit1 label {
	display: block;
	cursor: pointer;
	padding: 5px;
}

.inquiry .inquiry-con {
	padding-top: 10px;
	padding-bottom: 10px;
}

.inquiry .inquiry-con .tit1 p {
	padding-bottom: 5px;
}

.inquiry-con .inquiry-btn {
	text-align: center;
}

.inquiry .btn {
	margin-top:15px;
	padding: 10px 20px;
	color: #fff;
	border: none;
	cursor: pointer;
}

.blue {
	background: #06F;
}
</style>
</head>
		
<body style="width:800px;border: 0px solid #D9D9D9;margin:0 auto;">
	<div>
		<t:formvalid formid="formobj" refresh="true" dialog="false"
			callback="resultmsg" action="questionNaireController.do?audit"
			usePlugin="password" layout="table" beforeSubmit="">

			<input name="analyzeid" type="hidden" value="${analyzeid}">
			<input name="bianjiurl" type="hidden" value="${bianjiurl}">
			<div class="inquiry">
				<div>
					<div class="inquiry-head">
						<h3>${analyzedata.name }</h3>
						<p style="font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！现在我们就马上开始吧！</p>
					</div>
					<div class="inquiry-con">
						<div class="tit1">
							<c:forEach varStatus="j" items="${topiclist}" var="list">
							<br>
								<p style="font-size: 18px">${j.index+1}.${list.topicName}？</p>
									<c:forEach varStatus="i" items="${list.questionNaireOptionList}" var="a">
							<label class="opt1">
								<input type="radio"   name="topic_${list.id}" value="${a.id}">
								${a.optionName}
							</label>
							</c:forEach>
							<hr>
							</c:forEach>
 
						</div>
						<div class="inquiry-btn">
							<button type="button" class="btn blue" onclick="checkSex()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</t:formvalid>
		</div>
		</body>
<script>
	var str = false;
	function checkSex() {
		var trNum = $(".tit1 p");
		var check = $(".tit1 input");
		var num = 0;
		for (var i = 0; i < check.length; i++) {
			if (check[i].checked == true) {
				num++;
			}
		}
		if (num != trNum.length) {
			alert("请填写完所有问题!");
			str = true;
			return false;
		}
		
		// 提交数据
		var formjson = $('#formobj', document).serialize();
		$.ajax ({
			url:"questionNaireController.do?audit",
			data:formjson,
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
				}  	
		 	}
		);
	}
	function resultmsg(data) {
		if (!str) {
			alert(data.msg);
		}
	}
</script>
</body>
<script type="text/javascript">

       window.onload = function(){
		try{
    		  if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
    			  $("body").css({"width":380});
    		    } 
    		}catch(e){}
        }
    </script>
</html>