package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.core.service.dto.PostImageDTO;
import com.name.blog.provider.service.PostImageService;
import com.name.blog.web.dto.PostImageRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostImageController {
	private final PostImageService postImageService;
	
	@PostMapping("/api/v1/post-image")
	@Auth(roles = {Role.USER})
	public PostImageDTO createPostImage(PostImageRequestDTO postImageRequestDTO) {
		return postImageService.insertPostImage(postImageRequestDTO);
	}
}
