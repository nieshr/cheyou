<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>车有同盟</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>

<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(function(){
	
	    $("#btn_login").click(function(){
	    	login();
	    });
	     
	});


   document.onkeydown = function(event){
    if((event.keyCode || event.which) == 13){
        login();
    }
   }
   
   function login(){
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
    }
</script>
</head>

<body>
<header class="logintop">
  <div class="main pt20">
    <a class="fl" href="/"><img src="/client/images/liebiao_03.png" /></a>
    <p class="p3">售后保障</p>
    <p class="p2">100%品牌制造商</p>
    <p class="p1">100%正品保障</p>
    <div class="clear"></div>
  </div>
</header>
<div class="logingbg">
    <section class="loginbox">
        <p>请输入用户名/邮箱/车牌号码</p>
        <input id="txt_loginId" class="text" type="text" />
        <p>请输入密码</p>
        <input id="txt_loginPwd" class="text" type="password" />
        <div class="clear h15"></div>
        <p class="pb10">
            <input type="checkbox" />
            <span>记住密码</span>
            <span class="absolute-r"><a href="javascript:;">忘记密码</a> | <a href="/reg">免费注册</a></span>
        </p>
        <span>合作账号登录</span>
        <p>
            <span>
                <a href="#" title="QQ登录">
                    <img src="/client/images/20150619110939448_easyicon_net_72.png" width="30" height="30" />
                </a>
            </span>
            <span class="ml20">
                <a href="#" title="支付宝登录">
                    <img src="/client/images/20150619110924540_easyicon_net_72.png" width="30" height="30" />
                </a>
            </span>
        </p>
        <div class="clear h40"></div>
        <input id="btn_login" type="submit" class="sub" value="登录" />
        <div class="clear h20"></div>
    </section>
</div><!--logingbg END-->

<footer class="loginfoot">
    <nav>
        <#if help_level0_cat_list??>
            <#list help_level0_cat_list as item>
                <a href="/info/list/${item.id!''}">${item.title!''}</a>
            </#list>
        </#if>
    </nav>
    <p>友情链接：
    <#if site_link_list??>
        <#list site_link_list as item>
            <a href="${item.linkUri!''}">${item.title!''}</a> |
        </#list>
    </#if>
    </p>
    <p>${site.copyright!''}</p>
    <p>${site.address!''} 电话：${site.telephone!''} </p>
  <p><a title="云南网站建设" href="http://www.ynyes.com" target="_blank">网站建设</a>技术支持：<a title="云南网站建设" href="http://www.ynyes.com" target="_blank">昆明天度网络公司</a>
</p>
</footer>
</body>
</html>
