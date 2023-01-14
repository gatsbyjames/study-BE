package com.name.blog.provider.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.name.blog.core.Paginator;
import com.name.blog.core.entity.Category;
import com.name.blog.core.entity.Post;
import com.name.blog.core.entity.PostView;
import com.name.blog.core.repository.CategoryRepository;
import com.name.blog.core.repository.PostRepository;
import com.name.blog.core.repository.PostViewRepository;
import com.name.blog.core.service.dto.CategoryDTO;
import com.name.blog.core.service.dto.PostDTO;
import com.name.blog.core.service.dto.PostViewDTO;
import com.name.blog.web.dto.CategoryRequestDTO;
import com.name.blog.web.dto.PostRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	@Transactional
	public Optional<PostDTO> selectPostByPostId (Long id) {
	
		Post post = postRepository.findById(id).orElseGet(Post::new);
		
		PostDTO postDTO = PostDTO.of(post);
		
		return Optional.ofNullable(postDTO);
	}
	
	@Transactional
	public PostDTO insertPost() {
		Post post = new Post();
		
		return PostDTO.of(postRepository.save(post));
	}
	
	@Transactional
	public PostDTO updatePostById(Long id, PostRequestDTO postRequestDTO) {
		Post post = postRepository.findById(id).orElseThrow();
		
		post.changePost(postRequestDTO);
		
		return PostDTO.of(postRepository.save(post));
	}
	
	@Transactional
	public PostDTO deletePostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow();
		
		post.deletePost();
		
		return PostDTO.of(postRepository.save(post));
	}
	
	@Transactional
	public boolean isValidPost (String userName, Long id) {
		return postRepository.existsByUserNameAndId(userName, id);
	}
}
