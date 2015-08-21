<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统设置</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type="text/javascript">
$(function () {
    <#if status??>
        alert("修改系统配置成功");
    </#if>

    //初始化表单验证
    $("#form1").initValidform();
    
    //初始化编辑器
    var editor = KindEditor.create('.editor', {
        width: '98%',
        height: '350px',
        resizeType: 1,
        uploadJson: '/Verwalter/editor/upload?action=EditorFile',
        fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
        allowFileManager: true
    });
    
    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });
    
    //（缩略图）
    var txtPic = $("#txtImgUrl").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show").hide();
    }
    else {
        $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show").show();
    }
    
    var wxPic = $("#wxImgUrl").val();
    if (wxPic == "" || wxPic == null) {
        $(".thumb_wxImgUrl_show").hide();
    }
    else {
        $(".thumb_wxImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + wxPic + "' bigsrc='" + wxPic + "' /></div></li></ul>");
        $(".thumb_wxImgUrl_show").show();
    }
    
    var iOsPic = $("#iOsImgUrl").val();
    if (iOsPic == "" || iOsPic == null) {
        $(".thumb_iOsImgUrl_show").hide();
    }
    else {
        $(".thumb_iOsImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + iOsPic + "' bigsrc='" + iOsPic + "' /></div></li></ul>");
        $(".thumb_iOsImgUrl_show").show();
    }
    
    var androidPic = $("#androidImgUrl").val();
    if (androidPic == "" || androidPic == null) {
        $(".thumb_androidImgUrl_show").hide();
    }
    else {
        $(".thumb_androidImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + androidPic + "' bigsrc='" + androidPic + "' /></div></li></ul>");
        $(".thumb_androidImgUrl_show").show();
    }
    
    var logo1Pic = $("#logo1ImgUrl").val();
    if (logo1Pic == "" || logo1Pic == null) {
        $(".thumb_Logo1ImgUrl_show").hide();
    }
    else {
        $(".thumb_Logo1ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + logo1Pic + "' bigsrc='" + logo1Pic + "' /></div></li></ul>");
        $(".thumb_Logo1ImgUrl_show").show();
    }

    var logo2Pic = $("#logo2ImgUrl").val();
    if (logo2Pic == "" || logo2Pic == null) {
        $(".thumb_Logo2ImgUrl_show").hide();
    }
    else {
        $(".thumb_Logo2ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + logo2Pic + "' bigsrc='" + logo2Pic + "' /></div></li></ul>");
        $(".thumb_Logo2ImgUrl_show").show();
    }
    
    var logo3Pic = $("#logo3ImgUrl").val();
    if (logo3Pic == "" || logo3Pic == null) {
        $(".thumb_Logo3ImgUrl_show").hide();
    }
    else {
        $(".thumb_Logo3ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + logo3Pic + "' bigsrc='" + logo3Pic + "' /></div></li></ul>");
        $(".thumb_Logo3ImgUrl_show").show();
    }
    
    <#--
    $("#logo1ImgUrl").blur(function () {
        var logo1Pic = $("#logo1ImgUrl").val();
        if (logo1Pic == "" || logo1Pic == null) {
            $("#thumb_logo1ImgUrl_show").hide();
        }
        else {
            $("#thumb_logo1ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + logo1Pic + "' bigsrc='" + logo1Pic + "' /></div></li></ul>");
            $("#thumb_logo1ImgUrl_show").show();
        }
        
    });
    -->
});
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/setting/save" id="form1">
    <div>
    <input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
    <input type="hidden" name="id" value="<#if setting??>${setting.id?c!""}</#if>" >
    </div>
    <!--导航栏-->
    <div class="location" style="position: static; top: 0px;">
      <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>系统管理</span>
      <i class="arrow"></i>
      <span>系统设置</span>
    </div>
    <div class="line10"></div>
    <!--导航栏-->  
    <!--内容-->
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab">
            <div class="content-tab-ul-wrap" >
                <ul>
                    <li><a href="javascript:;" onclick="tabs(this);" class="selected">网站基本信息</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);">功能权限设置</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);">网站奖励设置</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);">注册用户协议</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="tab-content" style="display: block;">
        <dl>
            <dt>网站名称</dt>
            <dd>
                <input name="title" type="text" value="<#if setting??>${setting.title!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>网站域名</dt>
            <dd>
                <input name="domainName" type="text" value="<#if setting??>${setting.domainName!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>网站LOGO</dt>
            <dd>
                <input id="txtImgUrl" name="logoUri" type="text" datatype="*0-255" value="<#if setting??>${setting.logoUri!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <span class="Validform_checktip"></span>
                <div class="photo-list thumb_ImgUrl_show">
                </div>
            </dd>
        </dl>
        <dl>
            <dt>微信二维码</dt>
            <dd>
                <input id="wxImgUrl" name="wxQrCode" type="text" datatype="*0-255" value="<#if setting??>${setting.wxQrCode!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <span class="Validform_checktip"></span>
                <div class="photo-list thumb_wxImgUrl_show"></div>
            </dd>
        </dl>
        <dl>
            <dt>微博二维码</dt>
            <dd>
                <input id="iOsImgUrl" name="weiboQrCode" type="text" datatype="*0-255" value="<#if setting??>${setting.weiboQrCode!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <span class="Validform_checktip"></span>
                <div class="photo-list thumb_iOsImgUrl_show"></div>
            </dd>
        </dl>
        <#--
        <dl>
            <dt>安卓App二维码</dt>
            <dd>
                <input id="androidImgUrl" name="androidQrCode" type="text" datatype="*0-255" value="<#if setting??>${setting.androidQrCode!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <span class="Validform_checktip"></span>
                <div class="photo-list thumb_androidImgUrl_show"></div>
            </dd>
        </dl>
        -->
        <dl>
            <dt>公司名称</dt>
            <dd>
                <input name="company" type="text" value="<#if setting??>${setting.company!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>通讯地址</dt>
            <dd>
                <input name="address" type="text" value="<#if setting??>${setting.address!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>联系电话</dt>
            <dd>
                <input name="telephone" type="text" value="<#if setting??>${setting.telephone!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>传真号码</dt>
            <dd>
                <input name="fax" type="text" value="<#if setting??>${setting.fax!""}</#if>" class="input normal" datatype="n0-100|-" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>一号客服QQ</dt>
            <dd>
                <input name="qq1" type="text" value="<#if setting??>${setting.qq1!""}</#if>" class="input normal" datatype="n0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
         <dl>
            <dt>二号客服QQ</dt>
            <dd>
                <input name="qq2" type="text" value="<#if setting??>${setting.qq2!""}</#if>" class="input normal" datatype="n0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
         <dl>
            <dt>三号客服QQ</dt>
            <dd>
                <input name="qq3" type="text" value="<#if setting??>${setting.qq3!""}</#if>" class="input normal" datatype="n0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
         <dl>
            <dt>四号客服QQ</dt>
            <dd>
                <input name="qq4" type="text" value="<#if setting??>${setting.qq4!""}</#if>" class="input normal" datatype="n0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        
        <dl>
            <dt>管理员邮箱</dt>
            <dd>
                <input name="adminEmail" type="text" value="<#if setting??>${setting.adminEmail!""}</#if>" class="input normal" datatype="n0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>网站备案号</dt>
            <dd>
                <input name="icpNumber" type="text" value="<#if setting??>${setting.icpNumber!""}</#if>" class="input normal" datatype="n0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>首页标题(SEO)</dt>
            <dd>
                <textarea name="seoTitle" rows="2" cols="20" class="input" datatype="*1-255" sucmsg=" "><#if setting??>${setting.seoTitle!""}</#if></textarea>
                <span class="Validform_checktip">*自定义的首页标题</span>
            </dd>
        </dl>
        <dl>
            <dt>页面关键词(SEO)</dt>
            <dd>
                <textarea name="seoKeywords" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if setting??>${setting.seoKeywords!""}</#if></textarea>
                <span class="Validform_checktip">*自定义的首页标题</span>
            </dd>
        </dl>
        <dl>
            <dt>页面描述(SEO)</dt>
            <dd>
                <textarea name="seoDescription" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if setting??>${setting.seoDescription!""}</#if></textarea>
                <span class="Validform_checktip">*自定义的首页标题</span>
            </dd>
        </dl>
        <dl>
            <dt>网站版权信息</dt>
            <dd>
                <textarea name="copyright" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if setting??>${setting.copyright!""}</#if></textarea>
                <span class="Validform_checktip">支持HTML</span>
            </dd>
        </dl>
        <!--add 2015-7-28 17:46:12 mdj-->
        <dl>
            <dt>底部图标1</dt>
            <dd>
                <input id="logo1ImgUrl" name="bottomLogoUri1" type="text" datatype="*0-255" value="<#if setting??>${setting.bottomLogoUri1!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <span class="Validform_checktip"></span>
                <div class="photo-list thumb_Logo1ImgUrl_show"></div>
            </dd>
        </dl>
        <dl>
            <dt>图标1跳转地址</dt>
            <dd>
                <input name="bottomLogoLink1" type="text" value="<#if setting??>${setting.bottomLogoLink1!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>底部图标2</dt>
            <dd>
                <input id="logo2ImgUrl" name="bottomLogoUri2" type="text" datatype="*0-255" value="<#if setting??>${setting.bottomLogoUri2!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <span class="Validform_checktip"></span>
                <div class="photo-list thumb_Logo2ImgUrl_show"></div>
            </dd>
        </dl>
        <dl>
            <dt>图标2跳转地址</dt>
            <dd>
                <input name="bottomLogoLink2" type="text" value="<#if setting??>${setting.bottomLogoLink2!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>底部图标3</dt>
            <dd>
                <input id="logo3ImgUrl" name="bottomLogoUri3" type="text" datatype="*0-255" value="<#if setting??>${setting.bottomLogoUri3!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <span class="Validform_checktip"></span>
                <div class="photo-list thumb_Logo3ImgUrl_show"></div>
            </dd>
        </dl>
        <dl>
            <dt>图标3跳转地址</dt>
            <dd>
                <input name="bottomLogoLink3" type="text" value="<#if setting??>${setting.bottomLogoLink3!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <!--add 2015-7-28 17:46:12 mdj-->
    </div>
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>开启触屏网站</dt>
            <dd>
                <div class="rule-multi-radio">
                    <span>
                        <input type="radio" name="isTouchEnable" value="1" <#if setting?? && setting.isTouchEnable?? && setting.isTouchEnable==true>checked="checked"</#if> >
                        <label>是</label>
                        <input type="radio" name="isTouchEnable" value="0" <#if setting??==false || setting.isTouchEnable??==false || setting.isTouchEnable==false>checked="checked"</#if>>
                        <label>否</label>
                    </span>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>触屏网站域名</dt>
            <dd>
                <input name="touchUri" type="text" value="<#if setting??>${setting.touchUri!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>开启评论审核</dt>
            <dd>
                <div class="rule-multi-radio">
                    <span>
                        <input type="radio" name="isCommentVerify" value="1" <#if setting?? && setting.isCommentVerify?? && setting.isCommentVerify>checked="checked"</#if> >
                        <label>是</label>
                        <input type="radio" name="isCommentVerify" value="0" <#if setting??==false || setting.isCommentVerify??==false || !setting.isCommentVerify>checked="checked"</#if>>
                        <label>否</label>
                    </span>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>开启管理员日志</dt>
            <dd>
                <div class="rule-multi-radio">
                    <span>
                        <input type="radio" name="isEnableLog" value="1" <#if setting?? && setting.isEnableLog?? && setting.isEnableLog>checked="checked"</#if> >
                        <label>是</label>
                        <input type="radio" name="isEnableLog" value="0" <#if setting??==false || setting.isEnableLog??==false || !setting.isEnableLog>checked="checked"</#if>>
                        <label>否</label>
                    </span>
                </div>
            </dd>
        </dl>
    </div> 
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>注册成功奖励粮草</dt>
            <dd>
                <input name="registerSuccessPoints" type="text" value="<#if setting??>${setting.registerSuccessPoints!"50"}<#else>50</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>分享注册奖励粮草</dt>
            <dd>
                <input name="registerSharePoints" type="text" value="<#if setting??>${setting.registerSharePoints!"20"}<#else>20</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>分享商品奖励粮草</dt>
            <dd>
                <input name="goodsSharePoints" type="text" value="<#if setting??>${setting.goodsSharePoints!"10"}<#else>10</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>商品分享每日粮草限额</dt>
            <dd>
                <input name="goodsShareLimits" type="text" value="<#if setting??>${setting.goodsShareLimits!"50"}<#else>50</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">同一天通过商品分享获得的粮草超过该值将不再奖励粮草</span>
            </dd>
        </dl>
    </div>    
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>用户注册协议</dt>
            <dd>
                <textarea name="registerNego" class="editor"><#if setting??>${setting.registerNego!""}</#if></textarea>
            </dd>
        </dl>
    </div>  
    <!--/内容-->
    <!--工具栏-->
    <div class="page-footer">
        <div class="btn-list">
            <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
            <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
        </div>
        <div class="clear"></div>
    </div>
    <!--/工具栏-->
</form>
</body>
</html>