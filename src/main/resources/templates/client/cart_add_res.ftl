<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>

<link rel="shortcut icon" href="/client/images/cheyou.ico" />
<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
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
        
});
</script>
</head>
<body>
<!-- header开始 -->
<#include "/client/common_header.ftl" />
<!-- header结束 -->


<div class="main">
    <menu class="car_top">
        <p style="z-index:10; width:34%;">我的购物车<i></i></p>
        <p style="z-index:8;">我的订单信息<i></i></p>
        <p>支付成功</p>
        <div class="clear"></div>
    </menu>
    <div class="clear h30"></div>

    <div class="car_success">
        <#if res??>
            <p class="fc fs30 lh40 pb10 pt20">该商品仅限购买一次，您已购买过该商品！</p>
        <#else>
            <p class="fc fs30 lh40 pb10 pt20">加入购物车成功</p>
            <p class="fc fs18 pb10"> 
                <a href="/cart" style="
                    height: 30px;
                    background: #ff4454;
                    color: #fff;
                    width: 200px;
                    float: left;
                    text-align: center;
                    line-height: 30px;
                ">去购物车结算</a>
                <div class="clear"></div>
            </p>
        </#if>
        <p>您还可以 <a class="blue" href="/">继续购物</a></p>       
    </div>
    <div class="clear"></div> 
</div>

<#include "/client/common_footer.ftl" />
</body>
</html>