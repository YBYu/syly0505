<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="boatCheckList" title="" queryMode="group" actionUrl="boatMonthlyController.do?bmonthdatagrid" idField="id" fit="true">
<%-- <t:dgCol  title="月报时间"   field="boatMonth.dates"  formatter="yyyy-MM"  ></t:dgCol> --%>     
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol  title="企业名称"  sortable="true"  field="name" query="true"  ></t:dgCol>
	<t:dgCol  title="企业负责人"   sortable="true" field="principal" query="true"  ></t:dgCol>
	<%-- <t:dgCol title="涉旅机构名称" field="name"  query="true"   width="20"></t:dgCol> --%>
	<%-- <t:dgCol title="其他涉旅机构编码" field="organizationNum"  query="true" ></t:dgCol>	 --%>
	<t:dgCol title="基本信息id" field="boatMonth.id" hidden="true"></t:dgCol>	
	<%-- <t:dgCol title="其他涉旅机构类型" field="type" query="true" replace="其他_0,高尔夫项目_1,游艇项目_2,空中飞行项目_3,机场项目_4,动车项目_5" width="20"></t:dgCol> --%>	
	<t:dgCol title="接待人次（单位：人次）" field="boatMonth.recepNum"   width="20"></t:dgCol>	
	<t:dgCol title="营业收入（万元）" field="boatMonth.income"    width="20"></t:dgCol>
	<%-- <t:dgCol title="所在区" field="area" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol> --%>	
     <t:dgCol title="填报时间" field="boatMonth.writeDate" formatter="yyyy-MM-dd"  ></t:dgCol> 
	<t:dgCol title="审核状态" field="boatMonth.status" query="true" width="40" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="otherTravelController.do?del&id={id}"/> --%>
	<t:dgToolBar title="代填报" langArg="common.user" icon="icon-edit" onclick="daitianbaos()" ></t:dgToolBar>
	<t:dgFunOpt funname="detail(id,boatMonth.id)" title="查看详情" exp="boatMonth.status#ne#1" ></t:dgFunOpt>
	<t:dgFunOpt funname="checkwin(id,boatMonth.id)" title="审核" exp="boatMonth.status#eq#2"></t:dgFunOpt>
	<t:dgFunOpt funname="daitianbao(id,boatMonth.id)" title="代填报" exp="boatMonth.status#eq#1"></t:dgFunOpt>

</t:datagrid>
  


</div>
</div>
<script type="text/javascript">
	//表格刷新
	function reloadTable() {
		window.setTimeout(function() {
			$("#boatCheckList").datagrid('reload');
		}, 1000);

	}

	//游艇月报代填报
	function daitianbaos() {
		createwindow('代填报', 'boatMonthlyController.do?daitianbao');
	}
	//游艇月报详情
	function detail(id, bmonthId) {
		createdetailwindow('游艇月报详情', 'boatMonthlyController.do?detailbm&otid='
				+ id + '&load=detail&bmonthId=' + bmonthId);
	}

	function userListsearch() {
		$.dataGrid("#boatCheckList").reload();
	}
	//游艇月报审核
	function checkwin(id, bmonthId) {
		var button = [
				{
					name : "通过",
					callback : function() {
						$
								.ajax({
									url : "boatMonthlyController.do?addstatusbm&bmonthId="
											+ bmonthId,
									//data:{id:id},
									dataType : "json",
									success : function(data) {
										alert(data.msg);
										reloadTable();
									}
								});

					},
					focus : true
				}, {
					name : "退回",
					callback : function() {
						nocheck(bmonthId);

					},
					focus : true
				} ];
		createwindowbutton('审核', 'boatMonthlyController.do?auditbm&bmonthId='
				+ bmonthId + '&load=detail&otid=' + id, button);
		 
	}
	//游艇月报审核退回
	function nocheck(bmonthId) {

		$.ajax({
			url : "boatMonthlyController.do?nocheckbm&bmonthId=" + bmonthId,
			//data:{id:id},
			dataType : "json",
			success : function(data) {
				alert(data.msg);
				reloadTable();
			}
		});
	}

	var iframe;
	function daitianbao(id, bmonthId) {
		var button = [
				{
					name : "提交",
					callback : function() {
						iframe = this.iframe.contentWindow;
						saveObj();
						return false;

					},
					focus : true
				},
				{
					name : "暂存",
					callback : function() {
						iframe = this.iframe.contentWindow;
						var formjson = $('#boatMonthSave', iframe.document)
								.serialize();
						$
								.ajax({
									url : "boatMonthlyController.do?zancuncheckbm&otid="
											+ id,
									data : formjson,
									dataType : "json",
									success : function(data) {
										alert(data.msg);
										reloadTable();
									}
								});

					},
					focus : true
				} ];
		createwindowbutton('代填报',
				'boatMonthlyController.do?addsignbm&bmonthId=' + bmonthId
						+ '&otid=' + id, button);
	}

	/* function zancuncheck(id,bmonthId){
		var formjson = $('#boatMonthSave', iframe).serialize();
			$.ajax ({
			url:"boatMonthlyController.do?zancuncheckbm&otinfo="+id,
			data:formjson,
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
			}  	
			 }
			);
		} */
</script>
