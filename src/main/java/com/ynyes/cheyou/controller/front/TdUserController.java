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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdCoupon;
import com.ynyes.cheyou.entity.TdDemand;
import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdOrderGoods;
import com.ynyes.cheyou.entity.TdShippingAddress;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.entity.TdUserCollect;
import com.ynyes.cheyou.entity.TdUserComment;
import com.ynyes.cheyou.entity.TdUserConsult;
import com.ynyes.cheyou.entity.TdUserPoint;
import com.ynyes.cheyou.entity.TdUserRecentVisit;
import com.ynyes.cheyou.entity.TdUserReturn;
import com.ynyes.cheyou.entity.TdUserSuggestion;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdCouponService;
import com.ynyes.cheyou.service.TdDemandService;
import com.ynyes.cheyou.service.TdDiySiteService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdOrderGoodsService;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdShippingAddressService;
import com.ynyes.cheyou.service.TdUserCashRewardService;
import com.ynyes.cheyou.service.TdUserCollectService;
import com.ynyes.cheyou.service.TdUserCommentService;
import com.ynyes.cheyou.service.TdUserConsultService;
import com.ynyes.cheyou.service.TdUserPointService;
import com.ynyes.cheyou.service.TdUserRecentVisitService;
import com.ynyes.cheyou.service.TdUserReturnService;
import com.ynyes.cheyou.service.TdUserService;
import com.ynyes.cheyou.service.TdUserSuggestionService;
import com.ynyes.cheyou.util.ClientConstant;

/**
 * 用户中心
 * 
 * @author Sharon
 *
 */
@Controller
public class TdUserController extends AbstractPaytypeController {

    @Autowired
    private TdUserService tdUserService;

    @Autowired
    private TdGoodsService tdGoodsService;

    @Autowired
    private TdUserReturnService tdUserReturnService;

    @Autowired
    private TdOrderService tdOrderService;

    @Autowired
    private TdUserPointService tdUserPointService;

    @Autowired
    private TdCouponService tdCouponService;
    
    @Autowired
    private TdUserCollectService tdUserCollectService;

    @Autowired
    private TdUserConsultService tdUserConsultService;

    @Autowired
    private TdUserCommentService tdUserCommentService;

    @Autowired
    private TdDiySiteService tdDiySiteService;
    /**
     * 投诉service
     * 
     * @author Zhangji
     */
    @Autowired
    private TdUserSuggestionService tdUserSuggestionService;
    
    /**
     * 车友还想团购
     * @author Zhangji
     */
    @Autowired
    private TdDemandService tdDemandService;
    
    @Autowired
    private TdUserRecentVisitService tdUserRecentVisitService;

    @Autowired
    private TdShippingAddressService tdShippingAddressService;

    @Autowired
    private TdOrderGoodsService tdOrderGoodsService;

    @Autowired
    private TdUserCashRewardService tdUserCashRewardService;

    @Autowired
    private TdCommonService tdCommonService;

    @RequestMapping(value = "/user")
    public String user(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        map.addAttribute("server_ip", req.getLocalName());
        map.addAttribute("server_port", req.getLocalPort());

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        if (null == tdUser) {
            return "/client/error_404";
        }

        map.addAttribute("user", tdUser);
        map.addAttribute("order_page", tdOrderService.findByUsername(username,
                0, ClientConstant.pageSize));
        map.addAttribute("collect_page", tdUserCollectService.findByUsername(
                username, 0, ClientConstant.pageSize));
        map.addAttribute("recent_page", tdUserRecentVisitService
                .findByUsernameOrderByVisitTimeDesc(username, 0,
                        ClientConstant.pageSize));
        map.addAttribute("total_unpayed",
                tdOrderService.countByUsernameAndStatusId(username, 2));
        map.addAttribute("total_undelivered",
                tdOrderService.countByUsernameAndStatusId(username, 3));
        map.addAttribute("total_unreceived",
                tdOrderService.countByUsernameAndStatusId(username, 4));
        map.addAttribute("total_finished",
                tdOrderService.countByUsernameAndStatusId(username, 6));

        return "/client/user_index";
    }
    
