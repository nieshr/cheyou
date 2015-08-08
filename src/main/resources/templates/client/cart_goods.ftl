<#if cart_goods_list?? && cart_goods_list?size gt 0>
    <table class="car_list">
        <tr>
            <th colspan="3">商品信息</th>
            <th>单价</th>
            <th>数量</th>
            <th>合计</th>
            <th>操作</th>
        </tr>
        
        <#assign allChecked=true >
        <#assign totalGoods=0>
        <#assign totalPrice=0>
        
        <#list cart_goods_list as cg>
            <tr>
                <td width="20">
                    <input type="checkbox" onclick="javascript:toggleSelect(${cg.id});" <#if cg.isSelected?? && cg.isSelected>checked="checked"<#else><#assign allChecked=false></#if>/>
                </td>
                <td width="110">
                    <a href="/goods/${cg.goodsId}"><img src="${cg.goodsCoverImageUri!''}" width="100" /></a>
                </td>
                <td width="400" style="text-align:left;"><a href="/goods/${cg.goodsId}">${cg.goodsTitle!''}</a></td>
                <td class="red">￥${cg.price?string("0.00")}</td>
                <td width="150" class="num">
                    <a href="javascript:minusNum(${cg.id});"> - </a>
                    <input class="text" type="text" value="${cg.quantity!''}" />
                    <a href="javascript:addNum(${cg.id});"> + </a>
                </td>
                <td class="red">￥${(cg.price*cg.quantity)?string("0.00")}</td>
                <td><a class="del" href="javascript:delCartItem(${cg.id});">删除</a></td>
                <#if cg.giftList??><#list cg.giftList as gif><td >${gif.name!''}</td></#list></#if>
                <#if cg.isSelected>
                    <#assign totalGoods=totalGoods+cg.quantity>
                    <#assign totalPrice=totalPrice+cg.price*cg.quantity>
                </#if>
            </tr>
        </#list>
        
    
        <tr>
          <td width="20"><input type="checkbox" <#if allChecked>checked="checked" onclick="javascript:toggleAllSelect(1);"<#else>onclick="javascript:toggleAllSelect(0);"</#if>/></td>
          <td colspan="2" style="text-align:left;">
            <span class="mr20">全选</span>
            <#--<a class="c9" href="#">删除选中的商品</a>-->
          </td>
          <td colspan="4" style="text-align:right; font-size:16px; line-height:35px;">
            商品<span class="fc fw-b"><#if cart_goods_list??>${totalGoods!'0'}</#if></span>件&nbsp;&nbsp;
            总价：<span class="fc fw-b">￥<#if cart_goods_list??>${totalPrice?string("0.00")}</#if></span>&nbsp;&nbsp;
            商品总计（不含运费）：<span class="fc fw-b">￥<#if cart_goods_list??>${totalPrice?string("0.00")}</#if></span>
          </td>
        </tr>
    </table>
    
    <div class="clear"></div>
    
    <div class="car_btn">
        <a class="ml20 fc" href="/">继续购物</a>
        <input class="sub" type="submit" onclick="javascript:goNext(${totalGoods!0});" value="去结算" />
    </div>
    
    <div class="clear"></div>
<#else>
    <div class="car_kong">
        <p class="fc fs18 pb10">购物车空空的哦，去看看心意的商品吧！</p>
        <p class="pt20"><a class="blue" href="/">马上去购物>>  </a></p>
    </div>
</#if>