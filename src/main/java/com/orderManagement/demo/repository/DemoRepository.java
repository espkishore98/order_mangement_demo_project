package com.orderManagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.orderManagement.demo.entity.Demo;

public interface DemoRepository extends RevisionRepository<Demo, Long, Integer>, JpaRepository<Demo, Long> {

}
