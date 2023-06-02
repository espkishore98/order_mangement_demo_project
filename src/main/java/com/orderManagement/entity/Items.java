package com.orderManagement.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "items")
public class Items implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4683180017055075638L;
	/**
	 * 
	 */
	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	@Column(name = "item_name")
	private String itemName;
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "description")
	private String description;
	@Column(name = "category")
	private String category;
	@Column(name = "status")
	private String status;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;
//	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
//	@ManyToMany(targetEntity = Cart.class, cascade = CascadeType.PERSIST)
//	private List<Cart> cart;
	@Column(name = "quantity_available")
	private Long quantityAvailable;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "item", fetch = FetchType.LAZY)
	private Price price;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Long getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Long quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Items(Long itemId, String itemName, String uuid, String description, String category, String status,
			Date createdDate, Date updatedDate, Long quantityAvailable) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.uuid = uuid;
		this.description = description;
		this.category = category;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.quantityAvailable = quantityAvailable;
	}

	public Items(String itemName, String uuid, String description, String category, String status, Date createdDate,
			Date updatedDate) {
		super();
		this.itemName = itemName;
		this.uuid = uuid;
		this.description = description;
		this.category = category;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Items(String itemName, String uuid, String description, String category, String status,
			Long quantityAvailable) {
		super();
		this.itemName = itemName;
		this.uuid = uuid;
		this.description = description;
		this.category = category;
		this.status = status;
		this.quantityAvailable = quantityAvailable;
	}

	public Items(String itemName, String uuid, String description, String category, String status,
			Long quantityAvailable, Price price) {
		super();
		this.itemName = itemName;
		this.uuid = uuid;
		this.description = description;
		this.category = category;
		this.status = status;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Items [itemId=" + itemId + ", itemName=" + itemName + ", uuid=" + uuid + ", description=" + description
				+ ", category=" + category + ", status=" + status + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

}
