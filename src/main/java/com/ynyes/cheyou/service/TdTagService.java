package com.ynyes.cheyou.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ynyes.cheyou.entity.TdTag;
import com.ynyes.cheyou.repository.TdTagRepo;


/**
 * TdTag 服务类
 * 
 * @author libiao
 *
 */
@Service
public class TdTagService{

	@Autowired
	private TdTagRepo repository;

	/**
	 * 删除
	 * @author libiao
	 * @param id
	 */
	 public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
	 
	 public void delete(TdTag e)
     {
        if (null != e)
        {
            repository.delete(e);
        }
     }
    
    public void delete(List<TdTag> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    } 
	 
	
	/**
	 * 查找
	 * 
	 * @return
	 */
	public TdTag findOne(Long id)
	{
		if(null == id){
			
			return null;
		}
		
		return repository.findOne(id);
	}
	
	public Page<TdTag> findAllBySortIdAsc(int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.ASC,"sortId"));
		return repository.findAll(pageRequest);
	}
	public Page<TdTag> findByTypeIdBySortIdAsc(Long typeId,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.ASC,"sortId"));
		return repository.findByTypeId(typeId, pageRequest);
	}
	
	public List<TdTag> findByTypeId(Long id)
	{
		return repository.findByTypeId(id);
	}
	
	
	
	public TdTag save(TdTag e)
	{
		if(null == e.getCreateTime())
		{
			e.setCreateTime(new Date());
		}
		return repository.save(e);
	}
	
}
