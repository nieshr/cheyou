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
<div class="mymember_out">
  <div class="mymember_main">
    <div class="myclear" style="height:20px;"></div>
    <#include "/client/common_user_menu.ftl" />
    <#-- 左侧菜单结束 -->
    <div class="mymember_mainbox">
    <div class="mymember_info mymember_info02">
              <div class="separationInfo sepBasic">
                    <h5> 我的下级会员 <span id="TellToUser"></span> </h5>
             </div>
    
            <div class="record">
                <div class="money fl">
                    <span>下级会员总数：<b><#if lower_page??>${lower_page.totalElements}</#if></b></span>
                </div>
            </div>
            <div class="clear"></div> 
             
        <div id="mypurse-content" class="mypurse-content">
            <div class="content-title">会员列表</div>
            <div class="content-bg-hd">
    
                <div class="content-bg-hd">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <th width="9%" height="35">编号</th>
                        <th width="17%">用户名</th>
                        <th width="16%">注册时间</th>
                        <th width="18%">最后登录时间</th>
                        <th width="18%">消费总额</th>
                      </tr>
                    </table>
                </div>
            
                <div class="content-bg">
                <table border="0" cellpadding="0" cellspacing="0">
                    <tbody class="normal-status">
                        <#if lower_page??>
                            <#list lower_page.content as user>
                                <tr class="">
                                    <td width="9%">${user.id?c}</td>
                                    <td width="17%">${user.username!''}</td>
                                    <td width="16%">${user.registerTime!''}</td>
                                    <td width="12%">${user.lastLoginTime!''}</td>
                                    <td width="18%">${user.totalSpendCash!''}</td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
                </div>     
            </div>
            
            
        </div>  
        <div class="mymember_page">
        <#if lower_page??>
            <#assign continueEnter=false>
            <#if lower_page.totalPages gt 0>
                <#list 1..lower_page.totalPages as page>
                    <#if page <= 3 || (lower_page.totalPages-page) < 3 || (lower_page.number+1-page)?abs<3 >
                        <#if page == lower_page.number+1>
                            <a class="mysel" href="javascript:;">${page}</a>
                        <#else>
                            <a href="/user/distributor/lower?page=${page-1}">${page}</a>
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
		 mymemberMenuCheck("mymember_likecheck","a","mymember_likelist","li","mysel");
		 mymemberRightMove("mymember_storybox",70,90,"mymember_story_next",15,3,"a");
		 mymemberRightMove("mymember_gzbox",205,241,"mymember_gznext",15,3,"a");
		 mymemberRightMove("mymember_shinebox",205,310,"mymember_shinenext",15,3,"div");
      });
</script>
</body>
</html>
<!--结束-->
