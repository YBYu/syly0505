<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<div id="tempSearchColums" style="display: none">                                     
    <div name="searchColums"></div></div>
<t:datagrid name="userList2" title="" queryMode="group" actionUrl="scenicController.do?userdatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="景区名称" field="name"  query="true"   width="20"></t:dgCol>
	<t:dgCol title="景区编码" sortable="false"  query="true"  field="code" width="15"></t:dgCol>
	<t:dgCol title="所属辖区" field="area"  query="true" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5"></t:dgCol>
	<t:dgCol title="所属湾区" field="bayType"  query="true" replace="市区_0,亚龙湾_1,大东海_2,三亚湾_3,海棠湾_4"  ></t:dgCol>	
	<t:dgCol title="景区等级" field="level" query="true"   replace="未评定_6,AAAAA_1,AAAA_2,AAA_3,AA_4,A_5"></t:dgCol>
	<t:dgCol title="景区类型"  field="scenictype" sortable="true" replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10"  ></t:dgCol>
	
	<t:dgCol field="status" title="审核状态" query="true"  replace="未分配_1,已分配_2,未填报_3,提交待审_4,退回修订_5,区级审核通过_6,市级审核通过_7,省级审核通过_8,国家审核通过_9"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="scenicController.do?del&id={id}&name={name}"/>  --%>
	<t:dgFunOpt funname="update(id)"  title="编辑"></t:dgFunOpt>	
	<%-- <t:dgToolBar title="景区添加" langArg="common.user" icon="icon-add" url="scenicController.do?addUser" funname="add"></t:dgToolBar> --%>
<%-- <t:dgToolBar title="景区详情" langArg="common.user" icon="icon-edit" url="scenicController.do?addorupdate" funname="update"></t:dgToolBar>
--%>
    <t:dgFunOpt funname="detail(id)" title="查看详情" ></t:dgFunOpt>
          <t:dgFunOpt funname="comments(id)" title="查看审核意见" exp="status#eq#5"></t:dgFunOpt>
    <t:dgFunOpt funname="comments(id)" title="查看审核意见" exp="status#eq#8"></t:dgFunOpt>

<%-- <t:dgToolBar title="景区数据导出" langArg="common.user" icon="icon-put" funname="daochu"></t:dgToolBar>
 --%>
<%-- 
<t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar>
--%>
</t:datagrid>
  


</div>
</div>

<script type="text/javascript">
 
//景区年报上报
 function update(id){
	 $.ajax ({
			url:"scenicController.do?iffirst&scenicid="+id,
			//data:{id:id},
			dataType:"json",
			success:function(data){
				if(data=="notfirst"){
					alert("你今年的年报已经填写");
				}else{
					createwindow('景区编辑', 'scenicController.do?scenicmodify&scenicid='+id,850,500); 
				}
			}	
			 });
	 
	}    
//景区年报审核意见
	function comments(id){
		createdetailwindow('景区年报审核意见详情', 'scenicController.do?comments&scenicid='+id,600,280);
	} 
//景区详情
function detail(id){
		createdetailwindow('景区详情', 'scenicController.do?detialinfosta&scenicid='+id);  
	} 
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
 
 
</script>
