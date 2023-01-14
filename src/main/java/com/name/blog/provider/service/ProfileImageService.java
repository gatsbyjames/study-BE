package com.name.blog.provider.service;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.name.blog.core.LocalFileUploader;
//import com.name.blog.core.S3FileUploader;
import com.name.blog.core.entity.ProfileImage;
import com.name.blog.core.repository.ProfileImageRepository;
import com.name.blog.core.service.dto.ProfileImageDTO;
import com.name.blog.web.dto.ProfileImageRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileImageService {
	private final LocalFileUploader localFileUploader;
//	private final S3FileUploader s3FileUploader;
	private final ProfileImageRepository profileImageRepository;

	@Value("${local.profile.image.file.upload.path}")
	private String profileImageFileLocalUploadPath;
	
	@Value("${local.profile.image.file.upload.handler.path}")
	private String profileImageFileLocalUploadHandlerPath;

	@Transactional
	public Optional<ProfileImageDTO> selectProfileImageByUserName(String userName) {
		ProfileImage profileImage = profileImageRepository.findTop1ByUserNameOrderByIdDesc(userName).orElseGet(ProfileImage::new);

		ProfileImageDTO profileImageDTO = ProfileImageDTO.of(profileImage);
				
		return Optional.ofNullable(profileImageDTO);
	}
	
	@Transactional
	public ProfileImageDTO insertProfileImage(ProfileImageRequestDTO profileImageRequestDTO) {
		// S3를 사용하여 이미지를 저장하는 경우
//		Map<String, Object> uploadedFileInfo = s3FileUploader.uploadFile(profileImageRequestDTO.getImageFile());

		// 로컬 저장소를 사용하여 이미지를 저장하는 경우
		Map<String, Object> uploadedFileInfo = localFileUploader.uploadFile(profileImageRequestDTO.getImageFile(), profileImageFileLocalUploadPath, profileImageFileLocalUploadHandlerPath);

		ProfileImageDTO profileImageDTO = ProfileImageDTO.of(profileImageRepository.save(ProfileImage.builder()
			.userName(profileImageRequestDTO.getUserName())
			.uri(uploadedFileInfo.get("uploadURI").toString())
			.originalName(uploadedFileInfo.get("originalFileName").toString())
			.changedName(uploadedFileInfo.get("changedFileName").toString())
			.build()
			));

		return profileImageDTO;
	}
}
