package com.ynyes.cheyou.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdGoods;

/**
 * TdGoods 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdGoodsRepo extends
		PagingAndSortingRepository<TdGoods, Long>,
		JpaSpecificationExecutor<TdGoods> 
{
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrue(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(String categoryId, Pageable page);
    
    Page<TdGoods> findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(Pageable page);
    
    Page<TdGoods> findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySoldNumberDesc(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueOrderByOnSaleTimeDesc(String categoryId, Pageable page);
    
    List<TdGoods> findTop10ByIsOnSaleTrueOrderBySoldNumberDesc();
    
    Page<TdGoods> findByIsOnSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsOnSaleTrueAndIsFlashSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsOnSaleTrueAndIsGroupSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsFlashSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsGroupSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsOnSaleFalse(Pageable page);
    
    Page<TdGoods> findByIsOnSaleFalseAndIsFlashSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsOnSaleFalseAndIsGroupSaleTrue(Pageable page);
    
    List<TdGoods> findByIdAndIsOnSaleTrue(Iterable<Long> ids);
    
    Page<TdGoods> findByCategoryIdTreeContainingOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsFlashSaleTrueOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsGroupSaleTrueOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(String catId, Pageable page);
    
    Page<TdGoods> findByTitleContainingOrSubTitleContainingOrDetailContainingOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingAndIsFlashSaleTrueOrSubTitleContainingAndIsFlashSaleTrueOrDetailContainingAndIsFlashSaleTrueOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingAndIsGroupSaleTrueOrSubTitleContainingAndIsGroupSaleTrueOrDetailContainingAndIsGroupSaleTrueOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingAndIsOnSaleTrueOrSubTitleContainingAndIsOnSaleTrueOrDetailContainingAndIsOnSaleTrueOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrSubTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrDetailContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrSubTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrDetailContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingAndIsOnSaleFalseOrSubTitleContainingAndIsOnSaleFalseOrDetailContainingAndIsOnSaleFalseOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrSubTitleContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrDetailContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrSubTitleContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrDetailContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingOrCategoryIdTreeContainingAndSubTitleContainingOrCategoryIdTreeContainingAndDetailContainingOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsGroupSaleTrueOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsFlashSaleTrueOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleFalseOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(String categoryId, Long leftNumber, Double priceLow, Double priceHigh, String paramStr, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(String categoryId, Long brandId, Long leftNumber, Double priceLow, Double priceHigh, String paramStr, Pageable page);

    Page<TdGoods> findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndParamValueCollectLikeAndIsOnSaleTrue(String categoryId, Long leftNumber, String paramStr, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndParamValueCollectLikeAndIsOnSaleTrue(String categoryId, Long brandId, Long leftNumber, String paramStr, Pageable page);

    Page<TdGoods> findByCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(String categoryId, Double priceLow, Double priceHigh, String paramStr, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndBrandIdAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(String categoryId, Long brandId, Double priceLow, Double priceHigh, String paramStr, Pageable page);

    Page<TdGoods> findByCategoryIdTreeContainingAndParamValueCollectLikeAndIsOnSaleTrue(String categoryId, String paramStr, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndBrandIdAndParamValueCollectLikeAndIsOnSaleTrue(String categoryId, Long brandId, String paramStr, Pageable page);

    // 正在
    //Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeAfterAndGroupSaleStartTimeBeforeOrderByGroupSaleStartTimeAsc(Date current, Pageable page);
    
    // 正在团购
    Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeAfterAndGroupSaleStartTimeBeforeOrderByGroupSaleStartTimeAsc(Date current, Date current1, Pageable page);
    
    // 即将开始团购
    Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStartTimeAfterOrderByGroupSaleStartTimeAsc(Date current, Pageable page);
    
    // 已经结束团购
    Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeBeforeOrderByGroupSaleStartTimeAsc(Date current, Pageable page);
    
    // 全部团购
    Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueOrderByGroupSaleStartTimeAsc(Pageable page);
    
    // 正在秒杀
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeOrderByFlashSaleStartTimeAsc(Date current, Date current1, Pageable page);
    
    // 通过时间筛选
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date startTime, Pageable page);
    
    // 正在秒杀 限定开始时间
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date current, Date current1, Date startTime, Pageable page);
    
    // 即将开始秒杀
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterOrderByFlashSaleStartTimeAsc(Date current, Pageable page);
    
    // 即将开始秒杀，限定开始时刻
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date current, Date startTime, Pageable page);
    
    // 已经结束秒杀
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeOrderByFlashSaleStartTimeAsc(Date current, Pageable page);
    
    // 已经结束秒杀，限定开始时刻
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date current, Date startTime, Pageable page);
    
    // 全部秒杀
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueOrderByFlashSaleStartTimeAsc(Pageable page);

    Page<TdGoods> findByTitleContainingAndIsOnSaleTrueOrSubTitleContainingAndIsOnSaleTrueOrParamValueCollectContainingAndIsOnSaleTrueOrDetailContainingAndIsOnSaleTrue(String key1,
            String key2,
            String key3,
            String key4,
            Pageable page);

    List<TdGoods> findByProductIdAndIsOnSaleTrue(Long productId);
    
    Page<TdGoods> findByReturnPriceNotAndIsOnSaleTrue(double returnPrice, Pageable page);
    
    Page<TdGoods> findByReturnPriceNotAndTitleContainingAndIsOnSaleTrue(double returnPrice, String keywords, Pageable page);
    
    
}
