package com.orderManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.Order;
import com.orderManagement.entity.OrderTracking;

@Repository
public interface OrderTrackRepository extends JpaRepository<OrderTracking, Long> {

	List<OrderTracking> findByOrderId(Order order);

}
