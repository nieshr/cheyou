<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if keywords??>${keywords!''}-</#if>车有同盟</title>
<meta name="keywords" content="<#if site??>${site.seoKeywords!''}</#if>" />
<meta name="description" content="<#if site??>${site.seoDescription!''}</#if>" />
<meta name="copyright" content="${site.copyright!''}" /> 

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/html5.js"></script>
<script src="/client/js/list.js"></script>

<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />
<script>
function setprice() {
    var p1 = $.trim($('#ParamFiltern_price1').val()), p2 = $.trim($('#ParamFiltern_price2').val())
    if (isNaN(p1) || p1=="") { p1 = 0 }
    if (isNaN(p2) || p2== "") { p2 = 0 }
    var price = p1 + '-' + p2;
    var url = '/list/' + $('#urlcoll').val();
    if (price != "0-0") { url += "_" + price; }
    location.href = url;
}
function btnPageSubmit() 
{
    window.location.href = "/search?keywords=${keywords!''}&page="
    + (parseInt($('#iPageNum').val()) - 1);
}
</script>

<script type="text/javascript">
$(document).ready(function(){
    menuDownList("top_phone","#top_phonelist",".a1","sel");
    phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
    checkNowHover("shopping_down","shopping_sel");
    navDownList("navdown","li",".nav_showbox");
    menuDownList("mainnavdown","#navdown",".a2","sel");
    
    chooseMoreShow();
});
</script>


</head>
<body>
<!-- header开始 -->
<#include "/client/common_header.ftl" />
<!-- header结束 -->

