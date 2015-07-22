/**
 * 
 */
$(document).ready(function() {
    $("input[name='payTypeId']").change(function() {
        if(forPaymentFllow) {
            $.cookie('payMethod', $(this).val(), { expires: 7, path: '/' });
        }
        $(".paymethod").removeClass("paymethodSelected");
        var $selectedItem = $(this).parent();
        $selectedItem.addClass("paymethodSelected");
        $("input[name='payTypeId']").attr("checked", "checked");
    });
    
    var lastPaymentMethod = !paymethodId ? $.cookie('payMethod') : paymethodId;
    if(lastPaymentMethod) {
        $("input[value='" + lastPaymentMethod + "']:radio").trigger("change");
    }
    
    $("#expandBank").click(function() {
        $("#expandBank").hide();
        $("#hideBank").show();
        $(".moreclear").show();
        $(".morebank").show();
    });
    
    $("#hideBank").click(function() {
        $("#hideBank").hide();
        $("#expandBank").show();
        $(".moreclear").hide();
        $(".morebank").hide();
    });
});