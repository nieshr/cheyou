package com.ynyes.cheyou.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.cheyou.entity.TdUser;

/**
 * TdUser 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserRepo extends
		PagingAndSortingRepository<TdUser, Long>,
		JpaSpecificationExecutor<TdUser> 
{
    // 根据角色查找
    Page<TdUser> findByRoleIdOrderByIdDesc(Long roleId, Pageable page);
    
    Page<TdUser> findByUsernameContainingOrMobileContainingOrEmailContainingOrderByIdDesc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdUser> findByUsernameContainingAndRoleIdOrMobileContainingAndRoleIdOrEmailContainingAndRoleIdOrderByIdDesc(String keywords1, 
                                                                Long roleId1,
                                                                String keywords2,
                                                                Long roleId2,
                                                                String keyword3,
                                                                Long roleId3,
                                                                Pageable page);
    
    TdUser findByUsernameAndStatusIdOrUsernameAndStatusId(String username, Long statusId, String username1, Long statusId1);
    
    TdUser findByUsername(String username);
    
    TdUser findByUsernameAndIdNot(String username, Long id);
    
    Page<TdUser> findByUpperUsernameAndStatusIdOrderByIdDesc(String upperUsername, Long statusId, Pageable page);
    
    TdUser findByMobileAndStatusIdOrMobileAndStatusId(String mobile,Long statusId,String mobile1,Long statusId1);		//手机号已验证查找
    
    TdUser findByMobile(String mobile);		//手机号查找
    

    TdUser findByQqUserId(String qqUserId);

    TdUser findByAlipayUserId(String alipay_userid);	//支付宝用户名查找

}
