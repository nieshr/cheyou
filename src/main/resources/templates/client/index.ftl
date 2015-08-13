<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<meta property="qc:admins" content="274143415163145116375" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>


<link href="/client/css/style.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/common.css" rel="stylesheet" type="text/css" />

<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

<#--styletext zhanngji-->

<style type="text/css">
.tousuyemian{line-height: 30px;
  color: #333;
  background: #FFF;
  padding: 20px 20px 20px 20px;  
  position: fixed;
  overflow: hidden;
  width: 1150px;}
.tousuyemian2{line-height: 30px;
  color: #333;
  background: #FFF;
  padding: 20px 20px 20px 20px;  
  position: absolute;
  overflow: hidden;
  width: 900px;}
.tousuyemian p{ font-size:14px; line-height:20px; height:45px;}
.tousulianxifangshi span{width:65px;  text-align:right;}
textarea.input { width: 98%; height:50px; color:#666;}
.Message_an {border-radius:5px; -moz-border-radius:5px; -moz-webkit-radius:5px; -o-border-radius:5px; width:100px; height:35px; line-height:35px; color:#fff; font-size:14px;  background-position: 0px 0px; border:0px; cursor:pointer; background:#ff4454; }
.Message_an:hover,.hov:hover{
    -moz-box-shadow:0px 0px 4px #333112; -webkit-box-shadow:0px 0px 4px #333112; box-shadow:0px 0px 4px #333112; background:#1c2b38;}
}
.bgff{background:#FFF;}     
.mianfeilingqutanchu{background:url(../client/images/images/darkbg.png) repeat; width:100%; height:100%; z-index:999999999; position:absolute;overflow: hidden;}  
.mianfeilingqutanchu2{background:url(../client/images/images/darkbg.png) repeat; width:950px; height:460px; z-index:999999999; position:absolute;overflow: hidden;}  
.mianfeilingqutanchu_dl{ margin-top:3%;}
.mianfeilingqutanchu_dl2{ margin-top:6px;margin-left:4px;}
.rightb_gundong{ position:absolute; z-index:99999; margin-top:00px;width:30px; height:50px; margin-left:1130px;}
.rightb_gundong2{ position:absolute; z-index:99999; margin-top:00px;width:30px; height:50px; margin-left:880px;}

</style>

<#--2015年7月30日8:51:25--> 


<script type="text/javascript">
  $(document).ready(function(){
    menuDownList("top_phone","#top_phonelist",".a1","sel");
    phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
    navDownList("navdown","li",".nav_showbox");
    //menuDownList("mainnavdown","#navdown",".a2","sel");
    checkNowHover("shopping_down","shopping_sel");
    checkBoxShow("topcheck","a","topsum","li","sel");
    checkBoxShow("teamadmenu","a","teamadsum","a","sel");
    
    //左右单张滚动
    var the_one = {
      "boxid":"onebox",         //最外层id
      "sumid":"onesum",         //包裹列表的id
      "stylename":"onepart",    //内部滚动的标签class
      "boxwidth":570,           //外层宽
      "boxheight":375,          //外层高
      "objwidth":570,           //单个个体宽度
      "objheight":375,          //单个个体高度
      "autogo":true,            //是否开启自动滚动
      "gospeed":2000,           //滚动速度
      "speed":600,              // 滚动间隔速度
      "leftid":"oneboxLeft",    //左箭头id
      "rightid":"oneboxRight"   //右箭头id
    };//the_one END
    $.ljs_gundong.oneLeft(the_one);
    
    //左右单张滚动
    var the_one2 = {
      "boxid":"onebox2", //最外层id
      "sumid":"onesum2", //包裹列表的id
      "stylename":"onepart2",//内部滚动的标签class
      "boxwidth":280,//外层宽
      "boxheight":375,//外层高
      "objwidth":280,//单个个体宽度
      "objheight":375,//单个个体高度
      "autogo":true,//是否开启自动滚动
      "gospeed":2000,//滚动速度
      "speed":1500,             // 滚动间隔速度
      "leftid":"onebox2Left",   //左箭头id
      "rightid":"onebox2Right"  //右箭头id
    };//the_one END
    $.ljs_gundong.oneLeft(the_one2);
    
    
    //左右单张滚动
    var the_one3 = {
      "boxid":"onebox3", //最外层id
      "sumid":"onesum3", //包裹列表的id
      "stylename":"onepart3",//内部滚动的标签class
      "boxwidth":335,//外层宽
      "boxheight":323,//外层高
      "objwidth":335,//单个个体宽度
      "objheight":323,//单个个体高度
      "autogo":true,//是否开启自动滚动
      "gospeed":2000,//滚动速度
      "speed":300, // 滚动间隔速度
      "leftid":"onebox3Left",//左箭头id
      "rightid":"onebox3Right"//右箭头id
    };//the_one END
    $.ljs_gundong.oneLeft(the_one3);
    
    floatBoxQQ();
});
function checkTime(i)  
{  
    if (i < 10) {  
        i = "0" + i;  
    }  
    return i;  
}

function miaoPageChang(i)
{
    $("#miao-menu a").removeClass("sel");
    $("#miao-menu a").eq(i).addClass("sel");
    $("#miao-ul li").css("display", "none");
    $("#miao-ul li").eq(i).css("display", "block");
}

function miaoClockChang(i)
{
    $(".miaocur dl").css("display", "none");
    $(".miaocur dl").eq(i).css("display", "block");
    
    $("#miaoClockDiv a").removeClass("sel");
    $("#miaoClockDiv a").eq(i).addClass("sel"); 
}

function miaoClockNextChange(i)
{
    $(".miaonext dl").css("display", "none");
    $(".miaonext dl").eq(i).css("display", "block");
    
    $("#miaoClockNextDiv a").removeClass("sel");
    $("#miaoClockNextDiv a").eq(i).addClass("sel"); 
}

function miaoClockPrevChange(i)
{
    $(".miaoprev dl").css("display", "none");
    $(".miaoprev dl").eq(i).css("display", "block");
    
    $("#miaoClockPrevDiv a").removeClass("sel");
    $("#miaoClockPrevDiv a").eq(i).addClass("sel"); 
}

function tuanPageChang(i)
{
    $("#tuan-menu a").removeClass("sel");
    $("#tuan-menu a").eq(i).addClass("sel");
    $("#tuan-ul li").css("display", "none");
    $("#tuan-ul li").eq(i).css("display", "block");
}

function tourPageChang(i)
{
    $("#tour-menu a").removeClass("sel");
    $("#tour-menu a").eq(i).addClass("sel");
    $("#tour-ul li").css("display", "none");
    $("#tour-ul li").eq(i).css("display", "block");
}

$(function(){
    //左右滚动幻灯片广告
    var essayad = {
             "boxid":"indexadbox",//最外层id
             "sumid":"indexadsum",//列表id
             "li01":"threeli01",//li标签id
             "li02":"threeli02",//重复li二号id
             "stylename":"indexadpart",//内部滚动的标签class
             "numid":"indexadnum",//数字id
             "boxwidth":758,//宽度
             "boxheight":282,//高度
             "preid":"indexadpre",//上一个id
             "nextid":"indexadnext",//下一个id
             "speed":5000,// 切换速度
             "gospeed":500//轮换速度
          };
    $.ljs_adcartoon.arrowNum(essayad);
    
    var shopad = {
             "boxid":"shopadbox",//最外层id
             "sumid":"shopadsum",//列表id
             "li01":"shopli01",//li标签id
             "li02":"shopli02",//重复li二号id
             "stylename":"indexadpart",//内部滚动的标签class
             "numid":"shopadnum",//数字id
             "boxwidth":335,//宽度
             "boxheight":323,//高度
             "preid":"shopadpre",//上一个id
             "nextid":"shopadnext",//下一个id
             "speed":5000,// 切换速度
             "gospeed":500//轮换速度
          };
    $.ljs_adcartoon.arrowNum(shopad);
    });

//lichong 获取鼠标坐标    
    function mouseMove(ev) 
{ 
Ev= ev || window.event; 
var mousePos = mouseCoords(ev); 
document.getElementById("xxx").value = mousePos.x; 
document.getElementById("yyy").value = mousePos.y; 
} 
function mouseCoords(ev) 
{ 
if(ev.pageX || ev.pageY){ 
return {x:ev.pageX, y:ev.pageY}; 
} 
return{ 
x:ev.clientX + document.body.scrollLeft - document.body.clientLeft, 
y:ev.clientY + document.body.scrollTop - document.body.clientTop 
}; 
} 
document.onmousemove = mouseMove;
</script>
<meta property="qc:admins" content="274143415163145116375" />
</head>

<body>
<input type="hidden" name="mouseposX" id="xxx" value="0">
<input type="hidden" name="mouseposY" id="yyy" value="0">
<#if index_top_ad_list?? && index_top_ad_list?size gt 0>
    <#list index_top_ad_list as item>
        <a class="w100 block ta-c" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}" style=" background:url(${item.fileUri!''}) no-repeat top center; height:100px;"></a>
    </#list>
</#if>
<#-- 投訴頁面js-->
<#-- 投诉 -->
<script>
$(document).ready(function(){
    $("#suggestionForm").Validform({
        tiptype: 3,
        ajaxPost:true,
        callback: function(data) {
            if (data.code==0)
            {
                alert("提交成功，我们会尽快处理，请耐心等待");
                window.location.reload();
            }
            else
            {
                alert(data.message);
            }
        }
    });
});


function hideSuggestion()
{
    $("#sugguestion-div").css("display", "none");
}

function showSuggestion()
{
    $("#sugguestion-div").css("display", "block");
}
function move()
{
    $('html,body').animate({scrollTop:0},500);
}
</script>
<script>
//车友还想团购 demand
$(document).ready(function(){
    $("#demandForm").Validform({
        tiptype: 3,
        ajaxPost:true,
        callback: function(data) {
            if (data.code==0)
            {
                alert("提交成功，我们会尽快处理，请耐心等待");
                window.location.reload();
            }
            else
            {
                alert(data.message);
            }
        }
    });
});


function hideDemand()
{
    $("#demand-div").css("display", "none");
}

function showDemand()
{
    $("#demand-div").css("display", "block");
}
</script>

<script>
//二级状态增加阴影效果  @by zhangji
$(document).ready(function(){ 
     $(".shadow_add").mouseover(function(){   
        var index = $(this).index();
        $(".shadow_add").eq(index).css({"box-shadow":"10px 10px 5px #888888",
                                        "background-color":"#f7f7f7"});        
    });
        
     $(".shadow_add").mouseout(function(){    
        var index = $(this).index();
        $(".shadow_add").eq(index).css({"box-shadow":"0px 0px 0px #888888","background-color":"transparent"}); 
     });
     
     $(".shadow_add2").mouseover(function(){   
         var index = $(this).index();
         $(".shadow_add2").eq(index).css({"box-shadow":"10px 10px 5px #888888",
                                         "background-color":"#f7f7f7"});        
     });
         
      $(".shadow_add2").mouseout(function(){    
         var index = $(this).index();
         $(".shadow_add2").eq(index).css({"box-shadow":"0px 0px 0px #888888","background-color":"transparent"}); 
      });
}); 
</script>

<div id="sugguestion-div" class="mianfeilingqutanchu" style="display:none;">
    <div class="mianfeilingqutanchu_dl"> 
        <div class="main bgff">
            <div class="tousuyemian">
                <div class="rightb_gundong fr">
                    <a href="javascript:hideSuggestion();"><img src="/client/images/20150407114113116_easyicon_net_71.8756476684.png" width="21" height="21" /></a>
                </div>
                  <form id="suggestionForm" action="/suggestion/add">
                      <div class="clear"></div>
                      <h3>我要投诉</h3>
                      <p>在您填写下列投诉内容之前，我们首先代表车有同盟为导致您进行投诉的原因（行为）表示歉意，请详细描述事件经过，以便我们尽快为您解决问题，我们一定会及时处理，给您一个满意的解决方案，您的满意是我们最大的动力，谢谢！</p>
                      <div class="tousuneirong">
                          <div class="clear h10"></div>
                          <span><b class="red">*</b>投诉内容</span>
                          <div class=" clear"></div>
                          <textarea class="input Validform_error" style="width:1000px;height:250px" onfocus="if(value=='') {value=''}" onblur="if (value=='') {value=''}"  value="" datatype="*1-255" nullmsg="请输入投诉内容" id="suggestionContent" name="content"></textarea>
                          
                      </div>
                      <div class="tousulianxifangshi mt20">
                          <p>为了尽快为您解决问题，请提供您的联系方式，谢谢。</p>
                          <div> 
                              <span><b class="red">*</b>称呼</span>                                               
                              <input name="name" value="" datatype="*1-20" nullmsg="请输入您的称呼" class="input ml20" type="text"/>                                         
                          </div>
                          <div class="clear h10"></div>
                          <div>
                              <span>邮箱&nbsp;&nbsp;</span>
                              <input name="mail"   value="" datatype="*0-250" nullmsg="请输入正确的邮箱地址" class="input ml20" type="text"/>
                          </div>
                          <div class="clear h10"></div>
                          <div>
                              <span><b class="red">*</b>手机</span>
                              <input name="mobile"   value="" datatype="m" nullmsg="请输入您的手机号码" class="input ml20" type="text"/>
                          </div>
                          <div class="clear h20"></div>
                          <input class="Message_an" type="submit" value="提交" title="提交" />
                      </div>
                   </form>
            
                <div class="tousubeizhu mt5">
                     <h4>备注</h4>
                     <p>您还可以拨打${site.telephone!''}进行电话投诉</p>
                </div>  
            </div>
        </div>
    
        <div class="clear"></div> 
    </div>
</div>
                  


<div class="top">
    <div class="w1200 top1">
        <p class="huanyin">商城访问量：<span class="blue">${site.totalVisits!'0'}</span></p>
        <p class="huanyin">在线人数：<span class="blue">${site.totalOnlines!'1'}</span></p>
    
        <div class="wdbgg">
            <#if username??>
                <a href="/user">${username}<i></i></a>
                <a href="/logout">退出<i></i></a>
            <#else>
                <a href="/login" target="_blank">登录<i></i></a>
                <a href="/reg" target="_blank">免费注册<i></i></a>               
            </#if>
            <a href="/user/order/list/0">我的订单</a>
            <a href="/user">会员中心</a>
            <a href="javascript:;">在线咨询</a>
            服务热线：<#if site??>${site.telephone!''}</#if>
        </div>
    </div>
</div>
<div class="w1200 top2">
    <div class="logo"><a href="/"><img src="<#if site??>${site.logoUri!''}</#if>" width="200" height="100"/></a></div>
    <div class="tpgg">
        <#if top_small_ad_list??>
            <#list top_small_ad_list as item>
                <a <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                    <img src="${item.fileUri!''}" width="70" height="38"/>
                </a>
                <#break>
            </#list>
        </#if>
    </div>
    <div class="ssbox">
        <div class="clear"></div>
        <form action="/search" method="get">
            <input type="text" class="ss_txt" name="keywords" value="${keywords!keywords_list[0].title}"/>
            <input type="submit" class="ss_btn" value="" />
        </form>
        <div class="clear"></div>
        <p class="sousuohuise" style="margin-top: 5px;">
            <#if keywords_list??>
                <#list keywords_list as item>
                    <#if item_index gt 0>
                    <a href="${item.linkUri!''}">${item.title}</a>
                    </#if>
                </#list>
            </#if>
        </p>
    </div>
    <div id="shopping_down" class="shopping_box">
        <span class="sp1"><#if cart_goods_list??>${cart_goods_list?size}<#else>0</#if></span>
        <a class="a9" href="/cart"><img src="/client/images/liebiao_09.png" width="28" height="28" />购物车<i></i></a>
        <menu>
            <#if cart_goods_list?? && cart_goods_list?size gt 0>
<script>
function delItem(id)
{
    if (null == id)
    {
        return;
    }
    
    $.ajax({
        type:"post",
        url:"/cart/del",
        data:{"id": id},
        success:function(data){
            location.reload();
        }
    });
}
</script>
                <#assign totalGoods=0>
                <#assign totalPrice=0>
                <h2>最新加入的商品</h2>
                <#list cart_goods_list as item>
                    <div class="shopping_list">
                        <div class="clear"></div>
                        <a class="a2" href="/goods/${item.goodsId}"><img src="${item.goodsCoverImageUri!''}" /></a>
                        <a class="a3" href="/goods/${item.goodsId}">${item.goodsTitle!''}</a>
                        <p>￥<#if item.price??>${item.price?string("0.00")} x ${item.quantity!''}</#if><a href="javascript:delItem(${item.id?c});">删除</a></p>
                        <div class="clear"></div>
                    </div>
                    <#if item.isSelected>
                        <#assign totalGoods=totalGoods+item.quantity>
                        <#if item.price??>
                            <#assign totalPrice=totalPrice+item.price*item.quantity>
                        </#if>
                    </#if>
                </#list>
                
                <h4 class="shopping_price">
                    共<#if cart_goods_list??>${cart_goods_list?size}<#else>0</#if>件商品 &nbsp;&nbsp;共计<span class="fw-b">￥<#if totalPrice??>${totalPrice?string(0.00)}</#if></span>
                    <a href="/cart">去结算</a>
                </h4>
            <#else>
                <h3 class="ta-c pa15 fs14 fw400">购物车中还没有商品，赶紧选购吧！</h3>
            </#if>
        </menu>
    </div>
</div>
<nav class="navbox">
    <div class="w1200">
        <section class="navlist" id="mainnavdown">
            <a href="javascript:;" class="a2">全部商品分类</a>
            <ul class="navlistout" id="navdown">
                <#if top_cat_list??>
                    <#list top_cat_list as item>
                        <li>
                            <h3><a href="/list/${item.id?c}">${item.title!''}</a></h3>
                            <div class="nav_showbox">
                                <div class="clear"></div>
                                <#if ("second_level_"+item_index+"_cat_list")?eval?? >
                                    <table class="nav_more">
                                        <#list ("second_level_"+item_index+"_cat_list")?eval as secondLevelItem>
                                            <tr>
                                                <th width="120"><span><a href="/list/${secondLevelItem.id?c}">${secondLevelItem.title!''}</a></span></th>
                                                <td>
                                                    <#if ("third_level_"+item_index+secondLevelItem_index+"_cat_list")?eval?? >
                                                        <#list ("third_level_"+item_index+secondLevelItem_index+"_cat_list")?eval as thirdLevelItem>
                                                            <a href="/list/${thirdLevelItem.id?c}">${thirdLevelItem.title!''}</a>
                                                        </#list>
                                                    </#if>
                                                </td>
                                            </tr>
                                        </#list>
                                    </table>
                                </#if>
                                <div class="clear"></div>
                            </div>
                        </li>
                    </#list>
                </#if>
            </ul><!--navlistout END-->
        </section>
        <#if navi_item_list??>
            <#list navi_item_list as item>
                <a class="a1" href="${item.linkUri!''}">${item.title!''}</a>
            </#list>
        </#if> 
    </div> 
</nav>
<div class="clear"></div>
<aside class="floatbox" id="floatbox">
    <a class="a0" href="javascript:;" title="微信客服">
        <img src="/client/images/float_ico01.png" width="42" height="42" alt="微信客服" />
        <span>
            <img src="${site.wxQrCode!''}" width="84" height="84"/>
        </span>
    </a>
    
    <a href="javascript:;" title="在线咨询" id="floatqq"><img src="/client/images/float_ico02.png" width="42" height="42" alt="在线咨询" /></a>
    
    <a class="a0" href="javascript:;" title="新浪微博">
        <img src="/client/images/float_ico03.png" width="42" height="42" alt="新浪微博" />
        <span>
            <img src="${site.weiboQrCode!''}" width="84" height="84"/>
        </span>
    </a>
    <a class="a0" class="a0" href="javascript:showSuggestion();" title="投诉">
        <img src="/client/images/float_ico04.png" width="42" height="42" alt="服务热线"/>
    </a>
    <a class="a0" href="javascript:move();" title="到顶部">
        <img src="/client/images/float_ico05.png" width="42" height="42" alt="到顶部" />
    </a>
    
    <div class="floatboxlist" id="floatboxlist">
    <a href="<#if site.qq1??>http://wpa.qq.com/msgrd?v=3&uin=${site.qq1!''}&site=qq&menu=yes<#else>#</#if>">联系客户1号</a>
    <a href="<#if site.qq2??>http://wpa.qq.com/msgrd?v=3&uin=${site.qq2!''}&site=qq&menu=yes<#else>#</#if>">联系客户2号</a>
    <a href="<#if site.qq3??>http://wpa.qq.com/msgrd?v=3&uin=${site.qq3!''}&site=qq&menu=yes<#else>#</#if>">联系客户3号</a>
    <a href="<#if site.qq4??>http://wpa.qq.com/msgrd?v=3&uin=${site.qq4!''}&site=qq&menu=yes<#else>#</#if>">联系客户4号</a>
  </div>
    
</aside>



<div class="main">
    <section class="index_right">
        <menu class="threeshop">
            <a href="/shop/list"><img src="/client/images/images/index_ico01.png" /><p>昆明同盟店</p></a>
            <a href="/shop/list"><img src="/client/images/images/index_ico01.png" /><p>地州同盟店</p></a>
            <a href="/shop/list"><img src="/client/images/images/index_ico01.png" /><p>省外同盟店</p></a>
        </menu>
        <p class="index_qd">
            <a href="javascript:;"><img src="/client/images/images/index_ico04.png" /></a>
            ${.now?string("yyyy.MM.dd EEEE")}
        </p>
        <a class="index_free" href="/coupon/list">免费洗车券</a>
        <a class="index_free" href="/coupon/list">免费打蜡券</a>
        <a class="index_free" href="/coupon/list">200元优惠券</a>
        <div class="index_ask">          
            <a class="fl" href="javascript:;">免费询价</a>
            <a class="fr" href="javascript:;">免费安装</a>
            <div class="clear"></div>
        </div>
        <h3 class="lh30 fs16 w100">商城快报<a href="/info/list/10?catId=9" class="absolute-r fw400 fs14">更多 ></a></h3>
        <#if news_page??>
            <#list news_page.content as item>
                <#if item_index < 7>
                <a class="block mt5" href="/info/content/${item.id?c}?mid=12" style="height:20px; overflow:hidden;">${item.title!''}</a>
                </#if>
            </#list>
        </#if>
    </section>
  
    <section class="index_topad">
        <a id="indexadpre" href="javascript:void(0);">上一页</a>
        <a id="indexadnext" href="javascript:void(0);">下一页</a>
        <div id="indexadbox">
            <ul id="indexadsum">
                <li id="threeli01">
                    <#if big_scroll_ad_list??>
                        <#list big_scroll_ad_list as item>
                            <a class="indexadpart" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                                <img src="${item.fileUri!''}" width="758" height="282" />
                            </a>
                        </#list>
                    </#if>
                </li>
                <li id="threeli02"></li>
            </ul>
            <div style="clear:both;"></div>
        </div>
        <div class="index_topcheck" id="topcheck">
            <#if top_category_list??>
                <#list top_category_list as item>
                    <a <#if item_index==0>class="sel"</#if> href="/list/${item.id?c}">${item.title!''}</a>
                </#list>
            </#if>
            <div class="clear"></div>
        </div>
        <ul class="index_topsum" id="topsum">
            <#if top_category_list??>
                <#list top_category_list as e>
                    <li>
                        <#if ("top_cat_goods_page"+e_index)?eval??>
                            <#list ("top_cat_goods_page"+e_index)?eval.content as item>
                                <a href="/goods/${item.id?c}">
                                      <p class="lh30 fs16" style="overflow: hidden; width: 230px; height: 30px;">${item.title!''}</p>
                                      <p class="ta-r c9" style="overflow: hidden; width: 228px; height: 21px; text-align: left;">${item.subTitle!''}</p>
                                      <p class="ta-r sc">￥：<#if item.salePrice??>${item.salePrice?string("0.00")}<#else>0.00</#if></p>
                                      <img src="${item.coverImageUri!''}" height="100" />
                                </a>
                            </#list>
                        </#if>
                    </li>
                </#list>
            </#if>
        </ul>
    </section>
    
    <section class="index_topleft">
        <#if cat_bottom_ad_list??>
            <#list cat_bottom_ad_list as item>
                <a <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                    <img src="${item.fileUri!''}" width="201" height="61"/>
                </a>
                <#break>
            </#list>
        </#if>
    </section>
    
    <div class="clear h30"></div>
  
    <section class="indextit">
        <h3>1元抢拍</h3>
        <a class="a1" href="/info/content/39?mid=12">抢拍细则</a>
        <menu id="miao-menu">
            <a class="sel" href="javascript:;" onmouseenter="javascript:miaoPageChang(0);">今日抢拍</a>
            <a href="javascript:;" onmouseenter="javascript:miaoPageChang(1);">明日抢拍</a>
            <a href="javascript:;" onmouseenter="javascript:miaoPageChang(2);">往期回顾</a>
        </menu>
        <div class="clear"></div>
    </section>
    
    <ul id="miao-ul" class="index_knock">
        <li>
            <menu>
                <div id="miaoClockDiv">
                    <a class="sel" href="javascript:miaoClockChang(0);">10:00抢拍<i></i></a>
                    <a href="javascript:miaoClockChang(1);">14:00抢拍<i></i></a>
                    <a href="javascript:miaoClockChang(2);">20:00抢拍<i></i></a>
                </div>
                <a href="/info/list/12?catId=24">抢拍细则</a>
            </menu>
            <div class="knockbox miaocur">
                <dl>
                    <#if miao_cur_8_page??>
                        <#list miao_cur_8_page.content as item>
<script>
$(document).ready(function(){
     
    
    <#if item.flashSaleStartTime gt .now>
        $("#timeLeft8${item_index}").html("尚未开始");
    <#elseif item.flashSaleStopTime < .now>
        $("#timeLeft8${item_index}").html("已经结束");
    <#else>
        setInterval("timer8${item_index}()",1000);
        priceSearch${item_index}("priceid${item_index}","search${item_index}","xxx","yyy");
    </#if>
});

function timer8${item_index}()
{
<#if item.isFlashSale && item.flashSaleStartTime < .now && item.flashSaleStopTime gt .now>
    var ts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")})) - (new Date());//计算剩余的毫秒数
                
    var allts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")}))
               - (new Date(${item.flashSaleStartTime?string("yyyy")}, 
                parseInt(${item.flashSaleStartTime?string("MM")}, 10)-1, 
                ${item.flashSaleStartTime?string("dd")}, 
                ${item.flashSaleStartTime?string("HH")}, 
                ${item.flashSaleStartTime?string("mm")}, 
                ${item.flashSaleStartTime?string("ss")}));//总共的毫秒数
                
    if (0 == ts)
    {
        window.location.reload();
    }
    
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#timeLeft8${item_index}").html(dd+"天"+hh+":"+mm+":"+ss);
    
    var price = ${item.flashSalePrice?string("0.00")} * ts / allts;
    
    // var s_x = Math.round(price).toString();
    var s_x = price.toFixed(2).toString();
    
    var pos_decimal = s_x.indexOf('.');
    
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    
    $("#flashPrice8${item_index}").html(s_x);
                    
</#if>
}

//lichong 价格实时查询
function priceSearch${item_index}(priceid,boxid,x,y){
    var price_box = $("#"+priceid);
    var _search = $("#"+boxid);
    var _progress_bar = _search.find("i");
    
    
    var _search_y = 250;
    <#if item.flashSalePrice??>
    var price = ${item.flashSalePrice?c};
    
    </#if>   
    var allts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")}))
               - (new Date(${item.flashSaleStartTime?string("yyyy")}, 
                parseInt(${item.flashSaleStartTime?string("MM")}, 10)-1, 
                ${item.flashSaleStartTime?string("dd")}, 
                ${item.flashSaleStartTime?string("HH")}, 
                ${item.flashSaleStartTime?string("mm")}, 
                ${item.flashSaleStartTime?string("ss")}));//总共的毫秒数      
                
    _search.hover(function(){           
          },function(){
              _progress_bar.css({"width":"50%px","display":"block","height":"7px","border-radius":"4px","background":"#019ad3"});
              price_box.css({"display":"none"})
              });
              
    _search.mousemove(function(e){
            var _nowX = e.pageX - _search.offset().left;
            var _nowY = e.pageY - _search.offset().top;
            var newprice = price - (price/250) * _nowX;
            var search_time = (allts/250) * _nowX; //查询位置毫秒数
            var dd = parseInt(search_time / 1000 / 60 / 60 / 24, 10);//计算天数
            var hh = parseInt(search_time / 1000 / 60 / 60 % 24, 10);//计算小时数
            var mm = parseInt(search_time / 1000 / 60 % 60, 10);//计算分钟数
            var ss = parseInt(search_time / 1000 % 60, 10);//计算秒数
            hh = (hh + 10) % 24;
            dd = checkTime(dd);
            hh = checkTime(hh);
            mm = checkTime(mm);
            ss = checkTime(ss);
            
            $("#priceid${item_index}").html("<p>"+"时间："
                                            +hh+":"+mm
                                            +"</p>"
                                            +"<p>"+"价格："+parseInt(newprice)
                                            +"</p>");
            _progress_bar.css({"width":_nowX+"px","display":"block","height":"7px","border-radius":"4px","background":"#019ad3"});
            if(_nowX > 195){
                _nowX = 195;
            }
            price_box.css({"display":"inline-block","padding":"5px","border":"1px solid #ddd","position":"absolute","top":"-65px","left":_nowX-25+"px","font-size":"12px"});
    });
    
}
</script>
                            <#if item_index==0>
                                <dt>

                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span id="timeLeft8${item_index}" class="sc fw-b fs20">00:00:00</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="/goods/${item.id?c}?qiang=1">￥<b id="flashPrice8${item_index}"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime" id="search${item_index}">
                                        <a>实时查询</a>
                                        <i></i> 
                                        <div id="priceid${item_index}">
                                            <p>鼠标放到进度条试试</p>
                                        </div>                                       
                                    </div>
                                                                     
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;" >${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
                <dl style="display:none;">
                    <#if miao_cur_15_page??>
                        <#list miao_cur_15_page.content as item>
<script>
$(document).ready(function(){
    <#if item.flashSaleStartTime gt .now>
        $("#timeLeft15${item_index}").html("尚未开始");
    <#elseif item.flashSaleStopTime < .now>
        $("#timeLeft15${item_index}").html("已经结束");
    <#else>
        setInterval("timer15${item_index}()",1000);
        priceSearch1${item_index}("priceid1${item_index}","search1${item_index}","xxx","yyy");
    </#if>
});

function timer15${item_index}()
{
<#if item.isFlashSale && item.flashSaleStartTime < .now && item.flashSaleStopTime gt .now>
    var ts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")})) - (new Date());//计算剩余的毫秒数
                
    var allts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")}))
               - (new Date(${item.flashSaleStartTime?string("yyyy")}, 
                parseInt(${item.flashSaleStartTime?string("MM")}, 10)-1, 
                ${item.flashSaleStartTime?string("dd")}, 
                ${item.flashSaleStartTime?string("HH")}, 
                ${item.flashSaleStartTime?string("mm")}, 
                ${item.flashSaleStartTime?string("ss")}));//总共的毫秒数
                
    if (0 == ts)
    {
        window.location.reload();
    }
    
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#timeLeft15${item_index}").html(dd+"天"+hh+":"+mm+":"+ss);
    
    var price = ${item.flashSalePrice?string("0.00")} * ts / allts;
    
    //var s_x = Math.round(price).toString();
    var s_x = price.toFixed(2).toString();
    
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    
    $("#flashPrice15${item_index}").html(s_x);
                    
</#if>
}

//lichong 价格实时查询
function priceSearch1${item_index}(priceid,boxid,x,y){
    var price_box = $("#"+priceid);
    var _search = $("#"+boxid);
    var _progress_bar = _search.find("i");
    
    
    var _search_y = 250;
    <#if item.flashSalePrice??>
    var price = ${item.flashSalePrice?c};
    
    </#if>   
    var allts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")}))
               - (new Date(${item.flashSaleStartTime?string("yyyy")}, 
                parseInt(${item.flashSaleStartTime?string("MM")}, 10)-1, 
                ${item.flashSaleStartTime?string("dd")}, 
                ${item.flashSaleStartTime?string("HH")}, 
                ${item.flashSaleStartTime?string("mm")}, 
                ${item.flashSaleStartTime?string("ss")}));//总共的毫秒数      
                
    _search.hover(function(){
            
          },function(){
              _progress_bar.css({"width":"50%px","display":"block","height":"7px","border-radius":"4px","background":"#019ad3"});
              price_box.css({"display":"none"})
              });
              
    _search.mousemove(function(e){
            var _nowX = e.pageX - _search.offset().left;
            var _nowY = e.pageY - _search.offset().top;
            var newprice = price - (price/250) * _nowX;
            var search_time = (allts/250) * _nowX; //查询位置毫秒数
            var dd = parseInt(search_time / 1000 / 60 / 60 / 24, 10);//计算天数
            var hh = parseInt(search_time / 1000 / 60 / 60 % 24, 10);//计算小时数
            var mm = parseInt(search_time / 1000 / 60 % 60, 10);//计算分钟数
            var ss = parseInt(search_time / 1000 % 60, 10);//计算秒数
            hh = (hh + 14) % 24;
            dd = checkTime(dd);
            hh = checkTime(hh);
            mm = checkTime(mm);
            ss = checkTime(ss);
            $("#priceid1${item_index}").html("<p>"+"时间："
                                            +hh+":"+mm
                                            +"</p>"
                                            +"<p>"+"价格："+parseInt(newprice)
                                            +"</p>");
            _progress_bar.css({"width":_nowX+"px","display":"block","height":"7px","border-radius":"4px","background":"#019ad3"});
            if(_nowX > 195){
                _nowX = 195;
            }
            price_box.css({"display":"inline-block","padding":"5px","border":"1px solid #ddd","position":"absolute","top":"-65px","left":_nowX-25+"px","font-size":"12px"});
    });
}

</script>
                            <#if item_index==0>
                                <dt>

                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span id="timeLeft15${item_index}" class="sc fw-b fs20">00:00:00</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="/goods/${item.id?c}?qiang=1">￥<b id="flashPrice15${item_index}"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime" id="search1${item_index}">
                                        <a>实时查询</a>
                                        <i></i> 
                                        <div id="priceid1${item_index}">
                                            <p>鼠标放到进度条试试</p>
                                        </div>                                       
                                    </div>
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;" >${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
                <dl style="display: none;">
                    <#if miao_cur_23_page??>
                        <#list miao_cur_23_page.content as item>
<script>
$(document).ready(function(){
    <#if item.flashSaleStartTime gt .now>
        $("#timeLeft23${item_index}").html("尚未开始");
    <#elseif item.flashSaleStopTime < .now>
        $("#timeLeft23${item_index}").html("已经结束");
    <#else>
        setInterval("timer23${item_index}()",1000);
        priceSearch2${item_index}("priceid2${item_index}","search2${item_index}","xxx","yyy");
    </#if>
});

function timer23${item_index}()
{
<#if item.isFlashSale && item.flashSaleStartTime < .now && item.flashSaleStopTime gt .now>
    var ts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")})) - (new Date());//计算剩余的毫秒数
                
    var allts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")}))
               - (new Date(${item.flashSaleStartTime?string("yyyy")}, 
                parseInt(${item.flashSaleStartTime?string("MM")}, 10)-1, 
                ${item.flashSaleStartTime?string("dd")}, 
                ${item.flashSaleStartTime?string("HH")}, 
                ${item.flashSaleStartTime?string("mm")}, 
                ${item.flashSaleStartTime?string("ss")}));//总共的毫秒数
                
    if (0 == ts)
    {
        window.location.reload();
    }
    
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#timeLeft23${item_index}").html(dd+"天"+hh+":"+mm+":"+ss);
                    
    var price = ${item.flashSalePrice?string("0.00")} * ts / allts;
    
    // var s_x = Math.round(price).toString();
    var s_x = price.toFixed(2).toString();
    
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    
    $("#flashPrice23${item_index}").html(s_x);
</#if>
}

//lichong 价格实时查询
function priceSearch2${item_index}(priceid,boxid,x,y){
    var price_box = $("#"+priceid);
    var _search = $("#"+boxid);
    var _progress_bar = _search.find("i");
    
    
    var _search_y = 250;
    <#if item.flashSalePrice??>
    var price = ${item.flashSalePrice?c};
    
    </#if>   
    var allts = (new Date(${item.flashSaleStopTime?string("yyyy")}, 
                parseInt(${item.flashSaleStopTime?string("MM")}, 10)-1, 
                ${item.flashSaleStopTime?string("dd")}, 
                ${item.flashSaleStopTime?string("HH")}, 
                ${item.flashSaleStopTime?string("mm")}, 
                ${item.flashSaleStopTime?string("ss")}))
               - (new Date(${item.flashSaleStartTime?string("yyyy")}, 
                parseInt(${item.flashSaleStartTime?string("MM")}, 10)-1, 
                ${item.flashSaleStartTime?string("dd")}, 
                ${item.flashSaleStartTime?string("HH")}, 
                ${item.flashSaleStartTime?string("mm")}, 
                ${item.flashSaleStartTime?string("ss")}));//总共的毫秒数      
                
    _search.hover(function(){
            
          },function(){
              _progress_bar.css({"width":"50%px","display":"block","height":"7px","border-radius":"4px","background":"#019ad3"});
              price_box.css({"display":"none"})
              });
    
    _search.mousemove(function(e){
            var _nowX = e.pageX - _search.offset().left;
            var _nowY = e.pageY - _search.offset().top;
            var newprice = price - (price/250) * _nowX;
            var search_time = (allts/250) * _nowX; //查询位置毫秒数
            var dd = parseInt(search_time / 1000 / 60 / 60 / 24, 10);//计算天数
            var hh = parseInt(search_time / 1000 / 60 / 60 % 24, 10);//计算小时数
            var mm = parseInt(search_time / 1000 / 60 % 60, 10);//计算分钟数
            var ss = parseInt(search_time / 1000 % 60, 10);//计算秒数
            hh = (hh + 20) % 24;
            dd = checkTime(dd);
            hh = checkTime(hh);
            mm = checkTime(mm);
            ss = checkTime(ss);
            $("#priceid2${item_index}").html("<p>"+"时间："
                                            +hh+":"+mm
                                            +"</p>"
                                            +"<p>"+"价格："+parseInt(newprice)
                                            +"</p>");
            _progress_bar.css({"width":_nowX+"px","display":"block","height":"7px","border-radius":"4px","background":"#019ad3"});
            if(_nowX > 195){
                _nowX = 195;
            }
            price_box.css({"display":"inline-block","padding":"5px","border":"1px solid #ddd","position":"absolute","top":"-65px","left":_nowX-25+"px","font-size":"12px"});
    });
}
</script>
                            <#if item_index==0>
                                <dt>
                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span id="timeLeft23${item_index}" class="sc fw-b fs20">00:00:00</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="/goods/${item.id?c}?qiang=1">￥<b id="flashPrice23${item_index}"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime" id="search2${item_index}">
                                        <a>实时查询</a>
                                        <i></i> 
                                        <div id="priceid2${item_index}">
                                            <p>鼠标放到进度条试试</p>
                                        </div>                                       
                                    </div>
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;" >${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
            </div>
            <div class="clear"></div>
        </li>
        <li style="display: none;">
            <menu>
                <div id="miaoClockNextDiv">
                    <a class="sel" href="javascript:miaoClockNextChange(0);">10:00抢拍<i></i></a>
                    <a href="javascript:miaoClockNextChange(1);">14:00抢拍<i></i></a>
                    <a href="javascript:miaoClockNextChange(2);">20:00抢拍<i></i></a>
                </div>
                <a href="/info/list/12?catId=24">抢拍细则</a>
            </menu>
            <div class="knockbox miaonext">
                <dl>
                    <#if miao_next_8_page??>
                        <#list miao_next_8_page.content as item>
                            <#if item_index==0>
                                <dt>

                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span class="sc fw-b fs20">尚未开始</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="javascript:;">￥<b><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime">
                                        <a>实时查询</a>
                                        <i></i>
                                        <div>
                                            <p>时间：</p>
                                            <p>价格：</p>
                                        </div>
                                    </div>
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;" >${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
                <dl style="display: none;">
                    <#if miao_next_15_page??>
                        <#list miao_next_15_page.content as item>
                            <#if item_index==0>
                                <dt>
                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span class="sc fw-b fs20">尚未开始</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="javascript:;">￥<b><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime">
                                        <a>实时查询</a>
                                        <i></i>
                                        <div>
                                            <p>时间：</p>
                                            <p>价格：</p>
                                        </div>
                                    </div>
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;" >${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
                <dl style="display: none;">
                    <#if miao_next_23_page??>
                        <#list miao_next_23_page.content as item>
                            <#if item_index==0>
                                <dt>
                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span class="sc fw-b fs20">尚未开始</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="javascript:;">￥<b><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime">
                                        <a>实时查询</a>
                                        <i></i>
                                        <div>
                                            <p>时间：</p>
                                            <p>价格：</p>
                                        </div>
                                    </div>
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;" >${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
            </div>
            <div class="clear"></div>
        </li>
        <li style="display: none;">
            <menu>
                <div id="miaoClockPrevDiv">
                    <a class="sel" href="javascript:miaoClockPrevChange(0);">10:00抢拍<i></i></a>
                    <a href="javascript:miaoClockPrevChange(1);">14:00抢拍<i></i></a>
                    <a href="javascript:miaoClockPrevChange(2);">20:00抢拍<i></i></a>
                </div>
                <a href="/info/list/12?catId=24">抢拍细则</a>
            </menu>
            <div class="knockbox miaoprev">
                <dl>
                    <#if miao_prev_8_page??>
                        <#list miao_prev_8_page.content as item>
                            <#if item_index==0>
                                <dt>
                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span class="sc fw-b fs20">已经结束</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="javascript:;">￥<b><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime">
                                        <a>实时查询</a>
                                        <i></i>
                                        <div>
                                            <p>时间：</p>
                                            <p>价格：</p>
                                        </div>
                                    </div>
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;" >${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
                <dl style="display: none;">
                    <#if miao_prev_15_page??>
                        <#list miao_prev_15_page.content as item>
                            <#if item_index==0>
                                <dt>

                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span class="sc fw-b fs20">已经结束</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="javascript:;">￥<b><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime">
                                        <a>实时查询</a>
                                        <i></i>
                                        <div>
                                            <p>时间：</p>
                                            <p>价格：</p>
                                        </div>
                                    </div>
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;" >${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
                <dl style="display: none;">
                    <#if miao_prev_23_page??>
                        <#list miao_prev_23_page.content as item>
                            <#if item_index==0>
                                <dt>
                                    <a class="pro" href="/goods/${item.id?c}?qiang=1"><img class="pro" src="${item.coverImageUri!''}" width="200" height="200"/></a>
                                    <a class="fs18 lh25 fw-b" style="display: block; width: 500px; height: 27px; overflow: hidden;"  href="/goods/${item.id?c}?qiang=1">${item.title!''}</a>
                                    <p class="c9 pt10 pb20" style="width: 490px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>

                                    <p class="c9 pt20 lh30">剩余时间：<span class="sc fw-b fs20">已经结束</span><span class="rd3 ml20">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    <a class="a1" href="javascript:;">￥<b><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></b>
                                        <span>￥：<#if item.salePrice??>${item.salePrice?string("0.00")}</#if></span></a>
                                    <div class="qgtime">
                                        <a>实时查询</a>
                                        <i></i>
                                        <div>
                                            <p>时间：</p>
                                            <p>价格：</p>
                                        </div>
                                    </div>
                                </dt>
                            <#else>
                                <dd>
                                    <a href="/goods/${item.id?c}?qiang=1">
                                        <img src="${item.coverImageUri!''}" width="110" height="110"/>
                                        <p class="fs18 lh25 fw-b" style="display: block; width: 250px; height: 27px; overflow: hidden;"  style="display: block; width: 500px; height: 27px; overflow: hidden;">${item.title!''}</p>
                                        <p class="c9 pb20" style="width: 240px; height: 3px; overflow: hidden;">${item.subTitle!''}</p>
                                        <p class="sc pt20 lh30">￥<span class="fw-b fs20"><#if item.flashSalePrice??>${item.flashSalePrice?string("0.00")}</#if></span></p>
                                        <p><span class="rd3">${item.flashSaleSoldNumber!'0'}</span>人参与</p>
                                    </a>
                                </dd>
                            </#if>
                        </#list>
                    </#if>
                </dl>
            </div>
            <div class="clear"></div>
        </li>
    </ul>
    
    <div class="clear h30"></div>
    
    <section class="indextit">
        <h3>十人团购</h3>
        <a class="a1" href="/info/content/40?mid=12">团购细则</a>
        <menu id="tuan-menu">
            <a class="sel" href="javascript:;" onmouseenter="javascript:tuanPageChang(0);">本期团购</a>
            <a href="javascript:;" onmouseenter="javascript:tuanPageChang(1);">下期预告</a>
            <a href="javascript:;" onmouseenter="javascript:tuanPageChang(2);"">往期团购</a>
        </menu>
        <div class="clear"></div>
    </section>
    <ul id="tuan-ul">
        <li style="width: 100%; height: 350px;">
            <#if tuan_cur_page??>
                <#list tuan_cur_page.content as item>
                    <div class="index_team">
                        <a class="block ta-c" href="/goods/${item.id?c}?qiang=<#if item.isGroupSaleHundred?? && item.isGroupSaleHundred>100<#else>3</#if>">
                            <img src="${item.coverImageUri!''}" height="200" width="200"/>
                        </a>
                        <a class="block mt5 fs16" href="/goods/${item.id?c}?qiang=<#if item.isGroupSaleHundred?? && item.isGroupSaleHundred>100<#else>3</#if>" style="overflow: hidden;height: 20px;">${item.title!''}</a>
                        <p class="fs12 blue" style="overflow: hidden;height: 20px;">${item.subTitle!''}</p>
                        <div class="team_men">
                            <dl>
                                <dt style="width: <#if item.groupSaleSoldNumber??><#if item.groupSaleSoldNumber/10 gt 1>100<#else>${item.groupSaleSoldNumber * 10}</#if><#else>0</#if>%;"></dt>
                                <#--<dd class="sel"></dd>-->
                                <dd style="left:66%;"></dd>
                            </dl>
                            <p>
                                <span><#if item.groupSaleThreePrice??>￥${item.groupSaleThreePrice?string(0.00)}</#if></span>
                                三人团
                            </p>
                            <p>
                                <span><#if item.groupSaleSevenPrice??>￥${item.groupSaleSevenPrice?string(0.00)}</#if></span>
                                五人团
                            </p>
                            <p>
                                <span><#if item.groupSaleTenPrice??>￥${item.groupSaleTenPrice?string(0.00)}</#if></span>
                                十人团
                            </p>
                            <div class="clear"></div>
                        </div>
                        <p class="p1">
                            预付定金:<#if item.groupSalePrice??>￥${item.groupSalePrice?string(0.00)}</#if>元
                            <a href="/order/buy/tentuan?gid=${item.id?c}">立即参加</a>
                        </p>
                    </div>
                </#list>
            </#if>
        </li>
        <li style="width: 100%; height: 350px; display: none;">
            <#if tuan_next_page??>
                <#list tuan_next_page.content as item>
                    <div class="index_team">
                        <a class="block ta-c" href="/goods/${item.id?c}?qiang=<#if item.isGroupSaleHundred?? && item.isGroupSaleHundred>100<#else>3</#if>">
                            <img src="${item.coverImageUri!''}" height="200" width="200"/>
                        </a>
                        <a class="block mt5 fs16" href="/goods/${item.id?c}?qiang=<#if item.isGroupSaleHundred?? && item.isGroupSaleHundred>100<#else>3</#if>" style="overflow: hidden;height: 20px;">${item.title!''}</a>
                        <p class="fs12 blue" style="overflow: hidden;height: 20px;">${item.subTitle!''}</p>
                        <div class="team_men">
                            <dl>
                                <dt style="width: 0%"></dt>
                                <dd style="left:66%;"></dd>
                            </dl>
                            <p>
                                <span><#if item.groupSaleThreePrice??>￥${item.groupSaleThreePrice?string(0.00)}</#if></span>
                                三人团
                            </p>
                            <p>
                                <span><#if item.groupSaleSevenPrice??>￥${item.groupSaleSevenPrice?string(0.00)}</#if></span>
                                五人团
                            </p>
                            <p>
                                <span><#if item.groupSaleTenPrice??>￥${item.groupSaleTenPrice?string(0.00)}</#if></span>
                                十人团
                            </p>
                            <div class="clear"></div>
                        </div>
                        <p class="p1">
                            预付定金:<#if item.groupSalePrice??>￥${item.groupSalePrice?string(0.00)}</#if>元
                            <a href="/goods/${item.id?c}?qiang=3">查看详情</a>
                        </p>
                    </div>
                </#list>
            </#if>
        </li>
        <li style="width: 100%; height: 350px; display: none;">
            <#if tuan_prev_page??>
                <#list tuan_prev_page.content as item>
                    <div class="index_team">
                        <a class="block ta-c" href="/goods/${item.id?c}?qiang=<#if item.isGroupSaleHundred?? && item.isGroupSaleHundred>100<#else>3</#if>">
                            <img src="${item.coverImageUri!''}" height="200" width="200"/>
                        </a>
                        <a class="block mt5 fs16" href="/goods/${item.id?c}?qiang=<#if item.isGroupSaleHundred?? && item.isGroupSaleHundred>100<#else>3</#if>" style="overflow: hidden;height: 20px;">${item.title!''}</a>
                        <p class="fs12 blue" style="overflow: hidden;height: 20px;">${item.subTitle!''}</p>
                        <div class="team_men">
                            <dl>
                                <dt style="width: 100%"></dt>
                                <dd style="left:66%;"></dd>
                            </dl>
                            <p>
                                <span><#if item.groupSaleThreePrice??>￥${item.groupSaleThreePrice?string(0.00)}</#if></span>
                                三人团
                            </p>
                            <p>
                                <span><#if item.groupSaleSevenPrice??>￥${item.groupSaleSevenPrice?string(0.00)}</#if></span>
                                五人团
                            </p>
                            <p>
                                <span><#if item.groupSaleTenPrice??>￥${item.groupSaleTenPrice?string(0.00)}</#if></span>
                                十人团
                            </p>
                            <div class="clear"></div>
                        </div>
                        <p class="p1">
                            预付定金:<#if item.groupSalePrice??>￥${item.groupSalePrice?string(0.00)}</#if>元
                            <a href="/goods/${item.id?c}?qiang=3">查看详情</a>
                        </p>
                    </div>
                </#list>
            </#if>
        </li>
    </ul>
        
    <div class="clear h30"></div> 
    
  <#-- 车友还想团购 -->
  <div id="demand-div" class="mianfeilingqutanchu2" style="display:none;">
    <div class="mianfeilingqutanchu_dl2"> 
        <div class="main bgff">
            <div class="tousuyemian2">
                <div class="rightb_gundong2 fr" >
                    <a href="javascript:hideDemand();"><img src="/client/images/20150407114113116_easyicon_net_71.8756476684.png" width="21" height="21" /></a>
                </div>
                  <form id="demandForm" action="/demand/add">
                      <div class="clear"></div>
                      <h3>团购商品申请</h3>
                      <p>如果您有中意的商品，您可以在这个页面申请将其加入到新的团购中！同时您的留言还将可能以匿名的方式显示在首页，描述您中意的商品，让大家一起来团购吧！我们将根据反馈情况增加新的团购商品。您的满意是我们最大的动力，谢谢！</p>
                      <div class="tousuneirong">
                          <div class="clear h10"></div>
                          <span><b class="red">*</b>期望加入团购的商品</span>
                          <div class=" clear"></div>
                          <textarea class="input  Validform_error" style="width:720px;" onfocus="if(value=='') {value=''}" onblur="if (value=='') {value=''}"  value="" datatype="*1-55" nullmsg="请输入内容" id="demandContent" name="content"></textarea>
                          
                      </div>
                      <div class="tousulianxifangshi mt20">
                          <p>为了尽快为您解决问题，请提供您的联系方式，谢谢。</p>
                          <div> 
                              <span><b class="red">*</b>称呼</span>                                               
                              <input name="name" value="" datatype="*1-20" nullmsg="请输入您的称呼" class="input ml20" type="text">                                         
                          </div>
                          <div class="clear h10"></div>
                          <div>
                              <span>邮箱&nbsp;&nbsp;</span>
                              <input name="mail"   value="" datatype="*0-250" nullmsg="请输入正确的邮箱地址" class="input ml20" type="text">
                          </div>
                          <div class="clear h10"></div>
                          <div>
                              <span><b class="red">*</b>手机</span>
                              <input name="mobile"   value="" datatype="m" nullmsg="请输入您的手机号码" class="input ml20" type="text">                        
                          </div>
                          <div class="clear h20"></div>
                          <input class="Message_an" type="submit" value="提交" title="提交" />
                      </div>
                   </form> 
            </div>
        </div>
    
        <div class="clear"></div> 
    </div>
</div>
  
    <section class="teambanner">
        <div id="teamadsum">
            <#if mid_scroll_ad_list??>
                <#list mid_scroll_ad_list as item>
                    <#if item_index < 7>
                        <a <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                            <img src="${item.fileUri!''}" width="608" height="332" />
                        </a>
                    </#if>
                </#list>
            </#if>
        </div>
        <menu id="teamadmenu">
            <#if mid_scroll_ad_list??>
                <#list mid_scroll_ad_list as item>
                    <#if item_index < 7>
                        <a <#if item_index==0>class="sel"</#if> href="javascript:;">${item.title!''}</a>
                    </#if>
                </#list>
            </#if>
        </menu>
    </section>
    
    <section class="index_gz">
        <h3>百人团购
            <a class="a1" style="font-size:14px; margin-left:12px;" href="/info/content/57?mid=12">团购细则</a>    
        </h3>
            <#if baituan_cur_page??>
                <#list baituan_cur_page.content as item>
                    <p>
                        <a href="/goods/${item.id?c}?qiang=100" style="width: 320px; overflow: hidden; display: block; height: 25px; ">${item.title!''}</a>
                        <span class="c9">低于市场价${((item.salePrice - item.groupSaleHundredPrice)/item.salePrice*100)?string("0")}%</span>
                        <span class="sp1">${item.totalCollects!'0'}人关注</span>
                    </p>
                    <b><i></i></b>
                </#list>
            </#if>
    </section>
    
    <section class="index_mt">
        <h3>车友还想团购</h3>
        <#if demand_list??>

           <#assign n = 0>
               
               <#list demand_list?sort_by(["time"])?reverse as show>  
                   <#if show_index < 4>                    
                          <p class="pt15">网友<span style="color:red;">${show.name!''}</span>留言：</p>
                          <p style="text-indent:2em;">${show.content!''}</p>
                          <#assign n = n + 1>    
                   </#if>
               </#list>
              
        </#if>        
       <#--
        <p class="pt15">网友123*****34留言：</p>
        <p>别问我，我是内容文字。别问我，我是内容文字。别问我，我是内容文字。别问我，我是内容文字。别问我，我是内容文字。</p>
        <p class="pt15">网友123*****34留言：</p>
        <p>别问我，我是内容文字。别问我，我是内容文字。别问我，我是内容文字。别问我，我是内容文字。别问我，我是内容文字。</p>
       -->

        <#-- 增加链接的js函数showDemand();   zhangji -->        
        <a href="javascript:showDemand();"><img src="/client/images/images/index_ico09.png" /></a>   
    </section>
    
    <div class="clear h20"></div> 
    <#if mid_big_ad_list??>
        <#list mid_big_ad_list as item>
            <a <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                <img src="${item.fileUri!''}" width="1200" height="137" />
            </a>
            <#break>
        </#list>
    </#if>
    <div class="clear h30"></div> 
  
    <section class="indextit">
        <div class="lefta_gundong fl">
            <a id="onebox3Left" href="javascript:void(0);"><img src="/client/images/20150330051733397_easyicon_net_125.465346535.png" width="30" height="50" /></a>
        </div>
        <div class="righta_gundong fr">
            <a id="onebox3Right" href="javascript:void(0);"><img src="/client/images/20150408033704325_easyicon_net_121.900990099.png" width="30" height="50" /></a>
        </div>
        <h3>商品分类</h3>
        <div class="clear"></div>
    </section>
  
    <a id="shopadpre" href="javascript:void(0);">上一页</a>
    <a id="shopadnext" href="javascript:void(0);">下一页</a>
    <div id="shopadbox">
        <ul id="shopadsum">
            <li id="shopli01">
                <div id="onebox3">
                    <div id="onesum3">
                <#if type_scroll_ad_list??>
                    <#list type_scroll_ad_list as item>
                        <#if item_index < 3>
                            <a class="onepart3" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                                <img src="${item.fileUri!''}" width="335" height="323"/>
                            </a>
                        </#if>
                    </#list>
                </#if>
                    </div>
                </div>
            </li>
            <li id="shopli02"></li>
        </ul>
        <div style="clear:both;"></div>
    </div>
    <table class="propart">
        <tr>
            <#if top_category_list??>
                <#list top_category_list as item>
                    <#if item_index < 5>
                        <#-- 增加id @by zhangji -->
                        <td class="shadow_add">    
                         <#--  @by zhangji -->
                            <a href="/list/${item.id?c}"><img src="${item.imgUrl!''}" width="68" height="68"/>
                                <p>${item.title!''}</p>
                            </a>
                        </td>
                    </#if>
                </#list>
            </#if>
        </tr>
        <tr>
            <#if top_category_list??>
                <#list top_category_list as item>
                    <#if item_index gt 4 && item_index < 9>
                        <td class="shadow_add2">
                            <a href="/list/${item.id?c}"><img src="${item.imgUrl!''}" width="68" height="68"/>
                                <p>${item.title!''}</p>
                            </a>
                        </td>
                    </#if>
                </#list>
            </#if>
            <td style="border:none;">
                <a href="javascript:;">
                    <p>更多项目</p>
                    <p>敬请关注</p>
                </a>
            </td>
        </tr>  
    </table> 
    <div class="clear h30"></div>
  
    <section class="indextit">
        <h3>自驾游</h3>
        <menu id="tour-menu">
            <a class="sel" href="javascript:;" onmouseenter="javascript:tourPageChang(0);">昆明</a>
            <a href="javascript:;" onmouseenter="javascript:tourPageChang(1);">省内</a>
            <a href="javascript:;" onmouseenter="javascript:tourPageChang(2);">国内</a>
        </menu>
        <div class="clear"></div>
    </section>
    
    <div class="clear h10"></div>
  
    <ul id="tour-ul" style="width: 100%; height: 341px;">
        <li>
            <div class="index_tour">
                <#if tour_km_big_ad_list??>
                    <#list tour_km_big_ad_list as item>
                        <a class="a1" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                            <img src="${item.fileUri!''}" width="430" height="341"/>
                        </a>
                        <#break>
                    </#list>
                </#if>
            </div>
            
            <#if tour_km_list_ad_list??>
                <#list tour_km_list_ad_list as item>
                    <#if item_index < 4>
                        <div class="index_tourlist">
                            <a class="block w100 mb5" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                                <img class="w100" src="${item.fileUri!''}" height="341">
                            </a>
                        </div>
                    </#if>
                </#list>
            </#if>
        </li>
        <li style="display: none;">
            <div class="index_tour">
                <#if tour_province_big_ad_list??>
                    <#list tour_province_big_ad_list as item>
                        <a class="a1" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                            <img src="${item.fileUri!''}" width="430" height="341"/>
                        </a>
                        <#break>
                    </#list>
                </#if>
            </div>
            
            <#if tour_province_list_ad_list??>
                <#list tour_province_list_ad_list as item>
                    <#if item_index < 4>
                        <div class="index_tourlist">
                            <a class="block w100 mb5" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                                <img class="w100" src="${item.fileUri!''}" height="341">
                            </a>
                        </div>
                    </#if>
                </#list>
            </#if>
        </li>
        <li style="display: none;">
            <div class="index_tour">
                <#if tour_country_big_ad_list??>
                    <#list tour_country_big_ad_list as item>
                        <a class="a1" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                            <img src="${item.fileUri!''}" width="430" height="341"/>
                        </a>
                        <#break>
                    </#list>
                </#if>
            </div>
            
            <#if tour_country_list_ad_list??>
                <#list tour_country_list_ad_list as item>
                    <#if item_index < 4>
                        <div class="index_tourlist">
                            <a class="block w100 mb5" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                                <img class="w100" src="${item.fileUri!''}" height="341">
                            </a>
                        </div>
                    </#if>
                </#list>
            </#if>
        </li>
    </ul>
    
    <div class="clear h30"></div>
    
    <section class="index_botlist">
        <h3>养车宝典</h3>
        <#if curing_page??>
            <#list curing_page.content as item>
                <a href="/info/content/${item.id?c}?mid=11">${item.title!''}</a>
            </#list>
        </#if>
    </section>
    
    <section class="index_botad01">
        <div class="left_gundong fl">
            <a id="oneboxLeft" href="javascript:void(0);"><img src="/client/images/20150330051733397_easyicon_net_125.465346535.png" width="30" height="50" /></a>
        </div>
        <div class="right_gundong fr">
            <a id="oneboxRight" href="javascript:void(0);"><img src="/client/images/20150408033704325_easyicon_net_121.900990099.png" width="30" height="50" /></a>
        </div>
        <#if bottom_big_ad_list??>
            <div id="onebox">
                <div id="onesum">
                <#list bottom_big_ad_list as item>
                    <a class="onepart" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                        <img src="${item.fileUri!''}" width="570" height="375"/>
                    </a>
                </#list>
                </div>
            </div>
        </#if>
    </section>
    <section class="index_botad02">
        <div class="leftb_gundong fl">
            <a id="onebox2Left" href="javascript:void(0);"><img src="/client/images/20150330051733397_easyicon_net_125.465346535.png" width="30" height="50" /></a>
        </div>
        <div class="rightb_gundong fr">
            <a id="onebox2Right" href="javascript:void(0);"><img src="/client/images/20150408033704325_easyicon_net_121.900990099.png" width="30" height="50" /></a>
        </div>
        <#if bottom_small_ad_list??>
            <div id="onebox2">
                <div id="onesum2">
            <#list bottom_small_ad_list as item>
                <a class="onepart2" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if> href="${item.linkUri!''}">
                    <img src="${item.fileUri!''}" width="280" height="375"/>
                </a>
            </#list>
                </div>
            </div>
        </#if>
    </section>
  
    <div class="clear"></div> 
</div><!--main END-->

<div class="clear h50"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>
