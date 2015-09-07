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
    indexBanner("box","sum",300,5000,"num");
    searchTextClear(".comserch_text","搜索关键字","#999","#333");
});

function checkTime(i)  
{  
    if (i < 10) {  
        i = "0" + i;  
    }  
    return i;  
}
</script>


<!--
    --------------------------start-----------------------------
    判断有无一元秒拍商品，
    添加倒计时
    @author libiao
-->
<#if flash_goods_list??>
     <#list flash_goods_list as item>
<script>
$(document).ready(function(){
    setInterval("timer${item_index}()",1000);
});


function timer${item_index}()
{
    <#if item.isFlashSale && item.flashSaleStartTime < .now && item.flashSaleStopTime gt .now>
    var ts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")})) - (new Date());//计算剩余的毫秒数
    
    var allts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")}))
               - (new Date(${item.flashSaleStartTime?string("yyyy")}, 
                parseInt(${item.flashSaleStartTime?string("MM")}, 10)-1, 
                ${item.flashSaleStartTime?string("dd")}, 
                ${item.flashSaleStartTime?string("HH")}, 
                ${item.flashSaleStartTime?string("mm")}, 
                ${item.flashSaleStartTime?string("ss")}));//总共的毫秒数
                
    if (0 == ts)
    {
        window.location.reload();
    }
    
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
     if(ss < 0){
    	ss = 0;
    }
    if(mm < 0){
    	mm = 0;
    }
    if(hh < 0){
    	hh = 0;
    }
    if(dd < 0){
    	dd = 0;
    }
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#timeLeft${item_index}").html("<b>"+dd+"</b>:<b>"+hh+"</b>:<b>"+mm+"</b>:<b>"+ss+"</b>");
    console.debug(dd-hh-mm-ss);
    var price = ${item.flashSalePrice?string("0.00")} * ts / allts;
    if(price < 1){
    	price = 1;
    }
    //var s_x = Math.round(price).toString();
    var s_x = price.toFixed(2).toString();
    
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    
    $("#flashPrice${item_index}").html("￥" + s_x);              
</#if>
}

</script>
    </#list>
</#if>
<!--
-----------------------------------END------------------------------ 
-->


</head>

<body>
<header class="index_top">
  <div class="main">
    <section class="comserch">
        <form action="/touch/search" method="get">
            <input type="text" class="text comserch_text" name="keywords" value="${keywords!keywords_list[0].title}"/>
            <input type="submit" class="sub" value="" />
        </form>
    </section>
    <a class="a1" style="top:3px; left:1;" href="/touch"><img src="<#if site??>${site.logoUri!''}</#if>" height="40" /></a>
    <a class="a2" href="/touch/cart">
    	<img src="/touch/images/qq.png" height="30" />
    	<span class="sp1"><#if cart_goods_list??>${cart_goods_list?size}<#else>0</#if></span>
    </a>
  </div>
</header>
<section id="box">
  <ul id="sum">
    <#if big_scroll_ad_list??>
        <#list big_scroll_ad_list as item>
            <li><a href="${item.linkUri!''}" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if>><img src="${item.fileUri!''}" /></a></li>
        </#list>
    </#if>
  </ul>
  <div class="clear"></div>
</section><!--我是banner-->
<section class="indexnav">
  <nav class="main">
    <#if touch_navi_item_list??>
        <#list touch_navi_item_list as item>
            <#if item_index < 8>
                <li>
                  <a href="${item.linkUri}"><img src="${item.iconUri!''}" /><p>${item.title!''}</p></a>
                </li>
            </#if>
        </#list>
    </#if>
  </nav>
  <div class="clear"></div>
</section>
<!--
<div class="index_gg">
    <span>商城快报</span>
    <#if news_page??>
        <#list news_page.content as item>
            <a href="/info/content/${item.id}?mid=12">${item.title!''}</a>
        </#list>
    </#if>
</div>
-->

<section class="index_ad center mt10">
  <menu>
    <#if touch_middle_ad_list??>
        <#list touch_middle_ad_list as item>
            <#if item_index < 2>
                <a class="pr5" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                    <img src="${item.fileUri!''}"/>
                </a>
            </#if>
        </#list>
    </#if>
  </menu>
</section>

<h3 class="indextit mainbox mt10">

