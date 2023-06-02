package com.orderManagement.model;

public class UserResponseMap {
	private String userName;
	private String emailId;
	private Long phoneNumber;
	private String roleName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserResponseMap [userName=" + userName + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber
				+ ", roleName=" + roleName + "]";
	}

	public UserResponseMap(String userName, String emailId, Long phoneNumber, String roleName) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.roleName = roleName;
	}

	public UserResponseMap() {
		super();
		// TODO Auto-generated constructor stub
	}

}
