package com.ynyes.cheyou.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.ynyes.cheyou.entity.TdUserSuggestion;
/**
 * TdUserSuggestion  实体数据库操作接口
 * @author Zhangji
 *
 */
public interface TdUserSuggestionRepo extends 
           PagingAndSortingRepository<TdUserSuggestion, Long>,
           JpaSpecificationExecutor<TdUserSuggestion> 
{
// 根据查找
Page<TdUserSuggestion> findByIdOrderBySortIdAsc(Long Id, Pageable page);
List<TdUserSuggestion> findById(Long id);


}
