<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="travelAgency" title="" queryMode="group"
			actionUrl="travelController.do?datagrid" idField="id" fit="true"
			sortName="createTime" sortOrder="desc">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="许可证编号" field="licensenum" query="true" width="13"></t:dgCol>
			<t:dgCol title="单位名称" sortable="false" query="true" field="name"
				width="17"></t:dgCol>
			<t:dgCol title="详细地址" field="address" query="true" width="40"></t:dgCol>
			<t:dgCol title="法人代表" field="corporate" query="true" width="10"></t:dgCol>
			<t:dgCol title="电话" field="phone" query="false" width="10"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
			<t:dgDelOpt title="删除" url="travelController.do?del&id={id}" />
			<t:dgFunOpt funname="checkDetail(id)" title="查看详情"></t:dgFunOpt>
			<%-- <t:dgToolBar title="添加旅行社信息" langArg="common.traveldata" icon="icon-add" url="travelController.do?savetravel" funname="add"></t:dgToolBar> --%>
			<t:dgToolBar title="导出旅行社信息" langArg="common.traveldata"
				icon="icon-add" funname="exportExcel"></t:dgToolBar>
			<%-- <t:dgToolBar title="编辑旅行社信息" langArg="common.traveldata" icon="icon-edit" url="travelController.do?addorupdate" funname="update"></t:dgToolBar> --%>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
function reloadTable(){
	try{
		$("#travelAgency").datagrid('reload');
	}catch(e)
	{
		
	}	
}

function exportExcel(){
	var queryParams = $("#travelAgency").datagrid('options').queryParams;
	var licensenum = "";
	var name = "";
	var address = "";
	var corporate = "";
	
	if(typeof queryParams.licensenum != "undefined"){
		licensenum = queryParams.licensenum;
		name = queryParams.name;
		address = queryParams.address;
		corporate = queryParams.corporate;
	}
	/* $.ajax({
		url : "travelController.do?exportTravelInfo&licensenum="+licensenum+"&name="+name+"&address="+address+"&corporate="+corporate,
		dataType : "json"
	}); */
	window.open("travelController.do?exportTravelInfo&licensenum="+licensenum+"&name="+name+"&address="+address+"&corporate="+corporate);
}

function checkDetail(id){
	createdetailwindow("旅行社详情","travelController.do?addorupdate&id="+id,690,500);
}

</script>
