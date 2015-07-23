package com.cytm.payment.alipay;

import static com.cytm.payment.util.PaymentUtil.getServerPath;
import static com.cytm.payment.util.PaymentUtil.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cytm.payment.PaymentChannel;
import com.cytm.payment.alipay.core.AlipayConfirmGoods;
import com.cytm.payment.alipay.core.AlipayConfirmGoodsHandler;
import com.cytm.payment.alipay.core.AlipayNotify;
import com.cytm.payment.alipay.core.AlipaySubmit;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdPayRecord;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdPayRecordService;

@org.springframework.stereotype.Service
public class PaymentChannelAlipay implements PaymentChannel {
    private static final String OUT_TRADE_NO_PARA = "out_trade_no";
    private static final String ORDER_NO_TB_PARA = "trade_no";
    private static final String ORDER_STATUS_PARA = "trade_status";
    private static final String ISO_ENCODING = "ISO-8859-1";
    private static final int FIRST_TIME = 0;
    private static final int MAX_TIME = 10;

    private static final String NEW_LINE = System.getProperty("line.seperator",
            "\n");

    /*
     * 
     */
    private static final Logger paymentLogger = Logger.getLogger("paymentApi");
    
    @Autowired
    private TdPayRecordService payRecordService;
    
    @Autowired
    private TdOrderService orderService;

    @Override
    public String getPayFormData(HttpServletRequest request) {
        Map<String, String> requestParameters = new HashMap<String, String>();
        requestParameters.put(Constants.KEY_SERVICE, AlipayConfig.CREATE_TRADE_SERVICE);
        requestParameters.put(Constants.KEY_PARTNER, AlipayConfig.PARTNER);
        requestParameters.put(Constants.KEY_CHARSET, AlipayConfig.CHARSET);
        String serverPath = getServerPath(request);
        requestParameters.put(Constants.KEY_NOTIFY_URL, serverPath + "order/pay/notify_alipay");
        requestParameters.put(Constants.KEY_RETURN_URL, serverPath + "order/pay/result_alipay");
        
        //支付记录号码
        String payRecordId = (String) request.getAttribute("payRecordId");
        // 订单ID
        String orderId = (String) request.getAttribute("orderNumber");
        requestParameters.put(Constants.KEY_SUBJECT, orderId);
        
        requestParameters.put(Constants.KEY_OUT_TRADE_NO, orderId + payRecordId);
        requestParameters.put(Constants.KEY_PAYMENT_TYPE, AlipayConfig.PAYMENT_TYPE);
        requestParameters.put(Constants.KEY_LOGISTICS_TYPE, AlipayConfig.DEFAULT_EXPRESS);
        requestParameters.put(Constants.KEY_LOGISTICS_FEE, AlipayConfig.LOGISTICS_FREE);
        requestParameters.put(Constants.KEY_LOGISTICS_PAYMENT, AlipayConfig.SELLER_PAY);

        // 订单金额取得
        String orderAmount = (String) request.getAttribute("totalPrice");
        requestParameters.put(Constants.KEY_PRICE, orderAmount);
        requestParameters.put(Constants.KEY_QUANTITY, AlipayConfig.DEFAULT_QUANTITY);
        requestParameters.put(Constants.KEY_SELLER_EMAIL, AlipayConfig.SELLER_EMAIL);
        requestParameters.put(Constants.KEY_SELLER_ID, AlipayConfig.SELLER_ID);
        requestParameters.put(Constants.KEY_SELLER_ACCOUNT_NAME, AlipayConfig.SELLER_EMAIL);
        
        // 此字段要修改
        //requestParameters.put(Constants.KEY_BODY, "支付测试");
        requestParameters.put(Constants.KEY_TOTAL_FEE, orderAmount);
                // requestParameters.put(Constants.KEY_TBREC_POST,
                // AlipayConfig.RECORD_POST);
                // requestParameters.put(Constants.KEY_ITB_PAY, AlipayConfig.PAY_POST);
        return AlipaySubmit.buildRequestForm(requestParameters,
                Constants.METHOD_POST, Constants.PAY_BUTTON_NAME);
    }

