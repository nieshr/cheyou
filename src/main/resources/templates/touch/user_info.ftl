<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>暂无标题</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="copyright" content="" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/common.js"></script>

<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

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
    <p>个人信息设置</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
 <form id="form1" action="/touch/user/info" method="post">
<p class="address">姓名</p>
<input type="text" name="realName" class="address" value="${user.realName!''}" />
<p class="address">性别</p>
<p class="address">
  <input type="radio" name="sex" value="男" <#if user.sex?? && user.sex=="男">checked="checked" </#if>/><span>男</span>
  <input type="radio" name="sex" value="女" <#if user.sex?? && user.sex=="女">checked="checked" </#if> class="ml20" /><span>女</span>
</p>
<p class="address">电话</p>
<input type="text" class="address" name="mobile" value="${user.mobile!''}" />
<p class="address">车牌号码</p>
<input type="text" class="address" name="carCode" value="${user.carCode!''}" />




<input type="submit" class="greenbtn fs11" style="width:90%;" value="保存" />
</from>
</body>
</html>
