//下拉菜单 - 本效果由昆明天度网络IRIS原创制作
function menuDownList(boxid,_showname,_name,_hover){
	var _box = $("#"+boxid);
	var _show = _box.find(_showname);
	var _chek = _box.find(_name);
	_box.hover(function(){
		if(_show.is(":animated")){_show.stop(true,true);}
		_chek.addClass(_hover);
		_show.fadeIn(300);
		},function(){
			if(_show.is(":animated")){_show.stop(true,true);}
			_chek.removeClass(_hover);
		    _show.fadeOut(300);
			});
}
//搜索框部分 - 本效果由昆明天度网络IRIS原创制作
function searchTextClear(_name,_text,color01,color02){
	var _obj = $(_name);
	_obj.val(_text);
	_obj.css("color",color01);
	_obj.click(function(){
		var _now = _obj.val();
		if(_now == _text){
			_obj.val("");
			_obj.css("color",color02);
		}
	});
	_obj.blur(function(){
		var _now = _obj.val();
		if(_now == ""){
			_obj.val(_text);
			_obj.css("color",color01);
		}
	});
}
//加入状态 - 本效果由昆明天度网络IRIS原创制作
function checkNowHover(boxid,_name){
	var _box = $("#"+boxid);
	_box.hover(function(){
		_box.addClass(_name);
		},function(){
			_box.removeClass(_name);
			});
}
//顶部扫描单独下拉 - 本效果由昆明天度网络IRIS原创制作
function phoneListMore(){
	var _sum = $("#top_phonelist");
	var _arr = _sum.find("li");
	var _menu = $(".phone_sum_more");
	var _parr = $(".phone_sum_more p");
	var _index = 0;
	_arr.find("a").hover(function(){
		if(_menu.is(":animated")){_menu.stop(true,true);}
		if(_parr.is(":animated")){_parr.stop(true,true);}
		_index = $(this).parent().index();
		if(_index > 0){
			_menu.fadeIn(200);
			_parr.eq(_index-1).fadeIn(200).siblings().fadeOut(200);
			}
		},function(){
			_menu.fadeOut(200);
			_parr.fadeOut(200);
			});
}

