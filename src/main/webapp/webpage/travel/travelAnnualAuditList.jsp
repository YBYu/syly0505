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
			actionUrl="travelController.do?annualauditdatagrid" idField="id"
			fit="true" sortName="createTime" sortOrder="desc">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="traveldataAnnual.id" hidden="true"></t:dgCol>
			<t:dgCol title="许可证编号" field="licensenum" query="true" width="10"></t:dgCol>
			<t:dgCol title="单位名称" sortable="false" query="true" field="name"
				width="15"></t:dgCol>
			<t:dgCol title="详细地址" field="address" query="false" width="25"></t:dgCol>
			<t:dgCol title="所在地区" field="area"
				replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4" query="true" width="5"></t:dgCol>
			<t:dgCol title="法人代表" field="corporate" query="false" width="5"></t:dgCol>
			<t:dgCol title="电话" field="phone" query="false" width="10"></t:dgCol>
			<t:dgCol title="审核状态" field="traveldataAnnual.status" width="10"
				query="true"
				replace="市级审核未通过_0,市级审核通过_1,市级待审核_2,市级未填报_3,省级待审核_4,省级审核未通过_5,省级审核通过_6"></t:dgCol>
			<t:dgCol title="填报时间" field="traveldataAnnual.fillDate" query="false"
				width="10"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
			<t:dgFunOpt funname="checkDetail(traveldataAnnual.id)" title="查看详情"
				exp="traveldataAnnual.status#ne#3"></t:dgFunOpt>
			<t:dgFunOpt funname="check(traveldataAnnual.id)" title="审核"
				exp="traveldataAnnual.status#eq#2"></t:dgFunOpt>
			<t:dgFunOpt funname="fill(id,traveldataAnnual.id)" title="代填报"
				exp="traveldataAnnual.status#eq#3"></t:dgFunOpt>
			<t:dgFunOpt funname="countryContent(traveldataAnnual.id)" title="审核意见"
				exp="traveldataAnnual.status#eq#5"></t:dgFunOpt>
			<t:dgFunOpt funname="countryContent(traveldataAnnual.id)" title="审核意见"
				exp="traveldataAnnual.status#eq#6"></t:dgFunOpt>
			<t:dgToolBar title="添加旅行社信息" langArg="common.traveldata" height="630"
				icon="icon-add" url="travelController.do?savetravel" funname="add"></t:dgToolBar>
			<t:dgToolBar title="编辑旅行社信息" funname="edit()"></t:dgToolBar>
			<t:dgToolBar title="撤回旅行社信息" funname="revocation()"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
	function reloadTable() {
		try {
			// 重新加载，分页时加载当前页数据，你要的刷新，应该是用这个，如果只是刷新，不需要参数，可以直接使用  $('#id').datagrid('reload');
			$("#travelAgency").datagrid('reload');
		} catch (e) {

		}
	}
	
	// 编辑
	function edit(){
		var selection = $("#travelAgency").datagrid('getSelected');
		if(selection == null){
			alert("请选择一条数据!");
			return;
		}
		var status = selection["traveldataAnnual.status"];
		var id = selection.id;
		if(status != "1"){
			alert("该数据不允许编辑");
			return;
		}
		createwindow("旅行社基本信息年报编辑 ","travelController.do?tofilltravelinfo&id="+ id, 700, 500);
	}
	
	// 撤回
	function revocation(){
		var selection = $("#travelAgency").datagrid('getSelected');
		if(selection == null){
			alert("请选择一条数据!");
			return;
		}
		var status = selection["traveldataAnnual.status"];
		var id = selection["traveldataAnnual.id"];
		if(status != "4"){
			alert("该数据不允许撤回");
			return;
		}
		$.ajax({
			url : "travelController.do?revocation&id="+id,
			dataType : "json",
			success : function(data) {
				if(data == "success"){
					reloadTable();
					alert("撤回成功!");
				}else{
					alert("撤回失败!");
				}
			}
		});
	}
	
	// 代填报
	function fill(id) {
		createwindow("旅行社基本信息年报代填报 ","travelController.do?tofilltravelinfo&id="+ id, 700, 500);
	}
	
	// 审核
	function check(id) {
		var button = [
				{
					name : "通过",
					callback : function() {
						$.ajax({
							url : "travelController.do?checkAnnualInfo&id="+ id + "&option=1",
							dataType : "json",
							success : function(data) {
								alert(data.msg);
								reloadTable();
							}
						});
					},
					focus : true
				},
				{
					name : "退回",
					callback : function() {
						$.ajax({
							url : "travelController.do?checkAnnualInfo&id="+ id + "&option=0",
							dataType : "json",
							success : function(data) {
								alert(data.msg);
								reloadTable();
							}
						});
					},
					focus : true
				} ];
		createwindowbutton('旅行社基本信息年报审核','travelController.do?toCheckInfo&id='+ id, button, 700, 500);
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
