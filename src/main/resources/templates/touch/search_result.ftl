<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if productCategory??>${productCategory.seoTitle!''}-</#if>车有同盟</title>
<meta name="keywords" content="<#if productCategory??>${productCategory.seoKeywords!''}</#if>">
<meta name="description" content="<#if productCategory??>${productCategory.seoDescription!''}</#if>">
<meta name="copyright" content="<#if site??>${site.copyright!''}</#if>" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/common.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
  
});

var pageIdx = 1;
function loadMore()
{
    $.ajax({
        type:"post",
        url:"/touch/search?keywords=${keywords!''}&page=" + pageIdx + "&st=${st!''}&sd=${sd!''}",
        success:function(data){
            if ("" == data)
            {
                $("#a-more").css("display", "none");
            }
            else
            {
                $("#goods-menu").append(data);
                pageIdx++;
            }
        }
    });
}
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>搜索结果</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>

<div class="main comcheck">
  <nav>
    <a <#if st==0>class="sel"</#if> href="/touch/search?keywords=${keywords!''}&page=0&st=0&<#if sd?? && sd==0>sd=1<#else>sd=0</#if>"><p>销量</p></a>
    <a <#if st==1>class="sel"</#if> href="/touch/search?keywords=${keywords!''}&page=0&st=1&<#if sd?? && sd==0>sd=1<#else>sd=0</#if>"><p>价格</p></a>
    <a <#if st==2>class="sel"</#if> href="/touch/search?keywords=${keywords!''}&page=0&st=2&<#if sd?? && sd==0>sd=1<#else>sd=0</#if>"><p>上架时间</p></a>
  </nav>
</div><!--comcheck END-->

<menu class="whitebg myorder_list" id="goods-menu">
    <#if goods_page??>
        <#list goods_page.content as item>
            <a href="/touch/goods/${item.id!''}">
                <b><img src="${item.coverImageUri!''}" /></b>
                <p class="p1">${item.title!''}</p>
                <p class="p2">${item.subTitle!''}</p>
                <p class="p2">关注人数：<span class="red">${item.totalComments!'0'}</span>人</p>
                <p class="red p3">￥<#if item.salePrice??>${item.salePrice?string("0.00")}</#if><span class="unl-lt c9">￥<#if item.marketPrice??>${item.marketPrice?string("0.00")}</#if></span></p>
                <div class="clear"></div>
            </a>
        </#list>
    </#if>
</menu>

<a id="a-more" class="ma15 ta-c block" href="javascript:loadMore();"><img src="/touch/images/more.png" height="20" /></a>

</body>
</html>
