package com.orderManagement.model;

public class DemoRespDTO {

	private String name;

	private Long score;

	private Number revId;

	private String revTypeName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Number getRevId() {
		return revId;
	}

	public void setRevId(Number revId) {
		this.revId = revId;
	}

	public String getRevTypeName() {
		return revTypeName;
	}

	public void setRevTypeName(String revTypeName) {
		this.revTypeName = revTypeName;
	}

	@Override
	public String toString() {
		return "DemoRespDTO [name=" + name + ", score=" + score + ", revId=" + revId + ", revTypeName=" + revTypeName
				+ "]";
	}

}
