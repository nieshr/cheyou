package com.ynyes.cheyou.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdManager;

/**
 * TdManager 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdManagerRepo extends
		PagingAndSortingRepository<TdManager, Long>,
		JpaSpecificationExecutor<TdManager> 
{
    TdManager findByUsernameAndIsEnableTrue(String username);
}
