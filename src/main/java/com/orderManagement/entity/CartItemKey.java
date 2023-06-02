package com.orderManagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartItemKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "cart_id")
	Long cartId;

	@Column(name = "item_id")
	Long itemId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "CartItemKey [cartId=" + cartId + ", itemId=" + itemId + "]";
	}

	public CartItemKey(Long cartId, Long itemId) {
		super();
		this.cartId = cartId;
		this.itemId = itemId;
	}

	public CartItemKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CartItemKey that = (CartItemKey) o;

		if (!cartId.equals(that.cartId))
			return false;
		return itemId.equals(that.itemId);
	}

	@Override
	public int hashCode() {
		int result = cartId.hashCode();
		result = 31 * result + itemId.hashCode();
		return result;
	}
}
