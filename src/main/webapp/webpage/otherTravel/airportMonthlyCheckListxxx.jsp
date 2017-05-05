<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<div id="tempSearchColums" style="display: none">
	<div name="searchColums"></div></div>
<t:datagrid name="airportMonthlyList" actionUrl="airportMonthlyController.do?amonthdatagrid" queryMode="group" idField="id" fit="true">
	<t:dgCol title="ID" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol  title="企业名称"   field="name"   ></t:dgCol>
	<t:dgCol  title="企业负责人"   field="principal"   ></t:dgCol>
	<t:dgCol  title="月报时间"   field="airportMonth.dates" formatter="yyyy-MM" query="true" ></t:dgCol> 
	<t:dgCol title="基本信息id" field="airportMonth.oid" hidden="true"></t:dgCol>	
	<%-- <t:dgCol title="审核状态" field="airportMonth.status"></t:dgCol>  --%>
	<t:dgCol title="审核状态" field="airportMonth.status" query="true" width="40" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
	<%-- <t:dgCol title="是否代填报" field="hisreport"></t:dgCol> --%>
	<%-- <t:dgCol title="填报人" field="reporter"></t:dgCol> --%>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="airportMonthlyController.do?delam&id={id}"/> --%>
	<t:dgFunOpt funname="detail(id,airportMonth.id)" title="查看详情" exp="airportMonth.status#ne#1" ></t:dgFunOpt>
	<t:dgFunOpt funname="checkwin(id,airportMonth.id)" title="审核" exp="airportMonth.status#eq#2"></t:dgFunOpt>
	<t:dgFunOpt funname="daitianbao(id,airportMonth.id)" title="代填报" exp="airportMonth.status#eq#1"></t:dgFunOpt>

</t:datagrid>

</div>
</div>
<script type="text/javascript">

function detail(id,amonthId){
	createwindow('机场信息详情', 'airportMonthlyController.do?detailam&amonthId='+amonthId+'&load=detail&otid='+id);
}
function userListsearch(){
	$.dataGrid("#airportMonthlyList").reload();
}
/* function szqm(id) {
		createwindow('设置签名', 'userController.do?addsign&id=' + id);
	} */
	
/* function doMigrateOut(){
		JeecgExcelExport("airportMonthlyController.do?exportsxls","airportMonthlyList");
		
	} */
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
			/* $.ajax ({
			url:"scenicMonthController.do?addstatus&id="+id,
			//data:{id:id},
			dataType:"json",  
			success:function(data){
				alert(data.msg);
				reloadTable();
			}	
			 }
			); */
		}
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