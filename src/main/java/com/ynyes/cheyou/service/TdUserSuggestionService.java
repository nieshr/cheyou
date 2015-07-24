package com.ynyes.cheyou.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.cheyou.entity.TdUserComment;
import com.ynyes.cheyou.entity.TdUserSuggestion;
import com.ynyes.cheyou.repository.TdUserSuggestionRepo;

/**
 * TdUserSuggestion 服务类
 * @author Zhangji
 *
 */

@Service
@Transactional
public class TdUserSuggestionService {
	@Autowired
	TdUserSuggestionRepo repository;
	
	/**
	 * 查找
	 * @param id
	 * @author Zhangji
	 */
	public List<TdUserSuggestion> findAll(Iterable<Long> ids){
		return (List<TdUserSuggestion>) repository.findAll(ids);
	}
	
	public Page<TdUserSuggestion> findById(Long id, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIdOrderBySortIdAsc(id, pageRequest);
    }
	
	/**
	 * 添加
	 */
	public void save(TdUserSuggestion tdSuggestion){
		repository.save(tdSuggestion);
	}


}
