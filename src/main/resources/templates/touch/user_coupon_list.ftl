<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>车有同盟</title>
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
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>我的优惠卷</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>

<div class="main comcheck">
</div><!--comcheck END-->
<hr/>  
<menu class="whitebg mymenu_list">
<#if coupan_list??>
    <#list coupan_list as cg>
            <b><img src="${cg.typePicUri}" /></b>
            <p>有效期至：${cg.expireTime!''}<span class="sp1">${cg.isUsed?string("未用", "已使用")}</span></p>
            <div class="clear"></div>
            <hr/>
    </#list>
</#if>
</menu>

<#--
<a class="ma15 ta-c block" href="#"><img src="/touch/images/more.png" height="20" /></a>
-->

</body>
</html>
