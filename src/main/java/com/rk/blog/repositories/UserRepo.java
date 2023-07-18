package com.rk.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.blog.entites.User;

public interface UserRepo extends JpaRepository<User,Integer> {
  
	Optional<User> findByEmail(String email);
}
