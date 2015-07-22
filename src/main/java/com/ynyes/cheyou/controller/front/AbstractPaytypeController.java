package com.ynyes.cheyou.controller.front;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.cytm.payment.ceb.CEBPayConfig;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdPayType;
import com.ynyes.cheyou.service.TdPayTypeService;

public class AbstractPaytypeController {
    private static final String PAYMENT_COOKIE = "payMethod";
    
    @Autowired
    protected TdPayTypeService tdPayTypeService;
    
    /**
     * 取得支付方式列表
     * @param map
     * @param forPaymentFlow
     * @param fororderDetail
     * @param req
     */
    protected void setPayTypes(ModelMap map, 
            boolean forPaymentFlow, 
            boolean fororderDetail, 
            HttpServletRequest req) {        
        // 支付画面以Cookie存储的数据为上一次支付方式
        Cookie [] cookies = req.getCookies();
        String lastPaymentMethod = "";
        for(Cookie cookie : cookies) {
            if(PAYMENT_COOKIE.equals(cookie.getName())) {
                lastPaymentMethod = cookie.getValue();
            }
        }
        if(forPaymentFlow) {
            map.addAttribute("paymethodId", lastPaymentMethod);
        } else if(fororderDetail) {// 订单详细画面优先以订单保存的数据为上一次支付方式
            TdOrder order = (TdOrder)map.get("order");
            if(null != order) {
                lastPaymentMethod = order.getPayTypeId().toString();
            }
            map.addAttribute("paymethodId", lastPaymentMethod);
        }

        TdPayType lastPayType = null;
        Long lastPayId = null;
        try {
            lastPayId = Long.parseLong(lastPaymentMethod);
        } catch (NumberFormatException e) {
            // Do nothing
        }
        
        List<TdPayType> pay_type_list = tdPayTypeService.findByIsEnableTrue();
        List<TdPayType> pay_type_list_bank = new LinkedList<TdPayType>();
        List<TdPayType> pay_type_list_third = new LinkedList<TdPayType>();
        Set<String> supportedBanks = CEBPayConfig.INTER_B2C_BANK_CONFIG.keySet();
        for(int i = 0; i < pay_type_list.size(); i++) {
            TdPayType payType = pay_type_list.get(i);
            if(payType.getId().equals(lastPayId)) {
                lastPayType = payType;
            }
            if(supportedBanks.contains(payType.getCode())) {
                pay_type_list_bank.add(payType);
            } else {
                pay_type_list_third.add(payType);
            }
        }
        
        int lastPayMethodIndex = 0;
        if((null != lastPayType) 
                && ((lastPayMethodIndex = pay_type_list_bank.indexOf(lastPayType)) > -1)) {
            pay_type_list = pay_type_list_bank;
            pay_type_list.remove(lastPayMethodIndex);
            pay_type_list_bank = new LinkedList<TdPayType>();
            pay_type_list_bank.add(lastPayType);
            pay_type_list_bank.addAll(pay_type_list);
        } else if((null != lastPayType) 
                && ((lastPayMethodIndex = pay_type_list_third.indexOf(lastPayType)) > -1)) {
            pay_type_list = pay_type_list_third;
            pay_type_list.remove(lastPayMethodIndex);
            pay_type_list_third = new LinkedList<TdPayType>();
            pay_type_list_third.add(lastPayType);
            pay_type_list_third.addAll(pay_type_list);
        }
        
        // 网上银行
        map.addAttribute("pay_type_list_bank", pay_type_list_bank);
        
        // 三方支付
        map.addAttribute("pay_type_list_third", pay_type_list_third);
    }
}
