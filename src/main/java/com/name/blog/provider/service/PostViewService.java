package com.name.blog.provider.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.name.blog.core.Paginator;
import com.name.blog.core.entity.PostView;
import com.name.blog.core.repository.PostRepository;
import com.name.blog.core.repository.PostViewRepository;
import com.name.blog.core.service.dto.PostViewDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostViewService {
	private final Integer pagesPerBlock = 5;
	private final Integer postsPerPage = 5;
	private final PostViewRepository postViewRepository;
	
	@Transactional
	public Optional<List<PostViewDTO>> selectPostViewListByUserName (String userName, Integer page) {
		List<PostViewDTO> postViewDTOList = new ArrayList<PostViewDTO>();
		
		Pageable paging = PageRequest.of(page-1, postsPerPage);
		List<PostView> postViewList = postViewRepository.findByUserName(userName, paging)
				.orElse(Collections.EMPTY_LIST);
				
    	for(PostView postView : postViewList) {
    		postViewDTOList.add(PostViewDTO.of(postView));
    	}
		
		return Optional.ofNullable(postViewDTOList);
	}
	
	@Transactional
	public Map<String, Object> selectPostViewCountByUserName (String userName, Integer page) {
		Long totalPostCount = null;
		
		totalPostCount = postViewRepository.countByUserName(userName);
		Paginator paginator= new Paginator(pagesPerBlock, postsPerPage, totalPostCount);
		Map<String, Object> response = paginator.getFixedBlock(page);
		
		return response;
	}
	
	
	@Transactional
	public Optional<List<PostViewDTO>> selectPostViewListByCategoryId (Long categoryId, Integer page) {
		List<PostViewDTO> postViewDTOList = new ArrayList<PostViewDTO>();
		
		Pageable paging = PageRequest.of(page-1, postsPerPage);
		List<PostView> postViewList = postViewRepository.findByCategoryId(categoryId, paging)
				.orElse(Collections.EMPTY_LIST);
		
    	for(PostView postView : postViewList) {
    		postViewDTOList.add(PostViewDTO.of(postView));
    	}
		
		return Optional.ofNullable(postViewDTOList);
	}
	
	
	@Transactional
	public Map<String, Object> selectPostViewCountByCategoryId (Long categoryId, Integer page) {
		Long totalPostCount = null;

		totalPostCount = postViewRepository.countByCategoryId(categoryId);
		Paginator paginator= new Paginator(pagesPerBlock, postsPerPage, totalPostCount);
		Map<String, Object> response = paginator.getFixedBlock(page);
		
		return response;
	}
}
