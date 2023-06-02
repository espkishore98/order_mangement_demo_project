package com.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.Order;
import com.orderManagement.entity.Users;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

	Order findByUser(Users user);

	@Query(value = "select o from Order o where o.user=:user and o.isDeleted =:b", nativeQuery = false)
	Order findByUser(Users user, boolean b);

}
