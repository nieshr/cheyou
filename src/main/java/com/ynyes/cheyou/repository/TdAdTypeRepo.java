package com.ynyes.cheyou.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdAdType;

/**
 * TdAdType 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdAdTypeRepo extends
		PagingAndSortingRepository<TdAdType, Long>,
		JpaSpecificationExecutor<TdAdType> 
{
    TdAdType findByTitle(String title);
}
