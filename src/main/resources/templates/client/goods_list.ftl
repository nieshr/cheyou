<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if productCategory??>${productCategory.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="<#if productCategory??>${productCategory.seoKeywords!''}</#if>" />
<meta name="description" content="<#if productCategory??>${productCategory.seoDescription!''}</#if>" />
<meta name="copyright" content="<#if site??>${site.copyright!''}</#if>" /> 

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/html5.js"></script>
<script src="/client/js/list.js"></script>
<script src="/client/js/goods.js"></script>

<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

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

function setprice() {
    var p1 = $.trim($('#ParamFiltern_price1').val()), p2 = $.trim($('#ParamFiltern_price2').val())
    if (isNaN(p1) || p1=="") { p1 = 0 }
    if (isNaN(p2) || p2== "") { p2 = 0 }
    var price = p1 + '-' + p2;
    var url = "/list/${categoryId!'0'}-${brandIndex!'0'}<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${pageId!'0'}-${leftId!'0'}";
    if (price != "0-0") { url += "_" + price; }
    location.href = url;
}

function btnPageSubmit() 
{
    window.location.href = "${categoryId!'0'}-${brandIndex!0}<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-"
    + (parseInt($('#iPageNum').val()) - 1)
    + "-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string('#.##')}-${priceHigh?string('#.##')}</#if>";
}
</script>

<#-- 投訴頁面提交js-->
<script>
function submitSuggestion()
{
    $.ajax({
        type:"post",
        url:"/user/suggestion/add?" + $("#suggestionForm").serialize(),
        success:function(res){
            if (0 == res.code)
            {
                alert("提交投诉成功，请耐心等待审核~~");
            }
            else
            {
                var ss = "" + res.message;
                alert(res["message"]);
            }
        }
    });
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
    <div class="nygg">
        <div class="bt mt15"><a href="/info/list/10">通知公告</a></div>
        <ul>
            <#if news_page??>
                <#list news_page.content as item>
                  <#if item_index <4>
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
        <h3 class="screen_clear">
            商品筛选
            <a class="a1" href="${categoryId!'0'}-0<#list param_index_list as pindex>-0</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${pageId!'0'}-0">清空筛选条件</a>
        </h3>
    
        <section class="choose_box">
            <menu>
            <#if brand_list??>
                <h4>品牌：</h4>
                <a <#if brandIndex==0>class="sel"</#if> href="${categoryId!'0'}-0<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${pageId!'0'}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>">全部</a>
                
                <#list brand_list as brand>
                    <td><a href="${categoryId!'0'}-${brand_index+1}<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${pageId!'0'}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>" <#if brandIndex==brand_index+1>class="sel"</#if>>${brand.title?trim!''}</a>
                </#list>
            </#if>
            </menu>
            
            <#-- 参数开始 -->
            <#if param_list??>
                <#list param_list as param>
                    <menu>
                        <h4>${param.title!""}：</h4>
                        <a href="${categoryId!'0'}-${brandIndex!'0'}<#list param_index_list as pindex><#if param_index==pindex_index>-0<#else>-${pindex!'0'}</#if></#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${pageId!'0'}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>" <#if param_index_list[param_index]==0>class="sel"</#if>>全部</a>
                        <#if param.valueList??>
                            <#list param.valueList?split(",") as value>
                                <#if value!="">
                                    <a href="${categoryId!'0'}-${brandIndex!'0'}<#list param_index_list as pindex><#if param_index==pindex_index>-${value_index+1}<#else>-${pindex!'0'}</#if></#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${pageId!'0'}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>" <#if param_index_list[param_index]==value_index+1>class="sel"</#if>>${value?trim!""}</a>
                                </#if>
                            </#list>
                        </#if>
                    </menu>
                </#list>
            </#if>
            <#-- 参数结束 -->
            <#if param_list?? && param_list?size gt 4>
                <div class="choose_more"><a href="javascript:chooseMoreDown();">下拉，更多选项<img src="/client/images/content/arrow01.png" /></a></div>
            </#if>
        </section><!--choose_box-->
        
        <div class="clear h20"></div>
        
        <section class="column_px">
            <menu>
                <a <#if orderId==0><#if sort_id_list[0]==0>class="sel01"<#else>class="sel02"</#if></#if> href="${categoryId!'0'}-${brandIndex!'0'}<#list param_index_list as pindex>-${pindex!'0'}</#list>-0<#if sort_id_list??><#list sort_id_list as sortId><#if sortId_index==0><#if sortId==0>-1<#else>-0</#if><#else>-${sortId!'0'}</#if></#list></#if>-${pageId!'0'}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>"><span>综合</span></a>
                <a <#if orderId==1><#if sort_id_list[1]==0>class="sel01"<#else>class="sel02"</#if></#if> href="${categoryId!'0'}-${brandIndex!'0'}<#list param_index_list as pindex>-${pindex!'0'}</#list>-1<#if sort_id_list??><#list sort_id_list as sortId><#if sortId_index==1><#if sortId==0>-1<#else>-0</#if><#else>-${sortId!'0'}</#if></#list></#if>-${pageId!'0'}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>"><span>人气</span></a>
                <a <#if orderId==2><#if sort_id_list[2]==0>class="sel01"<#else>class="sel02"</#if></#if> href="${categoryId!'0'}-${brandIndex!'0'}<#list param_index_list as pindex>-${pindex!'0'}</#list>-2<#if sort_id_list??><#list sort_id_list as sortId><#if sortId_index==2><#if sortId==0>-1<#else>-0</#if><#else>-${sortId!'0'}</#if></#list></#if>-${pageId!'0'}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>"><span>价格</span></a>
                <a <#if orderId==3><#if sort_id_list[3]==0>class="sel01"<#else>class="sel02"</#if></#if> href="${categoryId!'0'}-${brandIndex!'0'}<#list param_index_list as pindex>-${pindex!'0'}</#list>-3<#if sort_id_list??><#list sort_id_list as sortId><#if sortId_index==3><#if sortId==0>-1<#else>-0</#if><#else>-${sortId!'0'}</#if></#list></#if>-${pageId!'0'}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>"><span>评论数</span></a>
                <div class="sxtjBox">
                    <span>价格范围：</span>
                    <input type="text" id="ParamFiltern_price1" class="jgqj_txt" value="<#if priceLow??>${priceLow?string("#.##")}</#if>"/>
                    <span>--</span>
                    <input type="text" id="ParamFiltern_price2" class="jgqj_txt" value="<#if priceHigh??>${priceHigh?string("#.##")}</#if>"/>
                    <input type="submit" class="jgqj_btn" onclick="setprice()" value="确定" />
                </div>
            </menu>
            <div class="fr">
                <#if goods_page.number+1 == goods_page.totalPages || goods_page.totalPages==0>
                    <a href="javascript:;"><img src="/client/images/page_n.png" height="11" /></a>
                <#else>
                    <a href="${categoryId!'0'}-${brandIndex!0}<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${goods_page.number+1}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>"><img src="/client/images/page_n.png" height="11" /></a> <#-- goods_page.number+1 -->
                </#if>
                        
                <#if goods_page.number+1 == 1>
                    <a href="javascript:;"><img src="/client/images/page_l.png" height="11" /></a>
                <#else>
                    <a href="${categoryId!'0'}-${brandIndex!0}<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${goods_page.number-1}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>"><img src="/client/images/page_l.png" height="11" /></a> <#-- goods_page.number-1 -->
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
                    <p class="fs14 w84 fl">关注：<span class="blue">${goods.totalclicks!'0'}</span>人</p>
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
                        <a class="a7" href="javascript:addCollect1(${goods.id?c});"><img id="addCollect${goods.id?c}" src="/client/images/content/liebiao_34.png" />关注</a>
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
                        <a class="a1 a0" href="${categoryId!'0'}-${brandIndex!0}<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${goods_page.number-1}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>"><span>上一页</span></a>
                    </#if>
                    
                    <#if goods_page.totalPages gt 0>
                        <#list 1..goods_page.totalPages as page>
                            <#if page <= 3 || (goods_page.totalPages-page) < 3 || (goods_page.number+1-page)?abs<3 >
                                <#if page == goods_page.number+1>
                                    <a class="sel" href="javascript:;">${page}</a>
                                <#else>
                                    <a href="${categoryId!'0'}-${brandIndex!0}<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${page-1}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>">${page}</a> <#-- ${page} -->
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
                        <a class="a2" href="${categoryId!'0'}-${brandIndex!0}<#list param_index_list as pindex>-${pindex!'0'}</#list>-${orderId!'0'}<#if sort_id_list??><#list sort_id_list as sortId>-${sortId!'0'}</#list></#if>-${goods_page.number+1}-${leftId!'0'}<#if priceLow?? && priceHigh??>_${priceLow?string("#.##")}-${priceHigh?string("#.##")}</#if>"><span>下一页</span></a>
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

<#include "/client/common_footer.ftl" />

</body>
</html>
