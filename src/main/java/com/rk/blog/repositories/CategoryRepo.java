package com.rk.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.blog.entites.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
