<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
</script>
</head>
<body style="" scroll="no">
	<t:formvalid formid="saveHotel" refresh="false" dialog="true"
		layout="table">
		<input id="id" type="hidden" value="${hotelmanage.id }">

		<table cellpadding="0" cellspacing="1" class="formtable">
			<tbody>
				<!--许可证号-->
				<tr>
					<td align="right" width="16%">组织机构代码</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmanage.organizationNum }" />
					</td>
					<td align="right" width="16%">单位名称</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmanage.unitname }" />
						<span></span>
					</td>
				</tr>
				<!--法人信息-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">法人</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmanage.legalPerson }" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">邮政编码</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmanage.zipcode }" />
					</td>
				</tr>
				<!--英文名称-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">电话号码</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmanage.telephone }" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">手机号码</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmanage.mobile }" />
					</td>
				</tr>
				<!--统一社会信用代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">酒店星级</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<select id="cc" name="housegrade" datatype="*" errormsg="请选择">
							<option value=""></option>
							<option ${hotelmanage.housegrade==8?'selected="selected"':''}
								value="8">五星</option>
							<option ${hotelmanage.housegrade==7?'selected="selected"':''}
								value="7">四星</option>
							<option ${hotelmanage.housegrade==6?'selected="selected"':''}
								value="6">三星</option>
							<option ${hotelmanage.housegrade==5?'selected="selected"':''}
								value="5">二星</option>
							<option ${hotelmanage.housegrade==4?'selected="selected"':''}
								value="4">一星</option>
							<option ${hotelmanage.housegrade==3?'selected="selected"':''}
								value="3">豪华</option>
							<option ${hotelmanage.housegrade==2?'selected="selected"':''}
								value="2">高档</option>
							<option ${hotelmanage.housegrade==1?'selected="selected"':''}
								value="1">舒适</option>
							<option ${hotelmanage.housegrade==0?'selected="selected"':''}
								value="0">其他</option>
						</select>
					</td>
					<td align="right" width="16%">
						<span class="filedzt">传真号码</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmanage.fax }" />
				</tr>
				<!--法人代表、行政区域代码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">房间数量</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmanage.housenum }" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">床位数量</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmanage.bednum }" />

					</td>
				</tr>
				<!--所在地区、详细地址-->
				<tr>
					<td align="right" width="16%">所属辖区</td>
					<td class="value" width="16%" colspan="2">
						<select style="width: 140px;" onchange="this.selectedIndex=1">
							<option value=""></option>
							<option ${hotelmanage.county=='0'?'selected':'' } value="0">市辖区</option>
							<option ${hotelmanage.county=='1'?'selected':'' } value="1">吉阳区</option>
							<option ${hotelmanage.county=='2'?'selected':'' } value="2">崖州区</option>
							<option ${hotelmanage.county=='3'?'selected':'' } value="3">天涯区</option>
							<option ${hotelmanage.county=='4'?'selected':'' } value="4">海棠区</option>
						</select>
					</td>
					<td align="right" width="16%">所属湾区</td>
					<td class="value" width="30%">
						<select id="area" style="width: 140px"
							onchange="this.selectedIndex=1">
							<option ${hotelmanage.registerstyle==""?"selected":""} value=""></option>
							<option ${hotelmanage.bay=='0'?'selected':''} value="0">市区</option>
							<option ${hotelmanage.bay=='1'?'selected':''} value="1">亚龙湾</option>
							<option ${hotelmanage.bay=='2'?'selected':''} value="2">大东海</option>
							<option ${hotelmanage.bay=='3'?'selected':''} value="3">三亚湾</option>
							<option ${hotelmanage.bay=='4'?'selected':''} value="4">海棠湾</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" width="16%">填表人电话</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmanage.mobile }" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">电子邮箱</span>
					</td>
					<td class="value" width="30%">
						<input type="text" value="${hotelmanage.email }" />
					</td>
				</tr>
				<!--网址、邮政编码-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">酒店网址</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmanage.weburl }" />
					</td>
					<td align="right" width="16%">填表时间</td>
					<td class="value" width="30%">
						<input type="text" pattern="yyyy-MM-dd"
							value=" <fmt:formatDate value="${hotelmanage.w_time }" type="date"/>" />
					</td>
				</tr>
				<!--单位负责人-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">单位负责人</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmanage.manager }" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">企业登记注册类型</span>
					</td>
					<td class="value" width="16%" colspan="2">
						<input type="text" value="${hotelmanage.registerstyle }" />
					</td>
					<!-- <select id="corRegister" name="registerstyle">
                    	<option type="text" value="default" class="opt-tit">国有</option>
                        <option type="text" value="私有">私有</option>
                    </select> -->
					</td>
				</tr>
				<!--是否经营出境游-->
				<tr>
					<td align="right" width="16%">
						<span class="filedzt">填表人</span>
					</td>
					<td class="value" width="32%" colspan="2">
						<input type="text" value="${hotelmanage.writer }" />
					</td>
					<td align="right" width="16%">
						<span class="filedzt">标牌编号</span>
					</td>
					<td class="value" width="32%">
						<input type="text" value="${hotelmanage.code }" />
					</td>
				</tr>
				<!--旅行社协会会员-->
				<!--  <tr>
				<td align="right" width="16%"><span class="filedzt">旅行社协会会员</span></td>
				<td class="value" width="16%" colspan="2"><input type="radio" type="text" value="1" name="yes">是</td>
                <td align="right" width="16%"><span class="filedzt">QQ</span></td>
				<td class="value" width="30%"><input type="text" type="text" value="" name="qq" class="qqId" errormsg="请输入QQ" /></td>
			</tr> -->
			</tbody>
		</table>
	</t:formvalid>
	<div style="padding: 5px">
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="check();">确认审核</a> -->

	</div>
</body>
<script type="text/javascript">
	/* function check(){
		var id = $("#id").val();
		$.ajax({
			url:"hotelController.do?check&id="+id,
			//data:{id:id}"/>,
			type:'get',
			dataType:"json",
			success:function(data){
				alert(data.msg);
				reloadTable();
			}"/>
			 
			}"/>);
	}"/> */
</script>