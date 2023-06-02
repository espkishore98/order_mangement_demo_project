package com.orderManagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.orderManagement.entity.Cart;
import com.orderManagement.entity.CartItemKey;
import com.orderManagement.entity.CartItems;
import com.orderManagement.entity.Items;
import com.orderManagement.entity.Users;
import com.orderManagement.model.CartItemsResponse;
import com.orderManagement.model.CartRequest;
import com.orderManagement.repository.CartItemsRepository;
import com.orderManagement.repository.CartRepository;
import com.orderManagement.repository.ItemsRepository;
import com.orderManagement.repository.PriceRepository;
import com.orderManagement.repository.UserRepository;

import constants.Constants;

@Service
public class CartService implements ICartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	ItemsRepository itemsRepository;

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CartItemsRepository cartItemsRepo;
	@Autowired
	PriceRepository priceRepository;
	@Autowired
	ItemService itemService;
	private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Override
	public ResponseEntity<?> addItemsToCart(CartRequest cartRequest, String token) {
		CartItems cartItem = null;
		Cart cart = null;
		Items item = null;
		String authToken = token.substring(7);
		String externalId = UserService.getUserExternalId(authToken);
		Users user = userRepo.findByExternalId(externalId);
		LOGGER.info("add item to cart started for user {}", user.getUserName());
		cart = cartRepository.findByUser(user, false);
		try {
			if (cartRequest.getItemId() != null) {
				item = itemsRepository.findByUuid(cartRequest.getItemId());
				if (item == null) {
					return new ResponseEntity<String>("ItemDetailsNotfound", HttpStatus.BAD_REQUEST);
				}
			}
			if (cart != null && cart.getCartId() != null) {
				Optional<Cart> extcart = cartRepository.findById(cart.getCartId());
				cartItem = cartItemsRepo.findItemsByCartIdandItemId(extcart.get().getCartId(), item.getItemId(), false);
				if (cartItem != null) {
					cartItem.setQuantity(cartRequest.getQuantity());
					cartItemsRepo.save(cartItem);
				} else {
					cartItem = new CartItems();
					CartItemKey cartItemKey = new CartItemKey();
					cartItemKey.setCartId(extcart.get().getCartId());
					cartItemKey.setItemId(item.getItemId());
					cartItem.setCartId(extcart.get());
					cartItem.setItemId(item);
					cartItem.setQuantity(cartRequest.getQuantity());
					cartItem.setCartItemsId(cartItemKey);
					cartItem.setIsDeleted(false);
					cartItemsRepo.save(cartItem);
				}
			} else {
				if (user != null) {
					cart = new Cart();
					cart.setDeleted(false);
					cart.setUser(user);
				}
				Cart savedResp = cartRepository.save(cart);
				cartItem = new CartItems();
				CartItemKey cartItemKey = new CartItemKey();
				cartItemKey.setCartId(savedResp.getCartId());
				cartItemKey.setItemId(item.getItemId());
				cartItem.setCartId(savedResp);
				cartItem.setItemId(item);
				cartItem.setQuantity(cartRequest.getQuantity());
				cartItem.setCartItemsId(cartItemKey);
				cartItem.setIsDeleted(false);
				cartItemsRepo.save(cartItem);
			}
		} catch (Exception e) {
			LOGGER.error("error while adding to cart", e);
			return new ResponseEntity<String>(Constants.ExceptionConstants.SOMETHING_WENT_WRONG,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(Constants.SuccessConstants.ITEMADDEDTOCART, HttpStatus.OK);
	}

	private Items getItemFromUUId(String uuid) {
		return itemsRepository.findByUuid(uuid);
	}

	@Override
	public ResponseEntity<?> removeFromCart(CartRequest cartRequest, String bearerToken) {
		Items item = null;
		try {
			if (cartRequest.getItemId() == null) {
				return new ResponseEntity<String>("Please Provide ItemId", HttpStatus.BAD_REQUEST);
			}
			item = itemsRepository.findByUuid(cartRequest.getItemId());
			String authToken = bearerToken.substring(7);
			String externalId = UserService.getUserExternalId(authToken);
			Users user = userRepo.findByExternalId(externalId);
			LOGGER.info("remove item to cart started for user {}", user.getUserName());
			if (user != null) {
				if (item == null) {
					return new ResponseEntity<String>("Item details not found", HttpStatus.BAD_REQUEST);
				}
				Cart cart = cartRepository.findByUser(user, false);
				CartItems cartItem = cartItemsRepo.findItemsByCartIdandItemId(cart.getCartId(), item.getItemId(),
						false);
				if (cartItem.getCartItemsId() != null) {
					cartItem.setIsDeleted(true);
					cartItemsRepo.save(cartItem);
				}
			}

		} catch (Exception e) {
			LOGGER.error("errow while removing item from cart", e);
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Item removed from cart", HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getItemsInCart(String bearerToken) {
		String authToken = bearerToken.substring(7);
		String externalId = UserService.getUserExternalId(authToken);
		Users user = userRepo.findByExternalId(externalId);
		if (user == null && ObjectUtils.isEmpty(user)) {
			return new ResponseEntity<String>("user details not found", HttpStatus.OK);
		}
		try {
			Cart cart = cartRepository.findByUser(user, false);
			if (cart == null && ObjectUtils.isEmpty(user)) {
				return new ResponseEntity<String>("cart is empty", HttpStatus.OK);
			}
			List<CartItems> cartItems = cartItemsRepo.findByCartId(cart, false);
			List<CartItemsResponse> response = cartItems.stream().map(cartItem -> cartItemMap(cartItem))
					.collect(Collectors.toList());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private CartItemsResponse cartItemMap(CartItems cartItem) {
		CartItemsResponse cartItemResponse = new CartItemsResponse();
		cartItemResponse.setItems(cartItem.getItemId());
		cartItemResponse.setQuantity(cartItem.getQuantity());
		cartItemResponse
				.setDiscountedPrice(itemService.getDiscountedPrice(cartItem.getItemId()) * cartItem.getQuantity());
		return cartItemResponse;
	}
}
