package com.name.blog.core.service.dto;

import com.name.blog.core.entity.PostImage;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostImageDTO {
	private String uri;
	
	public static PostImageDTO of(PostImage postImage) {
		 return PostImageDTO.builder()
				 .uri(postImage.getUri())
				 .build();
	}
}
