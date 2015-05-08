package com.ynyes.rongcheng.controller.management;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.rongcheng.entity.TdManager;
import com.ynyes.rongcheng.entity.TdNavigationMenu;
import com.ynyes.rongcheng.entity.TdSetting;
import com.ynyes.rongcheng.service.TdManagerService;
import com.ynyes.rongcheng.service.TdNavigationMenuService;
import com.ynyes.rongcheng.service.TdSettingService;

/**
 * 后台首页控制器
 * 
 * @author Sharon
 */
@Controller
public class TdManagerIndexController {
    
    @Autowired
    TdNavigationMenuService tdNavigationMenuService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    @Autowired
    TdSettingService tdSettingService;
    
    @RequestMapping(value="/admin")
    public String index(ModelMap map, HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/admin/login";
        }
        
        List<TdNavigationMenu> rootMenuList = tdNavigationMenuService.findByParentIdAndSort(0L);
        
        if (null != rootMenuList && rootMenuList.size() > 0)
        {
            for (int i=0; i < rootMenuList.size(); i++)
            {
                TdNavigationMenu rootMenu = rootMenuList.get(i);
                
                // 取一级菜单列表
                List<TdNavigationMenu> level0MenuList = tdNavigationMenuService.findByParentIdAndSort(rootMenu.getId());
                
                if (null != level0MenuList && level0MenuList.size() > 0)
                {
                    map.addAttribute("level_" + i + "_menu_list", level0MenuList);
                    
                    // 取二级菜单列表
                    for (int j=0; j < level0MenuList.size(); j++)
                    {
                        TdNavigationMenu level0Menu = level0MenuList.get(j);
                        
                        // 取二级菜单列表
                        List<TdNavigationMenu> level1MenuList = tdNavigationMenuService.findByParentIdAndSort(level0Menu.getId());
                        
                        if (null != level1MenuList && level1MenuList.size() > 0)
                        {
                            map.addAttribute("level_" + i + j + "_menu_list", level1MenuList);
                        }
                        
                    }
                }
                
            }
        }
        
        map.addAttribute("root_menu_list", rootMenuList);
        
        return "/site_mag/frame";
    }
    
    @RequestMapping(value="/admin/center")
    public String center(ModelMap map, HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/admin/login";
        }
        Properties props = System.getProperties();
        
        map.addAttribute("os_name", props.getProperty("os.name") 
                            + " " + props.getProperty("os.version"));
        map.addAttribute("java_home", props.getProperty("java.home"));
        map.addAttribute("java_version", props.getProperty("java.version"));
        map.addAttribute("remote_ip", req.getRemoteAddr());
        map.addAttribute("server_name", req.getServerName());
        map.addAttribute("server_ip", req.getLocalAddr());
        map.addAttribute("server_port", req.getServerPort());
        
        TdSetting setting = tdSettingService.findTopBy();
        
        map.addAttribute("site_name", setting.getTitle());
        map.addAttribute("company_name", setting.getCompany());
        
        if (!username.equalsIgnoreCase("admin"))
        {
            TdManager manager = tdManagerService.findByUsernameAndIsEnableTrue(username);
            map.addAttribute("last_ip", manager.getLastLoginIp());
            map.addAttribute("last_login_time", manager.getLastLoginTime());
        }
        
        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        
        map.addAttribute("client_ip", ip);
        
        return "/site_mag/center";
    }
    
}