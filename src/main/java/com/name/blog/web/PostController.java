package com.name.blog.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.core.Paginator;
import com.name.blog.core.entity.Post;
import com.name.blog.core.service.dto.PostDTO;
import com.name.blog.provider.service.PostService;
import com.name.blog.web.dto.PostRequestDTO;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostController {
	private final PostService postService;
	
	@GetMapping("/api/v1/post/{id}")
	public Optional<PostDTO> getPost(@PathVariable("id") Long id) {
		return postService.selectPostByPostId(id);
	}
	
	@PostMapping("/api/v1/post")
	@Auth(roles = {Role.USER})
	public PostDTO createPost() {
		return postService.insertPost();
	}

	@PutMapping("/api/v1/post/{id}")
	@Auth(roles = {Role.USER})
	public PostDTO revisePost(@PathVariable("id") Long id, @RequestBody PostRequestDTO postRequestDTO) {
		return postService.updatePostById(id, postRequestDTO);
	}
	
	@DeleteMapping("/api/v1/post/{id}")
	@Auth(roles = {Role.USER})
	public PostDTO removePost(@PathVariable("id") Long id) {
		return postService.deletePostById(id);
	}
	
	@GetMapping("/api/v1/user/{user-name}/post/{id}/check-post")
	public boolean checkPost(@PathVariable("user-name") String userName, @PathVariable("id") Long id) {
		return postService.isValidPost(userName, id);
	}
}
