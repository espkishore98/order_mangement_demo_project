package com.orderManagement.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orderManagement.config.SpringBatchConfig;
import com.orderManagement.entity.Items;
import com.orderManagement.entity.Price;
import com.orderManagement.model.ItemsResponseDTO;
import com.orderManagement.repository.ItemsRepository;
import com.orderManagement.repository.PriceRepository;
import com.orderManagement.repository.UserRepository;
import com.orderManagement.utils.Mapper;

import constants.Constants;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ItemService implements IItemService {
	Logger logger = LoggerFactory.getLogger("ItemService");
	@Autowired
	ItemsRepository itemsRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PriceRepository priceRepository;
	@Autowired
	Mapper mapper;
	@Autowired
	CacheManager cacheManager;
	@Autowired
	public SpringBatchConfig springBatchConfig;

	ModelMapper modelMapper;

	public void setItemsRepository(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}

	@Override
	@CachePut(cacheNames = "itemsInfo", key = "ItemService_getAllItems_.uuid")
//	@CacheEvict(value = "itemsInfo", allEntries = true) // removing all entries from the cache
	public ResponseEntity<?> addItem(Items item, String bearerToken) {

//		String authToken = bearerToken.substring(7);
//		String externalId = userService.getUserExternalId(authToken);
		logger.info("" + cacheManager.getCache("itemsInfo").toString());
		if (item.getItemName() == null) {
			logger.info("item name is empty");
			return new ResponseEntity<String>(Constants.ItemConstants.ITEM_CATEGORY_MANDATORY, HttpStatus.BAD_REQUEST);
		}
		if (item.getDescription() == null) {
			logger.info("item description is empty");
			return new ResponseEntity<String>(Constants.ItemConstants.ITEM_DESCRIPTION_MANDATORY,
					HttpStatus.BAD_REQUEST);
		}
		if (item.getCategory() == null) {
			logger.info("item category is empty");
			return new ResponseEntity<String>(Constants.ItemConstants.ITEM_CATEGORY_MANDATORY, HttpStatus.BAD_REQUEST);
		}
		try {
			if (item.getUuid() != null) {
				Items itemDtls = itemsRepository.findByUuid(item.getUuid());
				if (itemDtls != null) {
					itemDtls.setItemName(item.getItemName());
					itemDtls.setStatus(item.getStatus());
					itemDtls.setDescription(item.getDescription());
					itemDtls.setCategory(item.getCategory());
					itemDtls.setUpdatedDate(new Date());
					itemsRepository.save(itemDtls);
					return new ResponseEntity<String>(" Item Details updated successfully", HttpStatus.OK);
				}
			} else {
				item.setUuid(UUID.randomUUID().toString());
				item.setCreatedDate(new Date());
				item.setUpdatedDate(new Date());
				itemsRepository.save(item);
				return new ResponseEntity<String>(" Item Details saved successfully", HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error with adding item", e.getMessage());
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return null;
	}

	@Override
	@Cacheable(value = "itemsInfo", keyGenerator = "myKeyGenerator", sync = true)
	public List<ItemsResponseDTO> getAllItems() {
		logger.info("fetch all items started");
		Pageable paging = PageRequest.of(0, 200);
		Page<Items> items = itemsRepository.findAll(paging);
		logger.info("items from repository ");
		logger.info("" + Thread.activeCount());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		if (!items.isEmpty() && items != null && !CollectionUtils.isEmpty(items.getContent())) {
			logger.info("" + dtf.format(now));
			return items.stream()
					.map(item -> new ItemsResponseDTO(item.getItemName(), item.getUuid(), item.getDescription(),
							item.getCategory(), item.getStatus(), getItemPrice(item), getDiscountedPrice(item),
							item.getCreatedDate(), item.getUpdatedDate()))
					.collect(Collectors.toList());

		} else {
			logger.info("" + dtf.format(now));
			return null;

		}

	}

	@Override
	public List<ItemsResponseDTO> mapToItemsResponse(List<Items> items) {
		return items.stream()
				.map(item -> new ItemsResponseDTO(item.getItemName(), item.getUuid(), item.getDescription(),
						item.getCategory(), item.getStatus(), getItemPrice(item), getDiscountedPrice(item),
						item.getCreatedDate(), item.getUpdatedDate()))
				.collect(Collectors.toList());

	}

	private Long getItemPrice(Items item) {
		logger.info("object id od priceRepo" + priceRepository);
		Long itemPrice = priceRepository.findByItem(item).getAmount();
		return itemPrice;
	}

	public Long getDiscountedPrice(Items item) {
		logger.info("getItem Discounted price Started");
		Price price = priceRepository.findByItem(item);
		Long itemDiscountPrice = (price.getAmount()
				* (100 - (price.getDiscount() != null ? price.getDiscount().getPercentageOfDiscount() : 0))) / 100;
		return itemDiscountPrice;
	}

	@Override
	public ItemsResponseDTO getItemById(String uuid) {
		Items item = itemsRepository.findByUuid(uuid);
		if (item != null && !ObjectUtils.isEmpty(item)) {
			ItemsResponseDTO itemsResponse = new ItemsResponseDTO();
			BeanUtils.copyProperties(item, itemsResponse);
			return itemsResponse;
		} else {
			return null;
		}
	}

//	@Scheduled(cron = "* * * * * * ")
////	@Scheduled(fixedDelay = 1000, initialDelay = 1000)
//	public void cronJobSch() {
//		logger.info("availability check started============>  " + new Timestamp(new Date().getTime()));
//		List<Items> itemstoUpdate = new ArrayList<>();
//		Pageable pages = PageRequest.of(0, 5);
//		Page<Items> items = itemsRepository.findAll(pages);
//		itemstoUpdate = updateList(items.getContent());
//		itemsRepository.saveAll(items);
//	}
//
//	private List<Items> updateList(List<Items> items) {
//		items.stream().filter(i -> i.getQuantityAvailable() == 0).forEach(i -> {
//			i.setStatus("UNAVIALBLE");
//		});
//		items.stream().filter(i -> i.getQuantityAvailable() != 0).forEach(i -> {
//			i.setStatus("AVIALBLE");
//		});
//		return items;
//	}

//	@Scheduled(cron = "* * * * * * ")
//	@Scheduled(fixedDelay = 1000, initialDelay = 1000)
	public Job CronJobsch() {
		return springBatchConfig.CronJobsch();

	}

	public void updateList() {
		logger.info("update method started");
		List<Items> itemstoUpdate = new ArrayList<>();
		Pageable pages = PageRequest.of(0, 5);
		Page<Items> items = itemsRepository.findAll(pages);

		items.stream().filter(i -> i.getQuantityAvailable() == 0).forEach(i -> {
			i.setStatus("UNAVIALBLE");
		});
		items.stream().filter(i -> i.getQuantityAvailable() != 0).forEach(i -> {
			i.setStatus("AVIALBLE");
		});

		itemsRepository.saveAll(items.getContent());
	}

	@Override
	@CircuitBreaker(name = "myProjectAllRemoteCallsCB", fallbackMethod = "getAPIFallBack")
	public ResponseEntity getFromRemoteAPI(String searchVal) throws Exception {
		logger.info("remote call started");
		String response = "";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8082/convert")
				.queryParam("val", searchVal);
		URI uri = builder.build().toUri();
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> response1 = restTemplate.getForEntity(uri, String.class);
			response = response1.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception();
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getAPIFallBack(String topicPage, Exception e) {
		logger.error("getAPIFallBack::{}", e);
		return new ResponseEntity<String>("Server down try after sometime", HttpStatus.OK);
	}

	@Override
	@CacheEvict(value = "itemsInfo", allEntries = true) // removing all entries from the cache
	public ResponseEntity<String> removeFromCache() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public ResponseEntity<Cache> getCacheDetails(String name) {
		return new ResponseEntity(cacheManager.getCache(name), HttpStatus.OK);
	}
}
