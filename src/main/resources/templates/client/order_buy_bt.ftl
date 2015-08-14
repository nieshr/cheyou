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
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/cart.js"></script>
<script src="/client/js/order_info.js"></script>
<script src="/client/js/jquery.cityselect.js"></script>
<script src="/client/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>

<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />
<link href="/client/style/bankLogo.css" rel="stylesheet" type="text/css" />

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
        tiptype: 1
    });
    
    $("#address").citySelect({
        nodata:"none",
        <#if address?? && address.province??>prov: "${address.province!''}",</#if>
        <#if address?? && address.city??>city: "${address.city!''}",</#if>
        <#if address?? && address.disctrict??>dist: "${address.disctrict!''}",</#if>
        required:false
    }); 
});
</script>
<script type="text/javascript">
    var forPaymentFllow = true;
</script>



</head>
<body>
<#include "/client/common_header.ftl" />

<div class="main">
    <menu class="car_top">
        <p class="sel" style="z-index:10; width:34%;">我的购物车<i></i></p>
        <p class="sel" style="z-index:8;">我的订单信息<i></i></p>
        <p>支付成功</p>
        <div class="clear"></div>
    </menu>
    
    <div class="clear h30"></div>
    
    <form id="form1" name="form1" action="/order/buysubmit" method="post">
        
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
              <#--    <th>姓名</th>  -->
                  </tr>
              <#--    
                  <tr>
                    <th>*地区：</th>
                    <td>
                      <div id="address">
                      <select id="prov" class="prov" style="width: 100px;"></select>
                      <select id="city" class="city" style="width: 100px;"></select>
                      <select id="dist" class="dist" style="width: 100px;"></select>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th>*详细地址：</th>
                    <td>
                        <input class="mytext" id="detailAdd" value="" type="text">
                    </td>
                  </tr>
                  <tr>
                    <th>*邮政编码：</th>
                    <td>
                        <input class="mytext" id="postcode" value="" type="text">
                    </td>
                  </tr>
                  -->
                  <tr>
                    <th>*手机：</th>
                    <td>
                        <input class="mytext" id="mobile" value="" type="text">
                    </td>
                  </tr>
                  <tr>
                  <th>车牌（选填）：</th>
                  <td>
                  <input class="mytext" id="receiverCarcode" value="" type="text"/>
                  </td>
                  </tr>
                  <tr>
                  <th>车型（选填）：</th>
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
   
      
     
        <div class="clear h20"></div>
        
       <section class="order_check fl">
         <h3>选择线下同盟店</h3>
          
             
            <select name="shopId" id="formselect" datatype="n" nullmsg="请选择同盟店" errormsg="请选择同盟店">
                <option>请选择</option>
                <#if shop_list??>
                    <#list shop_list as item>
                        <option value="${item.id?c}">${item.title!''}</option>
                        <p>${item.address}</p>
                    </#list>                  
                </#if>
            </select>
       <#--
                 <#if shop_list??>
                     <#list shop_list as item>
                        <table id="${item.id}" style="display:">
                             <tr>
                             
                               <td>${item.address}</td>
                             </tr>
                         </table>
                       </#list>
                 </#if>
        -->          
        </section>
        <section class="order_check fr" style="width:40%;">
            <h3>选择预约安装时间</h3>
            <input name="appointmentTime" type="text" value="" datatype="*" class="text input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" errormsg="请选择预约安装时间" nullmsg="请选择预约安装时间">
            <a href="javascript:;"><img src="/client/images/content/rl.png" /></a>
        </section>
        <div class="clear h20"></div>
        <#assign maxMethodCount=5/>
        <#assign changePayMethod=false/>
        <#include "/client/paybox_common.ftl" />
        
        <div class="clear h20"></div>
        
        <h3 class="car_tit">给商城留言：</h3>
        <div class="car_pay">
            <input class="text" name="userMessage" type="text" value="" />
        </div>
        
        <div class="clear h20"></div>
        
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
                    <td width="110"><a href="/goods/${sg.goodsId}?qiang=100"><img src="${sg.goodsCoverImageUri!''}" width="100" height="100"/></a></td>
                    <td width="800" style="text-align:left;"><a href="/goods/${sg.goodsId}">${sg.goodsTitle!''}</a></td>
                    <td width="150" style="text-align:center;"><#if sg.price??>${sg.price?string("0.00")}</#if></td>
                    <td width="150" style="text-align:center;">×${sg.quantity!''}</td>
                    <td class="red ml100">￥<#if sg.price?? && sg.quantity??>${(sg.price * sg.quantity)?string("0.00")}</#if></td>
                </tr>
            </#list>
            </table>
        </#if>
        
        <div class="clear h20"></div>
      
        <div class="car_btn">
            <a class="ml20 fc" href="javascript:window.history.back();">返回上一页</a>
            <span>应付总额：商品价格（¥<b id="goodsFee">${totalPrice?string("0.00")}</b>）
                + 支付手续费（¥<b id="payTypeFee">0.00</b>）
                - 粮草抵扣（￥<b id="pointFee">0.00</b>）
                - 优惠券（￥<b id="couponFee">0.00</b>）
                = ￥<b class="red fs18" id="totalFee">${totalPrice?string('0.00')}</b></span>
            <input class="sub" type="submit" value="提交订单" />
        </div>
      
        <div class="clear"></div>
    </form>
</div>

<#include "/client/common_footer.ftl" />

</body>
</html>