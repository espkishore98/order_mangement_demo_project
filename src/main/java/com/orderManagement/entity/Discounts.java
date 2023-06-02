package com.orderManagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "discounts")
public class Discounts {
	@Id
	@Column(name = "discount_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long discountId;
	@Column(name = "discount_type")
	private String discountType;
	@Column(name = "percentage_of_discount")
	private Long percentageOfDiscount;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

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

	public Discounts(Long discountId, String discountType, Long percentageOfDiscount, Date createdDate,
			Date updatedDate) {
		super();
		this.discountId = discountId;
		this.discountType = discountType;
		this.percentageOfDiscount = percentageOfDiscount;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Discounts [discountId=" + discountId + ", discountType=" + discountType + ", percentageOfDiscount="
				+ percentageOfDiscount + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

	public Discounts() {
		super();
		// TODO Auto-generated constructor stub
	}

}
