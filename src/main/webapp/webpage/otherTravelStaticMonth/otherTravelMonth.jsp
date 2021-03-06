<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="plug-in/ace/css/bootstrap.css" />
		<link rel="stylesheet" href="plug-in/ace/css/font-awesome.css" />
		<link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css">
		<!-- text fonts -->
		<link rel="stylesheet" href="plug-in/ace/css/ace-fonts.css" />

		<link rel="stylesheet" href="plug-in/ace/css/jquery-ui.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="plug-in/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
	

		<script src="plug-in/ace/js/ace-extra.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="plug-in/ace/js/html5shiv.js"></script>
		<script src="plug-in/ace/js/respond.js"></script>
		<![endif]-->
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="leaveGridtb" style="padding: 3px; height: auto">
			<div name="searchColums">
				 <input  id="_sqlbuilder" name="sqlbuilder" type="hidden" /><span
					style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="地区"></span>  <select hidden="true" id="area" name="area" WIDTH="100"
					style="width: 104px">
						<option value="">------请选择------</option>
						<option value="0">市辖区</option>
						<option value="1">吉阳区</option>
						<option value="2">崖州区</option>
						<option value="3">天涯区</option>
						<option value="4">海棠区</option>
				</select></span>  <span style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="年份">年份：</span><select  id="year" name="year" WIDTH="100"
					style="width: 104px">
					    <option value="">---请选择---</option>
					    <option value="2020-">2020年</option>
					    <option value="2019-">2017年</option>
					    <option value="2018-">2017年</option>
						<option value="2017-">2017年</option>
						<option value="2016-">2016年</option>
						<option value="2015-">2015年</option>
						<option value="2014-">2014年</option>
					
					<%-- <c:forEach var="item"  items="${yearList}" varStatus="status">
					
					<option value="${item}">${item}年</option>
					
					</c:forEach> --%>
					</select>
					<!-- </span><input class="inuptxt" id="year"
					 type="text" /> --></span><span
					style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="季度">月份：</span><select  id="month" name="month" WIDTH="100"
					style="width: 104px">
						<option value="">---请选择---</option>
						<option value="01">一月</option>
						<option value="02">二月</option>
						<option value="03">三月</option>
						<option value="04">四月</option>
						<option value="05">五月</option>
						<option value="06">六月</option>
						<option value="07">七月</option>
						<option value="08">八月</option>
						<option value="09">九月</option>
						<option value="10">十月</option>
						<option value="11">十一</option>
						<option value="12">十二月</option>
				</select></span>
			</div>
			<div style="height: 30px;" class="datagrid-toolbar">
				<span style="float: left;"></span><span style="float: right"><a
					href="#" class="button" iconCls="icon-search"
					onclick="eeeeeGridsearch()">查询</a><a href="#" class="button"					
					iconCls="icon-reload" onclick="searchReset('leaveGrid')">重置</a></span>
			</div>
			</div>
<div>
		
		<table class="ui_border" border="1" cellspacing="1" cellpadding="0" style="height: 37px;width:650px">
		<caption>统计报表</caption>
		<tr>
			<td ><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">编号</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">统计报表</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">说明</div></td>
			</tr>
<!-- 			<tr>
				<th></th>
				<th></th>
				<th></th>
			</tr> -->		
			<tr>
			<td ><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">1</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">高尔夫月报统计</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn1" href="#"  onclick="daochu()" >点击报名表下载</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">2</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">游艇月报统计</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu1()" >点击报名表下载</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">3</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">动车月报统计</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu2()" >点击报名表下载</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">4</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">空中飞行月报统计</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu3()" >点击报名表下载</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">5</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">机场月报统计</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu4()" >点击报名表下载</a></div></td>
			</tr>
			<!-- <tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">4</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">星级非星级住宿接待情况</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu3()" >点击报名表下载</a></div></td>
			</tr> --> 
		
	</table>
		</div>
		</div>
		
		
	</div>

	<script type="text/javascript">
	//旅游饭店接待外国人分国别（地区）人数
	function daochu(){
		var area=$('#area')[0].value;
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		window.open("exportotherTravelController.do?excelExport1&year="+year+"&month="+month);
		
	}
	
	function daochu1(){
		var area=$('#area')[0].value;
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		window.open("exportotherTravelController.do?excelExport2&year="+year+"&month="+month);
		
	}
	
	function daochu2(){
		var area=$('#area')[0].value;
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		window.open("exportotherTravelController.do?excelExport3&year="+year+"&month="+month);
		
	}
	
	function daochu3(){
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		window.open("exportotherTravelController.do?excelExport4&year="+year+"&month="+month);
		
	}
	
	function daochu4(){
		var area=$('#area')[0].value;
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		window.open("exportotherTravelController.do?excelExport5&year="+year+"&month="+month);
		
	}
	
		
		// 表格刷新方法
		function reloadTable() {
			try {
				$("#leaveGrid").datagrid("reload");
			} catch (e) {

			}
		}

	</script>