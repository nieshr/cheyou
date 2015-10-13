package com.ynyes.cheyou.controller.front;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.cheyou.entity.TdCartGoods;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdGoodsGift;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.service.TdCartGoodsService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdGoodsCombinationService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdOrderService;

/**
 * 购物车
 *
 */
@Controller
public class TdCartController {

    @Autowired
    private TdCartGoodsService tdCartGoodsService;

    @Autowired
    private TdGoodsService tdGoodsService;
    
    @Autowired
    private TdGoodsCombinationService tdGoodsCombinationService;

    @Autowired
    private TdCommonService tdCommonService;
    
    @Autowired
    private TdGoodsService tdGoodService;
    
    @Autowired
    private TdOrderService tdOrderService;

    /**
     * 加入购物车
     * @param id 商品ID
     * @param quantity 数量 
     * @param qiang 抢购类型 0：正常销售 >0：促销
     * @param m 是否是触屏 0: 否 1: 是
     * @param req
     * @return
     */
    @RequestMapping(value = "/cart/init")
    public String addCart(Long id, Long quantity, String zhid, Integer m,
            HttpServletRequest req) {
        // 是否已登录
        boolean isLoggedIn = true;

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            isLoggedIn = false;
            username = req.getSession().getId();
        }
        
        if (null == m)
        {
            m = 0;
        }
        
        if (null == quantity || quantity.compareTo(1L) < 0)
        {
            quantity = 1L;
        }
        
        if (null != id) {
            // 这两种商品一个ID号只能购买一次
            if (id.equals(226L) || id.equals(1644L))
            {
                List<TdCartGoods> cartGoodsList = tdCartGoodsService.findByGoodsIdAndUsername(226L, username);
             
                // 已经添加了该商品
                if (null != cartGoodsList && cartGoodsList.size() > 0)
                {
                    return "redirect:/cart/add?id=" + id + "&m=" + m + "&r=1";
                }
                
                cartGoodsList = tdCartGoodsService.findByGoodsIdAndUsername(1644L, username);
                
                // 已经添加了该商品
                if (null != cartGoodsList && cartGoodsList.size() > 0)
                {
                    return "redirect:/cart/add?id=" + id + "&m=" + m + "&r=1";
                }
                
                List<TdOrder> orderList = tdOrderService.findByUsernameAndGoodsId(username, 226L);
                
                // 已经购买了该商品
                if (null != orderList && orderList.size() > 0)
                {
                    return "redirect:/cart/add?id=" + id + "&m=" + m + "&r=1";
                }
                
                orderList = tdOrderService.findByUsernameAndGoodsId(username, 1644L);
                
                // 已经购买了该商品
                if (null != orderList && orderList.size() > 0)
                {
                    return "redirect:/cart/add?id=" + id + "&m=" + m + "&r=1";
                }
            }
            
            TdGoods goods = tdGoodsService.findOne(id);

            if (null != goods) {
                
                // 购物车项目
                List<TdCartGoods> oldCartGoodsList = null;
                
                // 购物车是否已有该商品
                oldCartGoodsList = tdCartGoodsService
                                .findByGoodsIdAndUsername(id, username);
                
                // 有多项，则在第一项上数量进行相加
                if (null != oldCartGoodsList && oldCartGoodsList.size() > 0) {
                    long oldQuantity = oldCartGoodsList.get(0).getQuantity();
                    oldCartGoodsList.get(0).setQuantity(oldQuantity + quantity);
                    tdCartGoodsService.save(oldCartGoodsList.get(0));
                }
                // 新增购物车项
                else
                {
                    TdCartGoods cartGoods = new TdCartGoods();
                    
                    cartGoods.setIsLoggedIn(isLoggedIn);
                    cartGoods.setUsername(username);
    
                    cartGoods.setIsSelected(true);
                    cartGoods.setGoodsId(goods.getId());
    
                    cartGoods.setQuantity(quantity);
                    
                    tdCartGoodsService.save(cartGoods);
                }
            }
        }

