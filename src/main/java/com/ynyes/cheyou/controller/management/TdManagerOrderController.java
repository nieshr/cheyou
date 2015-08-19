package com.ynyes.cheyou.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.cheyou.entity.TdDeliveryType;
import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.entity.TdOrderGoods;
import com.ynyes.cheyou.entity.TdPayType;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.entity.TdUserPoint;
import com.ynyes.cheyou.service.TdArticleService;
import com.ynyes.cheyou.service.TdDeliveryTypeService;
import com.ynyes.cheyou.service.TdDiySiteService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdManagerLogService;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdPayTypeService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.service.TdUserPointService;
import com.ynyes.cheyou.service.TdUserService;
import com.ynyes.cheyou.util.SMSUtil;
import com.ynyes.cheyou.util.SiteMagConstant;

import scala.xml.dtd.PublicID;

import org.apache.activemq.blob.DefaultBlobDownloadStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.jasper.tagplugins.jstl.core.Import;
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * 后台首页控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/order")
public class TdManagerOrderController {
    
    @Autowired
    TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdGoodsService tdGoodsService;
    
    @Autowired
    TdPayTypeService tdPayTypeService;
    
    @Autowired
    TdDeliveryTypeService tdDeliveryTypeService;
    
    @Autowired
    TdDiySiteService tdDiySiteService;
    
    @Autowired
    TdUserPointService tdUserPointService;
    
    @Autowired
    TdOrderService tdOrderService;
    
    @Autowired
    TdUserService tdUserService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    // 订单设置
    @RequestMapping(value="/setting/{type}/list")
    public String setting(@PathVariable String type, 
                          Integer page,
                          Integer size,
                          String keywords,
                          String __EVENTTARGET,
                          String __EVENTARGUMENT,
                          String __VIEWSTATE,
                          Long[] listId,
                          Integer[] listChkId,
                          Long[] listSortId,
                          ModelMap map,
                          HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(type, listId, listChkId);
                
                if (type.equalsIgnoreCase("pay"))
                {
                    tdManagerLogService.addLog("delete", "删除支付方式", req);
                }
                else if (type.equalsIgnoreCase("delivery"))
                {
                    tdManagerLogService.addLog("delete", "删除配送方式", req);
                }
                else if (type.equalsIgnoreCase("diysite"))
                {
                    tdManagerLogService.addLog("delete", "删除自提点", req);
                }
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(type, listId, listSortId);
                
                if (type.equalsIgnoreCase("pay"))
                {
                    tdManagerLogService.addLog("edit", "修改支付方式", req);
                }
                else if (type.equalsIgnoreCase("delivery"))
                {
                    tdManagerLogService.addLog("edit", "修改配送方式", req);
                }
                else if (type.equalsIgnoreCase("diysite"))
                {
                    tdManagerLogService.addLog("edit", "修改自提点", req);
                }
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
                
        if (null != type)
        {
            if (type.equalsIgnoreCase("pay")) // 支付方式
            {
                if (null == keywords)
                {
                    map.addAttribute("pay_type_page", 
                            tdPayTypeService.findAllOrderBySortIdAsc(page, size));
                }
                else
                {
                    map.addAttribute("pay_type_page", 
                            tdPayTypeService.searchAllOrderBySortIdAsc(keywords, page, size));
                }
                
                return "/site_mag/pay_type_list";
            }
            else if (type.equalsIgnoreCase("delivery")) // 配送方式
            {
                if (null == keywords)
                {
                    map.addAttribute("delivery_type_page", 
                            tdDeliveryTypeService.findAllOrderBySortIdAsc(page, size));
                }
                else
                {
                    map.addAttribute("delivery_type_page", 
                            tdDeliveryTypeService.searchAllOrderBySortIdAsc(keywords, page, size));
                }
                
                return "/site_mag/delivery_type_list";
            }
            else if (type.equalsIgnoreCase("diysite")) // 配送方式
            {
                if (null == keywords)
                {
                    map.addAttribute("diy_site_page", 
                            tdDiySiteService.findAllOrderBySortIdAsc(page, size));
                }
                else
                {
                    map.addAttribute("diy_site_page", 
                            tdDiySiteService.searchAllOrderBySortIdAsc(keywords, page, size));
                }
                
                return "/site_mag/diy_site_list";
            }
        }
        
        return "/site_mag/pay_type_list";
    }
    
