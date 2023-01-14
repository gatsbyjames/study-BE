package com.name.blog.provider.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.name.blog.core.entity.Comment;
import com.name.blog.core.repository.CommentRepository;
import com.name.blog.core.service.dto.CommentDTO;
import com.name.blog.web.dto.CommentRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	
	@Transactional
	public CommentDTO insertComment(@RequestBody CommentRequestDTO commentRequestDTO) {
		return CommentDTO.of(commentRepository.save(commentRequestDTO.toEntity()));
	}
	
	@Transactional
	public CommentDTO deleteCommentById(Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow();
		
		comment.deleteComment();
		
		return CommentDTO.of(commentRepository.save(comment));
	}
	
}
