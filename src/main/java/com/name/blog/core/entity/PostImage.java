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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="post_image")
@Where(clause="delete_yn='N'")
@DynamicInsert
@DynamicUpdate
public class PostImage {
	@JsonIgnore
    @Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "post_id")
	@NotNull
	private Long postId;
	
	@Column(name = "uri")
	@NotNull
	@Size(max = 400)
	private String uri;
	
	@Column(name = "original_name")
	@NotNull
	@Size(max = 260)
	private String originalName;
	
	@Column(name = "changed_name")
	@NotNull
	@Size(max = 260)
	private String changedName;
	
	@Column(name = "delete_yn")
	@Size(max = 1)
	private String deleteYN;
	
    @Builder
    public PostImage(Long postId, String uri, String originalName, String changedName) {
    	this.postId = postId;
        this.uri = uri;
        this.originalName = originalName;
        this.changedName = changedName;
    }
	
}
