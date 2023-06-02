package com.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.UserRoles;
@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

}
