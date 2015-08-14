<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if goods??>${goods.seoTitle!''}-</#if></title>
<meta name="keywords" content="${goods.seoKeywords!''}">
<meta name="description" content="${goods.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/common.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
  
});
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>规格参数</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->
<table>
<#if goods.paramList??>
    <#list goods.paramList as param>
        <#if param_index % 3 == 0 >
            <tr>
                <td>${param.paramName!''}: ${param.value!''}&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <#if goods.paramList?size gt param_index+1 >
                    <td>${goods.paramList[param_index+1].paramName!''}: ${goods.paramList[param_index+1].value!''}&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </#if>
                <#if goods.paramList?size gt param_index+2 >
                    <td>${goods.paramList[param_index+2].paramName!''}: ${goods.paramList[param_index+2].value!''}&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </#if>
            </tr>  
        </#if>
    </#list>
</#if>
</table>
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
    <a href="/goods/${goods.id?c}">电脑版</a>
    <a href="/touch/goods/${goods.id?c}">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<p class="bottext mainbox">${site.icpNumber!''}</p>

</body>
</html>
