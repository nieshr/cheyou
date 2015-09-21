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
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/mymember.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/jquery.cityselect.js"></script>

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
    
    $("#address").citySelect({
        nodata:"none",
        <#if address?? && address.province??>prov: "${address.province!''}",</#if>
        <#if address?? && address.city??>city: "${address.city!''}",</#if>
        <#if address?? && address.disctrict??>dist: "${address.disctrict!''}",</#if>
        required:false
    }); 
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
    <!--mymember_menu END-->
    
    <div class="mymember_mainbox">
      <div class="mymember_info mymember_info02">
        <h3>安装信息</h3>
        
		
		<div class="haoh pt15 geren_rig">
        <table class="center_tab">
          <tbody>
          <tr>
            <th align="center">姓名</th>
            
          <#--
            <th>地区</th>
            <th>邮政编码</th>
          -->
          
            <th>手机号码</th>
            
          <#--
            <th>详细地址</th>
          -->
          
            <th>车牌</th>
            <th>车型</th>
            <th>操作</th>
          </tr>
          
          <#if address_list??>
            <#list address_list as address>
                <tr>
                    <td>${address.receiverName!''}</td>
                    
                  <#--  <td>${address.province!''}${address.city!''}${address.disctrict!''}</td>
                    <td>${address.postcode!''}</td>  
                  -->
                  
                    <td>${address.receiverMobile!''}</td>
                    
                  <#--
                    <td>${address.detailAddress!''}</td>
                  -->
                  
                    <td>${address.receiverCarcode!''}</td>          
                    <td>${address.receiverCartype!''}</td>
                    <td>
                      <#if address.isDefaultAddress?? && address.isDefaultAddress>
                        <p><a style="color:red;">默认地址</a></p>
                      <#else>
                        <p><a href="/user/address/default?id=${address.id?c}">设为默认</a></p>
                      </#if>
                      <p><a href="/user/address/update?id=${address.id?c}">修改</a></p>
                      <p><a href="/user/address/delete?id=${address.id?c}">删除</a></p>
                    </td>
                  </tr>
            </#list>
          </#if>
        </tbody></table>
        
      
    <div class="h20"></div>
    <#-- 新增一个添加提示 -->
    <h3>添加/修改</h3> 
    <form method="post" action="/user/address/save" id="form1">
    <table class="mymember_address">
      <input class="mytext" name="addressId" type="hidden" value="<#if address??>${address.id?c}</#if>">
      <tbody>
      <tr>
        <th align="center">姓名：</th>
        <td>
            <input class="mytext" name="receiverName" datatype="*2-128" errormsg="最少两个字符！" value="<#if address??>${address.receiverName}</#if>" type="text">
            <span class="Validform_checktip">*收货人姓名</span>
        </td>
      </tr>
      
    <#--  
      <tr>
        <th>地区：</th>
        <td>
          <div id="address">
          <select name="province" class="prov" style="width: 100px;" datatype="*"></select>
          <select name="city" class="city" style="width: 100px;" datatype="*"></select>
          <select name="disctrict" class="dist" style="width: 100px;" datatype="*0-10"></select>
          </div>
        </td>
      </tr>
      <tr>
        <th>详细地址：</th>
        <td>
            <input class="mytext" name="detailAddress" datatype="*2-128" errormsg="最少两个字符！" value="<#if address??>${address.detailAddress}</#if>" type="text">
            <span class="Validform_checktip">*详细地址</span>
        </td>
      </tr>
      <tr>
        <th>邮政编码：</th>
        <td>
            <input class="mytext" name="postcode" datatype="p" errormsg="邮政编码为6位数字！" value="<#if address??>${address.postcode}</#if>" type="text">
            <span class="Validform_checktip">*邮政编码</span>
        </td>
      </tr>
    -->
    
      <tr>
        <th>手机号码：</th>
        <td>
            <input class="mytext" name="receiverMobile" datatype="m" errormsg="请输入正确的电话号码格式！" value="<#if address??>${address.receiverMobile!''}</#if>" type="text">
            <span class="Validform_checktip">*手机号码</span>
        </td>
      </tr>
      <tr>
      <#-- 新增车牌填写项 -->
     <tr>
        <th>车牌：</th>
        <td>
            <input class="mytext" name="receiverCarcode" datatype="*" errormsg="请输入正确的车牌号码格式！" value="<#if address??>${address.receiverCarcode!''}</#if>" type="text">
            <span class="Validform_checktip">*车牌号码</span>
        </td>
      </tr>
      
      <#-- 新增车型填写项 -->
      <tr>
        <th>车型：</th>
        <td>
            <input class="mytext" name="receiverCaryype" datatype="*" errormsg="请输入正确的格式！" value="<#if address??>${address.receiverCartype!''}</#if>" type="text">
            <span class="Validform_checktip">*汽车型号</span>
        </td>
      </tr>   
      <tr>
      <tr>
        <th></th>
        <td><input class="mysub" type="submit" value="保存"></td>
      </tr>
    </tbody>
    </table>
    
    </form>
    </div>
	  
	  
	  
      </div>
      <!--mymember_info END-->
    
    </div>
    <!--mymember_center END-->   
    <div class="myclear"></div>
  </div>
  <!--mymember_main END-->
  <div class="myclear"></div>
</div>
<!--主体结束-->
<#include "/client/common_footer.ftl" />
</body>
</html>
<!--结束-->
