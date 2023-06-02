package com.orderManagement.repository;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagement.entity.Items;
import com.orderManagement.entity.Price;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface PriceRepository extends JpaRepository<Price, Long> {

	Price findByItem(Items item);

}
