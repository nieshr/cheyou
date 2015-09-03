package com.ynyes.cheyou.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.cheyou.entity.TdDiySite;
import com.ynyes.cheyou.entity.TdOrder;
import com.ynyes.cheyou.service.TdArticleCategoryService;
import com.ynyes.cheyou.service.TdArticleService;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdDiySiteService;
import com.ynyes.cheyou.service.TdOrderService;
import com.ynyes.cheyou.service.TdUserCommentService;
import com.ynyes.cheyou.service.TdUserRecentVisitService;
import com.ynyes.cheyou.util.ClientConstant;

/**
 * 
 * 同盟店
 *
 */
@Controller
@RequestMapping("/shop")
public class TdShopController {
	@Autowired 
	private TdArticleService tdArticleService;
	
	@Autowired 
    private TdArticleCategoryService tdArticleCategoryService;
	
	@Autowired 
    private TdDiySiteService tdDiySiteService;
	
	@Autowired 
    private TdOrderService tdOrderService;
	
    @Autowired
    private TdUserCommentService tdUserCommentService;
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
    private TdUserRecentVisitService tdUserRecentVisitService;
    
	@RequestMapping("/list")
    public String infoList(Integer page, 
                            ModelMap map,
                            Integer cid,
                            HttpServletRequest req){
	    
	    tdCommonService.setHeader(map, req);
        
        String username = (String) req.getSession().getAttribute("username");
        
        // 读取浏览记录
        if (null == username)
        {
            map.addAttribute("recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(req.getSession().getId(), 0, ClientConstant.pageSize));
        }
        else
        {
            map.addAttribute("recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(username, 0, ClientConstant.pageSize));
        }
        
        if (null == cid)
        {
            cid = 0;
        }
        
        String[] cityArray = {"昆明", "曲靖", "大理"};
        
        map.addAttribute("city_list", cityArray);
        map.addAttribute("cid", cid);
        
        
        List<TdDiySite> tdDiySitelist;
        switch (cid)
        {
        case 1:
            map.addAttribute("shop_list", tdDiySiteService.findByCityAndIsEnableTrueOrderBySortIdAsc("曲靖"));
            
            tdDiySitelist  = tdDiySiteService.findByCityAndIsEnableTrueOrderBySortIdAsc("曲靖");
            if (null != tdDiySitelist) {
            	 for (TdDiySite diySite : tdDiySitelist) 
                 {
                     List<TdOrder> tdOrders = tdOrderService.findByshopIdAndstatusId(diySite.getId(), 5L);
                     List<TdOrder> tdOrders1 = tdOrderService.findByshopIdAndstatusId(diySite.getId(), 6L);
                     map.addAttribute("shop_orderFinish_"+diySite.getId(), tdOrders.size()+tdOrders1.size());
                     map.addAttribute("shop_orderComment_"+diySite.getId(), tdDiySiteService.ContdiysiteComment(diySite.getId()));
                     map.addAttribute("shop_serviceStars"+diySite.getId(), tdDiySiteService.diysiteServiceStars(diySite.getId()));
                 }
			}
                   
            break;
            
        case 2:
            map.addAttribute("shop_list", tdDiySiteService.findByCityAndIsEnableTrueOrderBySortIdAsc("大理"));
            
            tdDiySitelist  = tdDiySiteService.findByCityAndIsEnableTrueOrderBySortIdAsc("大理");
            if (null != tdDiySitelist) {
            	 for (TdDiySite diySite : tdDiySitelist) 
                 {
                     List<TdOrder> tdOrders = tdOrderService.findByshopIdAndstatusId(diySite.getId(), 5L);
                     List<TdOrder> tdOrders1 = tdOrderService.findByshopIdAndstatusId(diySite.getId(), 6L);
                     
                     map.addAttribute("shop_orderFinish_"+diySite.getId(), tdOrders.size()+tdOrders1.size());
                     map.addAttribute("shop_orderComment_"+diySite.getId(), tdDiySiteService.ContdiysiteComment(diySite.getId()));
                     map.addAttribute("shop_serviceStars"+diySite.getId(), tdDiySiteService.diysiteServiceStars(diySite.getId()));
                 }
			}
            break;
            
        default:
          
            map.addAttribute("shop_list", tdDiySiteService.findByCityAndIsEnableTrueOrderBySortIdAsc("昆明"));
            tdDiySitelist  = tdDiySiteService.findByCityAndIsEnableTrueOrderBySortIdAsc("昆明");
            if (null != tdDiySitelist) {
            	 for (TdDiySite diySite : tdDiySitelist) 
                 {
                     List<TdOrder> tdOrders = tdOrderService.findByshopIdAndstatusId(diySite.getId(), 5L);
                     List<TdOrder> tdOrders1 = tdOrderService.findByshopIdAndstatusId(diySite.getId(), 6L);
                     map.addAttribute("shop_orderFinish_"+diySite.getId(), tdOrders.size()+tdOrders1.size());
                     map.addAttribute("shop_orderComment_"+diySite.getId(), tdDiySiteService.ContdiysiteComment(diySite.getId()));
//                     int a  = tdDiySiteService.ContdiysiteComment(diySite.getId());
                     map.addAttribute("shop_serviceStars"+diySite.getId(), tdDiySiteService.diysiteServiceStars(diySite.getId()));
                 }
			}
        }
        
        return "/client/shop_list";
    }
	
	@RequestMapping("/{id}")
    public String shop(@PathVariable Long id, Long stars, Integer page,
    		ModelMap map, HttpServletRequest req){
	    tdCommonService.setHeader(map, req);
	    
	    if (null == page) {
            page = 0;
        }

        if (null == stars) {
            stars = 0L;
        }
	    
	    map.addAttribute("shop", tdDiySiteService.findOne(id));
	    
	    // 全部评论数
        map.addAttribute("comment_count",
                tdUserCommentService.countByDiysiteIdAndIsShowable(id));

        // 好评数
        map.addAttribute("three_star_comment_count", tdUserCommentService
                .countByDiysiteIdAndStarsAndIsShowable(id, 3L));

        // 中评数
        map.addAttribute("two_star_comment_count", tdUserCommentService
                .countByDiysiteIdAndStarsAndIsShowable(id, 2L));

        // 差评数
        map.addAttribute("one_star_comment_count", tdUserCommentService
                .countByDiysiteIdAndStarsAndIsShowable(id, 1L));
        
        // 全部评论
        map.addAttribute("comment_page",
                tdUserCommentService.findByDiysiteIdAndIsShowable(id, 0,
                        ClientConstant.pageSize));
        
        map.addAttribute("diysiteId", id);
        return "/client/shop_detail";
    }
	
	 @RequestMapping("/comment/{diysiteId}")
	    public String comments(@PathVariable Long diysiteId, Integer page,
	            Long stars, ModelMap map, HttpServletRequest req) {

	        if (null == diysiteId) {
	            return "error_404";
	        }

	        if (null == page) {
	            page = 0;
	        }

	        if (null == stars) {
	            stars = 0L;
	        }

	        // 全部评论数
	        map.addAttribute("comment_count",
	                tdUserCommentService.countByDiysiteIdAndIsShowable(diysiteId));

	        // 好评数
	        map.addAttribute("three_star_comment_count", tdUserCommentService
	                .countByDiysiteIdAndStarsAndIsShowable(diysiteId, 3L));

	        // 中评数
	        map.addAttribute("two_star_comment_count", tdUserCommentService
	                .countByDiysiteIdAndStarsAndIsShowable(diysiteId, 2L));

	        // 差评数
	        map.addAttribute("one_star_comment_count", tdUserCommentService
	                .countByDiysiteIdAndStarsAndIsShowable(diysiteId, 1L));

	        if (stars.equals(0L)) {
	            map.addAttribute("comment_page", tdUserCommentService
	                    .findByDiysiteIdAndIsShowable(diysiteId, page,
	                            ClientConstant.pageSize));
	        } else {
	            map.addAttribute("comment_page", tdUserCommentService
	                    .findByDiysiteIdAndStarsAndIsShowable(diysiteId, stars, page,
	                            ClientConstant.pageSize));
	        }

	        // 评论
	        map.addAttribute("page", page);
	        map.addAttribute("stars", stars);
	        map.addAttribute("diysiteId", diysiteId);

	        return "/client/diysite_comment";
	    }
}
