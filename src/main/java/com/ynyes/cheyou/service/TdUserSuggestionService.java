package com.ynyes.cheyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	 public List<TdUserSuggestion> findAll(){
	        return (List<TdUserSuggestion>) repository.findAll();
	    }

	public TdUserSuggestion findOne(Long id)
    {
		if(null == id)
		{
			return null;
		}
		
        return repository.findOne(id);
    }
	
	public Page<TdUserSuggestion> findAllOrderByTimeDesc(int page,int size){

		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.DESC,"time"));
		return repository.findAll(pageRequest);
	}

	/**
	 * 添加
	 */
	public void save(TdUserSuggestion tdSuggestion){
		repository.save(tdSuggestion);
	}
	
	/**
	 * 删除
	 */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }


}
