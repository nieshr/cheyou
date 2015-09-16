package com.ynyes.cheyou.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 优惠券
 * 
 * @author Sharon
 *
 */

@Entity
public class TdCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	// 优惠券分类ID
	@Column
	private Long typeId;
	
	// 优惠券限用分类类型ID
    @Column
    private Long typeCategoryId;
	
	// 优惠券名称
	@Column
    private String typeTitle;
	
	// 优惠券描述
    @Column
    private String typeDescription;
    
    // 优惠券图片
    @Column
    private String typePicUri;
    
    // 金额
    @Column(scale=2)
    private Double price;
    
    // 所属同盟店
    @Column
    private Long diySiteId;
    
    // 所属同盟店
    @Column
    private String diySiteTitle;
    
    // 是否已领用
    @Column
    private Boolean isDistributted;
    
    // 是否已使用
    @Column
    private Boolean isUsed;
    
    // 剩余数量
    @Column
    private Long leftNumber;
    
    // 剩余数量
    @Column
    private Long getNumber;
    
    // 领用用户
    @Column
    private String username;
    
    // 领用日期
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date getTime;
    
    // 过期日期
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expireTime;
    
    // 核销日期
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;
    
    // 手机号
    @Column
    private String mobile;
    
    // 车牌号
    @Column
    private String carCode;
    
    //领取用户所属同盟店id
    @Column
    private Long userDiysiteId;
    
    //消费密码
    @Column
    private String consumerPassword;
    
    // 排序号
    @Column
    private Long sortId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

	public Long getUserDiysiteId() {
		return userDiysiteId;
	}

	public void setUserDiysiteId(Long userDiysiteId) {
		this.userDiysiteId = userDiysiteId;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getConsumerPassword() {
		return consumerPassword;
	}

	public void setConsumerPassword(String consumerPassword) {
		this.consumerPassword = consumerPassword;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public Boolean getIsDistributted() {
        return isDistributted;
    }

    public void setIsDistributted(Boolean isDistributted) {
        this.isDistributted = isDistributted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypePicUri() {
        return typePicUri;
    }

    public void setTypePicUri(String typePicUri) {
        this.typePicUri = typePicUri;
    }

    public Long getLeftNumber() {
        return leftNumber;
    }

    public void setLeftNumber(Long leftNumber) {
        this.leftNumber = leftNumber;
    }

    public Long getGetNumber() {
        return getNumber;
    }

    public void setGetNumber(Long getNumber) {
        this.getNumber = getNumber;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Long getDiySiteId() {
        return diySiteId;
    }

    public void setDiySiteId(Long diySiteId) {
        this.diySiteId = diySiteId;
    }

    public String getDiySiteTitle() {
        return diySiteTitle;
    }

    public void setDiySiteTitle(String diySiteTitle) {
        this.diySiteTitle = diySiteTitle;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public Long getTypeCategoryId() {
        return typeCategoryId;
    }

    public void setTypeCategoryId(Long typeCategoryId) {
        this.typeCategoryId = typeCategoryId;
    }
}
