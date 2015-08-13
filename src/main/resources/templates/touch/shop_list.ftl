<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;" />
<title><#if site??>${site.seoTitle!''}-</#if>车有同盟</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<style type="text/css">  
body, html,#myMap {width: 100%;height: 100%;overflow: hidden;margin:0;}  
</style> 
<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/common.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lwRXRetipHPGz8y6lzUlUZfc"></script>
<script type="text/javascript">
$(document).ready(function(){
hideMap();
});

function loadMap(x, y)
{
    showMap();
    // 百度地图API功能
    /*
    var map = new BMap.Map("myMap");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(x, y), 16);  // 初始化地图,设置中心点坐标和地图级别
    
    map.setCurrentCity("昆明");          // 设置地图显示的城市 此项是必须设置的
    
    map.addOverlay(new BMap.Marker(new BMap.Point(x, y)); // 创建点
    */
    
    var map = new BMap.Map("myMap");
    var point = new BMap.Point(x, y);
    map.centerAndZoom(point, 16);
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    var marker = new BMap.Marker(new BMap.Point(x, y)); // 创建点
    map.addOverlay(marker);
    map.addControl(new BMap.ZoomControl());//添加缩放控件
    
    
    
    var opts = {
        width : 400,    // 信息窗口宽度
        height: 70,     // 信息窗口高度
        title : "海底捞王府井店"  // 信息窗口标题
    }
    var infoWindow = new BMap.InfoWindow("点击marker将进入路线查询，并直接跳转到webapp主站", opts);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,point); //开启信息窗口
    marker.addEventListener("click", function(){
        /*start|end：（必选）
        {name:string,latlng:Lnglat}
        opts:
        mode：导航模式，固定为
        BMAP_MODE_TRANSIT、BMAP_MODE_DRIVING、
        BMAP_MODE_WALKING、BMAP_MODE_NAVIGATION
        分别表示公交、驾车、步行和导航，（必选）
        region：城市名或县名  当给定region时，认为起点和终点都在同一城市，除非单独给定起点或终点的城市
        origin_region/destination_region：同上
        */
        var start = {
             name:"王府井"
        }
        var end = {
            name:"西单"
        }
        var opts = {
            mode:BMAP_MODE_DRIVING,
            region:"北京"
        }
        var ss = new BMap.RouteSearch();
        ss.routeCall(start,end,opts);
    });
    
}

function hideMap()
{
    $("#allMap").hide();
}

function showMap(longitude,latitude)
{
    $("#allMap").show();
}

function hideSerivceStars()
{
    $("#serviceStars").hide();
}


</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>门店查询</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>
<!--header END-->


<!--地图的添加 2015-8-12 19:49:37 mdj-->
<div id="allMap" style="width:90%;margin-left:auto;margin-right:auto; height:80%;margin-bottom:auto;z-index:999999999;">
    <a class="fr" style="z-index:999999999; /* margin-top:50px; position: absolute;*/  margin-right: 10px;" href="javascript:hideMap();"><img src="/client/images/20150407114113116_easyicon_net_71.8756476684.png" width="25" height="25"></a>
    <div id="myMap">
    </div>
</div>
<!--地图结束     2015-8-12 19:49:37 mdj-->
<ul class="main shoplist">
    <#if shop_list??>
        <#list shop_list as item>
        <li>
            <a class="a1" href="#"><img src="${item.imageUri}" /></a>
            <p class="p1">${item.title}
              <span id="serviceStars">
                <#if ("shop_serviceStars"++item.id)?eval?? && ("shop_serviceStars"++item.id)?eval lte 0 >
                <#else>
                <img src="/touch/images/<#if ("shop_serviceStars"++item.id)?eval?? && ("shop_serviceStars"++item.id)?eval lt 1.0>star02.png<#else>star01.png</#if>" height="15" />
                <img src="/touch/images/<#if ("shop_serviceStars"++item.id)?eval?? && ("shop_serviceStars"++item.id)?eval lt 1.5>star03.png<#elseif ("shop_serviceStars"++item.id)?eval gte 1.5 && ("shop_serviceStars"++item.id)?eval lt 2.0>star02.png<#else>star01.png</#if>" height="15" />
                <img src="/touch/images/<#if ("shop_serviceStars"++item.id)?eval?? && ("shop_serviceStars"++item.id)?eval lt 2.5>star03.png<#elseif ("shop_serviceStars"++item.id)?eval gte 2.5 && ("shop_serviceStars"++item.id)?eval lt 3.0>star02.png<#else>star01.png</#if>" height="15" />
                <img src="/touch/images/<#if ("shop_serviceStars"++item.id)?eval?? && ("shop_serviceStars"++item.id)?eval lt 3.5>star03.png<#elseif ("shop_serviceStars"++item.id)?eval gte 3.5 && ("shop_serviceStars"++item.id)?eval lt 4.0>star02.png<#else>star01.png</#if>" height="15" />
                <img src="/touch/images/<#if ("shop_serviceStars"++item.id)?eval?? && ("shop_serviceStars"++item.id)?eval lt 4.5>star03.png<#elseif ("shop_serviceStars"++item.id)?eval gte 4.5 && ("shop_serviceStars"++item.id)?eval lt 5.0>star02.png<#else>star01.png</#if>" height="15" />
                </#if>
              </span>
            </p>
            <p class="p2">详细地址：${item.address}</p>
            <p class="p2">
              <a href="javascript:loadMap(<#if item.longitude??>${item.longitude?string("0.000000")}<#else>110</#if>, <#if item.latitude??>${item.latitude?string("0.000000")}<#else>39</#if>);">查看地图</a>
            <a class="a2" href="tel://${item.complainTele}">拨打电话</a>
            </p>
            <div class="clear"></div>
          </li>
        </#list>
    <#else>
    <li>暂无商店</li>
    </#if>
  
</ul>


<div class="clear"></div>
<#--<a class="ma15 ta-c block" href="#"><img src="/touch/images/more.png" height="20" /></a>-->
<div class="clear h20"></div>
<section class="botlogin">
  <#if username??>
  <a href="/touch/user">${username}</a>
  <a class="ml20" href="/touch/logout">退出</a>
  <#else>
  <a href="/touch/login">登录</a><a class="ml20" href="/touch/reg">注册</a>
  </#if>
  <a class="a1" href="javascript:$('html,body').animate({scrollTop:0},500);">TOP</a>
</section>
<div class="clear"></div>
<footer class="comfoot main">
    <a href="/shop/list">电脑版</a>
    <a href="/touch/shop/list">触屏版</a>
</footer>
<p class="bottext mainbox">${site.copyright!''}</p>
<!--没有任何代码-->
</body>
</html>
