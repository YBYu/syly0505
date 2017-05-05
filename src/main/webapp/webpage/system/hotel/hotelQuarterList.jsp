<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="userList2" title="" queryMode="group"
			actionUrl="hotelQuarterController.do?datagrid" idField="id"
			fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="year" query="true" sortable="ture"
				width="5"
				replace="2000年_2000,2001年_2001,2002年_2002,2003年_2003,2004年_2004,2005年_2005,2006年_2006,2007年_2007,2008年_2008,2009年_2009,2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
			<t:dgCol title="季度" field="quarter" query="true"
				replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4" width="10"></t:dgCol>
			<t:dgCol title="酒店名称" field="hotelmanage.unitname" query="true"
				width="15"></t:dgCol>
			<t:dgCol title="酒店星级" field="hotelmanage.housegrade" width="5"
				query="true" queryMode="group" sortable="ture"
				replace="一星_4,二星_5,三星_6,四星_7,五星_8"></t:dgCol>
			<t:dgCol title="所属辖区" field="hotelmanage.county" query="true"
				replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>
			<t:dgCol title="所属湾区" field="hotelmanage.bay" query="true"
				replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>
			<%-- <t:dgCol title="上报日期" field="writerDate"  width="5" ></t:dgCol> --%>
			<%-- <t:dgCol title="营业收入" field="totalIncome"   width="5"></t:dgCol>  --%>
			<t:dgCol title="id" hidden="true" field="hotelmanage.id"></t:dgCol>
			<%-- <t:dgCol title="餐厅总额" field="canteenIncome"  width="5" ></t:dgCol> 
	<t:dgCol title="住宿收入" field="roomIncome"   width="5"></t:dgCol>     
	<t:dgCol title="其他收入" field="otherIncome"   width="5"></t:dgCol>      --%>
			<t:dgCol title="区级审核状态" field="districtStatus" width="10"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="市级审核状态" field="status" query="true" width="10"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"  ></t:dgCol>
			<t:dgCol title="国家级审核状态" field="countryState" width="10" query="true"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"   ></t:dgCol>
			<t:dgCol title="操作" field="opt" width="15"></t:dgCol>
			<%-- <t:dgDelOpt title="删除" url="hotelQuarterController.do?del&id={id}&name={name}"/> --%>
			<t:dgFunOpt funname="detail(hotelmanage.id,id)" title="查看详情"></t:dgFunOpt>
			<%-- <t:dgToolBar title="查看周报详情" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> --%>
			<%-- <t:dgToolBar title="酒店季报导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar>
 --%>
			<%-- <t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar> --%>
			<t:dgToolBar title="导出酒店季报" funname="exportExcel"></t:dgToolBar>
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
			
			var quarter='${quarter}';
			var year='${year}';
			$("select[name='quarter']").find('option:selected').remove();
			$("select[name='quarter']").val(quarter);
			$("select[name='year']").val(year);
			}  	
	 	}
	);
});


	function detail(id,quarterId){
		createdetailwindow('酒店季报详情', 'hotelQuarterController.do?detail&hotelmanageId='+id+'&load=detail&quarterId='+quarterId);
	}
	
		function doMigrateOut(){
		JeecgExcelExport('hotelQuarterController.do?exportsxls', "userList2")
		
	}

		function exportExcel(){
			var queryParams = $("#userList2").datagrid('options').queryParams;
			var name = "";
			var year = "";
			var quarter = "";
			var beginStar="";
			var endStar="";
			var country="";
			var bay="";
			var status="";
			var countryState="";
			if(typeof queryParams["hotelmanage.unitname"] != "undefined"){
				name = queryParams["hotelmanage.unitname"];
				year = queryParams.year;
				quarter = queryParams.quarter;
				beginStar=queryParams["hotelmanage.housegrade_begin"];
				endStar=queryParams["hotelmanage.housegrade_end"];
				country=queryParams["hotelmanage.county"];
				bay=queryParams["hotelmanage.bay"];
				status=queryParams.status;
				countryState=queryParams.countryState;
			}
			
			window.open("hotelQuarterController.do?exportQuarter&name="+name+"&year="+year+"&quarter="+quarter+"&beginStar="+beginStar+"&endStar="+endStar+"&country="+country+"&bay="+bay+"&status="+status+"&countryState="+countryState);
		}
		
</script>
