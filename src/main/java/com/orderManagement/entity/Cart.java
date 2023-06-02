package com.orderManagement.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	@JoinColumn(name = "users", referencedColumnName = "id")
	@OneToOne(targetEntity = Users.class, cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Users user;

	@Column(name = "created_date")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "updated_date")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	@Column(name = "is_deleted")
	private boolean isDeleted;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
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

//	public Set<Items> getItem() {
//		return item;
//	}
//
//	public void setItem(Set<Items> item) {
//		this.item = item;
//	}

//	public Cart(Long cartId, Users user, Set<Items> item, Date createdDate, Date updatedDate) {
//		super();
//		this.cartId = cartId;
//		this.user = user;
//		this.item = item;
//		this.createdDate = createdDate;
//		this.updatedDate = updatedDate;
//	}

//	@Override
//	public String toString() {
//		return "Cart [cartId=" + cartId + ", user=" + user + ", item=" + item + ", createdDate=" + createdDate
//				+ ", updatedDate=" + updatedDate + "]";
//	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Cart() {
		super();
	}

	public Cart(Long cartId, Users user, Date createdDate, Date updatedDate, boolean isDeleted) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", isDeleted=" + isDeleted + "]";
	}

//	@Override
//	public String toString() {
//		return "Cart [cartId=" + cartId + ", user=" + user + ", item=" + item + ", createdDate=" + createdDate
//				+ ", updatedDate=" + updatedDate + ", isDeleted=" + isDeleted + "]";
//	}

}
