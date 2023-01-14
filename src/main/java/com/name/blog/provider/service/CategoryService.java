package com.name.blog.provider.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.name.blog.core.entity.Category;
import com.name.blog.core.entity.User;
import com.name.blog.core.repository.CategoryRepository;
import com.name.blog.core.repository.UserRepository;
import com.name.blog.core.service.dto.CategoryDTO;
import com.name.blog.core.service.dto.UserDTO;
import com.name.blog.web.dto.CategoryRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	@Transactional
	public Optional<List<CategoryDTO>> selectCategoryListByUserName(String userName)  {
		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
		
    	List<Category> categoryList = categoryRepository.findByUserName(userName)
    			.orElse(Collections.EMPTY_LIST);

    	for(Category category : categoryList) {
    		categoryDTOList.add(CategoryDTO.of(category));
    	}
		
		return Optional.ofNullable(categoryDTOList);
	}
	
	@Transactional
	public CategoryDTO insertCategory(CategoryRequestDTO categoryRequestDTO) {
		return CategoryDTO.of(categoryRepository.save(categoryRequestDTO.toEntity()));
	}

	@Transactional
	public CategoryDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
		Category category = categoryRepository.findById(id).get();
		category.changeCategory(categoryRequestDTO.getName());
	
		return CategoryDTO.of(categoryRepository.save(category));
	}

	@Transactional
	public CategoryDTO deleteCategory(Long id){
		Category category = categoryRepository.findById(id).get();
		category.deleteCategory();
				
		return CategoryDTO.of(categoryRepository.save(category));
	}

	@Transactional
	public boolean isValidCategory (String userName, Long id) {
		return categoryRepository.existsByUserNameAndId(userName, id);
	}
}
