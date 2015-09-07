$(document).ready(function(){

    // 点击tab
    $(".stab").click(function(){
        // tab特效切换
        $(".stab").removeClass("sel");
        $(this).addClass("sel");
        
        // tab页面切换
        $(".php_z").css("display", "none");
        $("#tab"+$(this).attr("tid")).css("display", "block");
    });
//
//    $(".comb-checkbox").click(function(){
//        var combop = parseFloat($("#combOriginPrice").html());
//        var combcp = parseFloat($("#combCurrentPrice").html());
//        var combsave = parseFloat($("#combSave").html());
//        var combCount = parseInt($("#combCount").html());
//        
//        
//        if ($(this).attr("checked"))
//        {
//            var index = $(".comb-checkbox").index($(this));
//            
//            var op = parseFloat($(".comb-origin-price").eq(index).html());
//            var cp = parseFloat($(".comb-current-price").eq(index).html());
//            var span = op - cp;
//            
//            $("#combOriginPrice").html(op + combop);
//            $("#combCurrentPrice").html(cp + combcp);
//            $("#combSave").html(span + combsave);
//            $("#combCount").html(combCount + 1);
//        }
//        else
//        {
//            var index = $(".comb-checkbox").index($(this));
//            
//            var op = parseFloat($(".comb-origin-price").eq(index).html());
//            var cp = parseFloat($(".comb-current-price").eq(index).html());
//            var span = op - cp;
//            
//            $("#combOriginPrice").html(combop - op + "");
//            $("#combCurrentPrice").html(combcp - cp + "");
//            $("#combSave").html(combsave - span + "");
//            $("#combCount").html(combCount - 1 + "");
//        }
//    });
});

function addCollect(goodsId)
{
    if (undefined == goodsId)
    {
        return;
    }
    
    $.ajax({
        type:"post",
        url:"/user/collect/add",
        data:{"goodsId": goodsId},
        dataType: "json",
        success:function(res){
            if(res.code==0){
            	$("#addCollect").removeClass("buy_share_a");
                $("#addCollect").addClass("buy_share_a1");
                
            }
            alert(res.message);
            
            // 需登录
            if (res.code==1)
            {
                setTimeout(function(){
                    window.location.href = "/login";
                }, 1000); 
            }
        }
    });
}

function addCollect1(goodsId)
{
    if (undefined == goodsId)
    {
        return;
    }
    var add = "addCollect"+goodsId;

    $.ajax({
        type:"post",
        url:"/user/collect/add",
        data:{"goodsId": goodsId},
        dataType: "json",
        success:function(res){
            if(res.code==0){
            	$("#"+add).attr('src',"/client/images/content/liebiao_342.png");
            }
            alert(res.message);
            
            // 需登录
            if (res.code==1)
            {
                setTimeout(function(){
                    window.location.href = "/login";
                }, 1000); 
            }
        }
    });
}
