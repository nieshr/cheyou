<#if goods_page??>
    <#list goods_page.content as item>
        <a href="/touch/goods/${item.id!''}">
            <b><img src="${item.coverImageUri!''}" /></b>
            <p class="p1">${item.title!''}</p>
            <p class="p2">${item.subTitle!''}</p>
            <p class="p2">评论：<span class="red">${item.totalComments!'0'}</span>人</p>
            <p class="red p3">￥<#if item.salePrice??>${item.salePrice?string("0.00")}</#if><span class="unl-lt c9">￥<#if item.marketPrice??>${item.marketPrice?string("0.00")}</#if></span></p>
            <div class="clear"></div>
        </a>
    </#list>
</#if>