<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>标签管理</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>

<script type="text/javascript" src="/mag/js/jquery.lazyload.min.js"></script>

<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>

<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form name="form1" method="post" action="/Verwalter/tag/list" id="form1">
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
<div class="location" style="position: static; top: 0px;">
  <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>关键词管理</span>
  <i class="arrow"></i>
  <span>关键词列表</span>  
</div>
<!--/导航栏-->

<!--工具栏-->
<div class="toolbar-wrap">
     <div id="floatHead" class="toolbar" style="position: static; top: 42px;">
          <div class="l-list">
              <ul class="icon-list">
                <li><a class="add" href="/Verwalter/tag/edit"><i></i><span>新增</span></a></li>
                <li><a class="save" href="javascript:__doPostBack('btnSave','')"><i></i><span>保存</span></a></li>
                <li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
                <li><a onclick="return ExePostBack('btnDelete');" id="btnDelete" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除</span></a></li>
              </ul>
              <div class="menu-list">
                    <div class="rule-single-select">
                        <select name="typeId" onchange="javascript:setTimeout(__doPostBack('typeId', ''), 0)">
                            <option <#if typeId??><#else>selected="selected"</#if> value="">所有标签</option>
                            <option <#if typeId?? && typeId==1>selected="selected"</#if> value="1">商品分类标签</option>
                            <option <#if typeId?? && typeId==2>selected="selected"</#if> value="2">商品标签</option>
                            <option <#if typeId?? && typeId==3>selected="selected"</#if> value="3">用户标签</option>
                            <option <#if typeId?? && typeId==4>selected="selected"</#if> value="4">评价标签</option>
                        </select>
                    </div>
              </div>
          </div>
     </div>
</div>
<!--/工具栏-->

<!--列表-->

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
  <tbody>
  <tr class="odd_bg">
    <th width="8%">选择</th>
    <th align="center">标签</th>
    <th align="center" width="10%">类型</th>
    <th align="center" width="25%">发布时间</th>
    <th width="8%">排序</th>
    <th width="6%">操作</th>
  </tr>

    <#if tag_page??>
        <#list tag_page.content as tag>
            <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input id="listChkId" type="checkbox" name="listChkId" value="${tag_index}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${tag.id?c}">
                </td>
                <td align="center"><a href="/Verwalter/tag/edit?id=${tag.id?c}">${tag.title!""}</a></td>
                <#if tag.typeId?? && tag.typeId==1>
                    <td align="center">商品分类标签</td>
                <#elseif tag.typeId?? && tag.typeId==2>
                    <td align="center">商品标签</td>
                <#elseif tag.typeId?? && tag.typeId==3>
                    <td align="center">用户标签</td>
                <#elseif tag.typeId?? && tag.typeId==4>
                    <td align="center">评价标签</td>
                </#if>    
                <td align="center">${tag.createTime!""}</td>
                <td align="center">
                    <input name="listSortId" type="text" value="${tag.sortId!""}" class="sort" onkeydown="return checkNumber(event);">
                </td>
                <td align="center">
                    <a href="/Verwalter/tag/edit?id=${tag.id?c}">修改</a>
                </td>
            </tr>
        </#list>
    </#if>
     
</tbody>
</table>

<!--/列表-->

<!--内容底部-->
<#assign PAGE_DATA=tag_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body></html>