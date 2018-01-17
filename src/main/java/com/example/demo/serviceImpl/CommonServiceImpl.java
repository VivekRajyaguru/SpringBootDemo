package com.example.demo.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.service.*;


@Service
public class CommonServiceImpl implements CommonService {
 
	private Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class); 
	
	@Override
	public void uploadFiles(List<MultipartFile> files) throws Exception {
		logger.info("Execute Method : uploadFiles");
		for(MultipartFile file: files) {
			if(file.isEmpty()) {
				continue;
			}
			File newFile = new File(OUTPUT_FOLDER_PATH+file.getOriginalFilename());
			if(!newFile.exists())newFile.createNewFile();
			byte[] byteAry = file.getBytes();
			Path path = Paths.get(OUTPUT_FOLDER_PATH+file.getOriginalFilename());
			Files.write(path, byteAry);
		}
	}
	
	
}
