package com.name.blog.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.core.service.dto.UserDTO;
import com.name.blog.exception.SignInFailedException;
import com.name.blog.provider.security.JwtAuthToken;
import com.name.blog.provider.service.AuthTokenService;
import com.name.blog.provider.service.SignInService;
import com.name.blog.web.dto.SignInRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class SignInController {
    private final SignInService signInService;
    private final AuthTokenService authTokenService;

    @PostMapping("/api/v1/sign-in")
    public Map<String, Object> signIn(@RequestBody SignInRequestDTO loginRequestDTO) {
    	Map<String, Object> response = new HashMap<>();
    	
        Optional<UserDTO> optionalUserDTO = signInService.signIn(loginRequestDTO);

        if (optionalUserDTO.isPresent()) {
        	
            JwtAuthToken jwtAuthToken = (JwtAuthToken) authTokenService.createAuthToken(optionalUserDTO.get());

            response.put("userName", optionalUserDTO.get().getUserName());
            response.put("accessToken", jwtAuthToken.getToken());
            
            return response;

        } else {
            throw new SignInFailedException();
        }
    }
}
