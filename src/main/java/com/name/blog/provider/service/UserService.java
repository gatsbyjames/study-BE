package com.name.blog.provider.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.name.blog.core.entity.Post;
import com.name.blog.core.entity.User;
import com.name.blog.core.repository.PostRepository;
import com.name.blog.core.repository.UserRepository;
import com.name.blog.core.service.dto.PostDTO;
import com.name.blog.core.service.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	@Transactional
	public Optional<UserDTO> selectUserByUserName (String userName) {
		User user = userRepository.findByUserName(userName).get();
		
		UserDTO userDTO = UserDTO.of(user);
		
		return Optional.ofNullable(userDTO);
	}
	
	@Transactional
	public boolean isValidUser(String userName) {
		return userRepository.existsByUserName(userName);
	}
}
