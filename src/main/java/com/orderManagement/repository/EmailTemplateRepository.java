package com.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.EmailTemplates;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplates, Long> {

	EmailTemplates findByTemplateType(String templateType);

}
