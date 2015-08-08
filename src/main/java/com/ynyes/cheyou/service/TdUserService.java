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

import com.ynyes.cheyou.entity.TdSetting;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.entity.TdUserLevel;
import com.ynyes.cheyou.entity.TdUserPoint;
import com.ynyes.cheyou.repository.TdUserRepo;

/**
 * TdUser 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserService {
    @Autowired
    TdUserRepo repository;
    
    @Autowired
    TdUserPointService tdUserPointService;
    
    @Autowired
    TdUserCollectService tdUserCollectService;
    
    @Autowired
    TdUserRecentVisitService tdUserRecentVisitService;
    
    @Autowired
    TdUserCashRewardService tdUserCashRewardService;
    
    @Autowired
    TdUserConsultService tdUserConsultService;
    
    @Autowired
    TdUserCommentService tdUserCommentService;
    
    @Autowired
    TdUserReturnService tdUserReturnService;
    
    @Autowired
    TdUserLevelService tdUserLevelService;
    
    @Autowired
    TdSettingService tdSettingService;
    
    /**
     * 删除用户数据
     * @param username
     */
    private void userDataDelete(String username)
    {
        if (null == username)
        {
            return;
        }
        
        tdUserPointService.delete(tdUserPointService.findByUsername(username));
        
        tdUserCollectService.delete(tdUserCollectService.findByUsername(username));
        
        tdUserRecentVisitService.delete(tdUserRecentVisitService.findByUsername(username));
        
        tdUserCashRewardService.delete(tdUserCashRewardService.findByUsername(username));
        
        tdUserConsultService.delete(tdUserConsultService.findByUsername(username));
        
        tdUserCommentService.delete(tdUserCommentService.findByUsername(username));
        
        tdUserReturnService.delete(tdUserReturnService.findByUsername(username));
    }
    
    /**
     * 清除计数器
     * 
     */
    public void resetCount()
    {
        List<TdUser> userList = (List<TdUser>) repository.findAll();
        
        if (null == userList)
        {
            return;
        }
        
        for (TdUser user : userList)
        {
            user.setPointGetByShareGoods(0L);
        }
        
        repository.save(userList);
    }
    
    public TdUser addNewUser(String username, String password, String mobile, String email, String carCode)
    {
        if (null == username || null == password || username.isEmpty() || password.isEmpty())
        {
            return null;
        }
        
        if (null != repository.findByUsername(username))
        {
            return null;
        }
        
        TdUser user = new TdUser();
        
        user.setUsername(username);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setCarCode(carCode);
        user.setRegisterTime(new Date());
        user.setLastLoginTime(new Date());
        user.setStatusId(0L); // 待审核
        user.setRoleId(0L); // 普通用户
        
        user.setTotalCashRewards(0L);
        user.setTotalCollectedGoods(0L);
        user.setTotalComments(0L);
        user.setTotalConsults(0L);
        user.setTotalLowerUsers(0L);
        user.setTotalRecentVisited(0L);
        user.setTotalReturns(0L);
        user.setTotalSpendCash(0.0);
        
        TdSetting setting = tdSettingService.findTopBy();
        
        TdUserPoint userPoint = new TdUserPoint();
        
        if (null != setting)
        {
            userPoint.setPoint(setting.getRegisterSuccessPoints());
        }
        else
        {
            userPoint.setPoint(0L);
        }
        
        userPoint.setTotalPoint(userPoint.getPoint());
        userPoint.setUsername(username);
        userPoint.setDetail("用户注册成功奖励");
        
        userPoint = tdUserPointService.save(userPoint);
        
        user.setTotalPoints(userPoint.getTotalPoint()); // 积分
        
        TdUserLevel level = tdUserLevelService.findByLevelIdAndIsEnableTrue(1L);
        
        if (null != level)
        {
            user.setUserLevelId(level.getId());
            user.setUserLevelTitle(level.getTitle());
        }
        
        return user;
    }
    
    /**
     * 增加用户累计消费额
     * 
     * @param username
     * @param spend
     * @return
     */
    public TdUser addTotalSpend(String username, Double spend)
    {
        if (null == username || username.isEmpty() || null == spend)
        {
            return null;
        }
        
        TdUser user = repository.findByUsername(username);
        
        if (null == user)
        {
            return null;
        }
        
        Double total = user.getTotalSpendCash();
        
        if (null == total)
        {
            total = 0.0;
        }
        
        user.setTotalSpendCash(total + spend);
        
        Long levelId = tdUserLevelService.getLevelId(user.getTotalSpendCash());
        
        if (null != levelId)
        {
            TdUserLevel level = tdUserLevelService.findByLevelId(levelId);
            if (null != level)
            {
                user.setUserLevelId(level.getLevelId());
                user.setUserLevelTitle(level.getTitle());
            }
        }
        
        return repository.save(user);
    }
    
    /**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            TdUser user = repository.findOne(id);
            
            if (null != user)
            {
                userDataDelete(user.getUsername());
            }
            
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdUser e)
    {
        if (null != e)
        {
            userDataDelete(e.getUsername());
            repository.delete(e);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdUser findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    public TdUser findByUsernameAndIsEnabled(String username)
    {
        if (null == username)
        {
            return null;
        }
        
        return repository.findByUsernameAndStatusIdOrUsernameAndStatusId(username, 0L, username, 1L);
    }
    
    public TdUser findByUsername(String username)
    {
        if (null == username)
        {
            return null;
        }
        
        return repository.findByUsername(username);
    }
    
    public TdUser findByUsernameAndIdNot(String username, Long id)
    {
        if (null == username || null == id)
        {
            return null;
        }
        
        return repository.findByUsernameAndIdNot(username, id);
    }
    
    public Page<TdUser> findByUpperDiySiteIdAndIsEnabled(Long upperDiySiteId, int page, int size)
    {
        if (null == upperDiySiteId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUpperDiySiteIdAndStatusIdOrderByIdDesc(upperDiySiteId, 1L, pageRequest);
    }
   /**
    *  手机号查找
    * @author libiao
    * @param mobile
    * @return
    */
    public TdUser findByMobileAndIsEnabled(String mobile){
    	if(null==mobile){
    		return null;
    	}
    	return repository.findByMobileAndStatusIdOrMobileAndStatusId(mobile, 0L, mobile, 1L);
    }
    /**
     * 
     * 注册查找----按手机号
     * @author libiao
     * @param mobile
     * @return
     */
    public TdUser findByMobile(String mobile){
    	if(null ==mobile){
    		return null;
    	}
    	return repository.findByMobile(mobile);
    }
    
    /**
     * 按QQ——openID查找
     * @author libiao
     * @param qq
     * @return
     */
    public TdUser findByQqUserId(String qqUserId){
    	if(null == qqUserId){
    		return null;
    	}
    	return repository.findByQqUserId(qqUserId);
    }
    
    /**
	 * @author lc
	 * @注释：按支付宝id查找
	 */
    public TdUser findByalipayname(String alipayname){
    	if(null ==alipayname){
    		return null;
    	}
    	return repository.findByAlipayUserId(alipayname);

    }
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdUser> findAll(Iterable<Long> ids)
    {
        return (List<TdUser>) repository.findAll(ids);
    }
    
    public Page<TdUser> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdUser> findByRoleIdOrderByIdDesc(Long roleId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByRoleIdOrderByIdDesc(roleId, pageRequest);
    }
    
    public Page<TdUser> searchAndFindByRoleIdOrderByIdDesc(String keywords, Long roleId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingAndRoleIdOrMobileContainingAndRoleIdOrEmailContainingAndRoleIdOrderByIdDesc(keywords, roleId, keywords, roleId, keywords, roleId, pageRequest);
    }
    
    public Page<TdUser> searchAndOrderByIdDesc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingOrMobileContainingOrEmailContainingOrderByIdDesc(keywords, keywords, keywords, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdUser save(TdUser e)
    {
        
        if (null != e.getUserLevelId())
        {
            TdUserLevel level = tdUserLevelService.findByLevelIdAndIsEnableTrue(e.getUserLevelId());
            e.setUserLevelTitle(level.getTitle());
        }
        
        return repository.save(e);
    }
    
    public List<TdUser> save(List<TdUser> entities)
    {
        
        return (List<TdUser>) repository.save(entities);
    }
}
