package com.name.blog.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.name.blog.core.security.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user")
@Where(clause="delete_yn='N'")
@DynamicInsert
@DynamicUpdate
public class User {
    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    @NotNull
    @Size(min = 10, max = 100)
    private String password;

    @Column(name = "email", unique = true)
    @NotNull
    @Size(min = 10, max = 40)
    private String email;
    
    @Column(name = "user_name")
    @NotNull
    @Size(min = 4, max = 20)
    private String userName;

    @JsonIgnore
    @Column(name = "role")
    @NotNull
    @Size(min = 4, max = 40)
    private String role;

    @Column(name = "delete_yn")
    @Size(min = 1, max = 1)
    private String deleteYN;
    
    @Builder
    public User(String email, String password, String userName, Role role) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.role = role.getCode();
    }

    public void deleteUser() {
        this.deleteYN="Y";
    }
}