function navDownList(boxid,_sumname,_showname){
	var _box = $("#"+boxid);
	var _arr = _box.find(_sumname);
	var _hover = _box.find(_showname);
	_arr.hover(function(){
		if(_hover.is(":animated")){_hover.stop(true,true);}
		var _height = $(this).height() + 5;
		$(this).find(".bg").height(_height);
		$(this).find(_showname).fadeIn(100);
		},function(){
			if(_hover.is(":animated")){_hover.stop(true,true);}
			_hover.fadeOut(100);
			});
}
//选项卡 - 本效果由昆明天度网络IRIS原创制作
function checkBoxShow(menuid,_mname,sumid,_sname,_hover){
	var _menu = $("#"+menuid);
	var _marr = _menu.find(_mname);
	var _sum = $("#"+sumid);
	var _sarr = _sum.find(_sname);
	var _index = 0;
	_marr.eq(0).addClass(_hover);
	_sarr.eq(0).css("display","block").siblings().css("display","none");
	_marr.hover(function(){
		_index = $(this).index();
		_marr.eq(_index).addClass(_hover).siblings().removeClass(_hover);
		_sarr.eq(_index).css("display","block").siblings().css("display","none");
		});
}
//首页banner - 本效果由昆明天度网络IRIS原创制作
function indexBannerShow(boxid,_bname,numid,_nname,_hover,_speed){
	var _box = $("#"+boxid);
	var _barr = _box.find(_bname);
	var _num = $("#"+numid);
	var _narr = _num.find(_nname);
	var _length = _barr.length;
	var _index = 0;
	var _width = _num.width()/_narr.length;
	_narr.width(_width);
	_narr.eq(_index).addClass(_hover);
	_barr.eq(_index).css("display","block").siblings().css("display","none");
	
	var _show = function(_before,_now){
		_index = _now;
		if(_index >= _length){_index = 0;}
		/*var _math = Math.random() * 10;
		_math = Math.floor(_math);
		var _ww = _num.width()/2;
		if(_math > 5){
			_ww = -1 * _ww;
			};
		_barr.eq(_now).css("z-index","3");
		_barr.eq(_before).animate({top:_ww+"px","opacity":"0.3"},300,function(){
			$(this).css({"z-index":"1","opacity":"1","top":"0"});
			_barr.eq(_now).css("z-index","5");
			});*/
			_narr.eq(_index).addClass(_hover).siblings().removeClass(_hover);
			_barr.eq(_index).fadeIn(400);
			_barr.eq(_before).fadeOut(400);
		};//fun END
		
	var _cartoon = setInterval(function(){
		var _nn = _index;
		_index++;
		_show(_nn,_index);
		},_speed);
	
	//鼠标事件
	_narr.hover(function(){
		clearInterval(_cartoon);
		var _nn = $(this).index();
		if(_nn != _index){
			_show(_index,_nn);
			}
		},function(){
			_cartoon = setInterval(function(){
		var _nn = _index;
		_index++;
		_show(_nn,_index);
		},_speed);
			});
}
//首页专用选项卡 - 本效果由昆明天度网络IRIS原创制作
function indexBoxShow(menuid,_mname,sumid,_sname,_hover){
	var _menu = $("#"+menuid);
	var _marr = _menu.find(_mname);
	var _sum = $("#"+sumid);
	var _sarr = _sum.find(_sname);
	var _index = _marr.length - 1;
	_marr.last().addClass(_hover);
	_marr.last().css("border-left","1px solid #eee");
	_marr.eq(0).css("border-right","1px solid #eee");
	_marr.eq(0).find("span").css("border","none");
	_sarr.last().css("display","block").siblings().css("display","none");
	_marr.hover(function(){
		_index = $(this).index();
		_marr.eq(_index).addClass(_hover).siblings().removeClass(_hover);
		_sarr.eq(_index).css("display","block").siblings().css("display","none");
		});
}
//楼层计算 - 本效果由昆明天度网络IRIS原创制作
function indexFloor(boxid,_name){
	var _box = $("#"+boxid);
	var _arr = _box.find(_name);
	
	var _floornum = function(_obj,_top,_bot){
		$(window).scroll(function(){
		  var _now = $(window).scrollTop();
		  if(_now>=_top && _now<_bot){
		  _obj.addClass("sel").siblings().removeClass("sel");
		  }
	       });
		};//fun END
	
	//_floornum(_arr.eq(0),0,1117);
	_floornum(_arr.eq(0),0,1920);
	_floornum(_arr.eq(1),1920,2715);
	_floornum(_arr.eq(2),2715,3510);
	_floornum(_arr.eq(3),3510,4300);
	_floornum(_arr.eq(4),4300,5400);
	
	$(window).scroll(function(){
		var _now = $(window).scrollTop();
		if(_box.is(":animated")){_box.stop(true,true);}
		if(_now > 1017 && _now < 4800){
			_box.fadeIn(200);
			}else{
			_box.fadeOut(200);
			}
		});
	
	var _gofloor = function(_seat){
		//$(window).scrollTop(_seat);
		$("html,body").animate({scrollTop:_seat},300);
		};//fun END
	_arr.eq(0).click(function(){ _gofloor(1127); });
	_arr.eq(1).click(function(){ _gofloor(1920); });
	_arr.eq(2).click(function(){ _gofloor(2715); });
	_arr.eq(3).click(function(){ _gofloor(3510); });
	_arr.eq(4).click(function(){ _gofloor(4300); });
}
//选择类型计算 - 本效果由昆明天度网络IRIS原创制作
function chooseMoreShow(){
	var _arr = $(".choose_box menu");
	var _length = _arr.length;
	if(_length > 4){
		for(var i=4;i<_length;i++){
			_arr.eq(i).css("display","none");
			}
	}//if END
}
function chooseMoreDown(){
	var _arr = $(".choose_box menu");
	var _length = _arr.length;
	if(_length > 4){
		var _str = "<a href='javascript:chooseMoreUp();'>收起选项<img src='images/content/arrow01.png' /></a>";
		$(".choose_more").html(_str);
		var _time = 200;
		for(var i=4;i<_length;i++){
			_time += 100;
			_arr.eq(i).slideDown(_time);
			}
	}
}
function chooseMoreUp(){
	var _arr = $(".choose_box menu");
	var _length = _arr.length;
	if(_length > 4){
		var _str = "<a href='javascript:chooseMoreDown();'>下拉，更多选项<img src='images/content/arrow01.png' /></a>";
		$(".choose_more").html(_str);
		var _time = 200;
		for(var i=4;i<_length;i++){
			_time += 100;
			_arr.eq(i).slideUp(_time);
			}
	}
}

