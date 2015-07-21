package com.ynyes.cheyou.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 支付方式
 *
 * 记录了支付方式各字段
 * 
 * @author Sharon
 *
 */

@Entity
public class TdPayType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 支付名称
    @Column
    private String title;
    
    // 线上付款
    @Column
    private Boolean isOnlinePay;
    
    // 是否启用
    @Column
    private Boolean isEnable;
    
    // 排序数字
    @Column
    private Long sortId;
    
    // 手续费类型
    @Column
    private Boolean isFeeCountByPecentage;
    
    // 支付手续费 百分比的计算公式：商品总金额+(商品总金额*百分比)+配送费用=订单总金额
    @Column(scale=2)
    private Double fee;
    
    // 显示图标
    @Column
    private String coverImageUri;
    
    // 描述说明
    @Column
    private String info;
    
    // 支付代码
    @Column
    private String code;
    
    // 支付接口活动引导小标题
    @Column
    private String adTitle;

    // 支付接口活动引导内容
    @Column
    private String adContents;

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

    public Boolean getIsOnlinePay() {
        return isOnlinePay;
    }

    public void setIsOnlinePay(Boolean isOnlinePay) {
        this.isOnlinePay = isOnlinePay;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public Boolean getIsFeeCountByPecentage() {
        return isFeeCountByPecentage;
    }

    public void setIsFeeCountByPecentage(Boolean isFeeCountByPecentage) {
        this.isFeeCountByPecentage = isFeeCountByPecentage;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getCoverImageUri() {
        return coverImageUri;
    }

    public void setCoverImageUri(String coverImageUri) {
        this.coverImageUri = coverImageUri;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdContents() {
        return adContents;
    }

    public void setAdContents(String adContents) {
        this.adContents = adContents;
    }
}
