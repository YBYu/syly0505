<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="quarterlyReport" title="" queryMode="group" actionUrl="travelQuarterController.do?auditdatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="编号" field="travelQuarterOne.id" hidden="true"></t:dgCol>
	<t:dgCol title="编号" field="travelQuarterTow.id" hidden="true"></t:dgCol>
	<t:dgCol title="编号" field="travelQuarterThree.id" hidden="true"></t:dgCol>	
	<t:dgCol title="许可证编号" field="licensenum" query="true" width="10"></t:dgCol>   
	<t:dgCol title="单位名称" field="name" query="true" width="30"></t:dgCol>		
	<t:dgCol title="入境报表状态" field="travelQuarterOne.status" query="false"  sortable="true" width="10" replace="<font color=\"red\">市级未填报</font>_1,<font color=\"#d2980c\">市级未审核</font>_2,市级已审核(审核不通过)_3,市级已审核(审核通过)_4,国家系统待审核_5,国家系统未通过_6,国家系统已通过_7" ></t:dgCol>	 
	<t:dgCol title="出境报表状态" field="travelQuarterTow.status"  query="false" sortable="true" width="10" replace="<font color=\"red\">市级未填报</font>_1,<font color=\"#d2980c\">市级未审核</font>_2,市级已审核(审核不通过)_3,市级已审核(审核通过)_4,国家系统待审核_5,国家系统未通过_6,国家系统已通过_7" ></t:dgCol>
	<t:dgCol title="国内报表状态" field="travelQuarterThree.status" query="false"  sortable="true" width="10" replace="<font color=\"red\">市级未填报</font>_1,<font color=\"#d2980c\">市级未审核</font>_2,市级已审核(审核不通过)_3,市级已审核(审核通过)_4,国家系统待审核_5,国家系统未通过_6,国家系统已通过_7" ></t:dgCol>		
	<%-- <t:dgCol title="省系统审核状态" field="quarterStatus" query="false"  sortable="ture" width="10" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol> --%>
	<t:dgCol title="操作" field="opt" width="40"></t:dgCol>   
  	<t:dgFunOpt funname="checkwin(id,travelQuarterOne.id)" title="审核入境报表" exp="travelQuarterOne.status#eq#2" ></t:dgFunOpt> 
  	<t:dgFunOpt funname="checkwintow(id,travelQuarterTow.id)" title="审核出境报表" exp="travelQuarterTow.status#eq#2" ></t:dgFunOpt> 
  	<t:dgFunOpt funname="checkwinthree(id,travelQuarterThree.id)" title="审核国内报表" exp="travelQuarterThree.status#eq#2" ></t:dgFunOpt>  	
	<t:dgFunOpt funname="daitianbao(id,travelQuarterOne.id)" title="入境报表代填报" exp="travelQuarterOne.status#eq#1"></t:dgFunOpt> 
  	<t:dgFunOpt funname="daitianbaotwo(id,travelQuarterTow.id)" title="出境报表代填报" exp="travelQuarterTow.status#eq#1"></t:dgFunOpt> 
    <t:dgFunOpt funname="daitianbaothree(id,travelQuarterThree.id)" title="国内报表代填报" exp="travelQuarterThree.status#eq#1"></t:dgFunOpt> 
    <t:dgFunOpt funname="checkDetail(id,travelQuarterOne.id,travelQuarterTow.id,travelQuarterThree.id)" title="查看详情" ></t:dgFunOpt>
    <t:dgFunOpt funname="countryContent(travelQuarterOne.id)" title="审核意见"></t:dgFunOpt>
	<t:dgToolBar title="编辑入境季报" funname="editEntry()"></t:dgToolBar>
	<t:dgToolBar title="编辑出境季报" funname="editLeave()"></t:dgToolBar>
	<t:dgToolBar title="编辑国内季报" funname="editInland()"></t:dgToolBar>
	<t:dgToolBar title="撤回季报" funname="revocation()"></t:dgToolBar>
</t:datagrid>
  


</div>
</div>

<!-- ADDED BY FP 
2016-12-22 13:45:16 -->
<script>

