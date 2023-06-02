package com.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.Discounts;
@Repository
public interface DiscountsRepository extends JpaRepository<Discounts, Long> {

}
