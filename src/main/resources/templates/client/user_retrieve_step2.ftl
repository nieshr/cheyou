<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/html5.js"></script>
<script src="/client/js/list.js"></script>

<link rel="shortcut icon" href="/client/images/cheyou.ico" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
  $(document).ready(function(){
    menuDownList("top_phone","#top_phonelist",".a1","sel");
    phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
    searchTextClear(".toptext","请输入品牌或商品名称","#999","#666");
    searchTextClear(".bottext","查看所有门店","#fff","#fff");
    checkNowHover("shopping_down","shopping_sel");
    navDownList("navdown","li",".nav_showbox");
    menuDownList("mainnavdown","#navdown",".a2","sel");
   floatBoxQQ();
});

<!-- 电话号码中间变为*号  2015-8-4 15:38:18 mdj  -->
$(document).ready(function(){
    var pElement = $("#mobile").toArray();
    for(var i = 0;i < pElement.length;i++)
    {
       var originName = pElement[i].innerHTML
       var name =  changeName(originName)
       pElement[i].innerText=name;
    }

});

 function changeName(p)
{
    var temp = p
    if(temp.length == 11)
    {
        var changeStr = temp.substring(3, 7)
        temp = temp.replace(changeStr,"****")
    }
    else
    {
        var startStr = ""
        var strLength = temp.length;
        for (var i = 0; i < strLength - 4; i++)
        {
            startStr += "*";
        }
        var changeStr = temp.substring(2, strLength - 2)

        temp = temp.replace(changeStr, startStr)
    }
    return temp;
}


var seed=60;    //60秒  
var t1=null; 

$(document).ready(function(){
    //初始化表单验证
    $("#form1").Validform({
        tiptype: 3
    });
     
    $("#smsCodeBtn").bind("click", function() {  
        
        var mob = <#if user??>${user.mobile!''}</#if>;
        
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

<#include "/client/common_header.ftl" />

<div class="main">
    <menu class="car_top">
        <p  style="z-index:10; width:34%;">填写账户名<i></i></p>
        <p class="sel" style="z-index:8;">验证身份<i></i></p>
        <p>设置新密码</p>
        <div class="clear"></div>
    </menu>  
      
    <section class="loginbox">
        <span style="color: #F00"><#if errCode??>
            <#if errCode==1>
                验证码错误
            <#elseif errCode==4>
                短信验证码错误
            </#if>
        </#if></span>
        <form id="form1" method="post" action="/login/retrieve_step2">
            <p>选择验证身份方式</p>
            <div class="clear h15"></div>
            <select><option>注册时填写的手机号</option></select>
            <div class="clear h15"></div>
            <p>用户名:&nbsp;&nbsp;<span class="red"><#if user??>${user.username}</#if></span></p>
            <div class="clear h15"></div>
            <p>手机号:&nbsp;&nbsp;<span id="mobile" class="red"><#if user??>${user.mobile!''}</#if></span></p>
            <div class="clear h15"></div>
            <p>请输入短信验证码</p>    
            <input class="text fl" type="text" name="smsCode" style="width:35%;" />
            <input id="smsCodeBtn" onclick="javascript:;" readOnly="true" class="sub" style="text-align:center;
            width: 50%; border-radius: 3px; margin-left:55px; background: #1c2b38; color: #fff; line-height: 35px; height: 35px;" value="点击获取短信验证码" />
            <div class="clear h15"></div>
            
            <input type="submit" class="sub" value="下一步" />
            <div class="clear h15"></div>
        </form>
      </section>
  
  <div class="clear"></div> 
</div><!--main END-->
<#include "/client/common_footer.ftl" />

</body>
</html>
