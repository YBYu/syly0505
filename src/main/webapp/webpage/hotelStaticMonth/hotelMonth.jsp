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
				 <input  id="_sqlbuilder" name="sqlbuilder" type="hidden" />
				 <span
					style="display: -moz-inline-box; display: inline-block;">
					<span style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="年份">年份：</span><select  id="year" name="year" WIDTH="100"
					style="width: 104px">
					<c:forEach var="item"  items="${yearList}" varStatus="status">
					
					<option value="${item}">${item}年</option>
					
					</c:forEach>
					</select>
					</span>
					<span
					style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="季度">月份：</span><select  id="month" name="month" WIDTH="100"
					style="width: 104px">
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
						<option value="11">十一</option>
						<option value="12">十二月</option>
				</select></span>
					<span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="地区">地区:</span>  
					<select   id="area" name="area"  style="width: 104px">
						<option value="">----请选择----</option>
						<option value="0">市辖区</option>
						<option value="1">崖州区</option>
						<option value="2">海棠区</option>
						<option value="3">天涯区</option>
						<option value="4">吉阳区</option>
				</select></span>  
				
				<span
					style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="湾区">湾区:</span>  <select  id="bay" name="bay"   
					style="width: 104px">
						<option value="">----请选择----</option>
						<option value="0">市区</option>
						<option value="1">亚龙湾</option>
						<option value="2">大东海</option>
						<option value="3">三亚湾</option>
						<option value="4">海棠湾</option>
				</select></span>
				<span
					style="display: -moz-inline-box; display: inline-block;"><span
					style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
					title="星级范围">星级范围:</span>  <select id="star" name="star" WIDTH="100"
					style="width: 104px">
						<option value="">----请选择----</option>
						<option value="4">一星</option>
						<option value="5">二星</option>
						<option value="6">三星</option>
						<option value="7">四星</option>
						<option value="8">五星</option>
				</select></span>
			</div>
			<div style="height: 30px;" hidden="true" class="datagrid-toolbar">
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
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">接待入境过夜游客分国别情况</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn1" href="#"  onclick="jieDaiRujingByCountry()" >点击下载报表</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">2</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">地方接待过夜游客情况汇总</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="totalJiedaiByArea()" >点击下载报表</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">3</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">分地区接待入境过夜游客分国别情况汇总</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="jiedaiByCountryAndArea()" >点击下载报表</a></div></td>
			</tr>
			 <tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">4</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">旅游饭店接待外国人分国别(地区)人数</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="foreignsJiedaiPeopleTimes()" >点击下载报表</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">5</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">星级非星级住宿接待情况</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="allHotelJiedai()" >点击下载报表</a></div></td>
			</tr>
			<tr>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">6</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name">(三亚市)房间出租率汇总
</div></td>
				<td><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-name"><a id="btn2" href="#" onclick="occupancyRate()" >点击下载报表</a></div></td>
			</tr>
		
	</table>
		</div>
		</div>
		
		
	</div>

	<script type="text/javascript">
	
	window.onload = function(){
		$("#month")[0].value = (new Date()).getMonth()+1;
	}
	
	// 接待入境过夜游客分国别情况
	function jieDaiRujingByCountry(){
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		window.open("statisticHotelMonthController.do?jieDaiRujingByCountry&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
	}

	// 地方接待过夜游客情况汇总
	function totalJiedaiByArea(){
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		window.open("statisticHotelMonthController.do?totalJiedaiByArea&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
	}
	
	// 分地区接待入境过夜游客分国别情况汇总
	function jiedaiByCountryAndArea(){
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		window.open("statisticHotelMonthController.do?jiedaiByCountryAndArea&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
	}
	
	// 旅游饭店接待外国人分国别(地区)人数
	function foreignsJiedaiPeopleTimes(){
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		window.open("statisticHotelMonthController.do?foreignsJiedaiPeopleTimes&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
	}
	
	// 星级非星级住宿接待情况
	function allHotelJiedai(){
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		window.open("statisticHotelMonthController.do?allHotelJiedai&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
	}
	
	//(三亚市)房间出租率汇总
	function occupancyRate(){
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		window.open("statisticHotelMonthController.do?occupancyRate&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
	}
	
	//旅游饭店接待外国人分国别（地区）人数
	function daochu(){
		//var area=$('#area')[0].value;
		
		
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		window.open("hotelexportcontroller.do?foreignersReception&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
		
	}
	//地方接待过夜游客情况汇总
	function daochu2(){
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		  window.open("hotelexportcontroller.do?overnightCollect&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
		
	}
	//星级非星级住宿接待情况
	function daochu3(){
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		   window.open("hotelexportcontroller.do?exportsxls4&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
		
	} 
	//接待入境过夜游客分国别情况
	function daochu4(){
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		  window.open("hotelexportcontroller.do?exportsxls5&year="+year+"&bay="+bay+"&star="+star+"&month="+month+"&area="+area);
		
	} 
	//分地区
	function daochu5(){
		var area=$('#area')[0].value;
		var bay=$('#bay')[0].value;
		var star=$('#star')[0].value;
		var year=$('#year')[0].value; 
		var month=$('#month')[0].value;
		   window.open("hotelexportcontroller.do?exportsxls6&year="+year+"&bay="+bay+"&star="+star+"&month="+month);
		
	} 
	
		// 表格刷新方法
		function reloadTable() {
			try {
				$("#leaveGrid").datagrid("reload");
			} catch (e) {

			}
		}

	</script>