<span style="float:left;margin-bottom:1%;">1元抢拍</span>
<span style="display:block;position:relative;"><a style="margin-left:2%;float:left;right:initial;top:2px;font-size:0.7em;color:red;" href="/touch/info/content/94?mid=12">抢拍细则</a></span>
<#--<a style="margin-right:57%;float:left;font-size:0.6em;color:red;" href="/touch/info/content/94?mid=12">抢拍细则</a>-->
<a href="/touch/promotion/miao">+更多</a></h3>

<section class="indexlist">
  <table style="border-collapse:separate; border-spacing:5px;">
    <tr>   
        <#if flash_goods_list??>
            <#list flash_goods_list as item>
                <#if item_index < 3>
                    <td style="width:30%;">
                        <a href="/touch/goods/${item.id?c}?qiang=1">
                          <p class="fs08"  style="height:15px;">${item.title!''}</p>
                          <p class="fs07 c9" style="height:30px;">${item.subTitle!''}</p>
                          <p id="timeLeft${item_index}"><b>00</b>天<b>00</b>时<b>00</b>分<b>00</b>秒</p>
                          <p class="fs07 red" id="flashPrice${item_index}">￥<#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></p>
                          <img src="${item.coverImageUri!''}" width="185" />
                        </a>
                    </td>
                </#if>
            </#list>
        </#if>
    </tr>
    <tr>
        <#if flash_goods_list??>
            <#list flash_goods_list as item>
                <#if item_index gt 2 && item_index < 6>
                    <td style="width:30%;">
                        <a href="/touch/goods/${item.id?c}?qiang=1">
                          <p class="fs08"  style="height:15px;">${item.title!''}</p>
                          <p class="fs07 c9"  style="height:30px;">${item.subTitle!''}</p>
                          <p id="timeLeft${item_index}"><b>00</b>天<b>00</b>时<b>00</b>分<b>00</b>秒</p>
                          <p class="fs07 red" id="flashPrice${item_index}">￥<#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></p>
                          <img src="${item.coverImageUri!''}" width="185"/>
                        </a>
                    </td>
                </#if>
            </#list>
        </#if>
    </tr>
  </table>
</section>

<h3 class="indextit mainbox">
<span style="float:left;margin-bottom:1%;">十人团购</span>
<span style="display:block;position:relative;"><a style="margin-left:2%;float:left;right:initial;top:2px;font-size:0.7em;color:red;" href="/touch/info/content/40?mid=12">团购细则</a></span>
<a href="/touch/promotion/tuan">+更多</a></h3>
<section class="indexlist">
  <table style="border-collapse:separate; border-spacing:5px;">
    <tr>  
      <#if tuan_cur_page??>
        <#list tuan_cur_page.content as item>
            <#if item_index lt 3>
                <td style="width:30%;">
                    <a href="/touch/goods/${item.id?c}?qiang=3">
                      <p class="fs08">${item.title!''}</p>
                      <p class="fs07 c9">${item.subTitle!''}</p>
                      <p class="fs07 red">￥<#if item.groupSalePrice??>${item.groupSalePrice?string("0.00")}</#if></p>
                      <img src="${item.coverImageUri!''}" width="185" />
                    </a>
                </td>
            </#if>
        </#list>
      </#if>
    </tr>
    <tr>
        <#if tuan_cur_page??>
            <#list tuan_cur_page.content as item>
                <#if item_index gt 2 && item_index lt 6>
                    <td style="width:30%;">
                        <a href="/touch/goods/${item.id?c}?qiang=3">
                          <p class="fs08">${item.title!''}</p>
                          <p class="fs07 c9">${item.subTitle!''}</p>
                          <p class="fs07 red">￥<#if item.groupSalePrice??>${item.groupSalePrice?string("0.00")}</#if></p>
                          <img src="${item.coverImageUri!''}" width="185" />
                        </a>
                    </td>
                </#if>
            </#list>
        </#if>
    </tr>
  </table>
</section>


