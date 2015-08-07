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
  searchTextClear(".logintext01","手机号/邮箱","#999","#333");
  searchTextClear(".logintext02","输入验证码","#999","#333");
});
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>找回密码</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<form id="form1" method="post" action="/touch/find">
    <section class="loginbox">
      <div class="main">
        <p style="color: #F00">${error!''}</p>
        <div class="logintext">
        <input type="text" class="logintext01" name="username" value="" />
        </div>
        <div class="logintext logintext_y">
        <input type="text" class="logintext02" name="code" value="" />
        </div>
        <a href="#" class="login_yzm"><img src="/code"  onclick="this.src = '/code?date='+Math.random();"/></a>
        <div class="clear"></div>
        <input type="submit" class="loginbtn" value="下一步" />
          </div>
      
    </section>
</form>
<div class="clear h15"></div>

</body>
</html>