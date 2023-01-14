package com.name.blog.provider.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

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
public class AuthTokenService{
    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final static long LOGIN_RETENTION_MINUTES = 90;
    
    public AuthToken createAuthToken(UserDTO userDTO) {
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
        return jwtAuthTokenProvider.createAuthToken(userDTO.getUserName(), userDTO.getRole().getCode(), expiredDate);
    }
}
