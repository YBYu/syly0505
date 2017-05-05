<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="annualStatistics" title="" queryMode="group"
			actionUrl="quarterStatisticsController.do?datagrid" idField="id"
			fit="true">
	

			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			
			  <t:dgCol title="景区名称" field="name"    ></t:dgCol>
			<t:dgCol title="年份" 	field="year" sortable="true" hidden="true" query="true" queryMode="group" replace="2000年_2000,2001年_2001,2002年_2002,2003年_2003,2004年_2004,2005年_2005,2006年_2006,2007年_2007,2008年_2008,2009年_2009,2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
			<t:dgCol title="季度" field="quarter" sortable="true" hidden="true" query="true" replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4"></t:dgCol>
			<t:dgCol title="所属辖区" field="area" hidden="true" query="true"   replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5"></t:dgCol>
			<t:dgCol title="景区等级" field="level" hidden="true" query="true" sortable="true"
				replace="其他_0,A_1,AA_2,AAA_3,AAAA_4,AAAAA_5,未评定_6"></t:dgCol>
			<t:dgCol title="景区类型" field="scenictype" replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10,null_未填写"></t:dgCol>
		  	<t:dgCol title="机构性质" field="orgproperty" replace="行政单位_2,事业单位_4,国有_7,集体_8,股份合作_9,国有联营_10,集体联营_11,国有与集体联营_12,其他联营_13,国有独资公司_14,其他有限责任公司_15,股份有限公司_16,私营独资_17,私营合伙_18,私营有限责任公司_19,私营股份有限公司_20,与港澳台商合资经营_23,与港澳台商合作经营_24,港澳台商独资_25,港澳台商投资股份有限公司_26,中外合资经营_28,中外合作经营_29,外资企业_30,外商投资股份有限公司_31,部队_33,其他_35,国有内资其他_21,未填写_0"></t:dgCol>
				<t:dgCol title="计算数据(条)" field="num" sortable="true"></t:dgCol>
			<t:dgCol title="营业收入(单位:万元) " field="totalIncome" sortable="true"></t:dgCol>
			<t:dgCol title="门票收入(单位:万元)" field="ticketIncome" sortable="true"></t:dgCol>
			<t:dgCol title="接待游客量(单位:万人)" field="receptionNum" sortable="true"></t:dgCol>
			<t:dgCol title="免票游客量(单位:万人)" field="exemptTicketNum" sortable="true"></t:dgCol>


			<t:dgCol title="按信息分组" field="dataproperty" hidden="true"
				query="true" sortable="true"
				replace="景区名称_name,所属区县_area,景区类型_scenictype,机构性质_orgproperty,景区等级_level,年份_year,季度_quarter"></t:dgCol>
			<t:dgCol title="统计" field="count" hidden="true" query="true"
				sortable="true" replace="总和_a,平均值_b,最大值_c,最小值_d"></t:dgCol>
	 

		</t:datagrid>



	</div>
</div>

<script type="text/javascript">

