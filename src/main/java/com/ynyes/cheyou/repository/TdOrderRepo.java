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
	 * @注释：同盟店订单查询
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
	 * @author lc
	 * @注释：同盟店信息查询
	 */
    List<TdOrder> findByShopIdAndStatusIdOrderByIdDesc(long shopId, long statusId);
    
    /**
	 * @author lc
	 * @注释：按订单类型和状态查询
	 */
    Page<TdOrder> findByStatusIdAndTypeIdOrderByIdDesc(long statusId, long typeId, Pageable page);
    List<TdOrder> findByStatusIdAndTypeIdOrderByIdDesc(long statusId, long typeId);
    Page<TdOrder> findByStatusIdOrderByIdDesc(long statusId, Pageable page);
    List<TdOrder> findByStatusIdOrderByIdDesc(long statusId);
    Page<TdOrder> findBytypeIdOrderByIdDesc(long typeId, Pageable page);
    List<TdOrder> findBytypeIdOrderByIdDesc(long typeId);
    /**
	 * @author lc
	 * @注释： 按时间、订单类型和订单状态查询
	 */
    Page<TdOrder> findByOrderTimeAfterOrderByIdDesc(Date time, Pageable page);
    List<TdOrder> findByOrderTimeAfterOrderByIdDesc(Date time);
    Page<TdOrder> findByStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(long statusId, long typeId, Date time, Pageable page);
    List<TdOrder> findByStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(long statusId, long typeId, Date time);
    Page<TdOrder> findByStatusIdAndOrderTimeAfterOrderByIdDesc(long statusId, Date time, Pageable page);
    List<TdOrder> findByStatusIdAndOrderTimeAfterOrderByIdDesc(long statusId, Date time);
    Page<TdOrder> findBytypeIdAndOrderTimeAfterOrderByIdDesc(long typeId, Date time, Pageable page);
    List<TdOrder> findBytypeIdAndOrderTimeAfterOrderByIdDesc(long typeId, Date time);
    /**
     * 按交易状态查询
     * @author libiao
     */
    List<TdOrder> findByStatusId(Long statusId);
//    List<TdOrder> findAll();
    
}