    @Override
    public void doResponse(HttpServletRequest request, HttpServletResponse resp) {
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        StringBuilder loggeMessage = new StringBuilder();
        for (Iterator<String> iter = requestParams.keySet().iterator(); 
                iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            loggeMessage.append(name).append("=").append(valueStr).append("&");
            params.put(name, valueStr);
        }
        // 消息记录处理
        loggeMessage.setLength(loggeMessage.length() - 1);

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        // 商户订单号
        String orderNo = null;

        // 支付宝交易号
        String trade_no = null;

        // 交易状态
        String trade_status = null;
        try {
            orderNo = new String(request.getParameter(OUT_TRADE_NO_PARA)
                    .getBytes(ISO_ENCODING), AlipayConfig.CHARSET);
            orderNo = orderNo == null ? "" : 
                orderNo.substring(0, orderNo.length() - 6);
            trade_no = new String(request.getParameter(ORDER_NO_TB_PARA)
                    .getBytes(ISO_ENCODING), AlipayConfig.CHARSET);
            trade_status = new String(request.getParameter(ORDER_STATUS_PARA)
                    .getBytes(ISO_ENCODING), AlipayConfig.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        
        PrintWriter out = null;
        try {
            paymentLogger.info(String.format("AlipayNotify:{%s}.", loggeMessage.toString()));
            // 获取支付宝的通知返回参数
            // 根据状态调用何时接口操作订单状态
            out = resp.getWriter();
            if (AlipayNotify.verify(params)) {// 验证成功
                paymentLogger.info("AlipayNotify:Accepted!");

                TdOrder order = orderService.findByOrderNumber(orderNo);
                List<TdPayRecord> payRecords = payRecordService.getAllByOrderId(order.getId());
                if (OrderStatus.WAIT_PAY.equals(trade_status)) {
                    // 该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款

                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    // 如果有做过处理，不执行商户的业务程序
                    paymentLogger.info(String.format(
                            "AlipayNotify:{%s}支付宝订单已经生成,用户未付款!",
                            orderNo));
                    if(order != null && order.getStatusId() == 1) {
                        order.setStatusId(2l);
                        orderService.save(order);
                    }
                } else if (OrderStatus.WAIT_SEND_GOODS.equals(trade_status)) {
                    // 该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货

                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    // 如果有做过处理，不执行商户的业务程序
                    paymentLogger.info(String.format("AlipayNotify:{%s}用户已经付款给支付宝,系统自动发货!",
                            orderNo));
                    if(order != null && (order.getStatusId() == 2 || order.getStatusId() == 8)) {
                        order.setStatusId(3l);
                        order.setPayTime(new Date());
                        orderService.save(order);
                    }
                    
                    if(!payRecords.isEmpty()) {
                        int i = 0;
                        for(TdPayRecord record : payRecords) {
                            if(i == 0) {
                                record.setStatusCode(2);
                            } else {
                                record.setStatusCode(3);
                            }
                            i++;
                        }
                        payRecordService.save(payRecords);
                    }
                    
                    sendConfirmGoods(FIRST_TIME, trade_no);
                } else if (OrderStatus.WAIT_ONFIRM_GOODS.equals(trade_status)) {
                    // 该判断表示卖家已经发了货，但买家还没有做确认收货的操作

                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    // 如果有做过处理，不执行商户的业务程序
                    paymentLogger.info(String.format("AlipayNotify:{%s}等待买家确认收货!", 
                            orderNo));
                    if(order != null && (order.getStatusId() == 3 || order.getStatusId() == 8)) {
                        order.setStatusId(4l);
                        order.setDeliveryTime(new Date());
                        orderService.save(order);
                    }
                } else if (OrderStatus.FINISHED.equals(trade_status)) {
                    // 该判断表示买家已经确认收货，这笔交易完成

                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    // 如果有做过处理，不执行商户的业务程序
                    paymentLogger.info(String.format("AlipayNotify:{%s}用户确认收货完毕,支付款入帐门订单成功!", 
                            orderNo));
                    if(order != null && (order.getStatusId() == 4 || order.getStatusId() == 8)) {
                        order.setStatusId(5l);
                        order.setFinishTime(new Date());
                        orderService.save(order);
                    }
                } else {
                    // 交易中途结束
                    paymentLogger.info(String.format("AlipayNotify:{%s}订单中途取消,支付失败S", orderNo));
                    if(order != null && (order.getStatusId() != 8)) {
                        order.setStatusId(8l);
                        order.setCancelTime(new Date());
                        orderService.save(order);
                    }
                    
                    if(!payRecords.isEmpty()) {
                        for(TdPayRecord record : payRecords) {
                            record.setStatusCode(3);
                        }
                        payRecordService.save(payRecords);
                    }
                }
                out.println("success");// 请不要修改或删除
            } else {// 验证失败
                paymentLogger.info("AlipayNotify:Rejected!");
                out.println("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                out.close();
            }
        }
    }

    private boolean sendConfirmGoods(int timeCount, String trade_no) {
        try {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            String result = (String)post(AlipayConfig.REAUESTURL,
                    AlipayConfirmGoods.generatNameValuePair(trade_no),
                    new AlipayConfirmGoodsHandler(), AlipayConfig.CHARSET);
            paymentLogger.info(String.format("AlipayConfirmGoods:%s%s!", NEW_LINE, result));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            if (timeCount < MAX_TIME) {
                sendConfirmGoods(timeCount++, trade_no);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (timeCount < MAX_TIME) {
                sendConfirmGoods(timeCount++, trade_no);
            }
        }
        return false;
    }

    static class OrderStatus {
        // 买家已在支付宝交易管理中产生了交易记录，但没有付款
        public static String WAIT_PAY = "WAIT_BUYER_PAY";
        // 买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
        public static String WAIT_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
        // 卖家已经发了货，但买家还没有做确认收货的操作
        public static String WAIT_ONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
        // 买家已经确认收货，这笔交易完成
        public static String FINISHED = "TRADE_FINISHED";
    }
}
