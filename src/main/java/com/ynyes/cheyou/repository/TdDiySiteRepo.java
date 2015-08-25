package com.ynyes.cheyou.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdDiySite;

/**
 * TdDiySite 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdDiySiteRepo extends
		PagingAndSortingRepository<TdDiySite, Long>,
		JpaSpecificationExecutor<TdDiySite> 
{
    Page<TdDiySite> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    List<TdDiySite> findByIsEnableTrue();
    
    List<TdDiySite> findByCityAndIsEnableTrueOrderBySortIdAsc(String city);
    
    /**
	 * @author lc
	 * @注释：
	 */
    TdDiySite findByUsernameAndIsEnableTrue(String username);
    List<TdDiySite> findByDisctrictAndIsEnableTrue(String disctrict);
}
