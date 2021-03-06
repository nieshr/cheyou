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
    <a <#if status_id?? && status_id==6>class="sel"</#if> href="/touch/user/comment/list?statusId=1"><p>已评价订单</p></a>
    <a <#if status_id?? && status_id==5>class="sel"</#if> href="/touch/user/comment/list?statusId=0"><p>待评价订单</p></a>
  </nav>
</div><!--comcheck END-->

<menu class="whitebg mymenu_list">
<#if order_page??>
    <#list order_page.content as order>                   
            <#list order.orderGoodsList as og>
                <a href="/touch/goods/${og.goodsId?c}">
                    <b><img src="${og.goodsCoverImageUri}" /></b>
                    <p>${og.goodsTitle!''}<span class="sp1">￥${og.price?string("0.00")}</span></p>
                    <p class="p1">${og.goodsSubTitle!''}<span class="sp2">X${og.quantity!''}</span></p>
                    <div class="clear"></div>
                </a>
                <#if og.isCommented?? && og.isCommented>
                    <input type="button" value="查看评价" onclick="javascript:window.location.href='/touch/user/comment/edit?goodsId=${og.id?c}&orderId=${order.id?c}'" style="margin-left:auto;margin-right:2px; display:block"></input>
                <#else>
                    <input type="button" value="评价商品" onclick="javascript:window.location.href='/touch/user/comment/edit?goodsId=${og.id?c}&orderId=${order.id?c}'" style="margin-left:auto;margin-right:2px; display:block">
                </#if>
                
            </#list>
    </#list>
</#if>
</menu>

<#--
<a class="ma15 ta-c block" href="#"><img src="/touch/images/more.png" height="20" /></a>
-->

</body>
</html>
