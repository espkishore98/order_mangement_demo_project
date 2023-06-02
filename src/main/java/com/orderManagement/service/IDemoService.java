package com.orderManagement.service;

import java.util.List;

import org.springframework.data.history.Revision;

import com.orderManagement.demo.entity.Demo;
import com.orderManagement.model.DemoRespDTO;

public interface IDemoService {
	List<Demo> getAll();

	Demo saveDemo(Demo demo);

	String updateDemo(Long id, Demo demo);

	String delete(Long ids);

	List<Revision<Integer, Demo>> getHistory();

	List<DemoRespDTO> getHistoryById(Long demoId);

	Demo getDemoById(Long id);

	Demo getDemoByVersion(Long id, Long versionId);

//	Demo getDemoByVersion(Long id, Number versionId);
}
