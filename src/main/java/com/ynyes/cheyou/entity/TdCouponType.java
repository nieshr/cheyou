package com.ynyes.cheyou.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 优惠券类型
 * 
 * @author Sharon
 *
 */

@Entity
public class TdCouponType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	// 名称
	@Column
	private String title;
	
	// 种类 0:普通券 1:满减券 2:限制品类券
	// 更新 0 全场通用券  1 分品类满减券  2 不分品类满减券
	@Column
	private Long categoryId;
	
	// 满减券金额
	@Column(scale=2)
    private Double canUsePrice;
	
	// 限制品类券品类ID
	@Column
    private Long productTypeId;
	
	// 金额
    @Column(scale=2)
    private Double price;
    
    // 图片
    @Column
    private String picUri;
    
    // 描述
    @Column
    private String description;
    
    // 排序号
    @Column
    private Long sortId;
    
    // 有效期限（天）
    @Column
    private Long totalDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicUri() {
        return picUri;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public Long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Long totalDays) {
        this.totalDays = totalDays;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getCanUsePrice() {
        return canUsePrice;
    }

    public void setCanUsePrice(Double canUsePrice) {
        this.canUsePrice = canUsePrice;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }
}
