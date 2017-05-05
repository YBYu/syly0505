/*
 * 读取数据
 */

function getInfoFromServer()
{
    var jsonObj = null;
    $.ajax({
        type : "get",
        async : false,
        url : "https://lytj.sanyatour.gov.cn:17002/syly/appservice/userlogincontroller.do?getOtherTravelDetailInfo",
        data : {
            "userId" : userId
        },
        dataType : "json",
        success : function(json)
        {
            jsonObj = json;
        },
        error : function()
        {
            alert('fail');
        }
    });

    return jsonObj;
}

/*
 * 提交数据
 */
function upLoadInfo()
{
	var json = getPageInfo();
    $.ajax({
        type : "post",
        async : false,
        url : "https://lytj.sanyatour.gov.cn:17002/syly/appservice/basicinfocontroller.do?fillBasicInfo",
        data : {
            "param" : JSON.stringify(json),
            "userId" : userId
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        dataType : "json",
        success : function(e)
        {
        	if("success" == e) alert("提交成功");
        	else alert("提交失败");
        	return;
        },
        error : function(e)
        {
        	alert("提交失败");
        	return;
        }
    });
}
