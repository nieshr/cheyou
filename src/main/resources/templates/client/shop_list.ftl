<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;" />
<title><#if site??>${site.seoTitle!''}-</#if>车有同盟</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>

<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />


<style type="text/css">
   html,#allmap {width: 100%; height:100%; margin:0;font-family:"微软雅黑";}     <#-- 去掉了body @by zhangji-->
   .dituliebiao_rt{width:743px; margin-top:20px; height:650px;float:right;}
   .dituliebiao_lt{ margin-top:20px;}
   .fr{float:right;}                <#--  @by zhangji-->
</style>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lwRXRetipHPGz8y6lzUlUZfc"></script>
<script type="text/javascript">
$(document).ready(function(){
	menuDownList("top_phone","#top_phonelist",".a1","sel");
	phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
	navDownList("navdown","li",".nav_showbox");
	menuDownList("mainnavdown","#navdown",".a2","sel");
	checkNowHover("shopping_down","shopping_sel");

    <#if shop_list?? && shop_list?size gt 0>
        loadMap(${shop_list[0].longitude!'102.718072'}, ${shop_list[0].latitude!'25.048034'});
    </#if>
});

function loadMap(x, y)
{
    // 百度地图API功能
    /*
    var map = new BMap.Map("allmap");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(x, y), 16);  // 初始化地图,设置中心点坐标和地图级别
    
    map.setCurrentCity("昆明");          // 设置地图显示的城市 此项是必须设置的
    
    map.addOverlay(new BMap.Marker(new BMap.Point(x, y)); // 创建点
    */
    
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(x, y);
    map.centerAndZoom(point, 16);
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    var marker = new BMap.Marker(new BMap.Point(x, y)); // 创建点
    map.addOverlay(marker);
}
</script>

</head>

<body>
<#include "/client/common_header.ftl" />

<div class="main">
    <div class="dituliebiao">
        <table class="pro_choose">
            <tr>
                <th width="50">区域</th>
                <td>
                    <#if city_list??>
                        <#list city_list as item>
                            <a <#if cid==item_index>class="sel"</#if> href="/shop/list?cid=${item_index}">${item!''}<span></span></a>
                        </#list>
                    </#if>
                </td>
            </tr>
        </table>
        <div class="clear"></div>
        <div>
        <div class="dituliebiao_lt">
            <div class="detail_tit" id="detail_tit">
                <div class="out">
                    <menu>
                        <a class="sel" href="#">综合</a>
                        <a href="#">评价</a>
                        <a href="#">人气</a>
                        <div class="clear"></div>
                    </menu>
                </div>
            </div>
   
            <ul style="display: block; position: relative; width: 457px; height: 650px; overflow: auto;">
                <#if shop_list??>
                    <#list shop_list as item>
                        <li>
                            <div class="mendiantupian">
                                <a href="/shop/${item.id}">
                                    <img src="${item.imageUri!''}" width="137" height="104" />
                                </a>
                            </div>
                            <div class="mendianditu_rt">
                            <h4><a href="/shop/${item.id}">${item.title!''}</a></h4>
                            <p>${item.address!''}</p>
                            <p>服务电话：<span>${item.serviceTele!''}</span><p>
                            <#--
                                <p>本店共完成<span><a href="#">120</a></span>个安装单，有<span><a href="#">197</a></span>条评论</p>
                                <p class="dituxinxin">
                                <a class="fl" href="#"><img src="/client/images/content/start01.png" /></a>
                                <a class="fl" href="#"><img src="/client/images/content/start01.png" /></a>
                                <a class="fl" href="#"><img src="/client/images/content/start01.png" /></a>
                                <a class="fl" href="#"><img src="/client/images/content/start02.png" /></a>
                                <a class="fl" href="#"><img src="/client/images/content/start03.png" /></a>
                                </p>
                            -->
                            <p class="dianouweizhi">
                                <a href="javascript:;" onclick="loadMap(<#if item.longitude??>${item.longitude?string("0.000000")}<#else>110</#if>, <#if item.latitude??>${item.latitude?string("0.000000")}<#else>39</#if>);">店铺位置</a>
                            </p>
                            </div>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>
   
        <div class="dituliebiao_rt fr">
            <div id="allmap" width="743" height="650";>  
            
            </div>
        </div>  
        </div>
    </div>
    <div class="clear"></div> 
</div><!--main END-->

<div class="clear h50"></div>

<#include "/client/common_footer.ftl" />
</body>
</html>
