package com.name.blog.provider.security;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

import com.name.blog.core.security.AuthTokenProvider;

@Slf4j
public class JwtAuthTokenProvider implements AuthTokenProvider<JwtAuthToken> {
    private final Key key;

    public JwtAuthTokenProvider(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public JwtAuthToken createAuthToken(String id, String role, Date expiredDate) {
        return new JwtAuthToken(id, role, expiredDate, key);
    }

    @Override
    public JwtAuthToken convertAuthToken(String token) {
        return new JwtAuthToken(token, key);
    }
}