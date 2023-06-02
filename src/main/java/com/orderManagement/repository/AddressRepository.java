package com.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.Address;
import com.orderManagement.entity.Users;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByUser(Users user);

}
