<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools,ckeditor"></t:base>
<script type="text/javascript">
</script>
</head>
<body  scroll="auto">
<t:formvalid formid="notice" refresh="false" dialog="true"  layout="table">
	<input id="id" type="hidden" value="${notice.id }">

<table style="width: 880px;height: 780px" cellpadding="0" cellspacing="1"
				class="formtable" id="formtableId">
				<tr>
					<td width="100px">
						<span>通知标题:</span>
					</td>
					<td>
						<input id="noticeTitle" name="title" type="text" class="text"
							style="width: 500px;" value="${notice.title }">
					</td>
				</tr>
				<%-- <tr>
					<td width="100px">
						<span>图片</span>
					</td>
					<td>
						<img src="${notice.image}" alt="图片不存在"></img>
					</td>
				</tr> --%>
				<tr>
					<td>
						<span>通知内容:</span>
					</td>
					<td>
						<textarea name="content" id="content" rows="20" cols="100">
                	${notice.content }
            </textarea>
					</td>
					<%-- 	<td><input id="noticeContent" name="content" type="text" class="text" style="width:500px;height:350px" value="${notice.content}"></td> --%>
				</tr>
				<tr>
					<td>
						<span>通知范围:</span>
					</td>
					<td>
						<input id="noticeTitle" name="title" type="text" class="text"
							style="width: 500px;" value="<c:if test="${notice.range==0 }">全部企业</c:if><c:if test="${notice.range==1 }">景区</c:if><c:if test="${notice.range==2}">旅行社</c:if><c:if test="${notice.range==3}">星级酒店</c:if><c:if test="${notice.range==4}">所有酒店</c:if><c:if test="${notice.range==5}">其他涉旅企业</c:if>">
					</td>
				</tr>
					<tr>
					<td>
						<span>通知来源:</span>
					</td>
					<td>
						<input id="noticeSource" name="source" type="text" class="text"
							style="width: 500px;" value="<c:if test="${notice.source==0 }">三亚旅游统计管理系统</c:if><c:if test="${notice.source==1}">A级景区管理系统</c:if><c:if test="${notice.source==2}">旅行社管理系统</c:if><c:if test="${notice.source==3}">星级酒店管理系统</c:if><c:if test="${notice.source==4}">旅游统计报送系统</c:if>">
					</td>
				</tr>
				<tr>
					<td>
						<span>发布者:</span>
					</td>
					<td>
						<input id="noticeAnnouncer" name="announcer" type="text"
							class="text" style="width: 500px;" value="${notice.announcer}">
					</td>
				</tr>
				<tr>
					<td>
						<span>创建日期:</span>
					</td>
					<td>
						<input id="noticeCreatedate" name="createdate" type="text"
							class="text" style="width: 500px;" value="${notice.createDate} ">
					</td>
				</tr>
				<tr>
					<td>
						<span>更新日期:</span>
					</td>
					<td>
						<input id="noticePublishdate" name="gengxin" type="text"
							class="text" style="width: 500px;" value="${notice.gengxin}">
					</td>
				</tr>
				<tr>
					<td>
						<span>状态:</span>
					</td>
					<td>
						<input id="noticeStatus" name="status" type="text"
							class="text" style="width: 500px;" value="<c:if test="${notice.status==0}">未发布</c:if><c:if test="${notice.status==1}">已发布</c:if><c:if test="${notice.status==2}">已失效</c:if>">
					</td>
				</tr>
			</table>
</t:formvalid>
<div style="padding:5px">
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		CKEDITOR.replace('content');
		/*
		var content='${notice.content}';
		debugger
		alert(content);
		 CKEDITOR.instances.content_text.setData(content); */
	});
</script>
