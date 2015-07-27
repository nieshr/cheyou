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

<div class="myclear"></div>
<div class="mymember_out">
<div class="mymember_main">
  <!--mymember_head END-->
  <div class="myclear" style="height:20px;"></div>
    <#-- 左侧菜单 -->
    <#include "/client/common_user_menu.ftl" />
    <#-- 左侧菜单结束 -->
    
  <div class="mymember_mainbox">

    <div class="mymember_info">
      <div class="mymember_order_search">
        <a class="a001" href="/user/comment/list">全部评论</a>
        
        <div class="clear"></div>
      </div>
      
        <table class="mymember_evaluate">
            <tr>
                <th colspan="2">商品信息</th>
                <th colspan="1">购买时间</th>
                <th width="200">评价状态</th>
            </tr>
            <#if order_page??>
                
            </#if>
            <#if comment_page??>
                <#list comment_page.content as comment>
                <tr>
                    <td>
                       <a href="/goods/${comment.goodsId}">
                            <img src="${comment.goodsCoverImageUri!''}" width="50" height="50" title="${comment.goodsTitle!''}">                                             </a>                                        </td>
                    <td>
                       <a target="_blank" href="/goods/${comment.goodsId}">${comment.goodsTitle!''}</a>
                    </td>
                    <td>
                        我的评论：${comment.content!''}
                        <br>
                        ${comment.commentTime!''}
                        <br>
                        <#if comment.isReplied>
                        ${comment.reply!''}
                        <#else>
                        （暂无回复）
                        </#if>    
                    </td>
                </tr>
                
                <tr id="mymember_eva01" class="">
                    <td class="td004" colspan="4">
                        <span style="position:absolute;right:258px;top:-13px;">
                            <img src="/client/images/mymember/arrow06.gif">
                        </span>
                        <div class="mymember_eva_div">
                          <b><font>* </font>评分：</b>
                          <div class="myclear"></div>
                          <a class="a001" href="#"><img src="/client/images/mymember/star02.png"></a>
                          <a class="a001" href="#"><img src="/client/images/mymember/star02.png"></a>
                          <a class="a001" href="#"><img src="/client/images/mymember/star02.png"></a>
                          <a class="a001" href="#"><img src="/client/images/mymember/star01.png"></a>
                          <a class="a001" href="#"><img src="/client/images/mymember/star01.png"></a>
                          <div class="myclear"></div>
                        </div>
                        <div class="mymember_eva_div">
                          <b><font>* </font>心得：</b>
                          <textarea></textarea>
                        </div>
                        <div class="mymember_eva_div">
                          <b><font>* </font>晒单：</b>
                          <div class="myclear"></div>
                          <a class="a002" href="#"><img src="/client/images/mymember/img01.jpg" width="75"></a>
                          <a class="a002" href="#">添加图片</a>
                          <div class="myclear"></div>
                        </div>
                        <div class="mymember_eva_div">
                          <input class="mysub" type="submit" value="发表评论">
                        </div>
                    </td>
                </tr>
                </#list>
            </#if>  
        </table>
      
      <div class="myclear" style="height:10px;"></div>
      <div class="mymember_page">
        <#if comment_page??>
            <#assign continueEnter=false>
            <#if comment_page.totalPages gt 0>
                <#list 1..comment_page.totalPages as page>
                    <#if page <= 3 || (comment_page.totalPages-page) < 3 || (comment_page.number+1-page)?abs<3 >
                        <#if page == comment_page.number+1>
                            <a class="mysel" href="javascript:;">${page}</a>
                        <#else>
                            <a href="/user/comment/list?page=${page-1}&keywords=${keywords!''}">${page}</a>
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

  </div><!--mymember_center END-->
  
<div class="myclear"></div>
</div>
<!--mymember END-->
<div class="clear h40"></div>
<#include "/client/common_footer.ftl" />
</div>
</body>
</html>




  











