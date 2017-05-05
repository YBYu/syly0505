<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- 引入状态数据 -->
<script type="text/javascript" src="hotel_js/status.js">
</script>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="userList2" title="节假日数据列表" queryMode="group"
			actionUrl="holidayController.do?datagrid" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="year" query="true" width="20"></t:dgCol>
			<t:dgCol title="节日类型" sortable="false" query="true" field="type"
				replace="元旦_0,清明_1,五一2,端午3,中秋4,春节黄金周5,十一黄金周6" width="20"></t:dgCol>
			<%-- <t:dgCol  title="美兰机场（万）" field="meilanAir"  ></t:dgCol> 
	<t:dgCol title="凤凰机场（万）" field="fenghuangAir"   ></t:dgCol>
	<t:dgCol title="海峡办（万人次）" field="straitOffice" query="true" ></t:dgCol> 
	<t:dgCol title="景区（万人次）" field="scenicNum"     width="20"></t:dgCol>
	<t:dgCol title="景区收入（万元）" field="scenicIncome"    width="20"></t:dgCol>
	<t:dgCol title="酒店（万人次）" field="hotelNum"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="酒店收入（万元）" field="hotelIncome"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="乡村旅游（万人次）" field="townTravelNum"  query="true"   width="20"></t:dgCol>
 --%>
			<t:dgCol title="乡村旅游收入（万元）" field="townTravelIncome" query="true"
				width="20"></t:dgCol>
			<t:dgCol title="免税店（万人次）" field="taxFreeNum" width="20"></t:dgCol>
			<t:dgCol title="免税店收入（万元）" field="taxFreeIncome" width="20"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
			<%-- <t:dgCol title="酒店类型" sortable="false" query="true" field="scenic_type" width="20"></t:dgCol> --%>

			<%-- <t:dgFunOpt funname="szqm(id)" title="设置签名" /> --%>
			<%-- <t:dgFunOpt funname="show(id)" title="详情信息" /> --%>
			<t:dgDelOpt title="删除" url="holidayController.do?del&id={id}" />
			<%-- <t:dgFunOpt funname="check(id)" exp="status#eq#0" title="审核"  />  --%>
			<%-- <t:dgToolBar title="添加" langArg="common.user" icon="icon-add" url="hotelController.do?add" funname="add"></t:dgToolBar> --%>
			<t:dgToolBar title="编辑" langArg="common.user" icon="icon-edit"
				url="holidayController.do?up" funname="update"></t:dgToolBar>
			<t:dgToolBar title="批量导入" langArg="common.user" icon="icon-edit"
				url="hotelController.do?up" funname="update"></t:dgToolBar>
			<t:dgToolBar title="导出excel" langArg="common.user" icon="icon-edit"
				url="hotelController.do?up" funname="update"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
