package com.name.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.name.blog.provider.security.JwtAuthTokenProvider;

/* 참조: https://brunch.co.kr/@springboot/491 */
@Configuration
public class JwtConfiguration {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtAuthTokenProvider jwtProvider() {
        return new JwtAuthTokenProvider(secret);
    }
}