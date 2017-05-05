<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>酒店信息录入</title>
</head>
<body>
	<div style>
		<label style="color: black; size: 5%"> 企业信息录入 </label>
	</div>
	<div style="padding: 10px 10px 10px 10px">
		<form id="contentAddForm" class="itemForm" method="post">
			<input type="hidden" name="categoryId" />
			<table cellpadding="5">
				<tr>
					<td>法人代表:</td>
					<td>
						<input class="easyui-textbox" type="text" name="title"
							data-options="required:true" style="width: 280px;"></input>
					</td>
				</tr>
				<tr>
					<td>组织机构代码:</td>
					<td>
						<input class="easyui-textbox" type="text" name="subTitle"
							style="width: 280px;"></input>
					</td>
				</tr>
				<tr>
					<td>单位名称:</td>
					<td>
						<input class="easyui-textbox" name="titleDesc"
							data-options="multiline:true,validType:'length[0,150]'"
							style="height: 60px; width: 280px;"></input>
					</td>
				</tr>

				<tr>
					<td>所属市:</td>
					<td>
						<input class="easyui-textbox" name="titleDesc"
							data-options="multiline:true,validType:'length[0,150]'"
							style="height: 60px; width: 280px;"></input>
					</td>
				</tr>
				<tr>
					<td>所属县:</td>
					<td>
						<input class="easyui-textbox" name="titleDesc"
							data-options="multiline:true,validType:'length[0,150]'"
							style="height: 60px; width: 280px;"></input>
					</td>
				</tr>

				<tr>
					<td>详细地址:</td>
					<td>
						<input class="easyui-textbox" type="text" />

					</td>
				</tr>
				<tr>
					<td>负责人:</td>
					<td>
						<input class="easyui-textbox" type="text" />
					</td>
				</tr>
				<tr>
					<td>邮政编码:</td>
					<td>
						<input class="easyui-textbox" type="text" />
					</td>
				</tr>
				<tr>
					<td>移动电话:</td>
					<td>
						<input class="easyui-textbox" type="text" />
					</td>
				</tr>
				<tr>
					<td>饭店星级:</td>
					<td>
						<input class="easyui-text" type="text" name="url"
							style="width: 280px;"></input>
					</td>
				</tr>
			</table>
		</form>
</body>
<script type="text/javascript">
	var contentAddEditor ;
	$(function(){
		contentAddEditor = TT.createEditor("#contentAddForm [name=content]");
		TT.initOnePicUpload();
		$("#contentAddForm [name=categoryId]").val($("#contentCategoryTree").tree("getSelected").id);
	});
	
	var contentAddPage  = {
			submitForm : function (){
				if(!$('#contentAddForm').form('validate')){
					$.messager.alert('提示','表单还未填写完成!');
					return ;
				}
				contentAddEditor.sync();
				
				$.post("/content/save",$("#contentAddForm").serialize(), function(data){
					if(data.status == 200){
						$.messager.alert('提示','新增内容成功!');
    					$("#contentList").datagrid("reload");
    					TT.closeCurrentWindow();
					}
				});
			},
			clearForm : function(){
				$('#contentAddForm').form('reset');
				contentAddEditor.html('');
			}
	};
</script>
</html>