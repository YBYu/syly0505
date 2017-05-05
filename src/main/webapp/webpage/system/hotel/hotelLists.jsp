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
			actionUrl="hotelController.do?datagrid" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="酒店名称" field="unitname" query="true" width="10"></t:dgCol>
			<t:dgCol title="酒店类型" field="housegrade" query="true" sortable="ture"
				width="4" replace="其他_0,舒适_1,高档_2,豪华_3,一星_4,二星_5,三星_6,四星_7,五星_8"></t:dgCol>
			<t:dgCol title="所属辖区" field="county" query="true"
				replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>
			<t:dgCol title="所属湾区" field="bay" query="true"
				replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>
			<t:dgCol title="季度营业状态" field="onBuinessSeason" query="true"
				sortable="ture" width="6" replace="营业中_1,停业中_0"></t:dgCol>
			<t:dgCol title="年度营业状态" field="onBuinessYear" query="true"
				sortable="ture" width="6" replace="营业中_1,停业中_0"></t:dgCol>
			<t:dgCol title="区级审核状态" field="districtStatus" query="true" width="7"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="市级审核状态" field="status" query="true" width="8"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="省系统审核状态" field="provinceStatus" query="true"
				width="5" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"   ></t:dgCol>
			<t:dgCol title="国家系统审核状态" field="countryStatus" query="true"
				width="5" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"  ></t:dgCol>

			<t:dgCol title="操作" field="opt" width="25"></t:dgCol>
			<t:dgFunOpt funname="onbuiness(id)" title="停业操作"></t:dgFunOpt>
			<t:dgDelOpt title="删除" url="hotelController.do?del&id={id}" />
			<t:dgFunOpt funname="detail(id)" title="查看详情 "></t:dgFunOpt>
			<t:dgToolBar title="导出酒店信息" funname="exportExcel"></t:dgToolBar>
		</t:datagrid>
		<script type="text/javascript">
		// 停业操作
			function onbuiness(id) {
				createwindow('酒店停业操作', 'hotelController.do?onbuiness&id=' + id,
						200, 100);
			}
			// 查看详情
			function detail(id) {
				createdetailwindow('酒店信息详情',
						'hotelController.do?detail&load=detail&id=' + id, 700, 450);
			}
			
			// 审核操作
			function checkwin(id) {
				var button = [ {
					name : "通过",
					callback : function() {
						$.ajax({
							url : "hotelController.do?addstatus&id=" + id,
							dataType : "json",
							success : function(data) {
								tip(data.msg);
								reloadTable();
							}
						});

					},
					focus : true
				}, {
					name : "退回",
					callback : function() {
						nocheck(id);
						reloadTable();
					},
					focus : true
				} ];
				createwindowbutton('审核', 'hotelController.do?audit&id=' + id,
						button);
			};
			
			// 驳回方法
			function nocheck(id) {
				$.ajax({
					url : "hotelController.do?nocheck&id=" + id,
					dataType : "json",
					success : function(data) {
						tip(data.msg);
						reloadTable();
					}
				});
			}

			// 刷新方法
			function back(data) {
				if (data.success)
					reloadTable();
			}
			
			// 导出excel
			function exportExcel() {
				var queryParams = $("#userList2").datagrid('options').queryParams;
				var unitname = "";
				var housegrade = "";
				var county = "";
				var bay = "";
				var onBuinessSeason = "";
				var onBuinessYear = "";
				var districtStatus = "";
				var status = "";
				var provinceStatus = "";
				var countryStatus = "";

				if (typeof queryParams.unitname != "undefined") {
					unitname = queryParams.unitname;
					housegrade = queryParams.housegrade;
					county = queryParams.county;
					bay = queryParams.bay;
					onBuinessSeason = queryParams.onBuinessSeason;
					onBuinessYear = queryParams.onBuinessYear;
					districtStatus = queryParams.districtStatus;
					status = queryParams.status;
					provinceStatus = queryParams.provinceStatus;
					countryStatus = queryParams.countryStatus;				
				}
				window.open("hotelController.do?exportHotelInfo&unitname="
						+ unitname + "&housegrade=" + housegrade + "&status="
						+ status + "&county=" + county + "&bay=" + bay
						+ "&onBuinessSeason=" + onBuinessSeason
						+ "&onBuinessYear=" + onBuinessYear
						+ "&districtStatus=" + districtStatus
						+ "&provinceStatus=" + provinceStatus
						+ "&countryStatus=" + countryStatus);
			}
		</script>