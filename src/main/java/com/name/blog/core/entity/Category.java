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
@Table(name = "category")
@Where(clause = "delete_yn='N'")
@DynamicInsert
@DynamicUpdate
public class Category {
	@JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(name = "user_name")
    @NotNull
    @Size(min = 4, max = 20)
	private String userName;
    
    @Column(name = "name")
    @NotNull
    @Size(max = 10)
	private String name;
    
    @Column(name = "delete_yn")
    @Size(max = 1)
    private String deleteYN;
	
    @Builder
    public Category(String userName, String name) {
        this.userName = userName;
        this.name = name;
    }
    
    public void changeCategory(String name) {
    	this.name = name;
    }
	
    public void deleteCategory() {
    	this.deleteYN="Y";
    }
}