    @RequestMapping(value = "/user/order/edit", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> orderedit(String orderNumber, Long type,
    		                              HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();

		res.put("code", 1);
		
		String username = (String) request.getSession().getAttribute("username");
        
        if (null == username) {
        	res.put("msg", "请登录！");
            return res;
        }
        
        if (null == orderNumber || null == type) {
        	res.put("msg", "操作失败！");
            return res;
		}
        
        if (type.equals(1L)) {
			TdOrder tdOrder = tdOrderService.findByOrderNumber(orderNumber);
			if (null == tdOrder || !tdOrder.getStatusId().equals(2L)) {
				res.put("msg", "操作失败！");
	            return res;
			}
			
			tdOrder.setStatusId(7L);
			tdOrderService.save(tdOrder);
			res.put("code", 0);
			return res;
		}
        else if (type.equals(2L)) {
        	TdOrder tdOrder = tdOrderService.findByOrderNumber(orderNumber);
			if (null == tdOrder || !tdOrder.getStatusId().equals(6L)) {
				res.put("msg", "操作失败！");
	            return res;
			}
			
			tdOrder.setStatusId(9L);
			tdOrderService.save(tdOrder);
			res.put("code", 0);
			return res;
		}
        
		return res;
    }
    
    @RequestMapping(value = "/user/coupon/list")
    public String couponList(HttpServletRequest req, Integer page,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        if (null == page)
        {
            page = 0;
        }
        
        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        
        map.addAttribute("user", tdUser);
        
        List<TdCoupon> coupanList = null;
        
        coupanList =tdCouponService.findByMoblie(tdUser.getMobile());
        
        map.addAttribute("coupan_list", coupanList);
        
        return "/client/user_coupon_list";
    }
    
    @RequestMapping(value = "/user/order/list/{statusId}")
    public String orderList(@PathVariable Integer statusId, Integer page,
            String keywords, Integer timeId, HttpServletRequest req,
            ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

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

        if (null == statusId) {
            statusId = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);
        map.addAttribute("status_id", statusId);
        map.addAttribute("time_id", timeId);

        Page<TdOrder> orderPage = null;

        if (timeId.equals(0)) {
            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService.findByUsernameAndSearch(
                            username, keywords, page, ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByUsername(username, page,
                            ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndSearch(username,
                                    statusId, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByUsernameAndStatusId(
                            username, statusId, page, ClientConstant.pageSize);
                }
            }
        } else if (timeId.equals(1)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -1);// 月份减一
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndTimeAfterAndSearch(username,
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByUsernameAndTimeAfter(
                            username, time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndTimeAfterAndSearch(
                                    username, statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndTimeAfter(username,
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        } else if (timeId.equals(3)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -3);// 月份减一
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndTimeAfterAndSearch(username,
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByUsernameAndTimeAfter(
                            username, time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndTimeAfterAndSearch(
                                    username, statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndTimeAfter(username,
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        } else if (timeId.equals(6)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -6);// 月份减一
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndTimeAfterAndSearch(username,
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByUsernameAndTimeAfter(
                            username, time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndTimeAfterAndSearch(
                                    username, statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndTimeAfter(username,
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        } else if (timeId.equals(12)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.YEAR, -1);// 减一
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndTimeAfterAndSearch(username,
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByUsernameAndTimeAfter(
                            username, time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndTimeAfterAndSearch(
                                    username, statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByUsernameAndStatusIdAndTimeAfter(username,
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        }

        map.addAttribute("order_page", orderPage);

        return "/client/user_order_list";
    }

    @RequestMapping(value = "/user/order")
    public String order(Long id, HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        if (null != id) {
            map.addAttribute("order", tdOrderService.findOne(id));
        }
        
        TdOrder tdOrder = tdOrderService.findOne(id);
        if (null != tdOrder) {
        	List<TdGoods> tdGoodslist = new ArrayList<>();
            for(TdOrderGoods tdOrderGood : tdOrder.getOrderGoodsList()){
             	TdGoods tdGoods = tdGoodsService.findOne(tdOrderGood.getGoodsId());
             	if (null != tdGoods) {
					tdGoodslist.add(tdGoods);
				}
            }
            map.addAttribute("goods_list", tdGoodslist);
		}
       
        
        // 支付方式列表
        setPayTypes(map, false, true, req);

        return "/client/user_order_detail";
    }

    /**
     * @author lc
     * @注释：同盟店订单查询
     */
    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/user/diysite/order/list/{statusId}")
    public String diysiteorderList(@PathVariable Integer statusId, Integer page,
            String keywords, Integer timeId, HttpServletRequest req,
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

        if (null == statusId) {
            statusId = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        TdDiySite tdDiySite = tdDiySiteService.findbyUsername(username);
        
        map.addAttribute("user", tdUser);
        map.addAttribute("status_id", statusId);
        map.addAttribute("time_id", timeId);

        Page<TdOrder> orderPage = null;

        if (timeId.equals(0)) {
            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService.findByDiysitenameAndSearch(
                    		tdDiySite.getTitle(), keywords, page, ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByDiysitename(tdDiySite.getTitle(), page,
                            ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndSearch(tdDiySite.getTitle(),
                                    statusId, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByDiysitenameAndStatusId(
                    		tdDiySite.getTitle(), statusId, page, ClientConstant.pageSize);
                }
            }
        } else if (timeId.equals(1)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            //calendar.add(Calendar.MONTH, -1);// 月份减一
            //calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);
            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndTimeAfterAndSearch(tdDiySite.getTitle(),
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByDiysitenameAndTimeAfter(
                    		tdDiySite.getTitle(), time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfterAndSearch(
                            		tdDiySite.getTitle(), statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfter(tdDiySite.getTitle(),
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        }else if (timeId.equals(2)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            //calendar.add(Calendar.MONTH, -1);// 月份减一
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndTimeAfterAndSearch(tdDiySite.getTitle(),
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByDiysitenameAndTimeAfter(
                    		tdDiySite.getTitle(), time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfterAndSearch(
                            		tdDiySite.getTitle(), statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfter(tdDiySite.getTitle(),
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        }  
        else if (timeId.equals(3)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -1);// 月份减一
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndTimeAfterAndSearch(tdDiySite.getTitle(),
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByDiysitenameAndTimeAfter(
                    		tdDiySite.getTitle(), time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfterAndSearch(
                            		tdDiySite.getTitle(), statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfter(tdDiySite.getTitle(),
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        } else if (timeId.equals(4)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -3);// 月份减一
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndTimeAfterAndSearch(tdDiySite.getTitle(),
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByDiysitenameAndTimeAfter(
                    		tdDiySite.getTitle(), time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfterAndSearch(
                            		tdDiySite.getTitle(), statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfter(tdDiySite.getTitle(),
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        } else if (timeId.equals(6)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -6);// 月份减一
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndTimeAfterAndSearch(tdDiySite.getTitle(),
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByDiysitenameAndTimeAfter(
                    		tdDiySite.getTitle(), time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfterAndSearch(
                            		tdDiySite.getTitle(), statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfter(tdDiySite.getTitle(),
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        } else if (timeId.equals(12)) {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.YEAR, -1);// 减一
            Date time = calendar.getTime();

            if (statusId.equals(0)) {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndTimeAfterAndSearch(tdDiySite.getTitle(),
                                    time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService.findByDiysitenameAndTimeAfter(
                    		tdDiySite.getTitle(), time, page, ClientConstant.pageSize);
                }
            } else {
                if (null != keywords && !keywords.isEmpty()) {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfterAndSearch(
                            		tdDiySite.getTitle(), statusId, time, keywords, page,
                                    ClientConstant.pageSize);
                } else {
                    orderPage = tdOrderService
                            .findByDiysitenameAndStatusIdAndTimeAfter(tdDiySite.getTitle(),
                                    statusId, time, page,
                                    ClientConstant.pageSize);
                }
            }
        }

        map.addAttribute("order_page", orderPage);

        return "/client/diysite_order_list";
    }
    /**
	 * @author lc
	 * @注释：同盟店订单详情
	 */
    @RequestMapping(value = "/diysite/order")
    public String diysiteorder(Long id, HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("diysiteUsername");
        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        if (null != id) {
            map.addAttribute("order", tdOrderService.findOne(id));
        }
        TdOrder tdOrder = tdOrderService.findOne(id);
        if (null != tdOrder) {
        	List<TdGoods> tdGoodslist = new ArrayList<>();
            for(TdOrderGoods tdOrderGood : tdOrder.getOrderGoodsList()){
             	TdGoods tdGoods = tdGoodsService.findOne(tdOrderGood.getGoodsId());
             	if (null != tdGoods) {
					tdGoodslist.add(tdGoods);
				}
            }
            map.addAttribute("goods_list", tdGoodslist);
		}
        
        // 支付方式列表
        setPayTypes(map, false, true, req);

        return "/client/diysite_order_detail";
    }
    /**
	 * @author lc
	 * @注释：所属会员
	 */
    @RequestMapping(value = "/user/diysite/member")
    public String diysitemember(HttpServletRequest req, Integer page,
            String keywords, ModelMap map) {
    	 String username = (String) req.getSession().getAttribute("diysiteUsername");

         if (null == username) {
             return "redirect:/login";
         }

         tdCommonService.setHeader(map, req);

         if (null == page) {
             page = 0;
         }

         TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

         map.addAttribute("user", tdUser);
         
         TdDiySite tdDiySite = tdDiySiteService.findbyUsername(username);
         
         Page<TdUser> memberPage = null;
         
         if (null == keywords || keywords.isEmpty()) {
        	 if (null != tdDiySite) {
        		 memberPage = tdUserService.findByshopId(tdDiySite.getId(), page,
                         ClientConstant.pageSize);
			}       		        	 
         } else { 
        	 if (null != tdDiySite) {
        		 memberPage = tdUserService.findByShopIdAndSearch(
            			 tdDiySite.getId(), keywords, page, ClientConstant.pageSize);
			}       	 
         }

         map.addAttribute("member_page", memberPage);
         map.addAttribute("keywords", keywords);

         return "/client/diysite_member_list";
    }

    @RequestMapping(value = "/user/collect/list")
    public String collectList(HttpServletRequest req, Integer page,
            String keywords, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        Page<TdUserCollect> collectPage = null;

        if (null == keywords || keywords.isEmpty()) {
            collectPage = tdUserCollectService.findByUsername(username, page,
                    ClientConstant.pageSize);
        } else {
            collectPage = tdUserCollectService.findByUsernameAndSearch(
                    username, keywords, page, ClientConstant.pageSize);
        }

        map.addAttribute("collect_page", collectPage);
        map.addAttribute("keywords", keywords);

        return "/client/user_collect_list";
    }

    @RequestMapping(value = "/user/collect/del")
    public String collectDel(HttpServletRequest req, Long id, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        if (null != id) {
            TdUserCollect collect = tdUserCollectService
                    .findByUsernameAndGoodsId(username, id);

            // 删除收藏
            if (null != collect) {
                tdUserCollectService.delete(collect);
                
                TdGoods goods = tdGoodsService.findOne(collect.getGoodsId());
                
                if (null != goods && null != goods.getTotalCollects())
                {
                    goods.setTotalCollects(goods.getTotalCollects() - 1L);
                    
                    tdGoodsService.save(goods);
                }
            }
        }

        return "redirect:/user/collect/list";
    }

    @RequestMapping(value = "/user/collect/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> collectAdd(HttpServletRequest req, Long goodsId,
            ModelMap map) {

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);

        if (null == goodsId) {
            res.put("message", "参数错误");
            return res;
        }

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            res.put("message", "请先登录");
            return res;
        }

        res.put("code", 0);

        // 没有收藏
        if (null == tdUserCollectService.findByUsernameAndGoodsId(username,
                goodsId)) {
            TdGoods goods = tdGoodsService.findOne(goodsId);

            if (null == goods) {
                res.put("message", "商品不存在");
                return res;
            }
            
            if (null == goods.getTotalCollects())
            {
                goods.setTotalCollects(0L);
            }
            
            goods.setTotalCollects(goods.getTotalCollects() + 1L);
            
            tdGoodsService.save(goods);

            TdUserCollect collect = new TdUserCollect();

            collect.setUsername(username);
            collect.setGoodsId(goods.getId());
            collect.setGoodsCoverImageUri(goods.getCoverImageUri());
            collect.setGoodsTitle(goods.getTitle());
            collect.setGoodsSalePrice(goods.getSalePrice());
            collect.setCollectTime(new Date());

            tdUserCollectService.save(collect);

            res.put("message", "添加成功");

            return res;
        }

        res.put("message", "您已收藏了该商品");

        return res;
    }

    @RequestMapping(value = "/user/recent/list")
    public String recentList(HttpServletRequest req, Integer page,
            String keywords, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        Page<TdUserRecentVisit> recentPage = null;

        if (null == keywords || keywords.isEmpty()) {
            recentPage = tdUserRecentVisitService
                    .findByUsernameOrderByVisitTimeDesc(username, page,
                            ClientConstant.pageSize);
        } else {
            recentPage = tdUserRecentVisitService
                    .findByUsernameAndSearchOrderByVisitTimeDesc(username,
                            keywords, page, ClientConstant.pageSize);
        }

        map.addAttribute("recent_page", recentPage);
        map.addAttribute("keywords", keywords);

        return "/client/user_recent_list";
    }

    @RequestMapping(value = "/user/point/list")
    public String pointList(HttpServletRequest req, Integer page, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        Page<TdUserPoint> pointPage = null;

        pointPage = tdUserPointService.findByUsernameAndisBackgroundShowfalse(username, page,
                ClientConstant.pageSize);

        map.addAttribute("point_page", pointPage);

        return "/client/user_point_list";
    }

    @RequestMapping(value = "/user/return/{orderId}")
    public String userReturn(HttpServletRequest req,
            @PathVariable Long orderId, Long id, // 商品ID
            String method, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        if (null != orderId) {
            TdOrder tdOrder = tdOrderService.findOne(orderId);
            map.addAttribute("order", tdOrder);

            if (null != tdOrder && null != id) {
                for (TdOrderGoods tog : tdOrder.getOrderGoodsList()) {
                    if (tog.getId().equals(id)) {
                        // 已经退换货
                        if (null != tog.getIsReturnApplied()
                                && tog.getIsReturnApplied()) {
                            map.addAttribute("has_returned", true);
                        }

                        map.addAttribute("order_goods", tog);

                        return "/client/user_return_edit";
                    }
                }
            }
        }

        return "/client/user_return";
    }

    @RequestMapping(value = "/user/return/save", method = RequestMethod.POST)
    public String returnSave(HttpServletRequest req, Long goodsId, Long id, // 订单ID
            String reason, String telephone, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null != id && null != goodsId) {
            TdOrder order = tdOrderService.findOne(id);

            if (null != order) {
                for (TdOrderGoods tog : order.getOrderGoodsList()) {
                    if (goodsId.equals(tog.getGoodsId())) {
                        TdUserReturn tdReturn = new TdUserReturn();

                        tdReturn.setIsReturn(false);

                        // 用户
                        tdReturn.setUsername(username);
                        tdReturn.setTelephone(telephone);
                        ;

                        // 退货订单商品
                        tdReturn.setOrderNumber(order.getOrderNumber());
                        tdReturn.setGoodsId(goodsId);
                        tdReturn.setGoodsTitle(tog.getGoodsTitle());
                        tdReturn.setGoodsPrice(tog.getPrice());
                        tdReturn.setGoodsCoverImageUri(tog
                                .getGoodsCoverImageUri());

                        // 退货时间及原因
                        tdReturn.setReason(reason);
                        tdReturn.setReturnTime(new Date());

                        tdReturn.setStatusId(0L);
                        tdReturn.setReturnNumber(1L);

                        // 保存
                        tdUserReturnService.save(tdReturn);

                        // 该商品已经退换货
                        tog.setIsReturnApplied(true);
                        tdOrderGoodsService.save(tog);
                        order.setIsReturn(true);
                        tdOrderService.save(order);
                        break;
                    }
                }
            }
        }

        return "redirect:/user/return/list";
    }

    @RequestMapping(value = "/user/return/list")
    public String returnList(HttpServletRequest req, Integer page,
            String keywords, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        Page<TdUserReturn> returnPage = null;

        if (null == keywords || keywords.isEmpty()) {
            returnPage = tdUserReturnService.findByUsername(username, page,
                    ClientConstant.pageSize);
        } else {
            returnPage = tdUserReturnService.findByUsernameAndSearch(username,
                    keywords, page, ClientConstant.pageSize);
        }

        map.addAttribute("return_page", returnPage);
        map.addAttribute("keywords", keywords);

        return "/client/user_return_list";
    }

    /**
     * 用户投诉
     * 
     * @param req
     * @param map
     * @return
     */
    // @RequestMapping(value="/user/suggestion/list")
    // public String suggestionList(HttpServletRequest req,
    // ModelMap map){
    // String username = (String) req.getSession().getAttribute("username");
    //
    // if (null == username)
    // {
    // return "redirect:/login";
    // }
    //
    // tdCommonService.setHeader(map, req);
    //
    //
    // TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
    //
    // map.addAttribute("user", tdUser);
    // return "/client/user_suggestion_list";
    // }

    /**
     * 投诉
     * 
     */
    @RequestMapping(value = "/suggestion/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> suggestionAdd(HttpServletRequest req,
            String content, String title, String name, String mobile,
            String mail, String code, ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);

        // String username = (String) req.getSession().getAttribute("username");
        //
        // if (null == username)
        // {
        // res.put("message", "请先登录！");
        // return res;
        // }
        //
        TdUserSuggestion tdSuggestion = new TdUserSuggestion();

        tdSuggestion.setContent(content);
        tdSuggestion.setTime(new Date());
        tdSuggestion.setName(name);
        tdSuggestion.setMail(mail);
        tdSuggestion.setMobile(mobile);

        // TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        tdUserSuggestionService.save(tdSuggestion);

        res.put("code", 0);

        return res;
    }
    
    /**
     * 车友还想团购
     *@author Zhangji
     *
     */
    @RequestMapping(value = "/demand/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> demandAdd(HttpServletRequest req, 
                  		String content,
                        String name,
                        String mobile,
                        String mail,
                        Long statusId,
                        ModelMap map){
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
              
        TdDemand tdDemand = new TdDemand();
        
        tdDemand.setContent(content);
        tdDemand.setTime(new Date());
        tdDemand.setName(name);
        tdDemand.setMail(mail);
        tdDemand.setMobile(mobile);
        tdDemand.setStatusId(0L);
               
//        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        tdDemandService.save(tdDemand);
        

        map.addAttribute("demand_list",tdDemand);
                
        res.put("code", 0);
        
        return res;
    }
    
    
    
    @RequestMapping(value = "/user/comment/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commentAdd(HttpServletRequest req,
            TdUserComment tdComment, Long orderId, Long ogId, String code,
            ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            res.put("message", "请先登录！");
            return res;
        }

        if (null == tdComment.getGoodsId()) {
            res.put("message", "商品ID不能为空！");
            return res;
        }

        TdGoods goods = tdGoodsService.findOne(tdComment.getGoodsId());

        if (null == goods) {
            res.put("message", "评论的商品不存在！");
            return res;
        }
        
        if (null == tdComment.getStars()) {
        	res.put("message", "请评价！");
        	tdUserCommentService.delete(tdComment);
        	return res;
		}
        
        tdComment.setCommentTime(new Date());
        tdComment.setGoodsCoverImageUri(goods.getCoverImageUri());
        tdComment.setGoodsTitle(goods.getTitle());
        tdComment.setIsReplied(false);
        tdComment.setNegativeNumber(0L);
        tdComment.setPositiveNumber(0L);
        tdComment.setUsername(username);
        
        tdComment = tdUserCommentService.save(tdComment);

        // 设置订单信息
        if (null != orderId) {
            TdOrder tdOrder = tdOrderService.findOne(orderId);

            if (null != tdOrder) {
                tdComment.setOrderNumber(tdOrder.getOrderNumber());
                /**
				 * @author lc
				 * @注释：添加同盟店评价
				 */
                tdComment.setDiysiteId(tdOrder.getShopId());
                
                List<TdOrderGoods> ogList = tdOrder.getOrderGoodsList();

                for (TdOrderGoods og : ogList) {
                    if (og.getId().equals(ogId)) {
                        og.setIsCommented(true);
                        og.setCommentId(tdComment.getId());
                        tdOrder = tdOrderService.save(tdOrder);
                        break;
                    }
                }

                // 判断订单是否完成
                boolean allIsCommented = true;
                for (TdOrderGoods og : tdOrder.getOrderGoodsList()) {
                    if (null == og.getIsCommented() || !og.getIsCommented()) {
                        allIsCommented = false;
                        break;
                    }
                }

                if (allIsCommented) {
                    tdOrder.setStatusId(6L);
                    tdOrder = tdOrderService.save(tdOrder);
                }
            }
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (null != user) {
            tdComment.setUserHeadUri(user.getHeadImageUri());
        }

        tdComment.setStatusId(0L);

        tdComment = tdUserCommentService.save(tdComment);

        if (null == goods.getTotalComments()) {
            goods.setTotalComments(1L);
        } else {
            goods.setTotalComments(goods.getTotalComments() + 1L);
        }

        res.put("code", 0);

        return res;
    }

    @RequestMapping(value = "/user/comment/sec")
    public String commentSec(HttpServletRequest req, Long commentId,
            ModelMap map) {
        return "/client/comment_sec";
    }

    @RequestMapping(value = "/user/comment/list")
    public String commentList(HttpServletRequest req, Integer page,
            Integer statusId, // 0表示未评价, 1表示已评价
            ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        if (null == statusId) {
            statusId = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        if (null != tdUser) {
            if (0 == statusId) {
                // 查找该用户的未评价订单
            	Page<TdOrder> orderPage = tdOrderService
                        .findByUsernameAndStatusId(username, 5L, page,
                                ClientConstant.pageSize);
                map.addAttribute("order_page", orderPage);
                if (null != orderPage) {
                    for (TdOrder tdOrder : orderPage.getContent()) {
                        if (null != tdOrder) {
                            for (TdOrderGoods og : tdOrder.getOrderGoodsList()) {
                                if (null != og && null != og.getCommentId()) {
                                    TdUserComment uc = tdUserCommentService
                                            .findOne(og.getCommentId());
                                    map.addAttribute("comment_"+tdOrder.getId()+"_"+og.getId(), uc);
                                }
                            }
                        }
                    }
                }
                
//                map.addAttribute("order_page", tdOrderService
//                        .findByUsernameAndStatusId(username, 5L, page,
//                                ClientConstant.pageSize));
            } else {
                // 查找该用户的已评价订单
                Page<TdOrder> orderPage = tdOrderService
                        .findByUsernameAndStatusId(username, 6L, page,
                                ClientConstant.pageSize);
                map.addAttribute("order_page", orderPage);

                if (null != orderPage) {
                    for (TdOrder tdOrder : orderPage.getContent()) {
                        if (null != tdOrder) {
                            for (TdOrderGoods og : tdOrder.getOrderGoodsList()) {
                                if (null != og && null != og.getCommentId()) {
                                    TdUserComment uc = tdUserCommentService
                                            .findOne(og.getCommentId());
                                    map.addAttribute("comment_"+tdOrder.getId()+"_"+og.getId(), uc);
                                }
                            }
                        }
                    }
                }
            }
        }

        map.addAttribute("statusId", statusId);

        return "/client/user_comment_list";
    }

    @RequestMapping(value = "/user/consult/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> consultAdd(HttpServletRequest req,
            TdUserConsult tdConsult,String code, ModelMap map) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);

        String username = (String) req.getSession().getAttribute("username");

        
        if (null == username) {
            res.put("message", "请先登录！");
            return res;
        }

        if (null == tdConsult.getGoodsId()) {
            res.put("message", "商品ID不能为空！");
            return res;
        }

        TdGoods goods = tdGoodsService.findOne(tdConsult.getGoodsId());

        if (null == goods) {
            res.put("message", "咨询的商品不存在！");
            return res;
        }

        // String codeBack = (String)
        // req.getSession().getAttribute("RANDOMVALIDATECODEKEY");
        //
        // if (!codeBack.equalsIgnoreCase(code))
        // {
        // res.put("message", "验证码不匹配！");
        // return res;
        // }

        tdConsult.setConsultTime(new Date());
        tdConsult.setGoodsCoverImageUri(goods.getCoverImageUri());
        tdConsult.setGoodsTitle(goods.getTitle());
        tdConsult.setIsReplied(false);
        tdConsult.setStatusId(0L);
        tdConsult.setUsername(username);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        if (null != user) {
            tdConsult.setUserHeadImageUri(user.getHeadImageUri());
        }

        tdUserConsultService.save(tdConsult);

        res.put("code", 0);

        return res;
    }

    @RequestMapping(value = "/user/consult/list")
    public String consultList(HttpServletRequest req, Integer page,
            String keywords, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        Page<TdUserConsult> consultPage = null;

        if (null == keywords || keywords.isEmpty()) {
            consultPage = tdUserConsultService.findByUsername(username, page,
                    ClientConstant.pageSize);
        } else {
            consultPage = tdUserConsultService.findByUsernameAndSearch(
                    username, keywords, page, ClientConstant.pageSize);
        }

        map.addAttribute("consult_page", consultPage);
        map.addAttribute("keywords", keywords);

        return "/client/user_consult_list";
    }

    @RequestMapping(value = "/user/address/ajax/add")
    @ResponseBody
    public Map<String, Object> addAddress(String receiverName, String prov,
            String city, String dist, String detail, String postcode,
            String mobile, String receiverCarcode, // 增加车牌 by zhangji
            String receiverCartype, // 车型
            HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("code", 1);

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            res.put("message", "请先登录");
            return res;
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (null == user) {
            res.put("message", "该用户不存在");
            return res;
        }

        TdShippingAddress address = new TdShippingAddress();

        address.setReceiverName(receiverName);
        address.setProvince(prov);
        address.setCity(city);
        address.setDisctrict(dist);
        address.setDetailAddress(detail);
        address.setPostcode(postcode);
        address.setReceiverMobile(mobile);
        address.setReceiverCarcode(receiverCarcode); // 增加车牌 by zhangji
        address.setReceiverCartype(receiverCartype); // 车型

        user.getShippingAddressList().add(address);

        tdShippingAddressService.save(address);

        tdUserService.save(user);

        res.put("code", 0);

        return res;
    }

    @RequestMapping(value = "/user/address/{method}")
    public String address(HttpServletRequest req, @PathVariable String method,
            Long id, TdShippingAddress tdShippingAddress, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        if (null != user) {
            List<TdShippingAddress> addressList = user.getShippingAddressList();

            if (null != method && !method.isEmpty()) {
                if (method.equalsIgnoreCase("update")) {
                    if (null != id) {
                        // map.addAttribute("address", s)
                        for (TdShippingAddress add : addressList) {
                            if (add.getId().equals(id)) {
                                map.addAttribute("address", add);
                            }
                        }
                    }
                } else if (method.equalsIgnoreCase("delete")) {
                    if (null != id) {
                        for (TdShippingAddress add : addressList) {
                            if (add.getId().equals(id)) {
                                addressList.remove(id);
                                user.setShippingAddressList(addressList);
                                tdShippingAddressService.delete(add);
                                return "redirect:/user/address/list";
                            }
                        }
                    }
                } else if (method.equalsIgnoreCase("save")) {
                    // 修改
                    if (null != tdShippingAddress.getId()) {
                        tdShippingAddressService.save(tdShippingAddress);
                    }
                    // 新增
                    else {
                        addressList.add(tdShippingAddressService
                                .save(tdShippingAddress));
                        user.setShippingAddressList(addressList);
                        tdUserService.save(user);
                    }

                    return "redirect:/user/address/list";
                }
            }

            map.addAttribute("address_list", user.getShippingAddressList());
        }

        return "/client/user_address_list";
    }

    @RequestMapping(value = "/user/distributor/return")
    public String distributorReturnList(HttpServletRequest req, Integer page,
            ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        map.addAttribute("reward_page", tdUserCashRewardService
                .findByUsernameOrderByIdDesc(username, page,
                        ClientConstant.pageSize));

        return "/client/user_distributor_return";
    }

//    @RequestMapping(value = "/user/distributor/lower")
//    public String distributorLowerList(HttpServletRequest req, Integer page,
//            ModelMap map) {
//
//        String username = (String) req.getSession().getAttribute("username");
//
//        if (null == username) {
//            return "redirect:/login";
//        }
//
//        tdCommonService.setHeader(map, req);
//
//        if (null == page) {
//            page = 0;
//        }
//
//        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//
//        map.addAttribute("user", user);
//        map.addAttribute("lower_page", tdUserService
//                .findByUpperUsernameAndIsEnabled(username, page,
//                        ClientConstant.pageSize));
//
//        return "/client/user_distributor_lower";
//    }

    @RequestMapping(value = "/user/distributor/bankcard")
    public String distributorLowerList(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        return "/client/user_distributor_bankcard";
    }

    /**
     * 返现商品列表
     * 
     * @param req
     * @param page
     * @param map
     * @return
     */
    @RequestMapping(value = "/user/distributor/goods")
    public String distributorGoodsList(HttpServletRequest req, String keywords,
            Integer page, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == page) {
            page = 0;
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        if (null == keywords || keywords.isEmpty()) {
            map.addAttribute("goods_page", tdGoodsService
                    .findByReturnPriceNotZeroAndIsOnSaleTrue(page,
                            ClientConstant.pageSize));
        } else {
            map.addAttribute("goods_page", tdGoodsService
                    .findByReturnPriceNotZeroAndSearchAndIsOnSaleTrue(page,
                            ClientConstant.pageSize, keywords));
        }

        return "/client/user_distributor_goods";
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public String userInfo(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        map.addAttribute("recommend_goods_page", tdGoodsService
                .findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(0,
                        ClientConstant.pageSize));

        return "/client/user_info";
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.POST)
    public String userInfo(HttpServletRequest req, String realName, String sex,
            String email, String mobile, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (null != email && null != mobile) {
            user.setRealName(realName);
            user.setSex(sex);
            user.setEmail(email);
            user.setMobile(mobile);
            user = tdUserService.save(user);
        }

        return "redirect:/user/info";
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.GET)
    public String userPassword(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        return "/client/user_change_password";
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    public String userPassword(HttpServletRequest req, String oldPassword,
            String newPassword, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
        }

        map.addAttribute("user", tdUserService.save(user));

        return "redirect:/user/password";
    }
    
    /**
     * @author mdj
     * @param rep
     * @param imgUrl 头像图片地址
     * @return
     */
    @RequestMapping(value = "/user/headImageUrl", method = RequestMethod.POST)
    @ResponseBody
    public String saveHeadPortrait(String imgUrl,HttpServletRequest rep)
    {
    	String username = (String)rep.getSession().getAttribute("username");
    	if (null == username) {
            return "redirect:/login";
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
    	user.setHeadImageUri(imgUrl);
    	tdUserService.save(user);
    	
    	return "client/user_index";
    }
    
    @ModelAttribute
    public void getModel(
            @RequestParam(value = "addressId", required = false) Long addressId,
            Model model) {
        if (addressId != null) {
            model.addAttribute("tdShippingAddress",
                    tdShippingAddressService.findOne(addressId));
        }
    }
}
