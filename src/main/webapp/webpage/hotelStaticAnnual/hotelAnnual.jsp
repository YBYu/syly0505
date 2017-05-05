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
					title="地区"> </span>  <select  hidden="true" id="area" name="area" WIDTH="100"
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
					<c:forEach var="item"  items="${yearList}" varStatus="status">
					
					<option value="${item}">${item}年</option>
					
					</c:forEach>
					</select>
			</div>
			<div hidden="true" style="height: 30px;" class="datagrid-toolbar">
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
			<tr>
			<td ><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">1</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">财务状况表汇总（按地区）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn1" href="#"  onclick="daochu1()" >点击下载报表</a></div></td>
			</tr>
			 <tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">2</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">财务状况表汇总-1（按星级饭店）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu2()" >点击下载报表</a></div></td>
			</tr>
		 	<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">3</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">财务状况表汇总-2（按星级饭店）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu3()" >点击下载报表</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">4</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">财务状况汇总表（分星级）（excel表)</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu4()" >点击下载报表</a></div></td>
			</tr> 
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">5</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">各地饭店数量汇总（分星级）（excel表）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu5()" >点击下载报表</a></div></td>
			</tr> 
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">6</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">基本经济指标汇总表(excel表)</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu6()" >点击下载报表</a></div></td>
			</tr> 
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">7</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">基本情况表汇总（按星级饭店）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu7()" >点击下载报表</a></div></td>
			</tr> 
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">8</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">基本情况汇总表（分星级）（excel表）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu8()" >点击下载报表</a></div></td>
			</tr> 
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">9</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">年报数据排序（excel表）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu9()" >点击下载报表</a></div></td>
			</tr> 
			<!-- <tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">10</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">财务状况汇总表（分星级）（excel表)</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu10()" >点击下载报表</a></div></td>
			</tr>  -->
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">10</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">主要经济指标汇总表（excel表）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu11()" >点击下载报表</a></div></td>
			</tr> 
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">11</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">星级饭店重要指标同比统计（excel表）</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="daochu12()" >点击下载报表</a></div></td>
			</tr> 
		
	</table>
		</div>
		</div>
		
		
	</div>

	<script type="text/javascript">
	//财务状况表汇总（按地区）
	function daochu1(){
		var area=$('#area')[0].value;
		
		
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport1&year="+year+"&area="+area);	
	}
	//地方接待过夜游客情况汇总
	function daochu2(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport2&year="+year+"&area="+area);	
	}
	//综合统计
	function daochu3(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport3&year="+year+"&area="+area);	
	} 
	function daochu4(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport4&year="+year);	
	} 
	function daochu5(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport5&year="+year+"&area="+area);	
	} 
	function daochu6(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport6&year="+year+"&area="+area);	
	} 
	function daochu7(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport7&year="+year+"&area="+area);	
	} 
	function daochu8(){
		//var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport8&year="+year);	
	} 
	function daochu9(){
		var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport9&year="+year+"&area="+area);	
	} 
	function daochu10(){
		//var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport10&year="+year);	
	} 
	function daochu11(){
		//var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport11&year="+year);	
	} 
	function daochu12(){
		//var area=$('#area')[0].value;				
		var year=$('#year')[0].value; 
		window.open("exportAnnualController.do?excelExport12&year="+year);	
	} 
	
	
		// 表格刷新方法
		function reloadTable() {
			try {
				$("#leaveGrid").datagrid("reload");
			} catch (e) {

			}
		}

	</script>