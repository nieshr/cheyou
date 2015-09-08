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
<#--
<script type="text/javascript">
$(document).ready(function(){
  $(".mianfeilingqutanchu #yincang").click(function(){
    $(this).parents(".mianfeilingqutanchu").hide();
  });
});
</script>

<script type="text/javascript">
$(document).ready(function(){
  $("#xianshi").click(function(){
      $("div.mianfeilingqutanchu").show();
  });
});
</script>
-->
</head>
<body>
<#include "/client/common_header.ftl" />

<div class="main">
    <#--
    <section class="team_tit">
        <img class="tit" src="/client/images/tg_tit.png" />
        <div class="time">
            <a href="/promotion/tuan">本期团购</a>
        </div>
        <a class="a1" href="/promotion/tuan?type=ongoing"><i></i>下期预告</a>
        <a class="a1 a2" href="/promotion/tuan?type=passed"><i></i>往期回顾</a>
    </section>
    -->
    <section class="team_tit">
        <a href="/promotion/baituan"><img class="tit" src="/client/images/tg_tit1.png"></a>
        <div class="time <#if type??>outtime</#if>">
          <a href="/promotion/tuan">本期团购</a>
        </div>
        <div class="time <#if !type?? || type="passed">outtime</#if>" style="left:325px;">
          <a href="/promotion/tuan?type=ongoing">下期预告</a>
        </div>
        <div class="time <#if !type?? || type="ongoing">outtime</#if>" style="left:500px;">
          <a href="/promotion/tuan?type=passed">往期回顾</a>
        </div>
    </section>
  
    <div class="clear h40"></div>
  
    <#if tuan_goods_page??>
        <#list tuan_goods_page.content as item>
        <script>
$(document).ready(function(){
    setInterval("timer${item_index}()",1000);
});

function timer${item_index}()
{
<#if item.isGroupSale && item.groupSaleStartTime < .now && item.groupSaleStopTime gt .now>
    var ts = (new Date(${item.groupSaleStopTime?string("yyyy")}, 
                parseInt(${item.groupSaleStopTime?string("MM")}, 10)-1, 
                ${item.groupSaleStopTime?string("dd")}, 
                ${item.groupSaleStopTime?string("HH")}, 
                ${item.groupSaleStopTime?string("mm")}, 
                ${item.groupSaleStopTime?string("ss")})) - (new Date());//计算剩余的毫秒数
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
            <section class="team_ad">
                <#if type?? && type=="passed">
                    <i class="yiyuanjieshu"></i>
                <#elseif type?? && type="ongoing">
                    <i class="yiyuanjijiang"></i>
                </#if>
                <a class="fl" href="/goods/${item.id?c}?qiang=<#if item.groupSaleSoldNumber?? && item.groupSaleSoldNumber < 3>3<#elseif item.groupSaleSoldNumber?? && item.groupSaleSoldNumber < 7>7<#elseif item.groupSaleSoldNumber?? && item.groupSaleSoldNumber gt 6>10</#if>">
                    <img src="${item.groupSaleImage!''}" height="392" width="732" />
                </a>
                <div class="team_info">
                    <p class="ta-r"><img src="/client/images/tg_top.png" /></p>
                    <a class="fs20 block pl15 pt5" href="/goods/${item.id?c}?qiang=<#if item.groupSaleSoldNumber?? && item.groupSaleSoldNumber < 3>3<#elseif item.groupSaleSoldNumber?? && item.groupSaleSoldNumber < 7>7<#elseif item.groupSaleSoldNumber?? && item.groupSaleSoldNumber gt 6>10</#if>">${item.title!''}</a>
                    <p class="red pt10 pl15 fs16">${item.subTitle!''}</p>
                    <p id="timeLeft${item_index}" class="p1 mb20">剩余时间：</p>
                    <div class="team_men fl">
                        <dl>
                            <dt style="width: <#if item.groupSaleSoldNumber??><#if item.groupSaleSoldNumber/10 gt 1>100<#else>${item.groupSaleSoldNumber * 10}</#if><#else>0</#if>%;"></dt>
                        </dl>
                        <p>
                            <span>￥<#if item.groupSaleThreePrice??>${item.groupSaleThreePrice?string("0.00")}</#if></span>
                            三人团
                        </p>
                        <p>
                            <span>￥<#if item.groupSaleSevenPrice??>${item.groupSaleSevenPrice?string("0.00")}</#if></span>
                            五人团
                        </p>
                        <p>
                            <span>￥<#if item.groupSaleTenPrice??>${item.groupSaleTenPrice?string("0.00")}</#if></span>
                            十人团
                        </p>
                        <div class="clear"></div>
                    </div>
                    
                    <div class="team_num fr">
                        <p>已有<span class="red">${item.groupSaleSoldNumber!'0'}</span>人参团</p>
                    </div>
                    
                    <div class="clear"></div>
                    
                    <p class="ml20 pt20 c3 fs16">市场价：<span class="unl-th">￥<#if item.marketPrice??>${item.marketPrice?string("0.00")}</#if></span></p>
                    <p class="team_red">
                        <span>预付定金：￥<#if item.groupSalePrice??>${item.groupSalePrice?string("0.00")}</#if></span>
                        <#if !type??>
                            <a href="/order/buy/tentuan?id=${item.id?c}">立即参团</a>
                        </#if>
                    </p>
                </div>
                <div class="clear"></div>
            </section>
            <div class="clear h40"></div>
        </#list>
    </#if>
  
  
  
    <div class="darkpage">
        <#if tuan_goods_page??>
            <#assign continueEnter=false>
            <#if tuan_goods_page.number+1 == 1>
                <a href="javascript:;">上一页</a>
            <#else>
                <a href="/promotion/tuan?page=${tuan_goods_page.number-1}<#if type??>&type=${type}</#if>">上一页</a>
            </#if>
            
            <#if tuan_goods_page.totalPages gt 0>
                <#list 1..tuan_goods_page.totalPages as page>
                    <#if page <= 3 || (tuan_goods_page.totalPages-page) < 3 || (tuan_goods_page.number+1-page)?abs<3 >
                        <#if page == tuan_goods_page.number+1>
                            <a class="sel" href="/promotion/tuan?page=${page-1}<#if type??>&type=${type}</#if>">${page}</a>
                        <#else>
                            <a href="/promotion/tuan?page=${page-1}<#if type??>&type=${type}</#if>">${page}</a>
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
            
            <#if tuan_goods_page.number+1 == tuan_goods_page.totalPages || tuan_goods_page.totalPages==0>
                <a href="javascript:;">下一页</a>
            <#else>
                <a href="/promotion/tuan?page=${tuan_goods_page.number+1}<#if type??>&type=${type}</#if>">下一页</a>
            </#if>
        </#if>
        <span class="sp1">共${tuan_goods_page.totalPages!'0'}页</span>
        <div>
            <span class="fl mr5">到第</span>
            <input id="pageNo" type="text" value="${page!'1'}" class="text" />
            <span class="fl mr5 ml5">页</span>
            <input type="submit" class="sub" onclick="javascript:window.location='/promotion/tuan?page='+(parseInt($('#pageNo').val())-1)+'<#if type??>&type=${type}</#if>'" value="确定" />
            <p class="clear"></p>
        </div>
    </div>
    <div class="clear"></div> 
</div>
    
    
</div><!--main END-->

<div class="clear h50"></div>

<#include "/client/common_footer.ftl" />
</body>
</html>
