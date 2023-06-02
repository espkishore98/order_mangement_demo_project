package com.orderManagement.model;

public class OrderRequest {
	private Long cartId;
	private Long addressId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public OrderRequest(Long cartId) {
		super();
		this.cartId = cartId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "OrderRequest [cartId=" + cartId + ", addressId=" + addressId + "]";
	}

	public OrderRequest(Long cartId, Long addressId) {
		super();
		this.cartId = cartId;
		this.addressId = addressId;
	}

	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