    // 订单设置编辑
    @RequestMapping(value="/setting/{type}/edit")
    public String edit(@PathVariable String type, 
                        Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null != type)
        {
            if (type.equalsIgnoreCase("pay")) // 支付方式
            {
                if (null != id)
                {
                    map.addAttribute("pay_type", tdPayTypeService.findOne(id));
                }
                
                return "/site_mag/pay_type_edit";
            }
            else if (type.equalsIgnoreCase("delivery")) // 配送方式
            {
                if (null != id)
                {
                    map.addAttribute("delivery_type", tdDeliveryTypeService.findOne(id));
                }
                
                return "/site_mag/delivery_type_edit";
            }
            else if (type.equalsIgnoreCase("diysite")) // 自提点
            {
                if (null != id)
                {
                    map.addAttribute("diy_site", tdDiySiteService.findOne(id));
                }
                
                return "/site_mag/diy_site_edit";
            }
        }
        
        return "/site_mag/pay_type_edit";
    }
    
    // 订单设置编辑
    @RequestMapping(value = "/setting/diysite/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(String param, Long id) {
        Map<String, String> res = new HashMap<String, String>();

        res.put("status", "n");

        if (null == param || param.isEmpty()) {
            res.put("info", "该字段不能为空");
            return res;
        }
        
        TdUser tdUser = tdUserService.findByUsername(param);
        
        if (null == id) // 新增
        {
            if (null != tdUser) {
                res.put("info", "该登录名不能使用");
                return res;
            }
        } 
        else // 修改，查找除当前ID的所有
        {
            TdDiySite dSite = tdDiySiteService.findOne(id);
            
            if (null == dSite)
            {
                if (null != tdUser && tdUser.getRoleId() == 2L) {
                    res.put("info", "该登录名不能使用");
                    return res;
                }
            }
            else
            {
                if (null != tdUser && tdUser.getUsername() != dSite.getUsername() && tdUser.getRoleId()!=2L) {
                    res.put("info", "该登录名不能使用");
                    return res;
                }
            }
        }

        res.put("status", "y");

        return res;
    }
    
    @RequestMapping(value="/edit")
    public String orderEdit(Long id,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
        if (null != id)
        {
            map.addAttribute("order", tdOrderService.findOne(id));
        }
        return "/site_mag/order_edit";
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdOrder tdOrder,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
        
        tdOrderService.save(tdOrder);
        
        tdManagerLogService.addLog("edit", "修改订单", req);
        
        return "redirect:/Verwalter/order/list/"+statusId;
    }
    
    
    // 订单列表
    @SuppressWarnings("deprecation")
	@RequestMapping(value="/list/{statusId}/{type}")
    public String goodsListDialog(String keywords,
                                @PathVariable Long statusId,
                                @PathVariable Long type,
                                Integer page, 
                                Integer size,
                                Integer timeId,
                                String __EVENTTARGET,
                                String __EVENTARGUMENT,
                                String __VIEWSTATE,
                                Long[] listId,
                                Integer[] listChkId,
                                ModelMap map,
                                String exportUrl,
//                                String dateId,
                                HttpServletResponse resp,
                                HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnCancel"))
            {
                btnCancel(listId, listChkId);
                tdManagerLogService.addLog("cancel", "取消订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnConfirm"))
            {
                btnConfirm(listId, listChkId);
                tdManagerLogService.addLog("confirm", "确认订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("export"))
            {
            	exportUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("export", "导出订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        if (null == timeId) {
            timeId = 0;
        }
        
        
        /**
  		 * @author lc
  		 * @注释：根据不同条件导出excel文件
  		 */
          // 第一步，创建一个webbook，对应一个Excel文件  
          HSSFWorkbook wb = new HSSFWorkbook();  
          // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
          HSSFSheet sheet = wb.createSheet("order");  
          // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
          HSSFRow row = sheet.createRow((int) 0);  
          // 第四步，创建单元格，并设置值表头 设置表头居中  
          HSSFCellStyle style = wb.createCellStyle();  
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          
          HSSFCell cell = row.createCell((short) 0);  
          cell.setCellValue("订单号");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);  
          cell.setCellValue("会员账号");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 2);  
          cell.setCellValue("支付方式");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);  
          cell.setCellValue("配送方式");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 4);  
          cell.setCellValue("订单状态");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 5);  
          cell.setCellValue("总金额");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 6);  
          cell.setCellValue("下单时间");  
          cell.setCellStyle(style);
        
        /**
		 * @author lc
		 * @注释：时间删选
		 */
        Double price = new Double(0.00);
        Double sales = new Double(0.00);
        if (timeId.equals(0)) {
        	if (null != statusId) {
    			if (statusId.equals(0L)) {				
                	if (type.equals(0L)) {
                		List<TdOrder> list = tdOrderService.findAll();                   	
                    	price = countprice(list);
                    	sales = countsales(list);
                    	map.addAttribute("order_page", tdOrderService.findAllOrderByIdDesc(page, size));
                    	if (null != exportUrl) {
                    		Page<TdOrder> tdOrderPage = tdOrderService.findAllOrderByIdDesc(page, size);
                          	
                          	if (ImportData(tdOrderPage, row, cell, sheet)) {
                          		download(wb, username, resp);
							}                          	                          
						}
    				}
                	else {
                		List<TdOrder> list = tdOrderService.findBytypeIdOrderByIdDesc(type);
                		price = countprice(list);
                    	sales = countsales(list);
                		map.addAttribute("order_page", tdOrderService.findBytypeIdOrderByIdDesc(type, page, size));
                		if (null != exportUrl) {
                    		Page<TdOrder> tdOrderPage = tdOrderService.findBytypeIdOrderByIdDesc(type, page, size);
                          	
                          	if (ImportData(tdOrderPage, row, cell, sheet)) {
                          		download(wb, username, resp);
							}                         	                           
						}
    				}				
    			}else{
    				if (type.equals(0L)) {
    					List<TdOrder> list = tdOrderService.findByStatusOrderByIdDesc(statusId);
    					price = countprice(list);
                    	sales = countsales(list);
    					map.addAttribute("order_page", tdOrderService.findByStatusOrderByIdDesc(statusId, page, size));
    					if (null != exportUrl) {
                    		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusOrderByIdDesc(statusId, page, size);
                          	
                          	if (ImportData(tdOrderPage, row, cell, sheet)) {
                          		download(wb, username, resp);
							}                         	                           
						}
    				}
    				else{					
    					List<TdOrder> list = tdOrderService.findByStatusAndTypeIdOrderByIdDesc(statusId, type);
    					price = countprice(list);
                    	sales = countsales(list);
    	        		map.addAttribute("order_page", tdOrderService.findByStatusAndTypeOrderByIdDesc(statusId, type, page, size));
    	        		if (null != exportUrl) {
                    		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTypeOrderByIdDesc(statusId, type, page, size);
                          	
                          	if (ImportData(tdOrderPage, row, cell, sheet)) {
                          		download(wb, username, resp);
							}                         	                           
						}
    				}
    				
    			}
    		}
		}
        else if (timeId.equals(1)) {
        	Date cur = new Date(); 
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
          //  calendar.add(Calendar.MONTH, -1);// 月份减一
          //  calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date time = calendar.getTime();
            time.setHours(0);
            time.setMinutes(0);            
        	if (statusId.equals(0L)) {
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByTimeAfterOrderByIdDesc(time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
        	else{
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findByStatusAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
		}
        else if (timeId.equals(2)) {
        	Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
          //  calendar.add(Calendar.MONTH, -1);// 月份减一
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            if (statusId.equals(0L)) {
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByTimeAfterOrderByIdDesc(time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
        	else{
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findByStatusAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
		}
        else if (timeId.equals(3)) {
        	Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -1);// 月份减一
           // calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            if (statusId.equals(0L)) {
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByTimeAfterOrderByIdDesc(time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
        	else{
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findByStatusAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
		}
        else if (timeId.equals(4)) {
        	Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -3);// 月份减一
           // calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            if (statusId.equals(0L)) {
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByTimeAfterOrderByIdDesc(time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
        	else{
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findByStatusAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
		}
        else if (timeId.equals(6)) {
        	Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -6);// 月份减一
           // calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            if (statusId.equals(0L)) {
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByTimeAfterOrderByIdDesc(time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
        	else{
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findByStatusAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
		}
        else if (timeId.equals(12)) {
        	Date cur = new Date();
            Calendar calendar = Calendar.getInstance();// 日历对象
            calendar.setTime(cur);// 设置当前日期
            calendar.add(Calendar.MONTH, -12);// 月份减一
           // calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date time = calendar.getTime();
            if (statusId.equals(0L)) {
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByTimeAfterOrderByIdDesc(time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByTimeAfterOrderByIdDesc(time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findBytypeIdAndTimeAfterOrderByIdDesc(type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
        	else{
        		if (type.equals(0L)) {
        			List<TdOrder> list = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTimeAfterOrderByIdDesc(statusId, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        		else{
        			List<TdOrder> list = tdOrderService.findByStatusAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time);
        			price = countprice(list);
                	sales = countsales(list);
	        		map.addAttribute("order_page", tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size));
	        		if (null != exportUrl) {
                		Page<TdOrder> tdOrderPage = tdOrderService.findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(statusId, type, time, page, size);
                      	
                      	if (ImportData(tdOrderPage, row, cell, sheet)) {
                      		download(wb, username, resp);
						}                         	                           
					}
        		}
        	}
		}       
        // 参数注回
//        map.addAttribute("dateId",dateId);
        map.addAttribute("price",price);
        map.addAttribute("sales",sales);
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("statusId", statusId);
        /**
		 * @author lc
		 * @注释：添加时间删选参数
		 */
        map.addAttribute("time_id", timeId);
        map.addAttribute("type", type);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        return "/site_mag/order_list";
    }
    /**
	 * @author lc
	 * @注释：将page中的订单数据存入excel表格中
	 */
    @SuppressWarnings("deprecation")
	public boolean ImportData(Page<TdOrder> tdOrderPage, HSSFRow row, HSSFCell cell, HSSFSheet sheet){
    	for (int i = 0; i < tdOrderPage.getContent().size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            TdOrder tdOrder = tdOrderPage.getContent().get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(tdOrder.getOrderNumber());  
            row.createCell((short) 1).setCellValue(tdOrder.getUsername());  
            row.createCell((short) 2).setCellValue(tdOrder.getPayTypeTitle());
            row.createCell((short) 3).setCellValue(tdOrder.getDeliverTypeTitle());
            if (tdOrder.getStatusId().equals(2L)) {
            	row.createCell((short) 4).setCellValue("待付款");
			}else if (tdOrder.getStatusId().equals(3L)) {
				row.createCell((short) 4).setCellValue("待付尾款");
			}else if (tdOrder.getStatusId().equals(4L)) {
				row.createCell((short) 4).setCellValue("待服务");
			}else if (tdOrder.getStatusId().equals(5L)) {
				row.createCell((short) 4).setCellValue("待评价 ");
			}else if (tdOrder.getStatusId().equals(6L)) {
				row.createCell((short) 4).setCellValue("已完成");
			}else if (tdOrder.getStatusId().equals(7L)) {
				row.createCell((short) 4).setCellValue("已取消");
			}else if (tdOrder.getStatusId().equals(8L)) {
				row.createCell((short) 4).setCellValue("支付取消(失败)");
			}else if (tdOrder.getStatusId().equals(9L)) {
				row.createCell((short) 4).setCellValue("已删除");
			}
            
            row.createCell((short) 5).setCellValue(tdOrder.getTotalPrice());
            cell = row.createCell((short) 6);  
            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(tdOrder.getOrderTime()));                                
      
        } 
    	return true;
    }
    /**
	 * @author lc
	 * @注释：文件写入和下载
	 */
    public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
    	 try  
         {  
	          FileOutputStream fout = new FileOutputStream(exportUrl+"order.xls");  
	          OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");	                       	     
	          wb.write(fout);  
	          fout.close();
         }catch (Exception e)  
         {  
             e.printStackTrace();  
         } 
    	 OutputStream os;
		 try {
				os = resp.getOutputStream();
				File file = new File(exportUrl + "order.xls");
                 
             if (file.exists())
                 {
                   try {
                         resp.reset();
                         resp.setHeader("Content-Disposition", "attachment; filename="
                                 + "order.xls");
                         resp.setContentType("application/octet-stream; charset=utf-8");
                         os.write(FileUtils.readFileToByteArray(file));
                         os.flush();
                     } finally {
                         if (os != null) {
                             os.close();
                         }
                     }
             }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		 return true;	
    }
    /**
	 * @author lc
	 * @注释：计算总额和销售额
	 */
    public Double countprice(List<TdOrder> list){
    	Double price = new Double(0.00);       
    	for (int i = 0; i < list.size(); i++) {
    		price += list.get(i).getTotalPrice();
    	}
    	return price;
    }
    public Double countsales(List<TdOrder> list){
    	Double sales = new Double(0.00);
    	for(int i = 0; i < list.size(); i++){
    		if (list.get(i).getStatusId().equals(2L) || list.get(i).getStatusId().equals(7L)) {	
    			
			}
    		else{
    			sales += list.get(i).getTotalPrice();
    		}
    	}
    	return sales;
    }
    
    @RequestMapping(value="/setting/pay/save", method = RequestMethod.POST)
    public String save(TdPayType tdPayType,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == tdPayType.getId())
        {
            tdManagerLogService.addLog("add", "新增支付方式", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改支付方式", req);
        }
        tdPayTypeService.save(tdPayType);
        
        return "redirect:/Verwalter/order/setting/pay/list";
    }
    
    @RequestMapping(value="/setting/delivery/save", method = RequestMethod.POST)
    public String save(TdDeliveryType tdDeliveryType,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == tdDeliveryType.getId())
        {
            tdManagerLogService.addLog("add", "新增配送方式", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改配送方式", req);
        }
        
        tdDeliveryTypeService.save(tdDeliveryType);
        
        return "redirect:/Verwalter/order/setting/delivery/list";
    }
    
    @RequestMapping(value="/setting/diysite/save", method = RequestMethod.POST)
    public String save(TdDiySite tdDiySite,
                        String[] hid_photo_name_show360,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        String uris = parsePicUris(hid_photo_name_show360);
        
        tdDiySite.setShowPictures(uris);
        
        if (null == tdDiySite.getId())
        {
            tdManagerLogService.addLog("add", "新增同盟店", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改同盟店", req);
        }
        
        tdDiySiteService.save(tdDiySite);
        
        return "redirect:/Verwalter/order/setting/diysite/list";
    }
    
    @RequestMapping(value="/dialog/contact")
    public String addressDialog(ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        return "/site_mag/dialog_contact";
    }
    
    @RequestMapping(value="/dialog/delivery")
    public String sendDialog(String orderNumber, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != orderNumber && !orderNumber.isEmpty())
        {
            map.addAttribute("order", tdOrderService.findByOrderNumber(orderNumber));
        }
        
        map.addAttribute("delivery_type_list", tdDeliveryTypeService.findByIsEnableTrue());
        
        return "/site_mag/dialog_delivery";
    }
    
    @RequestMapping(value="/dialog/print")
    public String printDialog(String orderNumber, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != orderNumber && !orderNumber.isEmpty())
        {
            TdOrder order = tdOrderService.findByOrderNumber(orderNumber);
            map.addAttribute("order", order);
            map.addAttribute("now", new Date());
            map.addAttribute("manager", req.getSession().getAttribute("manager"));
            
            if (null != order)
            {
                map.addAttribute("user", tdUserService.findByUsernameAndIsEnabled(order.getUsername()));
            }
        }
        
        return "/site_mag/dialog_order_print";
    }
    
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
        
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            res.put("message", "请重新登录");
            return res;
        }
        
        if (null != orderNumber && !orderNumber.isEmpty() && null != type && !type.isEmpty())
        {
            TdOrder order = tdOrderService.findByOrderNumber(orderNumber);
            
            // 修改备注
            if (type.equalsIgnoreCase("editMark"))
            {
                order.setRemarkInfo(data);
            }
            // 修改商品总金额
            else if (type.equalsIgnoreCase("editTotalGoodsPrice"))
            {
                double goodsPrice = Double.parseDouble(data);
                order.setTotalGoodsPrice(goodsPrice);
                order.setTotalPrice(goodsPrice + order.getPayTypeFee() + order.getDeliverTypeFee());
            }
            // 修改配送费用
            else if (type.equalsIgnoreCase("editDeliveryPrice"))
            {
                double deliveryPrice = Double.parseDouble(data);
                order.setDeliverTypeFee(deliveryPrice);
                order.setTotalPrice(deliveryPrice + order.getPayTypeFee() + order.getTotalGoodsPrice());
            }
            // 修改支付手续费
            else if (type.equalsIgnoreCase("editPayPrice"))
            {
                double payPrice = Double.parseDouble(data);
                order.setPayTypeFee(payPrice);
                order.setTotalPrice(payPrice + order.getTotalGoodsPrice() + order.getDeliverTypeFee());
            }
            // 修改联系方式
            else if (type.equalsIgnoreCase("editContact"))
            {
                order.setShippingName(name);
                order.setShippingAddress(address);
                order.setShippingPhone(mobile);
                order.setPostalCode(postal);
            }
            // 确认订单
            else if (type.equalsIgnoreCase("orderConfirm"))
            {
                if (order.getStatusId().equals(1L))
                {
                    order.setStatusId(2L);
                    order.setCheckTime(new Date());
                }
            }
            // 确认付款
            else if (type.equalsIgnoreCase("orderPay"))
            {
                if (order.getStatusId().equals(2L))
                {
                    // 需付尾款
                    if (null != order.getTotalLeftPrice() && order.getTotalLeftPrice() > 0)
                    {
                        order.setStatusId(3L);
                    }
                    // 不需付尾款，直接跳到可到店服务
                    else
                    {
                        order.setStatusId(4L);
                    }

                    order.setPayTime(new Date());
                }
            }
            // 确认付尾款
            else if (type.equalsIgnoreCase("orderPayLeft"))
            {
                if (order.getStatusId().equals(3L))
                {
                    order.setStatusId(4L);
                    order.setPayLeftTime(new Date());
                    /**
					 * @author lc
					 * @注释：添加同盟店所获返利
					 */
                    // 用户
                    TdUser tdUser = tdUserService.findByUsername(order.getUsername());

                    // 同盟店
                    TdDiySite tdShop = null;
                    if (null != tdUser.getUpperDiySiteId()) {
                    	// 同盟店
                        tdShop = tdDiySiteService.findOne(tdUser.getUpperDiySiteId());
            		}else{
            			// 同盟店
            	        tdShop = tdDiySiteService.findOne(order.getShopId());
            		}
                    List<TdOrderGoods> tdOrderGoodsList = order.getOrderGoodsList();

                    Long totalPoints = 0L;
                    Double totalCash = 0.0;

                    // 返利总额
                    if (null != tdOrderGoodsList) {
                        for (TdOrderGoods tog : tdOrderGoodsList) {
                            if (0 == tog.getGoodsSaleType()) // 正常销售
                            {
                                TdGoods tdGoods = tdGoodsService.findOne(tog.getGoodsId());

                                if (null != tdGoods && null != tdGoods.getReturnPoints()) {
                                    totalPoints += tdGoods.getReturnPoints();

                                    if (null != tdGoods.getShopReturnRation()) {
                                        totalCash = tdGoods.getSalePrice()
                                                * tdGoods.getShopReturnRation();
                                    }
                                }
                            }
                        }

                        // 用户返利
                        if (null != tdUser) {
                            TdUserPoint userPoint = new TdUserPoint();

                            userPoint.setDetail("购买商品赠送粮草");
                            userPoint.setOrderNumber(order.getOrderNumber());
                            userPoint.setPoint(totalPoints);
                            userPoint.setPointTime(new Date());
                            userPoint.setTotalPoint(tdUser.getTotalPoints() + totalPoints);
                            userPoint.setUsername(tdUser.getUsername());

                            userPoint = tdUserPointService.save(userPoint);

                            tdUser.setTotalPoints(userPoint.getTotalPoint());

                            tdUserService.save(tdUser);
                        }
                    }

                    // 同盟店返利
                    if (null != tdShop) {
                        if (null == tdShop.getTotalCash()) {
                            tdShop.setTotalCash(totalCash);
                        } else {
                            tdShop.setTotalCash(tdShop.getTotalCash() + totalCash);
                        }
                        order.setRebate(totalCash);//设置订单同盟店所获返利
                        order = tdOrderService.save(order);
                        tdDiySiteService.save(tdShop);
                    }
                }
            }
            // 确认已服务
            else if (type.equalsIgnoreCase("orderService"))
            {
                if (order.getStatusId().equals(4L))
                {
                    order.setStatusId(5L);
                    order.setServiceTime(new Date());
                }
            }
            // 货到付款确认付款
            else if (type.equalsIgnoreCase("orderPayOffline"))
            {
                if (order.getStatusId().equals(2L)
                        && !order.getIsOnlinePay())
                {
                    order.setStatusId(5L);
                    order.setPayTime(new Date());
                }
            }
            // 确认发货
            else if (type.equalsIgnoreCase("orderDelivery"))
            {
                if (order.getStatusId().equals(3L))
                {
                    order.setDeliverTypeId(deliverTypeId);
                    order.setExpressNumber(expressNumber);
                    order.setStatusId(4L);
                    order.setSendTime(new Date());
                    
                    TdUser tdUser = tdUserService.findByUsername(order.getUsername());
                    
                    if (null != tdUser)
                    {
                        SMSUtil.send(tdUser.getMobile(), "28744",
                                new String[] { order.getUsername(),
                                        order.getOrderNumber()});
                    }
                }
            }
            // 确认收货
            else if (type.equalsIgnoreCase("orderReceive"))
            {
                if (order.getStatusId().equals(4L))
                {
                    order.setStatusId(5L);
                    order.setReceiveTime(new Date());
                }
            }
            // 确认完成
            else if (type.equalsIgnoreCase("orderFinish"))
            {
                if (order.getStatusId().equals(5L))
                {
                    order.setStatusId(6L);
                    order.setFinishTime(new Date());
                    
                    tdUserService.addTotalSpend(order.getUsername(), order.getTotalPrice());
                }
            }
            // 确认取消
            else if (type.equalsIgnoreCase("orderCancel"))
            {
                if (order.getStatusId().equals(1L) ||
                        order.getStatusId().equals(2L))
                {
                    order.setStatusId(7L);
                    order.setCancelTime(new Date());
                }
            }
            
            tdOrderService.save(order);
            tdManagerLogService.addLog("edit", "修改订单", req);
            
            res.put("code", 0);
            res.put("message", "修改成功!");
            return res;
        }
        
        res.put("message", "参数错误!");
        return res;
    }
    
    @RequestMapping(value = "order/sumPrice" , method = RequestMethod.GET)
    public String sumPrice(String date,String date1,HttpServletRequest request){
    	
    	
    	return "/";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @ModelAttribute
    public void getModel(@RequestParam(value = "payTypeId", required = false) Long payTypeId,
                    @RequestParam(value = "deliveryTypeId", required = false) Long deliveryTypeId,
                    @RequestParam(value = "diySiteId", required = false) Long diySiteId,
                        Model model) {
        if (null != payTypeId) {
            model.addAttribute("tdPayType", tdPayTypeService.findOne(payTypeId));
        }
        
        if (null != deliveryTypeId) {
            model.addAttribute("tdDeliveryType", tdDeliveryTypeService.findOne(deliveryTypeId));
        }
        
        if (null != diySiteId) {
            model.addAttribute("tdDiySite", tdDiySiteService.findOne(diySiteId));
        }
    }
    
    private void btnSave(String type, Long[] ids, Long[] sortIds)
    {
        if (null == type || type.isEmpty())
        {
            return;
        }
        
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            if (type.equalsIgnoreCase("pay"))
            {
                TdPayType e = tdPayTypeService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdPayTypeService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("delivery"))
            {
                TdDeliveryType e = tdDeliveryTypeService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdDeliveryTypeService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("diysite"))
            {
                TdDiySite e = tdDiySiteService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdDiySiteService.save(e);
                    }
                }
            }
        }
    }
    
    private void btnDelete(String type, Long[] ids, Integer[] chkIds)
    {
        if (null == type || type.isEmpty())
        {
            return;
        }
        
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                if (type.equalsIgnoreCase("pay"))
                {
                    tdPayTypeService.delete(id);
                }
                else if (type.equalsIgnoreCase("delivery"))
                {
                    tdDeliveryTypeService.delete(id);
                }
                else if (type.equalsIgnoreCase("diysite"))
                {
                    tdDiySiteService.delete(id);
                }
            }
        }
    }
    
    private void btnConfirm(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有待确认(1L)订单能进行确认，确认后状态为待发货(3L)
                if (tdOrder.getStatusId().equals(1L))
                {
                    tdOrder.setStatusId(3L);
                    tdOrder.setCheckTime(new Date()); // 确认时间
                    tdOrderService.save(tdOrder);
                }
            }
        }
    }
    
    private void btnCancel(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有待确认(1L)、待付款(2L)订单能进行删除，确认后状态为已取消(7L)
                if (tdOrder.getStatusId().equals(1L) ||
                        tdOrder.getStatusId().equals(2L))
                {
                    tdOrder.setStatusId(7L);
                    tdOrder.setCancelTime(new Date()); // 取消时间
                    tdOrderService.save(tdOrder);
                }
            }
        }
    }
    
    private void btnDelete(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有已取消(7L)订单能进行删除
                if (tdOrder.getStatusId().equals(7L))
                {
                    tdOrderService.delete(tdOrder);
                }
            }
        }
    }
    
    /**
     * 图片地址字符串整理，多张图片用,隔开
     * 
     * @param params
     * @return
     */
    private String parsePicUris(String[] uris)
    {
        if (null == uris || 0 == uris.length)
        {
            return null;
        }
        
        String res = "";
        
        for (String item : uris)
        {
            String uri = item.substring(item.indexOf("|")+1, item.indexOf("|", 2));
            
            if (null != uri)
            {
                res += uri;
                res += ",";
            }
        }
        
        return res;
    }
}
