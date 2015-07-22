package com.cytm.payment.ceb;

import static com.csii.payment.client.core.CebMerchantSignVerify.merchantSignData_ABA;
import static com.csii.payment.client.core.CebMerchantSignVerify.merchantVerifyPayGate_ABA;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cytm.payment.PaymentChannel;
import com.cytm.payment.util.PaymentUtil;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdPayRecord;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdPayRecordService;

@Service
public class PaymentChannelCEB implements PaymentChannel{
    /*
     * 
     */
    private static final Logger paymentLogger = Logger.getLogger("paymentApi");
    /*
     * 
     */
    private static final String FIELD_SEPARATOR = "~|~";
    /*
     * 
     */
    private static final String FIELD_SEPARATOR_REG = "~\\|~";
    /*
     * 
     */
    private static final String PAYMETHOD_CEB = "CEB";
    
    /*
     * 
     */
    private static final String SUCCESSED = "AAAAAAA";
    
    @Autowired
    private TdPayRecordService payRecordService;
    
    @Autowired
    private TdOrderService orderService;

    @Override
    public String getPayFormData(HttpServletRequest request) {
        //支付记录号码
        String payRecordId = (String) request.getAttribute("payRecordId");
        //订单号
        String orderId = (String) request.getAttribute("orderNumber");
        //订单金额
        String orderAmount = (String) request.getAttribute("totalPrice");
        //支付银行
        String payMethod = (String) request.getAttribute("payMethod");
        String commitUrl = "";
        StringBuilder plainData = new StringBuilder();
        if(PAYMETHOD_CEB.equals(payMethod)) {
            plainData.append("transId=IPER~|~merchantId=")
            .append(CEBPayConfig.INNER_MERCHANT_ID).append("~|~");
            commitUrl = CEBPayConfig.INNER_B2C_URL;
        } else {
            plainData.append("transId=IPER~|~merchantId=")
            .append(CEBPayConfig.INTER_MERCHANT_ID).append("~|~payBankNo=")
            .append(CEBPayConfig.INTER_B2C_BANK_CONFIG.get(payMethod)[0]).append("~|~");
            commitUrl = CEBPayConfig.INTER_B2C_URL;
        }
        
        String serverURL = PaymentUtil.getServerPath(request);

        plainData.append("orderId=").append(orderId + payRecordId).append("~|~")
        .append("transAmt=").append(orderAmount).append("~|~")
        .append("transDateTime=")
        .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append("~|~")
        .append("currencyType=01~|~customerName=~|~customerEMail=~|~")
        .append("merURL=").append(serverURL).append("order/pay/notify_cebpay").append("~|~")
        .append("merURL1=").append(serverURL).append("order/pay/result_cebpay").append("~|~")
        //二级商户
        //.append("merSecName=").append(new String(shopTitle.getBytes("ISO-8859-1"), "UTF-8")).append("~|~")
        //商品信息
        .append("productInfo=").append(orderId).append("~|~")
        .append("payIp=~|~").append("msgExt=").append(orderId).append("|")
        .append(CEBPayConfig.INTER_B2C_BANK_CONFIG.get(payMethod)[0]);
    
        String plain = plainData.toString();
        String signature = merchantSignData_ABA(plain);
        paymentLogger.info("PaymentChannelCEB->>Send:Plain:" + plain);
        paymentLogger.info("PaymentChannelCEB->>Send:Signature:" + signature);
        StringBuilder formData = new StringBuilder();        
        formData.append("<form id=\"cebcommit\"  name=\"cebcommit\" action=\"").append(commitUrl).append("\" method=\"POST\">");
        formData.append("<input name = \"TransName\" type=\"hidden\" value=\"IPER\"/>");
        formData.append("<input name = \"Plain\" type=\"hidden\" value=\"").append(plain).append("\"/>");
        formData.append("<input name = \"Signature\" type=\"hidden\" value=\"").append(signature).append("\"/>");
        //formData.append("<input type=\"submit\" value=\"支付订单\"/>");
        formData.append("</form>");
        formData.append("<script>$(\"#cebcommit\").submit();</script>");
        return formData.toString();
    }

    @Override
    public void doResponse(HttpServletRequest request, HttpServletResponse resp) {
        String plainData = request.getParameter("Plain");
        String signature = request.getParameter("Signature");
        String notifyObject = "";
        paymentLogger.info("PaymentChannelCEB->>Receive:Plain:" + plainData);
        paymentLogger.info("PaymentChannelCEB->>Receive:Signature:" + signature);
        PrintWriter out;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        try {
            if(merchantVerifyPayGate_ABA(signature, plainData)) {
                paymentLogger.info("PaymentChannelCEB->>Receive:Accepted!");
                if(plainData.endsWith(FIELD_SEPARATOR)) {
                    notifyObject = plainData.substring(0, plainData.length() - 3);
                }
                notifyObject = notifyObject.replaceAll("=", "\":\"").replaceAll(FIELD_SEPARATOR_REG, "\",\"");
                notifyObject = "{\"" + notifyObject + "\"}";
                JSONObject paymentResult = JSONObject.fromObject(notifyObject);
                String merchantId = paymentResult.getString("merchantId");
                String orderId = paymentResult.getString("orderId");
                String transDateTime = paymentResult.getString("transDateTime");
                String procStatus = "1";
                String serverURL = PaymentUtil.getServerPath(request);
                StringBuilder responsePlainData = new StringBuilder();
                responsePlainData.append("merchantId=").append(merchantId).append(FIELD_SEPARATOR)
                .append("orderId=").append(orderId).append(FIELD_SEPARATOR)
                .append("transDateTime=").append(transDateTime).append(FIELD_SEPARATOR)
                .append("procStatus=").append(procStatus).append(FIELD_SEPARATOR)
                .append("merURL2=").append(serverURL).append("order/pay/result_cebpay");
                
                String responsePlain = responsePlainData.toString();
                String responseSignature = merchantSignData_ABA(responsePlain);
                paymentLogger.info("PaymentChannelCEB->>Response:Plain:" + responsePlain);
                paymentLogger.info("PaymentChannelCEB->>Response:Signature:" + responseSignature);
                StringBuilder responseContents = new StringBuilder();
                responseContents.append("Plain=").append(responsePlain)
                .append("\n").append("Signature=").append(responseSignature);
                out.print(responseContents.toString());
                
                String payResult = paymentResult.getString("respCode");
                orderId = orderId == null ? "" : 
                    orderId.substring(0, orderId.length() - 6);

                TdOrder order = orderService.findByOrderNumber(orderId);
                List<TdPayRecord> payRecords = payRecordService.getAllByOrderId(order.getId());
                
                Long status = order.getStatusId();
                if(SUCCESSED.equals(payResult) || "".equals(payResult)) {
                    //支付成功
                    if(status.equals(Long.valueOf(1l)) 
                            || status.equals(Long.valueOf(2l)) 
                            || status.equals(Long.valueOf(8l))) {
                        order.setStatusId(3l);
                        order.setPayTime(new Date());
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
                } else {
                    if(status.equals(Long.valueOf(1l)) 
                            || status.equals(Long.valueOf(8l))) {
                        order.setStatusId(8l);
                        order.setCancelTime(new Date());
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
                }
                orderService.save(order);
            } else {
                paymentLogger.info("PaymentChannelCEB->>Receive:Rejected!");
            }
        } catch (Exception e) {
        } finally {
            out.close();
        }
    }

}
