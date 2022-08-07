package com.blog.service;

import java.util.List;

import com.blog.payload.CategoryDto;


public interface CategoryService {

	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	CategoryDto findCategory(Integer categoryId);
	List<CategoryDto> findAllCategory();
	void deleteCategory(Integer categoryId);

}
