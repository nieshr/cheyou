<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>收藏商品</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>

<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form name="form1" method="post" action="/Verwalter/user/collect/list?userId=${userId!""}" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}">
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
</div>

<script type="text/javascript">
var theForm = document.forms['form1'];
    if (!theForm) {
        theForm = document.form1;
    }
    function __doPostBack(eventTarget, eventArgument) {
        if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
            theForm.__EVENTTARGET.value = eventTarget;
            theForm.__EVENTARGUMENT.value = eventArgument;
            theForm.submit();
        }
    }
</script>

<!--导航栏-->
<div class="location">
  <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <a href="/Verwalter/user/list"><span>用户列表</span></a>
  <i class="arrow"></i>
  <span>收藏商品</span>
</div>
<!--/导航栏-->

<!--工具栏-->
<div class="toolbar-wrap">
  <div id="floatHead" class="toolbar">
    <div class="l-list">      
    </div>
    <div class="r-list">
      <input name="keywords" type="text" value="${keywords!""}" id="txtKeywords" class="keyword">
      <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('btnSearch','')">查询</a>
    </div>
  </div>
</div>
<!--/工具栏-->

<!--列表-->

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
  <tbody>
  <tr class="odd_bg">
    <th align="center" width="15%">用户名</th>
    <th align="center" width="10%">商品ID</th>
    <th align="center">商品标题</th>
    <th align="left" width="16%">时间</th>
  </tr>

    <#if user_collect_page??>
        <#list user_collect_page.content as collect>
            <tr>
            <td align="center">${collect.username!""}</td>
            <td align="center">${collect.goodsId!""}</td>
            <td align="center">${collect.goodsTitle!""}</td>
            <td align="left">${collect.collectTime!""}</td>
            </tr>
        </#list>
    </#if>
  
</tbody></table>

<!--/列表-->

<!--内容底部-->
<div class="line20"></div>
<div class="pagelist">
  <div class="l-btns">
    <span>显示</span><input name="txtPageNum" type="text" value="${size!""}" onchange="javascript:setTimeout('__doPostBack('txtPageNum','')', 0)" onkeypress="if (WebForm_TextBoxKeyHandler(event) == false) return false;" id="txtPageNum" class="pagenum" onkeydown="return checkNumber(event);"><span>条/页</span>
  </div>
  <div id="PageContent" class="default"><span>共<#if user_collect_page??>${user_collect_page.totalElements}</#if>数据,分<#if user_collect_page??>${user_collect_page.totalPages}</#if>页显示,当前为第<#if user_collect_page??>${user_collect_page.number+1}</#if>页</span>
        <#if user_collect_page??>
            <#assign continueEnter=false>
            <#if user_collect_page.number+1 == 1>
                <span class="disabled">&lt;&lt;上一页</span>
            <#else>
                <a href="/Verwalter/user/collect/list?keyword=${keyword!""}&page=${user_collect_page.number-1}&size=${size!""}">&lt;&lt;上一页</a>
            </#if>
            
            <#list 1..user_collect_page.totalPages as page>
                <#if page <= 3 || (user_collect_page.totalPages-page) < 3 || (user_collect_page.number+1-page)?abs<3 >
                    <#if page == user_collect_page.number+1>
                        <span class="current">${page}</span>
                    <#else>
                        <a href="/Verwalter/user/collect/list?keyword=${keyword!""}&page=${page-1}&size=${size!""}">${page}</a>
                    </#if>
                    <#assign continueEnter=false>
                <#else>
                    <#if !continueEnter>
                        ...
                        <#assign continueEnter=true>
                    </#if>
                </#if>
            </#list>
            
            <#if user_collect_page.number+1 == user_collect_page.totalPages>
                <span class="disabled">下一页&gt;&gt;</span>
            <#else>
                <a href="/Verwalter/user/collect/list?recent=${keyword!""}&page=${user_collect_page.number+1}&size=${size!""}">下一页&gt;&gt;</a>
            </#if>
        </#if>
  </div>
</div>
<!--/内容底部-->
</form>


</body></html>