<#if (pay_type_index % maxMethodCount == 0)>
    <#if (pay_type_index == maxMethodCount) && onlineBank>
        <div id="expandBank">
            <span class="bankOpera">其他&gt;&gt;</span>
            <span class="ui-icon ui-icon-circle-triangle-s">&nbsp;</span>
        </div>
        <div id="hideBank">
            <span class="bankOpera">收起&gt;&gt;</span>
            <span class="ui-icon ui-icon-circle-triangle-n">&nbsp;</span>
        </div>
    </#if>
    <#if (pay_type_index >= maxMethodCount) && onlineBank>
        <div class="bankSelect morebank">
    <#else>
        <div class="bankSelect">
    </#if>
</#if>
<li>
<span class="paymethod" title="<#if pay_type.adTitle?? && pay_type.adContents??>${pay_type.adContents!''}<#else>${pay_type.title!''}</#if>">
    <input class="pay_type_chk" name="payTypeId" type="radio" datatype="n" value="${pay_type.id?c!''}" fee="${pay_type.fee!'0'}" nullmsg="请选择支付方式!" />
    <#if pay_type.coverImageUri??>
        <img class="banklogo" src="${pay_type.coverImageUri!''}" title="${pay_type.title!''}"/>
    <#else>
        <span>${pay_type.title!''}</span>
    </#if>
    <#if pay_type.adTitle?? && pay_type.adContents??>
        <span class="payment_ad" title="${pay_type.adContents!''}">${pay_type.adTitle!''}</span>
    <#else>
        <span class="payment_ad">&nbsp;</span>
    </#if>
</span>
</li>
<#if (pay_type_index % maxMethodCount == (maxMethodCount - 1))>
</div>
    <#if (pay_type_index == (maxMethodCount - 1))>
    <div class="clear h10"></div>
    <#else>
    <div class="moreclear h10"></div>
    </#if>
<#elseif !pay_type_has_next>
</div>
</#if>