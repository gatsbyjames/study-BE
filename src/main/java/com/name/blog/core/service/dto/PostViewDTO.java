package com.name.blog.core.service.dto;

import com.name.blog.core.entity.PostView;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostViewDTO {
	private String id;
	private String userName;
	private String categoryId;
	private String categoryName;
	private String title;
	private String content;
	private String createdTime;
	private String updatedTime;
	private String thumbnailImageId;
	private String thumbnailImageUri;
	
	public static PostViewDTO of(PostView postView) {
		 return PostViewDTO.builder()
				 .id(String.valueOf(postView.getId()))
				 .userName(postView.getUserName())
				 .categoryId(String.valueOf(postView.getCategoryId()))
				 .categoryName(postView.getCategoryName())
				 .title(postView.getTitle())
				 .content(postView.getContent())
				 .createdTime(postView.getCreatedTime())
				 .updatedTime(postView.getUpdatedTime())
				 .thumbnailImageId(String.valueOf(postView.getThumbnailImageId()))
				 .thumbnailImageUri(postView.getThumbnailImageUri())
				 .build();
	}
}
