package com.name.blog.web.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostImageRequestDTO {
	private MultipartFile imageFile;
	private Long postId;
}
