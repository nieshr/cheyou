package com.ynyes.cheyou.controller.front;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.cheyou.entity.TdAdType;
import com.ynyes.cheyou.entity.TdArticleCategory;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdProductCategory;
import com.ynyes.cheyou.repository.TdOrderRepo;
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
@RequestMapping("/")
public class TdIndexController {

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
       
        // 触屏
        if (device.isMobile() || device.isTablet()) {
            return "redirect:/touch/";
        }
        
        tdCommonService.setHeader(map, req);

        // 商城快报
        List<TdArticleCategory> catList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != catList && catList.size() > 0) {
            for (TdArticleCategory tdCat : catList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("商城快报"))
                {
                    map.addAttribute("news_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderBySortIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    break;
                }
                
            }
        }

        // 养车宝典
        if (null != catList && catList.size() > 0) {

            map.addAttribute("curing_page", tdArticleService
                    .findByMenuIdAndIsEnableOrderByIdDesc(11L, 0, ClientConstant.pageSize));
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
        adType = tdAdTypeService.findByTitle("首页顶部横幅广告");

        if (null != adType) {
            map.addAttribute("index_top_ad_list", tdAdService
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
                .findByGroupSalingOrderByGroupSaleStartTimeAsc(0, 5));
        
        // 正在进行百人团购
        map.addAttribute("baituan_cur_page", tdGoodsService
                .findByGroupSalingHundredOrderByGroupSaleStartTimeAsc(0, 6));


//         已经结束秒杀
//        map.addAttribute("miao_prev_page", tdGoodsService
//                .findByFlashSaleEndedOrderByFlashSaleStartTimeAsc(0, 5));
//        // 即将开始秒杀
//        map.addAttribute("miao_next_page", tdGoodsService
//                .findByFlashSaleGoingToStartOrderByFlashSaleStartTimeAsc(0, 5));
//        // 正在秒杀
//        map.addAttribute("miao_cur_page", tdGoodsService
//                .findByFlashSalingOrderByFlashSaleStartTimeAsc(0, 5));
        
        /*
         * 秒杀相关产品
         */
        // 8点
        Calendar cal = Calendar.getInstance();
        
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        map.addAttribute("miao_cur_8_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(cal.getTime(), 0, 5));
        map.addAttribute("miao_10_leftnumber", countleft(tdGoodsService.findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime())));
        
        cal.set(Calendar.HOUR_OF_DAY, 14);
        
        map.addAttribute("miao_cur_15_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(cal.getTime(), 0, 5));
        map.addAttribute("miao_14_leftnumber", countleft(tdGoodsService.findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime())));
        
        cal.set(Calendar.HOUR_OF_DAY, 20);
        
        map.addAttribute("miao_cur_23_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(cal.getTime(), 0, 5));
        map.addAttribute("miao_20_leftnumber", countleft(tdGoodsService.findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime())));
        
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        
        map.addAttribute("miao_next_8_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 14);
        
        map.addAttribute("miao_next_15_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 20);
        
        map.addAttribute("miao_next_23_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(cal.getTime(), 0, 5));
        
        cal.add(Calendar.DATE, -2);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        
        map.addAttribute("miao_prev_8_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 14);
       
        map.addAttribute("miao_prev_15_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 20);
        
        map.addAttribute("miao_prev_23_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(cal.getTime(), 0, 5));
        
        // 首页推荐商品
        map.addAttribute("index_recommend_goods_page", tdGoodsService
                .findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(0, 4));

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

        return "/client/index";
    }
    /**
	 * @author lc
	 * @注释：计算秒杀商品剩余数量
	 */
    public int countleft(List<TdGoods> tdGoods){
    	int totals = 0;
    	for(int i = 0; i < tdGoods.size(); i++){
    		if (null != tdGoods.get(i).getFlashSaleLeftNumber()) {
				totals += tdGoods.get(i).getFlashSaleLeftNumber();
			}
    	}
    	return totals;
    }
    
    @RequestMapping("/index")
    public String pcindex(HttpServletRequest req, Device device, ModelMap map) {
        
        tdCommonService.setHeader(map, req);

        // 商城快报
        List<TdArticleCategory> catList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != catList && catList.size() > 0) {
            for (TdArticleCategory tdCat : catList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("商城快报"))
                {
                    map.addAttribute("news_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    break;
                }
                
            }
        }

        // 养车宝典
        if (null != catList && catList.size() > 0) {

            map.addAttribute("curing_page", tdArticleService
                    .findByMenuIdAndIsEnableOrderByIdDesc(11L, 0, ClientConstant.pageSize));
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
        adType = tdAdTypeService.findByTitle("首页顶部横幅广告");

        if (null != adType) {
            map.addAttribute("index_top_ad_list", tdAdService
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
                .findByGroupSalingOrderByGroupSaleStartTimeAsc(0, 5));

//         已经结束秒杀
//        map.addAttribute("miao_prev_page", tdGoodsService
//                .findByFlashSaleEndedOrderByFlashSaleStartTimeAsc(0, 5));
//        // 即将开始秒杀
//        map.addAttribute("miao_next_page", tdGoodsService
//                .findByFlashSaleGoingToStartOrderByFlashSaleStartTimeAsc(0, 5));
//        // 正在秒杀
//        map.addAttribute("miao_cur_page", tdGoodsService
//                .findByFlashSalingOrderByFlashSaleStartTimeAsc(0, 5));
        
        /*
         * 秒杀相关产品
         */
        // 8点
        Calendar cal = Calendar.getInstance();
        
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        map.addAttribute("miao_cur_8_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 14);
        
        map.addAttribute("miao_cur_15_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 20);
        
        map.addAttribute("miao_cur_23_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        
        map.addAttribute("miao_next_8_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 14);
        
        map.addAttribute("miao_next_15_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 20);
        
        map.addAttribute("miao_next_23_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
        cal.add(Calendar.DATE, -2);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        
        map.addAttribute("miao_prev_8_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 14);
       
        map.addAttribute("miao_prev_15_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
        cal.set(Calendar.HOUR_OF_DAY, 20);
        
        map.addAttribute("miao_prev_23_page", tdGoodsService
                .findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(cal.getTime(), 0, 5));
        
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

        return "/client/index";
    }
}
