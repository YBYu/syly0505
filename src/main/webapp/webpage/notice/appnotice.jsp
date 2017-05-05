<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
 <script   src="plug-in/jquery/jquery-1.8.3.js"></script>
 <script type="text/javascript">
	
</script>
<%-- <table style="width:340px;height: 420px" cellpadding="0" cellspacing="1"
				class="formtable" id="formtableId">
				<tr>
					<!-- <td width="100px">
						<span>通知标题:</span>
					</td> -->
					<td align="center" >
						<h1>${notice.title }</h1>
					
						<input id="noticeTitle" name="title" type="text" class="text" readonly="readonly"
							style="width: 260px;" value="${notice.title }">
					</td>
				</tr>
				<tr>
					<td width="100px">
						<span>图片</span>
					</td>
					<td>
						<img src="${notice.image}" alt="图片不存在"></img>
					</td>
				</tr>
				<tr>
					<!-- <td>
						<span>通知内容:</span>
					</td> -->
					<td>
						
                	${notice.content }
					</td>
						<td><input id="noticeContent" name="content" type="text" class="text" style="width:500px;height:350px" value="${notice.content}"></td>
				</tr>
				<tr>
					<td>
						<span>通知范围:</span>
					</td>
					<td>
						<input id="noticeTitle" name="title" type="text" class="text" readonly="readonly"
							style="width: 260px;" value="<c:if test="${notice.range==0 }">全部企业</c:if><c:if test="${notice.range==1 }">景区</c:if><c:if test="${notice.range==2}">旅行社</c:if><c:if test="${notice.range==3}">星级酒店</c:if><c:if test="${notice.range==4}">所有酒店</c:if><c:if test="${notice.range==5}">其他涉旅企业</c:if>">
					</td>
				</tr>
					<tr>
					<td>
						<span>通知来源:</span>
					</td>
					<td>
						<input id="noticeSource" name="source" type="text" class="text" readonly="readonly"
							style="width: 260px;" value="<c:if test="${notice.source==0 }">三亚旅游统计管理系统</c:if><c:if test="${notice.source==1}">A级景区管理系统</c:if><c:if test="${notice.source==2}">旅行社管理系统</c:if><c:if test="${notice.source==3}">星级酒店管理系统</c:if><c:if test="${notice.source==4}">旅游统计报送系统</c:if>">
					</td>
				</tr>
				<tr>
					<!-- <td>
						<span>发布者:</span>
					</td> -->
					<td align="right"><FONT size="4" >${notice.announcer}</FONT>
					
						<input id="noticeAnnouncer" name="announcer" type="text" readonly="readonly"
							class="text" style="width: 260px;" value="${notice.announcer}">
					</td>
				</tr>
				<tr>
					<td>
						<span>创建日期:</span>
					</td>
					<td>
						<input id="noticeCreatedate" name="createdate" type="text" readonly="readonly"
							class="text" style="width: 260px;" value=" ${notice.createDate} ">
					</td>
				</tr>
				<tr>
					<td>
						<span>更新日期:</span>
					</td>
					<td>
						<input id="noticePublishdate" name="publishdate" type="text" readonly="readonly"
							class="text" style="width: 260px;" value=" ${notice.updateDate}  ">
					</td>
				</tr>
				<tr>
					<td>
						<span>状态:</span>
					</td>
					<td>
						<input id="noticeStatus" name="status" type="text" readonly="readonly"
							class="text" style="width: 260px;" value="<c:if test="${notice.status==0}">未发布</c:if><c:if test="${notice.status==1}">已发布</c:if><c:if test="${notice.status==2}">已失效</c:if>">
					</td>
				</tr>
			</table>	 --%>
			<div style="width:340px ">
			<h1 align="center">${notice.title }</h1>
			<h2 style="color:#CCC" align="center">${notice.announcer}&nbsp&nbsp<span ><c:if test="${not empty notice.gengxin}">${notice.gengxin}</c:if><c:if test="${empty notice.gengxin}">${notice.createDate}</c:if></span></h2>
			<p>${notice.content }</p>
			</div>
<div style="padding:5px">
	</div>
 
</body>
 
