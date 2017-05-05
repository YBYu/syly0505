<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">

	<div region="center" style="padding: 1px;">
		<t:datagrid name="noticeList" title="通知列表" autoLoadData="true"
			actionUrl="noticeController.do?dataGrid&noticerange=${range}" sortName="createDate"
			sortOrder="desc" fitColumns="true" idField="id" fit="true"
			queryMode="group" checkbox="false" queryBuilder="false" >
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="通知范围" field="range" hidden="true" ></t:dgCol>
			<t:dgCol title="通知标题" field="title"  width="60"></t:dgCol>
			<t:dgCol title="发布者" field="announcer" width="40"></t:dgCol>
			<t:dgCol title="通知来源" field="source" width="120" query="true"
				replace="三亚旅游统计管理系统_0,A级景区管理系统_1,旅行社管理系统_2,星级酒店管理系统_3,旅游统计报送系统_4"></t:dgCol>
			<t:dgCol title="通知状态" field="status" width="30" query="true" sortable="true"
				replace="未发布_0,已发布_1,已失效_2"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="60"></t:dgCol>
			<%-- <t:dgFunOpt funname="edit(id)" title="状态设置" exp="source#eq#0"></t:dgFunOpt>
			<t:dgFunOpt funname="detail(id)" title="查看详情" exp="source#eq#1"></t:dgFunOpt>
			<t:dgFunOpt funname="detail(id)" title="查看详情" exp="source#eq#2"></t:dgFunOpt>
			<t:dgFunOpt funname="detail(id)" title="查看详情" exp="source#eq#3"></t:dgFunOpt> --%>
			<t:dgFunOpt funname="edit(id,status)" title="编辑" exp="source#eq#0" ></t:dgFunOpt>
			<t:dgFunOpt funname="detail(id)" title="查看详情"></t:dgFunOpt>
			<t:dgToolBar operationCode="add" title="发布通知" icon="icon-add" url=""
				funname="addNotice"></t:dgToolBar>
			<t:dgDelOpt title="删除" url="noticeController.do?del&id={id}" />
			<%-- <t:dgToolBar operationCode="publih" title="发布通知" icon=""
				url="" funname="update"></t:dgToolBar> --%>	
		</t:datagrid>
	</div>
</div>
<script>
var iframe;
//表格刷新
function reloadtable(){
	try{
		$("#noticeList").datagrid('reload');
		}catch(e){
		
	}
	
}
 //发布通知
	function addNotice() {
		var button = [{  
			 name: "提交",
	            callback: function(){
	            	iframe = this.iframe.contentWindow;
				saveObj();
				reloadtable();
				return false;
	            },
	            focus: true
        }];
		createwindowbutton('发布通知', 'noticeController.do?addNotice',button, 960, 800);
	}
 //通知编辑
	function edit(id,status){
		if(status==2){
			alert('失效的通知无法编辑');
			return false;
		}
		var button = [{  
			 name: "确定",
	            callback: function(){
	            	iframe = this.iframe.contentWindow;
				saveObj();
				reloadtable();
				return false;
	            },
	            focus: true
       }];
		createwindowbutton('通知编辑', 'noticeController.do?edit&id='+ id,button, 960, 800);
	}
 //通知详情
	function detail(id){
		createdetailwindow('通知详情', 'noticeController.do?detail&load=detail&id='+ id,960,800);
	}
	/*
	function editNotice(){
		var $tableObj = $(this.DOM.main[0].childNodes[0].children[1].contentWindow.document.body);
		var status = $tableObj.find("#status")[0].value;
		var id= $tableObj.find("#id")[0].value;
		var noticetable={
				"id":id,
				"status":status
		};
		
		$.ajax({		
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: "data="+JSON.stringify(noticetable),
            url:"noticeController.do?editStatus",
            dataType: "json",
             error: function() {
                 alert("修改失败!");
             },
             success: function() {
            	 alert("修改成功!");
            	 reloadTable();
             }
		});

	}
	 function saveNotice(){
		 var $tableObj = $(this.DOM.main[0].childNodes[0].children[1].contentWindow.document.body);
		var title= $tableObj.find("#noticeTitle")[0].value;
		var content=$tableObj.find("#noticeContent")[0].value;
		var range=$tableObj.find("#noticeRange")[0].value;
		var announcer=$tableObj.find("#noticeAnnouncer")[0].value;
		var noticeTable={"title":title,"content":content,"range":range,"announcer":announcer,"status":1,"source":0};
		$.ajax({
			url:"noticeController.do?save",
			dataType: "json",
			success:function(result){
				alert("添加成功");
				reloadTable();
				
			},error:function(){
				alert("添加失败");
			}
		});
	}
	function saveNotice1(){
		var $tableObj = $(this.DOM.main[0].childNodes[0].children[1].contentWindow.document.body);
		var title= $tableObj.find("#noticeTitle")[0].value;
		var content=$tableObj.find("#noticeContent")[0].value;
		var announcer=$tableObj.find("#noticeAnnouncer")[0].value;
		var noticeTable={"title":title,"content":content,"range":range,"announcer":announcer,"status":0,"source":0};
		$.ajax({
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			url:"noticeController.do?save",
			data: "data="+JSON.stringify(noticeTable),
			dataType: "json",
			success:function(result){
				alert("添加成功");
				reloadTable();
				
			},error:function(){
				alert("添加失败");
			}
		});
	} */
</script>