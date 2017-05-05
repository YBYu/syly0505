<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<div id="tempSearchColums" style="display: none">
	<div name="searchColums"></div></div>
<t:datagrid name="airportMonthlList"  actionUrl="airportMonthlyController.do?datagrid" 
queryMode="group" idField="id" fit="true" sortName="dates" sortOrder="desc">
	<t:dgCol title="ID" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="月报时间 " field="dates" formatter="yyyy-MM" query="true"></t:dgCol>
	<t:dgCol title="机场名称" field="otherTravelInfo.name"></t:dgCol> 
	<t:dgCol title="基本信息id" field="oid" hidden="true"></t:dgCol>
	<%-- <t:dgCol title="本月计划完成的运输起降架次" field="plansortie"></t:dgCol>
	<t:dgCol title="本月实际完成的运输起降架次" field="actualsortie"></t:dgCol>
	<t:dgCol title="全年计划完成的运输起降架次" field="plansortieyear"></t:dgCol>
	<t:dgCol title="本月计划出发运量-旅客（万人）" field="planouttraveller"></t:dgCol>
	<t:dgCol title="本月实际完成的出发货运-旅客（万人）" field="actualouttraveller"></t:dgCol>
	<t:dgCol title="全年计划完成的出发货运" field="planouttravelleryear"></t:dgCol>
	<t:dgCol title="本月计划完成的出发运量-货邮行" field="planoutwill"></t:dgCol>
	<t:dgCol title="本月实际完成的出发运量-货邮行" field="actualoutwill"></t:dgCol>
	<t:dgCol title="全年计划完成的出发货运-货邮行（吨）" field="planoutwillyear"></t:dgCol>
	<t:dgCol title="本月计划完成的到达运量-旅客（万人）" field="planIntegerraveller"></t:dgCol>
	<t:dgCol title="本月实际完成的到达运量-旅客（万人）" field="actualIntegerraveller"></t:dgCol>
	<t:dgCol title="全年计划完成的到达运量-旅客（万人）" field="planIntegerravelleryear"></t:dgCol>
	<t:dgCol title="本月计划完成的到达运量-货邮行（吨）" field="planinwill"></t:dgCol>
	<t:dgCol title="本月实际完成的到达运量-货邮行（吨）" field="actualinwill"></t:dgCol>
	<t:dgCol title="全年计划完成的到达运量-货邮行（吨）" field="planinwillyear"></t:dgCol>
	<t:dgCol title="本月计划完成的吞吐量-旅客（万人）" field="planthroughputtraveller"></t:dgCol>
	<t:dgCol title="本月实际完成的吞吐量-旅客（万人）" field="actualthroughputtraveller"></t:dgCol>
	<t:dgCol title="全年计划完成的吞吐量-旅客（万人）" field="planthroughputtravelleryear"></t:dgCol>
	<t:dgCol title="本月计划完成的吞吐量-货邮行（吨）" field="planthroughputwill"></t:dgCol>
	<t:dgCol title="本月实际完成的吞吐量-货邮行（吨）" field="actualthroughputwill"></t:dgCol>
	<t:dgCol title="全年计划完成的吞吐量-货邮行（吨）" field="planthroughputwillyear"></t:dgCol>
 --%>	
 	<t:dgCol title="审核状态" field="status"></t:dgCol> 
	<%-- <t:dgCol title="是否代填报" field="hisreport"></t:dgCol> --%>
	<%-- <t:dgCol title="填报人" field="reporter"></t:dgCol> --%>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="otherTravelMonthlyController.do?delbm&id={id}"/> --%>
	<t:dgFunOpt funname="detailam(id,oid)" title="查看详情" ></t:dgFunOpt>
</t:datagrid>

</div>
</div>
<script type="text/javascript">
//机场信息详情
function detailam(id,otid){
	createwindow('机场信息详情', 'airportMonthlyController.do?detailam&amonthId='+id+'&load=detail&otid='+otid);
}
function userListsearch(){
	$.dataGrid("#airportMonthlList").reload();
}

</script>