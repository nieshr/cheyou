package com.ynyes.cheyou.touch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdSettingService;
import com.ynyes.cheyou.service.TdUserService;
import com.ynyes.cheyou.util.CommonService;

/**
 * 登录
 *
 */
@Controller
public class TdTouchLoginController {
    @Autowired
    private TdUserService tdUserService;
    
    @Autowired
    private TdSettingService tdSettingService;
    
    @Autowired
    private TdCommonService tdCommonService;

    @RequestMapping(value = "/touch/login", method = RequestMethod.GET)
    public String login(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        String referer = req.getHeader("referer");
        
        // 基本信息
        tdCommonService.setHeader(map, req);
        
        if (null == username) 
        {
            return "/touch/login";
        }
        
        if (null == referer)
        {
            referer = "/";
        }
        
        return "redirect:" + referer;
    }
    
    /**
     * 找回密码
     * @author mdj
     * @param req
     * @param map
     * @return
     */
    @RequestMapping("/touch/retrieve")
    public String findPassword(Integer errCode,HttpServletRequest request, ModelMap map,String name)
    {
    	String username = (String) request.getSession().getAttribute("username");
    	
    	/**
    	 * errCode = 0 代表验证码出错   1，用户不存在
    	 */
    	tdCommonService.setHeader(map, request);
    	if (null == username)
    	{
            if (null != errCode)
            {
                if (errCode.equals(0))
                {
                    map.addAttribute("error", "验证码错误");
                    return "/touch/user_retrieve";
                }
                else if(errCode.equals(1))
                {
                	map.addAttribute("error", "用户不存在");
                    return "/touch/user_retrieve";
				}
                
                map.addAttribute("errCode", errCode);
                return "/touch/user_retrieve_step2";
            }
            map.addAttribute("username",name);
            return "/touch/user_retrieve";         
        }
        return "redirect:/touch";
    }
    
    @RequestMapping(value="/touch/retrieve",method = RequestMethod.POST)
    public String findPassword(String username,String code,HttpServletRequest request,ModelMap map)
    {
    	String codeBack = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
    	
    	if(codeBack == null)
    	{
    		return "redirect:/touch/retrieve?name=" + username;
    	}
    	
    	if (!codeBack.equalsIgnoreCase(code))
    	{
			return "redirect:/touch/retrieve?name=" + username +"&errCode=0";
		}
    	
        TdUser tdUser = tdUserService.findByUsername(username);
        
        if(tdUser == null)
        {
        	return "redirect:/touch/retrieve?name" + username +"&errCode=1";
        }
        
        String mobile  = tdUser.getMobile();
        
        map.addAttribute("mobile", mobile);
        
        tdCommonService.setHeader(map, request);
        
        request.getSession().setAttribute("retrieve_username", tdUser.getUsername());
        
        map.put("user", tdUser);
        
        return "/touch/user_retrieve_step2";
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    @RequestMapping(value = "/touch/retrieve_step2", method = RequestMethod.POST)
	public String Step2(String smsCode,HttpServletRequest req, ModelMap map){
		if (null == smsCode) {
			return "redirect:/touch/retrieve?errCode=4";
		}
    	
		String smsCodeSave = (String) req.getSession().getAttribute("SMSCODE");
		if (null == smsCodeSave) {
			String username = (String) req.getSession().getAttribute("retrieve_username");
			TdUser tdUser = tdUserService.findByUsername(username);
			tdCommonService.setHeader(map, req);
	        
	        map.put("user", tdUser);
			return "/touch/user_retrieve_step2";
		}
		if (!smsCodeSave.equalsIgnoreCase(smsCode)) {
			return "redirect:/touch/retrieve?errCode=4";
		}
		String username = (String) req.getSession().getAttribute("retrieve_username");
		map.put("retrieve_username", username);
		tdCommonService.setHeader(map, req);
		
		return "/touch/user_retrieve_step3";
	}
    @RequestMapping(value = "/touch/retrieve_step3", method = RequestMethod.POST)
	public String Step3(String password, HttpServletRequest req, ModelMap map){
		String username = (String) req.getSession().getAttribute("retrieve_username");
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		if (null != password) {
			user.setPassword(password);
			tdUserService.save(user);
			tdCommonService.setHeader(map, req);
			req.getSession().setAttribute("username", user.getUsername());
			return "/touch/user_retrieve_ok";
		}
		return "/touch/error_404";
	}
    
    
    @RequestMapping(value="/touch/login",method = RequestMethod.POST)
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
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (null != user)
        {
        	if (!user.getPassword().equals(password))
            {
                res.put("msg", "密码错误");
                return res;
            }
        	user.setLastLoginTime(new Date());
        	user.setLastLoginIp(CommonService.getIp(request));
            tdUserService.save(user);
             
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("usermobile", user.getMobile());
            res.put("code", 0);
            return res;
        }
        
        user = tdUserService.findByMobileAndIsEnabled(username);
        if (null != user) {
        	if (!user.getPassword().equals(password))
            {
                res.put("msg", "密码错误");
                return res;
            }
        	user.setLastLoginTime(new Date());
        	user.setLastLoginIp(CommonService.getIp(request));
            tdUserService.save(user);
             
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("usermobile", user.getMobile());
            res.put("code", 0);
            return res;
		}else{
			res.put("msg", "不存在该用户");
			return res;
		}
        
    }
    
    /**
     * @author mdj
     * @param request
     * @return 返回手机端主页
     */
    @RequestMapping("/touch/logout")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/touch";
	}
}
