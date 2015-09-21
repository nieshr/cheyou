package com.ynyes.cheyou.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.cheyou.entity.TdArticle;
import com.ynyes.cheyou.entity.TdArticleCategory;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdManager;
import com.ynyes.cheyou.entity.TdManagerRole;
import com.ynyes.cheyou.service.TdArticleCategoryService;
import com.ynyes.cheyou.service.TdArticleService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdManagerLogService;
import com.ynyes.cheyou.service.TdManagerRoleService;
import com.ynyes.cheyou.service.TdManagerService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.util.SiteMagConstant;

/**
 * 后台首页控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter")
public class TdManagerListController {
    
    @Autowired
    TdArticleCategoryService tdArticleCategoryService;
    
    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    TdGoodsService tdGoodsService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    @RequestMapping(value="/category/list")
    public String categoryList(Long cid, 
                                Long mid, 
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
            switch (__EVENTTARGET)
            {
            case "btnSave":
                if (cid.equals(1L)) // 文章
                {
                    articleCategoryBtnSave(listId, listSortId);
                    tdManagerLogService.addLog("edit", "用户修改文章分类", req);
                }
//                else if (cid.equals(2L)) // 产品分类
//                {
//                    productCategoryBtnSave(listId, listSortId);
//                    tdManagerLogService.addLog("edit", "用户修改产品分类", req);
//                }
                
                break;
                
            case "btnDelete":
                if (cid.equals(1L)) // 文章
                {
                    articleCategoryBtnDelete(listId, listChkId);
                    tdManagerLogService.addLog("delete", "用户删除文章分类", req);
                }
//                else if (cid.equals(2L)) // 产品分类
//                {
//                    productCategoryBtnDelete(listId, listChkId);
//                    tdManagerLogService.addLog("delete", "用户删除产品分类", req);
//                }
                
                break;
            }
        }
        
        if (cid.equals(1L)) // 文章
        {
            map.addAttribute("category_list", tdArticleCategoryService.findByMenuId(mid));
        }
//        else if (cid.equals(2L)) // 商品
//        {
//            map.addAttribute("category_list", tdProductCategoryService.findAll());
//        }
        
        // 参数注回
        map.addAttribute("cid", cid);
        map.addAttribute("mid", mid);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        return "/site_mag/category_list";
    }
    
    @RequestMapping(value="/category/list", method=RequestMethod.POST)
    public String categoryPostList(Long cid, 
                            Long mid, 
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
            switch (__EVENTTARGET)
            {
            case "btnSave":
                if (cid.equals(1L)) // 文章
                {
                    articleCategoryBtnSave(listId, listSortId);
                    tdManagerLogService.addLog("edit", "用户修改文章分类", req);
                }
//                else if (cid.equals(2L)) // 商品分类
//                {
//                    productCategoryBtnSave(listId, listSortId);
//                    tdManagerLogService.addLog("edit", "用户修改产品分类", req);
//                }
                
                break;
                
            case "btnDelete":
                if (cid.equals(1L)) // 文章
                {
                    articleCategoryBtnDelete(listId, listChkId);
                    tdManagerLogService.addLog("delete", "用户删除文章分类", req);
                }
//                else if (cid.equals(2L)) // 商品分类
//                {
//                    productCategoryBtnDelete(listId, listChkId);
//                    tdManagerLogService.addLog("delete", "用户删除产品分类", req);
//                }
                
                break;
            }
        }
        
        if (cid.equals(1L)) // 文章
        {
            map.addAttribute("category_list", tdArticleCategoryService.findByMenuId(mid));
        }
        
        // 参数注回
        map.addAttribute("cid", cid);
        map.addAttribute("mid", mid);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        return "/site_mag/category_list";
    }
    
    @RequestMapping(value="/content/list")
    public String contentList(Long cid, 
                                Long mid, 
                                Integer page, 
                                Integer size,
                                Long categoryId,
                                String property,
                                String __EVENTTARGET,
                                String __EVENTARGUMENT,
                                String __VIEWSTATE,
                                String keywords,
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
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        if (null != __EVENTTARGET)
        {
            switch (__EVENTTARGET)
            {
            case "lbtnViewTxt":
            case "lbtnViewImg":
                __VIEWSTATE = __EVENTTARGET;
                break;
            
            case "btnSave":
                btnSave(cid, listId, listSortId, username);
                tdManagerLogService.addLog("edit", "用户修改文章", req);
                break;
                
            case "btnDelete":
                btnDelete(cid, listId, listChkId);
                tdManagerLogService.addLog("delete", "用户删除文章", req);
                
                break;
            }
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        if (null != __EVENTTARGET && __EVENTTARGET.equalsIgnoreCase("lbtnViewTxt")
                || null != __EVENTTARGET && __EVENTTARGET.equalsIgnoreCase("lbtnViewImg"))
        {
            __VIEWSTATE = __EVENTTARGET;
        }
        
        if (cid.equals(1L) || cid.equals(3L)) // 文章或商品参数
        {
            map.addAttribute("category_list", tdArticleCategoryService.findByMenuId(mid));
            
            if (null == categoryId)
            {
                map.addAttribute("content_page", tdArticleService.findByMenuId(mid, page, size));
            }
            else
            {
                map.addAttribute("content_page", tdArticleService.findByMenuIdAndCategoryId(mid, categoryId, page, size));
            }
        }
//        else if (cid.equals(2L)) // 商品
//        {
//            map.addAttribute("category_list", tdProductCategoryService.findAll());
//            
//            if (null == categoryId)
//            {
//                map.addAttribute("content_page", tdGoodsService.findAllOrderBySortIdAsc(page, size));
//            }
//            else
//            {
//                map.addAttribute("content_page", tdGoodsService.findByCategoryIdTreeContainingOrderBySortIdAsc(categoryId, page, size));
//            }
//        }
//        else // 品牌，产品
//        {
//            map.addAttribute("category_list", tdProductCategoryService.findAll());
//            
//            if (null == categoryId)
//            {
//                map.addAttribute("content_page", tdArticleService.findByChannelId(cid, page, size));
//            }
//            else
//            {
//                map.addAttribute("content_page", tdArticleService.findByChannelIdAndCategoryId(cid, categoryId, page, size));
//            }
//        }

        // 参数注回
        map.addAttribute("cid", cid);
        map.addAttribute("mid", mid);
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("categoryId", categoryId);
        map.addAttribute("property", property);
        
        // 文字列表模式
        if (null != __VIEWSTATE && __VIEWSTATE.equals("lbtnViewTxt"))
        {
            if (null != cid && cid.equals(2L)) // 商品
            {
                return "/site_mag/goods_txt_list";
            }
            else
            {
                return "/site_mag/content_txt_list";
            }
        }
        
        // 图片列表模式
        if (null != cid && cid.equals(2L)) // 商品
        {
            return "/site_mag/goods_pic_list";
        }
        else
        {
            return "/site_mag/content_pic_list";
        }
    }
    
    @RequestMapping(value="/content/list", method=RequestMethod.POST)
    public String contentListP(Long cid, 
                              Long mid, 
                              Integer page, 
                              Integer size,
                              Long categoryId,
                              String property,
                              String __EVENTTARGET,
                              String __EVENTARGUMENT,
                              String __VIEWSTATE,
                              String keywords,
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
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        if (null != __EVENTTARGET)
        {
            switch (__EVENTTARGET)
            {
            case "lbtnViewTxt":
            case "lbtnViewImg":
                __VIEWSTATE = __EVENTTARGET;
                break;
            
            case "btnSave":
                btnSave(cid, listId, listSortId, username);
                tdManagerLogService.addLog("edit", "用户修改文章", req);
                break;
                
            case "btnDelete":
                btnDelete(cid, listId, listChkId);
                tdManagerLogService.addLog("delete", "用户删除文章", req);
                break;
                
            case "btnPage":
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
                break;
            }
        }
        
        if (null != __EVENTTARGET && __EVENTTARGET.equalsIgnoreCase("lbtnViewTxt")
                || null != __EVENTTARGET && __EVENTTARGET.equalsIgnoreCase("lbtnViewImg"))
        {
            __VIEWSTATE = __EVENTTARGET;
        }
        
        if (cid.equals(1L) || cid.equals(3L)) // 文章或商品参数
        {
            map.addAttribute("category_list", tdArticleCategoryService.findByMenuId(mid));
            
            if (null == categoryId)
            {
                map.addAttribute("content_page", tdArticleService.findByMenuId(mid, page, size));
            }
            else
            {
                map.addAttribute("content_page", tdArticleService.findByMenuIdAndCategoryId(mid, categoryId, page, size));
            }
        }
//        else if (cid.equals(2L)) // 商品
//        {
//            map.addAttribute("category_list", tdProductCategoryService.findAll());
//            
//            if (null == categoryId)
//            {
//                map.addAttribute("content_page", tdGoodsService.findAllOrderBySortIdAsc(page, size));
//            }
//            else
//            {
//                map.addAttribute("content_page", tdGoodsService.findByCategoryIdTreeContainingOrderBySortIdAsc(categoryId, page, size));
//            }
//        }
//        else // 品牌，产品
//        {
//            map.addAttribute("category_list", tdProductCategoryService.findAll());
//            
//            if (null == categoryId)
//            {
//                map.addAttribute("content_page", tdArticleService.findByChannelId(cid, page, size));
//            }
//            else
//            {
//                map.addAttribute("content_page", tdArticleService.findByChannelIdAndCategoryId(cid, categoryId, page, size));
//            }
//        }

        // 参数注回
        map.addAttribute("cid", cid);
        map.addAttribute("mid", mid);
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("categoryId", categoryId);
        map.addAttribute("property", property);
        
        // 文字列表模式
        if (null != __VIEWSTATE && __VIEWSTATE.equals("lbtnViewTxt"))
        {
            if (null != cid && cid.equals(2L)) // 商品
            {
                return "/site_mag/goods_txt_list";
            }
            else
            {
                return "/site_mag/content_txt_list";
            }
        }
        
        // 图片列表模式
        if (null != cid && cid.equals(2L)) // 商品
        {
            return "/site_mag/goods_pic_list";
        }
        else
        {
            return "/site_mag/content_pic_list";
        }
    }
    
    /**
     * 保存文章排序ID
     * 
     * @param ids
     * @param chkIds
     * @param sortIds
     */
