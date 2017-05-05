<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="inlandGrid" title="" queryMode="group" actionUrl="travelQuarterController.do?inlandGridOfUser" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="编号" field="traveldata.id" hidden="true"></t:dgCol>    
	<t:dgCol title="许可证编号" field="traveldata.licensenum"  query="false"   width="15"></t:dgCol>
	<t:dgCol title="单位名称" field="traveldata.name"  query="false" width="20"></t:dgCol>		
	<t:dgCol title="年份" field="year" query="true"  sortable="ture" width="10" replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
	<t:dgCol title="季度" field="quarter" query="true"  sortable="ture" replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4" width="10"></t:dgCol>	
	<t:dgCol title="填报人" field="reportPerson" query="false" width="10"></t:dgCol>
	<t:dgCol title="填报时间" field="reportDate" query="false" width="5"></t:dgCol>
	<t:dgCol title="审核状态" field="status" query="true"  sortable="ture" width="10" replace="<font color=\"red\">市级未填报</font>_1,<font color=\"#d2980c\">市级未审核</font>_2,市级已审核(审核不通过)_3,市级已审核(审核通过)_4,国家系统待审核_5,国家系统未通过_6,国家系统已通过_7" ></t:dgCol>	
	<%-- <t:dgCol title="省系统审核状态" field="traveldata.quarterStatus" query="false"  sortable="ture" width="10" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol> --%>
	<t:dgCol title="操作" field="opt" width="15"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="travelQuarterController.do?deleteInlandReport&id={id}" exp="status#ne#4"/> --%>
	<t:dgFunOpt funname="checkDetail(id)" title="查看详情" ></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(id)" title="审核意见"
				exp="status#eq#6"></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(id)" title="审核意见"
				exp="status#eq#7"></t:dgFunOpt>
<t:dgToolBar title="新建" funname="add()"></t:dgToolBar>
<t:dgToolBar title="编辑" funname="edit()"></t:dgToolBar>
<%-- <t:dgToolBar title="季报导出" langArg="common.user" icon="icon-put" url="excelController.do?toexcel" funname="doMigrateOut"></t:dgToolBar> --%>
<%-- <t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar> --%>
</t:datagrid>
</div>
</div>

<script type="text/javascript">

//时间下拉框赋值
$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_travelagency_quarterly3&columnName=year",
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
        name: "确定",
        callback: function(){
        	iframe = this.iframe.contentWindow;
			saveObj();
			reloadTable();
			return false;
        },
        focus: true
    }];
createwindowbutton("新建国内报表","travelQuarterController.do?addOrEditInlandReport",button,850,500);
}

// 编辑
function edit(){
	var selection = $("#inlandGrid").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	if(selection.status == 4){
		alert("审核通过的数据不允许编辑!");
		return;
	}
	var button = [{  
        name: "确定",
        callback: function(){
        	iframe = this.iframe.contentWindow;
			saveObj();
			reloadTable();
			return false;
        },
        focus: true
    }];
createwindowbutton("编辑国内报表","travelQuarterController.do?addOrEditInlandReport&id="+selection.id,button,850,500);
}

// 查看详情
function checkDetail(id){
	createdetailwindow("国内报表详情","travelQuarterController.do?addOrEditInlandReport&id="+id,850,500);
}


// 表格刷新方法
function reloadTable(){
	try{
		$("#inlandGrid").datagrid("reload");
	}catch(e){
		
	}
}

//查看审核意见
function countryContent(id){
	createdetailwindow('国家系统审核意见','travelQuarterController.do?countryContent&id='+ id+"&type=3", 450, 160);
}
</script>
