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
import com.name.blog.web.dto.CommentRequestDTO;
import com.name.blog.web.dto.PostRequestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "comment")
@Where(clause = "delete_yn='N'")
@DynamicInsert
@DynamicUpdate
public class Comment {
	@JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(name = "post_id")
    @NotNull
	private Long postId;
	
    @Column(name = "user_name")
    @NotNull
    @Size(min = 4, max = 20)
	private String userName;
    
    @Column(name = "content")
    @NotNull
    @Size(min = 1, max = 200)
	private String content;
    
    @Column(name = "created_time")
    private String createdTime;
    
    @Column(name = "updated_time")
    private String updatedTime;
    
    @Column(name = "delete_yn")
    @Size(max = 1)
    private String deleteYN;
	
    @Builder
    public Comment(String userName, Long postId, String content, String createdTime, String updatedTime) {
        this.postId = postId;
        this.userName = userName;
        this.content = content;
    	this.createdTime = createdTime;
    	this.updatedTime = updatedTime;
    }
    
    public void changeComment(CommentRequestDTO commentRequestDTO) {
    	this.content = commentRequestDTO.getContent();
    	this.updatedTime = commentRequestDTO.getUpdatedTime();
    }
	
    public void deleteComment() {
    	this.deleteYN="Y";
    }

}
