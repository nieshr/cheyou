<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑仓库</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
});
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/warehouse/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="id" value="<#if warehouse??>${warehouse.id?c!""}</#if>" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/warehouse/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>仓库管理</span>
  <i class="arrow"></i>
  <span>编辑仓库</span>
</div>
<div class="line10"></div>
<!--导航栏-->
<!--内容-->
<div class="content-tab-wrap">
    <div id="floatHead" class="content-tab">
        <div class="content-tab-ul-wrap" >
            <ul>
                <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑仓库</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" style="display: block;">
    <dl>
        <dt>仓库名称</dt>
        <dd>
            <input name="title" type="text" value="<#if warehouse??>${warehouse.title!""}</#if>" class="input normal" ajaxurl="/Verwalter/warehouse/check<#if warehouse??>?id=${warehouse.id?c}</#if>" datatype="s" sucmsg=" ">
            <span class="Validform_checktip">*仓库名称</span>
        </dd>
    </dl>
    <dl>
        <dt>省</dt>
        <dd>
            <input name="province" type="text" value="<#if warehouse??>${warehouse.province!""}</#if>" class="input txt100" datatype="s0-10" sucmsg=" ">
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>市</dt>
        <dd>
            <input name="city" type="text" value="<#if warehouse??>${warehouse.city!""}</#if>" class="input txt100" datatype="s0-100" sucmsg=" ">
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>详细地址</dt>
        <dd>
            <input name="address" type="text" value="<#if warehouse??>${warehouse.address!""}</#if>" class="input normal" datatype="s0-100" sucmsg=" ">
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>排序数字</dt>
        <dd>
            <input name="sortId" type="text" value="<#if warehouse??>${warehouse.sortId!""}<#else>99</#if>" class="input txt100" datatype="n" sucmsg=" ">
            <span class="Validform_checktip">*数字，越小越向前</span>
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