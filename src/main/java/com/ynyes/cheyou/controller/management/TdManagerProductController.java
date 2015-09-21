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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdManager;
import com.ynyes.cheyou.entity.TdManagerRole;
import com.ynyes.cheyou.entity.TdProduct;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdManagerLogService;
import com.ynyes.cheyou.service.TdManagerRoleService;
import com.ynyes.cheyou.service.TdManagerService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.service.TdProductService;
import com.ynyes.cheyou.util.SiteMagConstant;

/**
 * 后台用户管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/product")
public class TdManagerProductController {
    
    @Autowired
    TdProductService tdProductService;
    
    @Autowired
    TdGoodsService tdGoodsService;
    
    @Autowired
    TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    @RequestMapping(value="/parameter/{id}", method = RequestMethod.GET)
    public String parameter(@PathVariable Long id, Long goodsId, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        TdProduct product = tdProductService.findOne(id);
        
        map.addAttribute("product", product);
        
        if (null != goodsId)
        {
            map.addAttribute("goods", tdGoodsService.findOne(goodsId));
        }
        
        return "/site_mag/product_param_list";
    }
    
    @RequestMapping(value="/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(Long catId, Long id, String param) {
        Map<String, String> res = new HashMap<String, String>();
        
        res.put("status", "n");
        
        if (null == param || param.isEmpty())
        {
            res.put("info", "该字段不能为空");
            return res;
        }
        
        if (null == id)
        {
            if (null == catId)
            {
                if (null != tdProductService.findByTitle(param))
                {
                    res.put("info", "该分类下已存在同名产品");
                    return res;
                }
            }
            else
            {
                if (null != tdProductService.findByProductCategoryTreeContainingAndTitle(catId, param))
                {
                    res.put("info", "该分类下已存在同名产品");
                    return res;
                }
            }
        }
        else
        {
            if (null == catId)
            {
                if (null != tdProductService.findByTitleAndIdNot(param, id))
                {
                    res.put("info", "该分类下已存在同名产品");
                    return res;
                }
            }
            else
            {
                if (null != tdProductService.findByProductCategoryTreeContainingAndTitleAndIdNot(catId, param, id))
                {
                    res.put("info", "该分类下已存在同名产品");
                    return res;
                }
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
                tdManagerLogService.addLog("delete", "删除产品", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "修改产品", req);
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

        Page<TdProduct> productPage = null;
        
        if (null == keywords || "".equalsIgnoreCase(keywords))
        {
            productPage = tdProductService.findAllOrderBySortIdAsc(page, size);
        }
        else
        {
            productPage = tdProductService.searchAndOrderBySortIdAsc(keywords, page, size);
        }
        
        map.addAttribute("product_page", productPage);
        
        return "/site_mag/product_list";
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
        
        map.addAttribute("category_list", tdProductCategoryService.findAll());
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        if (null != id)
        {
            map.addAttribute("product", tdProductService.findOne(id));
        }
        return "/site_mag/product_edit";
    }
    
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String orderEdit(TdProduct tdProduct,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null == tdProduct.getId())
        {
            tdManagerLogService.addLog("add", "用户修改产品", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "用户修改产品", req);
        }
        
        tdProductService.save(tdProduct);
        
        return "redirect:/Verwalter/product/list";
    }

    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
                        Model model) {
        if (null != id) {
            model.addAttribute("tdProduct", tdProductService.findOne(id));
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
            
            TdProduct e = tdProductService.findOne(id);
            
            if (null != e)
            {
                if (sortIds.length > i)
                {
                    e.setSortId(sortIds[i]);
                    tdProductService.save(e);
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
                
                tdProductService.delete(id);
            }
        }
    }
}
