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
@Table(name = "orders")
public class Order {
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
	@OneToOne(targetEntity = Cart.class, cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Cart cartId;
	@Column(name = "total_price")
	private Long totalPrice;
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	@OneToOne(targetEntity = Address.class, cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Address addressId;
	@Column(name = "status")
	private String status;
	@Column(name = "payment_status")
	private String paymentStatus;
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@OneToOne(targetEntity = Users.class, cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Users user;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	@UpdateTimestamp
	private Date updatedDate;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Cart getCartId() {
		return cartId;
	}

	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Address getAddressId() {
		return addressId;
	}

	public void setAddressId(Address addressId) {
		this.addressId = addressId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
		return "Order [orderId=" + orderId + ", cartId=" + cartId + ", totalPrice=" + totalPrice + ", addressId="
				+ addressId + ", status=" + status + ", paymentStatus=" + paymentStatus + ", user=" + user
				+ ", isDeleted=" + isDeleted + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

	public Order(Long orderId, Cart cartId, Long totalPrice, Address addressId, String status, String paymentStatus,
			Users user, boolean isDeleted, Date createdDate, Date updatedDate) {
		super();
		this.orderId = orderId;
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.addressId = addressId;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.user = user;
		this.isDeleted = isDeleted;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

}
