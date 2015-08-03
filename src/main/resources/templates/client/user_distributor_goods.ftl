<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>云南车有同盟商贸有限公司</title>
<meta name="keywords" content="${site.seoKeywords!''}" />
<meta name="description" content="${site.seoDescription!''}" />
<meta name="copyright" content="云南车有同盟商贸有限公司" />
<link href="/client/css/common.css" rel="stylesheet" type="text/css" />
<link href="/client/css/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/css/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/css/style.css" rel="stylesheet" type="text/css" />
<link href="/client/css/mymember.css" rel="stylesheet" type="text/css" />
<!--<link href="/client/css/member.css" rel="stylesheet" type="text/css" />-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/mymember.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>

<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="/client/js/DD_belatedPNG_0.0.8a.js" ></script>
<script>
DD_belatedPNG.fix('.,img,background');
</script>
<![endif]-->
<script type="text/javascript">
  $(document).ready(function(){
    menuDownList("top_phone","#top_phonelist",".a1","sel");
    phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
    navDownList("navdown","li",".nav_showbox");
    menuDownList("mainnavdown","#navdown",".a2","sel");
    checkNowHover("shopping_down","shopping_sel");
});
</script>

</head>
<body>
<!-- header开始 -->
<#include "/client/common_header.ftl" />
<!-- header结束 -->
<div class="mymember_out">
  <div class="mymember_main">
    <div class="myclear" style="height:20px;"></div>
    <#include "/client/common_user_menu.ftl" />
    <#-- 左侧菜单结束 -->
<form name="form1" action="/user/distributor/goods" method="GET">  
<script type="text/javascript">
var theForm = document.forms['form1'];
if (!theForm) {
    theForm = document.form1;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.submit();
    }
}
</script>
    <div class="mymember_mainbox">
      <div class="mymember_info mymember_info02">
        <div class="mymember_order_search"> <a class="a001" href="/user/collect/list">收藏商品</a>
          <input class="mysub" type="submit" value="查询">
          <input class="mytext" type="text" name="keywords" onfocus="if(value=='商品名称') {value=''}" onblur="if (value=='') {value='商品名称'}" value="${keywords!'商品名称'}">
          <div class="clear"></div>
        </div>
        <table>
          <tbody><tr class="mymember_infotab_tit01">
            <th colspan="2">商品</th>
            <th width="80">金额</th>
            <th width="70">返现额</th>
          </tr>
          <#if goods_page??>
            <#list goods_page.content as item>
                <tr id="tr_1424195166">
                    <td>
                        <a href="/goods/${item.id}" target="_blank">
                            <strong><img width="100" height="100" src="${item.coverImageUri!''}"></strong>
                        </a>
                    </td>
                    <td class="tb01">
                        <a href="/goods/${item.id}" target="_blank">${item.title!''}</a>
                    </td>
                    <td class="tb02">￥${item.salePrice?string("0.00")}</td>
                    <td>￥${item.returnPrice?string("0.00")}</td>
                  </tr>
            </#list>
          </#if>
        </tbody>
        </table>
        <div class="myclear" style="height:10px;"></div>
        <div class="mymember_page">
          <#if goods_page??>
                <#assign continueEnter=false>
                <#if goods_page.totalPages gt 0>
                    <#list 1..goods_page.totalPages as page>
                        <#if page <= 3 || (goods_page.totalPages-page) < 3 || (goods_page.number+1-page)?abs<3 >
                            <#if page == goods_page.number+1>
                                <a class="mysel" href="javascript:;">${page}</a>
                            <#else>
                                <a href="/user/distributor/goods?page=${page-1}&keywords=${keywords!''}">${page}</a>
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
            </#if>
        </div>
    </div>
    <div class="myclear"></div>
  </div>
</form>
  <div class="myclear"></div>
  </div>
</div>
<!--主体结束-->
<#include "/client/common_footer.ftl" />
<!--底部结束-->
<script type="text/javascript">
      $(document).ready(function(){
         mymemberMenuCheck("mymember_right_menu","a","mymember_right_check","li","mysel");
		 mymemberMenuCheck("mymember_likecheck","a","mymember_likelist","li","mysel");
		 mymemberRightMove("mymember_storybox",70,90,"mymember_story_next",15,3,"a");
		 mymemberRightMove("mymember_gzbox",205,241,"mymember_gznext",15,3,"a");
		 mymemberRightMove("mymember_shinebox",205,310,"mymember_shinenext",15,3,"div");
      });
</script>
</body>
</html>
<!--结束-->
