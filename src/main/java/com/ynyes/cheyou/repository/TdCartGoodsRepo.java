package com.ynyes.cheyou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdCartGoods;

/**
 * TdCartGoods 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdCartGoodsRepo extends
		PagingAndSortingRepository<TdCartGoods, Long>,
		JpaSpecificationExecutor<TdCartGoods> 
{
//    TdCartGoods findTopByGoodsIdAndPriceAndUsername(Long goodsId, Double price, String username);
    TdCartGoods findTopByGoodsIdAndUsername(Long goodsId, String username);
    
//    List<TdCartGoods> findByGoodsIdAndPriceAndUsername(Long goodsId, Double price, String username);
    
    List<TdCartGoods> findByGoodsIdAndUsername(Long goodsId, String username);
    
    List<TdCartGoods> findByUsername(String username);
    
    List<TdCartGoods> findByUsernameAndIsSelectedTrue(String username);
}
