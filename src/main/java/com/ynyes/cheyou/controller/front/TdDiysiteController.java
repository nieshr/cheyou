package com.ynyes.cheyou.controller.front;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdCoupon;
import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdCouponService;
import com.ynyes.cheyou.service.TdDiySiteService;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdUserService;
import com.ynyes.cheyou.util.SMSUtil;
import com.ynyes.cheyou.util.SiteMagConstant;

import scala.reflect.macros.internal.macroImpl;


@Controller
@RequestMapping(value="/diysite/order")
public class TdDiysiteController {
	
	@Autowired
	TdOrderService tdOrderService;
	
	@Autowired
	TdCommonService tdCommonService;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdDiySiteService tdDiySiteService;
	
	@Autowired
	TdCouponService tdCouponService;
	
	/**
	 * @author lc
	 * @注释：优惠券核销
	 */
	@RequestMapping(value = "/couponconfirm")
	public String couponconfirm(Integer page,
	                        HttpServletRequest req,
	                        ModelMap map) {
		String username = (String) req.getSession().getAttribute("diysiteUsername");
		if (null == username) {
            return "redirect:/login";
        }
		tdCommonService.setHeader(map, req);
		if (null == page) {
            page = 0;
        }
		
		TdDiySite tdDiySite = tdDiySiteService.findbyUsername(username);
		
//		List<TdCoupon> tdCouponlist = tdCouponService.findByDiySiteIdAndIsUsedTrue(tdDiySite.getId());
		
//		List<String> mobilelist = new ArrayList<>();
//		
//		for(TdCoupon tdCoupon : tdCouponlist){
//			if (tdCoupon.getTypeTitle().equals("免费洗车券") || tdCoupon.getTypeTitle().equals("免费打蜡券")) {
//				if (mobilelist.contains(tdCoupon.getMobile())) {
//					
//				}else{
//					mobilelist.add(tdCoupon.getMobile());
//				}
//				
//			}
//		}
//		
//		map.addAttribute("member_page", tdUserService.findByMoblieIn(mobilelist, page, SiteMagConstant.pageSize));
		map.addAttribute("member_page", tdCouponService.findByDiySiteIdAndIsUsedTrue(tdDiySite.getId(), page, SiteMagConstant.pageSize));
		map.addAttribute("diysite", tdDiySite);
		return "/client/diysite_couponconfirm";
		
	}
	
