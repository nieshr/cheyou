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
<script src="/client/js/Validform_v5.3.2_min.js"></script>
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

    function couponConfirm(){
        var mobile = $("#mobile").val();
        var password = $("#password").val();
        $.ajax({
                type: "post",
                url: "/diysite/order/couponconfirm",
                data: { "mobile": mobile, "password": password },
                dataType: "json",
                success: function (data) { 
                    if (data.code == 0) {
                       window.location.reload();
                       alert("核销成功！");
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

<div class="myclear"></div>
<div class="mymember_out">
<div class="mymember_main">
    
    <!--mymember_head END-->
    <div class="myclear" style="height:20px;"></div>
  
    <#-- 左侧菜单 -->
    <#include "/client/diysite_user_menu.ftl" />
    <#-- 左侧菜单结束 -->
    
    <form name="form1" id="form1" action="/diysite/order/couponconfirm" method="POST">  
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
            <div class="mymember_order_search"> 
                <a class="a001" href="/user/diysite/member">体验券核销</a>
               
                <div class="clear"></div>
            </div>
             <section class="loginbox">
                <p>请输入手机号</p>
                <input id="mobile" class="text" type="text" datatype="m" errormsg="请填写正确手机号"/>
                <p>请输入消费密码</p>
                <input id="password" class="text" type="text" datatype="s4-4" errormsg="请填写4位数字"/>
                <div class="clear h15"></div>
                <input id="btn_login" type="button" onclick="couponConfirm();" class="sub" value="确认核销" />
                <div class="clear h20"></div>
            </section>
            <table>
                <tr class="mymember_infotab_tit01">
                    <th width="60">序号</th>
                    <th width="100">类型</th>
                    <th width="100">用户名</th>
                    <th width="80">手机号</th>
                    <th width="80">车牌号</th>
                    <th width="80">领取日期</th>
                    <th width="90">核销日期</th>
                    <th width="90">本店会员</th>
                                                      
                </tr>
                <#if member_page??>
                    <#list member_page.content as item>
                        <tr id="tr_1424195166">
                            <td>
                                <p>${item_index+1}</p>
                            </td>  
                            <td>
                                <p>${item.typeTitle!''}</p>
                            </td>  
                            <td>
                                <p>${item.username!''}</p>                              
                            </td> 
                            <td>
                                <p>${item.mobile!''}</p>
                            </td>                                                   
                            <td>
                                <p>${item.carCode!''}</p>
                            </td>
                            <td>
                                <p>${item.getTime!''}</p>
                            </td>
                            <td>
                                <p>${item.confirmTime!''}</p>
                            </td>
                            <td>
                                <#if diysite?? && item.userDiysiteId??>
                                    <#if item.userDiysiteId == diysite.id>
                                        <p>是</p>
                                    <#else>
                                        <p>否</p>
                                    </#if>
                                </#if>                               
                            </td>
                          </tr>
                    </#list>
                </#if>
            </table>
            <div class="myclear" style="height:10px;"></div>
            <div class="mymember_page">
                <#if member_page??>
                    <#assign continueEnter=false>
                    <#if member_page.totalPages gt 0>
                        <#list 1..member_page.totalPages as page>
                            <#if page <= 3 || (member_page.totalPages-page) < 3 || (member_page.number+1-page)?abs<3 >
                                <#if page == member_page.number+1>
                                    <a class="mysel" href="javascript:;">${page}</a>
                                <#else>
                                    <a href="/diysite/order/couponconfirm?page=${page-1}">${page}</a>
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
    </form>
    <div class="myclear"></div>
</div>
<!--mymember_main END-->
<div class="myclear"></div>
</div>
<!--mymember END-->

<#include "/client/common_footer.ftl" />

</div>
</body>
</html>