<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>云南车有同盟商贸有限公司</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="云南车有同盟商贸有限公司" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/common.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
  searchTextClear(".logintext01","手机号/邮箱","#999","#333");
  searchTextClear(".logintext02","输入密码","#999","#333");
});
$(document).ready(function(){  
     //初始化表单验证
    $(".commentForm").Validform({
        tiptype: 4,
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
            $("a.goodsStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/> ');
            $("a.goodsStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 2:
            $("a.goodsStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 3:
            $("a.goodsStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/touch/images/star01.png" height="15" />');
            $("a.goodsStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 4:
            $("a.goodsStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 5:
            $("a.goodsStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/touch/images/star01.png" height="15"/>');
            break;
        default:
            $("a.goodsStar").eq(0).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.goodsStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');    
        }
    }
    // 同盟店专业技能
     else if ("skillStar" == type)
    {
        $("#skillStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.skillStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 2:
            $("a.skillStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 3:
            $("a.skillStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 4:
            $("a.skillStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 5:
            $("a.skillStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/touch/images/star01.png" height="15"/>');
            break;
        default:
            $("a.skillStar").eq(0).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.skillStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
                
        }
    }
    // 同盟店服务态度
    else if ("serviceStar" == type)
    {
        $("#serviceStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.serviceStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 2:
            $("a.serviceStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 3:
            $("a.serviceStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 4:
            $("a.serviceStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 5:
            $("a.serviceStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/touch/images/star01.png" height="15"/>');
            break;
        default:
            $("a.serviceStar").eq(0).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.serviceStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
                
        }
    }
     else if ("compositeStar" == type)
    {
        $("#compositeStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.compositeStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 2:
            $("a.compositeStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 3:
            $("a.compositeStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 4:
            $("a.compositeStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(3).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
            break;
        case 5:
            $("a.compositeStar").eq(0).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(1).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(2).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(3).html('<img src="/touch/images/star01.png" height="15"/>');
            $("a.compositeStar").eq(4).html('<img src="/touch/images/star01.png" height="15"/>');
            break;
        default:
            $("a.compositeStar").eq(0).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(1).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(2).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(3).html('<img src="/touch/images/star03.png" height="15"/>');
            $("a.compositeStar").eq(4).html('<img src="/touch/images/star03.png" height="15"/>');
                
        }
    }
}
</script>
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>登录</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<menu class="whitebg mymenu_list">
    <#if tdOrderGoods??>
      <a style="border:none;" href="/touch/goods/${tdOrderGoods.goodsId?c}">
        <b><img src="${tdOrderGoods.goodsCoverImageUri}" /></b>
        <p>${tdOrderGoods.goodsTitle!''}<span class="sp1">￥${tdOrderGoods.price?string("0.00")}</span></p>
        <p class="p1">${tdOrderGoods.goodsSubTitle!''}<span class="sp2">X${tdOrderGoods.quantity!''}</span></p>
        <div class="clear"></div>
      </a>
    </#if>
</menu>
<#if comment??>
    <div class="mainbox myassess">
     <p class="fs08 pb10">同盟商品满意度：
       <a href="#"><img src="/touch/images/<#if comment.goodsStar?? && comment.goodsStar gt 0>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.goodsStar?? && comment.goodsStar gt 1>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.goodsStar?? && comment.goodsStar gt 2>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.goodsStar?? && comment.goodsStar gt 3>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.goodsStar?? && comment.goodsStar gt 4>star01.png<#else>star03.png</#if>" height="15" /></a>
     </p>
     <p class="fs08 pb10">同盟店专业技能：
       <a href="#"><img src="/touch/images/<#if comment.skillStar?? && comment.skillStar gt 0>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.skillStar?? && comment.skillStar gt 1>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.skillStar?? && comment.skillStar gt 2>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.skillStar?? && comment.skillStar gt 3>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.skillStar?? && comment.skillStar gt 4>star01.png<#else>star03.png</#if>" height="15" /></a>
     </p>
     <p class="fs08 pb10">同盟店服务态度：
       <a href="#"><img src="/touch/images/<#if comment.serviceStar?? && comment.serviceStar gt 0>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.serviceStar?? && comment.serviceStar gt 1>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.serviceStar?? && comment.serviceStar gt 2>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.serviceStar?? && comment.serviceStar gt 3>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.serviceStar?? && comment.serviceStar gt 4>star01.png<#else>star03.png</#if>" height="15" /></a>
     </p>
     <p class="fs08 pb10">同盟店综合印象：
       <a href="#"><img src="/touch/images/<#if comment.compositeStar?? && comment.compositeStar gt 0>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.compositeStar?? && comment.compositeStar gt 1>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.compositeStar?? && comment.compositeStar gt 2>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.compositeStar?? && comment.compositeStar gt 3>star01.png<#else>star03.png</#if>" height="15" /></a>
       <a href="#"><img src="/touch/images/<#if comment.compositeStar?? && comment.compositeStar gt 4>star01.png<#else>star03.png</#if>" height="15" /></a>
     </p>
     <textarea>${comment.content!''}</textarea>
      <p class="pt10 pb10 w100">
          <input type="radio" disabled="disabled" <#if comment.stars?? && comment.stars==3>checked="checked"</#if>/>好评
          <input type="radio" disabled="disabled" <#if comment.stars?? && comment.stars==2>checked="checked"</#if>/>中评
          <input type="radio" disabled="disabled" <#if comment.stars?? && comment.stars==1>checked="checked"</#if>/>差评
      </p>
    <!--  <input type="submit" value="提交" class="sub" /> -->
      <div class="clear h20"></div>
    </div>
<#else>
<form class="commentForm" action="/touch/user/comment/add" method="post">
    <input type="hidden" name="orderId" value=${orderId?c} />
    <input type="hidden" name="ogId" value=${tdOrderGoods.id?c} />
    <input type="hidden" name="goodsId" value=${tdOrderGoods.goodsId?c} />
    <div class="mainbox myassess">
     <p class="fs08 pb10">同盟商品满意度：
     <input id="goodsStar" name="goodsStar" type="hidden" value="1" datatype="n" nullmsg="请点击进行评价"/>
       <a class="goodsStar" href="javascript:starChange('goodsStar', 1);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="goodsStar" href="javascript:starChange('goodsStar', 2);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="goodsStar" href="javascript:starChange('goodsStar', 3);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="goodsStar" href="javascript:starChange('goodsStar', 4);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="goodsStar" href="javascript:starChange('goodsStar', 5);"><img src="/touch/images/star03.png" height="15" /></a>
     </p>
     <p class="fs08 pb10">同盟店专业技能：
     <input id="skillStar" name="skillStar" type="hidden" value="1" datatype="n" nullmsg="请点击进行评价"/>
       <a class="skillStar" href="javascript:starChange('skillStar', 1);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="skillStar" href="javascript:starChange('skillStar', 2);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="skillStar" href="javascript:starChange('skillStar', 3);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="skillStar" href="javascript:starChange('skillStar', 4);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="skillStar" href="javascript:starChange('skillStar', 5);"><img src="/touch/images/star03.png" height="15" /></a>
     </p>
     <p class="fs08 pb10">同盟店服务态度：
     <input id="serviceStar" name="serviceStar" type="hidden" value="1" datatype="n" nullmsg="请点击进行评价"/>
       <a class="serviceStar" href="javascript:starChange('serviceStar', 1);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="serviceStar" href="javascript:starChange('serviceStar', 2);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="serviceStar" href="javascript:starChange('serviceStar', 3);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="serviceStar" href="javascript:starChange('serviceStar', 4);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="serviceStar" href="javascript:starChange('serviceStar', 5);"><img src="/touch/images/star03.png" height="15" /></a>
     </p>
     <p class="fs08 pb10">同盟店综合印象：
     <input id="compositeStar" name="compositeStar" type="hidden" value="1" datatype="n" nullmsg="请点击进行评价"/>
       <a class="compositeStar" href="javascript:starChange('compositeStar', 1);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="compositeStar" href="javascript:starChange('compositeStar', 2);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="compositeStar" href="javascript:starChange('compositeStar', 3);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="compositeStar" href="javascript:starChange('compositeStar', 4);"><img src="/touch/images/star03.png" height="15" /></a>
       <a class="compositeStar" href="javascript:starChange('compositeStar', 5);"><img src="/touch/images/star03.png" height="15" /></a>
     </p>
     <textarea name="content">请输入您的评价</textarea>
      <p class="pt10 pb10 w100">
          <input type="radio" name="stars" value="3" datatype="n" nullmsg="请点击进行评价"/>好评
          <input type="radio" name="stars" value="2" datatype="n" nullmsg="请点击进行评价"/>中评
          <input type="radio" name="stars" value="1" datatype="n" nullmsg="请点击进行评价"/>差评
      </p>
      <input type="submit" value="提交" class="sub" /> 
      <div class="clear h20"></div>
    </div>
</form>    
</#if>


<div class="clear"></div>
<section class="botlogin">

</section>
<footer class="comfoot main">
    <a href="/">电脑版</a>
    <a href="#">触屏版</a>
</footer>
<p class="bottext mainbox">Copyright©2015 www.cytm99.com 保留所有版权</p>
<p class="bottext mainbox">滇ICP备0932488号</p>

</body>
</html>
