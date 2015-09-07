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
function menuNextPage(boxid,_name,_li,_width,_height,lastid,nextid,_move,_num){
	var _box = $(boxid);
	var _menu = _box.find(_name);
	var _last = $(lastid);
	var _next = $(nextid);
	var _length = _menu.find(_li).length;
	var _index = 0;
	var _max = _length - _num;
	
	_box.css({"width":_width+"px","height":_height+"px","overflow":"hidden","":"","":""});
	_menu.css("width","99999px");
	
	var _nextpage = function(){
		_index++;
		if(_index > _max){_index=_max;}
		var _mm = -_move*_index;
		_menu.animate({left:_mm+"px"},200);
		};//fun end
	
	var _lastpage = function(){
		_index--;
		if(_index < 0){_index=0;}
		var _mm = -_move*_index;
		_menu.animate({left:_mm+"px"},200);
		};//fun end
		
	if(_length > _num){
		_next.click(function(){
			_nextpage();
			});
		_last.click(function(){
			_lastpage();
			});
		}//大于才有效
}