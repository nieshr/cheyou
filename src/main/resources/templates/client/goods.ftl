<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if goods??>${goods.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="${goods.seoKeywords!''}">
<meta name="description" content="${goods.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--[if IE]>
   <script src="/client/js/html5.js"></script>
<![endif]-->

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/common.js"></script>
<script src="/client/js/ljs-v1.01.js"></script>
<script src="/client/js/goods.js"></script>
<script src="/client/js/goods_comment_consult.js"></script>

<link href="/client/style/common.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cartoon.css" rel="stylesheet" type="text/css" />
<link href="/client/style/cytm.css" rel="stylesheet" type="text/css" />
<link href="/client/style/style.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
  $(document).ready(function(){
	menuDownList("top_phone","#top_phonelist",".a1","sel");
	phoneListMore();//单独下拉
    menuDownList("top_order","#top_orderlist",".a4","sel");//顶部下拉
	searchTextClear(".toptext","请输入品牌或商品名称","#999","#666");
	searchTextClear(".bottext","查看所有门店","#fff","#fff");
	checkNowHover("shopping_down","shopping_sel");
	navDownList("navdown","li",".nav_showbox");
	menuDownList("mainnavdown","#navdown",".a2","sel");
	
	productImgShow("proshowimg","li","proshowmenu","sel",396,396);
	checkBoxShow("assort_menu","a","assort_sum","li","sel");
	//productBoxShow("assort_menu","a","assort_ol","li","assort_sum","ul","sel");
	productBoxWidth(".partside");
	topTitFloat("detail_tit",1100,"detail_tit_sel");
	
	$("#id-minus").click(function(){
        var q = parseInt($("#quantity").val());
        
        if (q > 1)
        {
            $("#quantity").val(q-1);
        }
        
        $("#addCart").attr("href", "/cart/init?id=${goods.id?c}&quantity=" + $("#quantity").val());
    });
    
    $("#id-plus").click(function(){
        var q = parseInt($("#quantity").val());
        
        <#if goods.leftNumber??>
            if (q < ${goods.leftNumber!'0'})
            {
                $("#quantity").val(q+1);
            }
            else
            {
                alert("已达到库存最大值");
            }
        <#else>
            $("#quantity").val(q+1);
        </#if>
        $("#addCart").attr("href", "/cart/init?id=${goods.id?c}&quantity=" + $("#quantity").val());
    
    });
    
    $("#zhAddCart").click(function(){
        var str = "";
        $(".comboCheckBox:checked").each(function(){
            str += $(this).attr("zhid");
            str += ",";
        });
        var href = "/order/buy/comb?gid=" + ${goods.id?c} + "&zhid=" + str;
        
        window.location = href;
        
    });
    
});

<!-- 用户名中间变为*号  2015-8-4 15:38:18 mdj  -->
$(document).ready(function(){
    getElement();
});
/**
 * 获取要隐藏用户名的元素
 * @author mdj
 */
function getElement(){
    var pElement = $(".userName").toArray();
    for(var i = 0;i < pElement.length;i++)
    {
       var originName = pElement[i].innerHTML;
       var name =  changeName(originName);
       pElement[i].innerText=name;
    }
}
/**
 * 隐藏用户名
 * @author mdj
 */
function changeName(p)
{
    var temp = p;
    if(temp.length == 11)
    {
        var changeStr = temp.substring(3, 7);
        temp = temp.replace(changeStr,"****");
    }
    else
    {
        var startStr = "";
        var strLength = temp.length;
        for (var i = 0; i < strLength - 4; i++)
        {
            startStr += "*";
        }
        var changeStr = temp.substring(2, strLength - 2)

        temp = temp.replace(changeStr, startStr);
    }
    return temp;
}
<!-- 用户名中间变为*号  2015-8-4 15:38:18 mdj  end-->

function combSelect(self, price, originPrice)
{
    var count = parseInt($("#combCount").html());
    var currentPrice = parseFloat($("#combCurrentPrice").html());
    var combOriginPrice = parseFloat($("#combOriginPrice").html());
    var combSavePrice = parseFloat($("#combSave").html());
    
    // 选中
    if (self.checked)
    {
        $("#combCount").html(count+1);
        $("#combCurrentPrice").html(currentPrice + price);
        $("#combOriginPrice").html(combOriginPrice + originPrice);
        $("#combSave").html(combSavePrice + originPrice - price);
    }
    // 取消选中
    else
    {
        $("#combCount").html(count-1);
        $("#combCurrentPrice").html(currentPrice - price);
        $("#combOriginPrice").html(combOriginPrice - originPrice);
        $("#combSave").html(combSavePrice - originPrice + price);
    }
}

function clearSelect()
{
    $("#combCount").html(0);
    $("#combCurrentPrice").html(${goods.salePrice?string("0.00")});
    $("#combOriginPrice").html(${goods.salePrice?string("0.00")});
    $("#combSave").html(0);
    
    $(".comboCheckBox").attr("checked", false);
}

</script>
</head>

<body>
<#include "/client/common_header.ftl" />

<div class="pro_info"> 
  <h2 class="main pro_tit">
    <a class="a1" href="/">首页</a>
    <#if category_tree_list??>
        <#list category_tree_list as category>
            <span> > </span>
            <a class="a2" href="/list/${category.id?c}">${category.title!""}</a>
        </#list>
    </#if>
    <span> > ${goods.name!(goods.title!'')}</span>
  </h2>
    <div class="pro_box">
    <section class="proinfo_left">
        <menu id="proshowimg">
            <#if goods.showPictures??>
                <#list goods.showPictures?split(",") as uri>
                    <#if ""!=uri && uri_index < 4>
                        <li><img src="${uri!''}" /></li>
                    </#if>
                </#list>
            </#if>
        </menu>
        <div class="clear h15"></div>
        <#if goods.code??>
        <p class="pro_num">商品编码：${goods.code!''}<#--<a href="#">点击分享</a>--></p>
        </#if>
        <div class="clear h15"></div>
        <menu id="proshowmenu"></menu>
        <div class="clear"></div>

    </section>
    
        
    <section class="proinfo_right">
      <h3>${goods.title!''}</h3>
      <h4 class="red">${goods.subTitle!''}</h4>
      
      <#if qiang?? && qiang==1 && goods.flashSaleStartTime < .now && goods.flashSaleStopTime gt .now>
<script>
$(document).ready(function(){
    setInterval("timer()",1000);
});

function timer()
{
    var ts = (new Date(${goods.flashSaleStopTime?string("yyyy")}, 
                parseInt(${goods.flashSaleStopTime?string("MM")}, 10)-1, 
                ${goods.flashSaleStopTime?string("dd")}, 
                ${goods.flashSaleStopTime?string("HH")}, 
                ${goods.flashSaleStopTime?string("mm")}, 
                ${goods.flashSaleStopTime?string("ss")})) - (new Date());//计算剩余的毫秒数
                
    var allts = (new Date(${goods.flashSaleStopTime?string("yyyy")}, 
                parseInt(${goods.flashSaleStopTime?string("MM")}, 10)-1, 
                ${goods.flashSaleStopTime?string("dd")}, 
                ${goods.flashSaleStopTime?string("HH")}, 
                ${goods.flashSaleStopTime?string("mm")}, 
                ${goods.flashSaleStopTime?string("ss")}))
               - (new Date(${goods.flashSaleStartTime?string("yyyy")}, 
                parseInt(${goods.flashSaleStartTime?string("MM")}, 10)-1, 
                ${goods.flashSaleStartTime?string("dd")}, 
                ${goods.flashSaleStartTime?string("HH")}, 
                ${goods.flashSaleStartTime?string("mm")}, 
                ${goods.flashSaleStartTime?string("ss")}));//总共的毫秒数
                
    if (0 == ts)
    {
        window.location.reload();
    }
    
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
    if(ss < 0){
    	ss = 0;
    }
    if(mm < 0){
    	mm = 0;
    }
    if(hh < 0){
    	hh = 0;
    }
    if(dd < 0){
    	dd = 0;
    }
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    
    $("#lday").html(dd);
    $("#lhour").html(hh);
    $("#lmin").html(mm);
    $("#lsec").html(ss);
                    
    var price = ${goods.flashSalePrice?string("0.00")} * ts / allts;
    if(price < 1){
    	price = 1;
    }
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
    
    $("#currPrice").html("￥：" + s_x);
}

function checkTime(i)  
{  
    if (i < 10) {  
        i = "0" + i;  
    }  
    return i;  
}
</script>
          <div class="pro_price">
          <p class="p1">
              <span class="mr10">市场价</span>
              <span class="mr20">￥：${goods.salePrice?string("0.00")}</span>
            </p>
          <p class="p1">
              <span class="mr10">起拍价</span>
              <span class="mr20">￥：${goods.flashSalePrice?string("0.00")}</span>
            </p>
            <p class="p1">
              <span class="mr10">实时价格</span>
              <span class="red fs24 lh30 mr20" id="currPrice">￥：0.00</span>
            </p>
            <p class="p1">
              <span class="mr10">剩余时间</span>
              <span class="red mr10 ml10" id="lday">0</span>天<span class="red mr10 ml10" id="lhour">0</span>时<span class="red ml10 mr10" id="lmin">0</span>分<span class="red ml10 mr10" id="lsec">0</span>秒
            </p>
          </div>
      <#elseif qiang?? && qiang == 3 && goods.groupSaleStartTime?? && goods.groupSaleStartTime < .now && goods.groupSaleStopTime?? && goods.groupSaleStopTime gt .now>
            <p class="p1">
              <span class="mr10">预付价</span>
              <span class="red fs24 lh30 mr20">￥：<#if goods.groupSalePrice??>${goods.groupSalePrice?string("0.00")}</#if></span>
              <span class="unl-th c9">￥：<#if goods.marketPrice??>${goods.marketPrice?string("0.00")}</#if></span>
            </p>
            <p class="p1">
              <span class="mr10">三人团价</span>
              <span class="lh30 mr20">￥：<#if goods.groupSaleThreePrice??>${goods.groupSaleThreePrice?string("0.00")}</#if></span>
            </p>
            <p class="p1">
              <span class="mr10">七人团价</span>
              <span class="lh30 mr20">￥：<#if goods.groupSaleSevenPrice??>${goods.groupSaleSevenPrice?string("0.00")}</#if></span>
            </p>
            <p class="p1">
              <span class="mr10">十人团价</span>
              <span class="lh30 mr20">￥：<#if goods.groupSaleTenPrice??>${goods.groupSaleTenPrice?string("0.00")}</#if></span>
            </p>
      <#elseif qiang?? && qiang == 100 && goods.groupSaleHundredStartTime?? && goods.groupSaleHundredStartTime < .now && goods.groupSaleHundredStopTime?? && goods.groupSaleHundredStopTime gt .now>
            <p class="p1">
              <span class="mr10">预付价</span>
              <span class="red fs24 lh30 mr20">￥：<#if goods.groupSalePrePayPrice??>${goods.groupSalePrePayPrice?string("0.00")}</#if></span>
              <span class="unl-th c9">￥：<#if goods.groupSalePrice??>${goods.groupSalePrice?string("0.00")}</#if></span>
            </p>
            <p class="p1">
              <span class="mr10">百人团价格</span>
              <span class="lh30 mr20">￥：<#if goods.groupSaleHundredPrice??>${goods.groupSaleHundredPrice?string("0.00")}</#if></span>
            </p>
      <#else>
          <div class="pro_price">
            <p class="p1">
              <span class="mr10">同盟价</span>
              <#if qiang?? && qiang != 1 && goods.groupSaleStartTime?? && goods.groupSaleStartTime < .now && goods.groupSaleStopTime?? && goods.groupSaleStopTime gt .now>
                <span class="red fs24 lh30 mr20">￥：<#if goods.groupSalePrice??>${goods.groupSalePrice?string("0.00")}</#if></span>
              <#else>
                <span class="red fs24 lh30 mr20">￥：<#if goods.salePrice??>${goods.salePrice?string("0.00")}</#if></span>
              </#if>
              <span class="unl-th c9">￥：<#if goods.marketPrice??>${goods.marketPrice?string("0.00")}</#if></span>
            </p>
            <#if goods.returnPoints?? && goods.returnPoints != 0>
            <p class="p1 red">
              可获得粮草 ${goods.returnPoints!'0'} 担
            </p>
            </#if>
            <p class="p1">
              车友口碑 <span class="red ml10">${goods.totalComments!'0'}</span>人评价
            </p>
            <#if goods.giftList?? && goods.giftList?size gt 0>
                <p class="p">
                    <span style="color:#666;" class="red">赠品</span>
                    <#list goods.giftList as gitem>
                        <a class="red ml20" title="点击查看详情" href="/goods/${gitem.goodsId?c!''}">${gitem.goodsTitle!''}</a>
                    </#list>
                </p>
            </#if>
          </div><!--pro_price END-->
      </#if>
      <table class="pro_choose">
        <#if diy_site_list?? && diy_site_list?size gt 0>
            <tr>
              <td colspan="2"><span>线下同盟店</span>
                <select>
                    <option value="">请选择...</option>
                    <#list diy_site_list as item>
                        <option value="${item.id?c}">${item.title!''}</option>
                    </#list>
                </select>
              </td>
            </tr>
        </#if>
        <#if total_select??>
            <#if 1==total_select>
                <tr>
                    <th width="60">${select_one_name!''}</th>
                    <td>
                        <#if select_one_goods_list??>
                        <#list select_one_goods_list as item>
                            <a <#if item.selectOneValue==one_selected>class="sel"</#if> href="/goods/${item.id?c}">${item.selectOneValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
            <#elseif 2==total_select>
                <tr>
                    <th width="60">${select_one_name!''}</th>
                    <td>
                        <#if select_one_goods_list??>
                        <#list select_one_goods_list as item>
                            <a <#if item.selectOneValue==one_selected>class="sel"</#if> href="/goods/${item.id?c}">${item.selectOneValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <th width="60">${select_two_name!''}</th>
                    <td>
                        <#if select_two_goods_list??>
                        <#list select_two_goods_list as item>
                            <a <#if item.selectTwoValue==two_selected>class="sel"</#if> href="/goods/${item.id?c}">${item.selectTwoValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
            <#elseif 3==total_select>
                <tr>
                    <th width="60">${select_one_name!''}</th>
                    <td>
                        <#if select_one_goods_list??>
                        <#list select_one_goods_list as item>
                            <a <#if item.selectOneValue==one_selected>class="sel"</#if> href="/goods/${item.id?c}">${item.selectOneValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <th width="60">${select_two_name!''}</th>
                    <td>
                        <#if select_two_goods_list??>
                        <#list select_two_goods_list as item>
                            <a <#if item.selectTwoValue==two_selected>class="sel"</#if> href="/goods/${item.id?c}">${item.selectTwoValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <th width="60">${select_three_name!''}</th>
                    <td>
                        <#if select_three_goods_list??>
                        <#list select_three_goods_list as item>
                            <a <#if item.selectThreeValue==three_selected>class="sel"</#if> href="/goods/${item.id?c}">${item.selectThreeValue}</a>
                        </#list>
                        </#if>
                    </td>
                </tr>
            </#if>
        </#if>
        <tr>
            <th>数量</th>
            <td>                
                <#if qiang??>                
                    <input name="quantity" type="text" id="quantity" value="1" class="text" />
                    <div class="plus">
                        <a disabled="disabled" href="javascript:;"></a>
                        <a disabled="disabled" class="down" href="javascript:;"></a>                   
                    </div>
                <#else>
                    <input name="quantity" type="text" id="quantity" value="1" class="text" />
                <div class="plus">
                    <a id="id-plus" href="javascript:;"></a>
                    <a id="id-minus" class="down" href="javascript:;"></a>
                </div> 
                </#if>                                               
                <span class="fl ml10">库存
                <#if qiang?? && qiang==1 && goods.flashSaleStartTime < .now && goods.flashSaleStopTime gt .now>
                    ${goods.flashSaleLeftNumber!'0'}
                <#elseif qiang?? && qiang==3 && goods.groupSaleStartTime < .now && goods.groupSaleStopTime gt .now>
                    ${goods.groupSaleLeftNumber!'0'}
                <#elseif qiang?? && qiang==100 && goods.groupSaleHundredStartTime < .now && goods.groupSaleHundredStopTime gt .now>
                    ${goods.groupSaleHundredLeftNumber!'0'}
                <#else>
                    ${goods.leftNumber!'0'}
                </#if>
                    件</span>
                <div class="clear"></div>
            </td>
        </tr>
        <tr>
          <th></th>
          <td></td>
        </tr>
        <tr>
          <td colspan="2" style="border-top:1px solid #eee;border-bottom:1px solid #eee; padding:10px 0;">
            <span class="fl">服务承诺</span>
            <a class="a1" href="javascript:;" title="免运费">免运费</a>
            <a class="a1 a2" href="javascript:;" title="正品保障">正品保障</a>
            <a class="a1 a3" href="javascript:;" title="免费施工">免费施工</a>
            <a class="a1 a4" href="javascript:;" title="无忧售后">无忧售后</a>
            <div class="clear"></div>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <#if qiang?? && goods.flashSaleLeftNumber?? && goods.groupSaleLeftNumber??>
                <#if qiang==1 && goods.flashSaleLeftNumber gt 0>
                     <a id="addCart" href="/order/buy/<#if 1==qiang>qiang<#elseif 100==qiang>baituan<#else>tentuan</#if>?gid=${goods.id?c}" class="sub sub01">立即购买</a>
                <#elseif qiang!=1 && goods.groupSaleLeftNumber gt 0>
                     <a id="addCart" href="/order/buy/<#if 1==qiang>qiang<#elseif 100==qiang>baituan<#else>tentuan</#if>?gid=${goods.id?c}" class="sub sub01">立即购买</a>
                <#else>
                     <a id="addCart" href="#" class="sub sub01">库存不足</a>   
                </#if>
            <#else>
                <#if goods.leftNumber gt 0>
                    <a id="addCart" href="/cart/init?id=${goods.id?c}" class="sub sub01">加入购物车</a>
                <#else>
                    <a id="addCart" href="#" class="sub sub01">库存不足</a>
                </#if>
            </#if>
            <div class="buy_share">
              <a class="buy_share_a" href="javascript:addCollect(${goods.id?c});">收藏商品</a>
              <p>关注人数：<span class="red">${goods.totalCollects!'0'}</span>人</p>
            </div>
            <div class="clear"></div>
          </td>
        </tr>
      </table>
      
    </section><!--proinfo_right END-->
    <div class="clear h20"></div>
  </div><!--pro_box END--> 
  <div class="clear"></div>
</div><!--pro_info END-->

<div class="main pt20">
  <#if !qiang??>
  <section class="pro_assort">
    <menu id="assort_menu">
      <a class="sel" href="javascript:;">推荐配套</a>
      <div class="clear"></div>
    </menu>
    
    <div>
      <ul id="assort_sum">
      <li>
        <div class="part">
          <a href="/goods/${goods.id?c!''}"><img src="${goods.coverImageUri!''}" width="114" height="114" /></a>
          <p style="height: 33px; overflow: hidden;">${goods.title!''}</p>
          <p class="p1"><span>￥<#if goods.salePrice??>${goods.salePrice?string("0.00")}</#if></span></p>
        </div>
        
        <p class="part"><img src="/client/images/content/pro_plus.png" /></p>
        
        <aside class="partside">
            <div class="partside_out">
            
            <#if goods.combList?? && goods.combList?size gt 0>
                <#list goods.combList as item>
                    <div class="part">
                        <a href="/goods/${item.goodsId?c}"><img src="${item.coverImageUri!''}" width="114" height="114"/></a>
                        <p style="height: 35px; overflow: hidden;">${item.goodsTitle!''}</p>
                        <p class="p1">
                            <input type="checkbox" class="comboCheckBox" zhid="${item.id?c}" onclick="javascript:combSelect(this, ${item.currentPrice?string("0.00")}, ${item.goodsPrice?string("0.00")});"/>
                            <span>${item.currentPrice?string("0.00")}</span>
                        </p>原价：<del>¥<#if item.goodsPrice??>${item.goodsPrice?string("0.00")}</#if></del>
                    </div>
                    <#if item_index+1 < comb_list?size>
                        <p class="part"><img src="/client/images/content/pro_plus.png" /></p>
                    </#if>
                </#list>
            </#if>
        
          </div>
        </aside>
        <p class="part"><img src="/client/images/content/pro_eq.png" width="30" /></p>
        <div class="part01">
            <p>已选择：<span class="red" id="combCount">0</span>个组合<a href="javascript:clearSelect();">[清除选择]</a></p>
            <p>组合价：￥<span class="red" id="combCurrentPrice">${goods.salePrice?string("0.00")}</span></p>
            <p>原价：￥<span class="unl-th" id="combOriginPrice">${goods.salePrice?string("0.00")}</span></p>
            <p>已节省：￥<span id="combSave">0.00</span></p>
            <input id="zhAddCart" class="sub" type="submit" value="立即购买" />
        </div>
        <div class="clear"></div>
      </li>
    </ul>
    </div>
    <div class="clear"></div>
  </section><!--pro_assort END-->
  </#if>
  
  <div class="clear h20"></div>
  
  <section class="column_left">
    <#--
     <menu class="leftpart mb10">
      <h4 class="tit">相关分类</h4>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <p class="clear"></p>
     </menu>
     <menu class="leftpart">
      <h4 class="tit">相关品牌</h4>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <a href="#">行车记录仪</a>
      <a href="#">便捷导航仪</a>
      <p class="clear"></p>
     </menu>
     
    <menu class="hot_list mt20">
        <h5 class="tit">浏览了该商品的客户最终购买</h5>
        <a class="scan" href="#">
            <img src="/client/images/photo/img02.png" />
            <p style="overflow: hidden; height: 61px;">苹果iPhone 6 Plus/A1524 5.5英寸特惠版苹果iPhone 6 Plus/A1524 5.5英寸特惠版苹果iPhone 6 Plus/A1524 5.5英寸特惠版苹果iPhone 6 Plus/A1524 5.5英寸特惠版</p>
            <p class="red">￥5498.00</p>
        </a>
    </menu>
    -->
    <menu class="hot_list mt20">
        <h5 class="tit">热卖排行</h5>
        <#if hot_list??>
            <#list hot_list as item>
                <#if item_index < 5>
                    <a class="scan" href="/goods/${item.id?c}">
                        <img src="${item.coverImageUri!''}" title="${item.title!''} ${item.subTitle!''}"/>
                        <p style="overflow: hidden; height: 61px;">${item.title!""}</p>
                        <p class="red">￥${item.salePrice?string("0.00")}</p>
                    </a>
                </#if>
            </#list>
        </#if>
    </menu>
    
    <menu class="hot_list mt20">
        <h5 class="tit">浏览记录</h5>
        <#if recent_page??>
            <#list recent_page.content as item>
                <a class="scan" href="/goods/${item.goodsId?c}">
                    <img src="${item.goodsCoverImageUri!''}" title="${item.goodsTitle!''}"/>
                    <p style="overflow: hidden; height: 61px;">${item.goodsTitle!''}</p>
                    <p class="red">￥<#if item.goodsSalePrice??>${item.goodsSalePrice?string("0.00")}</#if></p>
                </a>
            </#list>
        </#if>
        <div class="ta-r" style="border-top:1px dotted #ddd;"><a class="blue fs12 lh30" href="#">清空浏览记录</a></div>
    </menu>
    
    
  </section><!--column_left END-->
  
    <div class="column_right">
        <div class="detail_tit" id="detail_tit">
          <div class="out">
            <menu>
              <a href="#detail_tit" tid="0" class="sel stab">商品介绍</a>
              <a href="#detail_tit" tid="1" class="stab">规格参数</a>
              <a href="#detail_tit" tid="2" class="stab">售后服务</a>
              <a href="#detail_tit" tid="3" class="stab">商品评价</a>
              <a href="#consult-sec" tid="4" class="stab">商品咨询</a>
              <div class="clear"></div>
            </menu>
          </div>
        </div><!--detail_tit END-->
        
        <div class="clear h10"></div>
        
        <div id="tab0" class="php_z">
            ${goods.detail!''}
        </div>
        
        <div id="tab1" class="php_z" style="display:none;">
            <table class="detail_tab">
                <#if goods.paramList??>
                    <#list goods.paramList as param>
                        <#if param_index % 3 == 0 >
                            <tr>
                                <td>${param.paramName!''}: ${param.value!''}</td>
                                <#if goods.paramList?size gt param_index+1 >
                                    <td>${goods.paramList[param_index+1].paramName!''}: ${goods.paramList[param_index+1].value!''}</td>
                                </#if>
                                <#if goods.paramList?size gt param_index+2 >
                                    <td>${goods.paramList[param_index+2].paramName!''}: ${goods.paramList[param_index+2].value!''}</td>
                                </#if>
                            </tr>  
                        </#if>
                    </#list>
                </#if>
            </table>
        </div>
        
        <div id="tab2" class="php_z" style="display:none;">
            ${goods.afterMarketService!''}
        </div>
        
        <div class="clear h20"></div>
        
        <#include "/client/goods_comment.ftl" />
        
        <div id="consult-sec" class="clear h20"></div>
        
        <section class="pro_myseek">
            <#if username??>
                <form id="consultForm" action="javascript:submitConsult();">
                    <input type="hidden" name="goodsId" value=${goods.id?c} />
                    <h3 class="lh30 fs18 fw400 pb10"><span class="circle_tit">我要咨询</span></h3>
                    <menu>
                        <textarea name="content" datatype="*5-255" nullmsg="请输入咨询内容"></textarea>
                    </menu>
                    <p>温馨提示：我们将尽快回复您的咨询。</p>
                    <div class="clear"></div>
                    <input class="sub" type="submit" value="马上提问" />
                </form>
            <#else>
                <div>请 <a href="/login">登录</a> 以进行咨询</div>
            </#if>
        </section>
        
        <#include "/client/goods_consult.ftl" />
        
    </div><!--column_right END-->
  
    <div class="clear"></div>
</div><!--main END-->
<#include "/client/common_footer.ftl" />
</body>
</html>
