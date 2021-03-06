package com.bresume.core.model.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;

@Entity
@Table(name = "br_oauth")
public class BAuth extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private User user;
	private String openId;
	private String accessToken;
	private Long expiresIn;
	private Integer type;
	private Date refreshAccessTime;
	// icon、nickName如需实时性可能需要更新
	private String icon;
	private String nickName;
	private Date createdTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "OPEN_ID")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "ACCESS_TOKEN")
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(name = "EXPIRES_IN")
	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Column(name = "`TYPE`")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "REFRESH_ACCESS_TIME")
	public Date getRefreshAccessTime() {
		return refreshAccessTime;
	}

	public void setRefreshAccessTime(Date refreshAccessTime) {
		this.refreshAccessTime = refreshAccessTime;
	}

	@Column(name = "`CREATED_TIME`")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "`ICON`")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "`NICK_NAME`")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
