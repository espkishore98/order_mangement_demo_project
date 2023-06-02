package com.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.Cart;
import com.orderManagement.entity.Users;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value = "select c from Cart c where c.user = :user and c.isDeleted = :isDeleted", nativeQuery = false)
	Cart findByUser(Users user, boolean isDeleted);

}
