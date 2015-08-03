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
  searchTextClear(".comserch_text","搜索关键字","#999","#333");
});
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>会员中心</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->
<section class="memberhead main">
  <a href="#"><img src="${user.headImageUri!'/mag/style/user_avatar.png'}" /></a>
  <p class="white fs08 ta-c">${user.username!''}</p>
</section>

<section class="main membertop mt10 mb10">
  <menu>
    <a href="/touch/user/order/list/2"><img src="images/order01.png" height="30" /><p>待付款（${total_unpayed!0}）</p></a>
    <a href="/touch/user/order/list/3"><img src="images/order02.png" height="30" /><p>待发货（${total_undelivered!0}）</p></a>
    <a href="/touch/user/order/list/4"><img src="images/order03.png" height="30" /><p>待收货（${total_unreceived!'0'}）</p></a>
    <a href="/touch/user/order/list/6"><img src="images/order04.png" height="30" /><p>已完成（${total_finished!'0'}）</p></a>
  </menu>
</section>

<menu class="main memberlist">
  <a href="/touch/user/order/list/0">全部订单</a>
  <a href="/touch/user/point/list">我的粮草（${user.totalPoints!0}）</a>
  <#--
  <a href="#">我的优惠券</a>
  -->
  <a href="/touch/user/collect/list">我的收藏</a>
  <a href="/touch/user/info">个人信息设置</a>
</menu>


<div class="clear h40"></div>
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
    <a href="/">电脑版</a>
    <a href="/touch">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<p class="bottext mainbox">${site.icpNumber!''}</p>

</body>
</html>
