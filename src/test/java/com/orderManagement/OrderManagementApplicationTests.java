package com.orderManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.orderManagement.entity.Items;
import com.orderManagement.model.ItemsResponseDTO;
import com.orderManagement.repository.ItemsRepository;
import com.orderManagement.repository.PriceRepository;
import com.orderManagement.service.ItemService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderManagementApplicationTests {

	@InjectMocks
	ItemService itemService;

	@Mock
	PriceRepository priceRepository;

	@Mock
	ItemsRepository itemsRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllItems() {
		// Arrange
		List<Items> items = List.of(
				new Items("watch", "123Abcdaa2211@1", "fastrack watch", "smart watch", "Available", new Date(),
						new Date()),
				new Items("watch", "123Abcdaa2211@2", "fossil watch", "smart watch", "Available", new Date(),
						new Date()));
		List<ItemsResponseDTO> itemsResp = List.of(
				new ItemsResponseDTO("watch", "123Abcdaa2211@1", "fastrack watch", "smart watch", "Available", 10000L,
						7500L, new Date(), new Date()),
				new ItemsResponseDTO("watch", "123Abcdaa2211@2", "fossil watch", "smart watch", "Available", 10000L,
						7500L, new Date(), new Date()));
		// Act
		when(itemsRepository.findAll()).thenReturn(items);
		List<ItemsResponseDTO> actualResp = itemService.getAllItems();
		// Assert
		assertEquals(actualResp.size(), itemsResp.size());
	}

	@Test
	public void getItemById() {

		Items mockitem = new Items("watch", "123Abcdaa2211@1", "fastrack watch", "smart watch", "Available", new Date(),
				new Date());
		ItemsResponseDTO itemResMock = new ItemsResponseDTO("watch", "123Abcdaa2211@1", "fastrack watch", "smart watch",
				"Available", null, null, new Date(), new Date());

		when(itemsRepository.findByUuid("123Abcdaa2211@1")).thenReturn(mockitem);
		Items item = itemsRepository.findByUuid("123Abcdaa2211@1");
		ItemsResponseDTO actualResp = itemService.getItemById(item.getUuid());
		assertEquals(itemResMock, actualResp);

	}

//	@Test
//	public void addItem() {
//		// Arrange
//		Items item = new Items("watch", "123Abcdaa2211@1", "fastrack watch", "smart watch", "Available", new Date(),
//				new Date());
//		when(itemsRepository.save(any(Items.class))).thenReturn(item);
////		when(itemService.addItem(any(Items.class), "randomToken")).thenReturn(item);
////		doNothing().when(itemService).addItem(item, "some random Tokem");
//		// Act
////		Assertions.assertNotNull(item.getCategory());
////		Assertions.assertNotNull(item.getItemName());
////		Assertions.assertNotNull(item.getDescription());
////		doReturn("item category is mandatory").when(item.getCategory() == null);
////		doReturn("item Name is mandatory").when(item.getItemName() == null);
////		doReturn("item Description is mandatory").when(item.getDescription() == null);
//
////		doNothing().when(itemsRepository).saveAndFlush(item);
//
////		doReturn("item Added successfully").when(itemService).addItem(item, "randomToken");
//		// assert
//		verify(itemsRepository).save(item);
//
//	}
}
