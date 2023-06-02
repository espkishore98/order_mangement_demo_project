package com.orderManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "email_templates")
@Entity
public class EmailTemplates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "template_id")
	private Long templateId;
	@Column(name = "subject")
	private String subject;
	@Column(name = "email_body")
	private String emailBody;
	@Column(name = "template_type")
	private String templateType;

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public EmailTemplates(Long templateId, String subject, String emailBody, String templateType) {
		super();
		this.templateId = templateId;
		this.subject = subject;
		this.emailBody = emailBody;
		this.templateType = templateType;
	}

	public EmailTemplates() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmailTemplates [templateId=" + templateId + ", subject=" + subject + ", emailBody=" + emailBody
				+ ", templateType=" + templateType + "]";
	}

}
