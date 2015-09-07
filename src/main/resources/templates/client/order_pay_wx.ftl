<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=${charset!'UTF-8'}" />
<title>订单支付成功</title>
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

<div class="wrapper mail_box main">
    <div class="mail_box_inner">
        <div class="area primary">
            <div class="pay_msg qr_default" id="payMsg">
                <div class="area_bd" id="qr_normal" style="display: block;">
                    <span class="qr_img_wrapper">
                        <img class="qrcode" alt="二维码" id="QRcode" src="/order/payqrcode">
                    </span>
                    <div class="msg_default_box">
                        <i class="icon60_qr"></i>
                        <p>
                            请使用微信扫描<br>
                            二维码以完成支付
                        </p>
                    </div>
                    <div class="msg_box">
                        <i class="icon60_qr"></i>
                        <p><strong>扫描成功</strong>请在手机确认支付</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="area second">
            <div class="pay_bill shopping">
                <div class="area_hd">
                    <h2>支付清单</h2>
                    <span class="icon_wrapper"><i class="icon60_pay"></i></span>
                </div>
                <div class="area_bd">
                    <h3 class="pay_money"><span>¥</span>${total_price}</h3>
                    <div class="pay_bill_unit no_extra">
                        <dl>
                            <dt>车有同盟</dt>
                            <dd>订单号：${order_number!''}</dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        <div class="aside">
            <div class="pay_widget help">
                <div class="pay_widget_hd">
                    <i class="icon30_add_on"></i>
                </div>
            </div>
        </div>
    </div>
</div>


<style type="text/css">
    .mail_box {border-top:1px solid #ddd; width:920px;position: relative;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.35);background: #FFF;}
    .mail_box_inner {position: relative;bottom: -10px;overflow: hidden;padding: 60px 170px 100px;}
    .area {text-align: center;}
    .qr_img_wrapper {display: block;position: relative;height: 306px;}
    .qr_img_wrapper .qrcode {width: 301px;height: 301px;}
    .guide {display: none;position: absolute;top: 0;margin-left: -101px;}
    .msg_default_box {padding: 12px 0;border: 1px solid #2B4D69;background-color: #445F85;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;text-align: center;letter-spacing: 6px;color: #FFF;}
    .pay_msg .msg_box, .pay_msg .msg_default_box {display: inline-block;width: 258px;}
    .icon60_qr {display: inline-block;width: 60px;height: 60px;vertical-align: middle;background:url(images/icon60_qr15b813.png) no-repeat 0 0;}
    .msg_default_box i {margin-left: -16px;*float:left;*margin-left:10px;}
    .qr_default .icon60_qr {background-position: 0 -60px;}
    .msg_default_box p {display: inline-block;vertical-align: middle;letter-spacing: normal;text-align: left;font-size: 16px;*float:left;*line-height:30px;}
    .msg_box {padding: 12px 0;border: 1px solid #259483;background-color: #4CA698;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);color: #FFF;text-align: center;letter-spacing: 6px;}
    .pay_msg .msg_box, .pay_msg .msg_default_box {display: inline-block;width: 258px;}
    .qr_default .msg_box {display: none;}
    .msg_box strong {display: block;color: #FFF;font-size: 15px;font-weight: 400;}
    .pay_bill .area_hd {border-bottom: 3px solid #E0E3EB;padding-bottom: 4px;height: 60px;}
    .area_hd h2 {display: none;}
    .pay_bill .area_hd .icon_wrapper {display: inline-block;position: relative;top: 34px;padding-left: 12px;padding-right: 14px;background-color: #FFF;}
    .icon60_pay {display: inline-block;width: 60px;height: 60px;vertical-align: middle;background:url(images/icon60.png) no-repeat 0 0;}
    .shopping .icon60_pay {background-position: 0 0;}
    .area_bd {display: none;}
    .pay_bill .area_bd {display: block;}
    .pay_money {padding-bottom: 20px;padding-top: 50px;color: #585858;font-size: 60px;font-weight: 400;border-bottom: 1px solid #D0D8E4;line-height: 68px;}
    .pay_bill_unit {padding: 18px 0 40px;}
    .pay_bill_unit dl {border-bottom: 1px solid #E5E7EA;}
    .pay_bill_unit dt {color: #4A4A4A;font-size: 20px;line-height: 24px;}
    .pay_bill_unit dd {padding-top: 4px;padding-bottom: 16px;color: #666;}
    .pay_bill_info {padding-top: 10px;line-height: 26px;}
    .pay_bill_info p {overflow: hidden;}
    .pay_bill_info label {float: left;font-size: 14px;color: #8E8E8E;}
    .pay_bill_info .pay_bill_value {float: right;}
    .aside {clear: both;margin-top: 14px;padding-top: 20px;border-top: 3px solid #E0E3EB;}
    .pay_widget_hd, .pay_widget_bd {display: inline-block;vertical-align: middle;}
    .pay_widget_hd {padding-top: .35em;}
    .icon30_add_on {display: inline-block;width: 30px;height: 30px;vertical-align: middle;background:url(images/icon30_add_on1518d5.png) no-repeat 0 0;}
    .help .icon30_add_on {background-position: 0 0;}
    .pay_widget_hd, .pay_widget_bd {display: inline-block;vertical-align: middle;}
    .widget_name {color: #6C6C6C;font-size: 12px;font-weight: 400;}
    .widget_desc {margin-top: -4px;font-size: 14px;}
</style>

<#include "/client/common_footer.ftl" />
</body>
</html>
<!--结束-->
