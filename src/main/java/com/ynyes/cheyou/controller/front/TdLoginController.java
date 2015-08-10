package com.ynyes.cheyou.controller.front;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
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

		if (null == username) {
			return "/client/login";
		}

		if (null == referer) {
			referer = "/";
		}
		/**
		 * @author lc 
		 * @注释：同盟店登录
		 */
		String diysiteUsername = (String) req.getSession().getAttribute("diysiteUsername");
		
		TdUser tdUser = tdUserService.findByUsername(diysiteUsername);
		
		if (null != tdUser.getRoleId() && tdUser.getRoleId().equals(2L)) {
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
	 * 
	 * @return String<BR>
	 * @param [参数1]
	 *            [参数1说明]
	 * @param [参数2]
	 *            [参数2说明]
	 * @exception <BR>
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password, String alipayuser_id,String type, String code,
			Boolean isSave, HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();

		res.put("code", 1);

		if (username.isEmpty() || password.isEmpty()) {
			res.put("msg", "用户名及密码不能为空");
		}
		/**
		 * 按账号查找登录验证 密码验证 修改最后登录时间
		 * 
		 * @author libiao
		 */
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		if (null != user) {
			if (!user.getPassword().equals(password)) {
				res.put("msg", "密码错误");
				return res;
			}
			user.setLastLoginTime(new Date());
			/**
			 * @author libiao
			 * 判断是首页直接登录还是绑定第三方账号
			 */
			if(null !=type){
				/**
				 * @author libiao
				 * 判断绑定类型为绑定QQ
				 */
				if("qq".equals(type)){
					user.setQqUserId(alipayuser_id);
				}
				/**
				 * @author lc
				 * @注释：添加支付宝第三方登陆用户名
				 */
				if("zfb".equals(type)){
					user.setAlipayUserId(alipayuser_id);
				}
			}
			user = tdUserService.save(user);
			

			res.put("code", 0);

			/**
			 * @author lichong
			 * @注释：判断用户类型
			 */
			if(null != user.getRoleId() && user.getRoleId().equals(2L)){
				res.put("role", 2);
				request.getSession().setAttribute("diysiteUsername", user.getUsername());
				return res;
			}
			request.getSession().setAttribute("username", user.getUsername());
			return res;
		}
		/**
		 * 如果账号验证未通过，再进行手机登录验证 密码验证 修改最后登录时间
		 * 
		 * @author libiao
		 */
		user = tdUserService.findByMobileAndIsEnabled(username);
		if (null != user) {
			if (!user.getPassword().equals(password)) {
				res.put("msg", "密码错误");
				return res;
			}
			user.setLastLoginTime(new Date());
			/**
			 * @author libiao
			 * 判断是首页直接登录还是绑定第三方账号
			 */
			if(null !=type){
				/**
				 * @author libiao
				 * 判断绑定类型为绑定QQ
				 */
				if("qq".equals(type)){
					user.setQqUserId(alipayuser_id);
				}
				/**
				 * @author lc
				 * @注释：添加支付宝第三方登陆用户名
				 */
				if("zfb".equals(type)){
					user.setAlipayUserId(alipayuser_id);
				}
			}
			user = tdUserService.save(user);
			

			res.put("code", 0);

			/**
			 * @author lichong
			 * @注释：判断用户类型
			 */
			if (user.getRoleId() == 2L) {
				res.put("role", 2);
				request.getSession().setAttribute("diysiteUsername", user.getUsername());
				return res;
			}
			request.getSession().setAttribute("username", user.getUsername());
			return res;
		} else { // 账号-手机都未通过验证，则用户不存在
			res.put("msg", "不存在该用户");
			return res;
		}
	}

	/**
	 * @author lc
	 * @return 
	 * @注释：密码找回
	 */
	@RequestMapping(value = "/login/password_retrieve", method = RequestMethod.GET)
	public String Retrieve(HttpServletRequest req, ModelMap map){
		tdCommonService.setHeader(map, req);
		return "/client/user_retrieve_step1";
	}
	
	@RequestMapping(value = "/login/password_retrieve", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> Check(String username, String code, HttpServletRequest request){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);
		
		if (username.isEmpty()) {
			res.put("msg", "用户名不能为空");
		}
		
		String codeBack = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
		if (!codeBack.equalsIgnoreCase(code)){
			res.put("msg", "验证码错误");
			return res;
		 }
		 
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		if (null != user) {
			
			request.getSession().setAttribute("retrieve_username", user.getUsername());
            
			res.put("code", 0);
		}
		else {
			res.put("msg", "用户不存在");
		}
		
		return res;
	}
	
	@RequestMapping(value = "/login/retrieve_step2", method = RequestMethod.GET)
	public String Step2(Integer errCode, HttpServletRequest req, ModelMap map){
		tdCommonService.setHeader(map, req);
		String username = (String) req.getSession().getAttribute("retrieve_username");
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		
		 if (null != errCode)
         {
             if (errCode.equals(1))
             {
                 map.addAttribute("error", "验证码错误");
             }
             
             map.addAttribute("errCode", errCode);
         }
		 
		map.put("user", user);
		
		return "/client/user_retrieve_step2";
	}
	@RequestMapping(value = "/login/retrieve_step2", method = RequestMethod.POST)
	public String Step2(String smsCode,HttpServletRequest req, ModelMap map){
		if (null == smsCode) {
			return "redirect:/login/retrieve_step2?errCode=4";
		}
		String smsCodeSave = (String) req.getSession().getAttribute("SMSCODE");
		if (!smsCodeSave.equalsIgnoreCase(smsCode)) {
			return "redirect:/login/retrieve_step2?errCode=4";
		}
		String username = (String) req.getSession().getAttribute("retrieve_username");
		map.put("retrieve_username", username);
		tdCommonService.setHeader(map, req);
		
		return "/client/user_retrieve_step3";
	}
	
	@RequestMapping(value = "/login/retrieve_step3", method = RequestMethod.POST)
	public String Step3(String password, HttpServletRequest req, ModelMap map){
		String username = (String) req.getSession().getAttribute("retrieve_username");
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		if (null != password) {
			user.setPassword(password);
			tdUserService.save(user);
			tdCommonService.setHeader(map, req);
			req.getSession().setAttribute("username", user.getUsername());
			return "/client/user_retrieve_ok";
		}
		return "/client/error_404";
	}
	/**
	 * @author lc
	 * @注释：支付宝登陆信息提交
	 */
	@RequestMapping(value = "/login/alipay_login", method = RequestMethod.GET)
	public String alipay_login(HttpServletRequest req, ModelMap map) {
		// 目标服务地址
		String target_service = "user.auth.quick.login";
		// 必填

		// 防钓鱼时间戳
		String anti_phishing_key = "";
		// 若要使用请调用类文件submit中的query_timestamp函数

		// 客户端的IP地址
		String exter_invoke_ip = "";
		// 非局域网的外网IP地址，如：221.0.0.1
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.auth.authorize");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("target_service", target_service);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");

		map.put("code", sHtmlText);

		return "/client/alipay_login";
	}

	/**
	 * @author lc
	 * @注释：支付宝登陆返回参数
	 */
	@RequestMapping(value= "/login/alipay_return_url"  , method = RequestMethod.GET)
	public String returnurl(HttpServletRequest request, ModelMap map){
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]>  requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {

			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params.put(name, valueStr);
		}

		tdCommonService.setHeader(map, request);

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 支付宝用户号
		String user_id = " ";
