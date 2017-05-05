<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid  name="annualStatistics"  title="" queryMode="group" actionUrl="sAnnualStatisticsController.do?datagrid" idField="id" fit="true" >
	<t:dgCol title="年报id" field="taId" hidden="true"></t:dgCol> 
	<t:dgCol title="景区id" field="tfId" hidden="true"></t:dgCol>	 
	<t:dgCol title="年份" field="year" query="true" sortable="true" ></t:dgCol>
	<t:dgCol title="景区名称" field="name"  query="true"> </t:dgCol>
	<t:dgCol title="所属辖区" field="area"  query="true"  replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>		
	<t:dgCol title="景区等级" field="level"  query="true" sortable="true" replace="其他_0,A_1,AA_2,AAA_3,AAAA_4,AAAAA_5"></t:dgCol>
	<t:dgCol title="机构类型" field="org_property" query="true" sortable="true" ></t:dgCol>
	<t:dgCol title="景区类型" field="scenic_type" query="true" sortable="true" ></t:dgCol>
	<t:dgCol title="经营总收入" field="total_income"  sortable="true" ></t:dgCol>
	<t:dgCol title="门票收入" field="ticket_income"  sortable="true" ></t:dgCol>
	<t:dgCol title="接待游客量" field="reception_num"  sortable="true" ></t:dgCol>
	<t:dgCol title="免票游客" field="exemptTicket_num"  sortable="true" ></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<%--  <t:dgToolBar title="查看同比" langArg="common.user" icon="icon-edit" url="sAnnualStatisticsController.do?totongbi" funname="tongbi"></t:dgToolBar> --%>
	<%-- <t:dgCol title="团散比" field="compare"   ></t:dgCol> --%>        
	<t:dgFunOpt funname="tongbi(taId,tfId)" title="查看同比" ></t:dgFunOpt>
	<%-- <t:dgToolBar title="景区数据导出" langArg="common.user" icon="icon-put" funname="daochu"></t:dgToolBar> --%>
	

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
function tongbi(taId,tfId){
	createwindow("景区年报同比展示",'+sAnnualStatisticsController.do?tongbi&taId='+taId+'&tfId='+tfId);
}     
/* 
function detail(spotId,id){
		createdetailwindow('景区详情', 'scenicAnnualController.do?detial&yearid='+id+'&load=detail&scenicdataid='+spotId);
	}  */
	
	//-----------------------------仅供参考--------------------------------
	

</script>
