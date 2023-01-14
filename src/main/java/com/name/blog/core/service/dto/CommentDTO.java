package com.name.blog.core.service.dto;

import com.name.blog.core.entity.Comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDTO {
	private String postId;
	private String userName;
	private String content;
	private String createdTime;
	private String updatedTime;
	
	public static CommentDTO of(Comment comment) {
	    return CommentDTO.builder()
	    		.postId(String.valueOf(comment.getPostId()))
	            .userName(comment.getUserName())
	            .content(comment.getContent())
	            .createdTime(comment.getCreatedTime())
	            .updatedTime(comment.getUpdatedTime())
	            .build();
	}

}
