package com.ynyes.cheyou.touch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.entity.TdProduct;
import com.ynyes.cheyou.entity.TdProductCategory;
import com.ynyes.cheyou.entity.TdSetting;
import com.ynyes.cheyou.entity.TdUser;
import com.ynyes.cheyou.entity.TdUserConsult;
import com.ynyes.cheyou.entity.TdUserPoint;
import com.ynyes.cheyou.service.TdCommonService;
import com.ynyes.cheyou.service.TdDiySiteService;
import com.ynyes.cheyou.service.TdGoodsCombinationService;
import com.ynyes.cheyou.service.TdGoodsService;
import com.ynyes.cheyou.service.TdProductCategoryService;
import com.ynyes.cheyou.service.TdProductService;
import com.ynyes.cheyou.service.TdSettingService;
import com.ynyes.cheyou.service.TdUserCollectService;
import com.ynyes.cheyou.service.TdUserCommentService;
import com.ynyes.cheyou.service.TdUserConsultService;
import com.ynyes.cheyou.service.TdUserPointService;
import com.ynyes.cheyou.service.TdUserRecentVisitService;
import com.ynyes.cheyou.service.TdUserService;
import com.ynyes.cheyou.util.ClientConstant;

/**
 * 商品详情页
 * 
 * @author Sharon
 *
 */
@Controller
public class TdTouchGoodsController {
    @Autowired
    private TdGoodsService tdGoodsService;

    @Autowired
    private TdUserConsultService tdUserConsultService;

    @Autowired
    private TdUserCommentService tdUserCommentService;
    
    @Autowired
    private TdUserCollectService tdUserCollectService;

    @Autowired
    private TdProductCategoryService tdProductCategoryService;

    @Autowired
    private TdCommonService tdCommonService;

    @Autowired
    private TdGoodsCombinationService tdGoodsCombinationService;

    @Autowired
    private TdUserRecentVisitService tdUserRecentVisitService;

    @Autowired
    private TdSettingService tdSettingService;

    @Autowired
    private TdUserService tdUserService;

    @Autowired
    private TdProductService tdProductService;
    
    @Autowired
    private TdUserPointService tdUserPointService;
    
    @Autowired
    private TdDiySiteService tdDiySiteService;

