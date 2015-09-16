//滚动系列 ↓↓
jQuery.ljs_gundong = {
//左右单张滚动
	oneLeft:function(_obj){
		var _box = $("#"+_obj.boxid);
		var _sum = $("#"+_obj.sumid); 
		var _style = _obj.stylename;
		var _width = _obj.boxwidth;
		var _height = _obj.boxheight;
		var _speed = _obj.speed;
		var _last = $("#"+_obj.leftid);
		var _next = $("#"+_obj.rightid);
		var _owidth = _obj.objwidth;
		var _oheight = _obj.objheight;
		var _arr = _box.find("."+_style);
		var _length = _arr.length;
	    //设定必要样式
		_box.css({"width":_width+"px","height":_height+"px","position":"relative","overflow":"hidden"});
		_arr.css({"float":"left","display":"block","position":"relative","width":_owidth+"px","height":_oheight+"px","overflow":"hidden"});
		_sum.css({"width":"99999px","position":"absolute","top":"0px","left":"0px"});
		_sum.append(_sum.html());
		var _index = 0;
		
		//动画
		var nextGo = function(){
		  if(_sum.is(":animated")){
			_sum.stop(true,true);
		    }
		  _index++;
		  var _move = 0;
		  if(_index >= _length){
			  _index = 0;
			  _move = -_length * _owidth;
			  _sum.animate({left:_move+"px"},_speed,function(){
				  _sum.css("left","0px");
				  });
			  }else {
				  _move = -_index * _owidth;
				  _sum.animate({left:_move+"px"},_speed);
				  }
			};
		_next.click(function(){nextGo();});
		
		var lastGo = function(){
		  if(_sum.is(":animated")){
			_sum.stop(true,true);
		    }
		  _index--;
		  var _move = 0;
		  if(_index < 0){
			  _move = -_length * _owidth;
			  _sum.css("left",_move+"px");
			  _index = _length - 1;
			  }
			  _move = -_index * _owidth;
			  _sum.animate({left:_move+"px"},_speed);
			};
		_last.click(function(){lastGo();});
		
		if(_obj.autogo){
			var cartoon = setInterval(nextGo,_obj.gospeed);
			}
	    
	},//左右单张滚动 END
	
//左右连续滚动↓↓↓
    allLeft:function(_obj){
		var _out = document.getElementById(_obj.boxid);
		var _box = $("#"+_obj.boxid);
		var _sum = $("#"+_obj.sumid); 
		var _style = _obj.stylename;
		var _width = _obj.boxwidth;
		var _height = _obj.boxheight;
		var _speed = _obj.speed;
		var _arr = _box.find("."+_style);
		var _length = _arr.length;
		
		var _maxl = 0;
		for(var i=0;i<_length;i++){
			_maxl += _arr.eq(i).width();	
		}
		
		_box.css({"width":_width+"px","height":_height+"px","position":"relative","overflow":"hidden"});
		_arr.css({"float":"left","position":"relative"});
		_sum.css({"width":"99999px","position":"absolute","top":"0px","left":"0px"});
		_sum.append(_sum.html());
		
		var _cartoon = setInterval(function(){
			if(_out.scrollLeft >= _maxl){
			_out.scrollLeft = 0;
			}
		    _out.scrollLeft++;
		},_speed);
		
		_box.hover(function(){
		    clearInterval(_cartoon);
		},function(){
			_cartoon = setInterval(function(){
		if(_out.scrollLeft >= _maxl){
			_out.scrollLeft = 0;
			}
		_out.scrollLeft++;
		},_speed);
			});
		
},//左右滚动 END

//左右列表切换↓↓↓
  manyListGo:function(_obj){
	  var _box = $("#"+_obj.boxid);
	  var _sum = $("#"+_obj.sumid);
	  var _part = _obj.partcl;
	  var _width = _obj.boxwidth;
	  var _height = _obj.boxheight;
	  var _speed = _obj.speed;
	  var _arr = _sum.find("."+_part);
	  var _length = _arr.length;
	  var _last = $("#"+_obj.lastid);
	  var _next = $("#"+_obj.nextid);
	  var _index = 0;
	  
	  _box.css({"width":_width+"px","height":_height+"px","position":"relative","overflow":"hidden"});
	  _arr.css({"width":_width+"px","height":_height+"px","position":"relative","overflow":"hidden","float":"left"});
	  _sum.css({"width":"99999px","position":"absolute","top":"0px","left":"0px"});
	  
	  var nextCartoon = function(){
		  _index++;
		  var _move = 0;
		  if(_index >= _length){
			  _index = 0;
			  _move = 0;
			  }else {
				  _move = -_index * _width;
				  }
				  
		  if(_sum.is(":animated")){
			_sum.stop(true,true);
		    }  
		  _sum.animate({left:_move+"px"},_speed);
		  };
	  _next.click(function(){nextCartoon();});
	  
	  var lastCartoon = function(){
		  _index--;
		  var _move = 0;
		  if(_index < 0){
			  _index = _length - 1;
			  _move = -_index * _width;
			  }else {
				  _move = -_index * _width;
				  }
		  
		  if(_sum.is(":animated")){
			_sum.stop(true,true);
		    }  
		  _sum.animate({left:_move+"px"},_speed);
		  };
	  _last.click(function(){lastCartoon();});
	  
	  if(_obj.autogo){
		  var _cartoon = setInterval(nextCartoon,_obj.gospeed);
		  }
	   
}//左右列表切换 END

	
};
//滚动系列 ↑↑ END

