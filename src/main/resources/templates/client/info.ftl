<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if info??>${info.seoTitle!''}-</#if>车有同盟</title>
<meta name="keywords" content="${info.seoKeywords!''}">
<meta name="description" content="${info.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>

<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
	menuDownList("top_phone","#top_phonelist",".a1","sel");
	phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
	navDownList("navdown","li",".nav_showbox");
	menuDownList("mainnavdown","#navdown",".a2","sel");
	checkNowHover("shopping_down","shopping_sel");
});

</script>
</head>

<body>
<#include "/client/common_header.ftl" />

<div class="main">
    <section class="column_left" style="float:right;">
        <menu class="hot_list mt20">
            <h5 class="tit">浏览记录</h5>
            <#if recent_page??>
                <#list recent_page.content as item>
                    <a class="scan" href="/goods/${item.goodsId?c}">
                        <img src="${item.goodsCoverImageUri!''}" title="${item.goodsTitle!''}"/>
                        <p>${item.goodsTitle!''}</p>
                        <p class="red">￥<#if item.goodsSalePrice??>￥${item.goodsSalePrice?string("0.00")}</#if></p>
                    </a>
                </#list>
            </#if>
        </menu>
    </section>
  
    <article class="essayamin">
        <h3 class="ta-c fw400 fs20 lh25 pb20">${info.title!''}</h3>
        <p class="essaytime">时间：${info.createTime?string("yyyy-MM-dd")}&nbsp;&nbsp;作者：${info.source!''}</p>
        <div class="essaycon">${info.content!''}</div>
        <div class="clear h20"></div>
        <#if prev_info??>
            <p class="mb10">上一篇：<a href="/info/content/${prev_info.id?c}?mid=${mid}">${prev_info.title!''}</a></p>
        </#if>
        <#if next_info??>
            <p>下一篇：<a href="/info/content/${next_info.id?c}?mid=${mid}">${next_info.title!''}</a></p>
        </#if>
        <div class="clear h20"></div>
        
        <#--
        <section class="pro_mytext" id="pro_mytext">
            <div class="pb20 lh25">
                <span class="sp1">我要说说</span>
            </div>
            <div class="area">
                <textarea></textarea>
            </div>
            <div class="clear h15"></div>
            <input class="sub fr" type="submit" value="提交" />
            <div class="clear"></div>
        </section>
        <menu class="pro_eval pro_zxli w100">
            <li>
                <a class="a1" href="#"><img src="images/photo/img05.png" /><p>135******555</p></a>
                <p class="pb10">我是评论内容之类的东西我是评论内容之类的东西我是评论内容之类的东西我是评论内容之类的东西我是评论内容之类的东西我是评论内容之类的东西我是评论内容之类的东西我是评论内容之类的东西我是评论内容之类的东西我是评论内容之类</p>
                <span class="time">2015.05.12</span> 
            </li>
        </menu>
        -->
    </article>
  
    <div class="clear"></div> 
</div><!--main END-->

<#include "/client/common_footer.ftl" />
</body>
</html>
