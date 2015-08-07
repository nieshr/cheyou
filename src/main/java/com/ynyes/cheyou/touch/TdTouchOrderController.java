package com.ynyes.cheyou.touch;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cytm.payment.alipay.PaymentChannelAlipay;
import com.cytm.payment.ceb.PaymentChannelCEB;
import com.ynyes.cheyou.controller.front.AbstractPaytypeController;
import com.ynyes.cheyou.entity.TdCartGoods;
import com.ynyes.cheyou.entity.TdCoupon;
import com.ynyes.cheyou.entity.TdCouponType;
import com.ynyes.cheyou.entity.TdDeliveryType;
import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdOrderGoods;
import com.ynyes.cheyou.entity.TdPayType;
import com.ynyes.cheyou.entity.TdShippingAddress;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.entity.TdUserPoint;
import com.ynyes.cheyou.service.TdCartGoodsService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdCouponService;
import com.ynyes.cheyou.service.TdCouponTypeService;
import com.ynyes.cheyou.service.TdDeliveryTypeService;
import com.ynyes.cheyou.service.TdDiySiteService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdOrderGoodsService;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdPayTypeService;
import com.ynyes.cheyou.service.TdUserPointService;
import com.ynyes.cheyou.service.TdUserService;

/**
 * 订单
 *
 */
@Controller
@RequestMapping("/touch/order")
public class TdTouchOrderController extends AbstractPaytypeController{

    @Autowired
    private TdCartGoodsService tdCartGoodsService;

    @Autowired
    private TdUserService tdUserService;

    @Autowired
    private TdGoodsService tdGoodsService;

    @Autowired
    private TdPayTypeService tdPayTypeService;

    @Autowired
    private TdOrderGoodsService tdOrderGoodsService;

    @Autowired
    private TdOrderService tdOrderService;

    @Autowired
    private TdDeliveryTypeService tdDeliveryTypeService;

    @Autowired
    private TdCommonService tdCommonService;

    @Autowired
    private TdUserPointService tdUserPointService;
    
    @Autowired
    private TdCouponService tdCouponService;
    
    @Autowired
    private TdCouponTypeService tdCouponTypeService;
    
    @Autowired
    private TdDiySiteService tdDiySiteService;
    
    @Autowired
    private PaymentChannelCEB payChannelCEB;

    @Autowired
    private PaymentChannelAlipay payChannelAlipay;

    @RequestMapping(value = "/info")
    public String orderInfo(HttpServletRequest req, HttpServletResponse resp,
            ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/touch/login";
        }

        // 把所有的购物车项转到该登陆用户下
        String sessionId = req.getSession().getId();

        List<TdCartGoods> cartGoodsList = tdCartGoodsService
                .findByUsername(sessionId);

        if (null != cartGoodsList && cartGoodsList.size() > 0) {
            for (TdCartGoods cartGoods : cartGoodsList) {
                cartGoods.setUsername(username);
                cartGoods.setIsLoggedIn(true);
            }
            tdCartGoodsService.save(cartGoodsList);
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (null != user) {
            map.addAttribute("user", user);
        }

        List<TdCartGoods> selectedGoodsList = tdCartGoodsService
                .findByUsernameAndIsSelectedTrue(username);

        Long totalPointLimited = 0L;// 积分限制综总和
        Double totalPrice = 0.0; // 购物总额

        // 积分限制总和 和 购物总额
        if (null != selectedGoodsList) {
            for (TdCartGoods cg : selectedGoodsList) {
                TdGoods goods = tdGoodsService.findOne(cg.getGoodsId());
                if (null != goods && null != goods.getPointLimited()) {
                    totalPointLimited += goods.getPointLimited()
                            * cg.getQuantity();
                    totalPrice += cg.getPrice() * cg.getQuantity();
                }
            }
        }

        // 优惠券
        map.addAttribute("coupon_list",
                tdCouponService.findByUsernameAndIsUseable(username));

        // 积分限额
        map.addAttribute("total_point_limit", totalPointLimited);

        // 线下同盟店
        map.addAttribute("shop_list", tdDiySiteService.findByIsEnableTrue());

        // 支付方式列表
        setPayTypes(map, true, false, req);

        // 配送方式
        map.addAttribute("delivery_type_list",
                tdDeliveryTypeService.findByIsEnableTrue());

        // 选中商品

        map.addAttribute("selected_goods_list", selectedGoodsList);

        tdCommonService.setHeader(map, req);

        return "/touch/order_info";
    }

    @RequestMapping(value = "/goods/{type}")
    public String orderEdit(HttpServletRequest req, HttpServletResponse resp,
            @PathVariable String type, Long gid, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/touch/login";
        }

     // 把所有的购物车项转到该登陆用户下
        List<TdCartGoods> cgList = tdCartGoodsService
                .findByUsernameAndIsSelectedTrue(username);

