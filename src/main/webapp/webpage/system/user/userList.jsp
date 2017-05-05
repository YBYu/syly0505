<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<%--update-start--Author:zhangguoming  Date:20140827 for：添加 组织机构查询条件--%>
<script>
	$(function() {
		var datagrid = $("#userListtb");
		datagrid.find("div[name='searchColums']").append(
				$("#tempSearchColums div[name='searchColums']").html());
	});
</script>
<div id="tempSearchColums" style="display: none">
	<div name="searchColums">
		<span style="display: -moz-inline-box; display: inline-block;">
			<%--<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;" title="<t:mutiLang langKey="common.department"/>">
                <t:mutiLang langKey="common.department"/>：
            </span>--%> <%--  <input id="orgIds" name="orgIds" type="hidden">
            <input readonly="true" type="text" name="departname" style="width: 100px" onclick="choose_297e201048183a730148183ad85c0001()"/>
             --%>
			<%--<t:choose hiddenName="orgIds" hiddenid="id"--%> <%--url="departController.do?departSelect" name="departList"--%>
			<%--icon="icon-search" title="common.department.list"--%> <%--textname="departname" isclear="true"></t:choose>--%>
		</span>
	</div>
</div>
<%--update-end--Author:zhangguoming  Date:20140827 for：添加 组织机构查询条件--%>

<t:datagrid name="userList" title=""
	actionUrl="userController.do?datagrid" fit="true" fitColumns="true"
	idField="id" queryMode="group" sortName="createDate" sortOrder="desc">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.username" sortable="false" width="70"
		field="userName" query="true"></t:dgCol>
	<%--update-start--Author:zhangguoming  Date:20140827 for：通过用户对象的关联属性值获取组织机构名称（多对多关联）--%>
	<%--<t:dgCol title="common.department" field="TSDepart_id" query="true" replace="${departsReplace}"></t:dgCol>--%>
	<%--<t:dgCol title="common.department" sortable="false" field="userOrgList.tsDepart.departname" query="false"></t:dgCol>--%>
	<%--update-end--Author:zhangguoming  Date:20140827 for：通过用户对象的关联属性值获取组织机构名称（多对多关联）--%>
	<t:dgCol title="企业名称" field="realName" width="70" query="true"></t:dgCol>
	<t:dgCol title="common.role" field="userKey" width="70" query="true"
		replace="星级酒店项目数据上报员_星级酒店上报员,非星级酒店项目数据上报员_非星级酒店上报员,景区项目数据上报员_景区数据上报员,旅行社项目数据上报员_旅行社数据上报员,机场项目数据上报员_机场上报员,空中飞行项目数据上报员_空中飞行上报员,动车项目数据上报员_动车上报员,高尔夫项目数据上报员_高尔夫数据上报员,游艇项目数据上报员_游艇数据上报员,区级管理员_区级管理员,系统管理员_系统管理员,超级管理员_超级管理员"></t:dgCol>
	<t:dgCol title="common.createby" field="createBy" hidden="true"></t:dgCol>
	<t:dgCol title="common.createtime" field="createDate"
		formatter="yyyy-MM-dd" width="70"></t:dgCol>
	<t:dgCol title="common.updateby" field="updateBy" hidden="true"></t:dgCol>
	<t:dgCol title="common.updatetime" field="updateDate"
		formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
	<t:dgCol title="common.status" sortable="true" width="70"
		field="status"
		replace="common.active_1,common.inactive_0,super.admin_-1"></t:dgCol>
	<%-- <t:dgCol title="common.operation" field="opt" width="70"></t:dgCol> --%>

	<t:dgToolBar title="common.add.param" langArg="common.user"
		icon="icon-add" url="userController.do?addorupdate" funname="add"></t:dgToolBar>
	<t:dgToolBar title="common.edit.param" langArg="common.user"
		icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="common.delete" funname="deleteUser()" />
	<t:dgToolBar title="common.password.reset" icon="icon-edit"
		url="userController.do?changepasswordforuser" funname="update"></t:dgToolBar>
	<t:dgToolBar title="common.lock.user" icon="icon-edit"
		url="userController.do?lock&lockvalue=0" funname="lockObj"></t:dgToolBar>
	<t:dgToolBar title="common.unlock.user" icon="icon-edit"
		url="userController.do?lock&lockvalue=1" funname="unlockObj"></t:dgToolBar>

