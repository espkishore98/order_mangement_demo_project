package com.orderManagement.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_role")
public class UserRoles {
	@EmbeddedId
	private UserRoleKey userRoleId;
	@JoinColumn(name = "role_id")
	@MapsId("roleId")
	@ManyToOne(targetEntity = Roles.class, cascade = CascadeType.ALL)
	private Roles roleId;
	@JoinColumn(name = "user_id")
	@MapsId("userId")
	@ManyToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
	private Users userId;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "updatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	public UserRoleKey getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(UserRoleKey userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Roles getRoleId() {
		return roleId;
	}

	public void setRoleId(Roles roleId) {
		this.roleId = roleId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "UserRoles [userRoleId=" + userRoleId + ", roleId=" + roleId + ", userId=" + userId + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}

	public UserRoles(UserRoleKey userRoleId, Roles roleId, Users userId, Date createdDate, Date updatedDate) {
		super();
		this.userRoleId = userRoleId;
		this.roleId = roleId;
		this.userId = userId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public UserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

}
