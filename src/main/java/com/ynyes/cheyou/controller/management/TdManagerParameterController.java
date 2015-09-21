package com.ynyes.cheyou.controller.management;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ynyes.cheyou.entity.TdParameter;
import com.ynyes.cheyou.service.TdManagerLogService;
import com.ynyes.cheyou.service.TdManagerRoleService;
import com.ynyes.cheyou.service.TdManagerService;
import com.ynyes.cheyou.service.TdParameterCategoryService;
import com.ynyes.cheyou.service.TdParameterService;
import com.ynyes.cheyou.util.SiteMagConstant;

/**
 * 后台广告位管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/parameter")
public class TdManagerParameterController {
    
    @Autowired
    TdParameterService tdParameterService;
    
    @Autowired
    TdParameterCategoryService tdParameterCategoryService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    @RequestMapping(value="/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(Long categoryId, Long id, String param) {
        Map<String, String> res = new HashMap<String, String>();
        
        res.put("status", "n");
        
        if (null == param || param.isEmpty())
        {
            res.put("info", "该字段不能为空");
            return res;
        }
        
        if (null == categoryId)
        {
            res.put("info", "请先选择参数分类");
            return res;
        }
        
        if (null == id)
        {
            if (null != tdParameterService.findByTitleAndCategoryId(param, categoryId))
            {
                res.put("info", "已存在同名参数");
                return res;
            }
        }
        else
        {
            if (null != tdParameterService.findByTitleAndCategoryIdAndIdNot(param, categoryId, id))
            {
                res.put("info", "已存在同名的兄弟参数");
                return res;
            }
        }
        
        res.put("status", "y");
        
        return res;
    }
    
    @RequestMapping(value="/list")
    public String setting(String __EVENTTARGET,
                          String __EVENTARGUMENT,
                          String __VIEWSTATE,
                          Long categoryId,
                          String keywords,
                          Integer page,
                          Integer size,
                          Long[] listId,
                          Integer[] listChkId,
                          Long[] listSortId,
                          ModelMap map,
                          HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
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
                tdManagerLogService.addLog("delete", "用户删除参数", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "用户修改参数", req);
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
        
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("categoryId", categoryId);
        map.addAttribute("keywords", keywords);
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        
        map.addAttribute("parameter_category_list", tdParameterCategoryService.findAll());
        
        
        if (null != keywords && !keywords.isEmpty())
        {
            map.addAttribute("parameter_page", tdParameterService.searchOrderBySortIdAsc(keywords, page, size));
        }
        else
        {
            if (null == categoryId)
            {
                map.addAttribute("parameter_page", tdParameterService.findAllOrderBySortIdAsc(page, size));
            }
            else
            {
                map.addAttribute("parameter_page", tdParameterService.findByCategoryTreeContainingOrderBySortIdAsc(categoryId, page, size));
            }
        }
        
        return "/site_mag/parameter_list";
    }
    
    @RequestMapping(value="/edit")
    public String paramCategoryEdit(Long id,
                        Long sub,       
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("category_list", tdParameterCategoryService.findAll());

        if (null != id)
        {
            map.addAttribute("parameter", tdParameterService.findOne(id));
        }
        
        return "/site_mag/parameter_edit";
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdParameter tdParameter,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        String type = null;
        
        if (null == tdParameter.getId())
        {
            type = "add";
        }
        else
        {
            type = "edit";
        }
        
        tdParameterService.save(tdParameter);
        
        tdManagerLogService.addLog(type, "用户修改参数", req);
        
        return "redirect:/Verwalter/parameter/list";
    }

    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
                        Model model) {
        if (null != id) {
            model.addAttribute("tdParameter", tdParameterService.findOne(id));
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
            
            TdParameter e = tdParameterService.findOne(id);
            
            if (null != e)
            {
                if (sortIds.length > i)
                {
                    e.setSortId(sortIds[i]);
                    tdParameterService.save(e);
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
                
                tdParameterService.delete(id);
            }
        }
    }
}
