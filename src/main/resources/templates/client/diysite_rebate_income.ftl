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

<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
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
<#include "/client/common_header.ftl" />

<!--mymember-->
<div class="myclear"></div>
<div class="mymember_out">
    <div class="mymember_main">
    <div class="myclear" style="height:20px;"></div>
    <#include "/client/diysite_user_menu.ftl" />
  
    <form name="form1" action="/diysite/order/rebateincome" method="POST">
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
                   <a class="a001" href="/diysite/order/rebateincome">返利收入</a>
                </div>
                <table>
                    <tr class="mymember_infotab_tit01">
                        <th>订单编号</th>
                        <th width="80">用户</th>
                        <th width="80">收货电话</th>
                        <th width="90">订单金额</th>
                        <th width="90">返利</th>
                        <th width="90">
                            <select name="timeId" onchange="javascript:setTimeout(__doPostBack('statusId',''), 0)">
                                <option value="0" <#if !time_id?? || time_id==0>selected="selected"</#if>>所有订单</option>
                                <option value="1" <#if time_id==1>selected="selected"</#if>>今天</option>
                                <option value="2" <#if time_id==2>selected="selected"</#if>>最近一周</option>
                                <option value="3" <#if time_id==3>selected="selected"</#if>>最近一个月</option>
                                <option value="4" <#if time_id==4>selected="selected"</#if>>最近三个月</option>
                                <option value="6" <#if time_id==6>selected="selected"</#if>>最近半年</option>
                                <option value="12" <#if time_id==12>selected="selected"</#if>>最近一年</option>                              
                            </select>
                        </th>                        
                        <th width="160">返利总额：￥${rebates?string("0.00")}</th>                       
                    </tr>    
                    <#if order_page??>
                        <#list order_page.content as order>                            
                            <tr>
                              <td>
                                                                                                                 订单编号：<a href="/diysite/order?id=${order.id?c}" id="spanOrderNumber">${order.orderNumber!''}</a>
                              </td>
                              <td>${order.username!''}</td>
                              <td>${order.shippingPhone!''}</td>
                              <td>
                                <p>￥${order.totalPrice?string("#.##")}</p>
                                <p>${order.payTypeTitle!''}</p>
                              </td>
                              <td>${order.rebate!''}</td>
                              <td class="td003">
                                ${order.orderTime!''}
                              </td>
                              <td>
                                <p>
                                    <#if order.statusId==1>
                                        <p>待确认</p>
                                    <#elseif order.statusId==2>
                                        <p>待付款</p>
                                    <#elseif order.statusId==3>
                                        <p>待付尾款</p>
                                    <#elseif order.statusId==4>
                                        <p>待服务</p>                                        
                                    <#elseif order.statusId==5>
                                        <p>待评价</p>
                                    <#elseif order.statusId==6>
                                        <p>已完成</p>
                                    <#elseif order.statusId==7>
                                        <p>已取消</p>
                                        <#--
                                        <a href="/order/delete/${order.id}">删除</a>
                                        -->
                                    </#if>
                                </p>
                              </td>                             
                            </tr>
                        </#list>
                    </#if>
                </table>
                <div class="myclear" style="height:10px;"></div>
                <div class="mymember_page">
                    <#if order_page??>
                    <#assign continueEnter=false>
                    <#if order_page.totalPages gt 0>
                        <#list 1..order_page.totalPages as page>
                            <#if page <= 3 || (order_page.totalPages-page) < 3 || (order_page.number+1-page)?abs<3 >
                                <#if page == order_page.number+1>
                                    <a class="mysel" href="javascript:;">${page}</a>
                                <#else>
                                    <a href="/diysite/order/rebateincome?page=${page-1}&timeId=${time_id}&keywords=${keywords!''}">${page}</a>
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
    
    
        </div><!--mymember_center END-->
        
    </form>
    <div class="myclear"></div>
    </div><!--mymember_main END-->
    <div class="myclear"></div>
</div>
<!--mymember END-->
<#include "/client/common_footer.ftl" />

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
