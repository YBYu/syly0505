<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="userList2" title="" queryMode="group" actionUrl="scenicMonthController.do?monthdatagrid" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="编号" field="spotMonth.id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="spotMonth.year"></t:dgCol>
			<t:dgCol title="月份" field="spotMonth.month"></t:dgCol>
			<t:dgCol title="景区名称" field="name" query="true" width="20"></t:dgCol>
			<t:dgCol title="所属辖区" field="area" query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5"></t:dgCol>
			<t:dgCol title="景区类型" field="scenictype" query="true"  replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10"  ></t:dgCol>
			<%-- <t:dgCol title="报表时间" field="spotMonth.time" formatter="yyyy-MM" width="20"></t:dgCol> --%>
			<t:dgCol title="景区等级" field="level" query="true"   queryMode="group" replace="未评定_6,AAAAA_1,AAAA_2,AAA_3,AA_4,A_5"></t:dgCol>
			
			<%-- 
			<t:dgCol title="营业收入(万元)" field="spotMonth.taking" sortable="true"></t:dgCol>
			<t:dgCol title="营业收入" field="spotMonth.id" hidden="true"></t:dgCol>
			<t:dgCol title="利润总额(万元)" field="spotMonth.totalprofit" sortable="true"></t:dgCol>
			<t:dgCol title="接待人数(人次)" field="spotMonth.receptionnum" sortable="true"></t:dgCol>
			<t:dgCol title="团散比" field="spotMonth.compare" sortable="true"></t:dgCol> --%>
			<t:dgCol field="spotMonth.reportdate" title="填报时间" formatter="yyyy-MM-dd" query="true" queryMode="group"></t:dgCol> 
			<t:dgCol field="spotMonth.preparer" title="填报人" ></t:dgCol> 
			<t:dgCol title="审核状态" field="spotMonth.status" query="true"  replace="未分配_1,已分配_2,未填报_3,提交待审_4,退回修订_5,区级审核通过_6,市级审核通过_7" ></t:dgCol>
			<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
			<%-- 	     
	<t:dgDelOpt title="删除" url="scenicWeekController.do?del&id={id}&name={name}"/>
--%>
			<t:dgFunOpt funname="detail(id,spotMonth.id)" title="查看详情"></t:dgFunOpt>
			<t:dgFunOpt funname="checkwin(id,spotMonth.id,spotMonth.status)" title="审核" exp="spotMonth.status#eq#4"></t:dgFunOpt>
			<t:dgFunOpt funname="checkwin(id,spotMonth.id,spotMonth.status)" title="审核" exp="spotMonth.status#eq#6"></t:dgFunOpt>
			<t:dgFunOpt funname="daitianbao(id,spotMonth.id)" title="代填报" exp="spotMonth.status#eq#3"></t:dgFunOpt>
			<%--<t:dgFunOpt funname="update" title="未审核" exp="spotMonth.status#eq#2"></t:dgFunOpt> 
	 <t:dgFunOpt funname="nocheck(spotMonth.id)" title="退回" exp="spotMonth.status#eq#3"></t:dgFunOpt> 	
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
//加载日期格式插件
$(document).ready(function(){
    $("input[name='spotMonth.reportdate_begin']").attr("class","Wdate").attr("style","height:30px;width:138.6px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
    $("input[name='spotMonth.reportdate_end']").attr("class","Wdate").attr("style","height:30px;width:138.6px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
});
//表格刷新
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
//景区月报详情
  function detail(id,spotId){
		createdetailwindow('月报详情', 'scenicMonthController.do?detial&monthid='+spotId+'&load=detail&scenicdataid='+id);
	} 

var iframe;
//景区月报代填报
function daitianbao(scenicdataid,spotId) {

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
						url:"scenicMonthController.do?zancuncheck&scenicdataid="+scenicdataid+"&monthid="+spotId,
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
		createwindowbutton('代填报','scenicMonthController.do?addsign&monthid='+spotId+'&scenicdataid='+scenicdataid,button);
	
	}    
	
 
 //景区月报审核
	
	function checkwin(id,spotId,status){
		var roleName='${roleName}';
		if(roleName=="区级管理员"&status=="6"){
			alert("您已经审核过了");
			return false;
		}
	var button = [{  
	            name: "通过",
	            callback: function(){
	            	$.ajax ({
						url:"scenicMonthController.do?addstatus&id="+spotId,
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
	            	nocheck(spotId);
	            	
	            },
	            focus: true
	        }];
		createwindowbutton('月报审核','scenicMonthController.do?audit&monthid='+spotId+'&load=detail&scenicdataid='+id,button);
		 
		}
 //审核退回
		function nocheck(spotId){
		
			$.ajax ({
			url:"scenicMonthController.do?nocheck&id="+spotId,
			//data:{id:id},
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
			}	
			 }
			);
		}
		
		//景区月报暂存
		function zancuncheck(scenicdataid,spotId){
		var formjson = $('#formobj', iframe).serialize();
			$.ajax ({
			url:"scenicMonthController.do?zancuncheck&id="+scenicdataid,
			data:formjson,
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
			}  	
			 }
			);
		}
		
 
</script>
