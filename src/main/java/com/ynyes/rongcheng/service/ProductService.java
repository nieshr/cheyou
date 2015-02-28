package com.ynyes.rongcheng.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ynyes.rongcheng.entity.Product;
import com.ynyes.rongcheng.entity.ProductCombination;
import com.ynyes.rongcheng.entity.ProductParameter;
import com.ynyes.rongcheng.entity.ProductType;
import com.ynyes.rongcheng.entity.ProductVersion;
import com.ynyes.rongcheng.repository.ProductRepo;
import com.ynyes.rongcheng.util.ImageUtil;

/**
 * 商品服务类
 * 
 * @author Sharon
 */

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepo repository;
    
    /**
     * 根据类型获取商品，并进行分页
     * 
     * @param type 商品类型名
     * @param page 页号，从0开始
     * @param size 每页大小
     * @param direction 排序方向，不区分大小写，asc表示升序，desc表示降序，为NULL时不进行排序
     * @param property 排序的字段名，为NULL时不进行排序
     * @return 商品列表
     */
    public Page<Product> findByType(String type,
                                        int page,
                                        int size,
                                        String direction,
                                        String property)
    {
        Page<Product> productPage = null;
        PageRequest pageRequest = null;
        
        if (null == type)
        {
            return null;
        }
        
        if (page < 0 || size < 0)
        {
            return null;
        }
        
        if (null == direction || null == property)
        {
            pageRequest = new PageRequest(page, size);
        }
        else
        {
            Sort sort = new Sort(direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC, 
                                 property);
            pageRequest = new PageRequest(page, size, sort);
        }
        
        productPage = repository.findByTypeAllLikeAndIsOnSaleTrue("%[" + type.trim() + "]%", pageRequest);
        
        return productPage;
    }
    
    /**
     * 根据类型获取商品
     * 
     * @param type 类型名称
     * @return 商品列表
     */
    public List<Product> findByType(String type) 
    {
        List<Product> productList = null;

        if (null == type) {
            return null;
        }

        productList = repository.findByTypeAllLikeAndIsOnSaleTrue("%[" + type.trim() + "]%");

        return productList;
    }
    
    /**
     * 获取所有商品，并分页
     * 
     * @param page 页号，从0开始
     * @param size 每页大小
     * @param direction 排序方向，不区分大小写，asc表示升序，desc表示降序，为NULL时不进行排序
     * @param property 排序的字段名，为NULL时不进行排序
     * @return 商品列表分页
     */
    public Page<Product> findAll(int page,
                                int size,
                                String direction,
                                String property)
    {
        Page<Product> productPage = null;
        PageRequest pageRequest = null;
        
        if (page < 0 || size < 0)
        {
            return null;
        }
        
        if (null == direction || null == property)
        {
            pageRequest = new PageRequest(page, size);
        }
        else
        {
            Sort sort = new Sort(direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC, 
                                 property);
            pageRequest = new PageRequest(page, size, sort);
        }
        
        productPage = repository.findAll(pageRequest);
        
        return productPage;
    }
    
    /**
     * 获取限时抢购商品，并进行分页
     * 
     * @param page 页号，从0开始
     * @param size 每页大小
     * @param direction 排序方向，不区分大小写，asc表示升序，desc表示降序，为NULL时不进行排序
     * @param property 排序的字段名，为NULL时不进行排序
     * @return 商品列表
     */
    public Page<Product> findFlashSale(int page,
                                        int size,
                                        String direction,
                                        String property)
    {
        Page<Product> productPage = null;
        PageRequest pageRequest = null;
        
        if (page < 0 || size < 0)
        {
            return null;
        }
        
        if (null == direction || null == property)
        {
            pageRequest = new PageRequest(page, size);
        }
        else
        {
            Sort sort = new Sort(direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC, 
                                 property);
            pageRequest = new PageRequest(page, size, sort);
        }
        
        Date current = new Date();
        
        productPage = repository.findByIsOnSaleTrueAndIsFlashSaleTrueAndFlashSaleStopTimeAfter(current, pageRequest);
        
        return productPage;
    }
    
    /**
     * 获取明星产品，并进行分页
     * 
     * @param page 页号，从0开始
     * @param size 每页大小
     * @param direction 排序方向，不区分大小写，asc表示升序，desc表示降序，为NULL时不进行排序
     * @param property 排序的字段名，为NULL时不进行排序
     * @return 商品列表
     */
    public Page<Product> findStar(int page,
                                        int size,
                                        String direction,
                                        String property)
    {
        Page<Product> productPage = null;
        PageRequest pageRequest = null;
        
        if (page < 0 || size < 0)
        {
            return null;
        }
        
        if (null == direction || null == property)
        {
            pageRequest = new PageRequest(page, size);
        }
        else
        {
            Sort sort = new Sort(direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC, 
                                 property);
            pageRequest = new PageRequest(page, size, sort);
        }
        
        productPage = repository.findByIsOnSaleTrueAndIsStarProductTrue(pageRequest);
        
        return productPage;
    }
    
    /**
     * 查找商品
     * 
     * @param id 商品ID
     * @return 找到的商品，未找到时返回NULL
     */
    public Product findOne(Long id)
    {
        Product p = null;
        
        if (null == id)
        {
            return null;
        }
        
        p = repository.findOne(id);
        
        if (null == p)
        {
            return null;
        }
        
        if (null == p.getCombinationList())
        {
            return p;
        }
        
        // 重新填充combinationList
        for (ProductCombination pc : p.getCombinationList())
        {
            if (null != pc.getPid() && null != pc.getVid())
            {
                Product related = repository.findOne(pc.getPid());
                
                // 设置价格
                for (ProductVersion ver : related.getVersionList())
                {
                    if (ver.getId().equals(pc.getVid()))
                    {
                        pc.setProductPrice(ver.getSalePrice());
                        pc.setProductName(related.getName());
                        pc.setProductCoverImageUri(related.getCoverImageUri());
                        pc.setProductBrief(related.getBrief());
                        pc.setProductType(related.getType());
                    }
                }
            }
        }
        
        return p;
    }
    
    /**
     * 保存商品
     * @param product 商品
     * @param coverImage 封面图片
     * @param pictures 展示图片
     * @param type 商品类型
     * @param combiList 组合商品列表
     * @param versionList 版本列表
     * @param paramList 参数列表
     * @param flashStartTime 限时抢购开始时间
     * @param flashEndTime 限时抢购结束时间
     * @return
     */
    public Product save(Product product, 
                    MultipartFile coverImage,
                    MultipartFile[] pictures,
                    ProductType type, 
                    List<ProductCombination> combiList,
                    List<ProductVersion> versionList,
                    List<ProductParameter> paramList,
                    String flashStartTime,
                    String flashEndTime)
    {
        if (null == product)
        {
            return null;
        }
        
        // 设置封面图片
        if (null != coverImage)
        {
            Map<String, String> uploadRes = ImageUtil.upload(coverImage);
    
            // 成功
            if ("0".equals(uploadRes.get("code"))) {
                // 保存uri
                product.setCoverImageUri(uploadRes.get("message"));
                product.setCoverImageHeight(Double.parseDouble(uploadRes.get("height")));
                product.setCoverImageWidth(Double.parseDouble(uploadRes.get("width")));
            } 
            else // 失败
            {
                return null;
            }
        }
        
        // 设置展示图片
        if (null != pictures)
        {
            String showPics = "";
            
            for (MultipartFile pic : pictures)
            {
                Map<String, String> uploadRes = ImageUtil.upload(pic);
    
                // 成功
                if ("0".equals(uploadRes.get("code"))) {
                    // 保存uri
                    showPics += uploadRes.get("message");
                    showPics += ",";
                } 
                else // 失败
                {
                    return null;
                }
            }
            
            product.setShowPictures(showPics);
        }
        
        // 设置类型
        if (null != type)
        {
            
        }
        
        // 设置商品组合
        if (null != combiList)
        {
            
        }
        
        // 设置版本
        if (null != versionList)
        {
            
        }
        
        // 设置参数
        if (null != paramList)
        {
            
        }
        
        // 设置限时抢购时间
        if (product.getIsFlashSale() && null != flashStartTime && null != flashEndTime)
        {
            
        }
        
        return repository.save(product);
    }
    
    /**
     * 删除商品
     * 
     * @param id 商品ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除商品
     * @param product 商品
     */
    public void delete(Product product)
    {
        if (null != product)
        {
            repository.delete(product);
        }
    }
    
    public void buy(Long pid, Long vid)
    {
        
    }
}
