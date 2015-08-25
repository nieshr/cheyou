package com.ynyes.cheyou.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.cheyou.entity.TdBrand;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdGoodsCombination;
import com.ynyes.cheyou.entity.TdGoodsGift;
import com.ynyes.cheyou.entity.TdGoodsParameter;
import com.ynyes.cheyou.entity.TdPriceChangeLog;
import com.ynyes.cheyou.entity.TdProductCategory;
import com.ynyes.cheyou.entity.TdProvider;
import com.ynyes.cheyou.entity.TdSite;
import com.ynyes.cheyou.entity.TdWarehouse;
import com.ynyes.cheyou.repository.TdGoodsRepo;

/**
 * TdGoods 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdGoodsService {
    @Autowired
    TdGoodsRepo repository;

    @Autowired
    TdProductCategoryService tdProductCategoryService;

    @Autowired
    TdBrandService tdBrandService;

    @Autowired
    TdArticleService tdArticleService;

    @Autowired
    TdParameterService tdParameterService;

    @Autowired
    TdGoodsParameterService tdGoodsParameterService;

    @Autowired
    TdWarehouseService tdWarehouseService;

    @Autowired
    TdProviderService tdProviderService;

    @Autowired
    TdGoodsGiftService tdGoodsGiftService;

    @Autowired
    TdGoodsCombinationService tdGoodsCombinationService;

    @Autowired
    TdPriceChangeLogService tdPriceChangeLogService;

    @Autowired
    TdSiteService tdSiteService;

    /**
     * 查找所有商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findAll(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findAll(pageRequest);
    }

    public TdGoods findById(Long id) {
        return repository.findById(id);
    }

    public Page<TdGoods> findAllOrderBySortIdAsc(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findAll(pageRequest);
    }

    public Page<TdGoods> findByIsFlashSaleTrueOrderBySortIdAsc(int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsFlashSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsGroupSaleTrueOrderBySortIdAsc(int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsGroupSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleTrueOrderBySortIdAsc(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleTrueAndFlashSaleTrueOrderBySortIdAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleTrueAndIsFlashSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleTrueAndGroupSaleTrueOrderBySortIdAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleTrueAndIsGroupSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleFalseOrderBySortIdAsc(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleFalse(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleFalseAndIsFlashSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleFalseAndIsGroupSaleTrue(pageRequest);
    }

    public Page<TdGoods> findAllAndIsOnSaleTrue(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleTrue(pageRequest);
    }

    public List<TdGoods> findTop10ByIsOnSaleTrueOrderBySoldNumberDesc() {
        return repository.findTop10ByIsOnSaleTrueOrderBySoldNumberDesc();
    }

    public Page<TdGoods> searchAndOrderBySortIdAsc(String keywords, int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingOrSubTitleContainingOrDetailContaining(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingAndIsFlashSaleTrueOrSubTitleContainingAndIsFlashSaleTrueOrDetailContainingAndIsFlashSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsGroupSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingAndIsGroupSaleTrueOrSubTitleContainingAndIsGroupSaleTrueOrDetailContainingAndIsGroupSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleTrueOrderBySortIdAsc(String keywords,
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingAndIsOnSaleTrueOrSubTitleContainingAndIsOnSaleTrueOrDetailContainingAndIsOnSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrSubTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrDetailContainingAndIsOnSaleTrueAndIsGroupSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrSubTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrDetailContainingAndIsOnSaleTrueAndIsFlashSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleFalseOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingAndIsOnSaleFalseOrSubTitleContainingAndIsOnSaleFalseOrDetailContainingAndIsOnSaleFalse(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrSubTitleContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrDetailContainingAndIsOnSaleFalseAndIsFlashSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrSubTitleContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrDetailContainingAndIsOnSaleFalseAndIsGroupSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingOrCategoryIdTreeContainingAndSubTitleContainingOrCategoryIdTreeContainingAndDetailContaining(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsGroupSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsGroupSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsFlashSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsGroupSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsFlashSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleFalseOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsFlashSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsGroupSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseAndIsFlashSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsGroupSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public List<TdGoods> findByIdAndIsOnSaleTrue(Iterable<Long> ids) {
        return repository.findByIdAndIsOnSaleTrue(ids);
    }

    /**
     * 按类型查找所有商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByReturnPriceNotZeroAndIsOnSaleTrue(int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByReturnPriceNotAndIsOnSaleTrue(0.0, pageRequest);
    }

    public Page<TdGoods> findByReturnPriceNotZeroAndSearchAndIsOnSaleTrue(
            int page, int size, String keywords) {
        if (null == keywords) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByReturnPriceNotAndTitleContainingAndIsOnSaleTrue(0.0,
                        keywords, pageRequest);
    }

    /**
     * 正在团购商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSalingOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeAfterAndGroupSaleStartTimeBeforeOrderByGroupSaleStartTimeAsc(
                        new Date(), new Date(), pageRequest);
    }
    
    /**
     * 正在百人团购商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSalingHundredOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeAfterAndGroupSaleHundredStartTimeBeforeOrderByGroupSaleHundredStartTimeAsc(
                        new Date(), new Date(), pageRequest);
    }

    /**
     * 即将开始团购
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleGoingToStartOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStartTimeAfterOrderByGroupSaleStartTimeAsc(
                        new Date(), pageRequest);
    }
    
    /**
	 * @author lc
	 * 即将开始百人团购
	 */
	public Page<TdGoods> findByGroupSaleGoingToHundredOrderByGroupSaleHundredStartTimeAsc(int page, int size){
		 PageRequest pageRequest = new PageRequest(page, size, new Sort(
	                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		 return repository.findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStartTimeAfterOrderByGroupSaleHundredStartTimeAsc(
                 new Date(), pageRequest);
	} 
	
    /**
     * 已结束团购
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleEndedOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeBeforeOrderByGroupSaleStartTimeAsc(
                        new Date(), pageRequest);
    }
    
    /**
     * 已经结束百人团购
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleHundredEndedOrderByGroupSaleHundredStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeBeforeOrderByGroupSaleHundredStartTimeAsc(
                        new Date(), pageRequest);
    }
    
    /**
	 * @author lc
	 * 已经结束百人团购
	 */
    public Page<TdGoods> findByGroupSaleEndedHundredOrderByGroupSaleHundredStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeBeforeOrderByGroupSaleHundredStartTimeAsc(
                        new Date(), pageRequest);
    }
    
    /**
     * 所有团购
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleAllOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleTrueAndIsOnSaleTrueOrderByGroupSaleStartTimeAsc(pageRequest);
    }
    
    /**
     * 所有百人团
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleHundredAllOrderByGroupSaleHundredStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleHundredTrueAndIsOnSaleTrueOrderByGroupSaleHundredStartTimeAsc(pageRequest);
    }
    

    /**
     * 正在秒杀商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSalingOrderByFlashSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeOrderByFlashSaleStartTimeAsc(
                        new Date(), new Date(), pageRequest);
    }

    /**
     * 通过开始时间查找秒杀商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
            Date startTime, int page, int size) 
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
                        startTime, pageRequest);
    }
    public Page<TdGoods> findByIsFlashSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(
            Date startTime, int page, int size) 
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(
                        startTime, pageRequest);
    }
    
    public List<TdGoods> findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date startTime){
    	return repository.findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(startTime);
    }
    /**
     * 正在秒杀商品，限定开始时间
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSalingAndStartTimeOrderByFlashSaleStartTimeAsc(
            Date startTime, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
                        new Date(), new Date(), startTime, pageRequest);
    }

    /**
     * 即将开始秒杀
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleGoingToStartOrderByFlashSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterOrderByFlashSaleStartTimeAsc(
                        new Date(), pageRequest);
    }

    /**
     * 即将开始秒杀，限定开始时间
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleGoingToStartAndStartTimeOrderByFlashSaleStartTimeAsc(
            Date startTime, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
                        new Date(), startTime, pageRequest);
    }

    /**
     * 已结束秒杀
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleEndedOrderByFlashSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeOrderByFlashSaleStartTimeAsc(
                        new Date(), pageRequest);
    }

    /**
     * 已结束秒杀，限定开始时间
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleEndedAndStartTimeOrderByFlashSaleStartTimeAsc(
            Date startTime, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
                        new Date(), startTime, pageRequest);
    }

    /**
     * 所有秒杀
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleAllOrderByFlashSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueOrderByFlashSaleStartTimeAsc(pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository.findByCategoryIdTreeContaining(
                catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsFlashSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsFlashSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsGroupSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsGroupSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleFalse(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsOnSaleTrue(Long catId, int page,
            int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository.findByCategoryIdTreeContainingAndIsOnSaleTrue(catStr,
                pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
                        catStr, pageRequest);
    }

    public Page<TdGoods> findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
    }

    public Page<TdGoods> findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
                        catStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsOnSaleTrueOrderBySoldNumberDesc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySoldNumberDesc(
                        catStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsOnSaleTrueOrderByOnSaleTimeDesc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                        catStr, pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleTrueOrderBySoldNumberDesc(int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "soldNumber"));

        return repository.findByIsOnSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
            long catId, double priceLow, double priceHigh, int page, int size,
            List<String> paramValueList) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "soldNumber"));

        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", 0L, priceLow, priceHigh, paramStr,
                        pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, double priceLow, double priceHigh, 
            List<String> paramValueList, Pageable pageRequest) {

        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", 0L, priceLow, priceHigh, paramStr,
                        pageRequest);
    }

    

    public Page<TdGoods> findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, long brandId, double priceLow, double priceHigh,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", brandId, 0L, priceLow, priceHigh,
                        paramStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrue(
            long catId, List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", 0L, paramStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrue(
            long catId, long brandId,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", brandId, 0L, paramStr, pageRequest);
    }

    

    public Page<TdGoods> findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, double priceLow, double priceHigh,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", priceLow, priceHigh, paramStr,
                        pageRequest);
    }

    public Page<TdGoods> searchGoods(String keywords, int page, int size) {
        if (null == keywords) {
            return null;
        }
        
        keywords = keywords.replace(" ", "%");

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleTrueOrSubTitleContainingAndIsOnSaleTrueOrParamValueCollectContainingAndIsOnSaleTrueOrDetailContainingAndIsOnSaleTrue(
                        keywords, keywords, keywords, keywords, pageRequest);
    }

    /**
     * 搜索商品
     * 
     * @param keywords
     *            关键字
     * @param page
     *            页号
     * @param size
     *            页大小
     * @param sortName
     *            排序字段名
     * @param sd
     *            排序方向
     * @return
     */
    public Page<TdGoods> searchGoods(String keywords, int page, int size,
            String sortName, Direction dir) {
        if (null == keywords || null == sortName) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(dir,
                sortName));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleTrueOrSubTitleContainingAndIsOnSaleTrueOrParamValueCollectContainingAndIsOnSaleTrueOrDetailContainingAndIsOnSaleTrue(
                        keywords, keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, long brandId, double priceLow, double priceHigh,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndBrandIdAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", brandId, priceLow, priceHigh,
                        paramStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
            long catId, List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        if (null != paramValueList) {
            for (int i = 0; i < paramValueList.size(); i++) {
                String value = paramValueList.get(i);
                if (!"".equals(value)) {
                    paramStr += value;
                    paramStr += "%";
                }
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", paramStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrue(
            long catId, long brandId,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndBrandIdAndParamValueCollectLikeAndIsOnSaleTrue(
                        "[" + catId + "]", brandId, paramStr, pageRequest);
    }

    public List<TdGoods> findByProductIdAndIsOnSaleTrue(Long productId) {
        if (null == productId) {
            return null;
        }

        return repository.findByProductIdAndIsOnSaleTrue(productId);
    }

    /**
     * 删除
     * 
     * @param id
     *            菜单项ID
     */
    public void delete(Long id) {
        if (null != id) {
            repository.delete(id);
        }
    }

    /**
     * 删除
     * 
     * @param e
     *            菜单项
     */
    public void delete(TdGoods e) {
        if (null != e) {
            repository.delete(e);
        }
    }

    /**
     * 查找
     * 
     * @param id
     *            ID
     * @return
     */
    public TdGoods findOne(Long id) {
        if (null == id) {
            return null;
        }

        return repository.findOne(id);
    }

    /**
     * 判断该商品是否正在进行秒杀
     * 
     * @param tdGoods
     * @return
     */
    public boolean isFlashSaleTrue(TdGoods tdGoods) {
        if (null == tdGoods) {
            return false;
        }

        Date curr = new Date();

        if (null != tdGoods.getIsFlashSale() && tdGoods.getIsFlashSale()
                && null != tdGoods.getFlashSaleStartTime()
                && tdGoods.getFlashSaleStartTime().before(curr)
                && null != tdGoods.getFlashSaleStopTime()
                && tdGoods.getFlashSaleStopTime().after(curr)
                && null != tdGoods.getFlashSaleLeftNumber()
                && tdGoods.getFlashSaleLeftNumber().compareTo(0L) > 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断该商品是否正在进行十人团
     * 
     * @param tdGoods
     * @return
     */
    public boolean isGroupSaleTrue(TdGoods tdGoods) {
        if (null == tdGoods) {
            return false;
        }

        Date curr = new Date();

        if (null != tdGoods.getIsGroupSale() && tdGoods.getIsGroupSale()
                && null != tdGoods.getGroupSaleStartTime()
                && tdGoods.getGroupSaleStartTime().before(curr)
                && null != tdGoods.getGroupSaleStopTime()
                && tdGoods.getGroupSaleStopTime().after(curr)
                && null != tdGoods.getGroupSaleLeftNumber()
                && tdGoods.getGroupSaleLeftNumber().compareTo(0L) > 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断该商品是否正在进行百人团
     * 
     * @param tdGoods
     *            商品
     * @return
     */
    public boolean isGroupSaleHundredTrue(TdGoods tdGoods) {
        if (null == tdGoods) {
            return false;
        }

        Date curr = new Date();

        if (null != tdGoods.getIsGroupSaleHundred()
                && tdGoods.getIsGroupSaleHundred()
                && null != tdGoods.getGroupSaleHundredStartTime()
                && tdGoods.getGroupSaleHundredStartTime().before(curr)
                && null != tdGoods.getGroupSaleHundredStopTime()
                && tdGoods.getGroupSaleHundredStopTime().after(curr)
                && null != tdGoods.getGroupSaleHundredLeftNumber()
                && tdGoods.getGroupSaleHundredLeftNumber().compareTo(0L) > 0) {
            return true;
        }

        return false;
    }

    public TdGoods save(TdGoods e)
    {
        if (null == e)
        {
            return null;
        }
        
        return repository.save(e);
    }
    /**
     * 保存类型
     * 
     * @param e
     * @return
     */
    public TdGoods save(TdGoods e, String manager) {
        if (null == e) {
            return null;
        }

        // 参数类型ID
        Long paramCategoryId = null;

        // 保存分类名称
        if (null != e.getCategoryId()) {
            TdProductCategory cat = tdProductCategoryService.findOne(e
                    .getCategoryId());
            e.setCategoryTitle(cat.getTitle());
            e.setCategoryIdTree(cat.getParentTree());

            paramCategoryId = cat.getParamCategoryId();
        }

        // 保存品牌名称
        if (null != e.getCategoryId()) {
            TdBrand brand = tdBrandService.findOne(e.getBrandId());

            if (null != brand) {
                e.setBrandTitle(brand.getTitle());
            }
        }

        // 保存销售价
        // if (null == e.getReturnPrice()) {
        // e.setReturnPrice(0.0);
        // }
        //
        // if (null == e.getOutFactoryPrice()) {
        // e.setOutFactoryPrice(0.0);
        // }
        //
        // e.setSalePrice(e.getReturnPrice() + e.getOutFactoryPrice());

        // 创建时间
        if (null == e.getCreateTime()) {
            e.setCreateTime(new Date());
        }

        // 上架时间
        if (null == e.getOnSaleTime() && e.getIsOnSale()) {
            e.setOnSaleTime(new Date());
        }

        // 站点
        if (null != e.getSiteId()) {
            TdSite s = tdSiteService.findOne(e.getSiteId());

            if (null != s) {
                e.setSiteTitle(s.getTitle());
            }
        }

        // 仓库名
        if (null != e.getWarehouseId()) {
            TdWarehouse w = tdWarehouseService.findOne(e.getWarehouseId());

            if (null != w) {
                e.setWarehouseTitle(w.getTitle());
            }
        }

        // 供应商名
        if (null != e.getProductId()) {
            TdProvider p = tdProviderService.findOne(e.getProductId());

            if (null != p) {
                e.setProviderTitle(p.getTitle());
            }
        }

        // 当修改时，若切换过类型，参数数量由多变少，需要删除多余的参数
        if (null != e.getId() && null != paramCategoryId) {
            int count = tdParameterService
                    .countByCategoryTreeContaining(paramCategoryId);
            int size = e.getParamList().size();

            if (size > count) {
                List<TdGoodsParameter> subList = e.getParamList().subList(
                        count, size);
                tdGoodsParameterService.delete(subList);
                e.getParamList().removeAll(subList);
            }
        }

        // 当修改时，赠品数量减少时，需删除多余的赠品
        if (null != e.getId() && null != e.getGiftList()
                && null != e.getTotalGift()) {
            int count = e.getTotalGift();
            int size = e.getGiftList().size();

            if (size > count) {
                List<TdGoodsGift> subList = e.getGiftList()
                        .subList(count, size);
                tdGoodsGiftService.delete(subList);
                e.getGiftList().removeAll(subList);
            }
        }

        // 当修改时，组合商品数量减少时，需删除多余的组合商品
        if (null != e.getId() && null != e.getCombList()
                && null != e.getTotalComb()) {
            int count = e.getTotalComb();
            int size = e.getCombList().size();

            if (size > count) {
                List<TdGoodsCombination> subList = e.getCombList().subList(
                        count, size);
                tdGoodsCombinationService.delete(subList);
                e.getCombList().removeAll(subList);
            }
        }

        if (null != e.getParamList() && e.getParamList().size() > 0) {
            String valueCollect = "";
            for (TdGoodsParameter gp : e.getParamList()) {
                valueCollect += gp.getValue();
                valueCollect += ",";
            }
            e.setParamValueCollect(valueCollect);

            // 保存参数
            tdGoodsParameterService.save(e.getParamList());
        } else {
            e.setParamValueCollect("");
        }

        if (null == e.getId()) {
            if (null != e.getGiftList() && e.getGiftList().size() > 0) {
                e.setTotalGift(e.getGiftList().size());
            }

            if (null != e.getCombList() && e.getCombList().size() > 0) {
                e.setTotalComb(e.getCombList().size());
            }
        }

        // 保存赠品
        tdGoodsGiftService.save(e.getGiftList());

        // 保存组合
        tdGoodsCombinationService.save(e.getCombList());

        e = repository.save(e);

        // 添加改价记录
        TdPriceChangeLog priceLog = tdPriceChangeLogService
                .findTopByGoodsIdOrderByIdDesc(e.getId());

        // 没有改过价，或改价后的记录与当前销售价不相等
        if (null == priceLog || !priceLog.getPrice().equals(e.getSalePrice())) {
            TdPriceChangeLog newPriceLog = new TdPriceChangeLog();

            newPriceLog.setCreateTime(new Date());
            newPriceLog.setGoodsId(e.getId());
            newPriceLog.setGoodsTitle(e.getTitle()
                    + (null == e.getSelectOneValue() ? "" : " "
                            + e.getSelectOneValue())
                    + (null == e.getSelectTwoValue() ? "" : " "
                            + e.getSelectTwoValue())
                    + (null == e.getSelectThreeValue() ? "" : " "
                            + e.getSelectThreeValue()));
            newPriceLog.setOperator(manager);

            if (null != priceLog) {
                newPriceLog.setOriginPrice(priceLog.getPrice());
            }

            newPriceLog.setPrice(e.getSalePrice());
            newPriceLog.setSortId(99L);

            tdPriceChangeLogService.save(newPriceLog);
        }

        return e;
    }

    /**
     * 计算实时秒杀价
     * 
     * @param goods
     * @return
     */
    public Double getFlashPrice(TdGoods goods) {
        if (null == goods) {
            return null;
        }

        Double flashPrice = null;
        Date curr = new Date();

        if (null != goods.getIsFlashSale()
                && null != goods.getFlashSaleStartTime()
                && null != goods.getFlashSaleStopTime()
                && null != goods.getFlashSalePrice() && goods.getIsFlashSale()
                && goods.getFlashSaleStopTime().after(curr)
                && goods.getFlashSaleStartTime().before(curr)) {
            // 剩余毫秒数
            long ts = goods.getFlashSaleStopTime().getTime() - curr.getTime();
            // 总共毫秒数
            long allts = goods.getFlashSaleStopTime().getTime()
                    - goods.getFlashSaleStartTime().getTime();

            flashPrice = goods.getFlashSalePrice() * ts / allts;

            BigDecimal b = new BigDecimal(flashPrice);

            flashPrice = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        return flashPrice;
    }
}
