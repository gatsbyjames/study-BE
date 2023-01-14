package com.name.blog.provider.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.name.blog.core.LocalFileUploader;
//import com.name.blog.core.S3FileUploader;
import com.name.blog.core.entity.PostImage;
import com.name.blog.core.repository.PostImageRepository;
import com.name.blog.core.service.dto.PostImageDTO;
import com.name.blog.web.dto.PostImageRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostImageService {
	private final LocalFileUploader localFileUploader;
//	private final S3FileUploader s3FileUploader;
	private final PostImageRepository postImageRepository;

	@Value("${local.post.image.file.upload.path}")
	private String postImageFileLocalUploadPath;
	
	@Value("${local.post.image.file.upload.handler.path}")
	private String postImageFileLocalUploadHandlerPath;

	@Transactional
	public PostImageDTO insertPostImage(PostImageRequestDTO postImageRequestDTO) {
		// S3를 사용하여 이미지를 저장하는 경우
//		Map<String, Object> uploadedFileInfo = s3FileUploader.uploadFile(postImageRequestDTO.getImageFile());

		// 로컬 저장소를 사용하여 이미지를 저장하는 경우
		Map<String, Object> uploadedFileInfo = localFileUploader.uploadFile(postImageRequestDTO.getImageFile(), postImageFileLocalUploadPath, postImageFileLocalUploadHandlerPath);

		PostImageDTO postImageDTO = PostImageDTO.of(postImageRepository.save(PostImage.builder()
			.postId(postImageRequestDTO.getPostId())
			.uri(uploadedFileInfo.get("uploadURI").toString())
			.originalName(uploadedFileInfo.get("originalFileName").toString())
			.changedName(uploadedFileInfo.get("changedFileName").toString())
			.build()
			));

		return postImageDTO;
	}
}