        if (null != cgList && null != type && null != gid) {
            for (TdCartGoods cg : cgList) {
                if (gid.equals(cg.getGoodsId())) {
                    TdGoods goods = tdGoodsService.findOne(cg.getGoodsId());

                    if (null != goods) {
                        if (type.equalsIgnoreCase("plus")) {
                            // 闪购
                            if (goods.getIsFlashSale()
                                    && goods.getFlashSaleStartTime().before(
                                            new Date())
                                    && goods.getFlashSaleStopTime().after(
                                            new Date())
                                    && cg.getPrice().equals(
                                            goods.getFlashSalePrice())) {
                                if (cg.getQuantity().compareTo(
                                        goods.getFlashSaleLeftNumber()) < 0) {
                                    cg.setQuantity(cg.getQuantity() + 1L);
                                }
                            } else {
                                if (cg.getQuantity().compareTo(
                                        goods.getLeftNumber()) < 0) {
                                    cg.setQuantity(cg.getQuantity() + 1L);
                                }
                            }
                        } else {
                            if (cg.getQuantity().compareTo(1L) > 0) {
                                cg.setQuantity(cg.getQuantity() - 1L);
                            }
                        }

                        tdCartGoodsService.save(cg);
                        break;
                    }
                }
            }
        }
        
        return "redirect:/touch/order/info";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(Long addressId, // 送货地址
            Long shopId, Long payTypeId, // 支付方式ID
            Long deliveryTypeId, // 配送方式ID
            Long pointUse, // 使用积分
            Boolean isNeedInvoice, // 是否需要发票
            String invoiceTitle, // 发票抬头
            String userMessage, // 用户留言
            Long couponId, // 优惠券ID
            String appointmentTime, HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/touch/login";
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (null == user) {
            return "/touch/error_404";
        }

        double payTypeFee = 0.0;
        double deliveryTypeFee = 0.0;
        double pointFee = 0.0;
        double couponFee = 0.0;

        // 收货地址
        TdShippingAddress address = null;

        if (null != addressId) {
            if (null == pointUse) {
                pointUse = 0L;
            }

            List<TdShippingAddress> addressList = user.getShippingAddressList();

            for (TdShippingAddress add : addressList) {
                if (add.getId().equals(addressId)) {
                    address = add;
                    break;
                }
            }
        }

        // 使用粮草
        if (null != user.getTotalPoints()) {
            if (pointUse.compareTo(user.getTotalPoints()) >= 0) {
                pointUse = user.getTotalPoints();
            }
        }

        // 购物车商品
        List<TdCartGoods> cartSelectedGoodsList = tdCartGoodsService
                .findByUsernameAndIsSelectedTrue(username);

        List<TdOrderGoods> orderGoodsList = new ArrayList<TdOrderGoods>();

        // 商品总价
        Double totalGoodsPrice = 0.0;
        
        // 返粮草总额
        Long totalPointReturn = 0L;

        // 购物车商品
        if (null != cartSelectedGoodsList) {
            for (TdCartGoods cartGoods : cartSelectedGoodsList) {
                if (cartGoods.getIsSelected()) {

                    TdGoods goods = tdGoodsService.findOne(cartGoods
                            .getGoodsId());

                    // 不存在该商品或已下架，则跳过
                    if (null == goods || !goods.getIsOnSale()) {
                        continue;
                    }

                    TdOrderGoods orderGoods = new TdOrderGoods();

                    // 商品信息
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setGoodsTitle(goods.getTitle());
                    orderGoods.setGoodsSubTitle(goods.getSubTitle());
                    orderGoods.setGoodsCoverImageUri(goods.getCoverImageUri());

                    // 是否已申请退货
                    orderGoods.setIsReturnApplied(false);

                    // 销售方式
                    orderGoods.setGoodsSaleType(0);

                    long quantity = 0;

                    // 成交价
                    orderGoods.setPrice(goods.getSalePrice());

                    // 数量
                    quantity = Math.min(cartGoods.getQuantity(),
                            goods.getLeftNumber());

                    orderGoods.setQuantity(quantity);

                    // 获得积分
                    if (null != goods.getReturnPoints()) {
                        totalPointReturn += goods.getReturnPoints() * quantity;
                        orderGoods.setPoints(goods.getReturnPoints() * quantity);
                    }

                    // 商品总价
                    totalGoodsPrice += cartGoods.getPrice()
                                    * cartGoods.getQuantity();

                    orderGoodsList.add(orderGoods);

                    // 更新销量
                    Long soldNumber = goods.getSoldNumber();

                    if (null == soldNumber) {
                        soldNumber = 0L;
                    }

                    soldNumber += quantity;
                    goods.setSoldNumber(soldNumber);

                    // 保存商品
                    tdGoodsService.save(goods, username);
                }
            }
        }

        if (null == orderGoodsList || orderGoodsList.size() <= 0) {
            return "/touch/error_404";
        }

        TdOrder tdOrder = new TdOrder();

        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String curStr = sdf.format(current);
        Random random = new Random();

        // 预约时间
        if (null != appointmentTime) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 小写的mm表示的是分钟

            try {
                Date appTime = sdf.parse(appointmentTime);

                tdOrder.setAppointmentTime(appTime);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // 基本信息
        tdOrder.setUsername(username);
        tdOrder.setOrderTime(current);

        // 订单号
        tdOrder.setOrderNumber("P" + curStr
                + leftPad(Integer.toString(random.nextInt(999)), 3, "0"));

        // 安装信息
        if (null != address) {
            // 增加车牌 by zhangji
            tdOrder.setCarCode(address.getReceiverCarcode());
            // 增加车型 by zhangji
            tdOrder.setCarType(address.getReceiverCartype());

            tdOrder.setPostalCode(address.getPostcode());

            tdOrder.setShippingName(address.getReceiverName());
            tdOrder.setShippingPhone(address.getReceiverMobile());
            tdOrder.setShippingAddress(address.getProvince()
                    + address.getCity() + address.getDisctrict()
                    + address.getDetailAddress());
        }
        
        if (null != payTypeId) {
            TdPayType payType = tdPayTypeService.findOne(payTypeId);

            // 支付类型
            payTypeFee = payType.getFee();
            tdOrder.setPayTypeId(payType.getId());
            tdOrder.setPayTypeTitle(payType.getTitle());
            tdOrder.setPayTypeFee(payTypeFee);
            tdOrder.setIsOnlinePay(payType.getIsOnlinePay());
        }

        // 配送方式
        if (null != deliveryTypeId) {
            TdDeliveryType deliveryType = tdDeliveryTypeService
                    .findOne(deliveryTypeId);
            tdOrder.setDeliverTypeId(deliveryType.getId());
            tdOrder.setDeliverTypeTitle(deliveryType.getTitle());
            tdOrder.setDeliverTypeFee(deliveryType.getFee());
            deliveryTypeFee = deliveryType.getFee();
        }

        // 线下同盟店
        if (null != shopId) {
            TdDiySite shop = tdDiySiteService.findOne(shopId);

            if (null != shop) {
                tdOrder.setShopId(shop.getId());
                tdOrder.setShopTitle(shop.getTitle());
            }
        }

        // 使用积分
        tdOrder.setPointUse(pointUse);

        // 用户留言
        tdOrder.setUserRemarkInfo(userMessage);

        // 优惠券
        if (null != couponId) {
            TdCoupon coupon = tdCouponService.findOne(couponId);

            if (null != coupon) {
                TdCouponType couponType = tdCouponTypeService.findOne(coupon
                        .getId());

                couponFee = couponType.getPrice();
                coupon.setIsUsed(true);
                tdCouponService.save(coupon);
            }
        }

        pointFee = pointUse / 1;
        
        // 待付款
        tdOrder.setStatusId(2L); 

        // 总价
        tdOrder.setTotalPrice(totalGoodsPrice + payTypeFee + deliveryTypeFee
                - pointFee - couponFee);
        
        // 需付尾款额
        tdOrder.setTotalLeftPrice(0.0);

        // 发票
        if (null != isNeedInvoice) {
            tdOrder.setIsNeedInvoice(isNeedInvoice);
            tdOrder.setInvoiceTitle(invoiceTitle);
        } else {
            tdOrder.setIsNeedInvoice(false);
        }

        // 订单商品
        tdOrder.setOrderGoodsList(orderGoodsList);
        tdOrder.setTotalGoodsPrice(totalGoodsPrice);

        // 粮草奖励
        tdOrder.setPoints(totalPointReturn);

        // 保存订单商品及订单
        tdOrderGoodsService.save(orderGoodsList);
        tdOrder = tdOrderService.save(tdOrder);

        // 添加积分使用记录
        if (null != user) {
            if (pointUse.compareTo(0L) >= 0 && null != user.getTotalPoints()
                    && user.getTotalPoints().compareTo(pointUse) >= 0) {
                TdUserPoint userPoint = new TdUserPoint();
                userPoint.setDetail("购买商品使用积分抵扣");
                userPoint.setOrderNumber(tdOrder.getOrderNumber());
                userPoint.setPoint(0 - pointUse);
                userPoint.setPointTime(new Date());
                userPoint.setUsername(username);
                userPoint.setTotalPoint(user.getTotalPoints() - pointUse);
                tdUserPointService.save(userPoint);

                user.setTotalPoints(user.getTotalPoints() - pointUse);
                tdUserService.save(user);
            }
        }

        // 删除已生成订单的购物车项
        tdCartGoodsService.delete(cartSelectedGoodsList);

        return "redirect:/touch/order/pay?orderId=" + tdOrder.getId();
    }

    @RequestMapping(value = "/pay")
    public String pay(Long orderId, ModelMap map, HttpServletRequest req) {

        tdCommonService.setHeader(map, req);

        if (null == orderId) {
            return "/touch/error_404";
        }

        map.addAttribute("order", tdOrderService.findOne(orderId));

        return "/touch/order_pay";
    }

    @RequestMapping(value = "/success")
    public String success(Long orderId, ModelMap map, HttpServletRequest req) {

        tdCommonService.setHeader(map, req);

        if (null == orderId) {
            return "/touch/error_404";
        }

        map.addAttribute("order", tdOrderService.findOne(orderId));

        return "/touch/order_success";
    }

    @RequestMapping(value = "/pay/success")
    public String paySuccess(ModelMap map, HttpServletRequest req) {

        tdCommonService.setHeader(map, req);

        return "/touch/order_pay_success";
    }
}
