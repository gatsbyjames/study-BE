package com.name.blog.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	boolean existsByUserNameAndId(String userName, Long id);
}	
