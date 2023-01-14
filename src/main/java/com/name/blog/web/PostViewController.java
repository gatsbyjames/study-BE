package com.name.blog.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.core.service.dto.PostViewDTO;
import com.name.blog.provider.service.PostService;
import com.name.blog.provider.service.PostViewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostViewController {
	private final PostViewService postViewService;
	
	@GetMapping("/api/v1/user/{user-name}/posts")
	public Optional<List<PostViewDTO>> getPostViewsByUserName(@PathVariable("user-name") String userName
			, @RequestParam(value="page", defaultValue="1") Integer page) {
		return postViewService.selectPostViewListByUserName(userName, page);
	}
	
	@GetMapping("/api/v1/user/{user-name}/posts/page-info")
	public Map<String, Object> getPostViewsPageInfoByUserName(@PathVariable("user-name") String userName
			, @RequestParam(value="page", defaultValue="1") Integer page) {
		return postViewService.selectPostViewCountByUserName(userName, page);
	}
	
	@GetMapping("/api/v1/user/{user-name}/category/{category-id}/posts")
	public Optional<List<PostViewDTO>> getPostViewsByCategoryId(@PathVariable("category-id") Long categoryId
			, @RequestParam(value="page", defaultValue="1") Integer page) {
		return postViewService.selectPostViewListByCategoryId(categoryId, page);
	}

	@GetMapping("/api/v1/user/{user-name}/category/{category-id}/posts/page-info")
	public Map<String, Object> getPostViewsPageInfoByCategoryId(@PathVariable("category-id") Long categoryId
			, @RequestParam(value="page", defaultValue="1") Integer page) {
		return postViewService.selectPostViewCountByCategoryId(categoryId, page);
	}
}
