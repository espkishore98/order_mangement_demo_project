package com.orderManagement.utils;

import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orderManagement.service.AdminService;

@Component
public class UploadUtils implements Callable<String> {
	@Autowired
	AdminService adminService;

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String upload(InputStreamReader inputStreamReader) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		try {
			executorService.execute(() -> {
				try {
					adminService.bulkUpload(inputStreamReader);
				} catch (Exception e) {
					e.printStackTrace();
				}

			});
			return "Upload in progress pls check after sometime";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
