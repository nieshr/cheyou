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
<script src="/client/js/jquery.cookie.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/touch/js/common.js"></script>
<script src="/touch/js/order_info.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/bankLogo.css" rel="stylesheet" type="text/css" />
<!-- add link 2015-7-31 11:24:56 mdj -->
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<link href="/client/css/style.css" rel="stylesheet" type="text/css" />
<!-- add link 2015-7-31 11:24:56 mdj end -->
<script type="text/javascript">
$(document).ready(function(){
    $("#form1").Validform({
        tiptype: 1
    });
});
</script>
<script type="text/javascript">
    var forPaymentFllow = true;
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>立即购买</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->

<form id="form1" name="form1" action="/touch/order/buysubmit" method="post">
<div class="mainbox">
    <table class="address_tab">
            <tr>
                <th width="140">
                    <p>安装信息</p>
                    <a class="red" href="javascript:toggleNewAddress();">新增安装信息</a>
                </th>
                <td>
                    <input id="input-address-id" type="hidden" name="addressId" value="" datatype="n" nullmsg="请选择安装信息!"/>
                    <#if user.shippingAddressList?? && user.shippingAddressList?size gt 0>
                        <#list user.shippingAddressList as address>
                            <a href="javascript:;" onclick="javascript:selectAddress(this, ${address.id?c});">
                                <p>姓名：${address.receiverName!''}</p>
                           <#--     <p class="p1">收货地址：${address.province!''}${address.city!''}${address.disctrict!''}${address.detailAddress!''}</p>  -->
                                <p>手机：${address.receiverMobile!''}</p>                              
                                <p>车牌：${address.receiverCarcode!'' }</p>
                                <P>车型：${address.receiverCartype!'' }</P>
                            </a>
                        </#list>                                                 
                    </#if>
                   
                    <div class="clear"></div>
                </td>
            </tr>
        </table>
        
        <p class="red">请填写真实信息，以便到店核实，信息不符的，同盟店有权拒绝服务</p>
        <div id="addressForm" class="hide">
            <table class="mymember_address">
                <tbody>
                  <tr>
                    <th>*姓名：</th>
                    <td>
                        <input class="mytext" id="receiverName" value="" type="text">
                    </td>
                  </tr>
                  <tr>
                    <th>*手机：</th>
                    <td>
                        <input class="mytext" id="mobile" value="" type="text">
                    </td>
                  </tr>
                  <tr>
                  <th>*车牌：</th>
                  <td>
                  <input class="mytext" id="receiverCarcode" value="" type="text"/>
                  </td>
                  </tr>
                  <tr>
                  <th>*车型：</th>
                  <td>
                   <input class="mytext" id="receiverCartype" value="" type="text"/>
                  </td>
                  </tr>
                  <tr>
                    <th></th>
                    <td><input onclick="javascript:submitAddress();" class="mysub" type="button" value="保存"></td>
                  </tr>
                </tbody>
            </table>
        </div>

  <p class="address">选择线下同盟店：</p>
  <div class="address">
    <select name="shopId" style="width:100%;" datatype="n" nullmsg="请选择同盟店" errormsg="请选择同盟店">
        <option value="">请选择同盟店</option>
        <#if shop_list??>
            <#list shop_list as item>
                <option value="${item.id?c}">${item.title!''}</option>
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
 <!-- <p class="address">使用粮草：
    <span>（可用：${total_point_limit!'0'}）</span>
  </p>
  <input type="text" class="address" value="0" name="pointUse" value="0" onchange="pointChange(this, $(this).val(), ${total_point_limit!'0'});"/>
  <p class="address">选择优惠券：</p>
  <div class="address">
    <select id="couponSelect" name="couponId" style="width:100%;" onchange="couponChange();">
        <option value="" fee="0">不使用优惠券</option>
        <#if coupon_list??>
            <#list coupon_list as item>
                <option value="${item.id?c}">${item.typeTitle!''}</option>
            </#list>
        </#if>
    </select>
  </div> -->
  <p class="address">选择支付方式：</p>
  <ul class="paystyle">
    <#assign maxMethodCount=5/>
    <#assign changePayMethod=false/>
    <#include "/client/paybox_common.ftl" />
    
  </ul>
  <div class="clear"></div>
  <p class="address">留言：</p>
  <input type="text" class="address" name="userMessage" value="" />
  
    <#assign totalQuantity=0>
    <#assign totalPrice=0>
    <#if buy_goods_list??>
            <table class="car_list">
            <tr>
                <th colspan="2">商品信息</th>
                <th>价格</th>
                <th>数量</th>
                <th>合计</th>
            </tr>
            <#list buy_goods_list as sg>
                <#assign totalQuantity=totalQuantity+sg.quantity>
                <#assign totalPrice=totalPrice+(sg.price*sg.quantity)>
                <tr>
                    <td width="110"><a href="/touch/goods/${sg.goodsId?c}?qiang=1"><img src="${sg.goodsCoverImageUri!''}" width="100" height="100"/></a></td>
                    <td width="300" style="text-align:left;"><a href="/touch/goods/${sg.goodsId?c}?qiang=1">${sg.goodsTitle!''}</a></td>
                    <td width="110" style="text-align:center;"><#if sg.price??>${sg.price?string("0.00")}</#if></td>
                    <td width="100" style="text-align:center;">×${sg.quantity!''}</td>
                    <td class="red ml100">￥<#if sg.price?? && sg.quantity??>${(sg.price * sg.quantity)?string("0.00")}</#if></td>
                </tr>
            </#list>
            </table>
        </#if>
        
  <p class="address ta-r">共<#if totalQuantity??>${totalQuantity!'0'}</#if>件商品，合计￥<span id="totalFee" class="red">${totalPrice?string('0.00')}</span></p>
</div>

<div class="carfoot_bg"></div>
<footer class="carfoot">
  <div class="mainbox">
    
    <p>共<span class="red"><#if totalQuantity??>${totalQuantity!'0'}</#if></span>件，<span class="red">￥${totalPrice?string('0.00')}</span></p>
    <input class="sub" type="submit" value="提交订单（<#if totalQuantity??>${totalQuantity!'0'}</#if>）" />
  </div>
</footer>
</form>
</body>
</html>