$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_scenicspot_quarterly&columnName=year",
		dataType:"json",
		success:function(data){
			$("select[name='year_begin']").empty();
			$("select[name='year_end']").empty();
			 var itemYear=data.split(",");
			 $("select[name='year_begin']").append("<option  value=''>请选择</option>");
			 $("select[name='year_end']").append("<option value=''>请选择</option>");
			 for(var i=0;i<itemYear.length;i++){
					$("select[name='year_begin']").append("<option value='"+itemYear[i]+"'>"+itemYear[i]+"年</option>");
					$("select[name='year_end']").append("<option value='"+itemYear[i]+"'>"+itemYear[i]+"年</option>");
			}
			 var year='${year}';
		}
		 });
	var dataproperty='${dataproperty}';
	var count='${count}';
	$("select[name='count']").find('option:selected').remove();
	$("select[name='dataproperty']").find('option:selected').remove();
	$("select[name='dataproperty']").val(dataproperty);
	$("select[name='count']").val(count);
});
$("select[name='dataproperty']").change(function(){
	var dataproperty=$(this).val();
	if(dataproperty=='area'){
		 $('#annualStatistics').datagrid('showColumn', 'area');
		 $('#annualStatistics').datagrid('hideColumn', 'name');
		 $('#annualStatistics').datagrid('hideColumn', 'scenictype');
		 $('#annualStatistics').datagrid('hideColumn', 'orgproperty');
		 $('#annualStatistics').datagrid('hideColumn', 'level'); 
		 $('#annualStatistics').datagrid('hideColumn', 'year');
		 $('#annualStatistics').datagrid('hideColumn', 'quarter');
	 }if(dataproperty=='level'){
		 $('#annualStatistics').datagrid('showColumn', 'level');
		 $('#annualStatistics').datagrid('hideColumn', 'name');
		 $('#annualStatistics').datagrid('hideColumn', 'scenictype');
		 $('#annualStatistics').datagrid('hideColumn', 'orgproperty');
		 $('#annualStatistics').datagrid('hideColumn', 'area'); 
		 $('#annualStatistics').datagrid('hideColumn', 'year');
		 $('#annualStatistics').datagrid('hideColumn', 'quarter');
	 }if(dataproperty=='name'){
		 $('#annualStatistics').datagrid('showColumn', 'name');
		 $('#annualStatistics').datagrid('hideColumn', 'level');
		 $('#annualStatistics').datagrid('hideColumn', 'scenictype');
		 $('#annualStatistics').datagrid('hideColumn', 'orgproperty');
		 $('#annualStatistics').datagrid('hideColumn', 'area'); 
		 $('#annualStatistics').datagrid('hideColumn', 'year');
		 $('#annualStatistics').datagrid('hideColumn', 'quarter');
	 }
	 if(dataproperty=='scenictype'){
		 $('#annualStatistics').datagrid('showColumn', 'scenictype');
		 $('#annualStatistics').datagrid('hideColumn', 'name');
		 $('#annualStatistics').datagrid('hideColumn', 'area');
		 $('#annualStatistics').datagrid('hideColumn', 'orgproperty');
		 $('#annualStatistics').datagrid('hideColumn', 'level'); 
		 $('#annualStatistics').datagrid('hideColumn', 'year');
		 $('#annualStatistics').datagrid('hideColumn', 'quarter');
	 }if(dataproperty=='orgproperty'){
		 $('#annualStatistics').datagrid('showColumn', 'orgproperty');
		 $('#annualStatistics').datagrid('hideColumn', 'name');
		 $('#annualStatistics').datagrid('hideColumn', 'scenictype');
		 $('#annualStatistics').datagrid('hideColumn', 'level');
		 $('#annualStatistics').datagrid('hideColumn', 'area'); 
		 $('#annualStatistics').datagrid('hideColumn', 'year');
		 $('#annualStatistics').datagrid('hideColumn', 'quarter');
	 }if(dataproperty=='year'){
		 $('#annualStatistics').datagrid('showColumn', 'year');
		 $('#annualStatistics').datagrid('hideColumn', 'level');
		 $('#annualStatistics').datagrid('hideColumn', 'scenictype');
		 $('#annualStatistics').datagrid('hideColumn', 'orgproperty');
		 $('#annualStatistics').datagrid('hideColumn', 'area'); 
		 $('#annualStatistics').datagrid('hideColumn', 'name');
		 $('#annualStatistics').datagrid('hideColumn', 'quarter');
	 }if(dataproperty=='quarter'){
		 $('#annualStatistics').datagrid('showColumn', 'quarter');
		 $('#annualStatistics').datagrid('hideColumn', 'level');
		 $('#annualStatistics').datagrid('hideColumn', 'scenictype');
		 $('#annualStatistics').datagrid('hideColumn', 'orgproperty');
		 $('#annualStatistics').datagrid('hideColumn', 'area'); 
		 $('#annualStatistics').datagrid('hideColumn', 'name');
		 $('#annualStatistics').datagrid('hideColumn', 'year');
	 }
	});
</script>
