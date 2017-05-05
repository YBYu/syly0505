<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
<style type="text/css">
.container {
	width: 750px;
	padding-left: 30px;
	border-radius: 10px;
}
</style>
</head>
<body>
	<t:formvalid formid="formobj" refresh="false" dialog="true" action=""
		usePlugin="password" layout="table" callback="" beforeSubmit="">
		<div class="container">
			<table id="surveyTable" cellspacing="0" cellpadding="5"
				style="border-radius: 3px; border: 0px solid #D7D7D7">
				<tr>
					<td width="66">问卷名称：</td>
					<td><input id="surveyName" type="text" class="text"
						style="width: 580px;"></td>
					<td width="60">&nbsp;</td>
				</tr>
				<tr>
					<td height="45">问卷说明：</td>
					<td><textarea id="declare"
							style="width: 580px; height: 60px; border-radius: 3px; border: 1px solid #D7D7D7"></textarea>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>问卷周期：</td>
					<td colspan=2><input id="startDate" type="text" class="easyui-datebox">
						<span>至：</span> <input id="endDate" type="text"
						class="easyui-datebox">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addQuestion()">添加问题</a>
						</td>
				</tr>
			</table>
		</div>
	</t:formvalid>
</body>

<script>

	function addQuestion() {
		var questionNumber = $(".question").length + 1;
		$("#surveyTable tbody")
				.append(
						"<tr id='question"+questionNumber+"' class='question'><td>题目"
								+ questionNumber
								+ "：</td><td><textarea style='width: 580px; height: 60px; border-radius: 3px; border: 1px solid #D7D7D7'></textarea></td><td>"
								+ "<span style='text-decoration:underline;color:blue;cursor:pointer' onclick='addOption()'>添加选项</span><br/><br/>"
								+ "<span style='text-decoration:underline;color:blue;cursor:pointer' onclick='delQuestion("+questionNumber+")'>删除问题</span></td></tr>");
	}
	
	function delQuestion(questionNumber){
		$("#question" + questionNumber).remove();
		$(".option" + questionNumber).remove();
	}

	function addOption() {
		var questionNumber = $(".question").length;
		var optionNumber = $(".option" + questionNumber).length + 1;
		$("#surveyTable tbody")
				.append(
						"<tr class='option"+questionNumber+"'><td>选项"
								+ optionNumber
								+ "：</td><td><input type='text' class='text' style='width: 580px;'></td><td>&nbsp;</td></tr>");
	}
	
	
</script>