package com.rk.blog.Serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rk.blog.config.AppConstants;
import com.rk.blog.entites.Role;
import com.rk.blog.entites.User;
import com.rk.blog.payloads.UserDto;
import com.rk.blog.repositories.RoleRepo;
import com.rk.blog.repositories.UserRepo;
import com.rk.blog.services.UserService;
import com.rk.blog.exception.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto create(UserDto userdto){
		// TODO Auto-generated method stub
		User user = this.dtotoUser(userdto);
		User saveduser = this.userRepo.save(user);
		return this.usertoDto(saveduser);
	}
 
	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
		
		user.setName(userdto.getName());
		user.setAbout(userdto.getAbout());
		user.setEmail(userdto.getEmail());
		user.setMobile(userdto.getMobile());
		user.setPassword(userdto.getPassword());
		
		User updateusersaved = this.userRepo.save(user);
		
		UserDto updateduserdto= usertoDto(updateusersaved);
		
		return updateduserdto;
	}

	@Override
	public UserDto getuserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
		
		
		
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userdtos = users.stream().map(user->this.usertoDto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
		this.userRepo.delete(user);
		
	}
	
	private User dtotoUser(UserDto userdto) {
		
		User user = this.modelMapper.map(userdto, User.class);
		
		
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setAbout(userdto.getAbout());
//		user.setEmail(userdto.getEmail());
//		user.setMobile(userdto.getMobile());
//		user.setPassword(userdto.getPassword());
		
		
		
		return user;
		
	}
	
private UserDto usertoDto(User user) {
		
		UserDto userdto = this.modelMapper.map(user,UserDto.class);
			return userdto;
		
	}

@Override
public UserDto registerNewUser(UserDto userDto) {

	User user = this.modelMapper.map(userDto, User.class);

	// encoded the password
	user.setPassword(this.passwordEncoder.encode(user.getPassword()));

	// roles
	Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

	user.getRoles().add(role);

	User newUser = this.userRepo.save(user);

	return this.modelMapper.map(newUser, UserDto.class);
}
	
	

}
