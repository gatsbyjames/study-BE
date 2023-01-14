package com.name.blog.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.Category;
import com.name.blog.core.entity.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<List<Category>> findByUserName(String userName);
	boolean existsByUserNameAndId(String userName, Long id);
}
