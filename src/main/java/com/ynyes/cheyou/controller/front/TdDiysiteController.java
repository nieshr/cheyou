package com.ynyes.cheyou.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdUserService;


@Controller
@RequestMapping(value="/diysite/order")
public class TdDiysiteController {
	
	@Autowired
	TdOrderService tdOrderService;
	
	@Autowired
	TdUserService tdUserService;
	
	@RequestMapping(value="/param/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> paramEdit(String orderNumber,
                        String type,
                        String data,
                        String name,
                        String address,
                        String postal,
                        String mobile,
                        String expressNumber,
                        Long deliverTypeId,
                        ModelMap map,
                        HttpServletRequest req){
        
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("diysiteUsername");
        if (null == username)
        {
            res.put("message", "请重新登录");
            return res;
        }
        
        if (null != orderNumber && !orderNumber.isEmpty() && null != type && !type.isEmpty())
        {
            TdOrder order = tdOrderService.findByOrderNumber(orderNumber);
            TdUser tdUser = tdUserService.findByUsername(order.getUsername());
            // 修改备注
            if (type.equalsIgnoreCase("editMark"))
            {
                order.setRemarkInfo(data);
            }
            // 确认已服务
            else if (type.equalsIgnoreCase("orderService"))
            {
                if (order.getStatusId().equals(4L))
                {
                	if (null == tdUser.getUpperDiySiteId()) {
						tdUser.setUpperDiySiteId(order.getShopId());
						tdUserService.save(tdUser);
					}
                    order.setStatusId(5L);
                    order.setServiceTime(new Date());
                }
            }
          
            
            tdOrderService.save(order);
            
            res.put("code", 0);
            res.put("message", "修改成功!");
            return res;
        }
        
        res.put("message", "参数错误!");
        return res;
    }
}
