<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>

<%-- 
        <script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>

        <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>

        <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
--%>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts-more.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/modules/exporting.js"></script>   

</head>
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicWeekController.do?save" usePlugin="password" layout="table">
	<input name="scenicdataid"  type="hidden" value="${scenicdataid}">
		<input name="weekid" type="hidden"  value="${weekid}">
	
	<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
				<tr>
				<td align="right" width="16%">年份</td>
               	 <td class="value" width="16%" colspan="2"><input type="text" readonly="readonly" value="${weekdata.year}"  name="year"  /><span class="red"></span></td>
					<td align="right" width="16%">周期</td>
                <td class="value" width="30%"><input type="text" value="${weekdata.cycle}" name="cycle" readonly="readonly"></td>
                </tr>
                <tr>
				<%-- 	
				</tr>
        	<tr>
            	<td align="right" width="16%">周期</td>
                <td class="value" width="16%" colspan="2"><input type="text" value=""  name="cycle" datatype=n  errormsg="周期只能为数字"/><span class="red"></span></td> --%>
                <td align="right" width="16%">单位名称 </td>
                <td class="value" width="30%" colspan="4"><input type="text" value="${scenicdata.name}" name="accountNum" class="accountNum" errormsg="请输入账号"/></td>
            </tr>
            <!--单位名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">营业收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${weekdata.taking}" name="taking" class="companyname" datatype="num"   errormsg="只能输入数字" /><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">其中门票收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${weekdata.ticket }" name="ticket" class="password" datatype="num" errormsg="只能输入数字"/></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人数（人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${weekdata.receptionnum }" name="receptionnum" class="englishname" datatype=n errormsg="只能为整数" /></td>
                <td align="right" width="16%"><span class="filedzt">其中境外人数（人次）</span></td>
                <td class="value" width="30%"><input type="text" value="${weekdata.overnum }" name="overnum" class="ogCode" datatype=n errormsg="只能为整数"/></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">团散比</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${weekdata.compare }" name="compare" class="creditCode" errormsg="统一社会信用代码"/></td>
                <td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
                <td class="value" width="30%"><input type="text" value="${weekdata.principal }" name="principal" class="ogCode" errormsg="请输入组织机构代码"/></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">填表人</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${weekdata.preparer }" name="preparer" class="deligate" errormsg="请输入法人代表" /></td>
                <td align="right" width="16%"><span class="filedzt">电话</span></td>
                <td class="value" width="30%"><input type="text" value="${weekdata.telephone }" name="telephone" class="adCode" datatype="m" errormsg="请输入正确的电话"/></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">报出日期</span></td>
				<td class="value" width="16%" colspan="2"><input   type="text"  name="reportdate" class="Wdate"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
                <td align="right" width="16%"></td>
                <td class="value" width="30%"></td>
			</tr>
            <!--网址、邮政编码-->
            
		
            
		</tbody>
	</table>
	<table class="input">
			<tbody>
				<tr>
					<th>&nbsp;</th>
					<td><input id="container" type="button" class="button" value="确&nbsp;&nbsp;定">
						<input type="button" class="button" value="返&nbsp;&nbsp;回"
							onclick="history.go(-1)">
					</td>
				</tr>
			</tbody>
		</table>  
	
</t:formvalid>
<div id="container" style="min-width:100px;height:100px">饼形图</div>	
</body>
<script type="text/javascript">

$(function () {
    Highcharts.chart('container', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Browser market shares January, 2015 to May, 2015'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [{
                name: 'Microsoft Internet Explorer',
                y: 56.33
            }, {
                name: 'Chrome',
                y: 24.03,
                sliced: true,
                selected: true
            }, {
                name: 'Firefox',
                y: 10.38
            }, {
                name: 'Safari',
                y: 4.77
            }, {
                name: 'Opera',
                y: 0.91
            }, {
                name: 'Proprietary or Undetectable',
                y: 0.2
            }]
        }]
    });
});




	
	

</script>


