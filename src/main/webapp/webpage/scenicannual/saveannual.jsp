<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">

<!-- <style type="text/css"> -->

<!-- </style> -->
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="" scroll="">  
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicAnnualController.do?save" usePlugin="password" layout="table">
	<input name="scenicdataid" type="hidden" value="${scenicdataid}">
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

  <div class="container">
    	<div class="MainInfo_con">
        		<div class="MainInfo_tit pdtb10">
            	三亚亚龙湾热带天堂森林公园-2016年-第四季度（10-12月）数据
            	</div>
            	<div class="item mgb20">
                	<span class="on">资产</span>
                    <div class="panel-body ItemCon">
                    	<table width="640px">
                        	<tr>
                            	<td><span style="width:120px;">资产总额（万元）</span><input type="text" id="assetstotal" name="assetstotal" class="text" style="width:173px;"></td>
                                <td></td>
                            </tr>
                        </table>	
                    </div>
                </div>
                <!--经营情况月报填报说明-->
                <div class="item mgb20">
                	<span class="on">经营情况月报填报说明</span>
                    <div class="panel-body ItemCon">
                    	<table width="640px">
                        	<tr>
                            	<td><span style="width:150px;">年度建设投资（万元）</span><input type="text" id="YBuildInvest" name="buildinvest" style="width:143px;"></td>
                                <td><span style="width:90px">拨款（万元）</span><input type="text" id="fund" name="appropriation" style="width:203px;"></td>
                            </tr>
                            <tr>
                            	<td><span style="width:180px">景区内部建设投资（万元）</span><input type="text" name="inbuild" id="interior" style="width:113px"></td>
                                <td><span style="width:90px">贷款（万元）</span><input type="text" name="loan" id="loan" style="width:203px;"></td>
                            </tr>
                            <tr>
                            	<td><span style="width:180px">景区外部建设投资（万元）</span><input type="text" name="outbuild" id="exterior" style="width:113px"></td>
                                <td><span style="width:90px">自筹（万元）</span><input type="text" name="fundsself" id="SelfRaised" style="width:203px;"></td>
                            </tr>
                        </table>
                    </div>
             	</div>
                <div class="item mgb20">
                	<span class="on">收益</span>
                    <div class="panel-body ItemCon">
                    	<table width="640px">
                        	<tr>
                            	<td><span style="width:165px;">景区经营总收入（万元）</span><input type="text" id="SmagIncome" name="totalincome" style="width:128px;"></td>
                                <td></td>
                            </tr>
                        </table>
                    	<table width="640px" class="wtable mgb10" style="border-top:1px solid #d3d3d3; border-right:1px solid #d3d3d3;">
                        	<tr>
                            	<td>门票收入（万元）</td>
                                <td><span class="addtext"></span></td>
                                <td>商品收入（万元）</td>
                                <td class="addtext"></td>
                            </tr>
                            <tr>
                            	<td>景区内部建设投资（万元）</td>
                                <td class="addtext"></td>
                                <td>贷款（万元）</td>
                                <td class="addtext"></td>
                            </tr>
                            <tr>
                            	<td>住宿收入（万元）</td>
                                <td class="addtext"></td>
                                <td>演艺收入（万元）</td>
                                <td class="addtext"></td>
                            </tr>
                            <tr>
                            	<td>其他收入（万元）</td>
                                <td id="addtext" class="addtext"></td>
                                <td></td>
                                <td id="addtext" class="addtext"></td>
                            </tr>
                        </table>
                        <p class="red" style="margin-bottom:0px;">注：此处列出其他收入项，故各项总和等于景区总收入。</p>
                    </div>
             	</div>
             	<div class="item mgb20">
                	<span class="on">游客</span>
                    <div class="panel-body ItemCon">
                    	<table width="640px">
                        	<tr>
                            	<td><span style="width:135px;">接待人次（万人次）</span><input type="text" id="RecepNum" name="RecepNum" style="width:158px;"></td>
                                <td></td>
                            </tr>
                            <tr>
                            	<td><span style="width:165px;">其中免票人次（万人次）</span><input type="text" name="FreeTicNum" id="FreeTicNum" style="width:128px;"></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
             	</div>
                <div class="item">
                	<span class="on">管理</span>
                    <div class="panel-body ItemCon">
                    	<table width="640px">
                        	<tr>
                            	<td><span style="width:105px;">就业人数（人）</span><input type="text" id="EmployNum" name="EmployNum" style="width:188px;"></td>
                                <td></td>
                            </tr>
                        </table>
                    	<table width="640px" class="wtable mgb10" style="border-top:1px solid #d3d3d3; border-right:1px solid #d3d3d3;">
                        	<tr>
                            	<td>固定用工（人）</td>
                                <td class="addtext"></td>
                                <td>临时（季节性）用工（人次）</td>
                                <td class="addtext"></td>
                            </tr>
                            <tr>
                            	<td>专职导游人数（人）</td>
                                <td class="addtext"></td>
                                <td>贷款（万元）</td>
                                <td class="addtext"></td>
                            </tr>
                        </table>
                        <table width="640px" class="wtable1">
                        	<tr>
                            	<td><span>备注：</span></td>
                                <td><textarea rows="10" style="width:100%"></textarea></td>
                            </tr>	
                        </table>
                    </div>
             	</div>   
             </div>	
        </div>
</t:formvalid>
</body>