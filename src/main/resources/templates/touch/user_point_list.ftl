<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>暂无标题</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="copyright" content="" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/common.js"></script>

<link href="/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="/touch/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
  searchTextClear(".logintext01","手机号/邮箱","#999","#333");
  searchTextClear(".logintext02","输入验证码","#999","#333");
});
</script>
</head>

<body>
<header class="comhead">
  <div class="main">
    <p>我的粮草</p>
    <a class="a1" href="javascript:history.go(-1);">返回</a>
    <a class="a2" href="/touch"><img src="/touch/images/home.png" height="25" /></a>
  </div>
</header>
<div class="comhead_bg"></div>

<p class="ta-c lh40">
    <img  src="/touch/images/lc.png" width="40%" height="40%" />
    <br />
           当前拥有粮草为：
       <span class="red">
         <#if point_page?? && point_page.content?? && point_page.content[0]??>
            ${point_page.content[0].totalPoint!'0'}
            <#else>
             0
          </#if>
                            个 
       </span>
</p>
</body>
</html>
