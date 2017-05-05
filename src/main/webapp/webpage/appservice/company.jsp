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
<script type="text/javascript" src="https://lytj.sanyatour.gov.cn:17002/syly/webpage/appservice/js/company.js"></script>
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
						<td><input type="text" name="id" style="display: none;"></td>
					</tr>
					<!-- <tr>
						<td class="infoTag">所在省：</td>
						<td><input type="text" name="id"></td>
					</tr> -->
					<tr>
						<td class="infoTag">企业类型：</td>
						<td><select name="type"  disabled>
								<option value="高尔夫">高尔夫项目</option>
								<option value="游艇">游艇项目</option>
								<option value="空中飞行">空中飞行项目</option>
								<option value="机场">机场项目</option>
								<option value="动车">动车项目</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">所在省：</td>
						<td><input type="text" name="privince" value="海南省"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">所在城市：</td>
						<td><input type="text" name="city" value="三亚市"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">所属辖区：</td>
						<td><select name="area">
								<option value="市辖区">市辖区</option>
								<option value="崖州区">崖州区</option>
								<option value="海棠区">海棠区</option>
								<option value="天涯区">天涯区</option>
								<option value="吉阳区">吉阳区</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">企业名称：</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">负责人：</td>
						<td><input type="text" name="principal"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">联系电话：</td>
						<td><input type="text" name="phone"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">手机号码：</td>
						<td><input type="text" name="tel"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">详细地址：</td>
						<td><input type="text" name="address"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">邮政编码：</td>
						<td><input type="text" name="postalcode"></td>
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