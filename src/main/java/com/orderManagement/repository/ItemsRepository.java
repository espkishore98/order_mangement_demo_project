package com.orderManagement.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.Items;

@Repository
public interface ItemsRepository extends PagingAndSortingRepository<Items, Long> {

	Items findByUuid(String uuid);

	@Query(value = "delete from Items i where i.uuid = :uuid", nativeQuery = false)
	@Transactional
	@Modifying
	void deleteByUuid(String uuid);

}
