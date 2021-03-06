package com.ynyes.cheyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.cheyou.entity.TdManagerRole;
import com.ynyes.cheyou.entity.TdNavigationMenu;
import com.ynyes.cheyou.repository.TdManagerRoleRepo;

/**
 * TdManagerRole 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdManagerRoleService {
    
    @Autowired
    TdManagerRoleRepo repository;
    
    @Autowired
    TdManagerPermissionService tdManagerPermissionService;
    
    @Autowired
    TdNavigationMenuService tdNavigationMenuService;
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
    public void delete(TdManagerRole e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdManagerRole> entities)
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
    public TdManagerRole findOne(Long id)
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
    public List<TdManagerRole> findAll(Iterable<Long> ids)
    {
        return (List<TdManagerRole>) repository.findAll(ids);
    }
    
    public List<TdManagerRole> findAll()
    {
        return (List<TdManagerRole>) repository.findAll();
    }
    
    public Page<TdManagerRole> findAll(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
    
    public List<TdManagerRole> findAllOrderBySortIdAsc()
    {
        return (List<TdManagerRole>) repository.findAll(new Sort(Direction.ASC, "sortId"));
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdManagerRole save(TdManagerRole e)
    {
        // 修改时
        if (null != e.getPermissionList())
        {
        	List<TdNavigationMenu> temp = tdNavigationMenuService.findAll();
        	if (null != temp) {
        	//	e.setTotalPermission(e.getPermissionList().size());
        		e.setTotalPermission(temp.size());
			}     
        }
        else
        {
            e.setTotalPermission(0);
        }
        
        tdManagerPermissionService.save(e.getPermissionList());

        return repository.save(e);
    }
    
    public List<TdManagerRole> save(List<TdManagerRole> entities)
    {
        return (List<TdManagerRole>) repository.save(entities);
    }
}