        return "redirect:/cart/add?id=" + id + "&m=" + m;
    }

    @RequestMapping(value = "/cart/add")
    public String cartInit(Long id, Integer m, Integer r, HttpServletRequest req, ModelMap map) {
        tdCommonService.setHeader(map, req);
        
        if (null != r && 1 == r)
        {
            map.addAttribute("res", "该商品仅限购买一次");
        }
        
        if (null == m)
        {
            m = 0;
        }
        
        if (m.equals(1)) { // 移动端浏览器
            
            return "/touch/cart_add_res";
        }
        
        return "/client/cart_add_res";
    }

    @RequestMapping(value = "/cart")
    public String cart(HttpServletRequest req, ModelMap map) {

        String username = (String) req.getSession().getAttribute("username");

        // 未登录用户的购物车商品
        List<TdCartGoods> cartSessionGoodsList = tdCartGoodsService
                .findByUsername(req.getSession().getId());
        if (null == username)
        {
            username = req.getSession().getId();
        }
        else
        {
            // 合并商品
            // 已登录用户的购物车
            List<TdCartGoods> cartUserGoodsList = tdCartGoodsService
                    .findByUsername(username);
            for (TdCartGoods cg : cartSessionGoodsList)
            {
            // 将未登录用户的购物车加入已登录用户购物车中
                cg.setUsername(username);
                cartUserGoodsList.add(cg);
            }

            cartUserGoodsList = tdCartGoodsService.save(cartUserGoodsList);

            for (TdCartGoods cg1 : cartUserGoodsList) 
            {
                // 删除重复的商品
                List<TdCartGoods> findList = tdCartGoodsService
                        .findByGoodsIdAndUsername(cg1.getGoodsId(), username);

                if (null != findList && findList.size() > 1) 
                {
                    tdCartGoodsService.delete(findList.subList(1,findList.size()));
                }
            }
        }

        List<TdCartGoods> resList = tdCartGoodsService.findByUsername(username);
        
        /*  添加gift  mdj 2015-8-7 09:57:28*/
        List<TdGoods> tdGoodsList = new ArrayList<>();
        for (TdCartGoods tdCartGoods : resList) 
        { 
			TdGoods tdGoods = tdGoodService.findById(tdCartGoods.getGoodsId());
			if (null != tdGoods) {
				List<TdGoodsGift> tdGoodGiftUserList = tdGoods.getGiftList();
				if (tdGoodGiftUserList != null && tdGoodGiftUserList.size()>=1)
				{
					tdGoodsList.add(tdGoods);
				}
			}			
		}
        
        if (tdGoodsList != null && tdGoodsList.size()>=1)
        {
        	map.addAttribute("goods_list",tdGoodsList);
		}
        
        map.addAttribute("cart_goods_list", tdCartGoodsService.updateGoodsInfo(resList));
        
        tdCommonService.setHeader(map, req);

        if (null == resList || resList.size() == 0)
        {
            return "/client/cart_null";
        }

        return "/client/cart";
    }

    @RequestMapping(value = "/cart/toggleSelect", method = RequestMethod.POST)
    public String cartToggle(Long id, HttpServletRequest req, ModelMap map) {

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            username = req.getSession().getId();
        }

        List<TdCartGoods> cartGoodsList = tdCartGoodsService
                .findByUsername(username);

        if (null != id) {
            for (TdCartGoods cartGoods : cartGoodsList) {
                if (cartGoods.getId().equals(id)) {
                    if (null == cartGoods.getIsSelected() || false == cartGoods.getIsSelected())
                    {
                        cartGoods.setIsSelected(true);
                    }
                    else
                    {
                        cartGoods.setIsSelected(false);
                    }
                    cartGoods = tdCartGoodsService.save(cartGoods);
                    break;
                }
            }
        }

        map.addAttribute("cart_goods_list", tdCartGoodsService.updateGoodsInfo(cartGoodsList));

        return "/client/cart_goods";
    }

    @RequestMapping(value = "/cart/toggleAll", method = RequestMethod.POST)
    public String cartToggleAll(Integer sid, HttpServletRequest req,
            ModelMap map) {

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            username = req.getSession().getId();
        }

        List<TdCartGoods> cartGoodsList = tdCartGoodsService
                .findByUsername(username);

        if (null != sid) {
            if (sid.equals(0)) // 全选
            {
                for (TdCartGoods cartGoods : cartGoodsList) {
                    cartGoods.setIsSelected(true);
                }
            } else // 取消全选
            {
                for (TdCartGoods cartGoods : cartGoodsList) {
                    cartGoods.setIsSelected(false);
                }
            }
            tdCartGoodsService.save(cartGoodsList);
        }

        map.addAttribute("cart_goods_list", tdCartGoodsService.updateGoodsInfo(cartGoodsList));

        return "/client/cart_goods";
    }

    @RequestMapping(value = "/cart/numberAdd", method = RequestMethod.POST)
    public String cartNumberAdd(Long id, HttpServletRequest req, ModelMap map) {

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            username = req.getSession().getId();
        }

        if (null != id) {
            TdCartGoods cartGoods = tdCartGoodsService.findOne(id);
            
            if (null != cartGoods)
            {
                // 226 和 1644商品仅限购买一次
                if (!cartGoods.getGoodsId().equals(226L) && !cartGoods.getGoodsId().equals(1644L))
                {
                    if (cartGoods.getUsername().equalsIgnoreCase(username)) {
                        long quantity = cartGoods.getQuantity();
                        cartGoods.setQuantity(quantity + 1);
                        tdCartGoodsService.save(cartGoods);
                    }
                }
            }
        }

        map.addAttribute("cart_goods_list",
                tdCartGoodsService.updateGoodsInfo(tdCartGoodsService.findByUsername(username)));

        return "/client/cart_goods";
    }

    @RequestMapping(value = "/cart/numberMinus", method = RequestMethod.POST)
    public String cartNumberMinus(Long id, HttpServletRequest req, ModelMap map) {

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            username = req.getSession().getId();
        }

        if (null != id) {
            TdCartGoods cartGoods = tdCartGoodsService.findOne(id);

            if (null != cartGoods)
            {
                // 226 和 1644商品仅限购买一次
                if (!cartGoods.getGoodsId().equals(226L) && !cartGoods.getGoodsId().equals(1644L))
                {
                    if (cartGoods.getUsername().equalsIgnoreCase(username)) {
                        long quantity = cartGoods.getQuantity();
        
                        quantity = quantity > 1 ? quantity - 1 : quantity;
        
                        cartGoods.setQuantity(quantity);
                        tdCartGoodsService.save(cartGoods);
                    }
                }
            }
        }

        map.addAttribute("cart_goods_list",
                tdCartGoodsService.updateGoodsInfo(tdCartGoodsService.findByUsername(username)));

        return "/client/cart_goods";
    }

    @RequestMapping(value = "/cart/del", method = RequestMethod.POST)
    public String cartDel(Long id, HttpServletRequest req, ModelMap map) {

        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            username = req.getSession().getId();
        }

        if (null != id) {
            TdCartGoods cartGoods = tdCartGoodsService.findOne(id);

            if (cartGoods.getUsername().equalsIgnoreCase(username)) {
                tdCartGoodsService.delete(cartGoods);
            }
        }

        map.addAttribute("cart_goods_list",
                tdCartGoodsService.updateGoodsInfo(tdCartGoodsService.findByUsername(username)));

        return "/client/cart_goods";
    }
}
