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
					title="地区">地区：</span>  <select id="area" name="area" WIDTH="100"
					style="width: 104px">
						<option value="">------请选择------</option>
						<option value="0">市辖区</option>
						<option value="1">吉阳区</option>
						<option value="2">崖州区</option>
						<option value="3">天涯区</option>
						<option value="4">海棠区</option>
				</select>< </span>  <span style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="年份">年份：</span><input class="inuptxt" id="year"
					 type="text" /> </span><span
					style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="季度">季度：</span><select id="quarter" name="quarter" WIDTH="100"
					style="width: 104px">
						<option value="">---请选择---</option>
						<option value="1">一月</option>
						<option value="2">二月</option>
						<option value="3">三月</option>
						<option value="4">四月</option>
						<option value="5">五月</option>
						<option value="6">六月</option>
						<option value="7">七月</option>
						<option value="8">八月</option>
						<option value="9">九月</option>
						<option value="10">十月</option>
						<option value="11">十一月</option>
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
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">旅行社年度财务状况</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn1" href="#"  onclick="daochu()" >点击报名表下载</a></div></td>
			</tr>
			<!-- <tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">2</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">旅行社国内游接待人天情况表(按旅行社)</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu1()" >点击报名表下载</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">3</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">旅行社国内游组织人次情况表(按旅行社)</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu2()" >点击报名表下载</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">4</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">旅行社国内游组织人天情况表(按旅行社)</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu3()" >点击报名表下载</a></div></td>
			</tr> -->
			<!-- <tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">5</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">旅行社国内游组织人天情况表(按旅行社)</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu3()" >点击报名表下载</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">6</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">组织人天和接待人天之和排序统计（按旅行社）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu3()" >点击报名表下载</a></div></td>
			</tr> -->
		
	</table>
		</div>
		</div>
		
		
	</div>

	<script type="text/javascript">
	//旅行社国内游接待人次情况表(按旅行社)
	function daochu(){
		var area=$('#area')[0].value;
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		//var quarter=$('#quarter')[0].value;
		   window.open("statisticTravelAnnualController.do?exportsxls1&year="+year);
		
	}
	//旅行社国内游接待人天情况表(按旅行社)
	function daochu1(){
		var area=$('#area')[0].value;
		
		
		var year=$('#year')[0].value; 
		var quarter=$('#quarter')[0].value;
		   window.open("inlandStatisticTravelquarterController.do?exportsxls2&year="+year+"&quarter="+quarter);
		
	}
	//旅行社国内游组织人次情况表(按旅行社)
	function daochu2(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		var quarter=$('#quarter')[0].value;
		   window.open("inlandStatisticTravelquarterController.do?exportsxls3&year="+year+"&quarter="+quarter);
		
	}
	//旅行社国内游组织人天情况表(按旅行社)
	function daochu3(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		var quarter=$('#quarter')[0].value;
		   window.open("inlandStatisticTravelquarterController.do?exportsxls4&year="+year+"&quarter="+quarter);
		
	}
	
		// 表格刷新方法
		function reloadTable() {
			try {
				$("#leaveGrid").datagrid("reload");
			} catch (e) {

			}
		}

	</script>