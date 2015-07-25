<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>

<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var seed=60;    //60秒  
var t1=null; 

$(document).ready(function(){
    //初始化表单验证
    $("#form1").Validform({
        tiptype: 3
    });
     
    $("#smsCodeBtn").bind("click", function() {  
        
        var mob = $('#mobileNumber').val();
        
        var re = /^1\d{10}$/;
        
        if (!re.test(mob)) {
            alert("请输入正确的手机号");
            return;
        }
        
        $("#smsCodeBtn").attr("disabled","disabled"); 
        
        $.ajax({  
            url : "/reg/smscode",  
            async : true,  
            type : 'GET',  
            data : {"mobile": mob},  
            success : function(data) {  
                
                if(data.statusCode == '000000')
                {  
                    t1 = setInterval(tip, 1000);  
                }
                else
                {
                    $("#smsCodeBtn").removeAttr("disabled");
                }
            },  
            error : function(XMLHttpRequest, textStatus,  
                    errorThrown) {  
                alert("error");
            }  
  
        });
        
    }); 

});

function enableBtn()
{  
    $("#smsCodeBtn").removeAttr("disabled");   
} 

function tip() 
{  
    seed--;  
    if (seed < 1) 
    {  
        enableBtn();  
        seed = 60;  
        $("#smsCodeBtn").val('点击获取短信验证码');  
        var t2 = clearInterval(t1);  
    } else {  
        $("#smsCodeBtn").val(seed + "秒后重新获取");  
    }  
} 
</script>
</head>

<body>
<header class="logintop">
  <div class="main pt20">
    <a class="fl" href="/"><img src="/client/images/liebiao_03.png" /></a>
    <p class="p3">售后保障</p>
    <p class="p2">100%品牌制造商</p>
    <p class="p1">100%正品保障</p>
    <div class="clear"></div>
  </div>
</header>
<div class="logingbg">
    <section class="loginbox">
        <span style="color: #F00"><#if errCode??>
            <#if errCode==1>
                验证码错误
            <#elseif errCode==4>
                短信验证码错误
            </#if>
        </#if></span>
        <form id="form1" method="post" action="/reg">
            <div>
                <p><b style="color: #FF0000;">*</b> 请输入用户名 </p>
                <input class="text" name="username" type="text" datatype="s6-20" ajaxurl="/reg/check/username" value = "${username!''}"/>
            </div>
            <div>
                <p>请输入车牌号码（选填）</p>
                <input class="text" name="carCode" type="text" value ="${carCode!''}"/>
            </div>
            <div>
                <p><b style="color: #FF0000;">*</b> 请输入密码</p>
                <input class="text" name="password" type="password" datatype="s6-20"/>
            </div>
            <div>
                <p><b style="color: #FF0000;">*</b> 请确认密码</p>
                <input class="text" type="password" datatype="*" recheck="password"/>
            </div>
            <div>
                <p><b style="color: #FF0000;">*</b> 手机验证 </p>
                <input id="mobileNumber" class="text" name="mobile" type="text" datatype="m"  ajaxurl="/reg/check/mobile"/>
            </div>
            <div>
                <p><b style="color: #FF0000;">*</b> 短信验证码</p>
                <input class="text fl" type="text" name="smsCode" style="width:35%;" />
                <input id="smsCodeBtn" onclick="javascript:;" readOnly="true" class="sub" style="text-align:center;width: 50%; border-radius: 3px; margin-left:55px; background: #1c2b38; color: #fff; line-height: 35px; height: 35px;" value="点击获取短信验证码" />
                <div class="clear h15"></div>
            </div>
            <div>
                <p><b style="color: #FF0000;">*</b> 验证码</p>
                <div class="clear"></div>
                <input class="text fl" name="code" type="text" style="width:35%;" datatype="s4-4" errormsg="请填写4位字符"/>
                <img src="/code" onclick="this.src = '/code?date='+Math.random();" id="yzm" height="37" style="padding-left: 55px;"/>
            </div>
            <div class="clear h15"></div>
            
            <input type="submit" class="sub" value="注册" />
        </form>
        <div class="clear h15"></div>
    </section>
</div><!--logingbg END-->

<footer class="loginfoot">
    <nav>
        <#if help_level0_cat_list??>
            <#list help_level0_cat_list as item>
                <a href="/info/list/${item.id!''}">${item.title!''}</a>
            </#list>
        </#if>
    </nav>
    <p>友情链接：
        <#if site_link_list??>
            <#list site_link_list as item>
                <a href="${item.linkUri!''}">${item.title!''}</a> |
            </#list>
        </#if>
    </p>
    <p>${site.copyright!''}</p>
    <p>${site.address!''} 电话：${site.telephone!''} </p>
    <p><a title="云南网站建设" href="http://www.ynyes.com" target="_blank">网站建设</a>技术支持：<a title="云南网站建设" href="http://www.ynyes.com" target="_blank">昆明天度网络公司</a></p>
</footer>
</body>
</html>
