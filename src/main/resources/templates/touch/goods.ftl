<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if goods??>${goods.seoTitle!''}-</#if></title>
<meta name="keywords" content="${goods.seoKeywords!''}">
<meta name="description" content="${goods.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/common.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
  searchTextClear(".comserch_text","搜索关键字","#999","#333");
  indexBanner("box","sum",300,5000,"pronum");
  
  $("#id-minus").click(function(){
        var q = parseInt($("#quantity").val());
        
        if (q > 1)
        {
            $("#quantity").val(q-1);
        }
        
        $("#addCart").attr("href", "/cart/init?id=${goods.id}&quantity=" + $("#quantity").val() + "<#if qiang??>&qiang=${qiang}</#if>");
    });
    
    $("#id-plus").click(function(){
        var q = parseInt($("#quantity").val());
        <#if goods.leftNumber??>
            if (q < ${goods.leftNumber!'0'})
            {
                $("#quantity").val(q+1);
            }
            else
            {
                alert("已达到库存最大值");
            }
        <#else>
            $("#quantity").val(q+1);
        </#if>
        $("#addCart").attr("href", "/cart/init?id=${goods.id}&quantity=" + $("#quantity").val() + "<#if qiang??>&qiang=${qiang}</#if>");
    
    });
});

function addCollect(goodsId)
{
    if (undefined == goodsId)
    {
        return;
    }
    
    $.ajax({
        type:"post",
        url:"/user/collect/add",
        data:{"goodsId": goodsId},
        dataType: "json",
        success:function(res){
            
            alert(res.message);
            
            // 需登录
            if (res.code==1)
            {
                setTimeout(function(){
                    window.location.href = "/touch/login";
                }, 1000); 
            }
        }
    });
}
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>产品详细</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->
<!--comcheck END-->
<section id="box">
  <ul id="sum">
    <#if goods.showPictures??>
        <#list goods.showPictures?split(",") as uri>
            <#if ""!=uri>
                <li><img src="${uri!''}" /></li>
            </#if>
        </#list>
    </#if>
  </ul>
  <div class="clear"></div>
</section><!--我是banner-->
<div class="main" style="z-index:10;">
  <a class="pro_share" href="javascript:addCollect(${goods.id});">关注</a>
  <h3 class="protext red">￥<#if goods.salePrice??>${goods.salePrice?string("0.00")}</#if><span class="unl-lt c9 fs07 ml10">￥<#if goods.marketPrice??>${goods.marketPrice?string("0.00")}</#if></span></h3>
  <p class="center fs09">${goods.title!''}</p>
  <p class="center fs07 red mb10">${goods.subTitle!''}</p>

  <div class="protext">
    <table>
        <tr>
            <th width="80">选择同盟店：</th>
            <td>
              <select>
                <option value="">请选择...</option>
                <#list diy_site_list as item>
                    <option value="${item.id}">${item.title!''}</option>
                </#list>
              </select> 
            </td>
        </tr>
      
        <#if total_select??>
            <#if 1==total_select>
                <tr>
                    <th>${select_one_name!''}：</th>
                    <td>
                        <#if select_one_goods_list??>
                        <#list select_one_goods_list as item>
                            <a <#if item.selectOneValue==one_selected>class="sel"</#if> href="/touch/goods/${item.id}">${item.selectOneValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
            <#elseif 2==total_select>
                <tr>
                    <th>${select_one_name!''}：</th>
                    <td>
                        <#if select_one_goods_list??>
                        <#list select_one_goods_list as item>
                            <a <#if item.selectOneValue==one_selected>class="sel"</#if> href="/touch/goods/${item.id}">${item.selectOneValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <th>${select_two_name!''}：</th>
                    <td>
                        <#if select_two_goods_list??>
                        <#list select_two_goods_list as item>
                            <a <#if item.selectTwoValue==two_selected>class="sel"</#if> href="/touch/goods/${item.id}">${item.selectTwoValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
            <#elseif 3==total_select>
                <tr>
                    <th>${select_one_name!''}：</th>
                    <td>
                        <#if select_one_goods_list??>
                        <#list select_one_goods_list as item>
                            <a <#if item.selectOneValue==one_selected>class="sel"</#if> href="/touch/goods/${item.id}">${item.selectOneValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <th>${select_two_name!''}：</th>
                    <td>
                        <#if select_two_goods_list??>
                        <#list select_two_goods_list as item>
                            <a <#if item.selectTwoValue==two_selected>class="sel"</#if> href="/touch/goods/${item.id}">${item.selectTwoValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <th>${select_three_name!''}：</th>
                    <td>
                        <#if select_three_goods_list??>
                        <#list select_three_goods_list as item>
                            <a <#if item.selectThreeValue==three_selected>class="sel"</#if> href="/touch/goods/${item.id}">${item.selectThreeValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
            </#if>
        </#if>
      
      
    </table>
  </div>
  
  <a class="protext" href="/touch/goods/detail/${goods.id}">图文详情</a>
  <a class="protext" href="/touch/goods/param/${goods.id}">规格参数</a>
  <a class="protext" href="/touch/goods/comment/${goods.id}">车友评价（${comment_count!'0'}）</a>
  <a class="protext" href="/touch/goods/consult/${goods.id}">留言咨询（${consult_page.totalElements!'0'}）</a>
 
</div><!--main END-->

<div class="clear h40"></div>
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
    <a href="/goods/${goods.id}">电脑版</a>
    <a href="/touch/goods/${goods.id}">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<p class="bottext mainbox">${site.icpNumber!''}</p>
<div class="buyfoot_bg"></div>
<footer class="buyfoot">
  <div class="mainbox">
    <div class="buynum">
      <a id="id-plus" href="javascript:;">+</a>
      <a class="a1"id="id-minus" href="javascript:;">-</a>
      <input type="text" id="quantity" class="text" value="1" />
    </div>
    <a id="addCart" class="fr" href="/cart/init?id=${goods.id}<#if qiang??>&qiang=${qiang}</#if>">加入购物车</a>
    <div class="clear"></div>
  </div>
</footer>
</body>
</html>
