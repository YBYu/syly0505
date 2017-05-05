<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="dataGrid" title="" queryMode="group"
			actionUrl="hotelMonthlyController.do?monthdatagrid" idField="id"
			fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="酒店月报id" field="hotelmonth.id" hidden="true"></t:dgCol>
			<t:dgCol title="年份" field="hotelmonth.year" width="5"></t:dgCol>
			<t:dgCol title="月份" field="hotelmonth.month" width="5"></t:dgCol>
			<t:dgCol title="酒店名称" field="unitname" query="true" width="15"></t:dgCol>
			<t:dgCol title="酒店类型" field="housegrade" query="true" queryMode="group"
				replace="其他_0,舒适_1,高档_2,豪华_3,一星_4,二星_5,三星_6,四星_7,五星_8" width="10"></t:dgCol>
			<t:dgCol title="所属辖区" field="county"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>		
			<t:dgCol title="所属湾区" field="bay"  query="true" replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>		
			<t:dgCol title="填报日期" field="hotelmonth.reportDate" width="10"></t:dgCol>
			<t:dgCol title="填报人" field="hotelmonth.filler" width="10"></t:dgCol>
			<t:dgCol title="区级审核状态" field="hotelmonth.districtStatus" query="true" width="5"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="市级审核状态" field="hotelmonth.status" width="5" query="true"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
			<t:dgCol title="省系统审核状态" field="hotelmonth.provinceState" width="5" query="true"
			replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
			<t:dgCol title="操作" field="opt" width="25"></t:dgCol>
			<t:dgFunOpt funname="check(id,hotelmonth.id,hotelmonth.status,hotelmonth.districtStatus)" title="审核" exp="hotelmonth.status#eq#2"></t:dgFunOpt>
			<t:dgFunOpt funname="daitianbao(id,hotelmonth.id)" title="代填报"
				exp="hotelmonth.status#eq#1"></t:dgFunOpt>
			<t:dgToolBar title="编辑酒店月报" funname="update"></t:dgToolBar>
			<t:dgFunOpt funname="detail(id,hotelmonth.id)" title="查看详情"
				exp="hotelmonth.status#ne#1"></t:dgFunOpt>
			<t:dgToolBar title="撤回酒店月报"  funname="revocation"></t:dgToolBar>
		</t:datagrid>



	</div>
</div>

<script type="text/javascript">
window.onload = function(e){
	$.ajax({
		url : "hotelMonthlyController.do?isdistrict",
		dataType : "json",
		async : false,
		success : function(data) {
			if(data == "1"){
				$(".button")[0].style.display="none";
				$(".button")[1].style.display="none";
			}
		}
	});
}

function check(id, tId, status, districtStatus){
	$.ajax({
		url : "hotelMonthlyController.do?isdistrict",
		dataType : "json",
		async : false,
		success : function(data) {
			if(data == "1"){
				checkQu(id, tId, status, districtStatus);
			}else{
				checkShi(id, tId);
			}
		}
	});
}

/**
 * 编辑酒店月报
 */
function update(){
	var selection = $("#dataGrid").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var monthId = selection["hotelmonth.id"];
	var id = selection.id;
	var provinceStatus = selection["hotelmonth.provinceState"];
	var status = selection["hotelmonth.status"];
	
	// 上级审核通过的月报不允许编辑
	if(provinceStatus == "4"){
		alert("该数据已审核通过，不允许编辑！");
		return;
	}
	if(provinceStatus == "2"){
		alert("该数据不允许编辑，请先撤回！");
		return;
	}
	if(status != "4"){
		alert("该数据未处于市级审核通过，不允许编辑！");
		return;
	}
	$.ajax({
		url : "hotelMonthlyController.do?isdistrict",
		dataType : "json",
		async : false,
		success : function(data) {
			if(data == "1"){
				alert("区级管理员不允许编辑");
				return;
			}
		}
	});
	var button = [
					{
						name : "提交",
						callback : function() {
							iframe = this.iframe.contentWindow;
							saveObj();
							reloadTable();
							return false;
						},
						focus : true
					} ];
			createwindowbutton('酒店月报编辑',
					'hotelMonthlyController.do?addsign&monthId=' + monthId
							+ '&hotelmanageId=' + id, button);
}

