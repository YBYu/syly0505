<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="userList2" title="" queryMode="group" actionUrl="scenicController.do?annualdatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="景区名称" field="name"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="景区编码" sortable="false"  query="true"  field="code" width="15"></t:dgCol>
	<t:dgCol title="所属辖区" field="area"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5" ></t:dgCol>
	<t:dgCol title="所属湾区" field="bayType"  query="true" replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4" ></t:dgCol>	
	<t:dgCol title="景区等级" field="level" query="true" queryMode="group" sortable="true" replace="未评定_6,AAAAA_1,AAAA_2,AAA_3,AA_4,A_5"></t:dgCol>
	<t:dgCol title="景区类型" sortable="false" field="scenictype" replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10"  width="15"></t:dgCol>
	
	<t:dgCol title="操作" field="opt" width="20"  ></t:dgCol>
	<t:dgDelOpt title="删除" url="scenicController.do?del&id={id}&name={name}"/>
	
 	<%-- <t:dgFunOpt funname="update(id)"  title="代填报"></t:dgFunOpt> --%>	
<%--	<t:dgToolBar title="添加景区" langArg="common.user" icon="icon-add" url="scenicController.do?add" funname="add"></t:dgToolBar> --%>
<%-- <t:dgToolBar title="景区详情" langArg="common.user" icon="icon-edit" url="scenicController.do?addorupdate" funname="update"></t:dgToolBar>
--%>
    <t:dgFunOpt funname="detail(id)" title="查看详情" ></t:dgFunOpt>

<%--     	<t:dgToolBar title="添加景区" langArg="common.user" icon="icon-add" url="scenicController.do?add" funname="add"></t:dgToolBar>
 --%>   <t:dgToolBar title="基本信息导出" langArg="common.user" icon="icon-put" funname="daochu"></t:dgToolBar>
   
   

<%-- <t:dgToolBar title="基本信息导出" langArg="common.user" icon="icon-put" funname="daochu"></t:dgToolBar> --%>

<%-- 
<t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar>
--%>
</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
//导出景区信息
function daochu(){
	var queryParams = $("#userList2").datagrid('options').queryParams;
	var name = "";
	var code = "";
	var area="";
	var bayType="";
	var level_begin="";
	var level_end="";
	
	if(typeof queryParams.name!= "undefined"){
		name = queryParams.name;
		code = queryParams.code;
		area=queryParams.area;
		bayType=queryParams.bayType;
		level_begin=queryParams.level_begin;
		level_end=queryParams.level_end;
	}
	window.open("scenicController.do?excelExport&name="+name+"&code="+code+"&status="+status+"&area="+area+"&bayType="+bayType+"&level_begin="+level_begin+"&level_end="+level_end);
}
//景区编辑
 function update(id){
		createwindow('景区编辑', 'scenicController.do?scenicmodify&scenicid='+id); 

	}    

//景区详情
function detail(id){
		createdetailwindow('景区详情', 'scenicController.do?detialinfo&scenicid='+id);  
	} 
//表格刷新
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
	 
 
</script>
