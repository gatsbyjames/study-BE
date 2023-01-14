package com.name.blog.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.core.service.dto.CategoryDTO;
import com.name.blog.core.service.dto.CommentDTO;
import com.name.blog.core.service.dto.CommentViewDTO;
import com.name.blog.provider.service.CommentService;
import com.name.blog.provider.service.CommentViewService;
import com.name.blog.web.dto.CommentRequestDTO;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentViewController {
	private final CommentViewService commentViewService;
	
	@GetMapping("/api/v1/post/{post-id}/comments")
	public Optional<List<CommentViewDTO>> getCommentViewsByPostId(@PathVariable("post-id") Long postId
			, @RequestParam(value="page", defaultValue="1") Integer page) {
		return commentViewService.selectCommentViewListByPostId(postId, page);
	}
	
	@GetMapping("/api/v1/post/{post-id}/comments/page-info")
	public Map<String, Object> getCommentViewsPageInfoByPostId(@PathVariable("post-id") Long postId
			, @RequestParam(value="page", defaultValue="1") Integer page) {
		return commentViewService.selectCommentViewCountByPostId(postId, page);
	}
}
