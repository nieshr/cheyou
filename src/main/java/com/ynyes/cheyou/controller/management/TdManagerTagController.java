package com.ynyes.cheyou.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.cheyou.entity.TdManager;
import com.ynyes.cheyou.entity.TdManagerRole;
import com.ynyes.cheyou.entity.TdTag;
import com.ynyes.cheyou.service.TdManagerLogService;
import com.ynyes.cheyou.service.TdManagerRoleService;
import com.ynyes.cheyou.service.TdManagerService;
import com.ynyes.cheyou.service.TdTagService;
import com.ynyes.cheyou.util.SiteMagConstant;
/**
 * 后台标签管理控制器
 * 
 * @author libiao
 *
 */

@Controller
@RequestMapping(value="/Verwalter/tag")
public class TdManagerTagController {

	@Autowired
	TdTagService tdTagService;
	
	@Autowired
	TdManagerLogService tdManagerLogService;
	
	@Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
	
	@RequestMapping(value="/list")
	public String tag(Integer page,
            Integer size,
            String __EVENTTARGET,
            String __EVENTARGUMENT,
            String __VIEWSTATE,
            Long[] listId,
            Long typeId,
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
                tdManagerLogService.addLog("delete", "用户删除标签", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "用户修改标签", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
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
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("typeId",typeId);
        
        if(null == typeId)
        {
        	map.addAttribute("tag_page",tdTagService.findAllBySortIdAsc(page,size));
        }else{
        	map.addAttribute("tag_page",tdTagService.findByTypeIdBySortIdAsc(typeId, page, size));
        }
        
		return "/site_mag/tag_list";
	}
	
	
	@RequestMapping(value="/edit")
	public String tagEdit(Long id,
			String _VIEWSTATE,
			ModelMap map,
			HttpServletRequest req
			){
		String username = (String) req.getSession().getAttribute("manager");
	    if (null == username)
	    {
	        return "redirect:/Verwalter/login";
	    }
	    map.addAttribute("_VIEWSTATE",_VIEWSTATE);
	    if(null !=id){
	    	map.addAttribute("tag",tdTagService.findOne(id));
	    }
	    
		return "/site_mag/tag_edit";
	}
	
	@RequestMapping(value="save")
	public String tagSave(TdTag tdTag,
			String __VIEWSTATE,
			ModelMap map,
			HttpServletRequest req
			){
		 String username = (String) req.getSession().getAttribute("manager");
	        if (null == username)
	        {
	            return "redirect:/Verwalter/login";
	        }
	        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
	     if(null == tdTag.getId())
	     {
	    	 tdManagerLogService.addLog("add", "用户修改标签", req);
	     }
	     else
	     {
	    	 tdManagerLogService.addLog("edit", "用户修改标签", req);
	     }
	     tdTagService.save(tdTag);
	     
		return "redirect:/Verwalter/tag/list";
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
	            
	            TdTag e = tdTagService.findOne(id);
	            
	            if (null != e)
	            {
	                if (sortIds.length > i)
	                {
	                    e.setSortId(sortIds[i]);
	                    tdTagService.save(e);
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
	                
	                tdTagService.delete(id);
	            }
	        }
	    }	
	
}
