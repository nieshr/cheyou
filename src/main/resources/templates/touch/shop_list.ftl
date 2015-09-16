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
body, html,#myMap {width: 100%;height: 100%;margin:0;}  
</style> 
<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/ljs-v1.01.js"></script>
<script src="/touch/js/common.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lwRXRetipHPGz8y6lzUlUZfc"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<script type="text/javascript">
$(document).ready(function(){
hideMap();
<#list shop_list as item>
    <#if item.showPictures??>
    var the_one${item_index} = {
          "boxid":"onebox${item_index}", //最外层id
          "sumid":"onesum${item_index}", //包裹列表的id
          "stylename":"onepart",//内部滚动的标签class
          "boxwidth":60,//外层宽
          "boxheight":60,//外层高
          "objwidth":60,//单个个体宽度
          "objheight":60,//单个个体高度
          "autogo":true,//是否开启自动滚动
          "gospeed":2000,//滚动速度
          "speed":200, // 滚动间隔速度
          "leftid":"oneleft",//左箭头id
          "rightid":"oneright"//右箭头id
        };//the_one END
        $.ljs_gundong.oneLeft(the_one${item_index});
        </#if>
</#list>
});
function loadMap(x, y, z, address)
{
    scroll(0,0);
    showMap();
    // 百度地图API功能
    /*
    var map = new BMap.Map("myMap");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(x, y), 16);  // 初始化地图,设置中心点坐标和地图级别
    
    map.setCurrentCity("昆明");          // 设置地图显示的城市 此项是必须设置的
    
    map.addOverlay(new BMap.Marker(new BMap.Point(x, y)); // 创建点
    */
    
    var myIcon = new BMap.Icon("/client/images/map.png", new BMap.Size(23, 25), {
    offset: new BMap.Size(10, 25), // 指定定位位置
    imageOffset: new BMap.Size(0, 0 - 0 * 25) // 设置图片偏移
    });
    
    var map = new BMap.Map("myMap");
    var point = new BMap.Point(x, y);
    map.centerAndZoom(point, 16);
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    var marker = new BMap.Marker(new BMap.Point(x, y),{icon: myIcon}); // 创建点
    map.addOverlay(marker);
   // map.addControl(new BMap.ZoomControl());//添加缩放控件
    
    
    var opts = {
        width : 10,    // 信息窗口宽度
        height: 50,     // 信息窗口高度
        title : z  // 信息窗口标题
    }
   // var infoWindow = new BMap.InfoWindow("点击将进入路线导航", opts);  // 创建信息窗口对象
    var infoWindow = new BMap.InfoWindow("<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+z+"</h4><p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>点击将进入路线查询</p>")
   
    //添加      
    var start1 = "";
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var map = new BMap.Map("newMap");
            var point1 = new BMap.Point(r.point.lng,r.point.lat);
            map.centerAndZoom(point,12);
            var geoc = new BMap.Geocoder(); 
            //alert('您的位置：'+r.point.lng+','+r.point.lat);
            geoc.getLocation(point1, function(rs){
            var addComp = rs.addressComponents;
            //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
            start1 = addComp.city + "" + addComp.district + "" + addComp.street + "" + addComp.streetNumber;
            //alert(start);
            marker.addEventListener("click", function(){
                        /*start|end：（必选）
                        {name:string,latlng:Lnglat}
                        opts:
                        mode：导航模式，固定为
                        BMAP_MODE_TRANSIT、BMAP_MODE_DRIVING、
                        BMAP_MODE_WALKING、BMAP_MODE_NAVIGATION
                        分别表示公交、驾车、步行和导航，（必选）
                        region：城市名或县名  当给定region时，认为起点和终点都在同一城市，除非单独给定起点
                
                或终点的城市
                        origin_region/destination_region：同上
                        */
                        var start = {
                             name:start1
                        }
                        var end = {
                            name:address
                        }
                        var opts = {
                            mode:BMAP_MODE_DRIVING,
                            region:"昆明"
                        }
                        var ss = new BMap.RouteSearch();
                        ss.routeCall(start,end,opts);
                    });
                         }); 
                        }              
    },{enableHighAccuracy: true})
    
    map.openInfoWindow(infoWindow,point); //开启信息窗口    

   
    
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

<div><div id="newMap"></div></div>
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
            <div id="onebox${item_index}" style="float:left;">
                <div id="onesum${item_index}" style="float:left;">
                    <#if item.showPictures??>
                        <#list item.showPictures?split(",") as uri>
                            <#if ""!=uri>
                                <a class="onepart" href="#"><img src="${item.imageUri}" width="60px;" height="60px;"/></a>
                            </#if>
                        </#list>
                    </#if>
                </div>
            </div>
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
              <a href="javascript:loadMap(<#if item.longitude??>${item.longitude?string("0.000000")}<#else>110</#if>, <#if item.latitude??>${item.latitude?string("0.000000")}<#else>39</#if>,'${item.title!''}','${item.address!''}');">查看地图</a>
            <a class="a2" href="tel://${item.serviceTele}">拨打电话</a>
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
