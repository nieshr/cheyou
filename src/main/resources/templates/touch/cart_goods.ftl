<#if cart_goods_list?? && cart_goods_list?size gt 0>
    <div class="whitebg">
      <ul class="car_list main">
        <#assign allChecked=true >
        <#assign totalGoods=0>
        <#assign totalPrice=0>
        
        <#list cart_goods_list as cg>
            <li>
              <a class="a1 <#if cg.isSelected>sel<#else><#assign allChecked=false></#if>" href="javascript:toggleSelect(${cg.id});">&nbsp;</a>
              <a class="a2" href="/touch/goods/${cg.goodsId}<#if cg.qiang??>?qiang=${cg.qiang!''}</#if>" style="background-image:url(${cg.goodsCoverImageUri!''}); height:64px;">
                <p>${cg.goodsTitle!''}</p>
              </a>
              <div class="car_num">
                <span class="red">￥${(cg.price*cg.quantity)?string("0.00")}</span>
                <input type="text" class="text" value="${cg.quantity!''}" />
                <a class="min" href="javascript:minusNum(${cg.id});">-</a>
                <a class="plus" href="javascript:addNum(${cg.id});">+</a>
              </div>
              <a class="a3" href="javascript:delCartItem(${cg.id});"><img src="/touch/images/delete.png" height="20" /></a>
              <div class="clear"></div>
              <#if cg.isSelected>
                <#assign totalGoods=totalGoods+cg.quantity>
                <#assign totalPrice=totalPrice+cg.price*cg.quantity>
              </#if>
            </li>
        </#list>
      </ul>
    </div>

    <div class="carfoot_bg"></div>
    <footer class="carfoot">
      <div class="mainbox">
        
        <p><a <#if allChecked>class="a1 sel" href="javascript:toggleAllSelect(1);"<#else>class="a1" href="javascript:toggleAllSelect(0);"</#if>><i></i>全选</a><span>合计：</span><span class="red">￥<#if cart_goods_list??>${totalPrice?string("0.00")}</#if></span></p>
        <input class="sub" onclick="javascript:goNext(${totalGoods!0});" type="submit" value="去结算（<#if cart_goods_list??>${totalGoods!'0'}</#if>）" />
      </div>
    </footer>
<#else>
    <div>
        <p>购物车空空的哦，去看看心意的商品吧！</p>
        <p><a class="blue" href="/touch">马上去购物>>  </a></p>
    </div>
</#if>