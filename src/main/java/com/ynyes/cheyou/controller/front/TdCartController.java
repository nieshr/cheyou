package com.ynyes.cheyou.controller.front;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.cheyou.entity.TdCartGoods;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdGoodsCombination;
import com.ynyes.cheyou.service.TdCartGoodsService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdGoodsCombinationService;
import com.ynyes.cheyou.service.TdGoodsService;

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

    /**
     * 加入购物车
     * @param id 商品ID
     * @param quantity 数量 
     * @param zpid 组合ID
     * @param qiang 抢购类型 0：正常销售 >0：促销
     * @param m 是否是触屏 0: 否 1: 是
     * @param req
     * @return
     */
    @RequestMapping(value = "/cart/init")
    public String addCart(Long id, Long quantity, String zpid, Integer qiang, Integer m,
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

        if (null == qiang) {
            qiang = 0;
        }
        
        if (null != id) {
            TdGoods goods = tdGoodsService.findOne(id);

            if (null != goods) {
                
                // 计算抢拍价
                Double qiangPrice = null;
                Date curr = new Date();
                
                if (qiang.equals(1))
                {
                    if (null != goods.getIsFlashSale() 
                            && null != goods.getFlashSaleStartTime()
                            && null != goods.getFlashSaleStopTime()
                            && null != goods.getFlashSalePrice()
                            && goods.getIsFlashSale()
                            && goods.getFlashSaleStopTime().after(curr)
                            && goods.getFlashSaleStartTime().before(curr))
                    {
                        // 剩余毫秒数
                        long ts = goods.getFlashSaleStopTime().getTime() - curr.getTime();
                        // 总共毫秒数
                        long allts = goods.getFlashSaleStopTime().getTime() - goods.getFlashSaleStartTime().getTime();
                        
                        qiangPrice = goods.getFlashSalePrice() * ts / allts;
                        
                        BigDecimal b = new BigDecimal(qiangPrice);
                        
                        qiangPrice = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    else
                    {
                        qiang = 0;
                    }
                }
                
                List<TdCartGoods> oldCartGoodsList = null;
                
                // 购物车是否已有该商品
                oldCartGoodsList = tdCartGoodsService
                                .findByGoodsIdAndQiangAndUsername(id, qiang, username);
                
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
                    cartGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
                    cartGoods.setGoodsTitle(goods.getTitle());
    
                    if (qiang.equals(1)) // 抢购价，只需要设置秒杀价，正常价和团购价在读取购物车时从商品表读
                    {
                        if (goods.getIsFlashSale()
                                && goods.getFlashSaleStopTime().after(new Date())
                                && goods.getFlashSaleStartTime().before(new Date())) 
                        {
                            cartGoods.setPrice(qiangPrice);
                        }
                    }
                    
                    cartGoods.setQiang(qiang);
    
                    cartGoods.setQuantity(quantity);
                    
    
                    tdCartGoodsService.save(cartGoods);
                }
            }
            
            if (null != zpid && !zpid.isEmpty())
            {
                String[] zpidArray = zpid.split(",");
                
                for (String idStr : zpidArray)
                {
                    if (!idStr.isEmpty())
                    {
                        Long zid = Long.parseLong(idStr);
                        
                        if (null == zid)
                        {
                            continue;
                        }
                        
                        TdGoodsCombination combGoods = tdGoodsCombinationService.findOne(zid);
                        
                        if (null == combGoods)
                        {
                            continue;
                        }

                        // 查找已有商品
                        TdCartGoods oldCartGoods = tdCartGoodsService
                                .findTopByGoodsIdAndQiangAndUsername(combGoods.getGoodsId(),
                                                                    0,
                                                                    username);

                        // 购物车已有该赠品，数量+1
                        if (null != oldCartGoods) 
                        {
                            oldCartGoods.setQuantity(oldCartGoods.getQuantity().longValue() + 1L);
                            tdCartGoodsService.save(oldCartGoods);
                        }
                        // 将赠品加进购物车
                        else
                        {
                            TdCartGoods cartGoods = new TdCartGoods();
    
                            cartGoods.setIsLoggedIn(isLoggedIn);
                            cartGoods.setUsername(username);
                            cartGoods.setIsSelected(true);
                            cartGoods.setGoodsId(combGoods.getGoodsId());
                            cartGoods.setGoodsCoverImageUri(combGoods.getCoverImageUri());
                            cartGoods.setGoodsTitle(combGoods.getGoodsTitle());
                            cartGoods.setPrice(combGoods.getCurrentPrice());
                            cartGoods.setQiang(1); // 设置为1，将不会进行价格的刷新
                            cartGoods.setQuantity(1L);
    
                            tdCartGoodsService.save(cartGoods);
                        }
                    }
                }
            }
        }

        return "redirect:/cart/add?id=" + id + "&m=" + m;
    }

    @RequestMapping(value = "/cart/add")
    public String cartInit(Long id, Integer m, Device device, HttpServletRequest req, ModelMap map) {
        tdCommonService.setHeader(map, req);
        
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

        if (null == username) {
            username = req.getSession().getId();
        } else {
            // 已登录用户的购物车
            List<TdCartGoods> cartUserGoodsList = tdCartGoodsService
                    .findByUsername(username);

            // 将未登录用户的购物车加入已登录用户购物车中
            for (TdCartGoods cg : cartSessionGoodsList) {
                cg.setUsername(username);
                cartUserGoodsList.add(cg);
            }

            cartUserGoodsList = tdCartGoodsService.save(cartUserGoodsList);

            for (TdCartGoods cg1 : cartUserGoodsList) {
                
                List<TdCartGoods> findList = tdCartGoodsService
                        .findByGoodsIdAndQiangAndUsername(cg1.getGoodsId(), cg1.getQiang(), username);

                if (null != findList && findList.size() > 1) {
                    tdCartGoodsService.delete(findList.subList(1,
                            findList.size()));
                }
            }
        }

        List<TdCartGoods> resList = tdCartGoodsService.findByUsername(username);
        
        
        map.addAttribute("cart_goods_list", tdCartGoodsService.updateGoodsInfo(resList));

        tdCommonService.setHeader(map, req);

        if (null == resList || resList.size() == 0) {
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

            if (cartGoods.getUsername().equalsIgnoreCase(username)) {
                long quantity = cartGoods.getQuantity();
                cartGoods.setQuantity(quantity + 1);
                tdCartGoodsService.save(cartGoods);
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

            if (cartGoods.getUsername().equalsIgnoreCase(username)) {
                long quantity = cartGoods.getQuantity();

                quantity = quantity > 1 ? quantity - 1 : quantity;

                cartGoods.setQuantity(quantity);
                tdCartGoodsService.save(cartGoods);
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
