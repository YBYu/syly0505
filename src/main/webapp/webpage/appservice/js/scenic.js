/*
 * 读取数据
 */
function getInfoFromServer()
{
    var jsonObj = null;
    $.ajax({
        type : "get",
        async : false,
        url : "https://lytj.sanyatour.gov.cn:17002/syly/appservice/appscenicController.do?scenic",
        data : {
            "userId" : userId
        },
        dataType : "json",
        success : function(json)
        {
            jsonObj = json;
        },
        error : function(e)
        {
            alert('fail');
        }
    });
    return JSON.parse(jsonObj);
}

/*
 * 提交数据
 */
function upLoadInfo()
{
	if(typeof getPageInfo().scenictitle == "undefined") {
		alert("请勾选景区称号");
		return;
	}
    $.ajax({
        type : "post",
        async : false,
        url : "https://lytj.sanyatour.gov.cn:17002/syly/appservice/appscenicController.do?editscenic",
        data : {
            "param" : JSON.stringify(getPageInfo()),
            "userId" : userId
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        dataType : "json",
        success : function(e)
        {
        	if("success" == e) alert("暂存成功");
        	else alert("暂存失败");
        	return;
        },
        error : function()
        {
        	alert("暂存失败");
        	return;
        }
    });
}
