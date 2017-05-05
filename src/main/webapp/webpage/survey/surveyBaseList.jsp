<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="surveyBaseList" title="调查问卷列表" autoLoadData="true"
			actionUrl="surveyServiceController.do?dataGrid" sortName="createTime" sortOrder="desc" fitColumns="true" idField="id"
			fit="true" queryMode="group" checkbox="false" queryBuilder="false">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="提交时间" field="createTime"
				formatter="yyyy-MM-dd" query="true" frozenColumn="true"
				width="120"></t:dgCol>
			<t:dgCol title="问卷标题" field="name" width="120" query="true"></t:dgCol>
			<t:dgCol title="问卷状态" field="status" width="120" query="true"
				replace="未发布_0,已发布_1,已回收_2"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="120"></t:dgCol>
			<t:dgFunOpt funname="update(id)"  title="发布"></t:dgFunOpt>
			<t:dgFunOpt funname="update(id)"  title="收回"></t:dgFunOpt>
			<t:dgFunOpt funname="update(id)"  title="问卷统计"></t:dgFunOpt>
			<t:dgToolBar operationCode="add" title="新增问卷" icon="icon-add"
				url="" funname="addSurvey"></t:dgToolBar>
			<t:dgToolBar operationCode="edit" title="编辑问卷" icon="icon-edit"
				url="" funname="update"></t:dgToolBar>
			<t:dgToolBar operationCode="delete" title="删除问卷" icon="icon-edit"
				url="" funname="update"></t:dgToolBar>	
		</t:datagrid>
	</div>
</div>

<script>
var button = [{  
    name: "确定",
    callback: saveSurvey,
    focus: true
}];

	function addSurvey(){
		createwindowbutton('新增问卷', 'surveyServiceController.do?addSurvey',button, 800, 600);
	}
	
	function saveSurvey(){
		var $tableObj = $(this.DOM.main[0].childNodes[0].children[1].contentWindow.document.body);
    	// 问卷名称
    	var surveyName = $tableObj.find("#surveyName")[0].value;
    	// 问卷说明
    	var declare = $tableObj.find("#declare")[0].value;
    	// 问卷开始时间
    	var startDate = $tableObj.find(".combo-value")[0].value;
    	// 问卷结束时间
    	var endDate = $tableObj.find(".combo-value")[1].value;
    	
    	var surveyTable = {
    			"surveyName" : surveyName,
    			"declare" : declare,
    			"startDate" : startDate,
    			"endDate" : endDate,
    			"questions" : []
    	};
    	
    	var questions = $tableObj.find(".question");
    	for (var i = 0; i < questions.length; i++) {
    		var obj = new Object();
    		obj.options = new Array();
    		// 问题
			obj.question = questions[i].cells[1].childNodes[0].value;
    		// 选项
    		var options = $tableObj.find(".option"+(i+1));
    		for (var j = 0; j < options.length; j++) {
    			obj.options.push(options[j].cells[1].childNodes[0].value);
			}
    		surveyTable.questions.push(obj);
		}
    	$.ajax ({
    		contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: "data="+JSON.stringify(surveyTable),
            url:"surveyServiceController.do?saveSurvey",
            dataType: "json",
             error: function(request) {
                 alert("保存失败!");
             },
             success: function(data) {
            	 alert("保存成功!");
            	 reloadTable();
             }
			 }
			);
	}
	
	function back(){
		reloadTable();
	}
</script>
