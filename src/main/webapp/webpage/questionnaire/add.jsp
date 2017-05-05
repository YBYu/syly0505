<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
<head>
<title>商品参数添加</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<t:base type="jquery,easyui,tools"></t:base>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<style type="text/css">
* {
	font: 12px tahoma, Arial, Verdana, sans-serif;
}
</style>
<script type="text/javascript">
	var titleCount = 1;
	var parameterIndex = 0;
	$(function() {
		var canSubmit = false;
		var $inputForm = $("#inputForm");
		var $parameterTable = $("#parameterTable");
		var $addParameter = $("#addParameter");
		var $deleteParameter = $("a.deleteParameter");
		var $optiondeleteParameter = $("a.optiondeleteParameter");
		var $saveParameter = $("a.saveParameter");
		var parameterIndexs = 0;
		// 增加参数
		$addParameter.live(
			"click",
			function() {
				var tr = "<tr><td colspan='3'><table><tr class='parameterTr'>"
						+ "<td>题&nbsp;&nbsp;目&nbsp;&nbsp;<b class='innum"+parameterIndex+"'>"
						+ (parameterIndex+1) 
						+ "</b></td>"
						+ "<td><input type='hidden' name='option"+parameterIndex+"'/>"
						+ "<input type='hidden' name='optionseq"+parameterIndex+"'/>"
						+"<input type='text' name='questionTopicList["+parameterIndex+"].topicName' class='text parametersName' style='width:500px;height:30px' /></td>"	
						+ "<td><a href='javascript:;' data-id='"+parameterIndex+"' class='deleteParameter'>[删除]</a></td><td>"
						+"<a href='javascript:;' class='saveParameter'  data-addid='"+parameterIndex+"' >[添加选项]</a></td></tr></table></td></tr>";
				$parameterTable.append(tr);
				parameterIndex++;
			});
		// 删除参数
		$deleteParameter.live("click", function() {
			parameterIndex--;
			var index = $(this).data("id");
			$(this).parent().parent().parent().remove();
			reloadName(index);
		});
		$optiondeleteParameter.live("click", function() {
			$(this).parent().parent().remove();
			//reloadName(index);
		});

		function reloadName(inx) {
			var x = inx;
			var index = inx + 1;
			var ele = $("input[name='questionTopicList[" + index + "].topicName']");
			ele.attr("name", "questionTopicList[" + x + "].topicName");
			
			var inele = $("b[class='innum" + index + "']");
			inele.attr('class', "innum" + x);
			inele.html(x+1); 
			var dataele = $("a[data-id='" + index + "']");
			dataele.attr("data-id", x);
			var adddataele = $("a[data-addid='" + index + "']");
			var opele = $("input[name='option" + index + "']");
			opele.attr("name", "option" + x );
			
			var opseqele = $("input[name='optionseq" + index + "']");
			opseqele.attr("name", "optionseq" + x );  
			var opelet = $("input[name='optiontt" + index + "']");
			opelet.attr("name", "optiontt" + x );
			
			adddataele.attr("data-addid", x);
			if (index < parameterIndex)
				reloadName(inx + 1);
		}
		
		$saveParameter.live(
			"click",
			function() {
				var addid = $(this).data("addid");
				var tr = "<tr class='parameterTr' ><td style='color:#0066FF'>选&nbsp;&nbsp;项&nbsp;&nbsp;</td>"
						+ "<td><input type='text' name='optiontt"+addid+"' class='text parametersName' style='width:500px;height:30px'/></td>" 
						+ "<td><a href='javascript:;' style='color:#0066FF' class='optiondeleteParameter'>[删除]</a></td></tr>";

				$(this).parent().parent().parent().append(tr);
				parameterIndexs++;
			});
		//添加修改类别界面
		$("#pid").change(function() {
			//alert("自己做");
		});
		$("#inputForm").bind("submit", function() {
			//alert("自己做")
			return false;
		});

		$("#inputForm").bind(
			"submit",
			function() {
				var name = $("input:first").val();
				if (name.length == 0) {
					$.message("error", "参数组名不能为空!");
					return false;
				} else {
					$.ajax({
						cache : false,
						type : "post",
						data : $("#inputForm").serialize(),
						url : "questionNaireController.do?save",
						success : function(args) {
							$.message("success", "添加成功！");
							setTimeout(
								function() {
									location.href = "questionNaireController.do?questionDatagrid";
								}, 3000);
						},
						error : function() {
							$.message("error", "添加失败！");
						}
					});
					return false;
				}
			});
	});
	function option2options(){
		var i = 0;
		while(i<parameterIndex){
			var ops  = $("input[name='optiontt"+i+"']"); 
			if(ops.length == 0){
				alert("请输入问卷选项!");
				return false;
			}
			var o = $("input[name='option"+i+"']");
			var oseq = $("input[name='optionseq"+i+"']");   
			var arr=[];
			var arrseq=[];
			for(var j=0;j<ops.length;j++){
				arr.push($(ops[j]).val());
			}
			o.val(arr);
			oseq.val(arrseq);  
			i++;
		}
		
		var wenjuan1=$("input[name='questionTopicList[0].topicName']").val();
		if(typeof(wenjuan1)=='undefined'){
			alert("请输入问卷题目!");
			return false;
		}
		for (var i = 0; i < parameterIndex; i++) {
			var wenjuan1=$("input[name='questionTopicList["+i+"].topicName']").val();
			if(wenjuan1 == ""){
				alert("请输入问卷题目!");
				return false;
			}
		}
	}
    
    function panduandata(n){
	    var startDate = $("#startTime").val();
	    var endDate=$("#endTime").val();
		var startNum = parseInt(startDate.replace(/-/g,''),10);
		var endNum = parseInt(endDate.replace(/-/g,''),10);	
		if(startNum && endNum && startNum > endNum){
			alert("结束时间不能在开始时间之前!");
			if(n == 1) {
				$("#startTime").attr("value","");
			} else {
				$("#endTime").attr("value","");
			}
			return false;
		}
	}    
</script>
</head>
<body>
	<t:formvalid formid="formobj" refresh="true" dialog="true"
		action="questionNaireController.do?save" usePlugin="password"
		layout="table" beforeSubmit="option2options">

		<table id="parameterTable" class="input">
			<tbody>
				<tr>
					<td><span class="requiredField"></span>问卷名称</td>
					<td colSpan = 2><input type="text" name="name" datatype="*"  class="text"
						style="width:500px;height:30px" errormsg="请输入问卷名称" ></td>
				</tr>
				<tr>
					<td><span class="requiredField"></span>问卷周期</td>
					<td><input type="text" name="startTime" id="startTime" class="Wdate" onblur="panduandata(1)"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:180px;height:30px" datatype="*" errormsg="请填写信息" /></td>
					<td>至&nbsp;&nbsp;<input type="text" name="endTime" id="endTime" class="Wdate"
					 onblur="panduandata(2)" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:180px;height:30px"  datatype="*"  errormsg="请填写信息" /></td>
				</tr>  
				<tr>
					<td>&nbsp;</td>
					<td><a href="javascript:;" style="color:#0066FF" id="addParameter" class="button">[增加题目]</a>
					</td>
				</tr>

			</tbody>
		</table>

	</t:formvalid>
</body>
</html>
