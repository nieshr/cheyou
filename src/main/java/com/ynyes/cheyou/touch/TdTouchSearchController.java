package com.ynyes.cheyou.touch;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * 
 * @author Sharon
 *
 */
@Controller
@RequestMapping("/touch")
public class TdTouchSearchController {

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

    /**
     * 搜索
     * 
     * @param keywords
     *            关键字
     * @param page
     *            页码
     * @param st
     *            排序字段
     * @param sd
     *            排序方向 0：降序 1：升序
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String list(String keywords, Integer page, Integer st, Integer sd,
            HttpServletRequest req, ModelMap map) {

        if (null == page || page < 0) {
            page = 0;
        }
        
        if (null == st)
        {
            st = 0;
        }
        
        if (null == sd)
        {
            sd = 0;
        }

        if (null != keywords) {
            TdKeywords key = tdKeywordsService.findByTitle(keywords);

            if (null != key) {
                if (null == key.getTotalSearch()) {
                    key.setTotalSearch(1L);
                } else {
                    key.setTotalSearch(key.getTotalSearch() + 1L);
                }

                key.setLastSearchTime(new Date());

                tdKeywordsService.save(key);
            }
            
            String orderColumn = "soldNumber";
                    
            if (st.equals(1))
            {
                orderColumn = "salePrice";
            }
            else if (st.equals(2))
            {
                orderColumn = "id";
            }
            
            Direction dir = Direction.DESC;
            
            if (sd.equals(1))
            {
                dir = Direction.ASC;
            }

            map.addAttribute("goods_page", tdGoodsService.searchGoods(
                    keywords.trim(), page, ClientConstant.pageSize,
                    orderColumn, dir));
        }

        map.addAttribute("pageId", page);
        map.addAttribute("keywords", keywords);
        map.addAttribute("st", st);
        map.addAttribute("sd", sd);
        
        return "/touch/search_result";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchMore(String keywords, Integer page, Integer st, Integer sd,
            HttpServletRequest req, ModelMap map) {

        if (null == page || page < 0) {
            page = 0;
        }
        
        if (null == st)
        {
            st = 0;
        }
        
        if (null == sd)
        {
            sd = 0;
        }

        if (null != keywords) {
            TdKeywords key = tdKeywordsService.findByTitle(keywords);

            if (null != key) {
                if (null == key.getTotalSearch()) {
                    key.setTotalSearch(1L);
                } else {
                    key.setTotalSearch(key.getTotalSearch() + 1L);
                }

                key.setLastSearchTime(new Date());

                tdKeywordsService.save(key);
            }
            
            String orderColumn = "soldNumber";
                    
            if (st.equals(1))
            {
                orderColumn = "salePrice";
            }
            else if (st.equals(2))
            {
                orderColumn = "id";
            }
            
            Direction dir = Direction.DESC;
            
            if (sd.equals(1))
            {
                dir = Direction.ASC;
            }

            map.addAttribute("goods_page", tdGoodsService.searchGoods(
                    keywords.trim(), page, ClientConstant.pageSize,
                    orderColumn, dir));
        }
        
        map.addAttribute("pageId", page);
        map.addAttribute("keywords", keywords);
        map.addAttribute("st", st);
        map.addAttribute("sd", sd);

        return "/touch/search_result_more";
    }
}
