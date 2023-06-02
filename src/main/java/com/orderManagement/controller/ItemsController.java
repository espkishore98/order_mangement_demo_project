package com.orderManagement.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.orderManagement.model.ItemsResponseDTO;
import com.orderManagement.repository.ItemsRepository;
import com.orderManagement.service.AdminService;
import com.orderManagement.service.IItemService;
import com.orderManagement.utils.UploadUtils;

import constants.Constants;

@RestController
@RequestMapping("/items")
public class ItemsController {
	Logger logger = LoggerFactory.getLogger("ItemsController");

	@Autowired
	IItemService itemService;
	@Autowired
	ItemsRepository itemsRepo;
	@Autowired
	AdminService adminService;
	@Autowired
	UploadUtils uploadUtils;
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	List<? extends Job> job;

//	@GetMapping("/item")
//	public ResponseEntity<?> getAllItems(@RequestHeader("Authorization") String bearerToken) {
//		return itemService.getAllItems();
//	}
	@GetMapping("/item")
	public ResponseEntity<?> getAllItems() {
		List<ItemsResponseDTO> items = itemService.getAllItems();
		if (items.size() != 0 && !CollectionUtils.isEmpty(items)) {
			return new ResponseEntity<Object>(items, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No item found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/item/{uuid}")
	public ResponseEntity<?> getItemById(@PathVariable("uuid") String uuid) {
		ItemsResponseDTO item = itemService.getItemById(uuid);
		if (item != null) {
			return new ResponseEntity<ItemsResponseDTO>(item, HttpStatus.OK);
		}
		return new ResponseEntity<String>(Constants.ExceptionConstants.ITEM_NOT_FOUND, HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/item/{uuid}")
	public ResponseEntity<?> deleteItemByUUid(@PathVariable("uuid") String uuid) {
		try {
			itemsRepo.deleteByUuid(uuid);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("something went wrong", e.getMessage());
			return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
	}

	@PostMapping("/bulkUpload")
	public String bulkupload(@RequestParam("file") MultipartFile file)
			throws UnsupportedEncodingException, IOException, InterruptedException, ExecutionException {
		return uploadUtils.upload(new InputStreamReader(file.getInputStream(), "UTF-8"));

	}

	@GetMapping("/upload")
	public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {

		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(maps);
		logger.info(job.get(0).getName() + "");
		List<Job> secndJob = job.stream().filter(j -> j.getName().equalsIgnoreCase("Cron job"))
				.collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(secndJob) && secndJob != null) {

			org.springframework.batch.core.JobExecution jobExecution = jobLauncher.run(secndJob.get(0), parameters);

			System.out.println("JobExecution: " + jobExecution.getStatus());

			System.out.println("Batch is Running...");
			while (jobExecution.isRunning()) {
				System.out.println("...");
			}
			return jobExecution.getStatus();
		}

		return null;
	}

	@GetMapping("/circuitBreak")
	public ResponseEntity<?> circuitBreakExample(@RequestParam String val) throws Exception {
		return itemService.getFromRemoteAPI(val);
	}

	@GetMapping("/removeFromCache")
	public ResponseEntity<String> removeFromCache() {
		return itemService.removeFromCache();
	}

	@GetMapping("/getCacheData")
	public ResponseEntity<Cache> getCacheData(@RequestParam String name) {
		return itemService.getCacheDetails(name);
	}
}
