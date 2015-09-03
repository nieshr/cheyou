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

   
     //发送AJAX请求
        function sendAjaxUrl(winObj, postData, sendUrl, orderId) {
            $.ajax({
                type: "post",
                url: sendUrl,
                data: postData,
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.dialog.alert('尝试发送失败，错误信息：' + errorThrown, function () { }, winObj);
                },
                success: function (data) {
                    if (data.code == 0) {
                        winObj.close();
                        $.dialog.tips(data.msg, 2, '32X32/succ.png', function () { window.location.href="/diysite/order?id="+orderId; }); //刷新页面
                    } else {
                        $.dialog.alert('错误提示：' + data.message, function () { }, winObj);
                    }
                }
            });
        }    
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
  
    <form name="form1" action="/user/diysite/order/list/${status_id}" method="POST">
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
                    <a class="a001" href="/user/order/list/${status_id}">
                        <#if status_id==0>
                            全部订单
                        <#elseif status_id==1>
                            待确认订单
                        <#elseif status_id==2>
                            待付款订单
                        <#elseif status_id==3>
                            待付尾款订单
                        <#elseif status_id==4>
                            待服务订单
                        <#elseif status_id==5>
                            待评价订单
                        <#elseif status_id==6>
                            已完成订单
                        <#elseif status_id==7>
                            已取消订单
                        <#elseif status_id==9>
                            已删除订单
                        </#if>
                    </a>
                </div>
                <table>
                    <tr class="mymember_infotab_tit01">
                        <th>订单信息</th>
                        <th width="70">收货人</th>
                        <th width="80">订单金额</th>
                        <th width="80">
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
                        <th width="80"></th>
                        <th width="60">返利</th>
                    </tr>    
                    <#if order_page??>
                        <#list order_page.content as order>
                        <script>
                            $(function () {
                                $("#btnService${order.id?c}").click(function () { OrderService${order.id?c}(); });   //确认到店消费
                           });
                           
                            //确认消费
                                function OrderService${order.id?c}() {
                                    var dialog = $.dialog({
                                        title: '请输入消费密码',
                                        content: '<textarea id="pointRemark" name="txtPointRemark" rows="2" cols="15" class="input"></textarea>',
                                        min: false,
                                        max: false,
                                        lock: true,
                                        ok: function () {
                                            var password = $("#pointRemark", parent.document).val();                   
                                            if (password == "") {
                                                $.dialog.alert('对不起，请输入消费密码！', function () { }, dialog);
                                                return false;
                                            }
                                            var orderNumber = $.trim($("#spanOrderNumber${order.id?c}").text());
                                            var orderId = ${order.id?c!''};
                                            var postData = { "orderNumber": orderNumber, "password": password, "type":"orderService" };
                                            //发送AJAX请求
                                            sendAjaxUrl(dialog, postData, "/diysite/order/param/edit", orderId);
                                            return false;
                                        },
                                        cancel: true
                                    });
                                }
                        </script>
                            <tr>
                              <th colspan="7">订单编号：<a href="/diysite/order?id=${order.id?c}" id="spanOrderNumber${order.id?c}">${order.orderNumber!''}</a></th>
                            </tr>                           
                            <tr>
                              <td class="td001">
                                <#list order.orderGoodsList as og>
                                    <a href="/goods/${og.goodsId?c}"><img src="${og.goodsCoverImageUri}" title="${og.goodsTitle}"></a>
                                </#list>
                              </td>
                              <td>${order.shippingName!''}</td>
                              <td>
                                <p>￥${order.totalPrice?string("#.##")}</p>
                                <p>${order.payTypeTitle!''}</p>
                              </td>
                              <td class="td003">
                                ${order.orderTime!''}
                              </td>
                              <td>
                                <p>
                                    <#if order.statusId==1>
                                        待确认
                                    <#elseif order.statusId==2>
                                        <p>待付款</p>
                                        <#--
                                        <a href="/order/cancel/${order.id}">取消订单</a>
                                        -->
                            <!--            <a href="/order/dopay/${order.id}">立即支付</a> -->
                                    <#elseif order.statusId==3>
                                        <p>待付尾款</p>
                              <!--          <a href="/user/dopayleft/${order.id}">付尾款</a> -->
                                    <#elseif order.statusId==4>
                                        <p>待服务</p>
                                        <input type="button" id="btnService${order.id?c}" value="确认核销" class="btn green">
                                    <#elseif order.statusId==5>
                                        <p>待评价</p>
                                <!--        <a href="/user/comment/list">发表评论</a>  -->
                                    <#elseif order.statusId==6>
                                        已完成
                                    <#elseif order.statusId==7>
                                        <p>已取消</p>
                                        <#--
                                        <a href="/order/delete/${order.id}">删除</a>
                                        -->
                                    </#if>
                                </p>
                              </td>
                              <td class="td003"> 
                                   <#if order.rebate??>${order.rebate?string("0.00")}</#if>
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
                                    <a href="/user/diysite/order/list/${statusId}?page=${page-1}&timeId=${time_id}&keywords=${keywords!''}">${page}</a>
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
