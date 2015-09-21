package com.ynyes.cheyou.controller.management;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ynyes.cheyou.entity.TdDemand;
import com.ynyes.cheyou.entity.TdManager;
import com.ynyes.cheyou.entity.TdManagerRole;
import com.ynyes.cheyou.entity.TdServiceItem;
import com.ynyes.cheyou.entity.TdSetting;
import com.ynyes.cheyou.entity.TdUserComment;
import com.ynyes.cheyou.entity.TdUserSuggestion;
import com.ynyes.cheyou.service.TdDemandService;
import com.ynyes.cheyou.service.TdManagerLogService;
import com.ynyes.cheyou.service.TdManagerRoleService;
import com.ynyes.cheyou.service.TdManagerService;
import com.ynyes.cheyou.service.TdServiceItemService;
import com.ynyes.cheyou.service.TdSettingService;
import com.ynyes.cheyou.service.TdUserSuggestionService;
import com.ynyes.cheyou.util.SiteMagConstant;

/**
 * 后台广告管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/setting")
public class TdManagerSettingController {
    
    @Autowired
    TdSettingService tdSettingService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdServiceItemService tdServiceItemService;
    
    @Autowired
    TdUserSuggestionService tdUserSuggestionService;
    
    @Autowired
    TdDemandService tdDemandService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    @RequestMapping
    public String setting(Long status, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("setting", tdSettingService.findTopBy());
        map.addAttribute("status", status);
        
        return "/site_mag/setting_edit";
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdSetting tdSetting,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == tdSetting.getId())
        {
            tdManagerLogService.addLog("add", "用户修改系统设置", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "用户修改系统设置", req);
        }
        
        tdSettingService.save(tdSetting);
        
        return "redirect:/Verwalter/setting?status=1";
    }
    
    @RequestMapping(value="/service/list")
    public String service(String __EVENTTARGET,
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
            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                
                tdManagerLogService.addLog("edit", "删除服务", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                
                tdManagerLogService.addLog("edit", "修改服务", req);
            }
        }

        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
                
        map.addAttribute("service_item_list", tdServiceItemService.findAllOrderBySortIdAsc());
                
        return "/site_mag/service_item_list";
    }
    
    /**
     * 后台投诉查看页面跳转
     * @author Zhangji
     * 
     */
    @RequestMapping(value="/suggestion/list")
    public String suggestion( String __EVENTTARGET,
                        String __EVENTARGUMENT,
                        String __VIEWSTATE,
                        Integer page,
                        Integer size,
                        Long id,
                        String name,
                        String content,
                        String mail,
                        Long mobile,
                        Long statusId,
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
                btnDeleteSuggesiton( listId, listChkId);
                tdManagerLogService.addLog("delete", "删除投诉", req);
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
     
       map.addAttribute("page", page);
       map.addAttribute("size", size);

        Page<TdUserSuggestion> suggestionPage = null;
       
        suggestionPage = tdUserSuggestionService.findAllOrderByTimeDesc(page, size);
                   
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        map.addAttribute("suggestion_page", suggestionPage);
        
        return "/site_mag/suggestion_list";
    }
