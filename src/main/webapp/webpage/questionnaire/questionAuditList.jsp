<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<t:formvalid formid="formobj" refresh="true" dialog="false" action="questionNaireController.do?audit" usePlugin="password" layout="table">
	<table style="width:0px;height:0px;margin-top:-10px">
		<tr> 
			<td> 
			<textarea id="registerUrl" style="width:0px;height:0px;">
				 <t:mutiLang langKey="zzc.platform.appBase"/>questionNaireController.do?guestreportquestion&analyzeid=
			</textarea>     
			</td> 
		</tr> 
	</table>
</t:formvalid>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="tempSearchColums" style="display: none">
			<div name="searchColums"></div>
		</div>
		<t:datagrid name="userList2" title="" queryMode="group" actionUrl="questionNaireController.do?questionDatagrid" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			
            <t:dgCol title="问卷名称" field="name" query="true"  width="20"></t:dgCol>
			<t:dgCol title="提交时间" field="startTime"   formatter="yyyy-MM-dd" width="10"></t:dgCol> 
			<t:dgCol title="问卷状态" field="status" query="true" sortable="true" replace="<font color=\"red\">未发布</font>_0,<font color=\"#d2980c\">已发布</font>_1,已回收_2"></t:dgCol>
			<t:dgToolBar title="新增问卷" langArg="common.user" icon="icon-add" url="questionNaireController.do?add" funname="add"></t:dgToolBar>
			<t:dgCol title="操作" field="opt" width="50"></t:dgCol>
      	    <t:dgDelOpt title="删除" url="questionNaireController.do?del&id={id}"/>

			<t:dgFunOpt funname="fabu(id)" title="发布" exp="status#eq#0"></t:dgFunOpt>
			<t:dgFunOpt funname="huishou(id)" title="回收" exp="status#eq#1"></t:dgFunOpt>
            <t:dgFunOpt funname="analyze(id)" title="问卷统计" exp="status#eq#1"  ></t:dgFunOpt>
            <t:dgFunOpt funname="analyze(id)" title="问卷统计" exp="status#eq#2"  ></t:dgFunOpt>
               
            <t:dgFunOpt funname="detail(id,url)" title="查看问卷" exp="status#eq#0" ></t:dgFunOpt> 
            <t:dgFunOpt funname="detail(id,url)" title="查看问卷" exp="status#eq#1" ></t:dgFunOpt>
            <t:dgFunOpt funname="detail(id,url)" title="查看问卷" exp="status#eq#2" ></t:dgFunOpt>
    
            <t:dgFunOpt funname="report(id)" title="发布链接" exp="status#eq#1" ></t:dgFunOpt>
		</t:datagrid>  
	</div>
</div>

<script type="text/javascript">
  	function report(id){
  		$.ajax ({
			url:"questionNaireController.do?shortUrl&id="+id,
			dataType:"json",
			type:"POST",
	        async:false,  
	        cache:false,
			success:function(data){
				if(data == null || data.length == 0){
					alert("链接转换失败!");
					return;
				}
				var input=document.getElementById("registerUrl");//input的ID值
		  		input.innerHTML = data;
		  		input.select(); //选择对象 
		  		document.execCommand("Copy"); //执行浏览器复制命令
		  		alert("链接已复制!");
		 	}}
		);
	}  

  	function userListsearch(){
		$.dataGrid("#userList2").reload();
	}

  	function detail(id,url){
		createdetailwindow('问卷详情', 'questionNaireController.do?auditwenjuan&analyzeid='+id+'&load=detail&bianjiurl='+url);
	} 
	
	function analyze(id){
		createdetailwindow('问卷统计', 'questionNaireController.do?wenjuan&analyzeid='+id,800,500);
	} 

	var iframe;
	function audit(id,url) {
	var button = [{  
           name: "提交",
           callback: function(){
                iframe = this.iframe.contentWindow;
				saveObj();
				return false;
           },
           focus: true
        }];    
		createwindowbutton('编辑','questionNaireController.do?auditwenjuan&analyzeid='+id+'&bianjiurl='+url,button);
	}    

	function fabu(id){
		$.ajax ({
			url:"questionNaireController.do?addsignOne&id="+id,
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
			}
		});
	}
		
	function huishou(id){
		$.ajax ({
			url:"questionNaireController.do?addsignTwo&id="+id,
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
			}
		});
	}
		
	function zancuncheck(scenicdataid,spotId){
	var formjson = $('#formobj', iframe).serialize();
		$.ajax ({
			url:"scenicMonthController.do?zancuncheck&id="+scenicdataid,
			data:formjson,
			dataType:"json",
			success:function(data){    
				alert(data.msg);
				reloadTable();
			}
		});
	}
</script>