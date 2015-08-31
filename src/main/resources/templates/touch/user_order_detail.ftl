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
    <p>订单详情</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->
<section class="whitebg">
  <menu class="myorder_info mainbox">
    <p><span class="c7">下单日期：</span>${order.orderTime!''}</p>
    <p><span class="c7">订单总价：</span><span class="red fs11">￥<#if order??><#if order.statusId==3>${order.totalLeftPrice?string("0.00")}<#else>${order.totalPrice?string("0.00")}</#if></#if></span></p>
    <p><span class="c7">订单编号：</span>${order.orderNumber!''}<span class="fc"><#if order.statusId==7>（已取消）</#if></span></p>
    <p><span class="c7">支付方式：</span><#if order??>${order.payTypeTitle!''}</#if><span class="red"><#if order??>
                <#if order.statusId==1>
                    (待确认)
                <#elseif order.statusId==2>
                    (待付款)
                <#elseif order.statusId==3>
                   (待付尾款)
                <#elseif order.statusId==4>
                    (待服务)
                <#elseif order.statusId==5>
                    (待评价)
                <#elseif order.statusId==6>
                    (已完成)
                <#elseif order.statusId==8>
                    (支付失败)
                </#if>
            </#if></span></p>
  </menu>
</section>
<section class="whitebg" style="margin-top:1px;">
  <menu class="myorder_info mainbox">
    <p><span class="c7">收货人：</span>${order.shippingName!''}</p>
    <p><span class="c7">手机号码：</span>${order.shippingPhone!''}</p>
    <P><span class="c7">同盟店：</span>${order.shopTitle!''}</p>
    <p><span class="c7">预约时间：</span>${order.appointmentTime!''}</p>
    <#if order??>
        <#if order.isNeedInvoice>
            <p><span class="c7">发票抬头：</span>${order.invoiceTitle!''}</p>
        </#if>
    </#if>
    
  </menu>
</section>
<p class="ta-c lh40">商品清单</p>
<menu class="whitebg mymenu_list">
  <#assign totalPrice = 0>
  <#if order?? && order.orderGoodsList??>
    <#list order.orderGoodsList as og>
        <a href="/touch/goods/${og.goodsId?c}">
            <b><img src="${og.goodsCoverImageUri}" width="64" height="64"/></b>
            <p>${og.goodsTitle}<span class="sp1">￥${og.price?string("#.##")}</p>
            <p class="p1">${og.goodsSubTitle}<span class="sp2">X${og.quantity!''}</p>
            <div class="clear"></div>
        </a>
        <#if og.price??>
            <#assign totalPrice=totalPrice+og.price*og.quantity>
        </#if>
    </#list>
  </#if>
 
</menu>
<section class="whitebg" style="margin-top:1px;">
  <menu class="myorder_info mainbox">
    <p><span class="c7">商品总价：</span>￥${totalPrice?string("#.##")}</p>
    <p><span class="c7">同盟店地址：</span><#if diysite??>${diysite.address!''}</#if></p>
    <p><span class="c7">优惠：</span>￥<#assign privilege=totalPrice-order.totalPrice>${privilege?string("#.##")}</p>
    <p class="mt10"><span class="c9">应付总额：</span><span class="red fs11">￥<#if order??><#if order.statusId==3>${order.totalLeftPrice?string("0.00")}<#else>${order.totalPrice?string("0.00")}</#if></#if></span></p>
  </menu>
</section>
<div class="clear h40"></div>
<footer class="comfoot main">
    <a href="/user/order?id=${order.id?c}">电脑版</a>
    <a href="/touch/user/order?id=${order.id?c}">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<div class="buyfoot_bg"></div>
<footer class="buyfoot">
  <div class="mainbox">
    <#if order.statusId==7>
    <#else>
        <#if order.statusId==2>
           <a class="fr" href="/touch/order/dopay/${order.id?c}">付款</a>
        <#elseif order.statusId==3>
           <a class="fr" href="/touch/order/dopayleft/${order.id?c}">付款</a>
        <#elseif order.statusId==5>
           <a class="fr" href="#">评价</a>
        </#if>
    </#if>
    <div class="clear"></div>
  </div>
</footer>
</body>
</html>
