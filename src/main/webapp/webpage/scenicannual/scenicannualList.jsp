<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="userList2" title="" queryMode="group" actionUrl="scenicAnnualController.do?datagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol> 
	<t:dgCol title="编号" field="scenicData.id" hidden="true"></t:dgCol>  
	 <t:dgCol title="年份" field="year" query="true"  queryMode="group" sortable="true"  replace="2000年_2000,2001年_2001,2002年_2002,2003年_2003,2004年_2004,2005年_2005,2006年_2006,2007年_2007,2008年_2008,2009年_2009,2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>	
	<t:dgCol title="景区名称" field="scenicData.name"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="所属辖区" field="scenicData.area"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5"></t:dgCol>		
	<t:dgCol title="景区类型" field="scenicData.scenictype" query="true" replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10"  ></t:dgCol>	
	<t:dgCol title="景区等级" field="scenicData.level" query="true" queryMode="group"  sortable="true" replace="未评定_6,AAAAA_1,AAAA_2,AAA_3,AA_4,A_5"></t:dgCol>
	
		<%-- <t:dgCol title="资产总额(万元)" field="assetstotal"   ></t:dgCol> 
	<t:dgCol title="营业收入(万元)" field="totalincome"   ></t:dgCol> 
	<t:dgCol title="接待人数(万人次)" field="receptionnum"   ></t:dgCol>   --%> 
		<t:dgCol title="审核状态" field="status"  replace="未分配_1,已分配_2,未填报_3,提交待审_4,退回修订_5,区级审核通过_6,市级审核通过_7,省级审核通过_8,国家审核通过_9,省级待审核_10"></t:dgCol>	
	  
	<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
	<t:dgFunOpt funname="detail(scenicData.id,id)" title="查看详情" ></t:dgFunOpt> 
	<t:dgToolBar title="年报数据导出" langArg="common.user" icon="icon-put" url="" funname="daochu" ></t:dgToolBar>
<%-- 	<t:dgToolBar title="同步数据" langArg="common.user" icon="icon-add" url="" funname="tongbu"></t:dgToolBar>
 --%>	

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
//加载上一年份
$(document).ready(function(){
	 $.ajax ({
			url:"calcYear.do?getYearList&tableName=t_scenicspot_annual&columnName=year",
			dataType:"json",
			success:function(data){
				$("select[name='year_begin']").empty();
				$("select[name='year_end']").empty();
				 var itemYear=data.split(",");
				 for(var i=0;i<itemYear.length;i++){
						$("select[name='year_begin']").append("<option value='"+itemYear[i]+"'>"+itemYear[i]+"年</option>");
						$("select[name='year_end']").append("<option value='"+itemYear[i]+"'>"+itemYear[i]+"年</option>");
				}
				 var year='${year}';
					$("select[name='year_begin']").val(year);
					$("select[name='year_end']").val(year);
			}
			 });
	 
	
});

//同步年报数据
function tongbu(){
	
	window.open("scenicAnnualController.do?tongbu");
}
//导出景区年报数据
function daochu(){
	
	var queryParams = $("#userList2").datagrid('options').queryParams;
	var beginLevel = "";
	var endLevel="";
	var name = "";
	var beginYear = "";
	var endYear="";
	var scenicType="";
	var area="";
	if(typeof queryParams["scenicData.name"] != "undefined"){
		beginLevel = queryParams["scenicData.level_begin"];
		endLevel=queryParams["scenicData.level_end"];
		name = queryParams["scenicData.name"];
		beginYear = queryParams.year_begin;
		endYear = queryParams.year_end;
		area= queryParams["scenicData.area"];
		scenicType= queryParams["scenicData.scenictype"];
	}
	window.open("scenicAnnualController.do?excelExport&beginLevel="+beginLevel+"&endLevel="+endLevel+"&name="+name+"&beginYear="+beginYear+"&endYear="+endYear+"&area="+area+"&scenicType="+scenicType);
}
     
//查看年报信息详情
function detail(spotId,id){
		createdetailwindow('景区年报信息详情', 'scenicAnnualController.do?detial&yearid='+id+'&load=detail&scenicdataid='+spotId);
	} 

</script>
