package com.ynyes.cheyou.touch;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdProductCategory;
import com.ynyes.cheyou.service.TdArticleCategoryService;
import com.ynyes.cheyou.service.TdArticleService;
import com.ynyes.cheyou.service.TdBrandService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdParameterService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.service.TdUserRecentVisitService;
import com.ynyes.cheyou.util.ClientConstant;


@Controller
@RequestMapping("/touch")
public class TdTouchListController {
    @Autowired
    private TdGoodsService tdGoodsService;
    
    @Autowired
    private TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    private TdArticleService tdArticleService;
    
    @Autowired
    private TdCommonService tdCommonService;
    
    @Autowired
    private TdBrandService tdBrandService;
    
    @Autowired
    private TdParameterService tdParameterService;
    
    @Autowired
    private TdUserRecentVisitService tdUserRecentVisitService;
    
    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;
    
    // 组成：typeID-[排序字段]-[销量排序标志]-[价格排序标志]-[上架时间排序标志]-[页号]
    @RequestMapping("/list/{listStr}")
    public String list(@PathVariable String listStr, ModelMap map, HttpServletRequest req){
        tdCommonService.setHeader(map, req);
        
        if (null == listStr || "".equals(listStr))
        {
            return "/touch/error_404";
        }
            
        String[] numberGroup = listStr.split("-");
        
        if (numberGroup.length <= 0)
        {
            return "/touch/error_404";
        }
        
        Long categoryId = null;
        
        // 分类ID
        categoryId = Long.parseLong(numberGroup[0]);
        
        map.addAttribute("categoryId", categoryId);
        
        TdProductCategory tdProductCategory = tdProductCategoryService.findOne(categoryId);
        
        if (null == tdProductCategory)
        {
            return "/touch/error_404";
        }
        
        map.addAttribute("productCategory", tdProductCategory);
        
        // 排序字段
        Integer orderId = 0;
        
        if (numberGroup.length > 1)
        {
            String orderIdStr = numberGroup[1];
            
            if (null != orderIdStr)
            {
                orderId = Integer.parseInt(orderIdStr);
            }
        }
        
        map.addAttribute("orderId", orderId);
        
        //  销量排序标志
        Integer soldId = 0;
        
        if (numberGroup.length > 2)
        {
            String soldIdStr = numberGroup[2];
            
            if (null != soldIdStr)
            {
                soldId = Integer.parseInt(soldIdStr);
            }
        }
        
        map.addAttribute("soldId", soldId);
        
        // 价格排序标志
        Integer priceId = 0;
        
        if (numberGroup.length > 3)
        {
            String priceIdStr = numberGroup[3];
            
            if (null != priceIdStr)
            {
                priceId = Integer.parseInt(priceIdStr);
            }
        }
        
        map.addAttribute("priceId", priceId);
        
        // 上架时间排序标志
        Integer timeId = 0;
        
        if (numberGroup.length > 4)
        {
            String timeIdStr = numberGroup[4];
            
            if (null != timeIdStr)
            {
                timeId = Integer.parseInt(timeIdStr);
            }
        }
        
        map.addAttribute("timeId", timeId);
        
        // 页号
        Integer pageId = 0;
        
        if (numberGroup.length > 5)
        {
            String pageIdStr = numberGroup[5];
            
            if (null != pageIdStr)
            {
                pageId = Integer.parseInt(pageIdStr);
            }
        }
        
        map.addAttribute("pageId", pageId);
        
        // 查找商品
        Page<TdGoods> goodsPage = null;
        

        // 按销量排序
        if (0 == orderId.intValue())
        {
            if (0 == soldId.intValue())
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                            categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                    Direction.DESC, "soldNumber")));
        
            }
            else
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.ASC, "soldNumber")));
    
            }
        }
        // 按价格排序
        else if (1 == orderId.intValue())
        {
            if (0 == priceId.intValue())
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.DESC, "salePrice")));
        
            }
            else
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.ASC, "salePrice")));
    
            }
        }
        // 按上架时间排序
        else 
        {
            if (0 == timeId.intValue())
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.DESC, "id")));
            }
            else
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.ASC, "id")));
    
            }
        }
            
        map.addAttribute("goods_page", goodsPage);
        
        return "/touch/list";
    }
    
    // 组成：typeID-[排序字段]-[销量排序标志]-[价格排序标志]-[上架时间排序标志]-[页号]
    @RequestMapping("/list/more/{listStr}")
    public String goodsList(@PathVariable String listStr, ModelMap map, HttpServletRequest req){
        if (null == listStr || "".equals(listStr))
        {
            return "/touch/list_more";
        }
            
        String[] numberGroup = listStr.split("-");
        
        if (numberGroup.length <= 0)
        {
            return "/touch/list_more";
        }
        
        Long categoryId = null;
        
        // 分类ID
        categoryId = Long.parseLong(numberGroup[0]);
        
//        map.addAttribute("categoryId", categoryId);
        
        TdProductCategory tdProductCategory = tdProductCategoryService.findOne(categoryId);
        
        if (null == tdProductCategory)
        {
            return "/touch/list_more";
        }
        
//        map.addAttribute("productCategory", tdProductCategory);
        
        // 排序字段
        Integer orderId = 0;
        
        if (numberGroup.length > 1)
        {
            String orderIdStr = numberGroup[1];
            
            if (null != orderIdStr)
            {
                orderId = Integer.parseInt(orderIdStr);
            }
        }
        
//        map.addAttribute("orderId", orderId);
        
        //  销量排序标志
        Integer soldId = 0;
        
        if (numberGroup.length > 2)
        {
            String soldIdStr = numberGroup[2];
            
            if (null != soldIdStr)
            {
                soldId = Integer.parseInt(soldIdStr);
            }
        }
        
//        map.addAttribute("soldId", soldId);
        
        // 价格排序标志
        Integer priceId = 0;
        
        if (numberGroup.length > 3)
        {
            String priceIdStr = numberGroup[3];
            
            if (null != priceIdStr)
            {
                priceId = Integer.parseInt(priceIdStr);
            }
        }
        
//        map.addAttribute("priceId", priceId);
        
        // 上架时间排序标志
        Integer timeId = 0;
        
        if (numberGroup.length > 4)
        {
            String timeIdStr = numberGroup[4];
            
            if (null != timeIdStr)
            {
                timeId = Integer.parseInt(timeIdStr);
            }
        }
        
//        map.addAttribute("timeId", timeId);
        
        // 页号
        Integer pageId = 0;
        
        if (numberGroup.length > 5)
        {
            String pageIdStr = numberGroup[5];
            
            if (null != pageIdStr)
            {
                pageId = Integer.parseInt(pageIdStr);
            }
        }
        
//        map.addAttribute("pageId", pageId);

        // 查找商品
        Page<TdGoods> goodsPage = null;
        

     // 按销量排序
        if (0 == orderId.intValue())
        {
            if (0 == soldId.intValue())
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                            categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                    Direction.DESC, "soldNumber")));
        
            }
            else
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.ASC, "soldNumber")));
    
            }
        }
        // 按价格排序
        else if (1 == orderId.intValue())
        {
            if (0 == priceId.intValue())
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.DESC, "salePrice")));
        
            }
            else
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.ASC, "salePrice")));
    
            }
        }
        // 按上架时间排序
        else 
        {
            if (0 == timeId.intValue())
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.DESC, "id")));
            }
            else
            {
                goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
                        categoryId, null, new PageRequest(pageId, ClientConstant.pageSize, new Sort(
                                Direction.ASC, "id")));
    
            }
        }
            
        map.addAttribute("goods_page", goodsPage);
        
        return "/touch/list_more";
    }
}
