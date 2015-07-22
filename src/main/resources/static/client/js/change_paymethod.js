$(document).ready(function(){
    $("#change_paytype").click(function() {
        $(".paybox").show();
    });
    
    $("#confirmChangePayMethod").click(function() {
        var orderId = $("#change_paytype").attr("data-orderid");
        var paymentMethodId = $('input[name=payTypeId]:radio:checked').val();
        if(!paymentMethodId) {
            alert("请选择一种支付方式!");
            return;
        }
        $.ajax({
            type : "post",
            url : "/order/change_paymethod",
            data : {
                "orderId" : orderId,
                "paymentMethodId" : paymentMethodId
            },

            success : function(result) {
                if ('S' == result.status) {
                    $.cookie('payMethod', paymentMethodId, { expires: 7, path: '/' });
                    window.location.reload();
                } else {
                    alert(result.message);
                }
            },
            
            error : function() {
                alert("订单支付方式修改失败!");
            }
        });
    });
});