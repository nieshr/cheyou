package com.ynyes.cheyou.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 用户
 * 
 * 储存所有用户信息
 * 
 * @author Sharon
 *
 */

@Entity
public class TdUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	// 收货地址
	@OneToMany
	@JoinColumn(name="userId")
	private List<TdShippingAddress> shippingAddressList;
	
	// 用户头像
	@Column
	private String headImageUri;
	
	// 用户名
	@Column(nullable=false, unique=true)
	private String username;
    
	//支付宝登陆名
	@Column
	private String alipayUserId;
	
	//QQ登录名
	@Column
	private String qqUserId;	

	// 所属同盟店ID
    @Column
    private Long upperDiySiteId;
	
	// 昵称
    @Column
    private String nickname;
	
	// 密码
	@Column(nullable=false)
	private String password;
	
	// 真实姓名
	@Column
	private String realName;
	
	// 性别
	@Column
	private String sex;
	
	// 生日
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	// 注册时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date registerTime;
	
	// 最后登录时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	
	// 身份证号码
	@Column
	private String identity;
	
	// 手机号码
	@Column
	private String mobile;
	
	// 手机是否已验证
	@Column
	private Boolean isMobileValidated;
	
	// 电话号码
	@Column
	private String telephone;
	
	// 电子邮箱
	@Column
	private String email;
	
	// 电子邮箱是否验证
	@Column
	private Boolean isEmailValidated;
	
	// 车牌号
	@Column
	private String carCode;

	// 省
	@Column
	private String province;
	
	// 市
	@Column
	private String city;
	
	// 区
	@Column
	private String district;
	
	// 详细地址
    @Column
    private String detailAddress;
    
    // QQ
    @Column
    private String qq;
	
	// 账户状态 0: 待审核 1: 正常
	@Column
	private Long statusId;
	
	// 用户类型
	@Column
	private String role;
	
	// 用户类型Id: 0-普通用户 1-分销商
    @Column
    private Long roleId;

	// 用户等级
	@Column
	private Long userLevelId;
	
	// 用户等级名称
	@Column
	private String userLevelTitle;
	
	// 收藏商品总数
	@Column
    private Long totalCollectedGoods;
	
	// 最近浏览总数
    @Column
    private Long totalRecentVisited;
    
    // 积分总额
    @Column
    private Long totalPoints;
    
    // 咨询总数
    @Column
    private Long totalConsults;
    
    // 评论总数
    @Column
    private Long totalComments;
    
    // 退换货总数
    @Column
    private Long totalReturns;
    
    // 下级用户总数
    @Column
    private Long totalLowerUsers;
    
    // 返现总笔数
    @Column
    private Long totalCashRewards;
    
    // 分销商银行卡号
    @Column
    private String bankCardCode;
    
    // 分销商银行卡名称
    @Column
    private String bankTitle;
    
    // 分销商银行卡已审核
    @Column
    private Boolean isBankCardVerified;
    
    // 累计消费额度
    @Column(scale=2)
    private Double totalSpendCash;
    
    // 排序号
    @Column
    private Long sortId;
    
    // 今日通过分享商品获得的积分
    @Column
    private Long pointGetByShareGoods;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAlipayUserId() {
		return alipayUserId;
	}

	public void setAlipayUserId(String alipay_userid) {
		this.alipayUserId = alipay_userid;
	}
	
    public String getQqUserId() {
		return qqUserId;
	}

	public void setQqUserId(String qqUserId) {
		this.qqUserId = qqUserId;
	}

	public List<TdShippingAddress> getShippingAddressList() {
        return shippingAddressList;
    }

    public void setShippingAddressList(List<TdShippingAddress> shippingAddressList) {
        this.shippingAddressList = shippingAddressList;
    }

    public String getHeadImageUri() {
        return headImageUri;
    }

    public void setHeadImageUri(String headImageUri) {
        this.headImageUri = headImageUri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUpperDiySiteId() {
        return upperDiySiteId;
    }

    public void setUpperDiySiteId(Long upperDiySiteId) {
        this.upperDiySiteId = upperDiySiteId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getIsMobileValidated() {
        return isMobileValidated;
    }

    public void setIsMobileValidated(Boolean isMobileValidated) {
        this.isMobileValidated = isMobileValidated;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsEmailValidated() {
        return isEmailValidated;
    }

    public void setIsEmailValidated(Boolean isEmailValidated) {
        this.isEmailValidated = isEmailValidated;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Long userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getUserLevelTitle() {
        return userLevelTitle;
    }

    public void setUserLevelTitle(String userLevelTitle) {
        this.userLevelTitle = userLevelTitle;
    }

    public Long getTotalCollectedGoods() {
        return totalCollectedGoods;
    }

    public void setTotalCollectedGoods(Long totalCollectedGoods) {
        this.totalCollectedGoods = totalCollectedGoods;
    }

    public Long getTotalRecentVisited() {
        return totalRecentVisited;
    }

    public void setTotalRecentVisited(Long totalRecentVisited) {
        this.totalRecentVisited = totalRecentVisited;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Long getTotalConsults() {
        return totalConsults;
    }

    public void setTotalConsults(Long totalConsults) {
        this.totalConsults = totalConsults;
    }

    public Long getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Long totalComments) {
        this.totalComments = totalComments;
    }

    public Long getTotalReturns() {
        return totalReturns;
    }

    public void setTotalReturns(Long totalReturns) {
        this.totalReturns = totalReturns;
    }

    public Long getTotalLowerUsers() {
        return totalLowerUsers;
    }

    public void setTotalLowerUsers(Long totalLowerUsers) {
        this.totalLowerUsers = totalLowerUsers;
    }

    public Long getTotalCashRewards() {
        return totalCashRewards;
    }

    public void setTotalCashRewards(Long totalCashRewards) {
        this.totalCashRewards = totalCashRewards;
    }

    public String getBankCardCode() {
        return bankCardCode;
    }

    public void setBankCardCode(String bankCardCode) {
        this.bankCardCode = bankCardCode;
    }

    public String getBankTitle() {
        return bankTitle;
    }

    public void setBankTitle(String bankTitle) {
        this.bankTitle = bankTitle;
    }

    public Boolean getIsBankCardVerified() {
        return isBankCardVerified;
    }

    public void setIsBankCardVerified(Boolean isBankCardVerified) {
        this.isBankCardVerified = isBankCardVerified;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public Double getTotalSpendCash() {
        return totalSpendCash;
    }

    public void setTotalSpendCash(Double totalSpendCash) {
        this.totalSpendCash = totalSpendCash;
    }

    public Long getPointGetByShareGoods() {
        return pointGetByShareGoods;
    }

    public void setPointGetByShareGoods(Long pointGetByShareGoods) {
        this.pointGetByShareGoods = pointGetByShareGoods;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

	@Override
	public String toString() {
		return "TdUser [id=" + id + ", lastLoginTime=" + lastLoginTime + "]";
	}

    
    
}
