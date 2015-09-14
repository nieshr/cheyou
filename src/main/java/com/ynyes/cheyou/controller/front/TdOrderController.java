package com.ynyes.cheyou.controller.front;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csii.payment.client.core.CebMerchantSignVerify;
import com.cytm.payment.alipay.AlipayConfig;
import com.cytm.payment.alipay.Constants;
import com.cytm.payment.alipay.PaymentChannelAlipay;
import com.cytm.payment.alipay.core.AlipayNotify;
import com.cytm.payment.ceb.CEBPayConfig;
import com.cytm.payment.ceb.PaymentChannelCEB;
import com.ibm.icu.util.Calendar;
import com.tencent.common.Configure;
import com.tencent.common.MD5;
import com.tencent.common.RandomStringGenerator;
import com.tencent.protocol.pay_protocol.UnifiedOrderReqData;
import com.tencent.service.UnifiedOrderService;
import com.ynyes.cheyou.entity.TdCartGoods;
import com.ynyes.cheyou.entity.TdCoupon;
import com.ynyes.cheyou.entity.TdCouponType;
import com.ynyes.cheyou.entity.TdDeliveryType;
import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdGoodsCombination;
import com.ynyes.cheyou.entity.TdGoodsDto;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdOrderGoods;
import com.ynyes.cheyou.entity.TdPayRecord;
import com.ynyes.cheyou.entity.TdPayType;
import com.ynyes.cheyou.entity.TdProductCategory;
import com.ynyes.cheyou.entity.TdShippingAddress;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.entity.TdUserPoint;
import com.ynyes.cheyou.service.TdCartGoodsService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdCouponService;
import com.ynyes.cheyou.service.TdCouponTypeService;
import com.ynyes.cheyou.service.TdDeliveryTypeService;
import com.ynyes.cheyou.service.TdDiySiteService;
import com.ynyes.cheyou.service.TdGoodsCombinationService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdOrderGoodsService;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdPayRecordService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.service.TdUserPointService;
import com.ynyes.cheyou.service.TdUserService;
import com.ynyes.cheyou.util.CommonService;
import com.ynyes.cheyou.util.QRCodeUtils;
import com.ynyes.cheyou.util.SMSUtil;

/**
 * 订单
 *
 */
@Controller
@RequestMapping("/order")
public class TdOrderController extends AbstractPaytypeController {

    private static final String PAYMENT_ALI = "ALI";
    private static final String PAYMENT_WX = "WX";

    @Autowired
    private TdCartGoodsService tdCartGoodsService;

    @Autowired
    private TdUserService tdUserService;

    @Autowired
    private TdGoodsService tdGoodsService;

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
    private TdPayRecordService payRecordService;

    @Autowired
    private TdGoodsCombinationService tdGoodsCombinationService;

    @Autowired
    private PaymentChannelCEB payChannelCEB;

    @Autowired
    private PaymentChannelAlipay payChannelAlipay;
    
