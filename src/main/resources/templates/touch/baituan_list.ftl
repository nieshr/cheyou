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
    <p>百人团购</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>

<div class="main comcheck">
  <nav>
    <a <#if !type??>class="sel"</#if> href="/touch/promotion/baituan"><p>正在进行</p></a>
    <a <#if type?? && type=="ongoing">class="sel"</#if> href="/touch/promotion/baituan?type=ongoing"><p>即将开始</p></a>
    <a <#if type?? && type=="passed">class="sel"</#if> href="/touch/promotion/baituan?type=passed"><p>往期团购</p></a>
  </nav>
</div><!--comcheck END-->

<menu class="whitebg myorder_list">
    <#if baituan_goods_page??>
        <#list baituan_goods_page.content as item>
<script>
$(document).ready(function(){
    setInterval("timer${item_index}()",1000);
});

function timer${item_index}()
{
<#if item.isGroupSaleHundred && item.groupSaleHundredStartTime < .now && item.groupSaleHundredStopTime gt .now>
    var ts = (new Date(${item.groupSaleHundredStopTime?string("yyyy")}, 
                parseInt(${item.groupSaleHundredStopTime?string("MM")}, 10)-1, 
                ${item.groupSaleHundredStopTime?string("dd")}, 
                ${item.groupSaleHundredStopTime?string("HH")}, 
                ${item.groupSaleHundredStopTime?string("mm")}, 
                ${item.groupSaleHundredStopTime?string("ss")})) - (new Date());//计算剩余的毫秒数
    if (0 == ts)
    {
        window.location.reload();
    }
    
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#timeLeft${item_index}").html("剩余时间：<span>"
                    + dd + "</span>天<span>"
                    + hh + "</span>时<span>"
                    + mm + "</span>分<span>"
                    + ss + "</span>秒");
</#if>
}
</script>
    <a href="/touch/goods/${item.id?c}?qiang=100">
        <b><img src="${item.groupSaleImage!''}" /></b>
        <p class="p1">${item.title!''}</p>
        <p>
            预付价<span  class="red p3">￥<#if item.groupSalePrePayPrice??>${item.groupSalePrePayPrice?string("0.00")}</#if></span>
            
        </p>
        <p class="p2">已售：<span class="red">${item.groupSaleHundredSoldNumber!'0'}</span>份</p>
        <p>
     <!--       三人团<span  class=" p3">￥<#if item.groupSaleThreePrice??>${item.groupSaleThreePrice?string("0.00")}</#if></span>
            
        </p>
        <p>
            五人团<span class=" p3">￥<#if item.groupSaleSevenPrice??>${item.groupSaleSevenPrice?string("0.00")}</#if></span>
            
        </p>
        <p>
            十人团<span class=" p3">￥<#if item.groupSaleTenPrice??>${item.groupSaleTenPrice?string("0.00")}</#if></span>
            
        </p>   -->
        <div class="clear"></div> 
    </a>
        </#list>
    </#if>
</menu>

<#--
<a class="ma15 ta-c block" href="#"><img src="images/more.png" height="20" /></a>
-->
</body>
</html>
