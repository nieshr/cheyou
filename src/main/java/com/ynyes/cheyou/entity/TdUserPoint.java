package com.ynyes.cheyou.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 用户积分
 * 
 * @author Sharon
 *
 */

@Entity
public class TdUserPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	// 用户名
	@Column
	private String username;
	
	// 积分时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date pointTime;
	
	// 积分额度
	@Column
	private Long point;
	
	// 该笔积分发生后余额
	@Column
	private Long totalPoint;
	
	// 涉及订单号
    @Column
    private String orderNumber;
    
    // 详情
    @Column
    private String detail;
	
    // 排序号
    @Column
    private Long sortId;

    // 是否仅后台显示
    @Column
    private Boolean isBackgroundShow;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getIsBackgroundShow() {
		return isBackgroundShow;
	}

	public void setIsBackgroundShow(Boolean isBackgroundShow) {
		this.isBackgroundShow = isBackgroundShow;
	}

	public void setUsername(String username) {
        this.username = username;
    }

    public Date getPointTime() {
        return pointTime;
    }

    public void setPointTime(Date pointTime) {
        this.pointTime = pointTime;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Long getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Long totalPoint) {
        this.totalPoint = totalPoint;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    
}
