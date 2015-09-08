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
<script src="/client/js/diysite_comment.js"></script>

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
	
	productImgShow("proshowimg","li","proshowmenu","sel",396,396);
	checkBoxShow("assort_menu","a","assort_sum","li","sel");
	//productBoxShow("assort_menu","a","assort_ol","li","assort_sum","ul","sel");
	productBoxWidth(".partside");
	topTitFloat("detail_tit",1100,"detail_tit_sel");
	menuNextPage("#proshowmenubox","menu","li",340,80,"#proshowlast","#proshownext",85,4);//zhangji
});

function sendAddress(){
    var id = <#if shop??>"${shop.id?c}"</#if>;
    $.ajax({
                type: "post",
                url: "/diysite/order/sendAddress",
                data: { "id": id},
                dataType: "json",
                success: function (data) { 
                    if (data.code == 0) {
                       alert("发送成功！请注意查收");
                       $("#sendAddress").removeAttr("href");
                    } else {
                        alert(data.msg);
                    }
                }
            });
}

</script>
</head>
<body>
<#include "/client/common_header.ftl" />

<div class="main">
    <div class="dituxiangxi">
    <div class="pro_box">
        <section class="proinfo_left">
            <menu id="proshowimg">
                <#if shop?? && shop.showPictures??>
                    <#list shop.showPictures?split(",") as uri>
                        <#if uri != "" >
                            <li><img src="${uri!""}" /></li>
                        </#if>
                    </#list>
                </#if>
            </menu>
            <div class="clear h15"></div>
	        <#-- 图片切换  zhangji-->
	        <div class="clear h15"></div>
	        <div style=" width:100%;z-index:50; position: relative;">
	            <a id="proshowlast" href="javascript:void(0);"> < </a>
	            <a id="proshownext" href="javascript:void(0);"> > </a>
	        </div>
	        <#-- 图片切换 end -->
      	    <div id="proshowmenubox" class="mga" style="position:relative;">
            	<menu id="proshowmenu"></menu>
            </div>	
            <div class="clear"></div>
    
        </section><!--proinfo_left END-->
    
    <section class="proinfo_right">
        <h3><#if shop??>${shop.title!''}</#if></h3>
        <div class="pro_price">
        
            <p class="p1">
             门店地址：<#if shop??>${shop.address!''}</#if>
            </p>
            <p class="p1">
             所在区域：<#if shop??>${shop.city!''}</#if>
            </p>
            <p class="p1">
            付款方式：<#if shop??>${shop.payType!''}</#if>
            </p>
            <p class="p1">
            营业时间：<#if shop??>${shop.openTimeSpan!''}</#if>
            </p>
            <p class="p1">
            门店客服：<#if shop??>${shop.serviceTele!''}</#if>
            </p>
            <p class="p1">
            投诉电话：<#if shop??>${shop.complainTele!''}</#if>
            </p>
        </div><!--pro_price END-->
        <table class="pro_choose">
            <tr>
                <td colspan="2">
                    <!--<input type="submit" class="sub" value="立即购买" />-->
                    <a id="sendAddress" href="javascript:sendAddress();" class="sub" style="text-align:center;">地址发送到手机</a>
                    <div class="clear"></div>
                </td>
            </tr>
        </table>
      
    </section><!--proinfo_right END-->
    <div class="clear h20"></div>
</div>
<div class="clear"></div>

<#include "/client/diysite_comment.ftl" />
  
<div class="clear"></div> 
</div><!--main END-->

<div class="clear h50"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>
