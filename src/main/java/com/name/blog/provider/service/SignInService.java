
package com.name.blog.provider.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.name.blog.core.entity.User;
import com.name.blog.core.repository.UserRepository;
import com.name.blog.core.security.AuthToken;
import com.name.blog.core.service.dto.UserDTO;
import com.name.blog.provider.security.JwtAuthTokenProvider;
import com.name.blog.web.dto.SignInRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignInService{
	private final UserRepository userRepository;
    
    @Transactional
    public Optional<UserDTO> signIn(SignInRequestDTO signInRequestDTO) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	UserDTO userDTO = null;
    	
    	User user = userRepository.findByUserName(signInRequestDTO.getUserName()).get();
    	
    	if(encoder.matches(signInRequestDTO.getPassword(), user.getPassword())){
    		userDTO = UserDTO.of(user);
    	}
    	
        return Optional.ofNullable(userDTO);
    }

}