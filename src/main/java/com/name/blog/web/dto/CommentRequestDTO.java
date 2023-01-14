package com.name.blog.web.dto;

import com.name.blog.core.entity.Comment;

import lombok.Data;

@Data
public class CommentRequestDTO {
	private String userName;
	private String postId;
	private String content;
	private String createdTime;
	private String updatedTime;
	
	public Comment toEntity() {
		return Comment.builder()
				.postId(Long.valueOf(postId))
				.userName(userName)
				.content(content)
				.createdTime(createdTime)
				.build();
	}
}