</t:datagrid>
<script type="text/javascript">
	// 删除用户
	function deleteUser() {
		var selection = $("#userList").datagrid('getSelected');
		if (selection == null) {
			alert("请选择一条数据!");
			return;
		}
		var role = selection.userKey;
		if (role == "区级管理员" || role == "系统管理员") {
			var id = selection.id;
			var userName = selection.userName;
			$.dialog.confirm("确认删除用户：" + userName + "?", function() {
				$.ajax({
					url : "userController.do?del&id=" + id + "&userName="
							+ userName,
					dataType : "json",
					success : function(data) {
						alert(data.msg);
						$("#userList").datagrid('reload');
					}
				});
			});
		} else {
			alert("该角色的用户不允许删除!");
			return;
		}
	}

	function lockObj(title, url, id) {

		gridname = id;
		var rowsData = $('#' + id).datagrid('getSelections');
		if (!rowsData || rowsData.length == 0) {
			tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
			return;
		}
		url += '&id=' + rowsData[0].id;

		$.dialog.confirm('<t:mutiLang langKey="common.lock.user.tips"/>',
				function() {
					lockuploadify(url, '&id');
				}, function() {
				});
	}
	function unlockObj(title, url, id) {

		gridname = id;
		var rowsData = $('#' + id).datagrid('getSelections');
		if (!rowsData || rowsData.length == 0) {
			tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
			return;
		}
		url += '&id=' + rowsData[0].id;

		$.dialog.confirm('<t:mutiLang langKey="common.unlock.user.tips"/>',
				function() {
					lockuploadify(url, '&id');
				}, function() {
				});
	}

	function lockuploadify(url, id) {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : url,// 请求的action路径
			error : function() {// 请求失败处理函数

			},
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					reloadTable();
				}

			}
		});
	}
</script>

<%--update-start--Author:zhangguoming  Date:20140827 for：添加 组织机构查询条件：弹出 选择组织机构列表 相关操作--%>
<%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onClick="choose_297e201048183a730148183ad85c0001()">选择</a>--%>
<%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" onClick="clearAll_297e201048183a730148183ad85c0001();">清空</a>--%>
<script type="text/javascript">
	//    var windowapi = frameElement.api, W = windowapi.opener;
	function choose_297e201048183a730148183ad85c0001() {
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				content : 'url:departController.do?departSelect',
				zIndex : 2100,
				title : '<t:mutiLang langKey="common.department.list"/>',
				lock : true,
				width : 400,
				height : 350,
				left : '85%',
				top : '65%',
				opacity : 0.4,
				button : [ {
					name : '<t:mutiLang langKey="common.confirm"/>',
					callback : clickcallback_297e201048183a730148183ad85c0001,
					focus : true
				}, {
					name : '<t:mutiLang langKey="common.cancel"/>',
					callback : function() {
					}
				} ]
			});
		} else {
			$.dialog({
				content : 'url:departController.do?departSelect',
				zIndex : 2100,
				title : '<t:mutiLang langKey="common.department.list"/>',
				lock : true,
				parent : windowapi,
				width : 400,
				height : 350,
				left : '85%',
				top : '65%',
				opacity : 0.4,
				button : [ {
					name : '<t:mutiLang langKey="common.confirm"/>',
					callback : clickcallback_297e201048183a730148183ad85c0001,
					focus : true
				}, {
					name : '<t:mutiLang langKey="common.cancel"/>',
					callback : function() {
					}
				} ]
			});
		}
	}
	function clearAll_297e201048183a730148183ad85c0001() {
		if ($('#departname').length >= 1) {
			$('#departname').val('');
			$('#departname').blur();
		}
		if ($("input[name='departname']").length >= 1) {
			$("input[name='departname']").val('');
			$("input[name='departname']").blur();
		}
		$('#orgIds').val("");
	}
	function clickcallback_297e201048183a730148183ad85c0001() {
		iframe = this.iframe.contentWindow;
		var departname = iframe.getdepartListSelections('departname');
		if ($('#departname').length >= 1) {
			$('#departname').val(departname);
			$('#departname').blur();
		}
		if ($("input[name='departname']").length >= 1) {
			$("input[name='departname']").val(departname);
			$("input[name='departname']").blur();
		}
		var id = iframe.getdepartListSelections('id');
		if (id !== undefined && id != "") {
			$('#orgIds').val(id);
			$("input[name='orgIds']").val(id);
		}
	}
</script>
<%--update-end--Author:zhangguoming  Date:20140827 for：添加 组织机构查询条件：弹出 选择组织机构列表 相关操作--%>
