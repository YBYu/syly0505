<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="userList2" title="" queryMode="group"
			actionUrl="ideaBackController.do?ideaBackDatagrid" idField="id"
			fit="true" sortName="createDate" sortOrder="desc">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="内容" field="content" query="true" width="20"></t:dgCol>

			<t:dgCol title="反馈人" field="ideaUserName" query="true" width="10"></t:dgCol>
			<t:dgCol title="反馈时间" field="createDate"
				formatter="yyyy-MM-dd hh:mm:ss" width="10"></t:dgCol>
			<t:dgCol title="反馈状态" field="status" query="true" sortable="true"
				replace="<font color=\"red\">未回复</font>_0,<font color=\"#d2980c\">已回复</font>_1"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="100"></t:dgCol>

			<t:dgDelOpt title="删除" url="ideaBackController.do?del&id={id}" />

			<t:dgFunOpt funname="saveBack(id)" title="回复管理" exp="status#eq#0"></t:dgFunOpt>
			<t:dgFunOpt funname="detail(id)" title="查看详情" exp="status#eq#1"></t:dgFunOpt>



		</t:datagrid>


	</div>

</div>
<%--
        <span>
            <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;" title="操作时间 "><t:mutiLang langKey="operate.time"/>: </span>
            <input type="text" name="createDate" style="width: 100px; height: 24px;">~
            <input type="text" name="replyDate" style="width: 100px; height: 24px; margin-right: 20px;">
        </span>  --%>

<script type="text/javascript">
	function report(id) {
		createdetailwindow('填写问卷',
				'questionNaireController.do?reportwenjuan&analyzeid=' + id);
	}

	function userListsearch() {
		$.dataGrid("#userList2").reload();
	}

	function detail(id) {
		createdetailwindow('意见反馈详情',
				'ideaBackController.do?detailContent&ideabackid=' + id);
	}

	function analyze(id) {
		createdetailwindow('问卷统计',
				'questionNaireController.do?wenjuan&analyzeid=' + id, 600, 400);
	}

	var iframe;
	function saveBack(id) {

		var button = [ {
			name : "保存",
			callback : function() {
				iframe = this.iframe.contentWindow;
				saveObj();
				return false;

			},
			focus : true
		} ];
		createwindowbutton('反馈回复', 'ideaBackController.do?backSave&ideabackid='
				+ id, button);

	}
</script>
