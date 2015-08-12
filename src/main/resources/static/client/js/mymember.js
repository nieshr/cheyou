//本效果由昆明天度网络IRIS原创制作

function hoverShowInfo(boxid){
	var _box = $("#"+boxid);
	var _d = _box.css("display");
	if(_box.is(":animated")){_box.stop(true,true);}
	if(_d == "none"){$(".mymember_info_show").fadeOut(200);_box.fadeIn(200);}
}	
function closeShowInfo(){
	$(".mymember_info_show").fadeOut(200);
}
function mymemberNavShow(boxid,navid){
	var _box = $("#"+boxid);
	var _d = _box.css("display");
	if(_box.is(":animated")){_box.stop(true,true);}
	if(_d == "none"){
		_box.fadeIn(200);
		}
	$("#"+navid).bind("mouseleave",function(){
		_box.fadeOut(200);
		});
}
function mymemberMenuCheck(menuid,menusum,checkid,checksum,_name){
	var _menu = $("#"+menuid+" "+menusum);
	var _arr = $("#"+checkid+" "+checksum);
	_arr.eq(0).css("display","block")
	_menu.hover(function(){
		var _index = $(this).index();
		$(this).addClass(_name).siblings().removeClass(_name);
		_arr.eq(_index).css("display","block").siblings().css("display","none");
		});
}
function mymemberRightMove(boxid,_width,_height,nextid,_speed,_num,arrname){
	var _box = $("#"+boxid);
	var _next = $("#"+nextid);
	var _sum = _box.find("ul");
	var _obj = _box.find("li");
	var _arr = _obj.find(arrname);
	
	var _out = _width * _num;
	_sum.css({"width":_out+"px","position":"relative","display":"block","overflow":"hidden","height":_height+"px"});
	_obj.css({"width":"9999px","position":"absolute","top":"0","left":"0"});
	
	var cartoon;
	var _min = 0;
	var _move = 0;
	if(_arr.length > _num){
		var _cha = _arr.length - _num;
		_min = -_cha * _width;
		}
	 
	var _rightgo = function(){
		if(_move > _min){
			_move--;
			_obj.css({"left":_move+"px"});
			}
		};
	
		
		_next.hover(function(){
		  cartoon = setInterval(_rightgo,_speed);
		  },function(){
			  clearInterval(cartoon);
			  });
		
	    _box.hover(function(){},function(){
			if(_obj.is(":animated")){_obj.stop(true,true);}
			_obj.animate({"left":"0px"},500);
			_move = 0;
			});
}



function evaluateShow(boxid,_name){
	var _box = $("#"+boxid);
	var _display = _box.css("display");
	if(_display == "none"){
		_box.removeClass(_name);
		}else {
			_box.addClass(_name);
			}
	
	
}

function memberScanList(lineid,_boxname){
	var _box = $(_boxname);
	var _line = $("#"+lineid);
	$(window).scroll(function(){
		var _max = _box.height();
		var _now = $(window).scrollTop();
		_now += 200;
		if(_line.is(":animated")){_line.stop(true,true);}
		if(_now <= _max){
			_line.animate({"height":_now+"px"},200);
			}else {
				_line.animate({"height":_max+"px"},200);
				}
		
		});
}

function myOrderShow(aid,tabid){
	var _obj = $("#"+aid);
	var _tab = $("#"+tabid);
	var _display = _tab.css("display");
	if(_display == "none"){
		_obj.addClass("member_down");
		_tab.slideDown(200);
		}else {
			_obj.removeClass("member_down");
		    _tab.slideUp(200);
			}
}

//本效果由昆明天度网络IRIS原创制作-左侧收缩菜单
function myLeftMunu(){
	var _show = $(".mymember_menu").find(".leftmenu");
	var _menu = $(".mymember_menu").find(".mymember_menu_tit");
	_menu.click(function(){
		var _obj = $(this).parent();
		var _box = _obj.find(".leftmenu");
		var _dis = _box.css("display");
		_box.slideToggle(100);
		if(_dis == "none"){
			$(this).addClass("sel01");
			_box.slideDown(100);
			}else{
				$(this).removeClass("sel01");
			    _box.slideUp(100);
				}
		
		});
	//alert(_menu.length)
	
}


