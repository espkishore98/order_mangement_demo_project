package com.orderManagement.model;

import java.util.Date;

public class ItemsResponseDTO {

	private String itemName;
	private String uuid;
	private String description;
	private String category;
	private String status;
	private Long actualPrice;
	private Long discountedPrice;
	private Date createdDate;
	private Date updatedDate;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Long actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Long getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Long discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	@Override
	public String toString() {
		return "ItemsResponseDTO [itemName=" + itemName + ", uuid=" + uuid + ", description=" + description
				+ ", category=" + category + ", status=" + status + ", actualPrice=" + actualPrice
				+ ", discountedPrice=" + discountedPrice + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

	public ItemsResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemsResponseDTO(String itemName, String uuid, String description, String category, String status,
			Long actualPrice, Long discountedPrice, Date createdDate, Date updatedDate) {
		super();
		this.itemName = itemName;
		this.uuid = uuid;
		this.description = description;
		this.category = category;
		this.status = status;
		this.actualPrice = actualPrice;
		this.discountedPrice = discountedPrice;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

}