//商品展示 - 本效果由昆明天度网络IRIS原创制作
function productImgShow(boxid,_name,_menuid,_hover,_width,_height){
	var _box = $("#"+boxid);
	var _menu = $("#"+_menuid);
	var _arr = _box.find(_name);
	_menu.html(_box.html());//创建menu部分
	var _sum = _menu.find(_name);
	var _index = 0;
	
	//这里是各个必须样式的写入
	_sum.eq(0).addClass(_hover);
	_arr.eq(0).css("display","block").siblings().css("display","none");
	_box.css({"width":_width+"px","height":_height+"px","position":"relative"});
	_arr.css({"width":_width+"px","height":_height+"px","overflow":"hidden","position":"absolute","left":"0","top":"0"});
	_arr.find("img").css({"width":_width+"px","height":_height+"px"});
	
	//加入放大框
	var _showwidth = 500;
	var _showheight = 500;
	var _boxshowstr = "<div class='myboxshowstr' style='position:absolute;top:0;left:"+_width+"px;width:"+_showwidth+"px;height:"+_showheight+"px;border:1px solid #ddd;background:#fff;display:none;overflow:hidden;margin-left:20px;transition-duration:0;'></div>";
	_box.append(_boxshowstr);
	var _cha = 100;
	var _showmovestr = "<div class='myboxmovestr' style='position:absolute;top:0;left:0;width:"+_cha+"px;height:"+_cha+"px;border:1px solid #ccc;background:#fff;z-index:10;opacity:0.5;filter:alpha(Opacity=50);-moz-opacity:0.5;display:none;transition: all 0s ease-in-out 0s;'></div>";
	_box.append(_showmovestr);
	var _showout = _box.find(".myboxshowstr");//放大镜盒子
	var _mouse = _box.find(".myboxmovestr");//鼠标移动100px框
	
	//放大镜鼠标移动事件
	var _score = _showwidth/_cha;
	
	_box.mousemove(function(e){
		
		var _nowX = e.pageX - _box.offset().left;
		var _nowY = e.pageY - _box.offset().top;
		var _moveX,_moveY;
		var _jian = _cha/2;
		if(_nowX <= _jian){_moveX=0;}
		if(_nowX > _jian){_moveX=_nowX-_jian;}
		if(_nowX >= _width-_jian){_moveX=_width-_cha;}
		if(_nowY <= _jian){_moveY=0;}
		if(_nowY > _jian){_moveY=_nowY-_jian;}
		if(_nowY >= _height-_jian){_moveY=_height-_cha;}
		
		_mouse.css({"left":_moveX+"px","top":_moveY+"px"});
		
		_moveX = -_moveX * _score;
		_moveY = -_moveY * _score;
		_showout.find("img").css({"left":_moveX+"px","top":_moveY+"px"});
		
		});//fun END
	_arr.hover(function(){
		_showout.html($(this).html());
		var _imgwidth = _width*_score;
		var _imgheight = _height*_score;
		_showout.find("img").css({"width":_imgwidth+"px","height":_imgheight+"px","position":"absolute"});
		});	
	_box.mouseenter(function(){
		_showout.fadeIn(100);
		_mouse.fadeIn(100);
		});
	_box.mouseleave(function(){
		_showout.fadeOut(100);
	    _mouse.fadeOut(100);
		});
	//menu切换
	_sum.hover(function(){
		_index = $(this).index();
		_arr.eq(_index).css("display","block").siblings().css("display","none");
		_sum.eq(_index).addClass(_hover).siblings().removeClass(_hover);
		});
	//alert(_arr.length);
	
	
}

function openMyWin(objid,_speed){
	$("#"+objid).slideToggle(_speed);
}

//顶部浮动 - 本效果由昆明天度网络IRIS原创制作
function topTitFloat(boxid,_top,_hover){
	var _box = $("#"+boxid);
	$(window).scroll(function(){
		var _now = $(window).scrollTop();
		if(_now > _top){
			_box.addClass(_hover);
			}else {
				_box.removeClass(_hover);
				}
	});
}
//login背景切换 - 本效果由昆明天度网络IRIS原创制作
function loginBgChange(boxid,_name,_speed){
	var _arr = $("#"+boxid+" "+_name);
	var _length = _arr.length;
	var _index = 0;
	_arr.eq(_index).siblings().css({"display":"none","opacity":"0"});
	
	var _nextShow = function(){
		var _last = _index;
		_index++;
		if(_index >= _length){ _index = 0; }
		_arr.eq(_index).css("display","block");
		_arr.eq(_index).animate({"opacity":"1"},500);
		_arr.eq(_last).animate({"opacity":"0"},500,function(){
			_arr.eq(_last).css("display","none");});
		};
	
	setInterval(_nextShow,_speed);
}

