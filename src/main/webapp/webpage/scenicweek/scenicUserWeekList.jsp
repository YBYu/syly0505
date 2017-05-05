<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">  
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="userList2" title="" queryMode="group" actionUrl="scenicWeekController.do?userDatagrid" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="编号" field="scenicData.id" hidden="true"></t:dgCol>	
		<t:dgCol field="year" title="年份" query="true" sortable="true" replace="2000年_2000,2001年_2001,2002年_2002,2003年_2003,2004年_2004,2005年_2005,2006年_2006,2007年_2007,2008年_2008,2009年_2009,2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
	<t:dgCol title="周数" field="cycle" query="true"  sortable="true" replace="第一周_1,第二周_2,第三周_3,第四周_4,第五周_5,第六周_6,第七周_7,第八周_8,第九周_9,第十周_10,第十一周_11,第十二周_12,第十三周_13,第十四周_14,第十五周_15,第十六周_16,第十七周_17,第十八周_18,第十九周_19,第二十周_20第二十一周_21,第二十二周_22,第二十三周_23,第二十四周_24,第二十五周_25,第二十六周_26,第二十七周_27,第二十八周_28,第二十九周_29,第三十周_30,第三十一周_31,第三十二周_32,第三十三周_33,第三十四周_34,第三十五周_35,第三十六周_36,第三十七周_37,第三十八周_38,第三十九周_39,第四十周_40,第四十一周_41,第四十二周_42,第四十三周_43,第四十四周_44,第四十五周_45,第四十六周_46,第四十七周_47,第四十八周_48,第四十九周_49,第五十周_50,第五十一周_51,第五十二周_52,第五十三周_53"></t:dgCol>
	<t:dgCol title="景区名称" field="scenicData.name"      width="20"></t:dgCol>
	<t:dgCol title="所属辖区" field="scenicData.area"    replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4,三亚市_5"></t:dgCol>	
	<t:dgCol title="景区类型" field="scenicData.scenictype"   replace="自然景观_1,历史文化_2,度假休闲_3,主题游乐_4,博物馆_5,乡村旅游_6,工业旅游_7,红色旅游_8,科技旅游_9,其他_10"  ></t:dgCol>
	<t:dgCol title="景区等级" field="scenicData.level"   sortable="true" replace="未评定_6,AAAAA_1,AAAA_2,AAA_3,AA_4,A_5"></t:dgCol>
	<%-- <t:dgCol title="营业收入(万元)" field="taking"   ></t:dgCol>   --%>
	<t:dgCol title="审核状态" field="status"    query="true"  replace="未分配_1,已分配_2,未填报_3,提交待审_4,退回修订_5,区级审核通过_6,市级审核通过_7" ></t:dgCol>	
	<t:dgCol title="操作" field="opt" width="20"></t:dgCol>
	
	<t:dgToolBar title="周报数据上报" langArg="common.user" icon="icon-add" url="" funname="add()"></t:dgToolBar>	         
	
	
	<t:dgDelOpt title="删除" url="scenicWeekController.do?del&id={id}"  exp="status#eq#4" />
	<t:dgDelOpt title="删除" url="scenicWeekController.do?del&id={id}"  exp="status#eq#5" />
	<t:dgFunOpt funname="detail(id,scenicData.id)" title="查看详情" ></t:dgFunOpt>
	<t:dgFunOpt funname="update(id,scenicData.id)" title="编辑" exp="status#eq#5" ></t:dgFunOpt> 
	<t:dgFunOpt funname="update(id,scenicData.id)" title="编辑" exp="status#eq#3" ></t:dgFunOpt> 
	<%-- <t:dgToolBar title="查看周报详情" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> --%>
<%-- 
<t:dgToolBar title="景区数据导出" langArg="common.user" icon="icon-put" url="excelController.do?toexcel" funname="doMigrateOut"></t:dgToolBar>
<t:dgToolBar title="批量导入" langArg="common.user" icon="icon-put" url="" funname=""></t:dgToolBar>
--%>
</t:datagrid>  
  


</div>
</div>
   
<script type="text/javascript">
//表格重新加载
function reloadTable(){
	try{
		$("#userList2").datagrid('reload');
	}catch(e)
	{
		
	}	
}$(document).ready(function(){

	 $.ajax ({
			url:"calcYear.do?getYearList&tableName=t_scenicspot_weekly&columnName=year",
			dataType:"json",
			success:function(data){
				$("select[name='year']").empty();
				 var itemYear=data.split(",");
				 $("select[name='year']").append("<option  >请选择</option>");
				 for(var i=0;i<itemYear.length;i++){
						$("select[name='year']").append("<option value='"+itemYear[i]+"'>"+itemYear[i]+"年</option>");
				}
				 
			}
			 });
	 
	
});
//周报上报
	function add(){
		var roleName='${scenicdata}';
		var thisweekdata='${scenicweekdata}';
		if(roleName=="null"){//如果区级管理员易经审核无法在审核
			alert("请先填写景区基本信息");
			return false;
		}
		else if(thisweekdata=="have"){
			alert("本周周报已填写");
			return false;
		}
		 createwindow('周报编辑','scenicWeekController.do?addUser', 850, 350);
	}
	//周报信息详情
  function detail(id,spotId){     
		createdetailwindow('景区周报信息详情', 'scenicWeekController.do?weekdetial&scenicdataid='+spotId+'&load=detail&weekid='+id);
	} 
	//景区周报编辑
 function update(id,spotId){
	 createwindow('周报编辑','scenicWeekController.do?updateScenic&scenicdataid='+spotId+'&weekid='+id, 850, 350);
 }
function userListsearch(){
	$.dataGrid("#userList2").reload();
}
 
 
</script>
