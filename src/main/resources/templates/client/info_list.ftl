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


$(function(){
	//左右滚动幻灯片广告
	var essayad = {
			 "boxid":"essayadbox",//最外层id
			 "sumid":"essayadsum",//列表id
			 "li01":"threeli01",//li标签id
			 "li02":"threeli02",//重复li二号id
			 "stylename":"essayadpart",//内部滚动的标签class
			 "numid":"essayadnum",//数字id
			 "boxwidth":660,//宽度
			 "boxheight":320,//高度
			 "preid":"essayadpre",//上一个id
			 "nextid":"essayadnext",//下一个id
			 "speed":5000,// 切换速度
			 "gospeed":500//轮换速度
		  };
	$.ljs_adcartoon.arrowNum(essayad);
	});
</script>
</head>
<body>
<#include "/client/common_header.ftl" />

<div class="main">
    <#--
    <a id="essayadpre" href="javascript:void(0);">上一页</a>
    <a id="essayadnext" href="javascript:void(0);">下一页</a>
    <div id="essayadbox">
        <ul id="essayadsum">
            <li id="threeli01">
                <a class="essayadpart" href="#"><img src="/client/images/photo/banner04.jpg" width="660" height="320" /><p>我是标题文字之类的东西哦</p></a>
                <a class="essayadpart" href="#"><img src="/client/images/photo/banner03.png" width="660" height="320" /><p>我是标题文字之类的东西哦</p></a>
                <a class="essayadpart" href="#"><img src="/client/images/photo/banner02.png" width="660" height="320" /><p>我是标题文字之类的东西哦</p></a>
            </li>
            <li id="threeli02"></li>
        </ul>
        <div style="clear:both;"></div>
    </div>
  
    <section class="essay_ico">
        <h3 class="sc lh30 fs24 pb10">热门标签</h3>
        <a href="#"><img src="/client/images/content/essay01.png" /><p>贴膜</p></a>
        <a href="#"><img src="/client/images/content/essay02.png" /><p>美容</p></a>
        <a href="#"><img src="/client/images/content/essay03.png" /><p>坐垫</p></a>
        <a href="#"><img src="/client/images/content/essay04.png" /><p>脚垫</p></a>
        <a href="#"><img src="/client/images/content/essay05.png" /><p>真皮</p></a>
        <a href="#"><img src="/client/images/content/essay06.png" /><p>电器</p></a>
        <a href="#"><img src="/client/images/content/essay07.png" /><p>改装</p></a>
        <a href="#"><img src="/client/images/content/essay08.png" /><p>自驾</p></a>
        <div class="clear"></div>
    </section>
  
    <div class="clear h40"></div>
    -->
    
    <section class="column_left" style="float:right;">
        <menu class="hot_list mt20">
            <h5 class="tit">浏览记录</h5>
            <#if recent_page??>
                <#list recent_page.content as item>
                    <a class="scan" href="/goods/${item.goodsId}">
                        <img src="${item.goodsCoverImageUri!''}" title="${item.goodsTitle!''}"/>
                        <p>${item.goodsTitle!''}</p>
                        <p class="red">￥<#if item.goodsSalePrice??>￥${item.goodsSalePrice?string("0.00")}</#if></p>
                    </a>
                </#list>
            </#if>
        </menu>
    </section>
  
    <ul class="essaysum">
        <#if info_page??>
            <#list info_page.content as item>
                <li>
                  <#--<img class="img01" src="/client/images/photo/img17.png" width="50" height="50" />-->
                  <a class="a1" href="/info/content/${item.id?c}?mid=${mid}"><img src="${item.imgUrl!''}" width="181" height="166" /></a>
                  <a class="a2" href="/info/content/${item.id?c}?mid=${mid}">${item.title!''}<#--<span>经验</span>--></a>
                  <p class="fs12 pt5 pb5">作者：<span class="rd3 mr10">${item.source!''}</span></p>
                  <p class="c7">${item.brief!''}</p>
                  <#--
                  <p class="pt15">
                    <a class="a3" href="#">522</a>
                    <a class="a4" href="#">522</a>
                  </p>
                  -->
                </li>
            </#list>
        </#if>
    </ul>
    <div class="darkpage" style="float:left; width:800px; padding-top:30px;">
        <#if info_page??>
            <#assign continueEnter=false>
            <#if info_page.number+1 == 1>
                <a href="javascript:;">上一页</a>
            <#else>
                <a href="/info/list/${mid}?catId=${catId}&page=${info_page.number-1}">上一页</a>
            </#if>
            
            <#if info_page.totalPages gt 0>
                <#list 1..info_page.totalPages as page>
                    <#if page <= 3 || (info_page.totalPages-page) < 3 || (info_page.number+1-page)?abs<3 >
                        <#if page == info_page.number+1>
                            <a href="javascript:;" class="sel">${page}</a>
                        <#else>
                            <a href="/info/list/${mid}?catId=${catId}&page=${page-1}">${page}</a>
                        </#if>
                        <#assign continueEnter=false>
                    <#else>
                        <#if !continueEnter>
                            <b class="pn-break">&hellip;</b>
                            <#assign continueEnter=true>
                        </#if>
                    </#if>
                </#list>
            </#if>
            
            <#if info_page.number+1 == info_page.totalPages || info_page.totalPages==0>
                <a href="javascript:;">下一页</a>
            <#else>
                <a href="/info/list/${mid}?catId=${info_page.number+1}">下一页</a>
            </#if>
            <span class="sp1">共${info_page.totalPages!'0'}页</span>
        </#if>
    </div>
    <div class="clear"></div> 
</div>

<#include "/client/common_footer.ftl" />
</body>
</html>
