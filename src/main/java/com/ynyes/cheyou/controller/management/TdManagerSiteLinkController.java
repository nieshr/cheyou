package com.ynyes.cheyou.controller.management;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdManager;
import com.ynyes.cheyou.entity.TdManagerRole;
import com.ynyes.cheyou.entity.TdSiteLink;
import com.ynyes.cheyou.service.TdManagerLogService;
import com.ynyes.cheyou.service.TdManagerRoleService;
import com.ynyes.cheyou.service.TdManagerService;
import com.ynyes.cheyou.service.TdSiteLinkService;
import com.ynyes.cheyou.util.SiteMagConstant;

/**
 * 后台友情链接管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/sitelink")
public class TdManagerSiteLinkController {
    
    @Autowired
    TdSiteLinkService tdSiteLinkService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    @RequestMapping(value="/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(String param, Long id) {
        Map<String, String> res = new HashMap<String, String>();
        
        res.put("status", "n");
        
        if (null == param || param.isEmpty())
        {
            res.put("info", "该字段不能为空");
            return res;
        }
        
        if (null == id)
        {
            if (null != tdSiteLinkService.findByTitle(param))
            {
                res.put("info", "已存在同名友情链接");
                return res;
            }
        }
        else
        {
            if (null != tdSiteLinkService.findByTitleAndIdNot(param, id))
            {
                res.put("info", "已存在同名友情链接");
                return res;
            }
        }
        
        res.put("status", "y");
        
        return res;
    }
    
    @RequestMapping(value="/list")
    public String setting(Integer page,
                          Integer size,
                          String keywords,
                          String __EVENTTARGET,
                          String __EVENTARGUMENT,
                          String __VIEWSTATE,
                          Long[] listId,
                          Integer[] listChkId,
                          Long[] listSortId,
                          ModelMap map,
                          HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        //管理员角色
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        TdManagerRole tdManagerRole = null;
        
        if (null != tdManager.getRoleId())
        {
            tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
        }
        
        if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}
        
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除友情链接", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "修改友情链接", req);
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        if (null != keywords)
        {
            keywords = keywords.trim();
        }
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        Page<TdSiteLink> siteLinkPage = null;
        
        if (null == keywords || "".equalsIgnoreCase(keywords))
        {
            siteLinkPage = tdSiteLinkService.findAllOrderBySortIdAsc(page, size);
        }
        else
        {
            siteLinkPage = tdSiteLinkService.searchAndOrderBySortIdAsc(keywords, page, size);
        }
        
        map.addAttribute("site_link_page", siteLinkPage);
        
        return "/site_mag/site_link_list";
    }
    
    @RequestMapping(value="/edit")
    public String orderEdit(Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        if (null != id)
        {
            map.addAttribute("site_link", tdSiteLinkService.findOne(id));
        }
        return "/site_mag/site_link_edit";
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdSiteLink tdSiteLink,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null == tdSiteLink.getId())
        {
            tdManagerLogService.addLog("add", "用户修改友情链接", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "用户修改友情链接", req);
        }
        
        tdSiteLinkService.save(tdSiteLink);
        
        return "redirect:/Verwalter/sitelink/list";
    }

    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
                        Model model) {
        if (null != id) {
            model.addAttribute("tdSiteLink", tdSiteLinkService.findOne(id));
        }
    }
    
    private void btnSave(Long[] ids, Long[] sortIds)
    {
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            TdSiteLink e = tdSiteLinkService.findOne(id);
            
            if (null != e)
            {
                if (sortIds.length > i)
                {
                    e.setSortId(sortIds[i]);
                    tdSiteLinkService.save(e);
                }
            }
        }
    }
    
    private void btnDelete(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                tdSiteLinkService.delete(id);
            }
        }
    }
}
