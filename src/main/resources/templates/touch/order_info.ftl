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
<script src="/touch/js/order_info.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />
<!-- add link 2015-7-31 11:24:56 mdj -->

<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>

<link href="/client/css/style.css" rel="stylesheet" type="text/css" />

<!-- add link 2015-7-31 11:24:56 mdj end -->
<script type="text/javascript">
$(document).ready(function(){
  
});
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>购物车</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->

<div class="mainbox">
  <p class="address">选择线下同盟店：</p>
  <div class="address">
    <select style="width:100%;">
        <option value="">请选择同盟店</option>
        <#if diy_site_list??>
            <#list diy_site_list as item>
                <option value="item.id">${item.title!''}</option>
            </#list>
        </#if>
    </select>
  </div>
  <!-- 2015-7-31 10:33:03  mdj  add datePicker -->
  <section class="order_check address" style="width:90%;">
            <p class="address" style="float:left">选择预约安装时间:</p> 
            <input name="appointmentTime" type="text" value="" datatype="*" class="text input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" errormsg="请选择预约安装时间" nullmsg="请选择预约安装时间">
            <a href="javascript:;"><img src="/client/images/content/rl.png" /></a>
        </section>
  <!-- end -->
  <p class="address">选择粮草：<span class="absolute-r">可用粮草（<b class="red">${total_point_limit!'0'}</b>）</span></p>
  <input type="text" class="address" value="" />
  <p class="address">选择优惠券：</p>
  <div class="address">
    <select id="couponSelect" name="couponId" style="width:100%;" onchange="couponChange();">
        <#if coupon_list??>
            <option value="" fee="0">不使用优惠券</option>
            <#list coupon_list as item>
                <option value="${item.id}">${item.typeTitle!''}</option>
            </#list>
        </#if>
    </select>
  </div>
  <p class="address">选择支付方式：</p>
  <ul class="paystyle">
    <#if pay_type_list??>
        <#list pay_type_list as pay_type>
            <li><input type="radio" datatype="n" value="${pay_type.id!''}"/><span>${pay_type.title!''}</span></li>
        </#list>
    </#if>
  </ul>
  <div class="clear"></div>
  <p class="address">留言：</p>
  <input type="text" class="address" name="userMessage" value="" />
  
    <#assign totalQuantity=0>
    <#assign totalPrice=0>
    <#if selected_goods_list??>
        <#list selected_goods_list as sg>
            <#assign totalQuantity=totalQuantity+sg.quantity>
            <#assign totalPrice=totalPrice+(sg.price*sg.quantity)>
        </#list>
    </#if>
        
  <p class="address ta-r">共<#if totalQuantity??>${totalQuantity!'0'}</#if>件商品，合计￥<span id="totalFee" class="red">${totalPrice?string('0.00')}</span></p>
</div>

<div class="carfoot_bg"></div>
<footer class="carfoot">
  <div class="mainbox">
    
    <p>共<span class="red"><#if totalQuantity??>${totalQuantity!'0'}</#if></span>件，<span class="red">￥${totalPrice?string('0.00')}</span></p>
    <input class="sub" type="submit" value="结算（<#if totalQuantity??>${totalQuantity!'0'}</#if>）" />
  </div>
</footer>
</body>
</html>
