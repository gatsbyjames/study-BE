package com.name.blog.web.dto;

import com.name.blog.core.entity.Post;

import lombok.Data;

@Data
public class PostRequestDTO {
	private String userName;
	private String categoryId;
	private String categoryName;
	private String title;
	private String content;
	private String createdTime;
	private String updatedTime;
	
	public Post toEntity() {
		return Post.builder()
				.userName(userName)
				.categoryId(Long.valueOf(categoryId))
				.title(title)
				.content(content)
				.createdTime(createdTime)
				.build();
	}
}
