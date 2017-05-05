<!doctype html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">        
<t:base type="jquery,easyui,tools"></t:base>
        
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts-more.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/modules/exporting.js"></script>           
</head>   

<body>
<div class=""   >
<t:formvalid  formid="formobj" refresh="true" dialog="true" action="scenicWeekController.do?save" usePlugin="password" layout="table">
	<input name="analyzeid"  type="hidden" value="${analyzeid}">
	<table  class="formtable">
		<tbody>
			<div class="MainInfo_tit pdtb10">
            	${analyzedata.name}
            </div>		
            <c:forEach  varStatus="j"   items="${topiclist}"  var="list">
             
            <tr>
				<td width="90px" align="middle"><span class="filedzt">题目${j.index+1}</span></td>
				<td class="value" colspan="2"><span class="filedzt">  ${list.topicName}</span></td>
				<td class="value" width="90px"><a href ="javascript:loadchar('${list.id}');" 
				  title='${list.topicName}' class="englishname" ><font color="blue">统计选项</font></a></td>
			</c:forEach>
		</tbody>
	</table>
	
</t:formvalid>
</div>
<div width="100%" id="container" style=" height:400px;"></div>
<script>
var chart;
function loadchar(id){
$.ajax({
	type : "GET",
	url : "questionNaireController.do?questionCount&answerid="+id,
	success : function(jsondata) {
		
		data = eval(jsondata);
		if(!data[0].data || data[0].data.length < 1) {
			alert("该问卷无填写数据！");
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'container',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false,
					type: 'pie'
				},
				title : {
					text : ''
				}
			});  
			return;
		}
		
		console.log(data);
		
		chart = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false,
				type: 'pie'
			},
			title : {
				text : '选项分析'
			},
	        yAxis: {
	            title: {
	                text: "选项名称"
	            }
	        },
			tooltip : {
				percentageDecimals : 1,
			    formatter: function() {
    				return  '<b>'+this.point.name + '</b>:'+this.point.y;
 				}
			},
			plotOptions : {
				column : {
					allowPointSelect : true,
					cursor : 'pointer',
					showInLegend : true,
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						formatter : function() {
							return '<b>' + this.point.name + ': '+this.point.y;
						}
					}
				}
			},
			series :  data
		});  
	}
});

}

</script>
</body>
</html>