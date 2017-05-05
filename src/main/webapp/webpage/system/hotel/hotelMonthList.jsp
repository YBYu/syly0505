<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="userList2" title="" queryMode="group"
			actionUrl="hotelMonthlyController.do?datagrid" idField="id"
			fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="酒店月报id" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="月报id" field="hotelmanage.id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="year" width="5" query="true"
				replace="2000年_2000,2001年_2001,2002年_2002,2003年_2003,2004年_2004,2005年_2005,2006年_2006,2007年_2007,2008年_2008,2009年_2009,2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
			<t:dgCol title="月份" field="month" width="5" query="true"
				replace="1月_1,2月_2,3月_3,4月_4,5月_5,6月_6,7月_7,8月_8,9月_9,10月_10,11月_11,12月_12"></t:dgCol>
			<t:dgCol title="酒店名称" field="hotelmanage.unitname" query="true"
				width="15"></t:dgCol>
			<%-- <t:dgCol title="酒店编码" field="hotelmanage.organizationNum" query="false" width="10"></t:dgCol> --%>
			<t:dgCol title="酒店类型" field="hotelmanage.housegrade" query="true"
				queryMode="group"
				replace="其他_0,舒适_1,高档_2,豪华_3,一星_4,二星_5,三星_6,四星_7,五星_8" width="10"></t:dgCol>
			<t:dgCol title="所属辖区" field="hotelmanage.county" query="true"
				replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>
			<t:dgCol title="所属湾区" field="hotelmanage.bay" query="true"
				replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>
			<%-- <t:dgCol title="营业收入" field="totalIncome" width="5"></t:dgCol>
			<t:dgCol title="上报日期" field="reportDate" width="10"></t:dgCol> --%>
			<t:dgCol title="区级审核状态" field="districtStatus" width="10"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="市级审核状态" field="status" width="10" query="true"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
			<t:dgCol title="省级审核状态" field="provinceState" width="10" query="true"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"  ></t:dgCol>
			<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
			<%-- <t:dgDelOpt title="删除" url="hotelMonthlyController.do?del&id={id}"/>  --%>
			<t:dgFunOpt funname="detail(hotelmanage.id,id)" title="查看详情"></t:dgFunOpt>
			<t:dgToolBar title="导出酒店月报" funname="exportExcel"></t:dgToolBar>
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
	
			var str = " <select> " ;   
			for(var i = 0; i < rs.length; i++){
				str  =  str  +   " <option value='" + rs[i] + "'> " + rs[i] +"年</option> ";
			}
			str = str + " </select> " ; 
			
			$("select[name='year']")[0].innerHTML = str;
			
			var month='${month}';
			var year='${year}';
			$("select[name='month']").find('option:selected').remove();
			$("select[name='month']").val(month);
			$("select[name='year']").val(year);
			}  	
	 	}
	);
});


function detail(id,monthId){
	createdetailwindow('酒店月报详情', 'hotelMonthlyController.do?detail&hotelmanageId='+id+'&monthId='+monthId);
}
	
function doMigrateOut(){
		JeecgExcelExport("hotelMonthlyController.do?exportsxls","userList2");
		
	}
	
function exportExcel(){
	var queryParams = $("#userList2").datagrid('options').queryParams;
	var name = "";
	/* var type = ""; */
	var beginStar="";
	var endStar="";
	var country="";
	var bay="";
	var status="";
	var provinceState="";
	var year="";
	var month="";
	
	if(typeof queryParams["hotelmanage.unitname"] != "undefined"){
		name = queryParams["hotelmanage.unitname"];
		/* type = queryParams["hotelmanage.housegrade"]; */
		beginStar=queryParams["hotelmanage.housegrade_begin"];
		endStar=queryParams["hotelmanage.housegrade_end"];
		country=queryParams["hotelmanage.county"];
		bay=queryParams["hotelmanage.bay"];
		status=queryParams.status;
		provinceState=queryParams.provinceState;
		year=queryParams.year;
		month=queryParams.month;
	}
	
	window.open("hotelMonthlyController.do?exportMonthly&name="+name+"&beginStar="+beginStar+"&endStar="+endStar+"&country="+country+"&bay="+bay+"&status="+status+"&provinceState="+provinceState+"&year="+year+"&month="+month);
}
</script>