	@RequestMapping(value="/couponconfirm", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> couponConf(String mobile, String password){
    	 Map<String, Object> res = new HashMap<String, Object>();         
        res.put("code", 1);
        
        TdCoupon tdCoupon = tdCouponService.findByMoblieAndConsumerPassword(mobile, password);
        if (null == tdCoupon) {
		    res.put("msg", "优惠券不存在！");
		    return res;
		}
        if (tdCoupon.getIsUsed()) {
			res.put("msg", "优惠券已使用");
		}
        if (null != tdCoupon ) {
        	TdUser tdUser = tdUserService.findByMobileAndIsEnabled(tdCoupon.getMobile());
        	if (null != tdUser) {
				tdCoupon.setUserDiysiteId(tdUser.getUpperDiySiteId());
			}
			tdCoupon.setIsUsed(true);
			tdCouponService.save(tdCoupon);
		}
        res.put("code", 0);
        
        return res;
    }
	
	/**
	 * @author lc
	 * @注释：体验订单查看
	 */
	@RequestMapping(value = "/experience")
	public String rebateincome(Integer page,
	                        Integer timeId,
	                        String keywords,
	                        HttpServletRequest req,
	                        ModelMap map) {
		String username = (String) req.getSession().getAttribute("diysiteUsername");
		if (null == username) {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        if (null == page) {
			page = 0;
		}
        
        TdDiySite tdDiySite = tdDiySiteService.findbyUsername(username);
        
        if (null == keywords) {
        	map.addAttribute("member_page", tdCouponService.findByDiySiteIdAndIsUsedTrue(tdDiySite.getId(), page, SiteMagConstant.pageSize));
		}
        else{
        	map.addAttribute("member_page", tdCouponService.findByDiySiteIdAndIsUsedTrueContainsKeywords(tdDiySite.getId(), keywords, page, SiteMagConstant.pageSize));
        }
        map.addAttribute("diysite", tdDiySite);
        return "/client/diysite_order_experience";
	}
	
	/**
	 * @author lc
	 * @注释：返利收入
	 */
	@RequestMapping(value = "/rebateincome")
	public String rebateincome(Integer page,
	                        Integer timeId, 
	                        HttpServletRequest req,
	                        ModelMap map) {
		String username = (String) req.getSession().getAttribute("diysiteUsername");
		if (null == username) {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        if (null == timeId) {
            timeId = 0;
        }

        TdDiySite tdDiySite = tdDiySiteService.findbyUsername(username);
        Double rebates = new Double(0.00);
        
        List<TdUser> tdUserlist = tdUserService.findByUpperDiySiteIdAndIsEnabled(tdDiySite.getId());
        List<String> tdUsers = new ArrayList<>();
        for(int i = 0; i < tdUserlist.size(); i++){
        	tdUsers.add(tdUserlist.get(i).getUsername());
        }
        if (timeId.equals(0)) {
        	if (null != tdUsers) {
        		List<TdOrder> list = tdOrderService.findByUsernameIn(tdUsers);
            	rebates = countrebates(list);
            	map.addAttribute("order_page", tdOrderService.findByUsernameIn(tdUsers, page, SiteMagConstant.pageSize));
			}     	
		}else if (timeId.equals(1)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
          //  calendar.add(Calendar.MONTH, -1);// 月份减一
          //  calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            
            if (null != tdUsers) {
        		List<TdOrder> list = tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time);
            	rebates = countrebates(list);
            	map.addAttribute("order_page", tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time, page, SiteMagConstant.pageSize));
			}   
		}else if (timeId.equals(2)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
          //  calendar.add(Calendar.MONTH, -1);// 月份减一
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            if (null != tdUsers) {
        		List<TdOrder> list = tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time);
            	rebates = countrebates(list);
            	map.addAttribute("order_page", tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time, page, SiteMagConstant.pageSize));
			}   
		}else if (timeId.equals(3)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -1);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            if (null != tdUsers) {
        		List<TdOrder> list = tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time);
            	rebates = countrebates(list);
            	map.addAttribute("order_page", tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time, page, SiteMagConstant.pageSize));
			}   
		}else if (timeId.equals(4)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -3);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            if (null != tdUsers) {
        		List<TdOrder> list = tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time);
            	rebates = countrebates(list);
            	map.addAttribute("order_page", tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time, page, SiteMagConstant.pageSize));
			}   
		}else if (timeId.equals(6)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -6);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            if (null != tdUsers) {
        		List<TdOrder> list = tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time);
            	rebates = countrebates(list);
            	map.addAttribute("order_page", tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time, page, SiteMagConstant.pageSize));
			}   
		}else if (timeId.equals(12)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -12);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            if (null != tdUsers) {
        		List<TdOrder> list = tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time);
            	rebates = countrebates(list);
            	map.addAttribute("order_page", tdOrderService.findByUsernameInAndOrderTimeAfter(tdUsers, time, page, SiteMagConstant.pageSize));
			}   
		}
        map.addAttribute("time_id", timeId);
        map.addAttribute("rebates",rebates);
        map.addAttribute("page", page);
        
        return "/client/diysite_rebate_income";
	}
	/**
	 * @author lc
	 * @注释：计算返利总额
	 */
	public Double countrebates(List<TdOrder> list){
    	Double rebates = new Double(0.00);       
    	for (int i = 0; i < list.size(); i++) {
    		if (null != list.get(i).getRebate()) {
    			rebates += list.get(i).getRebate();
			}  		
    	}
    	return rebates;
    }
	/**
	 * @author lc
	 * @注释：订单收入
	 */
	@RequestMapping(value = "/orderincome")
	public String orderincome(Integer page,
	                        Integer timeId, 
	                        HttpServletRequest req,
	                        ModelMap map) {
		String username = (String) req.getSession().getAttribute("diysiteUsername");

        if (null == username) {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        if (null == timeId) {
            timeId = 0;
        }
        
        
        Double sales = new Double(0.00);

        TdDiySite tdDiySite = tdDiySiteService.findbyUsername(username);        
        
        if (timeId.equals(0)) {
        	List<TdOrder> list = tdOrderService.findAllVerifyBelongShopTitle(tdDiySite.getTitle());
        	sales = countsales(list);
        	map.addAttribute("order_page", tdOrderService.findAllVerifyBelongShopTitleOrderByIdDesc(tdDiySite.getTitle(), page, SiteMagConstant.pageSize));
		}else if (timeId.equals(1)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
          //  calendar.add(Calendar.MONTH, -1);// 月份减一
          //  calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            
            List<TdOrder> list = tdOrderService.findAllVerifyBelongShopTitleAndTimeAfter(tdDiySite.getTitle(), time);
            sales = countsales(list);
            map.addAttribute("order_page", tdOrderService.findAllVerifyBelongShopTitleTimeAfterOrderByIdDesc(tdDiySite.getTitle(), time, page, SiteMagConstant.pageSize));
            
		}else if (timeId.equals(2)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
          //  calendar.add(Calendar.MONTH, -1);// 月份减一
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            
            List<TdOrder> list = tdOrderService.findAllVerifyBelongShopTitleAndTimeAfter(tdDiySite.getTitle(), time);
            sales = countsales(list);
            map.addAttribute("order_page", tdOrderService.findAllVerifyBelongShopTitleTimeAfterOrderByIdDesc(tdDiySite.getTitle(), time, page, SiteMagConstant.pageSize));
		}else if (timeId.equals(3)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -1);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            
            List<TdOrder> list = tdOrderService.findAllVerifyBelongShopTitleAndTimeAfter(tdDiySite.getTitle(), time);
            sales = countsales(list);
            map.addAttribute("order_page", tdOrderService.findAllVerifyBelongShopTitleTimeAfterOrderByIdDesc(tdDiySite.getTitle(), time, page, SiteMagConstant.pageSize));
		}else if (timeId.equals(4)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -3);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            
            List<TdOrder> list = tdOrderService.findAllVerifyBelongShopTitleAndTimeAfter(tdDiySite.getTitle(), time);
            sales = countsales(list);
            map.addAttribute("order_page", tdOrderService.findAllVerifyBelongShopTitleTimeAfterOrderByIdDesc(tdDiySite.getTitle(), time, page, SiteMagConstant.pageSize));
		}else if (timeId.equals(6)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -6);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            
            List<TdOrder> list = tdOrderService.findAllVerifyBelongShopTitleAndTimeAfter(tdDiySite.getTitle(), time);
            sales = countsales(list);
            map.addAttribute("order_page", tdOrderService.findAllVerifyBelongShopTitleTimeAfterOrderByIdDesc(tdDiySite.getTitle(), time, page, SiteMagConstant.pageSize));
		}else if (timeId.equals(12)) {
			Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -12);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            
            List<TdOrder> list = tdOrderService.findAllVerifyBelongShopTitleAndTimeAfter(tdDiySite.getTitle(), time);
            sales = countsales(list);
            map.addAttribute("order_page", tdOrderService.findAllVerifyBelongShopTitleTimeAfterOrderByIdDesc(tdDiySite.getTitle(), time, page, SiteMagConstant.pageSize));
		}
        map.addAttribute("time_id", timeId);
        map.addAttribute("sales",sales);
        map.addAttribute("page", page);
        
        return "/client/diysite_order_income";
	}
	/**
	 * @author lc
	 * @注释：计算总额和销售额
	 */
    public Double countprice(List<TdOrder> list){
    	Double price = new Double(0.00);       
    	for (int i = 0; i < list.size(); i++) {
    		price += list.get(i).getTotalPrice();
    	}
    	return price;
    }
    public Double countsales(List<TdOrder> list){
    	Double sales = new Double(0.00);
    	for(int i = 0; i < list.size(); i++){
    		if (list.get(i).getStatusId().equals(2L) || list.get(i).getStatusId().equals(7L)) {	
    			
			}
    		else{
    			if (null != list.get(i).getOrderIncome()) {
    				sales += list.get(i).getOrderIncome();
				}			
    		}
    	}
    	return sales;
    }
	
    /**
	 * @author lc
	 * @注释：通过id获取地址
	 */
    @RequestMapping(value="/getaddress", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getaddress(Long id){
    	 Map<String, Object> res = new HashMap<String, Object>();         
         res.put("code", 1);
         
         if (null != id) {
			TdDiySite tdDiySite = tdDiySiteService.findOne(id);
			res.put("address", tdDiySite.getAddress());
			res.put("code", 0);
		}else{
			res.put("address", " ");
			res.put("code", 0);
		}
         
         return res;
    }
    
    /**
	 * @author lc
	 * @注释：发送地址到用户手机
	 */
    @RequestMapping(value="/sendAddress", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendaddress(Long id, HttpServletRequest req){
    	 Map<String, Object> res = new HashMap<String, Object>();         
        res.put("code", 1);
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
			res.put("msg", "请登录");
			return res;
		}
         
        if (null == id) {
        	res.put("msg", "发送失败！");
			return res;
		}
        
        TdUser tdUser = tdUserService.findByUsername(username);
        TdDiySite tdDiySite = tdDiySiteService.findOne(id);
        if (null == tdDiySite) {
        	res.put("msg", "发送失败！");
			return res;
		}
        if (null == tdDiySite.getTitle() || null == tdDiySite.getAddress() || null == tdDiySite.getServiceTele()) {
        	res.put("msg", "发送失败！");
			return res;
		}
        //发送地址到手机
        SMSUtil.send(tdUser.getMobile(), "33442" ,new String[]{tdDiySite.getTitle(), tdDiySite.getAddress(), tdDiySite.getServiceTele(),"http://www.cytm99.com/touch/shop/list"});
        res.put("code", 0);
        return res;
    }
    
    /**
   	 * @author lc
   	 * @注释：通过地区获取同盟店列表
   	 */
     @RequestMapping(value="/getdiysites", method = RequestMethod.POST)
     @ResponseBody
     public Map<String, Object> getdiysites(String disctrict){
       Map<String, Object> res = new HashMap<String, Object>();         
       res.put("code", 1);
            
       if (null != disctrict) {
   			List<TdDiySite> tdDiySites = tdDiySiteService.findBydisctrict(disctrict);
   			
   			res.put("tdDiySites", tdDiySites);
   			res.put("code", 0);
       }
            
       return res;
    }
    
	@RequestMapping(value="/param/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> paramEdit(String orderNumber, String password,
                        String type,
                        String data,
                        String name,
                        String address,
                        String postal,
                        String mobile,
                        String expressNumber,
                        Long deliverTypeId,
                        ModelMap map,
                        HttpServletRequest req){
        
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("diysiteUsername");
        if (null == username)
        {
            res.put("message", "请重新登录");
            return res;
        }
        
        if (null != orderNumber && !orderNumber.isEmpty() && null != type && !type.isEmpty() && null != password)
        {
            TdOrder order = tdOrderService.findByOrderNumber(orderNumber);
            TdUser tdUser = tdUserService.findByUsername(order.getUsername());
            // 修改备注
            if (type.equalsIgnoreCase("editMark"))
            {
                order.setRemarkInfo(data);
            }
            // 确认已服务
            else if (type.equalsIgnoreCase("orderService"))
            {
                if (order.getStatusId().equals(4L))
                {
                	if (null == tdUser.getUpperDiySiteId()) {
						tdUser.setUpperDiySiteId(order.getShopId());
						tdUserService.save(tdUser);
					}
                	if (order.getSmscode().equals(password)) {
                		order.setStatusId(5L);
                        order.setServiceTime(new Date());
					}else{
						res.put("message", "消费密码错误!");
						return res;
					}
                    
                }
            }
          
            
            tdOrderService.save(order);
            
            res.put("code", 0);
            res.put("message", "修改成功!");
            return res;
        }
        
        res.put("message", "参数错误!");
        return res;
    }
	
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String userPassword(HttpServletRequest req, ModelMap map) {
        String diysiteUsername = (String) req.getSession().getAttribute("diysiteUsername");

        if (null == diysiteUsername) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdDiySite DiySiteUser = tdDiySiteService.findbyUsername(diysiteUsername);

        map.addAttribute("user", DiySiteUser);

        return "/client/diysite_change_password";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userPassword(HttpServletRequest req, String oldPassword,
            String newPassword, ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String diysiteUsername = (String) req.getSession().getAttribute("diysiteUsername");

        if (null == diysiteUsername) {
        	res.put("msg", "请先登录！");
            return res;
        }

        TdDiySite DiySiteUser = tdDiySiteService.findbyUsername(diysiteUsername);

        if (DiySiteUser.getPassword().equals(oldPassword)) {
        	DiySiteUser.setPassword(newPassword);
        }

        map.addAttribute("user", tdDiySiteService.save(DiySiteUser));

        res.put("code", 0);
        return res;
    }
}
