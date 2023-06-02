package com.orderManagement.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "demo")
@Audited
public class Demo implements Serializable {
	@Id
	@Audited
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Audited
	@Column(name = "name")
	private String name;
	@Audited
	@Column(name = "score")
	private Long score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Demo(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Demo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Demo [id=" + id + ", name=" + name + ", score=" + score + "]";
	}

}