<h3 class="indextit mainbox">
<span  style="float:left;margin-bottom:1%;">百人团购</span>
<span style="display:block;position:relative;"><a style="margin-left:2%;float:left;right:initial;top:2px;font-size:0.7em;color:red;" href="/touch/info/content/57?mid=12">团购细则</a></span>
<a href="/touch/promotion/baituan">+更多</a></h3>
<section class="indexlist">
  <table style="border-collapse:separate; border-spacing:5px;">
    <tr>
        <#if baituan_cur_page??>
            <#list baituan_cur_page.content as item>
                <#if item_index lt 3>
                    <td style="width:30%;">
                        <a href="/touch/goods/${item.id?c}?qiang=100">
                            <p class="fs08">${item.title!''}</p>
                            <p class="fs07 c9">${item.subTitle!''}</p>
                            <p class="fs07 red">￥<#if item.groupSalePrePayPrice??>${item.groupSalePrePayPrice?string("0.00")}</#if></p>
                            <img src="${item.coverImageUri!''}" width="185" />
                        </a>
                    </td>
                </#if>
            </#list>
        </#if>
    </tr>
    <tr>
        <#if baituan_cur_page??>
            <#list baituan_cur_page.content as item>
                <#if item_index gt 2 && item_index lt 6>
                    <td style="width:30%;">
                        <a href="/touch/goods/${item.id?c}?qiang=100">
                            <p class="fs08">${item.title!''}</p>
                            <p class="fs07 c9">${item.subTitle!''}</p>
                            <p class="fs07 red">￥<#if item.groupSalePrePayPrice??>${item.groupSalePrePayPrice?string("0.00")}</#if></p>
                            <img src="${item.coverImageUri!''}"width="185"/>
                        </a>
                    </td>
                </#if>
            </#list>
        </#if>
    </tr>
  </table>
</section>

<h3 class="indextit mainbox"><span>服务项目</span><#--<a href="#">+更多</a>--></h3>
<section class="indexlist">
  <table style="border-collapse:separate; border-spacing:3px;">
    <tr> 
        <#if top_category_list??>
            <#list top_category_list as item>
                <#if item_index < 5>
                    <td>
                        <a class="a3" href="/touch/list/${item.id?c}">
                          <p>${item.title!''}</p>
                          <img src="${item.imgUrl!''}" />
                        </a>
                    </td>
                </#if>
            </#list>
        </#if>
    </tr>
    <tr> 
        <#if top_category_list??>
            <#list top_category_list as item>
                <#if item_index gt 4 && item_index < 9>
                    <td>
                        <a class="a3" href="/touch/list/${item.id?c}">
                          <p>${item.title!''}</p>
                          <img src="${item.imgUrl!''}" />
                        </a>
                    </td>
                </#if>
            </#list>
        </#if>
          <td style="background:#56606b;">
            <a class="a3" href="javascript:;">
              <p class="white">更多项目</p>
              <p class="white">敬请期待</p>
            </a>
          </td>
    </tr>
  </table>
</section>

<h3 class="indextit mainbox mt10"><span>自驾游</span><#if self_drive_product_category??><a href="/touch/list/${self_drive_product_category.id?c}">+更多</a></#if></h3>

<section class="indexlist">
  <table style="table-layout:fixed;" class="index_my">
    <tr>
        <#if self_drive_goods_page??>
            <#list self_drive_goods_page.content as item>
                <td rowspan="2" width="30%">                 
                    <a class="ta-c" href="/touch/goods/${item.id?c}">
                      <img src="${item.coverImageUri!''}" />
                      <p class="fs08 ta-l">${item.title!''}</p>
                      <p class="fs10 red ta-l">￥<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></p>
                    </a>               
                </td>
            </#list>
        </#if>
    </tr>
    
  </table>
</section>

<h3 class="indextit mainbox"><span>特别推荐</span><#--<a href="#">+更多</a>--></h3>

<section class="indexlist">
  <table style="table-layout:fixed;">
    <tr>
        <#if index_recommend_goods_page??>
            <#list index_recommend_goods_page.content as item>
                <td rowspan="2" width="30%">
                    <a class="ta-c" href="/touch/goods/${item.id?c}">
                        <p class="fs08" style="overflow: hidden; height: 16px;">${item.title!''}</p>
                        <p class="fs07 c9" style="overflow: hidden; height: 16px;">${item.subTitle!''}</p>
                        <p class="fs07 red">￥<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></p>
                        <img src="${item.coverImageUri!''}" />
                    </a>
                </td>
            </#list>
        </#if>
    </tr>
    
  </table>
</section>


<div class="clear h20"></div>
<section class="botlogin">
  <#if username??>
  <a href="/touch/user">${username!''}</a>
  <a class="ml20" href="/touch/logout">退出</a>
  <#else>
  <a href="/touch/login">登录</a><a class="ml20" href="/touch/reg">注册</a>
  </#if>
  <a class="a1" href="javascript:$('html,body').animate({scrollTop:0},500);">TOP</a>
</section>
<footer class="comfoot main">
    <a href="/index">电脑版</a>
    <a href="/touch">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<p class="bottext mainbox">${site.icpNumber!''}</p>
</body>
</html>
