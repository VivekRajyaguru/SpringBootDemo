package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

	public String OUTPUT_FOLDER_PATH = "E:/Uploaded_Images/";
	public void uploadFiles(List<MultipartFile> files) throws Exception;
	
}
