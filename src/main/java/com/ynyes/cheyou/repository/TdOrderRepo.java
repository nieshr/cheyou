package com.ynyes.cheyou.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
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
    @Query("select o from TdOrder o join o.orderGoodsList g where o.username=?1 and g.goodsId=?2")
    List<TdOrder> findByUsernameAndGoodsId(String username, Long goodsId);
    
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
    
    Long countByUsername(String username);
    
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
	 * @注释：同盟店订单收入查询
	 */
    List<TdOrder> findByStatusIdAndShopTitleOrStatusIdAndShopTitle(Long statusId, String diystiename, Long statusId1, String diystiename1);
    Page<TdOrder> findByStatusIdAndShopTitleOrStatusIdAndShopTitleOrderByIdDesc(Long statusId, String diystiename,  Long statusId1, String diystiename1, Pageable page);
    
    List<TdOrder> findByStatusIdAndShopTitleAndOrderTimeAfterOrStatusIdAndShopTitleAndOrderTimeAfterOrderByIdDesc(Long statusId, String diystiename, Date time, Long statusId1, String diystiename1, Date time1);
    Page<TdOrder> findByStatusIdAndShopTitleAndOrderTimeAfterOrStatusIdAndShopTitleAndOrderTimeAfterOrderByIdDesc(Long statusId, String diystiename, Date time, Long statusId1, String diystiename1, Date time1, Pageable page);
    
    /**
	 * @author lc
	 * @注释：同盟店返利收入
	 */
    List<TdOrder> findByUsernameIn(List<String> tdUsers);
    Page<TdOrder> findByUsernameInOrderByIdDesc(List<String> tdUsers , Pageable page);
    
    List<TdOrder> findByUsernameInAndOrderTimeAfterOrderByIdDesc(List<String> tdUsers,  Date time);
    Page<TdOrder> findByUsernameInAndOrderTimeAfterOrderByIdDesc(List<String> tdUsers,  Date time, Pageable page);
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
    /**
     * 按订单号查询
     * @author libiao
     */
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrPayTypeTitleContainingIgnoreCase(String orderNumber, String username, String payTypeTitle, Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrPayTypeTitleContainingIgnoreCaseOrderByIdDesc(String orderNumber, String username, String payTypeTitle, long type);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdOrUsernameContainingIgnoreCaseAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndTypeId(String orderNumber, Long typeId1, String username, Long typeId2, String payTypeTitle, Long typeId3, Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdOrUsernameContainingIgnoreCaseAndStatusIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdOrderByIdDesc(String orderNumber, Long statusId1, String username, Long statusId2, String payTypeTitle, Long statusId3);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdOrUsernameContainingIgnoreCaseAndStatusIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdOrderByIdDesc(String orderNumber,Long statusId1, String username, Long statusId2, String payTypeTitle, Long statusId3, Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdOrderByIdDesc(String orderNumber, Long statusId, Long typeId, String username, Long statusId2, Long typeId2, String payTypeTitle, Long statusId3, Long typeId3);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeId(String orderNumber, Long statusId, Long  typeId, String username, Long statusId2, Long  typeId2, String payTypeTitle, Long statusId3, Long  typeId3,  Pageable pageRequest);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndOrderTimeAfterOrderByIdDesc(String orderNumber, Date time, String orderNumber2, Date time2, String orderNumber3, Date time3);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndOrderTimeAfter(String orderNumber,Date time, String orderNumber2,Date time2, String orderNumber3,Date time3, Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrderByIdDesc(String orderNumber, Long typeId, Date time, String orderNumber2, Long typeId2, Date time2, String orderNumber3, Long typeId3, Date time3);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndTypeIdAndOrderTimeAfter(String orderNumber, Long typeId, Date time, String orderNumber2, Long typeId2, Date time2, String orderNumber3, Long typeId3, Date time3, Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrderByIdDesc(String orderNumber, Long statusId, Date time, String orderNumber2, Long statusId2, Date time2, String orderNumber3, Long statusId3, Date time3);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndOrderTimeAfter(String OrderNumber, Long statusId, Date time, String OrderNumber2, Long statusId2, Date time2, String OrderNumber3, Long statusId3, Date time3, Pageable page);
    
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(String orderNumber, Long statusId, Long typeId, Date time, String orderNumber2, Long statusId2, Long typeId2, Date time2, String orderNumber3, Long statusId3, Long typeId3, Date time3);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfter(String orderNumber,Long statusId, Long typeId, Date time, String orderNumber2,Long statusId2, Long typeId2, Date time2, String orderNumber3,Long statusId3, Long typeId3, Date time3, Pageable page);
}
