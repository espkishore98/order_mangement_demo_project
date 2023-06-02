package com.orderManagement.model;

public class DiscountRequest {
	private String discountType;
	private Long percentageOfDiscount;

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Long getPercentageOfDiscount() {
		return percentageOfDiscount;
	}

	public void setPercentageOfDiscount(Long percentageOfDiscount) {
		this.percentageOfDiscount = percentageOfDiscount;
	}

	public DiscountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DiscountRequest [discountType=" + discountType + ", percentageOfDiscount=" + percentageOfDiscount + "]";
	}

	public DiscountRequest(String discountType, Long percentageOfDiscount) {
		super();
		this.discountType = discountType;
		this.percentageOfDiscount = percentageOfDiscount;
	}

}
