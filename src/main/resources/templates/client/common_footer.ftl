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