//鼠标悬停系列 ↓↓
jQuery.ljs_mouhover = {
	//黑色蒙版
	darkMask:function(_obj){
		
	}//黑色蒙版 END
	
};
//鼠标悬停系列 ↑↑ END



//幻灯片系列 ↓↓
jQuery.ljs_adcartoon = {
	//左右滚动
	arrowNum:function(_obj){
		var _box = $("#"+_obj.boxid);
		var _sum = $("#"+_obj.sumid); 
		var _li01 = $("#"+_obj.li01);
		var _li02 = $("#"+_obj.li02);
		var _width = _obj.boxwidth;
		var _height = _obj.boxheight;
		var _speed = _obj.speed;
		var _go = _obj.gospeed;
		var _pre = $("#"+_obj.preid);
		var _next = $("#"+_obj.nextid);
		var _style = _obj.stylename;
		var _arr = _li01.find("."+_style);
		
		_box.css({"width":_width+"px","height":_height+"px","position":"relative","overflow":"hidden"});
		_sum.css({"width":"99999px","position":"absolute","top":"0px","left":"0px"});
		_li01.css("float","left");
		_li02.css("float","left");
		_arr.css({"float":"left","position":"relative"});
		
		
		//插入右下角数值
	 var _numstr = "<div id='"+_obj.numid+"'>";//注意div的id
	 for(var i=1;i<=_arr.length;i++){
	 	_numstr += "<a href='#'>"+i+"</a>";
	 	}
	 _numstr += "</div>";
	 _box.append(_numstr);
	 var _numarr = $("#"+_obj.numid+" > a");//获取数字组
	 var _index = 0;//索引值
	 _numarr.eq(0).addClass("sel");
	 var _num = $("#"+_obj.numid);
	 _li02.html(_li01.html());//复制同组图片
	 
	 //动画方法定义
	 var _left = -(_arr.length * _width) + "px";//计算左边位置
	 
	 var leftGo = function(){
		 /*var ls = _sum.css("left");
	 	  if(ls == _left){
	 	  	_sum.css("left","0px");
	 	  	}*/ 
		  if(_sum.is(":animated")){
				_sum.stop(true,true);
			}	
	 	  _numarr.removeClass("sel");
		  
	 	  if(_index < _arr.length){//判断索引变化
	 	  	_numarr.eq(_index+1).addClass("sel");
	 	  	}else{
	 	  	  _numarr.eq(0).addClass("sel");
	 	  	  _index = 0;
			  _sum.css("left","0px");
	 	  		}
	 	  _sum.animate({left:"-="+_width+"px"},_go);//此处速度需小于滚动间隔时间
		  _index++;
	};
	
	var rightGo = function(){
		/*var ls = _sum.css("left");
	 	  var mm = "0px";
	 	  if(ls == mm){
	 	  	_sum.css("left",_left);
	 	  	}*/
	 	  if(_sum.is(":animated")){
				_sum.stop(true,true);
			}	
	 	  _numarr.removeClass("sel");
		  
	 	  if(_index > 0){//判断索引变化
	 	  	_numarr.eq(_index-1).addClass("sel");
	 	  	}else{
	 	  	  _numarr.eq(_arr.length-1).addClass("sel");
	 	  	  _index = _arr.length;
			  _sum.css("left",_left);
	 	  		}
						
	 	  _sum.animate({left:"+="+_width+"px"},_go);
		  _index--;
	};
	 
	 var cartoon = setInterval(leftGo,_speed);
     _box.hover(function(){
		 clearInterval(cartoon);
		 },function(){
			 cartoon = setInterval(leftGo,_speed);
			 });
	
	   _numarr.bind("mouseenter",function(){
	 	  _index = _numarr.index(this);
	 	  $(this).addClass('sel').siblings().removeClass('sel');
	 	  var _numsel =-(_index * _width) + "px";
	 	  _sum.animate({left:_numsel},_go);
	 	});
		
		_pre.bind("mousedown",function(){//注意ID
	 	  rightGo();
	 	});
	   _next.bind("mousedown",function(){//注意ID
		  leftGo();
	 	});

	}//左右滚动 END
	
};
//幻灯片系列 ↑↑ END