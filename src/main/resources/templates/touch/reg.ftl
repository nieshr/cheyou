<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-注册</#if></title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/touch/js/common.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var seed=60;    //60秒  
var t1=null; 
$(document).ready(function(){
  <!--  searchTextClear(".logintext01","手机号/邮箱","#999","#333");-->
   <!-- searchTextClear(".logintext02","输入验证码","#999","#333"); -->
    
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
<header class="comhead">
  <div class="main">
    <p>注册</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<section class="loginbox">
  <div class="main">
    <form id="form1" method="post" action="/touch/reg">
        <p style="color: #F00">${error!''}</p>
        <div class="logintext">
            <input class="logintext01" name="username" type="text" datatype="s6-20" placeholder="/用户名/手机号/邮箱"/>
        </div>
        <div class="logintext">
            <input class="logintext02" name="password" type="password" placeholder="请输入密码" datatype="s6-20"/>
        </div>
        <div class="logintext">
            <input class="logintext02" type="password" placeholder="请输入密码" recheck="password"/>
        </div>
        <div style="padding-top:2px;">
             <p><b style="color: #FF0000;">*</b> 手机验证 </p>
             <input id="mobileNumber" style="width:90%; padding-left:10%; margin: 10px auto 0; 
             background: #f5f5f5 url(/touch/images/login_z.png) no-repeat 3% center; background-size:15px;height: 35px;" 
             placeholder="请输入手机号" name="mobile" type="text" datatype="m"  ajaxurl="/reg/check/mobile"/>
        </div>
        <div style="padding-top:2px;">
             <p><b style="color: #FF0000;">*</b> 短信验证码</p>
             <input placeholder="验证码" style="width:30%;padding-left:10%; margin: 10px auto 0; background: #f5f5f5 url(/touch/images/login_z.png) no-repeat 3% center; background-size:15px;height: 35px;" type="text" name="smsCode" datatype="s4-4" />
             <input id="smsCodeBtn" onclick="javascript:;" readOnly="true" class="sub" style="text-align:center;width: 40%; border-radius: 3px; margin-left:45px; background: #1c2b38; color: #fff; line-height: 35px; height: 35px;" value="获取短信验证码" />
             <div class="clear h15"></div>
        </div>
        <div class="logintext logintext_y">
            <input type="text" class="logintext02" name="code" datatype="s4-4"/>
        </div>
        <a href="javascript:;" class="login_yzm"><img onclick="this.src = '/code?date='+Math.random();" src="/code" /></a>
        <div class="clear"></div>
        <input type="submit" class="loginbtn" value="注册" />
        <p class="login_a">
          <a href="javascript:;">注册即表示您同意<span class="red">《用户协议》</span></a>
        </p>
    </form>
  </div>
  
</section>
<div class="clear h15"></div>
<#--
<footer class="mainbox loginbot">
  <a href="javascript:;">100%正品保障</a>
  <a href="javascript:;">100%正品保障</a>
  <a href="javascript:;">100%正品保障</a>
</footer>
-->
</body>
</html>
