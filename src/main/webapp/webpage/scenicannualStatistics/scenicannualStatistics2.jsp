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
	 
	<t:dgCol title="景区名称" field="scenicData.name"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="所属辖区" field="scenicData.area"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>		
	<t:dgCol title="景区类型" field="scenicData.scenictype" query="true" replace="全部_0,自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10" width="20"></t:dgCol>	
	<t:dgCol title="景区等级" field="scenicData.level" query="true"  sortable="true" replace="其他_0,A_1,AA_2,AAA_3,AAAA_4,AAAAA_5"></t:dgCol>
	<t:dgCol title="年份" field="year" query="true"  sortable="true"  ></t:dgCol>	
	<t:dgCol title="营业收入(万元)" field="totalincome"   ></t:dgCol> 
	<%-- <t:dgCol title="利润总额(万元)" field="totalprofit"   ></t:dgCol>  --%>
	<t:dgCol title="接待人数(万人次)" field="receptionnum"   ></t:dgCol>  
	<t:dgCol title="门票收入" field="ticketincome"  sortable="true" ></t:dgCol>
	<t:dgCol title="免票游客" field="exemptTicketnum"  sortable="true" ></t:dgCol>   
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<t:dgFunOpt funname="tongbi(id,scenicData.id)" title="查看同比" ></t:dgFunOpt>
	<%-- <t:dgFunOpt funname="detail(scenicData.id,id)" title="查看详情" ></t:dgFunOpt>  --%>
	<%-- <t:dgToolBar title="年报数据导出" langArg="common.user" icon="icon-put" url="" funname="daochu" ></t:dgToolBar> --%>
	

</t:datagrid>
  


</div>
</div>

<script type="text/javascript">

/* function daochu(){
	
	window.open("excelController.do?excelannual");
} */
function tongbi(taId,tfId){
	createwindow("景区年报同比展示",'sAnnualStatisticsController.do?tongbi&taId='+taId+'&tfId='+tfId);
}      

function detail(spotId,id){
		createdetailwindow('景区详情', 'scenicAnnualController.do?detial&yearid='+id+'&load=detail&scenicdataid='+spotId);
	} 

</script>
