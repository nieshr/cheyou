package com.ynyes.cheyou.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户投诉
 * 
 * @author Zhangji
 *
 */
@Entity
public class TdUserSuggestion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    //订单编号
	@Column
	private String title;
	
	//投诉内容
	@Column
	private String content;
	
	//投诉时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;
	
	//称呼
	@Column
	private String name;
	
	//邮箱
	@Column
	private String mail;
	
	//联系方式
	@Column
	private String mobile;

	// 排序号
    @Column
    private Long sortId;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

}
