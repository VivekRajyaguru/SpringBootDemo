package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.CommonService;
import com.example.demo.vo.Document;
import com.example.demo.vo.ResponseVO;

@RestController
@PropertySource("classpath:message.properties")
public class Controller {
	
	@Value("${UPLOAD_SUCCESSFULLY}")
	private String uploadMessage;
	
	private Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private CommonService commonService;
	
	private Document document;
	
	@Autowired
	public void setDocument(Document document) {
		this.document = document;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/login")
	public Object login() {
		logger.info("URL : Login");
		ResponseVO<Document> object = new ResponseVO<>();
		object.setStatusCode("200");
		object.setValueObject(new Document("Demo", "Type"));
		
		return object;
	}
	
	
	@RequestMapping(value = "/userList")
	public Object userList() {
		logger.info("URL : USERLIST");
		ArrayList<String> userList = new ArrayList<>();
		userList.add("User1");
		userList.add("User2");
		userList.add("User3");
		return userList;
	}
	
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public ResponseEntity<?> uploadFiles(@RequestParam("fileList") MultipartFile[] fileList) {
		try {
			if(fileList != null) {
				commonService.uploadFiles(Arrays.asList(fileList));
			}
		} catch (Exception ex) {
			logger.error("Error in uploadFiles: "+ex);
			
		}
		return new ResponseEntity<>(document.getTitle()+"_"+document.getType()+"_"+document.toString()+"_"+uploadMessage, HttpStatus.OK);
	}
	
}
