package com.name.blog.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.Comment;
import com.name.blog.core.entity.CommentView;

@Repository
public interface CommentViewRepository extends JpaRepository<CommentView, Long> {

	Optional<List<CommentView>> findByPostId(Long postId, Pageable paging);
	Long countByPostId(Long postId);
}
