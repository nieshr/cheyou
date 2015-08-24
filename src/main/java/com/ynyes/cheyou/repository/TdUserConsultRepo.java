package com.ynyes.cheyou.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdUserConsult;

import javassist.bytecode.LineNumberAttribute;

/**
 * TdUserConsult 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserConsultRepo extends
		PagingAndSortingRepository<TdUserConsult, Long>,
		JpaSpecificationExecutor<TdUserConsult> 
{
    Page<TdUserConsult> findByStatusIdOrderBySortIdAsc(Long statusId, Pageable page);
    
    Page<TdUserConsult> findByStatusIdOrderByIdDesc(Long statusId, Pageable page);
    
    Page<TdUserConsult> findByUsernameContainingOrGoodsTitleContainingOrContentContainingOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdUserConsult> findByUsernameContainingOrGoodsTitleContainingOrContentContainingOrderByIdDesc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdUserConsult> findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderBySortIdAsc(String keywords1, 
            Long statusId1,
            String keywords2,
            Long statusId2,
            String keyword3,
            Long statusId3,
            Pageable page);
    
    Page<TdUserConsult> findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderByIdDesc(String keywords1, 
            Long statusId1,
            String keywords2,
            Long statusId2,
            String keyword3,
            Long statusId3,
            Pageable page);
    
    List<TdUserConsult> findByUsernameOrderByIdDesc(String username);
    
    Page<TdUserConsult> findByUsernameOrderByIdDesc(String username, Pageable page);
    
    Page<TdUserConsult> findByUsernameAndGoodsTitleContainingOrderByIdDesc(String username, String keywords, Pageable page);
    
    Page<TdUserConsult> findByGoodsIdAndStatusIdOrderByIdDesc(Long goodsId, Long statusId, Pageable page);
    
}
