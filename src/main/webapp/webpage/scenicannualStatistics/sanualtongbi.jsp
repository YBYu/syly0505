<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- context path -->
<t:base type="jquery,easyui"></t:base>
<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/highcharts.src.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/modules/exporting.src.js"></script>

<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {
		$(document).ready(function() {
			var chart;
			//var a= ${taId};
			//alert(a);
			//var b=${tfId};
			$.ajax({
				type : "POST",
				url : "sAnnualStatisticsController.do?sanualCount&reportType=column&taId=${taId}&tfId=${tfId}",
				success : function(jsondata) {
					
					data = eval(jsondata);
					console.log(data);
					
					chart = new Highcharts.Chart({
						chart : {
							renderTo : 'containerCol',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						title : {
							text : '景区年报'
						},
						/* xAxis : {
							categories : [ '2015', '2016','2017']
						}, */
				        yAxis: {
				            title: {
				                text: "营业额"
				            }
				        },
						tooltip : {
							 percentageDecimals : 1,
							 formatter: function() {
            					return  '<b>'+this.point.name + '</b>:';
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
										return '<b>' + this.point.name + '</b>: '+this.point.y;
									}
								}
							}
						},
						series : data
					});
				}
			});
		});
	});
</script>
<div id="containerCol" style="width: 80%; height: 80%"></div>
