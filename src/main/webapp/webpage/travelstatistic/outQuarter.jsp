<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="plug-in/ace/css/bootstrap.css" />
<link rel="stylesheet" href="plug-in/ace/css/font-awesome.css" />
<link rel="stylesheet" type="text/css"
	href="plug-in/accordion/css/accordion.css">
<!-- text fonts -->
<link rel="stylesheet" href="plug-in/ace/css/ace-fonts.css" />

<link rel="stylesheet" href="plug-in/ace/css/jquery-ui.css" />
<!-- ace styles -->
<link rel="stylesheet" href="plug-in/ace/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />


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
				<input id="_sqlbuilder" name="sqlbuilder" type="hidden" />
				<span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="年份">年份：</span>
				<select id="startYear" name="year" WIDTH="100" style="width: 104px">
					<c:forEach var="item" items="${yearList}" varStatus="status">
						<option value="${item}">${item}年</option>
					</c:forEach>
				</select>
				<span style="display: -moz-inline-box; display: inline-block;">
					<span
						style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
						title="季度">季度：</span>
					<select id="startQuarter" name="quarter" WIDTH="100"
						style="width: 104px">
						<option value="1">第一季度</option>
						<option value="2">第二季度</option>
						<option value="3">第三季度</option>
						<option value="4">第四季度</option>
					</select>
				</span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至</span>
				<span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="年份">年份：</span>
				<select id="endYear" name="year" WIDTH="100" style="width: 104px">
					<c:forEach var="item" items="${yearList}" varStatus="status">
						<option value="${item}">${item}年</option>
					</c:forEach>
				</select>
				<span style="display: -moz-inline-box; display: inline-block;">
					<span
						style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
						title="季度">季度：</span>
					<select id="endQuarter" name="quarter" WIDTH="100"
						style="width: 104px">
						<option value="1">第一季度</option>
						<option value="2">第二季度</option>
						<option value="3">第三季度</option>
						<option value="4">第四季度</option>
					</select>
				</span>
			</div>
			<div style="height: 30px; display: none;" class="datagrid-toolbar">
				<span style="float: left;"></span>
				<span style="float: right">
					<button class="autocompare">
						<a href="#" class="button" iconCls="icon-search"
							onclick="eeeeeGridsearch()">查询</a>
					</button>
					<button class="autocompare">
						<a href="#" class="button" iconCls="icon-reload"
							onclick="searchReset('leaveGrid')">重置</a>
					</button>
				</span>
			</div>
		</div>

		<table class="ui_border" border="1" cellspacing="1" cellpadding="0"
			style="height: 37px; width: 650px">
			<caption>统计报表</caption>
			<tr>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">编号</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">统计报表</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">说明</div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">1</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">旅行社组织出境旅游情况汇总统计</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">
						<a id="btn1" href="#" onclick="leaveCollection()">点击下载报表</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">2</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">出境旅游目的地国家人次统计表（按地区）</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">
						<a id="btn2" href="#" onclick="destinationPeopleTimesByArea()">点击下载报表</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">3</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">出境旅游目的地国家人次统计表（按旅行社）</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">
						<a id="btn2" href="#"
							onclick="destinationPeopleTimesByTravelagency()">点击下载报表</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">4</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">出境旅游目的地国家人天统计表（按地区）</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">
						<a id="btn2" href="#" onclick="destinationPeopleDayByArea()">点击下载报表</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">5</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">出境旅游目的地国家人天统计表（按旅行社）</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">
						<a id="btn2" href="#"
							onclick="destinationPeopleDayByTravelagency()">点击下载报表</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">6</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">旅行社出境游各洲人次统计</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">
						<a id="btn2" href="#" onclick="peopleTimesByRegion()">点击下载报表</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">7</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">出境旅游大洲人次统计表（按旅行社）</div>
				</td>
				<td>
					<div style="height: auto;"
						class="datagrid-cell datagrid-cell-c1-name">
						<a id="btn2" href="#" onclick="peopleTimesByTravelagency()">点击下载报表</a>
					</div>
				</td>
			</tr>

		</table>
	</div>
</div>


</div>

