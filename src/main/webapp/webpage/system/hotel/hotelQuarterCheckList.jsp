<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="quarterCheckList" title="" queryMode="group" actionUrl="hotelQuarterController.do?checkdatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="酒店季报id" field="hotelquarter.id" hidden="true"></t:dgCol>
	<t:dgCol  title="年份" field="hotelquarter.year" width="5"></t:dgCol>
	<t:dgCol title="季度" field="hotelquarter.quarter"  replace="第一季度_1,第二季度_2,第三季度_3,第四季度_4" sortable="ture"  width="5" > </t:dgCol>   
	<t:dgCol title="酒店名称" field="unitname"  query="true"   width="10"></t:dgCol>
	<t:dgCol title="酒店星级" field="housegrade" query="true" replace="一星_4,二星_5,三星_6,四星_7,五星_8" width="5"></t:dgCol>	
	<t:dgCol title="所属辖区" field="county" width="5" query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>		
	<t:dgCol title="所属湾区" field="bay" width="5" query="true" replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>
	<t:dgCol title="填报日期" field="hotelquarter.writerDate"  width="5" ></t:dgCol>
	<t:dgCol title="填报人" field="hotelquarter.writer" query="false" width="5" ></t:dgCol>
	<t:dgCol title="区级审核状态" field="hotelquarter.districtStatus" query="true" width="5"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
	<t:dgCol title="市级审核状态" field="hotelquarter.status" query="true" width="5" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"  ></t:dgCol>	
	<t:dgCol title="国家系统审核状态" field="hotelquarter.countryState" query="true" width="5" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"  ></t:dgCol>	
	<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
	<t:dgFunOpt funname="check(id,hotelquarter.id,hotelquarter.status,hotelquarter.districtStatus)" title="审核" exp="hotelquarter.status#eq#2"></t:dgFunOpt>
	<t:dgFunOpt funname="daitianbao(id,hotelquarter.id)" title="代填报" exp="hotelquarter.status#eq#1"></t:dgFunOpt>
	<t:dgFunOpt funname="detail(id,hotelquarter.id)" title="查看详情" exp="hotelquarter.status#ne#1" ></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(hotelquarter.id)" title="审核意见"
			exp="hotelquarter.countryState#eq#3"></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(hotelquarter.id)" title="审核意见"
			exp="hotelquarter.countryState#eq#4"></t:dgFunOpt>
	<t:dgToolBar title="编辑酒店季报" funname="update"></t:dgToolBar>
	<t:dgToolBar title="撤回酒店季报"  funname="revocation"></t:dgToolBar>
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

function reloadTable(){
	window.setTimeout(function(){
		$("#quarterCheckList").datagrid('reload');
	},1000);
	
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
 * 编辑酒店季报
 */
function update(){
	var selection = $("#quarterCheckList").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var quarterId = selection["hotelquarter.id"];
	var id = selection.id;
	var countryState = selection["hotelquarter.countryState"];
	var status = selection["hotelquarter.status"];
	
	// 上级审核通过的季报不允许编辑
	if(countryState == "4"){
		alert("该数据已审核通过，不允许编辑！");
		return;
	}
	if(countryState == "2"){
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
	var button = [{  
        name: "提交",
        	 callback: function(){
	            iframe = this.iframe.contentWindow;
				saveObj();
				return false;
        },
        focus: true
    }];
createwindowbutton('酒店季报编辑', 'hotelQuarterController.do?addsign&quarterId='+quarterId+'&hotelmanageId='+id,button);
}

/**
 * 撤回酒店季报
 */
function revocation(){
	var selection = $("#quarterCheckList").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var id = selection["hotelquarter.id"];
	var countryState = selection["hotelquarter.countryState"];
	
	if(countryState == "4"){
		alert("该数据已审核通过，不允许撤回！");
		return;
	}
	
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
	
	// 国家通过审核，则不允许撤回
	if(countryState == "2"){
	// 更改数据库字段为审核状态
	$.ajax({
		url : "hotelQuarterController.do?revocation&id="+id,
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
	
//酒店基本信息id 和 参数说明 季报id
function detail(id,quarterId){
	createdetailwindow('酒店季报详情', 'hotelQuarterController.do?detail&hotelmanageId='+id+'&load=detail&quarterId='+quarterId, 700, 450);
}
function detailee(id,quarterId){
	createwindow('酒店季报导入', 'hotelQuarterController.do?import');
}
function userListsearch(){
	$.dataGrid("#userList2").reload();
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
	
	//id为酒店季报的id manageiD为酒店基本信息的iD
function checkwin(id,quarterId){
		var button = [{  
		            name: "通过",
		            callback: function(){
		            	$.ajax ({
							url:"hotelQuarterController.do?addstatus&quarterId="+quarterId,
							//data:{id:id},
							dataType:"json",
							success:function(data){
								alert(data.msg);
								reloadTable();
							}	
							 }
							);
		            	
		            },
		            focus: true
		        },{
		            name: "退回",
		            callback: function(){
		            	nocheck(quarterId);
		            	
		            },
		            focus: true
		        }];
			createwindowbutton('酒店季报审核','hotelQuarterController.do?audit&quarterId='+quarterId+'&load=detail&hotelmanageId='+id,button, 700, 450);
			}
			function nocheck(quarterId){
			
				$.ajax ({
				url:"hotelQuarterController.do?nocheck&quarterId="+quarterId,
				//data:{id:id},
				dataType:"json",
				success:function(data){
					alert(data.msg);
					reloadTable();
				}	
				 }
				);
			}	
	
	var iframe;
	function daitianbao(id,quarterId) {
	var button = [{  
		            name: "提交",
		            	 callback: function(){
		 	            iframe = this.iframe.contentWindow;
		 				saveObj();
		 				return false;
		            	
		            },
		            focus: true
		        },{
		            name: "暂存",
		            callback: function(){
		           		 iframe = this.iframe.contentWindow;
		            	var formjson = $('#saveHotelQuarter', iframe.document).serialize();
						$.ajax ({
							url:"hotelQuarterController.do?zancuncheck&hotelId="+id,
							data:formjson,
							dataType:"json",
							success:function(data){    
								alert(data.msg);
								reloadTable();
								}  	
						 	}
						);
		            	
		            },
		            focus: true
		        }];
			createwindowbutton('酒店季报代填报', 'hotelQuarterController.do?addsign&quarterId='+quarterId+'&hotelmanageId='+id,button, 700, 450);
		} 
		
	function zancuncheck(id,quarterId){
		var formjson = $('#saveHotelQuarter', iframe).serialize();
			$.ajax ({
			url:"hotelMonthlyController.do?zancuncheck&hotelId="+id,
			data:formjson,
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
			}  	
			 }
			);
		}

	// 查看审核意见
	function countryContent(id){
		createdetailwindow('国家系统审核意见','hotelQuarterController.do?countryContent&id='+ id, 450, 160);
	}
</script>
