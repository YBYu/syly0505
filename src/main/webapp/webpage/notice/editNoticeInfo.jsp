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
<t:formvalid formid="notice" refresh="true" dialog="true"  layout="table" action="noticeController.do?editStatus" >
	<input name="id" type="hidden" value="${notice.id }">

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
						<textarea name="content" id="content" rows="20" cols="100" >
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
<%-- 						<input id="noticeTitle" name="range" type="text" class="text"  
							style="width: 500px;" value="<c:if test="${notice.range==0 }">全部企业</c:if><c:if test="${notice.range==1 }">景区</c:if><c:if test="${notice.range==2}">旅行社</c:if><c:if test="${notice.range==3}">星级酒店</c:if><c:if test="${notice.range==4}">所有酒店</c:if><c:if test="${notice.range==5}">其他涉旅企业</c:if>"> --%>
						<select style="width: 500px;" name="range">
							<option value="0" <c:if test='${notice.range==0 }'>selected</c:if>>全部项目</option>
							<option value="1" <c:if test='${notice.range==1 }'>selected</c:if>>旅游景区</option>
							<option value="2" <c:if test='${notice.range==2 }'>selected</c:if>>旅行社</option>
							<option value="3" <c:if test='${notice.range==3 }'>selected</c:if>>星级酒店</option>
							<option value="4" <c:if test='${notice.range==4 }'>selected</c:if>>所有酒店</option>
							<option value="5" <c:if test='${notice.range==5 }'>selected</c:if>>其他涉旅项目</option>
						</select>
					</td>
				</tr>
					<tr>
					<td>
						<span>通知来源:</span>
					</td>
					<td>
						<%-- <input id="noticeSource" name="source" type="text" class="text"  
							style="width: 500px;" value="<c:if test="${notice.source==0 }">三亚旅游统计管理系统</c:if><c:if test="${notice.source==1}">A级景区管理系统</c:if><c:if test="${notice.source==2}">旅行社管理系统</c:if><c:if test="${notice.source==3}">星级酒店管理系统</c:if><c:if test="${notice.source==4}">旅游统计报送系统</c:if>"> --%>
						<select style="width: 500px;" name="source" disabled="disabled">
							<option value="0" <c:if test='${notice.source==0 }'>selected</c:if>>三亚旅游统计管理系统</option>
							<option value="1" <c:if test='${notice.source==1 }'>selected</c:if>>A级景区管理系统</option>
							<option value="2" <c:if test='${notice.source==2 }'>selected</c:if>>旅行社管理系统</option>
							<option value="3" <c:if test='${notice.source==3 }'>selected</c:if>>星级酒店管理系统</option>
							<option value="4" <c:if test='${notice.source==4 }'>selected</c:if>>-旅游统计报送系统</option>
						</select>
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
				<%-- <!-- tr>
					<td>
						<span>创建日期:</span>
					</td>
					<td> -->
					<tr>
					<td>
						<input id="noticeCreatedate" name="createDate" type="hidden"  
							class="text" style="width: 500px;" value="${notice.createDate}" ></td></tr>
				<!-- 	</td>
				</tr> -->
				<tr>
					<td>
						<span>更新日期:</span>
					</td>
					<td>
						<input   name="updateDate"   readonly="readonly" type="text"
							class="text" style="width: 500px;" value="${notice.updateDate}">
					</td>
				</tr> --%>
				<input name="createDate" value="${notice.createDate}" type="hidden">
				<tr>
					<td>
						<span >状态:(请修改)</span>
					</td>
					<td>
						<select id="status" name="status">
							<option value="0" <c:if test="${'0' eq notice.status}">selected</c:if>>未发布</option>
							<option value="1" <c:if test="${'1' eq notice.status}">selected</c:if>>已发布</option>
							<option value="2" <c:if test="${'2' eq notice.status}">selected</c:if>>已失效</option>
						</select>
						</td>
				</tr>
			</table>
</t:formvalid>
<div style="padding:5px">
	</div>
</body>
<script type="text/javascript">
//加载富文本编辑器
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
	/* function giveAppContent(){
		var htmlStr = CKEDITOR.instances.content.document.getBody().getText();
		var len = 0;
		//处理pre
		var pres = htmlStr.match(/<pre*.?>(.*?)<\/pre>/g);
		htmlStr = htmlStr.replace(/<pre*.?>(.*?)<\/pre>/g,"");
		if(pres)
		    len += pres.join("").length;
		len += htmlStr
		    .replace(/\s+/g," ")               //将多个空字符换成一个空格
		    .replace(/<br\s*?\/?>/g,".")       //将所有换行符替换成一个字符(不用\n是因为可能会被后面换掉)
		    .replace(/(<\/p>)/g,".$1")         //为所有段落添加一个字符(或两个字符，自己定)将点放在前面避免影响后面的替换
		    .replace(/<\/.+?>\s*<[^\/]>/g,"") //去掉所有尾-首相连的HTML标签(包括中间的空字符)
		    .replace(/<.+?>/g,"")             //去掉剩下的HTML标签
		    .replace(/&.+?;/g,".")            //转换所有实体为一个字符
		    .length
		if(len>2000){
			alert("文本长度最大为2000!");
			return false;
		}
			
	}
 */
</script>
