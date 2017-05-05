<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="userList2" title="" queryMode="group" actionUrl="flightMonthlyController.do?fcdatagrid" 
idField="id" fit="true" sortName="dates" sortOrder="desc" >
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol  title="月报时间"   field="smonth"  query="true" ></t:dgCol>
	<t:dgCol  title="月报时间"   field="dates" formatter="yyyy-MM" hidden="true" ></t:dgCol>
	<t:dgCol  title="企业名称"   field="otherTravelInfo.name"  width="80"  ></t:dgCol>	     
	<%-- <t:dgCol title="涉旅机构名称" field="name"  query="true"   width="20"></t:dgCol> --%>
	<%-- <t:dgCol title="其他涉旅机构编码" field="organizationNum"  query="true" ></t:dgCol>	 --%>
	<t:dgCol title="基本信息id" field="otid" hidden="true"></t:dgCol>	
	<%-- <t:dgCol title="其他涉旅机构类型" field="type" query="true" replace="其他_0,高尔夫项目_1,游艇项目_2,空中飞行项目_3,机场项目_4,动车项目_5" width="20"></t:dgCol> --%>	
	<t:dgCol title="接待人次（单位：人次）" field="receptionnum" query="true" sortable="true"   width="20"></t:dgCol>	
	<t:dgCol title="营业收入（万元）" field="businessincome"  query="true" sortable="true"  width="20"></t:dgCol>
	<t:dgCol title="飞行架次(架次)" field="flighttimes"  query="true" sortable="true"  width="20"></t:dgCol>
	<t:dgCol title="审核状态" field="status" query="true" width="40" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
	<%-- <t:dgCol title="所在区" field="area" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol> --%>	     
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
		<t:dgFunOpt funname="editfm(id,otid)" title="编辑" exp="status#eq#3"></t:dgFunOpt>
	<t:dgToolBar title="月报录入" langArg="common.user" icon="icon-add" url="flightMonthlyController.do?addfm" funname="add"></t:dgToolBar>
	<%-- <t:dgDelOpt title="删除" url="flightMonthlyController.do?delfm&id={id}"/> --%>
	<t:dgFunOpt funname="detail(id,otid)" title="查看详情" ></t:dgFunOpt>
	
<%-- <t:dgToolBar title="空中飞行企业月报导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar> --%>

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
function editfm(id,otid){
	createwindow('编辑空中飞行月报信息', 'flightMonthlyController.do?editfm&fmonthId='+id+'&otid='+otid);
}

function detail(id,otid){
	createdetailwindow('空中飞行企业月报详情', 'flightMonthlyController.do?detailfm&fmonthId='+id+'&load=detail&otid='+otid);
}
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
/* function szqm(id) {
		createwindow('设置签名', 'userController.do?addsign&id=' + id);
	} */
	
function doMigrateOut(){
		JeecgExcelExport("flightMonthlyController.do?exportsxlss","userList2");
		
	}
function back(data){
	if(data.success)
	reloadTable();
}	

</script>
