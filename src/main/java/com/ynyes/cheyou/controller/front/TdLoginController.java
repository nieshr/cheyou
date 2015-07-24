package com.ynyes.cheyou.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdUserService;
import com.ynyes.cheyou.util.VerifServlet;

/**
 * 登录
 *
 */
@Controller
public class TdLoginController {
    @Autowired
    private TdUserService tdUserService;
    
    @Autowired
    private TdCommonService tdCommonService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        String referer = req.getHeader("referer");
        
        // 基本信息
        tdCommonService.setHeader(map, req);
        
        if (null == username) 
        {
            return "/client/login";
        }
        
        if (null == referer)
        {
            referer = "/";
        }
        /**
		 * @author lc
		 * @注释：
		 */
        TdUser tdUser = tdUserService.findByUsername(username);
        if(tdUser.getRoleId()==2L){
        	return "redirect:/user/diysite/order/list/0";
        }
        return "redirect:" + referer;
    }
    
    /**
     * 
     * 密码找回<BR>
     * 方法名：forget<BR>
     * 创建人：guozhengyang <BR>
     * 时间：2015年2月2日-下午4:37:35 <BR>
     * @return String<BR>
     * @param  [参数1]   [参数1说明]
     * @param  [参数2]   [参数2说明]
     * @exception <BR>
     * @since  1.0.0
     */
//    @RequestMapping("/forget")
//    public String forget(){
//        return "/front/forget";
//    }
    
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(String username, 
                String password, 
                String code, 
                Boolean isSave,
                HttpServletRequest request) {
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("code", 1);
        
        if (username.isEmpty() || password.isEmpty())
        {
            res.put("msg", "用户名及密码不能为空");
        }
        /**
         * 按账号查找登录验证
         * 密码验证
         * 修改最后登录时间
         * @author libiao
         */
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (null != user)
        {
        	if (!user.getPassword().equals(password))
            {
                res.put("msg", "密码错误");
                return res;
            }
        	user.setLastLoginTime(new Date());
        	user = tdUserService.save(user);
        	request.getSession().setAttribute("username", user.getUsername());
            
            res.put("code", 0);
            
            /**
    		 * @author lichong
    		 * @注释：判断用户类型
    		 */
            if(user.getRoleId()==2L){
            	res.put("role", 2);
            }
            
            return res;
        }
        /**
         * 如果账号验证未通过，再进行手机登录验证
         * 密码验证
         * 修改最后登录时间
         * @author libiao
         */
        user = tdUserService.findByMobileAndIsEnabled(username);
        if(null != user){
        	if (!user.getPassword().equals(password))
            {
                res.put("msg", "密码错误");
                return res;
            }
        	user.setLastLoginTime(new Date());
        	user = tdUserService.save(user);
        	request.getSession().setAttribute("username", user.getUsername());
            
            res.put("code", 0);
            
            /**
    		 * @author lichong
    		 * @注释：判断用户类型
    		 */
            if(user.getRoleId()==2L){
            	res.put("role", 2);
            }
            
            return res;
        }else
        {	//账号-手机都未通过验证，则用户不存在
        	res.put("msg", "不存在该用户");
        	return res;
        }
    }

    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    public void verify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifServlet randomValidateCode = new VerifServlet();
        randomValidateCode.getRandcode(request, response);
    }
}
