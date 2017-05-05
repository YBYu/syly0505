<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>通知管理-添加通知</title>
<t:base type="jquery,easyui,tools,ckeditor,ckfinder"></t:base>
<style type="text/css">
.container {
	width: 750px;
	border-radius: 10px;
}
</style>
<script type="text/javascript">

    
</script>
</head>
<body  scroll="auto">
	<t:formvalid formid="formobj" refresh="true" dialog="true" 
	action="noticeController.do?save" usePlugin="password" layout="table" >
		<div class="container">
			<input id="id" name="id" type="hidden" value="${notice.id }">
			<table style="width: 960px;height:750px " cellpadding="0" cellspacing="1"
				class="formtable" id="formtableId">
				<tr>
					<td width="100px">
						<span>通知标题:</span>
					</td>
					<td>
						<input id="noticeTitle" name="title" type="text" 
							style="width: 500px;" value="${notice.title }" class="inputxt"  datatype="*" errormsg="请输入标题">
					</td>
				</tr>
				<%-- <tr>
				<td width="100px">
						<span>图片:</span>
					</td>
			<td class="value">
			<t:ckfinder name="image" uploadType="Images"  width="150" height="120" buttonClass="ui-button" buttonValue="上传图片"></t:ckfinder>
			是否在首页显示:<input  type="radio" value="0" name="iffirst">是<input  type="radio" value="1" name="iffirst">否 
			</td>
		</tr> --%>
				<tr>
					<td>
						<span>通知内容:</span>
					</td>
					<td>
						<%-- 	<t:ckeditor name="content" isfinder="false"  type="width:860,height:400"></t:ckeditor> --%>
						<textarea name="content" id="content"></textarea>
							
				</tr>
				<tr>
					<td>
						<span>通知范围:</span>
					</td>
					<td>
						<select id="noticeRange" style="width: 500px;" name="range">
							<option value="0">全部项目</option>
							<option value="1">旅游景区</option>
							<option value="2">旅行社</option>
							<option value="3">星级酒店</option>
							<option value="4">所有酒店</option>
							<option value="5">其他涉旅项目</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<span>发布者:</span>
					</td>
					<td>
						<input id="noticeAnnouncer" name="announcer" type="text"
							class="text" style="width: 500px;" value="${notice.announcer}" datatype="*" errormsg="请输入发布者姓名">
					</td>
				</tr>
				<input  name="appContent" id="appContent" type="hidden">
			</table>
		</div>
	</t:formvalid>
	<script type="text/javascript">
	//富文本编辑器加载
	$(document).ready(function(){
		CKEDITOR.replace( 'content',
		         {
		             toolbar :
		            [
		　　　　　　　　  //加粗     斜体，     下划线      穿过线      下标字        上标字
		               ['Bold','Italic','Underline','Strike','Subscript','Superscript'],
		　　　　　　　　  //左对齐             居中对齐          右对齐          两端对齐
		               ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		　　　　　　　　　//样式       格式      字体    字体大小
		               ['Styles','Format','Font','FontSize'],
		　　　　　　　　  //文本颜色     背景颜色
		               ['TextColor'],
		             ],
		            height:'400px'
		         }
		    );
	});
	
	</script>
</body>