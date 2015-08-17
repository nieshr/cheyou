package com.cytm.payment.alipay.core;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.cytm.payment.alipay.AlipayConfig;

@SuppressWarnings("hiding")
public class AlipayConfirmGoodsHandler<String> implements ResponseHandler<java.lang.String> {

    @Override
	public java.lang.String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        StatusLine status = response.getStatusLine();
        if(status.getStatusCode() > 300) {
            throw new ClientProtocolException("Request failed");
        }
        
        return EntityUtils.toString(response.getEntity(), AlipayConfig.CHARSET);
    }
    
}
