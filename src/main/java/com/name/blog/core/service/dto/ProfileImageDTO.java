package com.name.blog.core.service.dto;

import com.name.blog.core.entity.ProfileImage;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileImageDTO {
	private String uri;
	
	public static ProfileImageDTO of(ProfileImage profileImage) {
		 return ProfileImageDTO.builder()
				 .uri(profileImage.getUri())
				 .build();
	}
}
