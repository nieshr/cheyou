package com.ynyes.cheyou.entity;



/**
 * 立即购买商品，不需要存储数据库
 * 
 * @author Sharon
 *
 */

public class TdGoodsDto {
    
    // 商品ID
    private Long goodsId;
    
    // 商品标题
    private String goodsTitle;
    
    // 商品封面
    private String goodsCoverImageUri;
    
    // 商品数量
    private Long quantity;
    
    // 成交价
    private Double price;
    
    // 销售方式 0-正常销售 1-组合销售 2-秒杀 3-十人团 4-百人团
    private Integer saleId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsCoverImageUri() {
        return goodsCoverImageUri;
    }

    public void setGoodsCoverImageUri(String goodsCoverImageUri) {
        this.goodsCoverImageUri = goodsCoverImageUri;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }
}
