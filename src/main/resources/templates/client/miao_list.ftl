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
<script src="/client/js/cart.js"></script>

<link rel="shortcut icon" href="/client/images/cheyou.ico" />
<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

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
function checkTime(i)  
{  
    if (i < 10) {  
        i = "0" + i;  
    }  
    return i;  
}
</script>

</head>

<body>
<#include "/client/common_header.ftl" />

<div class="main">
    <section class="team_tit">
        <img class="tit" src="/client/images/miao.png">
        <div class="time <#if type??>outtime</#if>">
          <a href="/promotion/miao">正在抢拍</a>
        </div>
        <div class="time <#if !type?? || type="passed">outtime</#if>" style="left:325px;">
          <a href="/promotion/miao?type=ongoing">即将开始</a>
        </div>
        <div class="time <#if !type?? || type="ongoing">outtime</#if>" style="left:500px;">
          <a href="/promotion/miao?type=passed">已经结束</a>
        </div>
    </section>
  
    <#if miao_goods_page??>
        <#list miao_goods_page.content as item>
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
                
    if (0 >= ts)
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
    $("#timeLeft${item_index}").html("<span>"+dd+"</span><span>"+hh+"</span><span>"+mm+"</span><span>"+ss+"</span>");
    //$("#timeLeft${item_index}").html(dd+"天"+hh+":"+mm+":"+ss);
    var price = ${item.flashSalePrice?string("0.00")} * ts / allts;
    
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
            <div class="yiyuqiangpailiebiao">
                <#if type?? && type=="ongoing">
                    <i class="yiyuanjijiang"></i> 
                <#elseif type?? && type=="passed">
                    <i class="yiyuanjieshu"></i> 
                </#if>
                <div class="yiyuqiangpailiebiao_lt">
                    <a href="/goods/${item.id?c}?qiang=1"><img src="${item.flashSaleImage!''}" width="624" height="332" /></a>
                </div>
                <div class="yiyuqiangpailiebiao_rt">
                    <div class="yiyuqiangpailiebiao_rt_bt">
                        <h4><a href="/goods/${item.id?c}?qiang=1">${item.title!''}</a></h4>
                        <span>${item.subTitle!''}</span>
                    </div>
                    
                    <div class="clear"></div>
                    
                    <div class="yiyuqiangpailiebiao_rt_jg">抢拍价：<span id="flashPrice${item_index}">¥<#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}<#else>0.00</#if></span></div>
                    
                    <div class="yiyuqiangpailiebiao_rt_anniu">
                        <#if type?? && type=="ongoing">
                        <a href="javascript:;">即将开始</a>
                        <#elseif type?? && type=="passed">
                        <a href="javascript:;">已经结束</a>
                        <#else>
                        <a href="/order/buy/qiang?gid=${item.id?c}">立即抢拍</a>
                        </#if>
                    </div>
          
                    <div class="clear"></div>
          
                    <div class="yiyuqiangpailiebiao_rt_lanse">
                        <p>市场价：<del>¥${item.marketPrice?string("0.00")}</del></p>
                        <p class="lh40"><span class="red">${item.totalclicks!'0'}</span>人抢拍</p>
                        <p class="lh40"><a href="/goods/${item.id?c}?qiang=1">查看详情</a></p>
                    </div>
                    
                    <div class="clear"></div>
                    
                    <div class="yiyuqiangpailiebiao_rt_time" id="timeLeft${item_index}">
                        <span>0</span><span>0</span><span>0</span><span>0</span>
                    </div>
                </div>
            </div>
        </#list>
    </#if>
  
    <div class="clear h40"></div>
  
    <div class="darkpage">
        <#if miao_goods_page??>
            <#assign continueEnter=false>
            <#if miao_goods_page.number+1 == 1>
                <a href="javascript:;">上一页</a>
            <#else>
                <a href="/promotion/miao?page=${miao_goods_page.number-1}<#if type??>&type=${type}</#if>">上一页</a>
            </#if>
            
            <#if miao_goods_page.totalPages gt 0>
                <#list 1..miao_goods_page.totalPages as page>
                    <#if page <= 3 || (miao_goods_page.totalPages-page) < 3 || (miao_goods_page.number+1-page)?abs<3 >
                        <#if page == miao_goods_page.number+1>
                            <a class="sel" href="/promotion/miao?page=${page-1}<#if type??>&type=${type}</#if>">${page}</a>
                        <#else>
                            <a href="/promotion/miao?page=${page-1}<#if type??>&type=${type}</#if>">${page}</a>
                        </#if>
                        <#assign continueEnter=false>
                    <#else>
                        <#if !continueEnter>
                            <span class="sp1">……</span>
                            <#assign continueEnter=true>
                        </#if>
                    </#if>
                </#list>
            </#if>
            
            <#if miao_goods_page.number+1 == miao_goods_page.totalPages || miao_goods_page.totalPages==0>
                <a href="javascript:;">下一页</a>
            <#else>
                <a href="/promotion/miao?page=${miao_goods_page.number+1}<#if type??>&type=${type}</#if>">下一页</a>
            </#if>
        </#if>
        <span class="sp1">共${miao_goods_page.totalPages!'0'}页</span>
        <div>
            <span class="fl mr5">到第</span>
            <input id="pageNo" type="text" value="${page!'1'}" class="text" />
            <span class="fl mr5 ml5">页</span>
            <input type="submit" class="sub" onclick="javascript:window.location='/promotion/miao?page='+(parseInt($('#pageNo').val())-1)+'<#if type??>&type=${type}</#if>'" value="确定" />
            <p class="clear"></p>
        </div>
    </div>
    
    <div class="clear"></div> 
</div><!--main END-->

<div class="clear h50"></div>

<#include "/client/common_footer.ftl" />
</body>
</html>
