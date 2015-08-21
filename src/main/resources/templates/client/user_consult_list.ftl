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

<!--mymember-->
<div class="myclear"></div>
<div class="mymember_out">
<div class="mymember_main">
  <!--mymember_head END-->
  <div class="myclear" style="height:20px;"></div>
  
    <#-- 左侧菜单 -->
    <#include "/client/common_user_menu.ftl" />
    <#-- 左侧菜单结束 -->
  
  <div class="mymember_mainbox">

    <div class="mymember_info mymember_info02">
        <div class="mymember_order_search">
            <a class="a001" href="/user/consult/list">全部咨询</a>   
            <div class="clear"></div>
        </div>
        <table class="tb-void">
            <thead>
                <tr>
                <th>咨询商品</th>
                <th>商品名称</th>
                <th align="center">咨询回复</th>
                </tr>
            </thead>			
            <tbody>
                <#if consult_page??>
                    <#list consult_page.content as consult>
                    <tr>
                        <td>
                           <a href="/goods/${consult.goodsId?c}">
                                <img src="${consult.goodsCoverImageUri!''}" width="50" height="50" title="${consult.goodsTitle!''}">                                             </a>                                        </td>
                        <td>
                           <a target="_blank" href="/goods/${consult.goodsId?c}">${consult.goodsTitle!''}</a>
                        </td>
                        <td>
                            我的咨询：${consult.content!''}
                            <br>
                            ${consult.consultTime!''}
                            <br>
                            <#if consult.isReplied>
                            ${consult.reply!''}
                            <#else>
                            （暂无回复）
                            </#if>    
                        </td>
                    </tr>   
                    </#list>
                </#if>  
            </tbody>
		</table>
		
      <div class="myclear" style="height:10px;"></div>
      <div class="mymember_page">
        <#if consult_page??>
            <#assign continueEnter=false>
            <#if consult_page.totalPages gt 0>
                <#list 1..consult_page.totalPages as page>
                    <#if page <= 3 || (consult_page.totalPages-page) < 3 || (consult_page.number+1-page)?abs<3 >
                        <#if page == consult_page.number+1>
                            <a class="mysel" href="javascript:;">${page}</a>
                        <#else>
                            <a href="/user/consult/list?page=${page-1}&keywords=${keywords!''}">${page}</a>
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
    </div><!--mymember_info END-->
    
    
    <script type="text/javascript">
      $(document).ready(function(){
         mymemberMenuCheck("mymember_likecheck","a","mymember_likelist","li","mysel");
    });
    </script>
    
    
  </div><!--mymember_center END-->
  </div>
<div class="clear h40"></div>

<#include "/client/common_footer.ftl" />

</body>
</html>




  











