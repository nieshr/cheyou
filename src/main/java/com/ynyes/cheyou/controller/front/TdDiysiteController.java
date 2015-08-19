package com.ynyes.cheyou.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdDiySiteService;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdUserService;


@Controller
@RequestMapping(value="/diysite/order")
public class TdDiysiteController {
	
	@Autowired
	TdOrderService tdOrderService;
	
	@Autowired
	TdCommonService tdCommonService;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdDiySiteService tdDiySiteService;
	
	/**
	 * @author lc
	 * @注释：订单收入
	 */
//	@RequestMapping(value = "/orderincome")
//	public String orderList(Integer page,
//	                        Integer timeId, 
//	                        HttpServletRequest req,
//	                        ModelMap map) {
//		String username = (String) req.getSession().getAttribute("diysiteUsername");
//
//        if (null == username) {
//            return "redirect:/login";
//        }
//        
//        tdCommonService.setHeader(map, req);
//
//        if (null == page) {
//            page = 0;
//        }
//
//        if (null == timeId) {
//            timeId = 0;
//        }
//        
//        
//        Double sales = new Double(0.00);
//        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
//        TdDiySite tdDiySite = tdDiySiteService.findbyUsername(username);
//        Page<TdOrder> orderPage = null;
//        
//        if (timeId.equals(0)) {
//			
//		}else if (timeId.equals(1)) {
//			
//		}else if (timeId.equals(2)) {
//			
//		}else if (timeId.equals(3)) {
//			
//		}else if (timeId.equals(4)) {
//			
//		}else if (timeId.equals(6)) {
//			
//		}else if (timeId.equals(12)) {
//			
//		}
//        map.addAttribute("time_id", timeId);
//        map.addAttribute("sales",sales);
//        map.addAttribute("page", page);
//        
//	}
	
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
