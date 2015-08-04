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
        
        if (null == user)
        {
            res.put("msg", "不存在该用户");
            return res;
        }
        
        if (!user.getPassword().equals(password))
        {
            res.put("msg", "密码错误");
            return res;
        }
        
        user.setLastLoginTime(new Date());
        
        tdUserService.save(user);
        
        request.getSession().setAttribute("username", username);
        
        res.put("code", 0);
        
        return res;
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