    @RequestMapping("/touch/goods/{goodsId}")
    public String product(@PathVariable Long goodsId, Long shareId, Integer qiang,
            ModelMap map, HttpServletRequest req) {

        tdCommonService.setHeader(map, req);

        String username = (String) req.getSession().getAttribute("username");

        // 添加浏览记录
        if (null != username) {
            tdUserRecentVisitService.addNew(username, goodsId);
            map.addAttribute("user",
                    tdUserService.findByUsernameAndIsEnabled(username));
        }
        else
        {
            tdUserRecentVisitService.addNew(req.getSession().getId(), goodsId);
        }
        
        // 促销标志位
        map.addAttribute("qiang", qiang);
        
        // 读取浏览记录
        if (null == username)
        {
            map.addAttribute("recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(req.getSession().getId(), 0, ClientConstant.pageSize));
        }
        else
        {
            map.addAttribute("recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(username, 0, ClientConstant.pageSize));
        }
        
        if (null == goodsId) {
            return "error_404";
        }

        TdGoods goods = tdGoodsService.findOne(goodsId);
        
        if (null == goods)
        {
            return "error_404";
        }

        Page<TdUserConsult> consultPage = tdUserConsultService
                .findByGoodsIdAndIsShowable(goodsId, 0, ClientConstant.pageSize);

        // 商品
        map.addAttribute("goods", goods);
        
        // 商品组合
        map.addAttribute("comb_list",
                tdGoodsCombinationService.findByGoodsId(goodsId));
        
        // 全部评论
        map.addAttribute("comment_page", tdUserCommentService
                .findByGoodsIdAndIsShowable(goodsId, 0, ClientConstant.pageSize));
        
        // 全部评论数
        map.addAttribute("comment_count", tdUserCommentService
                .countByGoodsIdAndIsShowable(goodsId));
        
        // 好评数
        map.addAttribute("three_star_comment_count", tdUserCommentService
                .countByGoodsIdAndStarsAndIsShowable(goodsId, 3L));
        
        // 中评数
        map.addAttribute("two_star_comment_count", tdUserCommentService
                .countByGoodsIdAndStarsAndIsShowable(goodsId, 2L));
        
        // 差评数
        map.addAttribute("one_star_comment_count", tdUserCommentService
                .countByGoodsIdAndStarsAndIsShowable(goodsId, 1L));
        
        // 咨询
        map.addAttribute("consult_page", consultPage);

        // 热卖
        map.addAttribute("hot_list",
                tdGoodsService.findTop10ByIsOnSaleTrueOrderBySoldNumberDesc());
        
        // 同盟店
        map.addAttribute("diy_site_list",
                        tdDiySiteService.findByIsEnableTrue());
        
        // 收藏总数
        map.addAttribute("total_collects", tdUserCollectService.countByGoodsId(goods.getId()));

        // 查找类型
        TdProductCategory tdProductCategory = tdProductCategoryService
                .findOne(goods.getCategoryId());

        // 获取该类型所有父类型
        if (null != tdProductCategory) {
            if (null != tdProductCategory.getParentTree()
                    && !"".equals(tdProductCategory.getParentTree())) {
                List<TdProductCategory> catList = new ArrayList<TdProductCategory>();

                for (String cid : tdProductCategory.getParentTree().split(",")) {
                    if (!"".equals(cid)) {
                        // 去除方括号
                        cid = cid.replace("[", "");
                        cid = cid.replace("]", "");

                        TdProductCategory tpc = tdProductCategoryService
                                .findOne(Long.parseLong(cid));

                        if (null != tpc) {
                            catList.add(tpc);
                        }
                    }
                }

                map.addAttribute("category_tree_list", catList);
            }
        }
        
        // 获取商品的其他版本
        if (null != goods.getProductId()) {
            TdProduct product = tdProductService.findOne(goods.getProductId());

            if (null != product) {
                List<TdGoods> productGoodsList = tdGoodsService
                        .findByProductIdAndIsOnSaleTrue(goods.getProductId());

                // 总的规格总类数量
                int totalSelects = product.getTotalSelects();

                List<String> selectOneList = new ArrayList<String>();
                List<String> selectTwoList = new ArrayList<String>();
                List<String> selectThreeList = new ArrayList<String>();

                List<TdGoods> selectOneGoodsList = new ArrayList<TdGoods>();
                List<TdGoods> selectTwoGoodsList = new ArrayList<TdGoods>();
                List<TdGoods> selectThreeGoodsList = new ArrayList<TdGoods>();

                String sOne = null;
                String sTwo = null;
                String sThree = null;

                map.addAttribute("total_select", totalSelects);

                switch (totalSelects) {
                case 1:
                    // 规格一的值
                    sOne = goods.getSelectOneValue();
                    
                    if (null != sOne)
                    {
                        sOne = sOne.trim();
                    }

                    for (TdGoods pdtGoods : productGoodsList) {
                        // 其他同类商品规格一的值
                        String s1 = pdtGoods.getSelectOneValue().trim();

                        // 规格值不同时加入展示列表
                        if (!selectOneList.contains(s1)) {
                            selectOneList.add(s1);
                            selectOneGoodsList.add(pdtGoods);
                        }
                    }

                    map.addAttribute("select_one_name",
                            product.getSelectOneName());
                    map.addAttribute("one_selected", sOne);
                    map.addAttribute("select_one_goods_list",
                            selectOneGoodsList);

                    break;
                case 2:
                    // 规格一、 二的值
                    sOne = goods.getSelectOneValue();
                    
                    if (null != sOne)
                    {
                        sOne = sOne.trim();
                    }
                    
                    sTwo = goods.getSelectTwoValue();
                    
                    if (null != sTwo)
                    {
                        sTwo = sTwo.trim();
                    }

                    for (TdGoods pdtGoods : productGoodsList) {
                        // 其他商品规格一、二的值
                        String s1 = pdtGoods.getSelectOneValue().trim();
                        String s2 = pdtGoods.getSelectTwoValue().trim();

                        // 规格一不同商品
                        if (!selectOneList.contains(s1)) {
                            selectOneList.add(s1);
                            selectOneGoodsList.add(pdtGoods);
                        }

                        // 规格二不同， 规格一相同的商品
                        if (!selectTwoList.contains(s2)
                                && sOne.equalsIgnoreCase(s1)) {
                            selectTwoList.add(s2);
                            selectTwoGoodsList.add(pdtGoods);
                        }
                    }

                    map.addAttribute("select_one_name",
                            product.getSelectOneName());
                    map.addAttribute("select_two_name",
                            product.getSelectTwoName());
                    map.addAttribute("one_selected", sOne);
                    map.addAttribute("two_selected", sTwo);
                    map.addAttribute("select_one_goods_list",
                            selectOneGoodsList);
                    map.addAttribute("select_two_goods_list",
                            selectTwoGoodsList);
                    break;

                case 3:
                    // 规格一、二、三的值
                    sOne = goods.getSelectOneValue();
                    
                    if (null != sOne)
                    {
                        sOne = sOne.trim();
                    }
                    
                    sTwo = goods.getSelectTwoValue();
                    
                    if (null != sTwo)
                    {
                        sTwo = sTwo.trim();
                    }
                    
                    sThree = goods.getSelectThreeValue();
                    
                    if (null != sThree)
                    {
                        sThree = sThree.trim();
                    }

                    for (TdGoods pdtGoods : productGoodsList) {
                        // 其他商品规格一、二、三的值
                        String s1 = pdtGoods.getSelectOneValue().trim();
                        String s2 = pdtGoods.getSelectTwoValue().trim();
                        String s3 = pdtGoods.getSelectThreeValue().trim();

                        // 规格一不同商品
                        if (!selectOneList.contains(s1)) 
                        {
                            selectOneList.add(s1);
                            selectOneGoodsList.add(pdtGoods);
                        }

                        // 规格二不同， 规格一的商品
                        if (!selectTwoList.contains(s2)
                                && sOne.equalsIgnoreCase(s1)) {
                            selectTwoList.add(s2);
                            selectTwoGoodsList.add(pdtGoods);
                        }

                        // 规格三不同， 规格一、二相同的商品
                        if (!selectThreeList.contains(s3)
                                && sTwo.equalsIgnoreCase(s2)
                                && sOne.equalsIgnoreCase(s1)) {
                            selectThreeList.add(s3);
                            selectThreeGoodsList.add(pdtGoods);
                        }
                    }

                    map.addAttribute("select_one_name",
                            product.getSelectOneName());
                    map.addAttribute("select_two_name",
                            product.getSelectTwoName());
                    map.addAttribute("select_three_name",
                            product.getSelectThreeName());
                    map.addAttribute("one_selected", sOne);
                    map.addAttribute("two_selected", sTwo);
                    map.addAttribute("three_selected", sThree);
                    map.addAttribute("select_one_goods_list",
                            selectOneGoodsList);
                    map.addAttribute("select_two_goods_list",
                            selectTwoGoodsList);
                    map.addAttribute("select_three_goods_list",
                            selectThreeGoodsList);
                    break;
                }
            }
        }

        // 分享时添加积分
        if (null != shareId) {
            TdUser sharedUser = tdUserService.findOne(shareId);
            TdSetting setting = tdSettingService.findTopBy();

            String clientIp = req.getRemoteHost();
            String oldIp = (String) req.getSession().getAttribute("remote_ip");

            // 不是来自同一个ip的访问，普通用户
            if (!clientIp.equalsIgnoreCase(oldIp) && sharedUser.getRoleId().equals(0L)) {
                req.getSession().setAttribute("remote_ip", clientIp);

                if (null != sharedUser && null != setting) {
                    if (null == sharedUser.getPointGetByShareGoods()) {
                        sharedUser.setPointGetByShareGoods(0L);
                    }

                    if (null == setting.getGoodsShareLimits()) {
                        setting.setGoodsShareLimits(50L); // 设定一个默认值
                    }

                    // 小于积分限额，进行积分
                    if (sharedUser.getPointGetByShareGoods().compareTo(setting.getGoodsShareLimits()) < 0) {
                        TdUserPoint point = new TdUserPoint();
                        point.setDetail("分享商品获得积分");
                        point.setPoint(setting.getGoodsSharePoints());
                        point.setPointTime(new Date());
                        point.setUsername(sharedUser.getUsername());

                        if (null != sharedUser.getTotalPoints()) {
                            point.setTotalPoint(sharedUser.getTotalPoints()
                                    + point.getPoint());
                        } else {
                            point.setTotalPoint(point.getPoint());
                        }

                        point = tdUserPointService.save(point);

                        sharedUser.setTotalPoints(point.getTotalPoint()); // 积分
                        tdUserService.save(sharedUser);
                    }
                }
            }
        }

        map.addAttribute("server_ip", req.getLocalName());
        map.addAttribute("server_port", req.getLocalPort());

        return "/touch/goods";
    }

    @RequestMapping("/touch/goods/comment/{goodsId}")
    public String comments(@PathVariable Long goodsId, 
                    Integer page, 
                    Long stars,
                    ModelMap map, HttpServletRequest req) {
        tdCommonService.setHeader(map, req);
        
        if (null == goodsId)
        {
            return "/touch/goods_comment";
        }
        
        if (null == page)
        {
            page = 0;
        }
        
        if (null == stars)
        {
            stars = 0L;
        }
        
        // 全部评论数
        map.addAttribute("comment_count", tdUserCommentService
                .countByGoodsIdAndIsShowable(goodsId));
        
        // 好评数
        map.addAttribute("three_star_comment_count", tdUserCommentService
                .countByGoodsIdAndStarsAndIsShowable(goodsId, 3L));
        
        // 中评数
        map.addAttribute("two_star_comment_count", tdUserCommentService
                .countByGoodsIdAndStarsAndIsShowable(goodsId, 2L));
        
        // 差评数
        map.addAttribute("one_star_comment_count", tdUserCommentService
                .countByGoodsIdAndStarsAndIsShowable(goodsId, 1L));
        
        if (stars.equals(0L))
        {
            map.addAttribute("comment_page", tdUserCommentService
                    .findByGoodsIdAndIsShowable(goodsId, page, ClientConstant.pageSize));
        }
        else
        {
             map.addAttribute("comment_page", tdUserCommentService
                    .findByGoodsIdAndStarsAndIsShowable(goodsId, stars, page, ClientConstant.pageSize));
        }
        
        // 评论
        map.addAttribute("page", page);
        map.addAttribute("stars", stars);
        map.addAttribute("goodsId", goodsId);
        
        return "/touch/goods_comment";
    }
    
    @RequestMapping("/touch/goods/consult/{goodsId}")
    public String consults(@PathVariable Long goodsId, 
                            Integer page, 
                            ModelMap map, 
                            HttpServletRequest req) {
        tdCommonService.setHeader(map, req);
        
        if (null == goodsId)
        {
            return "error_404";
        }
        
        if (null == page)
        {
            page = 0;
        }
        
        Page<TdUserConsult> consultPage = tdUserConsultService
                .findByGoodsIdAndIsShowable(goodsId, page, ClientConstant.pageSize);
        
        // 咨询
        map.addAttribute("consult_page", consultPage);
        map.addAttribute("page", page);
        map.addAttribute("goodsId", goodsId);
        
        return "/touch/goods_consult";
    }
    
    @RequestMapping("/touch/goods/detail/{goodsId}")
    public String detail(@PathVariable Long goodsId,
                            ModelMap map, 
                            HttpServletRequest req) {
        
        tdCommonService.setHeader(map, req);
        
        if (null == goodsId)
        {
            return "/touch/goods_detail";
        }
        
        // 商品
        map.addAttribute("goods", tdGoodsService.findOne(goodsId));
        
        return "/touch/goods_detail";
    }
    
    @RequestMapping("/touch/goods/param/{goodsId}")
    public String param(@PathVariable Long goodsId,
                            ModelMap map, 
                            HttpServletRequest req) {
        
        tdCommonService.setHeader(map, req);
        
        if (null == goodsId)
        {
            return "/touch/goods_param";
        }
        
        // 商品
        map.addAttribute("goods", tdGoodsService.findOne(goodsId));
        
        return "/touch/goods_param";
    }
}
