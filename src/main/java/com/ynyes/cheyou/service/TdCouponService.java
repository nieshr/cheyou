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

import com.ynyes.cheyou.entity.TdCoupon;
import com.ynyes.cheyou.entity.TdCouponType;
import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.repository.TdCouponRepo;

import scala.xml.dtd.PublicID;

/**
 * TdCoupon 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdCouponService {
    @Autowired
    TdCouponRepo repository;
    
    @Autowired
    TdCouponTypeService tdCouponTypeService;
    
    @Autowired
    TdDiySiteService tdDiySiteService;
    
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
    public void delete(TdCoupon e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdCoupon> entities)
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
    public TdCoupon findOne(Long id)
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
    public List<TdCoupon> findAll(Iterable<Long> ids)
    {
        return (List<TdCoupon>) repository.findAll(ids);
    }
    
    public List<TdCoupon> findByUsernameAndIsUseable(String username)
    {
        if (null == username)
        {
            return null;
        }
        return repository.findByUsernameAndExpireTimeAfterAndIsDistributtedTrueAndIsUsedFalse(username, new Date());
    }
    
    public List<TdCoupon> findByMobileAndIsUseable(String mobile){
    	if (null == mobile)
        {
            return null;
        }
    	return repository.findByMobileAndExpireTimeAfterAndIsDistributtedTrueAndIsUsedFalse(mobile, new Date());
    }
    
    public List<TdCoupon> findByUsername(String username)
    {
        if (null == username)
        {
            return null;
        }
        return repository.findByUsernameAndIsDistributtedTrue(username);
    }
    
    public Page<TdCoupon> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public List<TdCoupon> findAllOrderBySortIdAsc()
    {
        return (List<TdCoupon>) repository.findAll();
    }
    
    public List<TdCoupon> findDistinctTypeIdByIsDistributtedFalse()
    {
        return repository.findTypeIdDistinctByIsDistributtedFalse();
    }
    
    public List<TdCoupon> findByTypeIdAndIsDistributtedFalse(Long typeId)
    {
        return repository.findByTypeIdAndIsDistributtedFalse(typeId);
    }
    
    public List<TdCoupon> findByTypeIdAndIsDistributtedTrueOrderByIdDesc(Long typeId)
    {
        return repository.findByTypeIdAndIsDistributtedTrueOrderByIdDesc(typeId);
    }
    
    public TdCoupon findTopByTypeIdAndDiySiteIdAndIsDistributtedFalse(Long typeId, Long diySiteId)
    {
        if (null == typeId || null == diySiteId)
        {
            return null;
        }
        
        return repository.findTopByTypeIdAndDiySiteIdAndIsDistributtedFalse(typeId, diySiteId);
    }
    
    public Page<TdCoupon> findByIsDistributtedFalseOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedFalseOrderBySortIdAsc(pageRequest);
    }
    
    public Page<TdCoupon> findByIsDistributtedTrueOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueOrderBySortIdAsc(pageRequest);
    }
    
    public TdCoupon findByTypeIdAndMobileAndIsDistributtedTrue(Long typeId, String mobile)
    {
        if (null == typeId || null == mobile)
        {
            return null;
        }
        
        return repository.findTopByTypeIdAndMobileAndIsDistributtedTrue(typeId, mobile);
    }
    /**
	 * @author lc
	 * @注释：根据类型查找优惠券
	 */
    public TdCoupon findByTypeId(Long typeId){
    	 if (null == typeId )
         {
             return null;
         }
    	 return repository.findByTypeId(typeId);
    }
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdCoupon save(TdCoupon e)
    {
        if (null == e.getIsDistributted())
        {
            e.setIsDistributted(false);
        }
        
        if (null == e.getGetNumber())
        {
            e.setGetNumber(0L);
        }
        
        if (null != e.getTypeId())
        {
            TdCouponType cType = tdCouponTypeService.findOne(e.getTypeId());
            
            if (null != cType)
            {
                e.setTypeTitle(cType.getTitle());
                e.setTypePicUri(cType.getPicUri());
                e.setTypeDescription(cType.getDescription());
                e.setTypeCategoryId(cType.getCategoryId());
            }
        }
        
        if (null != e.getDiySiteId())
        {
            TdDiySite diySite = tdDiySiteService.findOne(e.getDiySiteId());
            
            if (null != diySite)
            {
                e.setDiySiteTitle(diySite.getTitle());
            }
        }
        return repository.save(e);
    }
    
    public List<TdCoupon> save(List<TdCoupon> entities)
    {
        
        return (List<TdCoupon>) repository.save(entities);
    }
}
