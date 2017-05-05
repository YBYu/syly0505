<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="userList2" title="" queryMode="group" actionUrl="scenicQuarterController.do?datagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>  
	<t:dgCol title="编号" field="scenicData.id" hidden="true"></t:dgCol>  	
	<t:dgCol title="年份" field="year" query="true"    replace="2000年_2000,2001年_2001,2002年_2002,2003年_2003,2004年_2004,2005年_2005,2006年_2006,2007年_2007,2008年_2008,2009年_2009,2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
	<t:dgCol title="季度" field="quarter" query="true"  sortable="true"    replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4"></t:dgCol>	
	<t:dgCol title="景区名称" field="scenicData.name"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="所属辖区" field="scenicData.area"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5"></t:dgCol>		
	<t:dgCol title="景区类型" field="scenicData.scenictype" query="true" replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10"  ></t:dgCol>	
	<t:dgCol title="景区等级" field="scenicData.level" query="true" queryMode="group" sortable="true" replace="AAAAA_1,AAAA_2,AAA_3,AA_4,A_5"></t:dgCol>
	
	<%-- <t:dgCol title="营业收入(万元)" field="totalincome"   ></t:dgCol> 
<t:dgCol title="利润总额(万元)" field="totalprofit"   ></t:dgCol> 
	<t:dgCol title="接待人数(万人次)" field="receptionnum"  ></t:dgCol>      --%>
	<t:dgCol title="审核状态" field="status"   replace="未分配_1,已分配_2,未填报_3,提交待审_4,退回修订_5,区级审核通过_6,市级审核通过_7,省级审核通过_8,国家审核通过_9,省级待审核_10"></t:dgCol>	
	
<%-- 	<t:dgCol title="团散比" field="compare"   ></t:dgCol>  --%>      
	<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
<%-- 	<t:dgDelOpt title="删除" url="scenicWeekController.do?del&id={id}&name={name}"/> --%>
	<t:dgFunOpt funname="detail(id,scenicData.id)" title="查看详情"></t:dgFunOpt>
	
 <t:dgToolBar title="季报数据导出" langArg="common.user" icon="icon-put" url="" funname="daochu"></t:dgToolBar> 
<%--  	<t:dgToolBar title="同步数据" langArg="common.user" icon="icon-add" url="" funname="tongbu"></t:dgToolBar>
 --%><%-- <t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar> --%>

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
//加载上一周期的年份和季度
$(document).ready(function(){
	 $.ajax ({
			url:"calcYear.do?getYearList&tableName=t_scenicspot_quarterly&columnName=year",
			dataType:"json",
			success:function(data){
				$("select[name='year']").empty();
				 var itemYear=data.split(",");
				 for(var i=0;i<itemYear.length;i++){
						$("select[name='year']").append("<option value='"+itemYear[i]+"'>"+itemYear[i]+"年</option>");
				}				
				 var quarter='${quarter}';
					var year='${year}';
					//$("select[name='quarter']").find('option:selected').remove();
					$("select[name='quarter']").val(quarter);
					$("select[name='year']").val(year);
			}
			 });
	 
	
});
$(document).ready(function(){
	var quarter='${quarter}';
	var year='${year}';
	$("select[name='year']").find('option:selected').remove();
	$("select[name='quarter']").find('option:selected').remove();
	$("select[name='quarter']").val(quarter);
	$("select[name='year']").val(year);
});
//同步国家系统的季报数据
function tongbu(){
	
	window.open("scenicQuarterController.do?tongbu");
}
//导出基本信息年报
function daochu(){
	
	var queryParams = $("#userList2").datagrid('options').queryParams;
	var beginLevel = "";
	var endLevel="";
	var name = "";
	var year = "";
	var quarter = "";
	var area="";
	var scenicType="";
	if(typeof queryParams["scenicData.name"] != "undefined"){
		beginLevel = queryParams["scenicData.level_begin"];
		endLevel=queryParams["scenicData.level_end"];
		name = queryParams["scenicData.name"];
		year = queryParams.year;
		quarter = queryParams.quarter;
		area= queryParams["scenicData.area"];
		scenicType= queryParams["scenicData.scenictype"];
	}
	window.open("scenicQuarterController.do?excelExport&beginLevel="+beginLevel+"&endLevel="+endLevel+"&name="+name+"&year="+year+"&quarter="+quarter+"&area="+area+"&scenicType="+scenicType);
}
//景区季报基本信息详情
function detail(id,spotId){
		createdetailwindow('景区季报信息详情', 'scenicQuarterController.do?detial&quarterid='+id+'&load=detail&scenicdataid='+spotId);
	} 

function userListsearch(){
	$.dataGrid("#userList2").reload();
}
 
 
</script>
