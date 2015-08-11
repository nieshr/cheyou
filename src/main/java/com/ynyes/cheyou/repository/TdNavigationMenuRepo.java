package com.ynyes.cheyou.repository;

import java.util.List;

import org.aspectj.weaver.Lint;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdNavigationMenu;

/**
 * TdNavigationMenu 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdNavigationMenuRepo extends
		PagingAndSortingRepository<TdNavigationMenu, Long>,
		JpaSpecificationExecutor<TdNavigationMenu> 
{
    // 通过父类型查找
    List<TdNavigationMenu> findByParentIdAndIsEnableTrueOrderBySortIdAsc(Long parentId);
    List<TdNavigationMenu> findByIsEnableTrueOrderBySortIdAsc();
}