    @Autowired
    private TdProductCategoryService tdProductCategoryService;
    /**
     * 立即购买
     * 
     * @param type
     *            购买类型 (comb: 组合购买)
     * @param gid
     *            商品ID
     * @param zhid
     *            组合ID，多个组合商品以","分开
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/buy/{type}")
    public String orderBuy(@PathVariable String type, Long gid, String zhid,
            HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        if (null == type || null == gid) {
            return "/client/error_404";
        }

        List<TdGoodsDto> tdGoodsList = new ArrayList<TdGoodsDto>();

        // 组合购买
        if (type.equalsIgnoreCase("comb")) {
            // 购买商品
            TdGoods goods = tdGoodsService.findOne(gid);

            if (null == goods) {
                return "/client/error_404";
            }

            // 优惠券
//            map.addAttribute("coupon_list",
//                    tdCouponService.findByUsernameAndIsUseable(username));
            
            
            // 积分限额
            TdUser tdUser = tdUserService.findByUsername(username);
            if (null != tdUser ) {
				if (null != tdUser.getTotalPoints() ) {
					if (goods.getPointLimited() > tdUser.getTotalPoints()) {
						map.addAttribute("total_point_limit", tdUser.getTotalPoints());
					}
					else{
						map.addAttribute("total_point_limit", goods.getPointLimited());
					}
				}
			}
            

            TdGoodsDto buyGoods = new TdGoodsDto();

            buyGoods.setGoodsId(goods.getId());
            buyGoods.setGoodsTitle(goods.getTitle());
            buyGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
            buyGoods.setPrice(goods.getSalePrice());
            buyGoods.setQuantity(1L);
            buyGoods.setSaleId(0);

            tdGoodsList.add(buyGoods);

            // 添加组合商品
            if (null != zhid && !zhid.isEmpty()) {
              
                String[] zhidArray = zhid.split(",");

                for (String idStr : zhidArray) {
                    if (!idStr.isEmpty()) {
                        Long zid = Long.parseLong(idStr);
    
                        if (null == zid) {
                            continue;
                        }
    
                        TdGoodsCombination combGoods = tdGoodsCombinationService
                                .findOne(zid);
    
                        if (null == combGoods) {
                            continue;
                        }
    
                        TdGoodsDto buyZhGoods = new TdGoodsDto();
    
                        buyZhGoods.setGoodsId(combGoods.getGoodsId());
                        buyZhGoods.setGoodsTitle(combGoods.getGoodsTitle());
                        buyZhGoods.setGoodsCoverImageUri(combGoods
                                .getCoverImageUri());
                        buyZhGoods.setPrice(combGoods.getCurrentPrice());
                        buyZhGoods.setQuantity(1L);
                        buyZhGoods.setSaleId(1);
    
                        tdGoodsList.add(buyZhGoods);
                    }
                }
            }
        }
        // 抢购
        else if (type.equalsIgnoreCase("qiang")) {
            // 购买商品
            TdGoods goods = tdGoodsService.findOne(gid);

            // 检查是否在秒杀
            if (null == goods || null == goods.getIsOnSale()
                    || !goods.getIsOnSale()
                    || !tdGoodsService.isFlashSaleTrue(goods)) {
                return "/client/error_404";
            }

            TdGoodsDto buyGoods = new TdGoodsDto();

            buyGoods.setGoodsId(goods.getId());
            buyGoods.setGoodsTitle(goods.getTitle());
            buyGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
            Double flashSalePrice = tdGoodsService.getFlashPrice(goods);
            
            if (null == flashSalePrice)
            {
                return "/client/error_404";
            }
            
            buyGoods.setPrice(flashSalePrice);
            buyGoods.setQuantity(1L);
            buyGoods.setSaleId(2);

            tdGoodsList.add(buyGoods);
        }
        // 十人团
        else if (type.equalsIgnoreCase("tentuan")) {
            // 购买商品
            TdGoods goods = tdGoodsService.findOne(gid);

            // 检查是否在十人团
            if (null == goods || null == goods.getIsOnSale()
                    || !goods.getIsOnSale()
                    || !tdGoodsService.isGroupSaleTrue(goods)) {
                return "/client/error_404";
            }

            TdGoodsDto buyGoods = new TdGoodsDto();

            buyGoods.setGoodsId(goods.getId());
            buyGoods.setGoodsTitle(goods.getTitle());
            buyGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
            buyGoods.setPrice(goods.getGroupSalePrice());
            buyGoods.setQuantity(1L);
            buyGoods.setSaleId(3);

            tdGoodsList.add(buyGoods);
        }
        // 百人团
        else if (type.equalsIgnoreCase("baituan")) {
            // 购买商品
            TdGoods goods = tdGoodsService.findOne(gid);

            // 检查是否在十人团
            if (null == goods || null == goods.getIsOnSale()
                    || !goods.getIsOnSale()
                    || !tdGoodsService.isGroupSaleHundredTrue(goods)) {
                return "/client/error_404";
            }

            TdGoodsDto buyGoods = new TdGoodsDto();

            buyGoods.setGoodsId(goods.getId());
            buyGoods.setGoodsTitle(goods.getTitle());
            buyGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
            buyGoods.setPrice(goods.getGroupSalePrePayPrice());
            buyGoods.setQuantity(1L);
            buyGoods.setSaleId(4);

            tdGoodsList.add(buyGoods);
        }

        // 购买商品表
        map.addAttribute("buy_goods_list", tdGoodsList);

        // 将购买的商品保存到session
        req.getSession().setAttribute("buy_goods_list", tdGoodsList);
        // 购买类型
        req.getSession().setAttribute("buyType", type);

        // 线下同盟店
        map.addAttribute("shop_list", tdDiySiteService.findByIsEnableTrue());

        // 支付方式列表
        setPayTypes(map, true, false, req);

        // 选中商品
        // map.addAttribute("selected_goods_list", selectedGoodsList);

        tdCommonService.setHeader(map, req);

        if (type.equalsIgnoreCase("comb"))
        {
            return "/client/order_buy_zh";
        }
        else if (type.equalsIgnoreCase("qiang"))
        {
            return "/client/order_buy_qiang";
        }
        else if (type.equalsIgnoreCase("baituan"))
        {
            return "/client/order_buy_bt";
        }
        else
        {
            return "/client/order_buy_tt";
        }
    }

    /**
     * 
     * @param addressId
     * @param shopId
     * @param payTypeId
     * @param deliveryTypeId
     * @param isNeedInvoice
     * @param invoiceTitle
     * @param userMessage
     * @param appointmentTime
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/buysubmit", method = RequestMethod.POST)
    public String buySubmit(Long addressId, // 送货地址
            Long shopId, Long payTypeId, // 支付方式ID
            Long couponId, Long deliveryTypeId, // 配送方式ID
            Long pointUse, // 使用粮草
            Boolean isNeedInvoice, // 是否需要发票
            String invoiceTitle, // 发票抬头
            String userMessage, // 用户留言
            String appointmentTime, HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (null == user) {
            return "/client/error_404";
        }

        String buyType = (String) req.getSession().getAttribute("buyType");

        if (null == buyType) {
            return "/client/error_404";
        }

        double payTypeFee = 0.0;
        double deliveryTypeFee = 0.0;
        double pointFee = 0.0;
        double couponFee = 0.0;

        // 订单商品
        List<TdOrderGoods> orderGoodsList = new ArrayList<TdOrderGoods>();

        // 商品总价
        Double totalGoodsPrice = 0.0;

        // 商品总尾款
        Double totalLeftPrice = 0.0;

        // 返粮草总额
        Long totalPointReturn = 0L;

        // 组合购买
        if (buyType.equalsIgnoreCase("comb")) {
            @SuppressWarnings("unchecked")
            List<TdGoodsDto> tdGoodsList = (List<TdGoodsDto>) req.getSession()
                    .getAttribute("buy_goods_list");

            if (null != tdGoodsList && tdGoodsList.size() > 0) {
                for (TdGoodsDto buyGoods : tdGoodsList) {
                    // 原商品
                    TdGoods goods = tdGoodsService.findOne(buyGoods
                            .getGoodsId());

                    // 不存在该商品或已下架或已售罄，则跳过
                    if (null == goods || !goods.getIsOnSale()
                            || null == goods.getLeftNumber()
                            || goods.getLeftNumber().compareTo(1L) < 0) {
                        return "/client/error_404";
                    }

                    TdOrderGoods orderGoods = new TdOrderGoods();

                    // 商品信息
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setGoodsTitle(goods.getTitle());
                    orderGoods.setGoodsSubTitle(goods.getSubTitle());
                    orderGoods.setGoodsCoverImageUri(goods.getCoverImageUri());

                    // 是否已申请退货
                    orderGoods.setIsReturnApplied(false);

                    // 正常销售
                    if (0 == buyGoods.getSaleId()) {
                        orderGoods.setPrice(goods.getSalePrice());

                        // 销售方式
                        orderGoods.setGoodsSaleType(0);

                        // 商品总价
                        totalGoodsPrice += goods.getSalePrice();

                    } else { // 组合销售
                        orderGoods.setPrice(buyGoods.getPrice());

                        // 销售方式
                        orderGoods.setGoodsSaleType(1);

                        // 商品总价
                        totalGoodsPrice += buyGoods.getPrice();
                    }

                    // 数量
                    orderGoods.setQuantity(1L);

                    // 获得积分
                    if (null != goods.getReturnPoints()) {
                        totalPointReturn += goods.getReturnPoints();
                        orderGoods.setPoints(goods.getReturnPoints());
                    }

                    orderGoodsList.add(orderGoods);

                    // 更新销量
                    Long soldNumber = goods.getSoldNumber();
                    Long leftNumber = goods.getLeftNumber();

                    if (null == soldNumber) {
                        soldNumber = 0L;
                    }

                    soldNumber += 1L;
                    goods.setSoldNumber(soldNumber);
                    goods.setLeftNumber(leftNumber - 1);

                    // 保存商品
                    tdGoodsService.save(goods, username);
                }
            }

            // 使用粮草
            if (null != user.getTotalPoints()) {
                if (pointUse.compareTo(user.getTotalPoints()) >= 0) {
                    pointUse = user.getTotalPoints();
                }
            }
        }
        // 秒杀
        else if (buyType.equalsIgnoreCase("qiang")) {
            @SuppressWarnings("unchecked")
            List<TdGoodsDto> tdGoodsList = (List<TdGoodsDto>) req.getSession()
                    .getAttribute("buy_goods_list");

            if (null != tdGoodsList && tdGoodsList.size() > 0) {
                for (TdGoodsDto buyGoods : tdGoodsList) {
                    // 原商品
                    TdGoods goods = tdGoodsService.findOne(buyGoods
                            .getGoodsId());

                    // 不存在该商品或已下架或已不在秒杀，则跳过
                    if (null == goods || !goods.getIsOnSale()
                            || !tdGoodsService.isFlashSaleTrue(goods)) {
                        return "/client/error_404";
                    }

                    TdOrderGoods orderGoods = new TdOrderGoods();

                    // 商品信息
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setGoodsTitle(goods.getTitle());
                    orderGoods.setGoodsSubTitle(goods.getSubTitle());
                    orderGoods.setGoodsCoverImageUri(goods.getCoverImageUri());

                    // 是否已申请退货
                    orderGoods.setIsReturnApplied(false);

                    // 抢购销售
                    Double flashSalePrice = tdGoodsService.getFlashPrice(goods);
                    
                    if (null == flashSalePrice)
                    {
                        return "/client/error_404";
                    }
                    
                    orderGoods.setPrice(flashSalePrice);

                    // 抢购
                    orderGoods.setGoodsSaleType(2);

                    // 商品总价
                    totalGoodsPrice += flashSalePrice;

                    // 数量
                    orderGoods.setQuantity(1L);

                    orderGoodsList.add(orderGoods);

                    // 更新销量
                    Long flashSoldNumber = goods.getFlashSaleSoldNumber();
                    Long flashLeftNumber = goods.getFlashSaleLeftNumber();

                    if (null == flashSoldNumber) {
                        flashSoldNumber = 0L;
                    }

                    flashSoldNumber += 1L;
                    goods.setFlashSaleSoldNumber(flashSoldNumber);
                    goods.setFlashSaleLeftNumber(flashLeftNumber - 1);
                    
                    //保存成交价
                    goods.setFlashSaleTransactionPrice(flashSalePrice);
                    // 保存商品
                    tdGoodsService.save(goods, username);
                }
            }
        }
        // 十人团
        else if (buyType.equalsIgnoreCase("tentuan")) {
            @SuppressWarnings("unchecked")
            List<TdGoodsDto> tdGoodsList = (List<TdGoodsDto>) req.getSession()
                    .getAttribute("buy_goods_list");

            if (null != tdGoodsList && tdGoodsList.size() > 0) {
                for (TdGoodsDto buyGoods : tdGoodsList) {
                    // 原商品
                    TdGoods goods = tdGoodsService.findOne(buyGoods
                            .getGoodsId());

                    // 不存在该商品或已下架或已不在秒杀，则跳过
                    if (null == goods || !goods.getIsOnSale()
                            || !tdGoodsService.isGroupSaleTrue(goods)) {
                        return "/client/error_404";
                    }

                    TdOrderGoods orderGoods = new TdOrderGoods();

                    // 商品信息
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setGoodsTitle(goods.getTitle());
                    orderGoods.setGoodsSubTitle(goods.getSubTitle());
                    orderGoods.setGoodsCoverImageUri(goods.getCoverImageUri());

                    // 是否已申请退货
                    orderGoods.setIsReturnApplied(false);

                    // 十人团预付价格
                    orderGoods.setPrice(goods.getGroupSalePrice());

                    // 十人团
                    orderGoods.setGoodsSaleType(3);

                    // 商品总价
                    totalGoodsPrice += goods.getGroupSalePrice();

                    // 尾款
                    totalLeftPrice = goods.getGroupSaleTenPrice()
                            - goods.getGroupSalePrice();
                    
                    if (totalLeftPrice < 0) {
                        totalLeftPrice = 0.0;
                    }

                    // 数量
                    orderGoods.setQuantity(1L);

                    orderGoodsList.add(orderGoods);

                    // 更新销量
                    Long groupSoldNumber = goods.getGroupSaleSoldNumber();
                    Long groupLeftNumber = goods.getGroupSaleLeftNumber();

                    if (null == groupSoldNumber) {
                        groupSoldNumber = 0L;
                    }

                    groupSoldNumber += 1L;
                    goods.setGroupSaleSoldNumber(groupSoldNumber);
                    goods.setGroupSaleLeftNumber(groupLeftNumber - 1);

                    // 保存商品
                    tdGoodsService.save(goods, username);
                }
            }
        }
        // 百人团
        else if (buyType.equalsIgnoreCase("baituan")) {
            @SuppressWarnings("unchecked")
            List<TdGoodsDto> tdGoodsList = (List<TdGoodsDto>) req.getSession()
                    .getAttribute("buy_goods_list");

            if (null != tdGoodsList && tdGoodsList.size() > 0) {
                for (TdGoodsDto buyGoods : tdGoodsList) {
                    // 原商品
                    TdGoods goods = tdGoodsService.findOne(buyGoods
                            .getGoodsId());

                    // 不存在该商品或已下架或已不在秒杀，则跳过
                    if (null == goods || !goods.getIsOnSale()
                            || !tdGoodsService.isGroupSaleTrue(goods)) {
                        return "/client/error_404";
                    }

                    TdOrderGoods orderGoods = new TdOrderGoods();

                    // 商品信息
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setGoodsTitle(goods.getTitle());
                    orderGoods.setGoodsSubTitle(goods.getSubTitle());
                    orderGoods.setGoodsCoverImageUri(goods.getCoverImageUri());

                    // 是否已申请退货
                    orderGoods.setIsReturnApplied(false);

                    // 百人团预付价格
                    orderGoods.setPrice(goods.getGroupSalePrePayPrice());

                    // 百人团
                    orderGoods.setGoodsSaleType(4);

                    // 商品总价
                    totalGoodsPrice += goods.getGroupSalePrePayPrice();

                    // 尾款
                    totalLeftPrice = goods.getGroupSaleHundredPrice()
                            - goods.getGroupSalePrePayPrice();

                    // 数量
                    orderGoods.setQuantity(1L);

                    orderGoodsList.add(orderGoods);

                    // 更新销量
                    Long groupSoldNumber = goods.getGroupSaleHundredSoldNumber();
                    Long groupLeftNumber = goods.getGroupSaleHundredLeftNumber();

                    if (null == groupSoldNumber) {
                        groupSoldNumber = 0L;
                    }

                    groupSoldNumber += 1L;
                    goods.setGroupSaleHundredSoldNumber(groupSoldNumber);
                    goods.setGroupSaleHundredLeftNumber(groupLeftNumber - 1);

                    // 保存商品
                    tdGoodsService.save(goods, username);
                }
            }
        }

        if (null == orderGoodsList || orderGoodsList.size() <= 0) {
            return "/client/error_404";
        }

        // 安装信息
        TdShippingAddress address = null;

        if (null != addressId) {

            List<TdShippingAddress> addressList = user.getShippingAddressList();

            for (TdShippingAddress add : addressList) {
                if (add.getId().equals(addressId)) {
                    address = add;
                    break;
                }
            }
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
        tdOrder.setUsername(user.getUsername());
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
                
                // 用户归属
                if (null != user.getUpperDiySiteId())
                {
                    user.setUpperDiySiteId(shop.getId());
                    user = tdUserService.save(user);
                }
            }
        }
        
        // 用户留言
        tdOrder.setUserRemarkInfo(userMessage);

        if (buyType.equalsIgnoreCase("comb"))
        {
            // 使用积分
            tdOrder.setPointUse(pointUse);

            // 优惠券
            if (null != couponId) {
                TdCoupon coupon = tdCouponService.findOne(couponId);

                if (null != coupon) {
                    TdCouponType couponType = tdCouponTypeService
                            .findOne(coupon.getId());

                    couponFee = couponType.getPrice();
                    coupon.setIsUsed(true);
                    tdCouponService.save(coupon);
                }
            }

            // 粮草奖励
            tdOrder.setPoints(totalPointReturn);

            pointFee = pointUse / 1;

            // 总价
            tdOrder.setTotalPrice(totalGoodsPrice + payTypeFee
                    + deliveryTypeFee - pointFee - couponFee);
            
            // 添加积分使用记录
            if (null != user) {
                if (null == user.getTotalPoints())
                {
                    user.setTotalPoints(0L);
                    
                    user = tdUserService.save(user);
                }
                
                if (pointUse.compareTo(0L) >= 0
                        && null != user.getTotalPoints()
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
        }
        else
        {
         // 总价
            tdOrder.setTotalPrice(totalGoodsPrice + payTypeFee + deliveryTypeFee);
        }

        // 待付款
        tdOrder.setStatusId(2L);

        // 需付尾款额
        tdOrder.setTotalLeftPrice(totalLeftPrice);

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

        // 保存订单商品及订单
        tdOrderGoodsService.save(orderGoodsList);
        /**
		 * @author lc
		 * @注释：订单类型设置 
		 */
        tdOrder.setTypeId(0L);
        for(TdOrderGoods tdOrderGoods : orderGoodsList){
        	if (tdOrderGoods.getGoodsSaleType() == 1) {
				tdOrder.setTypeId(2L);
			}       	
        }
        //抢购 团购 都只有一个商品
        for(TdOrderGoods tdOrderGoods : orderGoodsList){
        	if (tdOrderGoods.getGoodsSaleType() == 2) {
				tdOrder.setTypeId(3L);
			}
        	else if (tdOrderGoods.getGoodsSaleType() == 3) {
        		tdOrder.setTypeId(4L);
			}
        	else if (tdOrderGoods.getGoodsSaleType() == 4) {
        		tdOrder.setTypeId(5L);
			}
        }
        
