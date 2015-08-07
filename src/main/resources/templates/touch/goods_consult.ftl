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
$(document).ready(function(){
  
});

//提交咨询
function submitConsult()
{
    $.ajax({
        type:"post",
        url:"/user/consult/add?" + $("#consultForm").serialize(),
        success:function(res){
            if (0 == res.code)
            {
                alert("提交咨询成功，请耐心等待审核~~");
                window.location.reload();
            }
            else
            {
                var ss = "" + res.message;
                alert(res["message"]);
            }
        }
    });
}
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>留言咨询</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->

<div class="mainbox myassess pt20">
    <form id="consultForm" action="javascript:submitConsult();">
        <input type="hidden" name="goodsId" value=${goodsId} />
        <textarea name="content" datatype="*5-255" nullmsg="请输入咨询内容"></textarea>
        <div class="clear"></div>
        <input type="submit" value="提交" class="sub" />
    </form>
  
    <div class="clear h20"></div>
</div><!--main END-->

<ul class="main myassesslist">
    <#if consult_page??>
        <#list consult_page.content as item>
        <li>
            <p class="p1">${item.username?substring(0, 3)}****: ${item.content!''}</p>
            <#if item.isReplied?? && item.isReplied>
                <p class="p2">商家回复：${item.reply!''}</p>
            </#if>
        </li>
        </#list>
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
    <a href="/goods/${goodsId}">电脑版</a>
    <a href="/touch/goods/${goodsId}">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<p class="bottext mainbox">${site.icpNumber!''}</p>

</body>
</html>
