package com.orderManagement.model;

public class ItemPrice {
	private String itemName;
	private String itemDescription;
	private String category;
	private String status;
	private String uuid;
	private Long quantityAvailable;
	private Long amount;
	private Long discount;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Long quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "ItemPrice [itemName=" + itemName + ", itemDescription=" + itemDescription + ", category=" + category
				+ ", status=" + status + ", uuid=" + uuid + ", quantityAvailable=" + quantityAvailable + ", amount="
				+ amount + ", discount=" + discount + "]";
	}

}
