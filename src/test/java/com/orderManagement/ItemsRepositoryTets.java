package com.orderManagement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.orderManagement.entity.Items;
import com.orderManagement.repository.ItemsRepository;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemsRepositoryTets {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	ItemsRepository itemsRepository;

	@Test
	public void should_find_all_items() {
		Items it1 = new Items("watch", "123advswe", "fast watch", "watches", "AVAILABLE", 10L);
		entityManager.persist(it1);

		Items it2 = new Items("bottle", "adad212xad", "fast bottle", "Bottles", "AVAILABLE", 10L);
		entityManager.persist(it2);

		Items it3 = new Items("shoes", "adad1233411ff", "fast shoes", "shoes", "AVAILABLE", 10L);
		entityManager.persist(it3);

		Iterable items = itemsRepository.findAll();

		assertThat(items).hasSize(3).contains(it1, it2, it3);

	}

}
