package com.rk.blog.payloads;



import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {

	private int id;
	
	@NotEmpty
	
	private String name;
	
	@Email
	@NotEmpty
	private String Email;
	
	@NotEmpty
	private String mobile;
	
	@NotEmpty
	@Size(min=4,message="Password must contain minimum 4 Characters !!")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	
}
