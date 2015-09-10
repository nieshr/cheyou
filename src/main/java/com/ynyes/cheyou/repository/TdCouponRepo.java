package com.ynyes.cheyou.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdCoupon;

/**
 * TdCoupon 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdCouponRepo extends
		PagingAndSortingRepository<TdCoupon, Long>,
		JpaSpecificationExecutor<TdCoupon> 
{
	TdCoupon findByDiySiteIdAndTypeTitleAndIsDistributtedFalse(Long diySiteId,String typeTitle);  //zhangji
    List<TdCoupon> findByUsernameAndExpireTimeAfterAndIsDistributtedTrueAndIsUsedFalse(String username, Date current);
    
    List<TdCoupon> findByMobileAndExpireTimeAfterAndIsDistributtedTrueAndIsUsedFalse(String mobile, Date current);
    
    List<TdCoupon> findByUsernameAndIsDistributtedTrue(String username);
    
    List<TdCoupon> findByMobileAndIsDistributtedTrue(String mobile);
    
    List<TdCoupon> findByTypeIdAndIsDistributtedFalse(Long typeId);
    
    TdCoupon findTopByTypeIdAndDiySiteIdAndIsDistributtedFalse(Long typeId, Long diySiteId);
    
    List<TdCoupon> findByTypeIdAndIsDistributtedTrueOrderByIdDesc(Long typeId);
    
    TdCoupon findTopByTypeIdAndMobileAndIsDistributtedTrue(Long typeId, String mobile);
    
    Page<TdCoupon> findByIsDistributtedFalseOrderBySortIdAsc(Pageable page);
    
    Page<TdCoupon> findByIsDistributtedTrueOrderBySortIdAsc(Pageable page);
    
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(Pageable page);
    
    Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdOrderByIdDesc(Long diysiteId, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(Long diysiteId, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(Long diysiteId, Pageable page);
    
    Page<TdCoupon> findByIsDistributtedTrueAndUsernameContainingOrIsDistributtedTrueAndMobileContainingOrIsDistributtedTrueAndCarCodeContaining(String keywords,String keywords1,String keywords2, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueAndUsernameContainingOrIsDistributtedTrueAndIsUsedTrueAndMobileContainingOrIsDistributtedTrueAndIsUsedTrueAndCarCodeContaining(String keywords,String keywords1,String keywords2, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseAndUsernameContainingOrIsDistributtedTrueAndIsUsedFalseAndMobileContainingOrIsDistributtedTrueAndIsUsedFalseAndCarCodeContaining(String keywords,String keywords1,String keywords2, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndDiySiteIdAndCarCodeContaining(Long diysiteId, String keywords, Long diysiteId1, String keywords1, Long diysiteId2, String keywords2, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndCarCodeContaining(Long diysiteId, String keywords, Long diysiteId1, String keywords1, Long diysiteId2, String keywords2,Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndCarCodeContaining(Long diysiteId, String keywords, Long diysiteId1, String keywords1, Long diysiteId2, String keywords2, Pageable page);
    
    List<TdCoupon> findByIsDistributtedTrueOrderByIdDesc();
    
    List<TdCoupon> findTypeIdDistinctByIsDistributtedFalse();
    
    List<TdCoupon> findByDiySiteIdAndIsUsedTrue(Long diysiteId);
    
    TdCoupon findByMobileAndConsumerPassword(String mobile, String password);
    
    TdCoupon findByTypeId(Long typeId);
}