<div class="main">
    <div class="nygg">
        <div class="bt mt15"><a href="/info/list/10">通知公告</a></div>
        <ul>
            <#if news_page??>
                <#list news_page.content as item>
                  <#if item_index < 4>
                    <li><a href="/info/content/${item.id?c}?mid=12" style="margin-left: 12px;">${item.title!''}</a></li>
                  </#if>
                </#list>
            </#if>
        </ul>
    </div>
    <menu class="column_qg main border-df">
        <div class="bt">热卖商品</div>
        <div class="clear10"></div>
        <#if most_sold_list??>
            <#list most_sold_list as item>
                <#if item_index < 3>
                    <a class="list" href="/goods/${item.id?c}">
                        <img src="${item.coverImageUri!''}" title="${item.title!''} ${item.subTitle!''}"/>
                        <p class="pt10 pb10 h30 overflow">${item.title!""} ${item.version!""} ${item.color!""} ${item.capacity!""}</p>
                        <p class="red fs16">￥${item.salePrice?string("0.00")}</p>
                        <span>立即抢购</span>
                    </a>
                </#if>
            </#list>
        </#if>
    </menu>
    <div class="clear30"></div>  
    
    <section class="column_left">
        <#--
        <ul class="xcjly">
            <div class="nyltbt">
                <h4 class="nylt"><a href="#"><img src="/client/images/nylt_03.png" />行车记录仪</a></h4>
            </div>
            <li><a href="#">车载空气净化</a></li>
            <li><a href="#">车载空气净化</a></li>
        </ul>
        <ul class="xcjly">
            <div class="nyltbt">
                <h4 class="nylt"><a href="#"><img src="/client/images/nylt_06.png" />行车记录仪</a></h4>
            </div>
        </ul>
        -->
    
        <h3 class="tit">热卖排行</h3>

        <menu class="border-df">
            <#if hot_sale_list??>
                <#list hot_sale_list as item>
                    <#if item_index < 5>
                        <a class="scan" href="/goods/${item.id?c}">
                            <img src="${item.coverImageUri!''}" title="${item.title!''} ${item.subTitle!''}"/>
                            <div class="num1">${item_index+1}</div>
                            <p class=" h40 overflow">${item.title!""} ${item.version!""} ${item.color!""} ${item.capacity!""}</p>
                            <p class="red">￥${item.salePrice?string("0.00")}</p>
                        </a>
                    </#if>
                </#list>
            </#if>
        </menu>
        
        <h3 class="tit">浏览记录</h3>
        
        <menu class="border-df">
            <#if recent_page??>
                <#list recent_page.content as item>
                    <a class="scan" href="/goods/${item.goodsId?c}">
                        <img src="${item.goodsCoverImageUri!''}" title="${item.goodsTitle!''}"/>
                        <p class=" h40 overflow">${item.goodsTitle!''}</p>
                        <p class="red"><#if item.goodsSalePrice??>￥${item.goodsSalePrice?string("0.00")}</#if></p>
                    </a>
                </#list>
            </#if>
        </menu>
    </section>
    
    <div class="column_right">
        
        
        <div class="clear h20"></div>
        
        <section class="column_px">
            
            <div class="fr">
                <#if goods_page.number+1 == goods_page.totalPages || goods_page.totalPages==0>
                    <a href="javascript:;"><img src="/client/images/page_n.png" height="11" /></a>
                <#else>
                    <a href="/search?keywords=${keywords!''}&page=${goods_page.number+1}"><img src="/client/images/page_n.png" height="11" /></a> <#-- goods_page.number+1 -->
                </#if>
                        
                <#if goods_page.number+1 == 1>
                    <a href="javascript:;"><img src="/client/images/page_l.png" height="11" /></a>
                <#else>
                    <a href="/search?keywords=${keywords!''}&page=${goods_page.number-1}"><img src="/client/images/page_l.png" height="11" /></a> <#-- goods_page.number-1 -->
                </#if>
                <span><font class="fc"><#if goods_page.totalPages==0>0<#else>${goods_page.number+1}</#if></font>/${goods_page.totalPages!"0"}页</span>
            </div>
          <div class="clear"></div>
        </section>
        
        <div class="clear"></div>
        
        <ul class="column_sum">
            <#if goods_page??>
            <#list goods_page.content as goods>
                <li>
                    <a class="a1" href="/goods/${goods.id?c}"><img src="${goods.coverImageUri!''}" width="210" height="210" title="${goods.title!''} ${goods.subTitle!''}"/></a>
                    <p class="fs20 jiage lh35 red">￥${goods.salePrice?string("0.00")}</p><del class="yuanjia">¥${goods.marketPrice?string("0.00")}</del>
                    
                    <div class="clear"></div>
        
                    <a class="block h20 overflow" href="/goods/${goods.id?c}">${goods.title!""} ${goods.version!""} ${goods.color!""} ${goods.capacity!""}</a>
                    <a class="block fs12 blue h20 overflow" href="/goods/${goods.id?c}">${goods.subTitle!""}</a>
                    <p class="fs14 w84 fl">评论：<span class="blue">${goods.totalComments!'0'}</span>人</p>
                    <#if goods.returnPoints?? && goods.returnPoints gt 0>
                        <div class="yh fr">
                            <p><a href="/goods/${goods.id?c}">送粮草</a></p>
                        </div>
                    </#if>
                    <#if goods.giftList?? && goods.giftList?size gt 0>
                        <div class="yh fr">
                            <p><a href="/goods/${goods.id?c}">赠品</a></p>
                        </div>
                    </#if>
                    <div class="clear5"></div>
                    
                    <div class="goumai">
                        <a class="a8" href="/cart/init?id=${goods.id?c}" target="_blank"><img src="/client/images/liebiao_31.png" />加入购物车</a>
                        <a class="a7" href="javascript:addCollect(${goods.id?c});"><img src="/client/images/liebiao_34.png" />关注</a>
                        <p class="clear"></p>
                    </div>
                </li>
            </#list>
            </#if> 
        </ul>
        <div class="clear h20"></div>
        
        <div class="pagebox">
            <div class="num">
                <#if goods_page??>
                    <#assign continueEnter=false>
                    <#if goods_page.number+1 == 1>
                        <a class="a1 a0" href="javascript:;"><span>上一页</span></a>
                    <#else>
                        <a class="a1 a0" href="/search?keywords=${keywords!''}&page=${goods_page.number-1}"><span>上一页</span></a>
                    </#if>
                    
                    <#if goods_page.totalPages gt 0>
                        <#list 1..goods_page.totalPages as page>
                            <#if page <= 3 || (goods_page.totalPages-page) < 3 || (goods_page.number+1-page)?abs<3 >
                                <#if page == goods_page.number+1>
                                    <a class="sel" href="javascript:;">${page}</a>
                                <#else>
                                    <a href="/search?keywords=${keywords!''}&page=${page-1}">${page}</a> <#-- ${page} -->
                                </#if>
                                <#assign continueEnter=false>
                            <#else>
                                <#if !continueEnter>
                                    <span> ... </span>
                                    <#assign continueEnter=true>
                                </#if>
                            </#if>
                        </#list>
                    </#if>
                    
                    <#if goods_page.number+1 == goods_page.totalPages || goods_page.totalPages==0>
                        <a class="a2" href="javascript:;"><span>下一页</span></a>
                    <#else>
                        <a class="a2" href="/search?keywords=${keywords!''}&page=${goods_page.number+1}"><span>下一页</span></a>
                    </#if>
                </#if>
                <span> 共<b>${goods_page.totalPages}</b>页 </span>
            </div>
            <div class="page">
                <input class="sub" type="submit" onclick="javascript:btnPageSubmit();" value="确定" />
                <span>页</span>
                <input class="text" type="text" value="${pageId + 1}" id="iPageNum"/>
                <span>到第</span>
            </div>
            <div class="clear"></div>
        </div><!--pagebox END-->
    </div>
</div>



<div class="clear30"></div>
<!--大家都在看结束-->
<#include "/client/common_footer.ftl" />
<!--底部结束-->
</body>
</html>
