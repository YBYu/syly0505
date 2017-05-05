<!doctype html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>

        <meta charset="utf-8">
        
<t:base type="jquery,easyui,tools"></t:base>
        <style>



        </style>
        
        
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/highcharts-more.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-5.0.6/modules/exporting.js"></script>           

</head>   

    <body>

    <t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicWeekController.do?save" usePlugin="password" layout="table">
	<input id="answerid"  type="hidden" value="${answerid}">
	
	<table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
				
            <c:forEach  varStatus="j"   items="${topiclist}"  var="list">
              
             
            <tr>
				<td align="right" width="16%"><span class="filedzt">题目${j.index}</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${list.topicName}" name="topicName" class="englishname"  /></td>
                <!--   
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%"><input type="text" value="" name="" class="topicName" /></td>
			    -->   
			     <td class="value" width="30%"><a href="questionNaireController.do?questionCount&answerid=${list.id}">统计选项</a></td>
			    
			</tr>
                <c:forEach varStatus="i"  items="${list.questionNaireOptionList}"  var="a">
            <tr>
           
				<td align="right" width="16%"><span class="filedzt">选项${i.index}</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${a.optionName}" name="topicName" class="englishname"  /></td>
               <!--  
                <td align="right" width="16%"><span class="filedzt">选项</span></td>
                <td class="value" width="30%"><input type="text" value="${a.optionName}" name="optionName" class="topicName" /></td>
			   --> 
			    <td class="value" width="30%"></td>
			   
			</tr>  
			  </c:forEach>
			</c:forEach>
            <!--统一社会信用代码-->
            <%-- 
            <tr>
				<td align="right" width="16%"><span class="filedzt">团散比</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${analyzedata.name}" name="name" class="creditCode" /></td>
                <td align="right" width="16%"><span class="filedzt">单位负责人</span></td>
                <td class="value" width="30%"><input type="text" value="${analyzedata.name}" name="name" class="ogCode" /></td>
			</tr>
           
            --%>            
		</tbody>
	</table>
	<a id="rr" href="javascript:reloadc();" class="ampare">Addds</a>
	
</t:formvalid>
<div id="container" style="min-width:400px;height:400px;">kkkkkkk</div>
<button id="button" class="autocompare">Add series</button>

<script>

var val = document.getElementById("answerid").value;
function reloadc(){
	var da = chart.series.update({
                date: data
            });
	console.log(da);
}
			var chart;

$(function() {
		$(document).ready(function() {
			//var a= ${taId};
			//alert(a);
			//var b=${tfId};
			$.ajax({
				type : "GET",
				url : "questionNaireController.do?questionCount",
				data:{answerid:$("#answerid").val()},
				success : function(jsondata) {
					
					data = eval(jsondata);
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
						/* xAxis : {
							categories : [ '2015', '2016','2017']
						}, */
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
					/* 	exporting:{ 
			                filename:'column',  
			                url:'${ctxPath}/reportDemoController.do?export'//
			            }, */
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
		});
	}); 


</script>


</body>

</html>
