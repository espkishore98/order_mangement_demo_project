package com.orderManagement.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "user_address")
@Entity
public class UserAddress {
	@Column(name = "user_address_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userAddressId;
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	@ManyToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
	private List<Address> addresses;
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@OneToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
	private Users user;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "updated_date")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
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

	public UserAddress(Long userAddressId, List<Address> addresses, Users user, Date createdDate, Date updatedDate) {
		super();
		this.userAddressId = userAddressId;
		this.addresses = addresses;
		this.user = user;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public UserAddress() {
		super();
	}

	@Override
	public String toString() {
		return "UserAddress [userAddressId=" + userAddressId + ", addresses=" + addresses + ", user=" + user
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
