<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>暂无标题</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="copyright" content="" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="/touch/js/jquery-1.9.1.min.js"></script>
<script src="/touch/js/common.js"></script>


<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
  searchTextClear(".logintext01","手机号/邮箱","#999","#333");
  searchTextClear(".logintext02","输入验证码","#999","#333");
});
</script>

<script type="text/javascript">
	function check(){
		var informationText = document.getElementById("informationText");
		var identity = document.getElementById("identity").value.trim();
		var email = document.getElementById("email").value.trim();
		var telephone = document.getElementById("telephone").value.trim();
		var carCode = document.getElementById("carCode").value.trim();
		if((identity!="")&&(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(identity)==false)){
			informationText.innerHTML="*身份证号不正确！";
			return;
		}
		if((email!="")&&(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email)==false)){
			informationText.innerHTML="*邮箱格式不正确！";
			return;
		}
		if((telephone!="")&&(/^(13[0-9]{9})|(15[89][0-9]{8})$/.test(telephone)==false)){
			informationText.innerHTML="*手机号码格式不正确！";
			return;
		}
		if((carCode!="")&&(/^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(carCode)==false)){
			informationText.innerHTML="*车牌号格式不正确！";
			return;
		}
		informationText.innerHTML="";
		var informationForm = document.getElementById("informationForm");
		informationForm.submit();
	}
</script>

</head>

<body>
	<header class="comhead">
		<div class="main">
			<p>个人信息设置</p>
			<a class="a1" href="javascript:history.go(-1)">返回</a>
		   	<a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
		</div>
	</header>
		
	<form id="informationForm" action="/touch/user/information" method="post">
		<div class="comhead_bg"></div>
		<p class="address">昵称</p>
		<input type="text" class="address" name="nickname" value="${user.nickname!}" />
		<p class="address">性别</p>
		<p class="address">
			<#if user.sex=="女">
		  		<input type="radio" name="sex" value="男" /><span>男</span>
		  		<input type="radio" name="sex" class="ml20" value="女" checked="checked"/><span>女</span>
		 	<#else>
		 		<input type="radio" name="sex" value="男" checked="checked"/><span>男</span>
		  		<input type="radio" name="sex" class="ml20" value="女"/><span>女</span>
			</#if>
		</p>
		<p class="address">真实姓名</p>
		<input type="text" class="address" name="realName" value="${user.realName!}" />
		<p class="address">身份证号码</p>
		<input type="text" class="address" id="identity" name="identity" value="${user.identity!}" />
		<p class="address">电子邮箱</p>
		<input type="text" class="address" id="email" name="email" value="${user.email!}" />
		<p class="address">手机号码</p>
		<input type="text" class="address" id="telephone" name="telephone" value="${user.telephone!}" />
		<p class="address">车牌号码</p>
		<input type="text" class="address" id="carCode" name="carCode" value="${user.carCode!}" />
		<#--
			<p class="address">地址</p>
			<div class="address">
		 		<select class="fl"><option>省</option></select>
		  		<select class="fr"><option>市</option></select>
		  		<div class="clear  h10"></div>
		  		<select style="width:100%;"><option>区</option></select>
			</div>
		-->
		
		<br/>
		<label style="color:red;font-size:0.8em;" id="informationText" class="address"></label> 
		<input type="button" onclick="check()" class="greenbtn fs11" style="width:90%;" value="保存" />
	</form>
</body>
</html>