//    private void articleBtnSave(Long[] ids, Integer[] chkIds, Long[] sortIds)
//    {
//        if (null == ids || null == chkIds || null == sortIds
//                || ids.length < 1 || chkIds.length < 1 || sortIds.length < 1)
//        {
//            return;
//        }
//        
//        for (int chkId : chkIds)
//        {
//            if (chkId >=0 && ids.length > chkId && sortIds.length > chkId)
//            {
//                Long id = ids[chkId];
//                
//                TdArticle article = tdArticleService.findOne(id);
//                
//                article.setSortId(sortIds[chkId]);
//                
//                tdArticleService.save(article);
//            }
//        }
//    }
    
    /**
     * 保存类型排序ID
     * 
     * @param ids
     * @param chkIds
     * @param sortIds
     */
    private void articleCategoryBtnSave(Long[] ids, Long[] sortIds)
    {
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            TdArticleCategory category = tdArticleCategoryService.findOne(id);
                
            if (sortIds.length > i)
            {
                category.setSortId(sortIds[i]);
                tdArticleCategoryService.save(category);
            }
        }
    }
    
//    private void productCategoryBtnSave(Long[] ids, Long[] sortIds)
//    {
//        if (null == ids || null == sortIds
//                || ids.length < 1 || sortIds.length < 1)
//        {
//            return;
//        }
//        
//        for (int i = 0; i < ids.length; i++)
//        {
//            Long id = ids[i];
//            TdProductCategory category = tdProductCategoryService.findOne(id);
//                
//            if (sortIds.length > i)
//            {
//                category.setSortId(sortIds[i]);
//                tdProductCategoryService.save(category);
//            }
//        }
//    }
    
    /**
     * 删除类型
     * 
     * @param ids
     * @param chkIds
     * @param sortIds
     */
    private void articleCategoryBtnDelete(Long[] ids, Integer[] chkIds)
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
                
                tdArticleCategoryService.delete(id);
            }
        }
    }
    
