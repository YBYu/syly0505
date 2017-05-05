<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="userList2" title="" queryMode="group" actionUrl="boatMonthlyController.do?bcdatagrid" 
idField="id" fit="true" sortName="dates" sortOrder="desc">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol  title="月报时间"   field="smonth"  query="true" ></t:dgCol>
	<t:dgCol  title="月报时间"   field="dates" formatter="yyyy-MM" hidden="true" ></t:dgCol>
	<t:dgCol  title="企业名称"   field="otherTravelInfo.name"   ></t:dgCol>	     
	<t:dgCol title="基本信息id" field="otid" hidden="true"></t:dgCol>	
	<t:dgCol title="接待人次（单位：人次）" field="recepNum"   width="20"></t:dgCol>	
	<t:dgCol title="营业收入（万元）" field="income"    width="20"></t:dgCol>
	<t:dgCol title="审核状态" field="status" query="true" width="40" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<t:dgFunOpt funname="edit(id,otid)" title="编辑" exp="status#eq#3"></t:dgFunOpt>
	<t:dgFunOpt funname="detailbm(id,otid)" title="查看详情" ></t:dgFunOpt>
	<t:dgToolBar title="月报录入" langArg="common.user" icon="icon-add" url="boatMonthlyController.do?addbm" funname="add"></t:dgToolBar>
</t:datagrid>
</div>
</div>

<script type="text/javascript">

// 编辑功能
function edit(id, otid){
	createwindow("游艇月报编辑", 'boatMonthlyController.do?editPage&bmonthId='+id+'&otid='+otid);
}

function detailbm(id,otid){
	createdetailwindow('游艇月报详情', 'boatMonthlyController.do?detailbm&bmonthId='+id+'&load=detail&otid='+otid);
}
function userListsearch(){
	$.dataGrid("#userList2").reload();
}	
function doMigrateOut(){
		JeecgExcelExport("hotelMonthlyController.do?exportsxlss","userList2");
		
	}
function back(data){
	if(data.success)
	reloadTable();
}

</script>
