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

import com.ynyes.cheyou.entity.TdUserPoint;
import com.ynyes.cheyou.repository.TdUserPointRepo;

/**
 * TdUserPoint 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserPointService {
    
    @Autowired
    TdUserPointRepo repository;
    
    /**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdUserPoint e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdUserPoint> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdUserPoint findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdUserPoint> findAll(Iterable<Long> ids)
    {
        return (List<TdUserPoint>) repository.findAll(ids);
    }
    
    public List<TdUserPoint> findByUsername(String username)
    {
        return repository.findByUsername(username);
    }
    
    public Page<TdUserPoint> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdUserPoint> findByUsername(String username, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameOrderByIdDesc(username, pageRequest);
    }
    
    public Page<TdUserPoint> findByUsernameAndisBackgroundShowfalse(String username, int page, int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
    	return repository.findByUsernameAndIsBackgroundShowFalseOrUsernameAndIsBackgroundShowNullOrderByIdDesc(username, username, pageRequest);
    }
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdUserPoint save(TdUserPoint e)
    {
        
        if (null == e.getPointTime())
        {
            e.setPointTime(new Date());
        }
        
        return repository.save(e);
    }
    
    public List<TdUserPoint> save(List<TdUserPoint> entities)
    {
        
        return (List<TdUserPoint>) repository.save(entities);
    }
}
