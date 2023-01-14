package com.name.blog.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.name.blog.web.dto.PostRequestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="post")
@Where(clause="delete_yn='N'")
@DynamicInsert
@DynamicUpdate
public class Post {
	@JsonIgnore
    @Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "category_id")
	private Long categoryId;
	
    @Column(name = "category_name")
    @Size(min = 1, max = 10)
    private String categoryName;
	
    @Column(name = "user_name")
    @Size(min = 4, max = 20)
    private String userName;
    
    @Column(name = "title")
    @Size(max = 200)
    private String title;
    
    @Column(name = "content")
    @Size(max = 60000)
    private String content;
    
    @Column(name = "created_time")
    private String createdTime;
    
    @Column(name = "updated_time")
    private String updatedTime;
    
    @Column(name = "delete_yn")
    @Size(max = 1)
    private String deleteYN;
    
    @Builder
    public Post(String userName, Long categoryId, String title, String content, String createdTime) {
        this.userName = userName;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
    }
    
    public void changePost(PostRequestDTO postRequestDTO) {
    	this.categoryId = Long.valueOf(postRequestDTO.getCategoryId());
    	this.categoryName = postRequestDTO.getCategoryName();
    	this.userName = postRequestDTO.getUserName();
    	this.title = postRequestDTO.getTitle();
    	this.content = postRequestDTO.getContent();
    	this.createdTime = postRequestDTO.getCreatedTime();
    	this.updatedTime = postRequestDTO.getUpdatedTime();
    }
	
    public void deletePost() {
    	this.deleteYN="Y";
    }
}
