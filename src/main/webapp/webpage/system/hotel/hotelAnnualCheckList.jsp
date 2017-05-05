<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="annualList" title="" queryMode="group" actionUrl="hotelAnnualController.do?annualDatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="酒店id" field="hotelAnnual.id" hidden="true"></t:dgCol>
	<t:dgCol  title="年份" width="5" field="hotelAnnual.year" replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
	<t:dgCol title="酒店名称" field="unitname"  query="true"  width="20"></t:dgCol>
	<t:dgCol title="酒店星级" field="housegrade" query="true" width="5" replace="一星_4,二星_5,三星_6,四星_7,五星_8"></t:dgCol>	
	<t:dgCol title="所属辖区" field="county" width="5" query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4"></t:dgCol>		
	<t:dgCol title="所属湾区" field="bay" width="5" query="true" replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"></t:dgCol>
	<t:dgCol title="填报时间" field="hotelAnnual.reportTime"  width="5" ></t:dgCol>
	<t:dgCol title="填报人" field="hotelAnnual.reportPerson"  width="5" ></t:dgCol>
	<t:dgCol title="区级审核状态" field="hotelAnnual.districtStatus" query="true" width="10"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
	<t:dgCol title="市级审核状态" field="hotelAnnual.status" query="true" width="10" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4"></t:dgCol>
	<t:dgCol title="省级审核状态" field="hotelAnnual.countryState" query="true" width="10" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
    <%-- <t:dgCol title="营业收入(千元)" field="hotelAnnual.operationIncome" width="5"  ></t:dgCol> 
	<t:dgCol title="餐厅总额(千元)" field="hotelAnnual.canteeIncome"  width="5" ></t:dgCol> 
	<t:dgCol title="住宿收入(千元)" field="hotelAnnual.roomIncome"  width="5" ></t:dgCol>     
	<t:dgCol title="其他收入(千元)" field="hotelAnnual.otherIncome"  width="5" ></t:dgCol> --%>       	      
	<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
	<t:dgFunOpt funname="check(id,hotelAnnual.id,hotelAnnual.status,hotelAnnual.districtStatus)" title="审核" exp="hotelAnnual.status#eq#2"></t:dgFunOpt> 
	<t:dgFunOpt funname="daitianbao(id,hotelAnnual.id)" title="代填报" exp="hotelAnnual.status#eq#1"></t:dgFunOpt>
	<t:dgFunOpt funname="detail(id,hotelAnnual.id)" title="查看详情" exp="hotelAnnual.status#ne#1" ></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(hotelAnnual.id)" title="审核意见"
			exp="hotelAnnual.countryState#eq#3"></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(hotelAnnual.id)" title="审核意见"
			exp="hotelAnnual.countryState#eq#4"></t:dgFunOpt>
	<t:dgToolBar title="编辑酒店年报" funname="update"></t:dgToolBar>
	<t:dgToolBar title="撤回酒店年报"  funname="revocation"></t:dgToolBar>
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
		$("#annualList").datagrid('reload');
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
 * 编辑酒店年报
 */
function update(){
	var selection = $("#annualList").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var annualId = selection["hotelAnnual.id"];
	var id = selection.id;
	var countryState = selection["hotelAnnual.countryState"];
	var status = selection["hotelAnnual.status"];
	
	// 上级审核通过的季报不允许编辑
	if(countryState == "4"){
		alert("该数据已审核通过，不允许编辑！");
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
createwindowbutton('酒店年报编辑', 'hotelAnnualController.do?addsign&annualId='+annualId+'&hotelmanageId='+id,button);
}

/**
 * 撤回酒店年报
 */
function revocation(){
	var selection = $("#annualList").datagrid('getSelected');
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var id = selection["hotelAnnual.id"];
	var countryState = selection["hotelAnnual.countryState"];
	if(countryState != "2"){
		alert("该数据不允许撤回！");
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
		url : "hotelAnnualController.do?revocation&id="+id,
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
	
	
//酒店基本信息id 和 参数说明 年报id
function detail(hotelmanageId,annualId){
	createdetailwindow('酒店年报详情', 'hotelAnnualController.do?detail&hotelmanageId='+hotelmanageId+'&load=detail&annualId='+annualId);
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

	//iD为酒店基本信息的iD,
	function checkwin(id,annualId){
		var button = [{  
		            name: "通过",
		            callback: function(){
		            	$.ajax ({
							url:"hotelAnnualController.do?addstatus&annualId="+annualId,
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
		            	nocheck(annualId);
		            	
		            },
		            focus: true
		        }];
			createwindowbutton('酒店年报审核','hotelAnnualController.do?audit&annualId='+annualId+'&load=detail&hotelmanageId='+id,button);
			}
			function nocheck(annualId){
			
				$.ajax ({
				url:"hotelAnnualController.do?nocheck&annualId="+annualId,
				dataType:"json",
				success:function(data){
					alert(data.msg);
					reloadTable();
				}	
				 }
				);
			}	
	
		function doMigrateOut(title,url,id){
		createwindow('酒店年报导出', url);
	}
		
var iframe;
function daitianbao(id,annualId) {
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
        	var formjson = $('#saveAnnual', iframe.document).serialize();
			$.ajax ({
				url:"hotelAnnualController.do?zancuncheck&hotelId="+id,
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
createwindowbutton('酒店年报代填报', 'hotelAnnualController.do?addsign&annualId='+annualId+'&hotelmanageId='+id,button);
} 
 
// 查看审核意见
function countryContent(id){
	createdetailwindow('国家系统审核意见','hotelAnnualController.do?countryContent&id='+ id, 450, 160);
}


</script>
