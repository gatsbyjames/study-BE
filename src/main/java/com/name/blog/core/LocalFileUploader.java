package com.name.blog.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class LocalFileUploader {
	public Map<String, Object> uploadFile(MultipartFile file, String uploadPath, String handlerPath) {
		Map<String, Object> result = new HashMap<>();
		File fileUploadDirectory = new File(uploadPath);
		UUID uuid = UUID.randomUUID();
		
		String originalFileName = file.getOriginalFilename();
		//changedName 생성 시작
			//원본 파일 이름에서 확장자 추출
		String fileNameExtension = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
			//uuid와 확장자를 결합하여 새로운 파일 이름 생성
		String changedFileName = uuid.toString() + fileNameExtension;
		//changedName 생성 끝
		String realURI =fileUploadDirectory + "/" + changedFileName;
		String handlerURI = handlerPath + "/" + changedFileName;
		
		if(!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}
	
		try {
			File uploadFile = new File(realURI);
			file.transferTo(uploadFile);
		} catch (IllegalStateException | java.io.IOException e) {
			e.printStackTrace();
		}
		
		result.put("originalFileName", originalFileName);
		result.put("changedFileName", changedFileName);
		result.put("handlerURI", handlerURI);

		return result;
	}
}