//		String token = " ";
		try {
			user_id = new String(request.getParameter("user_id").getBytes("ISO-8859-1"),"UTF-8");
			//授权令牌
//			token = new String(request.getParameter("token").getBytes("ISO-8859-1"),"UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		// 计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
//		// 假设验证成功
//		verify_result = true;

		if (verify_result) {// 验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码
			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			// 判断是否在商户网站中已经做过了这次通知返回的处理
			// 如果没有做过处理，那么执行商户的业务程序
			// 如果有做过处理，那么不执行商户的业务程序

			// 该页面可做页面美工编辑
			// System.out.println("验证成功");
			map.put("alipay_user_id", user_id);
			TdUser user = tdUserService.findByalipayname(user_id);
			if (null != user) {
				user.setLastLoginTime(new Date());
				user = tdUserService.save(user);
				request.getSession().setAttribute("username", user.getUsername());
				return "redirect:/";
			} else {
				return "/client/accredit_login";
			}
			// request.getSession().setAttribute("alipay_user_id", user_id);
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		} else {
			// 该页面可做页面美工编辑
			// System.out.println("验证失败");

			return "/client/accredit_login";
			// 调试 假设验证成功
		}
	}
	
    /**
	 * @author lc
	 * @注释：支付宝绑定登陆
	 */
	@RequestMapping(value = "/login/alipay_accredit/{type}", method = RequestMethod.GET)
	public String alipaylogin(@PathVariable String type, String useralipay_username, HttpServletRequest request, ModelMap map) {

		TdUser user = tdUserService.findByalipayname(useralipay_username);

		String alipayusername = randomUsername();

		if (null != user) {
			user.setLastLoginTime(new Date());
			user = tdUserService.save(user);
			request.getSession().setAttribute("username", user.getUsername());

			return "redirect:/";
		} else {
			user = tdUserService.addNewUser(alipayusername, "123456", null, null, null);
			/**
			 * @author libiao
			 * 判断新建账号为QQ还是支付宝
			 */
			if("qq".equals(type)){
				//QQ登录新建账号
				user.setQqUserId(useralipay_username);
			}else{
				//支付宝登录新建账号
				user.setAlipayUserId(useralipay_username);
			}
			tdUserService.save(user);
			request.getSession().setAttribute("username", alipayusername);

			return "redirect:/";
		}
	}

	/**
	 * @author lc
	 * @注释：随机生成支付宝绑定用户名
	 */
	public String randomUsername() {
		Random random = new Random();
		String username = " ";
		while (true) {
			int temp1 = random.nextInt(10000000);
			username = "user_" + temp1;
			if (null == tdUserService.findByUsername(username)) {
				return username;
			}
		}
	}

	/**
	 * 跳转进入互联开放平台进行QQ登录验证
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author libiao
	 */
	@RequestMapping(value = "/qq/login", method = RequestMethod.GET)
	public void infoQQLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
	}

	/**
	 * QQ登录返回结果处理
	 * @author libiao
	 * @param code
	 * @param state
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/login/qq_login_return", method = RequestMethod.GET)
	public String qqLoginReturn(String code, String state, HttpServletRequest request, ModelMap map) {

		tdCommonService.setHeader(map, request);
		try {
			System.err.println("code-------"+code);
			System.err.println("state-------"+state);
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			System.err.println("accessTokenObj--------"+accessTokenObj);
			String accessToken = null, openID = null;
			long tokenExpireIn = 0L;

			if (accessTokenObj.getAccessToken().equals("")) {
				// 我们的网站被CSRF攻击了或者用户取消了授权
				// 做一些数据统计工作
				System.err.print("没有获取到响应参数");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				System.err.println("accessToken-------"+accessToken);
				
				tokenExpireIn = accessTokenObj.getExpireIn();

				request.getSession().setAttribute("demo_access_token", accessToken);
				request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

				// 利用获取到的accessToken 去获取当前用的openid -------- start
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();
				System.err.println("openID-----------"+openID);

				//利用获取到的accessToken,openid 去获取用户在Qzone的昵称
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                   map.put("nickName",userInfoBean.getNickname());
                }
				
				//根据openID查找用户
				map.put("alipay_user_id", openID);
				map.put("qq", "qq");
				TdUser user = tdUserService.findByQqUserId(openID);
				if(null == user){
					//用户不存在，跳转绑定页面
					return "/client/accredit_login";
				}else{
					//用户存在，修改最后登录时间，跳转首页
					user.setLastLoginTime(new Date());
					tdUserService.save(user);
					request.getSession().setAttribute("username", user.getUsername());
					return "redirect:/";
				}
			}
		} catch (QQConnectException e) {
			
		}
		return "/client/accredit_login";
	}

	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public void verify(HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		VerifServlet randomValidateCode = new VerifServlet();
		randomValidateCode.getRandcode(request, response);
	}
}
