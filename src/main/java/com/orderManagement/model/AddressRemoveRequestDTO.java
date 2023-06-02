package com.orderManagement.model;

public class AddressRemoveRequestDTO {
	private Long addressId;
	private String userId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddressRemoveRequestDTO [addressId=" + addressId + ", userId=" + userId + "]";
	}

	public AddressRemoveRequestDTO(Long addressId, String userId) {
		super();
		this.addressId = addressId;
		this.userId = userId;
	}

}