        tdOrder = tdOrderService.save(tdOrder);

        // if (tdOrder.getIsOnlinePay()) {
        return "redirect:/order/pay?orderId=" + tdOrder.getId();
        // }

        // return "redirect:/order/success?orderId=" + tdOrder.getId();
    }

    @RequestMapping(value = "/info")
    public String orderInfo(HttpServletRequest req, HttpServletResponse resp,
            ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
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
        
        //查询购物车的所有种类
        List<String> productIds = new ArrayList<>();
        for (TdCartGoods cg : selectedGoodsList){
        	TdGoods goods = tdGoodsService.findOne(cg.getGoodsId());
        	if (productIds.isEmpty()) {        		
				productIds.add(goods.getCategoryIdTree().split(",")[0]);//根类别
			}else{
				if (!productIds.contains(goods.getCategoryIdTree().split(",")[0])) {
					productIds.add(goods.getCategoryIdTree().split(",")[0]);
				}
			}
        }
        /**
		 * @author lc
		 * @注释：优惠券 TODO: 满减券， 单品类券，普通券查找
		 */
        //如果有不同种类的商品则不能使用优惠券
        if (productIds.size() < 2) {
        	List<TdCoupon> userCoupons = null;
            if (null != user.getMobile()) {
            	userCoupons = tdCouponService.findByMobileAndIsUseable(user.getMobile());//根据账号查询所有优惠券
    		}
             
            if (null != userCoupons) {
            	List<TdCoupon> userCouponList = new ArrayList<>(); //可用券
            	TdCouponType couponType = null;
            	for(int i = 0; i < userCoupons.size(); i++){
            		couponType = tdCouponTypeService.findOne(userCoupons.get(i).getTypeId());
            		if (null != couponType && !couponType.getTitle().equals("免费洗车券") && !couponType.getTitle().equals("免费打蜡券")) {
    					if (couponType.getCategoryId().equals(1L)) {
    						TdProductCategory tpc = tdProductCategoryService.findOne(couponType.getProductTypeId());
    						List<String> templist = new ArrayList<>();
    						for(String cid : tpc.getParentTree().split(",")){
    							templist.add(cid);
    						}   								
    						 //判断购物总价>满购券使用金额
    				        if (totalPrice > couponType.getCanUsePrice() && templist.contains(productIds.get(0))) {
    				        	userCouponList.add(userCoupons.get(i));
    				        }
    					}
    					else if (couponType.getCategoryId().equals(0L)) {
    						userCouponList.add(userCoupons.get(i));
    					}
    					else if (couponType.getCategoryId().equals(2L)) {
    						if (totalPrice > couponType.getCanUsePrice()) {
    							userCouponList.add(userCoupons.get(i));
    						}
    					}
    				}
            	}
            	 map.addAttribute("coupon_list",userCouponList);
    		}
		}
               
                  
//         /**
//         * 判断能使用的优惠券
//         * 1，满购券金额是否达到要求
//         * 2，单品类券是否包含有订单商品能使用的
//         * 3，普通券
//         * @author libiao
//         */
//         List<TdCoupon> userCouponList =null;
//         if(null != userCoupons)
//         {
//         if(userCoupons.size()>0)
//         {
//         for (int i = 0; i < userCoupons.size(); i++)
//         {
//         //查看优惠券
//         TdCouponType couponType =
//         tdCouponTypeService.findOne(userCoupons.get(i).getTypeId());
//         //判断为满购券
//         if(couponType.getCategoryId().equals(1L))
//         {
//         //判断购物总价>满购券使用金额
//         if(totalPrice>couponType.getCanUsePrice())
//         {
//         userCouponList.add(userCoupons.get(i));
//         }
//         }
//         //判断为普通券
//         if(couponType.getCategoryId().equals(0L))
//         {
//         userCouponList.add(userCoupons.get(i));
//         }
//         //判断为单品类券
//         if(couponType.getCategoryId().equals(2L))
//         {
//        
//         }
//         /**
//         * 取出购物车所有商品Id
//         * @author libiao
//         */
//         List<Long> goodIds =new ArrayList<>();
//         for (int j = 0; j < selectedGoodsList.size(); i++)
//         {
//         Long goodsId = selectedGoodsList.get(j).getGoodsId();
//         goodIds.add(goodsId);
//         }
//         /**
//         * 根据取出商品Id查找其所属分类Id
//         * @author libiao
//         */
//         List<Long> productIds =new ArrayList<>();
//        
//        
//         for (int y = 0; i < goodIds.size(); i++) {
//         Long productId =
//         tdGoodsService.findProductIdById(goodIds.get(y)).getProductId();
//         if(!productIds.contains(productId)){
//         productIds.add(productId);
//         }
//         }
//          /**
//          * 查找所有能用的单品类券
//          */
//          for (int j = 0; j < productIds.size(); j++) {
//          List<TdCouponType> counponTypes =
//         tdCouponTypeService.findByCategoryId(productIds.get(j));
//          }
//        
//         }
//        
//         }
//         }
        
        // 积分限额
        if (null != user.getTotalPoints()) {
			if (totalPointLimited > user.getTotalPoints()) {
				map.addAttribute("total_point_limit", user.getTotalPoints());
			}
			else{
				map.addAttribute("total_point_limit", totalPointLimited);
			}
		}
        
        // 线下同盟店
        map.addAttribute("shop_list", tdDiySiteService.findByIsEnableTrue());

        // 支付方式列表
        setPayTypes(map, true, false, req);

        // 配送方式
        map.addAttribute("delivery_type_list",
                tdDeliveryTypeService.findByIsEnableTrue());

        // 选中商品
        map.addAttribute("buy_goods_list", selectedGoodsList);

        tdCommonService.setHeader(map, req);

        return "/client/order_info";
    }

    /**
     * 购物车数量加减
     * 
     * @param req
     * @param resp
     * @param type
     *            加减标志位
     * @param gid
     *            商品ID
     * @param map
     * @return
     */
    @RequestMapping(value = "/goods/{type}")
    public String orderEdit(HttpServletRequest req, HttpServletResponse resp,
            @PathVariable String type, Long gid, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
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
        //
        // List<TdCartGoods> selectedGoodsList =
        // tdCartGoodsService.findByUsernameAndIsSelectedTrue(username);
        // map.addAttribute("selected_goods_list", selectedGoodsList);

        return "redirect:/order/info";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(Long addressId, // 送货地址
            Long shopId, Long payTypeId, // 支付方式ID
            Long deliveryTypeId, // 配送方式ID
            Long pointUse, // 使用粮草
            Boolean isNeedInvoice, // 是否需要发票
            String invoiceTitle, // 发票抬头
            String userMessage, // 用户留言
            Long couponId, // 优惠券ID
            String appointmentTime, HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (null == user) {
            return "/client/error_404";
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
                        orderGoods
                                .setPoints(goods.getReturnPoints() * quantity);
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
                    
                    /**
					 * @author lc
					 * @注释：更新库存
					 */
                    Long leftNumber = goods.getLeftNumber();
                    if (leftNumber >= quantity) {
                    	leftNumber = leftNumber - quantity;
					}               
                    goods.setLeftNumber(leftNumber);
                    
                    // 保存商品
                    tdGoodsService.save(goods, username);
                }
            }
        }

        if (null == orderGoodsList || orderGoodsList.size() <= 0) {
            return "/client/error_404";
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
        tdOrder.setUsername(user.getUsername());
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
                
                // 用户归属
                if (null != user.getUpperDiySiteId())
                {
                    user.setUpperDiySiteId(shop.getId());
                    user = tdUserService.save(user);
                }
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
                        .getTypeId());

                couponFee = couponType.getPrice();
                coupon.setIsUsed(true);
                tdCouponService.save(coupon);
                tdOrder.setCouponUse( couponFee);
                tdOrder.setCouponTitle(coupon.getTypeTitle());
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
        /**
		 * @author lc
		 * @注释：设置订单类型
		 */
        tdOrder.setTypeId(1L);
        tdOrder = tdOrderService.save(tdOrder);

        // 添加积分使用记录
        if (null != user) {
            if (null == user.getTotalPoints())
            {
                user.setTotalPoints(0L);
                
                user = tdUserService.save(user);
            }
            
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

        // if (tdOrder.getIsOnlinePay()) {
        return "redirect:/order/pay?orderId=" + tdOrder.getId();
        // }

        // return "redirect:/order/success?orderId=" + tdOrder.getId();
    }

    @RequestMapping(value = "/success")
    public String success(Long orderId, ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == orderId) {
            return "/client/error_404";
        }

        map.addAttribute("order", tdOrderService.findOne(orderId));

        return "/client/order_success";
    }

    /**
     * 支付选择页面
     * 
     * @param orderId
     *            订单ID
     * @param map
     * @param req
     * @return
     */
    @RequestMapping(value = "/pay")
    public String pay(Long orderId, ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == orderId) {
            return "/client/error_404";
        }

        map.addAttribute("order", tdOrderService.findOne(orderId));

        return "/client/order_pay";
    }

    /**
     * 支付
     * 
     * @param orderId
     * @param map
     * @param req
     * @return
     */
    @RequestMapping(value = "/dopay/{orderId}")
    public String payOrder(@PathVariable Long orderId, ModelMap map,
            HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == orderId) {
            return "/client/error_404";
        }
        
        TdOrder order = tdOrderService.findOne(orderId);
        
        if (null == order) {
            return "/client/error_404";
        }
        
        //根据订单类型来判断支付时间是否过期
        if (order.getTypeId().equals(3L)) { // 抢拍  订单提交后20分钟内
            Date cur = new Date();
        	long temp = cur.getTime() - order.getOrderTime().getTime();
//        	System.out.println(temp);
            if(temp > 1000*60*20){
            	 return "/client/overtime";
            }   
		}
        else if (order.getTypeId().equals(4L) || order.getTypeId().equals(5L)) { //团购  预付是订单提交后12小时内，尾款支付也是12小时
        	Date cur = new Date();
        	long temp = cur.getTime() - order.getOrderTime().getTime();
            if(temp > 1000*3600*12){
            	 return "/client/overtime";
            }   
		}
        else { //普通  订单提交后24小时内
        	Date cur = new Date();
        	long temp = cur.getTime() - order.getOrderTime().getTime();
            if(temp > 1000*3600*24){
            	 return "/client/overtime";
            }   
		}            
        
        // 待付款
        if (!order.getStatusId().equals(2L)) {
            return "/client/error_404";
        }

        String amount = order.getTotalPrice().toString();
        req.setAttribute("totalPrice", amount);

        String payForm = "";

        Long payId = order.getPayTypeId();
        TdPayType payType = tdPayTypeService.findOne(payId);
        if (payType != null) {
            TdPayRecord record = new TdPayRecord();
            record.setCreateTime(new Date());
            record.setOrderId(order.getId());
            record.setPayTypeId(payType.getId());
            record.setStatusCode(1);
            record.setCreateTime(new Date());
            record = payRecordService.save(record);

            String payRecordId = record.getId().toString();
            int recordLength = payRecordId.length();
            if (recordLength > 6) {
                payRecordId = payRecordId.substring(recordLength - 6);
            } else {
                payRecordId = leftPad(payRecordId, 6, "0");
            }
            req.setAttribute("payRecordId", payRecordId);

            req.setAttribute("orderNumber", order.getOrderNumber());

            String payCode = payType.getCode();
            if (PAYMENT_ALI.equals(payCode)) {
                payForm = payChannelAlipay.getPayFormData(req);
                map.addAttribute("charset", AlipayConfig.CHARSET);
            } else if (PAYMENT_WX.equals(payCode)) {
                map.addAttribute("order_number", order.getOrderNumber());
                map.addAttribute("total_price", order.getTotalPrice());
                
                String sa = "appid=" + Configure.getAppid() 
                        + "&mch_id=" + Configure.getMchid()
                        + "&nonce_str=" + RandomStringGenerator.getRandomStringByLength(32)
                        + "&product_id=" + order.getId()
                        + "&time_stamp=" + System.currentTimeMillis() / 1000;
                
                String sign = MD5.MD5Encode(sa + "&key=192006250b4c09247ec02edce69f6acy").toUpperCase();
                
                System.out.print("Sharon: weixin://wxpay/bizpayurl?" + sa + "&sign=" + sign + "\n");
                
                req.getSession().setAttribute("WXPAYURLSESSEION", 
                        "weixin://wxpay/bizpayurl?" + sa
                      + "&sign=" + sign);
                        // "weixin://wxpay/bizpayurl?appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D");
                return "/client/order_pay_wx";
            }
            else if (CEBPayConfig.INTER_B2C_BANK_CONFIG.keySet().contains(
                    payCode)) {
                req.setAttribute("payMethod", payCode);
                payForm = payChannelCEB.getPayFormData(req);
                map.addAttribute("charset", "GBK");
            } else {
                // 其他目前未实现的支付方式
                return "/client/error_404";
            }
        } else {
            return "/client/error_404";
        }

        order.setPayTime(new Date());

        tdOrderService.save(order);

        map.addAttribute("payForm", payForm);

        return "/client/order_pay_form";
    }
    
    @RequestMapping(value = "/wx_return",method = RequestMethod.GET)
    public void wx_return(String openid, String productid, 
            HttpServletResponse response, HttpServletRequest request) throws Exception {
        System.out.print("Sharon: openid=" + openid + ";   productid=" + productid + "\n");
        
        System.out.print("Sharon: session openid=" + request.getSession().getAttribute("openid") 
                + ";   session productid=" + request.getSession().getAttribute("productid") 
                + "\n");
        
        if (null == productid)
        {
            return;
        }
        
        Long orderId = Long.parseLong(productid);
        
        TdOrder order = tdOrderService.findOne(orderId);
        
        if (null == order)
        {
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calExpire = Calendar.getInstance();
        calExpire.setTime(order.getOrderTime());
        
        //根据订单类型来判断支付时间是否过期
        if (order.getTypeId().equals(3L)) { // 抢拍  订单提交后20分钟内
            calExpire.add(Calendar.MINUTE, 20);
        }
        else if (order.getTypeId().equals(4L) || order.getTypeId().equals(5L)) { //团购  预付是订单提交后12小时内，尾款支付也是12小时
            calExpire.add(Calendar.HOUR, 12);
        }
        else { //普通  订单提交后24小时内
            calExpire.add(Calendar.DATE, 1); 
        }
        
        UnifiedOrderService service = new UnifiedOrderService();
        UnifiedOrderReqData data = new UnifiedOrderReqData("支付订单"+order.getOrderNumber(),
                "",
                "",
                order.getOrderNumber(),
                "CNY",
                (int)(order.getTotalPrice() * 100),
                "",
                CommonService.getIp(request),
                sdf.format(order.getOrderTime()),
                sdf.format(calExpire.getTime()),
                "http://www.cytm99.com/order/wx_notify",
                "NATIVE",
                productid,
                "",
                openid,
                "");
        
        String res = service.request(data);
        
        System.out.print("Sharon: res=" + res + "\n");
        res.trim();
    }
    
    @RequestMapping(value = "/wx_notify",method = RequestMethod.GET)
    public void wx_notify(String prepay_id, 
            HttpServletResponse response, HttpServletRequest request){
        System.out.print("Sharon: prepay_id=" + prepay_id + "\n");
    }
    
    @RequestMapping(value = "/payqrcode",method = RequestMethod.GET)
    public void verify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        
        QRCodeUtils qr = new QRCodeUtils();
        String url = (String)request.getSession().getAttribute("WXPAYURLSESSEION");
        qr.getQRCode(url, 300, response);
    }

    /**
     * 支付尾款
     * 
     * @param orderId
     * @param map
     * @param req
     * @return
     */
    @RequestMapping(value = "/dopayleft/{orderId}")
    public String payOrderLeft(@PathVariable Long orderId, ModelMap map,
            HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setHeader(map, req);

        if (null == orderId) {
            return "/client/error_404";
        }

        TdOrder order = tdOrderService.findOne(orderId);

        if (null == order) {
            return "/client/error_404";
        }
        //根据订单类型来判断支付时间是否过期
        if (order.getTypeId().equals(3L)) { // 抢拍  订单提交后20分钟内
            Date cur = new Date();
        	long temp = cur.getTime() - order.getOrderTime().getTime();
  //      	System.out.println(temp);
            if(temp > 1000*60*20){
            	 return "/client/overtime";
            }   
		}
        else if (order.getTypeId().equals(4L) || order.getTypeId().equals(5L)) { //团购  预付是订单提交后12小时内，尾款支付也是12小时
        	Date cur = new Date();
        	long temp = cur.getTime() - order.getOrderTime().getTime();
            if(temp > 1000*3600*12){
            	 return "/client/overtime";
            }   
		}
        else { //普通  订单提交后24小时内
        	Date cur = new Date();
        	long temp = cur.getTime() - order.getOrderTime().getTime();
            if(temp > 1000*3600*24){
            	 return "/client/overtime";
            }   
		}
        
        // 待付尾款
        if (!order.getStatusId().equals(3L)) {
            return "/client/error_404";
        }

        String amount = order.getTotalLeftPrice().toString();
        req.setAttribute("totalPrice", amount);

        String payForm = "";

        Long payId = order.getPayTypeId();
        TdPayType payType = tdPayTypeService.findOne(payId);
        if (payType != null) {
            TdPayRecord record = new TdPayRecord();
            record.setCreateTime(new Date());
            record.setOrderId(order.getId());
            record.setPayTypeId(payType.getId());
            record.setStatusCode(1);
            record.setCreateTime(new Date());
            record = payRecordService.save(record);

            String payRecordId = record.getId().toString();
            int recordLength = payRecordId.length();
            if (recordLength > 6) {
                payRecordId = payRecordId.substring(recordLength - 6);
            } else {
                payRecordId = leftPad(payRecordId, 6, "0");
            }
            req.setAttribute("payRecordId", payRecordId);

            req.setAttribute("orderNumber", order.getOrderNumber());

            String payCode = payType.getCode();
            if (PAYMENT_ALI.equals(payCode)) {
                payForm = payChannelAlipay.getPayFormData(req);
                map.addAttribute("charset", AlipayConfig.CHARSET);
            } else if (CEBPayConfig.INTER_B2C_BANK_CONFIG.keySet().contains(
                    payCode)) {
                req.setAttribute("payMethod", payCode);
                payForm = payChannelCEB.getPayFormData(req);
                map.addAttribute("charset", "GBK");
            } else {
                // 其他目前未实现的支付方式
                return "/client/error_404";
            }
        } else {
            return "/client/error_404";
        }

        order.setPayTime(new Date());

        tdOrderService.save(order);

        map.addAttribute("payForm", payForm);

        return "/client/order_pay_form";
    }

    @RequestMapping(value = "/pay/success")
    public String paySuccess(ModelMap map, HttpServletRequest req) {
        // String username = (String) req.getSession().getAttribute("username");
        //
        // if (null == username) {
        // return "redirect:/login";
        // }

        tdCommonService.setHeader(map, req);

        return "/client/order_pay_success";
    }

    @RequestMapping(value = "/pay/notify")
    public String payNotify(ModelMap map, HttpServletRequest req) {
        // String username = (String) req.getSession().getAttribute("username");
        //
        // if (null == username) {
        // return "redirect:/login";
        // }

        tdCommonService.setHeader(map, req);

        return "/client/order_pay";
    }

    /*
     * 
     */
    @RequestMapping(value = "/pay/notify_alipay")
    public void payNotifyAlipay(ModelMap map, HttpServletRequest req,
            HttpServletResponse resp) {
        payChannelAlipay.doResponse(req, resp);
    }

    /*
     * 
     */
    @RequestMapping(value = "/pay/notify_cebpay")
    public void payNotifyCEBPay(ModelMap map, HttpServletRequest req,
            HttpServletResponse resp) {
        payChannelCEB.doResponse(req, resp);
    }

    /*
     * 
     */
    @RequestMapping(value = "/pay/result_alipay")
    public String payResultAlipay(ModelMap map, HttpServletRequest req,
            HttpServletResponse resp) {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = req.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter
                .hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"),
                        AlipayConfig.CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }

        // 获取支付宝的返回参数
        String orderNo = "";
        String trade_status = "";
        try {
            // 商户订单号
            orderNo = new String(req.getParameter(Constants.KEY_OUT_TRADE_NO)
                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
            // 交易状态
            trade_status = new String(req.getParameter("trade_status")
                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        tdCommonService.setHeader(map, req);
        orderNo = (orderNo == null) ? "" : (orderNo.length() < 6) ? orderNo
                : orderNo.substring(0, orderNo.length() - 6);
        TdOrder order = tdOrderService.findByOrderNumber(orderNo);
        if (order == null) {
            // 订单不存在
            return "/client/order_pay_failed";
        }
        map.put("order", order);
        if (verify_result) {// 验证成功
            if ("WAIT_SELLER_SEND_GOODS".equals(trade_status)) {

                // 订单支付成功
                afterPaySuccess(order);

                return "/client/order_pay_success";
            }
        }

        // 验证失败或者支付失败
        return "/client/order_pay_failed";
    }
    
    @RequestMapping(value = "/pay/result_wxpay")
    public String payResultWxpay(String productid, String openid, ModelMap map, HttpServletRequest req,
            HttpServletResponse resp) {
        
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = req.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter
                .hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"),
                        AlipayConfig.CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }

        // 获取支付宝的返回参数
        String orderNo = "";
        String trade_status = "";
        try {
            // 商户订单号
            orderNo = new String(req.getParameter(Constants.KEY_OUT_TRADE_NO)
                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
            // 交易状态
            trade_status = new String(req.getParameter("trade_status")
                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        tdCommonService.setHeader(map, req);
        
        orderNo = (orderNo == null) ? "" : (orderNo.length() < 6) ? orderNo
                : orderNo.substring(0, orderNo.length() - 6);
        TdOrder order = tdOrderService.findByOrderNumber(orderNo);
        if (order == null) {
            // 订单不存在
            return "/client/order_pay_failed";
        }
        
        map.put("order", order);
        
        if (verify_result) {// 验证成功
            if ("WAIT_SELLER_SEND_GOODS".equals(trade_status)) {

                // 订单支付成功
                afterPaySuccess(order);

                return "/client/order_pay_success";
            }
        }

        // 验证失败或者支付失败
        return "/client/order_pay_failed";
    }

    /*
     * 
     */
    @RequestMapping(value = "/pay/result_cebpay")
    public String payResultCEBPay(ModelMap map, HttpServletRequest req,
            HttpServletResponse resp) {
        tdCommonService.setHeader(map, req);

        String plainData = req.getParameter("Plain");
        String signature = req.getParameter("Signature");

        // 计算得出通知验证结果
        boolean verify_result = CebMerchantSignVerify
                .merchantVerifyPayGate_ABA(signature, plainData);
        String plainObjectStr = "";

        if (plainData.endsWith("~|~")) {
            plainObjectStr = plainData.substring(0, plainData.length() - 3);
        }

        plainObjectStr = plainObjectStr.replaceAll("=", "\":\"").replaceAll(
                "~\\|~", "\",\"");
        plainObjectStr = "{\"" + plainObjectStr + "\"}";

        JSONObject paymentResult = JSONObject.fromObject(plainObjectStr);

        String orderNo = paymentResult.getString("orderId");
        orderNo = (orderNo == null) ? "" : (orderNo.length() < 6) ? orderNo
                : orderNo.substring(0, orderNo.length() - 6);
        TdOrder order = tdOrderService.findByOrderNumber(orderNo);
        if (order == null) {
            // 订单不存在
            return "/client/order_pay_failed";
        }

        map.put("order", order);

        if (verify_result) {// 验证成功
            String trade_status = paymentResult.getString("respCode");
            if ("".equals(trade_status) || "AAAAAAA".equals(trade_status)) {
                // 订单支付成功

                afterPaySuccess(order);

                return "/client/order_pay_success";
            }

        }
        // 验证失败或者支付失败
        return "/client/order_pay_failed";
    }

    /*
     * 
     */
    @RequestMapping(value = "/change_paymethod", method = { RequestMethod.POST })
    public @ResponseBody Map<String, String> changePaymentMethod(Long orderId,
            Long paymentMethodId, ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "F");
        if (null == username) {
            result.put("message", "请先登录！");
            return result;
        }

        if (null == orderId) {
            result.put("message", "订单Id非法！");
            return result;
        }

        if (null == paymentMethodId) {
            result.put("message", "支付方式非法！");
            return result;
        }

        TdOrder order = tdOrderService.findOne(orderId);

        if (null == order) {
            result.put("message", "不存在的订单信息！");
            return result;
        }

        TdPayType payType = tdPayTypeService.findOne(paymentMethodId);
        if (null == payType) {
            result.put("message", "不存在的支付方式信息！");
            return result;
        }

        if (!order.getStatusId().equals(2L) && !order.getStatusId().equals(3L)) {
            result.put("message", "订单不能修改支付方式！");
            return result;
        }

        if (payType.getIsEnable()) {
            result.put("message", "所选的支付方式暂不支持，请选择其他支付方式！");
        }

        Double payTypeFee = payType.getFee();
        payTypeFee = payTypeFee == null ? 0.0 : payTypeFee;

        double goodPrice = order.getTotalGoodsPrice();
        Double deliverTypeFee = order.getDeliverTypeFee();
        deliverTypeFee = deliverTypeFee == null ? 0.0 : deliverTypeFee;
        /*
         * 订单金额=商品总额+支付手续费+运费-优惠券金额-积分抵扣金额 优惠券金额+积分抵扣金额=商品总额+支付手续费+运费-订单金额
         */
        Double orgPayTypeFee = order.getPayTypeFee();
        orgPayTypeFee = orgPayTypeFee == null ? 0.0 : orgPayTypeFee;
        double couponAndPointsFee = goodPrice + orgPayTypeFee + deliverTypeFee
                - order.getTotalPrice();

        /*
         * 按百分比收取手续费,手续费重新计算(商品总额*百分比)
         */
        if (payType.getIsFeeCountByPecentage()) {
            payTypeFee = goodPrice * payTypeFee / 100;
        }

        order.setTotalPrice(goodPrice + payTypeFee + deliverTypeFee
                - couponAndPointsFee);
        order.setPayTypeFee(payTypeFee);
        order.setPayTypeId(payType.getId());
        order.setPayTypeTitle(payType.getTitle());
        order.setIsOnlinePay(payType.getIsOnlinePay());

        tdOrderService.save(order);

        result.put("status", "S");
        result.put("message", "订单支付方式修改成功！");
        return result;
    }

    /**
     * 订单支付成功后步骤
     * 
     * @param tdOrder
     *            订单
     */
    private void afterPaySuccess(TdOrder tdOrder) {
        if (null == tdOrder) {
            return;
        }

        // 用户
        TdUser tdUser = tdUserService.findByUsername(tdOrder.getUsername());
        
        /**
		 * @author lc
		 * @注释：根据用户所属同盟店id查询同盟店
		 */
        TdDiySite tdShop = null;
        if (null != tdUser.getUpperDiySiteId()) {
        	// 同盟店
            tdShop = tdDiySiteService.findOne(tdUser.getUpperDiySiteId());
		}else{
			// 同盟店
	        tdShop = tdDiySiteService.findOne(tdOrder.getShopId());
		}
        

        if (tdOrder.getStatusId().equals(2L) && !tdOrder.getTotalLeftPrice().equals(0))
        {
            // 待付尾款
            tdOrder.setStatusId(3L);
            tdOrder = tdOrderService.save(tdOrder);
            return;
        }
        else
        {
            // 待服务
            tdOrder.setStatusId(4L);
            tdOrder = tdOrderService.save(tdOrder);
        }
        
        // 给用户发送短信
        if (null != tdUser) {
        	Random random = new Random();
            String smscode = String.format("%04d", random.nextInt(9999));
            SMSUtil.send(
                    tdOrder.getShippingPhone(),
                    "29040",
                    new String[] {
                            tdUser.getUsername(),
                            tdOrder.getOrderGoodsList().get(0).getGoodsTitle(),
                            smscode});
            tdOrder.setSmscode(smscode);
            tdOrder = tdOrderService.save(tdOrder);
        }

        // 给商户发短信
        if (null != tdShop && null != tdUser && null != tdShop.getMobile()) {
            SMSUtil.send(tdShop.getMobile(), 
                    "29039",
                    new String[] { tdShop.getTitle(), tdUser.getUsername(),
                            tdOrder.getOrderGoodsList().get(0).getGoodsTitle(),
                            tdOrder.getAppointmentTime().toString() });
        }
        
        List<TdOrderGoods> tdOrderGoodsList = tdOrder.getOrderGoodsList();

        Long totalPoints = 0L;       //总用户返利
        Double totalCash = 0.0;      //总同盟店返利
        Double platformService = 0.0;//商城服务费
        Double trainService = 0.0;   //培训费
        Double shopOrderincome = 0.0;//同盟店收入
        Double totalSaleprice = 0.0; //订单商品总销售价
        Double totalCostprice = 0.0; //订单商品总成本价
        // 返利总额
        if (null != tdOrderGoodsList) {
            for (TdOrderGoods tog : tdOrderGoodsList) {
                if (0 == tog.getGoodsSaleType()) // 正常销售
                {
                    TdGoods tdGoods = tdGoodsService.findOne(tog.getGoodsId());

                    if (null != tdGoods && null != tdGoods.getReturnPoints()) {
                        totalPoints += tdGoods.getReturnPoints()* tog.getQuantity();

                        if (null != tdGoods.getShopReturnRation()) {
                            totalCash += tdGoods.getSalePrice()
                                    * tdGoods.getShopReturnRation() * tog.getQuantity();
                        }
                    }
                    if (null != tdGoods && null != tdGoods.getPlatformServiceReturnRation()) {
                    	platformService += tdGoods.getSalePrice() * tdGoods.getPlatformServiceReturnRation() * tog.getQuantity();
					}
                    if (null != tdGoods && null != tdGoods.getTrainServiceReturnRation()) {
                    	trainService += tdGoods.getOutFactoryPrice() * tdGoods.getTrainServiceReturnRation()* tog.getQuantity(); 
					}
                    totalSaleprice += tdGoods.getSalePrice()* tog.getQuantity();
                    totalCostprice += tdGoods.getCostPrice()* tog.getQuantity();
                }
            }
            if (tdOrder.getTypeId().equals(1L)) {
            	//shopOrderincome = totalSaleprice - totalCostprice - totalPoints - platformService - trainService - totalCash;
            	shopOrderincome = totalSaleprice - totalCostprice - platformService - totalCash;
			}         
            
            final Long totalPointsDely = totalPoints;
            final TdUser tdUserDely = tdUser;
            final TdOrder tdOrderDely = tdOrder;
            // 用户返利
            if (null != tdUser) {
            	Timer timer = new Timer();  
                timer.schedule(new TimerTask() {  
                    public void run() {  
                       // System.out.println("-------设定要指定任务--------");  
                        TdUserPoint userPoint = new TdUserPoint();
                        TdOrder tdOrder1 = tdOrderService.findByOrderNumber(tdOrderDely.getOrderNumber());
                        
                        userPoint.setDetail("购买商品赠送粮草");
                        userPoint.setOrderNumber(tdOrderDely.getOrderNumber());
                        userPoint.setPoint(totalPointsDely);
                        userPoint.setPointTime(new Date());
                        userPoint.setTotalPoint(tdUserDely.getTotalPoints() + totalPointsDely);
                        userPoint.setUsername(tdUserDely.getUsername());

                        userPoint = tdUserPointService.save(userPoint);

                        tdUserDely.setTotalPoints(userPoint.getTotalPoint());
                        
                        tdOrder1.setIsReturnPoints(true);
                        tdOrderService.save(tdOrder1);
                        tdUserService.save(tdUserDely);
                    }  
                }, 1000*3600*24*7);// 设定指定的时间time,
            	
                
            }
        }
             
        // 同盟店返利
        if (null != tdShop) {
            if (null == tdShop.getTotalCash()) {
                tdShop.setTotalCash(totalCash);
            } else {
                tdShop.setTotalCash(tdShop.getTotalCash() + totalCash);
            }
            tdOrder.setRebate(totalCash);//设置订单同盟店所获返利
            tdOrder.setPlatformService(platformService);//设置订单平台服务费
            tdOrder.setTrainService(trainService);//设置订单培训服务费
            tdOrder.setOrderIncome(shopOrderincome);//设置同盟店订单收入
            tdOrder = tdOrderService.save(tdOrder);
            tdDiySiteService.save(tdShop);
        }
    }
}
