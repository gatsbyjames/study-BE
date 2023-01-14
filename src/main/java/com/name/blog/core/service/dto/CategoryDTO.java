package com.name.blog.core.service.dto;

import com.name.blog.core.entity.Category;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryDTO {

	private String id;
	private String userName;
	private String name;
	
    public static CategoryDTO of(Category category) {
        return CategoryDTO.builder()
                .id(String.valueOf(category.getId()))
                .userName(category.getUserName())
                .name(category.getName())
                .build();
    }
}
