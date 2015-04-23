package com.ynyes.rongcheng.controller.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.rongcheng.service.TdUserService;
import com.ynyes.rongcheng.util.VerifServlet;

/**
 * 登录及注册
 *
 */
@Controller
public class TdLoginAndRegController {
    @Autowired
    private TdUserService tdUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");

        String referer = req.getHeader("referer");
        
        if (null == username) 
        {
            return "/front/login";
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
    public Map<String, Object> login(String usr, 
                String pwd, 
                String code, 
                Boolean isSave,
                HttpServletRequest request) {
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("code", 1);
        
        if (usr.isEmpty() || pwd.isEmpty())
        {
            res.put("msg", "用户名及密码不能为空");
        }
        
        request.getSession().setAttribute("username", usr);
        
        res.put("code", 0);
        
        return res;
        
//        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(password)) {
//            flag = "false";
//        } else {
//            String msg = (String) request.getSession().getAttribute(
//                    "RANDOMVALIDATECODEKEY");
//            if (StringUtils.isNotEmpty(VERIF)) {
//                if (VERIF.equalsIgnoreCase(msg)) {
//                    Map<String, Object> res = UserService.loginCheck(username, StringUtils.encryption(password),request);
//                    
//                    if (res.get("code").equals(0)) {
//                       
//                        flag = "success";
//                        return flag;
//                    } else {
//                        flag = "false";
//                    }
//                }else{
//                    flag = "vfalse";
//                }
//                
//            }else{
//                flag = "fnull";
//            }
//        }
//        return flag;
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
