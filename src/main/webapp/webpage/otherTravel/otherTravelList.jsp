<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="userList2" title="" queryMode="group" actionUrl="otherTravelController.do?datagrid" sortOrder="" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>  
	<t:dgCol title="企业名称" field="name"  query="true"   width="20"></t:dgCol>
	<%-- <t:dgCol title="其他涉旅机构编码" field="organizationNum"  query="true" ></t:dgCol>	 --%>
	<%-- <t:dgCol title="其他涉旅企业id" field="id" hidden="true"></t:dgCol> --%>	
	<t:dgCol title="企业类型" field="type" query="true" replace="其他_0,高尔夫项目_1,游艇项目_2,空中飞行项目_3,机场项目_4,动车项目_5" width="20"></t:dgCol>	
	<t:dgCol title="企业负责人" field="principal"   width="20"></t:dgCol>	
	<t:dgCol title="所在城市" field="city"    width="20"></t:dgCol>
	<t:dgCol title="所在区" field="area" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>	
	       
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	 <t:dgDelOpt title="删除" url="otherTravelController.do?del&id={id}"/>
	<%-- <t:dgDelOpt title="编辑" url="otherTravelController.do?edit&otid={id}"/> --%>
	<t:dgFunOpt funname="editssss(id)" title="编辑" ></t:dgFunOpt>
	<t:dgFunOpt funname="detail(id)" title="查看详情" ></t:dgFunOpt>
	<t:dgToolBar title="添加涉旅企业" langArg="common.user" icon="icon-edit" url="otherTravelController.do?addUser" funname="add"></t:dgToolBar >
<%-- <t:dgToolBar title="查看周报详情" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> --%>
<%--导出的是酒店的数据，先屏蔽掉，后续修改
<t:dgToolBar title="其他涉旅机信息导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar>
 --%>

 <%-- <t:dgToolBar title="添加涉旅" langArg="common.user" icon="icon-put" url="" funname="add"></t:dgToolBar> --%> 

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">

function reloadTable(){
	window.setTimeout(function(){
		$("#userList2").datagrid('reload');
	},1000);
	
	}
	//其他涉旅企业信息详情
function detail(id){
	createdetailwindow('其他涉旅企业信息详情', 'otherTravelController.do?detail&load=detail&otherTravelId='+id);
}
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
//企业信息编辑
function editssss(id){
		createwindow('企业信息编辑', 'otherTravelController.do?details&otherTravelId='+id);
	}	
	
 
//添加涉旅成功后 回调 刷新列表	
function reloadTable(){
	window.setTimeout(function(){
		$("#userList2").datagrid('reload');
	},1000);
	
	}		
 
</script>
