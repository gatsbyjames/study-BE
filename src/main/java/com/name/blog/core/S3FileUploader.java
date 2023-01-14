package com.name.blog.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/* 참조: https://devlog-wjdrbs96.tistory.com/m/323 */
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class S3FileUploader {
//    private final AmazonS3 amazonS3;
//
//    @Value("${cloud.aws.s3.bucket}")
//    public String bucket;  // S3 버킷 이름
//
//    // Multipart를 통해 전송된 파일을 업로드 하는 메소드
//	public Map<String, Object> uploadFile(MultipartFile file) {
//		Map<String, Object> result = new HashMap<>();
//
//		UUID uuid = UUID.randomUUID();
//
//		String originalFileName = file.getOriginalFilename();
//		//changedName 생성 시작
//			//원본 파일 이름에서 확장자 추출
//		String fileNameExtension = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
//			//uuid와 확장자를 결합하여 새로운 파일 이름 생성
//		String changedFileName = uuid.toString() + fileNameExtension;
//		//changedName 생성 끝
//
//		ObjectMetadata objectMetadata = new ObjectMetadata();
//		objectMetadata.setContentLength(file.getSize());
//		objectMetadata.setContentType(file.getContentType());
//		try (InputStream inputStream = file.getInputStream()) {
//			amazonS3.putObject(new PutObjectRequest(bucket, changedFileName, inputStream, objectMetadata));
//		} catch (IOException e) {
//			throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다 (%s)", file.getOriginalFilename()));
//		}
//		result.put("uploadURI", amazonS3.getUrl(bucket, changedFileName).toString());
//		result.put("changedFileName", changedFileName);
//		result.put("originalFileName", originalFileName);
//
//		return result;
//	}
//}
