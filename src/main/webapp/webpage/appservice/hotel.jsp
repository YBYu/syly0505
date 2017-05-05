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
<script type="text/javascript" src="https://lytj.sanyatour.gov.cn:17002/syly/webpage/appservice/js/essential.js"></script>
<script type="text/javascript" src="https://lytj.sanyatour.gov.cn:17002/syly/webpage/appservice/js/jquery.js"></script>
<script type="text/javascript">
	var userId = "<%=request.getParameter("userId")%>";
</script>
</head>
<body>
	<div id="wholePage">
		<div id="formArea">
			<form action="#">
				<table id="formTable">
					<tr>
						<td class="infoTag"></td>
						<td><input type="text" name="id" style="display: none;"></td>
					</tr>
					<tr>
						<td class="infoTag">标牌编码：</td>
						<td><input type="text" name="code" readOnly="readOnly"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">组织机构代码：</td>
						<td><input type="text" name="organizationNum" readOnly="readOnly"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">酒店名称：</td>
						<td><input type="text" name="unitname"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">酒店地址：</td>
						<td><input type="text" name="address"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<!-- <tr>
						<td class="infoTag">注册时间：</td>
						<td><input type="text" name="registertime"></td>
					</tr> 
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr> -->
					<tr>
						<td class="infoTag">酒店级别：</td>
						<td><select name="housegrade" disabled="disabled">
								<option value="其他">其他</option>
								<option value="舒适">舒适</option>
								<option value="高档">高档</option>
								<option value="豪华">豪华</option>
								<option value="一星">一星</option>
								<option value="二星">二星</option>
								<option value="三星">三星</option>
								<option value="四星">四星</option>
								<option value="五星">五星</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">登记类型：</td>
						<td><input type="text" name="registerstyle" readOnly="readOnly"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">所属市：</td>
						<td><input type="text" name="city" value="三亚市" readOnly="readOnly"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">所属辖区：</td>
						<td><select name="county">
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
						<td class="infoTag">所属湾区：</td>
						<td><select name="bay">
								<option value="市区">市区</option>
								<option value="亚龙湾">亚龙湾</option>
								<option value="大东海">大东海</option>
								<option value="三亚湾">三亚湾</option>
								<option value="海棠湾">海棠湾</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">酒店法人：</td>
						<td><input type="text" name="legalPerson"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">负责人：</td>
						<td><input type="text" name="manager"></td>
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
						<td class="infoTag">电话号码：</td>
						<td><input type="text" name="telephone"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">手机号码：</td>
						<td><input type="text" name="mobile"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">房间数：</td>
						<td><input type="text" name="housenum"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">床位数量：</td>
						<td><input type="text" name="bednum"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">传真号码：</td>
						<td><input type="text" name="fax"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">网址：</td>
						<td><input type="text" name="weburl"></td>
					</tr>
					<tr>
						<td colspan="2" rowspan="1" class="hrLine"><hr /></td>
					</tr>
					<tr>
						<td class="infoTag">邮箱地址：</td>
						<td><input type="text" name="email"></td>
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