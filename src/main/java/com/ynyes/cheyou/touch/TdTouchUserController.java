package com.ynyes.cheyou.touch;

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
import com.ynyes.cheyou.repository.TdOrderRepo;
import com.ynyes.cheyou.service.TdCommonService;
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
import com.ynyes.cheyou.util.ClientConstant;

/**
 * 用户中心
 * @author Sharon
 *
 */
@Controller
@RequestMapping(value = "/touch")
public class TdTouchUserController {
    
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
    private TdUserCollectService tdUserCollectService;
    
    @Autowired
    private TdUserConsultService tdUserConsultService;
    
    @Autowired
    private TdUserCommentService tdUserCommentService;
    
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
        if (null == username)
        {
            return "redirect:/touch/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        map.addAttribute("server_ip", req.getLocalName());
        map.addAttribute("server_port", req.getLocalPort());
        
        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (null == tdUser)
        {
            return "/touch/error_404";
        }
        
        map.addAttribute("user", tdUser);
        map.addAttribute("order_page", tdOrderService.findByUsername(username, 0, ClientConstant.pageSize));
        map.addAttribute("collect_page", tdUserCollectService.findByUsername(username, 0, ClientConstant.pageSize));
        map.addAttribute("recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(username, 0, ClientConstant.pageSize));
        map.addAttribute("total_unpayed", tdOrderService.countByUsernameAndStatusId(username, 2));
        map.addAttribute("total_undelivered", tdOrderService.countByUsernameAndStatusId(username, 3));
        map.addAttribute("total_unreceived", tdOrderService.countByUsernameAndStatusId(username, 4));
        map.addAttribute("total_finished", tdOrderService.countByUsernameAndStatusId(username, 6));
        
        return "/touch/user_index";
    }
    
    @RequestMapping(value = "/user/order/list/{statusId}")
    public String orderList(@PathVariable Integer statusId, 
                        Integer page,
                        String keywords,
                        Integer timeId,
                        HttpServletRequest req, 
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
        
        if (null == timeId)
        {
            timeId = 0;
        }
        
        if (null == statusId)
        {
            statusId = 0;
        }
        
        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        
        map.addAttribute("user", tdUser);
        map.addAttribute("status_id", statusId);
        map.addAttribute("time_id", timeId);
        
        Page<TdOrder> orderPage = null;
        
        if (timeId.equals(0))
        {
            if (statusId.equals(0))
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndSearch(username, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsername(username, page, ClientConstant.pageSize);
                }
            }
            else
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndSearch(username, statusId, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndStatusId(username, statusId, page, ClientConstant.pageSize);
                }
            }
        }
        else if (timeId.equals(1))
        {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();//日历对象
            calendar.setTime(cur);//设置当前日期
            calendar.add(Calendar.MONTH, -1);//月份减一
            Date time =calendar.getTime();
            
            if (statusId.equals(0))
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndTimeAfterAndSearch(username, time, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndTimeAfter(username, time, page, ClientConstant.pageSize);
                }
            }
            else
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndTimeAfterAndSearch(username, statusId, time, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndTimeAfter(username, statusId, time, page, ClientConstant.pageSize);
                }
            }
        }
        else if (timeId.equals(3))
        {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();//日历对象
            calendar.setTime(cur);//设置当前日期
            calendar.add(Calendar.MONTH, -3);//月份减一
            Date time =calendar.getTime();
            
            if (statusId.equals(0))
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndTimeAfterAndSearch(username, time, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndTimeAfter(username, time, page, ClientConstant.pageSize);
                }
            }
            else
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndTimeAfterAndSearch(username, statusId, time, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndTimeAfter(username, statusId, time, page, ClientConstant.pageSize);
                }
            }
        }
        else if (timeId.equals(6))
        {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();//日历对象
            calendar.setTime(cur);//设置当前日期
            calendar.add(Calendar.MONTH, -6);//月份减一
            Date time =calendar.getTime();
            
            if (statusId.equals(0))
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndTimeAfterAndSearch(username, time, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndTimeAfter(username, time, page, ClientConstant.pageSize);
                }
            }
            else
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndTimeAfterAndSearch(username, statusId, time, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndTimeAfter(username, statusId, time, page, ClientConstant.pageSize);
                }
            }
        }
        else if (timeId.equals(12))
        {
            Date cur = new Date();
            Calendar calendar = Calendar.getInstance();//日历对象
            calendar.setTime(cur);//设置当前日期
            calendar.add(Calendar.YEAR, -1);//减一
            Date time =calendar.getTime();
            
            if (statusId.equals(0))
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndTimeAfterAndSearch(username, time, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndTimeAfter(username, time, page, ClientConstant.pageSize);
                }
            }
            else
            {
                if (null != keywords && !keywords.isEmpty())
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndTimeAfterAndSearch(username, statusId, time, keywords, page, ClientConstant.pageSize);
                }
                else
                {
                    orderPage = tdOrderService.findByUsernameAndStatusIdAndTimeAfter(username, statusId, time, page, ClientConstant.pageSize);
                }
            }
        }
        
        map.addAttribute("order_page", orderPage);
        
        return "/touch/user_order_list";
    }
    
    @RequestMapping(value = "/user/order")
    public String order(Long id,
                        HttpServletRequest req, 
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        
        map.addAttribute("user", tdUser);
        
        if (null != id)
        {
            map.addAttribute("order", tdOrderService.findOne(id));
        }
        
        return "/client/user_order_detail";
    }
    
    @RequestMapping(value = "/user/collect/list")
    public String collectList(HttpServletRequest req, 
                        Integer page,
                        String keywords,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/touch/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        if (null == page)
        {
            page = 0;
        }
        
        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        
        map.addAttribute("user", tdUser);
        
        Page<TdUserCollect> collectPage = null;
        
        if (null == keywords || keywords.isEmpty())
        {
            collectPage = tdUserCollectService.findByUsername(username, page, ClientConstant.pageSize);
        }
        else
        {
            collectPage = tdUserCollectService.findByUsernameAndSearch(username, keywords, page, ClientConstant.pageSize);
        }
        
        map.addAttribute("collect_page", collectPage);
        map.addAttribute("keywords", keywords);
        
        return "/touch/user_collect_list";
    }
    
    @RequestMapping(value = "/user/collect/del")
    public String collectDel(HttpServletRequest req, 
            Long id,
            ModelMap map){
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        
        if (null != id)
        {
            TdUserCollect collect = tdUserCollectService
                    .findByUsernameAndGoodsId(username, id);

            // 删除收藏
            if (null != collect) {
                tdUserCollectService.delete(collect);
            }
        }
        
        return "redirect:/user/collect/list";
    }
    
    @RequestMapping(value = "/user/collect/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> collectAdd(HttpServletRequest req, 
                        Long goodsId,
                        ModelMap map){
        
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        if (null == goodsId)
        {
            res.put("message", "参数错误");
            return res;
        }
        
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            res.put("message", "请先登录");
            return res;
        }
        
        res.put("code", 0);
        
        // 没有收藏
        if (null == tdUserCollectService.findByUsernameAndGoodsId(username, goodsId))
        {
            TdGoods goods = tdGoodsService.findOne(goodsId);
            
            if (null == goods)
            {
                res.put("message", "商品不存在");
                return res;
            }
            
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
    public String recentList(HttpServletRequest req, 
                        Integer page,
                        String keywords,
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
        
        Page<TdUserRecentVisit> recentPage = null;
        
        if (null == keywords || keywords.isEmpty())
        {
            recentPage = tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(username, page, ClientConstant.pageSize);
        }
        else
        {
            recentPage = tdUserRecentVisitService.findByUsernameAndSearchOrderByVisitTimeDesc(username, keywords, page, ClientConstant.pageSize);
        }
        
        map.addAttribute("recent_page", recentPage);
        map.addAttribute("keywords", keywords);
        
        return "/client/user_recent_list";
    }
    
    @RequestMapping(value = "/user/point/list")
    public String pointList(HttpServletRequest req, Integer page,
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
        
        Page<TdUserPoint> pointPage = null;
        
        pointPage = tdUserPointService.findByUsername(username, page, ClientConstant.pageSize);
        
        map.addAttribute("point_page", pointPage);
        
        return "/touch/user_point_list";
    }
    
    @RequestMapping(value = "/user/return/{orderId}")
    public String userReturn(HttpServletRequest req, 
                        @PathVariable Long orderId,
                        Long id, // 商品ID
                        String method,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        
        map.addAttribute("user", tdUser);
        
        if (null != orderId)
        {
            TdOrder tdOrder = tdOrderService.findOne(orderId);
            map.addAttribute("order", tdOrder);
            
            if (null != tdOrder && null != id)
            {
                for (TdOrderGoods tog : tdOrder.getOrderGoodsList())
                {
                    if (tog.getId().equals(id))
                    {
                        // 已经退换货
                        if (null != tog.getIsReturnApplied() && tog.getIsReturnApplied())
                        {
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
    
    @RequestMapping(value = "/user/return/save", method=RequestMethod.POST)
    public String returnSave(HttpServletRequest req, 
                        Long goodsId,
                        Long id, // 订单ID
                        String reason,
                        String telephone,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);

        if (null != id && null != goodsId)
        {
            TdOrder order = tdOrderService.findOne(id);
            
            if (null != order)
            {
                for (TdOrderGoods tog : order.getOrderGoodsList())
                {
                    if (goodsId.equals(tog.getGoodsId()))
                    {
                        TdUserReturn tdReturn = new TdUserReturn();
                        
                        tdReturn.setIsReturn(false); 
                        
                        // 用户
                        tdReturn.setUsername(username);
                        tdReturn.setTelephone(telephone);;
                        
                        // 退货订单商品
                        tdReturn.setOrderNumber(order.getOrderNumber());
                        tdReturn.setGoodsId(goodsId);
                        tdReturn.setGoodsTitle(tog.getGoodsTitle());
                        tdReturn.setGoodsPrice(tog.getPrice());
                        tdReturn.setGoodsCoverImageUri(tog.getGoodsCoverImageUri());
                        
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
                        break;
                    }
                }
            }
        }
        
        return "redirect:/user/return/list";
    }
    
    @RequestMapping(value = "/user/return/list")
    public String returnList(HttpServletRequest req, 
                        Integer page,
                        String keywords,
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
        
        Page<TdUserReturn> returnPage = null;
        
        if (null == keywords || keywords.isEmpty())
        {
            returnPage = tdUserReturnService.findByUsername(username, page, ClientConstant.pageSize);
        }
        else
        {
            returnPage = tdUserReturnService.findByUsernameAndSearch(username, keywords, page, ClientConstant.pageSize);
        }
        
        map.addAttribute("return_page", returnPage);
        map.addAttribute("keywords", keywords);
        
        return "/client/user_return_list";
    }
    
    @RequestMapping(value = "/user/comment/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commentAdd(HttpServletRequest req, 
                        TdUserComment tdComment,
                        String code,
                        ModelMap map){
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            res.put("message", "请先登录！");
            return res;
        }
        
        if (null == tdComment.getGoodsId())
        {
            res.put("message", "商品ID不能为空！");
            return res;
        }
//        TdOrder tdorder = tdOrderService.findByUsername(username, 0, 0);
        
        TdGoods goods = tdGoodsService.findOne(tdComment.getGoodsId());
        
        if (null == goods)
        {
            res.put("message", "评论的商品不存在！");
            return res;
        }
        
//        String codeBack = (String) req.getSession().getAttribute("RANDOMVALIDATECODEKEY");
//        
//        if (!codeBack.equalsIgnoreCase(code))
//        {
//            res.put("message", "验证码不匹配！");
//            return res;
//        }
//        
        tdComment.setCommentTime(new Date());
        tdComment.setGoodsCoverImageUri(goods.getCoverImageUri());
        tdComment.setGoodsTitle(goods.getTitle());
        tdComment.setIsReplied(false);
        tdComment.setNegativeNumber(0L);
        tdComment.setPositiveNumber(0L);
        tdComment.setUsername(username);
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (null != user)
        {
            tdComment.setUserHeadUri(user.getHeadImageUri());
        }
        
        tdComment.setStatusId(0L);
        
        tdUserCommentService.save(tdComment);
        

        if (null == goods.getTotalComments())
        {
            goods.setTotalComments(1L);
        }
        else
        {
            goods.setTotalComments(goods.getTotalComments() + 1L);
        }
        
        res.put("code", 0);
        
        return res;
    }
    
    @RequestMapping(value = "/user/comment/list")
    public String commentList(HttpServletRequest req, 
                        Integer page,
                        String keywords,
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
        
        Page<TdUserComment> commentPage = null;
        
        if (null == keywords || keywords.isEmpty())
        {
            commentPage = tdUserCommentService.findByUsername(username, page, ClientConstant.pageSize);
        }
        else
        {
            commentPage = tdUserCommentService.findByUsernameAndSearch(username, keywords, page, ClientConstant.pageSize);
        }
        
        map.addAttribute("comment_page", commentPage);
        map.addAttribute("keywords", keywords);
        
        return "/client/user_comment_list";
    }
    
    @RequestMapping(value = "/user/consult/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> consultAdd(HttpServletRequest req, 
                        TdUserConsult tdConsult,
                        String code,
                        ModelMap map){
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("username");
    
        if (null == username)
        {
            res.put("message", "请先登录！");
            return res;
        }
        
        if (null == tdConsult.getGoodsId())
        {
            res.put("message", "商品ID不能为空！");
            return res;
        }
        
        TdGoods goods = tdGoodsService.findOne(tdConsult.getGoodsId());
        
        if (null == goods)
        {
            res.put("message", "咨询的商品不存在！");
            return res;
        }
        
//        String codeBack = (String) req.getSession().getAttribute("RANDOMVALIDATECODEKEY");
//        
//        if (!codeBack.equalsIgnoreCase(code))
//        {
//            res.put("message", "验证码不匹配！");
//            return res;
//        }
        
        tdConsult.setConsultTime(new Date());
        tdConsult.setGoodsCoverImageUri(goods.getCoverImageUri());
        tdConsult.setGoodsTitle(goods.getTitle());
        tdConsult.setIsReplied(false);
        tdConsult.setStatusId(0L);
        tdConsult.setUsername(username);
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        if (null != user)
        {
            tdConsult.setUserHeadImageUri(user.getHeadImageUri());
        }
        
        tdUserConsultService.save(tdConsult);
        
        res.put("code", 0);
        
        return res;
    }
    
    @RequestMapping(value = "/user/consult/list")
    public String consultList(HttpServletRequest req, 
                        Integer page,
                        String keywords,
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
        
        Page<TdUserConsult> consultPage = null;
        
        if (null == keywords || keywords.isEmpty())
        {
            consultPage = tdUserConsultService.findByUsername(username, page, ClientConstant.pageSize);
        }
        else
        {
            consultPage = tdUserConsultService.findByUsernameAndSearch(username, keywords, page, ClientConstant.pageSize);
        }
        
        map.addAttribute("consult_page", consultPage);
        map.addAttribute("keywords", keywords);
        
        return "/client/user_consult_list";
    }
    
    @RequestMapping(value = "/user/address/ajax/add")
    @ResponseBody
    public Map<String, Object> addAddress(String receiverName,
                                    String prov,
                                    String city,
                                    String dist,
                                    String detail,
                                    String postcode,
                                    String mobile,
                                    HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            res.put("message", "请先登录");
            return res;
        }
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (null == user)
        {
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
        
        user.getShippingAddressList().add(address);
        
        tdShippingAddressService.save(address);
        
        tdUserService.save(user);
        
        res.put("code", 0);
        
        return res;
    }
    
    @RequestMapping(value = "/user/address/{method}")
    public String address(HttpServletRequest req, 
                        @PathVariable String method,
                        Long id,
                        TdShippingAddress tdShippingAddress,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        
        if (null != user)
        {
            List<TdShippingAddress> addressList = user.getShippingAddressList();
            
            if (null != method && !method.isEmpty())
            {
                if (method.equalsIgnoreCase("update"))
                {
                    if (null != id)
                    {
                        //map.addAttribute("address", s)
                        for (TdShippingAddress add : addressList)
                        {
                            if (add.getId().equals(id))
                            {
                                map.addAttribute("address", add);
                            }
                        }
                    }
                }
                else if (method.equalsIgnoreCase("delete"))
                {
                    if (null != id)
                    {
                        for (TdShippingAddress add : addressList)
                        {
                            if (add.getId().equals(id))
                            {
                                addressList.remove(id);
                                user.setShippingAddressList(addressList);
                                tdShippingAddressService.delete(add);
                                return "redirect:/user/address/list";
                            }
                        }
                    }
                }
                else if (method.equalsIgnoreCase("save"))
                {
                    // 修改
                    if (null != tdShippingAddress.getId())
                    {
                        tdShippingAddressService.save(tdShippingAddress);
                    }
                    // 新增
                    else
                    {
                        addressList.add(tdShippingAddressService.save(tdShippingAddress));
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
    public String distributorReturnList(HttpServletRequest req, 
                        Integer page,
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
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        map.addAttribute("reward_page", tdUserCashRewardService.findByUsernameOrderByIdDesc(username, page, ClientConstant.pageSize));
        
        return "/client/user_distributor_return";
    }
    
    @RequestMapping(value = "/user/distributor/lower")
    public String distributorLowerList(HttpServletRequest req, 
                        Integer page,
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
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        map.addAttribute("lower_page", tdUserService.findByUpperUsernameAndIsEnabled(username, page, ClientConstant.pageSize));
        
        return "/client/user_distributor_lower";
    }
    
    @RequestMapping(value = "/user/distributor/bankcard")
    public String distributorLowerList(HttpServletRequest req,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
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
    public String distributorGoodsList(HttpServletRequest req, 
                        String keywords,
                        Integer page,
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
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        
        if (null == keywords || keywords.isEmpty())
        {
            map.addAttribute("goods_page", tdGoodsService.findByReturnPriceNotZeroAndIsOnSaleTrue(page, ClientConstant.pageSize));
        }
        else
        {
            map.addAttribute("goods_page", tdGoodsService.findByReturnPriceNotZeroAndSearchAndIsOnSaleTrue(page, ClientConstant.pageSize, keywords));
        }
        
        return "/client/user_distributor_goods";
    }
    
    @RequestMapping(value = "/user/info", method=RequestMethod.GET)
    public String userInfo(HttpServletRequest req,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        map.addAttribute("user", user);
        
        map.addAttribute("recommend_goods_page", tdGoodsService.findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(0, ClientConstant.pageSize));
        
        return "/touch/user_info";
    }
    
    @RequestMapping(value = "/user/info", method=RequestMethod.POST)
    public String userInfo(HttpServletRequest req,
                        String realName,
                        String sex,
                        String carCode,
                        String mobile,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (null != carCode && null != mobile)
        {
            user.setRealName(realName);
            user.setSex(sex);
            user.setCarCode(carCode);
            user.setMobile(mobile);
            user = tdUserService.save(user);
        }
        
        return "redirect:/touch/user";
    }
    
    @RequestMapping(value = "/user/password", method=RequestMethod.GET)
    public String userPassword(HttpServletRequest req,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setHeader(map, req);
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        map.addAttribute("user", user);
        
        return "/client/user_change_password";
    }
    
    @RequestMapping(value = "/user/password", method=RequestMethod.POST)
    public String userPassword(HttpServletRequest req,
                        String oldPassword,
                        String newPassword,
                        ModelMap map){
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        if (user.getPassword().equals(oldPassword))
        {
            user.setPassword(newPassword);
        }
        
        map.addAttribute("user", tdUserService.save(user));
        
        return "redirect:/user/password";
    }
    
    @ModelAttribute
    public void getModel(@RequestParam(value = "addressId", required = false) Long addressId,
            Model model) {
        if (addressId != null) {
            model.addAttribute("tdShippingAddress", tdShippingAddressService.findOne(addressId));
        }
    }
}