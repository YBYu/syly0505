<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="userList2" title="" queryMode="group" actionUrl="scenicController.do?datagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="景区名称" field="name"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="景区编码" sortable="false"  query="true"  field="code" width="15"></t:dgCol>
	<t:dgCol title="所属辖区" field="area"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5"></t:dgCol>
	<t:dgCol title="所属湾区" field="bayType"  query="true" replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4" ></t:dgCol>	
	<t:dgCol title="景区等级" field="level" query="true"  queryMode="group" sortable="true" replace="未评定_6,AAAAA_1,AAAA_2,AAA_3,AA_4,A_5"></t:dgCol>
	<t:dgCol title="景区类型" sortable="false" field="scenictype" replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10"  width="15"></t:dgCol>
	<t:dgCol field="status" title="审核状态" query="true"  replace="未分配_1,已分配_2,未填报_3,提交待审_4,退回修订_5,区级审核通过_6,市级审核通过_7,省级审核通过_8,国家审核通过_9,省级待审核_10"></t:dgCol>
	<%-- <t:dgCol title="创建日期" field="createDate" formatter="yyyy-MM-dd" query="true" queryMode="group"></t:dgCol> --%>
	<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="scenicController.do?del&id={id}&name={name}"/> --%>
	<t:dgFunOpt funname="update(id)"  title="代填报"  exp="status#eq#3"></t:dgFunOpt>
	<t:dgFunOpt funname="update(id)"  title="代填报"  exp="status#eq#5"></t:dgFunOpt>
	<t:dgFunOpt funname="edit(id)" title="编辑" exp="status#eq#7"></t:dgFunOpt>
	<t:dgFunOpt funname="back(id)" title="撤回审核" exp="status#eq#10"></t:dgFunOpt>
	<t:dgFunOpt funname="checkwin(id,status)" title="审核" exp="status#eq#4" ></t:dgFunOpt>
	<t:dgFunOpt funname="checkwin(id,status)" title="审核" exp="status#eq#6" ></t:dgFunOpt>
	    	<t:dgToolBar title="添加景区" langArg="common.user" icon="icon-add" url="scenicController.do?add" funname="add"></t:dgToolBar>
<%-- 	<t:dgToolBar title="添加景区" langArg="common.user" icon="icon-add" url="scenicController.do?add" funname="add"></t:dgToolBar>
 --%><%-- 	<t:dgToolBar title="同步数据" langArg="common.user" icon="icon-add" url="" funname="tongbu"></t:dgToolBar> --%>
	
<%-- <t:dgToolBar title="景区详情" langArg="common.user" icon="icon-edit" url="scenicController.do?addorupdate" funname="update"></t:dgToolBar>
--%>
    <t:dgFunOpt funname="detail(id)" title="查看详情" ></t:dgFunOpt>
       <t:dgFunOpt funname="comments(id)" title="查看审核意见" exp="status#eq#5"></t:dgFunOpt>
    <t:dgFunOpt funname="comments(id)" title="查看审核意见" exp="status#eq#8"></t:dgFunOpt>

<%-- <t:dgToolBar title="基本信息导出" langArg="common.user" icon="icon-put" funname="daochu"></t:dgToolBar> --%>

<%-- 
<t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar>
--%>
</t:datagrid>
  

<script type="text/javascript">
//景区年报编辑
function edit(id){
	 createwindow('景区年报编辑', 'scenicController.do?scenicmodify&scenicid='+id,960,800); 
}
//审核撤回
function back(id){
	 $.ajax ({
			url:"scenicController.do?back&scenicid="+id,
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
				}  	
		 	});
}
 //导出年报
function daochu(){
	var queryParams = $("#userList2").datagrid('options').queryParams;
	var name = "";
	var code = "";
	var status = "";
	var area="";
	var bayType="";
	var level_begin="";
	var level_end="";
	
	if(typeof queryParams.name!= "undefined"){
		name = queryParams.name;
		code = queryParams.code;
		status = queryParams.status;
		area=queryParams.area;
		bayType=queryParams.bayType;
		level_begin=queryParams.level_begin;
		level_end=queryParams.level_end;
	}
	window.open("scenicController.do?excelExport&name="+name+"&code="+code+"&status="+status+"&area="+area+"&bayType="+bayType+"&level_begin="+level_begin+"&level_end="+level_end);
}
 //同步国家系统的数据
function tongbu(){
	
	window.open("scenicController.do?tongbu");
}


//景区年报代填报
 function update(id){
	 $.ajax ({
			url:"scenicController.do?iffirst&scenicid="+id,
			//data:{id:id},
			dataType:"json",
			success:function(data){
				if(data=="notfirst"){
					alert("该景区今年的年报已经填写");
				}else{
					createwindow('景区年报填写', 'scenicController.do?scenicmodify&scenicid='+id,960,800); 
				}
			}	
			 });
	 
		

	}    
//景区年报审核意见
	function comments(id){
		createdetailwindow('景区年报审核意见详情', 'scenicController.do?comments&scenicid='+id,600,280);
	} 
//景区详情
function detail(id){
		createdetailwindow('景区详情', 'scenicController.do?detialinfosta&scenicid='+id);  
	} 
	//表格刷新
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
	//年报审核
function checkwin(id,status){
	var roleName='${roleName}';
	if(roleName=="区级管理员"&status=="6"){//如果区级管理员易经审核无法在审核
		alert("您已经审核过了");
		return false;
	}
	var button = [{  
	            name: "通过",
	            callback: function(){
	            	$.ajax ({
						url:"scenicController.do?addstatus&id="+id,
						//data:{id:id},
						dataType:"json",
						success:function(data){
							tip(data.msg);
							reloadTable();
						}	
						 }
						);
	            	
	            },
	            focus: true
	        },{
	            name: "退回",
	            callback: function(){
	            	nocheck(id);
	            	reloadTable();
	            },
	            focus: true
	        }];
		createwindowbutton('审核','scenicController.do?audit&id='+id,button);
};
//年报退回
function nocheck(id){
 $.ajax ({
		url:"scenicController.do?nocheck&id="+id,
		//data:{id:id},
		dataType:"json",
		success:function(data){
			tip(data.msg);
			reloadTable();
		}	
		 }
		);
}
</script>
