<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="financeTable" title="" queryMode="group" actionUrl="travelAnnualFinanceController.do?datagridOfUser" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="编号" field="traveldata.id" hidden="true"></t:dgCol>	
	<t:dgCol title="年份" field="year" width="10" query="true" replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
	<t:dgCol title="单位名称" field="traveldata.name"  query="false" width="30"></t:dgCol>
	<t:dgCol title="许可证编号" field="traveldata.licensenum"  query="false"   width="15"></t:dgCol> 
	<t:dgCol title="填报时间" field="reportTime" width="10"></t:dgCol>
	<t:dgCol title="填报人" field="reportPerson"   width="10"></t:dgCol>
	<t:dgCol title="审核状态" field="status" query="true" width="10" sortable="true" replace="市级审核未通过_3,市级待审核_2,市级未填报_1,市级审核通过_4,省级待审核_5,省级审核未通过_6,省级审核通过_7"  ></t:dgCol>			
	<t:dgCol title="操作" field="opt" width="15"></t:dgCol>  
	<t:dgFunOpt funname="checkDetail(id)" title="查看详情" ></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(id)" title="审核意见"
				exp="status#eq#6"></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(id)" title="审核意见"
				exp="status#eq#7"></t:dgFunOpt>
	<t:dgToolBar title="新建" funname="add()"></t:dgToolBar>
	<t:dgToolBar title="编辑" funname="edit()"></t:dgToolBar>
</t:datagrid>
</div>
</div>

<script type="text/javascript">

//时间下拉框赋值
$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_travelagency_annual&columnName=year",
		dataType:"json",
		success:function(data){
			var rs = data.split(",");
	
			var str = " <select><option value>---请选择---<option> " ;   
			for(var i = 0; i < rs.length; i++){
				str  =  str  +   " <option value='" + rs[i] + "'> " + rs[i] +"年</option> ";
			}
			str = str + " </select> " ; 
			
			$("select[name='year']")[0].innerHTML = str;
			}  	
	 	}
	);
});

//新建
function add(){
	var button = [{  
        name: "提交",
        callback: function(){
        	iframe = this.iframe.contentWindow;
		saveObj();
		return false;
        },
        focus: true
    }];
createwindowbutton("新建财务年报","travelAnnualFinanceController.do?addOrEditFinanceReport",button,700,500);
}

// 编辑
function edit(){
	var selection = $("#financeTable").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var status = selection.status;
	if(status == "1" || status == "3" || status=="6"){
		
	}else{
		alert("该数据不允许编辑!");
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
createwindowbutton("编辑财务年报","travelAnnualFinanceController.do?addOrEditFinanceReport&id="+selection.id,button,700,500);
}

// 编辑
function checkDetail(id){
	createdetailwindow("财务年报详情","travelAnnualFinanceController.do?addOrEditFinanceReport&id="+id,700,500);
}


// 表格刷新方法
function reloadTable(){
	try{
		$("#financeTable").datagrid("reload");
	}catch(e){
		
	}
}

// 查看审核意见
function countryContent(id){
	createdetailwindow('国家系统审核意见','travelAnnualFinanceController.do?countryContent&id='+ id, 450, 160);
}
</script>
