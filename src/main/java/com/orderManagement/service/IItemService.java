package com.orderManagement.service;

import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orderManagement.entity.Items;
import com.orderManagement.model.ItemsResponseDTO;

@Service
public interface IItemService {

//	ResponseEntity<?> addItem(Items item);

	ResponseEntity<?> addItem(Items item, String bearerToken);

//	ResponseEntity<?> getAllItems(String bearerToken);
	List<ItemsResponseDTO> getAllItems();

	List<ItemsResponseDTO> mapToItemsResponse(List<Items> items);

	ItemsResponseDTO getItemById(String uuid);

	ResponseEntity<?> getFromRemoteAPI(String searchVal) throws Exception;

	ResponseEntity<String> removeFromCache();

	ResponseEntity<Cache> getCacheDetails(String name);

//	Future<String> bulkUpload(MultipartFile file);

}
