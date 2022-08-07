package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.CategoryDto;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createDto,HttpStatus.CREATED);
	}
	
	@PutMapping("{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
		CategoryDto updateDto = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updateDto,HttpStatus.OK);
	}
	
	@GetMapping("{catId}")
	public ResponseEntity<CategoryDto> getByIdCategory(@PathVariable Integer catId){
		CategoryDto getByIdDto= this.categoryService.findCategory(catId);
		return new ResponseEntity<CategoryDto>(getByIdDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> getAllDto = this.categoryService.findAllCategory();
		return new ResponseEntity<List<CategoryDto>>(getAllDto,HttpStatus.OK);
	}
	
	@DeleteMapping("{catId}")
	public ResponseEntity<ApiResponse> deleteDto(@PathVariable Integer catId) {
		
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
		
	}


}
