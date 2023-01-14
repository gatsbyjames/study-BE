package com.name.blog.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.User;
import com.name.blog.core.service.dto.UserDTO;
import com.name.blog.web.dto.SignInRequestDTO;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameAndPassword(String userName, String Password);
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
