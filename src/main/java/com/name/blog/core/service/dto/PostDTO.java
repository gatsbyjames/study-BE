package com.name.blog.core.service.dto;

import com.name.blog.core.entity.Category;
import com.name.blog.core.entity.Post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class PostDTO {
	
	private String id;
	private String categoryId;
	private String categoryName;
	private String userName;
	private String title;
	private String content;
	private String createdTime;
	private String updatedTime;
	
    public static PostDTO of(Post post) {
        return PostDTO.builder()
                .id(String.valueOf(post.getId()))
                .categoryId(String.valueOf(post.getCategoryId()))
                .categoryName(post.getCategoryName())
                .userName(post.getUserName())
                .title(post.getTitle())
                .content(post.getContent())
                .createdTime(post.getCreatedTime())
                .updatedTime(post.getUpdatedTime())
                .build();
    }
}
