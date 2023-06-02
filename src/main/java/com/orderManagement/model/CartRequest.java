package com.orderManagement.model;

public class CartRequest {

	private String itemId;
	private Long quantity;
	private Long cartId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	@Override
	public String toString() {
		return "CartRequest [itemId=" + itemId + ", quantity=" + quantity + ", cartId=" + cartId + "]";
	}

	public CartRequest(String itemId, Long quantity, Long cartId) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.cartId = cartId;
	}

}
