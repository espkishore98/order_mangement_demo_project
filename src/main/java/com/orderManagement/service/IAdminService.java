package com.orderManagement.service;

import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

@Service
public interface IAdminService {

//	Future<String> bulkUpload(MultipartFile file);

	String bulkUpload(InputStreamReader inputStreamReader) throws ExecutionException;

}
