package com.orderManagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "order_track")
@Entity
public class OrderTracking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_track_id")
	private Long orderTrackId;
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	@ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Order orderId;
	@Column(name = "order_stage")
	private String orderStage;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	@UpdateTimestamp
	private Date updatedDate;

	public Long getOrderTrackId() {
		return orderTrackId;
	}

	public void setOrderTrackId(Long orderTrackId) {
		this.orderTrackId = orderTrackId;
	}

	public Order getOrderId() {
		return orderId;
	}

	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}

	public String getOrderStage() {
		return orderStage;
	}

	public void setOrderStage(String orderStage) {
		this.orderStage = orderStage;
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

	public OrderTracking(Long orderTrackId, Order orderId, String orderStage, Date createdDate, Date updatedDate) {
		super();
		this.orderTrackId = orderTrackId;
		this.orderId = orderId;
		this.orderStage = orderStage;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public OrderTracking() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderTracking [orderTrackId=" + orderTrackId + ", orderId=" + orderId + ", orderStage=" + orderStage
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
