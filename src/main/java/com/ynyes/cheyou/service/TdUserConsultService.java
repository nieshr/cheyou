package com.ynyes.cheyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.cheyou.entity.TdUserConsult;
import com.ynyes.cheyou.repository.TdUserConsultRepo;

/**
 * TdUserConsult 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserConsultService {
    
    @Autowired
    TdUserConsultRepo repository;
    
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
    public void delete(TdUserConsult e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdUserConsult> entities)
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
    public TdUserConsult findOne(Long id)
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
    public List<TdUserConsult> findAll(Iterable<Long> ids)
    {
        return (List<TdUserConsult>) repository.findAll(ids);
    }
    /**
	 * @author lc
	 * @注释：
	 */
    public List<TdUserConsult> findAll(){
    	return (List<TdUserConsult>) repository.findAll();
    }
    
    public List<TdUserConsult> findByUsername(String username)
    {
        return repository.findByUsernameOrderByIdDesc(username);
    }
    
    public Page<TdUserConsult> findByUsername(String username, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameOrderByIdDesc(username, pageRequest);
    }
    
    public Page<TdUserConsult> findByGoodsIdAndIsShowable(Long goodsId, int page, int size)
    {
        if (null == goodsId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByGoodsIdAndStatusIdOrderByIdDesc(goodsId, 1L, pageRequest);
    }
    
    public Page<TdUserConsult> findByUsernameAndSearch(String username, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndGoodsTitleContainingOrderByIdDesc(username, keywords, pageRequest);
    }
    
    public Page<TdUserConsult> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdUserConsult> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdUserConsult> findByStatusIdOrderBySortIdAsc(Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderBySortIdAsc(statusId, pageRequest);
    }
    
    public Page<TdUserConsult> findByStatusIdOrderByIdDesc(Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderByIdDesc(statusId, pageRequest);
    }
    
    public Page<TdUserConsult> searchAndFindByStatusIdOrderBySortIdAsc(String keywords, Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderBySortIdAsc(keywords, statusId, keywords, statusId, keywords, statusId, pageRequest);
    }
    
    public Page<TdUserConsult> searchAndFindByStatusIdOrderByIdDesc(String keywords, Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderByIdDesc(keywords, statusId, keywords, statusId, keywords, statusId, pageRequest);
    }
    
    public Page<TdUserConsult> searchAndOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingOrGoodsTitleContainingOrContentContainingOrderBySortIdAsc(keywords, keywords, keywords, pageRequest);
    }
    
    public Page<TdUserConsult> searchAndOrderByIdDesc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingOrGoodsTitleContainingOrContentContainingOrderByIdDesc(keywords, keywords, keywords, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdUserConsult save(TdUserConsult e)
    {
        
        return repository.save(e);
    }
    
    public List<TdUserConsult> save(List<TdUserConsult> entities)
    {
        
        return (List<TdUserConsult>) repository.save(entities);
    }
}
