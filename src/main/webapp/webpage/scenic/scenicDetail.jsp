<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="plug-in/style/style.css">


<t:base type="jquery,easyui,tools"></t:base>
</head>
<!-- <body style="overflow-y: hidden" scroll="no"> -->
<body style="" scroll="">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicWeekController.do?save" usePlugin="password" layout="table">
	<input id="id" type="hidden" value="${scenicSpotWeek.id }">
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

<table style="width:750px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	
	<body>
	<div class="container">
    	<div class="InfoTop pdtb10">景区信息</div>
        <div class="MainInfo pdlr10">
        	<div class="MainInfo_tit pdtb10">
            	三亚亚龙湾热带天堂森林公园
            </div>
            <div class="MainInfo_con">
            	<!--基本信息-->
            	<div class="item item1 mgb20">
                	<span class="on">基本信息</span>
                    <div class="panel-body ItemCon">
                    	<div class="left LeftCon">
                        	<p><span>景区编号：</span><span>4602004A0006</span></p>
                            <p><span>所属辖区：</span><span>海南省三亚市</span></p>
                            <p><span>邮&nbsp;&nbsp;编：</span><span>572000</span></p>
                            <p><span>网&nbsp;&nbsp;址：</span><span>http://www.ylwpark.com</span></p>
                            <p><span>投资主体：</span><span>三亚亚龙湾热带森林公园有限公司</span></p>
                            <p><span>上级主管单位：</span><span>三亚市林业局</span></p>
                        </div>
                        <div class="left RightCon">
                        	<p><span>机构性质：</span><span>其他</span></p>
                            <p><span>地&nbsp;&nbsp;址：</span><span>亚龙湾国家旅游区</span></p>
                            <p><span>景区类型：</span><span>度假休闲</span></p>
                            <p><span>咨询电话：</span><span>0898-38278801</span></p>
                            <p><span>开业时间：</span><span>2009年09月14日</span></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
                <!--等级信息-->
                <div class="item item2 mgb20">
                	<span class="on">等级信息</span>
                    <div class="panel-body ItemCon">
                    	<div class="left LeftCon">
                        	<p><span>等&nbsp;&nbsp;级：</span><span>AAAA</span></p>
                            <p><span>日接待最大容量：</span><span>1.7(万人)</span></p>
                        </div>
                        <div class="left RightCon">
                        	<p><span>等级公告时间：</span><span>2013年09月13日</span></p>
                            <p><span>面&nbsp;&nbsp;积：</span><span>1506.0（公顷）</span></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
                <!--联系信息-->
                <div class="item item3 mgb20">
                	<span class="on">联系信息</span>
                    <div class="panel-body ItemCon">
                    	<div class="left LeftCon">
                        	<span class="column">负责人信息</span>
                        	<p><span>姓&nbsp;&nbsp;名：</span><span>李先生</span></p>
                            <p><span>电&nbsp;&nbsp;话：</span><span>0898-38278806</span></p>
                            <p><span>邮&nbsp;&nbsp;箱：</span><span>ylw@163.com</span></p>
                        </div>
                        <div class="left RightCon">
                        	<span class="column">填报人信息</span>
                        	<p><span>姓&nbsp;&nbsp;名：</span><span>李先生</span></p>
                            <p><span>电&nbsp;&nbsp;话：</span><span>0898-38278806</span></p>
                            <p><span>邮&nbsp;&nbsp;箱：</span><span>ylw@163.com</span></p>
                            <p><span>Q&nbsp;&nbsp;Q：</span><span>1009162226</span></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
                <!--门票信息-->
                <div class="item item4 mgb20">
                	<span class="on">门票信息</span>
                    <div class="panel-body ItemCon">
                    	<table cellspacing="0" cellpadding="0" align="center" style="border-right:1px solid #d3d3d3; margin-bottom:10px;">
                        	<thead>
                            	<tr>
                                	<td>类型</td><td>起止时间</td><td>门票价格</td><td>营业时间</td>
                                </tr>
                            </thead>
                            <tbody>
                            	<tr>
                                	<td>淡季</td><td>05月01日～09月30号</td><td>100</td><td>7时30号～17时30分</td>
                                </tr>
                                <tr>
                                	<td>旺季</td><td>10月01日～04月30号</td><td>120</td><td>7时30号～17时30分</td>
                                </tr>	
                            </tbody>
                        </table>
                        <div class="left LeftCon">
                        	<p><span>门票形式：</span><span>纸质，非一票制</span></p>
                        </div>
                        <div class="left RightCon">
                        	<p><span>门票有效期：</span><span>当天</span></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
                <!--其他信息-->
                <div class="item item5 mgb20">
                	<span class="on">其他信息</span>
                    <div class="panel-body ItemCon">
                    	<div class="left LeftCon">
                        	<p><span>景区所获称号：</span><span></span></p>
                            <p><span>备注：</span><span>景区索桥和滑索项目为另收费项目)</span></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>	
</body>
		</tbody>
	</table>
</t:formvalid>
</body>