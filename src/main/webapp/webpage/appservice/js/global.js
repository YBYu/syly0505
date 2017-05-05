window.onload = function()
{
    $(window).ready(function()
    {
        $("#header").css("height", $(window).height() * 0.09 + "px");
        $("#holdDiv").css("height", $(window).height() * 0.09 + "px");
        $("#topTitle").css("font-size", $(window).height() * 0.04 + "px");
        $(".infoTag").css("font-size", $(window).height() * 0.025 + "px");
        $("table").css("margin", $(window).height() * 0.02 + "px" + " auto");
        $("#submit").css({
            "height" : $(window).height() * 0.07 + "px",
            "font-size" : $(window).height() * 0.02 + "px"
        });
        $("input[type=text]").css({
            "height" : $(window).height() * 0.05 + "px",
            "font-size" : $(window).height() * 0.025 + "px"
        });
        $("input[type=checkbox]").css({
            "height" : $(window).height() * 0.03 + "px",
            "width" : $(window).height() * 0.03 + "px"
        });
        $("select").css({
            "height" : $(window).height() * 0.05 + "px",
            "font-size" : $(window).height() * 0.03 + "px"
        });
        var json = getInfoFromServer();

        putInfoIntoPage(json);
    });

};

/*
 * 测试分辨率用代码
 */
window.onresize = function()
{
    $("#header").css("height", $(window).height() * 0.09 + "px");
    $("#holdDiv").css("height", $(window).height() * 0.09 + "px");
    $("#topTitle").css("font-size", $(window).height() * 0.04 + "px");
    $(".infoTag").css("font-size", $(window).height() * 0.03 + "px");
    $("table").css("margin", $(window).height() * 0.02 + "px" + " auto");
    $("#submit").css({
        "height" : $(window).height() * 0.07 + "px",
        "font-size" : $(window).height() * 0.02 + "px"
    });
    $("input[type=text]").css({
        "height" : $(window).height() * 0.05 + "px",
        "font-size" : $(window).height() * 0.03 + "px"
    });
    $("select").css({
        "height" : $(window).height() * 0.05 + "px",
        "font-size" : $(window).height() * 0.03 + "px"
    });
};

/*
 * 将读取数据显示在页面加载
 */
function putInfoIntoPage(json)
{
    for (var key in json)
    {
        try
        {
            if (json[key] != null && json[key] != "")
            {
                $("input[name = " + key + "]").val(json[key]);
            }
            $("select[name=" + key + "]")[0].value=json[key];
            
            for(var option in $("select[name=" + key + "]")[0].options){
            	if(option.value == json[key]) $("select[name=" + key + "]")[0].textContent = option.text;
            }
            $("select[name=" + key + "] option[value*=" + json[key] + "]").attr("selected", true);

        } catch (e)
        {
            continue;
        }
    }
    if (json["scenictitle"] != null && json["scenictitle"] != undefined)
    {
        for (var i = 0; i < json["scenictitle"].split(',').length; i++)
        {
            $("input[type=checkbox][value=" + json["scenictitle"].split(',')[i] + "]").attr("checked", "checked");
        }
    }
}

function getPageInfo()
{
    var data = "{";
    var buff = '"scenictitle":"';
    for (var i = 0; i < $(":input").length; i++)
    {
        if ($(":input")[i].name != "")
        {
            data += ('"' + $(":input")[i].name + '":"' + $(":input")[i].value + '",');
        } else
        {
            if ($(":input")[i].checked == true)
            {
                buff += $(":input")[i].value + ",";
            }
        }
    }
    if (buff != '"scenictitle":"')
    {
        data += buff + '",';
    }
    return JSON.parse(data.substr(0, data.length - 1) + "}");
}