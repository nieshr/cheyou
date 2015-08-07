<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/html5.js"></script>
<script src="/client/js/list.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>

<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
  $(document).ready(function(){
    menuDownList("top_phone","#top_phonelist",".a1","sel");
    phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
    searchTextClear(".toptext","请输入品牌或商品名称","#999","#666");
    searchTextClear(".bottext","查看所有门店","#fff","#fff");
    checkNowHover("shopping_down","shopping_sel");
    navDownList("navdown","li",".nav_showbox");
    menuDownList("mainnavdown","#navdown",".a2","sel");
    
    $("#form1").Validform({
        tiptype: 3
    });
});

 
</script>
</head>

<body>
<#include "/client/common_header.ftl" />

<div class="main">
<menu class="car_top">
    <p style="z-index:10; width:34%;">填写账户名<i></i></p>
    <p   style="z-index:8;">验证身份<i></i></p>
    <p class="sel">设置新密码</p>
    <div class="clear"></div>
  </menu>  
  
  <section class="loginbox">
    <form id="form1" method="post" action="/login/retrieve_step3">
        <p>新密码：</p>
        <input class="text" name="password" type="password" datatype="s6-20"/>
        <p>确认新密码：</p>
        <input class="text" type="password" datatype="*" recheck="password"/>
        <div class="clear h15"></div>
        
        <input type="submit" class="sub" value="完成" />
        <div class="clear h15"></div>
    </form>
  </section>
  
 
  <div class="clear"></div> 
</div><!--main END-->
<#include "/client/common_footer.ftl" />

</body>
</html>
