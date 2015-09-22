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
    
    Page<TdCoupon> findByIsDistributtedTrueOrderByIdDesc(Pageable page);
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueOrderByIdDesc(Long typeId,Pageable page);//
    
    
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(Pageable page);
    
    Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdOrderByIdDesc(Long diysiteId, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(Long diysiteId, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(Long diysiteId, Pageable page);
    
    Page<TdCoupon> findByIsDistributtedTrueAndUsernameContainingOrIsDistributtedTrueAndMobileContainingOrIsDistributtedTrueAndCarCodeContainingOrderByIdDesc(String keywords,String keywords1,String keywords2, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueAndUsernameContainingOrIsDistributtedTrueAndIsUsedTrueAndMobileContainingOrIsDistributtedTrueAndIsUsedTrueAndCarCodeContainingOrderByIdDesc(String keywords,String keywords1,String keywords2, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseAndUsernameContainingOrIsDistributtedTrueAndIsUsedFalseAndMobileContainingOrIsDistributtedTrueAndIsUsedFalseAndCarCodeContainingOrderByIdDesc(String keywords,String keywords1,String keywords2, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(Long diysiteId, String keywords, Long diysiteId1, String keywords1, Long diysiteId2, String keywords2, Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(Long diysiteId, String keywords, Long diysiteId1, String keywords1, Long diysiteId2, String keywords2,Pageable page);
    Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndCarCodeContainingOrderByIdDesc(Long diysiteId, String keywords, Long diysiteId1, String keywords1, Long diysiteId2, String keywords2, Pageable page);
    
    List<TdCoupon> findByIsDistributtedTrueOrderByIdDesc();
    
    List<TdCoupon> findTypeIdDistinctByIsDistributtedFalse();
    
    List<TdCoupon> findByDiySiteIdAndIsUsedTrue(Long diysiteId);
    
    Page<TdCoupon> findByDiySiteIdAndIsUsedTrue(Long diysiteId, Pageable page);
    
    Page<TdCoupon> findByDiySiteIdAndIsUsedTrueAndMobileContainingOrDiySiteIdAndIsUsedTrueAndUsernameContainingOrDiySiteIdAndIsUsedTrueAndCarCodeContaining(Long diysiteId, String mobile, Long diysiteId1, String username, Long diysiteId2, String carcode, Pageable page);
    
    TdCoupon findByMobileAndConsumerPassword(String mobile, String password);
    
    TdCoupon findByTypeId(Long typeId);
    TdCoupon findTopByTypeIdAndIsDistributtedFalse(Long typeId);
    
    /**
     *  类型筛选
     *  @author libiao
     *  
     * @param typeId
     * @param page
     * @return
     */
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(Long typeId,Pageable page);//
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(Long typeId,Pageable page);//
    
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndDiySiteIdOrderByIdDesc(long typeId,Long diysiteId, Pageable page);
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(long typeId,long diysiteId,Pageable page);
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(long typeId,Long diysiteId, Pageable page);
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndMobileContainingOrTypeIdAndIsDistributtedTrueAndCarCodeContainingOrderByIdDesc(long typeId,String keywords,long typeId1,String keywords1,long typeId2,String keywords2, Pageable page);
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedTrueAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsUsedTrueAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsUsedTrueAndCarCodeContainingOrderByIdDesc(long typeId,String keywords,long typeId1,String keywords1,long typeId2,String keywords2,Pageable page);
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedFalseAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsUsedFalseAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsUsedFalseAndCarCodeContainingOrderByIdDesc(long typeId,String keywords,long typeId1,String keywords1,long typeId2,String keywords2,Pageable page);
    
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(long typeId ,long diysiteId,String  keywords,
    		long typeId1,long diysiteId1, String keywords1,
    		long typeId2,long diysiteId2, String keywords2,Pageable page);

    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(long typeId,long diysiteId, String keywords,
    		long typeId1,long diysiteId1, String keywords1,
    		long typeId2,long diysiteId2, String keywords2,Pageable page);
    
    Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndCarCodeContainingOrderByIdDesc(long typeId,long diysiteId, String keywords,
    		long typeId1,long diysiteId1, String keywords1,
    		long typeId2,long diysiteId2, String keywords2,Pageable page);
    
    
    
}
