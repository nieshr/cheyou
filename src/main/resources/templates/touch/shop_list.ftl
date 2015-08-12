<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>同盟店列表</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="copyright" content="" />
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
    <p>门店查询</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->
<ul class="main shoplist">
    <#if shop_list??>
        <#list shop_list as item>
        
        </#list>
    </#if>
  
  <li>
    <a class="a1" href="#"><img src="/touch/images/img01.png" /></a>
    <p class="p1">我是商店的名车哦
      <span>
        <img src="/touch/images/star01.png" height="15" />
        <img src="/touch/images/star01.png" height="15" />
        <img src="/touch/images/star01.png" height="15" />
        <img src="/touch/images/star01.png" height="15" />
        <img src="/touch/images/star01.png" height="15" />
      </span>
    </p>
    <p class="p2">详细地址：我是地址的东西文字描述</p>
    <p class="p2">
      <a href="#">查看地图</a>
      <a class="a2" href="#">拨打电话</a>
    </p>
    <div class="clear"></div>
  </li>
  
</ul>


<div class="clear"></div>
<a class="ma15 ta-c block" href="#"><img src="/touch/images/more.png" height="20" /></a>
<div class="clear h20"></div>
<section class="botlogin">
  <a href="#">登录</a><a class="ml20" href="#">注册</a>
  <a class="a1" href="#">TOP</a>
</section>
<footer class="comfoot main">
    <a href="#">电脑版</a>
    <a href="#">触屏版</a>
</footer>
<p class="bottext mainbox">Copyright©2015 www.cytm99.com 保留所有版权</p>
<p class="bottext mainbox">滇ICP备0932488号</p>

</body>
</html>
