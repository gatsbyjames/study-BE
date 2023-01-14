package com.name.blog.provider.security;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.name.blog.core.security.Auth;
import com.name.blog.exception.CustomJwtRuntimeException;
import com.name.blog.exception.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.name.blog.core.security.Role;
import com.name.blog.exception.CustomAuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private static final String AUTHORIZATION_HEADER = "x-auth-token";

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) {
		Optional<String> token = resolveToken(servletRequest);

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        if (auth == null) {
            return true;
        } else {
            return checkSecurity(token, auth.roles());
        }
    }

    private boolean checkSecurity(Optional<String>token, Role[] roles) {
        if (!(token.isPresent())) {
            throw new CustomJwtRuntimeException();
        }

        JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());

        if(Arrays.asList(roles).contains(Role.of((String) jwtAuthToken.getData().get("role")))) {
            if(jwtAuthToken.validate()) {
                return true;
            } else {
                throw new CustomJwtRuntimeException();
            }
        } else {
            throw new CustomAuthenticationException(new Exception("NOT_AUTHORIZED"));
        }
    }

    private Optional<String> resolveToken(HttpServletRequest request) {
        String authToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(authToken)) {
            return Optional.of(authToken);
        } else {
            return Optional.empty();
        }
    }
}
