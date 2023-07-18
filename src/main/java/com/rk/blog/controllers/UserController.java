package com.rk.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.blog.payloads.ApiResponse;
import com.rk.blog.payloads.UserDto;
import com.rk.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(  @RequestBody UserDto userdto){
		
		UserDto createUserdto = this.userService.create(userdto);
		
		return new ResponseEntity<>(createUserdto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto userdto, @PathVariable Integer userId){
		
		UserDto updateuser = this.userService.updateUser(userdto, userId);
		
		return ResponseEntity.ok(updateuser);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		
		this.userService.deleteUser(userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);

		
	}
	
	@GetMapping("/")
public ResponseEntity<List<UserDto>> getAllUser(){
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		
		return ResponseEntity.ok(this.userService.getuserById(userId));
	}
	
}
