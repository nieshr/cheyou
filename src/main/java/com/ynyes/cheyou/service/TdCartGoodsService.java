package com.ynyes.cheyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.cheyou.entity.TdCartGoods;
import com.ynyes.cheyou.entity.TdGoods;
import com.ynyes.cheyou.repository.TdCartGoodsRepo;

/**
 * TdCartGoods 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdCartGoodsService {
    
    @Autowired
    TdCartGoodsRepo repository;
    
    @Autowired
    TdGoodsService tdGoodsService;
    /**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdCartGoods e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdCartGoods> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdCartGoods findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdCartGoods> findAll(Iterable<Long> ids)
    {
        return (List<TdCartGoods>) repository.findAll(ids);
    }
    
    public List<TdCartGoods> findAll()
    {
        return (List<TdCartGoods>) repository.findAll();
    }
    
    public Page<TdCartGoods> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
    
    public TdCartGoods findTopByGoodsIdAndQiangAndUsername(long goodsId, int qiang, String username)
    {
        if (null == username)
        {
            return null;
        }
        
        return repository.findTopByGoodsIdAndQiangAndUsername(goodsId, qiang, username);
    }
    
//    public List<TdCartGoods> findByGoodsIdAndPriceAndUsername(Long goodsId, Double price, String username)
//    {
//        if (null == goodsId || null == price || null == username)
//        {
//            return null;
//        }
//        
//        return repository.findByGoodsIdAndPriceAndUsername(goodsId, price, username);
//    }
    
    public List<TdCartGoods> findByGoodsIdAndQiangAndUsername(Long goodsId, Integer qiang, String username)
    {
        if (null == goodsId || null == qiang || null == username)
        {
            return null;
        }
        
        return repository.findByGoodsIdAndQiangAndUsername(goodsId, qiang, username);
    }
    
    public List<TdCartGoods> findByUsername(String username)
    {
        return repository.findByUsername(username);
    }
    
    public List<TdCartGoods> updateGoodsInfo(List<TdCartGoods> cartGoodsList)
    {
        if (null == cartGoodsList || cartGoodsList.size() < 0)
        {
            return null;
        }
        
        for (TdCartGoods cartGoods : cartGoodsList)
        {
            if (null != cartGoods)
            {
                TdGoods goods = tdGoodsService.findOne(cartGoods.getGoodsId());
                
                if (null != goods)
                {
                    cartGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
                    cartGoods.setGoodsTitle(goods.getTitle());
                    
                    // 更新价格
                    if (cartGoods.getQiang().equals(0))
                    {
                        cartGoods.setPrice(goods.getSalePrice());
                    }
                    // 秒杀价格以加入购物车时为准
                    else if (cartGoods.getQiang().equals(1))
                    {
                        
                    }
                    // 更新百人团价格
                    else if (cartGoods.getQiang().equals(100))
                    {
                        cartGoods.setPrice(goods.getGroupSalePrePayPrice());
                    }
                    // 更新十人团价格
                    else
                    {
                        cartGoods.setPrice(goods.getGroupSalePrice());
                    }
                }
            }
        }
        return cartGoodsList;
    }
    
    public List<TdCartGoods> findByUsernameAndIsSelectedTrue(String username)
    {
        return repository.findByUsernameAndIsSelectedTrue(username);
    }
    
    public void delete(Iterable<TdCartGoods> entities)
    {
        repository.delete(entities);
    }
    
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdCartGoods save(TdCartGoods e)
    {
        
        return repository.save(e);
    }
    
    public List<TdCartGoods> save(List<TdCartGoods> entities)
    {
        
        return (List<TdCartGoods>) repository.save(entities);
    }
}
