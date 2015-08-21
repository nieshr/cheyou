<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑信息</title>
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
    $("#proCatId").change(function(){
        var url = "/Verwalter/product/check?catId=" + $(this).val() + "<#if product??>&id=${product.id?c}</#if>";
        $("#idProductTitle").attr("ajaxurl", url);
    });
    
    
    // 选择筛选项后需填写筛选项名称
    $("#totalSelects").change(function(){
        var countStr = $.trim($(this).val());
        
        if (isNaN(countStr) || countStr=="") { countStr = 0 }
        
        var count = parseInt(countStr);
        
        if (count < 1 || count > 3)
        {
            alert("支持的筛选项数量最多为3个");
            return;
        }
        
        if (1 == count)
        {
            $("#selectNameDiv").html('<dl><dt>筛选项一名称</dt><dd><input name="selectOneName" type="text" value="<#if product??>${product.selectOneName!''}</#if>" class="input txt100" datatype="*1-100" sucmsg=""></dd></dl>');
        }
        else if (2 == count)
        {
            $("#selectNameDiv").html('<dl><dt>筛选项一名称</dt><dd><input name="selectOneName" type="text" value="<#if product??>${product.selectOneName!''}</#if>" class="input txt100" datatype="*1-100" sucmsg=""></dd></dl>');
            $("#selectNameDiv").append('<dl><dt>筛选项二名称</dt><dd><input name="selectTwoName" type="text" value="<#if product??>${product.selectTwoName!''}</#if>" class="input txt100" datatype="*1-10" sucmsg=""></dd></dl>');
        }
        else if (3 == count)
        {
            $("#selectNameDiv").html('<dl><dt>筛选项一名称</dt><dd><input name="selectOneName" type="text" value="<#if product??>${product.selectOneName!''}</#if>" class="input txt100" datatype="*1-100" sucmsg=""></dd></dl>');
            $("#selectNameDiv").append('<dl><dt>筛选项二名称</dt><dd><input name="selectTwoName" type="text" value="<#if product??>${product.selectTwoName!''}</#if>" class="input txt100" datatype="*1-100" sucmsg=""></dd></dl>');
            $("#selectNameDiv").append('<dl><dt>筛选项三名称</dt><dd><input name="selectThreeName" type="text" value="<#if product??>${product.selectThreeName!''}</#if>" class="input txt100" datatype="*1-100" sucmsg=""></dd></dl>');
        }
    });
    
});
</script>
</head>
<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form method="post" action="/Verwalter/product/save" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" />
</div>
<input name="id" type="text" value='<#if product??>${product.id?c!""}</#if>' style="display:none">
    <!--导航栏-->
    <div class="location">
        <a href="/Verwalter/product/list" class="back"><i></i><span>
            返回列表页</span></a> 
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <span>编辑信息</span>
    </div>
    <div class="line10"></div>
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
                    <select id="proCatId" name="productCategoryId" datatype="*" sucmsg=" " nullmsg="请选择！" class="Validform_error" style="display: none;">
                    	<#if product??>
                    	<#else>
                    	<option value="">请选择类别...</option>
                    	</#if>
                        <#if category_list??>
                            <#list category_list as c>
                                <option value="${c.id?c!""}" <#if product?? && product.productCategoryId?? && product.productCategoryId==c.id>selected="selected"</#if>><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
                <#if product??>
                    <span class="Validform_checktip Validform_right"> </span>
                <#else>
                    <span class="Validform_checktip Validform_wrong">请选择！</span>
                </#if>
            </dd>
        </dl>
        <dl>
            <dt>产品名称</dt>
            <dd>
                <input id="idProductTitle" name="title" type="text" value="<#if product??>${product.title!""}</#if>" ajaxurl="/Verwalter/product/check<#if product??>?id=${product.id?c}</#if>" class="input normal" datatype="*1-1000" sucmsg=" ">
                <span class="Validform_checktip">*标题最多100个字符</span>
            </dd>
        </dl>
        <dl>
            <dt>货号</dt>
            <dd>
                <input name="productNumber" type="text" value="<#if product??>${product.productNumber!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>调用别名</dt>
            <dd>
                <input name="callIndex" type="text" value="<#if product??>${product.callIndex!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>排序数字</dt>
            <dd>
                <input name="sortId" type="text" value="<#if product??>${product.sortId!""}<#else>99</#if>" class="input txt100" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">*数字，越小越向前</span>
            </dd>
        </dl>
        <dl>
            <dt>筛选项数量</dt>
            <dd>
                <div class="rule-single-select">
                    <select id="totalSelects" name="totalSelects" datatype="n" sucmsg=" " nullmsg="请选择！" class="Validform_error">
                        <#if !product?? || !product.totalSelects??>
                        <option value="">请选择数量...</option>
                        </#if>
                        <option value="1" <#if product?? && product.totalSelects?? && product.totalSelects==1>selected="selected"</#if>>1</option>
                        <option value="2" <#if product?? && product.totalSelects?? && product.totalSelects==2>selected="selected"</#if>>2</option>
                        <option value="3" <#if product?? && product.totalSelects?? && product.totalSelects==3>selected="selected"</#if>>3</option>
                    </select>
                </div>
                <#if product?? && product.totalSelects??>
                    <span class="Validform_checktip Validform_right"></span>
                <#else>
                    <span class="Validform_checktip">*筛选项用于前台用户点击进行商品筛选</span>
                </#if>
            </dd>
        </dl>
        <div id="selectNameDiv">
            <#if product?? && product.totalSelects??>
                <#list 1..product.totalSelects as index>
                    <#if index==1>
                        <dl>
                            <dt>筛选项一名称</dt>
                            <dd><input name="selectOneName" type="text" value="${product.selectOneName!''}" class="input txt100" datatype="*1-100" sucmsg=""></dd>
                        </dl>
                    <#elseif index==2>
                        <dl>
                            <dt>筛选项二名称</dt>
                            <dd><input name="selectTwoName" type="text" value="${product.selectTwoName!''}" class="input txt100" datatype="*1-100" sucmsg=""></dd>
                        </dl>
                    <#elseif index==3>
                        <dl>
                            <dt>筛选项三名称</dt>
                            <dd><input name="selectThreeName" type="text" value="${product.selectThreeName!''}" class="input txt100" datatype="*1-100" sucmsg=""></dd>
                        </dl>
                    </#if>
                </#list>
            </#if>
        </div>
    </div>
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>SEO标题</dt>
            <dd>
                <input name="seoTitle" type="text" maxlength="255" id="txtSeoTitle" value="<#if product??>${product.seoTitle!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO关健字</dt>
            <dd>
                <textarea name="seoKeywords" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if product??>${product.seoKeywords!""}</#if></textarea>
                <span class="Validform_checktip">以“,”逗号区分开，255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO描述</dt>
            <dd>
                <textarea name="seoDescription" rows="2" cols="20" id="txtSeoDescription" class="input" datatype="*0-255" sucmsg=" "><#if product??>${product.seoDescription!""}</#if></textarea>
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


</body></html>