//左右箭头滚动 - 本效果由昆明天度网络IRIS原创制作
function arrowListGo(boxid,_sumname,_width,_num,_mga,_name,_lastid,_nextid){
	var _box = $("#"+boxid);
	var _sum =  _box.find(_sumname);
	var _arr = _box.find(_name);
	var _length = _arr.length;
	_box.css({"width":_width+"px","overflow":"hidden"});
	var _ww = (_width/_num)-(_mga*2);
	_arr.css({"width":_ww+"px","margin":"0 "+_mga+"px"});
	var _index = 0;
	
	var _nextGo = function(){
		_index++;
		if(_index > _length-_num){_index = _length-_num;}
		if(_sum.is(":animated")){
            _sum.stop(true,true);
           }
		var _move = -_index*(_mga*2+_ww);
		_sum.animate({left:_move+"px"},300);
	};
	
	var _lastGo = function(){
		_index--;
		if(_index < 0){_index = 0;}
		if(_sum.is(":animated")){
            _sum.stop(true,true);
           }
		var _move = -_index*(_mga*2+_ww);
		_sum.animate({left:_move+"px"},300);
	};
	
	
	
	if(_length > _num){
		$("#"+_nextid).click(function(){ _nextGo(); });
		$("#"+_lastid).click(function(){ _lastGo(); });
		}
}

//帮助中心 - 本效果由昆明天度网络IRIS原创制作
function comMenuList(boxid,_part,_name,_hover,_showbox){
	var _box = $("#"+boxid);
	var _arr = _box.find(_part);
	var _length = _arr.length;
	var _showlist = _box.find(_showbox);
	var _clicklist = _box.find(_name);
	_showlist.css("display","none");
	_clicklist.click(function(){
		var _obj = $(this).parent().find(_showbox);
		var _block = _obj.css("display");
		if(_block == "none"){
			_clicklist.removeClass(_hover);
	        _showlist.slideUp("fast");
			$(this).addClass(_hover);
	        _obj.slideToggle("fast");			
			}else{}
		});	
}
//选项卡 - 本效果由昆明天度网络IRIS原创制作
function productBoxShow(menuid,_mname,sumid,_sname,thisid,_tname,_hover){
	var _menu = $("#"+menuid);
	var _marr = _menu.find(_mname);
	var _sum = $("#"+sumid);
	var _sarr = _sum.find(_sname);
	var _show = $("#"+thisid);
	var _tarr = _show.find(_tname);
	var _index = 0;
	var _bindex = 0;
	
	var _startshow = function(){
		_marr.eq(_index).addClass(_hover).siblings().removeClass(_hover);
		var _nowol = _sarr.eq(_index);
		var _nowa = _nowol.find("a");
	    _nowol.css("display","block").siblings().css("display","none");
		_nowa.eq(_bindex).addClass(_hover).siblings().removeClass(_hover);
		var _nowul = _tarr.eq(_index);
		var _nowarr = _nowul.find("li");
	    _nowul.css("display","block").siblings().css("display","none");
		_nowarr.eq(_bindex).css("display","block").siblings().css("display","none");
		}; //fun END
	_startshow();
	
	_marr.hover(function(){
		if(_index != $(this).index()){ _bindex = 0; }
		_index = $(this).index();
		_startshow();	
		});
	
	_sum.find("a").hover(function(){
		_bindex = $(this).index();
		var _nowol = _sarr.eq(_index);
		var _nowa = _nowol.find("a");
		_nowa.eq(_bindex).addClass(_hover).siblings().removeClass(_hover);
		var _nowul = _tarr.eq(_index);
		var _nowarr = _nowul.find("li");
		_nowarr.eq(_bindex).css("display","block").siblings().css("display","none");
		});
}

function productBoxWidth(_name){
	var _arr = $(_name);
	var _length = _arr.length;
	for(var i=0;i<_length;i++){
		var _box = _arr.eq(i).find(".partside_out");
		var _sum = _arr.eq(i).find("div");
		var _num = _sum.length;
		var _ww = (_num-1)*160;
		_box.css({"width":_ww+"px"});
		_box.parent().css("height","190px");
		if(_num == 6){
			_box.parent().css({"overflow":"hidden","height":"172px"});
			}
	}	
}

function floatBoxQQ(){
	var _box = $("#floatbox");
	var _obj = $("#floatqq");
	var _menu = $("#floatboxlist");
	_obj.hover(function(){
		_menu.fadeIn(200);
		});
	_box.find(".a0").hover(function(){
		_menu.fadeOut(200);
		});
	_box.mouseleave(function(){
		_menu.fadeOut(200);
		});
}

