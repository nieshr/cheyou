<div class="down">
    <section class="index_center">
        <table>
            <#if service_item_list??>
                <tr>
                    <#list service_item_list as item>
                        <td>
                            <a href="javascript:;"><img src="${item.logo!''}" />${item.title!''}<br>${item.description!''}</a>
                        </td>
                    </#list>
                </tr>
            </#if>
        </table>
    </section><!--index_center END-->
</div>
<div class="down2">
    <div class="main">
        <ul style="height:30px;">
            <#if help_level0_cat_list??>
                <#list help_level0_cat_list as item>
                    <li><a href="/info/list/${help_id!'0'}?catId=${item.id!''}">${item.title!''}</a></li>
                </#list>
            </#if>
        </ul>
        
        <div class="clear"></div>
        
        <p>
        友情链接： 
        <#if site_link_list??>
            <#list site_link_list as item>
                <span class="youqin"><a href="${item.linkUri!''}">${item.title!''}</a></span>
            </#list>
        </#if>
        <br />
        ${site.copyright!''}
        <br />
        ${site.address!''} 电话：${site.telephone!''}
        <br />
        <span class="flr"><a title="天度网络信息技术有限公司" href="http://www.ynyes.com" target="_blank">网站建设</a>技术支持：<a title="天度网络信息技术有限公司" href="http://www.ynyes.com" target="_blank">昆明天度网络公司</a></span>
            <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254586643'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1254586643%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    
        </p>
    </div>
</div>

<<<<<<< HEAD
<script type="text/javascript">
$(document).ready(function(){
  $(".mianfeilingqutanchu .yincang").click(function(){
    $(this).parents(".mianfeilingqutanchu").hide("slow");
  });
});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".mianfeilingqutanchu #xianshi").click(function(){
      $(this).parents(".mianfeilingqutanchu").show();
  });
});
</script>
=======
<#--
<div class="mianfeilingqutanchu">
                  <div class="mianfeilingqutanchu_dl">
                    <div class="main bgff">
<div class="tousuyemian">
   <div class="rightb_gundong fr"><a href="#"><img src="images/20150407114113116_easyicon_net_71.8756476684.png" width="21" height="21" /></a></div>
        <div class="clear"></div>
            <h3>我要投诉</h3>
                <p>在您填写下列投诉内容之前，我们首先代表车有同盟为导致您进行投诉的原因（行为）表示歉意，请详细描述事件经过，以便我们尽快为您解决问题，我们一定会及时处理，给您一个满意的解决方案，您的满意是我们最大的动力，谢谢！</p>
                    <div class="tousuneirong">
<span>投诉对象</span>
<input type="checkbox" class="fll duoxuank ml5 mr5">同盟店
<input type="checkbox" class="fll duoxuank ml5 mr5">商品
<input type="checkbox" class="fll duoxuank ml5 mr5">服务
<div class="clear h10"></div>
<span>线下同盟店</span><select class="ml20"><option>学府路店</option><lect>
<div class="clear h10"></div>
<span>订单编号</span><input name="et_contact_name"  onfocus="if(value=='订单编号') {value=''}" onblur="if (value=='订单编号') {value='订单编号'}"  value="" id="et_contact_name" class="input ml20" type="text">
<div class="clear h10"></div>
<span>投诉内容</span>
<div class=" clear"></div>
<textarea class="input"  onfocus="if(value=='') {value=''}" onblur="if (value=='') {value=''}"  value=""  id="et_contact_message" name="et_contact_message"></textarea><b class="red">*</b>
</div>
<div class="tousulianxifangshi mt20">
<p>为了尽快为您解决问题，请提供您的联系方式，谢谢。</p>
<span>称呼</span><input name="et_contact_name"  onfocus="if(value=='订单编号') {value=''}" onblur="if (value=='订单编号') {value='订单编号'}"  value="" id="et_contact_name" class="input ml20" type="text"><div class="clear h10"></div>
>>>>>>> refs/remotes/origin/master


<<<<<<< HEAD

          
=======
<span>联系方式</span><input name="et_contact_name"  onfocus="if(value=='订单编号') {value=''}" onblur="if (value=='订单编号') {value='订单编号'}"  value="" id="et_contact_name" class="input ml20" type="text"><b class="red">*</b>
<div class="clear h20"></div>
<input class="Message_an" type="submit" name="button2" id="button2" value="提交" title="提交" />
</div>
<div class="tousubeizhu mt5">
<h4>备注</h4>
<p>1、您还可以拨打13987671655进行电话投诉<br />
2、您还可以扫描下方二维码进行微信投诉</p>
<img class="ml100" src="images/sys02.png" width="84" height="84" />
</div>  
</div>
-->
>>>>>>> refs/remotes/origin/master
