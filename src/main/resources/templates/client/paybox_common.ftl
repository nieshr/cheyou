<section class="paybox hide_first">
    <h3><#if changePayMethod>请选择新的支付方式<a id="confirmChangePayMethod" href="javascript:void()">确认修改</a><#else>选择支付方式</#if></h3>
    <#if pay_type_list_bank?? && (pay_type_list_bank?size > 0)>
    <span id="cloud_pay" title="网银由光大银行提供支付渠道"><img src="/client/img/paymethod_logo/cloudPayment.png" /></span>
    <h4>网银支付</h4>
    <#list pay_type_list_bank as pay_type>
        <#assign onlineBank=true/>
        <#include "/client/paymentmethod_info.ftl" />
    </#list>
    </#if>
    <#if pay_type_list_third?? && (pay_type_list_third?size > 0)>
        <div class="clear h10"></div>
        <h4>三方支付</h4>
        <#list pay_type_list_third as pay_type>
            <#assign onlineBank=false/>
            <#include "/client/paymentmethod_info.ftl" />
        </#list>
    </#if>
    <#if paymethodId??>
    <script type="text/javascript">
        var paymethodId = "${paymethodId}";
    </script>
    </#if>
    <script type="text/javascript" src="/client/js/bankLogo.js"></script>
</section>
<#if changePayMethod><div class="clear h50"></div></#if>