package com.orderManagement.model;

import java.util.List;

public class OrderResponse {
	private List<ItemsResponseDTO> items;
	private Long totalOrderCost;

	public List<ItemsResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemsResponseDTO> items) {
		this.items = items;
	}

	public Long getTotalOrderCost() {
		return totalOrderCost;
	}

	public void setTotalOrderCost(Long totalOrderCost) {
		this.totalOrderCost = totalOrderCost;
	}

	@Override
	public String toString() {
		return "OrderResponse [items=" + items + ", totalOrderCost=" + totalOrderCost + "]";
	}

	public OrderResponse(List<ItemsResponseDTO> items, Long totalOrderCost) {
		super();
		this.items = items;
		this.totalOrderCost = totalOrderCost;
	}

	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