//    private void productCategoryBtnDelete(Long[] ids, Integer[] chkIds)
//    {
//        if (null == ids || null == chkIds
//                || ids.length < 1 || chkIds.length < 1)
//        {
//            return;
//        }
//        
//        for (int chkId : chkIds)
//        {
//            if (chkId >=0 && ids.length > chkId)
//            {
//                Long id = ids[chkId];
//                
//                tdProductCategoryService.delete(id);
//            }
//        }
//    }
    
    /**
     * 保存文章排序ID
     * 
     * @param ids
     * @param chkIds
     * @param sortIds
     */
    private void btnSave(Long cid, Long[] ids, Long[] sortIds, String username)
    {
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            if (null != cid && cid.equals(2L))
            {
                TdGoods goods = tdGoodsService.findOne(id);
                
                if (sortIds.length > i)
                {
                    goods.setSortId(sortIds[i]);
                    tdGoodsService.save(goods, username);
                }
            }
            else
            {
                TdArticle article = tdArticleService.findOne(id);
                    
                if (sortIds.length > i)
                {
                    article.setSortId(sortIds[i]);
                    tdArticleService.save(article);
                }
            }
        }
    }
    
    /**
     * 删除文章
     * 
     * @param ids
     * @param chkIds
     * @param sortIds
     */
    private void btnDelete(Long cid, Long[] ids, Integer[] chkIds)
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
                
                if (null != cid && cid.equals(2L))
                {
                    tdGoodsService.delete(id);
                }
                else
                {
                    tdArticleService.delete(id);
                }
            }
        }
    }
}
