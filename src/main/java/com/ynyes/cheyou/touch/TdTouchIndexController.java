package com.ynyes.cheyou.touch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.cheyou.entity.TdAdType;
import com.ynyes.cheyou.entity.TdArticleCategory;
import com.ynyes.cheyou.entity.TdProductCategory;
import com.ynyes.cheyou.service.TdAdService;
import com.ynyes.cheyou.service.TdAdTypeService;
import com.ynyes.cheyou.service.TdArticleCategoryService;
import com.ynyes.cheyou.service.TdArticleService;
import com.ynyes.cheyou.service.TdBrandService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.service.TdSiteLinkService;
import com.ynyes.cheyou.util.ClientConstant;

/**
 * 前端首页控制
 *
 */
@Controller
@RequestMapping("/touch")
public class TdTouchIndexController {

    @Autowired
    private TdCommonService tdCommonService;

    @Autowired
    private TdGoodsService tdGoodsService;

    @Autowired
    private TdArticleService tdArticleService;

    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    private TdProductCategoryService tdProductCategoryService;

    @Autowired
    private TdSiteLinkService tdSiteLinkService;

    @Autowired
    private TdAdTypeService tdAdTypeService;

    @Autowired
    private TdAdService tdAdService;

    @Autowired
    private TdBrandService tdBrandService;

    @RequestMapping
    public String index(HttpServletRequest req, Device device, ModelMap map) {
        tdCommonService.setHeader(map, req);

        // 商城快报
        List<TdArticleCategory> catList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != catList && catList.size() > 0) {
            Long catId = catList.get(0).getId();

            map.addAttribute("news_page", tdArticleService
                    .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(10L,
                            catId, 0, ClientConstant.pageSize));
        }

        // 养车宝典
        catList = tdArticleCategoryService.findByMenuId(11L);

        if (null != catList && catList.size() > 0) {
            Long catId = catList.get(0).getId();

            map.addAttribute("curing_page", tdArticleService
                    .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(11L,
                            catId, 0, ClientConstant.pageSize));
        }

        // 一级分类
        List<TdProductCategory> topCatList = tdProductCategoryService
                .findByParentIdIsNullOrderBySortIdAsc();
        if (null != topCatList && topCatList.size() > 0) {
            map.addAttribute("top_category_list", topCatList);

            for (int i = 0; i < topCatList.size(); i++) {
                TdProductCategory topCat = topCatList.get(i);

                if (null != topCat) {
                    map.addAttribute(
                            "top_cat_goods_page" + i,
                            tdGoodsService
                                    .findByCategoryIdAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
                                            topCat.getId(), 0, 3));
                }
            }
        }

        // 首页大图轮播广告
        TdAdType adType = tdAdTypeService.findByTitle("首页轮播大图广告");

        if (null != adType) {
            map.addAttribute("big_scroll_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }
        
        // 商品分类底部广告
        adType = tdAdTypeService.findByTitle("商品分类底部广告");

        if (null != adType) {
            map.addAttribute("cat_bottom_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 页面中部轮播广告
        adType = tdAdTypeService.findByTitle("页面中部轮播广告");

        if (null != adType) {
            map.addAttribute("mid_scroll_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 页面中部大图广告
        adType = tdAdTypeService.findByTitle("页面中部大图广告");

        if (null != adType) {
            map.addAttribute("mid_big_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 商品分类轮播广告
        adType = tdAdTypeService.findByTitle("商品分类轮播广告");

        if (null != adType) {
            map.addAttribute("type_scroll_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 昆明自驾游大图广告
        adType = tdAdTypeService.findByTitle("昆明自驾游大图广告");

        if (null != adType) {
            map.addAttribute("tour_km_big_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 省内自驾游大图广告
        adType = tdAdTypeService.findByTitle("省内自驾游大图广告");

        if (null != adType) {
            map.addAttribute("tour_province_big_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 国内自驾游大图广告
        adType = tdAdTypeService.findByTitle("国内自驾游大图广告");

        if (null != adType) {
            map.addAttribute("tour_country_big_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 昆明自驾游列表广告
        adType = tdAdTypeService.findByTitle("昆明自驾游列表广告");

        if (null != adType) {
            map.addAttribute("tour_km_list_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 省内自驾游列表广告
        adType = tdAdTypeService.findByTitle("省内自驾游列表广告");

        if (null != adType) {
            map.addAttribute("tour_province_list_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 国内自驾游列表广告
        adType = tdAdTypeService.findByTitle("国内自驾游列表广告");

        if (null != adType) {
            map.addAttribute("tour_country_list_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 首页底部大图广告
        adType = tdAdTypeService.findByTitle("首页底部大图广告");

        if (null != adType) {
            map.addAttribute("bottom_big_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 首页底部小图广告
        adType = tdAdTypeService.findByTitle("首页底部小图广告");

        if (null != adType) {
            map.addAttribute("bottom_small_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }

        // 已经结束团购
        map.addAttribute("tuan_prev_page", tdGoodsService
                .findByGroupSaleEndedOrderByGroupSaleStartTimeAsc(0, 5));
        // 下期预告团购
        map.addAttribute("tuan_next_page", tdGoodsService
                .findByGroupSaleGoingToStartOrderByGroupSaleStartTimeAsc(0, 5));
        // 正在进行团购
        map.addAttribute("tuan_cur_page", tdGoodsService
                .findByGroupSalingOrderByGroupSaleStartTimeAsc(0, 6));

        // 已经结束秒杀
        map.addAttribute("miao_prev_page", tdGoodsService
                .findByFlashSaleEndedOrderByFlashSaleStartTimeAsc(0, 5));
        // 即将开始秒杀
        map.addAttribute("miao_next_page", tdGoodsService
                .findByFlashSaleGoingToStartOrderByFlashSaleStartTimeAsc(0, 5));
        // 正在秒杀
        map.addAttribute("miao_cur_page", tdGoodsService
                .findByFlashSalingOrderByFlashSaleStartTimeAsc(0, 6));
        
        // 首页推荐商品
        map.addAttribute("index_recommend_goods_page", tdGoodsService
                .findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(0, 4));
        
        // 触屏页中部广告
        adType = tdAdTypeService.findByTitle("触屏页中部广告");
        
        if (null != adType) {
            map.addAttribute("touch_middle_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }
        
        // 触屏页秒杀栏旁边广告
        adType = tdAdTypeService.findByTitle("触屏页秒杀栏旁边广告");

        if (null != adType) {
            map.addAttribute("touch_miao_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }
        
        // 触屏页团购栏旁边广告
        adType = tdAdTypeService.findByTitle("触屏页团购栏旁边广告");

        if (null != adType) {
            map.addAttribute("touch_tuan_ad_list", tdAdService
                    .findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
        }
        
        // 自驾游
        TdProductCategory pCat = tdProductCategoryService.findByTitle("自驾游");

        if (null != pCat) {
            map.addAttribute(
                    "self_drive_product_category", pCat);
            map.addAttribute(
                    "self_drive_goods_page",
                    tdGoodsService
                            .findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(
                                    pCat.getId(), 0, 5));
        }
        
        return "/touch/index";
    }
}
