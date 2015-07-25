package com.ynyes.cheyou.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 邮寄地址
 * 
 * 定义了对邮寄地址的管理
 * 
 * @author Sharon
 *
 */

@Entity
@Table
public class TdShippingAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// 国家
	@Column
	private String country;

	// 省
	@Column
	private String province;
	
	// 市
	@Column
	private String city;
	
	// 区
	@Column
	private String disctrict;
	
	// 详细地址
	@Column
	private String detailAddress;
	
	// 邮政编码
	@Column
	private String postcode;
	
	// 收货人姓名
	@Column
	private String receiverName;
	
	// 收货人移动电话
	@Column
	private String receiverMobile;
	
	// 收货人邮箱
    @Column
    private String receiverEmail;
    
    // 收货人电话区号
    @Column
    private String receiverTeleAreaCode;
	
	// 收货人电话号码
	@Column
	private String receiverTelephone;
	
	// 是否为默认地址
	@Column
	private Boolean isDefaultAddress;
	
	// 排序号
    @Column
    private Long sortId;
    
    /**
     * 新增车牌和车型参数，供“安装信息”使用
     * @author zhangji
     */
    
    //收货人车牌
    @Column
    private String receiverCarcode;
    
    //收货人车型
    @Column
    private String receiverCartype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDisctrict() {
        return disctrict;
    }

    public void setDisctrict(String disctrict) {
        this.disctrict = disctrict;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverTeleAreaCode() {
        return receiverTeleAreaCode;
    }

    public void setReceiverTeleAreaCode(String receiverTeleAreaCode) {
        this.receiverTeleAreaCode = receiverTeleAreaCode;
    }

    public String getReceiverTelephone() {
        return receiverTelephone;
    }

    public void setReceiverTelephone(String receiverTelephone) {
        this.receiverTelephone = receiverTelephone;
    }

    public Boolean getIsDefaultAddress() {
        return isDefaultAddress;
    }

    public void setIsDefaultAddress(Boolean isDefaultAddress) {
        this.isDefaultAddress = isDefaultAddress;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

	public String getReceiverCarcode() {
		return receiverCarcode;
	}

	public void setReceiverCarcode(String receiverCarcode) {
		this.receiverCarcode = receiverCarcode;
	}

	public String getReceiverCartype() {
		return receiverCartype;
	}

	public void setReceiverCartype(String receiverCartype) {
		this.receiverCartype = receiverCartype;
	}

    


}
