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
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">

<script type="text/javascript">
$(document).ready(function(){
  
});
 //发送AJAX请求
        function sendAjaxUrl(winObj, postData, sendUrl) {
            $.ajax({
                type: "post",
                url: sendUrl,
                data: postData,
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.dialog.alert('尝试发送失败，错误信息：' + errorThrown, function () { }, winObj);
                },
                success: function (data) {
                    if (data.code == 0) {
                        winObj.close();
                        $.dialog.tips(data.msg, 2, '32X32/succ.png', function () { location.reload(); }); //刷新页面
                    } else {
                        $.dialog.alert('错误提示：' + data.msg, function () { }, winObj);
                    }
                }
            });
        }
        
   
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>订单</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>

<div class="main comcheck">
  <nav>
    <a <#if !status_id?? || status_id==0>class="sel"</#if> href="/touch/user/order/list/0"><p>全部订单</p></a>
    <a <#if status_id?? && status_id==2>class="sel"</#if> href="/touch/user/order/list/2"><p>待付款</p></a>
    <a <#if status_id?? && status_id==3>class="sel"</#if> href="/touch/user/order/list/3"><p>待付尾款</p></a>
    <a <#if status_id?? && status_id==5>class="sel"</#if> href="/touch/user/order/list/5"><p>待评价</p></a>
    <a <#if status_id?? && status_id==6>class="sel"</#if> href="/touch/user/order/list/6"><p>已完成</p></a>
  </nav>
</div><!--comcheck END-->

<menu class="whitebg mymenu_list">
<#if order_page??>
    <#list order_page.content as order>
     <script>
                         //取消订单
                    function OrderCancel${order.id?c}() {
                        var dialog = $.dialog({
                            title: '取消订单',
                            content: '操作提示信息：<br />1、取消的订单，将不在购买流程中显示，您可以到取消的订单中查阅；<br />2、请单击相应按钮继续下一步操作！',
                            min: false,
                            max: false,
                            lock: true,
                            icon: 'confirm.gif',
                            button: [{
                                name: '直接取消',
                                callback: function () {
                                    var orderNumber = $.trim($("#spanOrderNumber${order.id?c}").text());
                                    var postData = { "orderNumber": orderNumber, "type": 1 };
                                    //发送AJAX请求
                                    sendAjaxUrl(dialog, postData, "/touch/user/order/edit");
                                    return false;
                                },
                                focus: true
                        }, {
                            name: '关闭'
                        }]
                    });
        
                }
                //作废订单
                    function OrderInvalid${order.id?c}() {
                        var dialog = $.dialog({
                            title: '取消订单',
                            content: '操作提示信息：<br />确定删除订单？',
                            min: false,
                            max: false,
                            lock: true,
                            icon: 'confirm.gif',
                            button: [{
                                name: '确定删除',
                                callback: function () {
                                    var order_no = $.trim($("#spanOrderNumber${order.id?c}").text());
                                    var postData = { "orderNumber": order_no, "type": 2 };
                                    //发送AJAX请求
                                    sendAjaxUrl(dialog, postData, "/touch/user/order/edit");
                                    return false;
                                }
                            }, {
                                name: '关闭'
                            }]
                        });
                    }
                        </script>
        <h4>订单号：<a href="/touch/user/order?id=${order.id?c}" id="spanOrderNumber${order.id?c}">${order.orderNumber!''}</a>        
        <span>共<b class="red">${order.orderGoodsList?size}</b>件商品，总计<b class="red">￥${order.totalPrice?string("0.00")}</b>元</span></h4>
        <#if order.statusId==2>
            <input type="button" value="取消订单" onclick=" OrderCancel${order.id?c}();" style="margin-left:auto;margin-right:2px; display:block"></input>
        <#elseif order.statusId==6> 
            <input type="button" value="删除订单" onclick="OrderInvalid${order.id?c}()" style="margin-left:auto;margin-right:2px; display:block"></input>
        <#elseif order.statusId==5> 
            <input type="button" value="评价订单" onclick="javascript:window.location.href='/touch/user/comment/list';" style="margin-left:auto;margin-right:2px; display:block"></input>
        </#if>
        <#list order.orderGoodsList as og>
            <a href="/touch/goods/${og.goodsId?c}">
                <b><img src="${og.goodsCoverImageUri}" /></b>
                <p>${og.goodsTitle!''}<span class="sp1">￥${og.price?string("0.00")}</span></p>
                <p class="p1">${og.goodsSubTitle!''}<span class="sp2">X${og.quantity!''}</span></p>
                <div class="clear"></div>
            </a>
        </#list>
    </#list>
</#if>
</menu>

<#--
<a class="ma15 ta-c block" href="#"><img src="/touch/images/more.png" height="20" /></a>
-->

</body>
</html>
