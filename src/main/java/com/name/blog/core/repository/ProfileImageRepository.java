package com.name.blog.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long>{
	Optional<ProfileImage> findTop1ByUserNameOrderByIdDesc(String userName);
}
