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
    searchTextClear(".logintext01","用户名/手机号/邮箱/车牌号","#999","#333");
    searchTextClear(".logintext02","输入密码","#999","#333");
    
    $("#btn_login").click(function(){
        var username = $("#txt_loginId").val();
        var password = $("#txt_loginPwd").val();
        
        if (username.length < 6 || password.length < 6)
        {
            alert("用户名或密码长度输入不足");
            return;
        }
        
        $.ajax({
                type: "post",
                url: "login",
                data: { "username": username, "password": password },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        var url = document.referrer;
                        if(undefined==url || ""==url){
                            window.location.href="/";
                        }else{
                            window.location.href = url; 
                        }
                    } else {
                        alert(data.msg);
                    }
                }
            });
    });
});
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>登录</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<section class="loginbox">
  <div class="main">
    <div class="logintext">
    <input id="txt_loginId" type="text" class="logintext01" value="" />
    </div>
    <div class="logintext logintext_p">
    <input id="txt_loginPwd" type="password" class="logintext02" value="" />
    </div>
    <input id="btn_login" type="submit" class="loginbtn" value="登录" />
    <p class="login_a">
      <a href="/touch/retrieve">找回密码</a>
      <a class="a1" href="/touch/reg">注册</a>
    </p>
  </div>
  <#--
  <div class="otherlogin">
    <p class="ta-c fs10 pb20"><span>第三方账号登录</span></p>
    <a href="#"><img src="images/login01.png" height="40" /><span>支付宝</span></a>
    <a href="#"><img src="images/login02.png" height="40" /><span>微信</span></a>
    <a href="#"><img src="images/login03.png" height="40" /><span>腾讯QQ</span></a>
  </div>
  -->
</section>
<div class="clear h15"></div>
<#--
<footer class="mainbox loginbot">
  <a href="#">100%正品保障</a>
  <a href="#">100%正品保障</a>
  <a href="#">100%正品保障</a>
</footer>
-->
</body>
</html>
