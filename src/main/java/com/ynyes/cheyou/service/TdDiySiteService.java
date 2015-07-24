package com.ynyes.cheyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.repository.TdDiySiteRepo;

/**
 * TdDiySite 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdDiySiteService {
    @Autowired
    TdDiySiteRepo repository;
    
    @Autowired
    TdUserService tdUserService;
    
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
    public void delete(TdDiySite e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdDiySite> entities)
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
    public TdDiySite findOne(Long id)
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
    public List<TdDiySite> findAll(Iterable<Long> ids)
    {
        return (List<TdDiySite>) repository.findAll(ids);
    }
    
    public List<TdDiySite> findByIsEnableTrue()
    {
        return repository.findByIsEnableTrue();
    }
    
    public List<TdDiySite> findByCityAndIsEnableTrueOrderBySortIdAsc(String city)
    {
        if (null == city)
        {
            return null;
        }
        
        return repository.findByCityAndIsEnableTrueOrderBySortIdAsc(city);
    }
    
    public Page<TdDiySite> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdDiySite> searchAllOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdDiySite save(TdDiySite e)
    {
        if (null != e.getUsername())    
        {
            TdUser user = tdUserService.findByUsername(e.getUsername());
            
            if (null == user )
            {
                user = tdUserService.addNewUser(null, e.getUsername(), e.getPassword(), e.getMobile(), null, null);
                
                user.setRoleId(2L); // 加盟商用户
               
                tdUserService.save(user);
            }
        }
        return repository.save(e);
    }
    
    public List<TdDiySite> save(List<TdDiySite> entities)
    {
        
        return (List<TdDiySite>) repository.save(entities);
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    public TdDiySite findbyUsername(String username){
		
    	return (repository.findByUsernameAndIsEnableTrue(username)); 	
    }
}
