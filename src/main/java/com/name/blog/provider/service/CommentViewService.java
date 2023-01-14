package com.name.blog.provider.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.name.blog.core.Paginator;
import com.name.blog.core.entity.Comment;
import com.name.blog.core.entity.CommentView;
import com.name.blog.core.repository.CommentRepository;
import com.name.blog.core.repository.CommentViewRepository;
import com.name.blog.core.service.dto.CommentDTO;
import com.name.blog.core.service.dto.CommentViewDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentViewService {
	private final Integer pagesPerBlock = 5;
	private final Integer commentsPerPage = 5;
	private final CommentViewRepository commentViewRepository;
	
	@Transactional
	public Optional<List<CommentViewDTO>> selectCommentViewListByPostId(Long postId, Integer page)  {
		List<CommentViewDTO> commentViewDTOList = new ArrayList<CommentViewDTO>();
		
		Pageable paging = PageRequest.of(page-1, commentsPerPage);
    	List<CommentView> commentViewList = commentViewRepository.findByPostId(postId, paging)
    			.orElse(Collections.EMPTY_LIST);

    	for(CommentView commentView : commentViewList) {
    		commentViewDTOList.add(CommentViewDTO.of(commentView));
    	}
		
		return Optional.ofNullable(commentViewDTOList);
	}
	
	@Transactional
	public Map<String, Object> selectCommentViewCountByPostId (Long postId, Integer page) {
		Long totalPostCount = null;
		
		totalPostCount = commentViewRepository.countByPostId(postId);
		Paginator paginator= new Paginator(pagesPerBlock, commentsPerPage, totalPostCount);
		Map<String, Object> response = paginator.getFixedBlock(page);
		
		return response;
	}

}
