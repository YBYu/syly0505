<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;"> 
<div id="tempSearchColums" style="display: none">
    <div name="searchColums"></div></div>
<t:datagrid name="userList2" title="" queryMode="group" actionUrl="hotelAnnualController.do?ckDatagridAnnual" idField="id" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>  
	<t:dgCol title="年份" field="year" query="true"  sortable="ture" width="5" replace="2010年_2010,2011年_2011,2011年_2011,2012年_2012,2013年_2013,2014年_2014,2015年_2015,2016年_2016,2017年_2017"></t:dgCol>
	<t:dgCol title="酒店名称" field="name"  query="false" width="17"  ></t:dgCol>
	<t:dgCol title="所属辖区" field="hotelmanage.county" width="10" replace="市辖区_0,崖州区_1,海棠区_2,天涯区_3,吉阳区_4" query="false" ></t:dgCol>		
	<t:dgCol title="酒店星级" field="hotelmanage.housegrade" width="10" query="false"  sortable="ture" replace="其他_0,舒适_1,高档_2,豪华_3,一星_4,二星_5,三星_6,四星_7,五星_8"></t:dgCol>		
	<%-- <t:dgCol title="单位负责人" field="person" width="10"  ></t:dgCol>  --%>
	<t:dgCol title="上报日期" field="reportTime"  width="8" ></t:dgCol>
	<t:dgCol title="id" hidden="true" field="hotelmanage.id" ></t:dgCol>
	<t:dgCol title="营业收入(千元)" field="operationIncome"  width="5" ></t:dgCol> 
	<t:dgCol title="餐厅总额(千元)" field="canteeIncome"  width="5" ></t:dgCol> 
	<t:dgCol title="住宿收入(千元)" field="roomIncome"  width="5" ></t:dgCol>     
	<t:dgCol title="其他收入(千元)" field="otherIncome" width="5"  ></t:dgCol>
	<t:dgCol title="区级审核状态" field="districtStatus" width="10" query="false"
				replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
	<t:dgCol title="市级审核状态" field="status" width="10"  replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" ></t:dgCol>
	<t:dgCol title="国家级审核状态" field="countryState" width="10" replace="<font color=\"red\">未填报</font>_1,<font color=\"#d2980c\">未审核</font>_2,已审核(审核不通过)_3,已审核(审核通过)_4" query="false" ></t:dgCol>
	<t:dgCol title="操作" field="opt" width="10"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="hotelAnnualController.do?del&id={id}&name={name}"/> --%>
	<t:dgToolBar title="年报录入" langArg="common.user" icon="icon-add" url="hotelAnnualController.do?addorupdate" funname="add"></t:dgToolBar>
	<t:dgFunOpt funname="edit(hotelmanage.id,id)" title="编辑" exp="status#eq#3" ></t:dgFunOpt>
	<t:dgFunOpt funname="detail(hotelmanage.id,id)" title="查看详情" ></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(id)" title="审核意见"
			exp="countryState#eq#3"></t:dgFunOpt>
	<t:dgFunOpt funname="countryContent(id)" title="审核意见"
			exp="countryState#eq#4"></t:dgFunOpt>
</t:datagrid>
  


</div>
</div>

<script type="text/javascript">

//时间下拉框赋值
$(document).ready(function(){
	$.ajax ({
		url:"calcYear.do?getYearList&tableName=t_hotelmonthly&columnName=year",
		dataType:"json",
		success:function(data){
			var rs = data.split(",");
	
			var str = " <select><option value>---请选择---</option> " ;   
			for(var i = 0; i < rs.length; i++){
				str  =  str  +   " <option value='" + rs[i] + "'> " + rs[i] +"年</option> ";
			}
			str = str + " </select> " ; 
			
			$("select[name='year']")[0].innerHTML = str;
			
			}  	
	 	}
	);
});

	function detail(id,annualId){
		createdetailwindow('酒店年报详情', 'hotelAnnualController.do?detail&hotelmanageId='+id+'&load=detail&annualId='+annualId);
	}
	
	function edit(id,annualId){
		createwindow('编辑财务年报','hotelAnnualController.do?editByUser&annualId='+annualId);
	}
	
function doMigrateOut(title,url,id){
	JeecgExcelExport("hotelAnnualController.do?exportsxlss","userList2");
	}

function back(data){
	if(data.success)
	reloadTable();
}

// 查看审核意见
function countryContent(id){
	createdetailwindow('国家系统审核意见','hotelAnnualController.do?countryContent&id='+ id, 450, 160);
}

</script>
