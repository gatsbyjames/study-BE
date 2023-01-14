package com.name.blog.web;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.core.service.dto.CategoryDTO;
import com.name.blog.core.service.dto.PostDTO;
import com.name.blog.core.service.dto.UserDTO;
import com.name.blog.provider.service.CategoryService;
import com.name.blog.provider.service.PostService;
import com.name.blog.provider.service.UserService;
import com.name.blog.web.dto.SignUpRequestDTO;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {
	private final UserService userService;
	
	@GetMapping("/api/v1/user/{user-name}")
	public Optional<UserDTO> getUserByUserName(@PathVariable("user-name") String userName) {
		return userService.selectUserByUserName(userName);
	}
	
	@GetMapping("/api/v1/user/{user-name}/check-user")
	public boolean checkUser(@PathVariable("user-name") String userName) {
		return userService.isValidUser(userName);
	}
	
}
