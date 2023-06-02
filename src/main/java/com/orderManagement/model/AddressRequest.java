package com.orderManagement.model;

import java.util.List;

import com.orderManagement.entity.Address;

public class AddressRequest {
	private List<Address> addresses;
	private String userId;

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddressRequest [addresses=" + addresses + ", userId=" + userId + "]";
	}

	public AddressRequest(List<Address> addresses, String userId) {
		super();
		this.addresses = addresses;
		this.userId = userId;
	}

}