/**
 * 撤回酒店月报
 */
function revocation(){
	var selection = $("#dataGrid").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var id = selection["hotelmonth.id"];
	var provinceStatus = selection["hotelmonth.provinceState"];
	
	$.ajax({
		url : "hotelMonthlyController.do?isdistrict",
		dataType : "json",
		async : false,
		success : function(data) {
			if(data == "1"){
				alert("区级管理员不允许撤回");
				return;
			}
		}
	});
	
	// 省系统通过审核，则不允许撤回
	if(provinceStatus == "2"){
	// 更改数据库字段为审核状态
	$.ajax({
		url : "hotelMonthlyController.do?revocation&id="+id,
		dataType : "json",
		success : function(data) {
			if(data == "success"){
				reloadTable();
				alert("撤回成功!");
			}else{
				alert("撤回失败!");
			}
		}
	});
	}else{
		alert("该数据不允许撤回!");
		return;
	}
}

	function reloadTable() {
		window.setTimeout(function() {
			$("#dataGrid").datagrid('reload');
		}, 1000);

	}
	function detail(id, monthId) {
		createdetailwindow('酒店月报详情',
				'hotelMonthlyController.do?detail&monthId=' + monthId
						+ '&load=detail&hotelmanageId=' + id);
	}
	function userListsearch() {
		$.dataGrid("#dataGrid").reload();
	}

	function checkQu(id, tId, status, districtStatus){
		if(status == "3" || status == "4"){
			alert("该数据已被上级管理员审核");
			return;
		}
		if(districtStatus != "2"){
			alert("该数据不允许审核");
			return;
		}
		$.ajax({
			url : "hotelMonthlyController.do?isdistrict",
			dataType : "json",
			success : function(data) {
				if(data == "1"){
					checkwin(id, tId);
				}else{
					alert("您不是区级管理员!");
					return;
				}
			}
		});
	}

	function checkShi(id, tId){
		$.ajax({
			url : "hotelMonthlyController.do?isdistrict",
			dataType : "json",
			success : function(data) {
				if(data == "1"){
					alert("您不是市级管理员!");
					return;
				}else{
					checkwin(id, tId);
				}
			}
		});
	}
	
	function checkwin(id, monthId) {
		var button = [
				{
					name : "通过",
					callback : function() {
						$.ajax({
									url : "hotelMonthlyController.do?addstatus&monthId="+ monthId,
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
						nocheck(monthId);
					},
					focus : true
				} ];
		createwindowbutton('酒店月报审核', 'hotelMonthlyController.do?audit&monthId='
				+ monthId + '&load=detail&hotelmanageId=' + id, button);
	}
	
	function nocheck(monthId) {
		$.ajax({
			url : "hotelMonthlyController.do?nocheck&monthId=" + monthId,
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				reloadTable();
			}
		});
	}

	function doMigrateOut(title, url, id) {
		createwindow('酒店信息导出', url);
	}

	//id 为基本信息,monthid为月报信息id
	var iframe;
	function daitianbao(id, monthId) {
		var button = [
				{
					name : "提交",
					callback : function() {
						iframe = this.iframe.contentWindow;
						iframe.$("#saveHotelMonthly")[0].action = iframe.$("#saveHotelMonthly")[0].action.replace(/&isTemp=1/g,'');
						saveObj();
						reloadTable();
						return false;
					},
					focus : true
				},
				{
					name : "暂存",
					callback : function() {
						iframe = this.iframe.contentWindow;
						iframe.$("#saveHotelMonthly")[0].action = iframe.$("#saveHotelMonthly")[0].action.replace(/&isTemp=1/g,'');
						iframe.$("#saveHotelMonthly")[0].action += "&isTemp=1";
						saveObj();
						reloadTable();
						return false;
					},
					focus : true
				} ];
		createwindowbutton('酒店月报代填报',
				'hotelMonthlyController.do?addsign&monthId=' + monthId
						+ '&hotelmanageId=' + id, button);
	}

	function zancuncheck(id, monthId) {
		var formjson = $('#saveHotelMonth', iframe).serialize();
		$.ajax({
			url : "hotelMonthlyController.do?zancuncheck&hotelId=" + id,
			data : formjson,
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				reloadTable();
			}
		});
	}
	
</script>