<script type="text/javascript">
	
	// 旅行社组织出境旅游情况汇总统计
	function leaveCollection(){
		var startYear = $('#startYear')[0].value;
		var startQuarter = $('#startQuarter')[0].value;
		var endYear = $('#endYear')[0].value;
		var endQuarter = $('#endQuarter')[0].value;
		
		window.open("statisticTravelquarterController.do?leaveCollection&startYear="
				+ startYear + "&startQuarter=" + startQuarter + "&endYear=" + endYear + "&endQuarter=" + endQuarter);
	}
	
	// 出境旅游目的地国家人次统计表（按地区）
	function destinationPeopleTimesByArea(){
		var startYear = $('#startYear')[0].value;
		var startQuarter = $('#startQuarter')[0].value;
		var endYear = $('#endYear')[0].value;
		var endQuarter = $('#endQuarter')[0].value;
		
		window.open("statisticTravelquarterController.do?destinationPeopleTimesByArea&startYear="
				+ startYear + "&startQuarter=" + startQuarter + "&endYear=" + endYear + "&endQuarter=" + endQuarter);
	}
	
	// 出境旅游目的地国家人次统计表（按旅行社）
	function destinationPeopleTimesByTravelagency(){
		var startYear = $('#startYear')[0].value;
		var startQuarter = $('#startQuarter')[0].value;
		var endYear = $('#endYear')[0].value;
		var endQuarter = $('#endQuarter')[0].value;
		
		window.open("statisticTravelquarterController.do?destinationPeopleTimesByTravelagency&startYear="
				+ startYear + "&startQuarter=" + startQuarter + "&endYear=" + endYear + "&endQuarter=" + endQuarter);
	}
	
	// 出境旅游目的地国家人天统计表（按地区）
	function destinationPeopleDayByArea(){
		var startYear = $('#startYear')[0].value;
		var startQuarter = $('#startQuarter')[0].value;
		var endYear = $('#endYear')[0].value;
		var endQuarter = $('#endQuarter')[0].value;
		
		window.open("statisticTravelquarterController.do?destinationPeopleDayByArea&startYear="
				+ startYear + "&startQuarter=" + startQuarter + "&endYear=" + endYear + "&endQuarter=" + endQuarter);
	}
	
	// 出境旅游目的地国家人天统计表（按旅行社）
	function destinationPeopleDayByTravelagency(){
		var startYear = $('#startYear')[0].value;
		var startQuarter = $('#startQuarter')[0].value;
		var endYear = $('#endYear')[0].value;
		var endQuarter = $('#endQuarter')[0].value;
		
		window.open("statisticTravelquarterController.do?destinationPeopleDayByTravelagency&startYear="
				+ startYear + "&startQuarter=" + startQuarter + "&endYear=" + endYear + "&endQuarter=" + endQuarter);
	}
	
	// 旅行社出境游各洲人次统计
	function peopleTimesByRegion(){
		var startYear = $('#startYear')[0].value;
		var startQuarter = $('#startQuarter')[0].value;
		var endYear = $('#endYear')[0].value;
		var endQuarter = $('#endQuarter')[0].value;
		
		window.open("statisticTravelquarterController.do?peopleTimesByRegion&startYear="
				+ startYear + "&startQuarter=" + startQuarter + "&endYear=" + endYear + "&endQuarter=" + endQuarter);
	}
	
	// 出境旅游大洲人次统计表（按旅行社）
	function peopleTimesByTravelagency(){
		var startYear = $('#startYear')[0].value;
		var startQuarter = $('#startQuarter')[0].value;
		var endYear = $('#endYear')[0].value;
		var endQuarter = $('#endQuarter')[0].value;
		
		window.open("statisticTravelquarterController.do?peopleTimesByTravelagency&startYear="
				+ startYear + "&startQuarter=" + startQuarter + "&endYear=" + endYear + "&endQuarter=" + endQuarter);
	}
	
	
	//导出出境旅游大洲人次统计表（按旅行社）
	function daochu(){
		var area=$('#area')[0].value;
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		//alert(year);
		var quarter=$('#quarter')[0].value;
		   window.open("statisticTravelquarterController.do?exportsxls1&year="+year+"&quarter="+quarter);
		
	}
	//出境旅游目的地国家人次统计表(按旅行社 单位人次)
	function daochu1(){
		var area=$('#area')[0].value;
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		//alert(year);
		var quarter=$('#quarter')[0].value;
		   window.open("statisticTravelquarterController.do?exportsxls2&year="+year+"&quarter="+quarter);
		
	}
	//
	function daochu2(){
		var area=$('#area')[0].value;
		
		//alert(area);
		
		var year=$('#year')[0].value; 
		//alert(year);
		var quarter=$('#quarter')[0].value;
		   window.open("statisticTravelquarterController.do?exportsxls3&year="+year+"&quarter="+quarter);
		
	}
	//出境旅游各洲人次统计
	function daochu3(){
		
		
		var year=$('#year')[0].value; 
		//alert(year);
		var quarter=$('#quarter')[0].value;
		   window.open("statisticTravelquarterController.do?exportsxls4&year="+year+"&quarter="+quarter);
		
	}

	//出境旅游人次倒序排列（按目的地国家）
	function daochu4(){
		var year=$('#year')[0].value; 
		var quarter=$('#quarter')[0].value;
		   window.open("statisticTravelquarterController.do?exportsxls5&year="+year+"&quarter="+quarter);
		
	}
	//出境旅游人天统计（按目的地国家）
	function daochu5(){
		var year=$('#year')[0].value; 
		var quarter=$('#quarter')[0].value;
		   window.open("statisticTravelquarterController.do?exportsxls6&year="+year+"&quarter="+quarter);
		
	}
	
		// 表格刷新方法
		function reloadTable() {
			try {
				$("#leaveGrid").datagrid("reload");
			} catch (e) {

			}
		}

	</script>