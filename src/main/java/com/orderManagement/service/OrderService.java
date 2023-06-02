package com.orderManagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orderManagement.entity.Address;
import com.orderManagement.entity.Cart;
import com.orderManagement.entity.CartItems;
import com.orderManagement.entity.Items;
import com.orderManagement.entity.Order;
import com.orderManagement.entity.OrderTracking;
import com.orderManagement.entity.Price;
import com.orderManagement.entity.Users;
import com.orderManagement.model.ItemsResponseDTO;
import com.orderManagement.model.OrderRequest;
import com.orderManagement.model.OrderResponse;
import com.orderManagement.repository.AddressRepository;
import com.orderManagement.repository.CartItemsRepository;
import com.orderManagement.repository.CartRepository;
import com.orderManagement.repository.OrderTrackRepository;
import com.orderManagement.repository.OrdersRepository;
import com.orderManagement.repository.PriceRepository;
import com.orderManagement.repository.UserRepository;

@Service
public class OrderService implements IOrderService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	ItemService itemService;
	@Autowired
	CartItemsRepository cartItemsRepository;
	@Autowired
	OrdersRepository ordersRepository;
	@Autowired
	PriceRepository priceRepository;
	@Autowired
	OrderTrackRepository orderTrackRepo;
	@Autowired
	EmailService emailService;

	@Override
	public ResponseEntity<?> placeOrder(OrderRequest request, String bearerToken) {
		try {
			OrderTracking orderTrack = new OrderTracking();
			String authToken = bearerToken.substring(7);
			String externalId = UserService.getUserExternalId(authToken);
			Users user = userRepository.findByExternalId(externalId);
			if (user == null) {
				return new ResponseEntity<String>("user not found", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Cart cart = cartRepository.findByUser(user, false);
			if (cart.getCartId() != null) {
				List<CartItems> cartItems = cartItemsRepository.findByCartId(cart, false);
				Optional<Address> address = addressRepository.findById(request.getAddressId());
				if (cart != null) {
					Order order = new Order();
					order.setCartId(cart);
					order.setUser(user);
					order.setAddressId(address.get());
					order.setStatus("Submitted");
//					cartItems.stream().
//					order.setTotalPrice(itemService.getDiscountedPrice(cartItem.getItemId()) * cartItem.getQuantity());
					order.setTotalPrice(totalOrderPrice(cartItems));
					order.setPaymentStatus("Payment recieved");
					order.setDeleted(false);
					ordersRepository.save(order);
					cart.setDeleted(true);
					cartRepository.save(cart);
					cartItems = cartItems.stream().map(cartItem -> cartItemMap(cartItem)).collect(Collectors.toList());
					cartItemsRepository.saveAll(cartItems);
					orderTrack.setOrderStage("SUBMITTED");
					orderTrack.setOrderId(order);
					orderTrackRepo.save(orderTrack);
					emailService.sendEmail("ORDER_CREATION", bearerToken);

				}

			}
			return new ResponseEntity<String>("ORDER PLACED SUCCESSFULLY", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	private CartItems cartItemMap(CartItems cartItem) {
		// TODO Auto-generated method stubs
		cartItem.setIsDeleted(true);
		return cartItem;
	}

	private Long totalOrderPrice(List<CartItems> cartItems) {
		Long totalPrice = cartItems.stream()
				.mapToLong(cartItem -> (itemService.getDiscountedPrice(cartItem.getItemId()) * cartItem.getQuantity()))
				.reduce(0, Long::sum);

//				cartItem -> {
//			Long ItemPrice = itemService.getDiscountedPrice(cartItem.getItemId()) * cartItem.getQuantity();
//			totalPrice = +ItemPrice;
//		})
		return totalPrice;
	}

	@Override
	public ResponseEntity<?> getAllOrders(String bearerToken) {
		String authToken = bearerToken.substring(7);
		String externalId = UserService.getUserExternalId(authToken);
		Users user = userRepository.findByExternalId(externalId);
		Order order = ordersRepository.findByUser(user, false);
		OrderResponse orderResponse = new OrderResponse();
		List<Items> cartItems = cartItemsRepository.findItemsByCartId(order.getCartId().getCartId(), false);
		List<ItemsResponseDTO> items = cartItems.stream().map(cartItem -> itemsMap(cartItem))
				.collect(Collectors.toList());
		orderResponse.setItems(items);
		orderResponse
				.setTotalOrderCost(items.stream().map(item -> item.getDiscountedPrice()).reduce(0L, (a, b) -> a + b));
		return new ResponseEntity<Object>(orderResponse, HttpStatus.OK);
	}

	private ItemsResponseDTO itemsMap(Items cartItem) {
		ItemsResponseDTO itemResp = new ItemsResponseDTO();
		BeanUtils.copyProperties(cartItem, itemResp);
		itemResp.setActualPrice(getItemPrice(cartItem));
		itemResp.setDiscountedPrice(getDiscountedPrice(cartItem));
		return itemResp;
	}

	private Long getItemPrice(Items item) {
		Long itemPrice = priceRepository.findByItem(item).getAmount();
		return itemPrice;
	}

	public Long getDiscountedPrice(Items item) {
		Price price = priceRepository.findByItem(item);
		Long itemDiscountPrice = (price.getAmount()
				* (100 - (price.getDiscount() != null ? price.getDiscount().getPercentageOfDiscount() : 0))) / 100;
		return itemDiscountPrice;
	}

	@Override
	public ResponseEntity<?> cancelOrder(String bearerToken) {
		String authToken = bearerToken.substring(7);
		String externalId = UserService.getUserExternalId(authToken);
		Users user = userRepository.findByExternalId(externalId);
		Order order = ordersRepository.findByUser(user, false);
		List<OrderTracking> orderTrackHstry = orderTrackRepo.findByOrderId(order);
		boolean isDispatched = orderTrackHstry.stream()
				.anyMatch(orderJrny -> orderJrny.getOrderStage().equalsIgnoreCase("dispatched"));
		if (isDispatched) {
			return new ResponseEntity<String>("Order cannot be canceled after dispatching", HttpStatus.OK);
		}
		order.setDeleted(true);
		ordersRepository.saveAndFlush(order);
		return new ResponseEntity<Object>("order cancelled successfully", HttpStatus.OK);
	}

}
