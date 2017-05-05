<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>  
<t:datagrid  name="ckmonthList" title="" queryMode="group" actionUrl="hotelMonthlyController.do?ckDatagrid"  idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="酒店id" field="hotelmanage.id" hidden="true"></t:dgCol>
	<t:dgCol title="酒店名称" field="hotelmanage.unitname" query="false" width="20"></t:dgCol>
			<t:dgCol title="酒店编码" field="hotelmanage.organizationNum" query="false" width="10"></t:dgCol>
			<t:dgCol title="酒店类型" field="hotelmanage.housegrade" query="false"
				replace="其他_0,舒适_1,高档_2,豪华_3,一星_4,二星_5,三星_6,四星_7,五星_8" width="10"></t:dgCol>
			<t:dgCol title="年份" field="year" width="5" query="true" replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
			<t:dgCol title="月份" field="month" width="5" query="true" replace="1月_1,2月_2,3月_3,4月_4,5月_5,6月_6,7月_7,8月_8,9月_9,10月_10,11月_11,12月_12"></t:dgCol>
			<t:dgCol title="营业收入" field="totalIncome" width="5"></t:dgCol>
			<t:dgCol title="上报日期" field="reportDate" width="5"></t:dgCol>
			<t:dgCol title="区级审核状态" field="districtStatus" width="10" query="false"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
			<t:dgCol title="市级审核状态" field="status" width="10" query="false"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
			<t:dgCol title="省级审核状态" field="provinceState" width="10" query="false"
			 replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
			<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
	<t:dgFunOpt funname="editMonth(hotelmanage.id,id)" title="编辑" exp="status#eq#3"></t:dgFunOpt>
	<t:dgFunOpt funname="detail(hotelmanage.id,id)" title="查看详情" ></t:dgFunOpt>
	<t:dgToolBar title="月报录入" langArg="common.user" icon="icon-add" url="hotelMonthlyController.do?addorupdate" funname="add"></t:dgToolBar>

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">

//时间下拉框赋值
$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_hotelmonthly&columnName=year",
		dataType:"json",
		success:function(data){
			var rs = data.split(",");
	
			var str = " <select><option value>---请选择---</option> " ;   
			for(var i = 0; i < rs.length; i++){
				str  =  str  +   " <option value='" + rs[i] + "'> " + rs[i] +"年</option> ";
			}
			str = str + " </select> " ; 
			
			$("select[name='year']")[0].innerHTML = str;
			
			}  	
	 	}
	);
});

function detail(id,monthId){
	createdetailwindow('酒店月报详情', 'hotelMonthlyController.do?detail&hotelmanageId='+id+'&load=detail&monthId='+monthId);
	//reloadTable();
}
	
function editMonth(id,monthId){
	createwindow('编辑酒店月报','hotelMonthlyController.do?editByUser&monthId='+monthId,700,500);
}
	
function doMigrateOut(){
		JeecgExcelExport("hotelMonthlyController.do?exportsxlss","userList2");
		
	}
function back(data){
	if(data.success)
	reloadTable();
}

</script>
