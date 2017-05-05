<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="motorMonthList" title="" queryMode="group" actionUrl="motorcarMonthlyController.do?cmonthdatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<%-- <t:dgCol  title="月报时间"   field="motorcarMonth.dates" formatter="yyyy-MM" ></t:dgCol> --%>
	<t:dgCol  title="企业名称"   field="name" sortable="true" query="true" ></t:dgCol>
	<t:dgCol  title="企业负责人"   sortable="true" field="principal"  query="true" ></t:dgCol>	     
	<%-- <t:dgCol title="涉旅机构名称" field="name"  query="true"   width="20"></t:dgCol> --%>
	<%-- <t:dgCol title="其他涉旅机构编码" field="organizationNum"  query="true" ></t:dgCol>	 --%>
	<%-- <t:dgCol title="基本信息id" field="otid" hidden="true"></t:dgCol>	 --%>
	<t:dgCol title="月报id" field="motorcarMonth.id" hidden="true"></t:dgCol>
	<%-- <t:dgCol title="其他涉旅机构类型" field="type" query="true" replace="其他_0,高尔夫项目_1,游艇项目_2,空中飞行项目_3,机场项目_4,动车项目_5" width="20"></t:dgCol> --%>	
	<t:dgCol title="进站人数（万人次）" field="motorcarMonth.innum"    width="20"></t:dgCol>	
	<t:dgCol title="出站人数（万人次）" field="motorcarMonth.outnum"    width="20"></t:dgCol>
	<t:dgCol title="吞吐量(万人次)" field="motorcarMonth.throughput"    width="20"></t:dgCol>
	<t:dgCol title="填报时间" field="motorcarMonth.writeDate" formatter="yyyy-MM-dd"  ></t:dgCol>
	<t:dgCol title="审核状态" field="motorcarMonth.status" query="true" width="40" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
	<%-- <t:dgCol title="所在区" field="area" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol> --%>	     
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="motorcarMonthlyController.do?delfm&id={id}"/> --%>
	<t:dgToolBar title="代填报" langArg="common.user" icon="icon-edit" onclick="daitianbaos()" ></t:dgToolBar>
	<t:dgFunOpt funname="detail(id,motorcarMonth.id)" title="查看详情" exp="motorcarMonth.status#ne#1" ></t:dgFunOpt>
	<t:dgFunOpt funname="checkwin(id,motorcarMonth.id)" title="审核" exp="motorcarMonth.status#eq#2"></t:dgFunOpt>
	<t:dgFunOpt funname="daitianbao(id,motorcarMonth.id)" title="代填报" exp="motorcarMonth.status#eq#1"></t:dgFunOpt>
	
<%-- <t:dgToolBar title="查看周报详情" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> --%>
<%-- <t:dgToolBar title="其他涉旅机信息导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar> --%>
<%-- <t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar> --%>

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
function reloadTable(){
	window.setTimeout(function(){
		$("#motorMonthList").datagrid('reload');
	},1000);
	
	}
	//动车月报代填报
function daitianbaos(){
	createwindow('代填报', 'motorcarMonthlyController.do?daitianbao');
	
}	//动车月报信息详情
function detail(id,cmonthId){
	createdetailwindow('动车月报信息详情', 'motorcarMonthlyController.do?detailcm&cmonthId='+cmonthId+'&load=detail&otid='+id);
}
function userListsearch(){
	$.dataGrid("#motorMonthList").reload();
}
//动车月报审核
	function checkwin(id,cmonthId){
		var button = [{  
		            name: "通过",
		            callback: function(){
		            	$.ajax ({
							url:"motorcarMonthlyController.do?addstatuscm&cmonthId="+cmonthId,
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
		            	nocheck(cmonthId);
		            	
		            },
		            focus: true
		        }];
			createwindowbutton('审核','motorcarMonthlyController.do?auditcm&cmonthId='+cmonthId+'&load=detail&otid='+id,button);
				 
			}
			//动车月报审核退回
			function nocheck(cmonthId){
			
				$.ajax ({
				url:"motorcarMonthlyController.do?nocheckcm&cmonthId="+cmonthId,
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
	//动车月报代填报
	function daitianbao(id,cmonthId) {
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
		            	var formjson = $('#savemotorMonth', iframe.document).serialize();
						$.ajax ({
							url:"motorcarMonthlyController.do?zancuncheckcm&otid="+id,
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
			createwindowbutton('代填报', 'motorcarMonthlyController.do?addsigncm&cmonthId='+cmonthId+'&otid='+id,button);
		} 
		

	

</script>
