$(function () {
//自动计算成本价	
    $("#costPrice").change(function(){
    	changePrice();
    });
    $("#marketPrice").change(function(){
    	changePrice();
    });
    $("#outFactoryPrice").change(function(){
    	changePrice();
    });
    $("#idComputeSalePrice").change(function(){
    	changePrice();
    });
    
    $("#returnPoints").change(function(){
    	changePrice();
    });
    
    $("#commentReturnPoints").change(function(){
    	changePrice();
    });
    
    $("#installationPrice").change(function(){
    	changePrice();
    });
    $("#otherPrice").change(function(){
    	changePrice();
    });
    $("#shopReturnRation").change(function(){
    	changePrice();
    });
    
    $("#platformServiceReturnRation").change(function(){
    	changePrice();
    });
    $("#trainServiceReturnRation").change(function(){
    	changePrice();
    });
        
});

	function changePrice(){
		 var p1 = $.trim($('#costPrice').val());                 //成本价
	     var p2 = $.trim($('#marketPrice').val());               //市场价
	     var p3 = $.trim($('#outFactoryPrice').val());           //批发价
	     var p4 = $.trim($('#idComputeSalePrice').val());        //销售价
	     var p5 = $.trim($('#returnPoints').val());              //赠送粮草
	     var p6 = $.trim($('#commentReturnPoints').val());       //好评粮草
	     var p7 = $.trim($('#installationPrice').val());         //工时费
	     var p8 = $.trim($('#otherPrice').val());                //其他费用
	     var p9 = $.trim($('#shopReturnRation').val());          //同盟店返利比
	     var p10 = $.trim($('#platformServiceReturnRation').val()); //平台服务费比
	     var p11 = $.trim($('#trainServiceReturnRation').val()); //培训服务费比
	        
	     if (isNaN(p1) || p1=="") { p1 = 0 }
	     if (isNaN(p2) || p2== "") { p2 = 0 }
	     if (isNaN(p3) || p3=="") { p3 = 0 }
	     if (isNaN(p4) || p4== "") { p4 = 0 }
	     if (isNaN(p5) || p5=="") { p5 = 0 }
	     if (isNaN(p6) || p6=="") { p6 = 0 }
	     if (isNaN(p7) || p7== "") { p7 = 0 }
	     if (isNaN(p8) || p8=="") { p8 = 0 }
	     if (isNaN(p9) || p9== "") { p9 = 0 }
	     if (isNaN(p10) || p10=="") { p10 = 0 }
	     if (isNaN(p11) || p11=="") { p11 = 0 }
	        
	     $("#costPrice").val((parseFloat(p3) + parseFloat(p5) + parseFloat(p7) + parseFloat(p8) + parseFloat(p3)*parseFloat(p11)));
	     $("#shopReturnProfit").val((parseFloat(p4) - parseFloat(p3) - parseFloat(p5) - parseFloat(p7) - parseFloat(p8) - (parseFloat(p3)*parseFloat(p11)) - (parseFloat(p3) * ( parseFloat(p9) + parseFloat(p10)))));
	     $("#shopReturn").val((parseFloat(p4) * parseFloat(p9)));
	     $("#trainServiceReturnProfit").val((parseFloat(p3) * parseFloat(p11)));
	     $("#platformServiceReturnProfit").val((parseFloat(p4) * parseFloat(p10) - parseFloat(p6)));
	}