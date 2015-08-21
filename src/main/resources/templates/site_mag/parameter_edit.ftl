<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑参数</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type="text/javascript">
$(function () {
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

    $("#txtImgUrl").blur(function () {
        var txtPic = $("#txtImgUrl").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show").hide();
        }
        else {
            $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show").show();
        }
    });
    
    //设置封面图片的样式
    $(".photo-list ul li .img-box img").each(function () {
        if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
            $(this).parent().addClass("selected");
        }
    });
    
    // 选择类型后修改ajaxurl
    $("#paramCatId").change(function(){
        var url = "/Verwalter/parameter/check?categoryId=" + $(this).val() + "<#if parameter??>&id=${parameter.id?c}</#if>";
        $("#idParamTitle").attr("ajaxurl", url);
    });
});
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/parameter/save" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" />
</div>
<input name="id" type="text" value='<#if parameter??>${parameter.id?c!""}</#if>' style="display:none">
<!--导航栏-->
<div class="location">
    <a href="/Verwalter/parameter/list" class="back"><i></i><span>
        返回列表页</span></a> 
    <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
    <i class="arrow"></i>
    <span>内容管理</span>
    <i class="arrow"></i><span>编辑信息</span>
</div>
<div class="line10">
</div>
<!--/导航栏-->

    <!--内容-->
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab">
            <div class="content-tab-ul-wrap">
                <ul>
                    <li><a href="javascript:;" onclick="tabs(this);" class="selected">基本信息</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">SEO选项</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="tab-content" style="display: block;">
        <dl>
            <dt>所属类别</dt>
            <dd>
                <div class="rule-single-select">
                    <select id="paramCatId" name="categoryId" id="ddlCategoryId" datatype="*" sucmsg=" " nullmsg="请选择！" class="Validform_error" style="display: none;">
                    	<#if parameter??>
                    	<#else>
                    	<option value="">请选择类别...</option>
                    	</#if>
                        <#if category_list??>
                            <#list category_list as c>
                                <option value="${c.id?c!""}" <#if parameter?? && parameter.categoryId==c.id>selected="selected"</#if>><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
                <#if parameter??>
                    <span class="Validform_checktip Validform_right"> </span>
                <#else>
                    <span class="Validform_checktip Validform_wrong">请选择！</span>
                </#if>
            </dd>
        </dl>
        <dl>
            <dt>参数名称</dt>
            <dd>
                <input id="idParamTitle" name="title" type="text" ajaxurl="/Verwalter/parameter/check<#if parameter??>?id=${parameter.id?c!''}&categoryId=${parameter.categoryId!''}</#if>" value="<#if parameter??>${parameter.title!''}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
                <span class="Validform_checktip">*名称最多100个字符</span>
            </dd>
        </dl>
        <dl>
            <dt>参数值输入类型</dt>
            <dd>
                <div class="rule-multi-radio">
                    <span style="display: none;">
                        <input type="radio" name="inputType" value="0" <#if parameter??==false || parameter.inputType==0>checked="checked"</#if>><label>手动输入</label>
                        <input type="radio" name="inputType" value="1" <#if parameter?? && parameter.inputType==1>checked="checked"</#if>><label>用户选择</label>
                    </span>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>多值参数</dt>
            <dd>
                <div class="rule-multi-radio">
                    <span style="display: none;">
                        <input type="radio" name="isMultiple" value="0" <#if parameter??==false || parameter.isMultiple??==false || parameter.isMultiple==false>checked="checked"</#if>><label>否</label>
                        <input type="radio" name="isMultiple" value="1" <#if parameter?? && parameter.isMultiple?? && parameter.isMultiple==true>checked="checked"</#if>><label>是</label>
                    </span>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>前台可检索</dt>
            <dd>
                <div class="rule-multi-radio">
                    <span style="display: none;">
                        <input type="radio" name="isSearchable" value="0" <#if parameter??==false || parameter.isSearchable??==false || parameter.isSearchable==false>checked="checked"</#if>><label>否</label>
                        <input type="radio" name="isSearchable" value="1" <#if parameter?? && parameter.isSearchable?? && parameter.isSearchable==true>checked="checked"</#if>><label>是</label>
                    </span>
                </div>
                <span class="Validform_checktip">*仅在参数值输入类型为“用户选择”时生效</span>
            </dd>
        </dl>
        <dl>
            <dt>排序数字</dt>
            <dd>
                <input name="sortId" type="text" value="<#if parameter??>${parameter.sortId!""}<#else>99</#if>" class="input txt100" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">*数字，越小越向前</span>
            </dd>
        </dl>
        <dl>
            <dt>调用别名</dt>
            <dd>
                <input name="callIndex" type="text" id="txtCallIndex" value="<#if parameter??>${parameter.callIndex!""}</#if>" class="input normal" datatype="/^\s*$|^[a-zA-Z0-9\-\_]{2,50}$/" sucmsg=" ">
                <span class="Validform_checktip">*别名访问，非必填，不可重复</span>
            </dd>
        </dl>
        <dl>
            <dt>参数值列表</dt>
            <dd>
                <textarea name="valueList" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if parameter??>${parameter.valueList!""}</#if></textarea>
                <span class="Validform_checktip">以“,”逗号区分开</span>
            </dd>
        </dl>
    </div>
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>SEO标题</dt>
            <dd>
                <input name="seoTitle" type="text" maxlength="255" id="txtSeoTitle" value="<#if parameter??>${parameter.seoTitle!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO关健字</dt>
            <dd>
                <textarea name="seoKeywords" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if parameter??>${parameter.seoKeywords!""}</#if></textarea>
                <span class="Validform_checktip">以“,”逗号区分开，255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO描述</dt>
            <dd>
                <textarea name="seoDescription" rows="2" cols="20" id="txtSeoDescription" class="input" datatype="*0-255" sucmsg=" "><#if parameter??>${parameter.seoDescription!""}</#if></textarea>
                <span class="Validform_checktip">255个字符以内</span>
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
        <div class="clear">
        </div>
    </div>
    <!--/工具栏-->
    </form>
</body>
</html>