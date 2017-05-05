<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">

	<div region="center" style="padding: 1px;">
		<t:datagrid name="noticeList1" title="通知列表" autoLoadData="true"
			actionUrl="noticeController.do?dataGrid&noticerange=${range}" sortName="createDate"
			sortOrder="desc" fitColumns="true" idField="id" fit="true"
			queryMode="group" checkbox="false" queryBuilder="false" >
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="通知范围" field="range" hidden="true" ></t:dgCol>
			<t:dgCol title="通知标题" field="title"  width="60"></t:dgCol>
			<t:dgCol title="发布者" field="announcer" width="40"></t:dgCol>
			<t:dgCol title="通知来源" field="source" width="120" query="true"
				replace="三亚旅游统计管理系统_0,A级景区管理系统_1,旅行社管理系统_2,星级酒店管理系统_3,旅游统计报送系统_4"></t:dgCol>
			<t:dgCol title="通知状态" field="status" width="30" query="true"
				replace="未发布_0,已发布_1,已失效_2"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="60"></t:dgCol>
			<t:dgFunOpt funname="detail(id)" title="查看详情" ></t:dgFunOpt>
		</t:datagrid>
	</div>
</div>
<script>
	 
	function detail(id){
		createdetailwindow('通知详情', 'noticeController.do?detail&load=detail&id='+ id,960,800);
	}
</script>