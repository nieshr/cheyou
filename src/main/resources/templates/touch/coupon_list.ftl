<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>车有同盟</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/common.js"></script>
<script src="/touch/js/Validform_v5.3.2_min.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
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
    $("#Validform_msg").hide();
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
            <a class="fr" style="z-index:999999999; /* margin-top:50px; position: absolute;*/  margin-right: 10px;" href="javascript:hideDialog();"><img src="/client/images/20150407114113116_easyicon_net_71.8756476684.png" width="25" height="25"></a>
            <form id="form1" action="/touch/coupon/request" method="post">
                <span class="Validform_checktip">请填写领取信息</span>
                <input id="couponId" name="couponId" class="text" type="hidden" value="" datatype="n"/>
                <p><b style="color: #FF0000;">*</b> 请输入姓名</p>
                <input class="text" name="username" type="text" datatype="*"  nullmsg="请输入姓名"/>
                <p>请输入车牌</p>
                <input class="text" name="carCode" type="text" />
                <p><b style="color: #FF0000;">*</b> 请输入手机</p>
                <input class="text" name="mobile" type="text" datatype="m" nullmsg="请输入手机号" errormsg="请输入正确的手机号"/>
                <p><b style="color: #FF0000;">*</b> 请输入验证码</p>
                <div class="clear"></div>
                <input class="text fl" name="code" type="text" style="width:35%;" datatype="*4-4" nullmsg="请输入验证码" errormsg="验证码为4位字符"/>
                <img src="/code" onclick="this.src = '/code?date='+Math.random();" id="yzm" height="37"/>
          
                <div class="clear h15"></div>
                <p class="pb10">
                    <span class="absolute-r" style="padding-bottom:10px;">
                          <#if username??>
                          <#else>
                          <a href="/touch/login">登录</a><a class="ml20" href="/touch/reg">注册</a>
                          </#if>
                    </span>
                </p>
                <div class="clear h40"></div>
                <input type="submit" class="sub" value="免费领取" />
                <div class="clear h20"></div>
            </form>
        </section>
    </div>
</div>

<header class="comhead">
  <div class="main">
    <p>免费领取</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->

<menu class="mainbox freelist">
    <#if coupon_type_list??>
        <#list coupon_type_list as item>
            <div class="mianfeilingqu">
                <div class="mianfeilingqu_left">
                    <img src="${item.picUri!''}" class="couponimg">
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
                        <p class="mt5">剩余：<span class="red"><#if ("coupon_" + item.id + "_list")?eval[0]??>${("coupon_" + item.id + "_list")?eval[0].leftNumber?c!'0'}</#if></span>张    <#if ("coupon_" + item.id + "_list")?eval[0].leftNumber==0><span id="tomorrow">明天再来</span></#if></p>
                        
                    </div>
                    <div class="clear"></div>
                    <div class="zongshushengyu">
                        <p class="mt5" style=" line-height: 30px;width: 50%; display: inline-block;">总数剩余：<span class="red">${couponCount?c!'0'}</span>张</p>
                    </div>
                    <div class="clear"></div>
                    <div class="linquanniu">
                        <a href="javascript:showDialog(${item_index});">点击领取</a> 
                    </div>
                </div>
                <div class="mianfeilingqu_right" style=" border-bottom: solid 2px #333; margin-bottom: 20px;">
                
                    <h3 style="font-size: 1em;">领取车友</h3>
                    <ul>
                        <#if ("distributed_coupon_" + item.id + "_list")?eval?? >
                            <#list ("distributed_coupon_" + item.id + "_list")?eval as distributedCoupon>
                                <#if distributedCoupon_index lt 10>
                                <li><span class="red"><#if distributedCoupon.mobile??>${distributedCoupon.mobile?substring(0, 7)}****</#if></span><span class="fr"><#if distributedCoupon.getTime??>${distributedCoupon.getTime?string("yyyy.MM.dd")}</#if></span></li>
                                </#if>
                            </#list>
                       <#else>
                            <li><span class="red">暂无人领取</span></li>
                       </#if> 
                    </ul>
                
                </div>
            </div>
        </#list>
    <#else>
        <a href="/touch">逛逛其他的</a>
    </#if>
</div>
</menu>

<div class="clear h40"></div>
<section class="botlogin">
  <#if username??>
  <a href="/touch/user">${username}</a>
  <a class="ml20" href="/touch/logout">退出</a>
  <#else>
  <a href="/touch/login">登录</a><a class="ml20" href="/touch/reg">注册</a>
  </#if>
  <a class="a1" href="javascript:$('html,body').animate({scrollTop:0},500);">TOP</a>
</section>
<div class="clear"></div>
<footer class="comfoot main">
    <a href="/coupon/list">电脑版</a>
    <a href="/touch/coupon/list">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<!--没有任何代码-->
</body>
</html>
