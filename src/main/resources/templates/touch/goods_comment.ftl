<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if></title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/common.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
<!-- 用户名中间变为*号  2015-8-4 15:38:18 mdj  -->
$(document).ready(function(){
    var pElement = $(".userName").toArray();
    for(var i = 0;i < pElement.length;i++)
    {
       var originName = pElement[i].innerHTML
       var name =  changeName(originName)
       pElement[i].innerText="用户名："+name
    }

});
/**
 * 隐藏用户名
 * @author mdj
 */
 function changeName(p)
{
    var temp = p
    if(temp.length == 11)
    {
        var changeStr = temp.substring(3, 7)
        temp = temp.replace(changeStr,"****")
    }
    else
    {
        var startStr = ""
        var strLength = temp.length;
        for (var i = 0; i < strLength - 4; i++)
        {
            startStr += "*";
        }
        var changeStr = temp.substring(2, strLength - 2)

        temp = temp.replace(changeStr, startStr)
    }
    return temp;
}
// 提交评论
function submitComment()
{
    $.ajax({
        type:"post",
        url:"/user/comment/add?" + $("#commentForm").serialize(),
        success:function(res){
            if (0 == res.code)
            {
                alert("提交评论成功，请耐心等待审核~~");
            }
            else
            {
                var ss = "" + res.message;
                alert(res["message"]);
            }
        }
    });
}

// 改变星级
function starChange(type, stars)
{
    if (null == type || null == stars)
    {
        return;
    }
    
    var starCount = parseInt(stars);
    
    // 商品满意度
    if ("goodsStar" == type)
    {
        $("#goodsStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.goodsStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 2:
            $("a.goodsStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 3:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 4:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 5:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start01.png" height="15"/>');
            break;
        default:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');    
        }
    }
    // 专业技能满意度
    else if ("skillStar" == type)
    {
        $("#skillStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 2:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 3:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 4:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 5:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start01.png" height="15"/>');
            break;
        default:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
                
        }
    }
    // 服务满意度
    else if ("serviceStar" == type)
    {
        $("#serviceStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 2:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 3:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 4:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
            break;
        case 5:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start01.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start01.png" height="15"/>');
            break;
        default:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" height="15"/>');
                
        }
    }
}


</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>商品评价</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>


<ul class="main myassesslist">
    <#--
    <li>
        <input type="radio" />全部评价
        <input type="radio" />好评
        <input type="radio" />中评
        <input type="radio" />差评
    </li>
    -->
    <#if comment_page?? && comment_page.content?size gt 0>
        <#list comment_page.content as item>
            <li>
                <p>${item.content!''}</p>
                <p id="userNameText" class="c9"><label class="userName">${item.username!''}</label><span>时间：${item.commentTime?string("yyyy-MM-dd")}</span></p>
            </li>
        </#list>
    <#else>
        <li>
        <p>暂无评价</p>
        </li>
    </#if>
</ul>

<#--
<a class="ma15 ta-c block" href="#"><img src="/touch/images/more.png" height="20" /></a>
-->

<div class="clear"></div>
<section class="botlogin">
  <#if username??>
  <a href="/touch/user">${username!''}</a>
  <a class="ml20" href="/touch/logout">退出</a>
  <#else>
  <a href="/touch/login">登录</a><a class="ml20" href="/touch/reg">注册</a>
  </#if>
  <a class="a1" href="javascript:$('html,body').animate({scrollTop:0},500);">TOP</a>
</section>
<footer class="comfoot main">
    <a href="/goods/${goodsId!''}">电脑版</a>
    <a href="/touch/goods/${goodsId!''}">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<p class="bottext mainbox">${site.icpNumber!''}</p>

</body>
</html>
