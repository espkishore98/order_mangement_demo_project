package com.orderManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.demo.entity.Demo;
import com.orderManagement.model.DemoRespDTO;
import com.orderManagement.service.IDemoService;

@RestController
public class DemoController {
	@Autowired
	IDemoService demoService;

	@GetMapping("/demo")
	public ResponseEntity<?> getAllDemoItems() {
		List<Demo> demoItems = demoService.getAll();
		if (CollectionUtils.isEmpty(demoItems) && demoItems.isEmpty()) {
			return new ResponseEntity<String>("no record found", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Demo>>(demoItems, HttpStatus.OK);
		}
	}

	@GetMapping("/demo/history")
	public ResponseEntity getDemoHistory() {
		@SuppressWarnings("unchecked")
		List<Revision<Integer, Demo>> demoItems = demoService.getHistory();
		if (CollectionUtils.isEmpty(demoItems) && demoItems.isEmpty()) {
			return new ResponseEntity<String>("no record found", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(demoItems.toString(), HttpStatus.OK);
		}
	}

	@GetMapping("/demo/history/{versionId}")
	public ResponseEntity getDemoHistoryById(@PathVariable Long versionId) {
		List<DemoRespDTO> demoItems = demoService.getHistoryById(versionId);
		if (CollectionUtils.isEmpty(demoItems) && demoItems.isEmpty()) {
			return new ResponseEntity<String>("no record found", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(demoItems, HttpStatus.OK);
		}
	}

	@PostMapping("/demo")
	public ResponseEntity<?> saveDemo(@RequestBody Demo demo) {
		Demo demoItems = demoService.saveDemo(demo);
		if (demoItems == null || ObjectUtils.isEmpty(demoItems)) {
			return new ResponseEntity<String>("Failed with saving demo", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>("saved successfully", HttpStatus.OK);
		}
	}

	@PutMapping("/demo/{id}")
	public ResponseEntity<?> updateDetails(@PathVariable(name = "id") Long id, @RequestBody Demo demo) {
		String resp = demoService.updateDemo(id, demo);
		if (resp != null) {
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/demo/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<String>(demoService.delete(id), HttpStatus.OK);
	}

	@GetMapping("/demo/{id}")
	public ResponseEntity<?> getDemoById(@PathVariable("id") Long id) {
		Demo demoItems = demoService.getDemoById(id);
		if (ObjectUtils.isEmpty(demoItems) && demoItems != null) {
			return new ResponseEntity<String>("no record found", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Demo>(demoItems, HttpStatus.OK);
		}
	}

	@GetMapping("/demo/history/{id}/{versionId}")
	public ResponseEntity<?> getDemoById(@PathVariable("id") Long id, @PathVariable("versionId") Long versionId) {
		Demo demoItems = demoService.getDemoByVersion(id, versionId);
		if (ObjectUtils.isEmpty(demoItems) && demoItems != null) {
			return new ResponseEntity<String>("no record found", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Demo>(demoItems, HttpStatus.OK);
		}
	}
}
