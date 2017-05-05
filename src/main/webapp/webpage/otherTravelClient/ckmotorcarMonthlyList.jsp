<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="userList2" title="" queryMode="group" actionUrl="motorcarMonthlyController.do?acdatagrid"  
idField="id" fit="true" sortName="dates" sortOrder="desc" >
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol  title="月报时间"   field="smonth"  query="true" ></t:dgCol>
	<t:dgCol  title="月报时间"   field="dates" formatter="yyyy-MM" hidden="true" ></t:dgCol>
	<t:dgCol  title="企业名称"   field="otherTravelInfo.name"   ></t:dgCol>	     
	<t:dgCol title="基本信息id" field="otid" hidden="true"></t:dgCol>	
	<t:dgCol title="接待人次（单位：万人次）" field="innum" query="true" sortable="true"   width="20"></t:dgCol>	
	<t:dgCol title="出站人数（万人）" field="outnum"  query="true" sortable="true"  width="20"></t:dgCol>
	<t:dgCol title="吞吐量(万人)" field="throughput"  query="true" sortable="true"  width="20"></t:dgCol>
	<t:dgCol title="审核状态" field="status" query="true" width="40" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<t:dgToolBar title="月报录入" langArg="common.user" icon="icon-add" url="motorcarMonthlyController.do?addcm" funname="add"></t:dgToolBar>
	<t:dgFunOpt funname="edit(id,otid)" title="编辑" exp="status#eq#3"></t:dgFunOpt>
	<t:dgFunOpt funname="detail(id,otid)" title="查看详情" ></t:dgFunOpt>
</t:datagrid>
  


</div>
</div>

<script type="text/javascript">

//编辑功能
function edit(id, otid){
	createwindow("动车月报编辑", 'motorcarMonthlyController.do?editPage&cmonthId='+id+'&otid='+otid);
}

function detail(id,otid){
	createdetailwindow('动车月报信息详情', 'motorcarMonthlyController.do?detailcm&cmonthId='+id+'&load=detail&otid='+otid);
}
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
function szqm(id) {
		createwindow('设置签名', 'userController.do?addsign&id=' + id);
	}
	
function doMigrateOut(){
		JeecgExcelExport("motorcarMonthlyController.do?exportsxlss","userList2");
		
	}
function back(data){
	if(data.success)
	reloadTable();
}

</script>
