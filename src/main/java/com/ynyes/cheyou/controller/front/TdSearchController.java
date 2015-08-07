package com.ynyes.cheyou.controller.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.cheyou.entity.TdArticleCategory;
import com.ynyes.cheyou.entity.TdKeywords;
import com.ynyes.cheyou.service.TdArticleCategoryService;
import com.ynyes.cheyou.service.TdArticleService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdKeywordsService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.util.ClientConstant;

/**
 * 商品检索
 * @author Sharon
 *
 */
@Controller
public class TdSearchController {
    
    @Autowired
    private TdCommonService tdCommonService;
    
    @Autowired
    private TdGoodsService tdGoodsService;
    
    @Autowired
    private TdKeywordsService tdKeywordsService;
    
    @Autowired
    private TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;
    
    @Autowired
    private TdArticleService tdArticleService;
    
 // 组成：[排序字段]-[销量排序标志]-[价格排序标志]-[上架时间排序标志]-[是否有货]-[页号]_[价格低值]-[价格高值]
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String list(String keywords, Integer page, HttpServletRequest req, ModelMap map){
        
        tdCommonService.setHeader(map, req);
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null != keywords)
        {
            TdKeywords key = tdKeywordsService.findByTitle(keywords);
            
            if (null != key)
            {
                if (null == key.getTotalSearch())
                {
                    key.setTotalSearch(1L);
                }
                else
                {
                    key.setTotalSearch(key.getTotalSearch() + 1L);
                }
                
                key.setLastSearchTime(new Date());
                
                tdKeywordsService.save(key);
            }
            
            map.addAttribute("goods_page", tdGoodsService.searchGoods(keywords.trim(), page, ClientConstant.pageSize));
        }
        
        // 商城资讯
        List<TdArticleCategory> articleCatList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != articleCatList && articleCatList.size() > 0) {
            Long articleCatId = articleCatList.get(0).getId();

            map.addAttribute("news_page", tdArticleService
                    .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(10L,
                            articleCatId, 0, ClientConstant.pageSize));
        }
        
        map.addAttribute("pageId", page);
        map.addAttribute("keywords", keywords);
        
        // 热卖推荐
        map.addAttribute("hot_sale_list", tdGoodsService.findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(0, 10).getContent());   
        
        // 销量排行
        map.addAttribute("most_sold_list", tdGoodsService.findByIsOnSaleTrueOrderBySoldNumberDesc(0, 10).getContent());   
        
        return "/client/search_result";
    }
}
