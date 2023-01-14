package com.name.blog.provider.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.name.blog.core.entity.User;
import com.name.blog.core.repository.UserRepository;
import com.name.blog.core.service.dto.UserDTO;
import com.name.blog.web.dto.SignUpRequestDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SignUpService {
	private final UserRepository userRepository;
	
	@Transactional
	public Optional<UserDTO> signUp(SignUpRequestDTO signUpRequestDTO) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//비밀번호 encode
		signUpRequestDTO.setPassword(encoder.encode(signUpRequestDTO.getPassword()));

		User user = userRepository.save(signUpRequestDTO.toEntity());
		UserDTO userDTO = UserDTO.of(user);
		
		return Optional.ofNullable(userDTO);
	}
	
	@Transactional
	public boolean isUniqueUserName (String userName) {
		return !(userRepository.existsByUserName(userName));
	}
}
