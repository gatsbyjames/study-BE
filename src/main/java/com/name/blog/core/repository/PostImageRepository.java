package com.name.blog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.PostImage;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long>{}
