package com.name.blog.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.core.service.dto.UserDTO;
import com.name.blog.exception.SignUpFailedException;
import com.name.blog.provider.security.JwtAuthToken;
import com.name.blog.provider.service.AuthTokenService;
import com.name.blog.provider.service.SignUpService;
import com.name.blog.web.dto.SignUpRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class SignUpController {
	private final SignUpService signUpService;
	private final AuthTokenService authTokenService;
	
	@PostMapping("/api/v1/sign-up")
	public Map<String, Object> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
		Map<String, Object> response = new HashMap<>();
		Optional<UserDTO> optionalUserDTO = signUpService.signUp(signUpRequestDTO);
		
		if (optionalUserDTO.isPresent()) {
			JwtAuthToken jwtAuthToken = (JwtAuthToken) authTokenService.createAuthToken(optionalUserDTO.get());

			response.put("userName", optionalUserDTO.get().getUserName());
			response.put("accessToken", jwtAuthToken.getToken());

			return response;
		} else {
			throw new SignUpFailedException();
		}
	}
	
	@GetMapping("/api/v1/user/{user-name}/check-user-name")
	public boolean checkUserName(@PathVariable("user-name") String userName) {
		return signUpService.isUniqueUserName(userName);
	}
}
