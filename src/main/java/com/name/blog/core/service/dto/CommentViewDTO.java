package com.name.blog.core.service.dto;

import com.name.blog.core.entity.CommentView;
import com.name.blog.core.entity.PostView;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentViewDTO {
	private String id;
	private String postId;
	private String userName;
	private String content;
	private String createdTime;
	private String updatedTime;
	private String profileImageId;
	private String profileImageUri;
	
	public static CommentViewDTO of(CommentView commentView) {
		 return CommentViewDTO.builder()
				 .id(String.valueOf(commentView.getId()))
				 .postId(String.valueOf(commentView.getPostId()))
				 .userName(commentView.getUserName())
				 .content(commentView.getContent())
				 .createdTime(commentView.getCreatedTime())
				 .updatedTime(commentView.getUpdatedTime())
				 .profileImageId(String.valueOf(commentView.getProfileImageId()))
				 .profileImageUri(commentView.getProfileImageUri())
				 .build();
	}
}
