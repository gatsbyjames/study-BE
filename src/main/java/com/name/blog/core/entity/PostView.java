package com.name.blog.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="post_view")
public class PostView {
	@Id
    @Column(name = "id")
	private Long id;
	
    @Column(name = "user_name")
	private String userName;
    
    @Column(name = "category_id")
	private Long categoryId;
    
    @Column(name = "category_name")
	private String categoryName;
    
    @Column(name = "title")
	private String title;
    
    @Column(name = "content")
	private String content;
    
    @Column(name = "created_time")
	private String createdTime;
    
    @Column(name = "updated_time")
	private String updatedTime;
    
    @Column(name = "thumbnail_image_id")
	private Long thumbnailImageId;
    
    @Column(name = "thumbnail_image_uri")
	private String thumbnailImageUri;
}
