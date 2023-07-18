package com.rk.blog.services;

import java.util.List;

import com.rk.blog.payloads.UserDto;

public interface UserService {

	UserDto create(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getuserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
	UserDto registerNewUser(UserDto user);
	
	
}
