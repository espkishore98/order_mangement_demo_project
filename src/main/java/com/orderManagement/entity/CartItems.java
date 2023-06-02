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

@Table(name = "cart_items")
@Entity
public class CartItems {
	@EmbeddedId
	private CartItemKey cartItemsId;
	@JoinColumn(name = "cart_id")
	@MapsId("cartId")
	@ManyToOne(targetEntity = Cart.class, cascade = CascadeType.PERSIST)
	private Cart cartId;
	@JoinColumn(name = "item_id")
	@MapsId("itemId")
	@ManyToOne(targetEntity = Items.class, cascade = CascadeType.PERSIST)
	private Items itemId;
	@Column(name = "quantity")
	private Long quantity;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "updated_date")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	public CartItemKey getCartItemsId() {
		return cartItemsId;
	}

	public void setCartItemsId(CartItemKey cartItemsId) {
		this.cartItemsId = cartItemsId;
	}

	public void setItemId(Items itemId) {
		this.itemId = itemId;
	}

	public Cart getCartId() {
		return cartId;
	}

	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

//	public Items getItemId() {
//		return itemId;
//	}
//
//	public void setItemId(Items itemId) {
//		this.itemId = itemId;
//	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Items getItemId() {
		return itemId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "CartItems [cartItemsId=" + cartItemsId + ", cartId=" + cartId + ", itemId=" + itemId + ", quantity="
				+ quantity + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", isDeleted="
				+ isDeleted + "]";
	}

}
