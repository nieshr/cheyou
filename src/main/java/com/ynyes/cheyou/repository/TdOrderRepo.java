package com.ynyes.cheyou.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdOrder;

/**
 * TdOrder 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdOrderRepo extends
		PagingAndSortingRepository<TdOrder, Long>,
		JpaSpecificationExecutor<TdOrder> 
{
    Page<TdOrder> findByStatusIdOrderByIdDesc(Long statusId, Pageable page);
    
    Page<TdOrder> findByUsernameOrderByIdDesc(String username, Pageable page);
    
    Page<TdOrder> findByUsernameAndOrderTimeAfterOrderByIdDesc(String username, Date time, Pageable page);
    
    Page<TdOrder> findByUsernameAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(String username, Date time, String keywords, Pageable page);
    
    Page<TdOrder> findByUsernameAndOrderNumberContainingOrderByIdDesc(String username, String keywords, Pageable page);
    
    Page<TdOrder> findByUsernameAndStatusIdOrderByIdDesc(String username, Long statusId, Pageable page);
    
    Page<TdOrder> findByUsernameAndStatusIdAndOrderNumberContainingOrderByIdDesc(String username, Long statusId, String keywords, Pageable page);
    
    Page<TdOrder> findByUsernameAndStatusIdAndOrderTimeAfterOrderByIdDesc(String username, Long statusId, Date time, Pageable page);
    
    Page<TdOrder> findByUsernameAndStatusIdAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(String username, Long statusId, Date time, String keywords, Pageable page);
  
    Long countByUsernameAndStatusId(String username, Long statusId);
    
    TdOrder findByOrderNumber(String orderNumber);
    
    /**
	 * @author lichong
	 * @注释：
	 */
    Page<TdOrder> findByshopTitleOrderByIdDesc(String diystiename, Pageable page);
    Page<TdOrder> findByshopTitleAndOrderNumberContainingOrderByIdDesc(String diystiename, String keywords, Pageable page);
    Page<TdOrder> findByshopTitleAndStatusIdAndOrderNumberContainingOrderByIdDesc(String diystiename, Long statusId, String keywords, Pageable page);
    Page<TdOrder> findByshopTitleAndStatusIdOrderByIdDesc(String diystiename, Long statusId, Pageable page);
    Page<TdOrder> findByshopTitleAndOrderTimeAfterOrderByIdDesc(String diystiename, Date time, Pageable page);
    Page<TdOrder> findByshopTitleAndStatusIdAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(String diystiename, Long statusId, Date time, String keywords, Pageable page);
    Page<TdOrder> findByshopTitleAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(String diystiename, Date time, String keywords, Pageable page);
    Page<TdOrder> findByshopTitleAndStatusIdAndOrderTimeAfterOrderByIdDesc(String diystiename, Long statusId, Date time, Pageable page);
    
    /**
     * 按交易状态查询
     * @author libiao
     */
    List<TdOrder> findByStatusId(Long statusId);
    
//    List<TdOrder> findAll();
    
}
