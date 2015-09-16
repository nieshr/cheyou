<#if user_page??>
    <#list user_page.content as user>
        <tr>
            <td align="center">
                <span class="checkall" style="vertical-align:middle;">
                    <input id="listChkId" type="checkbox" name="listChkId" value="${user_index}" >
                </span>
                <input type="hidden" name="listId" id="listId" value="${user.id?c}">
            </td>
            <td width="64">
              <a href="/Verwalter/user/edit?id=${user.id?c}">
                <img width="64" height="64" src="${user.headImageUri!"/mag/style/user_avatar.png"}">
              </a>
            </td>
            <td>
              <div class="user-box">
                <h4><b>${user.username!""}</b> (姓名：${user.realName!""})</h4>
                <i>注册时间：${user.registerTime!""}</i>
                <span>
                  <a class="amount" href="/Verwalter/user/point/list?userId=${user.id?c}" title="粮草">粮草</a>
                  <a class="point" href="/Verwalter/user/collect/list?userId=${user.id?c}" title="收藏商品">收藏商品</a>
                  <a class="msg" href="/Verwalter/user/recent/list?userId=${user.id?c}" title="浏览历史">浏览历史</a>
                  <#if user.roleId?? && user.roleId==1>
                      <a class="sms" href="/Verwalter/user/reward/list?userId=${user.id?c}" title="返现记录">返现记录</a>
                  </#if>
                </span>
              </div>
            </td>
            <td><#if user.roleId?? && user.roleId==0>普通会员<#elseif user.roleId?? && user.roleId==1>同盟店</#if></td>
            <td align="center">${user.email!""}</td>
            <td align="center">${user.mobile!""}</td>
            <td align="center">${user.lastLoginTime!""}</td>
            <td align="center">${user.totalPoints!""}                    
            </td>
            <td align="center"><#if user.statusId??><#if user.statusId==0>待审核<#elseif user.statusId==1>正常</#if></#if></td>
            <td align="center">
                <a href="/Verwalter/user/edit?id=${user.id?c}&roleId=${roleId!""}">修改</a> | 
                <a href="/Verwalter/user/edit?id=${user.id?c}&roleId=${roleId!""}&action=view">查看</a></td>
          </tr>
    </#list>
</#if>