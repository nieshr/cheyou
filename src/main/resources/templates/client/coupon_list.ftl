<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>车有同盟</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<link rel="shortcut icon" href="/client/images/cheyou.ico" />

<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />

<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>



<script type="text/javascript">
$(document).ready(function(){
    menuDownList("top_phone","#top_phonelist",".a1","sel");
    phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
    navDownList("navdown","li",".nav_showbox");
    menuDownList("mainnavdown","#navdown",".a2","sel");
    checkNowHover("shopping_down","shopping_sel");
    
    // 优惠券切换店铺
    $(".couponSel").change(function(){
        $(this).siblings(".mt5").eq(0).children(".red").eq(0).html($(this).find("option:selected").attr("tn"));
         if($(this).find("option:selected").attr("tn")!=0)
        {
           $("#tomorrow").text(" ");
        }else{
           $("#tomorrow").text("明天再来");
        }
    });
    
    //初始化表单验证
    $("#form1").Validform({
        tiptype: 3,
        ajaxPost:true,
        callback: function(data) {
            if (data.code==0)
            {
                alert("领取优惠券成功");
                window.location.reload();
            }
            else
            {
                alert(data.message);
            }
        }
    });
});

function showDialog(id)
{
    var cid = $(".mianfeilingqu").eq(id).find(".couponSel").eq(0).find("option:selected").attr("cid");
    
    if (null == cid)
    {
        alert("请选择同盟店");
        return;
    }
    
    $("#couponId").val(cid);
    
    $("#checkDialog").css("display", "block");
}

function hideDialog()
{
    $("#checkDialog").css("display", "none");
}
</script>
</head>
<body>
<div id="checkDialog" class="mianfeilingqutanchu" style="display: none;position: fixed; top: 0px; left: 0px;">
    <div class="mianfeilingqutanchu_dl">
        <section class="loginbox">
            <a class="fr" style="z-index:999999999; /* margin-top:50px; */ margin-left: 410px;position: absolute;" href="javascript:hideDialog();"><img src="/client/images/20150407114113116_easyicon_net_71.8756476684.png" width="25" height="25"></a>
            <form id="form1" action="/coupon/request" method="post">
                <span class="Validform_checktip">请填写领取信息</span>
                <input id="couponId" name="couponId" class="text" type="hidden" value="" datatype="n"/>
                <p><b style="color: #FF0000;">*</b> 请输入姓名</p>
                <input class="text" name="username" value="<#if user??>${user.realName!''}</#if>" type="text" datatype="*"  nullmsg="请输入姓名"/>
                <p>请输入车牌</p>
                <input class="text" name="carCode" value="<#if user??>${user.carCode!''}</#if>" type="text" />
                <p><b style="color: #FF0000;">*</b> 请输入手机</p>
                <input class="text" name="mobile" value="<#if user??>${user.mobile!''}</#if>" type="text" datatype="m" nullmsg="请输入手机号" errormsg="请输入正确的手机号"/>
                <p><b style="color: #FF0000;">*</b> 请输入验证码</p>
                <div class="clear"></div>
                <input class="text fl" name="code" type="text" style="width:35%;" datatype="*4-4" nullmsg="请输入验证码" errormsg="验证码为4位字符"/>
                <img src="/code" onclick="this.src = '/code?date='+Math.random();" id="yzm" height="37"/>
          
                <div class="clear h15"></div>
                <p class="pb10">
                    <span class="absolute-r"><a href="/reg">免费注册</a> | <a href="/login">登录</a></span>
                </p>
                <div class="clear h40"></div>
                <input type="submit" class="sub" value="免费领取" />
                <div class="clear h20"></div>
            </form>
        </section>
    </div>
</div>
<#include "/client/common_header.ftl" />

<div class="main">
    
    <#if coupon_type_list??>
        <#list coupon_type_list as item>
            <#if item_index < 2>
            <div class="mianfeilingqu">
                <div class="mianfeilingqu_left">
                    <img src="${item.picUri!''}" width="874" height="350">
                    <div class="mendian">
                        <span>同盟店:</span>
                        <select class="couponSel">
                            <#assign couponCount=0>
                            <#if ("coupon_" + item.id + "_list")?eval?? >
                                <#list ("coupon_" + item.id + "_list")?eval as coupon>
                                    <option value="${coupon.diySiteId!''}" cid="${coupon.id?c}" tn="${coupon.leftNumber?c!'0'}">${coupon.diySiteTitle!''}</option>
                                    <#assign couponCount=couponCount+coupon.leftNumber>
                                </#list>
                            </#if>
                        </select>
                        <div class="clear"></div>
                        <p class="mt5">剩余：<span class="red"><#if ("coupon_" + item.id + "_list")?eval[0]??>${("coupon_" + item.id + "_list")?eval[0].leftNumber?c!'0'}</#if></span>张  <#if ("coupon_" + item.id + "_list")?eval[0].leftNumber==0><span id="tomorrow">明天再来</span></#if></p>
                    </div>
                    <div class="clear"></div>
                    <div class="zongshushengyu">
                        <p class="mt5">总数剩余：<span class="red">${couponCount?c!'0'}</span>张</p>
                    </div>
                    <div class="clear"></div>
                    <div class="linquanniu">
                        <a href="javascript:showDialog(${item_index});">点击领取</a> 
                    </div>
                </div>
                <div class="mianfeilingqu_right">
                    <h3>领取车友</h3>
                    <ul>
                        <#if ("distributed_coupon_" + item.id + "_list")?eval?? >
                            <#list ("distributed_coupon_" + item.id + "_list")?eval as distributedCoupon>
                                <#if distributedCoupon_index < 10>
                                <li><span class="red"><#if distributedCoupon.mobile??>${distributedCoupon.mobile?substring(0, 7)}****</#if></span><span class="fr"><#if distributedCoupon.getTime??>${distributedCoupon.getTime?string("yyyy.MM.dd")}</#if></span></li>
                                </#if>
                            </#list>
                        </#if> 
                    </ul>
                </div>
            </div>
            </#if>
        </#list>
    </#if>
    
    <div class="clear"></div>
    <div class="mianfeilingqu2">
        <div class="mianfeilingqu2_left">
        <table style="width:882px; float:left;">
            <tbody>
                <tr>
                    <td>
                         <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                    <td>
                        <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                    <td>
                         <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                    <td>
                        <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                    <td>
                        <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                    </tr>
                <tr>
                    <td>
                        <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                    <td>
                        <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                    <td>
                        <a href="#"><img src="/client/images/youhuiquan.png" /></a>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="mianfeilingqu_right">
        <h3>领取车友</h3>
        <ul>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        <li><span class="red">135****2922</span><span class="fr">2015.03.21</span></li>
        </ul>
        
        </div>
        </div>  
  
  
  
  
  
  <div class="clear"></div> 
</div>
    
</div>

<div class="clear h50"></div>

<#include "/client/common_footer.ftl" />
</body>
</html>
