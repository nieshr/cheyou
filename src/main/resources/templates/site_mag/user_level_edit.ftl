<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑信息</title>
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
<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form method="post" action="/Verwalter/user/level/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="userLevelId" value="${userLevelId!""}" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/user/level/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>会员管理</span>
  <i class="arrow"></i>
  <span>编辑会员信息</span>
</div>
<div class="line10"></div>
<!--导航栏-->
<!--内容-->
<div class="content-tab-wrap">
    <div id="floatHead" class="content-tab">
        <div class="content-tab-ul-wrap" >
            <ul>
                <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑会员等级</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" style="display: block;">
    <dl>
        <dt>用户等级</dt>
        <dd>
            <input name="levelId" type="text" value="<#if user_level??>${user_level.levelId!""}</#if>" ajaxurl="/Verwalter/user/level/check/levelId<#if user_level??>?id=${user_level.id?c}</#if>" class="input txt100" datatype="n1-2" sucmsg=" ">
            <span class="Validform_checktip">*数字表示的用户等级，从1开始，熟悉越高等级越高</span>
        </dd>
    </dl>
    <dl>
        <dt>等级名称</dt>
        <dd>
            <input name="title" type="text" value="<#if user_level??>${user_level.title!""}</#if>" ajaxurl="/Verwalter/user/level/check/title<#if user_level??>?id=${user_level.id?c}</#if>" class="input txt100" datatype="s" sucmsg=" ">
            <span class="Validform_checktip">*等级名称，将在前台显示</span>
        </dd>
    </dl>
    <dl>
        <dt>累计消费额度</dt>
        <dd>
            <input name="requiredConsumption" type="text" value="<#if user_level??>${user_level.requiredConsumption?string("#.##")}</#if>" class="input txt100" datatype="/^[0-9][0-9]*[.]?[0-9]{0,2}$/" sucmsg=" ">
            <span class="Validform_checktip">*用户累计消费额度到达该值时自动升入该级别</span>
        </dd>
    </dl>
    <dl>
        <dt>价格百分比</dt>
        <dd>
            <input name="discountRatio" type="text" value="<#if user_level??>${user_level.discountRatio!""}</#if>" class="input txt100" datatype="/(^[1][.]?[0]{0,2}$)|(^[0][.]?[0-9]{0,2}$)/" sucmsg=" ">
            <span class="Validform_checktip">*商品优惠百分比，小于或等译1，为1时表示不优惠</span>
        </dd>
    </dl>
    <dl>
        <dt>是否开启</dt>
        <dd>
            <div class="rule-multi-radio multi-radio">
                <span id="rblStatus">
                    <input type="radio" name="isEnable" value="1" <#if user_level?? && user_level.isEnable>checked="checked"</#if>>
                    <label>启用</label>
                    <input type="radio" name="isEnable" value="0" <#if !user_level?? || !user_level.isEnable?? || !user_level.isEnable>checked="checked"</#if>>
                    <label>不启用</label>
            </div>
        </dd>
    </dl>
    <dl>
        <dt>排序数字</dt>
        <dd>
            <input name="sortId" type="text" value="<#if user_level??>${user_level.sortId!""}<#else>99</#if>" class="input txt100" datatype="n" sucmsg=" ">
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