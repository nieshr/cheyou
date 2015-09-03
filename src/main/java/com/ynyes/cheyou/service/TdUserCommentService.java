package com.ynyes.cheyou.service;

import java.util.List;

import org.neo4j.cypher.internal.compiler.v2_1.ast.rewriters.literalReplacement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.cheyou.entity.TdUserComment;
import com.ynyes.cheyou.repository.TdUserCommentRepo;

/**
 * TdUserComment 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserCommentService {
    
    @Autowired
    TdUserCommentRepo repository;
    
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
    public void delete(TdUserComment e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdUserComment> entities)
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
    public TdUserComment findOne(Long id)
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
    public List<TdUserComment> findAll(Iterable<Long> ids)
    {
        return (List<TdUserComment>) repository.findAll(ids);
    }
    
    public List<TdUserComment> findAll(){
    	return (List<TdUserComment>) repository.findAll();
    }
    
    public List<TdUserComment> findByUsername(String username)
    {
        return repository.findByUsernameOrderByIdDesc(username);
    }
    
    public Page<TdUserComment> findByUsername(String username, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameOrderByIdDesc(username, pageRequest);
    }
    
    public Page<TdUserComment> findByGoodsIdAndIsShowable(Long goodsId, int page, int size)
    {
        if (null == goodsId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByGoodsIdAndStatusIdOrderByIdDesc(goodsId, 1L, pageRequest);
    }
    /**
	 * @author lc
	 * @注释：
	 */
    public Page<TdUserComment> findByDiysiteIdAndIsShowable(Long diysiteId, int page, int size)
    {
        if (null == diysiteId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByDiysiteIdAndStatusIdOrderByIdDesc(diysiteId, 1L, pageRequest);
    }
    
    public Long countByGoodsIdAndIsShowable(Long goodsId)
    {
        if (null == goodsId)
        {
            return null;
        }
        
        return repository.countByGoodsIdAndStatusId(goodsId, 1L);
    }
    
    /**
	 * @author lc
	 * @注释：根据同盟店id
	 */
    public Long countByDiysiteIdAndIsShowable(Long diysiteId)
    {
        if (null == diysiteId)
        {
            return null;
        }
        
        return repository.countByDiysiteIdAndStatusId(diysiteId, 1L);
    }
    
    public Page<TdUserComment> findByGoodsIdAndStarsAndIsShowable(Long goodsId, Long stars, int page, int size)
    {
        if (null == goodsId || null == stars)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByGoodsIdAndStatusIdAndStarsOrderByIdDesc(goodsId, 1L, stars, pageRequest);
    }
    /**
	 * @author lc
	 * @注释：根据同盟店id
	 */
    public Page<TdUserComment> findByDiysiteIdAndStarsAndIsShowable(Long diysiteId, Long stars, int page, int size)
    {
        if (null == diysiteId || null == stars)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByDiysiteIdAndStatusIdAndStarsOrderByIdDesc(diysiteId, 1L, stars, pageRequest);
    }
    
    public Long countByGoodsIdAndStarsAndIsShowable(Long goodsId, Long stars)
    {
        if (null == goodsId || null == stars)
        {
            return null;
        }
        
        return repository.countByGoodsIdAndStatusIdAndStars(goodsId, 1L, stars);
    }
    /**
	 * @author lc
	 * @注释：根据同盟店id
	 */
    public Long countByDiysiteIdAndStarsAndIsShowable(Long diysiteId, Long stars)
    {
        if (null == diysiteId || null == stars)
        {
            return null;
        }
        
        return repository.countByDiysiteIdAndStatusIdAndStars(diysiteId, 1L, stars);
    }
    
    public Page<TdUserComment> findByUsernameAndSearch(String username, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndGoodsTitleContainingOrderByIdDesc(username, keywords, pageRequest);
    }
    
    public Page<TdUserComment> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdUserComment> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdUserComment> findByStatusIdOrderBySortIdAsc(Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderBySortIdAsc(statusId, pageRequest);
    }
    
    public Page<TdUserComment> findByStatusIdOrderByIdDesc(Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderByIdDesc(statusId, pageRequest);
    }
    
    public Page<TdUserComment> searchAndFindByStatusIdOrderBySortIdAsc(String keywords, Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderBySortIdAsc(keywords, statusId, keywords, statusId, keywords, statusId, pageRequest);
    }
    
    public Page<TdUserComment> searchAndFindByStatusIdOrderByIdDesc(String keywords, Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderByIdDesc(keywords, statusId, keywords, statusId, keywords, statusId, pageRequest);
    }
    
    public Page<TdUserComment> searchAndOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingOrGoodsTitleContainingOrContentContainingOrderBySortIdAsc(keywords, keywords, keywords, pageRequest);
    }
    
    public Page<TdUserComment> searchAndOrderByIdDesc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingOrGoodsTitleContainingOrContentContainingOrderByIdDesc(keywords, keywords, keywords, pageRequest);
    }
    
    /**
	 * @author lc
	 * @注释：查询同盟店评价
	 */
    public List<TdUserComment> findByDiysiteIdOrderByIdDesc(long diysiteId){
    	return repository.findByDiysiteIdOrderByIdDesc(diysiteId);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdUserComment save(TdUserComment e)
    {
        
        return repository.save(e);
    }
    
    public List<TdUserComment> save(List<TdUserComment> entities)
    {
        
        return (List<TdUserComment>) repository.save(entities);
    }
}
