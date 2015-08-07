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
<script src="/client/js/Validform_v5.3.2_min.js"></script>
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
    
    //初始化表单验证
    $(".commentForm").Validform({
        tiptype: 3,
        ajaxPost:true,
        callback: function(data) {
            if (data.code==0)
            {
                alert("提交评论成功");
                window.location.reload();
            }
            else
            {
                alert(data.message);
            }
        }
    });
});

function showCommentTr(i, j)
{
    $("#comment-tr" + i + j).toggleClass("hide");
}
// 改变星级
function starChange(type, stars)
{
    if (null == type || null == stars)
    {
        return;
    }
    
    var starCount = parseInt(stars);
    
    // 商品满意度
    if ("goodsStar" == type)
    {
        $("#goodsStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 2:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 3:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 4:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 5:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start01.png" />');
            break;
        default:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');    
        }
    }
    // 专业技能满意度
    else if ("skillStar" == type)
    {
        $("#skillStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 2:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 3:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 4:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 5:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start01.png" />');
            break;
        default:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
                
        }
    }
    // 服务满意度
    else if ("serviceStar" == type)
    {
        $("#serviceStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 2:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 3:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 4:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 5:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start01.png" />');
            break;
        default:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
                
        }
    }
}

function commentJump()
{
    window.location.href="/user/comment/list?statusId=" + $("#commentTypeSelect").val();
}
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
        
        <select id="commentTypeSelect" onchange="javascript:commentJump();">
            <option value="0" <#if statusId==0>selected="selected"</#if>>待评价</option>
            <option value="1" <#if statusId==1>selected="selected"</#if>>已评价</option>                          
        </select>
      </div>
      
        <table class="mymember_evaluate">
            <tr>
                <th colspan="2">商品信息</th>
                <th colspan="1">购买时间</th>
                <th width="200">评价状态</th>
            </tr>
            <#if order_page??>
                <#list order_page.content as order>
                    <#if order.orderGoodsList??>
                        <#list order.orderGoodsList as item>
                            <tr>
                                <td>
                                   <a href="/goods/${item.goodsId}">
                                        <img src="${item.goodsCoverImageUri!''}" width="50" height="50" title="${item.goodsTitle!''}">                                             </a>                                        </td>
                                <td>
                                   <a target="_blank" href="/goods/${item.goodsId}">${item.goodsTitle!''}</a>
                                </td>
                                <td>
                                    ${order.orderTime?string("yyyy-MM-dd")}  
                                </td>
                                <td>
                                    <#if item.isCommented?? && item.isCommented>
                                        <a href="javascript:showCommentTr(${order_index}, ${item_index});">已评价</a>
                                    <#else>
                                        <a href="javascript:showCommentTr(${order_index}, ${item_index});">发表评价</a>
                                    </#if>
                                </td>
                            </tr>
                            <#if item.isCommented?? && item.isCommented>
                                <#if ("comment_" + order.id + "_" + item.id)?eval??>
                                    <#assign comt=("comment_" + order.id + "_" + item.id)?eval>
                                    <tr id="comment-tr${order_index}${item_index}" class="hide">
                                        <td class="td004" colspan="4">
                                            <div class="pb20 lh25">
                                                <input type="radio" disabled="disabled" <#if comt.stars?? && comt.stars==3>checked="checked"</#if>/><span class="mr20"> 好评</span>
                                                <input type="radio" disabled="disabled" <#if comt.stars?? && comt.stars==2>checked="checked"</#if>/><span class="mr20"> 中评</span>
                                                <input type="radio" disabled="disabled" <#if comt.stars?? && comt.stars==1>checked="checked"</#if>/><span class="mr20"> 差评</span>
                                            </div>
                                            <span style="position:absolute;right:88px;top:-13px;">
                                                <img src="/client/images/mymember/arrow06.gif">
                                            </span>
                                            <div class="mymember_eva_div">
                                              <b><font>* </font>商品质量：</b>
                                              <div class="myclear"></div>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.goodsStar?? && comt.goodsStar gt 0>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.goodsStar?? && comt.goodsStar gt 1>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.goodsStar?? && comt.goodsStar gt 2>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.goodsStar?? && comt.goodsStar gt 3>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.goodsStar?? && comt.goodsStar gt 4>start01.png<#else>start03.png</#if>"></a>
                                              <div class="myclear"></div>
                                            </div>
                                            <div class="mymember_eva_div">
                                              <b><font>* </font>同盟店专业技能：</b>
                                              <div class="myclear"></div>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.skillStar?? && comt.skillStar gt 0>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.skillStar?? && comt.skillStar gt 1>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.skillStar?? && comt.skillStar gt 2>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.skillStar?? && comt.skillStar gt 3>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.skillStar?? && comt.skillStar gt 4>start01.png<#else>start03.png</#if>"></a>
                                              <div class="myclear"></div>
                                            </div>
                                            <div class="mymember_eva_div">
                                              <b><font>* </font>服务态度：</b>
                                              <div class="myclear"></div>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.serviceStar?? && comt.serviceStar gt 0>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.serviceStar?? && comt.serviceStar gt 1>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.serviceStar?? && comt.serviceStar gt 2>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.serviceStar?? && comt.serviceStar gt 3>start01.png<#else>start03.png</#if>"></a>
                                              <a class="a001" href="javascript:;"><img src="/client/images/content/<#if comt.serviceStar?? && comt.serviceStar gt 4>start01.png<#else>start03.png</#if>"></a>
                                              <div class="myclear"></div>
                                            </div>
                                            <div class="mymember_eva_div">
                                              <b><font>* </font>评价：</b>
                                              <textarea disabled="disabled">${comt.content!''}</textarea>
                                            </div>
                                        </td>
                                    </tr>
                                </#if>
                            <#else>
                                <tr id="comment-tr${order_index}${item_index}" class="hide">
                                    <form class="commentForm" action="/user/comment/add" method="post">
                                        <input type="hidden" name="orderId" value=${order.id} />
                                        <input type="hidden" name="ogId" value=${item.id} />
                                        <input type="hidden" name="goodsId" value=${item.goodsId} />
                                        <td class="td004" colspan="4">
                                            <div class="pb20 lh25">
                                                <input class="ml20" type="radio" name="stars" value="3" datatype="n" nullmsg="请点击进行评价"/><span class="mr20"> 好评</span>
                                                <input type="radio" name="stars" value="2" datatype="n" nullmsg="请点击进行评价"/><span class="mr20"> 中评</span>
                                                <input type="radio" name="stars" value="1" datatype="n" nullmsg="请点击进行评价"/><span class="mr20"> 差评</span>
                                            </div>
                                            <span style="position:absolute;right:88px;top:-13px;">
                                                <img src="/client/images/mymember/arrow06.gif">
                                            </span>
                                            <div class="mymember_eva_div">
                                              <b><font>* </font>商品质量：</b>
                                              <div class="myclear"></div>
                                              <input id="goodsStar" name="goodsStar" type="hidden" value="1" />
                                              <a class="goodsStar a001" href="javascript:starChange('goodsStar', 1);"><img src="/client/images/content/start03.png"></a>
                                              <a class="goodsStar a001" href="javascript:starChange('goodsStar', 2);"><img src="/client/images/content/start03.png"></a>
                                              <a class="goodsStar a001" href="javascript:starChange('goodsStar', 3);"><img src="/client/images/content/start03.png"></a>
                                              <a class="goodsStar a001" href="javascript:starChange('goodsStar', 4);"><img src="/client/images/content/start03.png"></a>
                                              <a class="goodsStar a001" href="javascript:starChange('goodsStar', 5);"><img src="/client/images/content/start03.png"></a>
                                              <div class="myclear"></div>
                                            </div>
                                            <div class="mymember_eva_div">
                                              <b><font>* </font>同盟店专业技能：</b>
                                              <div class="myclear"></div>
                                              <input id="skillStar" name="skillStar" type="hidden" value="1" />
                                              <a class="skillStar a001" href="javascript:starChange('skillStar', 1);"><img src="/client/images/content/start03.png"></a>
                                              <a class="skillStar a001" href="javascript:starChange('skillStar', 2);"><img src="/client/images/content/start03.png"></a>
                                              <a class="skillStar a001" href="javascript:starChange('skillStar', 3);"><img src="/client/images/content/start03.png"></a>
                                              <a class="skillStar a001" href="javascript:starChange('skillStar', 4);"><img src="/client/images/content/start03.png"></a>
                                              <a class="skillStar a001" href="javascript:starChange('skillStar', 5);"><img src="/client/images/content/start03.png"></a>
                                              <div class="myclear"></div>
                                            </div>
                                            <div class="mymember_eva_div">
                                              <b><font>* </font>服务态度：</b>
                                              <div class="myclear"></div>
                                              <input id="serviceStar" name="serviceStar" type="hidden" value="1" />
                                              <a class="serviceStar a001" href="javascript:starChange('serviceStar', 1);"><img src="/client/images/content/start03.png"></a>
                                              <a class="serviceStar a001" href="javascript:starChange('serviceStar', 2);"><img src="/client/images/content/start03.png"></a>
                                              <a class="serviceStar a001" href="javascript:starChange('serviceStar', 3);"><img src="/client/images/content/start03.png"></a>
                                              <a class="serviceStar a001" href="javascript:starChange('serviceStar', 4);"><img src="/client/images/content/start03.png"></a>
                                              <a class="serviceStar a001" href="javascript:starChange('serviceStar', 5);"><img src="/client/images/content/start03.png"></a>
                                              <div class="myclear"></div>
                                            </div>
                                            <div class="mymember_eva_div">
                                              <b><font>* </font>心得：</b>
                                              <textarea name="content" datatype="*5-255" nullmsg="请输入评价内容"></textarea>
                                            </div>
                                            <div class="mymember_eva_div">
                                              <input class="mysub" type="submit" value="发表评论">
                                            </div>
                                        </td>
                                    </form>
                                </tr>
                            </#if>
                        </#list>
                    </#if>
                </#list>
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




  











