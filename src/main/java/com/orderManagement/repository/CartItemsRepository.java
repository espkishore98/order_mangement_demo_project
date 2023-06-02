package com.orderManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orderManagement.entity.Cart;
import com.orderManagement.entity.CartItemKey;
import com.orderManagement.entity.CartItems;
import com.orderManagement.entity.Items;

public interface CartItemsRepository extends JpaRepository<CartItems, CartItemKey> {

	List<CartItems> findByCartId(Long cartId);

	@Query(value = "select c.itemId from CartItems c where c.cartId.cartId = :cartId and  c.isDeleted =:isDeleted", nativeQuery = false)
	List<Items> findItemsByCartId(Long cartId, boolean isDeleted);

	@Query(value = "select c from CartItems c where c.cartId.cartId = :cartId and c.itemId.itemId = :itemId and c.isDeleted = :isDeleted", nativeQuery = false)
	CartItems findItemsByCartIdandItemId(Long cartId, Long itemId, boolean isDeleted);

	@Query(value = "select ci from CartItems ci where ci.cartId = :cart and ci.isDeleted = :isDeleted", nativeQuery = false)
	List<CartItems> findByCartId(Cart cart, boolean isDeleted);

}
