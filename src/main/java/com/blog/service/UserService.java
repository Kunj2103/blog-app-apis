package com.blog.service;

import java.util.List;

import com.blog.payload.UserDto;

public interface UserService {

	UserDto registerUser(UserDto userDto);
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}
