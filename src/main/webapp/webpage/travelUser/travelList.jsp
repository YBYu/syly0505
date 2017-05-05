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
			actionUrl="travelController.do?travelUserDatagrid" idField="id"
			fit="true" sortName="createTime" sortOrder="desc">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="traveldataAnnual.id" hidden="true"></t:dgCol>
			<t:dgCol title="许可证编号" field="licensenum" query="false" width="10"></t:dgCol>
			<t:dgCol title="单位名称" sortable="false" query="false" field="name"
				width="15"></t:dgCol>
			<t:dgCol title="详细地址" field="address" query="false" width="20"></t:dgCol>
			<t:dgCol title="所在地区" field="area"
				replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4" query="false" width="5"></t:dgCol>
			<t:dgCol title="法人代表" field="corporate" query="false" width="5"></t:dgCol>
			<t:dgCol title="电话" field="phone" query="false" width="10"></t:dgCol>
			<t:dgCol title="审核状态" field="traveldataAnnual.status" width="10"
				query="false"
				replace="市级审核未通过_0,市级审核通过_1,市级待审核_2,市级未填报_3,省级待审核_4,省级审核未通过_5,省级审核通过_6"></t:dgCol>
			<t:dgCol title="填报时间" field="traveldataAnnual.fillDate" query="false"
				width="10"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="15"></t:dgCol>
			<t:dgFunOpt funname="edit(traveldataAnnual.status,traveldataAnnual.fillDate)" title="编辑"></t:dgFunOpt>
			<t:dgFunOpt funname="checkDetail(traveldataAnnual.id)" title="查看详情"
				exp="traveldataAnnual.status#ne#3"></t:dgFunOpt>
			<t:dgFunOpt funname="countryContent(traveldataAnnual.id)" title="审核意见"
				exp="traveldataAnnual.status#eq#5"></t:dgFunOpt>
			<t:dgFunOpt funname="countryContent(traveldataAnnual.id)" title="审核意见"
				exp="traveldataAnnual.status#eq#6"></t:dgFunOpt>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
	function reloadTable() {
		try {
			$("#travelAgency").datagrid('reload');
		} catch (e) {

		}
	}
	
	// 编辑
	function edit(status, fillDate){
		if(fillDate != null && fillDate.length != 0){
			var year = fillDate.substring(0,4);
			var nowYear = new Date().getFullYear();
			if(year >= nowYear){
				if(status != "0" && status != "3" && status != "5"){
					alert("今年基本信息年报已填报");
					return;
				}else{
					createwindow("旅行社基本信息年报编辑 ","travelController.do?travelInfoManageOfUser", 700, 500);
					return;
				}
			}
		}
		createwindow("旅行社基本信息年报编辑 ","travelController.do?travelInfoManageOfUser", 700, 500);
	}
	

	// 查看详情
	function checkDetail(id){
		createdetailwindow('旅行社基本信息年报详情','travelController.do?toCheckInfo&id='+ id, 700, 500);
	}
	
	// 查看审核意见
	function countryContent(id){
		createdetailwindow('国家系统审核意见','travelController.do?countryContent&id='+ id, 450, 160);
	}

</script>
