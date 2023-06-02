package com.orderManagement.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderManagement.entity.Items;
import com.orderManagement.entity.Price;
import com.orderManagement.repository.ItemsRepository;
import com.orderManagement.repository.PriceRepository;

@Service
public class AdminService implements IAdminService {
	Logger logger = LoggerFactory.getLogger("ADminService");

	@Autowired
	ItemsRepository itemsRepository;
	@Autowired
	PriceRepository priceRepository;

	@Override
//	@Async("asyncExecutor")
	@Transactional
	public String bulkUpload(InputStreamReader inputStreamReader) {
		try {
			BufferedReader reader = new BufferedReader(inputStreamReader);
			Stream<String> lines = reader.lines().skip(1);
			List<Price> prices = new ArrayList<Price>();
			lines.forEach(line -> {
				String[] item = line.split(",", -1);
				System.out.println(item[0] + "" + item[1] + "" + item[2] + "" + item[3] + "" + item[4] + "" + item[5]
						+ "" + item[6] + "" + item[7]);
				Items itemObj = new Items(item[0], UUID.randomUUID().toString(), item[1], item[2], item[3],
						item[5] != null ? Long.valueOf(item[5]) : null);
//				Items savedItem = itemsRepository.save(itemObj);
				logger.info("item  saved============>  " + itemObj + new Timestamp(new Date().getTime()));
				Price price = new Price();
				price.setAmount(item[6] != null ? Long.valueOf(item[6]) : null);
				price.setItem(itemObj);
				prices.add(price);

			});

			priceRepository.saveAllAndFlush(prices);
			logger.info("item price saved============>  " + prices + new Timestamp(new Date().getTime()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new String("In process pls check after some time");

	}

}
