<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="golfCheckList" title="" queryMode="group" actionUrl="otherTravelMonthlyController.do?gmonthdatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<%-- <t:dgCol  title="月报时间"   field="golfMonth.dates" formatter="yyyy-MM" ></t:dgCol> --%>
	<t:dgCol  title="企业名称"  field="name"  query="true" width="60" ></t:dgCol>
	<t:dgCol  title="企业负责人"  field="principal" query="true"   ></t:dgCol>	     
	<%-- <t:dgCol title="涉旅机构名称" field="name"  query="true"   width="20"></t:dgCol> --%>
	<%-- <t:dgCol title="其他涉旅机构编码" field="organizationNum"   ></t:dgCol>	 --%>
	<t:dgCol title="月报id" field="golfMonth.id" hidden="true"></t:dgCol>	
	<%-- <t:dgCol title="其他涉旅机构类型" field="type" query="true" replace="其他_0,高尔夫项目_1,游艇项目_2,空中飞行项目_3,机场项目_4,动车项目_5" width="20"></t:dgCol> --%>	
	<t:dgCol title="接待人次（单位：人次）" field="golfMonth.receptionnum"   width="20"></t:dgCol>	
	<t:dgCol title="营业收入（万元）" field="golfMonth.businessincome"    width="20"></t:dgCol>
	<%-- <t:dgCol title="所在区" field="area" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol> --%>	
    <t:dgCol title="审核状态" field="golfMonth.status"  query="true" width="40" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>  
    <t:dgCol title="填报时间" field="golfMonth.writeDate" formatter="yyyy-MM-dd"  ></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="otherTravelController.do?del&id={id}"/> --%>
	<%-- <t:dgFunOpt funname="detail(id,golfMonth.id)" title="查看详情" ></t:dgFunOpt> --%>
 <t:dgToolBar title="代填报" langArg="common.user" icon="icon-edit" onclick="daitianbaos()" ></t:dgToolBar> 
<%-- <t:dgToolBar title="其他涉旅机信息导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar> --%>
<%-- <t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar> --%>
	<t:dgFunOpt funname="detail(id,golfMonth.id)" title="查看详情" exp="golfMonth.status#ne#1" ></t:dgFunOpt>
	<t:dgFunOpt funname="checkwin(id,golfMonth.id)" title="审核" exp="golfMonth.status#eq#2"></t:dgFunOpt>
	<t:dgFunOpt funname="daitianbao(id,golfMonth.id)" title="代填报" exp="golfMonth.status#eq#1"></t:dgFunOpt>

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
function reloadTable(){
	window.setTimeout(function(){
		$("#golfCheckList").datagrid('reload');
	},1000);
	
	}
	//高尔夫月报代填报
function daitianbaos(){
	createwindow('代填报', 'otherTravelMonthlyController.do?daitianbao');
}	
 
function userListsearch(){
	$.dataGrid("#golfCheckList").reload();
}
//高尔夫月报详情
	function detail(id,gMonthId){
		createdetailwindow('高尔夫月报详情', 'otherTravelMonthlyController.do?detailgm&otid='+id+'&load=detail&gMonthId='+gMonthId);
	}
 
	function userListsearch(){
		$.dataGrid("#golfCheckList").reload();
	}
	 //高尔夫月报审核
	function checkwin(id,gMonthId){
			var button = [{  
			            name: "通过",
			            callback: function(){
			            	$.ajax ({
								url:"otherTravelMonthlyController.do?addstatusgm&gMonthId="+gMonthId,
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
			            	nocheck(gMonthId);
			            	
			            },
			            focus: true
			        }];
				createwindowbutton('审核','otherTravelMonthlyController.do?auditgm&gMonthId='+gMonthId+'&load=detail&otid='+id,button);
					 
				}
	 //高尔夫月报审核退回
				function nocheck(gMonthId){
				
					$.ajax ({
					url:"otherTravelMonthlyController.do?nocheckgm&gMonthId="+gMonthId,
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
		//高尔夫月报代填报
		function daitianbao(id,gMonthId) {
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
			            	var formjson = $('#saveot', iframe.document).serialize();
							$.ajax ({
								url:"otherTravelMonthlyController.do?zancuncheckgm&otid="+id,
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
				createwindowbutton('代填报', 'otherTravelMonthlyController.do?addsigngm&gMonthId='+gMonthId+'&otid='+id,button);
			} 
 

</script>
