<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- 引入状态数据 -->
<script type="text/javascript" src="hotel_js/status.js">
</script>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="userList2" title="" queryMode="group"
			actionUrl="hotelController.do?notStarDatagridSta" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="酒店名称" field="unitname" query="false" width="12"></t:dgCol>
			<t:dgCol title="酒店类型" field="housegrade" query="false" sortable="false"
				width="7" replace="其他_0,舒适_1,高档_2,豪华_3,一星_4,二星_5,三星_6,四星_7,五星_8"></t:dgCol>
			<t:dgCol title="所属辖区" field="county"
				replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>
			<t:dgCol title="所属湾区" field="bay"
				replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>
			<t:dgCol title="季度营业状态" field="onBuinessSeason" query="false"
				sortable="ture" width="6" replace="营业中_1,停业中_0"></t:dgCol>
			<t:dgCol title="年度营业状态" field="onBuinessYear" query="false"
				sortable="ture" width="6" replace="营业中_1,停业中_0"></t:dgCol>
			<t:dgCol title="区级审核状态" field="districtStatus" query="false" width="7"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="市级审核状态" field="status" query="false" width="7"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="省级系统核状态" field="provinceStatus" query="false"
				width="7" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"   ></t:dgCol>
			<t:dgCol field="writer" title="填报人" width="5"></t:dgCol>
			<t:dgCol title="填报时间" field="fillDate" query="false" sortable="true"
				width="10"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="25"></t:dgCol>
			<t:dgFunOpt funname="edit(districtStatus,status,provinceStatus,fillDate)" title="编辑"></t:dgFunOpt>
			<t:dgFunOpt funname="detail(id)" title="查看详情"></t:dgFunOpt>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
// 编辑
function edit(districtStatus, status, provinceStatus, fillDate){
	if(fillDate != null && fillDate.length != 0){
		var year = fillDate.substring(0,4);
		var nowYear = new Date().getFullYear();
		if(year >= nowYear){
			if(provinceStatus == "3" || status == "1" || status == "3" || districtStatus == "3"){
				createwindow("非星级酒店基本信息年报编辑 ","hotelController.do?addf", 600, 480);
			}else{
				alert("今年基本信息年报已填报");
			}
			return;
		}
	}
	createwindow("非星级酒店基本信息年报编辑 ","hotelController.do?addf", 600, 480);
}

// 查看详情
function detail(id){
	createdetailwindow('酒店基本信息年报详情', 'hotelController.do?detailc&load=detail&id=' + id,600,430);
}

</script>
