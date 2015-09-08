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
  
  <div class="mymember_scan_check">
        <a class="mysel" href="javascript:;">全部分类</a>
  </div>
  
<script type="text/javascript">
$(document).ready(function(){
    memberScanList("scan_line",".mymember_scan");
});
</script>

  <div class="mymember_scan">
    <span id="scan_line"></span>
        <#if recent_page??>
            <#list recent_page.content as rg>
                <#if !lastTime?? || rg.visitTime?string("yyyy-MM-dd") != lastTime?string("yyyy-MM-dd")>
                    <div class="myclear"></div>
                    <#assign lastTime=rg.visitTime>
                    <h3>${rg.visitTime?string("MM月dd日")}<span>${rg.visitTime?string("yyyy-MM-dd")}</span><i></i></h3>
                    <div class="myclear"></div>
                </#if>
                <div class="mymember_scanpart">
                  <#--
                  <a class="a001" href="#">
                    <img src="/client/images/mymember/delete.png">
                  </a>
                  -->
                  <a href="/goods/${rg.goodsId?c!''}">
                    <img src="${rg.goodsCoverImageUri!''}" width="220" height="220">
                  </a>
                  <p>￥${rg.goodsSalePrice?string("#.##")}</p>
                </div>
            </#list>
        </#if>
    <div class="myclear"></div>
  </div>
  
</div><!--mymember_main END-->
</div>
<!--mymember END-->

<#include "/client/common_footer.ftl" />

</div>
</body>
</html>




  











