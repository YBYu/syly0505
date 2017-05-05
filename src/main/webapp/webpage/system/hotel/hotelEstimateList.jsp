<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="userList2" title="" queryMode="group"
			actionUrl="hotelEstimateController.do?datagrid" idField="id"
			fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="year" query="true" width="10"
				replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
			<t:dgCol title="月份" field="month" query="true" width="10"
				replace="1月_1,2月_2,3月_3,4月_4,5月_5,6月_6,7月_7,8月_8,9月_9,10月_10,11月_11,12月_12"></t:dgCol>
			<t:dgCol title="地区编码" field="countryCode" query="fasle" width="20"></t:dgCol>
			<%-- <t:dgCol title="国内人数" field="domestic"   width="20"></t:dgCol>	
	<t:dgCol title="入境人数" field="entry"    width="20"></t:dgCol>
	<t:dgCol title="外国人数" field="foreignTimes"></t:dgCol>	
	<t:dgCol title="香港人数" field="hongkong"   ></t:dgCol> 
	<t:dgCol title="澳门人数" field="macao"   ></t:dgCol> 
	<t:dgCol title="台湾人数" field="taiwan"   ></t:dgCol>   --%>
			<t:dgCol title="填写人" field="bymanager" width="10"></t:dgCol>
			<t:dgCol title="填写日期" field="createTime" width="10"></t:dgCol>
			<t:dgCol title="人次" field="sum" width="10"></t:dgCol>
			<t:dgCol title="人天" field="sumD" width="10"></t:dgCol>
			<t:dgCol title="状态" field="sourceStatus" width="10"
				replace="未同步至省系统_0,已同步至省系统_1"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
			<%-- <t:dgDelOpt title="删除" url="hotelMonthlyController.do?del&id={id}"/> --%>
			<t:dgFunOpt funname="edit(id,sourceStatus)" title="编辑"></t:dgFunOpt>
			<t:dgFunOpt funname="detail(id)" title="查看详情"></t:dgFunOpt>
			<t:dgToolBar title="旅游住宿预计接待情况录入" langArg="common.user"
				icon="icon-add" funname="add"></t:dgToolBar>
			<t:dgToolBar title="导出预计接待数据" funname="exportExcel"></t:dgToolBar>
			<%-- <t:dgToolBar title="查看周报详情" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> --%>
			<%-- <t:dgToolBar title="预计接待数据导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar> --%>
			<%-- <t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar> --%>

		</t:datagrid>



	</div>
</div>

<script type="text/javascript">
function add(){
	var havedata='${havedata}';
	if(havedata=="havedata"){
		alert("本月月季接待情况已填写");
		return false;
		
	}
	createwindow('本月预计接待情况录入','hotelEstimateController.do?addorupdate', 700, 480);
}

function detail(id){
	createdetailwindow('旅游住宿预计接待详情', 'hotelEstimateController.do?detail&load=detail&hotelEstimateId='+id, 700, 480);
}

function edit(id,sourceStatus){
	if(sourceStatus == "1"){
		alert("该数据已同步，不允许编辑!");
		return;
	}
	createwindow('旅游住宿预计接待信息编辑', 'hotelEstimateController.do?editEstimate&id='+id, 700, 480);
}
	
function doMigrateOut(){
		JeecgExcelExport("hotelEstimateController.do?exportsxls","userList2");
		
	}
function back(data){
	if(data.success)
	reloadTable();
}

function reloadTable(){
	$("#userList2").datagrid("reload");
}

function exportExcel(){
	var queryParams = $("#userList2").datagrid('options').queryParams;
	var year = "";
	var month = "";
	
	if(typeof queryParams.year != "undefined"){
		year = queryParams.year;
		month = queryParams.month;
	}
	window.open("hotelEstimateController.do?exportData&year="+year+"&month="+month);
}

</script>
