package com.orderManagement.model;

public class PriceDto {
	private Long priceId;
	private Long itemId;
	private Long discountId;
	private Long amount;

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public PriceDto(Long priceId, Long itemId, Long discountId, Long amount) {
		super();
		this.priceId = priceId;
		this.itemId = itemId;
		this.discountId = discountId;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PriceDto [priceId=" + priceId + ", itemId=" + itemId + ", discountId=" + discountId + ", amount="
				+ amount + "]";
	}

}
