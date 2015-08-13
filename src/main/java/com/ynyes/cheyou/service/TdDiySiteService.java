package com.ynyes.cheyou.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.entity.TdUserComment;
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
    TdUserCommentService TdUserCommentService;
    
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
                user = tdUserService.addNewUser(e.getUsername(), e.getPassword(), e.getMobile(), null, null);
                
                user.setRoleId(2L); // 加盟商用户
            }
            // 修改加盟店密码也需要修改用户密码 @author: Sharon
            else
            {
                user.setPassword(e.getPassword());
            }
            
            tdUserService.save(user);
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
    
    /**
	 * @author lc
	 * @注释：同盟店评价信息
	 */
    public int ContdiysiteComment(Long diysiteId) {
		List<TdUserComment> tdUserComment_list = TdUserCommentService.findByDiysiteIdOrderByIdDesc(diysiteId);
		return tdUserComment_list.size();
	}
    
    public float diysiteServiceStars(Long diysiteId){
    	List<TdUserComment> tdUserComment_list = TdUserCommentService.findByDiysiteIdOrderByIdDesc(diysiteId);
    	
    	if (null != tdUserComment_list) {
    		Long[] result = new Long[20];
        	int temp = 0;
        	if (tdUserComment_list.size()==0) {
				return (float) 0.0;
			}
        	for(int i = 0; i < 20; i++){
        		result[i] = tdUserComment_list.get(Math.abs(new Random().nextInt())%tdUserComment_list.size()).getServiceStar();
        		temp = (int) (temp + result[i]);
        	}
        	return temp/20;
		}
    	
    	return (float) 0.0;
    	
    }
}
