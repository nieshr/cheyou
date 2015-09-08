<!DOCTYPE html>
<head>
<meta charset="utf-8">
<title>${site.seoTitle!''}-支付成功</title>
<meta name="keywords" content="${site.seoKeywords!''}" />
<meta name="description" content="${site.seoDescription!''}" />
<meta name="copyright" content="${site.copyright!''}" />
<link rel="shortcut icon" href="/client/images/cheyou.ico" />
<link href="/client/css/layout.css" rel="stylesheet" type="text/css" />
<link href="/client/css/style.css" rel="stylesheet" type="text/css" />
<link href="/client/css/common.css" rel="stylesheet" type="text/css" />
<link href="/client/css/mymember.css" rel="stylesheet" type="text/css" />
<link href="/client/css/gwc.css" rel="stylesheet" type="text/css" />
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/mymember.js"></script>
<script src="/client/js/jquery.cityselect.js"></script>
<script src="/client/js/lhgdialog.js"></script>
<script src="/client/js/order_info.js"></script>
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
    
    chooseMoreShow();
    
});
</script>
</head>
<body>
<!-- header开始 -->
<#include "/client/common_header.ftl" />
<!-- header结束 -->

<!--mymember-->
<div class="myclear"></div>

<div class="mymember_out">
    <div class="gwcbg">
    <article id="main" class="mt20">
      <div class="order bor fl">
            <div class="o-left">
                <b style="float:left; margin:10px 15px 0 0"><img src="/client/images/ok.png" width="32" height="32" /></b>
                <h3 class="o-title">
                  感谢您，订单提交成功！订单号：<#if order??>${order.orderNumber!''}</#if>
                </h3>
                <p class="o-tips">
                    车有同盟提供
                        <span class="pay-safeguard j_uiPop">
                        <em class="ps-title j_uiBaozhangPop">在线支付保障</em>
                        </span>
                        请您放心购买。
                </p>
            </div>
            <div class="o-right">
                <div class="o-price">
                    <em>货到付款</em><strong><#if order??>${order.totalPrice?string("0.00")}<#else>0</#if></strong><em>元</em>
                </div>
                <div class="o-detail" id="orderDetail"><a href="/user/order?id=<#if order??>${order.id?c}</#if>">订单详情 ></a></div>
                
            </div>
            <div class="clr"></div>
        </div>
    </article>
    </div>
<!--主体结束-->

<#include "/client/common_footer.ftl" />

</div>
</body>
</html>
<!--结束-->