// 撤回季报
function revocation(){
	var selection = $("#quarterlyReport").datagrid("getSelected");
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var statusIn = selection["travelQuarterOne.status"];
	var statusOut = selection["travelQuarterTow.status"];
	var statusInland = selection["travelQuarterThree.status"];
	var inId = selection["travelQuarterOne.id"];
	var outId = selection["travelQuarterTow.id"];
	var inlandId = selection["travelQuarterThree.id"];
	if(statusIn != "5" || statusOut != "5" || statusInland != "5"){
		alert("该数据不允许撤回");
		return;
	}
	$.ajax({
		url : "travelQuarterController.do?revocation&entryId="+inId+"&leaveId="+outId+"&inlandId="+inlandId,
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

// 编辑入境季报
function editEntry(){
	var selection = $("#quarterlyReport").datagrid("getSelected");
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var statusIn = selection["travelQuarterOne.status"];
	var statusOut = selection["travelQuarterTow.status"];
	var statusInland = selection["travelQuarterThree.status"];
	var id = selection.id;
	var inId = selection["travelQuarterOne.id"];

	if(statusIn != "4" || statusOut != "4" || statusInland != "4"){
		alert("该数据不允许编辑");
		return;
	}
	var button = [{  
            name: "提交",
            callback: function(){
            	iframe = this.iframe.contentWindow;
			saveObj();
			return false;
            },
            focus: true
        }];
	createwindowbutton('入境报表编辑','travelQuarterController.do?addsign&quarteroneid='+inId+'&travelid='+id,button,850,500);
}

// 编辑出境季报
function editLeave(){
	var selection = $("#quarterlyReport").datagrid("getSelected");
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var statusIn = selection["travelQuarterOne.status"];
	var statusOut = selection["travelQuarterTow.status"];
	var statusInland = selection["travelQuarterThree.status"];
	var id = selection.id;
	var outId = selection["travelQuarterTow.id"];

	if(statusIn != "4" || statusOut != "4" || statusInland != "4"){
		alert("该数据不允许编辑");
		return;
	}
	var button = [{  
            name: "提交",
            callback: function(){
            	iframe = this.iframe.contentWindow;
			saveObj();
			return false;
            },
            focus: true
        }];
	
	createwindowbutton('出境报表编辑','travelQuarterController.do?addsigntwo&quarteroneid='+outId+'&travelid='+id,button,850,500);
}

// 编辑国内季报
function editInland(){
	var selection = $("#quarterlyReport").datagrid("getSelected");
	if(selection == null){
		alert("请选择一条数据!");
		return;
	}
	var statusIn = selection["travelQuarterOne.status"];
	var statusOut = selection["travelQuarterTow.status"];
	var statusInland = selection["travelQuarterThree.status"];
	var id = selection.id;
	var inlandId = selection["travelQuarterThree.id"];

	if(statusIn != "4" || statusOut != "4" || statusInland != "4"){
		alert("该数据不允许编辑");
		return;
	}
	var button = [{  
            name: "提交",
            callback: function(){
            	iframe = this.iframe.contentWindow;
			saveObj();
			return false;
            },
            focus: true
        }];
	
	createwindowbutton('国内报表代填报','travelQuarterController.do?addsignthree&quarteroneid='+inlandId+'&travelid='+id,button,850,500);
}

	//查看详情
	function checkDetail(id, entryId, exitId, inlandId) {
		createdetailwindow('查看报表详情',
				"travelQuarterController.do?checkDetail&inlandId=" + inlandId
						+ "&exitId=" + exitId + "&entryId=" + entryId
						+ "&travelId=" + id, 780, 500);
	}

	//表格刷新
	function reloadTable() {
		try {
			$("#quarterlyReport").datagrid('reload');
		} catch (e) {

		}
	}
</script>

<script type="text/javascript">
var iframe;
function daitianbao(id,spotId) {//id 为旅行社ID,spotId为报表ID
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
	            	var formjson = $('#formobj', iframe.document).serialize();
					$.ajax ({
						url:"travelQuarterController.do?zancuncheck&scenicdataid="+id,
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
		createwindowbutton('入境报表代填报','travelQuarterController.do?addsign&quarteroneid='+spotId+'&travelid='+id,button,1080,500);
	
	}    	

	
	function checkwin(id,spotId){
	var button = [{
	            name: "通过",
	            callback: function(){
	            	$.ajax ({
						url:"travelQuarterController.do?addstatus&id="+spotId+"&travelid="+id,
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
	            	nocheck(spotId);
	            	reloadTable();
	            },
	            focus: true
	        }];
		createwindowbutton('入境报表审核','travelQuarterController.do?audit&quarteroneid='+spotId+'&load=detail&travelid='+id,button,1080,500);
		}
		function nocheck(spotId){
		
			$.ajax ({
			url:"travelQuarterController.do?nocheck&id="+spotId,
			//data:{id:id},
			dataType:"json",
			success:function(data){
				alert(data.msg);
				reloadTable();
			}	
			 }
			);
		}
		
function daitianbaotwo(id,spotId) {

  
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
	            	var formjson = $('#formobj', iframe.document).serialize();
					$.ajax ({
						url:"travelQuarterController.do?zancunchecktwo&scenicdataid="+id,
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
		createwindowbutton('出境报表代填报','travelQuarterController.do?addsigntwo&quarteroneid='+spotId+'&travelid='+id,button,850,500);
	
	}    	
	       
	function checkwintow(id,spotId){
	var button = [{
	            name: "通过",
	            callback: function(){
	            	$.ajax ({
						url:"travelQuarterController.do?addstatustwo&id="+spotId+"&travelid="+id,
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
	            	nochecktwo(spotId);
	            	
	            },
	            focus: true
	        }];
		createwindowbutton('出境报表审核','travelQuarterController.do?audittow&quartertwoid='+spotId+'&load=detail&travelid='+id,button,850,500);
	
		}
		
	function nochecktwo(spotId){  
		
			$.ajax ({
			url:"travelQuarterController.do?nochecktwo&id="+spotId,
			dataType:"json",
			success:function(data){
				alert(data.msg);
				reloadTable();
			}	
			 }
			);
		}			
function daitianbaothree(id,spotId) {

  
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
	            	var formjson = $('#formobj', iframe.document).serialize();
					$.ajax ({
						url:"travelQuarterController.do?zancuncheckthree&scenicdataid="+id,
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
		createwindowbutton('国内报表代填报','travelQuarterController.do?addsignthree&quarteroneid='+spotId+'&travelid='+id,button,850,500);
	
	} 
	
	function checkwinthree(id,spotId){
	var button = [{
	            name: "通过",
	            callback: function(){
	            	$.ajax ({
						url:"travelQuarterController.do?addstatusthree&id="+spotId+"&travelid="+id,
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
	            	nocheckthree(spotId);
	            	
	            },
	            focus: true
	        }];
		createwindowbutton('国内报表审核','travelQuarterController.do?auditthree&quarterthreeid='+spotId+'&load=detail&travelid='+id,button,850,500);
	
		}
		
function nocheckthree(spotId){
		
			$.ajax ({
			url:"travelQuarterController.do?nocheckthree&id="+spotId,
			//data:{id:id},
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
	createdetailwindow('国家系统审核意见','travelQuarterController.do?countryContent&id='+ id+"&type=1", 450, 160);
}
</script>
