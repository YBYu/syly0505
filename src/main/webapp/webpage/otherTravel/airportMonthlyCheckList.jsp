<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="airportMonthlyList" title="" queryMode="group" actionUrl="airportMonthlyController.do?amonthdatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<%-- <t:dgCol  title="月报时间"   field="airportMonth.dates" formatter="yyyy-MM"  ></t:dgCol> --%>
	<t:dgCol  title="企业名称"  field="name"  query="true" width="60" ></t:dgCol>
	<t:dgCol  title="企业负责人"  field="principal"    ></t:dgCol>	     
	<%-- <t:dgCol title="涉旅机构名称" field="name"  query="true"   width="20"></t:dgCol> --%>
	<%-- <t:dgCol title="其他涉旅机构编码" field="organizationNum"   ></t:dgCol>	 --%>
	<t:dgCol title="月报id" field="airportMonth.id" hidden="true"></t:dgCol>	
	<t:dgCol title="填报时间" field="airportMonth.reportdate" formatter="yyyy-MM-dd"  ></t:dgCol>	
	
	<%-- <t:dgCol title="其他涉旅机构类型" field="type" query="true" replace="其他_0,高尔夫项目_1,游艇项目_2,空中飞行项目_3,机场项目_4,动车项目_5" width="20"></t:dgCol> --%>	
	<%-- <t:dgCol title="接待人次（单位：人次）" field="airportMonth.receptionnum"   width="20"></t:dgCol>	
	<t:dgCol title="营业收入（万元）" field="airportMonth.businessincome"    width="20"></t:dgCol>
	 --%>
	 <%-- <t:dgCol title="所在区" field="area" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol> --%>	
    <t:dgCol title="审核状态" field="airportMonth.status" query="true" width="40" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>  
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="otherTravelController.do?del&id={id}"/> --%>
	<%-- <t:dgFunOpt funname="detail(id,airportMonth.id)" title="查看详情" ></t:dgFunOpt> --%>
<%-- <t:dgToolBar title="查看周报详情" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> --%>
<%-- <t:dgToolBar title="其他涉旅机信息导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar> --%>
<%-- <t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar> --%>
	<t:dgFunOpt funname="detail(id,airportMonth.id)" title="查看详情" exp="airportMonth.status#ne#1" ></t:dgFunOpt>
	<t:dgFunOpt funname="checkwin(id,airportMonth.id)" title="审核" exp="airportMonth.status#eq#2"></t:dgFunOpt>
	<t:dgFunOpt funname="daitianbao(id,airportMonth.id)" title="代填报" exp="airportMonth.status#eq#1"></t:dgFunOpt>

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
//表格刷新
function reloadTable(){
	window.setTimeout(function(){
		$("#airportMonthlyList").datagrid('reload');
	},1000);
	
	}
	//机场月报信息详情
function detail(id,amonthId){
	createdetailwindow('机场月报信息详情', 'airportMonthlyController.do?detailam&amonthId='+amonthId+'&load=detail&otid='+id);
}
function userListsearch(){
	$.dataGrid("#airportMonthlyList").reload();
}
 //机场月报审核通过
function checkwin(id,amonthId){
	var button = [{  
	            name: "通过",
	            callback: function(){
	            	$.ajax ({
						url:"airportMonthlyController.do?addstatusam&amonthId="+amonthId,
						//data:{id:id},
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
	            	nocheck(amonthId);
	            	
	            },
	            focus: true
	        }];
		createwindowbutton('审核','airportMonthlyController.do?auditam&amonthId='+amonthId+'&load=detail&otid='+id,button);
			 
		}
 //景区月报审核退回
function nocheck(amonthId){

	$.ajax ({
	url:"airportMonthlyController.do?nocheckam&amonthId="+amonthId,
	//data:{id:id},
	dataType:"json",
	success:function(data){
		alert(data.msg);
		reloadTable();
	}	
	 }
	);
}	
var iframe;
//景区月报代填报
function daitianbao(id,amonthId) {
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
	            	var formjson = $('#saveAirportMonth', iframe.document).serialize();
					$.ajax ({
						url:"airportMonthlyController.do?zancuncheckam&otid="+id,
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
		createwindowbutton('代填报', 'airportMonthlyController.do?addsignam&amonthId='+amonthId+'&otid='+id,button);
	} 
</script>
