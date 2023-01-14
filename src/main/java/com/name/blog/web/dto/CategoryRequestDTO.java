package com.name.blog.web.dto;

import com.name.blog.core.entity.Category;
import com.name.blog.core.entity.User;

import lombok.Data;

@Data
public class CategoryRequestDTO {
	private String userName;
	private String name;
	
	public Category toEntity() {
	    return Category.builder()
	            .userName(userName)
	            .name(name)
	            .build();
	   }
}
