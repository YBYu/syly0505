<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
<style type="text/css">
	ul{
		padding:0px; 
		margin:0px;
		}
	ul li{
		list-style:none;
		margin-bottom:10px;
		
		}
	.container{
		width:700px;
		padding:15px;
		margin:0 auto;
		background:#e7fafc;
		border-radius:10px;
		}
	.btn{
		display:inline-block;
		background:#d3d3d3;
		border:none;
		padding:3px;
		border-radius:3px;
		cursor:pointer;
		}
	ul li span{
		display:inline-block;
		font-size:14px;
		padding:3px;
		background:#9ddee3;
		}
	.gray{
		color:#d3d3d3;
		}
	.bg-blue{
		background:#e7fafc;
		}
	li:nth-child(4), li:nth-child(5), li:nth-child(6){
		width:30%;
		}
	li:nth-child(2) .text{
		width:75%
		}
	/*li:nth-child(11), li:nth-child(16){
		margin-left:50%;
		}
		*/
	.left{
		float:left;
		}
	.right{
		float:right;
		}
	.clear{
		overflow:hidden; clear:both; height:0;}
</style>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicController.do?save" usePlugin="password" layout="table">
	<input id="id" type="hidden" value="${scenicData.id }">
<body>
	<div class="container">
    	<ul class="ListForm">
            <li><span>景区ID：</span><input type="text" name="sceneryId" id="sceneryId" class="text" style="width:257px;"></li>
            <li><span>景区编号：</span><input type="text" name="sceneryNum" id="sceneryNum" class="text" style="width:244px;"></li>
            <li><span>景区名称：</span><input type="text" name="sceneryName" id="sceneryName" class="text" style="width:244px;"></li>
            <li class="left">
            	<span>所在省：</span>
            	<select  id="province" name="province" class="location">
                	<option value="">海南省</option>
                </select>
            </li>
            <li class="left">
            	<span>所在市：</span>
            	<select  id="city" name="city" class="location">
                	<option value="">三亚市</option>
                </select>
            </li>
            <li class="left";>	
            	<span>区：</span>
            	<select  id="district" name="district" class="location">
                	<option value="">三亚市</option>
                </select>
            </li>
            <li>	
            	<span>景区类型：</span>
            	<select id="sceneryType" name="sceneryType" class="level" style="width:248px;">
					<option value="AAAAA">AAAAA</option>
					<option value="AAAA">AAAA</option>
					<option value="AAA">AAA</option>
					<option value="AA">AA</option>
					<option value="A">A</option>		
				</select>
            </li>
            <li class="left"><span>地址：</span><input type="text" name="address1" id="address1" class="text" style="width:272px;"></li>
            <li class="left"><span>邮编：</span><input type="text" name="zipCode" id="zipCode" class="text" style="width:272px;"></li>
            <li style="clear:left;"><span>机构性质：</span><input type="text" name="institution" id="institution" class="text" style="width:244px;"></li>
            <li><span>网址：</span><input type="text" name="webUrl" id="webUrl" class="text" style="width:272px;"></li>
            <li><span>咨询电话：</span><input type="text" name="referTel" id="referTel" class="text" style="width:244px;"></li>
            <li class="left"><span>开业时间：</span><input type="text" name="openTime" id="openTime" class="text" style="width:244px;"></li>
            <li class="left"><span>日接待最大容量：</span><input type="text" name="dayCapacity" id="dayCapacity" class="text" style="width:205px;"></li>
            <li style="clear:left"><span>面积：</span><input type="text" name="proportion" id="proportion" class="text" style="width:272px;"></li>
            <li><span>投资主体：</span><input type="text" name="investor" id="investor" class="text" style="width:244px;"></li>
            <li><span>上级主管单位：</span><input type="text" name="superior" id="superior" class="text" style="width:216px;"></li>
            <li class="left">	
            	<span>等级：</span>
            	<input type="radio" name="level" id="level1" class="text" value="0">未评定
                <input type="radio" name="level" id="level2" class="text" value="1">补充完整
            </li>
            <li class="left"><span>等级公告时间：</span><input type="text" name="FillTable" id="FillTable" class="text" style="width:216px;"></li>
            
            
            <li class="left"><span>负责人姓名：</span><input type="text" name="tel" id="tel" class="text" style="width:230px;"></li>
            <li class="left"><span>负责人电话：</span><input type="text" name="tel" id="tel" class="text" style="width:230px;"></li>
            <li style="clear:left;"><span>负责人邮箱：</span><input type="text" name="tel" id="tel" class="text" style="width:230px;"></li>
            <li class="left"><span>填报人姓名：</span><input type="text" name="tel" id="tel" class="text" style="width:230px;"></li>
            <li class="left"><span>填报人电话：</span><input type="text" name="tel" id="tel" class="text" style="width:230px;"></li>
            <li class="left"><span>填报人邮箱：</span><input type="text" name="tel" id="tel" class="text" style="width:230px;"></li>
            <li class="left"><span>填报人QQ：</span><input type="text" name="tel" id="tel" class="text" style="width:236px;"></li>
            
            <li style="clear:left;">	
            	<span>门票形式：</span>
                <input type="radio" name="TicketForm" id="TicketForm1" class="text" value="1">纸质
                <input type="radio" name="TicketForm" id="TicketForm2" class="text" value="2">电子
            </li>
            
            <li>	
            	<span>（门票）是否一票制：</span>
                <input type="radio" name="yes" id="yes" class="text" value="1">是
                <input type="radio" name="yes" id="no" class="text" value="2">否
            </li>
            <li><span>景区有效期：</span><input type="text" name="validDate" id="validDate" class="text" style="width:230px;"></li>
            <li><span>景区所获称号：</span><input type="text" name="honor" id="honor" class="text" style="width:216px;"></li>
            <li><span class="mgb10">备注：</span><br><textarea name="comment" id="comment" class="text" rows="10" style="width:600px;"></textarea></li>
        </ul>
        <div class="clear"></div>
    </div>
</body>
</t:formvalid>
</body>