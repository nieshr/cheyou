package com.ynyes.cheyou.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
/**
 * QRCodeUtils<BR>
 * 创建人:Sharon <BR>
 * 时间：2015年1月29日-下午12:35:10 <BR>
 * @version 1.0.0
 *
 */
public class QRCodeUtils {

    /**
     * 生成二维码
     * @param content 内容
     * @param size 二维码大小，像素值
     * @param response
     */
    public void getQRCode(String content, int size, HttpServletResponse response) {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        
        hints.put(EncodeHintType.MARGIN, 0);
        
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        for (int x = 0; x < width; x++) {
             for (int y = 0; y < height; y++) {
                   image.setRGB(x, y, bitMatrix.get(x, y) == true ? 
                   Color.BLACK.getRGB():Color.WHITE.getRGB());
            }
        }
        
        try {
            ImageIO.write(image, "png", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
} 
  

