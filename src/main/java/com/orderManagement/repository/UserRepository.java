package com.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.UserRoles;
import com.orderManagement.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	@Query(value = "select u from Users u where u.externalId = :externalId", nativeQuery = false)
	Users getByExternalId(String externalId);

	Users findByUserName(String userName);

	Users findByEmailId(String emailId);

	Users findByExternalId(String externalId);

	@Query(value = "select u from UserRoles u where u.userId.id = :userId and u.roleId.roleId = :roleId", nativeQuery = false)
	UserRoles findByUserAndRole(Long userId, Long roleId);

}
