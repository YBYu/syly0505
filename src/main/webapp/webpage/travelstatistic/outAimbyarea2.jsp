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

<!--[if lte IE 9]>
			<link rel="stylesheet" href="plug-in/ace/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="plug-in/ace/css/ace-ie.css" />
		<![endif]-->
<!-- ace settings handler -->
<script src="plug-in/ace/js/ace-extra.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="plug-in/ace/js/html5shiv.js"></script>
		<script src="plug-in/ace/js/respond.js"></script>
		<![endif]-->
<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
	<ul style="height: 0px"
		class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"
		role="tablist">
		<li
			class="ui-state-default ui-corner-top ui-tabs-active ui-state-active"
			role="tab" tabindex="0" aria-controls="tabs-1"
			aria-labelledby="mainTitle" aria-selected="true" aria-expanded="true">
			<a href="#tabs-1" id="mainTitle" class="ui-tabs-anchor"
				role="presentation" tabindex="-1">首页</a>
		</li>

		<li class="ui-state-default ui-corner-top ui-tabs ui-state-active"
			role="tab" tabindex="0" aria-controls="tabs-2"
			aria-labelledby="mainTitle" aria-selected="true" aria-expanded="true">
			<a href="#tabs-2" id="mainTitle" class="ui-tabs-anchor"
				role="presentation" tabindex="-1">首33333页</a>
		</li>
	</ul>
	<div class="tab-content">
		<div id="tabs-1" style="padding: 0px" aria-labelledby="mainTitle"
			class="ui-tabs-panel ui-widget-content ui-corner-bottom"
			role="tabpanel" aria-hidden="false">
			<iframe style="width: 100%; height: 530px; margin: 0px; padding: 0px"
				scrolling="auto" frameborder="0" id="center"
				src="loginController.do?home"></iframe>
		</div>
		<div id="tabs-2" style="padding: 0px" aria-labelledby="mainTitle"
			class="ui-tabs-panel ui-widget-content ui-corner-bottom"
			role="tabpanel" aria-hidden="false">
			<iframe style="width: 100%; height: 530px; margin: 0px; padding: 0px"
				scrolling="auto" frameborder="0" id="center"
				src="statisticTravelquarterController.do?toOutAimlistPage"></iframe>
		</div>
	</div>
</div>

<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<div id="leaveGridtb" style="padding: 3px; height: auto">
			<div name="searchColums">
				<input id="_sqlbuilder" name="sqlbuilder" type="hidden" />
				<span style="display: -moz-inline-box; display: inline-block;">
					<span
						style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
						title="地区">地区：</span>
					<select name="area" WIDTH="100" style="width: 104px">
						<option value="">------请选择------</option>
						<option value="0">市辖区</option>
						<option value="1">吉阳区</option>
						<option value="2">崖州区</option>
						<option value="3">天涯区</option>
						<option value="4">海棠区</option>
					</select>
				</span>
				<span style="display: -moz-inline-box; display: inline-block;">
					<span
						style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
						title="年份">年份：</span>
					<input class="inuptxt" onkeypress="EnterPress(event)"
						onkeydown="EnterPress()" type="text" name="year" />
				</span>
				<span style="display: -moz-inline-box; display: inline-block;">
					<span
						style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
						title="季度">季度：</span>
					<select name="quarter" WIDTH="100" style="width: 104px">
						<option value="">---请选择---</option>
						<option value="1">第一季度</option>
						<option value="2">第二季度</option>
						<option value="3">第三季度</option>
						<option value="4">第四季度</option>
						<option value="4">第四季度</option>
					</select>
				</span>
			</div>
			<div style="height: 30px;" class="datagrid-toolbar">
				<span style="float: left;"></span>
				<span style="float: right">
					<a href="#" class="button" iconCls="icon-search"
						onclick="eeeeeGridsearch()">查询</a>
					<a href="#" class="button" iconCls="icon-reload"
						onclick="searchReset('leaveGrid')">重置</a>
				</span>
			</div>
		</div>

		<iframe id="aimIframe"
			style="width: 100%; height: 530px; margin: 0px; padding: 0px"
			scrolling="auto" frameborder="0" id="center"
			src="statisticTravelquarterController.do?toOutAimlistPage"></iframe>



	</div>
</div>

<script type="text/javascript">
	var eeeeeGridsearch=function(){
		$("#aimIframe").attr('src','statisticTravelquarterController.do?toOutAimlistPage1');
	}
		// 表格刷新方法
		function reloadTable() {
			try {
				$("#leaveGrid").datagrid("reload");
			} catch (e) {

			}
		}

	</script>