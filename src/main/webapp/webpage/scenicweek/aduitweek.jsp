<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="plug-in/style/zhoubao/style.css">
<link rel="stylesheet" type="text/css" href="plug-in/style/zhoubao/laydate.css">
<link rel="stylesheet" type="text/css" href="plug-in/style/js/laydate.css">
<link rel="stylesheet" type="text/css" href="plug-in/style/laydate-default/laydate.css">  

<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" refresh="true" dialog="true"
		action="scenicWeekController.do?save" usePlugin="password"
		layout="table">
		<input id="id" type="hidden" value="${scenicSpotWeek.id }">
		<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

		<div class="container">
    	<div class="MainInfo_con">
        		  <div class="MainInfo_tit mgb20" style="margin-bottom: 0;margin-top: 20px">
            	${scenicdata.name}-周报数据
            </div>
            	<!--经营情况月报填报-->
            	<div class="item item1 mgb20">
                	<span class="on">经营情况周报填报</span>
                    <div class="panel-body ItemCon">
                    	<table style="width:650px">
                        	<tr>
                            	<td colspan="2">
                                	<span>周期：</span>
                                    <input type="text" id="cycle" name="cycle" value="${scenicweek.cycle}" style="width:10%">
                                    <lable class="gray">即第几周</lable>	
                                </td>
                            </tr>
                            <tr>
                            	<td colspan="2"><span style="width:64px;">单位名称:</span><input type="text" name="Copname" value="${scenicdata.name}" disabled="disabled" id="Copname" class="text" style="width:60%; margin-right:10px;"><input type="button"  class="btn" value="选择企业"></td>
                            </tr>
                            <tr>
                            	<td><span style="width:135px;">营业收入（万元）：</span><input type="text" name="income" value="${scenicweek.taking}" disabled="disabled" id="income" class="text" style="width:158px"></td>
                                <td><span style="width:165px;">其中门票收入（万元）：</span><input type="text" name="TicIncome" value="${scenicweek.ticket}" disabled="disabled" id="TicIncome" class="text" style="width:128px"></td>
                            </tr>
                            <tr>
                            	<td><span style="width:135px;">接待人数（人次）：</span><input type="text" name="RecepNum" value="${scenicweek.receptionnum}" disabled="disabled" id="RecepNum" class="text" style="width:158px"></td>
                                <td><span style="width:165px;">其中境外人数（人次）：</span><input type="text" name="ExitNum" value="${scenicweek.overnum}" disabled="disabled" id="ExitNum" class="text" style="width:128px"></td>
                            </tr>
                            <tr>
                            	<td><span style="width:49px;">团散比:</span><input type="text" name="GroupRate" value="${scenicweek.compare}" disabled="disabled" id="GroupRate" class="text" style="width:244px"></td>
                                <td></td>
                            </tr>
                            <tr>
                            	<td><span style="width:90px;">单位负责人：</span><input type="text" name="PerCharge" value="${scenicweek.principal}" disabled="disabled"  id="PerCharge" class="text" style="width:203px"></td>
                                <td><span style="width:60px;">填表人：</span><input type="text" name="FillTable" value="${scenicweek.preparer}" id="FillTable" class="text" style="width:233px"></td>
                            </tr>
                            <tr>
                            	<td><span style="width:45px;">电话：</span><input type="text" name="tel" value="${scenicweek.telephone}" disabled="disabled" id="tel" class="text" style="width:248px"></td>
                                <td><span style="width:75px;">报出日期：</span><input type="text" name="SubDate" value="${scenicweek.reportdate}" id="SubDate" class="Wdate" onclick="WdatePicker()" style="width:218px"></td>
                            </tr> 
                        </table>
                    </div>
                </div>
             </div>	
        </div>
	</t:formvalid>
</body>