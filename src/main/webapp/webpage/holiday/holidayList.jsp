<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- 引入状态数据 -->
<script type="text/javascript" src="hotel_js/status.js">
</script>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<t:datagrid name="holidayList" title="" queryMode="group" actionUrl="holidayController.do?datagrid" idField="id" fit="true">
	<t:dgCol  title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="年份" field="year" query="true" width="5" replace="2000年_2000,2001年_2001,2002年_2002,2003年_2003,2004年_2004,2005年_2005,2006年_2006,2007年_2007,2008年_2008,2009年_2009,2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017,2018年_2018" ></t:dgCol>
	<t:dgCol title="节日类型" sortable="false"  query="true"  field="type" replace="元旦_0,清明_1,五一_2,端午_3,中秋_4,春节黄金周_5,十一黄金周_6" width="5"></t:dgCol>	
	<t:dgCol  title="美兰机场（万）" field="meilanAir"  width="6"></t:dgCol> 
	<t:dgCol title="凤凰机场（万）" field="fenghuangAir"   width="6"></t:dgCol>
	<t:dgCol title="海峡办（万人次）" field="straitOffice"  width="6"></t:dgCol> 
	<t:dgCol title="景区（万人次）" field="scenicNum"     width="6"></t:dgCol>
	<t:dgCol title="景区收入（万元）" field="scenicIncome"    width="6"></t:dgCol>
	<t:dgCol title="酒店（万人次）" field="hotelNum"     width="6"></t:dgCol>
	<t:dgCol title="酒店收入（万元）" field="hotelIncome"  width="6"></t:dgCol>
	<t:dgCol title="乡村旅游（万人次）" field="townTravelNum"     width="7"></t:dgCol>
	<t:dgCol title="乡村旅游收入（万元）" field="townTravelIncome"    width="7"></t:dgCol>
	<t:dgCol title="免税店（万人次）" field="taxFreeNum"     width="7"></t:dgCol>
	<t:dgCol title="免税店收入（万元）" field="taxFreeIncome"  width="7"></t:dgCol>
	<t:dgCol title="状态" field="sourceStatus" width="10" replace="未同步至省系统_0,已同步至省系统_1" ></t:dgCol>
	<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
	<t:dgFunOpt funname="detail(id)" title="查看详情" ></t:dgFunOpt>
 <t:dgToolBar title="节假日信息录入" langArg="common.user" icon="icon-edit" url="holidayController.do?addorupdate" funname="add"></t:dgToolBar> 
 <t:dgToolBar title="节假日信息导出" langArg="common.user" icon="icon-put"  onclick="doMigrateOut();"></t:dgToolBar>
</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
	
function detail(holidayId){
	createdetailwindow('节假日详情', 'holidayController.do?detail&load=detail&holidayId='+holidayId);
}
function back(data){
	if(data.success)
	reloadTable();
}
function doMigrateOut(){
	JeecgExcelExport("holidayController.do?exportsxls","holidayList");
	
}
	
	
 
		
</script>
