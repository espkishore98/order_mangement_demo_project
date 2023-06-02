package com.orderManagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.orderManagement.batch.ExceptionSkipPolicy;
import com.orderManagement.batch.MyBatchConfigurer;
import com.orderManagement.batch.StepSkipListener;
import com.orderManagement.batch.Task;
import com.orderManagement.model.ItemPrice;
import com.orderManagement.repository.ItemsRepository;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackageClasses = MyBatchConfigurer.class)
public class SpringBatchConfig {
	Logger logger = LoggerFactory.getLogger("SpringBatch config");
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	ItemsRepository itemsRepository;

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<ItemPrice> itemReader, ItemProcessor<ItemPrice, ItemPrice> itemProcessor,
			ItemWriter<ItemPrice> itemWriter) {
		Step step = stepBuilderFactory.get("ITEMS-FILE-UPLOAD").<ItemPrice, ItemPrice>chunk(100).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).faultTolerant().skipLimit(10)
				.skip(FlatFileParseException.class).listener(skipListener()).skipPolicy(skipPolicy()).build();
		return jobBuilderFactory.get("ITEMS-UPLOAD").incrementer(new RunIdIncrementer()).start(step).build();
	}

	@Bean
	public FlatFileItemReader<ItemPrice> itemReader() {

		FlatFileItemReader<ItemPrice> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource("src/main/resources/items.csv"));
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<ItemPrice> lineMapper() {
		DefaultLineMapper<ItemPrice> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("itemName", "itemDescription", "category", "status", "uuid", "quantityAvailable",
				"amount", "discount");
		BeanWrapperFieldSetMapper<ItemPrice> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(ItemPrice.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

	@Bean
	public SkipPolicy skipPolicy() {
		return new ExceptionSkipPolicy();
	}

	@Bean
	public SkipListener skipListener() {
		return new StepSkipListener();
	}

	@Bean
	public Job CronJobsch() {
		return jobBuilderFactory.get("Cron job").start(firstStep()).build();

	}

	@Bean
	public Step firstStep() {
		return stepBuilderFactory.get("update job").tasklet(new Task()).build();
	}

}
