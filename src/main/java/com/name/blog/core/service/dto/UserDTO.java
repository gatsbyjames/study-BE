package com.name.blog.core.service.dto;

import com.name.blog.core.entity.User;
import com.name.blog.core.security.Role;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {

	private String id;
	private String email;
	private String userName;
	private Role role;
	
    public static UserDTO of(User user) {
        return UserDTO.builder()
                .id(String.valueOf(user.getId()))
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(Role.of(user.getRole()))
                .build();
    }
     
}
