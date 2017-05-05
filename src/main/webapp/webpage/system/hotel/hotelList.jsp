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
			actionUrl="hotelController.do?datagridsta" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="酒店名称" field="unitname" query="true" width="12"></t:dgCol>
			<%-- <t:dgCol title="用户名" sortable="false" query="false"
				field="username" width="5"></t:dgCol> --%>
			<%-- <t:dgCol title="标牌编号"    
				field="code" width="10"></t:dgCol> --%>
			<t:dgCol title="酒店类型" field="housegrade" query="true" sortable="ture"
				width="7" replace="其他_0,舒适_1,高档_2,豪华_3,一星_4,二星_5,三星_6,四星_7,五星_8"></t:dgCol>
			<t:dgCol title="所属辖区" field="county"
				replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>
			<t:dgCol title="所属湾区" field="bay"
				replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>
			<t:dgCol title="季度营业状态" field="onBuinessSeason" query="true"
				sortable="ture" width="6" replace="营业中_1,停业中_0"></t:dgCol>
			<t:dgCol title="年度营业状态" field="onBuinessYear" query="true"
				sortable="ture" width="6" replace="营业中_1,停业中_0"></t:dgCol>
			<t:dgCol title="区级审核状态" field="districtStatus" query="true" width="7"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="市级审核状态" field="status" query="true" width="7"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
			<t:dgCol title="省级系统核状态" field="provinceStatus" query="true"
				width="7" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"   ></t:dgCol>
			<t:dgCol title="国家系统审核状态" field="countryStatus" query="true"
				width="7" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"   ></t:dgCol>

			<t:dgCol field="writer" title="填报人" width="5"></t:dgCol>
			<t:dgCol title="填报时间" field="fillDate" query="false" sortable="true"
				width="10"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="25"></t:dgCol>
			<t:dgFunOpt funname="check(id,status,districtStatus)" title="审核"
				exp="status#eq#2"></t:dgFunOpt>
			<t:dgFunOpt funname="detail(id)" title="查看详情"></t:dgFunOpt>
			<t:dgFunOpt funname="countryContent(id)" title="审核意见"
				exp="countryStatus#eq#3"></t:dgFunOpt>
			<t:dgFunOpt funname="countryContent(id)" title="审核意见"
				exp="countryStatus#eq#4"></t:dgFunOpt>
			<t:dgToolBar title="添加酒店" url="hotelController.do?addUser"
				funname="add"></t:dgToolBar>
			<t:dgToolBar title="编辑酒店年报" funname="update"></t:dgToolBar>
			<t:dgToolBar title="撤回酒店年报信息" funname="revocation"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
// 区级管理员去掉按钮
window.onload = function(e){
	$.ajax({
		url : "hotelMonthlyController.do?isdistrict",
		dataType : "json",
		async : false,
		success : function(data) {
			if(data == "1"){
				$(".button")[0].style.display="none";
				$(".button")[1].style.display="none";
				$(".button")[2].style.display="none";
			}
		}
	});
}

// 是否区级管理员
function check(id, status, districtStatus){
	$.ajax({
		url : "hotelMonthlyController.do?isdistrict",
		dataType : "json",
		async : false,
		success : function(data) {
			if(data == "1"){
				checkQu(id, status, districtStatus);
			}else{
				checkShi(id);
			}
		}
	});
}

/**
 * 编辑酒店年报
 */
function update(){
	var selection = $("#userList2").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var id = selection.id;
	var status = selection.status;
	var provinceStatus = selection.provinceStatus;
	var countryStatus = selection.countryStatus;
	// 已有提交上去的数据则不允许编辑
	if(provinceStatus == "2" || countryStatus == "2"){
		alert("该数据不允许编辑");
		return;
	}
	// 只能编辑市级审核通过的数据
	if(status == "2" || status == "3"){
		alert("该数据不允许编辑");
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
				}else{
					createwindow('酒店年报编辑', 'hotelController.do?rollbackEdit&&id=' + id, 700, 450);
				}
			}
		});
}

/**
 * 撤回酒店年报
 */
function revocation(){
	var selection = $("#userList2").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var id = selection.id;
	var provinceStatus = selection.provinceStatus;
	var countryStatus = selection.countryStatus;
	// 国家和省系统均通过审核，则不允许撤回
	if(provinceStatus == "2" || countryStatus == "2"){
		$.ajax({
			url : "hotelMonthlyController.do?isdistrict",
			dataType : "json",
			async : false,
			success : function(data) {
				if(data == "1"){
					alert("区级管理员不允许撤回");
					return;
				}else{
					// 更改数据库字段为审核状态
					$.ajax({
						url : "hotelController.do?revocation&id="+id,
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
				}
			}
		});
	}else{
		alert("该数据不允许撤回");
		return;
	}
}

// 查看详情
function detail(id){
	createdetailwindow('酒店信息详情', 'hotelController.do?detailc&load=detail&id=' + id, 700, 450);
}

// 区级操作校验
function checkQu(id, status, districtStatus){
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
				checkwin(id);
			}else{
				alert("您不是区级管理员!");
				return;
			}
		}
	});
}

// 市级操作校验
function checkShi(id){
	$.ajax({
		url : "hotelMonthlyController.do?isdistrict",
		dataType : "json",
		success : function(data) {
			if(data == "1"){
				alert("您不是市级管理员!");
				return;
			}else{
				checkwin(id);
			}
		}
	});
}

 function checkwin(id){
		var button = [{  
		            name: "通过",
		            callback: function(){
		            	$.ajax ({
							url:"hotelController.do?addstatus&id="+id,
							//data:{id:id},
							dataType:"json",
							success:function(data){
								tip(data.msg);
								reloadTable();
							}	
							 }
							);
		            	
		            },
		            focus: true
		        },{
		            name: "退回",
		            callback: function(){
		            	nocheck(id);
		            	reloadTable();
		            },
		            focus: true
		        }];
			createwindowbutton('审核','hotelController.do?audit&id='+id,button);
 };
 function nocheck(id){
	 $.ajax ({
			url:"hotelController.do?nocheck&id="+id,
			//data:{id:id},
			dataType:"json",
			success:function(data){
				tip(data.msg);
				reloadTable();
			}	
			 }
			);
 }
function doMigrateOut(){
// 		url += '&ids='+ getListSelections();
		/* createwindow('酒店基本信息导出', url); */
		JeecgExcelExport('hotelController.do?exportsxls', "userList2")
		
	}
 function back(data){
		if(data.success)
		reloadTable();
	}
 
	// 查看审核意见
	function countryContent(id){
		createdetailwindow('国家系统审核意见','hotelController.do?countryContent&id='+ id, 450, 160);
	}
</script>
