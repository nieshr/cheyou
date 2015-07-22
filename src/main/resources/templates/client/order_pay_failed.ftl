<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=${charset!'UTF-8'}" />
<title>订单支付失败</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/cart.js"></script>
<script src="/client/js/order_info.js"></script>
<script src="/client/js/jquery.cityselect.js"></script>

<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />
<link href="/client/style/payment_status.css" rel="stylesheet" type="text/css" />

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
<#include "/client/common_header.ftl" />
  <div class="main">
    <#if order??>
    <h3>订单号:${order.orderNumber!''}</h3>
    <h3>支付金额:${order.totalPrice?string('0.00')}</h3>
    <h3>支付方式:${order.payTypeTitle!''}</h3>
    </#if>
    <h3>订单状态</h3>
    <div class="clear h20"></div>
    <div id="trans-status">
        <div class="notice-title status-failed">
            <img class="notice-icon" title="付款失败" src="/client/img/transfailed.png"></img>
            <span class="notice-content"><#if order??>付款失败，或者支付结果验证失败，如果订单已经成功支付，请联系客服处理！<#else>找不到对应的订单！</#if></span>
        </div>
    </div>
    <div class="clear h40"></div>
  </div>
<#include "/client/common_footer.ftl" />
</body>
</html>
<!--结束-->
