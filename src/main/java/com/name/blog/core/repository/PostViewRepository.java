package com.name.blog.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.PostView;

@Repository
public interface PostViewRepository extends JpaRepository<PostView, Long>{
	Optional<List<PostView>> findByUserName(String userName, Pageable paging);
	Optional<List<PostView>> findByCategoryId(Long categoryId, Pageable paging);
	Long countByUserName(String userName);
	Long countByCategoryId(Long categoryId);
}
