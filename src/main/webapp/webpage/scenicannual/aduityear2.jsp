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
<t:formvalid formid="formobj" refresh="true" dialog="true" action="scenicAnnualController.do?save" usePlugin="password" layout="table"  >
	<input name="scenicdataid" type="hidden" value="${scenicdataid}">
		<input name="yearid" type="hidden" value="${yearid}">
	
<!--     <input id="sid" type="hidden"  value="${scenicSpotWeek.sid }"> -->

 <table style="width:650px; font-size:12px;" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
        	<!--许可证号--> 
        	<div class="MainInfo_tit pdtb10">
            	<span>${scenicdata.name}年报数据  </span>
            </div>
        	<tr>
        
            	<td align="right" width="16%">年份</td>
                <td class="value" width="16%" colspan="2"><input type="text" name="year" readOnly="readOnly"
						class="inputSize" value="${yeardata.year}"></td>
                <td align="right" width="16%">资产总额（万元）</td>
                <td class="value" width="30%"><input type="text" value="${yeardata.assetstotal}" name="assetstotal" datatype="xs"  /><span class="red"></span></td>
            </tr>
            <!--单位名称-->
             
            <tr>   

				<td align="right" width="16%"><span class="filedzt">年度建设投资（万元）  </span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.buildinvest}" name="buildinvest"   datatype="xs"  /><span class="red" ></span></td>
                <td align="right" width="16%"><span class="filedzt">拨款（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.appropriation}" name="appropriation" datatype="xs"   /></td>
			</tr>
            <!--英文名称-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区内部建设投资（万元）  </span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.inbuild}" name="inbuild" datatype="xs" class="build" /></td>
                <td align="right" width="16%"><span class="filedzt">贷款（万元）</span></td>
                <td class="value" width="30%"><input type="text"  value="${yeardata.loan}" name="loan" datatype="xs"  /></td>
			</tr>
            <!--统一社会信用代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区外部建设投资（万元）  </span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.outbuild}" name="outbuild" datatype="xs"  class="build"/><span class="red"></span></td>
                <td align="right" width="16%"><span class="filedzt">自筹（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.fundsself}" name="fundsself" datatype="xs"  /></td>
			</tr>
            <!--法人代表、行政区域代码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">景区经营总收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.totalincome}" name="totalincome"     style="background:#FAFAFA"  readonly="readonly"  /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%"></td>
			</tr>
            <!--所在地区、详细地址-->
       
            <!--固定联系方式-->
		
            <!--手机、电子邮箱-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">门票收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.ticketincome}" name="ticketincome"datatype="xs"   class="income" /></td>
                <td align="right" width="16%"><span class="filedzt"> 商品收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.shopincome}" name="shopincome" datatype="xs" class="income"  /></td>
			</tr>
            <!--网址、邮政编码-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">餐饮收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.foodincome}" name="foodincome" datatype="xs"  class="income"  /></td>
                <td align="right" width="16%"><span class="filedzt">交通收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.trafficincome}" name="trafficincome" datatype="xs" class="income"  /></td>
			</tr>
            <!--单位负责人-->
			 <tr>
				<td align="right" width="16%"><span class="filedzt">演艺收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.showincome}" name="showincome" datatype="xs" class="income"   /></td>
                <td align="right" width="16%"><span class="filedzt">住宿收入（万元）</span></td>
                <td class="value" width="30%"><input type="text" value="${yeardata.putUpincome}" name="putUpincome" datatype="xs" class="income"  /></td>
			</tr>			
			
            <tr>
				<td align="right" width="16%"><span class="filedzt">其他收入（万元）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.otherincome}" name="otherincome" datatype="xs"  class="income"  /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
                <td class="value" width="30%">
                </td>
			</tr>
           
           
           
            <tr>
				<td align="right" width="16%"><span class="filedzt">接待人次（万人次）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.receptionnum}" name="receptionnum" datatype="xs"   /></td>
                <td align="right" width="16%"><span class="filedzt">其中免票人次（万人次） </span></td>
                <td class="value" width="30%">
                	<input type="text" value="${yeardata.exemptTicketnum}" name="exemptTicketnum" datatype="xs"   />
                </td>
			</tr>
            <!--旅行社协会会员-->
            <tr>
				<td align="right" width="16%"><span class="filedzt">就业人数（人)</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.jobnum}" name="jobnum"   style="background:#FAFAFA"  readonly="readonly" /></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
				<td class="value" width="30%"></td>
			</tr>
			
			
			 <tr>
				<td align="right" width="16%"><span class="filedzt">其中固定用工（人）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.fixedworker}" name="fixedworker" datatype="n1-7" errormsg="只能为1-7位整数" class="job"/></td>
                <td align="right" width="16%"><span class="filedzt">临时（季节性）用工（人次）</span></td>
				<td class="value" width="30%"><input type="text" value="${yeardata.tempworker}" name="tempworker" datatype="n1-7" errormsg="只能为1-7位整数" class="job"/></td>
			</tr>
			 <tr>
				<td align="right" width="16%"><span class="filedzt">专职导游人数（人）</span></td>
				<td class="value" width="16%" colspan="2"><input type="text" value="${yeardata.guidenum}" name="guidenum" datatype="n1-7" errormsg="只能为1-7位整数"/></td>
                <td align="right" width="16%"><span class="filedzt"></span></td>
				<td class="value" width="30%"></td>
			</tr>
			<tr>
               	<td><span>备注</span></td>
                <td class="value" width="16%"  colspan="4"><textarea rows="10"   name="remarks" style="width:100%">${yeardata.remarks}</textarea></td>
            </tr>
			
            
		</tbody>
	</table>
 
</t:formvalid>
<SCRIPT type="text/javascript">
/* //计算年底建设投资
$(".build").change(function(){
	var buildinvest=eval($("input[name='inbuild']").val())+eval($("input[name='outbuild']").val());
	$("input[name='buildinvest']").val(buildinvest);
}); */
//计算景区经营总收入
$(".income").change(function(){
	var totalincome=eval($("input[name='ticketincome']").val())+eval($("input[name='shopincome']").val())+eval($("input[name='foodincome']").val())+eval($("input[name='trafficincome']").val())+eval($("input[name='putUpincome']").val())+eval($("input[name='showincome']").val())+eval($("input[name='otherincome']").val());
	$("input[name='totalincome']").val(totalincome);
});
//计算就业人数
$(".job").change(function(){

	var jobnum=eval($("input[name='fixedworker']").val())+eval($("input[name='tempworker']").val());
	$("input[name='jobnum']").val(jobnum);
});

	 
</SCRIPT>
</body>