<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本信息</title>
<%  String ctx = request.getContextPath();  %>
<link rel="stylesheet" type="text/css" href="https://lytj.sanyatour.gov.cn:17002/syly/webpage/appservice/css/global.css">
<script type="text/javascript" src="https://lytj.sanyatour.gov.cn:17002/syly/webpage/appservice/js/global.js"></script>
<script type="text/javascript" src="https://lytj.sanyatour.gov.cn:17002/syly/webpage/appservice/js/scenic.js"></script>
<script type="text/javascript" src="https://lytj.sanyatour.gov.cn:17002/syly/webpage/appservice/js/jquery.js"></script>
<script type="text/javascript">
	var userId = "<%=request.getParameter("userId")%>";
</script>
</head>
<body>
	<div id="wholePage">
			<form action="#">
				<table id="formTable">
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag"></td>
						<td><input type="text" name="id" value="<%=request.getParameter("userId")%>" style="display: none;"></td>
					</tr>
					<tr>
						<td class="infoTag">景区名称：</td>
						<td><input type="text" name="enterpriseName"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">景区编号：</td>
						<td><input type="text" name="enterpriseCode" readOnly="readOnly"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">邮政编码：</td>
						<td><input type="text" name="zipcode"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">网址：</td>
						<td><input type="text" name="url"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">投资主体：</td>
						<td><input type="text" name="investunit"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">上级单位：</td>
						<td><input type="text" name="superiorunit"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">机构性质：</td>
						<td><select name="orgproperty" >
								<option value="行政单位">行政单位</option>
								<option value="事业单位">事业单位</option>
								<option value="国有">国有</option>
								<option value="集体">集体</option>
								<option value="股份合作">股份合作</option>
								<option value="国有联营">国有联营</option>
								<option value="集体联营">集体联营</option>
								<option value="国有与集体联营">国有与集体联营</option>
								<option value="其他联营">其他联营</option>
								<option value="国有独资公司">国有独资公司</option>
								<option value="其他有限责任公司">其他有限责任公司</option>
								<option value="股份有限公司">股份有限公司</option>
								<option value="私营独资">私营独资</option>
								<option value="私营合伙">私营合伙</option>
								<option value="私营有限责任公司">私营有限责任公司</option>
								<option value="私营股份有限公司">私营股份有限公司</option>
								<option value="与港澳台商合资经营">与港澳台商合资经营</option>
								<option value="与港澳台商合作经营">与港澳台商合作经营</option>
								<option value="港澳台商投资股份有限公司">港澳台商投资股份有限公司</option>
								<option value="港澳台商独资">港澳台商独资</option>
								<option value="中外合资经营">中外合资经营</option>
								<option value="中外合作经营">中外合作经营</option>
								<option value="外资企业">外资企业</option>
								<option value="外商投资股份有限公司">外商投资股份有限公司</option>
								<option value="部队">部队</option>
								<option value="其他">其他</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">地址：</td>
						<td><input type="text" name="enterpriseAddress"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">咨询电话：</td>
						<td><input type="text" name="consultphone"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">开业时间：</td>
						<td><input type="text" name="opentime"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">日最大接待量：</td>
						<td><input type="text" name="daylimit"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">等级公告时间：</td>
						<td><input type="text" name="leveldate"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">面积（公顷）：</td>
						<td><input type="text" name="acreage"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">负责人姓名：</td>
						<td><input type="text" name="chargename"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">负责人电话：</td>
						<td><input type="text" name="chargephone"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">负责人邮箱：</td>
						<td><input type="text" name="chargeemail"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">上报人姓名：</td>
						<td><input type="text" name="informantname"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">上报人电话：</td>
						<td><input type="text" name="informantphone"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">上报人邮箱：</td>
						<td><input type="text" name="informantemail"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">上报人QQ：</td>
						<td><input type="text" name="informantqq"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">所属辖区：</td>
						<td><select name="enterpriseArea">
								<option value="0">市辖区</option>
								<option value="1">崖州区</option>
								<option value="2">海棠区</option>
								<option value="3">天涯区</option>
								<option value="4">吉阳区</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">景区类型：</td>
						<td><select name="scenictype">
								<option value="1">自然景观</option>
								<option value="2">历史文化</option>
								<option value="3">度假休闲</option>
								<option value="4">主题游乐</option>
								<option value="5">博物馆</option>
								<option value="6">乡村旅游</option>
								<option value="7">工业旅游</option>
								<option value="8">红色旅游</option>
								<option value="9">科技教育</option>
								<option value="10">其他</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">级别：</td>
						<td><select name="level" disabled="disabled">
								<option value="1">AAAAA</option>
								<option value="2">AAAA</option>
								<option value="3">AAA</option>
								<option value="4">AA</option>
								<option value="5">A</option>
								<option value="6">未评定</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">所属湾区：</td>
						<td><select name="bayType">
								<option value="0">市区</option>
								<option value="1">亚龙湾</option>
								<option value="2">大东海</option>
								<option value="3">三亚湾</option>
								<option value="4">海棠湾</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="1"><span class="infoTag">国家级名胜风景区</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="2"><span class="infoTag">国家自然保护区</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="3"><span class="infoTag">世界遗产</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="4"><span class="infoTag">国际水利公园</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="5"><span class="infoTag">世界地质公园</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="6"><span class="infoTag">文明风景旅游区</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="7"><span class="infoTag">国家森林公园</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="8"><span class="infoTag">爱国主义教育基地</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="9"><span class="infoTag">国家稳保单位</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input
							type="checkbox" value="10"><span class="infoTag">历史文化名镇、名村</span></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><input type="text"
							name="remarks" placeholder="请输入备注"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
				</table>
				<center>
					<input type="button" value="提交" id="submit" onclick="upLoadInfo()">
				</center>
			</form>
		</div>
	</div>
</body>
</html>