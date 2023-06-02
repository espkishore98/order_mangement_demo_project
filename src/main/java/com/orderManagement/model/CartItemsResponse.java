package com.orderManagement.model;

import com.orderManagement.entity.Items;

public class CartItemsResponse {
	private Items items;
	private Long quantity;
	private Long discountedPrice;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Long discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	@Override
	public String toString() {
		return "CartItemsResponse [items=" + items + ", quantity=" + quantity + ", discountedPrice=" + discountedPrice
				+ "]";
	}

}
