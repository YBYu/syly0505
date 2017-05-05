/*
 * 读取数据
 */

function getInfoFromServer()
{
     var jsonObj = null;
    $.ajax({
        type : "get",
        async : false,
        url : "https://lytj.sanyatour.gov.cn:17002/syly/appservice/userlogincontroller.do?getHotelDetailInfo",
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
    // var iframe = document.getElementById('iframe');
    // var iframeWindow = iframe.contentWindow;// 获取iframe里的window对象
    // alert(iframeWindow.document.body.innerHTML);
}

/*
 * 提交数据
 */
function upLoadInfo()
{
    $.ajax({
        type : "post",
        async : false,
        url : "https://lytj.sanyatour.gov.cn:17002/syly/appservice/apphotelcontroller.do?fillHotelBasicInfo",
        data : {
            "param" : JSON.stringify(getPageInfo()),
            "userId" : userId
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        dataType : "json",
        success : function(e)
        {
        	if("edited" == e) alert("今年已提交过基础信息年报");
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
