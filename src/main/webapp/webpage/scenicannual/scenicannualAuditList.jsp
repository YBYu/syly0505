<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="userList2" title="" queryMode="group" actionUrl="scenicAnnualController.do?annualdatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="编号" field="scenicyear.id" hidden="true"></t:dgCol>
	<t:dgCol title="年份" field="scenicyear.year"  ></t:dgCol>     
	<t:dgCol title="景区名称" field="name"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="所属辖区" field="area"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5"></t:dgCol>		
	<t:dgCol title="景区类型" field="scenictype" query="true" replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10" ></t:dgCol>	
	<t:dgCol title="景区等级" field="level" query="true"  sortable="true" replace="未评定_6,AAAAA_1,AAAA_2,AAA_3,AA_4,A_5" queryMode="group"></t:dgCol>
	<%-- <t:dgCol title="资产总额(万元)" field="scenicyear.assetstotal"   ></t:dgCol> 
	<t:dgCol title="经营收入(万元)" field="scenicyear.totalincome"   ></t:dgCol>   
	<t:dgCol title="接待人数(万人次)" field="scenicyear.receptionnum" ></t:dgCol>   --%>
	<%-- <t:dgCol title="团散比" field="scenicyear.compare"   ></t:dgCol>   --%>   
	<t:dgCol field="scenicyear.creatTime" title="填报时间" formatter="yyyy-MM-dd" query="true" queryMode="group"></t:dgCol> 
	<t:dgCol title="审核状态" field="scenicYear.status" query="true" sortable="ture" replace="未分配_1,已分配_2,未填报_3,提交待审_4,退回修订_5,区级审核通过_6,市级审核通过_7,省级审核通过_8,国家审核通过_9,省级待审核_10"></t:dgCol>	
	<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
<%-- 	
	<t:dgDelOpt title="删除" url="scenicWeekController.do?del&id={id}&name={name}"/>
--%>	
	<t:dgFunOpt funname="detail(id,scenicyear.id)" title="查看详情" ></t:dgFunOpt> 
  	<t:dgFunOpt funname="checkwin(id,scenicyear.id,scenicYear.status)" title="审核" exp="scenicYear.status#eq#4" ></t:dgFunOpt> 
  	<t:dgFunOpt funname="checkwin(id,scenicyear.id,scenicYear.status)" title="审核" exp="scenicYear.status#eq#6" ></t:dgFunOpt>
  	<t:dgFunOpt funname="edit(id,scenicyear.id)" title="编辑" exp="scenicYear.status#eq#7"></t:dgFunOpt>
	<t:dgFunOpt funname="back(id,scenicyear.id)" title="撤回审核" exp="scenicYear.status#eq#10"></t:dgFunOpt>	 
	<t:dgFunOpt funname="daitianbao(id,scenicyear.id)" title="代填报" exp="scenicYear.status#eq#3"></t:dgFunOpt>
	<t:dgFunOpt funname="daitianbao(id,scenicyear.id)" title="代填报" exp="scenicYear.status#eq#5"></t:dgFunOpt>
	<t:dgFunOpt funname="comments(id,scenicyear.id)" title="查看审核意见" exp="scenicYear.status#eq#5"></t:dgFunOpt>
	<t:dgFunOpt funname="comments(id,scenicyear.id)" title="查看审核意见" exp="scenicYear.status#eq#8"></t:dgFunOpt>
	
<%-- 
	<t:dgToolBar title="代填报" langArg="common.user" icon="icon-add" url="scenicAnnualController.do?add" funname="add"></t:dgToolBar>
--%>
<%-- <t:dgToolBar title="查看周报详情" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> --%>
<%-- 
<t:dgToolBar title="景区数据导出" langArg="common.user" icon="icon-put" url="excelController.do?toexcel" funname="doMigrateOut"></t:dgToolBar>

<t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar>
--%>
</t:datagrid>
    


</div>
</div>

<script type="text/javascript">
//加载日期插件
$(document).ready(function(){
    $("input[name='scenicyear.creatTime_begin']").attr("class","Wdate").attr("style","height:30px;width:138.6px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
    $("input[name='scenicyear.creatTime_end']").attr("class","Wdate").attr("style","height:30px;width:138.6px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
});
//景区年报信息详情
function detail(id,spotId){
		createdetailwindow('景区年报信息详情', 'scenicAnnualController.do?detial&yearid='+spotId+'&load=detail&scenicdataid='+id);
	} 
	//景区年报审核意见
	function comments(id,spotId){
		createdetailwindow('景区年报审核意见详情', 'scenicAnnualController.do?comments&yearid='+spotId,600,280);
	} 
	//景区年报编辑
function edit(id,spotId){
	 createwindow('景区年报编辑','scenicAnnualController.do?edit&yearid='+spotId+'&scenicdataid='+id,850,450);
}//景区年报撤回
function back(id,spotId){
	 $.ajax ({
			url:"scenicAnnualController.do?back&yearid="+spotId,
			dataType:"json", 
			success:function(data){    
				alert(data.msg);
				reloadTable();
				}  	
		 	});
}


 //代填报
function daitianbao(id,spotId) {
	

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
							url:"scenicAnnualController.do?zancuncheck&scenicdataid="+id+"&yearid="+spotId,
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

			createwindowbutton('代填报','scenicAnnualController.do?addsign&scenicdataid='+ id+"&yearid="+spotId,button);
		
		}
	//景区年报审核
	function checkwin(id, spotId,status) {
		var roleName='${roleName}';
		if(roleName=="区级管理员"&status=="6"){
			alert("您已经审核过了");
			return false;
		}
		var button = [ {
			name : "通过",
			callback : function() {
				$.ajax({
					url : "scenicAnnualController.do?addstatus&id=" + spotId,
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
				nocheck(spotId);

			},
			focus : true
		} ];
		createwindowbutton('年报审核', 'scenicAnnualController.do?audit&monthid='
				+ spotId + '&load=detail&scenicdataid=' + id, button);
		 
	}
	//审核退回
	function nocheck(spotId) {

		$.ajax({
			url : "scenicAnnualController.do?nocheck&id=" + spotId,
			//data:{id:id},
			dataType : "json",
			success : function(data) {
				alert(data.msg);
				reloadTable();
			}
		});
	}

	function userListsearch() {
		$.dataGrid("#userList2").reload();
	}
 

	 
	 
</script>
