package com.orderManagement.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "price")
public class Price implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "price_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long priceId;
	@Column(name = "amount")
	private Long amount;
	@JoinColumn(name = "items", referencedColumnName = "item_id")
//	@JsonIgnore
	@OneToOne(targetEntity = Items.class, cascade = CascadeType.ALL)
	private Items item;
	@JoinColumn(name = "discounts", referencedColumnName = "discount_id")
	@OneToOne(targetEntity = Discounts.class, cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Discounts discount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "updated_date")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)

	private Date updatedDate;

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public Discounts getDiscount() {
		return discount;
	}

	public void setDiscount(Discounts discount) {
		this.discount = discount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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

	public Price(Long priceId, Long amount, Items item, Discounts discount, Date createdDate, Date updatedDate) {
		super();
		this.priceId = priceId;
		this.amount = amount;
		this.item = item;
		this.discount = discount;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Price(Long priceId, Long amount, Discounts discount) {
		super();
		this.priceId = priceId;
		this.amount = amount;
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Price [priceId=" + priceId + ", amount=" + amount + ", item=" + item + ", discount=" + discount
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}

}
