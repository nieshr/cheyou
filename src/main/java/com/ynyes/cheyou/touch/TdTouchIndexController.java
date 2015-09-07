package com.ynyes.cheyou.touch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.cheyou.entity.TdAdType;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdProductCategory;
import com.ynyes.cheyou.service.TdAdService;
import com.ynyes.cheyou.service.TdAdTypeService;
import com.ynyes.cheyou.service.TdArticleCategoryService;
import com.ynyes.cheyou.service.TdArticleService;
import com.ynyes.cheyou.service.TdBrandService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdNaviBarItemService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.service.TdSiteLinkService;

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
    
    @Autowired
    private TdNaviBarItemService tdNaviBarItemService;

    @RequestMapping
    public String index(HttpServletRequest req, Device device, ModelMap map) {

        tdCommonService.setHeader(map, req);

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

        // 导航菜单
        map.addAttribute("touch_navi_item_list",
                tdNaviBarItemService.findByIsEnableTrueAndIsTouchShowTrueOrderBySortIdAsc());

        /*
         * 团购相关产品
         */
        // 已经结束团购
        map.addAttribute("tuan_prev_page", tdGoodsService
                .findByGroupSaleEndedOrderByGroupSaleStartTimeAsc(0, 5));
        // 下期预告团购
        map.addAttribute("tuan_next_page", tdGoodsService
                .findByGroupSaleGoingToStartOrderByGroupSaleStartTimeAsc(0, 5));
        // 正在进行团购
        map.addAttribute("tuan_cur_page", tdGoodsService
                .findByGroupSalingOrderByGroupSaleStartTimeAsc(0, 6));

        // 正在进行百人团购
        map.addAttribute("baituan_cur_page", tdGoodsService
                .findByGroupSalingHundredOrderByGroupSaleStartTimeAsc(0, 6));

        // 已经结束秒杀
        // map.addAttribute("miao_prev_page", tdGoodsService
        // .findByFlashSaleEndedOrderByFlashSaleStartTimeAsc(0, 5));
        // // 即将开始秒杀
        // map.addAttribute("miao_next_page", tdGoodsService
        // .findByFlashSaleGoingToStartOrderByFlashSaleStartTimeAsc(0, 5));
        // // 正在秒杀
        // map.addAttribute("miao_cur_page", tdGoodsService
        // .findByFlashSalingOrderByFlashSaleStartTimeAsc(0, 5));

        /*
         * 秒杀相关产品
         */
        // 8点
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        Date now = new Date(); //zhangji
        List<TdGoods> flashGoodsList = new ArrayList<TdGoods>();
        if (now.after(cal.getTime()))
        {
        Page<TdGoods> goodsPage1 = tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(
                        cal.getTime(), 0, 6);
        flashGoodsList.addAll(goodsPage1.getContent());
        }
        
        cal.set(Calendar.HOUR_OF_DAY, 14);
        if (now.after(cal.getTime()))
        {
        Page<TdGoods> goodsPage2 = tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(
                        cal.getTime(), 0, 6);
        flashGoodsList.addAll(goodsPage2.getContent());
        }
        
        cal.set(Calendar.HOUR_OF_DAY, 20);
        if (now.after(cal.getTime()))
        {
        Page<TdGoods> goodsPage3 = tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(
                        cal.getTime(), 0, 6);
        flashGoodsList.addAll(goodsPage3.getContent());
        }
        
        

       
        
        
       

        map.addAttribute("flash_goods_list", flashGoodsList);

        // 首页推荐商品
        map.addAttribute("index_recommend_goods_page", tdGoodsService
                .findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(0, 4));

        // 触屏页中部广告
        TdAdType adType = tdAdTypeService.findByTitle("触屏页中部广告");

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
            map.addAttribute("self_drive_product_category", pCat);
            map.addAttribute(
                    "self_drive_goods_page",
                    tdGoodsService
                            .findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(
                                    pCat.getId(), 0, 5));
        }

        return "/touch/index";
    }
}
