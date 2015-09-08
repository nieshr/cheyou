<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>云南车有同盟商贸有限公司</title>
<meta name="keywords" content="${site.seoKeywords!''}" />
<meta name="description" content="${site.seoDescription!''}" />
<meta name="copyright" content="云南车有同盟商贸有限公司" />
<link rel="shortcut icon" href="/client/images/cheyou.ico" />
<link href="/client/css/common.css" rel="stylesheet" type="text/css" />
<link href="/client/css/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/css/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/css/style.css" rel="stylesheet" type="text/css" />
<link href="/client/css/mymember.css" rel="stylesheet" type="text/css" />
<!--<link href="/client/css/member.css" rel="stylesheet" type="text/css" />-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
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
    $("#form1").Validform({
        tiptype: 3
    });
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
    <div class="mymember_mainbox">
    <div class="mymember_info mymember_info02">
        退换货——服务单明细
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="260">商品名称</th>
            <th width="170">赠送清单</th>
            <th width="105">购买数量</th>
        </tr>
        </thead>
        <tbody>
        <#if order_goods??>
        <tr>
            <td>
                <ul class="list-proinfo" id="removeTheSingleGife">
                    <li class="fore1">
                        <a href="/goods/${order_goods.goodsId?c!''}" target="_blank">
                            <img height="50" width="50" title="${order_goods.goodsTitle!''}" src="${order_goods.goodsCoverImageUri!''}" data-img="1" alt="${order_goods.goodsTitle!''}">
                        </a>
                        <div class="p-info">
                            <a href="/goods/${order_goods.goodsId?c!''}" target="_blank">${order_goods.goodsTitle!''}</a>
                        </div>
                    </li>
                </ul>
            </td>                  
            <td>-</td>
            <td>${order_goods.quantity}</td>
        </tr>
        </#if>
        </tbody>
    </table>
    <#if order_goods??>
    <form method="post" action="/user/return/save" id="form1">
    <table width="100%" border="0">
      <tbody>
          <tr>
            <td> 
                <span style="position:absolute;right:88px;top:-13px;">
                <img src="/client/images/mymember/arrow06.gif"></span>
                <input type="hidden" name="goodsId" value="${order_goods.goodsId?c!''}" />
                <input type="hidden" name="id" value="${order.id?c}" />
                <div class="mymember_eva_div">
                  <b><font>* </font>问题描述：</b>
                  <textarea name="reason" datatype="*5-255" errormsg="问题描述必须大于5个字符，小于255个字符" <#if has_returned??>disabled</#if>></textarea>
                  <span class="Validform_checktip">*问题描述</span>
                </div>
                
                <div class="mymember_eva_div">
                    <b><font>* </font>联系电话：</b>
                    <input type="text" name="telephone" datatype="n8-20" errormsg="请输入正确的电话格式" />
                    <span class="Validform_checktip">*联系电话</span>
                </div>
                
                
                <#if !has_returned??>
                <div class="mymember_eva_div">
                  <input class="mysub" type="submit" value="提交">
                </div>
                </#if>
            </td>
          </tr>
      </tbody>
    </table>
    </form>
    </#if>
    <div class="myclear" style="height:10px;"></div>
        
  </div>
      <!--mymember_info END-->
      
      <script type="text/javascript">
      $(document).ready(function(){
         mymemberMenuCheck("mymember_likecheck","a","mymember_likelist","li","mysel");
    });
    </script>
    </div>
    <!--mymember_center END-->
    <div class="myclear"></div>
  </div>
  <!--mymember_main END-->
  <div class="myclear"></div>
</div>
<!--主体结束-->
<#include "/client/common_footer.ftl" />
<!--底部结束-->
<script type="text/javascript">
      $(document).ready(function(){
         mymemberMenuCheck("mymember_right_menu","a","mymember_right_check","li","mysel");
		 mymemberRightMove("mymember_storybox",70,90,"mymember_story_next",15,3,"a");
		 mymemberRightMove("mymember_gzbox",205,241,"mymember_gznext",15,3,"a");
		 mymemberRightMove("mymember_shinebox",205,310,"mymember_shinenext",15,3,"div");
      });
</script>
</body>
</html>
<!--结束-->