//    /**
//     * 删除投诉
//     * @param req
//     * @param id
//     * @param map
//     * @return
//     */
//    @RequestMapping(value = "/suggestion/delete")
//    public String address(HttpServletRequest req,                        
//                        Long id,
//                   //     TdUserSuggestion tdUserSuggestion,
//                        ModelMap map){
//    	TdUserSuggestion tdUserSuggestion = new TdUserSuggestion();
//    	tdUserSuggestion.setId(id);
//    	tdUserSuggestionService.delete(id);
//    	map.addAttribute("suggestion_page",tdUserSuggestion);
//    	
//    	return "/setting/suggestion_list";
//    }
//    
//    
//  ///////////////////////////////////
    
    /**
     * 后台“车友还想团购”查看页面跳转
     * @author Zhangji
     * 
     */
    @RequestMapping(value="/demand/list")
    public String demand( String __EVENTTARGET,
                        String __EVENTARGUMENT,
                        String __VIEWSTATE,
                        Long statusId,
                        Integer page,
                        Integer size,
                        Long id,
                        String name,
                        String content,
                        String mail,
                        Long mobile,
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
                btnDeleteDemand(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除demand", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnVerify"))
            {
            	btnVerifyDemand(listId,listChkId);
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
     
       map.addAttribute("page", page);
       map.addAttribute("size", size);

        Page<TdDemand> tdDemandPage = null;
       
        tdDemandPage = tdDemandService.findAllOrderByTimeDesc(page, size);
                   
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        map.addAttribute("demand_page", tdDemandPage);
        
        return "/site_mag/demand_list";
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    @RequestMapping(value="/demand/edit")
    public String demandEdit(Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null != id)
        {
            map.addAttribute("demand_item", tdDemandService.findOne(id));
        }
        
        return "/site_mag/demand_edit";
    }
    
    @RequestMapping(value="/demand/save")
    public String demandSave(TdDemand tdDemand,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null == tdDemand.getIsReplied() || !tdDemand.getIsReplied())
        {
        	tdDemand.setIsReplied(true);
        	tdDemand.setReplyTime(new Date());
        }
               
        tdManagerLogService.addLog("edit", "回复用户还想团", req);
        
        
        
        tdDemandService.save(tdDemand);
        
        return "redirect:/Verwalter/setting/demand/list?statusId=" + __VIEWSTATE;
    }
    
    @RequestMapping(value="/service/edit")
    public String edit(Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null != id)
        {
            map.addAttribute("service_item", tdServiceItemService.findOne(id));           
        }
        
        return "/site_mag/service_item_edit";
    }
    
    @RequestMapping(value="/service/save", method = RequestMethod.POST)
    public String serviceItemEdit(TdServiceItem tdServiceItem,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        tdServiceItemService.save(tdServiceItem);
        
        tdManagerLogService.addLog("edit", "修改商城服务", req);
        
        return "redirect:/Verwalter/setting/service/list";
    }

    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "serviceItemId", required = false) Long serviceItemId,
                            @RequestParam(value = "demandId", required = false) Long demandId,
                            ModelMap map) {
        if (null != id) {
            map.addAttribute("tdSetting", tdSettingService.findOne(id));
        }
        
        if (null != demandId) {
            map.addAttribute("tdDemand", tdDemandService.findOne(demandId));
        }
        
        if (null != serviceItemId) {
            TdServiceItem serviceItem = tdServiceItemService.findOne(serviceItemId);
            map.addAttribute("tdServiceItem", serviceItem);
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
            
            TdServiceItem e = tdServiceItemService.findOne(id);
            
            if (null != e)
            {
                if (sortIds.length > i)
                {
                    e.setSortId(sortIds[i]);
                    tdServiceItemService.save(e);
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
                
                tdServiceItemService.delete(id);
            }
        }
    }
    
    /**
     * 删除团购要求
     * @author Zhangji
     * 2015年7月30日12:47:56
     * @param ids
     * @param chkIds
     */
    private void btnDeleteDemand(Long[] ids, Integer[] chkIds)
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
                
                tdDemandService.delete(id);
            }
        }
    }
    /**
     * 审核团购要求
     * @author Zhangji
     * 2015年7月30日13:24:06
     * @param ids
     * @param chkIds
     */
    private void btnVerifyDemand(Long[] ids, Integer[] chkIds)
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
                
                TdDemand e = tdDemandService.findOne(id);
                if (null != e)
                {
                	e.setStatusId(1L);
                	 tdDemandService.save(e);
                }
               
            }
        }
    }
    
    /**
     * 删除投诉
     * @author Zhangji
     * 2015年7月30日13:29:18
     * @param ids
     * @param chkIds
     */
    private void btnDeleteSuggesiton(Long[] ids, Integer[] chkIds)
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
                
                tdUserSuggestionService.delete(id);
            }
        }
    }
}
