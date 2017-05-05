<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="financeReport" title="" queryMode="group"
			actionUrl="travelAnnualFinanceController.do?auditdatagrid"
			idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="travelAnnual.id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="travelAnnual.year" width="10"
				query="false"
				replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
			<t:dgCol title="单位名称" field="name" query="true" width="20"></t:dgCol>
			<t:dgCol title="许可证编号" field="licensenum" query="true" width="15"></t:dgCol>
			<t:dgCol title="填报人" field="travelAnnual.reportPerson" width="10"></t:dgCol>
			<t:dgCol title="填报日期" field="travelAnnual.reportTime" width="10"></t:dgCol>
			<t:dgCol title="审核状态" field="travelAnnual.status" query="true"
				width="10" sortable="true"
				replace="市级审核未通过_3,市级待审核_2,市级未填报_1,市级审核通过_4,省级待审核_5,省级审核未通过_6,省级审核通过_7"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
			<t:dgFunOpt funname="checkDetail(id,travelAnnual.id)" title="查看详情"
				exp="travelAnnual.status#ne#1"></t:dgFunOpt>
			<t:dgFunOpt funname="checkwin(id,travelAnnual.id)" title="审核"
				exp="travelAnnual.status#eq#2"></t:dgFunOpt>
			<t:dgFunOpt funname="daitianbao(id,travelAnnual.id)" title="代填报"
				exp="travelAnnual.status#eq#1"></t:dgFunOpt>
			<t:dgFunOpt funname="countryContent(travelAnnual.id)" title="审核意见"
				exp="travelAnnual.status#eq#6"></t:dgFunOpt>
			<t:dgFunOpt funname="countryContent(travelAnnual.id)" title="审核意见"
				exp="travelAnnual.status#eq#7"></t:dgFunOpt>
			<t:dgToolBar title="编辑财务年报" funname="edit()"></t:dgToolBar>
			<t:dgToolBar title="撤回财务年报" funname="revocation()"></t:dgToolBar>
		</t:datagrid>



	</div>
</div>

<script type="text/javascript">

// 撤回年报
function revocation(){
	var selection = $("#financeReport").datagrid("getSelected");
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var annualId = selection["travelAnnual.id"];
	var status = selection["travelAnnual.status"];
	if(status != "5"){
		alert("该数据不允许撤回");
		return;
	}
	$.ajax({
		url : "travelAnnualFinanceController.do?revocation&id="+annualId,
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

// 编辑年报
function edit(){
	var selection = $("#financeReport").datagrid("getSelected");
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var annualId = selection["travelAnnual.id"];
	var status = selection["travelAnnual.status"];
	var id = selection.id;
	if(status != "4"){
		alert("该数据不允许编辑");
		return;
	}
	var button = [{  
            name: "提交",
            callback: function(){
            	iframe = this.iframe.contentWindow;
			saveObj();
			return false;
            },
            focus: true
        }];
	
	createwindowbutton('财务年报编辑','travelAnnualFinanceController.do?addsign&quarteroneid='+annualId+'&travelid='+id,button,850,500);}

//查看详情
function checkDetail(id, fid) { //id为旅行社ID,fid为年报id
	createdetailwindow('财务年报详情','travelAnnualFinanceController.do?audit&id='+fid+'&load=detail&travelid='+id, 780, 500);
}

//表格刷新
function reloadTable() {
	try {
		$("#financeReport").datagrid('reload');
	} catch (e) {

	}
}

var iframe;
function daitianbao(id, fid) {
var button = [{  
	            name: "提交",
	            callback: function(){
	            	iframe = this.iframe.contentWindow;
				saveObj();
				return false;
	            },
	            focus: true
	        },{
	            name: "暂存",
	            callback: function(){
	           		 iframe = this.iframe.contentWindow;
	            	var formjson = $('#formobj', iframe.document).serialize();
					$.ajax ({
						url:"travelAnnualFinanceController.do?saveTemporary&travelid="+id,
						data:formjson,
						dataType:"json",
						success:function(data){    
							alert(data.msg);
							reloadTable();
							}  	
					 	}
					);
	            	
	            },
	            focus: true
	        }];
		createwindowbutton('旅行社财务年报代填报','travelAnnualFinanceController.do?addsign&quarteroneid='+fid+'&travelid='+id,button,850,500);
	}   
	
	function checkwin(id,fid){
	var button = [{
	            name: "通过",
	            callback: function(){
	            	$.ajax ({
						url:"travelAnnualFinanceController.do?addstatus&id="+fid+"&travelid="+id,
						dataType:"json",
						success:function(data){
							alert(data.msg);
							reloadTable();
						}	
						 }
						);
	            	
	            },
	            focus: true
	        },{
	            name: "退回",
	            callback: function(){
	            	nocheck(fid);
	            },
	            focus: true
	        }];
		createwindowbutton('旅行社财务年报审核','travelAnnualFinanceController.do?audit&id='+fid+'&load=detail&travelid='+id,button);
		}
	
	
		function nocheck(spotId){
		
			$.ajax ({
			url:"travelAnnualFinanceController.do?nocheck&id="+spotId,
			//data:{id:id},
			dataType:"json",
			success:function(data){
				alert(data.msg);
				reloadTable();
			}	
			 }
			);
		}
		
		// 查看审核意见
		function countryContent(id){
			createdetailwindow('国家系统审核意见','travelAnnualFinanceController.do?countryContent&id='+ id, 450, 160);
		}
	
</script>
