package com.name.blog.web;

import java.util.Collections;
import java.util.List;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.core.service.dto.CategoryDTO;
import com.name.blog.provider.service.CategoryService;
import com.name.blog.web.dto.CategoryRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping("/api/v1/user/{user-name}/categories")
	public List<CategoryDTO> getCategoriesByUserName(@PathVariable("user-name") String userName) {
		return categoryService.selectCategoryListByUserName(userName).orElse(Collections.EMPTY_LIST);
	}

	@PostMapping("/api/v1/category")
	@Auth(roles = {Role.USER})
	public CategoryDTO createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
		return categoryService.insertCategory(categoryRequestDTO);
	}
	
	@PutMapping("/api/v1/category/{id}")
	@Auth(roles = {Role.USER})
	public CategoryDTO reviseCategory(@PathVariable("id") Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
		return categoryService.updateCategory(id, categoryRequestDTO);
	}
	
	@DeleteMapping("/api/v1/category/{id}")
	@Auth(roles = {Role.USER})
	public CategoryDTO removeCategory(@PathVariable("id") Long id){
		return categoryService.deleteCategory(id);
	}
	
	@GetMapping("/api/v1/user/{user-name}/category/{id}/check-category")
	public boolean checkCategory(@PathVariable("user-name") String userName, @PathVariable("id") Long id) {
		return categoryService.isValidCategory(userName, id);
	}
}
